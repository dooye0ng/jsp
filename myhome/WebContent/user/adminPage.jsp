<%@page import="myhome.model.UserDto"%>
<%@page import="java.util.ArrayList"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
	<c:forEach var = "dto" items="${ requestScope.userList }">
		<tr>
			<td>${pageScope.dto.id }</td>
			<td>${pageScope.dto.name }</td>
			<td>${pageScope.dto.email }</td>
			<td>${pageScope.dto.regdate }</td>
			<td><button onclick='deleteUser(this)'>DEL</button></td>
		</tr>
	</c:forEach>
</table>
<input type='hidden' name='signout_id' id='data_id' value=''/>
</form>

<script>
	function deleteUser(event){
		let userId = event.parentNode.parentNode.childNodes[1].innerText;
		console.log(userId);
		document.getElementById('data_id').value = userId;
	}
</script>

<%@ include file="/layout/footer.jsp"%>