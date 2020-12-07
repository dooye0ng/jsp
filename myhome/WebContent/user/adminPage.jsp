<%@page import="myhome.model.UserDto"%>
<%@page import="java.util.ArrayList"%>
<jsp:include page="/layout/header.jsp">
	<jsp:param name="title" value="Admin Page" />
</jsp:include>

<form action="adminDelete.do" method="post">
<table border="1">
<tr>
	<th>ID</th>
	<th>NAME</th>
	<th>E-MAIL</th>
	<th>REGDATE</th>
	<th>DELETE</th>
</tr>
<%
	ArrayList<UserDto> userList = (ArrayList)request.getAttribute("userList");
	for(UserDto user : userList){
		out.write("<tr>");
		out.write("<th>" + user.getId() + "</th>");
		out.write("<th>" + user.getName() + "</th>");
		out.write("<th>" + user.getEmail() + "</th>");
		out.write("<th>" + user.getRegdate()+ "</th>");
		// out.write("<th><button onclick='adminDelete.do?target=" + user.getId() + "\">DEL</button></th>");
		out.write("<th><button onclick='deleteUser(this)'>DEL</button></th>");
		out.write("</tr>");
	}
	out.write("<input type='hidden' name='signout_id' id='data_id' value=''");
	
%>
</table>
</form>


<script>
	function deleteUser(event){
		let userId = event.parentNode.parentNode.childNodes[0].innerText;
		console.log(userId);
		document.getElementById('data_id').value = userId;
	}
</script>

<%@ include file="/layout/footer.jsp"%>