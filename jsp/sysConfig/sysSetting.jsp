<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>系统设置</title>
	<link href="../../public/css/common.css" rel="stylesheet" type="text/css" />
	<link href="../../public/css/nav.css" rel="stylesheet" type="text/css" />
	<link href="../../public/css/sysSetting.css" rel="stylesheet" type="text/css" />
	<link href="../../public/css/footer.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<jsp:include  page="../nav.jsp"/>
	<div class="wrap">
		<p class="introinfo">
			<img src="../../public/images/sysconfig-1_03.png">&nbsp;系统设置
		</p>
		<div class="main-content">
			<p><div class="text-left">借阅时长：</div><div class="text-right"><input id="btime" type="text" style="width:140px;">&nbsp;天</div></p>
			<p><div class="text-left">最大借阅数量：</div><div class="text-right"><input id="bsum" type="text" style="width:140px;">&nbsp;本</div></p>
			<p><div class="text-left">续借时长：</div><div class="text-right"><input id="againbtime" type="text" style="width:140px;">&nbsp;天</div></p>
			<p><div class="text-left">最大续借次数：</div><div class="text-right"><input id="againbcount" type="text" style="width:140px;">&nbsp;次</div></p>
			<p><div class="text-left">外文书籍条形码最小编号：</div><div class="text-right"><input id="foreminnum" type="text" style="width:160px;"></div></p>
			<p><div class="text-left">端口：</div><div class="text-right"><input id="port" type="text" style="width:160px;"></div></p>
			<p><div class="text-left">smtp端口：</div><div class="text-right"><input id="smtpport" type="text" style="width:160px;"></div></p>
			<p><div class="text-left">邮件：</div><div class="text-right"><input id="email" type="text" style="width:160px;"></div></p>
			<p><div class="text-left">邮件smtp：</div><div class="text-right"><input id="smtpemail" type="text" style="width:160px;"></div></p>
			<!-- <p><div class="text-left">后台邮件地址：</div><div class="text-right"><input id="emailadress" type="text" style="width:160px;"></div></p> -->
			<p><div class="text-left">后台邮件密码：</div><div class="text-right"><input id="emailpwd" type="text" style="width:160px;"></div></p>
			<p><div class="text-left">回复反馈邮件标题：</div><div class="text-right"><input id="emailtitle" type="text" style="width:160px;"></div></p>
			<p><div class="text-left">提醒标题：</div><div class="text-right"><input id="tiptitle" type="text" style="width:160px;"></div></p>
			<p><div class="text-left">提醒模板：</div><div class="text-right">
														    <textarea id="tipmode" style="width:460px;height:80px;"></textarea>
														    <p style="color:red;"><img src="../../public/images/06-02-sysconfig-1_03.png"/>&nbsp;{1}代表用户名，{2}代表图书名，{3}代表条形码，{4}代表还书期限</p>
													  </div></p>
			<p><div class="text-left">提醒站内信标题：</div><div class="text-right"><input id="tipmsgtitle" type="text" style="width:160px;"></div></p>
			<p><div class="text-left">提醒站内信模板：</div><div class="text-right">
																<textarea id="tipmsgmode" style="width:460px;height:80px;"></textarea>
																<p style="color:red;"><img src="../../public/images/06-02-sysconfig-1_03.png"/>&nbsp;{1}代表图书名，{2}代表条形码，{3}代表还书期限</p>
															</div></p>
			<p><div class="text-left">关注标题：</div><div class="text-right"><input id="focustitle" type="text" style="width:160px;"></div></p>
			<p><div class="text-left">关注模板：</div><div class="text-right">
														  <textarea id="focusmode" style="width:460px;height:80px;"></textarea>
														  <p style="color:red;"><img src="../../public/images/06-02-sysconfig-1_03.png"/>&nbsp;{1}代表用户名，{2}代表图书名，{3}代表条形码</p>
													  </div></p>
			<p><div class="text-left">关注站内信标题：</div><div class="text-right"><input id="focusmsgtitle" type="text" style="width:160px;"></div></p>
			<p><div class="text-left">关注站内信模板：</div><div class="text-right">
																<textarea id="focusmsgmode" style="width:460px;height:80px;"></textarea>
																<p style="color:red;"><img src="../../public/images/06-02-sysconfig-1_03.png"/>&nbsp;{1}代表用户名，{2}代表图书名，{3}代表条形码</p>
															</div></p>
		</div>	
		<div class="row-button">
			<button id="send">保存</button>
		</div>
	</div>
	<div class="footer">
		Copyright &copy; 2017 中国科学院上海光学精密机械研究所
	</div>
<script src="../../public/js/jquery.min.js"></script>
<script src="../../public/js/nav.js"></script>
<script src="../../public/js/sysSetting.js"></script>
</body>
</html>