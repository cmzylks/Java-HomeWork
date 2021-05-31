package java6683.lesson12;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Elxect
 * @date 2021/5/30 11:41 上午
 */
public class SimpleStream6683 {
	public static void main(String[] args) {
		int sum = getRandomInt(0, 60, 40).stream()
						.distinct()
						.filter(SimpleStream6683::isPrime)
						.sorted()
						.mapToInt(item -> {
							System.out.print(item + "   ");
							return item;
						}).sum();
		System.out.println("=" + sum);
	}

	/**
	 * 生成随机数
	 *
	 * @param start 起始
	 * @param end   结束
	 * @param count 个数
	 * @return 随机数集
	 */
	public static List<Integer> getRandomInt(int start, int end, int count) {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			int num = (int) (start + Math.random() * (end - start + 1));
			list.add(num);
		}
		return list;
	}


	public static boolean isPrime(int num) {
		if (num == 1) {
			return true;
		} else if (num % 2 == 0) {
			return false;
		} else {
			for (int i = 3; i * i <= num; i += 2) {
				if (num % i == 0) {
					return false;
				}
			}
		}
		return true;
	}

}
