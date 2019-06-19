package web;

import service.*;
import tool.JSONToExcel;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Submission;
import net.sf.json.JSONArray;

/**
 * Servlet implementation class Tea_getScore
 */
@WebServlet("/Teacher/getScore")
public class Tea_getScore extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private InputStream is;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Tea_getScore() {
        super();
        // TODO Auto-generated constructor stub
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		System.out.println(request.getParameter("cou_id"));
		//ʵ�ʲ���
		ArrayList<Score> scores = getAllScoreByCourse(request.getParameter("cou_id"));
		response.setCharacterEncoding("UTF-8");
		
		
		ArrayList<HashMap<String,String>> scoresMap = new ArrayList<HashMap<String,String>>();
		
		for(Score s:scores) {
			HashMap<String, String> map = new HashMap<String,String>();
			map.put("ѧ��", s.getStu_id());
			map.put("����", s.getStu_name());
			map.put("��ҵ����", s.getTask_name());
			map.put("�ɼ�", s.getScore()+"");
			scoresMap.add(map);
		}
		
		JSONArray ja = JSONArray.fromObject(scoresMap);
		//תexcel
		System.out.println(ja.size());
		System.out.println(ja);
		//System.out.println(ja);
		//out.print(ja);
		
		
		JSONToExcel excel=new JSONToExcel(ja);
		try {
			is = excel.getInputStream();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//������Ӧͷ����ֵ��ʹ�ļ��Ը�����ʽ�������� 
        response.setHeader("content-disposition", "attachment;filename=target.xls");

        //��ȡ������
       // InputStream is = this.getServletContext().getResourceAsStream("/download/target.xls");
        ServletOutputStream os = response.getOutputStream();

        //�����ļ�
        byte[] bytes = new byte[1024];
        int len = 1;
        while((len = is.read(bytes)) != -1){
            os.write(bytes, 0, len);
        }
	}
	
	protected ArrayList<Score> getAllScoreByCourse(String cou_id) throws IOException {
		ArrayList<Score> scores = new ArrayList<Score>();
		ArrayList<Submission> subs = new ArrayList<Submission>();
		
		//��ȡ�γ���ȫ��submission
		TeacherCourseService tcs = new TeacherCourseService();
		subs = tcs.getSubmissionByCourse(cou_id);
		
		//��subs����ѭ������
		for(Submission s:subs) {
			Score score = new Score();
			score.setStu_id(s.getStu_id());
			score.setStu_name(s.getStu_name());
			score.setTask_name(s.getTask_name());
			score.setScore(s.getScore());
			scores.add(score);
		}
		
		return scores;
	}
	
	//�ڲ���Score����תjson�����Ҫ�Ľ��
	public class Score{
		private String stu_id;
		private String stu_name;
		private String task_name;
		private int score;
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
		public String getTask_name() {
			return task_name;
		}
		public void setTask_name(String task_name) {
			this.task_name = task_name;
		}
		public int getScore() {
			return score;
		}
		public void setScore(int score) {
			this.score = score;
		}
		@Override
		public String toString() {
			return "Score [stu_id=" + stu_id + ", stu_name=" + stu_name + ", task_name=" + task_name + ", score="
					+ score + "]";
		}
		
		
		
	}

}
