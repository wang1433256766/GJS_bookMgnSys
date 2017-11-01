$(function(){
	//获取url参数
	/*
	encodeURI()是Javascript中真正用来对URL编码的函数
	eg:
		编码：	Javascript:encodeURI("春节");
		解码:	Javascript:decodeURI("%E6%98%A5%E8%8A%82");
	*/
	(function ($) {
        $.getUrlParam = function (name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
            var r = window.location.search.substr(1).match(reg);
            if (r != null) return decodeURI(r[2]); return null;
        }
    })(jQuery);

    //简单检索
    var fid = $.getUrlParam('fid');
    var fdUid = "";

    //通过fid 获取反馈详情
    $.ajax({
    	type: 'GET',
    	url: '/getFeedback',
    	dataType: 'json',
    	data: {fid:fid},
    	success: function(res){
    		//console.log(res);
    		if(res.status == 0){
    			fdUid = res.data.fdUid;
    			$("#feedUser").text(res.data.uname);
    			$("#feedContent").text(res.data.content);
    			$("#feedDate").text(res.data.fdCreatetime);
    		}
    	}
    })

	//回复
	$("#answerBtn").click(function(){
		var adminContent = $("#answerContent").val();
		if(!adminContent){
			alert("请填写回复内容")
			return false;
		}
		$.ajax({
			type: 'POST',
			url: '/backFeedback',
			dataType: 'json',
			data: {adminContent:adminContent, isEmail:1, fid:fid, fdUid:fdUid},
			success: function(res){
				if(res.status == 0){
					self.location=document.referrer;//返回上一页并刷新
				}
				alert(res.msg);
			}
		})
	})
})