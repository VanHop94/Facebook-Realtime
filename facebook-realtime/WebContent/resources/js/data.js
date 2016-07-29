function query_data(){
	
	$.ajax({
		url : "../../post/all",
		type : "get" ,
		dataType : "json" ,
		success : function(data){
			var html = "";
			console.log(data);
//			for(var i = 0 ; i < data.length ; i++){
//				
//				html += '<tr>';
//					html += '<td>';
//						html += (i+1);
//					html += '</td>';
//					html += '<td>';
//						html += '<div class="fb-user-box">';
//							html += '<div class=\"fb-user-box-avatar\">';
//								html += '<img src="https://graph.facebook.com/picture?id='+data[i].pageId +'\" class=\"avatar-image\">';
//							html += '</div>'; 
//							html += '<span class=\"fb-user-name\">' + data[i].pageId +'</span>';
//						html += '</div>';
//					html += '</td>';
//					html += '<td>';
//						html += '<div class=\"fb-comment-box\">';
//							html += '<div class=\"fb-comment-box-comtent\">';
//								html += '<h5><i class=\"fa fa-clock-o\"></i>' + data[i].created_time + '</h5>';
//								html += '<div class=\"content\">';
//									html += data[i].message;
//								html += '</div>';
//							html += '</div>';
//						html += '</div>';
//					html += '</td>';
//					html += '<td>' + data[i].update_util + '</td>';
//					
//					if(data[i].status == 1){
//						html += '<td><span class=\"label label-primary\">Active</span></td>';
//						html += '<td class=\"action\"><a href=\"' + data[i].id + "/manager" + '\" class=\"rt-btn rt-btn-primary\"><i class=\"fa fa-eye\"></i></a></td>';
//					} else {
//						html += '<td><span class=\"label label-default\">Inactive</span></td>';
//						html += '<td class=\"action\"><a href=\"script::void\" class=\"rt-btn rt-btn-primary\"><i class=\"fa fa-eye\"></i></a></td>';
//					}
//				html += '</tr>';
//			}
			
//			$('#data').html(html);
			
		} ,
		error : function(status){
			alert(status);
		}
	});
	
}