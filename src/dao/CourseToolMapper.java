package dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import bean.Course;
import bean.Student;
import bean.StudentCourse;
import bean.Teacher;

public interface CourseToolMapper {
	//对Course进行增加
	@Insert("insert into course (tea_id,name) values(#{tea_id},#{name})")
	public void addCourse(Course course);
	
	//对Course进行修改
	@Update("update course set "
			+ "tea_id=#{tea_id},"
			+ "name=#{name} "
			+ "where id=#{id}")
	public void updateCourse(Course course);
	
	//对Course进行删除
	@Delete("delete from course where id=#{id}")
	public void deleteCourseByID(String id);
	
	//对Course进行查询
	@Select("select * from course where id like #{id}")
	public ArrayList<Course> getCourseLikeID(String id);
	
	//根据stu_id删除sc对应项
	@Delete("delete from sc where stu_id=#{stu_id}")
	public void deleteScByStu(String stu_id);
	
	//根据cou_id删除sc对应项
	@Delete("delete from sc where cou_id=#{cou_id}")
	public void deleteScByCou(String cou_id);
	
	//根据cou_id,stu_id删除sc对应项
	@Delete("delete from sc where stu_id=#{stu_id} and cou_id=#{cou_id}")
	public void deleteSc(@Param("cou_id")String cou_id,@Param("stu_id")String stu_id);
	
	//根据cou_id,stu_id增加sc项
	@Insert("insert into sc values (#{stu_id},#{cou_id})")
	public void addSC(@Param("stu_id")String stu_id,@Param("cou_id")String cou_id);
	
	//根据stu_id查询sc
	@Select("select * from sc where stu_id=#{stu_id} and cou_id=#{cou_id}")
	public ArrayList<StudentCourse> getScByStu(@Param("stu_id")String stu_id,@Param("cou_id")String cou_id);
}
