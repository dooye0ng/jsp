<jsp:include page="/layout/header.jsp">
	<jsp:param name="title" value="Signout Page" />
</jsp:include>
<h1><%=request.getAttribute("message") %></h1>
<%@ include file="/layout/footer.jsp"%>