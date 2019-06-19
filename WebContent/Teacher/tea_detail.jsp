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
		<jsp:include page="header.jsp"></jsp:include>
<!-- 		<div id="jishu">
			<a href="#">我的课程</a>&gt;<a href="#">现有课程</a>&gt;<a href="#">task01</a>
		</div> -->
		<div id="test-con">
			<div class="clear"></div>
			<div id="bj-inner">
				<div class="bj-infor">
					<table cellpadding="0" cellspacing="0" class="my-tab1">
						<thead>
							<tr>
								<th><a href="#" class="my-word">学号</a></th>
								<th><a href="#" class="my-word">姓名</a></th>
								<th><a href="#" class="my-word">年级</a></th>
								<th><a href="#" class="my-word">专业</a></th>
								<th><a href="#" class="my-word">上传日期</a></th>
								<th><a href="#" class="my-word">文件</a></th>
								<th><a href="#" class="my-word">成绩</a></th>
								<th><a href="#" class="my-word">操作</a></th>
							</tr>
						</thead>
						<tbody id="table_1">
							<tr id="tr1" style="display: none">
								<td class="stu_id">1611205119</td>
								<td class="stu_name">伍方健</td>
								<td class="entrolltime">2016</td>
								<td class="specialty">软件工程</td>
								<td class="time">2016/2/2</td>
								<td style="cursor: pointer;"><a class="file" href="#">查看</a></td>
								<td class="score">60</td>
								<td class="grade" style="cursor: pointer;">评分</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="yeshu"></div>
			</div>
		</div>
	</div>
</body>

<script>
	$(document).ready(

			function() {
				var taskid=getQueryString("task_id");
				//var taskid="3020";
				var data = {
					task_id : taskid,
					type:"select"
				};

				$.ajax({
					url : '/Test/Teacher/detailhand',
					type : 'post',
					data : data,
					dataType : "json",
					success : function(data) {
						console.log(data)
 						var table_1 = document.getElementById("table_1");
						var box1 = document.getElementById("tr1");
						var length = getJsonLength(data);

						for (var i = 0; i < length; i++) {
							var newNode;
							newNode = box1.cloneNode(true);
							table_1.appendChild(newNode);
	
							
							newNode.id = data[i].stu_id;
							$("#" + data[i].stu_id).removeAttr("style");
							$("#" + data[i].stu_id).find(".stu_id").text(
									data[i].stu_id);
							$("#" + data[i].stu_id).find(".stu_name").text(
									data[i].stu_name);
							$("#" + data[i].stu_id).find(".entrolltime").text(
									data[i].entrolltime);
							$("#" + data[i].stu_id).find(".specialty").text(
									data[i].specialty);
							
							if(data[i].update_time==""){
								$("#" + data[i].stu_id).find(".time").text("--");
								$("#" + data[i].stu_id).find(".file").text("--");
								$("#" + data[i].stu_id).find(".score").text("0");
							}else{
								$("#" + data[i].stu_id).find(".time").text(
										data[i].update_time);
								$("#" + data[i].stu_id).find(".file").attr
								("href","https://view.officeapps.live.com/op/view.aspx?src=wufangjian.top:8080/Test/download/"+data[i].filepath);
								$("#" + data[i].stu_id).find(".score").text(
										data[i].score);
								$("#" + data[i].stu_id).find(".grade").attr("stu_id",data[i].stu_id);
								$("#" + data[i].stu_id).find(".grade").attr("task_id",getQueryString("task_id"));
							}
							
							
							
							
							
							
							
							
						}

					} 

				})
				
				$("body").on("click",".grade",function(){
					if($(this).attr("task_id")==null){
						alert("作业未提交，不可评分");
						return;
					}
					var scor=prompt("请输入成绩：");
					
					if(scor!=null){
						if(!(/^\d{1,3}$/.test(scor))&&!(parseInt(scor)>=0&&parseInt(scor)<=100)){
							alert("请输入0-100的数字！");
							return;
						}
						var data1={type:"update",task_id:$(this).attr("task_id"),stu_id:$(this).attr("stu_id"),score:scor}
						$.ajax({
							url : '/Test/Teacher/detailhand',
							type : 'post',
							data : data1,
							dataType : "json",
							success : function(data) {
								alert(data[0].msg)
								location.reload();
							}
						});
					}
			 	});
			})
</script>
</html>
