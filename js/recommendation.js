$(function(){
	//获取统计数
	getStats();

	var status = $("input[name='status']:checked");
	var sortBy = $("#sortBy option:selected");
	var sortType = $("input[name='sort']:checked");
	
	//初始加载列表
	getDataList(status,sortBy,sortType,1);
	controlPage($("#page-num").val(),$("#total-page").text(),$("#first-page"),$("#pre-page"),$("#next-page"),$("#last-page"));
	//分页
	$("#first-page").click(function(){ //首页
		getDataList(status,sortBy,sortType,1);
		controlPage($("#page-num").val(),$("#total-page").text(),$("#first-page"),$("#pre-page"),$("#next-page"),$("#last-page"));
	})
	$("#last-page").click(function(){ //尾页
		var page = $("#total-page").text();
		getDataList(status,sortBy,sortType,page);
		controlPage($("#page-num").val(),$("#total-page").text(),$("#first-page"),$("#pre-page"),$("#next-page"),$("#last-page"));
	})
	$("#pre-page").click(function(){ //上一页
		if(!parseInt($("#page-num").val()) || parseInt($("#page-num").val())>parseInt($("#total-page").text()) || parseInt($("#page-num").val())<=1){
			alert('请保证当前页在合理范围内');
			return false;
		}
		var page = parseInt($("#page-num").val()) - 1;
		getDataList(status,sortBy,sortType,page);
		controlPage($("#page-num").val(),$("#total-page").text(),$("#first-page"),$("#pre-page"),$("#next-page"),$("#last-page"));
	})
	$("#next-page").click(function(){ //下一页
		if(!parseInt($("#page-num").val()) || parseInt($("#page-num").val())>parseInt($("#total-page").text()-1) || parseInt($("#page-num").val())<=0){
			alert('请保证当前页在合理范围内');
			return false;
		}
		var page = parseInt($("#page-num").val()) + 1;
		getDataList(status,sortBy,sortType,page);
		controlPage($("#page-num").val(),$("#total-page").text(),$("#first-page"),$("#pre-page"),$("#next-page"),$("#last-page"));
	})
	$("#page-num").keyup(function(e){
		if(e.keyCode == 13){
			var page = parseInt($("#page-num").val());
			if(!page || page>parseInt($("#total-page").text()) || page<=0){
				alert("输入的页数不对");
				return false;
			}
			getDataList(status,sortBy,sortType,page);
			controlPage($("#page-num").val(),$("#total-page").text(),$("#first-page"),$("#pre-page"),$("#next-page"),$("#last-page"));
		}
	})

	//按条件检索
	$("#query").click(function(){
		status = $("input[name='status']:checked");
		sortBy = $("#sortBy option:selected");
		sortType = $("input[name='sort']:checked");
		getDataList(status,sortBy,sortType,1);
		controlPage($("#page-num").val(),$("#total-page").text(),$("#first-page"),$("#pre-page"),$("#next-page"),$("#last-page"));
	})

	//审核通过
	$("#auditPass").click(function(){
		var pid = $("#pid").val();
		var bid = $("#bid").val();
		var uri = $("#http").val();
		var mark = $("#mark").val();
		$.ajax({
			type: 'POST',
			url: '/backPurchase',
			dataType: 'json',
			data: {pid:pid,status:1,mark:mark,uri:uri,bid:bid},
			success: function(res){
				alert(res.msg);
				getDataList(status,sortBy,sortType,1);
				getStats();
			}
		})
	})

	//审核拒绝
	$("#auditRefuse").click(function(){
		var pid = $("#pid").val();
		var bid = $("#bid").val();
		var uri = $("#http").val();
		var mark = $("#mark").val();
		$.ajax({
			type: 'POST',
			url: '/backPurchase',
			dataType: 'json',
			data: {pid:pid,status:2,mark:mark,uri:uri,bid:bid},
			success: function(res){
				alert(res.msg);
				getDataList(status,sortBy,sortType,1);
				getStats();
			}
		})
	})
})

function getDataList(status,sortBy,sortType,page){
	$.ajax({
		async: false,
		type: 'GET',
		url: '/getAllPurchase',
		dataType: 'json',
		data: {status:status.val(),sidx:sortBy.val(),sord:sortType.val(),page:page,rows:10},
		success: function(res){
			//console.log(res);
			var content = "";
			var status = "",opt="";
			//console.log(res);
			if(res.status == 0){
				if(res.data.purchaseList.length>0){
					$("#page-num").val(res.data.pages);
					var totalPage = Math.ceil(res.data.allRows/10);
					$("#total-page").text(totalPage);
					for(var i=0;i<res.data.purchaseList.length;i++){
						if(res.data.purchaseList[i].status == 0){
							status = '待审核';
							opt = '<button onclick="audit('+res.data.purchaseList[i].pid+')">审核</button>';
						}else if(res.data.purchaseList[i].status == 1){
							status = '已通过';
							opt = "";
						}else{
							status = '已拒绝';
							opt = "";
						}
						var createtimeStr = res.data.purchaseList[i].createtime.replace(new RegExp(/-/gm) ,"/");
				 			createtimeStr = createtimeStr.slice(0,createtimeStr.indexOf("."));
				 		var titleStr = res.data.purchaseList[i].title.length>25?res.data.purchaseList[i].title.substring(0,25)+'...':res.data.purchaseList[i].title; 
				 		var authorStr = res.data.purchaseList[i].author.length>10?res.data.purchaseList[i].author.substring(0,10)+'...':res.data.purchaseList[i].author; 
						content += '<tr>'+
							'<td>'+res.data.purchaseList[i].pid+'</td>'+
							'<td style="color:#0000ff;" title="'+res.data.purchaseList[i].title+'">'+titleStr+'</td>'+
							'<td title="'+res.data.purchaseList[i].author+'">'+authorStr+'</td>'+
							'<td>'+res.data.purchaseList[i].publisher+'</td>'+
							'<td>'+res.data.purchaseList[i].uname+'</td>'+
							'<td>'+new Date(createtimeStr).format("yyyy-MM-dd")+'</td>'+
							'<td>'+status+'</td>'+
							'<td>'+opt+'</td>'+
						'</tr>';
					}
				}
				$("tbody").html(content);
			}
		}
	})
}

//审核
function audit(pid){
	//给隐藏域赋pid
	$("#pid").val(pid);
	$('#auditModal').modal('view',{
		speed : 800,
		easing : 'easeInOutElastic',
		animation : 'top',
		position: '4% auto',
		overlayClose : false,
		on : 'click',
		overlayColor : '#ccc',
		overlayOpacity : .5,
		close : '.close'
	});
	$.ajax({
		type: 'GET',
		url: '/getAllPurchase',
		dataType: 'json',
		data: {pid:pid,sidx:'p_createtime',sord:'asc',page:1,rows:10},
		success: function(res){
			if(res.status == 0){
				$("#mname").text(res.data.purchaseList[0].title);
				$("#mauthor").text(res.data.purchaseList[0].author);
				$("#mpublish").text(res.data.purchaseList[0].publisher);
				$("#mbanci").text(res.data.purchaseList[0].edition);
				$("#musername").text(res.data.purchaseList[0].uname);
				$("#mtime").text(res.data.purchaseList[0].createtime);
				$("#mreason").text(res.data.purchaseList[0].reason);
			}
		}
	})
}
//获取统计
function getStats(){
	$.ajax({
		type: 'GET',
		url: '/purchaseStats',
		dataType: 'json',
		data: '',
		success: function(res){
			if(res.status == 0){
				$("#total").text(res.data.all);
				$("#waitAudit").text(res.data.wait);
				$("#agreeAudit").text(res.data.resolve);
				$("#refuseAudit").text(res.data.finish);
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