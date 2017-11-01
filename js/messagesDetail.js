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
    var nid = $.getUrlParam('nid');

    //获取通知详情
    $.ajax({
    	type: 'GET',
    	url: '/getNotify',
    	dataType: 'json',
    	data: {nid:nid},
    	success: function(res){
    		if(res.status == 0){
    			$("#titleContent").text(res.data.title);
    			$("#mainDate").text(res.data.createtime);
    			$("#mainContent").text(res.data.content);
    		}
    	}
    })
    
    //阅读通知
    $.ajax({
    	type: 'POST',
    	url: '/readNotify',
    	dataType: 'json',
    	data: {nids:nid},
    	success: function(res){
    		//console.log(res);
    	}
    })

    //返回
    $("#returnBack").click(function(){
    	self.location=document.referrer;//返回上一页并刷新
    }) 
})