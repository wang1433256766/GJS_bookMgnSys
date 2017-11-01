<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="navbar">
		<div class="nav-image">
			<img src="../public/images/tushuguan.png" />
			<div class="logo-text">欢迎访问中国科学院上海光学精密机械研究所图书馆！</div>
		</div>
		<div class="nav">
			<ul>
				<li id="nav-bookQueryCus"><a>图书检索</a>
					<div class="sed-nav">
						<ul>
							<li><a href="/bookQueryCus/bookQueryCusSimple">简单检索</a></li>
							<li><a href="/bookQueryCus/bookQueryCusComplex">组合检索</a></li>
						</ul>
					</div>
				</li>
				<li id="nav-recommendationCus"><a>读者荐购</a>
					<div class="sed-nav">
						<ul>
							<li><a href="/recommendationCus/recomCus">读者荐购</a></li>
							<li><a href="/recommendationCus/recomHistory">荐购历史</a></li>
						</ul>
					</div>
				</li>
				<li id="nav-myBooksInfo"><a>我的图书馆</a>
					<div class="sed-nav">
						<ul style="padding:7px;">
							<li><a href="/myBooksInfo/myInfo">读者信息</a></li>
							<li><a href="/myBooksInfo/borrowHistory">借阅历史</a></li>
							<li><a href="/myBooksInfo/messages">消息通知</a></li>
						</ul>
					</div>
				</li>
				<li id="nav-suggestion"><a>建议与反馈</a>
					<div class="sed-nav">
						<ul>
							<li><a href="/suggestion-customer">建议与反馈</a></li>
							<li><a href="/suggestion-customer-history">反馈历史</a></li>
						</ul>
					</div>
				</li>
				<div class="login"><a id="login-name"></a> | <a id="logout" style="cursor:pointer;">登出</a></div>
			</ul>
		</div>
	</div>
