package java6683.lesson09;

import java.io.File;
import java.util.Scanner;


/**
 * @author Elxect
 */
public class CopyFile6683 {
	public static void main(String[] args) throws Exception {
		Scanner input = new Scanner(System.in);
		System.out.print("请输入要拷贝的源文件名：");
		String source = input.nextLine();
		System.out.print("请输入目标文件名：");
		String target = input.nextLine();
		checkFile6683(source, target);
		//调用checkFileXXXX()方法检查两个文件名是否可用
		//调用System.currentTimeMillis()记下开始时间-->beginTime
		//如果两个文件名可用，调用copyXXXX()方法完成文件拷贝，并返回文件的字节数
		//调用System.currentTimeMillis()得到当前时间，计算拷贝文件所用的时长
		//输出拷贝文件的字节数和用时
	}

	private static boolean checkFile6683(String sourceName, String targetName) {
		File sourceFile = new File(sourceName);
		if (sourceFile.exists()) {
			File targetFile = new File(targetName);
			File targetParentFile = targetFile.getParentFile();
			if (targetParentFile.exists()) {
				System.out.println("目录存在");
				return true;
			} else {
				System.out.println("目录不存在");
				targetParentFile.mkdirs();
				System.out.println("创建目录：" + targetParentFile.getAbsolutePath());
				return true;
			}
		} else {
			System.out.println("源文件不存在");
			return false;
		}
		//确保源文件已经存在
		//调用File的getParentFile()，得到目标文件名所包含的路径
		//判断目标路径是否已经存在，如果不存在，创建
	}

	private static long copy6683(String sourceName, String targetName) {
		//针对源文件，创建带缓冲的输入流
		//针对目标文件，创建带缓冲的输出流
		// 定义一个字节数组，作为缓冲区-->buff
		//循环执行下列步骤，直到文件结束
		//1）从输入流中读取数据存入buff
		//2）再把buff中的内容写入到输出流
		//3）累加读到的字节数-->size
		//关闭输入流和输出流
		//返回字节数
		return 0;
	}
}