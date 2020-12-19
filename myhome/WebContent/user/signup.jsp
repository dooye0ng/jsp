<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/layout/header.jsp">
	<jsp:param name="title" value="Signup Page" />
</jsp:include>

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

<form action="signup.do" method="post">
	<table border="1">
		<caption>회원 가입</caption>
		<!-- 아이디 -->
		<tr>
			<th>ID</th>
			<td><input type="text" name="signup_id" required></td>
		</tr>

		<!-- 비밀번호 -->
		<tr>
			<th rowspan="2">PASSWORD</th>
			<td><input type="password" id="pw1" name="signup_password" required></td>
		</tr>

		<!-- 비밀번호 재입력 -->
		<tr>
			<td><input type="password" id="pw2" name="signup_password2" required></td>
		</tr>

		<!-- 이메일 입력 -->
		<tr>
			<th>EMAIL</th>
			<td><input type="email" name="signup_email" required></td>
		</tr>

		<!-- 닉네임 입력 -->
		<tr>
			<th>NICKNAME</th>
			<td><input type="text" name="signup_name" required></td>
		</tr>

		<!-- 가입 버튼 -->
		<tr>
			<td colspan="2">
				<!-- <input type = "button" id="btn_submit" value = "가입!"/> -->
				<button type="button" id="btn_submit">가입!</button>
			</td>
		</tr>
	</table>
	
</form>
<%@ include file="/layout/footer.jsp"%>






