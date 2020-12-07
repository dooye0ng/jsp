<jsp:include page="/layout/header.jsp">
	<jsp:param name="title" value="Login Page" />
</jsp:include>

<h1>Login Page</h1>
<form action="login.do" method="post">
	<table border="1">
		<tr>
			<th>ID</th>
			<td><input type="text" name="login_id" required></td>
		</tr>
		<tr>
			<th>Password</th>
			<td><input type="password" name="login_pw" required></td>
		</tr>
		<tr>
			<th colspan="2"><input type="submit" value="login!"></th>
		</tr>
	</table>
</form>

<%@ include file="/layout/footer.jsp"%>