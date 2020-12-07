package myhome.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myhome.model.UserDao;
import myhome.model.UserDto;

@WebServlet("/user/adminPage.do")
public class AdminPageServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		UserDao dao = UserDao.getInstance();
		List<UserDto> userList = dao.getAllUsers();
		request.setAttribute("userList", userList);
		rd = request.getRequestDispatcher("adminPage.jsp");
		rd.forward(request, response);
	}
}