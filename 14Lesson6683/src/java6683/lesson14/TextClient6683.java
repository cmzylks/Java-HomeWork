package java6683.lesson14;

import java.io.*;
import java.net.Socket;

public class TextClient6683 {
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("127.0.0.1", 8888);
			System.out.println("连接服务器成功！请从键盘输入数据：");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			String line;
			while ((line = br.readLine()) != null) {
				if ("stop".equals(line)) {
					break;
				}
				bw.write(line);
				bw.newLine();
				bw.flush();
			}
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
