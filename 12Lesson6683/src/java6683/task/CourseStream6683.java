package java6683.task;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * @author Elxect
 */
public class CourseStream6683 {

	public static void main(String[] args) {
		//收集数据中的学期
		Stream<String> terms = getCourses().stream()
						.map(Course6683::getTerm)
						.distinct();
		terms.forEach(CourseStream6683::semesterGroup);
	}

	/**
	 * 显示每学期的课程
	 *
	 * @param term 学期
	 */
	public static void semesterGroup(String term) {
		//根据学期收集课程
		List<Course6683> oneList = getCourses().stream()
						.filter(item -> term.equals(item.getTerm()))
						.sorted(Comparator.comparing(Course6683::getScore))
						.collect(Collectors.toList());
		//计算平均值
		OptionalDouble average = oneList.stream()
						.mapToInt(Course6683::getScore)
						.average();
		System.out.println("====" + term + "====");
		if (average.isPresent()) {
			System.out.println("共" + oneList.size() + "课程，平均分：" + average.getAsDouble());
		}
		oneList.forEach(System.out::println);
	}

	private static List<Course6683> getCourses() {
		List<Course6683> courses = new ArrayList<>();
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
			return no + " " + name + "\t\t" + term + "," + credit + "," + score;
		}
	}

}
