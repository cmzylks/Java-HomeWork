package java6683.newdate;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;

/**
 * @author 郑逢
 * @date 2021/3/22 6:48 下午
 */
public class BirthDay6683 extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		URL url = getClass().getResource("BirthDay6683View.fxml");
		VBox root = FXMLLoader.load(url);
		Scene scene = new Scene(root, 500, 500);
		primaryStage.setScene(scene);
		primaryStage.setTitle("生日计算");
		primaryStage.show();
	}
}
