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

table.my-tab1 {
	font-size: 14px;
}
</style>
</head>

<body>
	<div id="wrap">
		<jsp:include page="man_header.jsp"></jsp:include>
		<div id="jishu">
			<a href="#">学生文件管理</a>
		</div>
		<div id="test-con">
			<div class="clear"></div>
			<div id="bj-inner">
				<div class="bj-infor">
					<table cellpadding="0" cellspacing="0" class="my-tab1">
						<thead>
							<tr>
								<th><a href="#" class="my-word" style="cursor: default;">学号</a></th>
								<th><a href="#" class="my-word" style="cursor: default;">姓名</a></th>
								<th><a href="#" class="my-word" style="cursor: default;">文件名</a></th>
								<th><a href="#" class="my-word" style="cursor: default;">上传时间</a></th>
								<th><a href="#" class="my-word" style="cursor: default;">上传位置</a></th>
								<th><a href="#" class="my-word">操作</a></th>
								<th><a href="#" class="my-word">操作</a></th>
							</tr>
						</thead>
						<tbody id="table">
							<tr id="tr" style="display: none">
								<td class="id">16111205119</td>
								<td class="name">伍方健</td>
								<td class="filename">16111205119.docx</td>
								<td class="time">2016/1/2</td>
								<td class="path">高等代数/task01</td>
								<td><a href="#" class="review">预览</a></td>
								<td><a href="#" class="delete">删除</a></td>
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
</body>
<script type="text/javascript">
$(document).ready(

		function() {

			var data = {type:"select"};

			$.ajax({
				url : '/Test/Manager/filehand',
				type : 'post',
				data : data,
				dataType : "json",
				success : function(data) {
					console.log(data)
					var table = document.getElementById("table");
					var box = document.getElementById("tr");
					var length = getJsonLength(data);
					var page = parseInt(getQueryString("page"));
					if (isNaN(page))
						page=1;
					
					var count=Math.ceil(length/15);
					if(page<=1){
						$(".pagination").append("<li><a href='"+"man_file.jsp?page="+"1"+"'>«</a></li>");
					}else{
						$(".pagination").append("<li><a href='"+"man_file.jsp?page="+(page-1)+"'>«</a></li>");
					}
						
					for(var i=0;i<count;i++){
						if((i+1)!=page){
							$(".pagination").append("<li><a href='"+"man_file.jsp?page="+(i+1)+"'>"+(i+1)+"</a></li>");	
						}
						else{
							$(".pagination").append("<li><a class='active' href='"+"man_file.jsp?page="+(i+1)+"'>"+(i+1)+"</a></li>");
						}
					}
					if(page>=count){
						$(".pagination").append("<li><a href='"+"man_file.jsp?page="+(page)+"'>»</a></li>");
					}else{
						$(".pagination").append("<li><a href='"+"man_file.jsp?page="+(page+1)+"'>»</a></li>");
					}
					
					
					
					for (var i = (page - 1) * 15; i < 15 * page; i++) {
						if (i >= length || i < 0)
							return;
						var newNode;
						newNode = box.cloneNode(true);
						table.appendChild(newNode);

						
						//newNode.style.display = 'block';
						newNode.id = ""+i;
						$("#" + i).removeAttr("style");
						
						$("#" + i).find(".id").text(
								data[i].stu_id);
						$("#" + i).find(".name").text(
								data[i].stu_name);
						$("#" + i).find(".filename").text(
								data[i].filepath);
						$("#" + i).find(".time").text(
								data[i].time);
						$("#" + i).find(".path").text(
								data[i].cou_name+"/"+data[i].task_name);
						$("#" + i).find(".review").attr("href","https://view.officeapps.live.com/op/view.aspx?src=wufangjian.top/Test/download/"+data[i].filepath);
						
						$("#" + i).find(".delete").attr("stu_id",data[i].stu_id)
						$("#" + i).find(".delete").attr("task_id",data[i].task_id)
					}

				} 

			})
			
			
			$("body").on("click",".delete",function(){
				//alert($(this).attr("data-id"));
				if (!confirm("确认删除？")) {
						return;
				}
				var data1={stu_id:$(this).attr("stu_id"),task_id:$(this).attr("task_id"),type:"delete"}
				$.ajax({
					url : '/Test/Manager/filehand',
					type : 'post',
					data : data1,
					dataType : "json",
					success : function(data) {
						alert(data[0].msg)
						location.reload()
					}
				});
			 });
		})
		
		
	
</script>
</html>
