<%@ page import="model.UserDto"%>
<%@ page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/layout/header.jsp">
	<jsp:param name = "title" value = "Admin Page"/>
</jsp:include>
	
	<c:if test="${not empty requestScope.result}">
		<script>
			alert("${result ? '삭제 성공' : '삭제 실패'}");
		</script>
	</c:if>
	
	<table border = "1">
		<caption>회원 목록</caption>
		<tr>	
			<th>ID</th>
			<th>NICKNAME</th>
			<th>EMAIL</th>
			<th>PASSWORD</th>
			<th>REGDATE</th>
		</tr>
		
		
		
		<c:forEach var = "dto" items="${ requestScope.list }">
		<tr>
			<td>${pageScope.dto.id }</td>
			<td>${pageScope.dto.name }</td>
			<td>${pageScope.dto.email }</td>
			<td>${pageScope.dto.password }</td>
			<td>${pageScope.dto.regdate }</td>
			<td><button onclick="location.href='adminDelete.do?target=${pageScope.dto.id}'">삭제</button></td>
		</tr>
		</c:forEach>
	</table>
	
<%@include file = "/layout/footer.jsp" %>







