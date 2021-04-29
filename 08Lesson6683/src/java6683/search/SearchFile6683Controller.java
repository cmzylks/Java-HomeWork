package java6683.search;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author Elxect
 * @date 2021/4/28 9:45 上午
 */
public class SearchFile6683Controller {
	public ListView<String> lvFiles;
	public Button btnChoose;
	public TextField tfDir;
	public Label lblCount;
	public Label currentDir;
	public ObservableList<String> items;
	public int fileCount;
	public int dirCount;

	@FXML
	void initialize() {
		items = lvFiles.getItems();
	}

	public void listFiles(ActionEvent actionEvent) {
		String url = tfDir.getText().trim();
		url = url.length() == 0 ? "./" : url;
		File file = Paths.get(url).toFile();
		setLvFiles(file);
	}

	public void showFiles(ActionEvent actionEvent) {
		//创建文件选择器对象
		DirectoryChooser directoryChooser = new DirectoryChooser();
		//显示并获取选中的文件对象
		File file = directoryChooser.showDialog(new Stage());
		setLvFiles(file);
	}

	/**
	 * 获取文件类型
	 *
	 * @param files 文件列表
	 * @return 文件类型
	 */
	public List<String> getFileType(File[] files) {
		List<String> stringList = new ArrayList<>();
		for (File file : files) {
			String fileName = file.getName();
			//文件
			if (file.isFile()) {
				String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
				stringList.add(fileName + ",\t" + fileType + "类型");
			} else if (file.isDirectory() && fileName.startsWith(".")) {
				stringList.add(fileName + ",\t隐藏文件夹");
			} else {
				stringList.add(fileName + ",\t文件夹");
				stringList.sort(Comparator.comparing("文件夹"::compareTo).thenComparing(Comparator.naturalOrder()));
			}
		}

		return stringList;
	}

	public void setLvFiles(File file) {
		if (file != null) {
			//获取文件列表
			File[] files = file.listFiles();
			currentDir.setText("当前目录：" + file.getAbsolutePath());
			assert files != null;
			fileCount = 0;
			dirCount = 0;
			//筛选目录和文件数
			for (File listFile : files) {
				if (listFile.isFile()) {
					fileCount++;
				} else {
					dirCount++;
				}
			}
			lblCount.setText("子目录:" + dirCount + ",文件:" + fileCount);
			//渲染列表
			items.setAll(getFileType(files));
		}
	}
}
