$(function(){
	//获取当前登录的用户card
	var Uid = "";
	//判断是否登录
	$.ajax({
		async: false,
		type: 'GET',
		url: '/isLogin',
		dataType: 'json',
		data: '',
		success: function(res){
			//console.log(res);
			if(res.status == 0){
	            var data = eval('(' + res.data + ')');
	            Uid = data.uid;
	        }
		}
	})

	//获取自己的荐购历史信息
	getSelfFeedback(Uid,1);
	controlPage($("#page-num").val(),$("#total-page").text(),$("#first-page"),$("#pre-page"),$("#next-page"),$("#last-page"));
	//分页
	$("#first-page").click(function(){ //首页
		getSelfFeedback(Uid,1);
		controlPage($("#page-num").val(),$("#total-page").text(),$("#first-page"),$("#pre-page"),$("#next-page"),$("#last-page"));
	})
	$("#last-page").click(function(){ //尾页
		var page = $("#total-page").text();
		getSelfFeedback(Uid,page);
		controlPage($("#page-num").val(),$("#total-page").text(),$("#first-page"),$("#pre-page"),$("#next-page"),$("#last-page"));
	})
	$("#pre-page").click(function(){ //上一页
		if(!parseInt($("#page-num").val()) || parseInt($("#page-num").val())>parseInt($("#total-page").text()) || parseInt($("#page-num").val())<=1){
			alert('请保证当前页在合理范围内');
			return false;
		}
		var page = parseInt($("#page-num").val()) - 1;
		getSelfFeedback(Uid,page);
		controlPage($("#page-num").val(),$("#total-page").text(),$("#first-page"),$("#pre-page"),$("#next-page"),$("#last-page"));
	})
	$("#next-page").click(function(){ //下一页
		if(!parseInt($("#page-num").val()) || parseInt($("#page-num").val())>parseInt($("#total-page").text()-1) || parseInt($("#page-num").val())<=0){
			alert('请保证当前页在合理范围内');
			return false;
		}
		var page = parseInt($("#page-num").val()) + 1;
		getSelfFeedback(Uid,page);
		controlPage($("#page-num").val(),$("#total-page").text(),$("#first-page"),$("#pre-page"),$("#next-page"),$("#last-page"));
	})
	$("#page-num").keyup(function(e){
		if(e.keyCode == 13){
			var page = parseInt($("#page-num").val());
			if(!page || page>parseInt($("#total-page").text()) || page<=0){
				alert("输入的页数不对");
				return false;
			}
			getSelfFeedback(Uid,page);
			controlPage($("#page-num").val(),$("#total-page").text(),$("#first-page"),$("#pre-page"),$("#next-page"),$("#last-page"));
		}
	})
})

//获取自己的反馈历史信息
function getSelfFeedback(Uid,page){
	$.ajax({
		async: false,
		type: 'GET',
		url: '/getAllFeedback',
		dataType: 'json',
		data: {uid:Uid,sidx:'f_createtime',sord:'asc',rows:10,page:page},
		success: function(res){
			console.log(res);
			var content = "";
			if(res.status == 0){
				if(res.data.feedbackList.length>0){
					$("#totalNum").text(res.data.allrows);
					$("#page-num").val(res.data.page);
					var totalPage = Math.ceil(res.data.allrows/10);
					$("#total-page").text(totalPage);
					for(var i=0;i<res.data.feedbackList.length;i++){
						var contentStr = res.data.feedbackList[i].content.length>15?res.data.feedbackList[i].content.substring(0,15)+'...':res.data.feedbackList[i].content;
						var adminContentStr = res.data.feedbackList[i].adminContent?res.data.feedbackList[i].adminContent.length>15?res.data.feedbackList[i].adminContent.substring(0,15)+'...':res.data.feedbackList[i].adminContent:'';
						content += '<tr>'+
										'<td title="'+res.data.feedbackList[i].content+'">'+contentStr+'</td>'+
										'<td>'+res.data.feedbackList[i].fdCreatetime+'</td>'+
										'<td>'+res.data.feedbackList[i].uname+'</td>'+
										'<td title="'+res.data.feedbackList[i].adminContent+'">'+adminContentStr+'</td>'+
										'<td>'+res.data.feedbackList[i].fdUpdatetime+'</td>'+
										'<td></td>'+
									'</tr>';
					}
				}
				$("#selfPur").html(content);
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