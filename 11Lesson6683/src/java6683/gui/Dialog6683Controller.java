package java6683.gui;

import java6683.dao.Student6683;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.Optional;

/**
 * @author Elxect
 */
public class Dialog6683Controller {

	@FXML
	private AnchorPane root;
	@FXML
	private TextField tfSno;
	@FXML
	private TextField tfSname;
	@FXML
	private TextField tfLx1;
	@FXML
	private TextField tfLx2;
	@FXML
	private Button btnYes;

	private Student6683 student6683;

	/**
	 * 关闭窗口
	 */
	@FXML
	void no() {
		Stage dilog = (Stage) root.getScene().getWindow();
		dilog.close();
	}

	/**
	 * 将窗口中输入的信息封装为Student对象，并关闭窗口
	 */
	@FXML
	void yes() {
		String sno = tfSno.getText().trim();
		String sName = tfSname.getText().trim();
		if (sno.length() == 0 || sName.length() == 0) {
			alertShow("学号姓名不能为空！");
			return;
		}
		student6683 = new Student6683();
		student6683.setSno(sno);
		student6683.setsName(sName);
		student6683.setLx1(tfLx1.getText().length() == 0 ? 0 : Integer.parseInt(tfLx1.getText()));
		student6683.setLx2(tfLx2.getText().length() == 0 ? 0 : Integer.parseInt(tfLx2.getText()));
		Stage dilog = (Stage) root.getScene().getWindow();
		dilog.close();
	}

	/**
	 * 初始化并显示添加对话框
	 */
	void initAdd6683() {
		//清空表单
		tfSno.clear();
		tfSname.clear();
		tfLx1.clear();
		tfLx2.clear();
		student6683 = null;
		btnYes.setText("增加");
		Stage dilog = (Stage) root.getScene().getWindow();
		dilog.showAndWait();
	}

	/**
	 * 初始化并显示修改对话框
	 *
	 * @param student6683 学生对象
	 */
	void initChange6683(Student6683 student6683) {
		//插入学生对象的熟悉到表单中
		tfSno.setText(student6683.getSno());
		tfSname.setText(student6683.getName());
		tfLx1.setText(String.valueOf(student6683.getLx1()));
		tfLx2.setText(String.valueOf(student6683.getLx2()));
		btnYes.setText("修改");
		Stage dilog = (Stage) root.getScene().getWindow();
		dilog.showAndWait();
	}

	/**
	 * 获取学生对象
	 *
	 * @return 学生对象
	 */
	Student6683 getStudent() {
		return student6683;
	}

	/**
	 * 提示框
	 *
	 * @param massage 提示信息
	 */
	void alertShow(String massage) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("提示");
		alert.setContentText(massage);
		alert.show();
	}

	/**
	 * 弹出一个通用的确定对话框
	 *
	 * @param title 标题
	 * @param msg   提示内容
	 * @return boolean 是 否
	 */
	public boolean alertConfigDialog(String title, String msg) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION, msg, new ButtonType("否", ButtonBar.ButtonData.NO),
						new ButtonType("是", ButtonBar.ButtonData.YES));
		alert.setTitle(title);
		//showAndWait() 将在对话框消失以前不会执行之后的代码
		Optional<ButtonType> buttonType = alert.showAndWait();
		//根据点击结果返回
		return buttonType.get().getButtonData().equals(ButtonBar.ButtonData.YES);
	}
}
