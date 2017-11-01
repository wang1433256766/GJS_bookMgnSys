<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>系统通知</title>
	<link href="../../public/css/common.css" rel="stylesheet" type="text/css" />
	<link href="../../public/css/nav.css" rel="stylesheet" type="text/css" />
	<link href="../../public/css/sysNotification.css" rel="stylesheet" type="text/css" />
	<link href="../../public/css/footer.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<jsp:include  page="../nav.jsp"/>
	<div class="wrap">
		<p class="introinfo">
			<img src="../../public/images/suggestion-1_03.png">&nbsp;系统通知
			<a href="/sysConfig/sysNotificationList">已发送消息列表</a>
		</p>
		<div class="main-content">
			<p><span><label title="必填" style="color:red;cursor:pointer;">*</label>&nbsp;主题：</span><input type="text" id="mainTitle"></p>
			<p><span><label title="必填" style="color:red;cursor:pointer;">*</label>&nbsp;正文：</span><textarea id="mainContent"></textarea></p>
		</div>	
		<div class="row-button">
			<button id="send">发送</button>
		</div>
	</div>
	<div class="footer">
		Copyright &copy; 2017 中国科学院上海光学精密机械研究所
	</div>
<script src="../../public/js/jquery.min.js"></script>
<script src="../../public/js/nav.js"></script>
<script src="../../public/js/sysNotification.js"></script>
</body>
</html>