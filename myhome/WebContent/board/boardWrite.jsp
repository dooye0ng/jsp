<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/layout/header.jsp">
	<jsp:param value="Board List" name="title"/>
</jsp:include>

<style>
.boxsizingBorder {
    -webkit-box-sizing: border-box;
       -moz-box-sizing: border-box;
            box-sizing: border-box;
}

</style>
<div style="width:90%; align=center;">
<form action = "boardWrite.do" method = "post">
<table border = "1" style="width:100%">
	<tr>
		<th>제목</th>
		<td style="width:50%"><input style="width:100%"name="write_title" required placeholder="제목을 입력하세요."></td>
		<th>${name }</th>
	</tr>
	<tr>
		<td  colspan="3">
		<textarea class = "boxsizingBorder" name="write_content" rows="20" cols="60" placeholder="내용을 입력하세요." required></textarea>
		</td>
	</tr>
	<tr>
		<td style = "text-align:center" colspan="3">
		 <input type="submit" value="작성!">
		</td>
	</tr>
</table>
</form>
</div>



<jsp:include page="/layout/footer.jsp"/>