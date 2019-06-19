package service;

import bean.Submission;
import bean.Teacher;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import bean.Student;
import bean.StudentCourse;
import bean.StudentTask;
import bean.Submission;
import bean.Task;
import dao.CourseToolMapper;
import dao.StudentToolMapper;
import dao.TeacherToolMapper;
import dao.UserMapper;

public class StudentService {
	
	//自行构建session
	public SqlSessionFactory getSqlSessionFactory() throws IOException {
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		return new SqlSessionFactoryBuilder().build(inputStream);
	}

	
	//查询学生的课程信息
	@SuppressWarnings("finally")
	public ArrayList<StudentCourse> getStudentCourse(String stu_id) throws IOException{
		// 1、获取sqlSessionFactory对象
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		// 2、获取sqlSession对象
		SqlSession openSession = sqlSessionFactory.openSession();
		ArrayList<StudentCourse> sc_list = new ArrayList<StudentCourse>();
		try {
			// 3、获取接口的实现对象，由代理对象执行增删改查
			StudentToolMapper mapper = openSession.getMapper(StudentToolMapper.class);
			System.out.println(stu_id);
//			ArrayList<String[]> courseInfos = mapper.getCourseIDName(stu_id);
			ArrayList<HashMap> courseInfos = mapper.getCourseIDName(stu_id);
			for(HashMap map:courseInfos) {
				StudentCourse sc = new StudentCourse();
				String cou_id = String.valueOf(map.get("cou_id"));
				String cou_name = (String)map.get("cou_name");
				sc.setCou_id(Integer.valueOf(cou_id));
				sc.setCou_name(cou_name);
				
				//填充教师信息
				TeacherToolMapper tm = openSession.getMapper(TeacherToolMapper.class);
				Teacher teacher = tm.getTeacherByCourse(cou_id);
				sc.setTea_name(teacher.getName());
				
				//填充两个计数属性
				sc.setStu_num(mapper.getStuNum(cou_id));
				sc.setTask_num(mapper.getTaskNum(cou_id));
				
				//加入sc_list
				sc_list.add(sc);
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			openSession.close();
			return sc_list;
		}
	}
	
	//查询学生的作业列表
	@SuppressWarnings("finally")
	public ArrayList<StudentTask> getStudentTask(String cou_id,String stu_id) throws IOException{
		//1、获取sqlSessionFactory对象
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		//2、获取sqlSession对象
		SqlSession openSession = sqlSessionFactory.openSession();
		ArrayList<StudentTask> st_list = new ArrayList<StudentTask>();
		try {
			//3、获取接口的实现对象，由代理对象执行增删改查
			StudentToolMapper mapper = openSession.getMapper(StudentToolMapper.class);
			ArrayList<Task> tasks = mapper.getTaskIDByCourse(cou_id);
			for(Task t:tasks) {
				StudentTask st = new StudentTask();
				st.setTask_id(t.getId());
				st.setTask_name(t.getName());
				st.setTask_filepath(t.getFile());
				st.setEtime(t.getE_time());
				st.setStime(t.getS_time());
				
				//构造st内容
				Submission s = new Submission();
				ArrayList<Submission> subs = mapper.getSubmissionListByID(stu_id, String.valueOf(t.getId()));
				if(!subs.isEmpty()) {
					s = subs.get(0);
				}
				System.out.println("123");
				st.setScore(s.getScore());
				st.setStu_filepath(s.getFilepath());
				st_list.add(st);
			}
		}catch(Exception e) {
			System.out.println(e.toString());
		}
		finally {
			openSession.close();
			return st_list;
		}
	}
	
	//课程号查询课程name
	@SuppressWarnings("finally")
	public String getCourseName(String cou_id) throws IOException{
		//1、获取sqlSessionFactory对象
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		//2、获取sqlSession对象
		SqlSession openSession = sqlSessionFactory.openSession();
		String result="";
		try {
			//3、获取接口的实现对象，由代理对象执行增删改查
			StudentToolMapper mapper = openSession.getMapper(StudentToolMapper.class);
			result = mapper.getCourseName(cou_id);
		}catch(Exception e) {
			System.out.println(e.toString());
		}
		finally {
			openSession.close();
			return result;
		}
	}
	
	//在st中插入新数据
	@SuppressWarnings("finally")
	public void addSubmission(Submission sub) throws IOException{
		//1、获取sqlSessionFactory对象
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		//2、获取sqlSession对象
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			//3、获取接口的实现对象，由代理对象执行增删改查
			StudentToolMapper mapper = openSession.getMapper(StudentToolMapper.class);
			mapper.addSubmission(sub);
			openSession.commit();
		}catch(Exception e) {
			System.out.println(e.toString());
		}
		finally {
			openSession.close();
		}
	}
	
	//根据课程号学生号查询作业提交记录
	@SuppressWarnings("finally")
	public ArrayList<Submission> getSubmissionList(String cou_id,String stu_id) throws IOException{
		//1、获取sqlSessionFactory对象
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		//2、获取sqlSession对象
		SqlSession openSession = sqlSessionFactory.openSession();
		ArrayList<Submission> sub_list = null;
		try {
			//3、获取接口的实现对象，由代理对象执行增删改查
			StudentToolMapper mapper = openSession.getMapper(StudentToolMapper.class);
			sub_list = mapper.getSubmissionList(cou_id, stu_id);
		}catch(Exception e) {
			System.out.println(e.toString());
		}
		finally {
			openSession.close();
			return sub_list;
		}
	}
	
	//修改学生联系方式
	@SuppressWarnings("finally")
	public void updatePhone(String phone,String stu_id) throws IOException{
		//1、获取sqlSessionFactory对象
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		//2、获取sqlSession对象
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			//3、获取接口的实现对象，由代理对象执行增删改查
			StudentToolMapper mapper = openSession.getMapper(StudentToolMapper.class);
			mapper.updatePhone(phone, stu_id);
			openSession.commit();
		}catch(Exception e) {
			System.out.println(e.toString());
		}
		finally {
			openSession.close();
		}
	}
	
	@SuppressWarnings("finally")
	public ArrayList<Submission> getSubmissionListByID(@Param("stu_id")String stu_id,@Param("task_id")String task_id) throws IOException{
		ArrayList<Submission> result = null;
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		
		try {
			StudentToolMapper mapper = openSession.getMapper(StudentToolMapper.class);
			result = mapper.getSubmissionListByID(stu_id, task_id);
		}catch(Exception e){
			System.out.println(e.toString());
		}finally {
			openSession.close();
		}
		 
		return result;
	}
	
	@SuppressWarnings("finally")
	public void delSubmissionByID(@Param("stu_id")String stu_id,@Param("task_id")String task_id) throws IOException{
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		
		try {
			StudentToolMapper mapper = openSession.getMapper(StudentToolMapper.class);
			mapper.delSubmissionByID(stu_id, task_id);
			openSession.commit();
		}catch(Exception e){
			System.out.println(e.toString());
		}finally {
			openSession.close();
		}
		 
	}
	
	//根据课程号查询学生的信息
	@SuppressWarnings("finally")
	public ArrayList<Student> getStudentByCourse(String cou_id) throws IOException{
		ArrayList<Student> result = new ArrayList<Student>();
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		
		try {
			StudentToolMapper mapper = openSession.getMapper(StudentToolMapper.class);
			result = mapper.getStudentByCourse(cou_id);
		}catch(Exception e){
			System.out.println(e.toString());
		}finally {
			openSession.close();
			return result;
		}
		 
	}
	
	@SuppressWarnings("finally")
	public ArrayList<Task> getTaskIDByCourse(String cou_id) throws IOException{
		ArrayList<Task> result = new ArrayList<Task>();
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		
		try {
			StudentToolMapper mapper = openSession.getMapper(StudentToolMapper.class);
			result = mapper.getTaskIDByCourse(cou_id);
		}catch(Exception e){
			System.out.println(e.toString());
		}finally {
			openSession.close();
			return result;
		}
		 
	}
	
	//根据stu_id 查询sc
	@SuppressWarnings("finally")
	public ArrayList<StudentCourse> getScByStu(String stu_id,String cou_id) throws IOException{
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		ArrayList<StudentCourse> result = new ArrayList<StudentCourse>();
		try {
			CourseToolMapper mapper = openSession.getMapper(CourseToolMapper.class);
			result = mapper.getScByStu(stu_id,cou_id);
		}catch(Exception e){
			System.out.println(e.toString());
		}finally {
			openSession.close();
			return result;
		}
		 
	}
	
	//根据task_id 查询学生
	@SuppressWarnings("finally")
	public ArrayList<Student> getStudentByTaskID(String task_id) throws IOException{
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		ArrayList<Student> result = new ArrayList<Student>();
		try {
			StudentToolMapper mapper = openSession.getMapper(StudentToolMapper.class);
			result = mapper.getStudentByTaskID(task_id);
		}catch(Exception e){
			System.out.println(e.toString());
		}finally {
			openSession.close();
			return result;
		}
		 
	}	
	
	//修改学生密码
	public void updateStudentPswd(String pswd,String cou_id) throws IOException{
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			UserMapper mapper = openSession.getMapper(UserMapper.class);
			mapper.updateStudentPswd(pswd, cou_id);
			openSession.commit();
		}catch(Exception e){
			System.out.println(e.toString());
		}finally {
			openSession.close();
		}
	}
}
