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
		<c:when test="${empty sessionScope.session_id }">
				<a href="/myhome/">HOME</a> 
				| <a href="/myhome/user/login.jsp">LOGIN </a>
				| <a href="/myhome/user/join.jsp"> JOIN </a>
				| BOARD
				| DOWNLOAD
				|
			
		</c:when>
		<c:otherwise>
			<a href="/myhome/">HOME</a> 
				| ${sessionScope.session_id }
				| <a href="/myhome/user/logout.do">LOGOUT </a>
				| MY PAGE
				| BOARD
				| DOWNLOAD
				|
		</c:otherwise>
		</c:choose>
	</div>
		<div id="main" align="center">