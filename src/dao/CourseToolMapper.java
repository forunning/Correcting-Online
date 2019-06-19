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
	//��Course��������
	@Insert("insert into course (tea_id,name) values(#{tea_id},#{name})")
	public void addCourse(Course course);
	
	//��Course�����޸�
	@Update("update course set "
			+ "tea_id=#{tea_id},"
			+ "name=#{name} "
			+ "where id=#{id}")
	public void updateCourse(Course course);
	
	//��Course����ɾ��
	@Delete("delete from course where id=#{id}")
	public void deleteCourseByID(String id);
	
	//��Course���в�ѯ
	@Select("select * from course where id like #{id}")
	public ArrayList<Course> getCourseLikeID(String id);
	
	//����stu_idɾ��sc��Ӧ��
	@Delete("delete from sc where stu_id=#{stu_id}")
	public void deleteScByStu(String stu_id);
	
	//����cou_idɾ��sc��Ӧ��
	@Delete("delete from sc where cou_id=#{cou_id}")
	public void deleteScByCou(String cou_id);
	
	//����cou_id,stu_idɾ��sc��Ӧ��
	@Delete("delete from sc where stu_id=#{stu_id} and cou_id=#{cou_id}")
	public void deleteSc(@Param("cou_id")String cou_id,@Param("stu_id")String stu_id);
	
	//����cou_id,stu_id����sc��
	@Insert("insert into sc values (#{stu_id},#{cou_id})")
	public void addSC(@Param("stu_id")String stu_id,@Param("cou_id")String cou_id);
	
	//����stu_id��ѯsc
	@Select("select * from sc where stu_id=#{stu_id} and cou_id=#{cou_id}")
	public ArrayList<StudentCourse> getScByStu(@Param("stu_id")String stu_id,@Param("cou_id")String cou_id);
}
