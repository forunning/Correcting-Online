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
import bean.Task;
import bean.TeacherCourse;
import net.sf.json.JSONArray;
import service.ManagerService;
import service.StudentService;
import service.TeacherCourseService;

/**
 * Servlet implementation class Tea_getCourse
 */
@WebServlet("/Manager/getScore")
public class Man_getScore extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Man_getScore() {
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
		//doGet(request, response);
		ArrayList<ScoreInfo> infos = new ArrayList<ScoreInfo>();
		ManagerService ms = new ManagerService();
		ArrayList<Course> courses = ms.getCourse("%");
		for(Course c:courses) {
			ScoreInfo si = new ScoreInfo();
			si.setCou_name(c.getName());
			si.setCou_id(String.valueOf(c.getId()));
			ArrayList<Task> tasks = new ArrayList<Task>();
			StudentService ss = new StudentService();
			tasks = ss.getTaskIDByCourse(si.getCou_id());
			ArrayList<String[]> tas = new ArrayList<String[]>();
			for(Task t:tasks) {
				String[] s = new String[3];
				s[0] = String.valueOf(t.getId());
				s[1] = t.getName();
				s[2] = ms.getAvgScore(String.valueOf(t.getId()));
				tas.add(s);
			}
			si.setTasks(tas);
			infos.add(si);
		}
		//infos就是最后需要的信息列表
		
		response.setCharacterEncoding("UTF-8");
		PrintWriter printWriter = response.getWriter();
		JSONArray array = JSONArray.fromObject(infos);
		printWriter.print(array);
	}
	
	
	public class ScoreInfo{
		private String cou_name;
		private String cou_id;
		private ArrayList<String[]> tasks;//0:task_id 1:task_name 2:avg_score
		public String getCou_name() {
			return cou_name;
		}
		public void setCou_name(String cou_name) {
			this.cou_name = cou_name;
		}
		public String getCou_id() {
			return cou_id;
		}
		public void setCou_id(String cou_id) {
			this.cou_id = cou_id;
		}
		public ArrayList<String[]> getTasks() {
			return tasks;
		}
		public void setTasks(ArrayList<String[]> tasks) {
			this.tasks = tasks;
		}
		@Override
		public String toString() {
			return "ScoreInfo [cou_name=" + cou_name + ", cou_id=" + cou_id + ", tasks=" + tasks + "]";
		}
		
	}

}
