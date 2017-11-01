<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>读者荐购</title>
	<link href="../../public/css/common.css" rel="stylesheet" type="text/css" />
	<link href="../../public/css/nav.css" rel="stylesheet" type="text/css" />
	<link href="../../public/css/recommendation.css" rel="stylesheet" type="text/css" />
	<link href="../../public/css/footer.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<jsp:include  page="./nav.jsp"/>
	<div class="wrap">
		<div class="part-left">
			<p class="introinfo"><img src="../public/images/bookout_06.png">&nbsp;荐购审核</p>
			<p class="text">共有荐购审核【<span id="total">0</span>】条，待审核【<span id="waitAudit">0</span>】条，已通过【<span id="agreeAudit">0</span>】条，已拒绝【<span id="refuseAudit">0</span>】条</p>
		</div>
		<div class="part-right">
			<p>状态：
				<input type="radio" name="status" value=""/>全部
				<input type="radio" name="status" value="0" checked/>待审核
				<input type="radio" name="status" value="1"/>已通过
				<input type="radio" name="status" value="2"/>已拒绝
			</p>
			<p>
				按&nbsp;<select id="sortBy">
					<option value="p_createtime" selected>荐购时间</option>
					<option value="p_book_title">提名</option>
					<option value="p_author">责任者</option>
					<option value="p_publisher">出版信息</option>
					<option value="p_admin_uid">荐购者</option>
				</select>：
				<input type="radio" name="sort" value="asc" checked/>升序
				<input type="radio" name="sort" value="desc"/>降序&nbsp;&nbsp;&nbsp;
				<button class="query" id="query">查&nbsp;询</button>
			</p>
		</div>
		<table>
			<thead>
				<tr>
					<th></th>
					<th>题名</th>
					<th>责任者</th>
					<th>出版信息</th>
					<th>荐购者</th>
					<th>荐购时间</th>
					<th>状态</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				
			</tbody>
		</table>
		<div class="page">
			<input type="image" id="first-page" src="../../public/images/booklist-4.png" title="首页">
			<input type="image" id="pre-page" src="../../public/images/booklist-1.png" title="上一页">
			<!-- <img id="first-page" src="../../public/images/booklist-4.png" title="首页">
			<img id="pre-page" src="../../public/images/booklist-1.png" title="上一页"> -->
			<input id="page-num" type="text" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9]+/,'');}).call(this)" onblur="this.v();" style="width:30px;"/>&nbsp;共&nbsp;<span id="total-page"></span>&nbsp;页
			<input type="image" id="next-page" src="../../public/images/booklist-2.png" title="下一页">
			<input type="image" id="last-page" src="../../public/images/booklist-3.png" title="尾页">
			<!-- <img id="next-page" src="../../public/images/booklist-2.png" title="下一页">
			<img id="last-page" src="../../public/images/booklist-3.png" title="尾页"> -->
		</div>
	</div>
	<div id="auditModal" class="hidden">
		<div class="modal-title">
			<div class="introinfo"><img src="../public/images/bookout_06.png">&nbsp;荐购审核<span class="closebtn close">x</span></div>
		</div>
		<hr>
		<div class="modal-content">
			<input type="hidden" id="pid">
			<div class="row">
				<div class="left-text"><strong>题名：</strong></div>
				<div class="right-text"><span id="mname"></span></div>
			</div>
			<div class="row">
				<div class="left-text"><strong>责任人：</strong></div>
				<div class="right-text"><span id="mauthor"></span></div>
			</div>
			<div class="row">
				<div class="left-text"><strong>出版信息：</strong></div>
				<div class="right-text"><span id="mpublish"></span></div>
			</div>
			<div class="row">
				<div class="left-text"><strong>版次：</strong></div>
				<div class="right-text"><span id="mbanci"></span></div>
			</div>
			<div class="row">
				<div class="left-text"><strong>荐购者：</strong></div>
				<div class="right-text"><span id="musername"></span></div>
			</div>
			<div class="row">
				<div class="left-text"><strong>荐购时间：</strong></div>
				<div class="right-text"><span id="mtime"></span></div>
			</div>
			<div class="row">
				<div class="left-text"><strong>荐购理由：</strong></div>
				<div class="right-text"><span id="mreason"></span></div>
			</div>
			<div class="row">
				<div class="left-text"><strong>bid：</strong></div>
				<div class="right-text"><input type="text" id="bid"/></div>
			</div>
			<div class="row">
				<div class="left-text"><strong>http：</strong></div>
				<div class="right-text"><input type="text" id="http"/></div>
			</div>
			<div class="row">
				<div class="left-text"><strong>回复：</strong></div>
				<div class="right-text"><textarea id="mark"></textarea></div>
			</div>
		</div>
		<div class="modal-footer">
			<button id="auditPass" class="close">通过</button>&nbsp;&nbsp;&nbsp;&nbsp;
			<button id="auditRefuse" class="close">拒绝</button>
		</div>
	</div>
	<div class="footer">
		Copyright &copy; 2017 中国科学院上海光学精密机械研究所
	</div>
<script src="../../public/js/jquery.min.js"></script>
<script src="../../public/js/moaModal.minified.js"></script>
<script src="../../public/js/Sweefty.js"></script>
<script src="../../public/js/nav.js"></script>
<script src="../../public/js/recommendation.js"></script>	
</body>
</html>