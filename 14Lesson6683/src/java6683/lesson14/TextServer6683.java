package java6683.lesson14;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TextServer6683 {
	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(8888);
			System.out.println("服务器已启动，等待连接。。。");
			Socket socket = serverSocket.accept();
			System.out.println("客户端:" + socket.getLocalAddress().getHostName() + "已连接");
			System.out.println("等待客户端发送数据");
			InputStream socketInputStream = socket.getInputStream();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socketInputStream));
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("data/text6683.txt"));
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				System.out.println("客户端：" + line);
				bufferedWriter.write(line);
				bufferedWriter.newLine();
				bufferedWriter.flush();
			}
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
