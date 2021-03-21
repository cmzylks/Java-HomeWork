package java6683.regex;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.util.regex.Pattern;

/**
 * @author Elxect
 * @date 2021/3/15 8:21 下午
 */
public class CheckRegex6683 extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setScene(new Scene(getRoot(), 800, 500));
		primaryStage.setTitle("验证正则表达式");
		primaryStage.show();
	}

	private Parent getRoot() {
		//显示每个人的学号和姓名
		Label lblStudent = new Label("212006683 郑逢");
		//边框的颜色
		BorderStroke bs = new BorderStroke(Paint.valueOf("#8FBC8F"),
						//边框的样式
						BorderStrokeStyle.DOTTED,
						//边框四个角的圆角
						new CornerRadii(10),
						//边框的宽度
						new BorderWidths(3));
		//设置边框
		lblStudent.setBorder(new Border(bs));
		//设置边距
		lblStudent.setPadding(new Insets(10));

		//输入正则表达式的两个控件用水平排列
		HBox hRegex = new HBox(10);
		Label lblRegex = new Label("正则表达式：");
		TextField tfRegex = new TextField();
		//添加两个控件
		hRegex.getChildren().addAll(lblRegex, tfRegex);
		//垂直居中，水平靠左
		hRegex.setAlignment(Pos.CENTER_LEFT);
		//文本框的宽度填满剩余空间
		HBox.setHgrow(tfRegex, Priority.ALWAYS);

		Label lblInput = new Label("输入要检验的一组字符串（用逗号分隔）");
		//多行文本框，输入一组要验证的字符串
		TextArea taInput = new TextArea();
		//4行
		taInput.setPrefRowCount(4);
		//20列
		taInput.setPrefColumnCount(20);
		//允许自动换行
		taInput.setWrapText(true);

		Button button = new Button("验证字符串是否符合正则表达式");

		//用于输入的控件采用垂直排列
		VBox vInput = new VBox(20);
		//添加子节点
		vInput.getChildren().addAll(hRegex, lblInput, taInput, button);
		//设置边距
		vInput.setPadding(new Insets(20.0));
		//对齐方式
		vInput.setAlignment(Pos.CENTER);

		//列表显示验证结果
		ListView<String> lvPrint = new ListView<>();
		lvPrint.getItems().add("显示验证结果");

		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//调用正则表达式验证方法
				verificationRegex(tfRegex, taInput, lvPrint);
			}
		});

		//水平排列：左边输入，右边显示结果
		HBox hBox = new HBox();
		hBox.getChildren().addAll(vInput, lvPrint);

		//垂直排列的根节点
		//节点之间的垂直间距20
		VBox root = new VBox(20);
		//为根节点添加子节点
		root.getChildren().addAll(lblStudent, hBox);
		//设置根节点的边距
		root.setPadding(new Insets(20.0));
		//根节点中各节点的对齐方式
		root.setAlignment(Pos.TOP_CENTER);
		//用CSS样式设置容器中所有节点的字体大小
		root.setStyle("-fx-font-size:20");
		return root;
	}

	/**
	 * @param tfRegex 单行输入框
	 * @param taInput 多行输入框
	 * @param lvPrint listview显示
	 * @description 验证正则表达式，单行输入框输入正则表达式，多行输入框输入验证的字符串，listView显示验证结果
	 */
	private void verificationRegex(TextField tfRegex, TextArea taInput, ListView<String> lvPrint) {
		//读取单行文本框中输入的正则表达式
		String regex = tfRegex.getText();
		//读取多行文本框中输入的字符串
		String strInput = taInput.getText();
		//输入内容为空的情况
		if (strInput.length() == 0 || regex.length() == 0) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("输入内容不能为空！");
			alert.show(); // 显示对话框
			return;
		}
		//用逗号拆分字符串strInput
		String[] inputs = strInput.split(",");
		//清空ListView中已有的内容
		lvPrint.getItems().clear();
		for (int i = 0; i < inputs.length; i++) {
			//捕获输入不正确的正则表达式的情况
			try {
				//①验证是否满足正则表达式
				boolean isMatch = Pattern.matches(regex, inputs[i]);
				//②将当前元素及验证结果添加到ListView
				lvPrint.getItems().add(inputs[i] + ":" + isMatch);
			} catch (Exception e) {
				Alert alert = new Alert(Alert.AlertType.WARNING);
				alert.setContentText("正则表达式错误！");
				alert.show(); // 显示对话框
				break;
			}
		}
	}
}
