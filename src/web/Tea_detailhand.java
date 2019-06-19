package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.fabric.xmlrpc.base.Array;

import bean.Student;
import bean.Submission;
import bean.Task;
import bean.Teacher;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import service.StudentService;
import service.TeacherTaskService;
import tool.TimestampProcessor;
import tool.Tools;

/**
 * Servlet implementation class Tea_getStudent
 */
@WebServlet("/Teacher/detailhand")
public class Tea_detailhand extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Tea_detailhand() {
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
		
		System.out.println(request.getParameter("type"));
		if(request.getParameter("type").equals("select")) {
			System.out.println(request.getParameter("task_id"));
			//实际内容1
			ArrayList<Summary> summaries = new ArrayList<Summary>();
			//根据cou_id查找students表
			ArrayList<Student> students = new ArrayList<Student>();
			Task task = getTask(request.getParameter("task_id"));
			students = getStudents(request.getParameter("task_id"));
			for(Student s:students) {
				
				Summary sum = new Summary();
				Submission sub = getSubmission(s.getId()+"",task.getId()+"");
				//初始化sum的学生部分
				sum.setStu_id(s.getId());
				sum.setStu_name(s.getName());
				sum.setSpecialty(s.getSpecialty());
				sum.setEnrolltime(s.getEnrolltime());
				//初始化submission部分
				if(sub != null) {
					sum.setFilepath(sub.getFilepath());
					sum.setUpdate_time(sub.getTime());
					sum.setScore(sub.getScore()+"");
				}else {
					sum.setFilepath(null);
					sum.setUpdate_time(null);
					sum.setScore("");
				}
				summaries.add(sum);
				
			}
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			JsonConfig jsonConfig = new JsonConfig();                                                          
			jsonConfig.registerJsonValueProcessor(java.sql.Timestamp.class, new TimestampProcessor ());
			JSONArray ja = JSONArray.fromObject(summaries,jsonConfig);
			out.print(ja);
		}else {
			//实际操作2
			TeacherTaskService tts = new TeacherTaskService();
			System.out.println(request.getParameter("score"));
			System.out.println(request.getParameter("stu_id"));
			System.out.println(request.getParameter("task_id"));
			tts.updateScore(Integer.valueOf(request.getParameter("score")), request.getParameter("stu_id"), request.getParameter("task_id"));
			response.setCharacterEncoding("utf-8");
			PrintWriter outer = response.getWriter();
			JSONArray ja = JSONArray.fromObject(Tools.success("操作成功"));
			outer.print(ja);
		}
		
	}
	
	protected ArrayList<Student> getStudents(String task_id) throws IOException{
		ArrayList result = new ArrayList<Student>();
		Task task = new Task();
		TeacherTaskService teacherTaskService = new TeacherTaskService();
		task = teacherTaskService.getTaskByID(task_id);
		String cou_id = String.valueOf(task.getC_id());
		
		StudentService ss = new StudentService();
		result = ss.getStudentByCourse(cou_id);
		
		return result;
	}
	
	protected Submission getSubmission(String stu_id,String task_id) throws IOException {
		Submission submission = new Submission();
		
		StudentService ss = new StudentService();
		if(ss.getSubmissionListByID(stu_id, task_id).isEmpty()) {
			return null;
		}
		submission = ss.getSubmissionListByID(stu_id, task_id).get(0);
		
		return submission;
	}
	
	protected Task getTask(String task_id) throws IOException{
		Task tasks = new Task();
		
		TeacherTaskService tts = new TeacherTaskService();
		tasks = tts.getTaskByID(task_id);
		
		return tasks;
	}
	
	public class Summary{
		private String stu_id;
		private String stu_name;
		private String enrolltime;
		private String specialty;
		private Timestamp update_time;
		private String filepath;
		private String score;
		public String getStu_id() {
			return stu_id;
		}
		public void setStu_id(String stu_id) {
			this.stu_id = stu_id;
		}
		public String getStu_name() {
			return stu_name;
		}
		public void setStu_name(String stu_name) {
			this.stu_name = stu_name;
		}
		public String getEnrolltime() {
			return enrolltime;
		}
		public void setEnrolltime(String enrolltime) {
			this.enrolltime = enrolltime;
		}
		public String getSpecialty() {
			return specialty;
		}
		public void setSpecialty(String specialty) {
			this.specialty = specialty;
		}
		public Timestamp getUpdate_time() {
			return update_time;
		}
		public void setUpdate_time(Timestamp update_time) {
			this.update_time = update_time;
		}
		public String getFilepath() {
			return filepath;
		}
		public void setFilepath(String filepath) {
			this.filepath = filepath;
		}
		public String getScore() {
			return score;
		}
		public void setScore(String score) {
			this.score = score;
		}
		@Override
		public String toString() {
			return "Summary [stu_id=" + stu_id + ", stu_name=" + stu_name + ", enrolltime=" + enrolltime
					+ ", specialty=" + specialty + ", update_time=" + update_time + ", filepath=" + filepath
					+ ", score=" + score + "]";
		}
		
		
	}

}
