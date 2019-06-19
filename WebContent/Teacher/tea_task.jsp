<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>My JSP 'Stu_header.jsp' starting page</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Stu_course</title>
<link href="../css/bootstrap.min.css" rel="stylesheet">
<link href="../css/stu_css.css" rel="stylesheet">
<script src="../js/jquery.js"></script>
<script src="../js/jquery.dotdotdot.min.js"></script>
<script src="../js/headshow.js"></script>

</head>

<body>
	<div id="wrap">
		<jsp:include page="header.jsp"></jsp:include>
		<div id="jishu">
			<a href="#">我的课程</a>&gt;<a
				id="cou_name" href="#"></a>
		</div>
		<div id="test-con">
			<div class="add">
				<a data-toggle="modal" data-target="#addtask" href="#" onclick="reset()"
					style="color: white;">添加作业</a>
			</div>
			<div class="clear"></div>
			<div class="class-infor class-disblock" id="selectDiv"
				style="display: none">
				<div class="class1 class eachDiv" data-clsid="7">
					<h2 style="width: 400px;">
						<a href="#" class="task_name">task05</a>
					</h2>
					<div class="message">
						<table cellpadding="0" cellspacing="0" class="message_tab">
							<tbody>

								<tr>
									<td class="stu-con exam-con">
										<div>
											<span class="h4-bg">文档</span>
										</div>
										<div class="h4">
											<a href="" class="filepath">预览</a>
										</div>
									</td>
									<td class="stu-con">
										<div id="div" class="ishand">操作</div>
										<div class="h4">
											<a data-toggle="modal" data-target="#addtask" href="#"
												class="update">修改</a>
										</div>
									</td>
									<td class="stu-con">
										<div id="div" class="ishand">操作</div>
										<div class="h4">
											<a href="#" class="delete">删除</a>
										</div>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="clear"></div>
					<div class="date">
						<span class="belong">ID：<span class="task_id">161112</span></span>
						<span class="start">结束日期：<span class="edate">2016-10-11</span></span>
						<span class="start">开始日期：<span class="sdate">2019-12-31</span></span>
					</div>
					<div class="clear"></div>
				</div>
			</div>
		</div>
		<ul class="pagination" style="float: right"></ul>
	</div>
	<form method="post" action="" class="form-horizontal" role="form"
		id="form_data" style="margin: 20px;" onsubmit="">
		
		<div class="modal fade" id="addtask" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<input type="hidden" id="type" value="add" /> <input type="hidden"
			id="tas_id" value="1001" />
			<div class="modal-dialog" style="width: 450px">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">×</button>
						<h4 class="modal-title" id="myModalLabel">添加作业</h4>
					</div>
					<div class="modal-body">
						<form class="form-horizontal" role="form">
							<div class="form-group">
								<label for="user_id" class="col-sm-3 control-label">作业名：</label>
								<div class="col-sm-9">
									<input type="text" class="form-control" id="task_name"
										name="user_id" value="">
								</div>
							</div>
							<div class="form-group">
								<label for="user_id" class="col-sm-3 control-label">上传文件：</label>
								<div class="col-sm-9">
									<input type="file" id="file" class="form-control" value="">
								</div>
							</div>
							<div class="form-group">
								<label for="lastname" class="col-sm-3 control-label">开始日期：</label>
								<div class="col-sm-9">
									<input type="date" class="form-control" name="startdate"
										id="sdate" value="">
								</div>
							</div>
							<div class="form-group">
								<label for="lastname" class="col-sm-3 control-label">截止日期：</label>
								<div class="col-sm-9">
									<input type="date" class="form-control" name="enddate" value=""
										id="edate">
								</div>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="button" class="btn btn-primary" data-dismiss="modal"
							onclick="add()">提交</button>
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
function reset() {
	$("#type").val("add");
}
</script>
<script>
	$(document)
			.ready(

					function() {
						var id = getQueryString("cou_id");
						if (id == null)
							return;
						//id="1001";
						var data = {
							cou_id : id
						};

						$
								.ajax({
									url : '/Test/Teacher/getTask',
									type : 'post',
									data : data,
									async : false,
									dataType : "json",
									success : function(data) {
										var main = document
												.getElementById("test-con");
										var box = document
												.getElementById("selectDiv");
										var page = parseInt(getQueryString("page"));
										if (isNaN(page))
											page = 1;
										var length = getJsonLength(data);

										var count = Math.ceil(length / 4);
										if (page <= 1) {
											$(".pagination")
													.append(
															"<li><a href='"
																	+ "tea_task.jsp?page="
																	+ "1"
																	+ "'>«</a></li>");
										} else {
											$(".pagination")
													.append(
															"<li><a href='"
																	+ "tea_task.jsp?page="
																	+ (page - 1)
																	+ "'>«</a></li>");
										}

										for (var i = 0; i < count; i++) {
											if ((i + 1) != page) {
												$(".pagination")
														.append(
																"<li><a href='"
																		+ "tea_task.jsp?page="
																		+ (i + 1)
																		+ "'>"
																		+ (i + 1)
																		+ "</a></li>");
											} else {
												$(".pagination")
														.append(
																"<li><a class='active' href='"
																		+ "tea_task.jsp?page="
																		+ (i + 1)
																		+ "'>"
																		+ (i + 1)
																		+ "</a></li>");
											}
										}
										if (page >= count) {
											$(".pagination")
													.append(
															"<li><a href='"
																	+ "tea_task.jsp?page="
																	+ (page)
																	+ "'>»</a></li>");
										} else {
											$(".pagination")
													.append(
															"<li><a href='"
																	+ "tea_task.jsp?page="
																	+ (page + 1)
																	+ "'>»</a></li>");
										}

										for (var i = (page - 1) * 4; i < 4 * page; i++) {
											if (i >= length || i < 0)
												return;
											var newNode = box.cloneNode(true);
											newNode.style.display = 'block';
											newNode.id = data[i].task_id;
											main.appendChild(newNode);
											$("#" + data[i].task_id).find(
													".task_name").text(
													data[i].task_name);
											$("#" + data[i].task_id).find(
													".task_id").text(
													data[i].task_id);
											$("#" + data[i].task_id).find(
													".task_name").attr(
													"href",
													"tea_detail.jsp?page=1&task_id="
															+ data[i].task_id);

											$("#" + data[i].task_id).find(
													".sdate").text(
													data[i].stime);
											$("#" + data[i].task_id).find(
													".edate").text(
													data[i].etime);
											$("#" + data[i].task_id)
													.find(".filepath")
													.attr(
															"href",
															"https://view.officeapps.live.com/op/view.aspx?src=wufangjian.top:8080/Test/download/"
																	+ data[i].filepath);

											$("#" + data[i].task_id).find(
													".update").attr("task_id",
													data[i].task_id)
											$("#" + data[i].task_id).find(
													".delete").attr("task_id",
													data[i].task_id)

										}
									}

								})

						var data1 = {
							cou_id : id
						}
						$
								.ajax({
									url : '/Test/Student/getTask',
									type : 'get',
									data : data1,
									dataType : "json",
									success : function(data2) {
										console.log(data2[0].cou_name)
										document.getElementById("cou_name").innerHTML = data2[0].cou_name;
									}
								})

						$("body").on(
								"click",
								".delete",
								function() {
									//alert($(this).attr("task_id"));
									//var data1={task_id:$(this).attr("task_id"),type:"delete"}
									if (!confirm("确认删除？")) {
									    	return;
									}
									
									var formData = new FormData();
									formData.append('task_id', $(this).attr(
											"task_id"));
									formData.append('type', "delete");
									$.ajax({
										url : '/Test/Teacher/upTask',
										type : 'post',
										data : data1,
										data : formData,
										contentType : false,
										processData : false,
										success : function(data) {
											location.reload();
											alert(data[0].msg);
											
										}
									});
								});

						$("body").on("click", ".update", function() {
							//alert($(this).attr("data-id"));
							var data1 = {
								task_id : $(this).attr("task_id")
							}

							$.ajax({
								url : '/Test/Teacher/getTask',
								type : 'get',
								data : data1,
								dataType : "json",
								success : function(data) {
									$("#task_name").val(data[0].name);
									$("#sdate").val(data[0].s_time);
									$("#edate").val(data[0].e_time);
									$("#type").val("update");
									$("#tas_id").val(data[0].id);
									$(".modal-title").text("作业操作");
								}
							})

						});
					})


</script>
<script type="text/javascript">
	function add() {
		
		
		var task_id = $("#tas_id").val();
		var taskname = $("#task_name").val();
		var filename = $("#file").val();
		var sdate = $("#sdate").val() + " 00:00:00";
		var edate = $("#edate").val() + " 00:00:00";
		var type = $("#type").val();

		//alert("task_id:"+task_id+"\n"+"task_name:"+taskname+"\n"+"type"+type+"\n")
		//return;
		var cou_id = getQueryString("cou_id");
		//var cou_id="1001";

		if (taskname == "" || filename == "" || sdate == "" || edate == "") {
			alert("请检查各项输入，提交失败");
			return;
		}

		var filelast = $("#file")[0].files[0].name.split(".")[1];
		if (filelast != "doc" && filelast != "docx") {
			alert("请选择.doc或.docx的文件，提交失败");
			return;
		}
		
		var size=$("#file")[0].files[0].size/1024;
    	if(size>5000){
    		alert("请选择大小不超过5M的文件！");
    		location.reload();
    		return;
    	}
		
		

		var now = new Date();
		var year = now.getFullYear();
		var month = (now.getMonth() + 1).toString();
		var day = (now.getDate()).toString();

		now = new Date(year + "-" + month + "-" + day);
		var stime = new Date(sdate);
		var etime = new Date(edate);
		if (stime < now || etime < stime) {
			alert("时间错误，提交失败");
			return;
		}

		var formData = new FormData();
		formData.append('file', $("#file")[0].files[0]);
		formData.append('task_id', task_id);
		formData.append('task_name', taskname);
		formData.append('sdate', sdate);
		formData.append('edate', edate);
		formData.append('cou_id', cou_id);
		formData.append('type', type);
		$.ajax({
			url : "/Test/Teacher/upTask",
			type : "POST",
			data : formData,
			contentType : false,
			processData : false,
			success : function(data) {
				location.reload();
				alert("操作成功");
			}
		});

		//alert("name:"+taskname+"\n"+"filename:"+filename+"\n"+"sdate:"+sdate+"\nedate:"+edate)
	}
	
</script>
</html>
