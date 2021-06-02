package java6683.file;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Elxect
 * @date 2021/6/1 8:00 上午
 */
public class Export6683Controller {
	public TextField tfDir;
	public TextField tfSuffix;
	public Button btnExport;
	public Label lblExport;
	private Path path;

	/**
	 * 获取主目录
	 */
	public void getDir() {
		DirectoryChooser directoryChooser = new DirectoryChooser();
		File file = directoryChooser.showDialog(new Stage());
		directoryChooser.setTitle("选择目录");
		if (file != null && file.exists()) {
			path = file.toPath();
			tfDir.setText(path.toString());
			tfDir.setDisable(false);
			btnExport.setDisable(false);
		}
	}

	/**
	 * 导出文件名
	 */
	public void export() {
		try {
			Stream<Path> list = Files.list(path);
			//过滤非文件
			List<String> fileNameList = list.filter(item -> item.toFile().isFile())
							//过滤不匹配的对象
							.filter(item -> {
								if (tfSuffix.getText().trim().length() == 0) {
									return true;
								} else {
									return matchingExtension(getFileSuffix(item.getFileName().toString()));
								}
							})
							//Stream<Path>转成List<String>
							.flatMap(item -> Arrays.stream(new String[]{item.getFileName().toString()}))
							.collect(Collectors.toList());
			//写入文件
			Files.write(Paths.get("new.txt"), fileNameList);
			lblExport.setText("导出文件名：" + fileNameList.size());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 获取输入的后缀名
	 *
	 * @return 返回输入的后缀名集
	 */
	public List<String> getExtensionName() {
		List<String> stringList = new ArrayList<>();
		//获取输入扩展名
		String extensionName = tfSuffix.getText().trim();
		//空格分割
		Stream<String> stringStream = Stream.of(extensionName.split(" "));
		stringStream.forEach(s -> {
			//将不是空格的数据添加到集合中
			if (!s.isEmpty()) {
				stringList.add("." + s);
			}
		});
		return stringList;
	}

	/**
	 * 获取指定文件或文件夹的后缀名
	 *
	 * @param fileName 文件名称
	 */
	public String getFileSuffix(String fileName) {
		//例如：abc.png  截取后：.png
		return fileName.substring(fileName.lastIndexOf("."));
	}

	/**
	 * 匹配后缀名集
	 *
	 * @param fileSuffix 后缀名
	 */
	public boolean matchingExtension(String fileSuffix) {
		boolean flag = false;
		for (String s : getExtensionName()) {
			if (s.equals(fileSuffix)) {
				flag = true;
				break;
			}
		}
		return flag;
	}
}
