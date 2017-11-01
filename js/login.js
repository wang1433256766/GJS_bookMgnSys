$(document).ready(function(){

	var user = $("#card");

	var pwd  = $("#password");

	
	$("#submit").click(function(){
		$.ajax({
			type:"POST",
			url:"/dologin",
			dataType:'json',
			data:{idcard:user.val(),pwd:pwd.val()},
			success:function(data){
				// console.log(data);return false;
				if(data.status==0){//success relocation
					if(data.data.length>0){
						window.location.href="/bookQuery/bookQuerySimple";
					}else{
						window.location.href="/bookQueryCus/bookQueryCusSimple";
					}
			    }else{
			    	alert(data.msg);
			    }
			}
		});
	});

	// $("#forgetPwd").click(function(){
	// 	window.location.href="./forgetPwd.html"
	// })

	//回车登录
	$(document).keydown(function(e) {
	    if (e.keyCode == "13") {//keyCode=13是回车键
	    	$('#submit').click();
	    }
	});
});