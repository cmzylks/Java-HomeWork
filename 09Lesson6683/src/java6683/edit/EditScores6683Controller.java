package java6683.edit;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.*;
import java.util.*;

public class EditScores6683Controller {
	@FXML
	private Label filePath;
	@FXML
	private TableView<Score6683> tvScores;
	@FXML
	private TableColumn<Score6683, String> tvName;
	@FXML
	private TableColumn<Score6683, Integer> tvChinese;
	@FXML
	private TableColumn<Score6683, Integer> tvEnglish;
	@FXML
	private TableColumn<Score6683, Integer> tvMath;
	private ObservableList<Score6683> items;
	private FileChooser fc = new FileChooser();
	private File file;

	@FXML
	void initialize() {
		items = tvScores.getItems();
		//设置数据的对应字段
		tvName.setCellValueFactory(new PropertyValueFactory<>("name"));
		tvChinese.setCellValueFactory(new PropertyValueFactory<>("chinese"));
		tvMath.setCellValueFactory(new PropertyValueFactory<>("math"));
		tvEnglish.setCellValueFactory(new PropertyValueFactory<>("english"));
		//允许TableView编辑
		tvScores.setEditable(true);
		//设置每个字段可编辑
		initializeEdit();
	}

	@FXML
	void openFile(ActionEvent event) {
		//设置标题和默认路径
		fc.setTitle("请选择学生文件");
		fc.setInitialDirectory(new File("./"));
		//创建两种文件选择类型
		FileChooser.ExtensionFilter txtFilter = new FileChooser.ExtensionFilter("文本文件", "*.txt");
		FileChooser.ExtensionFilter allFilter = new FileChooser.ExtensionFilter("所以文件", "*.*");
		//添加到文件选择对象中
		fc.getExtensionFilters().add(txtFilter);
		fc.getExtensionFilters().add(allFilter);
		//设置默认的文件选择类型
		fc.setSelectedExtensionFilter(txtFilter);
		//返回选择的文件
		file = fc.showOpenDialog(new Stage());
		//文件存在且是txt文件
		if (file != null && file.getName().endsWith(".txt")) {
			//读取文件返回数据集
			List<Score6683> score6683s = readData(file);
			items.clear();
			items.setAll(score6683s);
			filePath.setText(items.size() + "条学生成绩数据：" + file.getAbsolutePath());
		}
	}

	@FXML
	void saveFile(ActionEvent event) {
		//如果有打开文件则直接保存不弹出窗口
		if (this.file != null) {
			writeData(items, file);
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("消息提示");
			alert.setContentText("保存成功！");
			alert.show();
			return;
		}
		//设置标题和默认路径
		fc.setTitle("保存文件");
		fc.setInitialDirectory(new File("./"));
		//创建两种文件选择类型
		FileChooser.ExtensionFilter txtFilter = new FileChooser.ExtensionFilter("文本文件", "*.txt");
		FileChooser.ExtensionFilter allFilter = new FileChooser.ExtensionFilter("所以文件", "*.*");
		fc.getExtensionFilters().clear();
		//添加到文件选择对象中
		fc.getExtensionFilters().add(txtFilter);
		fc.getExtensionFilters().add(allFilter);
		//设置默认的文件选择类型
		fc.setSelectedExtensionFilter(txtFilter);
		//保存的新文件
		File newFile = fc.showSaveDialog(new Stage());
		//写入新文件中
		if (newFile != null) {
			writeData(items, newFile);
		}
	}

	@FXML
	void addData(ActionEvent event) {
		//创建新的成就数据
		Score6683 newScore = new Score6683("new", 0, 0, 0);
		items.add(newScore);
		tvScores.getSelectionModel().select(newScore);
	}

	@FXML
	void delData(ActionEvent event) {
		//获取选择的索引
		int selectedIndex = tvScores.getSelectionModel().getSelectedIndex();
		//删除选中项目
		if (selectedIndex != -1) {
			items.remove(selectedIndex);
		}
	}

	/**
	 * 读取文件内的数据
	 *
	 * @param file 文件
	 * @return 返回文件数据集合
	 */
	public List<Score6683> readData(File file) {
		List<Score6683> score6683s = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = br.readLine()) != null) {
				//分割每个数据
				String[] split = line.split(",");
				//区分无效数据
				if (split.length > 1) {
					//创建对象并设置对应的属性
					Score6683 score6683 = new Score6683(split[0], Integer.parseInt(split[1]), Integer.parseInt(split[2]), Integer.parseInt(split[3]));
					score6683s.add(score6683);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return score6683s;
	}

	/**
	 * 设置每列数据为可编辑状态
	 */
	public void initializeEdit() {
		//编辑姓名
		tvName.setCellFactory(TextFieldTableCell.forTableColumn());
		ArrayList<TableColumn<Score6683, Integer>> tableColumns = new ArrayList<>(Arrays.asList(tvChinese, tvMath, tvEnglish));
		for (TableColumn<Score6683, Integer> col : tableColumns) {
			col.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Integer>() {
				@Override
				public String toString(Integer object) {
					return Objects.toString(object);
				}

				@Override
				public Integer fromString(String inString) {
					int input;
					try {
						input = Integer.parseInt(inString);
						input = input >= 0 && input <= 100 ? input : -1;
					} catch (Exception e) {
						input = -1;
					}
					return input;
				}
			}));
		}
		//当修改某个单元格，按回车确认后，再次按回车，能够让下一个单元格进入编辑状态
		tvScores.editingCellProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue == null) {
				tvScores.requestFocus();
				if (oldValue.getColumn() < tvScores.getColumns().size() - 1) {
					tvScores.getFocusModel().focusRightCell();
				} else {
					tvScores.getFocusModel().focus(oldValue.getRow(), tvScores.getColumns().get(0));
				}
			}
		});
	}

	/**
	 * 将数据写入文件中
	 *
	 * @param list 数据列表
	 * @param file 写入的文件
	 */
	public void writeData(List<Score6683> list, File file) {
		//如果file文件为空的话
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(file));) {
			for (Score6683 score6683 : list) {
				bw.write(score6683.getName()
								+ "," + score6683.getChinese()
								+ "," + score6683.getEnglish()
								+ "," + score6683.getMath());
				bw.write("\r\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
