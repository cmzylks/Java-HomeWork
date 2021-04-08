package java6683.sort.list;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleGroup;

import java.util.Arrays;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListSort6683ViewController {

	@FXML
	private ListView<Course6683> lvScore;

	@FXML
	private ToggleGroup lvRadios;

	public List<Course6683> courseList = Arrays.asList(
					new Course6683(78, "2上", 3, "A003", "数据库原理"),
					new Course6683(78, "2上", 3, "A002", "面向对象程序设计"),
					new Course6683(80, "1上", 3, "0001", "大学英语"),
					new Course6683(80, "1上", 4, "A001", "C程序设计基础"),
					new Course6683(75, "3上", 3, "B003", "网络基础"),
					new Course6683(83, "3上", 2, "C001", "JAVA程序高级开发"),
					new Course6683(85, "1下", 2, "0002", "高等数学"),
					new Course6683(75, "3下", 4, "C002", "软件工程与UML"),
					new Course6683(90, "2下", 3, "B001", "操作系统"),
					new Course6683(85, "1下", 2, "B002", "Python程序基础")
	);

	public ObservableList<Course6683> items;

	private int anInt = -1;

	@FXML
	void initialize() {
		items = lvScore.getItems();
		items.addAll(courseList);
		//默认执行的排序方法
		scoreSort();
		//监听选择的按钮
		lvRadios.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
			//获取选中的索引
			anInt = Integer.parseInt(newValue.getUserData().toString());
			//根据索引执行不同的排序方式
			switch (anInt) {
				case 0:
					scoreSort();
					break;
				case 1:
					termSort();
					break;
				case 2:
					creditSort();
					break;
				case 3:
					termCreditSort();
					break;
			}
		});
	}

	void scoreSort() {
		//自然排序
		Collections.sort(courseList);
		items.setAll(courseList);
	}

	void termSort() {
//		使用比较器排序 使用匿名内部类
		courseList.sort(new Comparator<Course6683>() {
			@Override
			public int compare(Course6683 o1, Course6683 o2) {
				return o1.getTerm().compareTo(o2.getTerm());
			}
		});
		items.setAll(courseList);
	}

	void creditSort() {
//		使用比较器排序 使用比较器类
		courseList.sort(new SorterByCredit6683());
		items.setAll(courseList);
	}

	void termCreditSort() {
//		使用比较器排序	使用方法引用
		courseList.sort(Comparator.comparing(Course6683::getTerm).thenComparing(Course6683::getCredit));
		items.setAll(courseList);
	}

	static class SorterByCredit6683 implements Comparator<Course6683> {
		@Override
		public int compare(Course6683 o1, Course6683 o2) {
			return o1.getCredit() - o2.getCredit();
		}
	}
}
