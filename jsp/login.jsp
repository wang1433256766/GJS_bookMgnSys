<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="../public/css/bootstrap.min.css">
	<link rel="stylesheet" href="../public/css/login.css"> 
	<link href="../public/css/footer.css" rel="stylesheet" type="text/css" />
	<title>Login</title>
</head>
<body>
	<div class="nav">
		<div class="nav-image">
			<img src="../public/images/tushuguan.png" />
			<div class="logo-text">欢迎访问中国科学院上海光学精密机械研究所图书馆！</div>
		</div>
	</div>
	<div class="wrap">
		<div class="main-content">
			<div class="content-left">
			</div>
			<div class="content-right">
				<div class="login-title">用户登录</div><hr>
				<div class="login-content">
					<div class="text1">
					    <input type="text" id="card" placeholder="请输入用户名"/>
					</div>
					<div class="text2">
					    <input type="password" id="password" placeholder="请输入密码"/>
					</div>
					<!-- <input type="text" placeholder=""><br>
					<input type="text"> -->
				</div>
				<div class="login-btn">
					<button id="submit">登录</button>
				</div>
			</div>
		</div>
	</div>
	<div class="footer">
		Copyright &copy; 2017 中国科学院上海光学精密机械研究所
	</div>
	<script src="../public/js/jquery.min.js"></script>
	<script src="../public/js/login.js"></script>
</body>
</html>