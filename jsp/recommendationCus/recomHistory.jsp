<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>荐购历史</title>
	<link href="../../public/css/common.css" rel="stylesheet" type="text/css" />
	<link href="../../public/css/nav-customer.css" rel="stylesheet" type="text/css" />
	<link href="../../public/css/recomHistory.css" rel="stylesheet" type="text/css" />
	<link href="../../public/css/footer.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<jsp:include  page="../nav-customer.jsp"/>
	<div class="wrap">
		<p class="introinfo">
			<img src="../../public/images/bookout_06.png">&nbsp;荐购历史
		</p>
		<table>
			<thead>
				<tr>
					<th>书名</th>
					<th>作者</th>
					<th>出版信息</th>
					<th>版次</th>
					<th>荐购时间</th>
					<th>bid</th>
					<th>http</th>
					<th>原因</th>
					<th>回复</th>
					<th></th>
				</tr>
			</thead>
			<tbody id="selfPur">
				
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
		</div>
		<p class="introinfo">
			<img src="../../public/images/bookout_06.png">&nbsp;他人荐购
		</p>
		<table>
			<thead>
				<tr>
					<th>书名</th>
					<th>作者</th>
					<th>出版信息</th>
					<th>版次</th>
					<th>荐购时间</th>
					<th>bid</th>
					<th>http</th>
					<th>原因</th>
					<th>回复</th>
					<th></th>
				</tr>
			</thead>
			<tbody id="otherPur">
				
			</tbody>
		</table>
		<div class="otherPage">
			<input type="image" id="other-first-page" src="../../public/images/booklist-4.png" title="首页">
			<input type="image" id="other-pre-page" src="../../public/images/booklist-1.png" title="上一页">
			<!-- <img id="first-page" src="../../public/images/booklist-4.png" title="首页">
			<img id="pre-page" src="../../public/images/booklist-1.png" title="上一页"> -->
			<input id="other-page-num" type="text" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9]+/,'');}).call(this)" onblur="this.v();" style="width:30px;"/>&nbsp;共&nbsp;<span id="other-total-page"></span>&nbsp;页
			<input type="image" id="other-next-page" src="../../public/images/booklist-2.png" title="下一页">
			<input type="image" id="other-last-page" src="../../public/images/booklist-3.png" title="尾页">
			<!-- <img id="next-page" src="../../public/images/booklist-2.png" title="下一页">
			<img id="last-page" src="../../public/images/booklist-3.png" title="尾页"> -->
		</div>
	</div>
	<div class="footer">
		Copyright &copy; 2017 中国科学院上海光学精密机械研究所
	</div>
<script src="../../public/js/jquery.min.js"></script>
<script src="../../public/js/nav-customer.js"></script>
<script src="../../public/js/recomHistory.js"></script>
</body>
</html>