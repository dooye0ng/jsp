<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${param.title }</title>
<style>
#header, #footer {
border: 1px solid black;
width: 650px;
height: 40px;
}
#main{
width: 650px;
min-height: 400px;
padding-top: 15px;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
<div align = "center">

<div id = "header" align = "center">

<%--

		로그인 중이면 : 닉네임을 보여줌
				관리자님 | LOGOUT | MY PAGE | BOARD | DOWNLOAD 
		로그인 아니면 : LOGIN 링크 보여줌
				LOGIN | JOIN | BOARD | DOWNLOAD 
 --%>
<c:choose>
	<c:when test="${not empty id }">
		<%--관리자님 | LOGOUT | MY PAGE | BOARD | DOWNLOAD  --%>
		<span>${name }님</span> | 
		<span><a href="${pageContext.request.contextPath }/user/logout">LOGOUT</a></span> | 
		<span><a href="${pageContext.request.contextPath }/user/modify">MY PAGE</a></span> | 

	</c:when>
	<c:otherwise>
		<%--LOGIN | JOIN | BOARD | DOWNLOAD  --%>
		<span><a href="${pageContext.request.contextPath }/user/login">LOGIN</a></span> | 
		<span><a href="${pageContext.request.contextPath }/user/signup">JOIN</a></span> | 
	</c:otherwise>
</c:choose>
<span><a href="${pageContext.request.contextPath }/board/boardList">BOARD</a></span> |
<span><a href="#">DOWNLOAD</a></span> |
</div>
<div id = "main" align = "center">















