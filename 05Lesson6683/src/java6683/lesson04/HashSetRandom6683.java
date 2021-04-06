package java6683.lesson04;

import java.util.HashSet;
import java.util.Random;

/**
 * @author Elxect
 */
public class HashSetRandom6683 {
	private static final int MAX = 80;
	private static final int MIN = 50;

	public static void main(String[] args) {
		HashSet<Integer> random = createRandom();
		//增强for输出
		for (Integer integer : random) {
			System.out.println(integer);
		}
	}

	/**
	 * 产生10个50～80之间的随机数存储在HashSet中
	 *
	 * @return 返回HashSet集合
	 */
	public static HashSet<Integer> createRandom() {
		//初始化HashSet集合用来存储随机数
		HashSet<Integer> list = new HashSet<>();
		Random random = new Random();
		//随机数的个数
		int num = 10;
		while (num > 0) {
			//产生随机数
			int randomNum = random.nextInt(MAX - MIN + 1) + MIN;
			//如果这个随机数添加失败重新生成
			if (!list.add(randomNum)) {
				continue;
			}
			//成功则下一个
			num--;
		}
		return list;
	}
}
