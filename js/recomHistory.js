$(function(){
	//获取当前登录的用户card
	var Uid = -1;
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
	getSelfPurchase(Uid,1);
	controlPage($("#page-num").val(),$("#total-page").text(),$("#first-page"),$("#pre-page"),$("#next-page"),$("#last-page"));
	//分页
	$("#first-page").click(function(){ //首页
		getSelfPurchase(Uid,1);
		controlPage($("#page-num").val(),$("#total-page").text(),$("#first-page"),$("#pre-page"),$("#next-page"),$("#last-page"));
	})
	$("#last-page").click(function(){ //尾页
		var page = $("#total-page").text();
		getSelfPurchase(Uid,page);
		controlPage($("#page-num").val(),$("#total-page").text(),$("#first-page"),$("#pre-page"),$("#next-page"),$("#last-page"));
	})
	$("#pre-page").click(function(){ //上一页
		if(!parseInt($("#page-num").val()) || parseInt($("#page-num").val())>parseInt($("#total-page").text()) || parseInt($("#page-num").val())<=1){
			alert('请保证当前页在合理范围内');
			return false;
		}
		var page = parseInt($("#page-num").val()) - 1;
		getSelfPurchase(Uid,page);
		controlPage($("#page-num").val(),$("#total-page").text(),$("#first-page"),$("#pre-page"),$("#next-page"),$("#last-page"));
	})
	$("#next-page").click(function(){ //下一页
		if(!parseInt($("#page-num").val()) || parseInt($("#page-num").val())>parseInt($("#total-page").text()-1) || parseInt($("#page-num").val())<=0){
			alert('请保证当前页在合理范围内');
			return false;
		}
		var page = parseInt($("#page-num").val()) + 1;
		getSelfPurchase(Uid,page);
		controlPage($("#page-num").val(),$("#total-page").text(),$("#first-page"),$("#pre-page"),$("#next-page"),$("#last-page"));
	})
	$("#page-num").keyup(function(e){
		if(e.keyCode == 13){
			var page = parseInt($("#page-num").val());
			if(!page || page>parseInt($("#total-page").text()) || page<=0){
				alert("输入的页数不对");
				return false;
			}
			getSelfPurchase(Uid,page);
			controlPage($("#page-num").val(),$("#total-page").text(),$("#first-page"),$("#pre-page"),$("#next-page"),$("#last-page"));
		}
	})

	//获取他人的荐购信息
	getOtherPurchase(1);
	controlPage($("#other-page-num").val(),$("#other-total-page").text(),$("#other-first-page"),$("#other-pre-page"),$("#other-next-page"),$("#other-last-page"));
	//分页
	$("#other-first-page").click(function(){ //首页
		getOtherPurchase(1);
		controlPage($("#other-page-num").val(),$("#other-total-page").text(),$("#other-first-page"),$("#other-pre-page"),$("#other-next-page"),$("#other-last-page"));
	})
	$("#other-last-page").click(function(){ //尾页
		var page = $("#other-total-page").text();
		getOtherPurchase(page);
		controlPage($("#other-page-num").val(),$("#other-total-page").text(),$("#other-first-page"),$("#other-pre-page"),$("#other-next-page"),$("#other-last-page"));
	})
	$("#other-pre-page").click(function(){ //上一页
		if(!parseInt($("#other-page-num").val()) || parseInt($("#other-page-num").val())>parseInt($("#other-total-page").text()) || parseInt($("#other-page-num").val())<=1){
			alert('请保证当前页在合理范围内');
			return false;
		}
		var page = parseInt($("#other-page-num").val()) - 1;
		getOtherPurchase(page);
		controlPage($("#other-page-num").val(),$("#other-total-page").text(),$("#other-first-page"),$("#other-pre-page"),$("#other-next-page"),$("#other-last-page"));
	}) 
	$("#other-next-page").click(function(){ //下一页
		if(!parseInt($("#other-page-num").val()) || parseInt($("#other-page-num").val())>parseInt($("#other-total-page").text()-1) || parseInt($("#other-page-num").val())<=0){
			alert('请保证当前页在合理范围内');
			return false;
		}
		var page = parseInt($("#other-page-num").val()) + 1;
		getOtherPurchase(page);
		controlPage($("#other-page-num").val(),$("#other-total-page").text(),$("#other-first-page"),$("#other-pre-page"),$("#other-next-page"),$("#other-last-page"));
	})
	$("#other-page-num").keyup(function(e){
		if(e.keyCode == 13){
			var page = parseInt($("#other-page-num").val());
			if(!page || page>parseInt($("#other-total-page").text()) || page<=0){
				alert("输入的页数不对");
				return false;
			}
			getOtherPurchase(page);
			controlPage($("#other-page-num").val(),$("#other-total-page").text(),$("#other-first-page"),$("#other-pre-page"),$("#other-next-page"),$("#other-last-page"));
		}
	})

})
//获取他人的荐购信息
function getOtherPurchase(page){
	$.ajax({
		async: false,
		type: 'GET',
		url: '/getUserPurchase',
		dataType: 'json',
		data: {sidx:'p_createtime',sord:'desc',page:page,rows:5},
		success: function(res){
			//console.log(res);
			var content = "";
			if(res.status == 0){
				if(res.data.purchaseList.length>0){
					$("#other-page-num").val(res.data.page);
					var totalPage = Math.ceil(res.data.allRows/5);
					$("#other-total-page").text(totalPage);
					for(var i=0;i<res.data.purchaseList.length;i++){
						var titleStr = res.data.purchaseList[i].title.length>25?res.data.purchaseList[i].title.substring(0,25)+'...':res.data.purchaseList[i].title;
						var authorStr = res.data.purchaseList[i].author.length>10?res.data.purchaseList[i].author.substring(0,10)+'...':res.data.purchaseList[i].author;
						var reasonStr = res.data.purchaseList[i].reason?res.data.purchaseList[i].reason.length>10?res.data.purchaseList[i].reason.substring(0,10)+'...':res.data.purchaseList[i].reason:'';
						var markStr = res.data.purchaseList[i].mark?res.data.purchaseList[i].mark.length>10?res.data.purchaseList[i].mark.substring(0,10)+'...':res.data.purchaseList[i].mark:'';
						var bidStr = res.data.purchaseList[i].bid?res.data.purchaseList[i].bid:'';
						var uriStr = res.data.purchaseList[i].uri?res.data.purchaseList[i].uri:'';
						var editionStr = res.data.purchaseList[i].edition?res.data.purchaseList[i].edition:'';
						content += '<tr>'+
										'<td class="bookname" title="'+res.data.purchaseList[i].title+'">'+titleStr+'</td>'+
										'<td title="'+res.data.purchaseList[i].author+'">'+authorStr+'</td>'+
										'<td>'+res.data.purchaseList[i].publisher+'</td>'+
										'<td>'+editionStr+'</td>'+
										'<td>'+res.data.purchaseList[i].createtime+'</td>'+
										'<td><a href="#">'+bidStr+'</a></td>'+
										'<td><a href="#">'+uriStr+'</a></td>'+
										'<td title="'+res.data.purchaseList[i].reason+'">'+reasonStr+'</td>'+
										'<td title="'+res.data.purchaseList[i].mark+'">'+markStr+'</td>'+
										'<td></td>'+
									'</tr>';
					}
				}
				$("#otherPur").html(content);
			}
		}
	})
}

//获取自己的荐购历史信息
function getSelfPurchase(Uid,page){
	$.ajax({
		async: false,
		type: 'GET',
		url: '/getAllPurchase',
		dataType: 'json',
		data: {uid:Uid,sidx:'p_createtime',sord:'desc',page:page,rows:5},
		success: function(res){
			//console.log(res);
			var content = "";
			if(res.status == 0){
				if(res.data.purchaseList.length>0){
					$("#page-num").val(res.data.pages);
					var totalPage = Math.ceil(res.data.allRows/5);
					$("#total-page").text(totalPage);
					for(var i=0;i<res.data.purchaseList.length;i++){
						var titleStr = res.data.purchaseList[i].title.length>10?res.data.purchaseList[i].title.substring(0,10)+'...':res.data.purchaseList[i].title;
						var reasonStr = res.data.purchaseList[i].reason?res.data.purchaseList[i].reason.length>10?res.data.purchaseList[i].reason.substring(0,10)+'...':res.data.purchaseList[i].reason:'';
						var markStr = res.data.purchaseList[i].mark?res.data.purchaseList[i].mark.length>10?res.data.purchaseList[i].mark.substring(0,10)+'...':res.data.purchaseList[i].mark:'';
						var editionStr = res.data.purchaseList[i].edition?res.data.purchaseList[i].edition:'';
						var bidStr = res.data.purchaseList[i].bid?res.data.purchaseList[i].bid:'';
						var uriStr = res.data.purchaseList[i].uri?res.data.purchaseList[i].uri:'';
						content += '<tr>'+
										'<td class="bookname" title="'+res.data.purchaseList[i].title+'">'+titleStr+'</td>'+
										'<td>'+res.data.purchaseList[i].author+'</td>'+
										'<td>'+res.data.purchaseList[i].publisher+'</td>'+
										'<td>'+editionStr+'</td>'+
										'<td>'+res.data.purchaseList[i].createtime+'</td>'+
										'<td><a href="#">'+bidStr+'</a></td>'+
										'<td><a href="#">'+uriStr+'</a></td>'+
										'<td title="'+res.data.purchaseList[i].reason+'">'+reasonStr+'</td>'+
										'<td title="'+res.data.purchaseList[i].mark+'">'+markStr+'</td>'+
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