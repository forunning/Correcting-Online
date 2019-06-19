package web;

import bean.Task;
import net.sf.json.JSONArray;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import service.TeacherTaskService;
import tool.Tools;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class Tea_upTask
 */
@WebServlet("/Teacher/upTask")
public class Tea_taskhand extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Tea_taskhand() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//String path_temp="C:\\Program Files\\Apache Software Foundation\\Tomcat 8.0\\webapps\\Test\\download\\";
		String path_temp =this.getServletConfig().getServletContext().getRealPath("/download");
		//String path_temp = this.getServletContext().getRealPath("temp");
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
		
		Map<String,Object> map=new HashMap<String,Object>();
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
				// 获得上传文件的输入流
				InputStream in = item.getInputStream();
				// 将输入流的数据copy至服务器上
				//String path_store = this.getServletContext().getRealPath("download");
				//String path_store="C:\\Program Files\\Apache Software Foundation\\Tomcat 8.0\\webapps\\Test\\download\\";
				String path_store =this.getServletConfig().getServletContext().getRealPath("/download");
				File file = new File(path_store);
				if (!file.exists())
					file.mkdir();
				// 新建一个输出流
				FileOutputStream out = new FileOutputStream(new File(path_store +"/"+ filename));// 当然也可以直接path_store+"/"+fileName
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
		
		
		response.setCharacterEncoding("utf-8");
		PrintWriter outer = response.getWriter();
		

		// 实际撰写内容
		String type = (String) map.get("type");
		if (type.equals("delete")) {
			System.out.println("删除"+(String)map.get("task_id"));
			delTask((String)map.get("task_id"));
			
			JSONArray ja = JSONArray.fromObject(Tools.success("删除成功"));
			System.out.println(ja);
			outer.print(ja);
			return;
		} else {
			Task task = new Task();
			// 初始化task对象
			if(map.get("cou_id")!=null)
				task.setC_id(Integer.valueOf((String)map.get("cou_id")));
			if(map.get("edate")!=null)
				task.setE_time(new Timestamp(0).valueOf((String)map.get("edate")));
			if(map.get("sdate")!=null)
				task.setS_time(new Timestamp(0).valueOf((String)map.get("sdate")));
			if(map.get("file")!=null)
				task.setFile((String)map.get("file"));
			task.setName((String)map.get("task_name"));

			// 增加
			if (type.equals("add")) {
				//task.setId(null);
				addTask(task);
			} else if (type.equals("update")) {
				String task_id = (String)map.get("task_id");
				//System.out.println("");
				TeacherTaskService tts = new TeacherTaskService();
				System.out.println(task_id);
				Task t = tts.getTaskByID(task_id);
				//String path_store = this.getServletContext().getRealPath("download");
				//String path_store="C:\\Program Files\\Apache Software Foundation\\Tomcat 8.0\\webapps\\Test\\download\\";
				String path_store =this.getServletConfig().getServletContext().getRealPath("/download");
				File file=new File(path_store+"/"+t.getFile());
				if(file.exists()) {
					file.delete();
					System.out.println("删除"+t.getFile());
				}else {
					System.out.println("文件不存在");
				}
					
				
				
				task.setId(Integer.valueOf((String)map.get("task_id")));
				upTask(task);
				
			}
			JSONArray ja = JSONArray.fromObject(Tools.success("操作成功"));
			System.out.println(ja);
			outer.print(ja);
			
		}

	}

	protected void addTask(Task task) throws IOException {
		TeacherTaskService tts = new TeacherTaskService();
		tts.addTask(task);
	}

	protected void delTask(String task_id) throws IOException {
		TeacherTaskService tts = new TeacherTaskService();
		tts.deleteTask(task_id);
	}

	protected void upTask(Task task) throws IOException {
		TeacherTaskService tts = new TeacherTaskService();
		tts.updateTask(task);
	}

}
