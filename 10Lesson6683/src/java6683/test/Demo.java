package java6683.test;

import java.io.*;

public class Demo {
    public static void main(String[] args) throws IOException {
        copyFile("a.txt", "temp.txt");
    }

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
