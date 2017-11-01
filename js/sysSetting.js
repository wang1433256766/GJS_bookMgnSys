$(function(){
	//获取初始设置信息
	getInitInfo();

	//重新配置设置内容
	$("#send").click(function(){
		var borrowTime = $("#btime").val();
		var maxBorrowed = $("#bsum").val();
		var renewPeriod = $("#againbtime").val();
		var maxRenewal = $("#againbcount").val();
		var foreignStart = $("#foreminnum").val();
		var smtpSocketFactoryPort = $("#port").val();
		var smtpPort = $("#smtpport").val();
		var email = $("#email").val();
		var smtpHost = $("#smtpemail").val();
		// var emailadress = $("#emailadress").val();
		var emailPassword = $("#emailpwd").val();
		var feedbackTitle = $("#emailtitle").val();
		var reminderTitle = $("#tiptitle").val();
		var reminderTemplate = $("#tipmode").val();
		var reminderMsgTitle = $("#tipmsgtitle").val();
		var reminderMsgTemplate = $("#tipmsgmode").val();
		var followTitle = $("#focustitle").val();
		var followTemplate = $("#focusmode").val();
		var followMsgTitle = $("#focusmsgtitle").val();
		var followMsgTemplate = $("#focusmsgmode").val();
		var data = {borrowTime:borrowTime,maxBorrowed:maxBorrowed,renewPeriod:renewPeriod,maxRenewal:maxRenewal,foreignStart:foreignStart,
					smtpSocketFactoryPort:smtpSocketFactoryPort,smtpPort:smtpPort,email:email,smtpHost:smtpHost,emailPassword:emailPassword,
					feedbackTitle:feedbackTitle,reminderTitle:reminderTitle,reminderTemplate:reminderTemplate,reminderMsgTitle:reminderMsgTitle,
					reminderMsgTemplate:reminderMsgTemplate,followTitle:followTitle,followTemplate:followTemplate,followMsgTitle:followMsgTitle,followMsgTemplate:followMsgTemplate};
		$.ajax({
			type: 'POST',
			url: '/updateSetting',
			dataType: 'json',
			data: data,
			success: function(res){
				if(res.status == 0){
					getInitInfo();
				}
				//console.log(res);
				alert(res.msg);
			}
		})
	})
})

//获取初始设置信息
function getInitInfo(){
	$.ajax({
		type: 'POST',
		url: '/getSetting',
		dataType: 'json',
		data: '',
		success: function(res){
			//console.log(res.data);
			if(res.status == 0){
				if(res.data.length>0){
					for(var i=0;i<res.data.length;i++){
						if(res.data[i].key == 'borrowTime'){
							$("#btime").val(res.data[i].value);
						}
						if(res.data[i].key == 'maxBorrowed'){
							$("#bsum").val(res.data[i].value);
						}
						if(res.data[i].key == 'renewPeriod'){
							$("#againbtime").val(res.data[i].value);
						}
						if(res.data[i].key == 'maxRenewal'){
							$("#againbcount").val(res.data[i].value);
						}
						if(res.data[i].key == 'foreignStart'){
							$("#foreminnum").val(res.data[i].value);
						}
						if(res.data[i].key == 'smtpSocketFactoryPort'){
							$("#port").val(res.data[i].value);
						}
						if(res.data[i].key == 'smtpPort'){
							$("#smtpport").val(res.data[i].value);
						}
						if(res.data[i].key == 'email'){
							$("#email").val(res.data[i].value);
						}
						if(res.data[i].key == 'smtpHost'){
							$("#smtpemail").val(res.data[i].value);
						}
						if(res.data[i].key == 'emailPassword'){
							$("#emailpwd").val(res.data[i].value);
						}
						if(res.data[i].key == 'feedbackTitle'){
							$("#emailtitle").val(res.data[i].value);
						}
						if(res.data[i].key == 'reminderTitle'){
							$("#tiptitle").val(res.data[i].value);
						}
						if(res.data[i].key == 'reminderTemplate'){
							var reminderTemplate = unescapeHTML(res.data[i].value);
							$("#tipmode").val(reminderTemplate);
						}
						if(res.data[i].key == 'reminderMsgTitle'){
							$("#tipmsgtitle").val(res.data[i].value);
						}
						if(res.data[i].key == 'reminderMsgTemplate'){
							var reminderMsgTemplate = unescapeHTML(res.data[i].value);
							$("#tipmsgmode").val(reminderMsgTemplate);
						}
						if(res.data[i].key == 'followTitle'){
							$("#focustitle").val(res.data[i].value);
						}
						if(res.data[i].key == 'followTemplate'){
							var followTemplate = unescapeHTML(res.data[i].value);
							$("#focusmode").val(followTemplate);
						}
						if(res.data[i].key == 'followMsgTitle'){
							$("#focusmsgtitle").val(res.data[i].value);
						}
						if(res.data[i].key == 'followMsgTemplate'){
							var followMsgTemplate = unescapeHTML(res.data[i].value);
							$("#focusmsgmode").val(followMsgTemplate);
						}
					}
				}
			}
		}
	})
}

//转义
function unescapeHTML(a){
     a = "" + a;
     var b = a.replace(/&lt;/g, "<").replace(/&gt;/g, ">").replace(/&amp;/g, "&").replace(/&quot;/g, '"').replace(/&apos;/g, "'");
     if(b.indexOf('&lt;')>=0 || b.indexOf('&gt;')>=0 || b.indexOf('&amp;')>=0 || b.indexOf('&quot;')>=0 || b.indexOf('&apos;')>=0){
     	return unescapeHTML(b);
     }else{
     	return b;
     }
}