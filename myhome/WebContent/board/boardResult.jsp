<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/layout/header.jsp">
	<jsp:param name="title" value="Board Page" />
</jsp:include>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- ${result }, ${flag } etc. --%>
<!-- -> redirect(script tag) -->

<c:set var="messeage">
	<c:choose>
		<c:when test="${flag =='WRITE' }">게시판 글쓰기에 ${result ? "성공하였습니다" : "실패하였습니다." }</c:when>
	</c:choose>
</c:set>

<script>
	alert('${message}');
	location.href='boardList';
</script>
<%@ include file="/layout/footer.jsp"%>