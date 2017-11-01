$(function(){
	var content = "";
	//获取未审核书目列表
	$.ajax({
		type: 'GET',
		url: '/getUncheckBook',
		dataType: 'json',
		data: {},
		success: function(res){
			//console.log(res);
			if(res.status == 0){
				var returnData = res.data;
				for(var i in returnData){
					var booknameStr = "",booknameTitle="";
					if(returnData[i].book.name){
						booknameStr = returnData[i].book.name.length>25 ? returnData[i].book.name.substr(0,25)+'...' : returnData[i].book.name;
						booknameTitle = returnData[i].book.name;
					}else{
						booknameStr = returnData[i].book.foreignName.length>40 ? returnData[i].book.foreignName.substr(0,40)+'...' : returnData[i].book.foreignName;
						booknameTitle = returnData[i].book.foreignName;
					}
					var authorStr = returnData[i].book.author.length>10 ? returnData[i].book.author.substr(0,10)+'...' : returnData[i].book.author;
					var createtimeStr = "";
					if(returnData[i].bmTime){
							createtimeStr = returnData[i].bmTime.replace(new RegExp(/-/gm) ,"/");
					 		createtimeStr = new Date(createtimeStr.slice(0,createtimeStr.indexOf("."))).format("yyyy-MM-dd");
					}
					content += '<tr>'+
									'<td>'+returnData[i].batchMark+'</td>'+
									'<td class="bookname" title="'+booknameTitle+'">'+booknameStr+'</td>'+
									'<td title="'+returnData[i].book.author+'">'+authorStr+'</td>'+
									'<td>'+returnData[i].book.publisher+'</td>'+
									'<td>'+createtimeStr+'</td>'+
									'<td><a class="btn-info" href="/bookMgn/bookUpdateDetail?barCode='+returnData[i].barcode+'">修改</a></td>'+
								'</tr>';
				}
				$(".wrap tbody").html(content);
			}
		}
	})
})