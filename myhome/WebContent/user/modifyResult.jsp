<%@ include file="/layout/header.jsp"%>
<script>
	alert('<%=request.getAttribute("msg")%>')
</script>
<h1><%=request.getAttribute("msg") %></h1>
<button onclick='document.location.href="/myhome/index.jsp"'>Go Main</button>
<%@ include file="/layout/footer.jsp"%>