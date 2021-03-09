package java6683.lesson01;

import java.util.Arrays;

/**
 * @author Elxect
 * 超市类
 */
public class Market6683 {
	/**
	 * 超市名
	 */
	private String name;
	/**
	 * 超市所售商品
	 */
	private Product6683[] productList;

	public Market6683() {
	}

	public Market6683(String name, Product6683[] productList) {
		this.name = name;
		this.productList = productList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Product6683[] getProductList() {
		return productList;
	}

	/**
	 * @param productList 设置超市所售商品
	 */
	public void setProductList(Product6683[] productList) {
		this.productList = productList;
	}

	/**
	 * @param name 用户所买的商品名
	 * @return 返回所购买的商品
	 */
	public Product6683 sell(String name) {
		//查找超市所售商品
		for (int i = 0; i < productList.length; i++) {
			//所售商品中是否有用户所需要的
			if (productList[i].getName().equals(name)) {
				//返回商品
				return productList[i];
			}
		}
		return null;
	}

	/**
	 * @param products 用户所以购买的商品清单
	 * @return 返回所购买的商品
	 */
	public String sell(Product6683[] products) {
		//存储用户购买的商品的篮子
		StringBuilder product = new StringBuilder();
		//查找用户购买商品清单
		for (int i = 0; i < products.length; i++) {
			//查找超市商品清单
			for (int j = 0; j < productList.length; j++) {
				//超市中是否有用户所需要购买的商品
				if (productList[j].equals(products[i])) {
					//添加到篮子中
					product.append(products[i].getName()).append(",");
				}
			}
		}
		//去除最后一个,
		product.deleteCharAt(product.length() - 1);
		//返回商品
		return product.toString();
	}

	@Override
	public String toString() {
		return name + Arrays.toString(productList);
	}
}
