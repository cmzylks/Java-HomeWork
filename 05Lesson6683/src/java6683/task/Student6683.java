package java6683.task;

import java.util.Objects;

/**
 * @author Elxect
 * @date 2021/4/6 9:09 上午
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
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Student6683)) return false;
		Student6683 that = (Student6683) o;
		return age == that.age &&
						Objects.equals(name, that.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, age);
	}

	@Override
	public String toString() {
		return name + "，" + age;
	}
}
