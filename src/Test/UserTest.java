package Test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import bean.Student;
import bean.Teacher;
import bean.Manager;
import dao.UserMapper;
import net.sf.json.JsonConfig;

public class UserTest {

	
	public SqlSessionFactory getSqlSessionFactory() throws IOException {
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		return new SqlSessionFactoryBuilder().build(inputStream);
	}
	@Test
	public void test() throws IOException {
		// 1����ȡsqlSessionFactory����
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		// 2����ȡsqlSession����
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			// 3����ȡ�ӿڵ�ʵ�������
			//��Ϊ�ӿ��Զ��Ĵ���һ��������󣬴������ȥִ����ɾ�Ĳ鷽��
			UserMapper mapper = openSession.getMapper(UserMapper.class);
			//Student employee = mapper.getStudent("16111205119");
			//Manager employee = mapper.getManager("123456");
			//System.out.println(employee);
			
			Manager manager=new Manager("wufangjian","wujian");
			
			openSession.commit();
		} finally {
			openSession.close();
		}
	}
	
	public void test1() {
		long fileName = System.currentTimeMillis() ;
		System.out.println(fileName);
	}
	
	public static void main(String[] args) {
		Date date=new Date();
		System.out.println(date.getTime());
	}

}
