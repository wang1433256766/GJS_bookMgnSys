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
    //var queryChoice = $.getUrlParam('queryChoice');
    var queryKey = $.getUrlParam('queryKey');
    //多字段检索
    var queryName = $.getUrlParam('queryName');
	var queryPublisher = $.getUrlParam('queryPublisher');
	var queryPerson = $.getUrlParam('queryPerson');
	var queryIsbn = $.getUrlParam('queryIsbn');
	var queryBookname = $.getUrlParam('queryBookname');
	var queryClaim = $.getUrlParam('queryClaim');
	var queryPubtime = $.getUrlParam('queryPubtime');

	if(queryKey){
	    $("#keyValue").text(queryKey);
	}else{
	    $("#keyValue").text(queryName);
	}

	var sortBy = $("#sortBy option:selected").val();
	var sortType = $("input[name='sortType']:checked").val();

    //所有图书和可借图书的切换
    var $li = $('.tab li');
				
	$li.click(function(){
		var $this = $(this);
		$li.removeClass();
		$this.addClass('active-tab');
	})

	//加载列表内容
	if(queryKey){
		loadDataBySimple(queryKey,1,sortBy,sortType);
	}else{
		loadData(1,sortBy,sortType,queryName,queryPerson,queryPublisher,queryIsbn,queryBookname,queryClaim,queryPubtime);
	}
	controlPage($("#page-num").val(),$("#total-page").text(),$("#first-page"),$("#pre-page"),$("#next-page"),$("#last-page"));

	//分页
	$("#first-page").click(function(){
		if(queryKey){
			loadDataBySimple(queryKey,1,sortBy,sortType);
		}else{
			loadData(1,sortBy,sortType,queryName,queryPerson,queryPublisher,queryIsbn,queryBookname,queryClaim,queryPubtime);
		}
		controlPage($("#page-num").val(),$("#total-page").text(),$("#first-page"),$("#pre-page"),$("#next-page"),$("#last-page"));
	})
	$("#pre-page").click(function(){
		if(!parseInt($("#page-num").val()) || parseInt($("#page-num").val())>parseInt($("#total-page").text()) || parseInt($("#page-num").val())<=1){
			alert('请保证当前页在合理范围内');
			return false;
		}
		var page = parseInt($("#page-num").val())-1;
		if(queryKey){
			loadDataBySimple(queryKey,page,sortBy,sortType);
		}else{
			loadData(page,sortBy,sortType,queryName,queryPerson,queryPublisher,queryIsbn,queryBookname,queryClaim,queryPubtime);
		}
		controlPage($("#page-num").val(),$("#total-page").text(),$("#first-page"),$("#pre-page"),$("#next-page"),$("#last-page"));
	})
	$("#next-page").click(function(){
		if(!parseInt($("#page-num").val()) || parseInt($("#page-num").val())>parseInt($("#total-page").text()-1) || parseInt($("#page-num").val())<=0){
			alert('请保证当前页在合理范围内');
			return false;
		}
		var page = parseInt($("#page-num").val())+1;
		if(queryKey){
			loadDataBySimple(queryKey,page,sortBy,sortType);
		}else{
			loadData(page,sortBy,sortType,queryName,queryPerson,queryPublisher,queryIsbn,queryBookname,queryClaim,queryPubtime);
		}
		controlPage($("#page-num").val(),$("#total-page").text(),$("#first-page"),$("#pre-page"),$("#next-page"),$("#last-page"));
	})
	$("#last-page").click(function(){
		var page = $("#total-page").text();
		if(queryKey){
			loadDataBySimple(queryKey,page,sortBy,sortType);
		}else{
			loadData(page,sortBy,sortType,queryName,queryPerson,queryPublisher,queryIsbn,queryBookname,queryClaim,queryPubtime);
		}
		controlPage($("#page-num").val(),$("#total-page").text(),$("#first-page"),$("#pre-page"),$("#next-page"),$("#last-page"));
	})
	$("#page-num").keyup(function(e){
		if(e.keyCode == 13){
			var page = parseInt($("#page-num").val());
			if(!page || page>parseInt($("#total-page").text()) || page<=0){
				alert("输入的页数不对");
				return false;
			}
			if(queryKey){
				loadDataBySimple(queryKey,page,sortBy,sortType);
			}else{
				loadData(page,sortBy,sortType,queryName,queryPerson,queryPublisher,queryIsbn,queryBookname,queryClaim,queryPubtime);
			}
			controlPage($("#page-num").val(),$("#total-page").text(),$("#first-page"),$("#pre-page"),$("#next-page"),$("#last-page"));
		}
	})

	//重新排序
	$("#reSort").click(function(){
		sortBy = $("#sortBy option:selected").val();
		sortType = $("input[name='sortType']:checked").val();
		if(queryKey){
			loadDataBySimple(queryKey,1,sortBy,sortType);
		}else{
			loadData(1,sortBy,sortType,queryName,queryPerson,queryPublisher,queryIsbn,queryBookname,queryClaim,queryPubtime);
		}
		controlPage($("#page-num").val(),$("#total-page").text(),$("#first-page"),$("#pre-page"),$("#next-page"),$("#last-page"));
	})

})

//加载列表内容 方法封装
function loadData(page,sidx,sord,queryName,queryPerson,queryPublisher,queryIsbn,queryBookname,queryClaim,queryPubtime){
	var data = {name:queryName,author:queryPerson,publisher:queryPublisher,isbn:queryIsbn,claimNumber:queryClaim,series:queryBookname,pubtime:queryPubtime,sidx:sidx,sord:sord,rows:10,page:page};
	var content = "";
	$.ajax({
		async: false,
		type: 'POST',
		url: '/search',
		dataType: 'json',
    	data: data,
    	success: function(res){
    		console.log(res);
    		if(res.status == 0){
    			var regName = new RegExp("(" + queryName + ")", "g"); //改变关键字的样式
    			var regPerson = new RegExp("(" + queryPerson + ")", "g"); //改变关键字的样式
    			var regPublisher = new RegExp("(" + queryPublisher + ")", "g"); //改变关键字的样式
    			var regIsbn = new RegExp("(" + queryIsbn + ")", "g"); //改变关键字的样式
    			var regBookname = new RegExp("(" + queryBookname + ")", "g"); //改变关键字的样式
    			var regClaim = new RegExp("(" + queryClaim + ")", "g"); //改变关键字的样式
    			var regPubtime = new RegExp("(" + queryPubtime + ")", "g"); //改变关键字的样式
    			var totalpage = Math.ceil(res.data.count/10);
    			$("#totalRecord").text(res.data.count); //总记录数
    			$("#total-page").text(totalpage); //总页数
    			$("#page-num").val(res.data.page); //当前页
    			for(var i in res.data.bookList){
    				var nameStr = "",nameStrf = "";
    				if(res.data.bookList[i].name){
    					nameStr = res.data.bookList[i].name.replace(regName, "<font color=\"red\">"+queryName+"</font>");
    				}if(res.data.bookList[i].foreignName){
    					nameStrf = res.data.bookList[i].foreignName.replace(regName, "<font color=\"red\">"+queryName+"</font>");
    				}
    				var personStr = res.data.bookList[i].author.replace(regPerson, "<font color=\"red\">"+queryPerson+"</font>");
    				var publisherStr = res.data.bookList[i].publisher.replace(regPublisher, "<font color=\"red\">"+queryPublisher+"</font>");
    				var isbnStr = res.data.bookList[i].isbn.replace(regIsbn, "<font color=\"red\">"+queryIsbn+"</font>");
    				var booknameStr = res.data.bookList[i].series.replace(regBookname, "<font color=\"red\">"+queryBookname+"</font>");
    				var claimStr = res.data.bookList[i].claimNumber.replace(regClaim, "<font color=\"red\">"+queryClaim+"</font>");
    				var pubtimeStr = res.data.bookList[i].pubtime?res.data.bookList[i].pubtime.replace(regPubtime, "<font color=\"red\">"+queryPubtime+"</font>"):'';
    				var otherUrlStr = res.data.bookList[i].otherUrl?res.data.bookList[i].otherUrl:'';
    				var asteriskStr = res.data.bookList[i].asterisk?res.data.bookList[i].asterisk:'';
    				var quanwen="",ebook="",chapter="",htmlUrl="";
	    			if(res.data.bookList[i].asterisk){ //res.data.bookList[i].otherUrl || 
	    				quanwen = '<span>超星全文：<a target="_blank" href="http://210.72.9.23:8082/markbook/GoBook.jsp?BID='+res.data.bookList[i].asterisk+'">'+asteriskStr+'</a>&nbsp;&nbsp;&nbsp;&nbsp;';//<a target="_blank" href="http://210.72.9.23:8082/markbook/GoBook.jsp?BID='+res.data.bookList[i].otherUrl+'">'+otherUrlStr+'</a>&nbsp;&nbsp;
	    			}
	    			if(res.data.bookList[i].ebook){
	    				var Str_ebook = res.data.bookList[i].ebook;
	    				var engE = Str_ebook.match(/^[a-z|A-Z]+/gi); 
	    				var numE = Str_ebook.match(/\d+$/gi);
	    				Str_ebook = 'EB'+numE+engE;
	    				ebook = '本馆电子书：<a target="_blank" href="http://book.optics.org.cn/Book/Details/'+Str_ebook+'">'+Str_ebook+'</a></span><br>';
	    			}
	    			if(res.data.bookList[i].chapter){
	    				chapter = '<span>章节阅读：<a target="_blank" href="http://www.optics.org.cn/web/search?channelid=114677&extrasql=%E5%9B%BE%E4%B9%A6%E4%BB%A3%E7%A0%81%3D'+res.data.bookList[i].chapter+'">'+res.data.bookList[i].chapter+'</a>&nbsp;&nbsp;&nbsp;&nbsp;';
	    			}
	    			if(res.data.bookList[i].htmlUrl){
	    				if(res.data.bookList[i].htmlUrl.indexOf('http://')!=-1){
	    					htmlUrl = '外部链接：<a target="_blank" href="'+res.data.bookList[i].htmlUrl+'">'+res.data.bookList[i].htmlUrl+'</a></span>';
	    				}else{
	    					htmlUrl = '外部链接：<a target="_blank" href="http://'+res.data.bookList[i].htmlUrl+'">'+res.data.bookList[i].htmlUrl+'</a></span>';
	    				}
	    				
	    			}
	    			var countStr = res.data.bookList[i].count?res.data.bookList[i].count:'0';
	    			var availableStr = res.data.bookList[i].available?res.data.bookList[i].available:'0';
	    			if(res.data.bookList[i].cover){
	    				var imgStr = '<div class="content-right"><img class="img" src="../../public/cover/'+res.data.bookList[i].cover+'"></div>';
	    			}else{
	    				var imgStr = '<div class="content-right"><img class="img" src="../../public/images/book_pictrue.png"></div>';
	    			}
    				content += '<li>'+
	    				'<div class="content-left">'+
		    				'<p>'+
			    				'<span><a href="/bookQuery/bookQueryListDetail?bid='+res.data.bookList[i].bid+'">'+nameStr+'</a></span><br>'+
			    				'<span><a href="/bookQuery/bookQueryListDetail?bid='+res.data.bookList[i].bid+'">'+nameStrf+'</a></span><br>'+
			    				'<span>作者：'+personStr+'</span>&nbsp;&nbsp;&nbsp;语种：'+res.data.bookList[i].lang+'<br>'+
			    				'<span>出版社：'+publisherStr+'&nbsp;&nbsp;&nbsp;出版年：'+pubtimeStr+'&nbsp;&nbsp;&nbsp;ISBN:'+res.data.bookList[i].isbn+'</span><br>'+
			    				'<span>索书号：'+res.data.bookList[i].claimNumber+'&nbsp;&nbsp;&nbsp;&nbsp;馆藏：'+countStr+'本&nbsp;&nbsp;&nbsp;&nbsp;可借：'+availableStr+'本</span><br>'+
			    				''+quanwen+ebook+chapter+htmlUrl+
		    				'</p>'+
	    				'</div>'+
	    				''+imgStr+
    				'</li>';
    			}
    			$(".content ul").html(content);
    		}else{
    			alert(res.msg);
    		}
    	}
	})
}

//加载列表内容 方法封装
function loadDataBySimple(queryKey,page,sidx,sord){
	var data = {bookName:queryKey,sidx:sidx,sord:sord,rows:10,page:page};
	var content = "";
	$.ajax({
		async: false,
		type: 'POST',
		url: '/fuzzySearch',
		dataType: 'json',
    	data: data,
    	success: function(res){
    		console.log(res);
    		if(res.status == 0){
				var totalpage = Math.ceil(res.data.allRows/10);
    			$("#totalRecord").text(res.data.allRows); //总记录数
    			$("#total-page").text(totalpage); //总页数
    			$("#page-num").val(res.data.page); //当前页
    			for(var i in res.data.bookList){
    				var newstr = "", newstrf="";
    				if(res.data.bookList[i].name){
    					newstr = res.data.bookList[i].name;
    				}if(res.data.bookList[i].foreignName){
    					newstrf = res.data.bookList[i].foreignName;
    				}
    				for(var k in res.data.keyList){
	    				var reg = new RegExp("(" + res.data.keyList[k].replace(/\s/g, "") + ")", "g"); //改变关键字的样式
	    				newstr = newstr.replace(reg, "<font color=\"red\">"+res.data.keyList[k].replace(/\s/g, "")+"</font>");
	    				newstrf = newstrf.replace(reg, "<font color=\"red\">"+res.data.keyList[k].replace(/\s/g, "")+"</font>");
	    			}
	    			var otherUrlStr = res.data.bookList[i].otherUrl?res.data.bookList[i].otherUrl:'';
    				var asteriskStr = res.data.bookList[i].asterisk?res.data.bookList[i].asterisk:'';
	    			var quanwen="",ebook="",chapter="",htmlUrl="";
	    			if(res.data.bookList[i].asterisk){
	    				quanwen = '<span>超星全文：<a target="_blank" href="http://210.72.9.23:8082/markbook/GoBook.jsp?BID='+res.data.bookList[i].asterisk+'">'+asteriskStr+'</a>&nbsp;&nbsp;&nbsp;&nbsp;';
	    			}
	    			if(res.data.bookList[i].ebook){
	    				var Str_ebook = res.data.bookList[i].ebook;
	    				var engE = Str_ebook.match(/^[a-z|A-Z]+/gi); 
	    				var numE = Str_ebook.match(/\d+$/gi);
	    				Str_ebook = 'EB'+numE+engE;
	    				ebook = '本馆电子书：<a target="_blank" href="http://book.optics.org.cn/Book/Details/'+Str_ebook+'">'+Str_ebook+'</a></span><br>';
	    			}
	    			if(res.data.bookList[i].chapter){
	    				chapter = '<span>章节阅读：<a target="_blank" href="http://www.optics.org.cn/web/search?channelid=114677&extrasql=%E5%9B%BE%E4%B9%A6%E4%BB%A3%E7%A0%81%3D'+res.data.bookList[i].chapter+'">'+res.data.bookList[i].chapter+'</a>&nbsp;&nbsp;&nbsp;&nbsp;';
	    			}
	    			if(res.data.bookList[i].htmlUrl){
	    				if(res.data.bookList[i].htmlUrl.indexOf('http://')!=-1){
	    					htmlUrl = '外部链接：<a target="_blank" href="'+res.data.bookList[i].htmlUrl+'">'+res.data.bookList[i].htmlUrl+'</a></span>';
	    				}else{
	    					htmlUrl = '外部链接：<a target="_blank" href="http://'+res.data.bookList[i].htmlUrl+'">'+res.data.bookList[i].htmlUrl+'</a></span>';
	    				}
	    				
	    			}
	    			var pubtimeStr = res.data.bookList[i].pubtime?res.data.bookList[i].pubtime:'';
	    			var countStr = res.data.bookList[i].count?res.data.bookList[i].count:'0';
	    			var availableStr = res.data.bookList[i].available?res.data.bookList[i].available:'0';
	    			if(res.data.bookList[i].cover){
	    				var imgStr = '<div class="content-right"><img class="img" src="../../public/cover/'+res.data.bookList[i].cover+'"></div>';
	    			}else{
	    				var imgStr = '<div class="content-right"><img class="img" src="../../public/images/book_pictrue.png"></div>';
	    			}
					content += '<li>'+
				    				'<div class="content-left">'+
					    				'<p>'+
						    				'<span><a href="/bookQuery/bookQueryListDetail?bid='+res.data.bookList[i].bid+'">'+newstr+'</a></span><br>'+
						    				'<span><a href="/bookQuery/bookQueryListDetail?bid='+res.data.bookList[i].bid+'">'+newstrf+'</a></span><br>'+
						    				'<span>作者：'+res.data.bookList[i].author+'</span>&nbsp;&nbsp;&nbsp;语种：'+res.data.bookList[i].lang+'<br>'+
						    				'<span>出版社：'+res.data.bookList[i].publisher+'&nbsp;&nbsp;&nbsp;出版年：'+pubtimeStr+'&nbsp;&nbsp;&nbsp;ISBN:'+res.data.bookList[i].isbn+'</span><br>'+
						    				'<span>索书号：'+res.data.bookList[i].claimNumber+'&nbsp;&nbsp;&nbsp;&nbsp;馆藏：'+countStr+'本&nbsp;&nbsp;&nbsp;&nbsp;可借：'+availableStr+'本</span><br>'+
						    				''+quanwen+ebook+chapter+htmlUrl+
					    				'</p>'+
				    				'</div>'+
				    				''+imgStr+
			    				'</li>';
    				
    			}
    			$(".content ul").html(content);
    		}else{
    			alert(res.msg);
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