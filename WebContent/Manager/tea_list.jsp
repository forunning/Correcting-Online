<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>My JSP 'Stu_header.jsp' starting page</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Stu_course</title>
<!-- <link href="../css/stu_css.css" rel="stylesheet"> -->
<script src="../js/jquery.js"></script>
<script src="../js/jquery.dotdotdot.min.js"></script>
<script src="../js/headshow.js"></script>
<link href="../css/bootstrap.min.css" rel="stylesheet">
<!-- <script src="../js/bootstrap.min.js"></script> -->
<link href="../css/stu_css.css" rel="stylesheet">
<style type="text/css">
#header .fir {
	border-bottom: 2px #2dbf7f solid;
}

table.my-tab1 {
	font-size: 14px;
}
</style>
</head>

<body>
	<div id="wrap">
		<jsp:include page="man_header.jsp"></jsp:include>
		<div id="jishu">
			<a href="#">教师账号管理</a>
		</div>
		<div id="test-con">
			<div class="add">
				<a data-toggle="modal" data-target="#add"
					style="color: white; cursor: pointer;">添加教师</a>
			</div>
			<div class="clear"></div>
			<div id="bj-inner">
				<div class="bj-infor">
					<div class="clear"></div>
					<table cellpadding="0" cellspacing="0" class="my-tab1">
						<thead>
							<tr>
								<th><a href="javascript:void(0)" class="my-word"
									style="cursor: default;">学号</a></th>
								<th><a href="javascript:void(0)" class="my-word"
									style="cursor: default;">姓名</a></th>
								<th><a href="javascript:void(0)" class="my-word"
									style="cursor: default;">性别</a></th>
								<th><a href="javascript:void(0)" class="my-word"
									style="cursor: default;">入职年份</a></th>
								<th><a href="javascript:void(0)" class="my-word"
									style="cursor: default;">学院</a></th>
								<th><a href="javascript:void(0)" class="my-word"
									style="cursor: default;">密码</a></th>
								<th><a href="javascript:void(0)" class="my-word">操作</a></th>
								<th><a href="javascript:void(0)" class="my-word">操作</a></th>
							</tr>
						</thead>
						<tbody id="table1">
							<tr id="tr1" style="display: none">
								<td class="id" style="cursor: default;">16111205119</td>
								<td class="name" style="cursor: default;">伍方健</td>
								<td class="sex" style="cursor: default;">男</td>
								<td class="time" style="cursor: default;">2016</td>
								<td class="department" style="cursor: default;">计算机与信息</td>
								<td class="password" style="cursor: default;">123456</td>
								<td style="cursor: pointer;" data-toggle="modal"
									data-target="#add" class="update">修改</td>
								<td style="cursor: pointer;" class="delete">删除</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="yeshu"></div>
			</div>
		</div>
		<ul class="pagination" style="float: right"></ul>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
	<form method="post" action="" class="form-horizontal" role="form"
		id="form_data" onsubmit="" style="margin: 20px;">
		<input type="hidden" value="add" id="type">
		<div class="modal fade" id="add" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 450px">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">×</button>
						<h4 class="modal-title" id="myModalLabel">添加用户</h4>
					</div>
					<div class="modal-body">
						<form class="form-horizontal" role="form">
							<div class="form-group">
								<label for="user_id" class="col-sm-3 control-label">姓名</label>
								<div class="col-sm-9">
									<input type="text" class="form-control" id="name"
										name="user_id" value="">
								</div>
							</div>
							<div class="form-group">
								<label for="lastname" class="col-sm-3 control-label" id="sex">性别</label>
								<div class="col-sm-9">
									<label class="radio-inline"> <input type="radio"
										name="sex" id="optionsRadios3" value="男" checked> 男
									</label> <label class="radio-inline"> <input type="radio"
										name="sex" id="optionsRadios4" value="女"> 女
									</label>
								</div>
							</div>
							<div class="form-group">
								<label for="lastname" class="col-sm-3 control-label" id="number">职工号</label>
								<div class="col-sm-9">
									<input type="text" class="form-control" name="startdate"
										 id="id" value="">
								</div>
							</div>
							<div class="form-group">
								<label for="lastname" class="col-sm-3 control-label" id="">密码</label>
								<div class="col-sm-9">
									<input type="text" class="form-control" name="startdate"
										id="password" value="">
								</div>
							</div>
							<div class="form-group">
								<label for="lastname" class="col-sm-3 control-label" id="time">入职年份</label>
								<div class="col-sm-9">
									<input type="text" class="form-control" name="startdate"
										id="entrolltime" value="">
								</div>
							</div>
							<div class="form-group">
								<label for="lastname" class="col-sm-3 control-label">学院</label>
								<div class="col-sm-9">
									<input type="text" class="form-control" name="startdate"
										value="" id="department">
								</div>
							</div>

						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="button" class="btn btn-default" data-dismiss="modal"
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
	$("body").on("click", "#sub", function() {
		//alert($("#type").val())
		/* alert($("#type").val()+"-"+$("#name").val()+"-"+$("#specialty").val()+"-"+$("#department").val()+"-"+$("#entrolltime").val()) */

		if($("#name").val()==""||$("#department").val()==""){
			alert("请检查姓名学院格式！");
			return;
		}
		if($("#type").val()=="add"&&!(/^\d{10}$/.test($("#id").val()))){
			alert("请检查职工号格式！");
			return;
		}
		
		if(!(/^\d{10}$/.test($("#password").val()))){
			alert("请检查密码格式！");
			return;
		}
		if(!(/^\d{4}$/.test($("#entrolltime").val()))){
			alert("请检查入职时间格式！");
			return;
		}
		
		
		
		
		var data = {
			id : $("#id").val(),
			name : $("#name").val(),
			department : $("#department").val(),
			worktime : $("#entrolltime").val(),
			password : $("#password").val(),
			sex : $('input:radio[name="sex"]:checked').val(),
			type : $("#type").val()
		}
		console.log(data)
		$.ajax({
			url : '/Test/Manager/teahand',
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
<script type="text/javascript">
	$(document).ready(

			function() {

				var data = {
					type : "select"
				};
				$.ajax({
					url : '/Test/Manager/teahand',
					type : 'post',
					data : data,
					dataType : "json",
					success : function(data) {
						console.log(data)
						var table = document.getElementById("table1");
						var box = document.getElementById("tr1");
						var length = getJsonLength(data);
						var page = parseInt(getQueryString("page"));
						if (isNaN(page))
							page = 1;

						var count = Math.ceil(length / 15);
						if (page <= 1) {
							$(".pagination").append(
									"<li><a href='" + "tea_list.jsp?page="
											+ "1" + "'>«</a></li>");
						} else {
							$(".pagination").append(
									"<li><a href='" + "tea_list.jsp?page="
											+ (page - 1) + "'>«</a></li>");
						}

						for (var i = 0; i < count; i++) {
							if ((i + 1) != page) {
								$(".pagination").append(
										"<li><a href='" + "tea_list.jsp?page="
												+ (i + 1) + "'>" + (i + 1)
												+ "</a></li>");
							} else {
								$(".pagination").append(
										"<li><a class='active' href='"
												+ "tea_list.jsp?page="
												+ (i + 1) + "'>" + (i + 1)
												+ "</a></li>");
							}
						}
						if (page >= count) {
							$(".pagination").append(
									"<li><a href='" + "tea_list.jsp?page="
											+ (page) + "'>»</a></li>");
						} else {
							$(".pagination").append(
									"<li><a href='" + "tea_list.jsp?page="
											+ (page + 1) + "'>»</a></li>");
						}

						for (var i = (page - 1) * 15; i < 15 * page; i++) {
							if (i >= length || i < 0)
								return;
							var newNode;
							newNode = box.cloneNode(true);
							table.appendChild(newNode);

							//newNode.style.display = 'block';
							newNode.id = data[i].id;
							$("#" + data[i].id).removeAttr("style");

							$("#" + data[i].id).find(".id").text(data[i].id);
							$("#" + data[i].id).find(".name")
									.text(data[i].name);
							$("#" + data[i].id).find(".sex").text(data[i].sex);
							$("#" + data[i].id).find(".time").text(
									data[i].worktime);
							$("#" + data[i].id).find(".department").text(
									data[i].department);
							$("#" + data[i].id).find(".password").text(
									data[i].password);
							$("#" + data[i].id).find(".update").attr("data-id",
									data[i].id)
							$("#" + data[i].id).find(".delete").attr("data-id",
									data[i].id)
						}

					}

				})

				$("body").on("click", ".delete", function() {
					//alert($(this).attr("data-id"));
					if (!confirm("确认删除？")) {
						return;
					}
					var data1 = {
						tea_id : $(this).attr("data-id"),
						type : "delete"
					}
					$.ajax({
						url : '/Test/Manager/teahand',
						type : 'post',
						data : data1,
						dataType : "json",
						success : function(data) {
							location.reload();
							alert(data[0].msg);
							
						}
					});
				});

				$("body").on("click", ".update", function() {
					//alert($(this).attr("data-id"));

					var data1 = {
						type : "select",
						tea_id : $(this).attr("data-id")
					}
					$.ajax({
						url : '/Test/Manager/teahand',
						type : 'post',
						data : data1,
						dataType : "json",
						success : function(data) {
							$("#type").val("update");
							
							$("#id").val(data[0].id);
							$("#id").attr("disabled","disabled");
							$("#name").val(data[0].name);
							$("#entrolltime").val(data[0].worktime);
							$("#department").val(data[0].department);
							$("#password").val(data[0].password);
							if (data[0].sex == "女") {
								$("#optionsRadios4").prop('checked', true);
							}
							$(".modal-title").text("修改用户");
							//alert($('input:radio[name="sex"]:checked').val())
						}
					})

				});

			})
	$('#add').on('hide.bs.modal', function() {
		//$("#type").val("add");
		//location.reload()
		/* $("#optionsRadios3").prop('checked', true);
		$(".modal-title").text("添加用户");
		//$("#type").val("add");
		$("#id").val("");
		$("#id").removeAttr("disabled");
		$("#name").val("");
		$("#entrolltime").val("");
		$("#department").val("");
		$("#password").val(""); */
	});
</script>
</html>
