package java6683.watch;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
import java.util.List;

/**
 * @author Elxect
 * @date 2021/6/9 3:41 下午
 */
public class Watch6683Controller {
	public ListView<WatchKey> lvWatches;
	public Button btnWork;
	public TextArea taRecords;
	private ObservableList<WatchKey> items;
	private boolean flag;
	private WatchService watcher;

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

	public void addDir() throws IOException, InterruptedException {
		DirectoryChooser directoryChooser = new DirectoryChooser();
		File file = directoryChooser.showDialog(new Stage());
		if (file.exists()) {
			items = lvWatches.getItems();
			//创建文件监视器
			watcher = FileSystems.getDefault().newWatchService();
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

	public void cancel() {
		int selectedIndex = lvWatches.getSelectionModel().getSelectedIndex();
		if (selectedIndex != -1) {
			items.remove(selectedIndex);
		}
	}

	public void work() {
		if (!flag) {
			try {
				startWatch();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			btnWork.setText("停止监控");
			flag = true;
		} else {
			btnWork.setText("记录修改记录");
			flag = false;
		}

	}

	public void clearRecords(ActionEvent actionEvent) {


	}

	public void startWatch() throws InterruptedException {
		WatchKey key = watcher.take();
		List<WatchEvent<?>> watchEvents = key.pollEvents();
		for (WatchEvent<?> watchEvent : watchEvents) {
			String msg = String.format("变化类型：%s，变化对象：%s\n",
							watchEvent.kind().name(),
							watchEvent.context().toString());
			taRecords.appendText(msg);
		}
	}


	public void stopWatch() {

	}
}
