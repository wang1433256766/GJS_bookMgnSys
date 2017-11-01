<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>系统通知</title>
	<link href="../../public/css/common.css" rel="stylesheet" type="text/css" />
	<link href="../../public/css/nav.css" rel="stylesheet" type="text/css" />
	<link href="../../public/css/sysNotificationList.css" rel="stylesheet" type="text/css" />
	<link href="../../public/css/footer.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<jsp:include  page="../nav.jsp"/>
	<div class="wrap">
		<p class="introinfo">
			<img src="../public/images/suggestion-1_03.png">&nbsp;已发送消息列表
			<button id="returnBtn">再来一封</button>
		</p>
		<div class="content">
			<ul>
				<!-- <li>
					<div class="content-left">
						<p>主题主题主题主题主题主题</p>
						<p>内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容</p>
					</div>
					<div class="content-right">
						<span>2017-07-25</span>
					</div>
				</li>
				<li>
					<div class="content-left">
						<p>主题主题主题主题主题主题</p>
						<p>内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容</p>
					</div>
					<div class="content-right">
						<span>2017-07-25</span>
					</div>
				</li> -->
			</ul>
			<div class="page">
				<input type="image" id="first-page" src="../../public/images/booklist-4.png" title="首页">
				<input type="image" id="pre-page" src="../../public/images/booklist-1.png" title="上一页">
				<input id="page-num" type="text" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9]+/,'');}).call(this)" onblur="this.v();" style="width:30px;"/>&nbsp;共&nbsp;<span id="total-page"></span>&nbsp;页
				<input type="image" id="next-page" src="../../public/images/booklist-2.png" title="下一页">
				<input type="image" id="last-page" src="../../public/images/booklist-3.png" title="尾页">
			</div>
		</div>
	</div>	
	<div class="footer">
		Copyright &copy; 2017 中国科学院上海光学精密机械研究所
	</div>
<script src="../../public/js/jquery.min.js"></script>
<script src="../../public/js/nav.js"></script>
<script src="../../public/js/sysNotificationList.js"></script>	
</body>
</html>