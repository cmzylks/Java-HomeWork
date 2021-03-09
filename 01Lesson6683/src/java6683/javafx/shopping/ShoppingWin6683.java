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


/**
 * @author Elxect
 * @deprecated 超市购物
 */
public class ShoppingWin6683 extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		//实例化根节点
		VBox root = new VBox(20);
		//实例化标签
		Label lblStudent = new Label("212006683 郑逢");
		//实例化虚线边框，为标签设置边框
		BorderStroke bs = new BorderStroke(Paint.valueOf("#8FBC8F")
						, BorderStrokeStyle.DOTTED
						, new CornerRadii(10)
						, new BorderWidths(3));
		lblStudent.setBorder(new Border(bs));
		lblStudent.setPadding(new Insets(10));

		//实例化水平盒子
		HBox hPerson = new HBox(10);
		Label lblPerson = new Label("购物者：");
		//输入框
		TextField tfPerson = new TextField();
		//设置输入框标题
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
		//实例化超市和商品清单
		Product6683[] products = {new Product6683("洗衣机"), new Product6683("电视机"), new Product6683("冰箱")};
		Market6683 market = new Market6683("永辉超市", products);

		//为按钮注册点击时间
		button.setOnAction(event -> {
			//实例化客户
			Person6683 person = new Person6683(tfPerson.getText());
			//初始化购物篮子
			String[] list;
			//分割输入的商品
			list = tfBuy.getText().split(",");
			//商品大于2个以上
			if (list.length > 1) {
				//初始化购物清单
				Product6683[] product6683s = new Product6683[100];
				for (int i = 0; i < list.length; i++) {
					//将输入的商品放到购物清单中
					Product6683 news = new Product6683(list[i]);
					product6683s[i] = news;
				}
				try {
					//超市中有客户所需的商品
					lblGet.setText(person.getName() + "在" + market.getName() + "买到了：" + person.shopping(market, product6683s));
				} catch (Exception e) {
					lblGet.setText("超市中没有你要购买的商品。");
				}
				//商品为1个的时候
			} else if (list.length == 1) {
				//超市中有客户所需的商品
				if (person.shopping(market, list[0]) != null) {
					lblGet.setText(person.getName() + "在" + market.getName() + "买到了：" + person.shopping(market, list[0]));
					return;
				}
				//输入错误或商品不存在
				lblGet.setText("请按要求输入或者您购买的商品不存在！");
			}
		});
		//将所有子节点添加到根节点中
		root.getChildren().addAll(lblStudent, hPerson, hBuy, button, lblGet);
		root.setPadding(new Insets(20.0));
		root.setAlignment(Pos.TOP_CENTER);
		//设置scene并添加根节点
		Scene scene = new Scene(root, 600, 400);
		primaryStage.setScene(scene);
		primaryStage.setTitle("超市购物");
		primaryStage.show();
	}


}
