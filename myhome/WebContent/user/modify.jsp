<%@page import="model.UserDto"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/layout/header.jsp">
	<jsp:param name="title" value="Modify Page" />
</jsp:include>

<form action="modify.do" method="post">
	<table border="1">
		<caption>회원 수정</caption>
		<!-- 아이디 -->
		<tr>
			<th>ID</th>
			<td><input type="text" value="${dto.id }" readonly></td>
		</tr>

		<!-- 비밀번호 -->
		<tr>
			<th rowspan="2">PASSWORD</th>
			<td><input type="password" id="pw1" name="modify_password" value = "${ dto.password}" required></td>
		</tr>

		<!-- 비밀번호 재입력 -->
		<tr>
			<td><input type="password" id="pw2" name="modify_password2" required></td>
		</tr>

		<!-- 이메일 입력 -->
		<tr>
			<th>EMAIL</th>
			<td><input type="email" name="modify_email" value = "${dto.email }" required></td>
		</tr>

		<!-- 닉네임 입력 -->
		<tr>
			<th>NICKNAME</th>
			<td><input type="text" name="modify_name" value = "${dto.name }" required></td>
		</tr>

		<!-- 가입 버튼 -->
		<tr>
			<td colspan="2">
				<!-- <input type = "button" id="btn_submit" value = "가입!"/> -->
				<button type="button" id="btn_submit">수정!</button>
			</td>
		</tr>
	</table>
</form>

<script>
	$(document).ready(function () {
		$("#btn_submit").click(function () {
			if($("#pw1").val() != $("#pw2").val()){
				alert("두 비밀번호가 일치하지 않습니다.");
				return;
			}
			// 비밀번호 정규식 검사
			// 아이디 정규식 검사 
			$(this).parents('form:first').submit();
		});
	}
	);
</script>

<%@ include file="/layout/footer.jsp"%>





