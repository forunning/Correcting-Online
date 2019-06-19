<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link href="../css/bootstrap.min.css" rel="stylesheet">
<script src="../js/bootstrap.min.js"></script>
<div id="header">
	<div class="itest-logo">
		<img src="../images/itest-logo_01.png">
	</div>
	<ul class="h-list">
		<li class="level1 "><a href="#" class="fir le-bg">账号管理</a>
			<div class="le" style="display: none;">
				<div style="height: 50px;"></div>
				<ul class="level2">
					<li style="border-top: 1px #e6e6e6 solid;"><a
						href="./tea_list.jsp" class="level2-bg">教师账号</a></li>
					<li><a href="./stu_list.jsp" class="">学生账号</a></li>
				</ul>
			</div></li>
		<li class="level1"><a href="./man_course.jsp" class="second ">课程管理</a>
		</li>
		<li class="level1 "><a href="#" class="third">文件管理</a>
			<div class="le" style="display: none;">
				<div style="height: 50px;">
					<a href="#" style="display: block; height: 50px; width: 100%;"></a>
				</div>
				<ul class="level2">
					<li style="border-top: 1px #e6e6e6 solid;"><a
						href="./man_file.jsp" class="">当前文件</a></li>
					<li><a href="man_score.jsp" class="">成绩预览</a></li>
				</ul>
			</div></li>
	</ul>
	<div class="per-infor" style="top: 0px">
		<img id="userlogo3" src="../images/stu-tx.png" class="person-head"
			style="width: 30px; height: 30px;"> <a href="#" class="infor">管理员</a>
		<div class="per" style="display: none;">
			<div style="height: 50px;"></div>
			<ul class="per-list">
				<li><a data-toggle="modal" data-target="#addUserModal" href=""
					class="a-bg">修改密码</a></li>
				<li><a href="/Test/Login" class="a-bg">退出登录</a></li>
			</ul>
		</div>
	</div>
	<div class="clear"></div>
</div>
<form method="post" action="" class="form-horizontal" role="form"
	id="form_data" onsubmit="" style="margin: 20px;">
	<div class="modal fade" id="addUserModal" tabindex="-1" role="dialog"
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
					<button type="button" class="btn btn-primary" id="subm">提交</button>
					<span id="tip"> </span>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
</form>

<script type="text/javascript">
$("body").on("click","#subm",function(){
		
		var data={id:"<%=session.getAttribute("user")%>"};
		$.ajax({
			url:'/Test/Manager/Userfix',
			type:'get',
			data:data,
			dataType:"json",
			success:function(data){
				console.log(data);
				var oldpass=$("#oldpass").val();
				if(oldpass!=data[0].password){
					alert("原密码错误");
					return;
				}
				
				
				var str=$("#newpass1").val();
				var isnum = /^\d{6}$/.test(str);
				if(!isnum){
					alert("密码格式错误，请输入6位数字！");
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
					dataType:"json",
					success:function(data){
						location.reload();
						alert(data[0].msg);
						
					}
				});
				
			}
		});
		
		
		
	}		
);
</script>