package java6683.lesson04;

/**
 * @author 郑逢
 * @date 2021/3/28 10:00 下午
 */
public class Student6683 {
	private String name;
	private int age;

	public Student6683() {
	}

	public Student6683(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return name + ":" + age;
	}
}
