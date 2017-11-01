$(function(){
	$("#send").click(function(){
		var content = $("#suggestionContent").val();
		if(!content){
			alert("请填写建议内容");
			return false;
		}
		$.ajax({
			type: 'POST',
			url: '/addFeedback',
			dataType: 'json',
			data: {content:content},
			success: function(res){
				alert(res.msg);
				if(res.status == 0){
					window.location.reload();
				}
			}
		})
	})
})