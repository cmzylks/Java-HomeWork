package java6683.register;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author Elxect
 * @date 2021/4/12 7:30 下午
 */
public class Register6683Controller {
	@FXML
	public TextField tfName;
	@FXML
	public PasswordField pf1;
	@FXML
	public PasswordField pf2;
	@FXML
	public TextField tfPhone;
	@FXML
	public Label lblName;
	@FXML
	public Label lblPw1;
	@FXML
	public Label lblPw2;
	@FXML
	public Label lblPhone;
	@FXML
	public Button btnOk;
	@FXML
	public ListView<User6683> lvUsers;

	public ObservableList<User6683> items;
	String userName;
	String passWord1;
	String passWord2;
	String phoneNumber;
	/**
	 * 所有输入是否符合规范
	 */
	private Boolean isMeets = false;

	@FXML
	void initialize() {
		List<User6683> user6683s = Arrays.asList(
						new User6683("陈文杰", "Airwomen2378", "13600893623"),
						new User6683("黄延超", "adjoins12", "17329564002"),
						new User6683("林青树", "antitoxin66", "13022448819")
		);
		items = lvUsers.getItems();
		items.setAll(user6683s);
		loseFocusListener();
	}

	@FXML
	void enterKey(ActionEvent actionEvent) {
		//获取当ENTER的对象
		Object source = actionEvent.getSource();
		//不同对象执行不同检查方式
		if (tfName.equals(source)) {
			checkUserName();
		} else if (pf1.equals(source)) {
			checkPassWord();
		} else if (pf2.equals(source)) {
			checkPassWordSecond();
		} else {
			checkPhoneNumber();
		}
	}

	@FXML
	void okClick() {
		//全部检测通过
		if (isMeets) {
			User6683 user6683 = new User6683(userName, passWord1, phoneNumber);
			items.add(user6683);
			alertShow("注册成功！");
			clear();
			return;
		}
		alertShow("注册失败，输入不合法!");
	}

	@FXML
	void noClick() {
		clear();
	}

	/**
	 * 失去焦点检测用户输入
	 */
	void loseFocusListener() {
		tfName.focusedProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue) {
				checkUserName();
			}
		});
		pf1.focusedProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue) {
				checkPassWord();
			}
		});
		pf2.focusedProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue) {
				checkPassWordSecond();
			}
		});
		tfPhone.focusedProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue) {
				checkPhoneNumber();
			}
		});
	}

	/**
	 * 检查用户名合理性
	 */
	private void checkUserName() {
		//获取用户名，并去除空格
		userName = tfName.getText().trim().replaceAll(" ", "");
		lblName.setVisible(false);
		isMeets = true;
		//用户名为空
		if (userName.isEmpty()) {
			lblName.setVisible(true);
			isMeets = false;
		}
		//遍历已经注册列表判断用户唯一性
		for (User6683 item : items) {
			if (item.getUserName().equals(userName)) {
				lblName.setVisible(true);
				isMeets = false;
				break;
			}
		}
	}

	/**
	 * 检查密码合理性
	 */
	private void checkPassWord() {
		String regex = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{6,}";
		passWord1 = pf1.getText();
		lblPw1.setVisible(false);
		isMeets = true;
		if (!Pattern.matches(regex, passWord1)) {
			lblPw1.setVisible(true);
			isMeets = false;
		}
	}

	/**
	 * 检查两次密码的相同性
	 */
	private void checkPassWordSecond() {
		passWord1 = pf1.getText();
		passWord2 = pf2.getText();
		lblPw2.setVisible(false);
		isMeets = true;
		if (!passWord2.equals(passWord1)) {
			lblPw2.setVisible(true);
			isMeets = false;
		}
	}

	/**
	 * 检查电话号码合理性
	 */
	private void checkPhoneNumber() {
		String regex = "1[3578]\\d{9}";
		phoneNumber = tfPhone.getText();
		lblPhone.setVisible(false);
		isMeets = true;
		if (!Pattern.matches(regex, phoneNumber)) {
			lblPhone.setVisible(true);
			isMeets = false;
		}
	}

	/**
	 * 弹出提示
	 *
	 * @param msg String 提示内容
	 */
	private void alertShow(String msg) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setContentText(msg);
		alert.show();
	}

	/**
	 * 清除用户所有输入
	 */
	private void clear() {
		tfName.clear();
		pf1.clear();
		pf2.clear();
		tfPhone.clear();
	}
}
