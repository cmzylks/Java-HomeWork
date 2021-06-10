package java6683.watch;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Elxect
 * @date 2021/6/9 3:41 下午
 */
public class Watch6683Controller {
	public ListView<WatchKey> lvWatches;
	public Button btnWork;
	public TextArea taRecords;
	private static ObservableList<WatchKey> items;
	/**
	 * 按钮修改标记
	 */
	private boolean flag;
	/**
	 * 文件路径
	 */
	private static Path absolutePath;
	/**
	 * 文件操作类型
	 */
	private static String kindName;
	/**
	 * 线程标记
	 */
	private static boolean keepRunning;

	@FXML
	void initialize() {
		items = lvWatches.getItems();
		StringConverter<WatchKey> converter = new StringConverter<WatchKey>() {
			@Override
			public String toString(WatchKey key) {
				return key.watchable().toString();
			}

			@Override
			public WatchKey fromString(String string) {
				return null;
			}
		};
		lvWatches.setCellFactory(TextFieldListCell.forListView(converter));
	}

	public void addDir() throws IOException {
		DirectoryChooser directoryChooser = new DirectoryChooser();
		File file = directoryChooser.showDialog(new Stage());
		if (null != file) {
			items = lvWatches.getItems();
			//创建文件监视器
			WatchService watcher = FileSystems.getDefault().newWatchService();
			//指定监听那些变化
			WatchEvent.Kind[] kinds = {
							// 新增
							StandardWatchEventKinds.ENTRY_CREATE,
							// 修改
							StandardWatchEventKinds.ENTRY_MODIFY,
							// 删除
							StandardWatchEventKinds.ENTRY_DELETE
			};
			//绑定目录
			WatchKey key = file.toPath().register(watcher, kinds);
			items.add(key);
		}
	}

	/**
	 * 删除目录
	 */
	public void cancel() {
		int selectedIndex = lvWatches.getSelectionModel().getSelectedIndex();
		if (selectedIndex != -1) {
			items.remove(selectedIndex);
		}
	}

	public void work() {
		if (!flag) {
			if (items.size() == 0) {
				alertShow("请先添加目录！！");
				return;
			}
			keepRunning = true;
			new Thread(this::startWatch).start();
			btnWork.setText("停止监控");
			flag = true;
		} else {
			stopWatch();
			btnWork.setText("记录修改记录");
			flag = false;
		}
	}

	/**
	 * 清空记录
	 */
	public void clearRecords() {
		if (flag) {
			alertShow("请先停止监控！！");
		} else {
			taRecords.clear();
		}
	}

	/**
	 * 开始监听
	 */
	void startWatch() {
		taRecords.appendText(getTime() + "：WatchService服务已启动\n");
		taRecords.appendText(getTime() + "：开始记录修改历史\n");
		//通过标记开启或关闭线程
		while (keepRunning) {
			//遍历watchKey集合
			items.forEach(key -> {
				//获取消息队列
				List<WatchEvent<?>> watchEvents = key.pollEvents();
				//获取具体操作
				watchEvents.forEach(event -> {
					String url = event.context().toString();
					absolutePath = Paths.get(url).toAbsolutePath();
					kindName = event.kind().name();
					taRecords.appendText(absolutePath.toString() + "\n");
					taRecords.appendText(getTime() + "====>" + kindName + "\n");
					//重置watchKey
					key.reset();
				});
			});

		}
	}

	/**
	 * 停止监听
	 */
	public void stopWatch() {
		taRecords.appendText(getTime() + "：WatchService服务已停止\n");
		keepRunning = false;
	}

	/**
	 * 获取当前时间
	 *
	 * @return
	 */
	public static String getTime() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}

	/**
	 * 提示窗
	 *
	 * @param msg
	 */
	public void alertShow(String msg) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setContentText(msg);
		alert.show();
	}
}
