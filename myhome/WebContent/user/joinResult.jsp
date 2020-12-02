<%@page import="com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@ include file="/layout/header.jsp" %>
<%
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement ps = null;
	
	String id = request.getParameter("join_id");
	String pw1 = request.getParameter("join_pw1");
	String pw2 = request.getParameter("join_pw2");
	String name = request.getParameter("join_name");
	String email = request.getParameter("join_email");
	String msg = "Join Error !";
	String uri = "./join.jsp";
	
	if(pw1.equals(pw2)){
		msg = id + " Joined !";
		uri = "./login.jsp";
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost/myhomedb",
					"root",
					"root");
			ps = conn.prepareStatement("INSERT INTO user (id, password, email, name) VALUES(?, ?, ?, ?)");
			ps.setString(1, id);
			ps.setString(2, pw1);
			ps.setString(3, email);
			ps.setString(4, name);

			int row = ps.executeUpdate();
			
		} catch(ClassCastException e){
			e.printStackTrace();
		} catch(MySQLIntegrityConstraintViolationException ive){
			msg = "id already exists !";
			uri = "./join.jsp";
		}
	}

%>

<script>
	alert('<%=msg%>');
	document.location.href = '<%=uri%>';
</script>

<%@ include file="/layout/footer.jsp" %>
