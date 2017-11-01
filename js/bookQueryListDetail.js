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

    var bid = $.getUrlParam('bid');

	getBookInfoByBid(bid);

    //录入
    $("#book-add").click(function(){
    	window.location.href = "/bookMgn/bookAdd?bid="+bid;
    })

    //判断是否登录
    $.get('/isLogin',function(res){
        //console.log(res);
        if(res.status == 0){
            var data = eval('(' + res.data + ')');
            var permissionArr = data.permission.split(',');
            if($.inArray('insert', permissionArr) == -1){
                $("#book-add").addClass("hidden");
            }else{
                $("#book-add").removeClass("hidden");
            }
        }
    })

    //关注
    $("#focusBook").click(function(){
        $.ajax({
            type: 'POST',
            url: '/addFollow',
            dataType: 'json',
            data: {foBeid:bid},
            success: function(res){
                //console.log(res);
                if(res.status == 0){
                    $("#focusBook").addClass('hidden');
                }
                alert(res.msg);
            }
        })
    })
});

function getBookInfoByBid(bid){
    var html = "";
    $.ajax({
        type: 'GET',
        url: '/getBookInfoByBid',
        dataType: 'json',
        data: {bid:bid},
        success: function(res){
            console.log(res);
            if(res.status == 0){
                if(res.data.canFollow == 2){
                    $("#focusBook").removeClass('hidden');
                }else{
                    $("#focusBook").addClass('hidden');
                }
                var available = 0;
                var bookData = res.data.book;
                var bookEntity = res.data.bookEntityList;
                if(bookData.cover){
                    var imgcontent = '<div class="content-right"><img class="img" src="../../public/cover/'+bookData.cover+'"></div>';
                    $(".book-cover").html(imgcontent);
                }
                $("#bookName").text(bookData.name);
                $("#foreignName").text(bookData.foreignName);
                $("#bookAuthor").text(bookData.author);
                $("#bookPublish").text(bookData.publisher);
                $("#bookYear").text(bookData.pubtime);
                $("#bookIsbn").text(bookData.isbn);
                $("#bookCategory").text(bookData.claimNumber);
                $("#bookLanguge").text(bookData.lang);
                $("#bookPage").text(bookData.pages);
                $("#bookContent").text(bookData.key);
                $("#bookTotal").text(bookEntity.length);
                //丛书如果有则显示
                if(bookData.series){
                    if(bookData.volume){
                        $("#seriesBook").text(bookData.series+'('+bookData.volume+')');
                    }else{
                        $("#seriesBook").text(bookData.series);
                    }
                }else{
                    $(".ser_ts").addClass('hidden');
                }
                //价格如果有则显示
                if(bookData.price){
                    $("#bookPrice").text(bookData.price);
                }else{
                    $(".pri_ts").addClass('hidden');
                }
                var bookUrl = "";
                if(bookData.asterisk){
                    bookUrl += '<p>超星全文：<a target="_blank" href="http://210.72.9.23:8082/markbook/GoBook.jsp?BID='+bookData.asterisk+'">'+bookData.asterisk+'</a></p>';
                }
                if(bookData.ebook){
                    var Str_ebook = bookData.ebook;
                    var engE = Str_ebook.match(/^[a-z|A-Z]+/gi); 
                    var numE = Str_ebook.match(/\d+$/gi);
                    Str_ebook = 'EB'+numE+engE;
                    bookUrl += '<p>本馆电子书：<a target="_blank" href="http://book.optics.org.cn/Book/Details/'+Str_ebook+'">'+Str_ebook+'</a></p>';
                }
                if(bookData.chapter){
                    bookUrl += '<p>章节阅读：<a target="_blank" href="http://www.optics.org.cn/web/search?channelid=114677&extrasql=%E5%9B%BE%E4%B9%A6%E4%BB%A3%E7%A0%81%3D'+bookData.chapter+'">'+bookData.chapter+'</a></p>';
                }
                if(bookData.htmlUrl){
                    if(bookData.htmlUrl.indexOf('http://')!=-1){
                        bookUrl += '<p>外部链接：<a target="_blank" href="'+bookData.htmlUrl+'">'+bookData.htmlUrl+'</a></p>';
                    }else{
                        bookUrl += '<p>外部链接：<a target="_blank" href="http://'+bookData.htmlUrl+'">'+bookData.htmlUrl+'</a></p>';
                    }
                    
                }
                $("#bookUrl").html(bookUrl);
                if(bookEntity.length > 0){
                    for(var i in bookEntity){
                        var status = "";
                        if(bookEntity[i].status == 0){
                            available += 1;
                            status = "可借";
                        }else if(bookEntity[i].status == 1){
                            status = "已借出";
                        }else if(bookEntity[i].status == 2){
                            status = "已损坏";
                        }else if(bookEntity[i].status == 3){
                            status = "已关注";
                        }else{
                            status = "未审核";
                        }
                        var borrowUserStr = bookEntity[i].borrowUser ? bookEntity[i].borrowUser : '';
                        html += '<tr>'+
                                    // '<td>'+bookEntity[i].beid+'</td>'+
                                    '<td>'+foo(bookEntity[i].barcode)+'</td>'+
                                    '<td>'+status+'</td>'+
                                    '<td>'+borrowUserStr+'</td>'+
                                '</tr>';
                    }
                    $("tbody").html(html);
                }
                $("#bookUsable").text(available);
            }
        }
    })
}
