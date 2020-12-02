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

@WebServlet("/user/join.do")
public class JoinServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;

		String id = request.getParameter("join_id");
		String pw1 = request.getParameter("join_pw1");
		String pw2 = request.getParameter("join_pw2");
		String name = request.getParameter("join_name");
		String email = request.getParameter("join_email");
		String msg = "Join Error !";
		String uri = "./join.jsp";

		if (pw1.equals(pw2)) {
			msg = id + " Joined !";
			uri = "./login.jsp";

			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://localhost/myhomedb", "root", "root");
				ps = conn.prepareStatement("INSERT INTO user (id, password, email, name) VALUES(?, ?, ?, ?)");
				ps.setString(1, id);
				ps.setString(2, pw1);
				ps.setString(3, email);
				ps.setString(4, name);

				int row = ps.executeUpdate();

			} catch (ClassCastException | ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				msg = "id already exists !";
				uri = "./join.jsp";
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
		request.setAttribute("uri", uri);
		RequestDispatcher rd = request.getRequestDispatcher("joinResult.jsp");
		rd.forward(request, response);
	}
}
