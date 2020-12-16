<%@page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<jsp:include page="/layout/header.jsp">
	<jsp:param name="title" value="Board Page" />
</jsp:include>

<h1>BOARD</h1>
<form action="boardWrite.do">
	<table border="1">
		<tr>
			<th>닉네임</th>
			<th>글 제목</th>
			<th>아이디</th>
		</tr>
		<tr>
			<th>${sessionScope.userVo.name }</th>
			<td><input type="text" placeholder="title here" name="board_title" value="" required></td>
			<th>${sessionScope.userVo.id }</th>
		</tr>
		<tr>
			<td colspan="3">
			<textarea rows="10" cols="60" name="board_content" value="" required></textarea>
			</td>
		</tr>
		<tr>
			<th colspan="3"><input type="submit" value="작성하기" /></th>
		</tr>
	</table>
	
</form>

<%@ include file="/layout/footer.jsp"%>