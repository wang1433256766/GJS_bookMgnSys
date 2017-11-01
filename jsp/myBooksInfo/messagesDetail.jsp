<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>通知详情</title>
	<link href="../../public/css/common.css" rel="stylesheet" type="text/css" />
	<link href="../../public/css/nav-customer.css" rel="stylesheet" type="text/css" />
	<link href="../../public/css/messagesDetail.css" rel="stylesheet" type="text/css" />
	<link href="../../public/css/footer.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<jsp:include  page="../nav-customer.jsp"/>
	<div class="wrap">
		<p class="introinfo">
			<img src="../../public/images/suggestion-1_03.png">&nbsp;通知详情
			<button id="returnBack">返回</button>
		</p>
		<div class="main-content">
			<div class="row"><div class="part-left">主题：</div>&nbsp;<div class="part-right" id="titleContent"></div></div>
			<div class="row"><div class="part-left">时间：</div>&nbsp;<div class="part-right" id="mainDate"></div></div>
			<div class="row"><div class="part-left">正文：</div>&nbsp;<div class="part-right" id="mainContent"></div></div>
		</div>	
		
	</div>
	<div class="footer">
		Copyright &copy; 2017 中国科学院上海光学精密机械研究所
	</div>
<script src="../../public/js/jquery.min.js"></script>
<script src="../../public/js/nav-customer.js"></script>
<script src="../../public/js/messagesDetail.js"></script>
</body>
</html>