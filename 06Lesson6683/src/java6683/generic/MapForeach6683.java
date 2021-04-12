package java6683.generic;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Elxect
 * @date 2021/4/12 2:57 下午
 */
public class MapForeach6683 {
	public static void main(String[] args) {
		Map<String, String> stringHashMap = new HashMap<>();
		stringHashMap.put("一", "壹");
		stringHashMap.put("二", "贰");
		stringHashMap.put("三", "叁");
		stringHashMap.put("四", "肆");
		stringHashMap.put("五", "伍");
		stringHashMap.put("九", "久");
		stringHashMap.replace("九", "玖");
		stringHashMap.remove("五");
		System.out.println("====Key的Set集合====");
		print6683(stringHashMap);
		System.out.println("====Set<Map.Entry<K,V>>的集合====");
		print6683(stringHashMap.entrySet());
	}

	public static void print6683(Map<String, String> hashMap) {
		Set<String> keys = hashMap.keySet();
		for (String key : keys) {
			System.out.println(hashMap.get(key) + " ");
		}
	}

	public static void print6683(Set<Map.Entry<String, String>> items) {
		for (Map.Entry<String, String> item : items) {
			System.out.println(item.getKey() + "=" + item.getValue());
		}
	}
}
