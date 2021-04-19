package java6683.lesson07;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;

/**
 * @author Elxect
 * @date 2021/4/19 8:12 上午
 */
public class ListFile6683 extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		URL url = getClass().getResource("ListFile6683View.fxml");
		Pane root = FXMLLoader.load(url);
		primaryStage.setScene(new Scene(root));
		primaryStage.setTitle("列出目录的内容");
		primaryStage.show();
	}
}
