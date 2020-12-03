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

@WebServlet("/user/modify2.do")
public class ModifyServlet02 extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDao dao = UserDao.getInstance();
		UserDto dto = new UserDto();
		dto.setId(request.getParameter("mod_id"));
		dto.setName(request.getParameter("mod_name"));
		dto.setEmail(request.getParameter("mod_email"));
		String pw1 = request.getParameter("mod_pw1");
		String pw2 = request.getParameter("mod_pw2");
		String msg = "Password does not match";
		RequestDispatcher rd = request.getRequestDispatcher("modifyResult.jsp");
		
		if (pw1.equals(pw2)) {
			dto.setPassword(pw1);
			if(dao.updateUser(dto)) {
				msg = "Modify Success !";
			}
			else {
				msg = "No Permission";
			}
		}
		
		request.setAttribute("msg", msg);
		rd.forward(request, response);
	}
}
