$(function(){
	//获取所有书目
	getAllBook(1);
	controlPage($("#page-num").val(),$("#total-page").text(),$("#first-page"),$("#pre-page"),$("#next-page"),$("#last-page"));

	//查询
	$("#queryBycondition").click(function(){
		getAllBook(1);
		controlPage($("#page-num").val(),$("#total-page").text(),$("#first-page"),$("#pre-page"),$("#next-page"),$("#last-page"));
	})

	//回车查询
	// $(document).keyup(function(e){
	// 	if(e.keyCode == 13){
	// 		$("#queryBycondition").click();
	// 	}
	// })
	$(document).keyup(function(e){
		var keycode;
		if(window.event){
			keycode = e.keyCode;
		}else if(e.which){
			keycode = e.which;
		}
		if(keycode ==13){
			if($("#page-num").is(":focus")){
				var page = parseInt($("#page-num").val());
				if(!page || page>parseInt($("#total-page").text()) || page<=0){
					alert("输入的页数不对");
					return false;
				}
				getAllBook(page);
				controlPage($("#page-num").val(),$("#total-page").text(),$("#first-page"),$("#pre-page"),$("#next-page"),$("#last-page"));
			}else{
				$("#queryBycondition").click();
			}
		}
	});

	//分页
	$("#first-page").click(function(){
		getAllBook(1);
		controlPage($("#page-num").val(),$("#total-page").text(),$("#first-page"),$("#pre-page"),$("#next-page"),$("#last-page"));
	})
	$("#pre-page").click(function(){
		if(!parseInt($("#page-num").val()) || parseInt($("#page-num").val())>parseInt($("#total-page").text()) || parseInt($("#page-num").val())<=1){
			alert('请保证当前页在合理范围内');
			return false;
		}
		var page = parseInt($("#page-num").val())-1;
		getAllBook(page);
		controlPage($("#page-num").val(),$("#total-page").text(),$("#first-page"),$("#pre-page"),$("#next-page"),$("#last-page"));
	})
	$("#next-page").click(function(){
		if(!parseInt($("#page-num").val()) || parseInt($("#page-num").val())>parseInt($("#total-page").text()-1) || parseInt($("#page-num").val())<=0){
			alert('请保证当前页在合理范围内');
			return false;
		}
		var page = parseInt($("#page-num").val())+1;
		getAllBook(page);
		controlPage($("#page-num").val(),$("#total-page").text(),$("#first-page"),$("#pre-page"),$("#next-page"),$("#last-page"));
	})
	$("#last-page").click(function(){
		var page = $("#total-page").text();
		getAllBook(page);
		controlPage($("#page-num").val(),$("#total-page").text(),$("#first-page"),$("#pre-page"),$("#next-page"),$("#last-page"));
	})
	// $("#page-num").keyup(function(e){
	// 	if(e.keyCode == 13){
	// 		var page = parseInt($("#page-num").val());
	// 		if(!page || page>parseInt($("#total-page").text()) || page<=0){
	// 			alert("输入的页数不对");
	// 			return false;
	// 		}
	// 		getAllBook(page);
	// 		controlPage($("#page-num").val(),$("#total-page").text(),$("#first-page"),$("#pre-page"),$("#next-page"),$("#last-page"));
	// 	}
	// })
})

//获取所有书目
function getAllBook(page){
	var content = "";
	var name = $("#search-key").val();
	var sortBy = $("#sortBy option:selected").val();
	var sortType = $("input[name='sortType']:checked").val();
	var data = {name:name,sidx:sortBy,sord:sortType,rows:10,page:page};
	$.ajax({
		async: false,
		type: 'POST',
		url: '/search',
		dataType: 'json',
    	data: data,
    	success: function(res){
    		//console.log(res);
    		if(res.status == 0){
    			var totalPage = Math.ceil(res.data.count/10);
    			$("#page-num").val(res.data.page); //当前页
    			$("#totalNum").text(res.data.count); //总记录数
    			$("#total-page").text(totalPage); //总页数
    			if(res.data.bookList && res.data.bookList.length>0){
    				var bookList = res.data.bookList;
    				for(var i in bookList){
    					var booknameStr = "",booknameTitle="";
    					if(bookList[i].name){
							booknameStr = bookList[i].name.length>25?bookList[i].name.substring(0,25)+'...':bookList[i].name;
							booknameTitle = bookList[i].name;
						}else{
							booknameStr = bookList[i].foreignName.length>40 ? bookList[i].foreignName.substr(0,40)+'...' : bookList[i].foreignName;
							booknameTitle = bookList[i].foreignName;
						}
    					var authorStr = bookList[i].author.length>10?bookList[i].author.substring(0,10)+'...':bookList[i].author;
    					var pubtimeStr = bookList[i].pubtime?bookList[i].pubtime:'';
    					var claimNumberStr = bookList[i].claimNumber?bookList[i].claimNumber:'';
    					//var countStr = bookList[i].count?bookList[i].count:'0';
    					//var createtimeStr = bookList[i].createtime?new Date(bookList[i].createtime).format("yyyy-MM-dd"):'';
    					content += '<tr>'+
										'<td title="'+booknameTitle+'"><a class="bookname" href="/bookQuery/bookQueryListDetail?bid='+bookList[i].bid+'">'+booknameStr+'</a></td>'+
										'<td title="'+bookList[i].author+'">'+authorStr+'</td>'+
										'<td>'+bookList[i].publisher+'</td>'+
										'<td>'+pubtimeStr+'</td>'+
										'<td>'+claimNumberStr+'</td>'+
										// '<td>'+countStr+'</td>'+
										// '<td>'+createtimeStr+'</td>'+
										'<td>'+
											'<form id="uploadForm">'+
												// '<input type="hidden" value="'+bookList[i].bid+'">'+
												'<div>'+
													'<div class="beautyUpload">'+
														'<input type="file" name="file" onchange="doUpload(this,'+bookList[i].bid+')">'+
														'上传封面'+
													'</div>'+
												'</div>'+
											'</form>'+
										'</td>'+
									'</tr>';
    				}
    				$(".wrap tbody").html(content);
    			}
    		}
    	}
    })
}

function doUpload(file,bid) {
	var formData=new FormData(); 
	formData.append("bid" , bid); 
	formData.append("file" , file.files[0]);
    // var formData = new FormData($( "#uploadForm" )[0]); 
     $.ajax({  
          url: '/uploadImg',  
          type: 'POST',  
          data: formData,  
          async: false,  
          cache: false,  
          contentType: false,  
          processData: false,  
          success: function (returndata) {  
              console.log(returndata);  
              if(returndata.status == 0){

              }
              alert(returndata.msg);
          },  
          error: function (returndata) {  
              console.log(returndata);  
          }  
     });  
}

// function imgupload(files,bid){
// 	if(!/image\/\w+/.test(files.files[0].type)){alert('只能上传图片');return;}
//     if(files.files[0].size>1048576){alert('单张图片大小不能大于1M');return;}
// 	var reader = new FileReader();//新建一个FileReader
// 	reader.readAsDataURL(files.files[0]);
//     reader.onload = function(evt){ //读取完文件之后会回来这里
//         var fileString = evt.target.result; // 读取文件内容
//         $.ajax({
//             type:'post',
//             url:"/uploadImg",
//             data: {bid:bid,file:fileString},
//             dataType:'json',
//             success:function(res){
//             	console.log(res);
//                 if(res.status == 0){

//                 }
//                 alert(res.msg);
//             }
//         })
//     }
// }

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