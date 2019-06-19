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
	//���й���session
	public SqlSessionFactory getSqlSessionFactory() throws IOException {
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		return new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	//�޸Ľ�ʦ����ϵ��ʽ
	@SuppressWarnings("finally")
	public void updatePhone(String phone,String tea_id) throws IOException{
		// 1����ȡsqlSessionFactory����
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		// 2����ȡsqlSession����
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			// 3����ȡ�ӿڵ�ʵ�ֶ����ɴ������ִ����ɾ�Ĳ�
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
		// 1����ȡsqlSessionFactory����
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		// 2����ȡsqlSession����
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			// 3����ȡ�ӿڵ�ʵ�ֶ����ɴ������ִ����ɾ�Ĳ�
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
		// 1����ȡsqlSessionFactory����
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		// 2����ȡsqlSession����
		SqlSession openSession = sqlSessionFactory.openSession();
		Teacher teacher = null;
		try {
			// 3����ȡ�ӿڵ�ʵ�ֶ����ɴ������ִ����ɾ�Ĳ�
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
