package java6683.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Dao6683 {
    private static Connection conn = null;
    private static PreparedStatement stmt = null;
    private static final ResultSet rs = null;

    public static void main(String[] args) throws SQLException {
        // delete6683(82);
        testAdd6683();
    }

    public static void delete6683(int id) {
        try {
            conn = DBUtil6683.getConnection();
            String sql = "delete from xslist where id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            int i = stmt.executeUpdate();
            System.out.println("删除:" + i);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil6683.close(rs, stmt, conn);
        }
    }

    public static void testAdd6683() {
        Student6683 student6683 = new Student6683("212006683", "郑逢");
        student6683.setLx1(90);
        student6683.setLx2(100);
        // System.out.println(Student6683Dao.update6683(0, student6683));
        System.out.println(Student6683Dao.delete6683(0));
    }
}
