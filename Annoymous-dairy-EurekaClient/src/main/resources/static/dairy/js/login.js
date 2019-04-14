function timeout(){
	$("#signup_forms_submit_code").css("pointer-events","auto");
	$("#signup_forms_submit_code").css("background","#C0C0C0");
	$("#signup_forms_submit_code").text("Get Code");
}
$(function(){
	
	$("#signup_forms_submit").click(function(){
		if($("#signin_email").val()==""||$("#signin_code").val()=="")
		{
			alert("email or code can't be null !");
			 
		}
		else if($("#signin_email").val().length > 17)
		{
			alert("Your email is not correct !");
		}
		else
		{
			if($("#signin_email").attr("name") == "email"){
				var email = $("#signin_email").val();
				var code = $("#signin_code").val();
				$.ajax({
					type:"post",
					url:"/dairy/login_authenticate",
					data:{"email":email,"code":code},
					dataType:"json",
					async:true,
					success:function(data)
					{
						if(data.code == 1){
							window.location.href="/dairy/index";
						}else{
							window.location.href="/dairy/login";
						}
					}
				});
			}else{
				var username = $("#signin_email").val();
				var password = $("#signin_code").val();
				$.ajax({
					type:"post",
					url:"/dairy/login_authenticate",
					data:{"username":username,"password":password},
					dataType:"json",
					async:true,
					success:function(data)
					{
						if(data.code == 1){
							window.location.href="/dairy/index";
						}else{
							window.location.href="/dairy/login";
						}
					}
				});
			}
		}
	});
	
	$("#signup_forms_submit_code").click(function(){		
		if($("#signin_email").val()==""){
			alert("you eamil or username is null");
		}else if($("#signin_email").val().length > 17){
			alert("Your email is not correct !");
		}else{
			if($("#signin_email").val().indexOf('@qq.com') != -1){
				$("#signup_forms_submit_code").css("background","#686868");
				$("#signup_forms_submit_code").css("pointer-events","none");
				var email = $("#signin_email").val();
				$.ajax({
					type:"post",
					url:"/dairy/login_getcode",
					data:{"email":email},
					dataType:"json",
					async:true,
					success:function(data)
					{
						if(data.msg == "send email success"){
							var time = 30;
						    var timer = setInterval(function () {
						        time--;			    
						        $("#signup_forms_submit_code").text(function(){return time;});
						        if (time==0) {
						            clearInterval(timer);
						            timeout();
						        }
						    }, 1000);
						}else{
							timeout();
						}
					}
				});
				//setTimeout(timeout,30000);
			}
		}

	});
	
	//将ajax的属性替换
	$("#signin_email").blur(function(){
		if($("#signin_email").val().indexOf('@qq.com') != -1){
			$("#signin_code").attr("placeholder","Code");
			$("#signin_code").attr("name","code");
			$("#signin_email").attr("name","email");
		}else{
			$("#signin_code").attr("placeholder","Password");
			$("#signin_code").attr("name","password");
			$("#signin_email").attr("name","username");	
			$("#signup_forms_submit_code").css("pointer-events","none");
		}
	});
});