package java6683.borrow;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;

/**
 * @author Elxect
 * @date 2021/3/10 10:20 上午
 */
public class BorrowBooks6683 {

	public BorrowBooks6683(String name) {
		VBox vBox = new VBox();
		GridPane gp = new GridPane();
		Label lName = new Label(name + " 欢迎您！");
		Label bookId = new Label("图书ID：");
		TextField tId = new TextField();
		tId.setPromptText("请输入图书编号");
		Label bookName = new Label("图书名称：");
		TextField tName = new TextField();
		tName.setPromptText("请输入图书名称");
		Button button = new Button("借书");
		gp.add(bookId, 0, 0);
		gp.add(tId, 1, 0);
		gp.add(bookName, 2, 0);
		gp.add(tName, 3, 0);
		GridPane.setMargin(bookName, new Insets(0, 0, 0, 10));
		// 创建表格的内容清单
		List<Book6683> book6683List = Arrays.asList(
						new Book6683(1, "狂人日记", "鲁迅"),
						new Book6683(2, "云边有个小卖部", "张嘉佳"),
						new Book6683(3, "百年孤独", "莫言"),
						new Book6683(4, "人间失格", "太宰治"),
						new Book6683(5, "三国演义", "罗贯中"));
		// 把清单对象转换为JavaFX控件能够识别的数据对象
		ObservableList<Book6683> obList = FXCollections.observableArrayList(book6683List);
		// 依据指定数据创建表格视图
		TableView<Book6683> tableView = new TableView<>(obList);
		// 设置表格视图的推荐宽高
		tableView.setPrefSize(400, 300);
		// 创建一个表格列
		TableColumn firstColumn = new TableColumn("图书编号");
		// 设置列的最小宽度
		firstColumn.setMinWidth(50);
		// 设置该列取值对应的属性名称。此处序号列要展示book6683List元素的id属性值
		firstColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		// 创建一个表格列
		TableColumn secondColumn = new TableColumn("图书名称");
		// 设置列的最小宽度
		secondColumn.setMinWidth(200);
		// 设置该列取值对应的属性名称。此处名称列要展示book6683List元素的name属性值
		secondColumn.setCellValueFactory(new PropertyValueFactory<>("bookName"));
		// 创建一个表格列
		TableColumn thirdColumn = new TableColumn("图书作者");
		// 设置列的最小宽度
		thirdColumn.setMinWidth(126);
		// 设置该列取值对应的属性名称。此处价格列要展示book6683List元素的bookAuthor属性值
		thirdColumn.setCellValueFactory(new PropertyValueFactory<>("bookAuthor"));
		// 把几个标题列一齐添加到表格视图
		tableView.getColumns().addAll(firstColumn, secondColumn, thirdColumn);
		vBox.getChildren().add(lName);
		VBox.setMargin(lName, new Insets(0, 450, 20, 0));
		vBox.getChildren().add(tableView);
		VBox.setMargin(tableView, new Insets(0, 100, 50, 100));
		vBox.getChildren().add(gp);
		VBox.setMargin(gp, new Insets(0, 0, 20, 70));
		vBox.getChildren().add(button);
		VBox.setMargin(button, new Insets(0, 0, 0, 350));
		vBox.setAlignment(Pos.CENTER);
		Scene scene = new Scene(vBox);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setWidth(600);
		stage.setHeight(600);
		stage.setTitle("借书系统主界面");
		stage.show();

		//注册点击事件
		button.setOnAction(event -> {
			int inputId = 0;
			String inputName = null;
			//输入为空的情况
			try {
				inputId = Integer.parseInt(tId.getText());
				inputName = tName.getText();
			} catch (Exception e) {
				Alert alert = new Alert(Alert.AlertType.WARNING); // 创建一个消息对话框
				alert.setContentText("请输入数据！");
				alert.show(); // 显示对话框
				return;
			}
			//收查找到的图书对象
			Book6683 book6683 = searchBook(obList, inputId, inputName);
			if (book6683 != null) {
				Alert alert = new Alert(Alert.AlertType.CONFIRMATION); // 创建一个消息对话框
				alert.setContentText("借书成功！");
				alert.show(); // 显示对话框
				tId.setText("");
				tName.setText("");
				obList.remove(book6683);
			} else {
				//未查找到
				Alert alert = new Alert(Alert.AlertType.WARNING); // 创建一个消息对话框
				alert.setContentText("图书不存在！");
				alert.show(); // 显示对话框
				tId.setText("");
				tName.setText("");
			}
		});

	}

	/**
	 * @param obList    图书列表
	 * @param inputId   查询的图书id
	 * @param inputName 查询的图书名称
	 * @return 图书对象
	 */
	public Book6683 searchBook(ObservableList<Book6683> obList, int inputId, String inputName) {
		for (Book6683 book6683 : obList) {
			if (book6683.getId() == inputId && book6683.getBookName().equals(inputName)) {
				return book6683;
			}
		}
		return null;
	}
}


