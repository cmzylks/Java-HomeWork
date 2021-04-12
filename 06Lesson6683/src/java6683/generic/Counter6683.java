package java6683.generic;

import java.util.*;

/**
 * @author Elxect
 * @date 2021/4/12 7:57 上午
 */
public class Counter6683 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("请输入字符串：");
		//去除空格
		String str = sc.nextLine().trim().replaceAll(" ", "");
		TreeMap<Character, Integer> treeMap = count6683(str);
		List<Map.Entry<Character, Integer>> list = new ArrayList<>(treeMap.entrySet());
		list.sort(new Sorter6683());
		list.forEach(System.out::println);
	}

	static class Sorter6683 implements Comparator<Map.Entry<Character, Integer>> {

		@Override
		public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
			//根据字符升序
			int num = o1.getKey() - o2.getKey();
			//根据次数降序
			return num == 0 ? o2.getValue() - o1.getValue() : num;
		}
	}

	/**
	 * @param sc String 输入的字符串
	 * @return 返回TreeMap集合
	 */
	public static TreeMap<Character, Integer> count6683(String sc) {
		TreeMap<Character, Integer> treeMap = new TreeMap<>();
		//将字符串转换成字符数组
		char[] chars = sc.toCharArray();
		//遍历字符数组
		for (char aChar : chars) {
			//默认的值为1
			int i = 1;
			//如果存在该字符则覆盖值
			i = treeMap.merge(aChar, i, Integer::sum);
			//put到集合中
			treeMap.put(aChar, i);
		}
		return treeMap;
	}
}
