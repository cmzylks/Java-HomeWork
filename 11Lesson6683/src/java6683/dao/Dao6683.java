package java6683.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Dao6683 {
	private static Connection conn = null;
	private static PreparedStatement stmt = null;
	private static final ResultSet rs = null;

	public static void main(String[] args) {
		delete6683(83);
		int id = testAdd6683();
		testFindAll6683();
		testUpdate6683(id);
		testDelete6683(id);
	}

	/**
	 * 删除xslist表中的指定ID的记录
	 *
	 * @param id 指定id
	 */
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

	/**
	 * 测试添加记录
	 */
	public static int testAdd6683() {
		Student6683 student = new Student6683();
		student.setSno("add123");
		student.setsName("数据访问");
		student.setLx1(70);
		student.setLx2(90);
		int id = Student6683Dao.add6683(student);
		System.out.println("id=" + id);
		return id;
	}

	/**
	 * 测试查找所有记录
	 */
	public static void testFindAll6683() {
		List<Student6683> list = Student6683Dao.findAll6683();
		if (list != null) {
			for (Student6683 student6683 : list) {
				System.out.println(student6683);
			}
		}
	}

	/**
	 * 测试修改记录
	 *
	 * @param id 根据id修改
	 */
	public static void testUpdate6683(int id) {
		Student6683 newStudent = new Student6683();
		newStudent.setSno("add123");
		newStudent.setsName("数据更新");
		newStudent.setLx1(100);
		newStudent.setLx2(100);
		int flag = Student6683Dao.update6683(id, newStudent);
		System.out.println("修改:" + flag);
	}

	/**
	 * 测试删除记录
	 *
	 * @param id 根据id删除
	 */
	public static void testDelete6683(int id) {
		int i = Student6683Dao.delete6683(id);
		System.out.println("删除:" + i);
	}
}
