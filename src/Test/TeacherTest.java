package Test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.mysql.fabric.xmlrpc.base.Array;

import bean.Student;
import bean.StudentCourse;
import bean.Submission;
import bean.Task;
import bean.TeacherCourse;
import bean.TeacherTask;
import dao.StudentToolMapper;
import dao.TeacherToolMapper;
import net.sf.json.JSONArray;
import service.ManagerService;
import service.StudentService;
import service.TeacherCourseService;
import service.TeacherTaskService;

public class TeacherTest {
	@Test
	public void TeaIsInCourse() throws IOException{
		boolean b = true;
		String tea_id = "5011";
		if(tea_id.equals("")||tea_id == null) b=true;
		else {
			ArrayList<TeacherCourse> tcs = new ArrayList<TeacherCourse>();
			TeacherCourseService service = new TeacherCourseService();
			tcs = service.getTeacherCourse(tea_id);
			System.out.println(tcs.toString());
			if(tcs.isEmpty()) b = false;
			else b = true;
		}
	
		System.out.println(b);

	}
}
