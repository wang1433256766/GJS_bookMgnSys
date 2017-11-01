<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用户管理</title>
    <link href="../../public/resource/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="../../public/css/common.css" rel="stylesheet" type="text/css"/>
    <link href="../../public/css/nav.css" rel="stylesheet" type="text/css"/>
    <link href="../../public/css/userMgn.css" rel="stylesheet" type="text/css"/>
    <link href="../../public/css/footer.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<jsp:include page="../nav.jsp"/>
<div class="wrap">
    <section class="section">
        <p class="introinfo"><img src="../../public/images/bookout_03.png">&nbsp;用户列表</p>
        <p class="text">
            <select name="" id="search-option" style="width:80px;height:22px;">
                <option value="">用户名</option>
            </select>
            <input type="text" id="search-key" placeholder="请输入关键字" style="width:390px;">
        </p>
        <p class="text">
            <select name="" id="sortBy" style="width:80px;height: 22px">
                <option value="u_id" selected="selected">用户ID</option>
                <option value="u_name">用户名</option>
                <option value="u_card">卡号</option>
                <option value="u_idcard">身份证</option>
                <option value="u_email">邮箱</option>
                <option value="u_phone">手机</option>
                <option value="u_tel">状态</option>
            </select>
            <input type="radio" name="sortType" value="desc" checked="checked">&nbsp;降序
            <input type="radio" name="sortType" value="asc">&nbsp;升序
            <button id="queryBycondition">查&nbsp;询</button>
        </p>
        <table>
            <thead>
                <tr>
                    <th>序号</th>
                    <th>用户名</th>
                    <th>一卡通</th>
                    <th>身份证</th>
                    <th>邮箱</th>
                    <th>手机</th>
                    <th>座机</th>
                    <th>状态</th>
                    <th>角色</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                
            </tbody>
        </table>
        <div class="selfPage">
            <input type="image" id="first-page" src="../../public/images/booklist-4.png" title="首页">
            <input type="image" id="pre-page" src="../../public/images/booklist-1.png" title="上一页">
            <!-- <img id="first-page" src="../../public/images/booklist-4.png" title="首页">
            <img id="pre-page" src="../../public/images/booklist-1.png" title="上一页"> -->
            <input id="page-num" type="text" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9]+/,'');}).call(this)" onblur="this.v();" style="width:30px;"/>&nbsp;共&nbsp;<span id="total-page"></span>&nbsp;页
            <input type="image" id="next-page" src="../../public/images/booklist-2.png" title="下一页">
            <input type="image" id="last-page" src="../../public/images/booklist-3.png" title="尾页">
            <!-- <img id="next-page" src="../../public/images/booklist-2.png" title="下一页">
            <img id="last-page" src="../../public/images/booklist-3.png" title="尾页"> -->
            <span class="countNum">总记录数为：<label id="totalNum"></label></span>
        </div>
    </section>
</div>
<div class="footer">
    Copyright &copy; 2017 中国科学院上海光学精密机械研究所
</div>
<script src="../../public/js/jquery.min.js"></script>
<script src="../../public/resource/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script src="../../public/js/nav.js"></script>
<script src="../../public/js/userMgn.js"></script>

<!-- userInfoModal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">修改用户</h4>
      </div>
      <div class="modal-body">
      <input type="hidden" id="m-uid">
        <div class="row" style="margin-top: 10px;">
            <div class="col-md-4 text-right" style="line-height: 35px;"><strong>用户名：</strong></div>
            <div class="col-md-6 text-left"><input type="text" id="m-uname" class="form-control" disabled></div>
        </div>
        <div class="row" style="margin-top: 10px;">
            <div class="col-md-4 text-right" style="line-height: 35px;"><strong>卡号：</strong></div>
            <div class="col-md-6 text-left"><input type="text" id="m-card" class="form-control" disabled></div>
        </div>
        <div class="row" style="margin-top: 10px;">
            <div class="col-md-4 text-right" style="line-height: 35px;"><strong>身份证：</strong></div>
            <div class="col-md-6 text-left"><input type="text" id="m-idcard" class="form-control" disabled></div>
        </div>
        <div class="row" style="margin-top: 10px;">
            <div class="col-md-4 text-right" style="line-height: 35px;"><strong>邮箱：</strong></div>
            <div class="col-md-6 text-left"><input type="text" id="m-email" class="form-control"></div>
        </div>
        <div class="row" style="margin-top: 10px;">
            <div class="col-md-4 text-right" style="line-height: 35px;"><strong>手机：</strong></div>
            <div class="col-md-6 text-left"><input type="text" id="m-phone" class="form-control"></div>
        </div>
        <div class="row" style="margin-top: 10px;">
            <div class="col-md-4 text-right" style="line-height: 35px;"><strong>座机：</strong></div>
            <div class="col-md-6 text-left"><input type="text" id="m-tel" class="form-control"></div>
        </div>
        <div class="row" style="margin-top: 10px;">
            <div class="col-md-4 text-right" style="line-height: 35px;"><strong>状态：</strong></div>
            <div class="col-md-6 text-left">
                <select class="form-control" id="m-status">
                    <option value="0">可用</option>
                    <option value="1">解除合同离所</option>
                    <option value="2">退休</option>
                    <option value="3">毕业离所</option>
                    <option value="4">违规停用</option>
                    <option value="5">其他原因停用</option>
                    <option value="99">不可用</option>
                </select>
            </div>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" id="saveUserInfo">保存</button>
      </div>
    </div>
  </div>
</div>

<!-- setRoleModal -->
<div class="modal fade" id="setRoleModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">设置角色</h4>
      </div>
      <input type="hidden" id="setRoleModal-uid">
      <div class="modal-body">

      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" id="submitOpt">提交</button>
      </div>
    </div>
  </div>
</div>

<!-- <div id="modal1" class="block modal2 hidden" style="background: #fff;height: 400px; width: 600px;">
    <div class="close" style="float: right;">
        ×
    </div>
    <div id="userInfoTable">
        <p>用户名<input type="text" name="uname" id="uname"></p>
        <p>邮箱<input type="text" name="email" id="email"></p>
        <p>卡号<input type="text" name="card" id="card"></p>
        <p>状态<input type="text" name="status" id="status"></p>
        <p>类型<input type="text" name="type" id="type"></p>
        <p><input type="hidden" name="uid" id="uid"></p>
        <p>
            <button id="updateSubmit" onclick="userSub()">提交</button>
    </div>
</div>

<div id="modal2" class="block modal2 hidden" style="background: #fff;height: 400px; width: 600px;">
    <div class="close" style="float: right;">
        ×
    </div>
    <form id="rolesTable">

    </form>
</div> -->
</body>
</html>