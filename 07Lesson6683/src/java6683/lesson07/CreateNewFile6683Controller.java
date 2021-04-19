package java6683.lesson07;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;

/**
 * @author Elxect
 * @date 2021/4/19 10:14 上午
 */
public class CreateNewFile6683Controller {
	@FXML
	public TextField tfFilename;
	@FXML
	public Label lblMessage;

	/**
	 *
	 * 1.创建上级文件是否存在，如果删除，当创建带目录的文件时就会抛异常
	 * 2.
	 */
	@FXML
	void listAll() throws IOException {
		String fileName = tfFilename.getText().trim();
		File file = new File(fileName);
		if (!file.exists()) {
			File parentFile = file.getParentFile();
			if (parentFile != null && !parentFile.exists()) {
				if (!parentFile.mkdirs()) {
					throw new IOException("不能创建目录：" + parentFile);
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
