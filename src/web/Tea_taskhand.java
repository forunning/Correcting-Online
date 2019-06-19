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
		// ��һ�����������û����ļ��Ĵ�С(Ҳ����Ҫ���Ż���)���ڶ������������û���ĵ�ַ
		// DiskFileItemFactory factory=new DiskFileItemFactory(1024*1024, new
		// File(path_temp));
		// ��ȻҲ������������
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(1024 * 1024);// ���û����СΪ1M
		factory.setRepository(new File(path_temp));

		// 2.�ڶ����½�һ���ļ��ϴ��ĺ�����
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 3.�ж��Ƿ���һ���ļ��ϴ��ı������ж��Ƿ���enctype="multipart/form-data"
		boolean multipartContent = ServletFileUpload.isMultipartContent(request);
		// ���ļ��ϴ����
		// 4.�����ϴ��ļ����ı��뷽ʽ,Ŀ���ǽ���ļ���Ϊ���ĵ���������
		upload.setHeaderEncoding("UTF-8");
		// 5.����request��ñ���ļ���,�������÷���ΪFileItem
		List<FileItem> parseRequest = null;
		try {
			parseRequest = upload.parseRequest(request);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		
		Map<String,Object> map=new HashMap<String,Object>();
		// 6.�����ļ����
		for (FileItem item : parseRequest) {
			// 7.�ж��Ƿ�����ͨ����
			boolean formField = item.isFormField();
			if (formField) {
				// ��ͨ����
				// ��ñ���name����ֵ
				String fieldName = item.getFieldName();
				String fieldValue = item.getString("UTF-8");// ������ʾ����ͨ��������ݽ���UTF-8���룬��ֹ�������룬��������ֱ��getString()
				System.out.println(fieldName + "-" + fieldValue);
				map.put(fieldName, fieldValue);
			} else {
				// �ļ��ϴ���
				// ����ļ��ϴ�������
				String[] ps=item.getName().split("\\\\");
				String filename =System.currentTimeMillis()+"$"+ps[ps.length-1];
				System.out.println("file-"+filename);
				map.put("file", filename);
				// ����ϴ��ļ���������
				InputStream in = item.getInputStream();
				// ��������������copy����������
				//String path_store = this.getServletContext().getRealPath("download");
				//String path_store="C:\\Program Files\\Apache Software Foundation\\Tomcat 8.0\\webapps\\Test\\download\\";
				String path_store =this.getServletConfig().getServletContext().getRealPath("/download");
				File file = new File(path_store);
				if (!file.exists())
					file.mkdir();
				// �½�һ�������
				FileOutputStream out = new FileOutputStream(new File(path_store +"/"+ filename));// ��ȻҲ����ֱ��path_store+"/"+fileName
				int len = 0;
				byte[] buffer = new byte[1024];
				while ((len = in.read(buffer)) > 0) {
					out.write(buffer, 0, len);
				}
				// ��Ȼ����Ĳ�������ͨ��IO���߼���д������������ͬ
				// IOUtils.copy(in, out);
				in.close();
				out.close();
				// 8.ɾ����ʱ�ļ�
				item.delete();
			}
		}
		
		
		response.setCharacterEncoding("utf-8");
		PrintWriter outer = response.getWriter();
		

		// ʵ��׫д����
		String type = (String) map.get("type");
		if (type.equals("delete")) {
			System.out.println("ɾ��"+(String)map.get("task_id"));
			delTask((String)map.get("task_id"));
			
			JSONArray ja = JSONArray.fromObject(Tools.success("ɾ���ɹ�"));
			System.out.println(ja);
			outer.print(ja);
			return;
		} else {
			Task task = new Task();
			// ��ʼ��task����
			if(map.get("cou_id")!=null)
				task.setC_id(Integer.valueOf((String)map.get("cou_id")));
			if(map.get("edate")!=null)
				task.setE_time(new Timestamp(0).valueOf((String)map.get("edate")));
			if(map.get("sdate")!=null)
				task.setS_time(new Timestamp(0).valueOf((String)map.get("sdate")));
			if(map.get("file")!=null)
				task.setFile((String)map.get("file"));
			task.setName((String)map.get("task_name"));

			// ����
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
					System.out.println("ɾ��"+t.getFile());
				}else {
					System.out.println("�ļ�������");
				}
					
				
				
				task.setId(Integer.valueOf((String)map.get("task_id")));
				upTask(task);
				
			}
			JSONArray ja = JSONArray.fromObject(Tools.success("�����ɹ�"));
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
