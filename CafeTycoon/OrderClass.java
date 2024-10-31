package CafeTycoon;

import java.util.*;

public class OrderClass {

	private static final int GOAL = 10000;
	private int money = 0;
	private int failCount = 0;
	private final List<MenuItem> menu = new ArrayList<>();
	private final Scanner scanner = new Scanner(System.in);

	public int getMoney() {
		return money;
	}

	public boolean isGoalReached() {
		return money >= GOAL;
	}

	public void initializeMenu() {
		menu.add(new Coffee("아메리카노", 5000, Arrays.asList("물", "샷")));
		menu.add(new Coffee("라떼", 7000, Arrays.asList("우유", "샷")));
		menu.add(new Coffee("카푸치노", 8000, Arrays.asList("우유", "샷", "시럽")));
		menu.add(new Coffee("모카", 9000, Arrays.asList("초콜릿", "우유", "샷")));
		menu.add(new Coffee("프라푸치노", 10000, Arrays.asList("얼음", "우유", "샷", "휘핑")));
	}

	public void printMenu() {
		System.out.println("\n-메뉴판-");
		for (int i = 0; i < menu.size(); i++) {
			MenuItem item = menu.get(i);
			System.out.println((i + 1) + ". " + item.getName() + " - " + item.getPrice() + "원");
			System.out.println("   레시피 : " + item.getRecipe());
		}
		System.out.println("\n***주의 사항*** 숫자만 입력해 주세요.");
	}

	public boolean takeOrder() {
		MenuItem order = menu.get(new Random().nextInt(menu.size()));
		System.out.println("고객이 " + order.getName() + "을(를) 주문했습니다. 가격은 $" + order.getPrice() + "입니다.");
		System.out.println("1. 주문 승낙하기");
		System.out.println("2. 주문 거절하기");
		System.out.print("선택하세요: ");

		int decision = getUserChoice();
		switch (decision) {
			case 1:
				System.out.println("'" + order.getName() + "' 제조를 시작합니다!");
				return prepareCoffee(order);
			case 2:
				System.out.println("주문을 거절하여 게임이 종료됩니다.");
				System.exit(0);
				return false;
			default:
				System.out.println("잘못된 선택입니다.");
		}
		return true;
	}

	private int getUserChoice() {
		try {
			return Integer.parseInt(scanner.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("올바른 숫자를 입력하세요.");
			return -1;
		}
	}

	private boolean prepareCoffee(MenuItem order) {
		List<String> recipe = order.getRecipe();
		System.out.println("레시피를 입력하세요 (재료를 공백으로 구분하여 입력):");

		String input = scanner.nextLine();
		List<String> inputIngredients = Arrays.asList(input.split(" "));

		if (new HashSet<>(inputIngredients).equals(new HashSet<>(recipe))) {
			money += order.getPrice();
			System.out.println("\n제조 성공! $" + order.getPrice() + "를 획득했습니다. 현재 자금: $" + money);
			return true;
		} else {
			failCount++;
			System.out.println("\n레시피 입력 실패! 현재 실패 횟수: " + failCount);
			if (failCount >= 3) {
				System.out.println("3회 실패하여 게임이 종료됩니다.");
				System.exit(0);
				return false;
			}
		}
		return true;
	}
}