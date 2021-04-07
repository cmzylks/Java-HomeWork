package java6683.task;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author Elxect
 * @date 2021/4/6 9:03 上午
 */
public class NoRepeatStudent6683Controller {
	@FXML
	public TextField tfName;
	@FXML
	public TextField tfAge;
	@FXML
	public ListView<Student6683> lvStudent;

	private ObservableList<Student6683> items;

	public List<Student6683> student6683s;

	private int index;

	@FXML
	void initialize() {
		//初始化渲染列表
		Collection<Student6683> list = Arrays.asList(
						new Student6683("张三", 15),
						new Student6683("李四", 18),
						new Student6683("王五", 20),
						new Student6683("刘能", 40)
		);
		student6683s = new ArrayList<>(list);
		items = lvStudent.getItems();
		items.addAll(student6683s);
		selectShow();
	}

	public void addClick(ActionEvent actionEvent) {
		//去除空格
		String name = tfName.getText().trim().replaceAll(" ", "");
		String age = tfAge.getText().trim().replaceAll(" ", "");
		//输入的格式是否正确
		if (isNull(name, age)) {
			return;
		}
		//添加新对象
		items.add(new Student6683(name, Integer.parseInt(age)));
	}

	public void changeClick(ActionEvent actionEvent) {
		String name = tfName.getText().trim().replaceAll(" ", "");
		String age = tfAge.getText().trim().replaceAll(" ", "");
		if (isNull(name, age)) {
			return;
		}
		//获取当前选中索引
		index = lvStudent.getSelectionModel().getSelectedIndex();
		//获取当前选中对象
		Student6683 selectedItem = lvStudent.getSelectionModel().getSelectedItem();
		//修改内容
		selectedItem.setName(name);
		selectedItem.setAge(Integer.parseInt(age));
		//重新渲染
		items.set(index, selectedItem);
	}

	public void clearClick(ActionEvent actionEvent) {
		//创建新集合
		Collection<Student6683> newArrays = new ArrayList<>();
		//遍历旧集合去除重复的对象
		for (Student6683 item : items) {
			if (!newArrays.contains(item)) {
				newArrays.add(item);
			}
		}
		//重新渲染
		items.setAll(newArrays);
		lvStudent.getSelectionModel().selectFirst();
	}

	/**
	 * 在ListView选中某项，对应的姓名和年龄显示在文本框中
	 */
	void selectShow() {
		//默认为第一项，并显示姓名年龄
		lvStudent.getSelectionModel().selectFirst();
		index = lvStudent.getSelectionModel().getSelectedIndex();
		tfName.setText(items.get(index).getName());
		tfAge.setText(String.valueOf(items.get(index).getAge()));
		//监听选项变化，并实时改变文本框内容
		lvStudent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				tfName.setText(newValue.getName());
				tfAge.setText(String.valueOf(newValue.getAge()));
			}
		});
	}

	/**
	 * 判断输入的内容是否合理
	 *
	 * @param name 姓名
	 * @param age  年龄
	 * @return true 不合理  false 合理
	 */
	boolean isNull(String name, String age) {
		//捕获输入的年龄不正常的情况
		try {
			Integer.parseInt(age);
		} catch (NumberFormatException nfe) {
			alartShow("请输入正确的年龄！");
			return true;
		}
		//输入为空的情况
		if (name.isEmpty() || age.isEmpty()) {
			alartShow("请输入姓名和年龄！");
			return true;
		}
		return false;
	}
	/**
	 * 不合理情况提示框
	 *
	 * @param msg 提示的内容
	 */
	void alartShow(String msg) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setContentText(msg);
		alert.show();
	}
}
