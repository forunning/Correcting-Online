package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Task;
import bean.TeacherTask;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import service.StudentService;
import service.TeacherCourseService;
import service.TeacherTaskService;
import tool.TimestampProcessor;

/**
 * Servlet implementation class Tea_getTask
 */
@WebServlet("/Teacher/getTask")
public class Tea_getTask extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Tea_getTask() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//根据id获取课程名
		String task_id = request.getParameter("task_id");
		TeacherTaskService tts = new TeacherTaskService();
		Task task = tts.getTaskByID(task_id);
		JsonConfig jsonConfig = new JsonConfig();                                                          
		jsonConfig.registerJsonValueProcessor(java.sql.Timestamp.class, new TimestampProcessor ());
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		JSONArray ja = JSONArray.fromObject(task,jsonConfig);
		out.print(ja);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		//获取教师课程下的作业
		TeacherCourseService tcs = new TeacherCourseService(); 
		ArrayList<TeacherTask> tt = tcs.getTeacherTask(request.getParameter("cou_id"));
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		JsonConfig jsonConfig = new JsonConfig();                                                          
		jsonConfig.registerJsonValueProcessor(java.sql.Timestamp.class, new TimestampProcessor ());
		JSONArray ja = JSONArray.fromObject(tt,jsonConfig);
		out.print(ja);
	}

}
