<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>图书录入列表</title>
	<link href="../public/css/common.css" rel="stylesheet" type="text/css" />
	<link href="../public/css/nav.css" rel="stylesheet" type="text/css" />
	<link href="../public/css/bookAddList.css" rel="stylesheet" type="text/css" />
	<link href="../public/css/footer.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<jsp:include  page="../nav.jsp"/>
	<div class="wrap">
		<p class="introinfo"><img src="../public/images/bookout_06.png">&nbsp;已录入图书信息</p>
		<table>
			<thead>
				<tr>
					<th>批号</th>
					<th>书名</th>
					<th>责任者</th>
					<th>出版信息</th>
					<th>录入时间</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				
			</tbody>
		</table>
	</div>
<script src="../public/js/jquery.min.js"></script>
<script src="../public/js/nav.js"></script>
<script src="../public/js/bookAddList.js"></script>
</body>
</html>