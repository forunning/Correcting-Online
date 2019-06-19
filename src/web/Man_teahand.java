package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import bean.Teacher;
import bean.TeacherCourse;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import service.ManagerService;
import service.TeacherCourseService;
import service.TeacherService;
import tool.TimestampProcessor;
import tool.Tools;

/**
 * Servlet implementation class Man_setTea
 */
@WebServlet("/Manager/teahand")
public class Man_teahand extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Man_teahand() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println(request.getParameter(""));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		//实际内容
		String type = request.getParameter("type");
		
		System.out.println(type+"-");
		//对教师查询
		if(type.equals("select") ){
			System.out.println("select*");
			String id = request.getParameter("tea_id");
			if(id == ""||id == null) {
				id = "%";
			}
			ArrayList<Teacher> teachers = new ArrayList<Teacher>();
			ManagerService managerService = new ManagerService();
			teachers = managerService.getTeacher(id);
			JsonConfig jsonConfig = new JsonConfig();                                                          
			jsonConfig.registerJsonValueProcessor(java.sql.Timestamp.class, new TimestampProcessor ());
			JSONArray ja = JSONArray.fromObject(teachers,jsonConfig);
			out.print(ja);
		}
		//对教师删除
		else if(type.equals("delete")) {
			System.out.println(type+"delete");
			ManagerService managerService = new ManagerService();
			Teacher teacher = new Teacher();
			String tea_id = request.getParameter("tea_id");
			boolean b = TeaIsInCourse(tea_id);
			System.out.println(b);
			if(!b) {
				teacher.setId(tea_id);
				managerService.deleteTeacher(teacher);
			}else {
				JSONArray ja = JSONArray.fromObject(Tools.success("有课程关联，请将课程的教师修改再删除"));
				out.print(ja);
				return;
			}
			JSONArray ja = JSONArray.fromObject(Tools.success("操作成功"));
			out.print(ja);
		}else {//增加或者修改
			
			Teacher teacher = new Teacher();
			teacher.setId(request.getParameter("id"));
			teacher.setDepartment(request.getParameter("department"));
			teacher.setName(request.getParameter("name"));
			teacher.setPhone("");
			teacher.setSex(request.getParameter("sex"));
			teacher.setWorktime(request.getParameter("worktime"));
			teacher.setPassword(request.getParameter("password"));
			ManagerService managerService = new ManagerService();
			if(type.equals("add")) {
				if(TeaIsExits(teacher.getId())) {
					JSONArray ja = JSONArray.fromObject(Tools.success("id已存在！操作失败！"));
					out.print(ja);
					return;
				}
				managerService.addTeacher(teacher);
			}
			if(type.equals("update")) {
				teacher.setId(request.getParameter("id"));
				managerService.updateTeacher(teacher);
			}
			JSONArray ja = JSONArray.fromObject(Tools.success("操作成功"));
			out.print(ja);
		}
	}
	
	protected boolean TeaIsInCourse(String tea_id) throws IOException{
		boolean b = true;
		if(tea_id.equals("")||tea_id == null) b=true;
		else {
			ArrayList<TeacherCourse> tcs = new ArrayList<TeacherCourse>();
			TeacherCourseService service = new TeacherCourseService();
			tcs = service.getTeacherCourse(tea_id);
			if(tcs.isEmpty()) b = false;
			else b = true;
		}
		
		return b;
	}
	protected boolean TeaIsExits(String tea_id) throws IOException {
		boolean b = false;
		
		ManagerService managerService = new ManagerService();
		if(!managerService.getTeacher(tea_id).isEmpty())
			b = true;
		
		return b;
	}


}
