package myhome.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myhome.model.UserDao;
import myhome.model.UserDto;

@WebServlet("/user/signout.do")
public class SignoutServlet extends HttpServlet {
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String msg = "Invalid Request";
		boolean result = false;
		UserDto dto = new UserDto();
		dto.setId(request.getParameter("signout_id"));
		dto.setPassword(request.getParameter("signout_pw"));
		UserDao dao = UserDao.getInstance();
		
		result = dao.delete(dto);
		
		if (result) {
			msg = "Signed Out";
		}
		
		request.setAttribute("message", msg);
		RequestDispatcher rd = request.getRequestDispatcher("signoutResult.jsp");
		rd.forward(request, response);

		
	}

}
