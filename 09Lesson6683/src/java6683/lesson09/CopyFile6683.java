package java6683.lesson09;

import java.io.*;
import java.util.Scanner;


/**
 * @author Elxect
 */
public class CopyFile6683 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("请输入要拷贝的源文件名：");
		String source = input.nextLine();
		System.out.print("请输入目标文件名：");
		String target = input.nextLine();
		//调用checkFileXXXX()方法检查两个文件名是否可用
		if (checkFile6683(source, target)) {
			//调用System.currentTimeMillis()记下开始时间-->beginTime
			long startTime = System.currentTimeMillis();
			//如果两个文件名可用，调用copyXXXX()方法完成文件拷贝，并返回文件的字节数
			long size = copy6683(source, target);
			//调用System.currentTimeMillis()得到当前时间，计算拷贝文件所用的时长
			long endTime = System.currentTimeMillis();
			//输出拷贝文件的字节数和用时
			System.out.println("拷贝" + size + "字节," + "用时:" + (endTime - startTime) + "ms");
		}
	}

	/**
	 * 检查源文件和目标文件是否可用
	 *
	 * @param sourceName 源文件
	 * @param targetName 目标文件
	 * @return boolean 检查是否通过
	 */
	private static boolean checkFile6683(String sourceName, String targetName) {
		File sourceFile = new File(sourceName);
		//确保源文件已经存在
		if (sourceFile.exists()) {
			File targetFile = new File(targetName);
			if (targetFile.isDirectory() || sourceFile.isDirectory()) {
				System.out.println("源文件或目标文件是一个目录");
				return false;
			}
			//调用File的getParentFile()，得到目标文件名所包含的路径
			File targetParentFile = targetFile.getParentFile();
			//判断目标路径是否已经存在，如果不存在，创建
			if (!targetParentFile.exists()) {
				System.out.println("目录不存在");
				if (!targetParentFile.mkdirs()) {
					System.out.println("创建目录：" + targetParentFile.getAbsolutePath());
				}
			}
			return true;
		} else {
			System.out.println("源文件不存在");
			return false;
		}
	}

	/**
	 * 将源文件复制到目标文件
	 *
	 * @param sourceName 源文件
	 * @param targetName 目标文件
	 * @return 读到的字节数
	 */
	private static long copy6683(String sourceName, String targetName) {
		long size = 0;
		//针对目标文件，创建带缓冲的输出流
		try (BufferedOutputStream br = new BufferedOutputStream(new FileOutputStream(targetName));
				 //针对源文件，创建带缓冲的输入流
				 BufferedInputStream bw = new BufferedInputStream(new FileInputStream(sourceName))) {
			// 定义一个字节数组，作为缓冲区-->buff
			byte[] buff = new byte[1024];
			//循环执行下列步骤，直到文件结束
			int len;
			//1）从输入流中读取数据存入buff
			while ((len = bw.read(buff)) != -1) {
				//2）再把buff中的内容写入到输出流
				br.write(buff, 0, len);
				//3）累加读到的字节数-->size
				size += len;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		//返回字节数
		return size;
	}
}