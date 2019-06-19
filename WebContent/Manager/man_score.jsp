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
<!-- <script src="../js/bootstrap.min.js"></script> -->
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
			<div id="container"
				style="height: 100%; height: 300px; display: none"></div>
		</div>
		<ul class="pagination" style="float: right"></ul>
	</div>
</body>
<script type="text/javascript" src="../js/echarts.min.js"></script>
<script type="text/javascript">
	$(document).ready(

			function() {
				var data={};
				$.ajax({
					url : '/Test/Manager/getScore',
					type : 'post',
					data : data,
					dataType : "json",
					success : function(data) {
						var main = document.getElementById("test-con");
						var box = document.getElementById("container");
						var page = parseInt(getQueryString("page"));
						if (isNaN(page))
							page = 1;
						var length = getJsonLength(data);

						var count = Math.ceil(length / 4);
						if (page <= 1) {
							$(".pagination").append(
									"<li><a href='" + "man_score.jsp?page="
											+ "1" + "'>«</a></li>");
						} else {
							$(".pagination").append(
									"<li><a href='" + "man_score.jsp?page="
											+ (page - 1) + "'>«</a></li>");
						}

						for (var i = 0; i < count; i++) {
							if ((i + 1) != page) {
								$(".pagination").append(
										"<li><a href='"
												+ "man_score.jsp?page="
												+ (i + 1) + "'>" + (i + 1)
												+ "</a></li>");
							} else {
								$(".pagination").append(
										"<li><a class='active' href='"
												+ "man_score.jsp?page="
												+ (i + 1) + "'>" + (i + 1)
												+ "</a></li>");
							}
						}
						if (page >= count) {
							$(".pagination").append(
									"<li><a href='" + "man_score.jsp?page="
											+ (page) + "'>»</a></li>");
						} else {
							$(".pagination").append(
									"<li><a href='" + "man_score.jsp?page="
											+ (page + 1) + "'>»</a></li>");
						}

						for (var i = (page - 1) * 4; i < 4 * page; i++) {
							var value=data[i];
							var title=value.cou_name;
							var tasks=value.tasks;
							
							var clo=[];
							var row=[];
							//alert(tasks.length)
							for(var j=0;j<tasks.length;j++){
								clo.push(tasks[j][1])
								if(tasks[j][2]==null){
									row.push(0)
								}else{
									row.push(parseInt(tasks[j][2]))
								}
							} 
							
							
							
							var dom = box.cloneNode(true);
							dom.style.display = 'block';
							main.appendChild(dom);
							var myChart = echarts.init(dom);
							var app = {};
							option = null;
							option = {
								title : {
									text : title
								},
								tooltip : {
									trigger : 'axis'
								},
								grid : {
									left : '3%',
									right : '4%',
									bottom : '3%',
									containLabel : true
								},
								toolbox : {
									feature : {
										saveAsImage : {}
									}
								},
								xAxis : {
									type : 'category',
									boundaryGap : false,
									data : clo
								},
								yAxis : {
									type : 'value'
								},
								series : [ {
									type : 'line',
									stack : '总量',
									data : row
								} ]
							};
							;
							if (option && typeof option === "object") {
								myChart.setOption(option, true);
							}

						}

					}
				});
			})
</script>
</html>