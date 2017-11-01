<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>书目检索</title>
	<link href="../../public/css/common.css" rel="stylesheet" type="text/css" />
	<link href="../../public/css/nav.css" rel="stylesheet" type="text/css" />
	<link href="../../public/css/bookQueryComplex.css" rel="stylesheet" type="text/css" />
	<link href="../../public/css/footer.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<jsp:include  page="../nav.jsp"/>
	<div class="wrap">
		<img src="../../public/images/home-1_03.png" />
		<div class="main-wrap">
			<div class="row">
				<div class="col-md-6">
					<div class="text-left"><label>题名：</label></div>
					<div class="content-right"><input type="text" id="query-name" placeholder="请输入题名"></div>
				</div>
				<div class="col-md-6">
					<div class="text-left"><label>出版社：</label></div>
					<div class="content-right"><input type="text" id="query-publisher" placeholder="请输入出版社"></div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="text-left"><label>作者：</label></div>
					<div class="content-right"><input type="text" id="query-person" placeholder="请输入责任人"></div>
				</div>
				<div class="col-md-6">
					<div class="text-left"><label>ISBN：</label></div>
					<div class="content-right"><input type="text" id="query-isbn" placeholder="请输入ISBN"></div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="text-left"><label>丛书名：</label></div>
					<div class="content-right"><input type="text" id="query-bookname" placeholder="请输入丛书名"></div>
				</div>
				<div class="col-md-6">
					<div class="text-left"><label>索书号：</label></div>
					<div class="content-right"><input type="text" id="query-claim" placeholder="请输入索书号"></div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="text-left"><label>出版年：</label></div>
					<div class="content-right"><input type="text" id="query-pubtime" placeholder="请输入出版年"></div>
				</div>
			</div>
			<div class="row-btn">
				<button id="btn-query" type="button">检索</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button id="btn-reset" type="button">重置</button>
			</div>
		</div>
	</div>
	<div class="footer">
		Copyright &copy; 2017 中国科学院上海光学精密机械研究所
	</div>
<script src="../../public/js/jquery.min.js"></script>
<script src="../../public/js/nav.js"></script>
<script src="../../public/js/bookQueryComplex.js"></script>
</body>
</html>