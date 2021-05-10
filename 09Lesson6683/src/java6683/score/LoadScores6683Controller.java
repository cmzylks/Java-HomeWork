package java6683.score;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LoadScores6683Controller {
	public Label lblFilename;
	public TableView<Score6683> tvScores;
	public TableColumn<Score6683, String> colName;
	public TableColumn<Score6683, String> colChinese;
	public TableColumn<Score6683, String> colMath;
	public TableColumn<Score6683, String> colEnglish;
	public TableColumn<Score6683, String> colSum;
	public ObservableList<Score6683> items;

	public void openFile(ActionEvent actionEvent) {
		//创建文件选择对象
		FileChooser fc = new FileChooser();
		//设置标题和默认路径
		fc.setTitle("请选择学生文件");
		fc.setInitialDirectory(new File("./"));
		//创建两种文件选择类型
		FileChooser.ExtensionFilter txtFilter = new FileChooser.ExtensionFilter("文本文件", "*.txt");
		FileChooser.ExtensionFilter allFilter = new FileChooser.ExtensionFilter("所以文件", "*.*");
		//添加到文件选择对象中
		fc.getExtensionFilters().add(txtFilter);
		fc.getExtensionFilters().add(allFilter);
		//设置默认的文件选择类型
		fc.setSelectedExtensionFilter(txtFilter);
		//返回选择的文件
		File file = fc.showOpenDialog(new Stage());
		//文件存在且是txt文件
		if (file != null && file.getName().endsWith(".txt")) {
			//读取文件返回数据集
			List<Score6683> score6683s = readData(file);
			//渲染数据集合返回数据条数
			int size = setTvScores(score6683s);
			lblFilename.setText(size + "条学生成绩数据：" + file.getAbsolutePath());
		}
	}

	/**
	 * 读取文件内的数据
	 *
	 * @param file 文件
	 * @return 返回文件数据集合
	 */
	public List<Score6683> readData(File file) {
		List<Score6683> score6683s = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = br.readLine()) != null) {
				//分割每个数据
				String[] split = line.split(",");
				//区分无效数据
				if (split.length > 1) {
					//创建对象并设置对应的属性
					Score6683 score6683 = new Score6683(split[0], Integer.parseInt(split[1]), Integer.parseInt(split[2]), Integer.parseInt(split[3]));
					score6683s.add(score6683);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return score6683s;
	}

	/**
	 * 渲染数据
	 *
	 * @param score6683s 文件中的集合数据
	 * @return 返回数据条数
	 */
	public int setTvScores(List<Score6683> score6683s) {
		items = tvScores.getItems();
		//设置数据的对应字段
		colName.setCellValueFactory(new PropertyValueFactory<>("name"));
		colChinese.setCellValueFactory(new PropertyValueFactory<>("chinese"));
		colMath.setCellValueFactory(new PropertyValueFactory<>("math"));
		colEnglish.setCellValueFactory(new PropertyValueFactory<>("english"));
		colSum.setCellValueFactory(new PropertyValueFactory<>("sum"));
		//渲染列表
		items.setAll(score6683s);
		//多字段排序
		items.sort(Comparator.comparing(Score6683::getSum).reversed()
						.thenComparing(Score6683::getChinese)
						.thenComparing(Score6683::getMath)
						.thenComparing(Score6683::getEnglish)
						.thenComparing(Score6683::getName)
		);
		return items.size();
	}
}
