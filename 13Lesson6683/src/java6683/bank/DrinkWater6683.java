package java6683.bank;

public class DrinkWater6683 {
	private static Integer waters;

	public DrinkWater6683(int waters) {
		DrinkWater6683.waters = waters;
	}

	public static void main(String[] args) {
		DrinkWater6683.waters = 20;
		new Thread(DrinkWater6683::drinking6683, "狗").start();
		new Thread(DrinkWater6683::drinking6683, "猫").start();
	}

	public static void drinking6683() {
		String name = Thread.currentThread().getName();
		while (waters > 0) {
			synchronized (waters) {
				if (name.equals("狗") && waters >= 5) {
					waters = waters - 5;
				} else if (name.equals("猫") && waters >= 2) {
					waters = waters - 2;
				} else {
					System.out.println(name + "请加水！！");
					return;
				}
				System.out.println(name + "已喝水,剩" + waters);
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
