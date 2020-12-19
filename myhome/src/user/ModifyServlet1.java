package user;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.UserDao;
import model.UserDto;

//@WebServlet("/user/modify1.do")
public class ModifyServlet1 extends HttpServlet {
	// 수정 <form> 을 뷰로 띄우기 전 단계 (사용자 정보를 DB에서 가져오는 단계)
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		UserDao dao = UserDao.getInstance();
		UserDto dto = dao.select(id);

		request.setAttribute("dto", dto);
		
		RequestDispatcher rd = request.getRequestDispatcher("modify.jsp");
		rd.forward(request, response);
		
	}
}











