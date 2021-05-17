package java6683.task;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.Scanner;

public class Editor6683Controller {
	public TextField tfFilename;
	public TextArea taText;
	private Boolean isChange = false;
	private File file;

	@FXML
	void initialize() {
		taText.setWrapText(false);
		//监听文件是否被修改
		taText.textProperty().addListener((observable, oldValue, newValue) -> {
			isChange = true;
		});

	}

	public void openFile(ActionEvent actionEvent) {
		isChange = false;
		FileChooser fc = getFc("打开文件");
		//返回选择的文件
		file = fc.showOpenDialog(new Stage());
		if (file != null) {
			tfFilename.setText(file.toString());
			readData(file);
		}
	}

	public void saveFile(ActionEvent actionEvent) {
		//如果文本文件改变
		if (isChange) {
			writeData(file);
			isChange = false;
			alertShow("保存成功");
		}
	}

	public void saveAsFile() {
		FileChooser fc = getFc("保存文件");
		//保存的新文件
		File newFile = fc.showSaveDialog(new Stage());
		if (newFile != null) {
			//写入数据
			writeData(newFile);
			isChange = false;
			alertShow("保存成功");
		}
	}

	/**
	 * 读数据
	 *
	 * @param file 文件
	 */
	public void readData(File file) {
		StringBuilder sb = new StringBuilder();
		try (Scanner scanner = new Scanner(file)) {
			while (scanner.hasNextLine()) {
				String s = scanner.nextLine();
				sb.append(s).append("\r\n");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		taText.setText(sb.toString());
	}

	/**
	 * 写数据
	 *
	 * @param file 文件
	 */
	public void writeData(File file) {
		//未打开文件直接保存
		if (isChange) {
			if (file == null) {
				saveAsFile();
				isChange = false;
				return;
			}
		}
		try (PrintWriter printStream = new PrintWriter(file)) {
			printStream.write(taText.getText());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		isChange = false;
	}

	/**
	 * 获取选择文件对话框
	 *
	 * @param str 对话框标题
	 * @return 文件对话框
	 */
	public FileChooser getFc(String str) {
		FileChooser fc = new FileChooser();
		//设置标题和默认路径
		fc.setTitle(str);
		fc.setInitialDirectory(new File("./"));
		//创建两种文件选择类型
		FileChooser.ExtensionFilter txtFilter = new FileChooser.ExtensionFilter("文本文件", "*.txt");
		FileChooser.ExtensionFilter allFilter = new FileChooser.ExtensionFilter("java文件", "*.java");
		fc.getExtensionFilters().clear();
		//添加到文件选择对象中
		fc.getExtensionFilters().add(txtFilter);
		fc.getExtensionFilters().add(allFilter);
		//设置默认的文件选择类型
		fc.setSelectedExtensionFilter(txtFilter);
		return fc;
	}

	/**
	 * 点击关闭按钮弹窗对话框
	 *
	 * @param stage 场景
	 * @param event 窗口
	 */
	public void setCloseRequest(Stage stage, WindowEvent event) {
		//修改文件后
		if (isChange) {
			//未打开文件 点关闭
			if (file == null) {
				int savaStatus = alertConfigDialog("文本编辑器关闭提示", "是否将为存盘");
				//是
				if (savaStatus == 1) {
					//执行保存操作
					saveAsFile();
					stage.close();
					//否
				} else if (savaStatus == 2) {
					stage.close();
					//取消
				} else {
					event.consume();
				}
				return;
			}
			//返回点击对框的状态
			int savaStatus = alertConfigDialog("文本编辑器关闭提示", "是否将为存盘的修改保存到" + tfFilename.getText());
			//是
			if (savaStatus == 1) {
				writeData(file);
				stage.close();
				//否
			} else if (savaStatus == 2) {
				stage.close();
				//取消
			} else {
				//维持当前窗口
				event.consume();
			}
		}
	}

	/**
	 * 弹出一个通用的确定对话框
	 *
	 * @param title 标题
	 * @param msg   提示内容
	 * @return int 点击不同按钮的状态  1----是  2----否  3----取消
	 */
	public int alertConfigDialog(String title, String msg) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION, msg, new ButtonType("否", ButtonBar.ButtonData.NO),
						new ButtonType("是", ButtonBar.ButtonData.YES), new ButtonType("取消", ButtonBar.ButtonData.CANCEL_CLOSE));
		alert.setTitle(title);
		//showAndWait() 将在对话框消失以前不会执行之后的代码
		Optional<ButtonType> buttonType = alert.showAndWait();
		//根据点击结果返回
		if (buttonType.get().getButtonData().equals(ButtonBar.ButtonData.YES)) {
			return 1;
		} else if (buttonType.get().getButtonData().equals(ButtonBar.ButtonData.NO)) {
			return 2;
		}
		return 3;
	}

	public void alertShow(String massage) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("提示");
		alert.setContentText(massage);
		alert.show();
	}
}
