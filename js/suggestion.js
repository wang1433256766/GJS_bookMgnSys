$(function(){
	//获取所有feedback
	getAllFeedBack(1);
	controlPage($("#page-num").val(),$("#total-page").text(),$("#first-page"),$("#pre-page"),$("#next-page"),$("#last-page"));

	//分页
	$("#first-page").click(function(){
		getAllFeedBack(1);
		controlPage($("#page-num").val(),$("#total-page").text(),$("#first-page"),$("#pre-page"),$("#next-page"),$("#last-page"));
	})
	$("#pre-page").click(function(){
		if(!parseInt($("#page-num").val()) || parseInt($("#page-num").val())>parseInt($("#total-page").text()) || parseInt($("#page-num").val())<=1){
			alert('请保证当前页在合理范围内');
			return false;
		}
		var page = parseInt($("#page-num").val())-1;
		getAllFeedBack(page);
		controlPage($("#page-num").val(),$("#total-page").text(),$("#first-page"),$("#pre-page"),$("#next-page"),$("#last-page"));
	})
	$("#next-page").click(function(){
		if(!parseInt($("#page-num").val()) || parseInt($("#page-num").val())>parseInt($("#total-page").text()-1) || parseInt($("#page-num").val())<=0){
			alert('请保证当前页在合理范围内');
			return false;
		}
		var page = parseInt($("#page-num").val())+1;
		getAllFeedBack(page);
		controlPage($("#page-num").val(),$("#total-page").text(),$("#first-page"),$("#pre-page"),$("#next-page"),$("#last-page"));
	})
	$("#last-page").click(function(){
		var page = $("#total-page").text();
		getAllFeedBack(page);
		controlPage($("#page-num").val(),$("#total-page").text(),$("#first-page"),$("#pre-page"),$("#next-page"),$("#last-page"));
	})
	$("#page-num").keyup(function(e){
		if(e.keyCode == 13){
			var page = parseInt($("#page-num").val());
			if(!page || page>parseInt($("#total-page").text()) || page<=0){
				alert("输入的页数不对");
				return false;
			}
			getAllFeedBack(page);
			controlPage($("#page-num").val(),$("#total-page").text(),$("#first-page"),$("#pre-page"),$("#next-page"),$("#last-page"));
		}
	})
})

//获取所有反馈
function getAllFeedBack(page){
	$.ajax({
		async: false,
		type: 'GET',
		url: '/getAllFeedback',
		dataType: 'json',
		data: {sidx:'f_createtime',sord:'desc',rows:10,page:page},
		success: function(res){
			var content = "";
			var statusStr = "";
			//console.log(res);
			if(res.status == 0){
    			var totalpage = Math.ceil(res.data.allrows/10);
    			$("#total-page").text(totalpage); //总页数
    			$("#page-num").val(res.data.page); //当前页
				if(res.data.feedbackList.length>0){
					for(var i=0;i<res.data.feedbackList.length;i++){
						var fdCreatetimeStr = res.data.feedbackList[i].fdCreatetime.replace(new RegExp(/-/gm) ,"/");
				 			fdCreatetimeStr = fdCreatetimeStr.slice(0,fdCreatetimeStr.indexOf("."));
						if(!res.data.feedbackList[i].adminContent){
							statusStr = '<span style="color:red">待处理</span>';
							content += '<li><a href="/suggestionAnswer?fid='+res.data.feedbackList[i].fid+'">'+
											'<div class="content-left">'+
												'<p>'+res.data.feedbackList[i].content+'</p>'+
												'<p>'+res.data.feedbackList[i].uname+'</p>'+
												'<p>'+new Date(fdCreatetimeStr).format("yyyy-MM-dd hh:mm:ss")+'</p>'+
											'</div>'+
											'<div class="content-right">'+statusStr+'</div>'+
										'</a></li>';
						}else{
							statusStr = '<span style="color:green">已处理</span>';
							content += '<li>'+
											'<div class="content-left">'+
												'<p>'+res.data.feedbackList[i].content+'</p>'+
												'<p style="color:green;">'+res.data.feedbackList[i].adminContent+'</p>'+
												'<p>'+res.data.feedbackList[i].uname+'&nbsp;&nbsp;&nbsp;&nbsp;'+new Date(fdCreatetimeStr).format("yyyy-MM-dd hh:mm:ss")+'</p>'+
											'</div>'+
											'<div class="content-right">'+statusStr+'</div>'+
										'</li>';
						}
						
					}
				}
				$(".content ul").html(content);
			}
		}
	})
}

//控制分页按钮
function controlPage(currentPage,totalPage,firstPage,prePage,nextPage,lastPage){
	if(currentPage&&totalPage){
		if(totalPage==1){
			firstPage.attr('disabled',true);
			prePage.attr('disabled',true);
			nextPage.attr('disabled',true);
			lastPage.attr('disabled',true);
		}else{
			if(currentPage==1){
				firstPage.attr('disabled',true);
				prePage.attr('disabled',true);
				nextPage.attr('disabled',false);
				lastPage.attr('disabled',false);
			}else if(currentPage == totalPage){
				firstPage.attr('disabled',false);
				prePage.attr('disabled',false);
				nextPage.attr('disabled',true);
				lastPage.attr('disabled',true);
			}else{
				firstPage.attr('disabled',false);
				prePage.attr('disabled',false);
				nextPage.attr('disabled',false);
				lastPage.attr('disabled',false);
			}
		}
	}
}