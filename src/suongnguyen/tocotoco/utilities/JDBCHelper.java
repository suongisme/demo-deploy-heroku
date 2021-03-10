package suongnguyen.tocotoco.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCHelper {
	
	private static Connection con;
	private static final String URL = "jdbc:postgresql://ec2-54-164-22-242.compute-1.amazonaws.com:5432/d96rdh9u1hmqhm";
	private static final String USER_NAME = "sozmlskccxtyhy";
	private static final String PASSWORD = "5946c667114d77415a265f4efb10fb68dce56f7829b70053debf285f239b2c5f";
	private static final String DRIVER = "org.postgresql.Driver";
	
	public static void openConnection() {
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static PreparedStatement getPreparedStatement(String sql, Object...arguments) throws SQLException {
		if (con == null || con.isClosed()) openConnection();
		PreparedStatement pre = con.prepareStatement(sql);
		addArgumentsTo(pre, arguments);
		return pre;
		
	}
	
	public static void addArgumentsTo(PreparedStatement pre, Object...arguments) throws SQLException {
		for (int i = 0; i < arguments.length; i++) {
			pre.setObject(i+1, arguments[i]);
		}
	}
	
	public static ResultSet query(String sql, Object...arguments) throws SQLException {
		return getPreparedStatement(sql, arguments).executeQuery();
	}
	
	public static int update(String sql, Object...arguments) throws SQLException {
		return getPreparedStatement(sql, arguments).executeUpdate();
	}
	
	public static void closeConnection() {
		try {
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
