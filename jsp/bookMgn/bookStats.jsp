<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>图书统计</title>
	<link href="../../public/css/common.css" rel="stylesheet" type="text/css" />
	<link href="../../public/css/nav.css" rel="stylesheet" type="text/css" />
	<link href="../../public/css/bookStats.css" rel="stylesheet" type="text/css" />
	<link href="../../public/css/footer.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<jsp:include  page="../nav.jsp"/>
	<div class="wrap">
		<p class="introinfo"><img src="../../public/images/bookout_06.png">&nbsp;图书统计</p>
		<p class="text">
			<span>批次号：</span>
			<select name="" id="queryKey" style="width:300px;height:22px;"></select>
			<button type="button" id="queryBtn">查&nbsp;询</button>
		</p>
		<table>
			<thead>
				<tr>
					<th>书名</th>
					<th>责任者</th>
					<th>出版信息</th>
					<th>价格</th>
					<th>出版年</th>
					<th>索书号</th>
					<th>条形码</th>
					<th>录入时间</th>
					<th>状态</th>
				</tr>
			</thead>
			<tbody>
				
			</tbody>
		</table>
		<div class="row-button">
			<button id="exportExecl">导出</button>
		</div>
	</div>
	<div class="footer">
		Copyright &copy; 2017 中国科学院上海光学精密机械研究所
	</div>
<script src="../../public/js/jquery.min.js"></script>
<script src="../../public/js/nav.js"></script>
<script src="../../public/js/bookStats.js"></script>	
</body>
</html>