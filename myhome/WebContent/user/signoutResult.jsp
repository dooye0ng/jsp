<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@ include file="/layout/header.jsp" %>

<%
	String id = request.getParameter("signout_id");
	String pw = request.getParameter("signout_pw");
	
	Connection conn = null;
	PreparedStatement ps = null;
	String msg = "Invalid Request";
	try{
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(
				"jdbc:mysql://localhost/myhomedb",
				"root",
				"root");
		ps = conn.prepareStatement("DELETE FROM user WHERE id = ? AND password = ?");
		ps.setString(1, id);
		ps.setString(2, pw);
		
		if(ps.executeUpdate() > 0){
			msg = "Signed Out";
			out.write("<h1>" + msg + "</h1>");
		}
		else{		
			out.write("<h1>" + msg + "</h1>");
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
%>

<%@ include file="/layout/footer.jsp" %>