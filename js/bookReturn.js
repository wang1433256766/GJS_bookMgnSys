$(function(){
	$("#barcard").focus();
	//还书并获取图书和用户信息：
	$('#barcard').keyup(function(e){
		if(e.keyCode==13){
		   var barCode = $("#barcard").val();
		   if(!barCode){
		   		alert('条形码必填');
		   		return false;
		   }
		   $.ajax({
		   		async: false,
		   		type: 'POST',
		   		url: '/back',
		   		dataType: 'json',
		   		data: {barCode:barCode},
		   		success: function(res){
		   			console.log(res);
		   			if(res.status == 0){
		   				$("#re-barcode").text(res.data.backReturn.barcode);
		   				$("#re-bookname").text(res.data.backReturn.bookName);
		   				$("#change-display").removeClass('hidden');
		   				var user = res.data.userInfo;
			   				if(user.status == 0){
								statusText = '可用';
							}else if(user.status == 1){
								statusText = '解除合同离所';
							}else if(user.status == 2){
								statusText = '退休';
							}else if(user.status == 3){
								statusText = '毕业离所';
							}else if(user.status == 4){
								statusText = '违规停用';
							}else{
								statusText = '其他原因停用';
							}
		   					$("#ustatus").text(statusText);
			                $("#qrcode").text(user.qrCode==null?'':user.qrCode);
			                $("#uname").text(user.uname==null?'':user.uname);
			                $("#uidcard").text(user.idcard==null?'':user.idcard);
			                $("#uemail").text(user.email==null?'':user.email);
			                $("#ucard").text(user.card==null?'':user.card);
			                $("#uphone").text(user.phone==null?'':user.phone);
			                $("#utel").text(user.tel==null?'':user.tel);
			                $("#urole").text(user.roleStr==null?'':user.roleStr);
			                $("#unumber").text(user.number==null?'':user.number);
			                $("#ucreateTime").text(user.createTime==null?'':user.createTime);
			                $("#uupdateTime").text(user.updateTime==null?'':user.updateTime);
			                $("#udepartment").text(user.department==null?'':user.department);
			                $("#ututor").text(user.tutor==null?'':user.tutor);
		   				var bookEntityList = res.data.borrowList;
		   				if(bookEntityList && bookEntityList.length > 0){
		   					var borrowedHtml = "";
		   					var backtimeStrFormat = "";
		   					for(var i in bookEntityList){
		   						var backtimeStr = bookEntityList[i].backtime.replace(new RegExp(/-/gm) ,"/");
	                  				backtimeStr = backtimeStr.slice(0,backtimeStr.indexOf("."));
		   						//判断超期图书,超期用红色字显示
			                    if(new Date(backtimeStr) < new Date()){
			                       backtimeStrFormat = '<span class="enddate">'+new Date(backtimeStr).format("yyyy-MM-dd")+'</span>';
			                    }else{
			                       backtimeStrFormat = '<span class="">'+new Date(backtimeStr).format("yyyy-MM-dd")+'</span>';
			                    }
			                    //var booknameStr = bookEntityList[i].bookName.length>25?bookEntityList[i].bookName.substring(0,25)+'...':bookEntityList[i].bookName;	
			                    var booknameStr = "",booknameTitle="";
			                        if(bookEntityList[i].bookName){
			                        booknameStr = bookEntityList[i].bookName.length>25?bookEntityList[i].bookName.substring(0,25)+'...':bookEntityList[i].bookName;
			                        booknameTitle = bookEntityList[i].bookName;
			                     }else{
			                        booknameStr = bookEntityList[i].foreignName.length>40?bookEntityList[i].foreignName.substring(0,40)+'...':bookEntityList[i].foreignName;
			                        booknameTitle = bookEntityList[i].foreignName;
			                     }
			                    var authorStr = bookEntityList[i].author.length>10?bookEntityList[i].author.substring(0,10)+'...':bookEntityList[i].author;		   						
	   							var createtimeStr = bookEntityList[i].createtime.replace(new RegExp(/-/gm) ,"/");
							 		createtimeStr = createtimeStr.slice(0,createtimeStr.indexOf("."));
	   							borrowedHtml += '<tr>'+
		                                          '<td>'+foo(bookEntityList[i].beid)+'</td>'+
		                                          '<td class="bookname" title="'+booknameTitle+'">'+booknameStr+'</td>'+
		                                          '<td title="'+bookEntityList[i].author+'">'+authorStr+'</td>'+
		                                          '<td>'+new Date(createtimeStr).format("yyyy-MM-dd")+'</td>'+
		                                          '<td>'+backtimeStrFormat+'</td>'+
		                                       '</tr>';
			   					
			   				}
			   				$("#borrowed-tbody").html(borrowedHtml);
		   				}
		   				$("#barcard").val('');
		   				$("#barcard").focus();
		   			}else{
		   				alert(res.msg);
		   				$("#change-display").addClass('hidden');
		   			}
		   		}
		   })
		}
	})

})