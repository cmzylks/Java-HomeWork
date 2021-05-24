package java6683.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Student6683Dao {
	private static Connection conn = null;
	private static PreparedStatement stmt = null;
	private static ResultSet rs = null;

	/**
	 * 向数据表xslist中添加一条记录
	 *
	 * @param student6683 学生对象
	 * @return 如果已向数据表添加了记录，返回新记录的id，否则返回-1
	 */
	public static int add6683(Student6683 student6683) {
		int id = -1;
		try {
			conn = DBUtil6683.getConnection();
			String sql = "insert into xslist(sno, sname) values (?,?)";
			// 使用PrepareStatement执行INSERT语句
			stmt = conn.prepareStatement(sql);
			// 将student对象的属性作为INSERT语句的参数
			stmt.setString(1, student6683.getSno());
			stmt.setString(2, student6683.getsName());
			stmt.executeUpdate();
			//执行INSERT语句后，利用PrepareStatement的getGeneratedKeys()方法返回的ResultSet，读取新增记录的id值（id是自增长列）
			rs = stmt.getGeneratedKeys();
			//为参数student对象设置id值
			student6683.setId(rs.getInt(1));
			//返回id值
			id = rs.getInt(1);
		} catch (SQLException e) {
			System.out.println("数据库连接异常");
		} finally {
			//关闭资源
			DBUtil6683.close(rs, stmt, conn);
		}
		return id;
	}

	public static int update6683(int id, Student6683 newStudent) {
		int i = 0;
		try {
			conn = DBUtil6683.getConnection();
			String sql = "update xslist set sno=?,sname=?,lx1=?,lx2=? where id=?";
			// 使用PrepareStatement执行update语句
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, newStudent.getSno());
			stmt.setString(2, newStudent.getsName());
			stmt.setInt(3, newStudent.getLx1());
			stmt.setInt(4, newStudent.getLx2());
			stmt.setInt(5, id);
			//返回1表示已修改，返回-1表示未修改
			i = stmt.executeUpdate() == 1 ? stmt.executeUpdate() : -1;
		} catch (SQLException e) {
			System.out.println("数据库连接异常");
		} finally {
			//关闭资源
			DBUtil6683.close(rs, stmt, conn);
		}
		return i;
	}

	public static int delete6683(int id) {
		int i = 0;
		try {
			conn = DBUtil6683.getConnection();
			String sql = "delete from xslist where id=?";
			// 使用PrepareStatement执行update语句
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			//返回1表示已删除，返回-1表示未删除
			i = stmt.executeUpdate() == 1 ? stmt.executeUpdate() : -1;
		} catch (SQLException e) {
			System.out.println("数据库连接异常");
		} finally {
			//关闭资源
			DBUtil6683.close(rs, stmt, conn);
		}
		return i;
	}


	public static List<Student6683> findAll6683() {
		List<Student6683> list = new ArrayList<>();
		try {
			conn = DBUtil6683.getConnection();
			String sql = "select * from xslist";
			// 使用PrepareStatement执行update语句
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			//遍历结果
			while (rs.next()) {
				//实例化学生对象
				Student6683 student6683 = new Student6683();
				//设置对应的每个属性
				student6683.setId(rs.getInt("id"));
				student6683.setSno(rs.getString("sno"));
				student6683.setsName(rs.getString("sname"));
				student6683.setLx1(rs.getInt("lx1"));
				student6683.setLx2(rs.getInt("lx2"));
				//添加到集合中
				list.add(student6683);
			}
		} catch (SQLException e) {
			System.out.println("数据库连接异常");
		} finally {
			//关闭资源
			DBUtil6683.close(rs, stmt, conn);
		}
		//表中没有数据返回null
		return list.size() == 0 ? null : list;
	}

	public static Student6683 findStudent6683(String sno) {
		try {
			conn = DBUtil6683.getConnection();
			String sql = "select * from xslist where sno = ?";
			// 使用PrepareStatement执行update语句
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, sno);
			rs = stmt.executeQuery();
			//实例化学生对象
			Student6683 student6683 = new Student6683();
			//设置对应的每个属性
			student6683.setId(rs.getInt("id"));
			student6683.setSno(rs.getString("sno"));
			student6683.setsName(rs.getString("sname"));
			student6683.setLx1(rs.getInt("lx1"));
			student6683.setLx2(rs.getInt("lx2"));
		} catch (SQLException e) {
			System.out.println("数据库连接异常");
		} finally {
			//关闭资源
			DBUtil6683.close(rs, stmt, conn);
		}
		return null;
	}
}
