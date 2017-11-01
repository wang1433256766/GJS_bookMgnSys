<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>图书录入</title>
	<link href="../../public/css/xcConfirm.css" rel="stylesheet" type="text/css" />
	<link href="../../public/css/common.css" rel="stylesheet" type="text/css" />
	<link href="../../public/css/nav.css" rel="stylesheet" type="text/css" />
	<link href="../../public/css/bookAdd.css" rel="stylesheet" type="text/css" />
	<link href="../../public/css/footer.css" rel="stylesheet" type="text/css" />
	<style>
		.xcConfirm .popBox {height: 220px;}
		.xcConfirm .popBox .txtBox {
    		margin: 0;
    	}
    	.xcConfirm .popBox .txtBox p {
    		font-size: 24px;
    		color: red;
    		margin-top: 36px;
    	}
	</style>
</head>
<body>
	<jsp:include  page="../nav.jsp"/>
	<div class="wrap">
		<p class="introinfo p1"><img src="../../public/images/bookout_06.png">&nbsp;图书信息</p>
		<div class="part-1">
			<div class="part1-left">
				<p><div class="text-left">ISBN：</div><div class="text-right"><input id="isbn" type="text" style="width:235px;"></div></p>
				<p><div class="text-left">中文书名：</div><div class="text-right"><input id="name" type="text" style="width:235px;"></div></p>
				<p><div class="text-left">外文书名：</div><div class="text-right"><input id="foreignName" type="text" style="width:230px;"></div></p>
				<p><div class="text-left">书籍版次：</div><div class="text-right"><input id="edition" type="text" style="width:112px;">&nbsp;版</div></p>
				<p><div class="text-left">中国分类号：</div><div class="text-right"><input id="categoryThird" type="text" style="width:235px;"></div></p>
				<p><div class="text-left">书籍价格：</div><div class="text-right"><input id="price" type="text" style="width:112px;">&nbsp;元</div></p>
				<p><div class="text-left">书籍页数：</div><div class="text-right"><input id="pages" type="text" style="width:112px;">&nbsp;页</div></p>
				<p><div class="text-left">书籍开本：</div><div class="text-right"><input id="size" type="text" style="width:235px;"></div></p>
				<p><div class="text-left">语种：</div><div class="text-right">
					<select id="lang" style="width:112px;">
						<option value="chi">中文</option>
						<option value="eng">英文</option>
						<option value="fre">法文</option>
						<option value="dem">德文</option>
						<option value="rus">俄文</option>
						<option value="jpn">日文</option>
						<option value="other">其他</option>
					</select>
				</div></p>
				<!-- <p><div class="text-left">索书号：</div><div class="text-right"><input id="index" type="text" style="width:235px;"></div></p> -->
				<p><div class="text-left">丛书：</div><div class="text-right"><input id="series" type="text" style="width:235px;"></div></p>
				<p><div class="text-left">丛书卷：</div><div class="text-right"><input id="volume" type="text" style="width:235px;"></div></p>
				<p><div class="text-left">出版日期：</div><div class="text-right"><input id="pubtime" type="text" style="width:112px;">&nbsp;年</div></p>
				<p><div class="text-left">作者信息：</div><div class="text-right"><input id="author" type="text" style="width:235px;"></div></p>
				<!-- <p><div class="text-left">作者机构信息：</div><div class="text-right"><input id="authorMechanism" type="text" style="width:235px;"></div></p> -->
			</div>
			<div class="part1-right">
				<!-- <p><div class="text-left">译者信息：</div><div class="text-right"><input id="translationAuthor" type="text" style="width:235px;"></div></p> -->
				<p><div class="text-left">出版社：</div><div class="text-right"><input id="publisher" type="text" style="width:235px;"></div></p>
				<p><div class="text-left">关键词：</div><div class="text-right"><input id="key" type="text" style="width:235px;"></div></p>
				<p><div class="text-left">本馆全文编号：</div><div class="text-right"><input id="beurl" type="text" style="width:235px;"></div></p>
				<p><div class="text-left">http：</div><div class="text-right"><input id="htmlUrl" type="text" style="width:235px;"></div></p>
				<p><div class="text-left">其他全文：</div><div class="text-right"><input id="otherUrl" type="text" style="width:235px;"></div></p>
				<p><div class="text-left">内容简介：</div><div class="text-right"><textarea id="summary" style="width:310px;height:80px;"></textarea></div></p>
				<p><div class="text-left">外文简介：</div><div class="text-right"><textarea id="foreignSummary" style="width:310px;height:50px;"></textarea></div></p>
				<p><div class="text-left">附注项</div><div class="text-right"><textarea id="mark" style="width:310px;height:80px;"></textarea></div></p>
			</div>
		</div>
		<p class="introinfo p2"><img src="../../public/images/bookout_06.png">&nbsp;图书实体信息</p>
		<div class="part-2">
			<div class="part2-left">
				<p><div class="text-left">批次号：</div><div class="text-right"><select id="choice-batchId" class="" style="width:235px;"><option value="">请选择</option></select><input class="hidden" id="add-batchId" type="text" style="width:235px;">&nbsp;&nbsp;<button id="addBtn" class="" style="padding:0 15px;">新建</button><button id="saveBtn" class="hidden" style="padding:0 15px;">保存</button></div></p>
				<p><div class="text-left">册数：</div><div class="text-right"><input id="numOfBook" type="text" style="width:235px;" value="1"></div></p>
				<p><div class="text-left">采购价格：</div><div class="text-right"><input id="batchPrice" type="text" style="width:235px;"></div></p>
			</div>
			<div class="part2-right">
				<p><div class="text-left">备注：</div><div class="text-right"><textarea id="bemark" style="width:310px;height:50px;"></textarea></div></p>
			</div>
		</div>
		<div class="row-button">
			<button id="submitBtn">保&nbsp;存</button>
		</div>
	</div>
	<div class="footer">
		Copyright &copy; 2017 中国科学院上海光学精密机械研究所
	</div>
<script src="../../public/js/jquery.min.js"></script>
<script src="../../public/js/xcConfirm.js"></script>
<script src="../../public/js/nav.js"></script>
<script src="../../public/js/bookAdd.js"></script>
</body>
</html>