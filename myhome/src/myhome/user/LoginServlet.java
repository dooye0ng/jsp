package myhome.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
			
			if(request.getParameter("remember") != null) {
				Cookie cookie = new Cookie("remember_id", request.getParameter("login_id"));
				// cookie.setSecure(true);
				cookie.setPath("/myhome/user");
				cookie.setMaxAge(60 * 60);
				response.addCookie(cookie);
			}
			else {	// not checked
				Cookie cookie = new Cookie("remember_id", "");
				cookie.setPath("/myhome/user");
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
			
			request.getSession().setAttribute("session_id", request.getParameter("login_id"));
			request.getSession().setMaxInactiveInterval(300);
			
		}
		
		request.setAttribute("msg", msg);
		RequestDispatcher rd = request.getRequestDispatcher("loginResult.jsp");
		rd.forward(request, response);
	}
}
