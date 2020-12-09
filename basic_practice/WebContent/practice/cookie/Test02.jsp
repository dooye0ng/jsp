<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<!-- Test01Servlet으로 저장된 쿠키를 Test02.jsp에서 확인하기
	쿠키 조회방법
		1. EL 표현 방법
		2. java 버전
 -->
 
 ${cookie.a.name } / ${cookie.a.value } 
 <hr/>
 ${cookie.b.name } / ${cookie.b.value } 
 <hr/>
 ${cookie.c.name } / ${cookie.c.value } 
</body>
</html>