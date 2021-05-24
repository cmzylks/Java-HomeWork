package java6683.lesson11;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCDemo6683 {
    private static final String DRIVE = "org.sqlite.JDBC";
    private static final String DBNAME = "db/db6683.db3";
    private static final String URL = "jdbc:sqlite:" + DBNAME;
    private static final String USER = "";
    private static final String PW = "";

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName(DRIVE);
        Connection conn = DriverManager.getConnection(URL, USER, PW);
        System.out.println("使用的数据库版本:" + conn.getMetaData().getDatabaseProductName());
        System.out.println("版本号:" + conn.getMetaData().getDatabaseProductVersion());
        System.out.println("数据库的URL:" + conn.getMetaData().getURL());
    }
}
