<jsp:include page="/layout/header.jsp">
	<jsp:param name="title" value="Modify Page" />
</jsp:include>

<h1>Modify User Page</h1>

<form action="modify2.do" method="post">
	<table border="1">
		<tr>
			<th colspan="2">Modify !</th>
		</tr>
		<tr>
			<th>ID</th>
			<th>${requestScope.id }<input type="hidden" name="mod_id" value="${requestScope.id }"></th>
		</tr>
		<tr>
			<th>E-MAIL</th>
			<td><input type="email" name="mod_email" value="${requestScope.email }"
				required></td>
		</tr>
		<tr>
			<th>NAME</th>
			<td><input type="text" name="mod_name" value="${requestScope.name }"
				required></td>
		</tr>
		<tr>
			<th>PW</th>
			<td><input type="password" name="mod_pw1" required></td>
		</tr>
		<tr>
			<th>PW CHECK</th>
			<td><input type="password" name="mod_pw2" required></td>
		</tr>
		<tr>
			<th colspan="2"><input type="submit" value="Modify!"></th>
		</tr>
	</table>
</form>

<%@ include file="/layout/footer.jsp"%>