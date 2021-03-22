package java6683.newdate;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;

public class MothersDay6683 extends Application  {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url = getClass().getResource("MothersDay6683View.fxml");
        VBox root = FXMLLoader.load(url);
        Scene scene = new Scene(root,500,500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
