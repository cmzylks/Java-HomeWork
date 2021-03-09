package java6683.javafx.shopping;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class ShoppingWin6683 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox(20);
        Label lblStudent = new Label("填写自己的学号和姓名");
        BorderStroke bs = new BorderStroke(Paint.valueOf("#8FBC8F")
                , BorderStrokeStyle.DOTTED
                , new CornerRadii(10)
                , new BorderWidths(3));
        lblStudent.setBorder(new Border(bs));
        lblStudent.setPadding(new Insets(10));

        HBox hPerson = new HBox(10);
        Label lblPerson = new Label("购物者：");
        TextField tfPerson = new TextField();
        tfPerson.setPromptText("购物者姓名");
        hPerson.getChildren().addAll(lblPerson, tfPerson);

        HBox hBuy = new HBox(10);
        Label lblBuy = new Label("购买商品清单：");
        TextField tfBuy = new TextField();
        tfBuy.setPrefWidth(420);
        tfBuy.setPromptText("用逗号分隔的商品名称");
        hBuy.getChildren().addAll(lblBuy, tfBuy);

        Button button = new Button("购买");
        Label lblGet = new Label();

        root.getChildren().addAll(lblStudent, hPerson, hBuy, button, lblGet);
        root.setPadding(new Insets(20.0));
        root.setAlignment(Pos.TOP_CENTER);

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("超市购物");
        primaryStage.show();

    }


}
