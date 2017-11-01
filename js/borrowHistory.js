$(function(){
	//获取借阅历史信息
	getBorrowedHistory(1);
})

//获取借阅历史信息
function getBorrowedHistory(page){
	$.ajax({
		type: 'GET',
		url: '/getBorrowInfo',
		dataType: 'json',
		data: {sidx:'bo_createtime',sord:'desc',page:page,rows:10},
		success: function(res){
			//console.log(res);
			var content = "";
			if(res.status == 0){
				if(res.data.borrowList.length>0){
					for(var i=0;i<res.data.borrowList.length;i++){
						var bookNameStr = "",booknameTitle="";
						if(res.data.borrowList[i].bookName){
							bookNameStr = res.data.borrowList[i].bookName.length>25?res.data.borrowList[i].bookName.substring(0,25)+'...':res.data.borrowList[i].bookName;
							booknameTitle = res.data.borrowList[i].bookName;
						}else{
							bookNameStr = res.data.borrowList[i].foreignName.length>40?res.data.borrowList[i].foreignName.substring(0,40)+'...':res.data.borrowList[i].foreignName;
							booknameTitle = res.data.borrowList[i].foreignName;
						}
						var authorStr = res.data.borrowList[i].author.length>10?res.data.borrowList[i].author.substring(0,10)+'...':res.data.borrowList[i].author;
						var createtimeStr = res.data.borrowList[i].createtime.replace(new RegExp(/-/gm) ,"/");
                         	createtimeStr = createtimeStr.slice(0,createtimeStr.indexOf("."));
                        var backtimeStr = res.data.borrowList[i].backtime.replace(new RegExp(/-/gm) ,"/");
                         	backtimeStr = backtimeStr.slice(0,backtimeStr.indexOf("."));
                     	var realbackStr = "";
                        if(res.data.borrowList[i].realback){
                        	realbackStr = res.data.borrowList[i].realback.replace(new RegExp(/-/gm) ,"/");
                        	realbackStr = realbackStr.slice(0,realbackStr.indexOf("."));
                        	realbackStr = new Date(realbackStr).format("yyyy-MM-dd");
                        }
						content += '<tr>'+
										'<td>'+foo(res.data.borrowList[i].beid)+'</td>'+
										'<td class="bookname" title="'+booknameTitle+'">'+bookNameStr+'</td>'+
										'<td title="'+res.data.borrowList[i].author+'">'+authorStr+'</td>'+
										'<td>'+new Date(createtimeStr).format("yyyy-MM-dd")+'</td>'+
										'<td>'+new Date(backtimeStr).format("yyyy-MM-dd")+'</td>'+
										'<td>'+realbackStr+'</td>'+
									'</tr>';
					}
				}
				$("#borrowed-tbody").html(content);
			}
		}
	})
}



