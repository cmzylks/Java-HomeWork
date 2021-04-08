package java6683.sort.set;

import java.util.*;

public class SetSort6683 {
	public static void main(String[] args) {
		Collection<Course6683> courseList = Arrays.asList(
						new Course6683(78, "2上", 3, "A003", "数据库原理"),
						new Course6683(78, "2上", 3, "A002", "面向对象程序设计"),
						new Course6683(80, "1上", 3, "0001", "大学英语"),
						new Course6683(80, "1上", 4, "A001", "C程序设计基础"),
						new Course6683(75, "3上", 3, "B003", "网络基础"),
						new Course6683(83, "3上", 2, "C001", "JAVA程序高级开发"),
						new Course6683(85, "1下", 3, "0002", "高等数学"),
						new Course6683(75, "3下", 4, "C002", "软件工程与UML"),
						new Course6683(90, "2下", 3, "B001", "操作系统"),
						new Course6683(85, "1下", 2, "B002", "Python程序基础")
		);
		//成绩排序
		courseSort(courseList);
		//学期排序
		termSort(courseList);
		//学分排序
		creditSort(courseList);
		//学期学分排序
		termCreditSort(courseList);
	}

	public static void courseSort(Collection<Course6683> courseList) {
		System.out.println("==============按成绩降序==============");
		//使用自然排序
		TreeSet<Course6683> list = new TreeSet<>();
		list.addAll(courseList);
		list.forEach(System.out::println);
	}

	public static void termSort(Collection<Course6683> courseList) {
		System.out.println("======按学期升序=======");
		//使用比较器排序 使用匿名内部类
		TreeSet<Course6683> list = new TreeSet<>(new Comparator<Course6683>() {
			@Override
			public int compare(Course6683 o1, Course6683 o2) {
				//先比较学期
				int num = o1.getTerm().compareTo(o2.getTerm());
				int num2 = num == 0 ? o1.getCredit() - o2.getCredit() : num;
				int num3 = num2 == 0 ? o1.getScore() - o2.getScore() : num2;
				return num3 == 0 ? o1.getNo().compareTo(o2.getNo()) : num3;
			}
		});
		list.addAll(courseList);
		list.forEach(System.out::println);
	}

	public static void creditSort(Collection<Course6683> courseList) {
		System.out.println("==============按学分升序==============");
		//使用比较器排序 使用比较器类
		TreeSet<Course6683> list = new TreeSet<>(new SorterByCredit6683());
		list.addAll(courseList);
		list.forEach(System.out::println);
	}

	public static void termCreditSort(Collection<Course6683> courseList) {
		System.out.println("=======多关键字排序：学期、学分升序==========");
		//使用比较器排序 使用方法引用
		TreeSet<Course6683> list = new TreeSet<>(
						//首先使用学期排序，接着使用学分排序
						Comparator.comparing(Course6683::getTerm)
										.thenComparing(Course6683::getCredit)
										//再根据自然排序
										.thenComparing(Course6683::compareTo)
		);
		list.addAll(courseList);
		list.forEach(System.out::println);
	}

	static class SorterByCredit6683 implements Comparator<Course6683> {
		@Override
		public int compare(Course6683 o1, Course6683 o2) {
			//先比较学分
			int num = o1.getCredit() - o2.getCredit();
			int num2 = num == 0 ? o1.getTerm().compareTo(o2.getTerm()) : num;
			int num3 = num2 == 0 ? o1.getScore() - o2.getScore() : num2;
			return num3 == 0 ? o1.getNo().compareTo(o2.getNo()) : num3;
		}
	}
}
