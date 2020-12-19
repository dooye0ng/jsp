<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/layout/header.jsp">
	<jsp:param name="title" value="Modify Page" />
</jsp:include>

<h1>${message }</h1>
<c:choose>
	<c:when test = "${success }">
		<button onclick='location.href=\"${pageContext.request.contextPath}\"'>메인으로</button>
	</c:when>
	<c:otherwise>
		<button onclick='history.back()'>뒤로</button>
	</c:otherwise>
</c:choose>



<%@ include file="/layout/footer.jsp"%>

