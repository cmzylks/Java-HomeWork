package java6683.lesson13;

public class ThreadAndRunnable6683 {
	public static void main(String[] args) {
//		TicketThread6683.saleByThread6683();
		TicketRunnable6683.saleByThread6683();
	}
}

class TicketThread6683 extends Thread {
	private int tickets = 1;

	public TicketThread6683(String threadName) {
		super(threadName);
	}

	@Override
	public void run() {
		while (tickets <= 8) {
			String name = Thread.currentThread().getName();
			System.out.println(name + ":第" + tickets++ + "张票");
		}
	}

	public static void saleByThread6683() {
		TicketThread6683 t1 = new TicketThread6683("郑逢1");
		TicketThread6683 t2 = new TicketThread6683("郑逢2");
		TicketThread6683 t3 = new TicketThread6683("郑逢3");
		t1.start();
		t2.start();
		t3.start();
	}
}


class TicketRunnable6683 implements Runnable {
	private int tickets = 1;

	@Override
	public void run() {
		while (tickets <= 8) {
			String name = Thread.currentThread().getName();
			System.out.println(name + ":第" + tickets++ + "张票");
		}
	}

	public static void saleByThread6683() {
		TicketRunnable6683 task = new TicketRunnable6683();
		new Thread(task, "郑逢1").start();
		new Thread(task, "郑逢2").start();
		new Thread(task, "郑逢3").start();
	}
}