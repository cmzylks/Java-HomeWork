package java6683.watch;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;

/**
 * @author Elxect
 * @date 2021/6/9 3:41 下午
 */
public class Watch6683 extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		URL url =getClass().getResource("Watch6683View.fxml");
		assert url != null;
		Pane root = FXMLLoader.load(url);
		primaryStage.setScene(new Scene(root));
		primaryStage.setTitle("文件监控");
		primaryStage.show();
	}
}
