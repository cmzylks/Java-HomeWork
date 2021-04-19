package java6683.nio2;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Elxect
 * @date 2021/4/19 8:13 下午
 */
public class CreateNewFile6683Controller {
	public TextField tfFilename;
	public Label lblMessage;

	public void listAll() throws IOException {
		String fileName = tfFilename.getText().trim();
		File file = new File(fileName);
		if (!file.exists()) {
			File parentFile = file.getParentFile();
			if (parentFile != null && !parentFile.exists()) {
				Path path = Paths.get(parentFile.toURI());
				System.out.println(path);
				if (!parentFile.mkdirs()) {
					if (Files.deleteIfExists(path)) {
						lblMessage.setText("不能创建目录：" + path);
						return;
					}
				}
				lblMessage.setText("创建目录:" + parentFile);
			}
			file.createNewFile();
			lblMessage.setText("创建新文件：" + file.getAbsolutePath());
		} else {
			lblMessage.setText("文件已存在");
		}
	}
}
