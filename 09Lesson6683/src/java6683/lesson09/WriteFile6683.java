package java6683.lesson09;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Elxect
 * @date 2021/5/9 7:29 下午
 */
public class WriteFile6683 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("请输入文件名：");
		String pathName = sc.nextLine();
		File file = new File(pathName.trim());
//		如果filename中包含路径，必须确保路径已存在
		File parentFile = file.getParentFile();
		if (!parentFile.exists()) {
			System.out.println("路径不存在");
			return;
		}
		write6683(file);
		System.out.println("已向" + file.getAbsoluteFile() + "中写入" + file.length() + "字节");
	}

	/**
	 * 向文件写入数据
	 *
	 * @param file 文件路径地址
	 */
	public static void write6683(File file) {
		Scanner sc = new Scanner(System.in);
		FileWriter fw = null;

		try {
			//创建字符流
			fw = new FileWriter(file);
			System.out.println("请逐行输入要写入的内容，输入“end”结束：");
			String str;
			//存储输入的字符
			List<String> strList = new ArrayList<>();
			while (true) {
				str = sc.nextLine();
				if ("end".equalsIgnoreCase(str)) {
					break;
				}
				strList.add(str);
			}
			//遍历集合并逐行写入
			for (String s : strList) {
				fw.write(s);
				fw.write("\r");
			}
		} catch (IOException ioException) {
			ioException.printStackTrace();
		} finally {
			if (fw != null) {
				try {
					//关闭字符流
					fw.close();
				} catch (IOException ioException) {
					ioException.printStackTrace();
				}
			}
		}
	}
}
