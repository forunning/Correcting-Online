<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>My JSP 'Stu_header.jsp' starting page</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Stu_course</title>
<link href="../css/stu_css.css" rel="stylesheet">

<script src="../js/jquery.js"></script>
<script src="../js/jquery.dotdotdot.min.js"></script>
<script src="../js/headshow.js"></script>
<script src="../js/echarts.js"></script>



</head>

<body>
	<div id="wrap">
		<jsp:include page="header.jsp"></jsp:include>
		<div id="jishu">
			<a href="/itest/itest/s/space">个人信息</a>
		</div>
		<div id="test-con">
			<div id="app" class="app">
				<div class="container view">
					<div class="info">
						<div class="avatar">
							<div class="avatar-icon">
								<img src="../images/stu-tx.png" class="img-circle">
							</div>
							<div class="avatar-username">
								<p>伍方健</p>
							</div>
							<div class="avatar-userid">
								<span class="stu_id">16111205119</span>
							</div>
							<div class="avatar-member">
								<div class="member-button">
									<span>学生用户</span>
								</div>
							</div>
						</div>
					</div>
					<div class="box">
						<div class="line">
							<div class="lineleft">姓名：</div>
							<div class="lineright" id="name">伍方健</div>
							<div class="clear"></div>
						</div>
						<div class="line">
							<div class="lineleft">性别：</div>
							<div class="lineright" id="sex">男</div>
							<div class="clear"></div>
						</div>
						<div class="line">
							<div class="lineleft">入学时间：</div>
							<div class="lineright" id="time">2016</div>
							<div class="clear"></div>
						</div>
						<div class="line">
							<div class="lineleft">学院：</div>
							<div class="lineright" id="department">计算机与信息学院</div>
							<div class="clear"></div>
						</div>
						<div class="line">
							<div class="lineleft">专业：</div>
							<div class="lineright" id="specialty">软件工程</div>
							<div class="clear"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
<script>

$(document).ready(
		
		function(){
			var data={stu_id:"<%=session.getAttribute("user")%>",type:"select"};
			$.ajax({
				url:'/Test/Manager/stuhand',
				type:'post',
				data:data,
				dataType:"json",
				success:function(data){
					console.log(data)
					$("#name").text(data[0].name);
					$("#sex").text(data[0].sex);
					$("#time").text(data[0].enrolltime);
					$("#department").text(data[0].department);
					$("#specialty").text(data[0].specialty);
					$(".avatar-username").text(data[0].name);
					$(".stu_id").text(data[0].id);
					//$(".avatar-userid").attr("password",data[0].password);
				}
			});
		});
</script>
</html>


