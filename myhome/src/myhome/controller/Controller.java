package myhome.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import myhome.model.BoardDto;
import myhome.model.UserDao;
import myhome.model.UserDto;
import myhome.service.AdminService;
import myhome.service.AdminServiceImpl;
import myhome.service.BoardService;
import myhome.service.BoardServiceImpl;
import myhome.service.UserService;
import myhome.service.UserServiceImpl;

@WebServlet("/") 	// http://127.0.0.1:8080/myhome/user 컨텍스트의 모든 요청을 처리함
public class Controller extends HttpServlet{
	private UserService userService;
	private AdminService adminService;
	private BoardService boardService;
	private UserDto dto;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String reqUri = req.getRequestURI().replace(req.getContextPath(), "");
		switch(reqUri) {
		case "/user/login":
			req.getRequestDispatcher("/user/login.jsp").forward(req, resp);
			return;
			
		case "/user/login.do":
			String id = req.getParameter("login_id");
			String pw = req.getParameter("login_pw");
			userService = UserServiceImpl.getInstance();
			
			dto = userService.login(id, pw);
			if(dto != null) {
				if(req.getParameter("remember") != null) {
					Cookie cookie = new Cookie("remember_id", id);
					// cookie.setSecure(true);
					cookie.setPath("/myhome/user");
					cookie.setMaxAge(60 * 60);
					resp.addCookie(cookie);
				}
				else {	// not checked
					Cookie cookie = new Cookie("remember_id", "");
					cookie.setPath("/myhome/user");
					cookie.setMaxAge(0);
					resp.addCookie(cookie);
				}
				
				req.getSession().setAttribute("userVo", dto);
				req.getSession().setMaxInactiveInterval(300);
				
			}
			
			RequestDispatcher rd = req.getRequestDispatcher("loginResult.jsp");
			rd.forward(req, resp);
			return;
			
		case "/user/logout.do":
			req.getSession().invalidate();
			rd = req.getRequestDispatcher("/user/logoutResult.jsp");
			rd.forward(req, resp);
			return;
			
		case "/user/join":
			req.getRequestDispatcher("/user/join.jsp").forward(req, resp);
			return;
			
		case "/user/join.do":
			req.setCharacterEncoding("UTF-8");
			userService = UserServiceImpl.getInstance();
			dto = new UserDto();
			dto.setId(req.getParameter("join_id"));
			dto.setPassword(req.getParameter("join_pw1"));
			dto.setName(req.getParameter("join_name"));
			dto.setEmail(req.getParameter("join_email"));
			
			req.setAttribute("msg", (userService.join(dto)) ? "Success !" : "Failed ...");
			rd = req.getRequestDispatcher("/user/joinResult.jsp");
			rd.forward(req, resp);
			return;
		
		case "/user/modify1.do":
			userService = UserServiceImpl.getInstance();
			HttpSession session = req.getSession();
			dto = new UserDto();
			dto = (UserDto)session.getAttribute("userVo");
			
			dto = userService.modify1(dto);
			
			req.setAttribute("id", dto.getId());
			req.setAttribute("email", dto.getEmail());
			req.setAttribute("name", dto.getName());
			rd = req.getRequestDispatcher("modifyUser.jsp");
			rd.forward(req, resp);
			
			return;
		
		case "/user/modify2.do":
			userService = UserServiceImpl.getInstance();
			dto = new UserDto();
			dto.setId(req.getParameter("mod_id"));
			dto.setName(req.getParameter("mod_name"));
			dto.setEmail(req.getParameter("mod_email"));
			
			String pw1 = req.getParameter("mod_pw1");
			String pw2 = req.getParameter("mod_pw2");
			String msg = "Password does not match";
			
			if (pw1.equals(pw2)) {
				dto.setPassword(pw1);
				
				if(userService.modify2(dto)) {
					msg = "Modify Success !";
					req.getSession().setAttribute("userVo", dto);
				}
				else {
					msg = "Modify Failed ...";
				}
			}
			req.setAttribute("msg", msg);
			rd = req.getRequestDispatcher("modifyResult.jsp");
			rd.forward(req, resp);
			return;
			
		case "/user/adminPage.do":
			adminService = AdminServiceImpl.getInstance();
			UserDao dao = UserDao.getInstance();
			List<UserDto> userList = adminService.getAllUsers();
			req.setAttribute("userList", userList);
			rd = req.getRequestDispatcher("adminPage.jsp");
			rd.forward(req, resp);
			return;
			
		case "/user/adminDelete.do":
			adminService = AdminServiceImpl.getInstance();
			adminService.deleteByAdmin(req.getParameter("signout_id"));
			
			rd = req.getRequestDispatcher("/user/adminPage.do");
			rd.forward(req, resp);
			return;
			
		case "/board/boardList":
			rd = req.getRequestDispatcher("/board/boardList.jsp");
			boardService = BoardServiceImpl.getInstance();
			ArrayList<BoardDto> boards = (ArrayList)boardService.getList();
			req.setAttribute("boardList", boards);
			rd.forward(req, resp);
			return;
			
		case "/board/boardWrite":
			rd = req.getRequestDispatcher("/board/boardWrite.jsp");
			rd.forward(req, resp);
			return;
			
		case "/board/boardWrite.do":
			boardService = BoardServiceImpl.getInstance();
			String title = req.getParameter("board_title");
			String content = req.getParameter("board_content");
			
			boolean result = boardService.write((UserDto)req.getSession().getAttribute("userVo"), title, content);
			req.setAttribute("result", result);
			req.setAttribute("flag", "WRITE");
			req.getRequestDispatcher("boardResult.jsp").forward(req, resp);
			return;
			
		default:
			req.getRequestDispatcher("/").forward(req, resp);
		}
		
	}
}




















