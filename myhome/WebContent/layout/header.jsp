<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${param.title}</title>
<style>
#header, #footer {
	border: 1px solid black;
	width: 650px;
	height: 40px;
}

#main {
	width: 650px;
	min-height: 400px;
	padding-top: 15px;
}
</style>
</head>
<body>
	<div align="center">
	<div id="header" align="center">
		<c:choose>
		<c:when test="${empty sessionScope.userVo }">
				<a href="/myhome/">HOME</a> 
				| <a href="/myhome/user/login">LOGIN </a>
				| <a href="/myhome/user/join"> JOIN </a>
		</c:when>
		<c:otherwise>
			<a href="/myhome/">HOME</a> 
				| ${sessionScope.userVo.name }
				| <a href="/myhome/user/logout.do">LOGOUT </a>
				| <a href="/myhome/user/modify1.do"> MY PAGE</a>
		</c:otherwise>
		</c:choose>
		| <a href="/myhome/board/boardList"> BOARD</a>
		| <a href="#"> DOWNLOAD</a>
		
	</div>
		<div id="main" align="center">