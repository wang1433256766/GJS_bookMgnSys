$(function(){
	$("#card").focus();
	//回车借书
	$(document).keyup(function(e){
		var keycode;
		if(window.event){
			keycode = e.keyCode;
		}else if(e.which){
			keycode = e.which;
		}
		if(keycode ==13){
			//console.log($("#card").is(":focus"));
			//console.log($('#barcard').is(":focus"));
			if($("#card").is(":focus")){
				var card = $("#card").val();
				if(!card){
					alert("card不能为空");return false;
				}
		   		userInfo(card,0);
			}else if($('#barcard').is(":focus")){
				getBookInfo();
			}else{
				if(!$("#borrow").prop("disabled")){
					$("#borrow").click();
				}
			}
		}
	});
	
	//借书操作
	$("#borrow").click(function(){
		var beid = $("#b-beid").val();
		var book_status = $("#b-bestatus").val();
		if(book_status == 1){
			//alert("此书已借出");
			window.wxc.xcConfirm("此书已借出，请检查条形码", window.wxc.xcConfirm.typeEnum.error); 
			return false;
		}else if(book_status == 2){
			window.wxc.xcConfirm("此书已损坏，请检查条形码", window.wxc.xcConfirm.typeEnum.error); 
			return false;
		}else if(book_status == 3){
			window.wxc.xcConfirm("此书已预约，请检查条形码", window.wxc.xcConfirm.typeEnum.error); 
			return false;
		}else if(book_status == 4){
			window.wxc.xcConfirm("此书未审核，请检查条形码", window.wxc.xcConfirm.typeEnum.error); 
			return false;
		}else if(book_status == 5){
			window.wxc.xcConfirm("此书已遗失，请检查条形码", window.wxc.xcConfirm.typeEnum.error); 
			return false;
		}else if(book_status == 6){
			window.wxc.xcConfirm("此书已剔旧，请检查条形码", window.wxc.xcConfirm.typeEnum.error); 
			return false;
		}else if(book_status == 7){
			window.wxc.xcConfirm("此书在装订修补，请检查条形码", window.wxc.xcConfirm.typeEnum.error); 
			return false;
		}else if(book_status == 8){
			window.wxc.xcConfirm("此书疑似遗失，请检查条形码", window.wxc.xcConfirm.typeEnum.error); 
			return false;
		}
		var card = $("#ucard").text();
		$.ajax({
			type: 'POST',
			url: '/borrow',
			cache: false,
			dataType: 'json',
			data: {card:card,beid:beid},
			success: function(res){
				//console.log(res);
				if(res.status == 0){
					userInfo(card);
					$("#barcard").val("");
					$("#willborrow-tbody").html("");
					$("#borrow").addClass('hidden');
				}else if(res.status == 1){
					window.wxc.xcConfirm("书目找不到", window.wxc.xcConfirm.typeEnum.error);
				}else if(res.status == 2){
					window.wxc.xcConfirm("用户不存在", window.wxc.xcConfirm.typeEnum.error);
				}else if(res.status == 3){
					window.wxc.xcConfirm("用户状态有问题", window.wxc.xcConfirm.typeEnum.error);
				}else if(res.status == 4){
					window.wxc.xcConfirm("其他错误", window.wxc.xcConfirm.typeEnum.error);
				}else if(res.status == 5){
					window.wxc.xcConfirm("书目实体状态有误", window.wxc.xcConfirm.typeEnum.error);
				}else if(res.status == 6){
					window.wxc.xcConfirm("达到最大借阅数量", window.wxc.xcConfirm.typeEnum.error);
				}else if(res.status == 7){
					window.wxc.xcConfirm("借阅失败", window.wxc.xcConfirm.typeEnum.error);
				}
			}
		})
	})

	//点击修改
	$("#change-selfinfo").click(function(){
		$(".span-text").addClass('hidden');
		$(".input-text").removeClass('hidden');
		$("#change-selfinfo").addClass('hidden');
		$("#cancle-change").removeClass('hidden');
		$("#submit-change").removeClass('hidden');
	})

	//取消修改
	$("#cancle-change").click(function(){
		$(".input-text").addClass('hidden');
		$(".span-text").removeClass('hidden');
		$("#cancle-change").addClass('hidden');
		$("#submit-change").addClass('hidden');
		$("#change-selfinfo").removeClass('hidden');
	})

	//确认修改
	$("#submit-change").click(function(){
		var uid = $("#i-uid").val();
        var email = $("#i-uemail").val();
        var phone = $("#i-uphone").val();
        var tel = $("#i-utel").val();
        var number = $("#i-unumber").val();
        var department = $("#i-udepartment").val();
        var tutor = $("#i-ututor").val();
        var status = $("#i-ustatus option:selected").val();
        $.ajax({
            type: 'POST',
            url: '/userUpdate',
            dataType: 'json',
            data: {uid:uid,email:email,phone:phone,tel:tel,number:number,department:department,tutor:tutor,status:status},
            success: function(res){
                if(res.status == 0){
                	$("#cancle-change").click();
                    userInfo($("#card").val());
                }else if(res.status == 2){
                	window.wxc.xcConfirm("此用户有借还记录", window.wxc.xcConfirm.typeEnum.error);
                }else{
                	alert(res.msg);
                }
            }
        })
	})

});

//用户信息和用户已借图书信息 封装
function userInfo(card,flag){
	$.ajax({
   		type: 'GET',
   		url: '/getUserInfoByCard',
   		cache: false,
   		dataType: 'json',
   		data: {card:card},
   		success: function(res){
   			//console.log(res.data);
	         if(res.status == 0){
	         	$("#change-display").removeClass('hidden');
				$("#change-selfinfo").removeClass('hidden');
				var statusText = "";
				var user = res.data.user;
				if(user.status == 0){
					statusText = '可用';
				}else if(user.status == 1){
					statusText = '解除合同离所';
					if(flag==0){
						alert('已解除合同离所');
					}
				}else if(user.status == 2){
					statusText = '退休';
					if(flag==0){
					alert('已退休');}
				}else if(user.status == 3){
					statusText = '毕业离所';
					if(flag==0){
					alert('已毕业离所');}
				}else if(user.status == 4){
					statusText = '违规停用';
					if(flag==0){
					alert('已违规停用');}
				}else if(user.status == 99){
					statusText = '不可用';
					if(flag==0){
					alert('不可用');}
				}else{
					statusText = '其他原因停用';
					if(flag==0){
					alert('已其他原因停用');}
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

	               //$("#i-uname").val(user.uname);
	               //$("#i-uidcard").val(user.idcard);
	               //$("#i-ucard").val(user.card);
	               $("#i-uid").val(user.uid);//通过隐藏域获取uid
	               $("#i-ustatus").val(user.status);
	               $("#i-uemail").val(user.email);
	               $("#i-uphone").val(user.phone);
	               $("#i-utel").val(user.tel);
	               //$("#i-urole").val(user.roleStr);
	               $("#i-unumber").val(user.number);
	               // $("#i-ucreateTime").val(user.createTime);
	               // $("#i-uupdateTime").val(user.updateTime);
	               $("#i-udepartment").val(user.department);
	               $("#i-ututor").val(user.tutor);

	               $("#borrowed-tbody").html('');
	            var bookEntityList = res.data.borrowList;
	            var borrow_count = 0, over_count = 0;
	               if(bookEntityList && bookEntityList.length > 0){
	                  var borrowedHtml = "";
	                  var backtimeStrFormat = "";
	                  for(var i in bookEntityList){
	                  	var backtimeStr = "";
	                  	if(bookEntityList[i].backtime){
	                  		backtimeStr = bookEntityList[i].backtime.replace(new RegExp(/-/gm) ,"/");
	                  		backtimeStr = new Date(backtimeStr.slice(0,backtimeStr.indexOf("."))).format("yyyy-MM-dd");
	                  	}
	                     borrow_count++;
	                     //判断超期图书,超期用红色字显示
	                     if(backtimeStr < new Date()){
	                        over_count++;
	                        backtimeStrFormat = '<span class="enddate">'+backtimeStr+'</span>';
	                     }else{
	                        backtimeStrFormat = '<span class="">'+backtimeStr+'</span>';
	                     }
	                     var booknameStr = "",booknameTitle="";
			   				if(bookEntityList[i].bookName){
								booknameStr = bookEntityList[i].bookName.length>25?bookEntityList[i].bookName.substring(0,25)+'...':bookEntityList[i].bookName;
								booknameTitle = bookEntityList[i].bookName;
							}else{
								booknameStr = bookEntityList[i].foreignName.length>40?bookEntityList[i].foreignName.substring(0,40)+'...':bookEntityList[i].foreignName;
								booknameTitle = bookEntityList[i].foreignName;
							}
	                     var authorStr = bookEntityList[i].author.length>10?bookEntityList[i].author.substring(0,10)+'...':bookEntityList[i].author;
	                     var createtimeStr = "";
	                     if(bookEntityList[i].createtime){
	                     	createtimeStr = bookEntityList[i].createtime.replace(new RegExp(/-/gm) ,"/");
							createtimeStr = new Date(createtimeStr.slice(0,createtimeStr.indexOf("."))).format("yyyy-MM-dd");
						 }
	                     borrowedHtml += '<tr>'+
	                                          '<td>'+foo(bookEntityList[i].beid)+'</td>'+
	                                          '<td class="bookname" title="'+booknameTitle+'">'+booknameStr+'</td>'+
	                                          '<td title="'+bookEntityList[i].author+'">'+authorStr+'</td>'+
	                                          '<td>'+createtimeStr+'</td>'+
	                                          '<td>'+backtimeStrFormat+'</td>'+
	                                       '</tr>';
	                  }
	                  $("#borrowed-tbody").html(borrowedHtml);
	                  $("#borrowCount").text(borrow_count);
	                  $("#overCount").text(over_count);
	               }
	         }else{
   				$("#change-display").addClass('hidden');
   				$("#change-selfinfo").addClass('hidden');
   				$("#uname").text('');
   				$("#uemail").text('');
   				$("#ucard").text('');
   				$("#qrcode").text('');
   				$("#urole").text('');
   				$("#permitUser").text('');
   				$("#createTime").text('');
   				$("#unumber").text('');
   			}
   		}
   })
}

//获取图书信息
function getBookInfo(){
   var barCard = $("#barcard").val();
   $.ajax({
   		type: 'GET',
   		url: '/getBookInfo',
   		cache: false,
   		dataType: 'json',
   		data: {barCode:barCard},
   		success: function(res){
   			if(res.status == 0){
   				var statusStr = "";
   				var bookdata = res.data;
   				if(bookdata.status == 0){
   					statusStr = '<span>可借</span>';
   				}else if(bookdata.status == 1){
   					statusStr = '<span class="enddate">已借</span>';
   				}else if(bookdata.status == 2){
   					statusStr = '<span class="enddate">已损坏</span>';
   				}else if(bookdata.status == 3){
   					statusStr = '<span class="enddate">已预约</span>';
   				}else if(bookdata.status == 4){
   					statusStr = '<span class="enddate">未审核</span>';
   				}else if(bookdata.status == 5){
   					statusStr = '<span class="enddate">已遗失</span>';
   				}else if(bookdata.status == 6){
   					statusStr = '<span class="enddate">剔旧</span>';
   				}else if(bookdata.status == 7){
   					statusStr = '<span class="enddate">装订修补</span>';
   				}else{
   					statusStr = '<span class="enddate">疑似遗失</span>';
   				}
   				$("#b-beid").val(bookdata.barcode);
   				$("#b-bestatus").val(bookdata.status);
   				// beid = bookdata.barcode;
   				// book_status = bookdata.status;
   				var booknameStr = "",booknameTitle="";
   				if(bookdata.book.name){
					booknameStr = bookdata.book.name.length>25?bookdata.book.name.substring(0,25)+'...':bookdata.book.name;
					booknameTitle = bookdata.book.name;
				}else{
					booknameStr = bookdata.book.foreignName.length>40?bookdata.book.foreignName.substring(0,40)+'...':bookdata.book.foreignName;
					booknameTitle = bookdata.book.foreignName;
				}
   				var authorStr = bookdata.book.author.length>10?bookdata.book.author.substring(0,10)+'...':bookdata.book.author;
   				var willborrowHtml = '<tr>'+
						   				'<td>'+foo(bookdata.barcode)+'</td>'+
						   				'<td class="bookname" title="'+booknameTitle+'">'+booknameStr+'</td>'+
						   				'<td title="'+bookdata.book.author+'">'+authorStr+'</td>'+
						   				'<td>'+bookdata.book.isbn+'</td>'+
						   				'<td>'+statusStr+'</td>'+
					   				'</tr>';
				$("#willborrow-tbody").html(willborrowHtml);
				$("#borrow").removeClass('hidden');
				$("#borrow").prop('disabled',false);
   			}else{
   				alert(res.msg);
   				$("#willborrow-tbody").html("");
   				$("#borrow").addClass('hidden');
   			}
   		}
   })
}