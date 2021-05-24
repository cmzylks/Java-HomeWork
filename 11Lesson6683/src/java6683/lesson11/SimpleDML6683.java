package java6683.lesson11;

import java.sql.*;

public class SimpleDML6683 {
    private static final String DRIVE = "org.sqlite.JDBC";
    private static final String DBNAME = "db/db6683.db3";
    private static final String URL = "jdbc:sqlite:" + DBNAME;
    private static final String USER = "";
    private static final String PW = "";

    public static void main(String[] args) {
        create6683();
        // for (int i = 0; i < 10; i++) {
        //     insert6683();
        // }
        // update6683();
        // delete6683();
        // String sql = "update xslist set lx1 =id*2,lx2=lx1+id";
        // int i = execute6683(sql);
        // System.out.println("执行" + i + "行");
    }

    public static void create6683() {
        String sql = "create table xslist(id integer primary key autoincrement not null ,sno text not null ,sname text,lx1 integer,lx2 integer)";
        execute6683(sql);
        System.out.println("创建表成功");
    }
    //
    // public static void insert6683() {
    //     String sql = "insert into xslist(sno,sname) values ('212006683','郑逢')";
    //     int i = execute6683(sql);
    //     System.out.println("插入成功");
    // }
    //
    // public static void delete6683() {
    //     String sql = "delete from xslist where lx1%3=0";
    //     int i = execute6683(sql);
    //     System.out.println("删除" + i + "行");
    // }
    //
    //
    // public static void update6683() {
    //     String sql = "update xslist set lx1 =id*2,lx2=lx1+id";
    //     int i = execute6683(sql);
    //     System.out.println("修改" + i + "行");
    // }

    public static int execute6683(String sql) {
        int i = 0;
        try {
            Class.forName(DRIVE);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection conn = DriverManager.getConnection(URL, USER, PW);
             PreparedStatement pst = conn.prepareStatement(sql)) {
            i = pst.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return i;
    }
}
