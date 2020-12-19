<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/layout/header.jsp">
	<jsp:param value="Board List" name="title"/>
</jsp:include>

<div style="width:100%; align=center;">

<c:if test="${not empty id}">
	<div align="right"><button onclick="location.href='boardWrite'">글쓰기</button></div>
</c:if>

<table border = "1" style="width:80%">
	<tr>
		<th>번호</th>
		<th>조회수</th>
		<th style="width:50%">제목</th>
		<th>작성자</th>
		<th>등록시간</th>
		<th>삭제</th>
		<th>수정</th>
	</tr>
	<c:if test = "${empty list }">
		<tr>
			<th colspan="5">게시글이 없습니다.</th>
		</tr>
	</c:if>
	<c:forEach var="vo" items="${list }">
	<tr>		
		<td align="center">${vo.no }</td>
		<td align="center">${vo.hit_count }</td>
		<td style="width:45%"><a href="${pageContext.request.contextPath}/board/boardRead?no=${vo.no}">${vo.title }</a></td>
		<td>${vo.writer_name }</td>
		<td>${vo.regdate }</td>
		<c:if test="${vo.writer_id eq sessionScope.id }">
		<td><button onclick="if(confirm('정말 삭제하시겠습니까?')){location.href='boardDelete?no=${vo.no}'}">삭제</button></td>
		<td><button onclick="location.href='boardDelete?no=${vo.no}'">수정</button></td>
		</c:if>
	</tr>
	</c:forEach>
</table>
</div>



<jsp:include page="/layout/footer.jsp"/>