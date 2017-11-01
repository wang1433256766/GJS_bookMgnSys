$(function(){
    var permissionArr = [];
    //判断是否登录
    $.ajax({
        async: false,
        type: 'GET',
        url: '/isLogin',
        cache: false,
        dataType: 'json',
        data: {},
        success: function(res){
            if(res.status == 0){
                var data = eval('(' + res.data + ')');
                permissionArr = data.permission.split(',');
            }
        }
    })

    var searchKey = $("#search-key").val();
    var sortBy = $("#sortBy option:selected").val();
    var sortType = $("input[name='sortType']:checked").val();

    //获取用户初始列表
    getUserList(searchKey,sortBy,sortType,1);
    controlPage($("#page-num").val(),$("#total-page").text(),$("#first-page"),$("#pre-page"),$("#next-page"),$("#last-page"));

    //按条件查询用户
    $("#queryBycondition").click(function(){
        searchKey = $("#search-key").val();
        sortBy = $("#sortBy option:selected").val();
        sortType = $("input[name='sortType']:checked").val();
        getUserList(searchKey,sortBy,sortType,1);
        controlPage($("#page-num").val(),$("#total-page").text(),$("#first-page"),$("#pre-page"),$("#next-page"),$("#last-page"));
    })

    //回车查询
    $("body").keydown(function() {
        if (event.keyCode == "13") {//keyCode=13是回车键
            $('#queryBycondition').click();
        }
    });

    //分页
    $("#first-page").click(function(){ //首页
        getUserList(searchKey,sortBy,sortType,1);
        controlPage($("#page-num").val(),$("#total-page").text(),$("#first-page"),$("#pre-page"),$("#next-page"),$("#last-page"));
    })
    $("#last-page").click(function(){ //尾页
        var page = $("#total-page").text();
        getUserList(searchKey,sortBy,sortType,page);
        controlPage($("#page-num").val(),$("#total-page").text(),$("#first-page"),$("#pre-page"),$("#next-page"),$("#last-page"));
    })
    $("#pre-page").click(function(){ //上一页
        if(!parseInt($("#page-num").val()) || parseInt($("#page-num").val())>parseInt($("#total-page").text()) || parseInt($("#page-num").val())<=1){
            alert('请保证当前页在合理范围内');
            return false;
        }
        var page = parseInt($("#page-num").val()) - 1;
        getUserList(searchKey,sortBy,sortType,page);
        controlPage($("#page-num").val(),$("#total-page").text(),$("#first-page"),$("#pre-page"),$("#next-page"),$("#last-page"));
    })
    $("#next-page").click(function(){ //下一页
        if(!parseInt($("#page-num").val()) || parseInt($("#page-num").val())>parseInt($("#total-page").text()-1) || parseInt($("#page-num").val())<=0){
            alert('请保证当前页在合理范围内');
            return false;
        }
        var page = parseInt($("#page-num").val()) + 1;
        getUserList(searchKey,sortBy,sortType,page);
        controlPage($("#page-num").val(),$("#total-page").text(),$("#first-page"),$("#pre-page"),$("#next-page"),$("#last-page"));
    })
    $("#page-num").keyup(function(e){
        if(e.keyCode == 13){
            var page = parseInt($("#page-num").val());
            if(!page || page>parseInt($("#total-page").text()) || page<=0){
                alert("输入的页数不对");
                return false;
            }
            getUserList(searchKey,sortBy,sortType,page);
            controlPage($("#page-num").val(),$("#total-page").text(),$("#first-page"),$("#pre-page"),$("#next-page"),$("#last-page"));
        }
    })

    //确认修改用户信息
    $("#saveUserInfo").click(function(){
        var uid = $("#m-uid").val();
        var uname = $("#m-uname").val();
        var card = $("#m-card").val();
        var idcard = $("#m-idcard").val();
        var email = $("#m-email").val();
        var phone = $("#m-phone").val();
        var tel = $("#m-tel").val();
        var status = $("#m-status").val();
        $.ajax({
            type: 'POST',
            url: '/userUpdate',
            dataType: 'json',
            data: {uid:uid,uname:uname,card:card,idcard:idcard,email:email,phone:phone,tel:tel,status:status},
            success: function(res){
                if(res.status == 0){
                    $('#myModal').modal('hide');
                    getUserList(searchKey,sortBy,sortType,1);
                    controlPage($("#page-num").val(),$("#total-page").text(),$("#first-page"),$("#pre-page"),$("#next-page"),$("#last-page"));
                }
                alert(res.msg);
            }
        })
    })

    //确认设置用户角色
    $("#submitOpt").click(function(){
        var uid = $("#setRoleModal-uid").val();
        var rids = "";
        var checkObj = $(".roleCheckbox");
        for(var i=0;i<checkObj.length;i++){
            if($(checkObj[i]).is(':checked')){
                rids += checkObj[i].id.substring(9)+',';
            }
        }
        rids = rids.substring(0,rids.length-1);
        $.ajax({
            type: 'POST',
            url: '/addRoles4User',
            dataType: 'json',
            data: {uid:uid,rids:rids},
            success: function(res){
                if(res.status == 0){
                    $('#setRoleModal').modal('hide');
                    getUserList(searchKey,sortBy,sortType,1);
                    controlPage($("#page-num").val(),$("#total-page").text(),$("#first-page"),$("#pre-page"),$("#next-page"),$("#last-page"));
                }
                alert(res.msg);
            }
        })
    })

})

//获取用户列表
function getUserList(searchKey,sortBy,sortType,page,permissionArr){
    var tbodyHtml = "";
    $.ajax({
        async: false,
        type: 'GET',
        url: '/getAllUser',
        dataType: 'json',
        data: {uname:searchKey,sidx:sortBy,sord:sortType,page:page,rows:10},
        success: function(res){
            if(res.status == 0){
                if(res.data.userInfoList && res.data.userInfoList.length>0){
                    $("#totalNum").text(res.data.allRows);
                    $("#page-num").val(res.data.page);
                    var totalPage = Math.ceil(res.data.allRows/10);
                    $("#total-page").text(totalPage);
                    var statusStr = '<label class="label label-success">正常</label>';
                    for(var i=0; i<res.data.userInfoList.length; i++){
                        if(res.data.userInfoList[i].status == 1){
                            statusStr = '<label class="label label-danger">解除合同离所</label>';
                        }else if(res.data.userInfoList[i].status == 2){
                            statusStr = '<label class="label label-danger">退休</label>';
                        }else if(res.data.userInfoList[i].status == 3){
                            statusStr = '<label class="label label-danger">毕业离所</label>';
                        }else if(res.data.userInfoList[i].status == 4){
                            statusStr = '<label class="label label-danger">违规停用</label>';
                        }else if(res.data.userInfoList[i].status == 5){
                            statusStr = '<label class="label label-danger">其他原因停用</label>';
                        }else if(res.data.userInfoList[i].status == 99){
                            statusStr = '<label class="label label-danger">不可用</label>';
                        }else{
                            statusStr = '<label class="label label-success">正常</label>';
                        }
                        var roleStrPer = res.data.userInfoList[i].roleStr;
                        if(roleStrPer){
                            roleStrPer = roleStrPer.substring(0,roleStrPer.length-2);
                        }
                        
                        tbodyHtml += '<tr>'+
                                        '<td>'+ (Number(i) + 1) +'</td>'+
                                        '<td>'+ res.data.userInfoList[i].uname +'</td>'+
                                        '<td>'+ res.data.userInfoList[i].card +'</td>'+
                                        '<td>'+ res.data.userInfoList[i].idcard +'</td>'+
                                        '<td>'+ res.data.userInfoList[i].email +'</td>'+
                                        '<td>'+ res.data.userInfoList[i].phone +'</td>'+
                                        '<td>'+ res.data.userInfoList[i].tel +'</td>'+
                                        '<td>'+ statusStr +'</td>'+
                                        '<td>'+ roleStrPer +'</td>'+
                                        '<td>'+
                                            '<button class="btn btn-xs btn-primary" onclick="getUserInfo('+res.data.userInfoList[i].card+')" data-toggle="modal" data-target="#myModal">编辑</button>&nbsp;'+
                                            '<button class="btn btn-xs btn-primary userRoleControl" onclick="setUserRole('+res.data.userInfoList[i].uid+')" data-toggle="modal" data-target="#setRoleModal">设置角色</button>'+
                                        '</td>'+
                                    '</tr>';
                    }
                    $(".wrap tbody").html(tbodyHtml);
                }
            }
        }
    })
    if($.inArray('userRole', permissionArr) == -1){
        $(".userRoleControl").addClass("hidden");
    }else{
        $(".userRoleControl").removeClass("hidden");
    }
}

//获取用户信息
function getUserInfo(card){
    $.ajax({
        type: 'GET',
        url: '/getUserInfoByCard',
        dataType: 'json',
        data: {card:card},
        success: function(res){
            if(res.status == 0){
                $("#m-uid").val(res.data.user.uid);
                $("#m-uname").val(res.data.user.uname);
                $("#m-card").val(res.data.user.card);
                $("#m-idcard").val(res.data.user.idcard);
                $("#m-email").val(res.data.user.email);
                $("#m-phone").val(res.data.user.phone);
                $("#m-tel").val(res.data.user.tel);
                $("#m-status").val(res.data.user.status);
            }
        }
    })
}

/**
 * [setUserRole description] 获取所有角色，获取用户的角色，在所有角色内将用户的角色打勾
 * @param {[type]} card [description] 用户一卡通
 */
function setUserRole(uid){
    $("#setRoleModal-uid").val(uid);
    //获取所有角色
    var content = "";
    $.ajax({
        async: false,
        type: 'GET',
        url: '/getAllRoles',
        dataType: 'json',
        data: {},
        success: function(res){
            if(res.status == 0){
                if(res.data && res.data.length>0){
                    for(var i=0;i<res.data.length;i++){
                        content += '<div class="row text-center"><div class="col-md-10"><input type="checkbox" class="roleCheckbox" id="checkbox_'+res.data[i].rid+'">&nbsp;'+res.data[i].rname+'</div></div>';
                    }
                    $("#setRoleModal .modal-body").html(content);
                }
            }
        }
    })
    //获取用户角色
    $.ajax({
        type: 'GET',
        url: '/getRole',
        dataType: 'json',
        data: {uid:uid},
        success: function(res){
            //console.log(res);
            if(res.status == 0){
                $(".roleCheckbox").prop('checked',false);
                if(res.data && res.data.length>0){
                    for(var i=0; i<res.data.length; i++){
                        $("#checkbox_"+res.data[i].rid).prop('checked',true);
                    }
                }
            }
        }
    })
}

//控制分页按钮
function controlPage(currentPage,totalPage,firstPage,prePage,nextPage,lastPage){
    if(currentPage&&totalPage){
        if(totalPage==1){
            firstPage.attr('disabled',true);
            prePage.attr('disabled',true);
            nextPage.attr('disabled',true);
            lastPage.attr('disabled',true);
        }else{
            if(currentPage==1){
                firstPage.attr('disabled',true);
                prePage.attr('disabled',true);
                nextPage.attr('disabled',false);
                lastPage.attr('disabled',false);
            }else if(currentPage == totalPage){
                firstPage.attr('disabled',false);
                prePage.attr('disabled',false);
                nextPage.attr('disabled',true);
                lastPage.attr('disabled',true);
            }else{
                firstPage.attr('disabled',false);
                prePage.attr('disabled',false);
                nextPage.attr('disabled',false);
                lastPage.attr('disabled',false);
            }
        }
    }
}

// var userList;
// $(function () {
//     var tbodyHtml = "";
//     $.ajax({
//         type: 'GET',
//         url: '/getAllUser',
//         dataType: 'json',
//         data: 'sidx=u_id&sord=desc&rows=10&page=1',
//         success: function (result) {
//             userList = result.data.userInfoList;
//             for (var i in result.data.userInfoList) {
//                 var userStatus;
//                 if (result.data.userInfoList[i].status == 0) {
//                     userStatus = '禁用';
//                 }
//                 if (result.data.userInfoList[i].status == 1) {
//                     userStatus = '启用';
//                 }
//                 tbodyHtml += "<tr><td>" + (Number(i) + 1) + "</td>"
//                     + "<td>" + result.data.userInfoList[i].uname + "</td>"
//                     + "<td>" + result.data.userInfoList[i].card + "</td>"
//                     + "<td>" + result.data.userInfoList[i].email + "</td>"
//                     + "<td>" + result.data.userInfoList[i].status + "</td>"
//                     + "<td>" + result.data.userInfoList[i].roleStr.substr(0, result.data.userInfoList[i].roleStr.length - 2) + "</td>"
//                     + "<td><button onclick='updateuser(" + i + ")'>修改</button><button onclick='changeStatus(" + i + ")'>" + userStatus + "</button><button onclick='updateRole(" + i + ")'>角色</button></td><tr>";
//             }
//             $("tbody").html(tbodyHtml);
//         }
//     })

//     var roleHtml = "";
//     $.ajax({
//         type: 'GET',
//         url: '/getAllRoles',
//         dataType: 'json',
//         data: '',
//         success: function (result) {
//             for (var i in result.data) {
//                 roleHtml += "<p>" + result.data[i].rname + "<input type='checkbox' id='role-" + result.data[i].rid + "'></input></p>";
//             }
//             roleHtml += "<input type='hidden' id='role-uid'></input><button type='button' onclick='roleSubmit()'>提交</button>"
//             $("#rolesTable").html(roleHtml);
//         }
//     })
// });

// function updateuser(id) {
//     $('#uname').attr("value", userList[id].uname);
//     $('#email').attr("value", userList[id].email);
//     $('#card').attr("value", userList[id].card);
//     $('#status').attr("value", userList[id].status);
//     $('#type').attr("value", userList[id].type);
//     $('#uid').attr("value", userList[id].uid);

//     $('#modal1').modal('view', {

//         speed: 600,

//         easing: 'easeInOutElastic',

//         animation: 'top height width',

//         position: '10% auto',

//         overlayClose: false,

//         on: 'click',

//         overlayColor: '#ccc',

//         overlayOpacity: .9,

//         close: '.close'

//     });
// }

// function userSub() {
//     $uname = $('#uname').val();
//     $email = $('#email').val();
//     $card = $('#card').val();
//     $status = $('#status').val();
//     $type = $('#type').val();
//     $uid = $('#uid').val();

//     $data = "uname=" + $uname
//         + "&email=" + $email
//         + "&card=" + $card
//         + "&status=" + $status
//         + "&type=" + $type
//         + "&uid=" + $uid;

//     $.ajax({
//         type: 'POST',
//         url: '/userUpdate',
//         dataType: 'json',
//         data: $data,
//         success: function (result) {
//             location.reload();
//             alert(result.msg);
//         },
//     })
// }

// function changeStatus(id) {
//     var changed;
//     if (userList[id].status == 0) {
//         changed = 1;
//     }
//     if (userList[id].status == 1) {
//         changed = 0;
//     }

//     $data = "status=" + changed + "&uid=" + userList[id].uid;
//     $.ajax({
//         type: 'POST',
//         url: '/userUpdate',
//         dataType: 'json',
//         data: $data,
//         success: function (result) {
//             location.reload();
//             alert(result.msg);
//         },
//     })
// }

// function updateRole(id) {
//     $("#role-uid").val(userList[id].uid);

//     $data = "uid=" + userList[id].uid;
//     $.ajax({
//         type: 'GET',
//         url: '/getRole',
//         dataType: 'json',
//         data: $data,
//         success: function (result) {
//             $("#rolesTable").children("p").children("input").each(function () {
//                 $(this).prop("checked", false);
//             });

//             for (var i in result.data) {
//                 $("#rolesTable").children("p").children("input").each(function () {
//                     if (($(this).attr("id").split('-'))[1] == result.data[i].rid) {
//                         var checked = '#role-' + result.data[i].rid;
//                         $(checked).prop("checked", true);
//                     }
//                 });
//             }
//         },
//     });

//     $('#modal2').modal('view', {

//         speed: 600,

//         easing: 'easeInOutElastic',

//         animation: 'top height width',

//         position: '10% auto',

//         overlayClose: false,

//         on: 'click',

//         overlayColor: '#ccc',

//         overlayOpacity: .9,

//         close: '.close'

//     });
// }

// function roleSubmit() {
//     $data = "uid=" + $("#role-uid").val() + "&rids=";
//     $("#rolesTable").children("p").children("input").each(function () {
//         //console.log($(this).prop("checked"));
//         if ($(this).prop("checked") == true) {
//             var num = ($(this).attr("id").split('-'))[1];
//             //console.log(num);
//             $data = $data + num + ',';
//         }
//     });
//     $data = $data.substr(0, $data.length - 1);
//     //console.log($data);
//     $.ajax({
//         type: 'POST',
//         url: '/addRoles4User',
//         dataType: 'json',
//         data: $data,
//         success: function (result) {
//             location.reload();
//             alert(result.msg);
//         },
//     });
// }

