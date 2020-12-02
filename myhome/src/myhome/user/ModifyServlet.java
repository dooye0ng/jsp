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

@WebServlet("/user/modify.do")
public class ModifyServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("mod_id");
		String pw1 = request.getParameter("mod_pw1");
		String pw2 = request.getParameter("mod_pw2");
		String name = request.getParameter("mod_name");
		String email = request.getParameter("mod_email");
		String msg = "Password doesn't match";
		RequestDispatcher rd = request.getRequestDispatcher("modifyResult.jsp");

		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		if (pw1.equals(pw2)) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://localhost/myhomedb", "root", "root");
				ps = conn.prepareStatement(
						"UPDATE user SET name = ?, password = ?, email = ? WHERE id = ? AND password = ?");
				ps.setString(1, name);
				ps.setString(2, pw1);
				ps.setString(3, email);
				ps.setString(4, id);
				ps.setString(5, pw1);
	
				if (ps.executeUpdate() > 0) {
					msg = "Sucess !";
				} else {
					msg = "Modify Failed";
				}

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
		
		request.setAttribute("msg", msg);
		rd.forward(request, response);
	}
}
