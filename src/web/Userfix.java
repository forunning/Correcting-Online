package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Manager;
import bean.Student;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import service.ManagerService;
import service.StudentService;
import service.TeacherService;
import tool.Tools;

/**
 * Servlet implementation class Stu_fix
 */
@WebServlet("/Manager/Userfix")
public class Userfix extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Userfix() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String id=request.getParameter("id");
		ManagerService ms = new  ManagerService();
		Manager manager = ms.getManager(id);
		JSONArray ja=JSONArray.fromObject(manager);
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(ja);
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
		String id=request.getParameter("id");
		String password=request.getParameter("password");
		System.out.println("mima"+password);
		JSONArray ja=JSONArray.fromObject(Tools.success("修改成功"));
		if(StuIsExits(id)) {
			updateStuPswd(password,id);
		}else if(TeaIsExits(id)) {
			updateTeaPswd(password,id);
		}else if(ManIsExits(id)) {
			updateManPswd(password,id);
		}else {
			ja=JSONArray.fromObject(Tools.success("修改失败"));
		}
		out.print(ja);
	}

	
	public void updateStuPswd(String pswd,String stu_id) throws IOException{
		StudentService ss = new StudentService();
		ss.updateStudentPswd(pswd,stu_id);
	}
	public void updateTeaPswd(String pswd,String stu_id) throws IOException{
		TeacherService ss = new TeacherService();
		ss.updatePswd(pswd,stu_id);
		
	}
	public void updateManPswd(String pswd,String stu_id) throws IOException{
		ManagerService ss = new ManagerService();
		ss.updatePswd(pswd,stu_id);
		
	}
	
	
	
	protected boolean TeaIsExits(String tea_id) throws IOException {
		boolean b = false;
		
		ManagerService managerService = new ManagerService();
		if(!managerService.getTeacher(tea_id).isEmpty())
			b = true;
		
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
	
	protected boolean ManIsExits(String id) throws IOException{
		boolean b = true;
		
		ManagerService ms = new  ManagerService();
		if(ms.getManager(id)==null) {
			b = false;
		}
		
		return b;
	}
}
