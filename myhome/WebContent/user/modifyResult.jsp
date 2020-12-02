<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@ include file = "/layout/header.jsp" %>
<%
	String id = request.getParameter("mod_id");
	String pw1 = request.getParameter("mod_pw1");
	String pw2 = request.getParameter("mod_pw2");
	String name = request.getParameter("mod_name");
	String email = request.getParameter("mod_email");
	
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement ps = null;
	
	if(!pw1.equals(pw2)){
		out.write("<h1>" + "Wrong Request" + "</h1>");
	}
	else{
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost/myhomedb",
					"root",
					"root");
			ps = conn.prepareStatement("UPDATE user SET name = ?, password = ?, email = ? WHERE id = ? AND password = ?");
			ps.setString(1, name);
			ps.setString(2, pw1);
			ps.setString(3, email);
			ps.setString(4, id);
			ps.setString(5, pw1);
			
			if(ps.executeUpdate() > 0){ %>
				<table border="1">
					<tr>
						<th colspan="2"> Join Us ! </th>
					</tr>
					<tr>
						<th> ID </th>
						<th> <%= id %></th>
					</tr>
					<tr>
						<th> NAME </th>
						<th> <%= name %></th>
					</tr>
					<tr>
						<th> EMAIL </th>
						<th> <%= email %></th>
					</tr>
				</table>
			<%}
			else{
				out.write("<h1> Password doesn't match </h1>");
			}
			
		} catch(ClassCastException e){
			e.printStackTrace();
		} finally {
			try{
				if(null != ps){
					ps.close();
				}
				
				if(null != conn){
					conn.close();
				}
			} catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
%>
<%@ include file = "/layout/footer.jsp" %>