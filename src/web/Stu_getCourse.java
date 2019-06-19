package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.StudentCourse;
import net.sf.json.JSONArray;
import service.StudentService;

/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/Student/getCourse")
public class Stu_getCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Stu_getCourse() {
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
		System.out.println("post");
		System.out.println(request.getParameter("stu_id"));
		StudentService ss = new StudentService();
		ArrayList<StudentCourse> sc_list = ss.getStudentCourse(request.getParameter("stu_id"));
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		JSONArray ja = JSONArray.fromObject(sc_list);
		out.print(ja);
	//	doGet(request, response);
	}

}
