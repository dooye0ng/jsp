package user;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UserDao;
import model.UserDto;

//@WebServlet("/user/adminPage.do")
public class AdminServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDao dao = UserDao.getInstance();
		ArrayList<UserDto> list = dao.selectAll();
		
		request.setAttribute("list", list);
		
		RequestDispatcher rd = request.getRequestDispatcher("adminPage.jsp");
		rd.forward(request, response);
		
		
	}
}











