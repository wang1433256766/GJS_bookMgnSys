$(function(){
	$("#returnBtn").click(function(){
		window.location.href = window.history.back();
	})
	//获取所有通知
	getAllNotify(1);
	controlPage($("#page-num").val(),$("#total-page").text(),$("#first-page"),$("#pre-page"),$("#next-page"),$("#last-page"));

	//分页
	$("#first-page").click(function(){
		getAllNotify(1);
		controlPage($("#page-num").val(),$("#total-page").text(),$("#first-page"),$("#pre-page"),$("#next-page"),$("#last-page"));
	})
	$("#pre-page").click(function(){
		if(!parseInt($("#page-num").val()) || parseInt($("#page-num").val())>parseInt($("#total-page").text()) || parseInt($("#page-num").val())<=1){
			alert('请保证当前页在合理范围内');
			return false;
		}
		var page = parseInt($("#page-num").val())-1;
		getAllNotify(page);
		controlPage($("#page-num").val(),$("#total-page").text(),$("#first-page"),$("#pre-page"),$("#next-page"),$("#last-page"));
	})
	$("#next-page").click(function(){
		if(!parseInt($("#page-num").val()) || parseInt($("#page-num").val())>parseInt($("#total-page").text()-1) || parseInt($("#page-num").val())<=0){
			alert('请保证当前页在合理范围内');
			return false;
		}
		var page = parseInt($("#page-num").val())+1;
		getAllNotify(page);
		controlPage($("#page-num").val(),$("#total-page").text(),$("#first-page"),$("#pre-page"),$("#next-page"),$("#last-page"));
	})
	$("#last-page").click(function(){
		var page = $("#total-page").text();
		getAllNotify(page);
		controlPage($("#page-num").val(),$("#total-page").text(),$("#first-page"),$("#pre-page"),$("#next-page"),$("#last-page"));
	})
	$("#page-num").keyup(function(e){
		if(e.keyCode == 13){
			var page = parseInt($("#page-num").val());
			if(!page || page>parseInt($("#total-page").text()) || page<=0){
				alert("输入的页数不对");
				return false;
			}
			getAllNotify(page);
			controlPage($("#page-num").val(),$("#total-page").text(),$("#first-page"),$("#pre-page"),$("#next-page"),$("#last-page"));
		}
	})
})

//获取所有已发送通知
function getAllNotify(page){
	$.ajax({
		async: false,
		type: 'GET',
		url: '/getAllNotify',
		dataType: 'json',
		data: {sidx:'n_createtime',sord:'asc',rows:10,page:page},
		success: function(res){
			var content = "";
			//console.log(res);
			if(res.status == 0){
    			var totalpage = Math.ceil(res.data.allrows/10);
    			$("#total-page").text(totalpage); //总页数
    			$("#page-num").val(res.data.page); //当前页
				if(res.data.notifyList.length>0){
					for(var i=0;i<res.data.notifyList.length;i++){
						content += '<li>'+
							'<div class="content-left">'+
								'<p>'+res.data.notifyList[i].title+'</p>'+
								'<p>'+res.data.notifyList[i].content+'</p>'+
							'</div>'+
							'<div class="content-right">'+
								'<span>'+res.data.notifyList[i].createtime+'</span>'+
							'</div>'+
						'</li>';
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