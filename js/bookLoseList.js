$(function(){
	//获取返回数据
	getAllEntityBook(1);
	controlPage($("#page-num").val(),$("#total-page").text(),$("#first-page"),$("#pre-page"),$("#next-page"),$("#last-page"));

	//分页
	$("#first-page").click(function(){
		getAllEntityBook(1);
		controlPage($("#page-num").val(),$("#total-page").text(),$("#first-page"),$("#pre-page"),$("#next-page"),$("#last-page"));
	})
	$("#pre-page").click(function(){
		if(!parseInt($("#page-num").val()) || parseInt($("#page-num").val())>parseInt($("#total-page").text()) || parseInt($("#page-num").val())<=1){
			alert('请保证当前页在合理范围内');
			return false;
		}
		var page = parseInt($("#page-num").val())-1;
		getAllEntityBook(page);
		controlPage($("#page-num").val(),$("#total-page").text(),$("#first-page"),$("#pre-page"),$("#next-page"),$("#last-page"));
	})
	$("#next-page").click(function(){
		if(!parseInt($("#page-num").val()) || parseInt($("#page-num").val())>parseInt($("#total-page").text()-1) || parseInt($("#page-num").val())<=0){
			alert('请保证当前页在合理范围内');
			return false;
		}
		var page = parseInt($("#page-num").val())+1;
		getAllEntityBook(page);
		controlPage($("#page-num").val(),$("#total-page").text(),$("#first-page"),$("#pre-page"),$("#next-page"),$("#last-page"));
	})
	$("#last-page").click(function(){
		var page = $("#total-page").text();
		getAllEntityBook(page);
		controlPage($("#page-num").val(),$("#total-page").text(),$("#first-page"),$("#pre-page"),$("#next-page"),$("#last-page"));
	})
	$("#page-num").keyup(function(e){
		if(e.keyCode == 13){
			var page = parseInt($("#page-num").val());
			if(!page || page>parseInt($("#total-page").text()) || page<=0){
				alert("输入的页数不对");
				return false;
			}
			getAllEntityBook(page);
			controlPage($("#page-num").val(),$("#total-page").text(),$("#first-page"),$("#pre-page"),$("#next-page"),$("#last-page"));
		}
	})
})

//获取所有损坏，遗失，剔旧的实体
function getAllEntityBook(page){
	var content = "";
	$.ajax({
		async: false,
		type: 'POST',
		url: '/getAllBookentity',
		dataType: 'json',
		data: {statusStr:'2,5,6,7,8',sidx:'be_updatetime',sord:'desc',rows:10,page:page},
		success: function(res){
			console.log(res);
			if(res.status == 0){
				var totalPage = Math.ceil(res.data.count/10);
				$("#page-num").val(res.data.page);
				$("#total-page").text(totalPage);
				$("#totalNum").text(res.data.count);
				if(res.data.bookEntityList){
					var resultData = res.data.bookEntityList;
					var statusView = '';
					for(var i in resultData){
						//var booknameStr = resultData[i].book.name.length>25?resultData[i].book.name.substring(0,25)+'...':resultData[i].book.name;
						var booknameStr = "",booknameTitle = "";
						if(resultData[i].book.name){
							booknameStr = resultData[i].book.name.length>25 ? resultData[i].book.name.substr(0,25)+'...' : resultData[i].book.name;
							booknameTitle = resultData[i].book.name;
						}else{
							booknameStr = resultData[i].book.foreignName.length>40 ? resultData[i].book.foreignName.substr(0,40)+'...' : resultData[i].book.foreignName;
							booknameTitle = resultData[i].book.foreignName;
						}
						var authorStr = resultData[i].book.author.length>10?resultData[i].book.author.substring(0,10)+'...':resultData[i].book.author;
						if(resultData[i].status == 2){
							statusView = '<span style="color:red;">已损坏</span>';
						}else if(resultData[i].status == 5){
							statusView = '<span style="color:red;">已遗失</span>';
						}else if(resultData[i].status == 6){
							statusView = '<span style="color:red;">已剔旧</span>';
						}else if(resultData[i].status == 7){
							statusView = '<span style="color:red;">装订修补</span>';
						}else if(resultData[i].status == 8){
							statusView = '<span style="color:red;">疑似遗失</span>';
						}
						var losterNameStr = resultData[i].losterName?resultData[i].losterName:'';
						content += '<tr>'+
										'<td class="bookname" title="'+booknameTitle+'">'+booknameStr+'</td>'+
										'<td title="'+resultData[i].book.author+'">'+authorStr+'</td>'+
										'<td>'+resultData[i].book.publisher+'</td>'+
										'<td>'+resultData[i].book.claimNumber+'</td>'+
										'<td>'+foo(resultData[i].barcode)+'</td>'+
										'<td>'+losterNameStr+'</td>'+
										'<td>'+statusView+'</td>'+
										'<td><button onclick="returnNomal('+resultData[i].beid+')">还原</button></td>'+
									'</tr>';
					}
					$(".wrap tbody").html(content);
				}
			}
		}
	})
}

//还原
function returnNomal(beid){
	$.ajax({
    	type: 'POST',
    	url: '/updateBook',
    	dataType: 'json',
    	data: {beidStr:beid,status:0},
    	success: function(res){
    		//console.log(res);
    		if(res.status == 0){
    			window.location.reload();
    		}
    		alert(res.msg);
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