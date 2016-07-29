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
<header>
    <div class="header-main-content">
        <nav id="header-main" class="navbar navbar-default">
		  <div class="container-fluid">
		    <!-- Brand and toggle get grouped for better mobile display -->
		    <div class="logo pull-left">
				<a class="logo-brand" href="#/">REAL-TIME SYSTEM</a>
		    </div>

		    <!-- Collect the nav links, forms, and other content for toggling -->
		    <div class="dropdown user-box pull-right">
		    	<div class="user-avatar-box">
					<img src="img/user.png" class="avatar-image">
				</div> 
				<a href="script::void"id="dLabel" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					<span class="user-name">{{name}}</span>
					<span class="caret"></span>
				</a>
				<ul class="dropdown-menu" aria-labelledby="dLabel" ng-controller="logoutCtrl">
		          <li><a href="#change-password">Change Password</a></li>
		          <li><a href="script::void" ng-click="logout()">Log out</a></li>
				</ul>
			</div><!-- /.navbar-collapse -->
		  </div><!-- /.container-fluid -->
		</nav>
    </div>
</header>
<div class="container">
	<div class="row">
		<div class="col-md-12">
			<div class="rt-card">
				<div class="rt-card-heading">
					<a  class="rt-card-heading-title active" href="#admin">User Management</a>
					<a  class="rt-card-heading-title" href="#post-management">Post Management</a>
					<a  class="rt-card-heading-title" href="#list-post">List Post</a>
				</div>
				<div class="rt-card-body" ng-controller="adminCtrl">
					<div class="row">
						<div class="col-md-12">
							<div class="add-user-button pull-right">
								<a href="script::void" class="rt-btn rt-btn-primary" ng-click="open()"><i class="fa fa-plus"></i> Add New User</a>
							</div>
						</div>
						<div class="col-md-12" style="margin:0px 0px 20px;">
							<div class="search-box">
								<form class="form-inline">
									<div class="form-group">
										<label>Filter</label>
										<input type="text" class="form-control"  ng-model="q" placeholder="Enter a keyword...">
									</div>
									<div class="form-group">
										<label>Group</label>
										<select class="form-control"  ng-options="option.name for option in group_filter.availableOptions track by option.id"   ng-model="group_filter.selectedOption" ng-change="filter()">
										</select>
									</div>
									<div class="form-group">
										<label>Role</label>
										<select class="form-control"  ng-options="option.name for option in role_filter.availableOptions track by option.id"   ng-model="role_filter.selectedOption" ng-change="filter()">
										</select>
									</div>
									<div class="form-group pull-right">
	   									<label>Show</label>
										<input type="number" min="1" max="100" class="form-control itemPage" ng-model="pageSize">
										<label> entries</label>
									</div>
								</form>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<table id="admin-managerment" class="table table-striped" >
								<thead>
									<tr>
										<td>#</td>
										<td>Username</td>
										<td>
											<a href="script::void" class="sort" ng-click="sortReverse = !sortReverse;sortType='name'">
							                    Name
							                    <i ng-show="sortReverse&&sortType=='name'" class="fa fa-caret-down"></i>
							                    <i ng-show="!sortReverse&&sortType=='name'" class="fa fa-caret-up"></i>
							                </a>
										</td>
										<td>Status</td>
										<td>
											<a href="script::void" class="sort" ng-click="sortReverse = !sortReverse;sortType='created_time'">
							                    Created Time
							                    <i ng-show="sortReverse&&sortType=='created_time'" class="fa fa-caret-down"></i>
							                    <i ng-show="!sortReverse&&sortType=='created_time'" class="fa fa-caret-up"></i>
							                </a>
	                					</td>
										<td>Group</td>
										<td>Role</td>
										<td colspan="3">Action</td>
									</tr>
								</thead>
								<tbody >
									<tr dir-paginate= "item in items | orderBy:sortType:sortReverse | itemsPerPage: pageSize | filter:q"  current-page="currentPage">
										<td>{{(currentPage-1)*pageSize + $index + 1}}</td>
										<td>{{item.username}}</td>
										<td ng-show="!editState[item.id]">{{item.name}}</td>
										<td ng-show="!editState[item.id]">
											<span class="label label-primary" ng-if="item.status == 1">Active</span>
											<span class="label label-default" ng-if="item.status == 0">Inactive</span>
										</td>
										<td ng-show="!editState[item.id]">{{item.created_time | date:'yyyy-MM-dd'}}</td>
										<td ng-show="!editState[item.id]">{{group_title[item.group]}}</td>
										<td ng-show="!editState[item.id]">{{role_title[item.role]}}</td>
										<td class="action" ng-show="!editState[item.id]">
											<button class="rt-btn rt-btn-danger" ng-click="edit(item.id)" ng-disabled="editStatus"><i class="fa fa-pencil-square-o"></i> Edit</button>
										</td>
										<td class="action" ng-show="!editState[item.id]">
											<a href="script::void" class="rt-btn rt-btn-default" ng-click="openConfirmBoxDelete()"><i class="fa fa-trash-o" ></i> Delete</a>
										</td>
										<td class="action" ng-show="!editState[item.id]">
											<a href="script::void" class="rt-btn rt-btn-default" ng-click="openConfirmBoxDelete()"> Reset</a>
										</td>
										<td ng-show="editState[item.id]"><input type="type" class="form-control" ng-model="item.name" ng-model-options="{ updateOn: 'blur' }"></td>
										<td ng-show="editState[item.id]"><input type="checkbox" icheck ng-model="item.status" ng-true-value='1' ng-false-value='0'  ></td>
										<td ng-show="editState[item.id]">{{item.created_time |date:'yyyy-MM-dd'}}</td>
										<td ng-show="editState[item.id]">
											<select class="form-control"  ng-options="option.name for option in group.availableOptions track by option.id"   ng-model="group.selectedOption"></select>
										</td>
										<td ng-show="editState[item.id]">
											<select class="form-control"  ng-options="option.name for option in role.availableOptions track by option.id"   ng-model="role.selectedOption"></select>
										</td>
										<td class="action" ng-show="editState[item.id]"><a href="script::void" class="rt-btn rt-btn-primary" ng-click="update(item.id)"><i class="fa fa-check"></i> Update</a></td>
										<td class="action" ng-show="editState[item.id]"><a href="script::void" class="rt-btn rt-btn-warning"  ng-click="cancelEdit(item.id)"><i class="fa fa-ban"></i> Cancel</a></td>
									</tr>
								</tbody>
							</table>
							<div class="text-center">
				              	<dir-pagination-controls boundary-links="true" on-page-change="pageChangeHandler(newPageNumber)" template-url="resources/dirPagination.tpl.html"></dir-pagination-controls>
				            </div>
				        </div>
			        </div>
				</div>
			</div>
		</div>
	</div>
</div>
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