<jsp:include page="/layout/header.jsp">
	<jsp:param name="title" value="Signout Page" />
</jsp:include>
<h1>Sign Out Page</h1>
<form action="signout.do" method="post">
	<table border="1">
		<tr>
			<th>ID</th>
			<td><input type="text" name="signout_id" required></td>
		</tr>
		<tr>
			<th>Password</th>
			<td><input type="password" name="signout_pw" required></td>
		</tr>
		<tr>
			<th colspan="2"><input type="submit" value="Sign Out"></th>
		</tr>
	</table>
</form>
<%@ include file="/layout/footer.jsp"%>