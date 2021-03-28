package java6683.lesson04;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @author Elxect
 * @date 2021/3/28 10:00 下午
 */
public class Foreach6683 {
	public static void main(String[] args) {
		List<Student6683> student6683s = new ArrayList<>();
		Student6683 s1 = new Student6683("张三", 22);
		Student6683 s2 = new Student6683("李四", 23);
		Student6683 s3 = new Student6683("王五", 24);
		Student6683 s4 = new Student6683("赵六", 25);
		student6683s.add(s1);
		student6683s.add(s2);
		student6683s.add(s3);
		student6683s.add(s4);
		System.out.print("迭代器的方式遍历集合：");
		seIterator6683(student6683s);
		System.out.print("增强for的方式遍历集合：");
		useForeach6683(student6683s);
		System.out.print("序号的方式遍历集合：");
		useList6683(student6683s);
		System.out.print("采用数组的方式遍历：");
		useArray6683(student6683s);
	}

	/**
	 * @param students 学生对象集合
	 * @description 采用迭代器的方式遍历集合对象
	 */
	public static void seIterator6683(Collection<Student6683> students) {
		Iterator<Student6683> student6683Iterator = students.iterator();
		while (student6683Iterator.hasNext()) {
			Student6683 student6683 = student6683Iterator.next();
			System.out.print(student6683 + " ");
		}
		System.out.println();
	}

	/**
	 * @param students 学生对象集合
	 * @description 采用增强for的方式遍历集合对象
	 */
	public static void useForeach6683(Collection<Student6683> students) {
		for (Student6683 student : students) {
			System.out.print(student);
		}
		System.out.println();
	}

	/**
	 * @param students 学生对象集合
	 * @description 采用序号遍历输出
	 */
	public static void useList6683(List<Student6683> students) {
		for (int i = 0; i < students.size(); i++) {
			System.out.print(students.get(i));
		}
		System.out.println();
	}

	/**
	 * @param students 学生对象集合
	 * @description 采用数组遍历集合
	 */
	public static void useArray6683(Collection<Student6683> students) {
		Student6683[] student6683s = students.toArray(new Student6683[0]);
		for (int i = 0; i < student6683s.length; i++) {
			System.out.print(student6683s[i]);
		}
	}
}
