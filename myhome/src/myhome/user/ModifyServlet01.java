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

@WebServlet("/user/modify1.do")
public class ModifyServlet01 extends HttpServlet{
	// 수정하기 전 단계 (사용자 정보를 DB에서 가져오는 단계)
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = "admin";
		UserDto dto = null;
		UserDao dao = UserDao.getInstance();

		dto = dao.getUserById(id);
		if(null != dto) {
			request.setAttribute("id", dto.getId());
			request.setAttribute("email", dto.getEmail());
			request.setAttribute("name", dto.getName());
		}
		RequestDispatcher rd = request.getRequestDispatcher("modifyUser.jsp");
		rd.forward(request, response);
	}
}
