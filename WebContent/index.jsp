<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/login.css" rel="stylesheet" type="text/css" />

<title>Insert title here</title>
</head>
<body>
	<h1>
		网上批阅系统<sup>V2019</sup>
	</h1>

	<div class="login" style="margin-top: 50px;">
		<div class="web_qr_login" id="web_qr_login"
			style="display: block; height: 235px;">
			<div class="web_login" id="web_login">
				<div class="login-box">
					<div class="login_form">
						<form action="" accept-charset="utf-8" id="login_form"
							class="loginForm" method="post">
							<input type="hidden" name="method" value="login" /> <input
								type="hidden" name="did" value="0" /> <input type="hidden"
								name="to" value="log" />
							<div class="uinArea" id="uinArea">
								<label class="input-tips" for="u">帐号：</label>
								<div class="inputOuter" id="uArea">
									<input type="text" id="u" name="name"
										value="${cookie.name.value}" class="inputstyle" required />
								</div>
							</div>
							<div class="pwdArea" id="pwdArea">
								<label class="input-tips" for="p">密码：</label>
								<div class="inputOuter" id="pArea">

									<input type="password" id="p" name="password" placeholder=""
										value="${cookie.password.value}" class="inputstyle" required />
								</div>
							</div>

							<div style="margin-left: 45px; margin-top: 10px">
								<input style="vertical-align: middle;" type="checkbox"
									value="yes" id="remember">记住密码<br />

							</div>

							<div style="padding-left: 50px; margin-top: 20px;">
								<input id="submit" value="登 录" style="width: 150px;"
									class="button_blue" />
							</div>
						</form>
					</div>
				</div>
			</div>
			<!--登录end-->
		</div>
	</div>
</body>
<script src="js/jquery.js"></script>
<script>
$("#submit").click(function(){
	var user = $("#u").val();
	var pass = $("#p").val();
	var remb=$("#remember").is(':checked');
	if(!user){
		$("#u").attr("placeholder","请输入用户名");
		return;
	}
	if(!pass){
		$("#p").attr("placeholder","请输入密码");
		return;
	}
	
	var data = {user:user,pass:pass,remember:remb}
	
	$.ajax({
		url:'/Test/Login',
		type:'post',
		data:data,
		dataType : "json",
		success:function(res){
			console.log(res)
			if(res[0].code==400){
				alert(res[0].msg);
				return;
			}
			if(res[0].code==200){
				if(res[0].msg=="1")
					window.location.href="Teacher/tea_course.jsp";
				if(res[0].msg=="2")
					window.location.href="Student/stu_course.jsp";
				if(res[0].msg=="0")
					window.location.href="Manager/tea_list.jsp";
			}
		}
		
	})

	
})


</script>
</html>