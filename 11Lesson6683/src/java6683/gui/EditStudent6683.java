package java6683.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;

public class EditStudent6683 extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		URL url = getClass().getResource("EditStudent6683View.fxml");
		if (url != null) {
			Pane root = FXMLLoader.load(url);
			primaryStage.setScene(new Scene(root));
		}
		primaryStage.setTitle("维护学生数据表");
		primaryStage.show();
	}
}
