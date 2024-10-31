package CafeTycoon;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameClass {
	static Scanner scanner = new Scanner(System.in);
	static List<MenuItem> menu = new ArrayList<>();
	
	private TimeCount timeCount;
	private OrderClass orderClass;
	
	public GameClass () {
		timeCount = new TimeCount();
		orderClass = new OrderClass();
		orderClass.initializeMenu();
	}

	public void startGame() {
		System.out.println("카페 타이쿤 게임에 오신 것을 환영합니다!");
		orderClass.printMenu();

		while (true) {
			if (orderClass.isGoalReached()) {
				System.out.println("\n축하합니다! 목표 금액을 달성하여 게임이 종료됩니다.");
				timeCount.shutdownScheduler();
				System.exit(0);
				break;
			}
			
			timeCount.firstTimeout();

			System.out.println("\n현재 자금: $" + orderClass.getMoney());
			System.out.println("1. 주문 받기");
			System.out.println("2. 종료");
			System.out.println("선택하세요: ");

			int choice = getUserChoice();
			switch (choice) {
				case 1:
					if (!orderClass.takeOrder()) break;
					break;
				case 2:
					System.out.println("게임을 종료합니다.");
					timeCount.shutdownScheduler();
					System.exit(0);
					return;
				default:
					System.out.println("잘못된 선택입니다.");
			}
		}
		scanner.close();
	}

	private int getUserChoice() {
		try {
			return Integer.parseInt(scanner.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("올바른 숫자를 입력하세요.");
			return -1;
		}
	}
}
