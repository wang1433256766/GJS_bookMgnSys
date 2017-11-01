<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>图书录入</title>
	<link href="../../public/css/common.css" rel="stylesheet" type="text/css" />
	<link href="../../public/css/nav.css" rel="stylesheet" type="text/css" />
	<link href="../../public/css/bookList.css" rel="stylesheet" type="text/css" />
	<link href="../../public/css/footer.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<jsp:include  page="../nav.jsp"/>
	<div class="wrap">
		<section class="section">
			<div class="part-1">
				<p class="introinfo"><img src="../../public/images/bookout_06.png">&nbsp;图书信息</p>
				<!-- <p class="text">馆藏图书【<span>0</span>】本，已借阅图书【<span>0</span>】本，剩余图书【<span>0</span>】本</p> -->
				<p class="text">
					<!-- <select name="" id="" style="width:80px;height:22px;">
						<option value="">书名</option>
					</select> -->
					书名：
					<input type="text" id="query-key" placeholder="请输入关键字" style="width:390px;">
					<button type="button" id="query-btn">查&nbsp;询</button>
				</p>
			</div>
			<div class="part-2">
				<button type="button" id="bookinsert">图书录入</button>
			</div>
			<table>
				<thead>
					<tr>
						<th>书名</th>
						<th>责任者</th>
						<th>出版信息</th>
						<th>出版年</th>
						<th>索书号</th>
						<th>馆藏</th>
						<th>录入时间</th>
					</tr>
				</thead>
				<tbody>
					
				</tbody>
			</table>
			<div class="selfPage hidden">
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
		</section>
	</div>
	<div class="footer">
		Copyright &copy; 2017 中国科学院上海光学精密机械研究所
	</div>
<script src="../../public/js/jquery.min.js"></script>
<script src="../../public/js/nav.js"></script>
<script src="../../public/js/bookList.js"></script>
</body>
</html>