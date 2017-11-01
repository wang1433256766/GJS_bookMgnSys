<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>回复反馈</title>
	<link href="../../public/css/common.css" rel="stylesheet" type="text/css" />
	<link href="../../public/css/nav.css" rel="stylesheet" type="text/css" />
	<link href="../../public/css/suggestionAnswer.css" rel="stylesheet" type="text/css" />
	<link href="../../public/css/footer.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<jsp:include  page="./nav.jsp"/>
	<div class="wrap">
		<p class="introinfo">
			<img src="../../public/images/suggestion-1_03.png">&nbsp;反馈回复
		</p>
		<div class="main-content">
			<div class="row"><div class="part-left">建议人：</div>&nbsp;<div class="part-right" id="feedUser"></div></div>
			<div class="row"><div class="part-left">建议内容：</div>&nbsp;<div class="part-right" id="feedContent"></div></div>
			<div class="row"><div class="part-left">建议时间：</div>&nbsp;<div class="part-right" id="feedDate"></div></div>
			<div class="row"><div class="part-left"><span title="必填" style="color:red;cursor: pointer;">*</span>&nbsp;回复：</div>&nbsp;<div class="part-right"><textarea id="answerContent"></textarea></div></div>
		</div>	
		<div class="rowbtn">
			<button id="answerBtn">回复</button>
		</div>
	</div>
	<div class="footer">
		Copyright &copy; 2017 中国科学院上海光学精密机械研究所
	</div>
<script src="../../public/js/jquery.min.js"></script>
<script src="../../public/js/nav.js"></script>
<script src="../../public/js/suggestionAnswer.js"></script>
</body>
</html>