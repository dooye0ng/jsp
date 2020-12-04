package myhome.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myhome.model.UserDao;

@WebServlet("/user/adminDelete.do")
public class AdminDeleteServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/user/adminPage.jsp");
		String id = request.getParameter("signout_id");
		UserDao dao = UserDao.getInstance();
		dao.deleteUserByAdmin(id);
		rd.forward(request, response);
	}
}
