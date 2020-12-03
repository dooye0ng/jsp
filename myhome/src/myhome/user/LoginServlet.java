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

import myhome.model.UserDao;
import myhome.model.UserDto;


@WebServlet("/user/login.do") // http://localhost:8080/myhome/user/login.do
public class LoginServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDto dto = new UserDto();
		UserDao dao = UserDao.getInstance();
		String msg = "login error !";
		dto.setId(request.getParameter("login_id"));
		dto.setPassword(request.getParameter("login_pw"));
		
		if(dao.verify(dto)) {
			msg = "login success !";
		}
		
		request.setAttribute("msg", msg);
		RequestDispatcher rd = request.getRequestDispatcher("loginResult.jsp");
		rd.forward(request, response);
	}
}
