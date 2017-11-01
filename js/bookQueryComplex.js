$(function(){
	//重置输入框
	$("#btn-reset").click(function(){
		$("#query-name").val('');
		$("#query-publisher").val('');
		$("#query-person").val('');
		$("#query-isbn").val('');
		$("#query-bookname").val('');
		$("#query-claim").val('');
		$("#query-pubtime").val('');
	})

	//检索按钮跳页面
	$("#btn-query").click(function(){
		var queryName = $("#query-name").val(),
			queryPublisher = $("#query-publisher").val(),
			queryPerson = $("#query-person").val(),
			queryIsbn = $("#query-isbn").val(),
			queryBookname = $("#query-bookname").val(),
			queryClaim = $("#query-claim").val(),
			queryPubtime = $("#query-pubtime").val();
		if(queryName.trim()=="" && queryPublisher.trim()=="" && queryPerson.trim()=="" && queryIsbn.trim()=="" && queryBookname.trim()=="" && queryClaim.trim()=="" && queryPubtime.trim()==""){
			alert("请输入检索关键字");
			return false;
		}
		window.location.href = "/bookQuery/bookQueryList?queryName="+encodeURI(queryName)+"&queryPublisher="+encodeURI(queryPublisher)+"&queryPerson="+encodeURI(queryPerson)+"&queryIsbn="+encodeURI(queryIsbn)+"&queryBookname="+encodeURI(queryBookname)+"&queryClaim="+encodeURI(queryClaim)+"&queryPubtime="+encodeURI(queryPubtime);
	})

	//回车检索
	$("body").keydown(function() {
	    if (event.keyCode == "13") {//keyCode=13是回车键
	    	$('#btn-query').click();
	    }
	});
})