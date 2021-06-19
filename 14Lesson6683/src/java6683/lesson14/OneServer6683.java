package java6683.lesson14;

import java.io.IOException;
import java.io.PrintStream;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class OneServer6683 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try {
			ServerSocket serverSocket = new ServerSocket(18888);
			System.out.println("18888服务平台");
			Socket socket = serverSocket.accept();
			//包装Socket中的IO字节流
			Scanner reader = new Scanner(socket.getInputStream());
			PrintStream print = new PrintStream(socket.getOutputStream());
			print.println("欢迎致电18888服务平台，很高兴为您服务。。。");
			String client = socket.getInetAddress().getHostAddress() + ":" + socket.getPort();
			System.out.println(client + ",已接入");
			int count = 1;
			String problem;
			while (true) {
				problem = reader.nextLine().trim();
				if ("谢谢".equals(problem)) {
					print.println("再见");
					System.out.println("结束" + ":" + client);
					break;
				}
				System.out.println("问" + count++ + ":" + problem);
				System.out.print("请输入答案：");
				print.println(sc.nextLine());
			}
			sc.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
