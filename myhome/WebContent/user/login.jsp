<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- <%@ include file ="/layout/header.jsp" %> --%>

<jsp:include page="/layout/header.jsp">
	<jsp:param name="title" value="Login Page" />
</jsp:include>

<form action="login.do" method = "post">

<table border = "1">
	<tr>
		<th>ID</th>
		<td><input type="text" name = "login_id" value= "${ cookie.remembered_id.value }" required></td>
	</tr>
	<tr>
		<th>Password</th>
		<td><input type="password" name = "login_password" required></td>
	</tr>
	<tr>
		
		<th colspan="2">
			<input type="checkbox" name = "remember_id" value="true" checked>아이디 기억하기
			<input type = "submit" value = "Login!">
		</th>
	</tr>
</table>
</form>

<%@ include file ="/layout/footer.jsp" %>






