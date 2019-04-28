/*
function timeout(){
	$("#signup_forms_submit_code").css("pointer-events","auto");
	$("#signup_forms_submit_code").css("background","#C0C0C0");
}
*/
$(function(){
		//当email失去焦点的时候且有值的时候就发送请求查看是否有值
		$("#signup_email").blur(function(){
			if($("#signup_email").val()!=""){
				//判断email格式是否正确
				if($("#signup_email").val().indexOf('@qq.com') == -1){
						alert("Your email is not correct !");						
				}
				else if($("#signup_email").val().length > 17){
						alert("Your email is not correct !");
				}else{
					var email = $("#signup_email").val();
					$.ajax({
						type:"post",
						url:"/dairy/check_email",
						data:{"email":email},
						dataType:"json",
						async:true,
						success:function(data)
						{
							$("#email_notice").html(data.msg);
						}
					});
				}
			}else{
				$("#email_notice").html("邮箱已经存在");
			}			
		});
		
		//当username失去焦点的时候且有值的时候就发送请求查看是否有值
		$("#signup_username").blur(function(){
			if($("#signup_username").val()!=""){
				var username = $("#signup_username").val();
				$.ajax({
					type:"post",
					url:"/dairy/check_username",
					data:{"username":username},
					dataType:"json",
					async:true,
					success:function(data)
					{
						$("#username_notice").html("用户名已经存在");
					}
				});
			}else{
				$("#username_notice").html("");
			}
			
		});
		
		$("#signup_forms_submit").click(function(){
			if($("#signup_password").val()==""||$("#signup_confirm_password").val()==""||$("#signup_username").val()==""){
				alert("username or password can't be null !");
			}else if($("#signup_password").val()!=$("#signup_confirm_password").val()){
				alert("Your password is not same !");
				$("#signup_password").val("");
				$("#signup_confirm_password").val("");
			}else{
				 if($("#signup_email").val()!=""){
					 var email = $("#signup_email").val();			
					 var username = $("#signup_username").val();
					 var password = $("#signup_password").val();
					 $.ajax({
						type:"post",
						url:"/dairy/register_now",
						data:{"email":email,"username":username,"password":password},
						dataType:"json",
						async:true,
						success:function(data)
						{
							window.location.href="/dairy/register_success";
						}
					});
				 }else{
					 var username = $("#signup_username").val();
					 var password = $("#signup_password").val();
					 $.ajax({
						type:"post",
						url:"/dairy/register_now",
						data:{"username":username,"password":password},
						dataType:"json",
						async:true,
						success:function(data)
						{
							window.location.href="/dairy/register_success";
						}
					});
				 }
			 }
		});
			
});