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
    <link href="<spring:url value="/resources/css/jquery.tagsinput.min.css" />" rel="stylesheet">
    <link rel="stylesheet" href="<spring:url value="/resources/css/angularjs-datetime-picker.css" />">
    <link rel="stylesheet" type="text/css" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.13/themes/start/jquery-ui.css">
</head>
<body ng-app="rtApp">
	<input type="text" id="timeToRepeat" class="hidden" value="${timeToRepeat}" />
	<input type="text" id="idPost" class="hidden" value="${idPost}" />
	<input type="text" id="keyword" class="hidden" value="${keyword}" />
	<input type="text" id="root" class="hidden" value="${pageContext.request.contextPath}" />
	<div id="main">
	
		<nav class="navbar navbar-inverse navbar-fixed-top">
	      <div class="container">
	        <div class="navbar-header">
	          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
	            <span class="sr-only">Toggle navigation</span>
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	          </button>
	          <a class="navbar-brand" style="color: #fff;font-size: 24px" href="${pageContext.request.contextPath}">DATASECTION</a>
	        </div>
	        <div id="navbar" class="collapse navbar-collapse">
	          <ul class="nav navbar-nav">
	            <li class="active"><a href="#">Quản lý người dùng</a></li>
	            <li class="active"><a href="${pageContext.request.contextPath}/view/post/management">Quản lý bài đăng</a></li>
	          </ul>
	          
	          <div class="dropdown user-box pull-right" style="margin-right: -70px;"> 
		    	<div class="user-avatar-box">
					<img src="<spring:url value="/resources/img/user.png" /> " class="avatar-image">
				</div> 
				<a href="script::void"id="dLabel" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					<span class="user-name">${name}</span>
					<span class="caret"></span>
				</a>
				<ul class="dropdown-menu" aria-labelledby="dLabel">
		          <li><a href="${pageContext.request.contextPath}/logout">Thoát</a></li>
				</ul>
			</div><!-- /.navbar-collapse -->
	          
	          
	        </div><!--/.nav-collapse -->
	      </div>
	    </nav>
		
		<%-- <header>
		    <div class="header-main-content">
		        <nav id="header-main" class="navbar navbar-default">
				  <div class="container-fluid">
				    <!-- Brand and toggle get grouped for better mobile display -->
					<div class="logo pull-left">
					    <a class="logo-brand" href="${pageContext.request.contextPath}">DATASECTION</a>
					</div>
					<ul class="nav navbar-nav">
					 	<li><a href="#">Quản lý người dùng <span class="sr-only">(current)</span></a></li>
					    <li><a href="${pageContext.request.contextPath}/view/post/management">Quản lý bài đăng</a></li>
					    <li><a href="${pageContext.request.contextPath}/view/post/list_post">Danh sách bài đăng</a></li>
					</ul>	
				    <!-- Collect the nav links, forms, and other content for toggling -->
				    <div class="dropdown user-box pull-right">
				    	<div class="user-avatar-box">
							<img src="<spring:url value="/resources/img/user.png" /> " class="avatar-image">
						</div> 
						<a href="script::void"id="dLabel" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							<span class="user-name">${name}</span>
							<span class="caret"></span>
						</a>
						<ul class="dropdown-menu" aria-labelledby="dLabel">
				          <li><a href="${pageContext.request.contextPath}/logout">Thoát</a></li>
						</ul>
					</div><!-- /.navbar-collapse -->
				  </div><!-- /.container-fluid -->
				</nav>
		    </div>
		</header> --%>
		<div id="block-page" class="container">
			<div class="row">
				<div class="col-md-12" style="padding:0px;">
					<div class="post-monitor" ng-controller="getPostDetail">
				    	<table id="list-post" class="table table-striped block-table">
							<thead>
								<tr>
									<td style="width : 10%">Fanpage</td>
									<td>Bài viết</td>
									<td>Thao tác</td>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td style="width : 10%">
										<div class="fb-user-box">
											<div class="fb-user-box-avatar">
												<img src="https://graph.facebook.com/picture?id={{item.pageId}}" class="avatar-image"/>
											</div> 
											<span class="fb-user-name" id="idPage" style="word-break: break-all !important;"><a href="http://facebook.com/{{item.pageId}}" target="_blank">{{item.pageId}}</a></span>
										</div>
									</td>
									<td>
										<div class="fb-comment-box">
											<div class="fb-comment-box-comtent">
												<h5 id="time_post"><i class="fa fa-clock-o"></i> <a href="http://facebook.com/{{item.post_id}}" target="_blank">{{item.created_time | date:'yyyy-MM-dd HH:mm'}}</a></h5>
												<div class="content" id="content_post">
													{{item.message}}
												</div>
											</div>
										</div>
									</td>
									<td width="190px">
										<a href="${pageContext.request.contextPath}/view/post/{{item.post_id}}/graph_realtime" target = "_blank" class="rt-btn rt-btn-primary"><i class="fa fa-share"></i> Theo dõi bài</a>
									</td>
								</tr>
							</tbody>
						</table>
		    		</div>
				</div>
		<!-- <div class="col-md-4" ng-controller="blockUserCtrl" style="padding:0px;">
			<div class="rt-card" style="padding-top:10px;">
				<div class="rt-card-heading">
			    	<div class="checkAll"><input type="checkbox"   ng-model="selectAll"  ng-true-value='1' ng-false-value='0' ng-change="All()"></div>
			     	<div style="margin-bottom:10px;">
			      		<button class="btn btn-primary" title="Block user" ng-click="ShowAll()" ng-disabled="blockButton"> <i class="fa fa-check"></i> Hiện người dùng</button>
			      		<button class="btn btn-danger" title="Block user" ng-click="BlockAll()" ng-disabled="blockButton"> <i class="fa fa-ban" ></i> Ẩn người dùng</button>
			     	</div>
			    </div>
				<div class="rt-card-body" style="padding:5px">
					<table class="block-table table">
						<tbody style="display:block;max-height:500px;overflow:auto">
							<tr ng-repeat="item in items">
								<td><input type="checkbox" ng-model="selectState[item.user_id]"  ng-true-value='1' ng-false-value='0' ng-change="Check()"></td>
								<td class="check_status{{item.status}}">
									<div class="fb-user-box" style="max-width:255px;word-wrap: break-word;">
										<div class="fb-user-box-avatar">
											<img src="https://graph.facebook.com/picture?id={{item.user_id}}" class="avatar-image">
										</div> 
										<a class="fb-user-name" href="http://facebook.com/{{item.user_id}}" target="_blank">{{item.name}}</a>
									</div>
								</td>
								<td class="action">
									<button class="btn btn-sm btn-primary" title="Show user" ng-click="Show(item.user_id)" ng-show="item.status == 0"> <i class="fa fa-check"></i></button>
									<button class="btn btn-sm btn-success" title="Show user" ng-show="item.status == 2"> <i class="fa fa-spinner"></i></button>
									<button class="btn btn-sm btn-danger" title="Block user" ng-click="Block(item.user_id)" ng-show="item.status == 1"> <i class="fa fa-ban"></i></button>
								</td>	
							</tr>								
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div class="col-md-1">
			<div class="arrow">
                <i class="fa fa-arrow-right"></i>
            </div>
		</div> -->
		
		
		<div class="col-md-12" ng-controller="blockCommentCtrl" style="padding:0px;">
			<div class="rt-card" style="padding-top:10px;">
				<div class="rt-card-heading">
					<div class="checkAll" style="margin-left: 20px;"><input type="checkbox"   ng-model="selectAll"  ng-true-value='1' ng-false-value='0' ng-change="All()"></div>
					<div style="margin-bottom:10px;margin-right: 10px;margin-left: 80px">

						<button class="btn btn-primary" title="Block user" ng-click="ShowAllComments()" ng-disabled="blockButton"> <i class="fa fa-check"></i> Hiện bình luận</button>
						<button class="btn btn-danger" title="Block user" ng-click="BlockAllComments()" ng-disabled="blockButton"> <i class="fa fa-ban" ></i> Ẩn bình luận</button>
						<!-- <button class="btn btn-danger" title="Block Comments" ng-click="BlockAllUsers()" ng-disabled="blockButton"> <i class="fa fa-ban" ></i> Ẩn người dùng</buttton> -->
						<div class="pull-right">
						<div class="control-group">
                            <div class="col-md-9 col-sm-9 col-xs-12" style="max-height: 70px !importance">
                                <input id="search" type="text" class="tags form-control" value="" />
                                <div id="suggestions-container" style="position: relative; float: left; width: 300px; margin: 10px;"></div>
                            </div>
                            <button style="margin-left: 5px" class="btn btn-primary" onclick="keySearch()">Lọc</button>
                        </div>
                        <!-- <input class="form-control" type="text" ng-model="search" placeholder="Search..."/> -->
					</div>
				</div>
				<div class="rt-card-body" style="padding:5px;height: 500px;width: 100%;overflow: auto;">
					<table class="block-table table ">
						<tbody style="max-height:500px;overflow:auto">
							<tr ng-repeat="item in items">
								<td><input type="checkbox" ng-model="selectState[item.comment_id]"  ng-true-value='1' ng-false-value='0' ng-change="Check()"></td>
								<td class="check_status{{item.status}}">
									<div class="fb-comment-box">
										<div class="fb-user-box style-2">
											<div class="fb-user-box-avatar style-2">
												<img src="https://graph.facebook.com/picture?id={{item.from_id}}" class="avatar-image">
											</div> 
											<a class="fb-user-name style-2" href="http://facebook.com/{{item.from_id}}" target="_blank">{{item.from_name}}</a>
										</div>
										<div class="fb-comment-box-comtent" style="width:470px;">
											<h5><i class="fa fa-clock-o"></i> <a href="http://facebook.com/{{item.comment_id}}" target="_blank">{{item.create_time | date:'yyyy-MM-dd HH:mm:ss'}}</a> </h5>
											<div class="content" style="word-wrap: break-word;">{{item.content}}</div>
										</div>
									</div>
								</td>
								<td class="action">
									<button class="btn btn-sm btn-primary" title="Show user" ng-click="Show(item.comment_id)" ng-show="item.status == 0"> <i class="fa fa-check"></i></button>
									<button class="btn btn-sm btn-danger" title="Block user" ng-click="Block(item.comment_id)" ng-show="item.status == 1"> <i class="fa fa-ban"></i></button>
								</td>
							</tr>						
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<div class="footer">
	    <div class="copyright">
	        <div class="container-fluid">
	            <div class="row">
	                <div class="col-md-12 col-sm-12">
	                    <h4 style="text-align: center;">© 2015 <a href="http://datasection.com.vn" target="_blank">Datasection</a> All Rights Reserved.</h4>
	                </div>
	            </div>
	        </div>
	    </div>
	</div>
	<div id="loader">
        <div class="spinner">
            <div class="dot1"></div>
            <div class="dot2"></div>
        </div>
    </div>
	</body>
	<!--Core js-->
	<script type="text/javascript" src="<spring:url value="/resources/js/jquery-1.11.3.min.js" />"></script>
	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.13/jquery-ui.min.js"></script>
	<script type="text/javascript" src="<spring:url value="/resources/js/angular.min.js" />"></script>
	<script type="text/javascript" src="<spring:url value="/resources/js/bootstrap.min.js" />"></script>
	<script type="text/javascript" src="<spring:url value="/resources/js/ui-bootstrap-tpls-0.13.4.min.js" />"></script>
	<script type="text/javascript" src="<spring:url value="/resources/js/dirPagination.js" />"></script>
	<script type="text/javascript" src="<spring:url value="/resources/js/rtModule.js" />"></script>
	<script type="text/javascript" src="<spring:url value="/resources/js/angular-confirm.js" />"></script>
	<script type="text/javascript" src="<spring:url value="/resources/js/jquery.tagsinput.min.js" />"></script>
	<script type="text/javascript" src="<spring:url value="/resources/js/angularjs-datetime-picker.min.js" />"></script>
	
	<script>
		
		$(function(){
			$('#search').tagsInput({width:'auto'});
		});
		
		function keySearch(){
			var key_search = $('#search').val();
			var url = window.location.href;
			var index = url.indexOf("?keyword");
			if(index > -1)
				url = url.substring(0,index);
			window.location.href = url + "?keyword=" + key_search;
		}
		
	
	</script>
	
	</html>