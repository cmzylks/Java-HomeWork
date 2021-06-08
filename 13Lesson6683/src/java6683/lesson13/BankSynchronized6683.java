package java6683.lesson13;

public class BankSynchronized6683 {
	/**
	 * 银行账户对象
	 */
	private static BankAccount6683 account;

	public static void main(String[] args) throws InterruptedException {
		account = new BankAccount6683("212006683", 1000.0);
		Thread t1 = new Thread(BankSynchronized6683::bank6683, "会计");
		Thread t2 = new Thread(BankSynchronized6683::bank6683, "出纳");
		System.out.println(account.getBankID() + "账上开始余额：" + account.getBalance() + "元");
		long start = System.currentTimeMillis();
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		long end = System.currentTimeMillis();
		System.out.println(account.getBankID() + "账上最终余额：" + account.getBalance() + "元");
		System.out.println("用时:" + (end - start) * 0.001 + "秒");
	}

	public static void bank6683() {
		String name = Thread.currentThread().getName();
		String action = "";
		double money = 0, b;
		if ("会计".equals(name)) {
			money = 300;
			action = "存入";
		} else if ("出纳".equals(name)) {
			money = -100;
			action = "取出";
		}
		for (int i = 1; i <= 3; i++) {
			b = account.getBalance() + money;
			System.out.println(name + action + Math.abs(money));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (account){
				account.setBalance(b);
			}
		}
	}
}
