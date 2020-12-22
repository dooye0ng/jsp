<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/layout/header.jsp">
	<jsp:param name="title" value="Download" />
</jsp:include>

<c:if test="${not empty id }">
	<div align="right">
		<button onclick="location.href = 'upload'">업로드</button>
	</div>
</c:if>

<table border="1">

	<tr>
		<th>NO.</th>
		<th>업로더</th>
		<th>파일명</th>
		<th>등록일자</th>
	</tr>
	<c:forEach var="vo" items="${list }">
	<tr>
		<th>${vo.no }</th>
		<th>${vo.uploader_id }</th>
		<th><a href="download.do?no=${vo.no }">${vo.fileName }</a></th>
		<th>${vo.regdate }</th>
	</tr>
	</c:forEach>

</table>
<jsp:include page="/layout/footer.jsp" />
