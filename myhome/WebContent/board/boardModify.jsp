<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/layout/header.jsp">
	<jsp:param value="Board Modify" name="title"/>
</jsp:include>

<div align = "center">
	<button id="del_btn">글 삭제하기</button>
</div>

<form action = "boardModify.do" method = "post">
	<input type = "hidden" name = "modify_no" value = "${vo.no }"/>
<table border = "1" style="width:100%">
	<tr>
		<th>제목</th>
		<td><input style = "width:100%"name="modify_title" value="${vo.title }"></td>
	</tr>
	<tr>
		<td colspan="2" style="height:400px; vertical-align:top;">
		<textarea name = "modify_content" style="width:100%; height:400px">${vo.content}</textarea>
		</td>
	</tr>
	<tr>
		<td align="center" colspan="2">
			<input type = "submit" value = "수정!"/>
		</td>
	</tr>
</table>

</form>










<script>
$("#del_btn").click(function(){
    if(confirm("정말 삭제하시겠습니까?") == true){
        //alert("등록되었습니다");
    	//location.href='boardDelete?no=${param.no}';
    	$.ajax({
    	    url: "boardDelete", // 클라이언트가 요청을 보낼 서버의 URL 주소
    	    data: { no: '${param.no}' },                // HTTP 요청과 함께 서버로 보낼 데이터
    	    type: "GET",                             // HTTP 요청 방식(GET, POST)
    	    dataType: "json"                         // 서버에서 보내줄 데이터의 타입

    	})
    	// HTTP 요청이 성공하면 요청한 데이터가 done() 메소드로 전달됨.
    	.done(function(a) {
			if(a.result){  // 
				alert(a.message);
				location.href='boardList';
			}
    	})
    	// HTTP 요청이 실패하면 오류와 상태에 관한 정보가 fail() 메소드로 전달됨.

    	.fail(function(xhr, status, errorThrown) {
	    	   alert('오류가 발생했습니다.');
    	})
    	
    }
    else{
        return ;
    }
});



</script>


<jsp:include page="/layout/footer.jsp"/>
