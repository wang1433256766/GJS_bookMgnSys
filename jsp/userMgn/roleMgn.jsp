<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>角色管理</title>
    <link href="../../public/resource/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="../../public/css/common.css" rel="stylesheet" type="text/css" />
    <link href="../../public/css/nav.css" rel="stylesheet" type="text/css" />
    <link href="../../public/css/roleMgn.css" rel="stylesheet" type="text/css" />
    <link href="../../public/css/footer.css" rel="stylesheet" type="text/css" />
</head>
<body>
<jsp:include  page="../nav.jsp"/>
<div class="wrap">
    <section class="section">
    	<p class="introinfo"><img src="../../public/images/bookout_03.png">&nbsp;角色管理</p>
    	<div class="row">
    		<div class="col-md-4">
    			<div class="row" style="background: #1491d0;">
	    			<div class="col-md-6 text-left" style="color: #ffffff;">角色列表（共<span id="roleTotal" style="color:red;"> 0 </span>条记录）</div>
	    			<div class="col-md-6 text-right"><button class="btn btn-sm btn-primary" data-toggle="modal" data-target="#addRoleModal">添加</button></div>
    			</div>
    			<div class="row">
	    			<table>
	    				<thead>
		    				<tr>
		    					<th>序号</th>
		    					<th>角色</th>
		    					<th>操作</th>
		    				</tr>
	    				</thead>
	    				<tbody id="allRoles">
	    					
	    				</tbody>
	    			</table>
	    		</div>
    		</div>
    		<div class="col-md-1"></div>
    		<div class="col-md-7" style="border: 1px solid #1491d0;">
    			<div class="row" style="background: #1491d0;">
	    			<div class="col-md-6 text-left" style="color: #ffffff;">权限列表（共<span id="permissionTotal" style="color:red;"> 0 </span>条记录）&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" id="perSelectAll">&nbsp;全选</div>
	    			<div class="col-md-6 text-right"><button class="btn btn-sm btn-primary" id="setRolePermission">设置</button></div>
    			</div>
    			<div class="row" id="allPermission" style="padding: 20px;">
    				
    			</div>
    		</div>
    	</div>
        <!-- <div style="width: 1200px;">
            <div style="height: 100%; width: 30%; display: inline; float: left;">
                <div><p>角色列表</p></div>
                <div><button onclick="dspModal1()">添加</button></div>
                <div id="leftDiv">
                </div>
            </div>
                
            <div style="height: 100%; width: 50%; display: inline; float: left;">
                <div><p>权限列表</p></div>
                <div id="rightDiv">
                </div>
            </div>
        </div> -->
    </section>
</div>
<div class="footer">
    Copyright &copy; 2017 中国科学院上海光学精密机械研究所
</div>
<script src="../../public/js/jquery.min.js"></script>
<script src="../../public/resource/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script src="../../public/js/nav.js"></script>
<script src="../../public/js/roleMgn.js"></script>
<!-- <script src="../../public/js/moaModal.minified.js"></script>
<script src="../../public/js/Sweefty.js"></script> -->

<!-- addRoleModal -->
<div class="modal fade" id="addRoleModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">添加角色</h4>
      </div>
      <div class="modal-body">
        <div class="row" style="margin-top: 10px;">
            <div class="col-md-4 text-right" style="line-height: 35px;"><strong><span style="color:red;cursor:pointer;" title="必填">*</span>&nbsp;角色名：</strong></div>
            <div class="col-md-6 text-left"><input type="text" id="m-rolename" class="form-control"></div>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" id="saveAddOpt">提交</button>
      </div>
    </div>
  </div>
</div>

<!-- setUserModal -->
<div class="modal fade" id="addRoleModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">添加角色</h4>
      </div>
      <div class="modal-body">
        
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" id="saveSetUserOpt">提交</button>
      </div>
    </div>
  </div>
</div>
<!-- <div id="modal1" class="block modal2 hidden" style="background: #fff;height: 400px; width: 600px;">
    <div class="close" style="float: right;">
        ×
    </div>
    <div>
        <p>添加角色</p>
        <div>
            角色名: <input type="text" id="roleName">
            <div>
                <button onclick="addRoleSub()">提交</button>
            </div>
        </div>
    </div>
</div> -->


</body>
</html>