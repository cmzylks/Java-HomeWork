package java6683.lesson07;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.File;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

/**
 * @author Elxect
 * @date 2021/4/19 8:10 上午
 */
public class ListFile6683Controller {

	@FXML
	public TextField tfDir;
	@FXML
	public ListView<String> lvFiles;
	@FXML
	public Label lblCount;
	public ObservableList<String> list;

	public void listAll() {
		String url = tfDir.getText().trim();
		//统计文件夹数
		int directoryCount = 0;
		//统计文件数
		int fileCount = 0;
		list = lvFiles.getItems();
		//输入空串默认为当前目录
		url = url.length() == 0 ? "./" : url;
		//创建file对象
		File file = new File(url);
		//先清空
		list.clear();
		//获取目录下的所有子文件和子目录
		File[] files = file.listFiles();
		DecimalFormat df = new DecimalFormat("#0.0");
		if (files == null) {
			list.add("目录不存在");
			lblCount.setVisible(false);
			return;
		}
		//遍历所有子文件目录
		for (File item : files) {
			if (item.isFile()) {
				//文件
				list.add(item.getName() + "----" + getDate(item.lastModified()) + "----" +
								String.format("%.2f", Double.parseDouble(df.format(item.length())) / 1024) + "KB");
				fileCount++;
			} else {
				//目录
				list.add("文件夹：" + item.getName() + "----" + getDate(item.lastModified()));
				directoryCount++;
			}
		}
		//渲染列表
		lblCount.setText("子目录:" + directoryCount + ",文件：" + fileCount);
		lblCount.setVisible(true);
		//排序
		list.sort(Comparator.reverseOrder());
	}

	/**
	 * 将输入的时间戳进行格式化成标准的时间日期
	 *
	 * @param milliseconds 时间戳
	 * @return 格式化后的时间
	 */
	public String getDate(long milliseconds) {
		//格式模版
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		//将时间戳转成标准的日期时间
		LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(milliseconds / 1000,
						0, ZoneOffset.ofHours(8));
		return localDateTime.format(formatter);
	}
}
