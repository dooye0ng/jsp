<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/layout/header.jsp">
	<jsp:param value="Board" name="title"/>
</jsp:include>

<c:set var="message">
<c:choose>
	<c:when test = "${flag == 'WRITE'}">게시판 글쓰기에 ${ result ? "성공하였습니다." : "실패하였습니다." }</c:when>
	<c:when test = "${flag == 'MODIFY'}">게시판 글수정에 ${ result ? "성공하였습니다." : "실패하였습니다." }</c:when>
</c:choose>
</c:set>


<script>
	alert('${message}');
	location.href='boardList';
</script>

<jsp:include page="/layout/footer.jsp"/>