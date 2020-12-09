

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
		// http://localhost:8080/myhome/practice/Test03 ��û -> (��Ű������ ���� ���� ���̴�)
		// ��Ű���� �о�鿩�� sysout ����غ���
		
		Cookie[] cookies = req.getCookies();
		if(cookies == null) return;
		for(Cookie cookie : cookies) {
			System.out.println(cookie.getName() + " " + cookie.getValue());
		}
	}
}
