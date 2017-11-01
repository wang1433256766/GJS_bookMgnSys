//获取同步结果
var int = setInterval("getSynRes()", 5000);

$(function(){

	//用户同步
	$("#user-sync").click(function(){
		$.ajax({
			type: 'POST',
			url: '/synUser',
			dataType: 'json',
			data: {},
			success: function(res){
				int = setInterval("getSynRes()", 5000);
			}
		})
	})
})

//获取用户同步结果
function getSynRes(){
	$.ajax({
		type: 'GET',
		url: '/getSynRes',
		dataType: 'json',
		data: {},
		success: function(res){
			var userContent = "",borrowContent = "";
			if(res.data.synStatus == 0){
				$("#user-sync").attr('disabled', true);
				$("#sync-info").text('正在同步中...');
			}
			else{
				clearInterval(int); //清除定时器
				$("#user-sync").attr('disabled', false);
				$("#sync-info").text('');
			}
			if(res.data.addUserList.length>0){
				for(var i=0; i<res.data.addUserList.length; i++){
					userContent += '<tr>'+
						    			'<td>'+res.data.addUserList[i].uname+'</td>'+
						    			'<td>'+res.data.addUserList[i].idcard+'</td>'+
						    			'<td>'+res.data.addUserList[i].card+'</td>'+
						    			'<td>'+res.data.addUserList[i].phone+'</td>'+
						    			'<td>'+res.data.addUserList[i].department+'</td>'+
						    			'<td style="color:green;">新增</td>'+
						    		'</tr>';
				}
			}
			if(res.data.deleteUserList.length>0){
				for(var j=0; j<res.data.deleteUserList.length; j++){
					userContent += '<tr>'+
						    			'<td>'+res.data.deleteUserList[j].uname+'</td>'+
						    			'<td>'+res.data.deleteUserList[j].idcard+'</td>'+
						    			'<td>'+res.data.deleteUserList[j].card+'</td>'+
						    			'<td>'+res.data.deleteUserList[j].phone+'</td>'+
						    			'<td>'+res.data.deleteUserList[j].department+'</td>'+
						    			'<td style="color:red;">删除</td>'+
						    		'</tr>';
				}
			}
			if(res.data.updateUserList.length>0){
				for(var k=0; k<res.data.updateUserList.length; k++){
					userContent += '<tr>'+
						    			'<td>'+res.data.updateUserList[k].uname+'</td>'+
						    			'<td>'+res.data.updateUserList[k].idcard+'</td>'+
						    			'<td>'+res.data.updateUserList[k].card+'</td>'+
						    			'<td>'+res.data.updateUserList[k].phone+'</td>'+
						    			'<td>'+res.data.updateUserList[k].department+'</td>'+
						    			'<td style="color:yellow;">更新</td>'+
						    		'</tr>';
				}
			}
			if(res.data.borrowList.length>0){
				for(var a=0; a<res.data.borrowList.length; a++){
					borrowContent += '<tr>'+
						    			'<td>'+res.data.borrowList[a].bookName+'</td>'+
						    			'<td>'+res.data.borrowList[a].author+'</td>'+
						    			'<td>'+res.data.borrowList[a].userName+'</td>'+
						    		 '</tr>';
				}
			}
			$("#userChangeInfo").html(userContent);
			$("#userBorrowInfo").html(borrowContent);
		}
	})
}
	