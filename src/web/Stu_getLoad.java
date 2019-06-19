package web;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import bean.Submission;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import service.StudentService;
import tool.Tools;

/**
 * Servlet3.0文件上传下载
 */
@WebServlet("/Student/upload")
@MultipartConfig
public class Stu_getLoad extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//String path_temp = this.getServletContext().getRealPath("temp");
		String path_temp =this.getServletConfig().getServletContext().getRealPath("/download");
		//String path_temp="C:\\Program Files\\Apache Software Foundation\\Tomcat 8.0\\webapps\\Test\\download\\";
		// 第一个参数是设置缓存文件的大小(也就是要多大才缓存)，第二个参数是设置缓存的地址
		// DiskFileItemFactory factory=new DiskFileItemFactory(1024*1024, new
		// File(path_temp));
		// 当然也可以这样设置
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(1024 * 1024);// 设置缓存大小为1M
		factory.setRepository(new File(path_temp));

		// 2.第二步新建一个文件上传的核心类
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 3.判断是否是一个文件上传的表单，即判断是否是enctype="multipart/form-data"
		boolean multipartContent = ServletFileUpload.isMultipartContent(request);
		// 是文件上传类表单
		// 4.设置上传文件名的编码方式,目的是解决文件名为中文的乱码问题
		upload.setHeaderEncoding("UTF-8");
		// 5.解析request获得表单项的集合,设置设置泛型为FileItem
		List<FileItem> parseRequest = null;
		try {
			parseRequest = upload.parseRequest(request);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		HashMap<String,String> map=new HashMap<String,String>();
		// 6.遍历文件项集合
		for (FileItem item : parseRequest) {
			// 7.判断是否是普通表单项
			boolean formField = item.isFormField();
			if (formField) {
				// 普通表单项
				// 获得表单的name属性值
				String fieldName = item.getFieldName();
				String fieldValue = item.getString("UTF-8");// 参数表示对普通表单项的内容进行UTF-8编码，防止中文乱码，若非中文直接getString()
				System.out.println(fieldName + "-" + fieldValue);
				map.put(fieldName, fieldValue);
			} else {
				// 文件上传项
				// 获得文件上传的内容
				String[] ps=item.getName().split("\\\\");
				String filename =System.currentTimeMillis()+"$"+ps[ps.length-1];
				System.out.println("file-"+filename);
				map.put("file", filename);
				//String path_store = this.getServletContext().getRealPath("download");
				String path_store=this.getServletConfig().getServletContext().getRealPath("/download");

				// 获得上传文件的输入流
				InputStream in = item.getInputStream();
				// 将输入流的数据copy至服务器上
				
				System.out.println(path_store + "/" + filename);
				File file = new File(path_store);
				if (!file.exists())
					file.mkdir();
				// 新建一个输出流
				FileOutputStream out = new FileOutputStream(new File(path_store +"/" + filename));// 当然也可以直接path_store+"/"+fileName
				int len = 0;
				byte[] buffer = new byte[1024];
				while ((len = in.read(buffer)) > 0) {
					out.write(buffer, 0, len);
				}
				// 当然上面的操作可以通过IO工具简化书写，其它步骤相同
				// IOUtils.copy(in, out);
				in.close();
				out.close();
				// 8.删除临时文件
				item.delete();
			}
		}
		
		//数据库处理
		StudentService ss = new StudentService();
		Submission exist = isExist(map.get("stu_id"), map.get("task_id"));
		if(exist!=null) {
			//如果存在将记录删除
			ss.delSubmissionByID(map.get("stu_id"), map.get("task_id"));
			String filepath = exist.getFilepath();
			//系统删除文件
			System.out.println("删除系统文件"+exist.getFilepath());
			//String path_store = this.getServletContext().getRealPath("download");
			//String path_store="C:\\Program Files\\Apache Software Foundation\\Tomcat 8.0\\webapps\\Test\\download\\";
			String path_store=this.getServletConfig().getServletContext().getRealPath("/download");
			File file=new File(path_store+"/"+filepath);
			if(file.exists())
				file.delete();
		}
		
		Submission sub = new Submission();
		sub.setFilepath(map.get("file"));//填入文件路径全名
		sub.setStu_id(map.get("stu_id"));
		sub.setTask_id(map.get("task_id"));
		//获取服务器时间
		Timestamp time = new Timestamp(System.currentTimeMillis());
		sub.setTime(time);
		//添加记录
		ss.addSubmission(sub);
		
		System.out.println("upload success");
		response.setCharacterEncoding("utf-8");
		PrintWriter outer = response.getWriter();
		JSONArray ja = JSONArray.fromObject(Tools.success(path_temp));
		outer.print(ja);
		// doGet(request, response);
	}
	
	private static Submission isExist(String stu_id,String task_id) throws IOException {
		
		StudentService ss = new StudentService();
		ArrayList<Submission> submission = ss.getSubmissionListByID(stu_id, task_id);
		for(Submission s:submission) {
			return s;
		}
		
		return null;
	}
	
	

}
