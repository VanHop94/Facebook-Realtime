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
    <link rel="stylesheet" href="<spring:url value="/resources/css/angularjs-datetime-picker.css" />">
</head>
<body ng-app="rtApp">
	<input type="text" id="root" class="hidden" value="${pageContext.request.contextPath}" />
	<div id="main">
		<header>
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
		</header>
		<div class="container">
			<div class="row">
				<div class="col-md-12" ng-controller="listPostCtrl">
					<div class="rt-card">
						<div class="rt-card-heading">
							<a  class="rt-card-heading-title active" href="#">Danh sách bài đăng</a>
						</div>
						<div class="rt-card-body">
							<table id="list-post" class="table table-striped block-table">
								<thead>
									<tr>
										<td>#</td>
										<td style="width:120px">Page ID</td>
										<td>Bài viết</td>
										<td style="width:100px">Theo dõi đến</td>
										<td>Trạng thái</td>
										<td>Thao tác</td>
									</tr>
								</thead>
								<tbody>
									<tr dir-paginate= "item in items | orderBy:sortType:sortReverse | itemsPerPage: pageSize | filter:q"  current-page="currentPage">
										<td>{{$index +1}}</td>
										<td>
											<div class="fb-user-box">
												<div class="fb-user-box-avatar">
													<img src="https://graph.facebook.com/picture?id={{item.pageId}}" class="avatar-image"/>
												</div> 
												<span class="fb-user-name"><a href="http://facebook.com/{{item.pageId}}" target="_blank">{{item.pageId}}</a></span>
											</div>
										</td>
										<td>
											<div class="fb-comment-box">
												<div class="fb-comment-box-comtent">
													<h5><i class="fa fa-clock-o"></i> <a href="http://facebook.com/{{item.id}}" target="_blank">{{item.created_time | date:'yyyy-MM-dd HH:mm'}}</a></h5>
													<div class="content">
														{{item.message}}
													</div>
												</div>
											</div>
										</td>
										<td>{{item.update_util | date:'yyyy-MM-dd HH:mm'}}</td>
										<td>
											<span class="label label-primary" ng-if="item.status == 1">Active</span>
											<span class="label label-default" ng-if="item.status == 0">Inactive</span>
										</td>
										<td class="action" ng-if="item.status == 1"><a href="${pageContext.request.contextPath}/view/post/{{item.id}}/manager" target="_blank" class="rt-btn rt-btn-primary"><i class="fa fa-eye"></i></a></td>
										<td class="action" ng-if="item.status == 0"><a href="${pageContext.request.contextPath}/view/post/{{item.id}}/graph_realtime" target="_blank" class="rt-btn rt-btn-primary"><i class="fa fa-eye"></i></a></td>
									</tr>
								</tbody>
							</table>
							<div class="text-center">
				              	<dir-pagination-controls boundary-links="true" on-page-change="pageChangeHandler(newPageNumber)" template-url="<spring:url value="/resources/dirPagination.tpl.html" />">
				            </div>
						</div>
					</div>
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
<script type="text/javascript" src="<spring:url value="/resources/js/angular.min.js" />"></script>
<script type="text/javascript" src="<spring:url value="/resources/js/bootstrap.min.js" />"></script>
<script type="text/javascript" src="<spring:url value="/resources/js/ui-bootstrap-tpls-0.13.4.min.js" />"></script>
<script type="text/javascript" src="<spring:url value="/resources/js/dirPagination.js" />"></script>
<script type="text/javascript" src="<spring:url value="/resources/js/rtModule.js" />"></script>
<script type="text/javascript" src="<spring:url value="/resources/js/angular-confirm.js" />"></script>
<script type="text/javascript" src="<spring:url value="/resources/js/angularjs-datetime-picker.min.js" />"></script>
</html>