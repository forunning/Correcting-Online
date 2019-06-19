<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
			<a href="#">我的课程</a>&gt;<a id="cou_name" href="#"></a>
		</div>
		<div id="test-con">
			<div class="class-infor class-disblock" id="selectDiv"
				style="display: none">
				<div class="class1 class eachDiv" data-clsid="7">
					<h2 style="width: 400px;" class="task_name">task05</h2>
					<div class="message">
						<table cellpadding="0" cellspacing="0" class="message_tab">
							<tbody>

								<tr>
									<td class="stu-con exam-con">
										<div>
											<span class="h4-bg">作业</span>
										</div>
										<div class="h4">
											<a href="#" class="filepath">预览</a>
										</div>
									</td>
									<td class="stu-con">
										<div id="div" class="ishand">未提交</div>
										<div class="h4">
											<input type="file" style="width: 74px" class="file h4-bg" value="上传"
												onchange="upload(this)">
										</div>
									</td>
									<td class="stu-con">
										<div>成绩</div>
										<div class="h4">
											<div class="h4-bg student" title=""
												style="overflow-wrap: break-word;">--</div>
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
	<jsp:include page="footer.jsp"></jsp:include>
</body>
<script>
	$(document).ready(
			
			function(){
			var id=getQueryString("cou_id");
			//id="1001";
			var data={stu_id:"<%=session.getAttribute("user")%>",cou_id:id};
			//var data={stu_id:"1611205119",cou_id:id}; 

			$.ajax({
				url:'/Test/Student/getTask',
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
						$(".pagination").append("<li><a href='"+"stu_task.jsp?page="+"1"+"'>«</a></li>");
					}else{
						$(".pagination").append("<li><a href='"+"stu_task.jsp?page="+(page-1)+"'>«</a></li>");
					}
						
					for(var i=0;i<count;i++){
						if((i+1)!=page){
							$(".pagination").append("<li><a href='"+"stu_task.jsp?page="+(i+1)+"'>"+(i+1)+"</a></li>");	
						}
						else{
							$(".pagination").append("<li><a class='active' href='"+"stu_task.jsp?page="+(i+1)+"'>"+(i+1)+"</a></li>");
						}
					}
					if(page>=count){
						$(".pagination").append("<li><a href='"+"stu_task.jsp?page="+(page)+"'>»</a></li>");
					}else{
						$(".pagination").append("<li><a href='"+"stu_task.jsp?page="+(page+1)+"'>»</a></li>");
					}
					
					
					for (var i = (page - 1) * 4; i < 4 * page; i++) {
						if (i >= length || i < 0)
						 	return; 
						var newNode = box.cloneNode(true);
						newNode.style.display = 'block';
						newNode.id = data[i].task_id;
						main.appendChild(newNode);
						$("#" + data[i].task_id).find(".task_name").text(data[i].task_name);
						$("#" + data[i].task_id).find(".student").text(data[i].score);
						$("#" + data[i].task_id).find(".sdate").text(data[i].stime);
						$("#" + data[i].task_id).find(".edate").text(data[i].etime);
						$("#" + data[i].task_id).find(".task_id").text(data[i].task_id);
						$("#" + data[i].task_id).find(".filepath").attr("href","https://view.officeapps.live.com/op/view.aspx?src=wufangjian.top:8080/Test/download/"+data[i].stu_filepath);
						
						
						var time=new Date(data[i].etime);
				    	var now = new Date();
				    	var year = now.getFullYear(); 
				    	var month =(now.getMonth() + 1).toString(); 
				    	var day = (now.getDate()).toString();  
				    	
				    	now=new Date(year+"-"+month+"-"+day);
				    	
				    	if(now>time){
				    		$("#" + data[i].task_id).find(".file").attr("disabled","disabled");
				    		$("#" + data[i].task_id).find(".file").attr("title","作业已截止");
				    	}else{
				    		$("#" + data[i].task_id).find(".file").attr("title",data[i].task_id);
				    	}
						
						
						if(data[i].stu_filepath==""){
							$("#" + data[i].task_id).find(".ishand").text("未提交");
						}else{
							$("#" + data[i].task_id).find(".ishand").text("已提交");
						}
						
					}
					
					
					
				}
			
			
			})	
			
			
			var data1={cou_id:id}
			$.ajax({
				url:'/Test/Student/getTask',
				type:'get',
				data:data1,
				dataType:"json",
				success:function(data2){
					console.log(data2[0].cou_name)
					document.getElementById("cou_name").innerHTML=data2[0].cou_name;
				}
			})
			
			})

</script>

<script> 
    function upload(obj){
    	var size=$(obj)[0].files[0].size/1024;
    	if(size>5000){
    		alert("请选择大小不超过5M的文件！");
    		location.reload();
    		return;
    	}
    	
    	
   	 	var formData = new FormData();
      	formData.append('file', $(obj)[0].files[0]);
      	formData.append('stu_id', "<%=session.getAttribute("user")%>");
      	//formData.append('stu_id', "1611205119");
      	formData.append('task_id', $(obj)[0].title);
 	  	if($(obj)[0].files[0].name.split(".")[1]!="doc"&&$(obj)[0].files[0].name.split(".")[1]!="docx"){
 	  		location.reload();
 	  		alert("请选择.doc或.docx格式文档");
 	  		return;
 	  	}
        $.ajax({
        url: "/Test/Student/upload",
        type: "POST",
        data: formData,
        cache: false,
        async:false,
        contentType: false,
        processData: false,
        success: function (data) {
        	console.log(data);
        	alert("上传成功！");
        	location.reload();
        }
      }); 
      
    }
</script>
</html>
