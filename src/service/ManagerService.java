package service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.SeekableByteChannel;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.sun.org.apache.regexp.internal.recompile;
import com.sun.xml.internal.ws.wsdl.writer.document.OpenAtts;

import bean.Course;
import bean.Manager;
import bean.Student;
import bean.Teacher;
import bean.TeacherCourse;
import dao.CourseToolMapper;
import dao.StudentToolMapper;
import dao.TeacherToolMapper;
import dao.UserMapper;

public class ManagerService {
	
	//���й���session
	public SqlSessionFactory getSqlSessionFactory() throws IOException {
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		return new SqlSessionFactoryBuilder().build(inputStream);
	}
		
	//��ѧ���Ĳ�ѯ
	@SuppressWarnings("finally")
	public ArrayList<Student> getStudent(String id) throws IOException{
		// 1����ȡsqlSessionFactory����
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		// 2����ȡsqlSession����
		SqlSession openSession = sqlSessionFactory.openSession();
		ArrayList<Student> students = null;
		try {
			// 3����ȡ�ӿڵ�ʵ�ֶ����ɴ������ִ����ɾ�Ĳ�
			UserMapper mapper = openSession.getMapper(UserMapper.class);
			students = mapper.selectStudentByIDLike(id);
		} catch(Exception e) {
			System.out.println(e.toString());
		}
		finally {
			openSession.close();
			return students;
		}
	}
	//��ѧ��ɾ��
	@SuppressWarnings("finally")
	public void deleteStudent(Student student) throws IOException{
		SqlSessionFactory factory = getSqlSessionFactory();
		SqlSession opeSession = factory.openSession();
		try {
			UserMapper mapper = opeSession.getMapper(UserMapper.class);
			mapper.deleteStudent(student);
			opeSession.commit();
		}catch(Exception e) {
			System.out.println(e.toString());
		}finally {
			opeSession.close();
		}
	}
	//��ѧ������
	@SuppressWarnings("finally")
	public void addStudent(Student student) throws IOException{
		// 1����ȡsqlSessionFactory����
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		// 2����ȡsqlSession����
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			// 3����ȡ�ӿڵ�ʵ�ֶ����ɴ������ִ����ɾ�Ĳ�
			UserMapper mapper = openSession.getMapper(UserMapper.class);
			mapper.addStudent(student);
			openSession.commit();
		} catch(Exception e) {
			System.out.println(e.toString());
		}
		finally {
			openSession.close();
		}
	}
	//��ѧ���޸�
	@SuppressWarnings("finally")
	public void updateStudent(Student student) throws IOException{
		// 1����ȡsqlSessionFactory����
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		// 2����ȡsqlSession����
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			// 3����ȡ�ӿڵ�ʵ�ֶ����ɴ������ִ����ɾ�Ĳ�
			UserMapper mapper = openSession.getMapper(UserMapper.class);
			mapper.updateStudent(student);
			openSession.commit();
		} catch(Exception e) {
			System.out.println(e.toString());
		}
		finally {
			openSession.close();
		}
	}
	
	//�Խ�ʦ������
	@SuppressWarnings("finally")
	public void addTeacher(Teacher teacher) throws IOException{
		// 1����ȡsqlSessionFactory����
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		// 2����ȡsqlSession����
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			// 3����ȡ�ӿڵ�ʵ�ֶ����ɴ������ִ����ɾ�Ĳ�
			UserMapper mapper = openSession.getMapper(UserMapper.class);
			mapper.addTeacher(teacher);
			openSession.commit();
		} catch(Exception e) {
			System.out.println(e.toString());
		}
		finally {
			openSession.close();
		}
	}
	
	//�Խ�ʦ���޸�
	@SuppressWarnings("finally")
	public void updateTeacher(Teacher teacher) throws IOException{
		// 1����ȡsqlSessionFactory����
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		// 2����ȡsqlSession����
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			// 3����ȡ�ӿڵ�ʵ�ֶ����ɴ������ִ����ɾ�Ĳ�
			UserMapper mapper = openSession.getMapper(UserMapper.class);
			mapper.updateTeacher(teacher);
			openSession.commit();
		} catch(Exception e) {
			System.out.println(e.toString());
		}
		finally {
			openSession.close();
		}
	}
	
	//�Խ�ʦ��ɾ��
	@SuppressWarnings("finally")
	public void deleteTeacher(Teacher teacher) throws IOException{
		SqlSessionFactory factory = getSqlSessionFactory();
		SqlSession opeSession = factory.openSession();
		try {
			UserMapper mapper = opeSession.getMapper(UserMapper.class);
			mapper.deleteTeacher(teacher);
			opeSession.commit();
		}catch(Exception e) {
			System.out.println(e.toString());
		}finally {
			opeSession.close();
		}
	}
	
	//�Խ�ʦ�Ĳ�ѯ
	@SuppressWarnings("finally")
	public ArrayList<Teacher> getTeacher(String id) throws IOException{
		// 1����ȡsqlSessionFactory����
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		// 2����ȡsqlSession����
		SqlSession openSession = sqlSessionFactory.openSession();
		ArrayList<Teacher> teachers = null;
		try {
			// 3����ȡ�ӿڵ�ʵ�ֶ����ɴ������ִ����ɾ�Ĳ�
			UserMapper mapper = openSession.getMapper(UserMapper.class);
			teachers = mapper.selectTeacherLikeID(id);
		} catch(Exception e) {
			System.out.println(e.toString());
		}
		finally {
			openSession.close();
			return teachers;
		}
	}
	
	//�Կγ̽�������
	@SuppressWarnings("finally")
	public void addCourse(Course course) throws IOException{
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			CourseToolMapper courseToolMapper = session.getMapper(CourseToolMapper.class);
			courseToolMapper.addCourse(course);
			session.commit();
		}catch(Exception e) {
			System.out.println(e.toString());
		}finally{
			session.close();
		}
	}
	
	//�Կγ̽����޸�
	@SuppressWarnings("finally")
	public void updateCourse(Course course) throws IOException{
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			CourseToolMapper courseToolMapper = session.getMapper(CourseToolMapper.class);
			courseToolMapper.updateCourse(course);
			session.commit();
		}catch(Exception e) {
			System.out.println(e.toString());
		}finally{
			session.close();
		}
	}
	
	//�Կγ̽���ɾ��
	@SuppressWarnings("finally")
	public void deleteCourse(String id) throws IOException{
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			CourseToolMapper courseToolMapper = session.getMapper(CourseToolMapper.class);
			courseToolMapper.deleteCourseByID(id);
			session.commit();
		}catch(Exception e) {
			System.out.println(e.toString());
		}finally{
			session.close();
		}
	}
	
	//�Կγ̽��в�ѯ
	@SuppressWarnings("finally")
	public ArrayList<Course> getCourse(String id) throws IOException{
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession session = sqlSessionFactory.openSession();
		ArrayList<Course> courses = new ArrayList<Course>();
		
		try {
			CourseToolMapper courseToolMapper = session.getMapper(CourseToolMapper.class);
			courses = courseToolMapper.getCourseLikeID(id);
		}catch(Exception e) {
			System.out.println(e.toString());
		}finally{
			session.close();
			return courses;
		}
	}
	
	//�Ը���stu_idɾ��sc
	@SuppressWarnings("finally")
	public void deleteScByStu(String stu_id) throws IOException{
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			CourseToolMapper courseToolMapper = session.getMapper(CourseToolMapper.class);
			courseToolMapper.deleteScByStu(stu_id);
			session.commit();
		}catch(Exception e) {
			System.out.println(e.toString());
		}finally{
			session.close();
		}
	}
	
	//����cou_idɾ��sc
	@SuppressWarnings("finally")
	public void deleteScByCou(String cou_id) throws IOException{
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			CourseToolMapper courseToolMapper = session.getMapper(CourseToolMapper.class);
			courseToolMapper.deleteScByCou(cou_id);
			session.commit();
		}catch(Exception e) {
			System.out.println(e.toString());
		}finally{
			session.close();
		}
	}
	
	//����cou_idɾ��sc
	@SuppressWarnings("finally")
	public void deleteSc(String cou_id,String stu_id) throws IOException{
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			CourseToolMapper courseToolMapper = session.getMapper(CourseToolMapper.class);
			courseToolMapper.deleteSc(cou_id, stu_id);
			session.commit();
		}catch(Exception e) {
			System.out.println(e.toString());
		}finally{
			session.close();
		}
	}
	
	//����cou_id,stu_id����sc��
	@SuppressWarnings("finally")
	public void addSC(String cou_id,String stu_id) throws IOException{
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			CourseToolMapper courseToolMapper = session.getMapper(CourseToolMapper.class);
			courseToolMapper.addSC(stu_id, cou_id);
			session.commit();
		}catch(Exception e) {
			System.out.println(e.toString());
		}finally{
			session.close();
		}
	}
	
	//�޸�����
	@SuppressWarnings("finally")
	public void updatePswd(String pswd,String id) throws IOException{
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			mapper.updataManager(pswd,id);
			session.commit();
		}catch(Exception e) {
			System.out.println(e.toString());
		}finally{
			session.close();
		}
	}
	
	//��ȡƽ������
	@SuppressWarnings("finally")
	public String getAvgScore(String task_id) throws IOException{
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession session = sqlSessionFactory.openSession();
		String avg = "";
		try {
			StudentToolMapper mapper = session.getMapper(StudentToolMapper.class);
			avg = mapper.getAvgScore(task_id);
		}catch(Exception e) {
			System.out.println(e.toString());
		}finally{
			session.close();
			return avg;
		}
	}
	
	//��ȡ������ҵ�ύ��Ϣ
	@SuppressWarnings("finally")
	public ArrayList<HashMap> getFileInfo() throws IOException{
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession session = sqlSessionFactory.openSession();
		ArrayList<HashMap> map = null;
		try {
			StudentToolMapper mapper = session.getMapper(StudentToolMapper.class);
			map = mapper.getFileInfo();
		}catch(Exception e) {
			System.out.println(e.toString());
		}finally{
			session.close();
			return map;
		}
	}
	
	//��ѯ����Ա
	@SuppressWarnings("finally")
	public Manager getManager(String id) throws IOException{
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession session = sqlSessionFactory.openSession();
		Manager manager = null;
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			if(mapper.getManager(id)!=null) {
				manager = mapper.getManager(id);
			}
		}catch(Exception e) {
			System.out.println(e.toString());
		}finally{
			session.close();
			return manager;
		}
	}
}
