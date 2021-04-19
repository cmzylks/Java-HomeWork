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
	 * 1.这个方法只能生成一层一层的文件夹，不能生成文件，而你的file对象路径是直接到文件那一层的，
	 * 不用getParentFile()获得父目录的话，就会想你说的那样生成两个文件夹而不是你想要的文件，所以要先调用getParentFile()获得父目录，
	 * 用.mkdirs()生成父目录文件夹，最后把你想要的文件生成到这个文件夹下面，就是想要的结果如果删除，当创建带目录的文件时就会抛异常
	 * 2.getParentFile()的作用是获得父目录
	 * 3.此路径名没有指定父目录
	 * 4.mkdirs(); 生成所有目录  mkdir(); 必须AAA目录存在才能生成BBB目录 程序抛异常
	 * 5.直接生成f:\java\temp.txt为文件名的文件
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
