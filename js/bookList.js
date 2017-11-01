$(function(){
	//返回时
	getBookList(1);
	controlPage($("#page-num").val(),$("#total-page").text(),$("#first-page"),$("#pre-page"),$("#next-page"),$("#last-page"));
	//$("#query-key").val("");
	//图书录入
	$("#bookinsert").click(function(){
		window.location.href="/bookMgn/bookAdd";
	})

	//查询图书是否存在
	$("#query-btn").click(function(){
		getBookList(1);
		controlPage($("#page-num").val(),$("#total-page").text(),$("#first-page"),$("#pre-page"),$("#next-page"),$("#last-page"));
	})

	//回车查询
	$('#query-key').keyup(function(e){
		if(e.keyCode==13){
		   $('#query-btn').click(); //处理事件
		}
	});

	//分页
	$("#first-page").click(function(){ //首页
		getBookList(1);
		controlPage($("#page-num").val(),$("#total-page").text(),$("#first-page"),$("#pre-page"),$("#next-page"),$("#last-page"));
	})
	$("#last-page").click(function(){ //尾页
		var page = $("#total-page").text();
		getBookList(page);
		controlPage($("#page-num").val(),$("#total-page").text(),$("#first-page"),$("#pre-page"),$("#next-page"),$("#last-page"));
	})
	$("#pre-page").click(function(){ //上一页
		if(!parseInt($("#page-num").val()) || parseInt($("#page-num").val())>parseInt($("#total-page").text()) || parseInt($("#page-num").val())<=1){
			alert('请保证当前页在合理范围内');
			return false;
		}
		var page = parseInt($("#page-num").val()) - 1;
		getBookList(page);
		controlPage($("#page-num").val(),$("#total-page").text(),$("#first-page"),$("#pre-page"),$("#next-page"),$("#last-page"));
	})
	$("#next-page").click(function(){ //下一页
		if(!parseInt($("#page-num").val()) || parseInt($("#page-num").val())>parseInt($("#total-page").text()-1) || parseInt($("#page-num").val())<=0){
			alert('请保证当前页在合理范围内');
			return false;
		}
		var page = parseInt($("#page-num").val()) + 1;
		getBookList(page);
		controlPage($("#page-num").val(),$("#total-page").text(),$("#first-page"),$("#pre-page"),$("#next-page"),$("#last-page"));
	})
	$("#page-num").keyup(function(e){
		if(e.keyCode == 13){
			var page = parseInt($("#page-num").val());
			if(!page || page>parseInt($("#total-page").text()) || page<=0){
				alert("输入的页数不对");
				return false;
			}
			getBookList(page);
			controlPage($("#page-num").val(),$("#total-page").text(),$("#first-page"),$("#pre-page"),$("#next-page"),$("#last-page"));
		}
	})
})

//获取图书列表信息
function getBookList(page){
	var content = "";
	var queryKey = $("#query-key").val().trim();
	if(queryKey){
		$.ajax({
			async: false,
			type: 'POST',
			url: '/search',
			dataType: 'json',
			data: {name:queryKey,sidx:'b_pub_time',sord:'asc',rows:10,page:page},
			success: function(res){
				//console.log(res);
				$(".selfPage").removeClass('hidden');
				if(res.status == 0){
					var totalPage = Math.ceil(res.data.count/10); 
					$("#page-num").val(res.data.page);
					$("#total-page").text(totalPage);
					var returnData = res.data.bookList;
					for(var i in returnData){
						var bookName = "",booknameTitle="";
						if(returnData[i].name){
							bookName = returnData[i].name.length>25 ? returnData[i].name.substr(0,25)+'...' : returnData[i].name;
							booknameTitle = returnData[i].name;
						}else{
							bookName = returnData[i].foreignName.length>40?returnData[i].foreignName.substring(0,40)+'...':returnData[i].foreignName;
							booknameTitle = returnData[i].foreignName;
						}
						var authorStr = returnData[i].author.length>10 ? returnData[i].author.substr(0,10)+'...' : returnData[i].author;
						var countStr = returnData[i].count?returnData[i].count:'';
						var pubtimeStr = returnData[i].pubtime?returnData[i].pubtime:'';
						var createtimeStr = "";
						if(returnData[i].createtime){
							createtimeStr = returnData[i].createtime.replace(new RegExp(/-/gm) ,"/");
					 		createtimeStr = new Date(createtimeStr.slice(0,createtimeStr.indexOf("."))).format("yyyy-MM-dd");
					 	}
						content += '<tr>'+
										'<td><a class="bookname" title="'+booknameTitle+'" href="/bookQuery/bookQueryListDetail?bid='+returnData[i].bid+'">'+bookName+'</a></td>'+
										'<td title="'+returnData[i].author+'">'+authorStr+'</td>'+
										'<td>'+returnData[i].publisher+'</td>'+
										'<td>'+pubtimeStr+'</td>'+
										'<td>'+returnData[i].claimNumber+'</td>'+
										'<td>'+countStr+'</td>'+
										'<td>'+createtimeStr+'</td>'+
									'</tr>';
					}
					$(".wrap tbody").html(content);
				}
			}
		})
	}else{
		$(".wrap tbody").html('');
		$(".selfPage").addClass('hidden');
	}
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