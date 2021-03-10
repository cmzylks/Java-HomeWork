package java6683.borrow;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * @author Elxect
 * @date 2021/3/10 9:09 上午
 */
public class Main6683 extends Application {
	User6683 user6683 = new User6683("root", "12345");

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Label lName = new Label("账号：");
		//设置文字大小
		lName.setFont(Font.font(14));
		Label lPassword = new Label("密码：");
		lPassword.setFont(Font.font(14));
		TextField tName = new TextField();
		PasswordField pPassword = new PasswordField();

		Button login = new Button("登录");
		Button clear = new Button("清除");
		//网格布局
		GridPane gp = new GridPane();
		VBox vBox = new VBox();
		//标题
		Label title = new Label("图书馆借书系统");
		title.setFont(Font.font("STKaiti", 30));
		//添加组件并布局
		gp.add(lName, 0, 0);
		gp.add(tName, 1, 0);
		gp.add(lPassword, 0, 1);
		gp.add(pPassword, 1, 1);
		gp.add(login, 0, 2);
		gp.add(clear, 1, 2);
		//设置水平间距
		gp.setHgap(5);
		gp.setVgap(17);
		//为清除按钮设置左外边距
		GridPane.setMargin(clear, new Insets(0, 0, 0, 120));
		VBox.setMargin(title, new Insets(0, 0, 50, 0));
		VBox.setMargin(gp, new Insets(10, 0, 100, 0));
		//设置所有元素居中
		vBox.getChildren().addAll(title, gp);
		vBox.setAlignment(Pos.CENTER);
		gp.setAlignment(Pos.CENTER);
		//设置样式
		vBox.setStyle("-fx-background-color: #ecf0f1");
		Scene scene = new Scene(vBox);
		primaryStage.setScene(scene);
		primaryStage.setTitle("登录");
		primaryStage.setWidth(500);
		primaryStage.setHeight(500);
		primaryStage.show();

		/*
		  清空输入框
		 */
		clear.setOnAction(event -> {
			tName.setText("");
			pPassword.setText("");
		});
		/*
				注册点击事件
		 */
		login.setOnAction(event -> {
			String userName = user6683.getUserName();
			String passWord = user6683.getPassWord();
			if (tName.getText().equals(userName) && pPassword.getText().equals(passWord)) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION); // 创建一个消息对话框
				alert.setContentText("登录成功！");
				alert.showAndWait(); // 显示对话框
				//实例化新的对话框，传入用户名
				BorrowBooks6683 window = new BorrowBooks6683(userName);
				primaryStage.close();
			} else {
				primaryStage.setTitle("账号或密码输入错误");
				//实例化动画
				FadeTransition fade = new FadeTransition();
				//设置动画持续事件
				fade.setDuration(Duration.seconds(0.5));
				fade.setNode(gp);
				fade.setFromValue(0);
				fade.setToValue(1);
				fade.play();
			}
		});
	}
}
