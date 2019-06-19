<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
<style type="text/css">
#header .fir {
	border-bottom: 2px #2dbf7f solid;
}
</style>
</head>

<body>
	<div id="wrap">
		<jsp:include page="man_header.jsp"></jsp:include>
		<div id="jishu">
			<a href="#">全部课程</a>
		</div>
		<div id="test-con">
			<div class="add">
				<a style="color: white; cursor: pointer;" id="add">添加学生</a>
			</div>
			<div class="clear"></div>
			<div id="bj-inner">
				<div class="bj-infor">
					<table cellpadding="0" cellspacing="0" class="my-tab1">
						<thead>
							<tr>
								<th><a href="#" class="my-word" style="cursor: default;">学号</a></th>
								<th><a href="#" class="my-word" style="cursor: default;">姓名</a></th>
								<th><a href="#" class="my-word" style="cursor: default;">性别</a></th>
								<th><a href="#" class="my-word" style="cursor: default;">入学年份</a></th>
								<th><a href="#" class="my-word" style="cursor: default;">学院</a></th>
								<th><a href="#" class="my-word" style="cursor: default;">专业</a></th>
								<th><a href="#" class="my-word">操作</a></th>
							</tr>
						</thead>
						<tbody id="table">
							<tr id="tr" style="display: none">
								<td class="id">16111205119</td>
								<td class="name">伍方健</td>
								<td class="sex">男</td>
								<td class="time">2016</td>
								<td class="department">计算机与信息</td>
								<td class="specialty">软件工程</td>
								<td><a href="#" class="delete">删除</a></td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="yeshu"></div>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>

</body>

<script type="text/javascript">
$("#add").click(function(){
	var id=prompt("请输入学号：");
	if(!(/^\d{10}$/.test(id))){
		alert("请检查输入格式！");
		return;
	}
	var couid=getQueryString("cou_id");
	var data={type:"add",stu_id:id,cou_id:couid};
	$.ajax({
		url : '/Test/Manager/detailhand',
		type : 'post',
		data : data,
		dataType : "json",
		success : function(data) {
			location.reload();
			alert(data[0].msg);
			
		}
	});
});
</script>

<script type="text/javascript">
$(document).ready(

		function() {
			//var id="1001";
			var id=getQueryString("cou_id");
			var data = {type:"select",cou_id:id};
			$.ajax({
				url : '/Test/Manager/detailhand',
				type : 'post',
				data : data,
				dataType : "json",
				success : function(data) {
					console.log(data)
					var table = document.getElementById("table");
					var box = document.getElementById("tr");
					var length = getJsonLength(data);

					for (var i = 0; i < length; i++) {
						var newNode;
						newNode = box.cloneNode(true);
						table.appendChild(newNode);

						
						//newNode.style.display = 'block';
						newNode.id = data[i].id;
						$("#" + data[i].id).removeAttr("style");
						
						$("#" + data[i].id).find(".id").text(
								data[i].id);
						$("#" + data[i].id).find(".name").text(
								data[i].name);
						$("#" + data[i].id).find(".sex").text(
								data[i].sex);
						$("#" + data[i].id).find(".time").text(
								data[i].entrolltime);
						$("#" + data[i].id).find(".department").text(
								data[i].department);
						$("#" + data[i].id).find(".specialty").text(
								data[i].specialty);
						$("#" + data[i].id).find(".phone").text(
								data[i].phone);
						$("#" + data[i].id).find(".delete").attr("data-id",data[i].id)
					}

				} 

			})
			
			
			
			$("body").on("click",".delete",function(){
			//alert($(this).attr("data-id"));
			if (!confirm("确认删除？")) {
						return;
					}
				var data1={stu_id:$(this).attr("data-id"),type:"delete",cou_id:1001}
				$.ajax({
					url : '/Test/Manager/detailhand',
					type : 'post',
					data : data1,
					dataType : "json",
					success : function(data) {
						location.reload();
						alert(data[0].msg);
						
					}
				});
		 	});
			
			
			
		})
	
</script>
</html>
