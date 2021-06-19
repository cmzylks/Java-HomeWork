package java6683.lesson14;


import java.io.*;
import java.net.Socket;

public class BaseClient6683 {
	public static void main(String[] args) {
		try {
			Socket s = new Socket("127.0.0.1", 8888);
			System.out.println("客户拨号···");
			//构建IO
			InputStream is = s.getInputStream();
			OutputStream os = s.getOutputStream();

			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
			//向服务器端发送一条消息
			bw.write("空调不制冷--212006683郑逢\n");
			bw.flush();

			//读取服务器返回的消息
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String mess = br.readLine();
			System.out.println(mess);
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
