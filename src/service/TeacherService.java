package service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import bean.Teacher;
import bean.TeacherCourse;
import dao.TeacherToolMapper;
import dao.UserMapper;

public class TeacherService {
	//自行构建session
	public SqlSessionFactory getSqlSessionFactory() throws IOException {
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		return new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	//修改教师的联系方式
	@SuppressWarnings("finally")
	public void updatePhone(String phone,String tea_id) throws IOException{
		// 1、获取sqlSessionFactory对象
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		// 2、获取sqlSession对象
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			// 3、获取接口的实现对象，由代理对象执行增删改查
			TeacherToolMapper mapper = openSession.getMapper(TeacherToolMapper.class);
			mapper.updatePhone(phone, tea_id);
		} catch(Exception e) {
			System.out.println(e.toString());
		}
		finally {
			openSession.close();
		}
	}	
	
	@SuppressWarnings("finally")
	public void updatePswd(String pswd,String tea_id) throws IOException{
		// 1、获取sqlSessionFactory对象
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		// 2、获取sqlSession对象
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			// 3、获取接口的实现对象，由代理对象执行增删改查
			TeacherToolMapper mapper = openSession.getMapper(TeacherToolMapper.class);
			mapper.updatePswd(pswd, tea_id);
			openSession.commit();
		} catch(Exception e) {
			System.out.println(e.toString());
		}
		finally {
			openSession.close();
		}
	}
	
	@SuppressWarnings("finally")
	public Teacher getTeacherByID(String tea_id) throws IOException{
		// 1、获取sqlSessionFactory对象
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		// 2、获取sqlSession对象
		SqlSession openSession = sqlSessionFactory.openSession();
		Teacher teacher = null;
		try {
			// 3、获取接口的实现对象，由代理对象执行增删改查
			UserMapper mapper = openSession.getMapper(UserMapper.class);
			teacher =  mapper.getTeacher(tea_id);
		} catch(Exception e) {
			System.out.println(e.toString());
		}
		finally {
			openSession.close();
			return teacher;
		}
	}
}
