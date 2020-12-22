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

<table border = "1" style="width:100%">
	<tr>
		<th>번호</th>
		<th>조회수</th>
		<th style="width:50%">제목</th>
		<th>작성자</th>
		<th>등록시간</th>
		
	</tr>
	<c:if test = "${empty list }">
		<tr>
			<th colspan="5">게시글이 없습니다.</th>
		</tr>
	</c:if>
	<c:forEach var="vo" items="${list }">
	<tr>		
		<td align="center">${vo.no }</td>
		<td align="center">${vo.hit }</td>
		<td style="width:45%"><a href="${pageContext.request.contextPath}/board/boardRead?no=${vo.no}">${vo.title }</a></td>
		<td>${vo.writer_name }</td>
		<td>${vo.regdate }</td>
		
	</tr>
	</c:forEach>
</table>
</div>

<div>

	<c:set var= "currentPage" value = "${ empty param.page ? 1 : param.page}" scope="page"/>
	
	<%-- 현재 페이지 -3 부분 --%>
	<c:if test = "${currentPage > 3}">
		<span><a href="boardList?page=${currentPage-1 }">[이전]</a></span>
	</c:if>
	
	<%-- 현재 페이지 -2 부분 --%>
	<c:if test = "${currentPage > 2}">
		<span><a href="boardList?page=${currentPage-2 }">[${currentPage-2 }]</a></span>
	</c:if>
	
	<%-- 현재 페이지 -1 부분 --%>
	<c:if test = "${currentPage > 1}">
		<span><a href="boardList?page=${currentPage-1 }">[${currentPage-1 }]</a></span>
	</c:if>
	
	<span>${currentPage}</span>

	<%-- 현재 페이지 +1 부분 --%>
	<c:if test = "${currentPage < lastPage}">
		<span><a href="boardList?page=${currentPage+1 }">[${currentPage+1 }]</a></span>
	</c:if>
	
	<%-- 현재 페이지 +2 부분 --%>
	<c:if test = "${currentPage < lastPage-1}">
		<span><a href="boardList?page=${currentPage+2 }">[${currentPage+2 }]</a></span>
	</c:if>
	
	<%-- 현재 페이지 +3 부분 --%>
	<c:if test = "${currentPage < lastPage-2}">
		<span><a href="boardList?page=${currentPage+1 }">[다음]</a></span>
	</c:if>
</div>


<jsp:include page="/layout/footer.jsp"/>









