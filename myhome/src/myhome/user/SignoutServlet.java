package myhome.user;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/user/signout.do")
public class SignoutServlet extends HttpServlet {
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("signout_id");
		String pw = request.getParameter("signout_pw");

		Connection conn = null;
		PreparedStatement ps = null;
		String msg = "Invalid Request";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/myhomedb", "root", "root");
			ps = conn.prepareStatement("DELETE FROM user WHERE id = ? AND password = ?");
			ps.setString(1, id);
			ps.setString(2, pw);

			if (ps.executeUpdate() > 0) {
				msg = "Signed Out";
			}
			request.setAttribute("message", msg);
			RequestDispatcher rd = request.getRequestDispatcher("signoutResult.jsp");
			rd.forward(request, response);

		} catch (Exception e) {
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
