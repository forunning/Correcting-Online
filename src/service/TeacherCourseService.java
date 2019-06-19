package service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import bean.Submission;
import bean.Teacher;
import bean.TeacherCourse;
import bean.TeacherTask;
import dao.TeacherToolMapper;

public class TeacherCourseService {
	//���й���session
	public SqlSessionFactory getSqlSessionFactory() throws IOException {
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		return new SqlSessionFactoryBuilder().build(inputStream);
	}

	//��ѯ��ʦ�Ŀγ̱�
	@SuppressWarnings("finally")
	public ArrayList<TeacherCourse> getTeacherCourse(String tea_id) throws IOException{
		// 1����ȡsqlSessionFactory����
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		// 2����ȡsqlSession����
		SqlSession openSession = sqlSessionFactory.openSession();
		ArrayList<TeacherCourse> tc = null;
		try {
			// 3����ȡ�ӿڵ�ʵ�ֶ����ɴ������ִ����ɾ�Ĳ�
			TeacherToolMapper mapper = openSession.getMapper(TeacherToolMapper.class);
			tc = mapper.getTeacherCourse(tea_id);
		} catch(Exception e) {
			System.out.println(e.toString());
		}
		finally {
			openSession.close();
			return tc;
		}
	}
	
	//��ѯ��ʦĳ�γ̵���ҵ
	@SuppressWarnings("finally")
	public ArrayList<TeacherTask> getTeacherTask(String cou_id) throws IOException{
		// 1����ȡsqlSessionFactory����
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		// 2����ȡsqlSession����
		SqlSession openSession = sqlSessionFactory.openSession();
		ArrayList<TeacherTask> tt = null;
		try {
			// 3����ȡ�ӿڵ�ʵ�ֶ����ɴ������ִ����ɾ�Ĳ�
			TeacherToolMapper mapper = openSession.getMapper(TeacherToolMapper.class);
			tt = mapper.getTeacherTask(cou_id);
		} catch(Exception e){
			System.out.println(e.toString());
		}
		finally {
			openSession.close();
			return tt;
		}
	}
	
	//���γ̺Ų�ѧ���ύ��¼
	@SuppressWarnings("finally")
	public ArrayList<Submission> getSubmissionByCourse(String cou_id) throws IOException{
		// 1����ȡsqlSessionFactory����
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		// 2����ȡsqlSession����
		SqlSession openSession = sqlSessionFactory.openSession();
		ArrayList<Submission> sub_list = null;
		try {
			// 3����ȡ�ӿڵ�ʵ�ֶ����ɴ������ִ����ɾ�Ĳ�
			TeacherToolMapper mapper = openSession.getMapper(TeacherToolMapper.class);
			sub_list = mapper.getSubmissionByCourse(cou_id);
		} catch(Exception e){
			System.out.println(e.toString());
		}
		finally {
			openSession.close();
			return sub_list;
		}
	}
	
	//���γ̺Ų��Ӧ�Ľ�ʦ��Ϣ
	@SuppressWarnings("finally")
	public Teacher getTeacherByCourse(String cou_id) throws IOException{
		// 1����ȡsqlSessionFactory����
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		// 2����ȡsqlSession����
		SqlSession openSession = sqlSessionFactory.openSession();
		Teacher teacher = new Teacher();
		try {
			// 3����ȡ�ӿڵ�ʵ�ֶ����ɴ������ִ����ɾ�Ĳ�
			TeacherToolMapper mapper = openSession.getMapper(TeacherToolMapper.class);
			teacher = mapper.getTeacherByCourse(cou_id);
		} catch(Exception e){
			System.out.println(e.toString());
		}
		finally {
			openSession.close();
			return teacher;
		}
	}
}
