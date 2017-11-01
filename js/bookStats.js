$(function(){
	//获取所有批次号
	$.ajax({
		async: false,
		type: 'GET',
		url: '/getAllBatchInfo',
		dataType: 'json',
		data: '',
		success: function(res){
			var optionHtml = "";
			for(var i in res.data){
    			optionHtml += '<option value="'+res.data[i].biid+'">'+res.data[i].mark+'</option>';
    		}
    		$("#queryKey").html(optionHtml);
		}
	})

	//获取某一批次录入的所有图书实体
	getBookEntityByBatch();

	//通过批次查询
	$("#queryBtn").click(function(){
		getBookEntityByBatch();
	})

	$(document).keyup(function(e){
		if(e.keyCode == 13){
			$("#queryBtn").click();
		}
	})

	//导出
	$("#exportExecl").click(function(){
		var selectBatch = $("#queryKey option:selected").val();
		window.location.href = "/downloadXlsx?batchId="+selectBatch;
	})
})

//获取某一批次录入的所有图书实体
function getBookEntityByBatch(){
	var selectBatch = $("#queryKey option:selected").val();
	$.ajax({
		type: 'GET',
		url: '/getBookByBatch',
		dataType: 'json',
		data: {batchId:selectBatch},
		success: function(res){
			//console.log(res);
			var content = "";
			if(res.status == 0){
				for(var i=0;i<res.data.length;i++){
					var nameStr = "",booknameTitle="";
					if(res.data[i].book.name){
						nameStr = res.data[i].book.name.length>20?res.data[i].book.name.substring(0,20)+'...':res.data[i].book.name;
						booknameTitle = res.data[i].book.name;
					}else{
						nameStr = res.data[i].book.foreignName.length>40 ? res.data[i].book.foreignName.substr(0,40)+'...' : res.data[i].book.foreignName;
						booknameTitle = res.data[i].book.foreignName;
					}
					var authorStr = res.data[i].book.author.length>10?res.data[i].book.author.substring(0,10)+'...':res.data[i].book.author;
					var statusStr = "";
					if(res.data[i].status == 0){
						statusStr = "可借";
					}else if(res.data[i].status == 1){
						statusStr = "已借";
					}else if(res.data[i].status == 2){
						statusStr = "损坏";
					}else if(res.data[i].status == 3){
						statusStr = "已预约";
					}else if(res.data[i].status == 4){
						statusStr = "未审核";
					}else if(res.data[i].status == 5){
						statusStr = "遗失";
					}else if(res.data[i].status == 6){
						statusStr = "剔旧";
					}else if(res.data[i].status == 7){
						statusStr = "装订修补";
					}else if(res.data[i].status == 8){
						statusStr = "疑似遗失";
					}
					var dataStr = "";
					if(res.data[i].bmTime){
						dataStr = res.data[i].bmTime.replace(new RegExp(/-/gm) ,"/");
						dataStr = new Date(dataStr.slice(0,dataStr.indexOf("."))).format('yyyy-MM-dd');
					}
					content += '<tr>'+
						'<td style="color:#0000ff;" title="'+booknameTitle+'">'+nameStr+'</td>'+
						'<td title="'+res.data[i].book.author+'">'+authorStr+'</td>'+
						'<td>'+res.data[i].book.publisher+'</td>'+
						'<td>'+res.data[i].book.price+'</td>'+
						'<td>'+res.data[i].book.pubtime+'</td>'+
						'<td>'+res.data[i].book.claimNumber+'</td>'+
						'<td>'+foo(res.data[i].barcode)+'</td>'+
						'<td>'+dataStr+'</td>'+
						'<td>'+statusStr+'</td>'+
					'</tr>';
				}
				$("tbody").html(content);
			}
		}
	})
}

//new Date(res.data[i].bmTime).format('yyyy-MM-dd')