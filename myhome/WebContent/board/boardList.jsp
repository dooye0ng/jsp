<%@page import="myhome.model.BoardDto"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %>
<jsp:include page="/layout/header.jsp">
	<jsp:param name="title" value="Board Page" />
</jsp:include>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:choose>
	<c:when test="${empty sessionScope.userVo }">
		<h1>글쓰기를 하려면 로그인하세요.</h1>
	</c:when>
	<c:otherwise>
		<button onclick="location.href='/myhome/board/boardWrite'">글쓰기</button>
	</c:otherwise>
</c:choose>

<div>
<table border="1">
	<tr>
		<th>번호</th>
		<th>제목</th>
		<th>작성자</th>
		<th>등록시간</th>
	</tr>
	<c:forEach var = "dto" items="${requestScope.boardList }">
		<tr>
			<td>${pageScope.dto.no }</td>
			<td><button onclick="alert('${pageScope.dto.content}')">${pageScope.dto.title }</button></td>
			<td>${pageScope.dto.writer_id }</td>
			<td>${pageScope.dto.regdate }</td>
		</tr>
	</c:forEach>
</table>
</div>

<%@ include file="/layout/footer.jsp"%>