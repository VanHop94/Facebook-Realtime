<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="chrome=1">
    <title>Realtime Tivi</title>
    <meta name="description" content="Real time post">
    <meta name="robots" content="noindex, follow">
    <meta name="viewport" content="width=device-width, minimum-scale=1, maximum-scale=1">
    <!--Core CSS -->
    <link href="<spring:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="<spring:url value="/resources/font-awesome/css/font-awesome.css" />" rel="stylesheet">
    <link href="<spring:url value="/resources/css/skin/square/red.css" />" rel="stylesheet">
    <link rel="stylesheet" href="<spring:url value="/resources/css/isteven-multi-select.css" />">
    <link href="<spring:url value="/resources/css/style.css" />" rel="stylesheet">
</head>
<body>
	<input type="text" id="root" class="hidden" value="${pageContext.request.contextPath}" />
	<div id="main">
		<div class="container">
			<div class="row">
				<div class="col-md-4 col-md-offset-4">
					<div class="login-page rt-card">
						<div class="login-page-content">
							<div class="login-page-heading">
								<div class="user-avatar"></div>
							</div>
							<div class="alert alert-danger hidden" role="alert" id="error">
								${error}
							</div>
							<form name="loginForm" method="post" action="login" onsubmit="return checkValidate()">
								<div class="form-group">
								    <label for="username">Tên đăng nhập</label>
								    <input type="text" id="username" name="username" class="form-control" ng-model="credentials.username">
								</div>
								<div class="alert-box">
							      <span id="alert-username"></span>
							    </div>
								<div class="form-group">
								    <label for="password">Mật khẩu</label>
								    <input id="password" type="password" name="password" class="form-control" ng-model="credentials.password">
								</div>
								<div class="alert-box">
							      <span id="alert-password"></span>
							    </div>
								<button type="submit" class="btn btn-primary btn-login-page">Đăng Nhập</button>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

<!--Core js-->
<script type="text/javascript" src="<spring:url value="/resources/js/jquery-1.11.3.min.js" />"></script>
<script type="text/javascript" src="<spring:url value="/resources/js/bootstrap.min.js" />"></script>
<script type="text/javascript">
	$('.dropdown-toggle').dropdown();
</script>

<script>
	
	$(document).ready(function(){
		if($.trim($('#error').text()) != '')
			$('#error').removeClass('hidden');
	});
	
	function checkValidate(){
		
		var flag = true;
		
		if($('#username').val() == ''){
			$('#alert-username').text("Vui lòng nhập tên của bạn");
			flag = false;
		}
		else $('#alert-username').text("");
		
		if($('#password').val() == ''){
			$('#alert-password').text("Vui lòng nhập mật khẩu");
			flag = false;
		}
		else $('#alert-password').text("");
		
		
		/* var str = $('#username').val();
		var re = /([a-zA-Z_.0-9])+/i;
		var found = str.test(re);

		console.log(found); */
		
		//return false;
		
		return flag;
	}

</script>

</html>