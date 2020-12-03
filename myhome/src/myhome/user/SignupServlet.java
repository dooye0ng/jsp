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

@WebServlet("/user/join.do")
public class SignupServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String msg = "Join Error !";
		String uri = "./join.jsp";
		UserDao dao = null;
		UserDto dto = new UserDto();
		boolean success = false;
		
		dto.setId(request.getParameter("join_id"));
		dto.setPassword(request.getParameter("join_pw1"));
		dto.setName(request.getParameter("join_name"));
		dto.setEmail(request.getParameter("join_email"));
		
		dao = UserDao.getInstance();
		success = dao.insert(dto);
		msg = success ? dto.getId() + " Joined !" : "Join Failed ...";
		
		request.setAttribute("msg", msg);
		RequestDispatcher rd = request.getRequestDispatcher("joinResult.jsp");
		rd.forward(request, response);
	}
}
