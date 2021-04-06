package java6683.task;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

	public ObservableList<Student6683> items;

	public List<Student6683> student6683s;

	private int index;

	@FXML
	void initialize() {
		Collection<Student6683> list = Arrays.asList(
						new Student6683("张三", 15),
						new Student6683("李四", 18),
						new Student6683("王五", 20)
		);
		student6683s = new ArrayList<>(list);
		items = lvStudent.getItems();
		items.clear();
		items.addAll(student6683s);
		lvStudent.getSelectionModel().selectFirst();
		tfName.setText(items.get(index).getName());
		tfAge.setText(String.valueOf(items.get(index).getAge()));
	}

	public void addClick(ActionEvent actionEvent) {
		String name = tfName.getText();
		int age = Integer.parseInt(tfAge.getText());
		Student6683 student6683 = new Student6683(name, age);
		student6683s.add(student6683);
		items = lvStudent.getItems();
		items.clear();
		items.addAll(student6683s);
		lvStudent.getSelectionModel().selectFirst();
		index = lvStudent.getSelectionModel().getSelectedIndex();
		tfName.setText(items.get(index).getName());
		tfAge.setText(String.valueOf(items.get(index).getAge()));
	}

	public void changeClick(ActionEvent actionEvent) {
	}

	public void clearClick(ActionEvent actionEvent) {

	}

}
