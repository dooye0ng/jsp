<jsp:include page="/layout/header.jsp">
	<jsp:param name="title" value="Login Page" />
</jsp:include>
<script>
		alert('${sessionScope.userVo.id != null ? "hello !" : "login failed ..."}');
</script>
<a href="../">Home</a>
<%@ include file="/layout/footer.jsp"%>