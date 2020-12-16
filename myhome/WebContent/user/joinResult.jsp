<jsp:include page="/layout/header.jsp">
	<jsp:param name="title" value="Join Page" />
</jsp:include>

<script>
	alert('${requestScope.msg}');
</script>
<h1>${requestScope.msg}</h1>
<a href="/myhome">HOME</a>
<%@ include file="/layout/footer.jsp"%>
