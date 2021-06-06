package java6683.lesson13;

public class DrinkWater6683 {
	private static int waters;

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
			if (name.equals("狗")) {
				waters = waters - 5;
			} else if (name.equals("猫")) {
				waters = waters - 2;
			}
			System.out.println(name + "已喝水,剩" + waters);
		}
		System.out.println(name + "请加水！！");
	}
}
