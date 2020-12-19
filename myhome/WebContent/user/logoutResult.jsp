<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/layout/header.jsp">
	<jsp:param value="Logged Out" name="title"/>
</jsp:include>

<script  src="http://code.jquery.com/jquery-latest.min.js"></script>


<p>로그아웃을 완료하였습니다.<br> <span id="second">3</span>초 뒤 메인 화면으로 이동합니다...</p>

<script>
$(document).ready(function(){
	window.setTimeout(function(){
		clearInterval(tick);
		location.href="<c:out value='${pageContext.request.contextPath}'/>";
	}, 3000);
	
	tick = setInterval(function() {
		$('#second').value -= 1;
	}, 1000);
});
</script>


<jsp:include page="/layout/footer.jsp"/>




