package dao;

import bean.Student;
import bean.Teacher;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.sun.org.glassfish.gmbal.ParameterNames;

import bean.Manager;

public interface UserMapper {
	//Student增删改查
	@Select("select * from student where id = #{id}")
	public Student getStudent(String id);
	
	@Insert("insert into student values(#{id},#{name},#{phone},#{sex},#{password},#{enrolltime},#{department},#{specialty})")
	public void addStudent(Student stuent);
	
	@Update("update student "
			+ "set name=#{name},phone=#{phone},sex=#{sex},password=#{password},"
			+ "enrolltime=#{enrolltime},department=#{department},specialty=#{specialty} "
			+ "where id=#{id}")
	public void updateStudent(Student stuent);
	
	@Delete("delete from student where id=#{id}")
	public void deleteStudent(Student student);
	//根据id的模糊查询
	@Select("select * from student where id like #{id}")
	public ArrayList<Student> selectStudentByIDLike(String id);
	//学生修改密码
	@Update("update student set password=#{pswd} where id=#{stu_id}")
	public void updateStudentPswd(@Param("pswd")String pswd,@Param("stu_id")String stu_id);
	
	//Teacher增删改查
	@Select("select * from teacher where id = #{id}")
	public Teacher getTeacher(String id);
	
	@Delete("delete from teacher where id=#{id}")
	public void deleteTeacher(Teacher teacher);
	
	@Insert("insert into teacher values(#{id},#{phone},#{name},#{sex},#{password},#{worktime},#{department})")
	public void addTeacher(Teacher teacher);
	
	@Update("update teacher "
			+ "set name=#{name},phone=#{phone},sex=#{sex},password=#{password},"
			+ "worktime=#{worktime},department=#{department} "
			+ "where id=#{id}")
	public void updateTeacher(Teacher teacher);
	
	@Select("select * from teacher where id like #{id}")
	public ArrayList<Teacher> selectTeacherLikeID(String id);
	
	
	
	//Manager改查
	@Select("select * from manager where id = #{id}")
	public Manager getManager(String id);
	
	@Update("update manager set password=#{password} where id=#{id}")
	public void updataManager(@Param("password")String pswd,@Param("id")String id);
}
