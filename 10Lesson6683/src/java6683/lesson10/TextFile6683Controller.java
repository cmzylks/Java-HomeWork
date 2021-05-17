package java6683.lesson10;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Elxect
 * @date 2021/5/17 7:58 上午
 */
public class TextFile6683Controller {
    public Label lblSource;
    public Label lblTarget;
    public TextArea taText;
    public File sourceFile;
    public File targetFile;
    public StringBuilder text = new StringBuilder();


    public void selectSource(ActionEvent actionEvent) {
        sourceFile = getFc("选择源文件");
        lblSource.setText(String.valueOf(sourceFile));
    }

    public void selectTarget(ActionEvent actionEvent) {
        targetFile = getFc("选择目标文件");
        lblTarget.setText(String.valueOf(targetFile));
    }

    public void copyFile(ActionEvent actionEvent) {
        if (sourceFile == null || targetFile == null) {
            taText.setText("请选择文本文件");
            return;
        }
        try (
                Scanner scInput = new Scanner(sourceFile);
                PrintStream printStream = new PrintStream(targetFile);
        ) {

            while (scInput.hasNextLine()) {
//				（1）从文本文件中读一行
                String s = scInput.nextLine();
//				（2）将当前行写入目标文件
                printStream.println(s);
//				（3）将当前行添加到文本域
                text.append(s).append("\r\n");

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        taText.setText(text.toString());
    }

    public File getFc(String str) {
        FileChooser fc = new FileChooser();
        //设置标题和默认路径
        fc.setTitle(str);
        fc.setInitialDirectory(new File("./"));
        //创建两种文件选择类型
        FileChooser.ExtensionFilter txtFilter = new FileChooser.ExtensionFilter("文本文件", "*.txt");
        //添加到文件选择对象中
        fc.getExtensionFilters().add(txtFilter);
        //返回选择的文件
        return fc.showOpenDialog(new Stage());
    }
}
