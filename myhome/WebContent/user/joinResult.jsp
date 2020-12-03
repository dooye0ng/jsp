<%@ include file="/layout/header.jsp"%>

<script>
	alert('<%=request.getAttribute("msg")%>');
</script>
<h1><%=request.getAttribute("msg") %></h1>
<%@ include file="/layout/footer.jsp"%>
