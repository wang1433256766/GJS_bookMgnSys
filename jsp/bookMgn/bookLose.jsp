<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>图书遗失</title>
	<link href="../../public/css/common.css" rel="stylesheet" type="text/css" />
	<link href="../../public/css/nav.css" rel="stylesheet" type="text/css" />
	<link href="../../public/css/bookLose.css" rel="stylesheet" type="text/css" />
	<link href="../../public/css/footer.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<jsp:include  page="../nav.jsp"/>
	<div class="wrap">
		<section class="section">
			<div class="part-1">
				<p class="introinfo"><img src="../../public/images/bookout_06.png">&nbsp;图书遗失</p>
				<p class="text">
					<!-- <select name="" id="" style="width:80px;height:22px;">
						<option value="">书名</option>
					</select> -->
					书名：<input type="text" id="queryKey" placeholder="请输入关键字" style="width:390px;">
					<button type="button" id="queryBtn">查&nbsp;询</button>
				</p>
			</div>
			<div class="part-2">
				<a href="/bookMgn/bookLoseList">图书遗失列表</a>
			</div>
			<table>
				<thead>
					<tr>
						<th><input type="checkbox" class="checkAll"></th>
						<th>书名</th>
						<th>责任者</th>
						<th>出版信息</th>
						<th>索书号</th>
						<th>条形码</th>
						<th>遗失(损坏)人</th>
						<th>状态</th>
					</tr>
				</thead>
				<tbody>
					
				</tbody>
			</table>
			<div class="row-button">
				<button type="button" id="loseBtn">确定遗失</button>&nbsp;&nbsp;
				<button type="button" id="damageBtn">确定损坏</button>&nbsp;&nbsp;
				<button type="button" id="replaceBtn">确定剔旧</button>&nbsp;&nbsp;
				<button type="button" id="zdxbBtn">装订修补</button>&nbsp;&nbsp;
				<button type="button" id="ysysBtn">疑似遗失</button>
			</div>
		</section>
	</div>
	<div class="footer">
		Copyright &copy; 2017 中国科学院上海光学精密机械研究所
	</div>
<script src="../../public/js/jquery.min.js"></script>
<script src="../../public/js/nav.js"></script>
<script src="../../public/js/bookLose.js"></script>
</body>
</html>