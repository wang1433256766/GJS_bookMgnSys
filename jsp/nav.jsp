<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="navbar">
		<div class="nav-image">
			<img src="../public/images/tushuguan.png" />
			<div class="logo-text">欢迎访问中国科学院上海光学精密机械研究所图书馆！</div>
		</div>
		<div class="nav">
			<ul>
				<li id="nav-bookQuery"><a>书目检索</a>
					<div class="sed-nav">
						<ul>
							<li><a href="/bookQuery/bookQuerySimple">简单检索</a></li>
							<li><a href="/bookQuery/bookQueryComplex">组合检索</a></li>
						</ul>
					</div>
				</li>
				<li id="nav-bookBorrowed"><a>图书借还</a>
					<div class="sed-nav">
						<ul>
							<li><a href="/bookBorrowed/bookBorrow">图书借阅</a></li>
							<li><a href="/bookBorrowed/bookReturn">图书归还</a></li>
						</ul>
					</div>
				</li>
				<li id="nav-bookMgn"><a>图书管理</a>
					<div class="sed-nav">
						<ul>
							<li><a href="/bookMgn/bookList">图书录入</a></li>
							<li><a href="/bookMgn/bookAddList">录入列表</a></li>
							<li><a href="/bookMgn/bookAuditList">图书审核</a></li>
							<li><a href="/bookMgn/bookLose">图书遗失</a></li>
							<li><a href="/bookMgn/bookStats">图书统计</a></li>
							<li><a href="/bookMgn/bookListMgn">书目列表</a></li>
						</ul>
					</div>
				</li>
				<li id="nav-recommendation"><a href="/recommendation">读者荐购</a></li>
				<li id="nav-userMgn"><a>用户管理</a>
					<div class="sed-nav">
						<ul>
							<li><a href="/userMgn/registerAudit">用户同步</a></li>
							<li><a href="/userMgn/userMgn">用户管理</a></li>
							<li><a href="/userMgn/roleMgn">角色管理</a></li>
						</ul>
					</div>
				</li>
				<li id="nav-sysConfig"><a>系统设置</a>
					<div class="sed-nav">
						<ul>
							<!-- <li><a href="/userMgn/registerAudit">注册审核</a></li> -->
							<li><a href="/sysConfig/sysNotification">系统通知</a></li>
							<li><a href="/sysConfig/sysSetting">系统设置</a></li>
						</ul>
					</div>
				</li>
				<li id="nav-suggestion"><a href="/suggestion">建议与反馈</a></li>
				<li id="nav-myBooksInfo"><a>我的图书馆</a>
					<div class="sed-nav">
						<ul style="padding:7px;">
							<li><a href="/myBooksInfo/myInfo_admin">读者信息</a></li>
							<li><a href="/myBooksInfo/borrowHistory_admin">借阅历史</a></li>
							<li><a href="/myBooksInfo/messages_admin">消息通知</a></li>
						</ul>
					</div>
				</li>
				<div class="login"><a id="login-name"></a> | <a id="logout" style="cursor:pointer;">登出</a></div>
			</ul>
		</div>
	</div>
