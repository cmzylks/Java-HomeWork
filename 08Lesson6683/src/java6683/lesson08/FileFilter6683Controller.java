package java6683.lesson08;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

/**
 * @author Elxect
 */
public class FileFilter6683Controller {

	public ListView<String> lvFiles;
	public TextField tfDir;
	public Label lblMsg;
	public ToggleGroup tg;
	private int anInt = -1;
	private File file;
	private ObservableList<String> items;

	@FXML
	void initialize() {
		items = lvFiles.getItems();
		tg.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
			anInt = Integer.parseInt(newValue.getUserData().toString());
			switch (anInt) {
				case 1:
					try {
						items.setAll(inner6683(file));
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				case 2:
					try {
						items.setAll(lambda6683(file));
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				case 3:
					try {
						items.setAll(reference6683(file));
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				default:
					break;
			}
		});

	}

	public void listFile() {
		String url = tfDir.getText().trim();
		Path path = Paths.get(url.length() == 0 ? "./" : url);
		file = new File(String.valueOf(path));
		try {
			//默认使用匿名内部类
			items.setAll(inner6683(file));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 用匿名内部类实现文件过滤器接口FilenameFilter
	 *
	 * @param file 文件路径
	 * @return 过滤后的集合
	 */
	ArrayList<String> inner6683(File file) throws Exception {
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
				return new File(dir, name).length() / 1024 > 2;
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

	/**
	 * 用Lambda表达式实现文件过滤器接口FilenameFilter
	 *
	 * @param file 文件路径
	 * @return 过滤后的集合
	 */
	ArrayList<String> lambda6683(File file) {
		ArrayList<String> list = new ArrayList<>();
		File[] endListFiles = file.listFiles((dir, name) -> name.endsWith("." + "java"));
		assert endListFiles != null;
		addList(endListFiles, list, "(1)后缀为java的文件：" + endListFiles.length);
		File[] lengthListFiles = file.listFiles((dir, name) -> new File(dir, name).length() / 1024 > 2);
		assert lengthListFiles != null;
		addList(lengthListFiles, list, "(2)大于2K的文件：" + lengthListFiles.length);
		final LocalDateTime rq = LocalDateTime.of(2021, 4, 22, 0, 0);
		final long milli = rq.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
		File[] timeListFiles = file.listFiles((dir, name) -> new File(dir, name).lastModified() > milli);
		assert timeListFiles != null;
		addList(timeListFiles, list, "(3)三天前到现在修改过的文件：" + timeListFiles.length);
		return list;
	}

	/**
	 * 用方法引用实现文件过滤器接口FilenameFilter
	 *
	 * @param file 文件路径
	 * @return 过滤后的集合
	 */
	ArrayList<String> reference6683(File file) {
		ArrayList<String> list = new ArrayList<>();
		File[] endListFiles = file.listFiles(Filter::filterEnd);
		assert endListFiles != null;
		addList(endListFiles, list, "(1)后缀为java的文件：" + endListFiles.length);
		File[] lengthListFiles = file.listFiles(Filter::filterFileSize);
		assert lengthListFiles != null;
		addList(lengthListFiles, list, "(2)大于2K的文件：" + lengthListFiles.length);
		File[] timeListFiles = file.listFiles(Filter::filterFileTimer);
		assert timeListFiles != null;
		addList(timeListFiles, list, "(3)三天前到现在修改过的文件：" + timeListFiles.length);
		return list;
	}

	static class Filter {
		public static Boolean filterEnd(File dir, String name) {
			return name.endsWith("." + "java");
		}

		public static Boolean filterFileSize(File dir, String name) {
			return new File(dir, name).length() / 1024 > 2;
		}

		public static Boolean filterFileTimer(File dir, String name) {
			final LocalDateTime rq = LocalDateTime.of(2021, 4, 22, 0, 0);
			final long milli = rq.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
			return new File(dir, name).lastModified() > milli;
		}
	}

	void addList(File[] files, ArrayList<String> list, String msg) {
		list.add(msg);
		for (File file : files) {
			list.add(String.valueOf(file));
		}
	}


}