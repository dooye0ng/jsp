<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/layout/header.jsp">
	<jsp:param name="title" value="Download" />
</jsp:include>

<form action="upload.do" method="post" enctype="multipart/form-data">
	<table border="1">
		<tr>
			<th>파일 1</th>
			<td><input type="file" name="file1"/></td>
		</tr>
		
		<tr>
			<th>파일 2</th>
			<td><input type="file" name="file2"/></td>
		</tr>
		
		<tr>
			<td colspan="2" align="center"><input type="submit"/></td>
		</tr>
	</table>
</form>

<jsp:include page="/layout/footer.jsp" />
