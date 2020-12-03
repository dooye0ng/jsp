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

@WebServlet("/admin.do")
public class AdminServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
		UserDao dao = UserDao.getInstance();
		List<UserDto> userList = dao.getAllUsers();
		
		request.setAttribute("userList", userList);
		rd.forward(request, response);
	}
}
