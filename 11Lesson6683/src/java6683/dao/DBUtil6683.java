package java6683.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DBUtil6683 {
    // 定义静态变量，使用静态代码获取配置文件的值
    private static String url;
    private static String user;
    private static String password;

    // 使用静态代码注册驱动并给静态变量赋值
    static {
        try {
            // 创建Properties集合类
            Properties pro = new Properties();
            pro.load(new FileReader("config/database6683.properties"));
            // 给静态变量赋值
            url = pro.getProperty("url");
            user = pro.getProperty("user");
            password = pro.getProperty("pw");
            String driver = pro.getProperty("driver");
            // 注册驱动
            Class.forName(driver);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    /**
     * @return void
     * @Author Elxect
     * @Description 释放资源
     * @Param [stmt 执行sql的对象, conn 数据库连接对象]
     **/
    public static void close(Statement stmt, Connection conn) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @return void
     * @Author Elxect
     * @Description 释放资源Pro版
     * @Param [rs 结果集对象, stmt 执行sql的对象, conn 数据库连接对象]
     **/
    public static void close(ResultSet rs, Statement stmt, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
