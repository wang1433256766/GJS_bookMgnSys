$(function(){
    //获取url参数
    /*
    encodeURI()是Javascript中真正用来对URL编码的函数
    eg:
        编码： Javascript:encodeURI("春节");
        解码: Javascript:decodeURI("%E6%98%A5%E8%8A%82");
    */
    (function ($) {
        $.getUrlParam = function (name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
            var r = window.location.search.substr(1).match(reg);
            if (r != null) return decodeURI(r[2]); return null;
        }
    })(jQuery);

    var barCode = $.getUrlParam('barCode');
    var beid,bid;

    //获取所有批次号
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

    //通过barCode获取书目实体信息
    $.ajax({
        type: 'GET',
        url: '/getBookInfo',
        dataType: 'json',
        data: {barCode:barCode},
        success: function(res){
            if(res.status == 0){
                if(res.data.insertType==0){
                    $("#bookTip").text("副本修改");
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
                }else{
                    $("#bookTip").text("新书修改");
                }
                beid = res.data.beid;
                bid = res.data.bid;
                //书目信息
                $("#isbn").val(res.data.book.isbn);
                $("#name").val(res.data.book.name); //必须
                $("#foreignName").val(res.data.book.foreignName);
                $("#edition").val(res.data.book.edition);
                $("#categoryThird").val(res.data.book.categoryThird); //必须
                $("#price").val(res.data.book.price);
                $("#pages").val(res.data.book.pages);
                $("#size").val(res.data.book.size);
                //$("#index").val(res.data.book.claimNumber);
                $("#lang").val(res.data.book.lang);
                $("#series").val(res.data.book.series);
                $("#volume").val(res.data.book.volume);
                $("#author").val(res.data.book.author);
                $("#authorMechanism").val(res.data.book.authorMechanism);
                $("#translationAuthor").val(res.data.book.translationAuthor);
                $("#pubtime").val(res.data.book.pubtime);
                $("#publisher").val(res.data.book.publisher);
                $("#key").val(res.data.book.key);
                $("#summary").val(res.data.book.summary);
                $("#foreignSummary").val(res.data.book.foreignSummary);
                $("#mark").val(res.data.book.mark);
                $("#beurl").val(res.data.book.beurl);
                $("#htmlUrl").val(res.data.book.htmlUrl);
                $("#otherUrl").val(res.data.book.otherUrl);
                //书目实体信息
                $("#choice-batchId").val(res.data.batchId);
                $("#barcode").val(res.data.barcode);
                $("#batchPrice").val(res.data.batchPrice);
                $("#bemark").val(res.data.bemark);
            }
        }
    })

    //保存
    $("#submitBtn").click(function(){
        //书目信息
        var isbn = $("#isbn").val();
        var name = $("#name").val(); //必须
        var foreignName = $("#foreignName").val();
        var edition = $("#edition").val();
        var categoryThird = $("#categoryThird").val(); //必须
        var price = $("#price").val();
        var pages = $("#pages").val();
        var size = $("#size").val();
        var claimNumber = $("#index").val();
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
        var barcode = $("#barcode").val();
        var batchPrice = $("#batchPrice").val();
        var beurl = $("#beurl").val();
        var htmlUrl = $("#htmlUrl").val();
        var otherUrl = $("#otherUrl").val();
        var bemark = $("#bemark").val();
        if(!name || !categoryThird || !batchId){
            alert("书名或三级分类或批次号不能为空");return false;
        }
        var data = {
            beid:beid,bid:bid,isbn:isbn,name:name,foreignName:foreignName,edition:edition,categoryThird:categoryThird,price:price,
            pages:pages,size:size,claimNumber:claimNumber,lang:lang,series:series,volume:volume,author:author,authorMechanism:authorMechanism,
            translationAuthor:translationAuthor,pubtime:pubtime,publisher:publisher,key:key,summary:summary,foreignSummary:foreignSummary,mark:mark,
            batchId:batchId,barcode:barcode,batchPrice:batchPrice,beurl:beurl,htmlUrl:htmlUrl,otherUrl:otherUrl,bemark:bemark
        }
        $.ajax({
            type: 'POST',
            url: '/updateBook',
            dataType: 'json',
            data: data,
            success: function(res){
                //console.log(res);
                if(res.status == 0){
                    //window.history.go(-1);
                    self.location=document.referrer;//返回上一页并刷新
                }
                alert(res.msg);
            }
        })
    })

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

        