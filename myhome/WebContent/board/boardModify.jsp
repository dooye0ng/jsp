<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/layout/header.jsp">
	<jsp:param value="Board Modify" name="title"/>
</jsp:include>

<div align = "center">
	<button id="del_btn">�� �����ϱ�</button>
</div>

<form action = "boardModify.do" method = "post">
	<input type = "hidden" name = "modify_no" value = "${vo.no }"/>
<table border = "1" style="width:100%">
	<tr>
		<th>����</th>
		<td><input style = "width:100%"name="modify_title" value="${vo.title }"></td>
	</tr>
	<tr>
		<td colspan="2" style="height:400px; vertical-align:top;">
		<textarea name = "modify_content" style="width:100%; height:400px">${vo.content}</textarea>
		</td>
	</tr>
	<tr>
		<td align="center" colspan="2">
			<input type = "submit" value = "����!"/>
		</td>
	</tr>
</table>

</form>










<script>
$("#del_btn").click(function(){
    if(confirm("���� �����Ͻðڽ��ϱ�?") == true){
        //alert("��ϵǾ����ϴ�");
    	//location.href='boardDelete?no=${param.no}';
    	$.ajax({
    	    url: "boardDelete", // Ŭ���̾�Ʈ�� ��û�� ���� ������ URL �ּ�
    	    data: { no: '${param.no}' },                // HTTP ��û�� �Բ� ������ ���� ������
    	    type: "GET",                             // HTTP ��û ���(GET, POST)
    	    dataType: "json"                         // �������� ������ �������� Ÿ��

    	})
    	// HTTP ��û�� �����ϸ� ��û�� �����Ͱ� done() �޼ҵ�� ���޵�.
    	.done(function(a) {
			if(a.result){  // 
				alert(a.message);
				location.href='boardList';
			}
    	})
    	// HTTP ��û�� �����ϸ� ������ ���¿� ���� ������ fail() �޼ҵ�� ���޵�.

    	.fail(function(xhr, status, errorThrown) {
	    	   alert('������ �߻��߽��ϴ�.');
    	})
    	
    }
    else{
        return ;
    }
});



</script>


<jsp:include page="/layout/footer.jsp"/>
