<%@ include file="/layout/header.jsp"%>

<script>
	alert('<%=request.getAttribute("msg")%>');
	document.location.href = '<%=request.getAttribute("uri")%>';
</script>

<%@ include file="/layout/footer.jsp"%>
