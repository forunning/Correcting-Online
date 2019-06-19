package Test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import bean.StudentCourse;
import bean.StudentTask;
import bean.Submission;
import dao.StudentToolMapper;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import service.StudentService;
import tool.TimestampProcessor;

public class StudentTest {
	
//	public SqlSessionFactory getSqlSessionFactory() throws IOException {
//		String resource = "mybatis-config.xml";
//		InputStream inputStream = Resources.getResourceAsStream(resource);
//		return new SqlSessionFactoryBuilder().build(inputStream);
//	}
//
//	@Test
//	public void testOfGetStudentCourse() throws IOException{
//		//1����ȡsqlSessionFactory����
//		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
//		//2����ȡsqlSession����
//		SqlSession openSession = sqlSessionFactory.openSession();
//		try {
//			//3����ȡ�ӿڵ�ʵ�ֶ����ɴ������ִ����ɾ�Ĳ�
//			StudentToolMapper mapper = openSession.getMapper(StudentToolMapper.class);
//			ArrayList<StudentCourse> sc_list = mapper.getStudentCourse("1611205119");
//			for(StudentCourse sc:sc_list) {
//				System.out.println(sc.toString());
//			}
//		}finally {
//			openSession.close();
//		}
//	}
////	
//	@Test
//	public void testOfGetStudentTask() throws IOException{
//		//1����ȡsqlSessionFactory����
//		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
//		//2����ȡsqlSession����
//		SqlSession openSession = sqlSessionFactory.openSession();
//		try {
//			//3����ȡ�ӿڵ�ʵ�ֶ����ɴ������ִ����ɾ�Ĳ�
//			StudentToolMapper mapper = openSession.getMapper(StudentToolMapper.class);
//			ArrayList<StudentTask> st_list = mapper.getStudentTask("1001","1611205119");
//			JsonConfig jsonConfig = new JsonConfig();                                                          
//			jsonConfig.registerJsonValueProcessor(java.sql.Timestamp.class, new TimestampProcessor ());
//			JSONArray ja = JSONArray.fromObject(st_list,jsonConfig);
//			System.out.println(ja);
//		}finally {
//			openSession.close();
//		}
//	}
//	
//	@Test
//	public void test() throws IOException{
//			//���ݿ⴦��
//			StudentService ss = new StudentService();
//			if(isExist("1611205119",  "3001")) {
//				//������ڽ���¼ɾ��
//				ss.delSubmissionByID("1611205119", "3001");
//			}
//			
//			Submission sub = new Submission();
//			sub.setFilepath("");//�����ļ�·��ȫ��
//			sub.setStu_id("1611205119");
//			sub.setTask_id("3001");
//			//��ȡ������ʱ��
//			Timestamp time = new Timestamp(System.currentTimeMillis());
//			sub.setTime(time);
//			//��Ӽ�¼
//			ss.addSubmission(sub);
//		}
//	
//	private static boolean isExist(String stu_id,String task_id) throws IOException {
//		boolean result = false;
//		
//		StudentService ss = new StudentService();
//		ArrayList<Submission> submission = ss.getSubmissionListByID(stu_id, task_id);
//		for(Submission s:submission) {
//			result = true;//ֻҪsubmission�о�˵�����ύ��¼
//		}
//		
//		return result;
//	}
//	
//	@Test
//	public void testStudentCourse() throws IOException{
//		StudentService ss = new StudentService();
//		ArrayList<StudentCourse> sc_list = ss.getStudentCourse("1611205119");
//		System.out.println(sc_list);
//		
//	}
	
	@Test
	public void testStudentTask() throws IOException{
		StudentService ss = new StudentService();
		ArrayList<StudentTask> st_list = ss.getStudentTask("1001", "1611205119");
		System.out.println(st_list);
		
	}
	
}
