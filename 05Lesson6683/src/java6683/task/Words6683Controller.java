package java6683.task;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Elxect
 * @date 2021/4/6 8:06 上午
 */
public class Words6683Controller {
	@FXML
	public ListView<String> lvWords;
	@FXML
	public TextArea taWords;
	@FXML
	public Label lblCount;


	public void buttonClick(ActionEvent actionEvent) {
		//初始化集合
		Collection<String> wordList = new HashSet<>();
		//获取输入框
		String wordsText = taWords.getText();
		//输入为空，全是空格的情况
		if (wordsText.isEmpty() || wordsText.trim().length() == 0) {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setContentText("请输入内容！");
			alert.show();
			return;
		}
		//匹配正则
		String regex = "[a-zA-Z]+('?[a-zA-Z])?";
		Pattern pattern = Pattern.compile(regex);
		Matcher m = pattern.matcher(wordsText);
		//匹配项添加到集合中
		while (m.find()) {
			wordList.add(m.group().toLowerCase());
		}
		//获取监听列表
		ObservableList<String> items = lvWords.getItems();
		//先清除列表，防止多次点击
		items.clear();
		//渲染列表
		items.addAll(wordList);
		//排序
		Collections.sort(items);
		lblCount.setText("有" + wordList.size() + "个唯一单词");
	}

}
