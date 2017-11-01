$(function(){
    //获取所有角色
    getAllRoles();

    //获取所有权限
    getAllPermisssion();

    //默认选中角色的第一行
    if(parseInt($("#roleTotal").text())>0){
	    $('#allRoles tr:eq(0)').addClass('trcolor');
	    $.ajax({
	    	type: 'GET',
	    	url: '/getPermisssionOfRole',
	    	dataType: 'json',
	    	data: {rid:$('#allRoles tr:eq(0)')[0].id.substring(5)},
	    	success: function(res){
	    		//console.log(res);
	    		if(res.status == 0){
	    			$(".perCheckbox").prop('checked',false);
	    			if(res.data && res.data.length>0){
	    				for(var i=0;i<res.data.length;i++){
	    					$("#checkbox_"+res.data[i].pid).prop('checked',true);
	    				}
	    			}
	    		}
	    	}
	    })
    }

    //选中角色行事件
    $("#allRoles tr").click(function() {
        $(this).addClass("trcolor").siblings().removeClass("trcolor");
        var rid = this.id.substring(5);
        //获取该选中角色的权限
        $.ajax({
        	type: 'GET',
        	url: '/getPermisssionOfRole',
        	dataType: 'json',
        	data: {rid:rid},
        	success: function(res){
        		//console.log(res);
        		if(res.status == 0){
        			$(".perCheckbox").prop('checked',false);
        			if(res.data && res.data.length>0 && res.data[0]){
        				for(var i=0;i<res.data.length;i++){
        					$("#checkbox_"+res.data[i].pid).prop('checked',true);
        				}
        			}
        		}
        	}
        })
    });

    //权限全选操作
    $("#perSelectAll").click(function(){
    	if($("#perSelectAll").is(':checked')){
    		$(".perCheckbox").prop('checked',true);
    	}else{
    		$(".perCheckbox").prop('checked',false);
    	}
    })

    //设置角色的权限
    $("#setRolePermission").click(function(){
    	if(parseInt($("#roleTotal").text())<=0){
    		alert("当前没有角色");return false;
    	}
    	//得到当前的rid
    	var rid = $("#allRoles .trcolor")[0].id.substring(5);
    	//得到pids
    	var pids = "";
    	var checkObj = $(".perCheckbox");
        for(var i=0;i<checkObj.length;i++){
            if($(checkObj[i]).is(':checked')){
                pids += checkObj[i].id.substring(9)+',';
            }
        }
        pids = pids.substring(0,pids.length-1);
        $.ajax({
            type: 'POST',
            url: '/changeRoleOfPmtion',
            dataType: 'json',
            data: {rid:rid,pids:pids},
            success: function(res){
                if(res.status == 0){
                	console.log('success');
                }
                alert(res.msg);
            }
        })
    })

    //添加角色
    $("#saveAddOpt").click(function(){
    	var rolename = $("#m-rolename").val();
    	if(!rolename){
    		alert("请填写角色名");return false;
    	}
    	$.ajax({
    		type: 'POST',
    		url: '/addRole',
    		dataType: 'json',
    		data: {rname:rolename},
    		success: function(res){
    			if(res.status == 0){
    				window.location.reload();
    			}
    			alert(res.msg);
    		}
    	})
    })

})

//获取所有角色 //'<button class="btn btn-xs btn-primary" onclick="openUserModal('+res.data[i].rid+')">分配用户</button>'+
function getAllRoles(){
	var content = "";
	$.ajax({
		async: false,
		type: 'GET',
		url: '/getAllRoles',
		dataType: 'json',
		data: {},
		success: function(res){
			//console.log(res);
			if(res.status == 0){
				if(res.data && res.data.length>0){
					$("#roleTotal").text(res.data.length);
					for(var i=0;i<res.data.length;i++){
						content += '<tr id="role_'+res.data[i].rid+'">'+
		    						'<td>'+res.data[i].rid+'</td>'+
		    						'<td>'+res.data[i].rname+'</td>'+
		    						'<td>'+
			    						'<button class="btn btn-xs btn-primary" onclick="deleteRole('+res.data[i].rid+')">删除</button>&nbsp;'+
		    						'</td>'+
		    					'</tr>';
		    		}
				}
				$("#allRoles").html(content);
			}
		}
	})
}

//获取所有权限
function getAllPermisssion(){
	var content = "";
	$.ajax({
		async: false,
		type: 'GET',
    	url: '/getAllPermisssion',
    	dataType: 'json',
    	data: '',
    	success: function(res){
    		//console.log(res);
    		if(res.status == 0){
    			if(res.data && res.data.length>0){
    				$("#permissionTotal").text(res.data.length);
    				for(var i=0;i<res.data.length;i++){
    					content += '<div class="col-md-6">'+
				    					'<input type="checkbox" class="perCheckbox" id="checkbox_'+res.data[i].pid+'">&nbsp;'+res.data[i].mark+''+
				    			   '</div>';
    				}
    			}
    			$("#allPermission").html(content);
    		}
    	}
	})
}

//删除角色
function deleteRole(rid){
	$.ajax({
		type: 'POST',
		url: '/deleteRole',
		dataType: 'json',
		data: {rid:rid},
		success: function(res){
			if(res.status == 0){
				window.location.reload();
			}
			alert(res.msg);
		}
	})
}

// var roleList;
// var currentRid;
// $(function(){
// 	var leftDiv = '';
// 	var rightDiv = '';

// 	$.ajax({
//     	type: 'GET',
//     	url: '/getAllRoles',
//     	dataType: 'json',
//     	async: false,
//     	data: '',
//     	success: function(result){
//     		roleList = result.data;
//             currentRid = result.data[0].rid;
//     		for(var i in result.data){
//     			leftDiv += "<p onclick='getPer(" + result.data[i].rid + ")'>" + result.data[i].rname  + "</p>";
//     		}
//     		$("#leftDiv").html(leftDiv);
//     	}
//     });

// 	$.ajax({
//     	type: 'GET',
//     	url: '/getAllPermisssion',
//     	dataType: 'json',
//     	async: false,
//     	data: '',
//     	success: function(result){
//     		for(var i in result.data){
//     			rightDiv += "<p><input type='checkbox' id='per-" + result.data[i].pid + "'></input>" + result.data[i].pname  + "</p>";
//     		}
//             rightDiv += "<button onclick='updatePer()'>修改</button>";
//     		$("#rightDiv").html(rightDiv);
//     	}
//     });

//     $.ajax({
//     	type: 'GET',
//     	url: '/getPermisssionOfRole',
//     	dataType: 'json',
//     	async: false,
//     	data: 'rid=' + roleList[0].rid,
//     	success: function(result){
//             $("#rightDiv").children("p").children("input").each(function() {
//                 $(this).prop("checked", false);
//             });
//     		for(var i in result.data){
//     			$("#rightDiv").children("p").children("input").each(function() {
//     				if(($(this).attr("id").split('-'))[1] == result.data[i].pid){
//                         $(this).prop("checked", true);
//                     }
//     			});
//     		}
//     	}
//     });
// });

// function getPer(rid) {
//     currentRid = rid;

// 	$.ajax({
//     	type: 'GET',
//     	url: '/getPermisssionOfRole',
//     	dataType: 'json',
//     	data: 'rid=' + rid,
//     	success: function(result){
// 			$("#rightDiv").children("p").children("input").each(function() {
//                 $(this).prop("checked", false);
//             });

//     		for(var i in result.data){
//     			$("#rightDiv").children("p").children("input").each(function() {
//     				if(($(this).attr("id").split('-'))[1] == result.data[i].pid) {
//     					$(this).prop("checked", true);
//     				}
//     			});
//     		}
//     	}
//     });
// }

// function dspModal1() {
// 	$('#modal1').modal('view',{

//         speed : 600,

//         easing : 'easeInOutElastic',

//         animation : 'top height width',

//         position: '10% auto',

//         overlayClose : false,

//         on : 'click',

//         overlayColor : '#ccc',

//         overlayOpacity : .9,

//         close : '.close'

//     });

// }

// function addRoleSub() {
// 	$data = "rname=" + $("#roleName").val();
//     $.ajax({
//     	type: 'POST',
//     	url: '/addRole',
//     	dataType: 'json',
//     	data: $data,
//     	success: function(result){
// 			location.reload();
//     		alert(result.msg);
//     	}
//     });
// }

// function updatePer() {
//     $data = 'rid=' + currentRid + "&pids=";
//     $("#rightDiv").children("p").children("input").each(function() {
//         if($(this).prop("checked") == true) {
//             var num = ($(this).attr("id").split('-'))[1];
//             $data = $data + num + ',';
//         }
//     });
//     $data = $data.substr(0, $data.length-1);

//     $.ajax({
//         type: 'POST',
//         url: '/changeRoleOfPmtion',
//         dataType: 'json',
//         data: $data,
//         success: function(result){
//             location.reload();
//             alert(result.msg);
//         }
//     });
// }

