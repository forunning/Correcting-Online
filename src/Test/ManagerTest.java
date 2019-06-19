package Test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.junit.Test;

import bean.Course;
import bean.Student;
import bean.StudentCourse;
import bean.Teacher;
import net.sf.json.JSONArray;
import service.ManagerService;
import service.StudentService;
import service.TeacherCourseService;

public class ManagerTest {

//Man_handStu
//	@Test
//	public void testselect() throws IOException {
//		String id = "1611205119";
//		if(id == ""||id == null) {
//			id = "%";
//		}
//		ArrayList<Student> students = new ArrayList<Student>();
//		ManagerService managerService = new ManagerService();
//		students = managerService.getStudent(id);
//		System.out.println(students.toString());
//	}
//	
//	@Test
//	public void testAdd() throws IOException {
//		ManagerService managerService = new ManagerService();
//		Student stu = new Student();
//		stu.setId("1611111111");
//		stu.setDepartment("123");
//		stu.setEnrolltime("2016");
//		stu.setName("silver");
//		stu.setPassword("123456");
//		stu.setPhone("123456");
//		stu.setSex("��");
//		stu.setSpecialty("�������");
//		managerService.addStudent(stu);
//	}
//	@Test
//	public void testUpdate() throws IOException {
//		ManagerService managerService = new ManagerService();
//		Student stu = new Student();
//		stu.setId("1611111111");
//		stu.setDepartment("123");
//		stu.setEnrolltime("2016");
//		stu.setName("silverRice");
//		stu.setPassword("123456");
//		stu.setPhone("123456");
//		stu.setSex("��");
//		stu.setSpecialty("�������");
//		managerService.updateStudent(stu);
//	}
//	
//	@Test
//	public void testDel() throws IOException {
//		ManagerService managerService = new ManagerService();
//		Student stu = new Student();
//		stu.setId("1611111111");
//		managerService.deleteStudent(stu);
//	}
	
//Man_handTea
//	@Test
//	public void testselect() throws IOException {
//		String id = "5001";
//		if(id == ""||id == null) {
//			id = "%";
//		}
//		ArrayList<Teacher> teachers = new ArrayList<Teacher>();
//		ManagerService managerService = new ManagerService();
//		teachers = managerService.getTeacher(id);
//		System.out.println(teachers.toString());
//	}
//	
//	@Test
//	public void testAdd() throws IOException {
//		ManagerService managerService = new ManagerService();
//		Teacher teacher = new Teacher();
//		teacher.setSex("��");
//		teacher.setId("5011");
//		teacher.setDepartment("�������");
//		teacher.setName("silver");
//		teacher.setPassword("silver");
//		teacher.setPhone("132465");
//		teacher.setWorktime("1999");
//		managerService.addTeacher(teacher);
//	}

//	@Test
//	public void testDel() throws IOException {
//		ManagerService managerService = new ManagerService();
//		Teacher teacher = new Teacher();
//		teacher.setId("");
//		managerService.deleteTeacher(teacher);
//	}
	
//Man_setCou
//	@Test
//	public void testSelectCourse() throws IOException{
//		String id = "1001";
//		if(id == ""||id == null) {
//			id = "%";
//		}
//		ArrayList<Course> courses = new ArrayList<Course>();
//		ManagerService managerService = new ManagerService();
//		courses = managerService.getCourse(id);
//		System.out.println(courses.toString());
//	}
	
//	@Test
//	public void testAdd() throws IOException{
//		Course course = new Course();
//		course.setName("123");
//		course.setTea_id("5001");
//		ManagerService managerService = new ManagerService();
//		managerService.addCourse(course);
//	}
//	
//	@Test
//	public void testUpdate() throws IOException{
//		Course course = new Course();
//		course.setId(1011);
//		course.setName("silver");
//		course.setTea_id("5001");
//		ManagerService managerService = new ManagerService();
//		managerService.updateCourse(course);;
//	}

	//
//	@Test
//	public void test() throws IOException{
//		String cou_id = "1001";
//		String stu_id = "1611205001";
//		ManagerService managerService = new ManagerService();
//		managerService.deleteSc(cou_id, stu_id);
//	}
//	
//	protected boolean isExistedInCourse(String stu_id,String cou_id) throws IOException {
//		boolean b = false;
//		
//		StudentService studentService = new StudentService();
//		for(StudentCourse sc:studentService.getScByStu(stu_id, cou_id)) {
//			if (sc!=null)
//				b = true;
//		}
//		
//		return b;
//	}
	
	@Test
	public void test() throws IOException{
		boolean b = ManIsExits("2003");
		System.out.println(b);
	}
	
	
	protected boolean ManIsExits(String id) throws IOException{
		boolean b = true;
		
		ManagerService ms = new  ManagerService();
		if(ms.getManager(id)==null) {
			b = false;
		}
		
		return b;
	}
}
