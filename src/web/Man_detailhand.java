package web;

import bean.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import service.ManagerService;
import service.StudentService;
import service.TeacherCourseService;
import tool.Tools;

/**
 * Servlet implementation class Man_setDetail
 */
@WebServlet("/Manager/detailhand")
public class Man_detailhand extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Man_detailhand() {
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
		if(type.equals("select")) {//输出课程的学生信息
			ArrayList<Student> students = new ArrayList<Student>();
			StudentService service = new StudentService();
			students = service.getStudentByCourse(request.getParameter("cou_id"));
			

			JSONArray array = JSONArray.fromObject(students);
			out.print(array);
		}
		else if(type.equals("add")) {//对课堂中的学生进行增加
			System.out.println("add"+"-"+request.getParameter("stu_id")+"-"+request.getParameter("cou_id"));
			String cou_id = request.getParameter("cou_id");
			String stu_id = request.getParameter("stu_id");
			if(StuIsExits(stu_id)) {
				if(isExistedInCourse(stu_id, cou_id)) {
					JSONArray ja = JSONArray.fromObject(Tools.success("该学生已存在该课程中！"));
					out.print(ja);
					return;
				}
				
				ManagerService managerService = new ManagerService();
				managerService.addSC(cou_id, stu_id);
				JSONArray ja = JSONArray.fromObject(Tools.success("操作成功"));
				out.print(ja);
				return;
			}else {
				JSONArray ja = JSONArray.fromObject(Tools.error("操作失败,请检查学号是否存在"));
				out.print(ja);
			}
			
		}else if(type.equals("delete")) {//对课堂中的学生进行删除
			System.out.println("delete"+"-"+request.getParameter("stu_id")+"-"+request.getParameter("cou_id"));
			String cou_id = request.getParameter("cou_id");
			String stu_id = request.getParameter("stu_id");
			ManagerService managerService = new ManagerService();
			managerService.deleteSc(cou_id, stu_id);
			JSONArray ja = JSONArray.fromObject(Tools.success("删除成功"));
			out.print(ja);
		}
	}
	
	protected boolean isExistedInCourse(String stu_id,String cou_id) throws IOException {
		boolean b = false;
		
		StudentService studentService = new StudentService();
		for(StudentCourse sc:studentService.getScByStu(stu_id, cou_id)) {
			if (sc!=null)
				b = true;
		}
		
		return b;
	}
	
	protected boolean StuIsExits(String stu_id) throws IOException {
		boolean b = true;
		
		ManagerService ms = new ManagerService();
		ArrayList<Student> students = ms.getStudent(stu_id);
		if(students.isEmpty())
			b = false;
		else
			b = true;
		
		
		return b;
	}

}
