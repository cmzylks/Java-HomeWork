package java6683.selection;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

/**
 * @author Elxect
 * @date 2021/3/8 3:41 下午
 */
public class SelectionWin6683 extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		//初始化数组用于存储名单
		Object[] list = new Object[100];
		//实例化按钮
		Button button = new Button("随机选人");
		//实例化名字
		Text name = new Text("212006683 郑逢");
		//实例化标签
		Text randomName = new Text("请点击按钮！");
		//实例化根节点
		VBox vBox = new VBox(30);
		//添加子节点添加名字，按钮，标签
		vBox.getChildren().addAll(name, button, randomName);
		//设置居中
		vBox.setAlignment(Pos.CENTER);
		try {
			//从文本文件获取名单存入数组
			list = Files.lines(Paths.get("Data/StudentList.txt")).toArray();
		} catch (Exception e) {
			e.printStackTrace();
		}

		//lambda 表达式中使用的变量应为 final 或 effectively final
		Object[] finalList = list;
		//为button注册点击事件
		button.setOnAction(event -> {
			Random random = new Random();
			//设置随机数0～84
			int i = random.nextInt(84);
			//修改标签文本为随机选取的学号和姓名
			randomName.setText("恭喜" + finalList[i].toString() + "被选中！");
		});
		primaryStage.setScene(new Scene(vBox));
		primaryStage.setWidth(500);
		primaryStage.setHeight(500);
		primaryStage.setTitle("随机选人");
		primaryStage.show();
	}
}
