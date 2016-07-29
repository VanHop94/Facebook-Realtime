<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="<spring:url value="/resources/js/jquery-1.11.3.min.js" />"></script>
<title>Insert title here</title>
</head>
<body>
	<div id="comments"></div>

</body>
<script type="text/javascript">

	sendAjax('0');
	
	var loop, flag = 0;
	$(document).ready(function() {
		loop = setInterval(sendAjax, 1000);
	});

	function sendAjax() {
		$.ajax({
			url : "http://localhost:8080/SpringProject/display/manager/${id}",
			type : 'GET',
			dataType : 'json',
			headers: {
	             Accept: 'application/json'
	            },
			data : "",
			contentType : 'application/json',
			success : function(data) {
				var commentHtml = "";
				for (var i = 0; i < data.length; i++) {
					commentHtml += data[i].from_name + " ( ";
					commentHtml += "<input type=\"radio\" name=\"" + data[i].id +"\" value=\"1\" onclick=\"updateStatusOfComment('" + data[i].id +"','1')\" ";
					if(data[i].status == 1) commentHtml += "checked";
					commentHtml += "> Hiện";
					commentHtml += "<input type=\"radio\" name=\"" + data[i].id +"\" value=\"0\" onclick=\"updateStatusOfComment('" + data[i].id +"','0')\"";
					if(data[i].status == 0) commentHtml += "checked";
					commentHtml += "> Ẩn" + " ) ";
					commentHtml += "("
							+ new Date(data[i].create_time).toUTCString() + ")"
							+ "<br/>";
					commentHtml += data[i].content + "<br/><br/>";				
				}
				$("#comments").html(commentHtml);
			},
			error : function(data, status, er) {
				clearInterval(loop);
			}
		});
	}
	
	function updateStatusOfComment(idComments,status){
		
		$.ajax({
			url: "http://localhost:8080/SpringProject/display/manager/${id}/updateComment/" + idComments + "/" + status,
			type: "get",
			dataType: "text",
			success: function(result){
				//alert("update thanh cong");
			},
			error : function(result, status, er) {
				
			}
		});
		
	}
	
</script>
</html>