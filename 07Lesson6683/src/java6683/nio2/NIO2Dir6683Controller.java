package java6683.nio2;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.text.DecimalFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author Elxect
 * @date 2021/4/19 8:04 下午
 */
public class NIO2Dir6683Controller {
	public TextField tfDir;
	public Label lblCount;
	public TableView<MyFile6683> tableFiles;
	public TableColumn<MyFile6683, String> colName;
	public TableColumn<MyFile6683, String> colTime;
	public TableColumn<MyFile6683, String> colType;
	public TableColumn<MyFile6683, String> colSize;
	public ObservableList<MyFile6683> items;
	/**
	 * 鼠标点击次数
	 */
	public final int CLICKCOUNT = 2;

	@FXML
	void initialize() {
		items = tableFiles.getItems();
		colName.setCellValueFactory(new PropertyValueFactory<>("name"));
		colType.setCellValueFactory(new PropertyValueFactory<>("type"));
		colTime.setCellValueFactory(new PropertyValueFactory<>("modifiedTime"));
		colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
	}

	public void listAll() {
		String pathName = tfDir.getText().trim();
		pathName = pathName.length() == 0 ? "./" : pathName;
		Path path = Paths.get(pathName);
		items.clear();
		if (Files.isDirectory(path)) {
			try {
				Stream<Path> list = Files.list(path);
				list.forEach((Path item) -> items.add(getMyFile6683(item)));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public void doubleClick(MouseEvent mouseEvent) {
		if (mouseEvent.getClickCount() == CLICKCOUNT) {
			MyFile6683 selectedItem = tableFiles.getSelectionModel().getSelectedItem();
			File file = new File(selectedItem.getName());
			items.clear();
			List<Path> list = new ArrayList<>();
			List<Path> pathList = returnPathUrl(file, list);
			for (Path path : pathList) {
				if (Files.isRegularFile(path)) {
					System.out.println(path);
					items.add(getMyFile6683(path));
				} else {
					items.add(getMyFile6683(path));
				}
			}
		}
	}

	public List<Path> returnPathUrl(File dir, List<Path> fileList) {
		File[] listFiles = dir.listFiles();
		for (int i = 0; i < (listFiles != null ? listFiles.length : 0); i++) {
			//判断读取的是否是目录
			fileList.add(listFiles[i].toPath());
		}
		return fileList;
	}


	public MyFile6683 getMyFile6683(Path path) {
		DecimalFormat df = new DecimalFormat("#0.0");
		String type = " ";
		String size = null;
		try {
			size = String.format("%.2f", Double.parseDouble(df.format(Files.size(path))) / 1024) + "KB";
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (Files.isDirectory(path)) {
			type = "文件夹";
			size = " ";
		}
		MyFile6683 myFile6683 = new MyFile6683();
		try {
			myFile6683.setName(path.getFileName().toString());
			myFile6683.setSize(size);
			myFile6683.setModifiedTime(getModifiedTimeFormat(path));
			myFile6683.setType(type);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return myFile6683;
	}

	/**
	 * 获取文件最后修改时间
	 *
	 * @param path 目录对象
	 * @return 格式化后的时间
	 * @throws IOException 文件读取失败
	 */
	public String getModifiedTimeFormat(Path path) throws IOException {
		FileTime lastModifiedTime = Files.getLastModifiedTime(path);
		Instant instant = lastModifiedTime.toInstant();
		LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
		return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}
}
