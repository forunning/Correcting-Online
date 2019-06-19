<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>My JSP 'Stu_header.jsp' starting page</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Stu_course</title>
<link href="../css/bootstrap.min.css" rel="stylesheet">
<script src="../js/bootstrap.min.js"></script>
<link href="../css/stu_css.css" rel="stylesheet">
<script src="../js/jquery.js"></script>
<script src="../js/jquery.dotdotdot.min.js"></script>
<script src="../js/headshow.js"></script>

</head>
<body>
	<div id="header">
		<div class="itest-logo">
		<a href="stu_course.jsp">
			<img src="../images/itest-logo_01.png">
			</a>
		</div>
		<div class="per-infor" style="top: 0px">
			<img id="userlogo3" src="../images/stu-tx.png" class="person-head"
				style="width: 30px; height: 30px;"> <a href="stu_info.jsp"
				class="infor"><%=session.getAttribute("user")%></a>
			<div class="per" style="display: none;">
				<div style="height: 50px;"></div>
				<ul class="per-list">
					<li><a data-toggle="modal" data-target="#add" href=""
						class="a-bg">修改密码</a></li>
					<li><a href="stu_info.jsp" class="a-bg">个人中心</a></li>
					<li><a href="/Test/Login" class="a-bg">退出登录</a></li>
				</ul>
			</div>
		</div>
		<div class="clear"></div>
	</div>
	<form method="post" action="" class="form-horizontal" role="form"
		id="form_data" onsubmit="return check_form()" style="margin: 20px;">
		<div class="modal fade" id="add" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 400px">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">×</button>
						<h4 class="modal-title" id="myModalLabel">修改密码</h4>
					</div>
					<div class="modal-body">
						<form class="form-horizontal" role="form">
							<div class="form-group">
								<label for="user_id" class="col-sm-3 control-label">原密码</label>
								<div class="col-sm-9">
									<input type="text" class="form-control" id="oldpass"
										name="user_id" value="" placeholder="请输入原密码">
								</div>
							</div>
							<div class="form-group">
								<label for="lastname" class="col-sm-3 control-label">新密码</label>
								<div class="col-sm-9">
									<input type="text" class="form-control" name="user_name"
										value="" id="newpass1" placeholder="新密码">
								</div>
							</div>
							<div class="form-group">
								<label for="lastname" class="col-sm-3 control-label">新密码</label>
								<div class="col-sm-9">
									<input type="text" class="form-control" name="user_name"
										value="" id="newpass2" placeholder="再次输入新密码">
								</div>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭
						</button>
						<button type="button" class="btn btn-primary" data-dismiss="modal"
							id="sub">提交</button>
						<span id="tip"> </span>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>
	</form>
</body>
<script type="text/javascript">
$("body").on("click","#sub",function(){
		
		var oldpass=$("#oldpass").val();
		var data={stu_id:"<%=session.getAttribute("user")%>",type:"select"};
		$.ajax({
			url:'/Test/Manager/stuhand',
			type:'post',
			data:data,
			dataType:"json",
			success:function(data){
				console.log(data);
				if(oldpass!=data[0].password){
					alert("原密码错误");
					return;
				}
				
				
				var str=$("#newpass1").val();
				var isnum = /^\d{10}$/.test(str);
				if(!isnum){
					alert("密码格式错误，请输入10位数字！");
					return;
				}
				
				var str1=$("#newpass2").val();
				if(str1!=str){
					alert("密码不一致");
					return;
				}
				
				
				
				var fix={
						id:"<%=session.getAttribute("user")%>",
						password:$("#newpass1").val()
				};
				$.ajax({
					url:'/Test/Manager/Userfix',
					type:'post',
					data:fix,
					async:false,
					dataType:"json",
					success:function(data){
						alert(data[0].msg);
						location.reload();
						

					}
				});
				
			}
		});
		
		
		
	}		
);
</script>
</html>