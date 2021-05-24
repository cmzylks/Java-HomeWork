package java6683.lesson11;

import java.sql.*;
import java.util.Scanner;

public class Select6683 {
    private static final String DRIVER;
    private static final String URL;
    private static final String USER;
    private static final String PW;
    private static final String DBNAME;

    static {
        //使用SQLite
        DRIVER = "org.sqlite.JDBC";
        DBNAME = "db/db6683.db3";
        URL = "jdbc:sqlite:" + DBNAME;
        USER = null;
        PW = null;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入要查找的名字中包含的任何信息:");
        String strFind = sc.nextLine();
        System.out.print("请输入id范围:");
        int maxId = sc.nextInt();
        find6683(strFind, maxId);
    }

    public static void find6683(String strFind, int maxId) {
        String sql = "select id, sno, sname from xslist where sname like ? and id<?";
        // （1）加载并注册数据库驱动
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // (2)连接数据库
        try (Connection conn = DriverManager.getConnection(URL, USER, PW);
             //执行带参数的sql语句
             PreparedStatement pst = conn.prepareStatement(sql)) {
            //给sql语句传参
            pst.setString(1, "%" + strFind + "%");
            pst.setInt(2, maxId);
            String sno, sname;
            //获取查询结果
            ResultSet resultSet = pst.executeQuery();
            //遍历结果输出
            while (resultSet.next()) {
                sno = resultSet.getString("sno");
                sname = resultSet.getString(3);
                System.out.printf("%d,%9s,%-10s\n", resultSet.getInt(1), sno, sname);
            }
            resultSet.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
