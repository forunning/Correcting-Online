package web;

import service.*;
import tool.TimestampProcessor;
import tool.Tools;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import bean.StudentCourse;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

/**
 * Servlet implementation class Man_setStu
 */
@WebServlet("/Manager/stuhand")
public class Man_stuhand extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Man_stuhand() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		//实际内容
		String type = request.getParameter("type");
		System.out.println(type);
		//对学生查询
		if(type.equals("select")){
			String id = request.getParameter("stu_id");
			if(id == ""||id == null) {
				id = "%";
			}
			ArrayList<Student> students = new ArrayList<Student>();
			ManagerService managerService = new ManagerService();
			students = managerService.getStudent(id);
			JsonConfig jsonConfig = new JsonConfig();                                                          
			jsonConfig.registerJsonValueProcessor(java.sql.Timestamp.class, new TimestampProcessor ());
			
			JSONArray ja = JSONArray.fromObject(students,jsonConfig);
			out.print(ja);
		}
		
		//对学生删除
		else if(type.equals("delete")) {
			System.out.println("删除"+request.getParameter("stu_id"));
			String stu_id = request.getParameter("stu_id");
			delStudent(stu_id);
			JSONArray ja = JSONArray.fromObject(Tools.success("删除成功"));
			out.print(ja);
			return;
		}else {//增加或者修改
			Student stu = new Student();
			stu.setDepartment(request.getParameter("department"));
			stu.setEnrolltime(request.getParameter("entrolltime"));
			stu.setName(request.getParameter("name"));
			stu.setPassword(request.getParameter("id"));
			
			stu.setPhone("");
			stu.setSex(request.getParameter("sex"));
			stu.setSpecialty(request.getParameter("specialty"));
			stu.setId(request.getParameter("id"));
			ManagerService managerService = new ManagerService();
			if(type.equals("add")) {
				if(StuIsExits(stu.getId())) {
					JSONArray ja = JSONArray.fromObject(Tools.success("id已存在！操作失败！"));
					out.print(ja);
					return;
				}
				
				managerService.addStudent(stu);
				JSONArray ja = JSONArray.fromObject(Tools.success("操作成功"));
				out.print(ja);
				return;
			}
			if(type.equals("update")) {
				stu.setId(request.getParameter("id"));
				managerService.updateStudent(stu);
			}
			JSONArray ja = JSONArray.fromObject(Tools.success("操作成功"));
			out.print(ja);
		}
	}
	
	protected void delStudent(String stu_id) throws IOException{
		//删除学生表
		ManagerService ms = new ManagerService();
		Student student = new Student();
		student.setId(stu_id);
		ms.deleteStudent(student);
		//删除SC表
		StudentService ss = new StudentService();
		ArrayList<StudentCourse> courses = ss.getStudentCourse(stu_id);
		for(StudentCourse sc:courses) {
			ms.deleteSc(sc.getId(), stu_id);
		}
		//删除task记录
		ss.delSubmissionByID(stu_id, "%");
	}

	protected boolean StuIsExits(String stu_id) throws IOException {
		boolean b = false;
		
		ManagerService ms = new ManagerService();
		if(!ms.getStudent(stu_id).isEmpty()) b = true;
		
		return b;
	}


}
