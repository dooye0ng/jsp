<jsp:include page="/layout/header.jsp">
	<jsp:param name="title" value="Join Page" />
</jsp:include>

<script>
	alert('${requestScope.msg}');
</script>
<h1>${requestScope.msg}</h1>
<!-- requestScope 없이 사용해도 가능 함 -->
<!-- 영역이 작은 순서대로 찾아봄 pageContext -> request -> session -> application -->
<%-- <h1>${msg }</h1> --%>
<%@ include file="/layout/footer.jsp"%>
