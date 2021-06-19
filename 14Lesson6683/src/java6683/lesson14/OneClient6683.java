package java6683.lesson14;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Properties;
import java.util.Scanner;

public class OneClient6683 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 创建Properties集合类
		Properties pro = new Properties();
		try {
			pro.load(new FileReader("config/oneClient6683.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		String ip = pro.getProperty("ip");
		String port = pro.getProperty("port");
		try {
			Socket socket = new Socket(ip, Integer.parseInt(port));
			System.out.println("客户端");
			//包装Socket中的IO字节流
			Scanner reader = new Scanner(socket.getInputStream());
			PrintStream print = new PrintStream(socket.getOutputStream());
			System.out.println("连接：" + socket.getInetAddress().getHostAddress() + ":" + socket.getPort());
			String welcome = reader.nextLine().trim();
			System.out.println(port + ":" + welcome);
			String problem;
			while (true) {
				System.out.print("请输入问题：");
				problem = sc.nextLine().trim();
				print.println(problem);
				String reslut = reader.nextLine().trim();
				if ("再见".equals(reslut)) {
					System.out.println(port + ":" + reslut);
					break;
				}
				System.out.println(port + ":" + reslut);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
