<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>书目检索详情</title>
	<link href="../../public/css/common.css" rel="stylesheet" type="text/css" />
	<link href="../../public/css/nav-customer.css" rel="stylesheet" type="text/css" />
	<link href="../../public/css/bookQueryListDetail.css" rel="stylesheet" type="text/css" />
	<link href="../../public/css/footer.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<jsp:include  page="../nav-customer.jsp"/>
	<div class="wrap">
		<div class="wrap-top">
			<div class="part-1">
				<div class="book-cover">
					<img src="../../public/images/book_pictrue.png">
				</div>
			</div>
			<div class="part-2">
				<h2><span id="bookName"></span></h2>
				<h3><span id="foreignName"></span></h3>
				<p>作者：<span id="bookAuthor"></span></p>
				<p>出版社：<span id="bookPublish"></span></p>
				<p>出版年：<span id="bookYear"></span></p>
				<p class="pri_ts">价格：<span id="bookPrice"></span></p>
				<p class="ser_ts">丛书：<span id="seriesBook"></span></p>
				<p>ISBN：<span id="bookIsbn"></span></p>
				<p>分类/种次号：<span id="bookCategory"></span></p>
				<p>图书语种：<span id="bookLanguge"></span></p>
				<p>图书页数：<span id="bookPage"></span></p>
				<p>中文摘要：<span id="bookContent"></span></p>
				<span id="bookUrl"></span>
			</div>
			<div class="part-3">
				<p>馆藏&nbsp;<span style="color:#f30c0c;" id="bookTotal"></span>&nbsp;本</p>
				<p>可借&nbsp;<span style="color:#f30c0c;" id="bookUsable"></span>&nbsp;本</p>
				<p><button id="focusBook" class="hidden">关&nbsp;注</button>&nbsp;<button onclick="window.history.back()">返&nbsp;回</button></p>
			</div>
		</div>
		<table>
			<thead>
				<tr>
					<!-- <th>编号</th> -->
					<th>条形码</th>
					<th>书籍状态</th>
					<th>借阅人</th>
					<!-- <th>归还期限</th> -->
				</tr>
			</thead>
			<tbody>
				
			</tbody>
		</table>
	</div>
	<div class="footer">
		Copyright &copy; 2017 中国科学院上海光学精密机械研究所
	</div>
<script src="../../public/js/jquery.min.js"></script>
<script src="../../public/js/nav-customer.js"></script>
<script src="../../public/js/bookQueryListDetail.js"></script>
</body>
</html>