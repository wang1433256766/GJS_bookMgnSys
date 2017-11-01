<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>我的图书馆</title>
	<link href="../../public/css/common.css" rel="stylesheet" type="text/css" />
	<link href="../../public/css/nav.css" rel="stylesheet" type="text/css" />
	<link href="../../public/css/myInfo.css" rel="stylesheet" type="text/css" />
	<link href="../../public/css/footer.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<jsp:include  page="../nav.jsp"/>
	<div class="wrap">
		<section class="section">
			<p class="introinfo"><img src="../../public/images/bookout_03.png">&nbsp;证件信息</p>
			<p class="text introtext">已借阅的图书【<span id="borrowCount">0</span>】本，已超期的图书【<span id="overCount">0</span>】本，已关注图书【<span id="orderCount">0</span>】本</p>
			<div class="selfinfo">
				<div class="part-1">
					<div class="selflogo"></div>
					<div class="selfcode">二维码：<span id="qrcode"></span></div>
					<!-- <div class="pwd">密码：<span class="span-text" id="pwd">******</span>
						<input type="password" class="input-text hidden" id="i-pwd"></div> -->
					<div class="selfbtn"><button id="change-selfinfo">修改</button><button class="hidden" id="cancle-change">取消</button>&nbsp;<button class="hidden" id="submit-change">提交</button></div>
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
					<div class="row">
						<div class="text-left">密码：</div>
						<div class="text-right">
							<span class="span-text" id="pwd">******</span>
							<input type="password" class="input-text hidden" id="i-pwd">
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
					<div class="row input-text hidden">
						<div class="text-left">确认密码：</div>
						<div class="text-right">
							<input type="password" class="" id="i-repwd">
						</div>
					</div>
				</div>
			</div>
		</section>
		<section class="section">
			<p class="introinfo"><img src="../../public/images/bookout_08.png">&nbsp;当前借阅</p>
			<table>
				<thead>
					<tr>
						<th>条形码</th>
						<th>书名</th>
						<th>作者</th>
						<th>借阅日期</th>
						<th>归还期限</th>
						<th></th>
					</tr>
				</thead>
				<tbody id="borrowed-tbody">
					
				</tbody>
			</table>
		</section>
		<section class="section hidden">
			<p class="introinfo"><img src="../../public/images/bookout_08.png">&nbsp;关注图书</p>
			<table>
				<thead>
					<tr>
						<th>条形码</th>
						<th>书名</th>
						<th>作者</th>
						<th>关注日期</th>
						<th></th>
					</tr>
				</thead>
				<tbody id="focus-tbody">
					
				</tbody>
			</table>
		</section>
	</div>
	<div class="footer">
		Copyright &copy; 2017 中国科学院上海光学精密机械研究所
	</div>
<script src="../../public/js/jquery.min.js"></script>
<script src="../../public/js/nav.js"></script>
<script src="../../public/js/myInfo.js"></script>
</body>
</html>