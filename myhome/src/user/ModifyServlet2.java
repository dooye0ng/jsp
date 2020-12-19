package user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.UserDao;
import model.UserDto;

//@WebServlet("/user/modify2.do")
public class ModifyServlet2 extends HttpServlet {
	// 수정 <form> 을 뷰로 띄우기 전 단계 (사용자 정보를 DB에서 가져오는 단계)
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// POST 방식으로 넘어온 파라미터는 getParameter 하기전에 인코딩을 UTF-8 변경
		UserDao dao = null;
		UserDto dto = new UserDto();
		boolean success = false;
		String message = "회원 수정 에러!";
		HttpSession session = request.getSession();
		
		request.setCharacterEncoding("UTF-8");
		dto.setId((String)session.getAttribute("id"));
		// session.getId() ==> JSESSIONID
		
		dto.setPassword(request.getParameter("modify_password"));
		dto.setEmail(request.getParameter("modify_email"));
		dto.setName(request.getParameter("modify_name"));
		
		dao = UserDao.getInstance();
		success = dao.update(dto);
		if(success) {
			message = "회원 수정 완료!";
		}
		request.setAttribute("message", message);
		request.setAttribute("success", success);
		RequestDispatcher rd = request.getRequestDispatcher("modifyResult.jsp");
		rd.forward(request, response);
	}
}











