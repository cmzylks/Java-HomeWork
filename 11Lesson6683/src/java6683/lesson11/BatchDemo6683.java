package java6683.lesson11;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.stream.IntStream;

public class BatchDemo6683 {
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

    public static void main(String[] args) throws Exception {
        // （1）加载并注册数据库驱动
        Class.forName(DRIVER);
        // （2）连接数据库
        Connection conn = DriverManager.getConnection(URL, USER, PW);
        // （3）执行SQL语句
        String sql = "INSERT INTO  xslist (SNO, SNAME) VALUES (?,?);";
        //默认自动提交自动提交事务，每次成功地执行一个SQL语句时，就会向数据库自动提交
        //开启事务（取消自动提交事务）
        conn.setAutoCommit(false);
        PreparedStatement preSt = conn.prepareStatement(sql);
        Files.lines(Paths.get("data/StudentList.txt")).forEach(line -> {
            String[] datas = line.split(" ");
            try {
                preSt.setString(1, datas[0]);
                preSt.setString(2, datas[1]);
                //添加到批处理中（批量传参）
                preSt.addBatch();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        //批量执行
        int[] results = preSt.executeBatch();
        //提交事务
        conn.commit();
        //恢复自动提交事务
        conn.setAutoCommit(true);

        // （4）关闭资源
        preSt.close();
        conn.close();
        //使用Stream统计执行结果（下一次课介绍）
        int success = IntStream.of(results).filter(n -> n > 0).sum();
        int fail = (int) IntStream.of(results).filter(n -> n < 0).count();
        System.out.println(String.format("成功添加：%d，失败：%d", success, fail));
    }
}