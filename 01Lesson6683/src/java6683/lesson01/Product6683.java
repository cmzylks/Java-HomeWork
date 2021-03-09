package java6683.lesson01;

import java.util.Objects;

/**
 * @author Elxect
 * 商品类
 */
public class Product6683 {
	/**
	 * 商品名称
	 */
	private String name;

	public Product6683() {
	}

	public Product6683(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		if (o instanceof Product6683) {
			Product6683 that = (Product6683) o;
			return Objects.equals(name, that.name);
		}
		return false;
	}

	@Override
	public String toString() {
		return name;
	}
}
