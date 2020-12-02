<%@ include file="/layout/header.jsp" %>

<form action="joinResult.jsp" method="post">
	<table border="1">
		<tr>
			<th colspan="2"> Join Us ! </th>
		</tr>
		<tr>
			<th> NAME </th>
			<td> <input type="text" name="join_name" required> </td>
		</tr>
		<tr>
			<th> E-MAIL </th>
			<td> <input type="email" name="join_email" required> </td>
		</tr>
		<tr>
			<th> ID </th>
			<td> <input type="text" name="join_id" required> </td>
		</tr>
		<tr>
			<th> PW </th>
			<td> <input type="password" name="join_pw1" required> </td>
		</tr>
		<tr>
			<th> PW CHECK </th>
			<td> <input type="password" name="join_pw2" required> </td>
		</tr>
		<tr>
			<th colspan="2"> <input type="submit" value="Join !"> </th>
		</tr>
	</table>
</form>

<%@ include file="/layout/footer.jsp" %>