$(function(){
	//获取当前登录的用户card
	var IDCard = "";
	//判断是否登录
	$.ajax({
		async: false,
		type: 'GET',
		url: '/isLogin',
		dataType: 'json',
		data: '',
		success: function(res){
			if(res.status == 0){
	            var data = eval('(' + res.data + ')');
	            IDCard = data.card;
	        }
		}
	})

	//获取用户的详细信息和用户借阅的图书
	getUserInfo(IDCard);

   //获取用户关注的图书
   getFocusBook();

	//点击修改
	$("#change-selfinfo").click(function(){
		$(".span-text").addClass('hidden');
		$(".input-text").removeClass('hidden');
		$("#change-selfinfo").addClass('hidden');
		$("#cancle-change").removeClass('hidden');
		$("#submit-change").removeClass('hidden');
	})

	//取消修改
	$("#cancle-change").click(function(){
		$(".input-text").addClass('hidden');
		$(".span-text").removeClass('hidden');
		$("#cancle-change").addClass('hidden');
		$("#submit-change").addClass('hidden');
		$("#change-selfinfo").removeClass('hidden');
	})

	//确认修改
	$("#submit-change").click(function(){
      var email = $("#i-uemail").val();
      var phone = $("#i-uphone").val();
      var tel = $("#i-utel").val();
      var number = $("#i-unumber").val();
      var department = $("#i-udepartment").val();
      var tutor = $("#i-ututor").val();
      var pwd = $("#i-pwd").val().trim();
      var repwd = $("#i-repwd").val().trim();
      if(pwd&&pwd.length<6){
         alert("密码请大于6位");
         return false;
      }
      if(pwd != repwd){
         alert("两次密码请保持一致");
         return false;
      }
      $.ajax({
         type: 'POST',
         url: '/updateSelf',
         dataType: 'json',
         data: {email:email,phone:phone,tel:tel,number:number,department:department,tutor:tutor,pwd:pwd},
         success: function(res){
            if(res.status == 0){
               $("#cancle-change").click();
               getUserInfo(IDCard);
            }
            alert(res.msg);
         }
      })
	})

})

//获取用户的详细信息和用户借阅的图书
function getUserInfo(IDCard){
   $.ajax({
      type: 'GET',
      url: '/getUserInfoByCard',
      dataType: 'json',
      data: {card:IDCard},
      success: function(res){
         //console.log(res.data);
         if(res.status == 0){
            var user = res.data.user;
               $("#qrcode").text(user.qrCode==null?'':user.qrCode);
               $("#uname").text(user.uname==null?'':user.uname);
               $("#uidcard").text(user.idcard==null?'':user.idcard);
               $("#uemail").text(user.email==null?'':user.email);
               $("#ucard").text(user.card==null?'':user.card);
               $("#uphone").text(user.phone==null?'':user.phone);
               $("#utel").text(user.tel==null?'':user.tel);
               $("#urole").text(user.roleStr==null?'':user.roleStr);
               $("#unumber").text(user.number==null?'':user.number);
               $("#ucreateTime").text(user.createTime==null?'':user.createTime);
               $("#uupdateTime").text(user.updateTime==null?'':user.updateTime);
               $("#udepartment").text(user.department==null?'':user.department);
               $("#ututor").text(user.tutor==null?'':user.tutor);

               //$("#i-uname").val(user.uname);
               //$("#i-uidcard").val(user.idcard);
               //$("#i-ucard").val(user.card);
               $("#i-uemail").val(user.email);
               $("#i-uphone").val(user.phone);
               $("#i-utel").val(user.tel);
               //$("#i-urole").val(user.roleStr);
               $("#i-unumber").val(user.number);
               // $("#i-ucreateTime").val(user.createTime);
               // $("#i-uupdateTime").val(user.updateTime);
               $("#i-udepartment").val(user.department);
               $("#i-ututor").val(user.tutor);
            var bookEntityList = res.data.borrowList;
            var borrow_count = 0, over_count = 0;
               if(bookEntityList && bookEntityList.length > 0){
                  var borrowedHtml = "";
                  var backtimeStrFormat = "";
                  for(var i in bookEntityList){
                     var backtimeStr = bookEntityList[i].backtime.replace(new RegExp(/-/gm) ,"/");
                         backtimeStr = backtimeStr.slice(0,backtimeStr.indexOf("."));
                     borrow_count++;
                     //判断超期图书,超期用红色字显示
                     if(new Date(backtimeStr) < new Date()){
                        over_count++;
                        backtimeStrFormat = '<span class="enddate">'+new Date(backtimeStr).format("yyyy-MM-dd")+'</span>';
                     }else{
                        backtimeStrFormat = '<span class="">'+new Date(backtimeStr).format("yyyy-MM-dd")+'</span>';
                     }
                     //var booknameStr = bookEntityList[i].bookName.length>25?bookEntityList[i].bookName.substring(0,25)+'...':bookEntityList[i].bookName;
                     var booknameStr = "",booknameTitle="";
                        if(bookEntityList[i].bookName){
                        booknameStr = bookEntityList[i].bookName.length>25?bookEntityList[i].bookName.substring(0,25)+'...':bookEntityList[i].bookName;
                        booknameTitle = bookEntityList[i].bookName;
                     }else{
                        booknameStr = bookEntityList[i].foreignName.length>40?bookEntityList[i].foreignName.substring(0,40)+'...':bookEntityList[i].foreignName;
                        booknameTitle = bookEntityList[i].foreignName;
                     }
                     var authorStr = bookEntityList[i].author.length>10?bookEntityList[i].author.substring(0,10)+'...':bookEntityList[i].author;
                     var createtimeStr = bookEntityList[i].createtime.replace(new RegExp(/-/gm) ,"/");
                         createtimeStr = createtimeStr.slice(0,createtimeStr.indexOf("."));
                     borrowedHtml += '<tr>'+
                                          '<td>'+foo(bookEntityList[i].beid)+'</td>'+
                                          '<td class="bookname" title="'+booknameTitle+'">'+booknameStr+'</td>'+
                                          '<td title="'+bookEntityList[i].author+'">'+authorStr+'</td>'+
                                          '<td>'+new Date(createtimeStr).format("yyyy-MM-dd")+'</td>'+
                                          '<td>'+backtimeStrFormat+'</td>'+
                                       '</tr>';
                  }
                  $("#borrowed-tbody").html(borrowedHtml);
                  $("#borrowCount").text(borrow_count);
                  $("#overCount").text(over_count);
               }
         }
      }
   })
}

//获取用户关注的图书
function getFocusBook(){
   $.ajax({
      type: 'POST',
      url: '/getFollow',
      dataType: 'json',
      data: {sidx:'fo_createtime',sord:'desc',rows:10,page:1},
      success: function(res){
         //console.log(res);
         var content = "";
         if(res.status == 0){
            var order_count = 0;
            if(res.data.length>0){
               for(var i=0; i<res.data.length;i++){
                  order_count++;
                  var bookNameStr = res.data[i].bookName?res.data[i].bookName.length>25?res.data[i].bookName.substring(0,25)+'...':res.data[i].bookName:'';
                  var authorStr = res.data[i].author?res.data[i].author.length>10?res.data[i].author.substring(0,10)+'...':res.data[i].author:'';
                  content += '<tr>'+
                              '<td>'+foo(res.data[i].foBeid)+'</td>'+
                              '<td title="'+res.data[i].bookName+'">'+bookNameStr+'</td>'+
                              '<td title="'+res.data[i].author+'">'+authorStr+'</td>'+
                              '<td>'+res.data[i].foCreatetime+'</td>'+
                              '<td><button style="cursor:pointer;" onclick="cancleFocu('+res.data[i].foID+')">取消关注</button></td>'+
                           '</tr>';
               }
            }
            $("#focus-tbody").html(content);
            $("#orderCount").text(order_count);
         }
      }
   })
}

//取消关注
function cancleFocu(bid){
   $.ajax({
      type: 'POST',
      url: '/updateFollow',
      dataType: 'json',
      data: {foid:bid,status:1},
      success: function(res){
         if(res.status == 0){
            getFocusBook();
         }
         alert(res.msg);
      }
   })
}