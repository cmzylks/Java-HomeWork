package java6683.test;

import java.io.*;

public class Demo {
    public static void main(String[] args) throws IOException {
        copyFile("a.txt", "temp.txt");
    }
    // 【错误1】
    // 行号: 7
    // 原因: 未设置偏移量,浪费不必要的资源，
    // 如果数组太大，则会对输出造成负担，如果太小则会导致从文件中读取的次数变多。
    //         【错误2】
    // 行号:11
    // 原因:没有返回值，需要返回文件字节数
    public static long copyFile(String name1, String name2) throws IOException {
        long size = 0;
        InputStream is = new BufferedInputStream(new FileInputStream(name1));
        OutputStream os = new BufferedOutputStream(new FileOutputStream(name2));
        byte[] datas = new byte[8192];
        int len;
        while ((len = is.read(datas)) != -1) {
            os.write(datas, 0, len);
            size += len;
        }
        is.close();
        os.close();
        return size;
    }
}
