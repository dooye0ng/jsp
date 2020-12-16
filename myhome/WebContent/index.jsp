<jsp:include page="/layout/header.jsp">
	<jsp:param name="title" value="Home Page" />
</jsp:include>
<h1>Home Page</h1>
<a href="/myhome/user/adminPage.do">Admin Page</a>
<%@ include file="/layout/footer.jsp"%>

<!-- 
	index 페이지
	http://localhost:8080/myhome/			==> 올바른 접근.
	http://localhost:8080/myhome/index.jsp 	==> 잘못된 접근입니다. (에러 페이지)
	
	로그인
	
	첫 요청 : http://localhost:8080/myhome/user/login
	login.jsp 포워드
	
	login.do 요청 :
	1. UserService.login(id, password) 실행
	2. loginResult.do 요청
	
	loginResult.do 요청 :
	loginReslut.jsp 포워드
	
	
	http://localhost:8080/myhome/user/login.jsp
	http://localhost:8080/myhome/user/login.do
	http://localhost:8080/myhome/user/loginResult.jsp
	
	
	회원가입
	http://localhost:8080/myhome/user/join.jsp
	http://localhost:8080/myhome/user/join.do
	http://localhost:8080/myhome/user/joinResult.jsp
	
	회원수정
	http://localhost:8080/myhome/user/modifyUser.jsp
	http://localhost:8080/myhome/user/modify1.do
	http://localhost:8080/myhome/user/modify2.do
	http://localhost:8080/myhome/user/modifyResult.jsp
	
	회원탈퇴
	http://localhost:8080/myhome/user/signout.jsp
	http://localhost:8080/myhome/user/signout.do
	http://localhost:8080/myhome/user/signoutResult.jsp
	
	관리자 페이지
	http://localhost:8080/myhome/user/adminPage.do
	http://localhost:8080/myhome/user/adminPage.jsp
	http://localhost:8080/myhome/user/adminDelte.do
-->
