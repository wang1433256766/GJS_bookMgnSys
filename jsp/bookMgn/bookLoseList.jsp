<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>图书遗失列表</title>
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
				<p class="introinfo"><img src="../../public/images/bookout_06.png">&nbsp;图书遗失列表</p>
			</div>
			<table>
				<thead>
					<tr>
						<th>书名</th>
						<th>责任者</th>
						<th>出版信息</th>
						<th>索书号</th>
						<th>条形码</th>
						<th>遗失人</th>
						<th>状态</th>
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
		</section>
	</div>
	<div class="footer">
		Copyright &copy; 2017 中国科学院上海光学精密机械研究所
	</div>
<script src="../../public/js/jquery.min.js"></script>
<script src="../../public/js/nav.js"></script>
<script src="../../public/js/bookLoseList.js"></script>
</body>
</html>