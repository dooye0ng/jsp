<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@ include file="/layout/header.jsp"%>

<%
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement ps = null;
	String id = "admin";
	String pw = "admin1234";
	String userId = null, userEmail = null, userName = null;

	try {
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost/myhomedb", "root", "root");
		ps = conn.prepareStatement("SELECT id, email, name FROM user WHERE id = ? AND password = ?");
		ps.setString(1, id);
		ps.setString(2, pw);
		rs = ps.executeQuery();

		if (rs.next()) {
			userId = rs.getString("id");
			userEmail = rs.getString("email");
			userName = rs.getString("name");
		} else {

		}

	} catch (ClassCastException e) {
		e.printStackTrace();
	} finally {
		try {
			if (null != ps) {
				ps.close();
			}

			if (null != conn) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
%>

<h1>Modify User Page</h1>

<form action="modifyResult.jsp" method="post">
	<table border="1">
		<tr>
			<th colspan="2">Modify !</th>
		</tr>
		<tr>
			<th>ID</th>
			<th><%=userId%><input type="hidden" name="mod_id" value="<%=userId%>"></th>
		</tr>
		<tr>
			<th>E-MAIL</th>
			<td><input type="email" name="mod_email" value="<%=userEmail%>"
				required></td>
		</tr>
		<tr>
			<th>NAME</th>
			<td><input type="text" name="mod_name" value="<%=userName%>"
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