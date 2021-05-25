package java6683.gui;

import java6683.dao.Student6683;
import java6683.dao.Student6683Dao;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;

import java.util.List;

public class EditStudent6683Controller {
	@FXML
	public Pane root;
	@FXML
	public TableView<Student6683> tvStudent;
	@FXML
	private TableColumn<Student6683, String> sNo;
	@FXML
	private TableColumn<Student6683, String> sName;
	@FXML
	private TableColumn<Student6683, Integer> lx1;
	@FXML
	private TableColumn<Student6683, Integer> lx2;
	/**
	 * 表集合
	 */
	private ObservableList<Student6683> items;
	/**
	 * 对话框控制器
	 */
	private Dialog6683Controller dialog6684;

	@FXML
	void initialize() {
		//查找数据库记录，渲染表格
		List<Student6683> allStudent = Student6683Dao.findAll6683();
		sNo.setCellValueFactory(new PropertyValueFactory<>("sno"));
		sName.setCellValueFactory(new PropertyValueFactory<>("name"));
		lx1.setCellValueFactory(new PropertyValueFactory<>("lx1"));
		lx2.setCellValueFactory(new PropertyValueFactory<>("lx2"));
		items = tvStudent.getItems();
		items.setAll(allStudent);
		//实例化控制器对象
		dialog6684 = createDialog6684();
		//监听鼠标双击事件
		tvStudent.setRowFactory(param -> {
			TableRow<Student6683> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!row.isEmpty())) {
					editData();
				}
			});
			return row;
		});
	}

	@FXML
	void editData() {
		//获取当前选择的对象和索引
		Student6683 selectedItem = tvStudent.getSelectionModel().getSelectedItem();
		int index = tvStudent.getSelectionModel().getFocusedIndex();
		if (selectedItem == null) {
			dialog6684.alertShow("请选择要修改的记录！");
			return;
		}
		//初始化并弹出对话框
		dialog6684.initChange6683(selectedItem);
		//获取修改的新对象
		Student6683 student = dialog6684.getStudent();
		if (student != null) {
			//数据库修改记录
			Student6683Dao.update6683(selectedItem.getId(), student);
			items.set(index, student);
			tvStudent.refresh();
			tvStudent.getSelectionModel().select(index);
			tvStudent.requestFocus();
			tvStudent.scrollTo(index);
		}

	}

	@FXML
	void addData() {
		//调用控制器初始化对话框
		dialog6684.initAdd6683();
		//获取添加的新对象
		Student6683 student = dialog6684.getStudent();
		if (student != null) {
			//数据库中添加记录
			Student6683Dao.add6683(student);
			items.add(student);
			tvStudent.refresh();
			tvStudent.getSelectionModel().select(student);
			tvStudent.requestFocus();
			tvStudent.scrollTo(student);
		}
	}


	@FXML
	void deleteData() {
		//获取当前选中的学生对象
		Student6683 selectedItem = tvStudent.getSelectionModel().getSelectedItem();
		if (selectedItem == null) {
			dialog6684.alertShow("请选择要删除的记录");
			return;
		}
		boolean status = dialog6684.alertConfigDialog("警告", "是否删除");
		if (status) {
			//数据库中删除记录
			Student6683Dao.delete6683(selectedItem.getId());
			items.remove(selectedItem);
			tvStudent.refresh();
		}
	}

	/**
	 * 加载创建对话框
	 *
	 * @return 返回对话框控制器
	 */
	Dialog6683Controller createDialog6684() {
		//实例化一个Stage作为对话框窗口
		Stage dialog = new Stage();
		//加载DialogXXXXView.fxml，创建根节点，并以此根节点创建Scene
		URL url = getClass().getResource("Dialog6683View.fxml");
		FXMLLoader load = new FXMLLoader(url);
		Parent root = null;
		try {
			root = load.load();
			dialog.setScene(new Scene(root));
		} catch (IOException e) {
			e.printStackTrace();
		}
		dialog.setTitle("学生信息编辑窗口");
		//返回对话框控制器
		return load.getController();
	}
}
