<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>建议反馈</title>
	<link href="../../public/css/common.css" rel="stylesheet" type="text/css" />
	<link href="../../public/css/nav-customer.css" rel="stylesheet" type="text/css" />
	<link href="../../public/css/suggestion-customer.css" rel="stylesheet" type="text/css" />
	<link href="../../public/css/footer.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<jsp:include  page="./nav-customer.jsp"/>
	<div class="wrap">
		<p class="introinfo"><img src="../public/images/suggestion-1_03.png">&nbsp;建议反馈</p>
		<div class="content">
			<p>您在使用过程中，有什么建议与反馈，可以和我们联系，感谢您的建议。</p>
			<p><span title="必填" style="color:red;cursor:pointer;">*</span>&nbsp;建议：<textarea id="suggestionContent"></textarea></p>
		</div>
		<div class="row-button">
			<button id="send">发送</button>
		</div>
	</div>	
	<div class="footer">
		Copyright &copy; 2017 中国科学院上海光学精密机械研究所
	</div>
<script src="../../public/js/jquery.min.js"></script>
<script src="../../public/js/nav-customer.js"></script>
<script src="../../public/js/suggestion-customer.js"></script>	
</body>
</html>