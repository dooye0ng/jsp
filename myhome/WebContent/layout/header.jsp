<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String title = "나의 홈페이지";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%=title %></title>
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
</head>
<body>
	<div align = "center">
		<div id = "header" align = "center">
			<a href="/myhome/user/login.jsp">LOGIN </a> |
			LOGOUT | 
			<a href="/myhome/user/signout.jsp"> SIGN OUT </a>| 
			<a href="/myhome/user/join.jsp"> JOIN </a>| 
			MY PAGE |
			BOARD |
			<a href="/myhome/user/modifyUser.jsp"> MODIFY </a>|
		</div>
		<div id = "main" align = "center">
		