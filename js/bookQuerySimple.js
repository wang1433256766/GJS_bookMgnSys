$(function(){
	$("#btn-query").click(function(){
		//var queryChoice = $("#query-choice option:selected").text();
		var queryKey = $("#query-key").val();
		if(queryKey.trim()==""){
			alert("请输入检索关键字！");
			return false;
		}
		window.location.href = "/bookQuery/bookQueryList?queryKey="+encodeURI(queryKey);
	})

	//回车检索
	$(document).keydown(function(event) {
	    if (event.keyCode == "13") {//keyCode=13是回车键
	    	$('#btn-query').click();
	    }
	});

	//获取热门图书
	$.ajax({
		type: 'GET',
		url: '/getHotBook',
		dataType: 'json',
		data: '',
		success: function(res){
			console.log(res);
			var content = "";
			for(var i=0; i<res.data.length; i++){
				if(res.data[i].name){
					var bookNameStr = res.data[i].name.length>14?res.data[i].name.substring(0,14)+"...":res.data[i].name;
					content += '<li><a href="/bookQuery/bookQueryListDetail?bid='+encodeURI(res.data[i].bid)+'" title="'+res.data[i].name+'">'+bookNameStr+'</a></li>';
				}else{
					var foreignNameStr = res.data[i].foreignName.length>15?res.data[i].foreignName.substring(0,15)+"...":res.data[i].foreignName;
					content += '<li><a href="/bookQuery/bookQueryListDetail?bid='+encodeURI(res.data[i].bid)+'" title="'+res.data[i].foreignName+'">'+foreignNameStr+'</a></li>';
				}
			}
			$(".hotbook ul").html(content);
		}
	})

	//获取热门借阅
	$.ajax({
		type: 'GET',
		url: '/getHotBorrow',
		dataType: 'json',
		data: '',
		success: function(res){
			//console.log(res);
			var content = "";
			for(var i=0; i<res.data.length;i++){
				if(res.data[i].name){
					var bookNameStr = res.data[i].name.length>14?res.data[i].name.substring(0,14)+"...":res.data[i].name;
					content += '<li><a href="/bookQuery/bookQueryListDetail?bid='+encodeURI(res.data[i].bid)+'" title="'+res.data[i].name+'">'+bookNameStr+'</a></li>';
				}else{
					var foreignNameStr = res.data[i].foreignName.length>15?res.data[i].foreignName.substring(0,15)+"...":res.data[i].foreignName;
					content += '<li><a href="/bookQuery/bookQueryListDetail?bid='+encodeURI(res.data[i].bid)+'" title="'+res.data[i].foreignName+'">'+foreignNameStr+'</a></li>';
				}
			}
			$(".hotjy ul").html(content);
		}
	})

	//获取新书推荐
	$.ajax({
		type: 'GET',
		url: '/getNewBook',
		dataType: 'json',
		data: '',
		success: function(res){
			//console.log(res);
			var content = "";
			for(var i=0; i<res.data.length;i++){
				if(res.data[i].book.name){
					var bookNameStr = res.data[i].book.name.length>14?res.data[i].book.name.substring(0,14)+"...":res.data[i].book.name;
					content += '<li><a href="/bookQuery/bookQueryListDetail?bid='+encodeURI(res.data[i].book.bid)+'" title="'+res.data[i].book.name+'">'+bookNameStr+'</a></li>';
				}else{
					var foreignNameStr = res.data[i].book.foreignName.length>15?res.data[i].book.foreignName.substring(0,15)+"...":res.data[i].book.foreignName;
					content += '<li><a href="/bookQuery/bookQueryListDetail?bid='+encodeURI(res.data[i].book.bid)+'" title="'+res.data[i].book.foreignName+'">'+foreignNameStr+'</a></li>';
				}
			}
			$(".newbook ul").html(content);
		}
	})
})