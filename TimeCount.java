package CafeTycoon;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimeCount {
	public final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	public void firstTimeout() {
		scheduler.schedule(() -> {
			System.out.println("주문을 받지 않아 게임이 종료됩니다.");
			System.exit(0);
		}, 600, TimeUnit.SECONDS);
	}

	public void shutdownScheduler() {
		if (!scheduler.isShutdown()) {
			scheduler.shutdown();
		}
	}
}
