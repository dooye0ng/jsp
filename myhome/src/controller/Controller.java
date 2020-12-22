package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model.BoardDto;
import model.BoardDtoAndName;
import model.UserDto;
import service.AdminService;
import service.AdminServiceImpl;
import service.BoardService;
import service.BoardServiceImpl;
import service.FileService;
import service.FileServiceImpl;
import service.UserService;
import service.UserServiceImpl;

@SuppressWarnings("serial")
@WebServlet("/")// http://127.0.0.1:8080/myhome 컨텍스트의 모든 요청
public class Controller extends HttpServlet {

	UserService userService = UserServiceImpl.getInstance();
	AdminService adminService = AdminServiceImpl.getInstance();
	BoardService boardService = BoardServiceImpl.getInstance();
	FileService fileService = FileServiceImpl.getInstance();
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
		
		System.out.println("contextPath : " + request.getContextPath());
		System.out.println("요청 주소 URI : " + request.getRequestURI());
		System.out.println("요청 주소 URL : " + request.getRequestURL());
		
		String requestUri = request.getRequestURI().replace(request.getContextPath(), "");
		
		switch (requestUri) {
		// 자료실 부분
		case "/download/downloadList":
			// storage 디렉토리의 모든 파일을 목록화
			// String[] - 파일명 배열을 request의 Attr로 추가한다.
//			String[] filenames = downloadService.getFileNames();
//			request.setAttribute("list", filenames);
			
			// DB가 있다면 DB에서 모두 가져오기
			request.setAttribute("list", fileService.getList());
			
			// list.jsp로 forward함
			request.getRequestDispatcher("list.jsp").forward(request, response);
			return;
		
		case "/download/upload":
			// upload.jsp로 포워드
			request.getRequestDispatcher("upload.jsp").forward(request, response);
			return;
		case "/download/upload.do":
			String uploader = (String)request.getSession().getAttribute("id");
			// 누가 업로드했는지 id 세션 조회
			
			// MultipartRequest를 사용해 /storage에 저장
			// param : file1, file2 
			// 저장 경로, 파일명 받기
			// 저장 경로를 file DB 테이블에 저장 (저장 경로 + 업로드 유저)
			MultipartRequest mr = new MultipartRequest(
					request,	// 파일 파라미터가 담겨있는 request 객체
					request.getServletContext().getRealPath("/storage"),	// 파일을 저장할 경로(논리적 경로)
					5 * 1024 * 1024,	// 업로드 제한 용량
					"UTF-8",	// 파일명 인코딩 형식
					new DefaultFileRenamePolicy()	// 중복된 파일인 경우 끝에 넘버링하는 기본 정책
			); // 생성자만 실행해도 파일 파라미터들이 자동으로 지정해준 경로에 저장됨
			
			// 저장된 파일들의 정보를 알고 싶을 때
			File file1 = mr.getFile("file1");	// getFile(파라미터 명)
			File file2 = mr.getFile("file2");
			
			// null일 수도 있다.
			if(file1 != null) {
				System.out.println("파일 경로 : " + file1.getAbsolutePath());
				System.out.println("변경 전 파일명 : " + mr.getOriginalFileName("file1"));
				System.out.println("변경 후 파일명 : " + mr.getFilesystemName("file1"));
				System.out.println("사이즈 : " + file1.length() + "byte");
				
				fileService.upload(mr.getFilesystemName("file1"),
						file1.getAbsolutePath(),
						uploader);
			}
			
			if(file2!= null) {
				System.out.println("파일 경로 : " + file2.getAbsolutePath());
				System.out.println("변경 전 파일명 : " + mr.getOriginalFileName("file2"));
				System.out.println("변경 후 파일명 : " + mr.getFilesystemName("file2"));
				System.out.println("사이즈 : " + file2.length() + "byte");
				
				fileService.upload(mr.getFilesystemName("file1"),
						file1.getAbsolutePath(),
						uploader);
			}
			
			request.getRequestDispatcher("downloadList").forward(request, response);
			return;
		case "/download/download.do":
			int fileNo = Integer.parseInt(request.getParameter("no"));
			File file = fileService.download(fileNo);
			byte[] bytes = new byte[(int)file.length()];
			
			// 파일 다운로드를 위한 헤더 설정
			response.reset(); // 헤더정보 초기화
			response.setContentType("application/octect-stream");	// 헤더 content type 설정
			response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
			
			// Controller ---> response body부분으로 내보냄
			ServletOutputStream out = response.getOutputStream();
			
			// File ---> Controller
			FileInputStream in = new FileInputStream(file);
			
			in.read(bytes);
			out.write(bytes);
			out.flush();
			
			in.close();
			out.close();
			return;
		// 게시판 부분
		case "/board/boardList":
			// 현재 페이지 (파라미터) 가져옴
			String sPage = request.getParameter("page");
			int page = sPage == null ? 1 : Integer.parseInt(sPage);
			
			// 전체 페이지 (application 바구니에 들어있는 attribute. 없으면 새로 넣는다.)
			// 4가지 영역 : page < request < session < application
			ServletContext application = getServletContext();
			
			Object obj = application.getAttribute("lastPage");
			
			
			Integer lastPage = (Integer)(obj == null ? 1 : obj);
			
			if(lastPage == 1) {
				application.setAttribute("lastPage", boardService.getLastPage());
			}
			
			List<BoardDtoAndName> list = boardService.getList(page);
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
					
				if(result) {
					getServletContext().setAttribute("lastPage", boardService.getLastPage());
				}
				
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
		case "/board/boardModify":
		{
			int no = Integer.parseInt(request.getParameter("no"));
			BoardDto dto = boardService.fetchContent(no, true);
			request.setAttribute("vo", dto);
			request.getRequestDispatcher("boardModify.jsp").forward(request, response);
		}
			return;
		case "/board/boardModify.do":{
			String title = request.getParameter("modify_title");
			String content = request.getParameter("modify_content");
			int no = Integer.parseInt(request.getParameter("modify_no"));
			boolean result = boardService.modifyContent(no, title, content);
			request.setAttribute("result", result);
			request.setAttribute("flag", "MODIFY");
			request.getRequestDispatcher("boardResult.jsp").forward(request, response);
		}
			return;
		case "/board/boardDelete":
			int no = Integer.parseInt(request.getParameter("no"));
			boolean result = boardService.deleteContent(no);
			/*
			 * 응답해줄 JSON의 모양
			 * { 
			 * 	  "result": true,
			 * 	  "message" : "성공하였습니다."
			 * }
			 * 
			 * { 
			 * 		"result":false,
			 * 		"message": "삭제 실패하였습니다"
			 * }
			 */
			JsonObject object = new JsonObject();
			object.addProperty("result", result);
			object.addProperty("message", result?"성공하였습니다." : "삭제 실패하였습니다.");
			///////////////////// 이부분 부터! //////////////////////
			
			if(result) {
				getServletContext().setAttribute("lastPage", boardService.getLastPage());
			}
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(object.toString());
//			response.getWriter().write("{ \"result\" : true }");
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