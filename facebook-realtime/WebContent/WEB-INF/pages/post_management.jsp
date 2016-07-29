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
    <link rel="stylesheet" href="<spring:url value="/resources/css/angularjs-datetime-picker.css" />">
    <link href="<spring:url value="/resources/css/style.css" />" rel="stylesheet">
</head>
<body ng-app="rtApp">
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
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="rt-card">
						<div class="rt-card-heading">
							<a  class="rt-card-heading-title active" href="#">Quản lí bài đăng</a>
						</div>
						<div class="rt-card-body" ng-controller="postManagementCtrl">
							<div class="add-user-button pull-right">
								<button type="button" ng-click="updateTime()"data-toggle="modal" data-target="#myModal" class="rt-btn rt-btn-primary" ><i class="fa fa-plus"></i> Thêm bài để quản lí</a>
							</div>
							<table id="admin-managerment" class="table table-striped">
								<thead>
									<tr>
										<td>#</td>
										<td>Hình ảnh</td>
										<td>Nội Dung</td>
										<td>Theo dõi đến</td>
										<td>Trạng thái</td>
										<!-- <td>Người tạo</td> -->
										<td colspan="2" >Thao tác</td>
									</tr>
								</thead>
								<tbody>
									<tr ng-repeat =  "item in items" ng-init="date1='01-01-2015 00:00:00'">
										<td>{{$index + 1 }}</td>
										<td style="width: 15%!important"><img style="max-height: 150px;max-width: 150px" src="{{item.picture}}" alt="picture" /><br/><center><a href="http://facebook.com/{{item.post_id}}" target="_blank">Đi đến bài viết</a></center></td>
										<td style="width: 40%!important;word-break: break-all;">{{item.message}}</td>
										<td ng-if="!editState[item.id]">{{item.update_util | date:'dd-MM-yyyy HH:mm'}}</td>
										<td ng-if="editState[item.id]"><input type="datetime-local" class="form-control" ng-model="item.update_util" style="width: 200px;"></td>
										<td>
											<span class="label label-primary" ng-if="item.status == 1">Active</span>
											<span class="label label-default" ng-if="item.status == 2">Inactive</span>
										</td>
										<!-- <td>{{item.human.username}}</td> -->

										<!--  -->
										
										<td class="action" ng-if="item.status == 1"><a href="${pageContext.request.contextPath}/view/post/{{item.post_id}}/manager" target="_blank" class="rt-btn rt-btn-primary"><i class="fa fa-eye"></i> Quản Lí</a></td>
										<td class="action" ng-if="item.status == 2"><a href="${pageContext.request.contextPath}/view/post/{{item.post_id}}/graph_realtime" target="_blank" class="rt-btn rt-btn-primary"><i class="fa fa-eye"></i> Theo dõi</a></td>
										
										<!-- <td class="action" ng-if="!editState[item.id]&&item.status==1"><a href="script::void" class="rt-btn rt-btn-danger" ng-click="edit(item)"><i class="fa fa-pencil-square-o"></i> Sửa</a></td> -->
										
										<td class="action" ng-if="!editState[item.id]"><a href="script::void" class="rt-btn rt-btn-default"  ng-click="openConfirmBoxDelete(item.id)"><i class="fa fa-trash-o"></i> Xóa</a></td>
										
										<td class="action" ng-if="editState[item.id]" style="width:150px"><button class="rt-btn rt-btn-primary" ng-click="update(item)" ><i class="fa fa-check"></i> Cập nhật</button></td>
										<td class="action" ng-if="editState[item.id]"><a href="script::void" class="rt-btn rt-btn-warning"  ng-click="cancelEdit(item.id)"><i class="fa fa-ban"></i> Hủy</a></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" ng-controller="addPost">
		<div class="modal-dialog  modal-lg" role="document">
	    	<div class="modal-content">
				<div class="modal-header">
	    			<h3 class="modal-title">Thêm bài</h3>
				</div>
				<div class="modal-body">
				    <div class="add-user row">
				    	<div class="col-md-12 alert alert-danger" id="error" role="alert" ng-if="status == 0">
				    		<button type="button" class="close" data-dismiss="alert">×</button>
				    		<strong>{{ message }}</strong>
				    	</div>
						<form class="form-horizontal" method="post" >
							<div class="col-md-7">
					            <label for="name">Nhập đường dẫn</label>
					            <input class="form-control" id="url" ng-model="post.url" type="text" value="" size="30" required>
					        </div>
					        <div class="col-md-4">
					            <label for="name">Theo dõi đến</label>
					            <input class="form-control" type="text" value="{{update_until|date:'dd-MM-yyyy hh:mm'}} " ng-disabled="true"/>
					        </div>
						</form>
					</div>
				</div>
				<div class="modal-footer">
	   				<button  class="rt-btn rt-btn-warning" type="button" data-dismiss="modal"><i class="fa fa-ban"></i> Hủy</button>
	    			<button class="rt-btn rt-btn-primary" type="button" ng-click="add(post)" ng-disabled="disable_status">Thêm</button>
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

<script type="text/javascript" src="<spring:url value="/resources/js/angular-ui-router.min.js" />"></script>
<script type="text/javascript" src="<spring:url value="/resources/js/ui-bootstrap-tpls-0.13.4.min.js" />"></script>
<script type="text/javascript" src="<spring:url value="/resources/js/dirPagination.js" />"></script>
<script type="text/javascript" src="<spring:url value="/resources/js/angular-confirm.js" />"></script>
<script type="text/javascript" src="<spring:url value="/resources/js/angularjs-datetime-picker.min.js" />"></script>
<script type="text/javascript" src="<spring:url value="/resources/js/rtModule.js" />"></script>



</html>