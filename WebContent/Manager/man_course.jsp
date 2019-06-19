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
#header .second {
	border-bottom: 2px #2dbf7f solid;
}

.h4-bg {
	font-size: 14px;
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
				<a data-toggle="modal" data-target="#add" href="#"
					style="color: white;">添加课程</a>
			</div>
			<div class="clear"></div>
			<div class="class-infor class-disblock " id="selectDiv"
				style="display: none">
				<div class="class1 class eachDiv" data-clsid="7">
					<h2 style="width: 400px;">
						<a href="#" class="cou_name">软件工程项目实践</a>
					</h2>
					<div class="message">
						<table cellpadding="0" cellspacing="0" class="message_tab">
							<tbody>
								<tr>
									<td class="stu-con exam-con">
										<div>教师</div>
										<div class="h4">
											<span class="h4-bg tea_name">董尼</span>
										</div>
									</td>
									<td class="stu-con">
										<div>操作</div>
										<div class="h4">
											<span class="h4-bg student update" name="73"
												style="overflow-wrap: break-word; cursor: pointer"
												data-toggle="modal" data-target="#add">修改</span>
										</div>
									</td>
									<td class="stu-con">
										<div>操作</div>
										<div class="h4">
											<span class="h4-bg student delete" name="73"
												style="overflow-wrap: break-word; cursor: pointer">删除</span>
										</div>
									</td>
								</tr>
							</tbody>
						</table>
					</div>

					<div class="clear"></div>
					<div class="date">
						<span class="belong">ID：<span class="cou_id">10025001</span></span>
					</div>
				</div>

			</div>
		</div>
		<ul class="pagination" style="float: right"></ul>
	</div>

	<jsp:include page="footer.jsp"></jsp:include>
	<form method="post" action="" class="form-horizontal" role="form"
		id="form_data" style="margin: 20px;">
		<input type="hidden" value="add" id="type"> <input
			type="hidden" value="1001" id="cou_id">
		<div class="modal fade" id="add" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 450px">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">×</button>
						<h4 class="modal-title" id="myModalLabel">添加课程</h4>
					</div>
					<div class="modal-body">
						<form class="form-horizontal" role="form">
							<div class="form-group">
								<label for="user_id" class="col-sm-3 control-label">课程名：</label>
								<div class="col-sm-9">
									<input type="text" class="form-control" id="cou_name"
										name="user_id" value="">
								</div>
							</div>
							<div class="form-group">
								<label for="lastname" class="col-sm-3 control-label">教师ID：</label>
								<div class="col-sm-9">
									<input type="text" class="form-control" name="startdate"
										id="tea_id" value="">
								</div>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
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
	if($("#cou_name").val()==""){
		alert("课程名不能为空！");
		return;
	}
	if(!(/^\d{10}$/.test($("#tea_id").val()))){
		alert("请检查教师id格式！");
		return;
	}
	
	
 	var data={
		tea_id:$("#tea_id").val(),
		name:$("#cou_name").val(),
		cou_id:$("#cou_id").val(),
		type:$("#type").val()
	}
 	//alert($("#type").val())
	$.ajax({
		url : '/Test/Manager/couhand',
		type : 'post',
		data : data,
		dataType : "json",
		success : function(data) {
			location.reload();
			alert(data[0].msg);
			
		}
	});  
})

</script>
<script>

$(document).ready(
		
		function(){
			
		var data={type:"select"};

		$.ajax({
			url:'/Test/Manager/couhand',
			type:'post',
			data:data,
			dataType:"json",
			success:function(data){
				console.log(data)
				var main = document.getElementById("test-con");
				var box = document.getElementById("selectDiv");
				var page = parseInt(getQueryString("page"));
				if (isNaN(page))
					page=1;
				var length = getJsonLength(data);
				
				
				
				var count=Math.ceil(length/4);
				if(page<=1){
					$(".pagination").append("<li><a href='"+"man_course.jsp?page="+"1"+"'>«</a></li>");
				}else{
					$(".pagination").append("<li><a href='"+"man_course.jsp?page="+(page-1)+"'>«</a></li>");
				}
					
				for(var i=0;i<count;i++){
					if((i+1)!=page){
						$(".pagination").append("<li><a href='"+"man_course.jsp?page="+(i+1)+"'>"+(i+1)+"</a></li>");	
					}
					else{
						$(".pagination").append("<li><a class='active' href='"+"man_course.jsp?page="+(i+1)+"'>"+(i+1)+"</a></li>");
					}
				}
				if(page>=count){
					$(".pagination").append("<li><a href='"+"man_course.jsp?page="+(page)+"'>»</a></li>");
				}else{
					$(".pagination").append("<li><a href='"+"man_course.jsp?page="+(page+1)+"'>»</a></li>");
				}
				
				
				for (var i = (page - 1) * 4; i < 4 * page; i++) {
					if (i >= length || i < 0)
						return;
					var newNode = box.cloneNode(true);
					newNode.style.display = 'block';
					newNode.id = data[i].cou_id;
					main.appendChild(newNode);
					$("#" + data[i].cou_id).find(".cou_name").text(
							data[i].cou_name);
					$("#" + data[i].cou_id).find("a").attr(
							"href",
							"man_detail.jsp?page=1&cou_id=" + data[i].cou_id);
					$("#" + data[i].cou_id).find(".tea_name").text(
							data[i].tea_name);
/* 					$("#" + data[i].cou_id).find(".stu_num").text(
							data[i].stu_num);
					$("#" + data[i].cou_id).find(".task_num").text(
							data[i].task_num); */
					$("#" + data[i].cou_id).find(".cou_id")
							.text(data[i].cou_id);
							
					$("#" + data[i].cou_id).find(".update").attr("cou_name",data[i].cou_name)
					$("#" + data[i].cou_id).find(".update").attr("tea_id",data[i].tea_id)
					$("#" + data[i].cou_id).find(".update").attr("cou_id",data[i].cou_id)
					
					
					$("#" + data[i].cou_id).find(".delete").attr("data-id",data[i].cou_id)		
					
				}
				
				
				
			}
		
		
		})	
		
		
		
		$("body").on("click",".delete",function(){
			//alert($(this).attr("data-id"));
			if (!confirm("确认删除？")) {
						return;
					}
				var data1={cou_id:$(this).attr("data-id"),type:"delete"}
				$.ajax({
					url : '/Test/Manager/couhand',
					type : 'post',
					data : data1,
					dataType : "json",
					success : function(data) {
						location.reload();
						alert(data[0].msg);
						
					}
				});
		 	});
			
			$("body").on("click",".update",function(){
				//alert($(this).attr("data-id"));
				//alert($(this).attr("cou_id"))
				$("#type").val("update");
				$("#cou_name").val($(this).attr("cou_name"));
				$("#tea_id").val($(this).attr("tea_id"));
				$("#cou_id").val($(this).attr("cou_id"));
			});
		
		
		})

		
		$(function () { $('#add').on('hide.bs.modal', function () {
			//$("#type").val("add");
			//location.reload();
			/* $("#type").val("update");
			$("#cou_name").val("");
			$("#tea_id").val(""); */
		 
		})
		 });
</script>
</html>
