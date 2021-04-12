package java6683.generic;

/**
 * @author Elxect
 * @date 2021/4/12 3:25 下午
 */
public class MyMath6683<T extends Comparable<T>> {
	private T data;

	public MyMath6683() {
	}

	public MyMath6683(T data) {
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	T max6683(T data) {
		return this.data.compareTo(data) > 0 ? this.data : data;
	}

	T min6683(T data) {
		return this.data.compareTo(data) < 0 ? this.data : data;
	}
}
