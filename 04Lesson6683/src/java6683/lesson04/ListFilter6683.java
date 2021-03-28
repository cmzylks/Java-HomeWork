package java6683.lesson04;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;

/**
 * @author Elxect
 * @date 2021/3/28 10:35 下午
 */
public class ListFilter6683 extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		URL url = getClass().getResource("ListFilter6683View.fxml");
		Pane root = FXMLLoader.load(url);
		primaryStage.setScene(new Scene(root));
		primaryStage.setTitle("筛选随机数");
		primaryStage.show();
	}
}
