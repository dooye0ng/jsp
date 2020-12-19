<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/layout/header.jsp">
	<jsp:param name="title" value="Login Page" />
</jsp:include>
	<script>
		alert('${login_result ? sessionScope.name.concat("님 안녕하세요!") : "아이디 혹은 비밀번호를 확인해주세요."}');
	</script>
	
	
	<a href="${pageContext.request.contextPath}/user/delete.jsp">회원탈퇴</a>
</body>
</html>
<%@ include file = "/layout/footer.jsp" %>






