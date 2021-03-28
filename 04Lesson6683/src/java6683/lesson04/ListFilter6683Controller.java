package java6683.lesson04;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Elxect
 * @date 2021/3/28 10:33 下午
 */
public class ListFilter6683Controller {
	public ListView<Integer> lvAll;
	public Label lbl5;
	public ListView<Integer> lv5;

	public void buttonClick(ActionEvent actionEvent) {
		//接收生成的20个随机数的整数集合
		List<Integer> list6683 = createList6683();
		//接收能被5整除的数的集合
		List<Integer> fiveList = findList6683(list6683);
		//监听存放20个随机数的列表
		ObservableList<Integer> items = lvAll.getItems();
		//监听存放5的倍数的随机数的列表
		ObservableList<Integer> fiveItems = lv5.getItems();
		//先清空列表
		items.clear();
		fiveItems.clear();
		//渲染列表
		lbl5.setText("其中：5的倍数有"+fiveList.size()+"个");
		items.addAll(list6683);
		fiveItems.addAll(fiveList);
	}

	/**
	 * @return list集合
	 * @description 产生20个[60, 100]的整数，存储到集合中
	 */
	public List<Integer> createList6683() {
		//创建一个空集合
		List<Integer> list = new ArrayList<>();
		//实例化随机数对象
		Random random = new Random();
		//随机数个数
		int number = 20;
		//生成20个[60-100]的整数随机数
		for (int i = 0; i < number; i++) {
			int randomNum = random.nextInt(41) + 60;
			//将每个随机添加到集合中
			list.add(randomNum);
		}
		//返回集合对象
		return list;
	}

	/**
	 * @param list 20随机数的集合
	 * @return 集合对象
	 * @description 筛选出是5的倍数的数存入集合中
	 */
	public List<Integer> findList6683(List<Integer> list) {
		//创建一个空集合
		List<Integer> fiveNumList = new ArrayList<>();
		//遍历20随机数的集合
		for (Integer integer : list) {
			//筛选出能被5整除的数
			if (integer % 5 == 0) {
				//添加到集合
				fiveNumList.add(integer);
			}
		}
		//返回这个集合对象
		return fiveNumList;
	}
}
