package java6683.task;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.imageio.IIOException;
import java.net.URL;

public class Editor6683 extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		URL url = getClass().getResource("Editor6683View.fxml");
		assert url != null;
		FXMLLoader load = new FXMLLoader(url);
		Parent root = null;
		try {
			root = load.load();
		} catch (IIOException e) {
			e.printStackTrace();
		}
		assert root != null;
		primaryStage.setScene(new Scene(root));
		primaryStage.setTitle("文本编辑器");
		primaryStage.show();
		Editor6683Controller controller = load.getController();
		primaryStage.setOnCloseRequest(event -> {
			controller.setCloseRequest(primaryStage, event);
		});
	}
}
