<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/layout/header.jsp">
	<jsp:param name="title" value="Welcome!" />
</jsp:include>

<a href="${pageContext.request.contextPath }/user/adminPage">관리자 페이지로</a>
<jsp:include page="/layout/footer.jsp" />
