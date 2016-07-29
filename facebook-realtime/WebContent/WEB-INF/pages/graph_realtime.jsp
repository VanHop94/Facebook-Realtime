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
    <script type="text/javascript" src="<spring:url value="/resources/js/jquery-1.11.3.min.js" />"></script>
	<script type="text/javascript" src="<spring:url value="/resources/js/bootstrap.min.js" />"></script>
	<script type="text/javascript" src="<spring:url value="/resources/js/highcharts.js" />"></script>
</head>
<script>
	var chart; // global
	var showPost = false;
	var hasError = false;
	$(document).ready(function() {
		requestData();
	});
	
	var loop, flag = 0;
	$(document).ready(function() {
		var timeCallServer = $('#timeCallServer').val();
		loop = setInterval(requestData, timeCallServer);
	});

	/**
	 * Request data from the server, add it to the graph and set a timeout to request again
	 */
	function requestData() {
		$.ajax({
			url : "${pageContext.request.contextPath}/post/${id}/graph_realtime/data?request_times=" + $('#request_times').val(),
			success : function(point) {
				if (!isNaN(point)) {
					clearInterval(loop);
					if(!hasError){
						hasError = true;
						if(parseInt(point) == 2)
							alert("Hệ Thống Đã Dừng Cập Nhập Post Này");
						if(parseInt(point) == 3)
							alert("Post Này Không Tồn Tại Trong Hệ Thống Hoặc Đã Bị Xóa");
					}
				} else {
					data = jQuery.parseJSON(point);
					console.log(data);
					// var chartData = data.action_info;
					var series = chart.series[0], shift = series.data.length > 30;
					// shift if the series is longer than 20
					$('#request_times').val(data.action_info.requestTimes);
					if (data.action_info.requestTimes <= 1) {
						for (var i = 0; i < data.action_info.storage.list.length; i++) {
							chart.series[0].addPoint([data.action_info.storage.list[i].time,data.action_info.storage.list[i].like ],true, shift);
							chart.series[1].addPoint([data.action_info.storage.list[i].time,data.action_info.storage.list[i].share ],true, shift);
							chart.series[2].addPoint([data.action_info.storage.list[i].time,data.action_info.storage.list[i].comments ],true, shift);
						}
						$('.direct-chat-msg').html(
								"<p id='insert-comment'></p>");
					} else {
						if (data.action_info.like_count != -1) {
							console.log("co");
							console.log(data.action_info);
							console.log(new Date(data.action_info.date_time));
							chart.series[0].addPoint([
									data.action_info.date_time,
									data.action_info.like_count ], true,
									shift);
							chart.series[1].addPoint([
									data.action_info.date_time,
									data.action_info.share_count ], true,
									shift);
							chart.series[2].addPoint([
									data.action_info.date_time,
									data.action_info.comment_count ], true,
									shift);
							$('.direct-chat-msg').html(
									"<p id='insert-comment'></p>");
						}
					}
		
					//if (data.comment_detail.length != 0) {
						var new_comment = '';
						for ( var i in data.comment_detail) {
							var d = new Date(data.comment_detail[i].create_time);
							var time_comment = ("0" + d.getDate()).slice(-2)+ "-"+ ("0" + (d.getMonth() + 1)).slice(-2)
									+ "-"+ d.getFullYear()+ " "+ ("0" + d.getHours()).slice(-2)+ ":"
									+ ("0" + d.getMinutes()).slice(-2)+ ":"+ ("0" + d.getSeconds()).slice(-2);
							new_comment += '<tr><td><div class="fb-comment-box"><div class="fb-user-box style-2">'
									+ '<div class="fb-user-box-avatar style-2">'
									+ '<img class="avatar-image" src=" https://graph.facebook.com/'+ data.comment_detail[i].from_id + '/picture" alt="message user image"/>'
									+ '</div><a target="_blank" class="fb-user-name style-2" href="https://www.facebook.com/'+ data.comment_detail[i].from_id+'">'
									+ data.comment_detail[i].from_name
									+ '</a></div>'
									+ '<div class="fb-comment-box-comtent"><a target="_blank" href="'+data.comment_detail[i].url +'">'
									+ '<h5><i class="fa fa-clock-o"></i> '
									+ time_comment
									+ '</h5></a>'
									+ '<div class="content" style="word-break: break-all;">'
									+ data.comment_detail[i].content
									+ '</div></div></td></tr>';
							//$(new_comment).insertAfter("#insert-comment-1");
						}
						$('#insert-comment-1').html(new_comment);
		
					//}
		
					//if (data.user_info.length != 0) {
						var list_user = '';
						for ( var i in data.user_info) {
							list_user += '<tr class="delete"><td><div class="fb-user-box"><div class="fb-user-box-avatar">'
									+ '<a target="_blank" href="https://www.facebook.com/'+ data.user_info[i].userId+'">'
									+ '<img  class="avatar-image" src="https://graph.facebook.com/'+ data.user_info[i].userId + '/picture" alt=""/></a></div>'
									+ '<a target="_blank" class="fb-user-name style-2" href="https://www.facebook.com/'+ data.user_info[i].userId+'">'
									+ data.user_info[i].username
									+ '</a></div></td><td style="width:40px">'
									+ data.user_info[i].numCmt
									+ ' <i class="fa fa-thumbs-o-up"></i></span></a>'
									+ '</td></tr>';
						}
						$(".delete").remove();
						$(list_user).appendTo('#list-user');
		
					//}
		
					if (!showPost) {
						if( data.post_detail.message.length >300){
							data.post_detail.message = data.post_detail.message.substring(0,300) +'...';
						}
						var postDiv = '<div class="col-sm-12"><a style="font-size:16px;word-wrap:break-word;text-align:justify" target="_blank" href="http://facebook.com/' +  data.post_detail.post_id + '">'
								+ data.post_detail.pageId
								+ '</a></div>'
								+ '<div class="col-sm-4" style="padding:0px;">'
								+ '<img height="auto" width="100%" src="'
								+ data.post_detail.picture + '"></div>';
						postDiv += '<div class="col-sm-8" >';
						postDiv += '<p style="font-size:16px;text-align:justify">'
								+ data.post_detail.message + '</p>';
		
						postDiv += '</div>';
						$('#post-detail').append(postDiv);
						showPost = true;
					}
		
					$(".delete2").remove();
					var like_share_comment = '<div class="delete2" style="font-size: 1.2em; padding: 10px 0px">'
							+ '<p style="color:#2196f3;font-weight:600"><span><i class="fa fa-thumbs-o-up"></i> </span>'
							+ data.post_detail.like_count
							+ '</p>'
							+ '<p style="color:#7cb342;font-weight:600"><span><i class="fa fa-share"></i> </span>'
							+ data.post_detail.share_count
							+ '</p>'
							+ '<p style="color:#e53935;font-weight:600"><span><i class="fa fa-comments-o"></i> </span>'
							+ data.post_detail.comment_count
							+ '</p>'
							+ '</div>';
					$('#like-share-comment').append(like_share_comment);
				}
				$('#loader').fadeOut();
			},
			error : function(data, status, er) {
				clearInterval(loop);
			},
			cache : false
		});
	}

	$(document).ready(function() {
		chart = new Highcharts.Chart({
			chart : {
				renderTo : 'container',
				events : {
					load : requestData
				}
			},
			title : {
				text : 'Timeline Information'
			},
			xAxis : {
				type : 'datetime',
				labels : {
					formatter : function() {
						var myDate = new Date(this.value + 7 * 60 * 60 * 1000);
						return Highcharts.dateFormat('%H:%M:%S', myDate);
					}
				}
			},
			credits : {
				enabled : false
			},
			yAxis : {
				min : 0,
				minPadding : 0.2,
				maxPadding : 0.2,
				title : {
					text : 'Value',
					margin : 40
				}
			},
			plotOptions : {
				line : {
					marker : {
						enabled : false
					}
				}
			},
			series : [ {
				name : 'Like',
				color : '#2196f3',
				data : []
			}, {
				name : 'Share',
				color : '#7cb342',
				data : []
			}, {
				name : 'Comment',
				color : '#e53935',
				data : []
			} ]
		});
	});
</script>
<body>
	<input type="text" class="hidden" id="timeCallServer" value="${timeCallServer}" />
	<input type="text" class="hidden" id="request_times" value="${request_times}" />
	<input type="text" id="root" class="hidden" value="${pageContext.request.contextPath}" />
	
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
	<!-- <header> -->
		<%-- <div class="header-main-content">
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
						<a href="script::void" id="dLabel" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false"> <span
							class="user-name">${name}</span> <span class="caret"></span>
						</a>
						<ul class="dropdown-menu" aria-labelledby="dLabel">
							<li><a href="${pageContext.request.contextPath}/logout">Thoát</a></li>
						</ul>
					</div>
					<!-- /.navbar-collapse -->
				</div>
				<!-- /.container-fluid -->
			</nav>
		</div>
	</header> --%>
	<div class="container-fluid">
		<div class="row">
			<!-- good member -->
			<div class="col-md-2">
				<div class="rt-card">
					<div class="rt-card-heading">
						<div class="rt-card-heading-title active">Xếp hạng người dùng</div>
					</div>
					<div class="rt-card-body" style="max-height: 630px; overflow: auto">
						<table class="block-table table table-striped">
							<tbody id="list-user">
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="row">
					<!-- post -->
					<div class="rt-card">
						<div class="rt-card-body">
							<table class="block-table table" style="margin-bottom:0px;">
								<tbody>
									<tr>
										<td id="like-share-comment" style="background-color: #f1f1f1;"></td>
										<td id="post-detail" style="vertical-align: top"></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<!-- chart -->
					<div class="rt-card" style="margin-top: 15px;">
						<div class="rt-card-body">
							<div id="container"></div>
						</div>
					</div>
				</div>
			</div>

			<div class="col-md-4">
				<div class="rt-card">
					<div class="rt-card-heading">
						<div class="rt-card-heading-title active">Bình luận mới nhất</div>
					</div>
					<div class="rt-card-body" style="max-height: 630px; overflow: auto">
						<table class="block-table table table-striped ">
							<tbody id="insert-comment-1">

							</tbody>
						</table>
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
</html>