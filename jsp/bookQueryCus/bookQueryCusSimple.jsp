<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>书目检索</title>
	<link href="../../public/css/common.css" rel="stylesheet" type="text/css" />
	<link href="../../public/css/nav-customer.css" rel="stylesheet" type="text/css" />
	<link href="../../public/css/bookQuerySimple.css" rel="stylesheet" type="text/css" />
	<link href="../../public/css/footer.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<jsp:include  page="../nav-customer.jsp"/>
	<div class="wrap">
		<div class="wrap-child-1">
			<img src="../../public/images/home-1_03.png" />
			<div class="main-wrap">
				<div class="row-1">
					<!-- <select name="" id="query-choice" style="width:80px;height:22px;">
						<option value="">书名</option>
						<option value="">作者</option>
					</select> -->
					书名：
					<input type="text" id="query-key" placeholder="请输入关键字" style="width:390px;">
				</div>
				<div class="row-2">
					<span style="font-size: 12px;"><strong>注：检索词间的空格代表and关系，单引号内检索词视为一个词组短语</strong></span>
					<!-- <input type="radio" name="booktype" checked><span>所有书刊</span>
					<input type="radio" name="booktype"><span>中文书刊</span>
					<input type="radio" name="booktype"><span>英文书刊</span> -->
				</div>
				<div class="row-3">
					<button id="btn-query">检索</button>
				</div>
			</div>
		</div>
		<div class="wrap-child-2">
			<div class="newbook">
				<p><img src="../../public/images/home-1_06.png">&nbsp;新书推荐</p>
				<ul>
					
				</ul>
			</div>
			<div class="hotbook">
				<p><img src="../../public/images/home-1_10.png">热门图书</p>
				<ul>
					
				</ul>
			</div>
			<div class="hotjy">
				<p><img src="../../public/images/home-1_10.png">热门借阅</p>
				<ul>
					
				</ul>
			</div>
		</div>
	</div>
	<div class="footer">
		Copyright &copy; 2017 中国科学院上海光学精密机械研究所
	</div>
<script src="../../public/js/jquery.min.js"></script>
<script src="../../public/js/nav-customer.js"></script>
<script>
	$(function(){
		$("#btn-query").click(function(){
			//var queryChoice = $("#query-choice option:selected").text();
			var queryKey = $("#query-key").val();
			if(queryKey.trim()==""){
				alert("请输入检索关键字！");
				return false;
			}
			window.location.href = "/bookQueryCus/bookQueryCusList?queryKey="+encodeURI(queryKey);
		})
		//回车检索
		$(document).keyup(function(event) {
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
				var content = "";
				for(var i=0; i<res.data.length; i++){
					if(res.data[i].name){
						var bookNameStr = res.data[i].name.length>14?res.data[i].name.substring(0,14)+"...":res.data[i].name;
						content += '<li><a href="/bookQueryCus/bookQueryCusListDetail?bid='+encodeURI(res.data[i].bid)+'" title="'+res.data[i].name+'">'+bookNameStr+'</a></li>';
					}else{
						var foreignNameStr = res.data[i].foreignName.length>15?res.data[i].foreignName.substring(0,15)+"...":res.data[i].foreignName;
						content += '<li><a href="/bookQueryCus/bookQueryCusListDetail?bid='+encodeURI(res.data[i].bid)+'" title="'+res.data[i].foreignName+'">'+foreignNameStr+'</a></li>';
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
				var content = "";
				for(var i=0; i<res.data.length;i++){
					if(res.data[i].name){
						var bookNameStr = res.data[i].name.length>14?res.data[i].name.substring(0,14)+"...":res.data[i].name;
						content += '<li><a href="/bookQueryCus/bookQueryCusListDetail?bid='+encodeURI(res.data[i].bid)+'" title="'+res.data[i].name+'">'+bookNameStr+'</a></li>';
					}else{
						var foreignNameStr = res.data[i].foreignName.length>15?res.data[i].foreignName.substring(0,15)+"...":res.data[i].foreignName;
						content += '<li><a href="/bookQueryCus/bookQueryCusListDetail?bid='+encodeURI(res.data[i].bid)+'" title="'+res.data[i].foreignName+'">'+foreignNameStr+'</a></li>';
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
						content += '<li><a href="/bookQueryCus/bookQueryCusListDetail?bid='+encodeURI(res.data[i].book.bid)+'" title="'+res.data[i].book.name+'">'+res.data[i].book.name+'</a></li>';
					}else{
						var foreignNameStr = res.data[i].book.foreignName.length>15?res.data[i].book.foreignName.substring(0,15)+"...":res.data[i].book.foreignName;
						content += '<li><a href="/bookQueryCus/bookQueryCusListDetail?bid='+encodeURI(res.data[i].book.bid)+'" title="'+res.data[i].book.foreignName+'">'+foreignNameStr+'</a></li>';
					}
				}
				$(".newbook ul").html(content);
			}
		})
	})
</script>
</body>
</html>