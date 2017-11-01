$(function(){
	//发送系统通知
	$("#send").click(function(){
		var mainTitle = $("#mainTitle").val();
		var mainContent = $("#mainContent").val();
		if(!mainTitle || !mainContent){
			alert('请填写主题和正文');
			return false;
		}
		$.ajax({
			type: 'POST',
			url: '/sendNoti',
			dataType: 'json',
			data: {title:mainTitle,context:mainContent},
			success: function(res){
				if(res.status == 0){
					window.location.href = "/sysConfig/sysNotificationList";
				}
				alert(res.msg);
			}
		})
	})
})