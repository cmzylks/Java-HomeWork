package java6683.check;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Elxect
 * @date 2021/3/17 10:32 上午
 */
public class CheckBlank6683 extends Application {
	TextArea taTitle = new TextArea();
	TextField tfInput = new TextField();
	TextField tfRegex = new TextField();
	Label lblPrint = new Label();
	Button button = new Button("检验");

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setScene(new Scene(getRoot(), 1000, 600));
		primaryStage.setTitle("检验填空题");
		primaryStage.show();
	}

	private Parent getRoot() {
		//Top
		Label lblStudent = new Label("212006683郑逢");
		//【注意】多行代码的书写规范
		BorderStroke bs = new BorderStroke(Paint.valueOf("#8FBC8F")
						, BorderStrokeStyle.DOTTED
						, new CornerRadii(10)
						, new BorderWidths(3));
		lblStudent.setBorder(new Border(bs));
		lblStudent.setPadding(new Insets(10));

		//Left
		VBox vRadio = new VBox(30);
		RadioButton rb1 = new RadioButton("第一题");
		RadioButton rb2 = new RadioButton("第二题");
		RadioButton rb3 = new RadioButton("第三题");
		RadioButton rb4 = new RadioButton("第四题");
		ToggleGroup tg = new ToggleGroup();
		rb1.setToggleGroup(tg);
		rb2.setToggleGroup(tg);
		rb3.setToggleGroup(tg);
		rb4.setToggleGroup(tg);
		vRadio.getChildren().addAll(rb1, rb2, rb3, rb4);
		vRadio.setAlignment(Pos.CENTER);
		vRadio.setPadding(new Insets(10));

		//Center
		taTitle.setEditable(false);

		HBox hInput = new HBox(10);
		Label lblInput = new Label("输入：");
		tfInput.setPromptText("输入被分析的字符串");
		hInput.getChildren().addAll(lblInput, tfInput);
		hInput.setAlignment(Pos.CENTER_LEFT);

		HBox hRegex = new HBox(10);
		Label lblRegex = new Label("正则表达式：");

		hRegex.getChildren().addAll(lblRegex, tfRegex);
		hRegex.setAlignment(Pos.CENTER_LEFT);

		VBox vCenter = new VBox(20);
		vCenter.getChildren().addAll(taTitle, hInput, hRegex, button);
		vCenter.setPadding(new Insets(20.0));
		vCenter.setAlignment(Pos.TOP_CENTER);
		VBox.setVgrow(taTitle, Priority.ALWAYS);
		HBox.setHgrow(tfInput, Priority.ALWAYS);
		HBox.setHgrow(tfRegex, Priority.ALWAYS);
		//Bottom
		//调用选取题目方法
		chooseTopic(rb1, rb2, rb3, rb4);
		BorderPane root = new BorderPane();
		root.setTop(lblStudent);
		root.setLeft(vRadio);
		root.setBottom(lblPrint);
		root.setCenter(vCenter);
		root.setPadding(new Insets(10));
		BorderPane.setAlignment(lblStudent, Pos.CENTER);
		BorderPane.setAlignment(lblPrint, Pos.CENTER);
		//用CSS样式设置容器中所有节点的字体大小
		root.setStyle("-fx-font-size:20");
		rb1.setSelected(true);
		return root;
	}

	/**
	 * @param rb1 复选框1
	 * @param rb2 复选框2
	 * @param rb3 复选框3
	 * @param rb4 复选框4
	 *  选取不同复选框,显示不同题目
	 */
	private void chooseTopic(RadioButton rb1, RadioButton rb2, RadioButton rb3, RadioButton rb4) {
		String[] topic = {
						//第一题
						"String str = \"2816001张达2018年第1学期成绩：数学83.2分，英语91.7分，语文88分，作文80分。\";\n" +
										"String regex =\"填空1\";\n" +
										"Pattern p = 填空2;\n" +
										"Matcher m = 填空3;\n" +
										"double sum = 0;\n" +
										"while (m.find()) {\n" +
										"sum+=Double.parseDouble(str.substring(填空4,填空5));\n" +
										"}\n" +
										"System.out.println(\"sum=\" + sum);",
						//第二题
						"String str = \"E:\\\\JavaTest\\\\02LessonXXXX\\\\src\";\n" +
										"String regex =\" 填空1 \";\n" +
										"String[] dirs = str.split(regex);\n" +
										"System.out.println( 填空2 );",
						//第三题
						"String str = \"第1节String第2节Object第3节Collection\";\n" +
										"String regex = \"[a-zA-Z] 填空2\";\n" +
										"System.out.println(str.填空3 );",
						//第四题
						"String str = \"2018年成绩：数学83分，英语91分，语文88分，作文80分。\";\n" +
										"String regex = \"填空1\" ;\n" +
										"String[] words = str.split(regex);\n" +
										"System.out.println( 填空2 );"
		};
		//监听按钮1
		rb1.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				//清空两个输入框
				tfInput.clear();
				tfRegex.clear();
				//清空输出结果
				lblPrint.setText("");
				//设置题目
				taTitle.setText(topic[0]);
				//为按钮注册监听事件
				button.setOnAction(event -> {
					//调用方法验证
					verificationRegexTop1();
				});
			}
		});
		//监听按钮2
		rb2.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				tfInput.clear();
				tfRegex.clear();
				lblPrint.setText("");
				taTitle.setText(topic[1]);
				button.setOnAction(event -> {
					verificationRegexTop();
				});
			}
		});
		//监听按钮3
		rb3.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				tfInput.clear();
				tfRegex.clear();
				lblPrint.setText("");
				taTitle.setText(topic[2]);
				button.setOnAction(event -> {
					verificationRegexTop3();
				});
			}
		});
		//监听按钮4
		rb4.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				tfInput.clear();
				tfRegex.clear();
				lblPrint.setText("");
				taTitle.setText(topic[3]);
				button.setOnAction(event -> {
					verificationRegexTop();
				});
			}
		});
	}

	private void verificationRegexTop1() {
		//接收输入的字符串
		String str = tfInput.getText();
		//接收输入的正则表达式
		String regex = tfRegex.getText();
		//输入内容为空的情况
		if (str.length() == 0 || regex.length() == 0) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("输入内容不能为空！");
			alert.show(); // 显示对话框
			return;
		}
		Pattern p;
		try {
			p = Pattern.compile(regex);
		} catch (Exception e) {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setContentText("正则表达式错误！");
			alert.show(); // 显示对话框
			return;
		}
		Matcher m = p.matcher(str);
		double sum = 0;
		while (m.find()) {
			sum += Double.parseDouble(str.substring(m.start(), m.end() - 1));
		}
		lblPrint.setText("校验结果：" + sum);
	}

	private void verificationRegexTop() {
		//接收输入的字符串
		String str = tfInput.getText();
		//接收输入的正则表达式
		String regex = tfRegex.getText();
		//输入内容为空的情况
		if (str.length() == 0 || regex.length() == 0) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("输入内容不能为空！");
			alert.show(); // 显示对话框
			return;
		}
		//初始化字符串数组
		String[] results = new String[0];
		try {
			results = str.split(regex);
		} catch (Exception e) {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setContentText("正则表达式错误！");
			alert.show(); // 显示对话框
		}
		lblPrint.setText("校验结果：" + Arrays.toString(results));
	}

	private void verificationRegexTop3() {
		//接收输入的字符串
		String str = tfInput.getText();
		//接收输入的正则表达式
		String regex = tfRegex.getText();
		//输入内容为空的情况
		if (str.length() == 0 || regex.length() == 0) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("输入内容不能为空！");
			alert.show(); // 显示对话框
			return;
		}
		try {
			lblPrint.setText("校验结果：" + str.replaceAll(regex, "***"));
		} catch (Exception e) {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setContentText("正则表达式错误！");
			alert.show(); // 显示对话框
		}
	}
}
