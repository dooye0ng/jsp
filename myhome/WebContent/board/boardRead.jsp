<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<jsp:include page="/layout/header.jsp">
	<jsp:param value="Board List" name="title"/>
</jsp:include>

<table border = "1" style="width:100%">
	<tr>
		<th>�۾���</th>
		<td style="width:30%">${vo.writer_name }</td>
		<th>��ȸ��</th>
		<td style="width:30%">${vo.hit_count }</td>
	</tr>
	<tr>
		<th>����</th>
		<td  colspan="3">${vo.title }</td>
	</tr>
	<tr>
		<td colspan="4" style="height:400px; vertical-align:top;">${vo.content}</td>
	</tr>
</table>
<div align ="right">
	<button onclick="location.href='boardList'">�ڷ�</button>
</div>
<jsp:include page="/layout/footer.jsp"/>