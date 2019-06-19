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
#header .le-bg {
	border-bottom: 2px #2dbf7f solid;
}
</style>
</head>

<body>
	<div id="wrap">
		<jsp:include page="header.jsp"></jsp:include>
		<div id="jishu">
			<a href="#">我的课程</a>
		</div>
		<div id="test-con">

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
										<div>成绩</div>
										<div class="h4">
											<a class="h4-bg output" style="cursor: pointer;" href="#">导出</a>
										</div>
									</td>
									<td class="stu-con">
										<div>学生</div>
										<div class="h4">
											<span class="h4-bg student stu_num" name="73"
												style="overflow-wrap: break-word;">73</span>
										</div>
									</td>
									<td class="stu-con">
										<div>测试数</div>
										<div class="h4">
											<span class="h4-bg student task_num" title=""
												style="overflow-wrap: break-word;">1</span>
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
</body>

<script>
	$(document).ready(

			function() {
 
				var data = {
					tea_id : "<%=session.getAttribute("user")%>"
				}; 

				
				//var data={tea_id:"5001"}
				$.ajax({
					url : '/Test/Teacher/getCourse',
					type : 'post',
					data : data,
					dataType : "json",
					success : function(data) {
 						var main = document.getElementById("test-con");
						var box = document.getElementById("selectDiv");
						var page = parseInt(getQueryString("page"));
						if (isNaN(page))
							page = 1;
						var length = getJsonLength(data);

						var count = Math.ceil(length / 4);
						if (page <= 1) {
							$(".pagination").append(
									"<li><a href='" + "tea_course.jsp?page="
											+ "1" + "'>«</a></li>");
						} else {
							$(".pagination").append(
									"<li><a href='" + "tea_course.jsp?page="
											+ (page - 1) + "'>«</a></li>");
						}

						for (var i = 0; i < count; i++) {
							if ((i + 1) != page) {
								$(".pagination").append(
										"<li><a href='"
												+ "tea_course.jsp?page="
												+ (i + 1) + "'>" + (i + 1)
												+ "</a></li>");
							} else {
								$(".pagination").append(
										"<li><a class='active' href='"
												+ "tea_course.jsp?page="
												+ (i + 1) + "'>" + (i + 1)
												+ "</a></li>");
							}
						}
						if (page >= count) {
							$(".pagination").append(
									"<li><a href='" + "tea_course.jsp?page="
											+ (page) + "'>»</a></li>");
						} else {
							$(".pagination").append(
									"<li><a href='" + "tea_course.jsp?page="
											+ (page + 1) + "'>»</a></li>");
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
							$("#" + data[i].cou_id).find(".cou_name").attr("href","tea_task.jsp?cou_id="+data[i].cou_id)
							$("#" + data[i].cou_id).find(".stu_num").text(
									data[i].stu_num);
							$("#" + data[i].cou_id).find(".task_num").text(
									data[i].task_num);
							$("#" + data[i].cou_id).find(".cou_id").text(
									data[i].cou_id);
							$("#" + data[i].cou_id).find(".output").attr("href","/Test/Teacher/getScore?cou_id="+data[i].cou_id);
						}

					} 

				})
			})
</script>


</html>
