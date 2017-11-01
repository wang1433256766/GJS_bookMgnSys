<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>图书归还</title>
	<link href="../../public/css/common.css" rel="stylesheet" type="text/css" />
	<link href="../../public/css/nav.css" rel="stylesheet" type="text/css" />
	<link href="../../public/css/bookBorrow.css" rel="stylesheet" type="text/css" />
	<link href="../../public/css/footer.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<jsp:include  page="../nav.jsp"/>
	<div class="wrap">
		<p class="text">图书条形码： &nbsp;&nbsp;<input id="barcard" type="text" placeholder="输入条形码" style="width:345px;"></p>
		
		<div class="hidden" id="change-display">
			<section class="section">
				<p class="introinfo"><img src="../../public/images/bookout_06.png">&nbsp;已还图书信息</p>
				<p>条形码为&nbsp;<span style="color:red;" id="re-barcode"></span>,书名为&nbsp;<span style="color:red;" id="re-bookname"></span>&nbsp;的书刚刚归还成功</p>
			</section>
			<section class="section">
				<hr>
				<p class="introinfo"><img src="../../public/images/bookout_06.png">&nbsp;用户信息</p>
				<div class="selfinfo">
					<div class="part-1">
						<div class="selflogo"></div>
						<input type="hidden" id="i-uid">
						<div class="selfstatus">
							状态：<span id="ustatus" class="span-text"></span>
						</div>
						<div class="selfcode">二维码：<span id="qrcode"></span></div>
					</div>
					<div class="part-2">
						<div class="row">
							<div class="text-left">用户名：</div>
							<div class="text-right">
								<span class="" id="uname"></span>
							</div>
						</div>
						<div class="row">
							<div class="text-left">身份证号：</div>
							<div class="text-right">
								<span class="" id="uidcard"></span>
							</div>
						</div>
						<div class="row">
							<div class="text-left">一卡通ID：</div>
							<div class="text-right">
								<span class="" id="ucard"></span>
							</div>
						</div>
						<div class="row">
							<div class="text-left">邮箱地址：</div>
							<div class="text-right">
								<span class="span-text" id="uemail"></span>
							</div>
						</div>
						<div class="row">
							<div class="text-left">手机号：</div>
							<div class="text-right">
								<span class="span-text" id="uphone"></span>
							</div>
						</div>
						<div class="row">
							<div class="text-left">固定电话：</div>
							<div class="text-right">
								<span class="span-text" id="utel"></span>
							</div>
						</div>
					</div>
					<div class="part-3">
						<div class="row">
							<div class="text-left">导师：</div>
							<div class="text-right">
								<span class="span-text" id="ututor"></span>
							</div>
						</div>
						<div class="row">
							<div class="text-left">所属部门：</div>
							<div class="text-right">
								<span class="span-text" id="udepartment"></span>
							</div>
						</div>
						<div class="row">
							<div class="text-left">用户角色：</div>
							<div class="text-right">
								<span class="" id="urole"></span>
							</div>
						</div>
						<div class="row">
							<div class="text-left">用户工号：</div>
							<div class="text-right">
								<span class="span-text" id="unumber"></span>
							</div>
						</div>
						<div class="row">
							<div class="text-left">用户创建时间：</div>
							<div class="text-right">
								<span class="" id="ucreateTime"></span>
							</div>
						</div>
						<div class="row">
							<div class="text-left">用户更新时间：</div>
							<div class="text-right">
								<span class="" id="uupdateTime"></span>
							</div>
						</div>
					</div>
				</div>
			</section>
			
			<section class="section">
				<hr>
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
		</div>
	</div>
	<div class="footer">
		Copyright &copy; 2017 中国科学院上海光学精密机械研究所
	</div>
<script src="../../public/js/jquery.min.js"></script>
<script src="../../public/js/nav.js"></script>
<script src="../../public/js/bookReturn.js"></script>
</body>
</html>