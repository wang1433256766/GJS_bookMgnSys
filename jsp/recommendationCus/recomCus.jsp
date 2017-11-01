<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>读者荐购</title>
	<link href="../../public/css/common.css" rel="stylesheet" type="text/css" />
	<link href="../../public/css/nav-customer.css" rel="stylesheet" type="text/css" />
	<link href="../../public/css/recomCus.css" rel="stylesheet" type="text/css" />
	<link href="../../public/css/footer.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<jsp:include  page="../nav-customer.jsp"/>
	<div class="wrap">
		<p class="introinfo">
			<img src="../../public/images/bookout_06.png">&nbsp;读者荐购
		</p>
		<div class="main-content">
			<div class="row">
				<div class="left-text"><span style="color:red;cursor:pointer;" title="必填">*</span>&nbsp;书名：</div>
				<div class="right-part"><input type="text" id="title" required="required"></div>
			</div>
			<div class="row">
				<div class="left-text"><span style="color:red;cursor:pointer;" title="必填">*</span>&nbsp;作者：</div>
				<div class="right-part"><input type="text" id="author" required="required"></div>
			</div>
			<div class="row">
				<div class="left-text"><span style="color:red;cursor:pointer;" title="必填">*</span>&nbsp;出版社：</div>
				<div class="right-part"><input type="text" id="publisher" required="required"></div>
			</div>
			<div class="row">
				<div class="left-text">版次：</div>
				<div class="right-part"><input type="text" id="edition"></div>
			</div>
			<div class="row">
				<div class="left-text">推荐理由：</div>
				<div class="right-part"><textarea id="reason"></textarea></div>
			</div>
		</div>	
		<div class="row-button">
			<button id="send" type="button">荐购</button>
		</div>
	</div>
	<div class="footer">
		Copyright &copy; 2017 中国科学院上海光学精密机械研究所
	</div>
<script src="../../public/js/jquery.min.js"></script>
<script src="../../public/js/nav-customer.js"></script>
<script src="../../public/js/recomCus.js"></script>
</body>
</html>