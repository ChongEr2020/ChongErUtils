package ChongErUtils.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

/**
 * 这个类用来开发JDBC的工具类 
 * 1 私有化构造函数 
 * 2 提供静态方法getConnection，用来对外提供数据库连接对象 
 * 3 提供静态方法close，用来释放资源
 * @author chonger
 *
 */
public class JDBCUtils {
	
//	 私有化构造函数
	private JDBCUtils() {
	}
	
	/**
	 * 提供静态方法getConnection，用来对外提供数据库连接对象
	 * @return Connection
	 */
	public static Connection getConnection() {
		ResourceBundle rb = ResourceBundle.getBundle("jdbc");//读取属性文件数据
		try {
			Class.forName(rb.getString("driverClass"));
			Connection conn = DriverManager.getConnection(
					rb.getString("jdbcUrl"), 
					rb.getString("user"), 
					rb.getString("password"));
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 提供静态方法close，用来释放资源
	 * @param rs ResultSet
	 * @param st Statement
	 * @param conn Connection
	 */
	public static void close(ResultSet rs,Statement st,Connection conn) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				rs = null;
			}
		}
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				st = null;
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				conn = null;
			}
		}
	}
}
