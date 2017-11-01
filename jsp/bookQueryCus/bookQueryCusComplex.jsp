<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>书目检索</title>
	<link href="../../public/css/common.css" rel="stylesheet" type="text/css" />
	<link href="../../public/css/nav-customer.css" rel="stylesheet" type="text/css" />
	<link href="../../public/css/bookQueryComplex.css" rel="stylesheet" type="text/css" />
	<link href="../../public/css/footer.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<jsp:include  page="../nav-customer.jsp"/>
	<div class="wrap">
		<img src="../../public/images/home-1_03.png" />
		<div class="main-wrap">
			<div class="row">
				<div class="col-md-6">
					<div class="text-left"><label>题名：</label></div>
					<div class="content-right"><input type="text" id="query-name" placeholder="请输入题名"></div>
				</div>
				<div class="col-md-6">
					<div class="text-left"><label>出版社：</label></div>
					<div class="content-right"><input type="text" id="query-publisher" placeholder="请输入出版社"></div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="text-left"><label>作者：</label></div>
					<div class="content-right"><input type="text" id="query-person" placeholder="请输入责任人"></div>
				</div>
				<div class="col-md-6">
					<div class="text-left"><label>ISBN：</label></div>
					<div class="content-right"><input type="text" id="query-isbn" placeholder="请输入ISBN"></div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="text-left"><label>丛书名：</label></div>
					<div class="content-right"><input type="text" id="query-bookname" placeholder="请输入丛书名"></div>
				</div>
				<div class="col-md-6">
					<div class="text-left"><label>索书号：</label></div>
					<div class="content-right"><input type="text" id="query-claim" placeholder="请输入索书号"></div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="text-left"><label>出版年：</label></div>
					<div class="content-right"><input type="text" id="query-pubtime" placeholder="请输入出版年"></div>
				</div>
			</div>
			<div class="row-btn">
				<button id="btn-query" type="button">检索</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button id="btn-reset" type="button">重置</button>
			</div>
		</div>
	</div>
	<div class="footer">
		Copyright &copy; 2017 中国科学院上海光学精密机械研究所
	</div>
<script src="../../public/js/jquery.min.js"></script>
<script src="../../public/js/nav-customer.js"></script>
</body>
</html>
<script>
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
		window.location.href = "/bookQueryCus/bookQueryCusList?queryName="+encodeURI(queryName)+"&queryPublisher="+encodeURI(queryPublisher)+"&queryPerson="+encodeURI(queryPerson)+"&queryIsbn="+encodeURI(queryIsbn)+"&queryBookname="+encodeURI(queryBookname)+"&queryClaim="+encodeURI(queryClaim)+"&queryPubtime="+encodeURI(queryPubtime);
	})

	//回车检索
	$(document).keydown(function(event) {
	    if (event.keyCode == "13") {//keyCode=13是回车键
	    	$('#btn-query').click();
	    }
	});
</script>