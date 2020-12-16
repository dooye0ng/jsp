<jsp:include page="/layout/header.jsp">
	<jsp:param name="title" value="Logout Page" />
</jsp:include>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h1>Logout Success !</h1>
<p>go main page in <span id="number">3</span> seconds</p>
<script>
	window.setTimeout(function(){
		location.href='<c:out value="${pageContext.request.contextPath}"/>'
	}, 3000);
	
	var i = 3;
	
	setInterval(function(){
		document.getElementById('number').innerText = --i;
	}, 1000);
</script>

<%@ include file="/layout/footer.jsp"%>
