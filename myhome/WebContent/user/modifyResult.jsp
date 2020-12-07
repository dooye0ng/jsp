<jsp:include page="/layout/header.jsp">
	<jsp:param name="title" value="Modify Page" />
</jsp:include>
<script>
	alert('${requestScope.msg}');
</script>
<h1>${requestScope.msg}</h1>
<button onclick='document.location.href="/myhome/index.jsp"'>Go Main</button>
<%@ include file="/layout/footer.jsp"%>