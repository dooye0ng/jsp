<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/layout/header.jsp">
	<jsp:param name = "title" value = "Delete Page"/>
</jsp:include>

<form action="delete.do" method = "post">

<table border = "1">
	<tr>
		<th>ID</th>
		<td><input type="text" name = "delete_id" required></td>
	</tr>
	<tr>
		<th>Password</th>
		<td><input type="password" name = "delete_password" required></td>
	</tr>
	<tr>
		<th colspan="2">
			<input type = "submit" value = "탈퇴!">
		</th>
	</tr>
</table>
</form>

<%@ include file ="/layout/footer.jsp" %>