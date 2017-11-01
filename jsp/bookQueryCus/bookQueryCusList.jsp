<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>书目检索列表</title>
	<link href="../../public/css/common.css" rel="stylesheet" type="text/css" />
	<link href="../../public/css/nav-customer.css" rel="stylesheet" type="text/css" />
	<link href="../../public/css/bookQueryList.css" rel="stylesheet" type="text/css" />
	<link href="../../public/css/footer.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<jsp:include  page="../nav-customer.jsp"/>
	<div class="wrap">
		<p class="tip-text">查询到<span id="totalRecord"></span>条 <span>书名</span>含有<span id="keyValue"></span>的结果</p>
		<p>
			<select id="sortBy" style="width:82px;height:22px;font-size:12px;">
				<option value="b_pub_time" selected="selected">出版年</option>
				<option value="b_name">书名</option>
				<option value="b_author">作者</option>
				<option value="b_publisher">出版社</option>
			</select>
			<input type="radio" name="sortType" value="desc" checked="checked">降序
			<input type="radio" name="sortType" value="asc">升序
			<button class="button-style" id="reSort">重新排序</button>
		</p>
		<!-- <p class="radio-part">
			<input type="radio" name="booktype" checked><span>所有书刊</span>
			<input type="radio" name="booktype"><span>中文书刊</span>
			<input type="radio" name="booktype"><span>英文书刊</span>
		</p> -->
		<div class="tab">
			<ul>
				<a id="all-book"><li class="active-tab">所有图书</li></a>
				<!-- <a id="usable-book"><li>可借图书</li></a> -->
			</ul>
		</div>
		<div class="content">
			<ul>
			</ul>
			<div class="page">
				<input type="image" id="first-page" src="../../public/images/booklist-4.png" title="首页">
				<input type="image" id="pre-page" src="../../public/images/booklist-1.png" title="上一页">
				<!-- <img id="first-page" src="../../public/images/booklist-4.png" title="首页">
				<img id="pre-page" src="../../public/images/booklist-1.png" title="上一页"> -->
				<input id="page-num" type="text" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9]+/,'');}).call(this)" onblur="this.v();" style="width:30px;"/>&nbsp;共&nbsp;<span id="total-page"></span>&nbsp;页
				<input type="image" id="next-page" src="../../public/images/booklist-2.png" title="下一页">
				<input type="image" id="last-page" src="../../public/images/booklist-3.png" title="尾页">
				<!-- <img id="next-page" src="../../public/images/booklist-2.png" title="下一页">
				<img id="last-page" src="../../public/images/booklist-3.png" title="尾页"> -->
			</div>
		</div>
	</div>
	<div class="footer">
		Copyright &copy; 2017 中国科学院上海光学精密机械研究所
	</div>
<script src="../../public/js/jquery.min.js"></script>
<script src="../../public/js/nav-customer.js"></script>
<script src="../../public/js/bookQueryCusList.js"></script>
</body>
</html>