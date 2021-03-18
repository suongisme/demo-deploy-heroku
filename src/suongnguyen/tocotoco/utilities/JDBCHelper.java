package suongnguyen.tocotoco.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCHelper {
	
	private static Connection con;
	private static final String URL = "jdbc:postgresql://localhost:5432/dcs7di6la9nisr";
	private static final String USER_NAME = "getsfowhidexef";
	private static final String PASSWORD = "98b14d755da40ee5cdb651bbf77d9bb7d76326ee0accd9ffcb1cefa5a6698c1f";
	private static final String DRIVER = "org.postgresql.Driver";
	
	public static void openConnection() {
		try {
			System.out.println(System.getenv("JDBC_DATABASE_URL"));
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
			con.setAutoCommit(false);
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
	
	public static void main(String[] args) {
		try {
			openConnection();
			System.out.println("DONE");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
