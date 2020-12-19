<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/layout/header.jsp">
	<jsp:param name = "title" value = "Delete Page"/>
</jsp:include>
<h1>${message }</h1>
<button onclick='location.href="${pageContext.request.contextPath}"'>메인으로</button>









