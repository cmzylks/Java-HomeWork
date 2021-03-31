package java6683.ktv;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;

/**
 * @author Elxect
 * @date 2021/3/29 8:10 上午
 */
public class KTVSelect6683 extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		URL url = getClass().getResource("KTVSelect6683View.fxml");
		Pane root = FXMLLoader.load(url);
		primaryStage.setTitle("模拟KTV点歌");
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}
}
