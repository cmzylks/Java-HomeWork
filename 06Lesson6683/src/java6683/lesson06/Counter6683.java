package java6683.lesson06;

import java.util.Scanner;
import java.util.TreeMap;

/**
 * @author Elxect
 * @date 2021/4/12 7:57 上午
 */
public class Counter6683 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//去除空格
		String str = sc.nextLine().trim().replaceAll(" ", "");
		TreeMap<Character, Integer> treeMap = count6683(str);
		System.out.println(treeMap);
	}

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
