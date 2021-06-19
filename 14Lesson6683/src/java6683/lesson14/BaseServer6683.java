package java6683.lesson14;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class BaseServer6683 {
	public static void main(String[] args) {

		try {
			ServerSocket ss = new ServerSocket(8888);
			System.out.println("等待客户接电话....");
			Socket s = ss.accept();
			System.out.println("客户:" + s.getInetAddress().getHostName() + "接听电话。。");

			BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			//读取客户端发送来的消息
			String mess = br.readLine();
			System.out.println(mess);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
			String s1 = "很高兴为您服务，我是工号：212006683 郑逢";
			bw.write(s1 + "\n");
			bw.flush();
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
