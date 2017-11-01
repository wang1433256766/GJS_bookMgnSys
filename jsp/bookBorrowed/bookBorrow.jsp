<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>图书借阅</title>
	<link href="../../public/css/xcConfirm.css" rel="stylesheet" type="text/css" />
	<link href="../../public/css/common.css" rel="stylesheet" type="text/css" />
	<link href="../../public/css/nav.css" rel="stylesheet" type="text/css" />
	<link href="../../public/css/bookBorrow.css" rel="stylesheet" type="text/css" />
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
		<section class="section">
			<p class="introinfo"><img src="../../public/images/bookout_03.png">&nbsp;证件信息</p>
			<p class="text">一卡通ID: &nbsp;&nbsp;<input id="card" type="text" placeholder="输入一卡通ID" style="width:345px;"/></p>
			<p class="text introtext">已借阅的图书【<span id="borrowCount">0</span>】本，已超期的图书【<span id="overCount">0</span>】本</p><!-- ，关注图书【<span id="orderCount">0</span>】本 -->
			<div class="selfinfo">
				<div class="part-1">
					<div class="selflogo"></div>
					<input type="hidden" id="i-uid">
					<div class="selfstatus">
						状态：<span id="ustatus" class="span-text"></span>
						<select id="i-ustatus" class="input-text hidden">
							<option value="0">可用</option>
		                    <option value="1">解除合同离所</option>
		                    <option value="2">退休</option>
		                    <option value="3">毕业离所</option>
		                    <option value="4">违规停用</option>
		                    <option value="5">其他原因停用</option>
		                    <option value="99">不可用</option>
						</select>
					</div>
					<div class="selfcode">二维码：<span id="qrcode"></span></div>
					<div class="selfbtn"><button type="button" class="hidden" id="change-selfinfo">修改</button><button type="button" class="hidden" id="cancle-change">取消</button>&nbsp;<button type="button" class="hidden" id="submit-change">提交</button></div>
				</div>
				<div class="part-2">
					<div class="row">
						<div class="text-left">用户名：</div>
						<div class="text-right">
							<span class="" id="uname"></span>
							<!-- <input type="text" class="input-text hidden" id="i-uname"> -->
						</div>
					</div>
					<div class="row">
						<div class="text-left">身份证号：</div>
						<div class="text-right">
							<span class="" id="uidcard"></span>
							<!-- <input type="text" class="input-text hidden" id="i-uidcard"> -->
						</div>
					</div>
					<div class="row">
						<div class="text-left">一卡通ID：</div>
						<div class="text-right">
							<span class="" id="ucard"></span>
							<!-- <input type="text" class="input-text hidden" id="i-ucard"> -->
						</div>
					</div>
					<div class="row">
						<div class="text-left">邮箱地址：</div>
						<div class="text-right">
							<span class="span-text" id="uemail"></span>
							<input type="text" class="input-text hidden" id="i-uemail">
						</div>
					</div>
					<div class="row">
						<div class="text-left">手机号：</div>
						<div class="text-right">
							<span class="span-text" id="uphone"></span>
							<input type="text" class="input-text hidden" id="i-uphone">
						</div>
					</div>
					<div class="row">
						<div class="text-left">固定电话：</div>
						<div class="text-right">
							<span class="span-text" id="utel"></span>
							<input type="text" class="input-text hidden" id="i-utel">
						</div>
					</div>
				</div>
				<div class="part-3">
					<div class="row">
						<div class="text-left">导师：</div>
						<div class="text-right">
							<span class="span-text" id="ututor"></span>
							<input type="text" class="input-text hidden" id="i-ututor">
						</div>
					</div>
					<div class="row">
						<div class="text-left">所属部门：</div>
						<div class="text-right">
							<span class="span-text" id="udepartment"></span>
							<input type="text" class="input-text hidden" id="i-udepartment">
						</div>
					</div>
					<div class="row">
						<div class="text-left">用户角色：</div>
						<div class="text-right">
							<span class="" id="urole"></span>
							<!-- <input type="text" class="input-text hidden" id="i-urole"> -->
						</div>
					</div>
					<div class="row">
						<div class="text-left">用户工号：</div>
						<div class="text-right">
							<span class="span-text" id="unumber"></span>
							<input type="text" class="input-text hidden" id="i-unumber">
						</div>
					</div>
					<div class="row">
						<div class="text-left">用户创建时间：</div>
						<div class="text-right">
							<span class="" id="ucreateTime"></span>
							<!-- <input type="text" class="input-text hidden" id="i-ucreateTime"> -->
						</div>
					</div>
					<div class="row">
						<div class="text-left">用户更新时间：</div>
						<div class="text-right">
							<span class="" id="uupdateTime"></span>
							<!-- <input type="text" class="input-text hidden" id="i-uupdateTime"> -->
						</div>
					</div>
				</div>
			</div>
		</section>
		<div class="hidden" id="change-display">
			<hr>
			<section class="section">
				<p class="introinfo"><img src="../../public/images/bookout_08.png">&nbsp;用户已借图书</p>
				<table>
					<thead>
						<tr>
							<th>条形码</th>
							<th>书名</th>
							<th>责任者</th>
							<th>借阅日期</th>
							<th>归还期限</th>
						</tr>
					</thead>
					<tbody id="borrowed-tbody">
						
					</tbody>
				</table>
			</section>
			<hr>
			<section class="section">
				<p class="introinfo"><img src="../../public/images/bookout_06.png">&nbsp;图书信息</p>
				<p class="text">图书条形码： &nbsp;&nbsp;<input id="barcard" type="text" placeholder="输入条形码" style="width:345px;"></p>
				<input type="hidden" id="b-beid">
				<input type="hidden" id="b-bestatus">
				<table>
					<thead>
						<tr>
							<th>条形码</th>
							<th>书名</th>
							<th>责任者</th>
							<th>ISBN</th>
							<th>状态</th>
						</tr>
					</thead>
					<tbody id="willborrow-tbody">
						<!-- <tr>
							<td>1</td>
							<td class="bookname">玻璃的光学和光谱性质</td>
							<td>干福熹著</td>
							<td>2017-07-18</td>
							<td class="enddate">2017-08-18</td>
						</tr> -->
					</tbody>
				</table>
				<div class="row-button">
					<button type="button" id="borrow" class="hidden" disabled="disabled">借&nbsp;书</button>
				</div>
			</section>
		</div>
	</div>
	<div class="footer">
		Copyright &copy; 2017 中国科学院上海光学精密机械研究所
	</div>
<script src="../../public/js/jquery.min.js"></script>
<script src="../../public/js/xcConfirm.js"></script>
<script src="../../public/js/nav.js"></script>
<script src="../../public/js/bookBorrow.js"></script>
</body>
</html>