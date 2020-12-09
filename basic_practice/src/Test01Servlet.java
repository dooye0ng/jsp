

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 *	쿠키 저장 
 */
@WebServlet("/practice/Test01")
public class Test01Servlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. 쿠키 객체 생성
		Cookie cookie1, cookie2, cookie3;
		cookie1 = new Cookie("a", "cookie1a");
		cookie2 = new Cookie("b", "cookie2b");
		cookie3 = new Cookie("c", "cookie3c");
		
		// 2. 쿠키 설정
		
		// setPath()란 이 쿠키를 조회할 수 있는 도메인 경로
		cookie1.setPath("/");	// http://localhost:8080/ 이하 페이지 조회 가능
		cookie2.setPath("/myhome");	// http://localhost:8080/myhome 이하 페이지 조회 가능
		// cookie3은 기본
		
		cookie1.setMaxAge(24 * 60 * 60); // 초 단위
		cookie2.setMaxAge(60 * 60);
		// cookie3은 기본
		
		// cookie1.setDomain("www.example.com");
		// cookie2, 3은 기본 도메인으로
		
		cookie1.setComment("My First Cookie");
		cookie1.setComment("My Second Cookie");
		// cookie3은 기본
		
		cookie1.setValue("new-value-a-changed");
		
		// 3. 쿠키를 response 객체에 담기
		resp.addCookie(cookie1);
		resp.addCookie(cookie2);
		resp.addCookie(cookie3);
		
		PrintWriter out = resp.getWriter();
		out.write("<script>alert('cookie saved !');</script>");
	}
}









