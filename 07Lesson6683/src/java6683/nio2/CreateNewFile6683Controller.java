package java6683.nio2;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.nio.file.*;

/**
 * @author Elxect
 * @date 2021/4/19 8:13 下午
 */
public class CreateNewFile6683Controller {
	public TextField tfFilename;
	public Label lblMessage;

	public void listAll() throws IOException {
		String fileName = tfFilename.getText().trim();
		if (fileName.isEmpty()) {
			lblMessage.setText("请输入内容");
			return;
		}
		Path path = Paths.get(fileName);
		if (!Files.exists(path)) {
			Path parent = path.getParent();
			if (parent != null && !Files.exists(parent)) {
				try {
					Files.createDirectories(parent);
					Files.createFile(path);
				} catch (Exception e) {
					lblMessage.setText("不能创建目录：" + path.toAbsolutePath());
					return;
				}
				lblMessage.setText("创建目录：" + path.toAbsolutePath());
				return;
			}

			Files.createFile(path);
			lblMessage.setText("创建新文件：" + path.toAbsolutePath());
		} else {
			lblMessage.setText("文件已存在");
		}
	}
}
