<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<!-- Test01Servlet���� ����� ��Ű�� Test02.jsp���� Ȯ���ϱ�
	��Ű ��ȸ���
		1. EL ǥ�� ���
		2. java ����
 -->
 
 ${cookie.a.name } / ${cookie.a.value } 
 <hr/>
 ${cookie.b.name } / ${cookie.b.value } 
 <hr/>
 ${cookie.c.name } / ${cookie.c.value } 
</body>
</html>