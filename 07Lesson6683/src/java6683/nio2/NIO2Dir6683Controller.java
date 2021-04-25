package java6683.nio2;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

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
	public String absolutePath = null;
	public int countFile;
	public int countDirectory;
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
		countFile=0;
		countDirectory=0;
		pathName = pathName.length() == 0 ? "./" : pathName;
		Path path = Paths.get(pathName);
		absolutePath = String.valueOf(path.getFileName());
		showFileDirectory(path);
		lblCount.setText("文件夹："+countDirectory+"，文件："+countFile);
	}

	public void doubleClick(MouseEvent mouseEvent) {
		//双击
		if (mouseEvent.getClickCount() == CLICKCOUNT) {
			countFile=0;
			countDirectory=0;
			//获取双击的对象
			MyFile6683 selectedItem = tableFiles.getSelectionModel().getSelectedItem();
			Path path = Paths.get(selectedItem.getName());
			//拼接路径
			absolutePath += "\\" + path;
			Path childPath = Paths.get(absolutePath);
			//渲染子目录
			showFileDirectory(childPath);
			lblCount.setText("文件夹："+countDirectory+"，文件："+countFile);
		}
	}

	/**
	 * 读取文件的属性设置文件属性
	 *
	 * @param path 路径
	 * @return 文件对象
	 */
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
			countDirectory++;
		}else {
			countFile++;
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
	 * 渲染路径下的文件和目录
	 *
	 * @param path 路径
	 */
	public void showFileDirectory(Path path) {
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
