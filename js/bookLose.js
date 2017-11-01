$(function(){
	//回车查询
	$("#queryKey").keyup(function(e){
		var keycode;
		if(window.event){
			keycode = e.keyCode;
		}else if(e.which){
			keycode = e.which;
		}
		if(keycode == 13){
			$("#queryBtn").click();
		}
	})
	//查询图书实体
	$("#queryBtn").click(function(){
		var content = "";
		var queryKey = $("#queryKey").val().trim();
		if(!queryKey){
			return false;
		}
		$.ajax({
			type: 'POST',
			url: '/getAllBookentity',
			dataType: 'json',
			data: {bookName:queryKey,sidx:'be_updatetime',sord:'desc',rows:10,page:1},
			success: function(res){
				//console.log(res);
				if(res.status == 0){
					if(res.data.bookEntityList){
						var resultData = res.data.bookEntityList;
						for(var i in resultData){
							var statusStr = "";
							if(resultData[i].status == 0){
								statusStr = "可借";
							}else if(resultData[i].status == 1){
								statusStr = "已借";
							}else if(resultData[i].status == 2){
								statusStr = "损坏";
							}else if(resultData[i].status == 3){
								statusStr = "已预约";
							}else if(resultData[i].status == 4){
								statusStr = "未审核";
							}else if(resultData[i].status == 5){
								statusStr = "遗失";
							}else if(resultData[i].status == 6){
								statusStr = "剔旧";
							}else if(resultData[i].status == 7){
								statusStr = "装订修补";
							}else if(resultData[i].status == 8){
								statusStr = "疑似遗失";
							}
							var losterNameStr = resultData[i].losterName?resultData[i].losterName:'';
							var booknameStr = "",booknameTitle = "";
							if(resultData[i].book.name){
								booknameStr = resultData[i].book.name.length>25 ? resultData[i].book.name.substr(0,25)+'...' : resultData[i].book.name;
								booknameTitle = resultData[i].book.name;
							}else{
								booknameStr = resultData[i].book.foreignName.length>40 ? resultData[i].book.foreignName.substr(0,40)+'...' : resultData[i].book.foreignName;
								booknameTitle = resultData[i].book.foreignName;
							}
							var authorStr = resultData[i].book.author.length>10 ? resultData[i].book.author.substr(0,10)+'...' : resultData[i].book.author;
							content += '<tr>'+
											'<td><input type="checkbox" class="checkSingle" id="checkSingle_'+resultData[i].beid+'"></td>'+
											'<td class="bookname" title="'+booknameTitle+'">'+booknameStr+'</td>'+
											'<td title="'+resultData[i].book.author+'">'+authorStr+'</td>'+
											'<td>'+resultData[i].book.publisher+'</td>'+
											'<td>'+resultData[i].book.claimNumber+'</td>'+
											'<td>'+foo(resultData[i].barcode)+'</td>'+
											'<td>'+losterNameStr+'</td>'+
											'<td>'+statusStr+'</td>'+
										'</tr>';
						}
						$(".wrap tbody").html(content);
					}
				}
			}
		})
	})

	//全选
	$(".checkAll").click(function(){
		if($(".checkAll").is(':checked')){
            $(".checkSingle").prop('checked', true);
        }else{
            $(".checkSingle").prop('checked', false);
        }
	})

	//确定遗失
	$("#loseBtn").click(function(){
		loseOrDamage(5);
	})

	//确定损坏
	$("#damageBtn").click(function(){
		loseOrDamage(2);
	})

	//确定剔旧
	$("#replaceBtn").click(function(){
		loseOrDamage(6);
	})

	//装订修补
	$("#zdxbBtn").click(function(){
		loseOrDamage(7);
	})

	//疑似遗失
	$("#ysysBtn").click(function(){
		loseOrDamage(8);
	})

})

//遗失或损坏接口封装
function loseOrDamage(status){
	var beid = "";
    if($(".checkSingle").length>0){
        for(var i=0; i<$(".checkSingle").length; i++){
            if($(".checkSingle")[i].checked){
                beid += $(".checkSingle")[i].id.substring(12)+',';
            }
        }
    }
    if(beid == ""){
        alert("请选择要操作的行！"); return false;
    }
    beid = beid.substring(0,beid.length-1);
    $.ajax({
    	type: 'POST',
    	url: '/updateBook',
    	dataType: 'json',
    	data: {beidStr:beid,status:status},
    	success: function(res){
    		//console.log(res);
    		if(res.status == 0){
    			window.location.reload();
    		}
    		alert(res.msg);
    	}
    })
}
