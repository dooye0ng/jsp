<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.ResultSet" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement ps = null;
	String message = "서버 문제 발생 !";
	String id = request.getParameter("login_id");
	String pw = request.getParameter("login_pw");
	
	try{
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(
				"jdbc:mysql://localhost/myhomedb",
				"root",
				"root");
		ps = conn.prepareStatement("SELECT name FROM user WHERE id = ? AND password = ?");
		ps.setString(1, id);
		ps.setString(2, pw);
		
		rs = ps.executeQuery();
		
		if(rs.next()){
			message = "안녕하세요, " + rs.getString(1) + " 님!";
		}
		else{
			message = "아이디 혹은 비밀번호를 다시 확인해주세요";
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
 
<%@ include file = "/layout/header.jsp" %>
	<script>
		alert('<%=message%>');
	</script>
<%@ include file = "/layout/footer.jsp" %>