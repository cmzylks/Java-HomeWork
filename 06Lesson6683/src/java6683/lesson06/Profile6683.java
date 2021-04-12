package java6683.lesson06;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Elxect
 * @date 2021/4/12 8:51 上午
 */
public class Profile6683 {
	public static void main(String[] args) {
		Properties properties = new Properties();
		InputStream resourceAsStream = Profile6683.class.getResourceAsStream("myProFile6683.properties");
		try {
			properties.load(resourceAsStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(properties.getProperty("id"));
		System.out.println(properties.getProperty("name"));
		System.out.println(properties.getProperty("sex"));
		System.out.println(properties.getProperty("age"));
	}
}
