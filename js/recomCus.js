$(function(){
	//荐购操作
	$("#send").click(function(){
		var title = $("#title").val();
		var publisher = $("#publisher").val();
		var author = $("#author").val();
		var edition = $("#edition").val();
		var reason = $("#reason").val();
		if(!title || !publisher || !author){
			alert("书名、作者和出版社必填");
			return false;
		}
		$.ajax({
			type: 'POST',
			url: '/addPurchase',
			dataType: 'json',
			data: {title:title,publisher:publisher,author:author,edition:edition,reason:reason},
			success: function(res){
				alert(res.msg);
				if(res.status == 0){
					window.location.href="/recommendationCus/recomHistory";
				}
			}
		})
	})
})