<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>书目列表</title>
	<link href="../../public/css/common.css" rel="stylesheet" type="text/css"/>
    <link href="../../public/css/nav.css" rel="stylesheet" type="text/css"/>
    <link href="../../public/css/bookListMgn.css" rel="stylesheet" type="text/css"/>
    <link href="../../public/css/footer.css" rel="stylesheet" type="text/css"/>
    <style type="text/css"> 
		.beautyUpload 
		{ 
			position: relative; 
			width: 90px; 
			height: 30px; 
			background-color: #53AD3F; 
			/*background-image: url(images/bg.png);*/ 
			background-repeat: no-repeat; 
			background-position: 0 0; 
			background-attachment: scroll; 
			line-height: 30px; 
			text-align: center; 
			color: white; 
			cursor: pointer; 
			overflow: hidden; 
			z-index: 1; 
		} 
		.beautyUpload input 
		{ 
			position: absolute; 
			width: 90px; 
			height: 31px; 
			line-height: 31px; 
			font-size: 23px; 
			opacity: 0; 
			filter: "alpha(opacity=0)"; 
			filter: alpha(opacity=0); 
			-moz-opacity: 0; 
			left: -5px; 
			top: -2px; 
			cursor: pointer; 
			z-index: 2; 
		} 
	</style>
</head>
<body>
	<jsp:include  page="../nav.jsp"/>
	<div class="wrap">
		<p class="introinfo"><img src="../public/images/bookout_06.png">&nbsp;书目列表</p>
		<p class="text">
            <!-- <select name="" id="search-option" style="width:80px;height:22px;">
                <option value="">书名</option>
            </select> -->
            书名：<input type="text" id="search-key" placeholder="请输入关键字" style="width:390px;">
        </p>
        <p class="text">
            <select name="" id="sortBy" style="width:80px;height: 22px">
				<option value="b_name" selected="selected">书名</option>
				<option value="b_author">责任者</option>
				<option value="b_publisher">出版信息</option>
				<option value="b_pub_time">出版年</option>
            </select>
            <input type="radio" name="sortType" value="desc" checked="checked">&nbsp;降序
            <input type="radio" name="sortType" value="asc">&nbsp;升序
            <button id="queryBycondition">查&nbsp;询</button>
        </p>
		<table>
			<thead>
				<tr>
					<th>书名</th>
					<th>责任者</th>
					<th>出版信息</th>
					<th>出版年</th>
					<th>索书号</th>
					<!-- <th>馆藏</th> -->
					<th></th>
				</tr>
			</thead>
			<tbody>
				
			</tbody>
		</table>
		<div class="selfPage">
            <input type="image" id="first-page" src="../../public/images/booklist-4.png" title="首页">
            <input type="image" id="pre-page" src="../../public/images/booklist-1.png" title="上一页">
            <!-- <img id="first-page" src="../../public/images/booklist-4.png" title="首页">
            <img id="pre-page" src="../../public/images/booklist-1.png" title="上一页"> -->
            <input id="page-num" type="text" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9]+/,'');}).call(this)" onblur="this.v();" style="width:30px;"/>&nbsp;共&nbsp;<span id="total-page"></span>&nbsp;页
            <input type="image" id="next-page" src="../../public/images/booklist-2.png" title="下一页">
            <input type="image" id="last-page" src="../../public/images/booklist-3.png" title="尾页">
            <!-- <img id="next-page" src="../../public/images/booklist-2.png" title="下一页">
            <img id="last-page" src="../../public/images/booklist-3.png" title="尾页"> -->
            <span class="countNum">总记录数为：<label id="totalNum"></label></span>
        </div>
	</div>
	<div class="footer">
		Copyright &copy; 2017 中国科学院上海光学精密机械研究所
	</div>
<script src="../../public/js/jquery.min.js"></script>
<script src="../../public/js/nav.js"></script>
<script src="../../public/js/bookListMgn.js"></script>
</body>
</html>