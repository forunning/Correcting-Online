package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Course;
import bean.Student;
import bean.Task;
import net.sf.json.JSONArray;
import service.ManagerService;
import service.StudentService;
import service.TeacherCourseService;
import service.TeacherService;
import tool.Tools;

/**
 * Servlet implementation class Man_setCou
 */
@WebServlet("/Manager/couhand")
public class Man_couhand extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Man_couhand() {
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
		//实际内容
		String type = request.getParameter("type");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		System.out.println(type);
		//对课程查询
		if(type.equals("select")){
			String id = "";//输入课程号
			if(id == ""||id == null) {
				id = "%";
			}
			ArrayList<Course> courses = new ArrayList<Course>();
			ManagerService managerService = new ManagerService();
			TeacherCourseService tcs = new TeacherCourseService();
			courses = managerService.getCourse(id);
			ArrayList<CourseInfo> courseInfos = new ArrayList<CourseInfo>();
			System.out.println(courses.toString());
			for(Course c:courses) {
				//System.out.println("123");
				//构建内部类
				CourseInfo ci = new CourseInfo();
				//构建课程号，课程名，教师id
				ci.cou_id = String.valueOf(c.getId());
				ci.cou_name = c.getName();
				ci.tea_id = c.getTea_id();
				//构建教师姓名
				TeacherService ts = new TeacherService();
				ci.tea_name = ts.getTeacherByID(c.getTea_id()).getName();
				//构建作业信息
				ArrayList<Task> tasks = new ArrayList<Task>();
				StudentService ss = new StudentService();
				tasks = ss.getTaskIDByCourse(ci.cou_id);
				System.out.println(tasks.toString());
				ArrayList<String[]> tasksInfo = new ArrayList<String[]>();
				for(Task t:tasks) {
					String[] s = new String[2];
					s[0] = String.valueOf(t.getId());
					s[1] = t.getName();
					tasksInfo.add(s);
					System.out.println("t");
				}
				ci.tasks = tasksInfo;
				//构建学生数量
				ArrayList<Student> students = new ArrayList<Student>();
				students = ss.getStudentByCourse(ci.cou_id);
				System.out.println(students.toString());
				int num=0;
				for(Student s:students) {
					num++;
				}
				ci.stu_num = num;
				courseInfos.add(ci);
			}
//			courseInfos就是需要的课程信息
			System.out.println(courseInfos.toString());
			System.out.println("123");
			
			JSONArray ja = JSONArray.fromObject(courseInfos);
			System.out.println(ja.toString());
			out.print(ja);
		}
		//对课程删除
		else if(type.equals("delete")) {
			ManagerService managerService = new ManagerService();
			managerService.deleteCourse(request.getParameter("cou_id"));
			JSONArray ja = JSONArray.fromObject(Tools.success("删除成功"));
			out.print(ja);
		}else {//增加或者修改
			Course course = new Course();
			course.setName(request.getParameter("name"));
			course.setTea_id(request.getParameter("tea_id"));
			ManagerService managerService = new ManagerService();
			if(!TeaIsExits(request.getParameter("tea_id"))) {
				JSONArray ja = JSONArray.fromObject(Tools.success("教师id不存在！"));
				out.print(ja);
				return;
			}
			if(type.equals("add")) {
				
				managerService.addCourse(course);
			}
			if(type.equals("update")) {
				
				//System.out.println(request.getParameter("cou_id")+"*");
				course.setId(Integer.valueOf(request.getParameter("cou_id")));
				managerService.updateCourse(course);
			}
			JSONArray ja = JSONArray.fromObject(Tools.success("操作成功"));
			out.print(ja);
		}
	}

	
	protected boolean TeaIsExits(String tea_id) throws IOException {
		boolean b = false;
		
		ManagerService managerService = new ManagerService();
		if(!managerService.getTeacher(tea_id).isEmpty())
			b = true;
		
		return b;
	}
	
	public class CourseInfo{
		String cou_id;
		String cou_name;
		String tea_name;
		String tea_id;
		ArrayList<String[]> tasks;
		int stu_num;
		@Override
		public String toString() {
			return "CourseInfo [cou_id=" + cou_id + ", cou_name=" + cou_name + ", tea_name=" + tea_name + ", tea_id="
					+ tea_id + ", tasks=" + tasks.toString() + ", stu_num=" + stu_num + "]";
		}
		public String getCou_id() {
			return cou_id;
		}
		public void setCou_id(String cou_id) {
			this.cou_id = cou_id;
		}
		public String getCou_name() {
			return cou_name;
		}
		public void setCou_name(String cou_name) {
			this.cou_name = cou_name;
		}
		public String getTea_name() {
			return tea_name;
		}
		public void setTea_name(String tea_name) {
			this.tea_name = tea_name;
		}
		public String getTea_id() {
			return tea_id;
		}
		public void setTea_id(String tea_id) {
			this.tea_id = tea_id;
		}
		public ArrayList<String[]> getTasks() {
			return tasks;
		}
		public void setTasks(ArrayList<String[]> tasks) {
			this.tasks = tasks;
		}
		public int getStu_num() {
			return stu_num;
		}
		public void setStu_num(int stu_num) {
			this.stu_num = stu_num;
		}
		
		
	}
}
