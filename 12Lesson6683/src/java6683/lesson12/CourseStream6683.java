package java6683.lesson12;


import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.OptionalInt;


/**
 * @author Elxect
 */
public class CourseStream6683 {

	public static void main(String[] args) {
		System.out.println("=====85分以上的课程名单=====");
		long count = getCourses().stream().filter(item -> item.getScore() >= 85).count();
		System.out.println("共" + count + "门");
		getCourses().stream().filter(item -> item.getScore() >= 85).forEach(System.out::println);
		OptionalDouble average = getCourses().stream().mapToInt(Course6683::getScore).average();
		if (average.isPresent()) {
			System.out.printf("【1】所有的课程的平均值：%.2f\n", average.getAsDouble());
		}
		System.out.println("【2】最高分的课程：");
		OptionalInt max = getCourses().stream().mapToInt(Course6683::getScore).max();
		if (max.isPresent()) {
			getCourses().stream().filter(item -> item.getScore() == max.getAsInt()).forEach(System.out::println);
		}
		System.out.println("【3】最低分的课程：");
		OptionalInt min = getCourses().stream().mapToInt(Course6683::getScore).min();
		if (min.isPresent()) {
			getCourses().stream().filter(item -> item.getScore() == min.getAsInt()).forEach(System.out::println);
		}

	}

	private static List<Course6683> getCourses() {
		List<Course6683> courses = new ArrayList<Course6683>();
		courses.add(new Course6683("A001", "C程序设计基础", "1上", 4, 80));
		courses.add(new Course6683("A004", "离散数学", "1下", 3, 79));
		courses.add(new Course6683("B002", "Pthon程序基础", "1下", 2, 85));
		courses.add(new Course6683("A002", "面向对象程序设计", "2上", 3, 78));
		courses.add(new Course6683("C012", "软件测试", "3下", 2, 72));
		courses.add(new Course6683("C001", "Java程序高级开发", "3上", 2, 83));
		courses.add(new Course6683("0003", "大学物理", "2上", 2, 77));
		courses.add(new Course6683("C002", "软件工程", "3下", 3, 75));
		courses.add(new Course6683("C011", "移动项目开发", "3下", 2, 87));
		courses.add(new Course6683("B001", "操作系统", "2下", 3, 90));
		courses.add(new Course6683("A005", "算法与数据结构", "2上", 3, 82));
		courses.add(new Course6683("A003", "数据库原理", "2上", 3, 78));
		courses.add(new Course6683("C003", "前端开发技术", "3上", 3, 69));
		courses.add(new Course6683("B003", "网络基础", "2下", 3, 75));
		courses.add(new Course6683("0001", "大学英语", "1上", 3, 80));
		courses.add(new Course6683("0002", "高等数学", "1下", 2, 85));
		courses.add(new Course6683("B004", "计算机网络", "1下", 4, 68));
		courses.add(new Course6683("B005", "计算机数组原理", "2上", 4, 78));
		return courses;
	}

	private static class Course6683 {
		private String no;//课程编号
		private String name;//课程名称
		private String term;//开课学期
		private int credit;//学分
		private int score;//成绩

		public Course6683() {
			super();
		}

		public Course6683(String no, String name, String term, int credit, int score) {
			super();
			this.no = no;
			this.name = name;
			this.term = term;
			this.credit = credit;
			this.score = score;
		}

		public String getNo() {
			return no;
		}

		public void setNo(String no) {
			this.no = no;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getTerm() {
			return term;
		}

		public void setTerm(String term) {
			this.term = term;
		}

		public int getCredit() {
			return credit;
		}

		public void setCredit(int credit) {
			this.credit = credit;
		}

		public int getScore() {
			return score;
		}

		public void setScore(int score) {
			this.score = score;
		}

		@Override
		public String toString() {
			return no + " " + name + "," + term + "," + credit + "," + score;
		}
	}

}
