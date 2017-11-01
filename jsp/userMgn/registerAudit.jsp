<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用户同步</title>
    <link href="../../public/css/common.css" rel="stylesheet" type="text/css"/>
    <link href="../../public/css/nav.css" rel="stylesheet" type="text/css"/>
    <link href="../../public/css/registerAudit.css" rel="stylesheet" type="text/css"/>
    <link href="../../public/css/footer.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<jsp:include page="../nav.jsp"/>
<div class="wrap">
    <div class="wrap-top">
    	<button id="user-sync">用户同步</button>&nbsp;&nbsp;&nbsp;&nbsp;
    	<span id="sync-info"></span>
    </div>
    <p>变动的用户信息</p>
    <table>
    	<thead>
    		<tr>
    			<th>用户名</th>
    			<th>身份证</th>
    			<th>一卡通</th>
    			<th>电话</th>
    			<th>部门</th>
    			<th>状态</th>
    		</tr>
    	</thead>
    	<tbody id="userChangeInfo">
    		
    	</tbody>
    </table>
    <p>借阅信息</p>
    <table>
    	<thead>
    		<tr>
    			<th>书名</th>
    			<th>作者</th>
    			<th>用户名</th>
    		</tr>
    	</thead>
    	<tbody id="userBorrowInfo">
    		
    	</tbody>
    </table>
</div>
<div class="footer">
    Copyright &copy; 2017 中国科学院上海光学精密机械研究所
</div>
<script src="../../public/js/jquery.min.js"></script>
<script src="../../public/js/nav.js"></script>
<script src="../../public/js/registerAudit.js"></script>

</body>
</html>