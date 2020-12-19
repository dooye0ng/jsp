package user;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UserDao;
import model.UserDto;

@SuppressWarnings("serial")
//@WebServlet("/user/signup.do")
public class SignupServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// POST 방식으로 넘어온 파라미터는 getParameter 하기전에 인코딩을 UTF-8 변경
		String message = "회원 가입 에러!";
		boolean success = false;
		UserDao dao = null;
		UserDto dto = new UserDto();

		request.setCharacterEncoding("UTF-8");

		dto.setId(request.getParameter("signup_id"));

		dto.setPassword(request.getParameter("signup_password"));

		dto.setEmail(request.getParameter("signup_email"));

		dto.setName(request.getParameter("signup_name"));

		dao = UserDao.getInstance();
		success = dao.insert(dto);
		
		if(success) {
			message = "회원 가입을 완료하였습니다.";
		}
		
		
		request.setAttribute("message", message);
		request.setAttribute("success", success);
		RequestDispatcher rd = request.getRequestDispatcher("signupResult.jsp");
		rd.forward(request, response);

	}
}
