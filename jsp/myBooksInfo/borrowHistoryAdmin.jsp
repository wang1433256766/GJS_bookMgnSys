<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>我的图书馆</title>
	<link href="../../public/css/common.css" rel="stylesheet" type="text/css" />
	<link href="../../public/css/nav.css" rel="stylesheet" type="text/css" />
	<link href="../../public/css/borrowHistory.css" rel="stylesheet" type="text/css" />
	<link href="../../public/css/footer.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<jsp:include  page="../nav.jsp"/>
	<div class="wrap">
		<p class="introinfo"><img src="../../public/images/bookout_08.png">&nbsp;借阅历史</p>
			<table>
				<thead>
					<tr>
						<th>条形码</th>
						<th>书名</th>
						<th>作者</th>
						<th>借阅日期</th>
						<th>归还期限</th>
						<th>归还时间</th>
						<th></th>
					</tr>
				</thead>
				<tbody id="borrowed-tbody">
					
				</tbody>
			</table>
	</div>
	<div class="footer">
		Copyright &copy; 2017 中国科学院上海光学精密机械研究所
	</div>
<script src="../../public/js/jquery.min.js"></script>
<script src="../../public/js/nav.js"></script>
<script src="../../public/js/borrowHistory.js"></script>
</body>
</html>