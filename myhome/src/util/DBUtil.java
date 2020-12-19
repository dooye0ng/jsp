package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBUtil {
	private static DataSource dataSource;

	static {
		try {
			// javax.naming.Context
			Context context = new InitialContext(); // context.xml 을 객체화 함
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/mysql");
			// Tomcat 환경에서 xml의 name을 참조하려면 'java:comp/env"를 붙여야 한다.

		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

	public static void close(Connection conn, PreparedStatement ps) { // conn, ps
		close(conn, ps, null);
	}

	public static void close(Connection conn, PreparedStatement ps, ResultSet rs) { // conn, ps, rs
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
