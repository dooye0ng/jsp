<%@page import="myhome.model.UserDto"%>
<%@page import="java.util.ArrayList"%>
<%@ include file="/layout/header.jsp"%>

<form action="user/signout.do" method="post">
<ol>
<%
	ArrayList<UserDto> userList = (ArrayList)request.getAttribute("userList");
	String delete_id = "del";
	String delete_pw = "del";
	
	for(UserDto user : userList){
		out.write("<li>ID : " +  user.getId() + " NAME : " + user.getName() + " E-MAIL : " + user.getEmail() + " DATE : " + user.getRegdate().substring(0, 10) + "] <button type='submit'/>delete</button></li>");
	}
	out.write("<input type='hidden' name='signout_id' value='" + delete_id + "'><input type='hidden' name='signout_pw' value='" + delete_pw + "'");
%>
</ol>
</form>

<%@ include file="/layout/footer.jsp"%>