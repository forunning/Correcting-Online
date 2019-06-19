package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.TeacherCourse;
import net.sf.json.JSONArray;
import service.TeacherCourseService;

/**
 * Servlet implementation class Tea_getCourse
 */
@WebServlet("/Teacher/getCourse")
public class Tea_getCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Tea_getCourse() {
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
		//获取教师教授的课程
		TeacherCourseService tcs = new TeacherCourseService();
		
		System.out.println(request.getParameter("tea_id"));
		ArrayList<TeacherCourse> cou_list = tcs.getTeacherCourse(request.getParameter("tea_id"));
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		JSONArray ja = JSONArray.fromObject(cou_list);
		out.print(ja);
		//out.print(request.getParameter("tea_id"));
	}

}
