package myhome.user;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/user/login.do") // http://localhost:8080/myhome/user/login.do
public class LoginServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String message = "서버 문제 발생 !";
		String id = request.getParameter("login_id");
		String pw = request.getParameter("login_pw");

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/myhomedb", "root", "root");
			ps = conn.prepareStatement("SELECT name FROM user WHERE id = ? AND password = ?");
			ps.setString(1, id);
			ps.setString(2, pw);

			rs = ps.executeQuery();

			if (rs.next()) {
				message = "안녕하세요, " + rs.getString(1) + " 님!";
			} else {
				message = "아이디 혹은 비밀번호를 다시 확인해주세요";
			}
			
			request.setAttribute("login_result", message);
			RequestDispatcher rd = request.getRequestDispatcher("loginResult.jsp");
			rd.forward(request, response);

		} catch (ClassCastException | SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != ps) {
					ps.close();
				}

				if (null != conn) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
