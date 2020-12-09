

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 *	��Ű ���� 
 */
@WebServlet("/practice/Test01")
public class Test01Servlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. ��Ű ��ü ����
		Cookie cookie1, cookie2, cookie3;
		cookie1 = new Cookie("a", "cookie1a");
		cookie2 = new Cookie("b", "cookie2b");
		cookie3 = new Cookie("c", "cookie3c");
		
		// 2. ��Ű ����
		
		// setPath()�� �� ��Ű�� ��ȸ�� �� �ִ� ������ ���
		cookie1.setPath("/");	// http://localhost:8080/ ���� ������ ��ȸ ����
		cookie2.setPath("/myhome");	// http://localhost:8080/myhome ���� ������ ��ȸ ����
		// cookie3�� �⺻
		
		cookie1.setMaxAge(24 * 60 * 60); // �� ����
		cookie2.setMaxAge(60 * 60);
		// cookie3�� �⺻
		
		// cookie1.setDomain("www.example.com");
		// cookie2, 3�� �⺻ ����������
		
		cookie1.setComment("My First Cookie");
		cookie1.setComment("My Second Cookie");
		// cookie3�� �⺻
		
		cookie1.setValue("new-value-a-changed");
		
		// 3. ��Ű�� response ��ü�� ���
		resp.addCookie(cookie1);
		resp.addCookie(cookie2);
		resp.addCookie(cookie3);
		
		PrintWriter out = resp.getWriter();
		out.write("<script>alert('cookie saved !');</script>");
	}
}









