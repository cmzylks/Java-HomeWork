package java6683.bank;

public class ThreadAndRunnable6683 {
	public static void main(String[] args) {
		TicketRunnable6683 task = new TicketRunnable6683();
		Thread t1 = new Thread(task, "郑逢1");
		Thread t2 = new Thread(task, "郑逢2");
		Thread t3 = new Thread(task, "郑逢3");
		t1.start();
		t2.start();
		t3.start();
	}
}


class TicketRunnable6683 implements Runnable {
	private Integer tickets = 1;

	@Override
	public void run() {
		while (tickets <= 8) {
			synchronized (tickets) {
				String name = Thread.currentThread().getName();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(name + ":第" + tickets++ + "张票");
			}
		}
	}
}