package java6683.lesson01;

import java.util.Arrays;

/**
 * @author Elxect
 * @date 2021/3/8 12:33 下午
 */
public class Shopping6683 {
	public static void main(String[] args) {
		//超市商品列表
		Product6683[] marketProducts = {new Product6683("豆浆机"), new Product6683("吹风机")};
		//创建超市对象
		Market6683 market6683 = new Market6683();
		market6683.setName("永辉超市");
		//添加商品
		market6683.setProductList(marketProducts);

		//创建客户对象
		Person6683 p1 = new Person6683("张木强");
		//创建客户购买清单
		Product6683[] shopList = {new Product6683("豆浆机"), new Product6683("吹风机")};
		System.out.println(p1.getName() + "的购物清单：" + Arrays.toString(shopList));
		System.out.println(p1.getName() + "在" + market6683.getName() + "买到了:" + p1.shopping(market6683, shopList));
	}
}
