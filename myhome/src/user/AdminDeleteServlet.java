package user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UserDao;

//@WebServlet("/user/adminDelete.do")
public class AdminDeleteServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("target");
		
		UserDao dao = UserDao.getInstance();
		boolean result = dao.deleteByAdmin(id);
		
		request.setAttribute("result", result);
		RequestDispatcher rd = request.getRequestDispatcher("adminPage.do");
		rd.forward(request, response);
	}
}









