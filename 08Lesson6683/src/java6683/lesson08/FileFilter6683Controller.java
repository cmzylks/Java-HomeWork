package java6683.lesson08;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.function.Function;

public class FileFilter6683Controller {

	public ListView<String> lvFiles;
	public TextField tfDir;
	public Label lblMsg;
	public ToggleGroup tg;
	public RadioButton rb1;
	public RadioButton rb2;
	public RadioButton rb3;

	public void listFile(ActionEvent actionEvent) {
		String url = tfDir.getText().trim();
		Path path = Paths.get(url.equals("") ? "08Lesson6683\\src\\java6683\\lesson08" : url);
		File file = new File(String.valueOf(path));
		ArrayList<String> files = lambda6683(file);
		ObservableList<String> items = lvFiles.getItems();
		items.addAll(files);
	}

	ArrayList<String> inner6683(File file) {
		ArrayList<String> list = new ArrayList<>();
		File[] endListFiles = file.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith("." + "java");
			}
		});
		assert endListFiles != null;
		addList(endListFiles, list, "(1)后缀为java的文件：" + endListFiles.length);
		File[] lengthListFiles = file.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return new File(dir, name).length() / 1024 > 2 * 1024;
			}
		});
		assert lengthListFiles != null;
		addList(lengthListFiles, list, "(2)大于2K的文件：" + lengthListFiles.length);
		File[] timeListFiles = file.listFiles(new FilenameFilter() {
			final LocalDateTime rq = LocalDateTime.of(2021, 4, 22, 0, 0);
			final long milli = rq.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

			@Override
			public boolean accept(File dir, String name) {
				return new File(dir, name).lastModified() > milli;
			}
		});
		assert timeListFiles != null;
		addList(timeListFiles, list, "(3)三天前到现在修改过的文件：" + timeListFiles.length);
		return list;
	}

	ArrayList<String> lambda6683(File file) {
		ArrayList<String> list = new ArrayList<>();
		File[] endListFiles = file.listFiles();
		assert endListFiles != null;
		addList(endListFiles, list, "(1)后缀为java的文件：" + endListFiles.length);
		File[] lengthListFiles = file.listFiles((dir, name) -> new File(dir, name).length() / 1024 > 2 * 1024);
		assert lengthListFiles != null;
		addList(lengthListFiles, list, "(2)大于2K的文件：" + lengthListFiles.length);
		final LocalDateTime rq = LocalDateTime.of(2021, 4, 22, 0, 0);
		final long milli = rq.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
		File[] timeListFiles = file.listFiles((dir, name) -> new File(dir, name).lastModified() > milli);
		assert timeListFiles != null;
		addList(timeListFiles, list, "(3)三天前到现在修改过的文件：" + timeListFiles.length);
		return list;
	}

	ArrayList<String> reference6683(File file) {
		ArrayList<String> list = new ArrayList<>();
		File[] endListFiles = file.listFiles((dir, name) -> name.endsWith("." + "java"));
		assert endListFiles != null;
		addList(endListFiles, list, "(1)后缀为java的文件：" + endListFiles.length);
		File[] lengthListFiles = file.listFiles((dir, name) -> new File(dir, name).length() / 1024 > 2 * 1024);
		assert lengthListFiles != null;
		addList(lengthListFiles, list, "(2)大于2K的文件：" + lengthListFiles.length);
		final LocalDateTime rq = LocalDateTime.of(2021, 4, 22, 0, 0);
		final long milli = rq.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
		File[] timeListFiles = file.listFiles((dir, name) -> new File(dir, name).lastModified() > milli);
		assert timeListFiles != null;
		addList(timeListFiles, list, "(3)三天前到现在修改过的文件：" + timeListFiles.length);
		return list;
	}

	void addList(File[] files, ArrayList<String> list, String msg) {
		list.add(msg);
		for (File file : files) {
			list.add(String.valueOf(file));
		}
	}
}