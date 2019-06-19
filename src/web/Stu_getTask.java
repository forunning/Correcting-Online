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

import bean.StudentTask;
import dao.StudentToolMapper;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import service.StudentService;
import tool.TimestampProcessor;

/**
 * Servlet implementation class Stu_gettask
 */
@WebServlet("/Student/getTask")
public class Stu_getTask extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Stu_getTask() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		StudentService ss = new StudentService();
		String cou_name = ss.getCourseName(request.getParameter("cou_id"));
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("cou_name", cou_name);
		System.out.println(request.getParameter("cou_id")+"-"+cou_name);
		JSONArray jsonObj = JSONArray.fromObject(map);
		out.print(jsonObj);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		//获得学生课程的作业列表
		StudentService ss = new StudentService();
		ArrayList<StudentTask> st_list =  ss.getStudentTask(request.getParameter("cou_id"),request.getParameter("stu_id"));
		//ArrayList<StudentTask> st_list =  ss.getStudentTask("1001","1611205119");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		JsonConfig jsonConfig = new JsonConfig();                                                          
		jsonConfig.registerJsonValueProcessor(java.sql.Timestamp.class, new TimestampProcessor ());
		JSONArray ja = JSONArray.fromObject(st_list,jsonConfig);
		
		out.print(ja);
		System.out.println("ja");
	}

}
