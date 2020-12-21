<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/layout/header.jsp">
	<jsp:param name="title" value="Download" />
</jsp:include>

<div align="right">
	<button onclick="location.href = 'upload'">업로드</button>
</div>
<!--  -->


<a href="${pageContext.request.contextPath }/user/adminPage">관리자 페이지로</a>
<jsp:include page="/layout/footer.jsp" />
