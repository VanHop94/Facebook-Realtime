var app = angular.module('rtApp',['ui.bootstrap','angularUtils.directives.dirPagination','angular-confirm','angularjs-datetime-picker']);

/* Admin Page----------------------------------------------------------------------------------*/
app.controller('adminCtrl',function ($scope,$rootScope,USER_ROLES,AUTH_EVENTS, AuthService, ListUser, $modal){
	ListUser.success(function(data){
	$scope.sortType     = 'created_time'; // set the default sort type
		$scope.sortReverse  = true;  // set the default sort order
		$scope.items_org = data;
		$scope.item_org_2 = [];
		for(i in data){
			var ID = data[i].id
			$scope.item_org_2[ID] = angular.copy(data[i]);
		}
		$scope.items = angular.copy($scope.items_org);
		$scope.editState =[];
		$scope.editStatus = 0;
		$scope.group_title=['Group 1','Group 2','Group 3'];
		$scope.role_title=['Admin','Monitor','Reviewer'];
		$scope.group_filter = {
		    availableOptions: [
		      {id: '0', name: 'All'},
		      {id: '1', name: 'Group 1'},
		      {id: '2', name: 'Group 2'},
		      {id: '3', name: 'Group 3'} 
		    ],
		    selectedOption: {id: '0', name: 'All'} //This sets the default value of the select in the ui
	    };
	    $scope.role_filter = {
		    availableOptions: [
		      {id: '0', name: 'All'},
		      {id: '1', name: 'Admin'},
		      {id: '2', name: 'Monitor'},
		      {id: '3', name: 'Reviewer'} 
		    ],
		    selectedOption: {id: '0', name: 'All'} //This sets the default value of the select in the ui
	    };
		for (i in data){
			$scope.editState[data[i].id] = false;
		}
		$scope.currentPage = 1;
		$scope.pageSize = 10;
		$scope.filter = function () {
			$scope.items = angular.copy($scope.items_org);
			for (i in $scope.items ){
				var Id = $scope.items[i].id;
				$scope.items[i] = angular.copy($scope.item_org_2[Id]);
			}
			for(var i = $scope.items.length - 1; i >= 0; i--){
				if($scope.items[i].group != ($scope.group_filter.selectedOption.id-1) && $scope.group_filter.selectedOption.id!= 0){
					$scope.items.splice(i,1);
				}
			}
			for(var i = $scope.items.length - 1; i >= 0; i--){
				if($scope.items[i].role!= ($scope.role_filter.selectedOption.id - 1) && $scope.role_filter.selectedOption.id!= 0){
					$scope.items.splice(i,1);
				}
			}
		};
		$scope.edit = function($id){
			$scope.editStatus = 1;
			$scope.editState[$id]=true;
			$scope.group = {
			    availableOptions: [
			      {id: '0', name: 'Group 1'},
			      {id: '1', name: 'Group 2'},
			      {id: '2', name: 'Group 3'} 
			    ],
			    selectedOption: {id: '0', name: 'Group 1'} //This sets the default value of the select in the ui
		    };
		    $scope.role = {
			    availableOptions: [
			      {id: '0', name: 'Admin'},
			      {id: '1', name: 'Monitor'},
			      {id: '2', name: 'Reviewer'} 
			    ],
			    selectedOption: {id: '2', name: 'Reviewer'} //This sets the default value of the select in the ui
		    };
		    for(i in $scope.items){
				var uid = $scope.items[i].id;
				if(uid == $id ){
					$scope.group.selectedOption.id = $scope.items[i].group;
					$scope.role.selectedOption.id =  $scope.items[i].role;
				}
			}
		}
		$scope.cancelEdit = function($id){
			$scope.editStatus = 0;
			$scope.editState[$id]= false;
			for(i in $scope.items){
				var uid = $scope.items[i].id;
				if(uid == $id ){
					$scope.items[i] = angular.copy($scope.item_org_2[uid]);
				}
			}
		};
		$scope.update = function($id){
			$scope.editState[$id]= false;
			$scope.editStatus = 0;
			for(i in $scope.items){
				var uid = $scope.items[i].id;
				if(uid == $id){
					$scope.items[i].group = $scope.group.selectedOption.id;
					$scope.items[i].role = $scope.role.selectedOption.id;
					$scope.item_org_2[$id] = angular.copy($scope.items[i]);
				}
			}
		};
		$scope.open = function (size) {
			var modalInstance = $modal.open({
			  animation: true,
			  templateUrl: 'views/add_user',
			  controller: 'AddUserCtrl',
			  size: 'lg',
			});
		};
		$scope.openConfirmBoxDelete  = function (size) {
			var modalInstance = $modal.open({
			  animation: true,
			  templateUrl: 'views/confirm_box',
			  controller: 'ComfirmBoxDeleteCtrl',
			});
		};
	});
});
/* Post Management Page------------------------------------------------------------------------*/
app.controller('addPost', function($scope,$http){
	var rootPath = $('#root').val();
	$scope.update_until ='';
	
	$scope.$on("myEvent", function (event, args) {
		 $scope.update_until  = args.value;
    });
	
	$scope.disable_status = false;
	$scope.add = function ($post){
		$scope.disable_status = true;
		$scope.message = '';
		$scope.status = 1;
		if($('#url').val() == ''){
			$scope.status = 0;
			$scope.message = "Vui lòng nhập đường dẫn và thời gian theo dõi!";
			$scope.disable_status = false;
			return;
		}
		console.log($scope.update_until);
		var date = new Date($scope.update_until);
		$('#loader').show();
		$http({
		       method  : 'POST',
		       url     : rootPath + '/post/crawler',
		       params    : {'url' : $post.url , 'update_util' : date.getTime()},
		       contentType: "application/json",
		       headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'}
		  })
		 .success(function(result) {
			 var data = jQuery.parseJSON(result);
			 $scope.message = data.message;
			 $scope.status = data.status;
			 $scope.disable_status = false;
			 $('#loader').fadeOut();
			 if(data.status == 1)
				 window.location.href = rootPath + "/view/post/management";
		 })
		 .error(function(result) {
			 $('#loader').fadeOut();
			 $scope.disable_status = false;
			 $scope.message = "Mất kết nối đến máy chủ hoặc dữ liệu đầu vào không chính xác!";
			 $scope.status = 0;
		 });
	}
});

app.controller('postManagementCtrl', function ($scope, $modal,$confirm,$http,$filter){
	var rootPath = $('#root').val();
	$scope.updateTime = function () {
		var now = new Date();
		$scope.update_until = now.getTime() + 3600*1000;
		$scope.update_until = new Date($scope.update_until)
//		var fullYear = $scope.update_until.getFullYear();
//		var month =("0" + ($scope.update_until.getMonth() +1)).slice(-2);
//		var date = ("0" + $scope.update_until.getDate()).slice(-2);
//		var hour = ("0" + $scope.update_until.getHours()).slice(-2);
//		var minute = ("0" + $scope.update_until.getMinutes()).slice(-2);
//		$scope.update_until = date+'-'+month+'-'+fullYear+' '+hour+':'+minute;
//		console.log($scope.update_until);
        $scope.$root.$broadcast("myEvent", {
        	value: $scope.update_until
        });
    };
	$http.get(rootPath + "/post/all")
	.success(function(data){
		$scope.sortType = 'update_util'; // set the default sort type
		$scope.sortReverse  = true;  // set the default sort order
		$scope.group_title=['Group 1','Group 2','Group 3'];
		$scope.items = angular.copy(data);
		console.log(data);
		$scope.editState =[];
		for (i in $scope.items ){
			$scope.editState[data[i].id] = false;
			$scope.items[i].update_util = new Date($scope.items[i].update_util);
		}
		
		$scope.items_org = angular.copy($scope.items);
		$('#loader').fadeOut();
		$scope.edit = function($item){
			console.log($item.update_util);
			$item.update_util = new Date($item.update_util);
			var mm = $item.update_util.getMonth()+1;
			var dd = $item.update_util.getDate();
			var yyyy = $item.update_util.getFullYear();
			var hh = $item.update_util.getHours();
			var ss = $item.update_util.getMinutes();
			$item.update_util = dd+'-'+mm+'-'+yyyy+' '+hh+':'+ss+':'+'00';
			$scope.editState[$item.id]=true;
			
		}
		$scope.cancelEdit = function($id){
			$scope.editState[$id]= false;
			$scope.items = angular.copy($scope.items_org);
		};
		$scope.update = function($item){
			$scope.editState[$item.id]= false;
			for(i in $scope.items){
				var uid = $scope.items[i].id;
				if(uid != $item.id && $scope.editState[uid] ==false){
					$scope.items[i] = angular.copy($scope.items_org[i]);
				}
			}
			
			var date = (new Date($item.update_util)).getTime();
			$http({
				url : rootPath + "/post/" + $item.id +"/update",
				method : "POST",
				params : {'update_util' : date, 'status' : $item.status},
				contentType: "application/json",
				headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'}
			}).then(function(result){
				if(!result || result == 'false')
					alert("Thời gian cập nhật không cho phép vượt quá giới hạn!");
			}, function(){
				alert("Mất kết nối tới máy chủ hoặc dữ liệu đầu vào không chính xác!");
			});
		};
		$scope.openConfirmBoxDelete  = function ($id) {
			$confirm({text: 'Bạn có thực sự muốn thực hiện hành động này?'})
	        .then(function() {
	        	
	        	$http({
	        		url : rootPath + "/post/" + $id + "/delete",
	        		method : "post" , 
	         		contentType: "application/json",
	         		headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'}
	        	}).then(function(result){
	        		window.location.reload();
	        	}, function(){
	        		window.location.reload();
	        	});
	         	
	        });

		};
	});
});
/* List Post Page------------------------------------------------------------------------------*/
app.controller('listPostCtrl', function ($scope,$http){
	var rootPath = $('#root').val();
	$http.get(rootPath + '/post/all')
	.success(function(data){
		$scope.sortType = 'created_time';
		$scope.items = data;
		$scope.currentPage = 1;
		$scope.pageSize = 10;
		$('#loader').fadeOut();
	});
});
/* Block Page----------------------------------------------------------------------------------*/

app.controller('getPostDetail',function ($scope,$http){
	var rootPath = $('#root').val();
	$http.get(rootPath + "/post/" + $('#idPost').val() + "/query")
	.success(function(data){
		$scope.item = data;
	})
	.error(function(responce){
		alert("Mất kết nối tới máy chủ!");
	});
});


app.controller('blockUserCtrl', function ($scope,$modal,$confirm,$http,$interval){
	var rootPath = $('#root').val();
	var idPost = $('#idPost').val();
	var hasError = false;
	$scope.items_org=[];
	$scope.items =[];
	$scope.selectState =[];
	$scope.created_time = 0;
	$scope.blockButton = true;
	var stop;
	
	var getData = function(){
		$http.get(rootPath + '/post/' + idPost + "/user/manager?request_times=1&&created_time="+0) //$scope.created_time
		.success(function(result){
			if(result.length == 0 || result == 'null'){
				$interval.cancel(stop);
			} else {
				var data = jQuery.parseJSON(result);
				data = data.reverse();
				$scope.selectAll =0;
				$scope.newData = data.slice($scope.created_time, data.length);
				$scope.oldDataUpdate = data.slice(0,$scope.created_time);
				for(i in $scope.oldDataUpdate){
					if($scope.items[i].status != $scope.oldDataUpdate[i].status){
						$scope.items[i].status = $scope.oldDataUpdate[i].status;
						$scope.items_org[i].status = $scope.oldDataUpdate[i].status;
					}
				}
				
				$scope.items_org = $scope.items_org.concat($scope.newData);
				$scope.items = $scope.items.concat($scope.newData);
				/*for (i = data.length -1; i>=0;i--){
					$scope.selectState[data[i].id] = 0;
				}*/
				for (i in $scope.newData){
					$scope.selectState[$scope.newData[i].user_id] = 0;
				}
				
				$scope.created_time = $scope.items.length;
				//if($scope.items.length > 0)
				//	$scope.created_time = $scope.items[$scope.items.length-1].created_time;
//				console.log($scope.created_time);
			}
		})
		/*.error(function(responce){
			alert("Mất kết nối tới máy chủ!");
		});*/	
	};
	$http.get(rootPath + '/post/' + idPost + "/user/manager?request_times=0&&created_time=0")
	.success(function(result){
		var data = jQuery.parseJSON(result);
		data = data.reverse();
		$scope.items_org = angular.copy(data);
		$scope.items = angular.copy(data);
		$scope.selectAll =0;
		for (i in data){
			$scope.selectState[data[i].user_id] = 0;
		}
		//if($scope.items.length > 0)
			$scope.created_time = $scope.items.length;//$scope.items[$scope.items.length-1].created_time;
		stop = $interval (getData,$('#timeToRepeat').val());
	})
	/*.error(function(responce){
		alert("Mất kết nối tới máy chủ!");
	});	*/

	$scope.BlockAll = function () {
		$confirm({text: 'Bạn có thực sự muốn thực hiện hành động này?'})
        .then(function() {
        	$scope.blockButton = true;
        	stop = $interval (getData,$('#timeToRepeat').val());
        	var listIds = [];
        	var count = 0;
        	var selectAllFlag = true ;
         	for(k in $scope.selectState){
				if($scope.selectState[k] == 1){
					for (i in $scope.items){
						if($scope.items[i].user_id == k){
							listIds[count++] = $scope.items[i].user_id;
							$scope.items[i].status = 0;
							break;
						}
					}
					$scope.selectState[k] = 0;
				}
			}
         	if($scope.selectAll == 1){
	         	$http({
	         		method : 'POST',
	         		url : rootPath + "/post/" + idPost + "/user/updateAll" ,
	         		params : {
	         			'post_id' : idPost,
	         			'status' : 0,
	         		},
	         		contentType: "application/json",
	         		headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'}
	         	}).then(function(){
	         		
	         	},function(){
	     			/*alert("Mất kết nối tới máy chủ!");*/
	         	});
        	} else {
        		$http({
	         		method : 'POST',
	         		url : rootPath + "/post/" + idPost + "/user/update" ,
	         		params : {
	         			'user_ids' : listIds,
	         			'post_id' : idPost,
	         			'status' : 0,
	         		},
	         		contentType: "application/json",
	         		headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'}
	         	}).then(function(){
	         		
	         	},function(){
	     			/*alert("Mất kết nối tới máy chủ!");*/
	         	});
        	}
         	
         	$scope.selectAll = 0;
        });

	};
	$scope.All = function () {
		
		if($scope.selectAll == 1){
			$interval.cancel(stop);
			for(j in $scope.selectState){
				$scope.selectState[j] = 1;
			}
			$scope.blockButton = false;
		}else{
			stop = $interval (getData,$('#timeToRepeat').val());
			for(j in $scope.selectState){
				$scope.selectState[j] = 0;
			}
			$scope.blockButton = true;
		}	
	};
	$scope.Check = function () {
		var t = 1
		var isCheck = 0;
		var intervalStatus = 1;
		for(k in $scope.selectState){
			if($scope.selectState[k] == 0){
				$scope.selectAll = 0;
				t = 0;
			}else{
				intervalStatus = 0;
				isCheck = 1;
			}
		}
		if(intervalStatus ==1){
			stop = $interval (getData,$('#timeToRepeat').val());
		}else{
			$interval.cancel(stop);
		}
		if(isCheck == 1 ){
			$scope.blockButton = false;
		}else{
			$scope.blockButton = true;
		}
		if(t == 1){
			$scope.selectAll = 1;
		}
	};
	$scope.Show = function ($id) {
		for (i in $scope.items){
			if($scope.items[i].user_id == $id){
				$scope.items[i].status = 1;
				var user_ids = [$scope.items[i].user_id];
				$http({
	         		method : 'POST',
	         		url : rootPath + "/post/" + idPost + "/user/update" ,
	         		params : {
	         			'user_ids' : user_ids,
	         			'post_id' : idPost,
	         			'status' : 1,
	         		},
	         		contentType: "application/json",
	         		headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'}
	         	}).then(function(result){
	         		if(result.data == false){
						$scope.items[i].status = 0;
	         		}
	         	},function(response){
         			/*alert("Mất kết nối tới máy chủ!");*/
	         	});
				break;
			}
		}
	};
	$scope.ShowAll = function () {
		$confirm({text: 'Bạn có thực sự muốn thực hiện hành động này?'})
        .then(function() {
        	$scope.blockButton = true;
        	stop = $interval (getData,$('#timeToRepeat').val());
        	var listIds = [];
        	var count = 0;
         	for(k in $scope.selectState){
				if($scope.selectState[k] == 1){
					for (i in $scope.items){
						if($scope.items[i].user_id == k){
							listIds[count++] = $scope.items[i].user_id;
							$scope.items[i].status = 1;
							break;
						}
					}
					$scope.selectState[k] = 0;
				}
			}
         	if($scope.selectAll == 1){
	     		$http({
	         		method : 'POST',
	         		url : rootPath + "/post/" + idPost + "/user/updateAll" ,
	         		params : {
	         			'post_id' : idPost,
	         			'status' : 1,
	         		},
	         		contentType: "application/json",
	         		headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'}
	         	}).then(function(){
	         		
	         	},function(){
	     			/*alert("Mất kết nối tới máy chủ!");*/
	         	});
         	} else {
         		$http({
	         		method : 'POST',
	         		url : rootPath + "/post/" + idPost + "/user/update" ,
	         		params : {
	         			'user_ids' : listIds,
	         			'post_id' : idPost,
	         			'status' : 1,
	         		},
	         		contentType: "application/json",
	         		headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'}
	         	}).then(function(){
	         		
	         	},function(){
	     			/*alert("Mất kết nối tới máy chủ!");*/
	         	});
         	}
         	$scope.selectAll = 0;
         	
        });

	};
	$scope.Block = function ($id) {
		for (i in $scope.items){
			if($scope.items[i].user_id == $id){
				$scope.items[i].status = 0;
				var user_ids = [$scope.items[i].user_id];
				$http({
	         		method : 'POST',
	         		url : rootPath + "/post/" + idPost + "/user/update" ,
	         		params : {
	         			'user_ids' : user_ids,
	         			'post_id' : idPost,
	         			'status' : 0,
	         		},
	         		contentType: "application/json",
	         		headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'}
	         	}).then(function(result){
	         		if(result.data == false){
						$scope.items[i].status = 1;
					}
	         	},function(response){
         			/*alert("Mất kết nối tới máy chủ!");*/
	         	});
				break;
			}
		}
		
	};
	
});
	
Array.prototype.remove = function(from, to) {
  var rest = this.slice((to || from) + 1 || this.length);
  this.length = from < 0 ? this.length + from : from;
  return this.push.apply(this, rest);
};
	
app.controller('blockCommentCtrl', function($scope,$modal,$confirm,$http,$interval){
	var rootPath = $('#root').val();
	var idPost = $('#idPost').val();
	var hasError = false;
	var stop;
	$scope.blockButton = true;
	$scope.created_time = 0;
	
	var getData = function(){
		$http.get(rootPath + '/post/'+ idPost + "/comments/manager?request_times=1&&keyword=" + $('#keyword').val())
		.success(function(result){
			if(!isNaN(result)){
				$interval.cancel(stop);
				if(!hasError){
					hasError = true;
					if(parseInt(result) == 2)
						alert("Hệ thống đã dừng cập nhật bài đăng này");
					else
						alert("Không tồn tại Post này trong hệ thống hoặc đã bị xóa!");
				}
			}
			else {
				var data = jQuery.parseJSON(result);
				data = data.reverse();
				//$scope.items_org = data;
				//$scope.items = angular.copy($scope.items_org);
				$scope.selectAll = 0;
				$scope.newData = data.slice($scope.created_time, data.length);
				$scope.oldDataUpdate = data.slice(0,$scope.created_time);
				for(i in $scope.oldDataUpdate){
					if($scope.items[i].status != $scope.oldDataUpdate[i].status){
						$scope.items[i].status = $scope.oldDataUpdate[i].status;
						$scope.items_org[i].status = $scope.oldDataUpdate[i].status;
					}
				}
				
				$scope.items_org = $scope.items_org.concat($scope.newData);
				$scope.items = $scope.items.concat($scope.newData);
				/*for (i = data.length -1; i>=0;i--){
					$scope.selectState[data[i].id] = 0;
				}*/
				for (i in $scope.newData){
					$scope.selectState[$scope.newData[i].comment_id] = 0;
				}
				
				$scope.created_time = $scope.items.length;
				/*$scope.selectState =[];
				for (i in $scope.items) {
					$scope.selectState[$scope.items[i].comment_id] = 0;
				}*/
			}
		})
		/*.error(function(responce){
			alert("Mất kết nối tới máy chủ!");
		});*/
	}
	//getData();
	$http.get(rootPath + '/post/'+ idPost + "/comments/manager?request_times=0&&keyword=" + $('#keyword').val())
	.success(function(result){
		var data = jQuery.parseJSON(result);
		data = data.reverse();
		$scope.items_org = data;
		$scope.items = angular.copy($scope.items_org);
		$scope.selectAll =0;
		$scope.selectState =[];
		for (i in $scope.items) {
			$scope.selectState[$scope.items[i].comment_id] = 0;
		}
		$scope.created_time = $scope.items.length;
	})
	/*.error(function(responce){
		alert("Mất kết nối tới máy chủ!");
	});*/
	$('#loader').fadeOut();
	stop = $interval(getData, $('#timeToRepeat').val());
	$scope.All = function () {
		if($scope.selectAll == 1){
			$interval.cancel(stop);
			for(j in $scope.selectState){
				$scope.selectState[j] = 1;
			}
			$scope.blockButton = false;
		}else{
			stop = $interval(getData,$('#timeToRepeat').val());
			for(j in $scope.selectState){
				$scope.selectState[j] = 0;
			}
			$scope.blockButton = true;
		}	
	};
	$scope.Check = function () {
		var t = 1
		var isCheck = 0;
		var intervalStatus = 1;
		for(k in $scope.selectState){
			if($scope.selectState[k] == 0){
				$scope.selectAll = 0;
				t = 0;
			}else{
				isCheck = 1;
				intervalStatus = 0;
			}
		}
		if(intervalStatus ==1){
			stop = $interval (getData,$('#timeToRepeat').val());
		}else{
			$interval.cancel(stop);
		}
		if(isCheck == 1 ){
			$scope.blockButton = false;
		}else{
			$scope.blockButton = true;
		}
		if(t == 1){
			$scope.selectAll = 1;
		}
	};
	$scope.Show = function ($id) {
		for (i in $scope.items){
			if($scope.items[i].comment_id == $id){
				var listIds = [$scope.items[i].comment_id];
				$scope.items[i].status = 1;
				$http({
	         		method : 'POST',
	         		url : rootPath + "/post/" + idPost + "/comments/updateComments" ,
	         		params : {
	         			'listIds' : listIds,
	         			'status' : 1,
	         			'post_id' : idPost
	         		},
	         		contentType: "application/json",
	         		headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'}
	         	}).then(function(result){
	         		if(result.data == false){
						$scope.items[i].status = 0;
         				//alert("Có lỗi trong lúc update!");
	         		}
	         	},function(response){
         			/*alert("Mất kết nối tới máy chủ!");*/
	         	});
				break;
			}
		}
		
	};
	$scope.ShowAllComments = function () {
		$confirm({text: 'Bạn có thực sự muốn thực hiện hành động này?'})
        .then(function() {
        	$scope.blockButton = true;
        	stop = $interval(getData, $('#timeToRepeat').val());
        	var listIds = [];
        	var count = 0;
         	for(k in $scope.selectState){
				if($scope.selectState[k] == 1){
					for (i in $scope.items){
						if($scope.items[i].comment_id == k){
							listIds[count++] = $scope.items[i].comment_id;
							$scope.items[i].status = 1;
							break;
						}
					}
					$scope.selectState[k] = 0;
				}
			}
//         	if($scope.selectAll == 1){
//	         	$http({
//	         		method : 'POST',
//	         		url : rootPath + "/post/" + idPost + "/comments/updateCommentsAll" ,
//	         		params : {
//	         			'status' : 1,
//	         			'post_id' : idPost
//	         		},
//	         		contentType: "application/json",
//	         		headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'}
//	         	}).then(function(){
//	         		
//	         	},function(){
//	     			/*alert("Mất kết nối tới máy chủ!");*/
//	         	});
//         	} else {
         		$http({
	         		method : 'POST',
	         		url : rootPath + "/post/" + idPost + "/comments/updateComments" ,
	         		params : {
	         			'listIds' : listIds,
	         			'status' : 1,
	         			'post_id' : idPost
	         		},
	         		contentType: "application/json",
	         		headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'}
	         	}).then(function(response){
	         		
	         	},function(response){
	     			/*alert("Mất kết nối tới máy chủ!");*/
	         	});
         	//}
         	$scope.selectAll = 0;
        });

	};
	$scope.ShowAllUsers = function () {
		$confirm({text: 'Bạn có thực sự muốn thực hiện hành động này?'})
        .then(function() {
        	$scope.blockButton = true;
        	stop = $interval(getData, $('#timeToRepeat').val());
        	var listIds = [];
        	var count = 0;
         	for(k in $scope.selectState){
				if($scope.selectState[k] == 1){
					for (i in $scope.items){
						if($scope.items[i].id == k){
							listIds[count++] = $scope.items[i].from_id;
							$scope.items[i].status = 1;
							break;
						}
					}
					$scope.selectState[k] = 0;
				}
			}
         	if($scope.selectAll == 1){
	         	$http({
	         		method : 'POST',
	         		url : rootPath + "/post/" + idPost + "/user/updateAll" ,
	         		params : {
	         			'post_id' : idPost,
	         			'status' : 1,
	         		},
	         		contentType: "application/json",
	         		headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'}
	         	}).then(function(){
	         	},function(){
	     			/*alert("Mất kết nối tới máy chủ!");*/
	         	});
         	} else {
         		$http({
	         		method : 'POST',
	         		url : rootPath + "/post/" + idPost + "/user/update" ,
	         		params : {
	         			'user_ids' : listIds,
	         			'post_id' : idPost,
	         			'status' : 1,
	         		},
	         		contentType: "application/json",
	         		headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'}
	         	}).then(function(response){
	         	},function(response){
	     			/*alert("Mất kết nối tới máy chủ!");*/
	         	});
         	}
         	$scope.selectAll = 0;
        });

	};
	$scope.Block = function ($id) {
		for (i in $scope.items){
			if($scope.items[i].comment_id == $id){
				var listIds = [$scope.items[i].comment_id];
				$scope.items[i].status = 0;
				$http({
	         		method : 'POST',
	         		url : rootPath + "/post/" + idPost + "/comments/updateComments" ,
	         		params : {
	         			'listIds' : listIds,
	         			'status' : 0,
	         			'post_id' : idPost,
	         		},
	         		contentType: "application/json",
	         		headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'}
	         	}).then(function(result){
	         		if(result.data == false){
						$scope.items[i].status = 1;
         				alert("Có lỗi trong lúc update!");
	         		}
	         	},function(response){
         			/*alert("Mất kết nối tới máy chủ!");*/
	         	});
				break;
			}
		}
		
	};
	$scope.BlockAllComments = function () {
		$confirm({text: 'Bạn có thực sự muốn thực hiện hành động này?'})
        .then(function() {
        	$scope.blockButton = true;
        	stop = $interval(getData, $('#timeToRepeat').val());
        	var listIds = [];
        	var count = 0;
         	for(k in $scope.selectState){
				if($scope.selectState[k] == 1){
					for (i in $scope.items){
						if($scope.items[i].comment_id == k){
							listIds[count++] = $scope.items[i].comment_id;
							$scope.items[i].status = 0;
							break;
						}
					}
					$scope.selectState[k] = 0;
				}
			}
         	if($scope.selectAll == 1){
	         	$http({
	         		method : 'POST',
	         		url : rootPath + "/post/" + idPost + "/comments/updateCommentsAll" ,
	         		params : {
	         			'status' : 0,
	         			'post_id' : idPost
	         		},
	         		contentType: "application/json",
	         		headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'}
	         	}).then(function(){
	         		
	         	},function(){
	     			/*alert("Mất kết nối tới máy chủ!");*/
	         	});
         	} else {
         		$http({
	         		method : 'POST',
	         		url : rootPath + "/post/" + idPost + "/comments/updateComments" ,
	         		params : {
	         			'listIds' : listIds,
	         			'status' : 0,
	         			'post_id' : idPost
	         		},
	         		contentType: "application/json",
	         		headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'}
	         	}).then(function(response){
	         		
	         	},function(response){
	     			/*alert("Mất kết nối tới máy chủ!");*/
	         	});
         	}
         	$scope.selectAll = 0;
        });

	};
	$scope.BlockAllUsers = function () {
		$confirm({text: 'Bạn có thực sự muốn thực hiện hành động này?'})
        .then(function() {
        	$scope.blockButton = true;
        	stop = $interval(getData, $('#timeToRepeat').val());
        	var listIds = [];
        	var count = 0;
         	for(k in $scope.selectState){
				if($scope.selectState[k] == 1){
					for (i in $scope.items){
						if($scope.items[i].comment_id == k){
							console.log($scope.items[i].from_id);
							listIds[count++] = $scope.items[i].from_id;
							$scope.items[i].status = 0;
							break;
						}
					}
					$scope.selectState[k] = 0;
				}
			}
         	if($scope.selectAll == 1){
	         	$http({
	         		method : 'POST',
	         		url : rootPath + "/post/" + idPost + "/user/updateAll" ,
	         		params : {
	         			'post_id' : idPost,
	         			'status' : 0,
	         		},
	         		contentType: "application/json",
	         		headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'}
	         	}).then(function(){
	         		/*window.location.reload();*/
	         	},function(){
	         	});
         	} else {
         		$http({
	         		method : 'POST',
	         		url : rootPath + "/post/" + idPost + "/user/update" ,
	         		params : {
	         			'user_ids' : listIds,
	         			'post_id' : idPost,
	         			'status' : 0,
	         		},
	         		contentType: "application/json",
	         		headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'}
	         	}).then(function(response){
	         		/*window.location.reload();*/
	         	},function(response){
	         	});
         	}
         	$scope.selectAll = 0;
         	window.location.reload();
        });
	}
});

/* Template HTML-------------------------------------------------------------------------------*/
app.controller('AddUserCtrl', function ($scope, $modalInstance) {
	$scope.group = {
	    availableOptions: [
	      {id: '0', name: 'Group 1'},
	      {id: '1', name: 'Group 2'},
	      {id: '2', name: 'Group 3'} 
	    ],
	    selectedOption: {id: '0', name: 'Group 1'} //This sets the default value of the select in the ui
    };
    $scope.role = {
	    availableOptions: [
	      {id: '0', name: 'Admin'},
	      {id: '1', name: 'Monitor'},
	      {id: '2', name: 'Reviewer'} 
	    ],
	    selectedOption: {id: '2', name: 'Reviewer'} //This sets the default value of the select in the ui
    };
	// $scope.items = items;
	$scope.ok = function () {
		// $modalInstance.close();
	};

	$scope.cancel = function () {
		$modalInstance.dismiss('cancel');
	};
});
app.controller('AddLinkCtrl', function ($scope, $modalInstance) {
	$scope.group = {
	    availableOptions: [
	      {id: '0', name: 'Group 1'},
	      {id: '1', name: 'Group 2'},
	      {id: '2', name: 'Group 3'} 
	    ],
	    selectedOption: {id: '0', name: 'Group 1'}
	};
    $scope.modernBrowsers = [
        {                          
            name: 'Mac Hieu',              
            value: '0',        
            ticked: false    
        },
        { 
            name: 'Hanh chi',  
            value: '1',
            ticked: false   
        },
        { 
            name: 'Dinh Chien',            
            value: '2',    
            ticked: false    
        },
        { 
            name: 'Thanh Nhan',             
            value: '3',                 
            ticked: false   
        },
        { 
            name: 'Thanh Cong',             
            value: '4',                
            ticked: false   
        }    
    ];
	// $scope.items = items;
	$scope.ok = function () {
		// $modalInstance.close();
	};

	$scope.cancel = function () {
		$modalInstance.dismiss('cancel');
	};
});
