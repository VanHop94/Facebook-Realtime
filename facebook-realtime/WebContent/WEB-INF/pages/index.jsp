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
<body ng-app="rtApp">
	<div id="main">
		<div ui-view></div>
	</div>
</body>

<!--Core js-->
<script type="text/javascript" src="<spring:url value="/resources/js/jquery-1.11.3.min.js" />"></script>
<script type="text/javascript" src="<spring:url value="/resources/js/angular.min.js" />"></script>
<script type="text/javascript" src="<spring:url value="/resources/js/bootstrap.min.js" />"></script>
<script src="//ajax.googleapis.com/ajax/libs/angularjs/1.4.5/angular-sanitize.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/angularjs/1.4.5/angular-animate.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/angularjs/1.4.5/angular-cookies.js"></script>
<script type="text/javascript" src="<spring:url value="/r	esources/js/angular-ui-router.min.js" />"></script>
<script type="text/javascript" src="<spring:url value="/resources/js/ui-bootstrap-tpls-0.13.4.min.js" />"></script>
<script type="text/javascript" src="<spring:url value="/resources/js/dirPagination.js" />"></script>
<script type="text/javascript" src="<spring:url value="/resources/js/icheck.min.js" />"></script>
<script type="text/javascript" src="<spring:url value="/resources/js/rtModule.js" />"></script>
<script type="text/javascript" src="<spring:url value="/resources/js/angular-confirm.js" />"></script>
<script type="text/javascript" src="<spring:url value="/resources/js/isteven-multi-select.js" />"></script>
<script type="text/javascript">
	$('.dropdown-toggle').dropdown();
</script>

</html>