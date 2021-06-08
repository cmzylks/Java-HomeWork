import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Elxect
 * @date 2021/6/1 10:31 上午
 */
public class FileSummary6683 {
	private static String path = System.getProperty("user.dir");
	private static int deep = 1;

	public static void main(String[] args) {
		checkParams6683(args);
		//输入的目录不存在，则遍历默认目录
		if (!Paths.get(path).toFile().exists()) {
			path = System.getProperty("user.dir");
		}
		//接收并遍历
		Map<String, Long> summary6683 = getSummary6683();
		if (summary6683 != null) {
			System.out.println("=====统计目录的各类文件=====");
			System.out.println("目录：" + path);
			System.out.println("统计目录的深度：" + deep);
			for (Map.Entry<String, Long> entry : summary6683.entrySet()) {
				String key = entry.getKey();
				Long value = entry.getValue();
				System.out.printf("%10s，%s\n", key, value);
			}
		}
	}

	public static Map<String, Long> getSummary6683() {
		Stream<Path> pathStream = null;
		try {
			//查找非目录文件
			pathStream = Files.find(Paths.get(path), deep,
							(p, basicFileAttributes) -> !basicFileAttributes.isDirectory());
		} catch (IOException e) {
			e.printStackTrace();
		}
		assert pathStream != null;
		//获取非目录文件的扩展名
		Stream<String> stringStream = pathStream.map(item -> {
			Path fileName = item.getFileName();
			return lastName(fileName);
		});
		//按将后缀名和文件数量分组统计并返回
		return stringStream.collect(
						Collectors.groupingBy(String::toString, Collectors.counting()));
	}

	/**
	 * 检查参数
	 *
	 * @param args 参数列表
	 */
	public static void checkParams6683(String[] args) {
		//参数列表为空时不做检查
		if (args.length == 0) {
			return;
		}
		//参数列表为1时
		if (args.length == 1) {
			//判断是否时数字
			if (isNumeric(args[0])) {
				//设置深度
				deep = Integer.parseInt(args[0]);
			} else {
				//设置路径
				path = args[0];
			}
			//两个或以上
		} else {
			//设置第一个参数为路径
			path = args[0];
			try {
				//设置第二个参数为深度
				deep = Integer.parseInt(args[1]);
			} catch (NumberFormatException e) {
				deep = 1;
			}
		}
	}

	/**
	 * 判断字符串是否是数字
	 *
	 * @param str 字符串
	 */
	public static boolean isNumeric(String str) {
		String regex = "[0-9]*";
		Pattern compile = Pattern.compile(regex);
		return compile.matcher(str).matches();
	}

	/**
	 * 获取文件后缀名
	 *
	 * @param file 文件
	 * @return 后缀名
	 */
	public static String lastName(Path file) {
		//文件不存
		if (file == null) {
			return null;
		}
		String filename = String.valueOf(file.getFileName());
		if (filename.lastIndexOf(".") == -1) {
			//文件没有后缀名的情况
			return "<none>";
		}
		return filename.substring(filename.lastIndexOf(".") + 1);
	}
}

