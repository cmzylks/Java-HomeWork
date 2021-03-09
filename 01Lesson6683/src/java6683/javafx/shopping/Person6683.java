package java6683.javafx.shopping;

/**
 * @author Elxect
 * 客户类
 */
public class Person6683 {
	/**
	 * 客户姓名
	 */
	private String name;

	public Person6683() {
	}

	public Person6683(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param market 选择购买超市
	 * @param name   购买商品
	 * @return 返回购买的商品
	 */
	public Product6683 shopping(Market6683 market, String name) {
		return market.sell(name);
	}

	/**
	 * @param market   选择购买超市
	 * @param products 用户购买清单
	 * @return 返回用户所买到的商品
	 */
	public String shopping(Market6683 market, Product6683[] products) {
		return market.sell(products);
	}

	@Override
	public String toString() {
		return name;
	}
}
