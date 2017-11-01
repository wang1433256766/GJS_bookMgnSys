$(function(){ 
    //导航条切换
    var $li = $('.nav>ul>li');
    $li.bind('mouseover', function(){
        var $this = $(this);
        var $t = $this.index();
        $this.find('.sed-nav').css('display','block');
    }).bind('mouseleave', function(){
        var $this = $(this);
        var $t = $this.index();
        $this.find('.sed-nav').css('display','none');
    }) 

    var urlName = window.location.pathname;
    //console.log(urlName);
    if(urlName.indexOf('bookQueryCus')>0){
        $("#nav-bookQueryCus").addClass("active");
    }else if(urlName.indexOf('recommendationCus')>0){
        $("#nav-recommendationCus").addClass("active");
    }else if(urlName.indexOf('myBooksInfo')>0){
        $("#nav-myBooksInfo").addClass("active");
    }else if(urlName.indexOf('suggestion')>0){
        $("#nav-suggestion").addClass("active");
    }

	var rightContent = '<a href="/login">登录</a>';

    //判断是否登录
    $.ajax({
        type: 'GET',
        url: '/isLogin',
        cache: false,
        dataType: 'json',
        data: {},
        success: function(res){
            if(res.status == 0){
                var data = eval('(' + res.data + ')');
                $("#login-name").text(data.name);
            }else{
                $(".login").html('');
                $(".login").html(rightContent);
            }
        }
    })
	//判断是否登录
	// $.get('/isLogin',function(res){
 //        //console.log(res);
 //        if(res.status == 0){
 //            var data = eval('(' + res.data + ')');
 //            $("#login-name").text(data.name);
 //        }else{
 //            $(".login").html('');
 //            $(".login").html(rightContent);
 //        }
 //    })

	//登出
    $("#logout").click(function(){
         // $.get('/logout',function (data) {
         //    //delCookie("email");
         //    window.location.href = '/login';
         // })
         $.ajax({
            type: 'GET',
            url: '/logout',
            cache: false,
            dataType: 'json',
            data: {},
            success: function(res){
                window.location.href = '/login';
            }
         })
    })
})

/**
 * 返回指定format的string
 * format eg:'yyyy-MM-dd hh:mm:ss'
 **/
Date.prototype.format = function(format) {
    var o = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),
        "S": this.getMilliseconds()
    }
    if (/(y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return format;
}

function foo(str){
    str ='0000000'+str;
    return str.substring(str.length-7,str.length);
}