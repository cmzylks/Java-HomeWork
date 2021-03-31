package java6683.group;

/**
 * @author Elxect
 * @date 2021/3/31 9:55 上午
 */
public class Student6683 {
	private String name;

	public Student6683() {
	}

	public Student6683(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
