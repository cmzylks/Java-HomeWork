package java6683.generic;

/**
 * @author Elxect
 * @date 2021/4/12 3:38 下午
 */
public class DemoMyMath6683 {
	public static void main(String[] args) {
		MyMath6683<String> string = new MyMath6683<>("aaa");
		MyMath6683<Integer> integer = new MyMath6683<>(111);
		MyMath6683<Character> character = new MyMath6683<>('a');
//		MyMath6683<LocalDate> localDate = new MyMath6683<LocalDate>("2021");
		System.out.println("两个字符串的Max：" + string.max6683("sss"));
		System.out.println("两个字符串的Min：" + string.min6683("sss"));
		System.out.println("两个Integer的Max:" + integer.max6683(345));
		System.out.println("两个Integer的Min:" + integer.min6683(345));
		System.out.println("两个Character的Max:" + character.max6683('z'));
		System.out.println("两个Character的Min:" + character.min6683('z'));
	}
}
