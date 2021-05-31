package java6683.task;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Elxect
 * @date 2021/5/31 9:16 上午
 */
public class Counter6683 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("请输入一段字符串:");
		String strings = sc.nextLine().trim();
		//将String转成流,每个字符作为流中的一个元素
		Stream<String> stringStream = Stream.of(strings.split(""));
		//收集每个字符，统计次数，返回map集合
		Map<String, Long> countString = stringStream.collect(
						Collectors.groupingBy(String::toString, Collectors.counting()));
		//对map集合排序
		Map<String, Long> result = countString.entrySet().stream()
						.sorted(Map.Entry.comparingByKey())
						.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
										(oldValue, newValue) -> oldValue, LinkedHashMap::new));
		System.out.println(result);
	}
}
