package java6683.lesson10;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;

/**
 * @author Elxect
 * @date 2021/5/17 7:59 上午
 */
public class TextFile6683 extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		URL url = getClass().getResource("TextFile6683View.fxml");
		Pane root = FXMLLoader.load(url);
		primaryStage.setScene(new Scene(root));
		primaryStage.setTitle("读写文本文件");
		primaryStage.show();

	}
}
