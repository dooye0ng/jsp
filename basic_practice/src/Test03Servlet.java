

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/practice/Test03")
public class Test03Servlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// http://localhost:8080/myhome/practice/Test03 요청 -> (쿠키정보가 같이 들어올 것이다)
		// 쿠키들을 읽어들여서 sysout 출력해보기
		
		Cookie[] cookies = req.getCookies();
		if(cookies == null) return;
		for(Cookie cookie : cookies) {
			System.out.println(cookie.getName() + " " + cookie.getValue());
		}
	}
}
