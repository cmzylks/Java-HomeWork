package java6683.lesson12;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * @author Elxect
 * @date 2021/5/30 11:41 上午
 */
public class SimpleStream6683 {
	public static void main(String[] args) {
		Random random = new Random();
		int sum = IntStream.generate(() -> random.nextInt(61))
						.limit(40)
						.distinct()
						.filter(SimpleStream6683::isPrime)
						.sorted()
						.peek(item -> System.out.print(item + " ")).sum();
		System.out.println("=" + sum);
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
