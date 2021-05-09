package java6683.lesson09;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.*;

/**
 * @author Elxect
 * @date 2021/5/9 10:19 下午
 */
public class ReadFile6683Controller {

	public TextField tfFilename;
	public TextArea taText;

	public void readFile() {
		String pathName = tfFilename.getText();
		File file = new File(pathName);
		//创建字符缓存流对象
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			StringBuilder sb = new StringBuilder();
			String line = null;
			//保证读到的数据完整
			while ((line = br.readLine()) != null) {
				//读取一行后换行
				sb.append(line).append("\n");
			}
			//渲染数据
			taText.setText(sb.toString());
		} catch (FileNotFoundException e) {
			taText.setText("文件不存在！");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
