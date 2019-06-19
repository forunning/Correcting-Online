package Test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import bean.Course;
import bean.Manager;
import bean.Student;
import bean.StudentTask;
import bean.Task;
import bean.Teacher;
import bean.TeacherCourse;
import net.sf.json.JSONArray;
import service.ManagerService;
import service.StudentService;
import service.TeacherCourseService;
import service.TeacherService;
import service.TeacherTaskService;

public class TestCase {
/*
	@Test
	public void Stu_fix() throws IOException {
		String type = "update_pswd";
		if(type.equals("update_phone")) {
			updateStuPhone("17718264651", "1611205119");
		}
		else if(type.equals("update_pswd")) {
			updateStuPswd("123456", "1611205119");
		}
	}
	
	public void updateStuPhone(String phone,String stu_id) throws IOException{
		StudentService ss = new StudentService();
		//输入修改内容
		ss.updatePhone(phone,stu_id);
	}
	
	public void updateStuPswd(String pswd,String stu_id) throws IOException{
		StudentService ss = new StudentService();
		ss.updateStudentPswd(pswd,stu_id);
	}

//================================================================================================================
	@Test
	public void Tea_fix() throws IOException {
		String type = "update_pswd";
		if(type.equals("update_phone")) {
			updateTeaPhone("17718264651", "1611205119");
		}
		else if(type.equals("update_pswd")) {
			updateTeaPswd("123456", "1611205119");
		}
		
		
	}
	
	public void updateTeaPhone(String phone,String tea_id) throws IOException{
		TeacherService ts = new TeacherService();
		//输入修改内容
		ts.updatePhone(phone,tea_id);
	}
	
	
	public void updateTeaPswd(String pswd,String tea_id) throws IOException{
		TeacherService ts = new TeacherService();
		ts.updatePswd(pswd, tea_id);;
	}
//------------------------------------------------------------------------------------------------------
	@Test 
	public void Man_couHand() throws IOException{
		String id = "1001";//输入课程号
		if(id == ""||id == null) {
			id = "%";
		}
		ArrayList<Course> courses = new ArrayList<Course>();
		ManagerService managerService = new ManagerService();
		TeacherCourseService tcs = new TeacherCourseService();
		courses = managerService.getCourse(id);
		ArrayList<CourseInfo> courseInfos = new ArrayList<TestCase.CourseInfo>();
		for(Course c:courses) {
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
			for(Task t:tasks) {
				String[] s = new String[2];
				s[0] = String.valueOf(t.getId());
				s[1] = t.getName();
				ci.tasks.add(s);
			}
			//构建学生数量
			ArrayList<Student> students = new ArrayList<Student>();
			students = ss.getStudentByCourse(ci.cou_id);
			int num=0;
			for(Student s:students) {
				num++;
			}
			ci.stu_num = num;
			courseInfos.add(ci);
		}
//		courseInfos就是需要的课程信息
	}
	
	class CourseInfo{
		String cou_id;
		String cou_name;
		String tea_name;
		String tea_id;
		ArrayList<String[]> tasks;
		int stu_num;
	}
	
//	=====================================================================================================
	
	@Test
	public void Man_filehand() throws IOException{
		String type="select";
		if(type.equals("select")) {
			ManagerService managerService = new ManagerService();
			ArrayList<HashMap> map = managerService.getFileInfo();
//			map就是需要的数据 time task_name cou_name stu_id stu_nam filepath
		}
		else if(type.equals("delete")) {
			StudentService ss = new StudentService();
			ss.delSubmissionByID("", "");//stu_id task_id
		}
	}
	
	
	
// ==========================================================================================================
	
	@Test
	public void Man_getScore() throws IOException{
		ArrayList<ScoreInfo> infos = new ArrayList<TestCase.ScoreInfo>();
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
	}
	
	class ScoreInfo{
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
	
//====================================================================================================
	
	@Test
	public void Man_fix() throws IOException{
		Manager manager = new Manager();
		manager.setId("");
		manager.setPassword("");
		ManagerService ms = new ManagerService();
		ms.updatePswd(manager);
	}
	
	//===================================================================
	
	@Test 
	public void Man_couHand() throws IOException{
		String id = "";//输入课程号
		if(id == ""||id == null) {
			id = "%";
		}
		ArrayList<Course> courses = new ArrayList<Course>();
		ManagerService managerService = new ManagerService();
		TeacherCourseService tcs = new TeacherCourseService();
		courses = managerService.getCourse(id);
		ArrayList<CourseInfo> courseInfos = new ArrayList<TestCase.CourseInfo>();
		System.out.println(courses.toString());
		for(Course c:courses) {
			System.out.println("123");
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
//		courseInfos就是需要的课程信息
		System.out.println(courseInfos.toString());
		System.out.println("123");
	}
	
	class CourseInfo{
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
		
		
	}
	*/
	
	//============================================
	@Test
	public void testGetTask() throws IOException{
		String task_id = "3001";
		TeacherTaskService tts = new TeacherTaskService();
		Task task = tts.getTaskByID(task_id);
		System.out.println(task);
	}
}
