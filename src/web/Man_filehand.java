package web;

import bean.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import service.ManagerService;
import service.StudentService;
import service.TeacherCourseService;
import tool.TimestampProcessor;
import tool.Tools;

/**
 * Servlet implementation class Man_setDetail
 */
@WebServlet("/Manager/filehand")
public class Man_filehand extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Man_filehand() {
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
		if(type.equals("select")) {
			ManagerService managerService = new ManagerService();
			ArrayList<HashMap> map = managerService.getFileInfo();
//			map就是需要的数据 time task_name cou_name stu_id stu_nam filepath
			
			JsonConfig jsonConfig = new JsonConfig();                                                          
			jsonConfig.registerJsonValueProcessor(java.sql.Timestamp.class, new TimestampProcessor ());
			JSONArray ja = JSONArray.fromObject(map,jsonConfig);
			out.print(ja);
		}
		else if(type.equals("delete")) {
			StudentService ss = new StudentService();
			ss.delSubmissionByID(request.getParameter("stu_id"), request.getParameter("task_id"));//stu_id task_id
			JSONArray ja = JSONArray.fromObject(Tools.success("删除成功"));
			out.print(ja);
		}
	}


}
