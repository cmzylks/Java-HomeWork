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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Elxect
 * @date 2021/3/15 12:55 下午
 */
public class Words6683 extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setScene(new Scene(getRoot(), 500, 300));
		primaryStage.setTitle("提取三个字符的单词");
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
		Label lblText = new Label("输入单词：");
		TextField tfText = new TextField();
		//文本无内容时显示的提示信息
		tfText.setPromptText("用空格分隔或逗号分隔的单词");
		hText.getChildren().addAll(lblText, tfText);
		//两个控件的对齐方式
		hText.setAlignment(Pos.CENTER_LEFT);

		Button button = new Button("提取");
		//显示排序结果
		Label lblPrint = new Label();
		//为按钮注册点击事件
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//调用提取方法,显示结果
				lblPrint.setText(extractWords(tfText));
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
	 * @param tfText 获取文本框输入
	 * @return 返回匹配规则的字符串
	 */
	private String extractWords(TextField tfText) {
		//获取输入框中输入的字符串
		String inputWords = tfText.getText();
		//设置正则
		String regex = "[^ ^,][a-zA-Z0-9]+";
		StringBuilder stringBuilder = new StringBuilder();
		//得到Pattern对象
		Pattern p = Pattern.compile(regex);
		//得到Matcher对象
		Matcher m = p.matcher(inputWords);
		//判断是否还有匹配正则表达式的匹配项
		while (m.find()) {
			//获取该项
			String legaWord = inputWords.substring(m.start(), m.end());
			//匹配由三个字符组成的单词
			if (legaWord.length() == 3) {
				//由空格分割
				stringBuilder.append(legaWord).append(" ");
			}
		}
		//去除最后一个空格
		stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		return stringBuilder.toString();
	}
}
