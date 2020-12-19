package user;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.UserDao;
import model.UserDto;

// @WebServlet("/user/login.do")
// http://127.0.0.1:8080/myhome/user/login.do

public class LoginServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String message = "서버 문제 발생!";
		String id = request.getParameter("login_id");
		String password = request.getParameter("login_password");

		UserDto dto = new UserDto();
		dto.setId(id);
		dto.setPassword(password);

		UserDao dao = UserDao.getInstance();

		if (dao.verify(dto)) {
			message = "안녕하세요, " + dto.getName() + "님!";
			
			// 아이디 기억하기가 체크되었다면 
			if(request.getParameter("remember_id") != null) {
				// if(Boolean.parseBoolean(request.getParameter("remember_id")))
				// 쿠키 생성
				Cookie cookie = new Cookie("remembered_id", id);
				cookie.setPath("/myhome/user");
				cookie.setMaxAge(365 * 24 * 60 * 60); // 1년
				response.addCookie(cookie);
			}
			else { // 체크가 안되었다면, 기존에 있을 수 있는 remebered_id 쿠키를 삭제
				Cookie cookie = new Cookie("remembered_id", "");
				cookie.setPath("/myhome/user");
				cookie.setMaxAge(0);
				response.addCookie(cookie);
				// 삭제 원리 
				// 이름과 path 가 동일한 쿠키가 재저장되면
				// 새로운 쿠키가 만들어지지 않고 기존 쿠키를 덮어씀
				// 수명이 0초짜리인 쿠키로 재저장하면 바로 없어짐
				
			}
			
			// 세션 객체에 이 회원의 id 랑 name 을 저장
			HttpSession session = request.getSession();
			session.setAttribute("id", dto.getId());
			session.setAttribute("name", dto.getName());
			
			// dto.setPassword("");
			//session.setAttribute("user", dto);
			
		} else {
			message = "아이디 혹은 비밀번호를 다시 확인해주세요.";
		}

		request.setAttribute("login_result", message);

		RequestDispatcher rd = request.getRequestDispatcher("loginResult.jsp");
		rd.forward(request, response);

	}
}
