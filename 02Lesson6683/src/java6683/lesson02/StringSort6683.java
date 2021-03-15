package java6683.lesson02;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.util.Arrays;


/**
 * @author Elxect
 * @date 2021/3/15 10:31 上午
 */
public class StringSort6683 extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setScene(new Scene(getRoot(), 500, 300));
		primaryStage.setTitle("字符串中的数字排序");
		primaryStage.show();
	}

	private Parent getRoot() {
		Label lblStudent = new Label("212006683 郑逢");
		//边框【注意】多行代码的书写规范
		//边框的颜色
		BorderStroke bs = new BorderStroke(Paint.valueOf("#8FBC8F")
						//边框的样式
						, BorderStrokeStyle.DOTTED
						//边框四个角的圆角
						, new CornerRadii(10)
						//边框的宽度
						, new BorderWidths(3));
		//设置边框
		lblStudent.setBorder(new Border(bs));
		//设置边距
		lblStudent.setPadding(new Insets(10));

		//水平排列的两个控件，用于输入
		HBox hText = new HBox(10);
		Label lblText = new Label("输入数字串：");
		TextField tfText = new TextField();
		//文本无内容时显示的提示信息
		tfText.setPromptText("用空格分隔的数字串");
		hText.getChildren().addAll(lblText, tfText);
		//两个控件的对齐方式
		hText.setAlignment(Pos.CENTER_LEFT);

		Button button = new Button("排序");
		//显示排序结果
		Label lblPrint = new Label();
		//为按钮注册点击事件
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//调用排序方法,显示结果
				lblPrint.setText("排序结果：" + sort(tfText));
			}
		});

		//垂直排列的根节点
		//节点之间的垂直间距20
		VBox root = new VBox(20);
		//为根节点添加子节点
		root.getChildren().addAll(lblStudent, hText, button, lblPrint);
		//设置根节点的边距
		root.setPadding(new Insets(20.0));
		//根节点中各节点的对齐方式
		root.setAlignment(Pos.TOP_CENTER);
		//用CSS样式设置容器中所有节点的字体大小
		root.setStyle("-fx-font-size:20");
		return root;
	}

	/**
	 * @param tfText 输入框
	 * @return 返回排序后的结果
	 * @description 获取输入框的数字进行排序
	 */
	private String sort(TextField tfText) {
		//获取输入框中输入的内容
		String str = tfText.getText();
		//用空格分割
		String[] strings = str.split(" ");
		//初始化一个整型数组
		int[] ints = new int[strings.length];
		for (int i = 0; i < strings.length; i++) {
			//将分割后的数字存放到数组中
			ints[i] = Integer.parseInt(strings[i]);
		}
		//排序
		Arrays.sort(ints);
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < ints.length; i++) {
			//每个数字用空格分开
			stringBuilder.append(ints[i]).append(" ");
		}
		//去掉最后一个数字
		stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		//将排序结果显示出来
		return stringBuilder.toString();
	}
}


