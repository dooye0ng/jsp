package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.BoardDto;
import model.BoardDtoAndName;
import model.UserDto;
import service.AdminService;
import service.AdminServiceImpl;
import service.BoardService;
import service.BoardServiceImpl;
import service.UserService;
import service.UserServiceImpl;

@SuppressWarnings("serial")
@WebServlet("/")// http://127.0.0.1:8080/myhome 컨텍스트의 모든 요청
public class Controller extends HttpServlet {

	UserService userService = UserServiceImpl.getInstance();
	AdminService adminService = AdminServiceImpl.getInstance();
	BoardService boardService = BoardServiceImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		doProcess(req, resp);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
//		System.out.println("contextPath : " + request.getContextPath());
//		System.out.println("요청 주소 URI : " + request.getRequestURI());
//		System.out.println("요청 주소 URL : " + request.getRequestURL());

		String requestUri = request.getRequestURI().replace(request.getContextPath(), "");
		
		
		
		switch (requestUri) {
		
		// 게시판 부분
		case "/board/boardList":
			List<BoardDtoAndName> list = boardService.getList();
			request.setAttribute("list", list);
			request.getRequestDispatcher("/board/boardList.jsp").forward(request, response);	
			return;
		case "/board/boardWrite":
			request.getRequestDispatcher("/board/boardWrite.jsp").forward(request, response);	
			return;
		case "/board/boardWrite.do":{
				String title = request.getParameter("write_title");
				String content = request.getParameter("write_content");
				System.out.println(title);
				System.out.println(content);
				String writer_id = (String)request.getSession().getAttribute("id");
				
				boolean result = boardService.write(title, content, writer_id);
				request.setAttribute("result", result);
				request.setAttribute("flag", "WRITE");
				request.getRequestDispatcher("boardResult.jsp").forward(request, response);
			}
			return;
		case "/board/boardRead": {
			int no = Integer.parseInt(request.getParameter("no"));
			BoardDtoAndName vo = null; // 글제목, 조회수, 글본문, 등록일자, 글쓴이 이름
			
			
			// 세션이 이 글을 읽은 적이 있는지?
//			HttpSession session = request.getSession();
//			if(session.getAttribute("article_" + no) != null) { // 읽은 적 있음
//				vo = boardService.fetchContent(no, true);
//			}
//			else { // 없음 
//				session.setAttribute("article_" + no, "");
//				vo = boardService.fetchContent(no, false);
//			}
			////////////////////////////////////////////////////////////
			// 브라우저가 이 글을 읽은 적이 있는지?
			boolean result = false;
			Cookie[] cookies = request.getCookies();
			for(Cookie cookie: cookies) {
				if(cookie.getName().equals("article_" + no)) {
					result = true;
					break;
				}
			}
			
			if(result) { // 있으면
				vo = boardService.fetchContent(no, false);
			}
			else { // 없으면
				Cookie cookie = new Cookie("article_" + no, "");
				
				cookie.setMaxAge(24 * 3600);
				
				response.addCookie(cookie);
				
				vo = boardService.fetchContent(no, true);
			}
			///////////////////////////////////////////////////////////
			
			request.setAttribute("vo", vo);
			
			request.getRequestDispatcher("boardRead.jsp").forward(request, response);
		}
			
			return;
			
		case "/board/boardDelete":
			int no = Integer.parseInt(request.getParameter("no"));
			boardService.delete(no);
			response.sendRedirect("boardList");
			return;
		// 회원 부분	
		case "/user/login":
			login(request, response);
			return;
		case "/user/login.do":
			loginDo(request, response);
			return;
		case "/user/logout":
			logoutDo(request, response);
			return;
		case "/user/signup":
			signup(request, response);
			return;
		case "/user/signup.do":
			signupDo(request, response);
			return;
		case "/user/delete.do":
			deleteDo(request, response);
			return;
		case "/user/modify":
			modify(request, response);
			return;
		case "/user/modify.do":
			modifyDo(request, response);
			return;
		case "/user/adminPage":
			adminPageDo(request, response);
			return;
		case "/user/adminDelete":
			adminDeleteDo(request, response);
			return;
		
		default:
			// 메인페이지로 이동
			request.getRequestDispatcher("/").forward(request, response);
		}

	}
	
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/user/login.jsp").forward(request, response);
	}

	private void loginDo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean result = false;
		String id = request.getParameter("login_id");
		String password = request.getParameter("login_password");
		UserDto dto = userService.login(id, password);

		if (dto != null) {
			result = true;
			if (request.getParameter("remember_id") != null) {
				Cookie cookie = new Cookie("remembered_id", id);
				cookie.setPath(request.getContextPath() + "/user");
				cookie.setMaxAge(365 * 24 * 60 * 60); // 1년
				response.addCookie(cookie);
			} else {
				Cookie cookie = new Cookie("remembered_id", "");
				cookie.setPath(request.getContextPath() +"/user");
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}

			HttpSession session = request.getSession();
			session.setAttribute("id", dto.getId());
			session.setAttribute("name", dto.getName());
		}
		request.setAttribute("login_result", result);

		RequestDispatcher rd = request.getRequestDispatcher("loginResult.jsp");
		rd.forward(request, response);
	}

	private void logoutDo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		session.invalidate();

		RequestDispatcher rd = request.getRequestDispatcher("logoutResult.jsp");
		rd.forward(request, response);

	}

	private void signup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("signup.jsp");
		rd.forward(request, response);
	}

	private void signupDo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String message = "회원 가입 에러!";
		boolean success = userService.signup(
				request.getParameter("signup_id"),
				request.getParameter("signup_password"),
				request.getParameter("signup_email"),
				request.getParameter("signup_name")
				);

		if (success) {
			message = "회원 가입을 완료하였습니다.";
		}

		request.setAttribute("message", message);
		request.setAttribute("success", success);
		RequestDispatcher rd = request.getRequestDispatcher("signupResult.jsp");
		rd.forward(request, response);
	}

	private void deleteDo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// POST 방식으로 넘어온 파라미터는 getParameter 하기전에 인코딩을 UTF-8 변경
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("delete_id");
		String password = request.getParameter("delete_password");
		String message = "비밀번호 혹은 아이디가 잘못되었습니다.";

		if (userService.signout(id, password)) {
			message = "회원 탈퇴 완료 .... ㅜㅜ";
		}

		// 세션 갱신 (탈퇴를 했으니 비로그인으로)
		HttpSession session = request.getSession();
		session.invalidate();
		/////////////////////////////////////////

		request.setAttribute("message", message);
		RequestDispatcher rd = request.getRequestDispatcher("deleteResult.jsp");
		rd.forward(request, response);

	}

	private void modify(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		request.setAttribute("dto", userService.getUser(id));
		RequestDispatcher rd = request.getRequestDispatcher("modify.jsp");
		rd.forward(request, response);
	}

	private void modifyDo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// POST 방식으로 넘어온 파라미터는 getParameter 하기전에 인코딩을 UTF-8 변경
		boolean success = false;
		String message = "회원 수정 에러!";
		HttpSession session = request.getSession();

		success = userService.modifyUser(
				(String) session.getAttribute("id"), 
				request.getParameter("modify_password"), 
				request.getParameter("modify_email"), 
				request.getParameter("modify_name"));
		if (success) {
			message = "회원 수정 완료!";
		}
		request.setAttribute("message", message);
		request.setAttribute("success", success);
		RequestDispatcher rd = request.getRequestDispatcher("modifyResult.jsp");
		rd.forward(request, response);
	}

	private void adminPageDo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("list", adminService.listUser());
		RequestDispatcher rd = request.getRequestDispatcher("adminPage.jsp");
		rd.forward(request, response);
	}

	private void adminDeleteDo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("target");
		request.setAttribute("result", adminService.delete(id));
		RequestDispatcher rd = request.getRequestDispatcher("adminPage.do");
		rd.forward(request, response);
	}

}
