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

    //有存在bid,则不需要录入书目信息
    if(bid != null){
        //$(".p1,.part-1").css('display','none');
        $("#isbn").attr('disabled',true);
        $("#name").attr('disabled',true);
        $("#foreignName").attr('disabled',true);
        $("#edition").attr('disabled',true);
        $("#categoryThird").attr('disabled',true);
        $("#price").attr('disabled',true);
        $("#pages").attr('disabled',true);
        $("#size").attr('disabled',true);
        $("#lang").attr('disabled',true);
    	$("#series").attr('disabled',true);
        $("#volume").attr('disabled',true);
        $("#pubtime").attr('disabled',true);
        $("#author").attr('disabled',true);
        $("#publisher").attr('disabled',true);
        $("#key").attr('disabled',true);
        $("#beurl").attr('disabled',true);
        $("#htmlUrl").attr('disabled',true);
        $("#otherUrl").attr('disabled',true);
        $("#summary").attr('disabled',true);
        $("#foreignSummary").attr('disabled',true);
        $("#mark").attr('disabled',true);
        $.ajax({
            type: 'GET',
            url: '/getBookInfoByBid',
            dataType: 'json',
            data: {bid:bid},
            success: function(res){
                if(res.status == 0){
                    var bookInfo = res.data.book;
                    //console.log(bookInfo);
                    $("#isbn").val(bookInfo.isbn);
                    $("#name").val(bookInfo.name);
                    $("#foreignName").val(bookInfo.foreignName);
                    $("#edition").val(bookInfo.edition);
                    $("#categoryThird").val(bookInfo.categoryThird);
                    $("#price").val(bookInfo.price);
                    $("#pages").val(bookInfo.pages);
                    $("#size").val(bookInfo.size);
                    $("#lang").val(bookInfo.lang);
                    $("#series").val(bookInfo.series);
                    $("#volume").val(bookInfo.volume);
                    $("#pubtime").val(bookInfo.pubtime);
                    $("#author").val(bookInfo.author);
                    $("#publisher").val(bookInfo.publisher);
                    $("#key").val(bookInfo.key);
                    $("#beurl").val(bookInfo.ebook);
                    $("#htmlUrl").val(bookInfo.htmlUrl);
                    $("#otherUrl").val(bookInfo.otherUrl);
                    $("#summary").val(bookInfo.summary);
                    $("#foreignSummary").val(bookInfo.foreignSummary);
                    $("#mark").val(bookInfo.mark);
                }
            }
        })
    }

    //获取所有批次
    getAllBatch();

    //批次号切换操作
    $("#addBtn").click(function(){
        $("#add-batchId").val('');
    	$("#choice-batchId").addClass('hidden');
    	$("#addBtn").addClass("hidden");
    	$("#add-batchId").removeClass('hidden');
    	$("#saveBtn").removeClass('hidden')
    })
    $("#saveBtn").click(function(){
        var mark = $("#add-batchId").val();
        if(mark){
            $.ajax({
                type: 'POST',
                url: '/addBatchInfo',
                dataType: 'json',
                data: {mark:mark},
                success: function(res){
                    if(res.status == 0){
                        getAllBatch();
                        $("#add-batchId").addClass('hidden');
                        $("#saveBtn").addClass('hidden');
                        $("#choice-batchId").removeClass('hidden');
                        $("#addBtn").removeClass("hidden");
                    }else{
                        alert(res.msg);
                    }
                }
            })
        }else{
            $("#add-batchId").addClass('hidden');
            $("#saveBtn").addClass('hidden');
            $("#choice-batchId").removeClass('hidden');
            $("#addBtn").removeClass("hidden");
        }
    	
    })

    //新增操作
    $("#submitBtn").click(function(){
    	//console.log($("#cover"));return false;
    	var data = {};
    	//书目信息
    	var isbn = $("#isbn").val();
    	var name = $("#name").val(); //必须
    	var foreignName = $("#foreignName").val();
    	var edition = $("#edition").val();
    	var categoryThird = $("#categoryThird").val(); 
    	var price = $("#price").val();
    	var pages = $("#pages").val();
    	var size = $("#size").val();
    	var lang = $("#lang option:selected").val();
    	var series = $("#series").val();
    	var volume = $("#volume").val();
    	var author = $("#author").val();
    	var authorMechanism = $("#authorMechanism").val();
    	var translationAuthor = $("#translationAuthor").val();
    	var pubtime = $("#pubtime").val();
    	var publisher = $("#publisher").val();
    	var key = $("#key").val();
    	var summary = $("#summary").val();
    	var foreignSummary = $("#foreignSummary").val();
    	var mark = $("#mark").val();

    	//书目实体信息
    	var batchId = $("#choice-batchId option:selected").val();
    	var numOfBook = $("#numOfBook").val();
    	var batchPrice = $("#batchPrice").val();
    	var beurl = $("#beurl").val();
    	var htmlUrl = $("#htmlUrl").val();
    	var otherUrl = $("#otherUrl").val();
    	var bemark = $("#bemark").val();

    	if(bid != null){
    		data = {bid:bid,batchId:batchId,numOfBook:numOfBook,batchPrice:batchPrice,beurl:beurl,htmlUrl:htmlUrl,otherUrl:otherUrl,bemark:bemark};
    	}else{
    		//校验
	    	if(!name ||!batchId || !numOfBook){
	    		alert("书名、批次号和册数不能为空");return false;
	    	}
    		data = {isbn:isbn,name:name,foreignName:foreignName,edition:edition,categoryThird:categoryThird,price:price,pages:pages,size:size,lang:lang,
    				series:series,volume:volume,author:author,authorMechanism:authorMechanism,translationAuthor:translationAuthor,pubtime:pubtime,
    				publisher:publisher,key:key,summary:summary,foreignSummary:foreignSummary,mark:mark,
    				batchId:batchId,numOfBook:numOfBook,batchPrice:batchPrice,beurl:beurl,htmlUrl:htmlUrl,otherUrl:otherUrl,bemark:bemark};
    	}

    	$.ajax({
    		type: 'POST',
    		url: '/edit',
    		dataType: 'json',
    		data: data,
    		success: function(res){
                console.log(res);
    			if(res.status == 0){
                    var c_barcode = "";
                    if(res.data.barcodeList && res.data.barcodeList.length>0){
                        for(var i in res.data.barcodeList){
                            c_barcode += res.data.barcodeList[i] + ',';
                        }
                        c_barcode = c_barcode.substring(0,c_barcode.length-1);
                    }
                    window.wxc.xcConfirm('录入成功，生成的条形码：'+c_barcode, window.wxc.xcConfirm.typeEnum.success);
                    $(".sgBtn").click(function(){
                        self.location=document.referrer;//返回上一页并刷新
                    })
    			}else{
                    alert(res.msg);
                }
    		}
    	})
    });
})

//获取所有批次
function getAllBatch(){
    var optionHtml = "";
    $.ajax({
        async: false,
        type: 'GET',
        url: '/getAllBatchInfo',
        dataType: 'json',
        data: '',
        success: function(result){
            for(var i in result.data){
                optionHtml += '<option value="'+result.data[i].biid+'">'+result.data[i].mark+'</option>';
            }
            $("#choice-batchId").html(optionHtml);
        }
    })
}

