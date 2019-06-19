package service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

import bean.Submission;
import bean.Task;
import bean.TeacherTask;
import dao.TeacherToolMapper;

public class TeacherTaskService {
	//自行构建session
	public SqlSessionFactory getSqlSessionFactory() throws IOException {
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		return new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	//教师发布新的作业
	public void addTask(Task task) throws IOException {
		// 1、获取sqlSessionFactory对象
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		// 2、获取sqlSession对象
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			// 3、获取接口的实现对象，由代理对象执行增删改查
			TeacherToolMapper mapper = openSession.getMapper(TeacherToolMapper.class);
			mapper.addTask(task);
			openSession.commit();
			System.out.println("插入成功！");
		} catch(Exception e) {
			System.out.println("insert faild");
		}
		finally {
			openSession.close();
		}

	}
	
	public void deleteTask(String task_id) throws IOException{
		// 1、获取sqlSessionFactory对象
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		// 2、获取sqlSession对象
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			// 3、获取接口的实现对象，由代理对象执行增删改查
			TeacherToolMapper mapper = openSession.getMapper(TeacherToolMapper.class);
			mapper.deleteTask(task_id);
			openSession.commit();
			System.out.println("insert success");
		}catch(Exception e) {
			System.out.println(e.toString());
		}
		finally {
			openSession.close();
		}

	}
	
	//教师修改作业信息
	public void updateTask(Task task) throws IOException{
		// 1、获取sqlSessionFactory对象
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		// 2、获取sqlSession对象
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			// 3、获取接口的实现对象，由代理对象执行增删改查
			TeacherToolMapper mapper = openSession.getMapper(TeacherToolMapper.class);
			mapper.updateTask(task);
			openSession.commit();
			System.out.println("update success!");
		}catch(Exception e) {
			System.out.println("update faild!");
		}
		finally {
			openSession.close();
		}

	}
	
	//教师某个作业提交记录
	@SuppressWarnings("finally")
	public ArrayList<Submission> getStudentSubmissions(String stu_id) throws IOException{
		// 1、获取sqlSessionFactory对象
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		// 2、获取sqlSession对象
		SqlSession openSession = sqlSessionFactory.openSession();
		ArrayList<Submission> sub = null;
		try {
			// 3、获取接口的实现对象，由代理对象执行增删改查
			TeacherToolMapper mapper = openSession.getMapper(TeacherToolMapper.class);
			sub = mapper.getStudentSubmssion(stu_id);
		} catch(Exception e) {
			System.out.println(e.toString());
		}
		finally {
			openSession.close();
			return sub;
		}
		
	}
	
	//教师修改作业分数
	public void updateScore(int score,String stu_id,String task_id) throws IOException{
		// 1、获取sqlSessionFactory对象
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		// 2、获取sqlSession对象
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			// 3、获取接口的实现对象，由代理对象执行增删改查
			TeacherToolMapper mapper = openSession.getMapper(TeacherToolMapper.class);
			mapper.updateScore(score, stu_id, task_id);
			openSession.commit();
			System.out.println("update success!");
		} catch(Exception e) {
			System.out.println("update faild!");
		}
		finally {
			openSession.close();
		}

	}
	
	//根据作业id查询作业的要求文档地址
	@SuppressWarnings("finally")
	public String getTaskFilepath(String task_id) throws IOException{
		// 1、获取sqlSessionFactory对象
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		// 2、获取sqlSession对象
		SqlSession openSession = sqlSessionFactory.openSession();
		String path=null;
		try {
			// 3、获取接口的实现对象，由代理对象执行增删改查
			TeacherToolMapper mapper = openSession.getMapper(TeacherToolMapper.class);
			path = mapper.getTaskFile(task_id);
		} catch(Exception e) {
			System.out.println(e.toString());
		}
		finally {
			openSession.close();
			return path;
		}
		
	}
	
	////根据作业id与学生id查询学生提交的作业地址
	@SuppressWarnings("finally")
	public String getSubmissionPath(String task_id,String stu_id) throws IOException{
		// 1、获取sqlSessionFactory对象
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		// 2、获取sqlSession对象
		SqlSession openSession = sqlSessionFactory.openSession();
		String path=null;
		try {
			// 3、获取接口的实现对象，由代理对象执行增删改查
			TeacherToolMapper mapper = openSession.getMapper(TeacherToolMapper.class);
			path = mapper.getSubmissionFilepath(stu_id, task_id);
		} catch(Exception e) {
			System.out.println(e.toString());
		}
		finally {
			openSession.close();
			return path;
		}
		
	}
	
	//根据Task_id查询task
	@SuppressWarnings("finally")
	public Task getTaskByID(String task_id) throws IOException{
		// 1、获取sqlSessionFactory对象
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		// 2、获取sqlSession对象
		SqlSession openSession = sqlSessionFactory.openSession();
		Task task = new Task();
		try {
			// 3、获取接口的实现对象，由代理对象执行增删改查
			TeacherToolMapper mapper = openSession.getMapper(TeacherToolMapper.class);
			task = mapper.getTaskByID(task_id);
		} catch(Exception e) {
			System.out.println(e.toString());
		}
		finally {
			openSession.close();
			return task;
		}
		
	}
	
	//给Tea_id查Task_info
	@SuppressWarnings("finally")
	public ArrayList<String[]> getTaskByTeaID(String tea_id) throws IOException{
		// 1、获取sqlSessionFactory对象
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		// 2、获取sqlSession对象
		SqlSession openSession = sqlSessionFactory.openSession();
		ArrayList<String[]> result = null;
		try {
			// 3、获取接口的实现对象，由代理对象执行增删改查
			TeacherToolMapper mapper = openSession.getMapper(TeacherToolMapper.class);
			result = mapper.getTaskInfoByTeaID(tea_id);
		} catch(Exception e) {
			System.out.println(e.toString());
		}
		finally {
			openSession.close();
			return result;
		}
		
	}
}
