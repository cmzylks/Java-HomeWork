package java6683.ktv;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Elxect
 * @date 2021/3/29 8:07 上午
 */
public class KTVSelect6683Controller {
	@FXML
	public TextField tfNewName;
	@FXML
	public ListView<String> lvNames;

	@FXML
	void addClick(ActionEvent actionEvent) {
		//获取已有歌曲列表
		ObservableList<String> items = lvNames.getItems();
		//获取输入的歌曲名
		String text = tfNewName.getText().trim();
		//删除头尾空格中间空格
		text = text.replaceAll(" ", "");
		//输入为空的情况
		if (text.length() == 0) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("KTV点歌");
			alert.setHeaderText("请注意");
			alert.setContentText("你输入的歌曲为空！");
			alert.show();
			return;
		}
		//列表中已有该歌曲
		if (items.contains(text)) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("KTV点歌");
			alert.setHeaderText("请注意");
			alert.setContentText("【" + text + "】已在歌单中");
			alert.show();
			return;
		}
		//获取当前选择歌曲的索引值
		int selectedIndex = lvNames.getSelectionModel().getSelectedIndex();
		//在当前索引位置添加歌曲
		items.add(selectedIndex, text);
		//添加完成之后清空输入框
		tfNewName.clear();
		//选中添加的新歌
		lvNames.getSelectionModel().select(selectedIndex);
	}

	/**
	 * @param actionEvent
	 * @description 向上移动歌曲
	 */
	@FXML
	void upClick(ActionEvent actionEvent) {
		//获取当前选择歌曲的索引值
		int selectedIndex = lvNames.getSelectionModel().getSelectedIndex();
		//监听歌曲列表
		ObservableList<String> items = lvNames.getItems();
		//当前选择歌曲不是第一首歌
		if (selectedIndex != 0) {
			//将当前歌往前移动一位，索引值-1
			Collections.swap(items, selectedIndex, selectedIndex - 1);
			//并且移动后同时也选中该歌曲
			lvNames.getSelectionModel().select(selectedIndex - 1);
		}
	}

	/**
	 * @param actionEvent
	 * @description 向下移动选中歌曲
	 */
	@FXML
	void downClick(ActionEvent actionEvent) {
		//获取当前选择歌曲的索引值
		int selectedIndex = lvNames.getSelectionModel().getSelectedIndex();
		//监听歌曲列表
		ObservableList<String> items = lvNames.getItems();
		//当前选择歌曲不是最后一首歌
		if (selectedIndex != items.size() - 1) {
			//将当前歌往后移动一位，索引值-1
			Collections.swap(items, selectedIndex, selectedIndex + 1);
			//并且移动后同时也选中该歌曲
			lvNames.getSelectionModel().select(selectedIndex + 1);
		}
	}

	/**
	 * @param actionEvent
	 * @description 置顶选中歌曲
	 */
	@FXML
	void topClick(ActionEvent actionEvent) {
		//获取当前选择歌曲的索引值
		int selectedIndex = lvNames.getSelectionModel().getSelectedIndex();
		//监听歌曲列表
		ObservableList<String> items = lvNames.getItems();
		//将当前歌曲添加到第一位
		items.add(0, items.get(selectedIndex));
		//删除当前选中的歌曲
		items.remove(selectedIndex + 1);
		//选中置顶的歌曲
		lvNames.getSelectionModel().selectFirst();
	}


	/**
	 * 初始化
	 * 打开窗口时在ListView中显示所有曲目
	 */
	@FXML
	void initialize() {
		//初始化歌曲列表集合
		List<String> musicList = new ArrayList<>();
		//添加曲目
		musicList.add("大鱼");
		musicList.add("不分手的恋爱");
		musicList.add("小酒窝");
		musicList.add("感谢你曾经来过");
		musicList.add("你走以后");
		//监听列表
		ObservableList<String> items = lvNames.getItems();
		//渲染列表
		items.addAll(musicList);
		//默认选择第一个
		lvNames.getSelectionModel().selectFirst();
	}
}
