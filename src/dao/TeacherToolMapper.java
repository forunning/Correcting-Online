package dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import bean.Student;
import bean.Submission;
import bean.Task;
import bean.Teacher;
import bean.TeacherCourse;
import bean.TeacherTask;

public interface TeacherToolMapper {
	//--查询教师的课表
	@Select("select course.name cou_name,course.id cou_id,stu_num,task_num\r\n" + 
			"			from course \r\n" + 
			"LEFT JOIN (\r\n" + 
			"				select c_id cou_id,count(*) task_num \r\n" + 
			"				from task \r\n" + 
			"				group by c_id \r\n" + 
			"			) as a on course.id=a.cou_id \r\n" + 
			"LEFT JOIN (\r\n" + 
			"				select cou_id,count(*) stu_num \r\n" + 
			"				from sc \r\n" + 
			"				group by cou_id\r\n" + 
			"			) as b ON course.id=b.cou_id \r\n" + 
			"			where \r\n" + 
			"				course.tea_id=#{tea_id}")
	public ArrayList<TeacherCourse> getTeacherCourse(String tea_id);
	
	//--查询教师课程的作业
	@Select("select task.id task_id,task.name task_name,\r\n" + 
			"	task.s_time stime,task.e_time etime,task.file filepath \r\n" + 
			"from task \r\n" + 
			"where task.c_id=#{cou_id}")
	public ArrayList<TeacherTask> getTeacherTask(String cou_id);
	
	//--教师发布新的作业
	@Insert("insert into task \r\n" + 
			"	values \r\n" + 
			"	(#{id},#{s_time},#{e_time},#{file},#{c_id},#{name})")
	public void addTask(Task task);
	
	//--教师查询一个作业下的所有提交情况
	@Select("select student.id stu_id,student.name stu_name,\r\n" + 
			"	student.sex stu_sex,student.specialty stu_spe,\r\n" + 
			"	st.filepath filepath,st.time time,st.score st_score \r\n" + 
			"from student,st \r\n" + 
			"where student.id=st.stu_id and st.tas_id=#{task_id}")
	public ArrayList<Submission> getStudentSubmssion(String task_id);
	
	//教师修改作业
	@Update("update task set \r\n" + 
			"	s_time=#{s_time},\r\n" + 
			"	e_time=#{e_time},\r\n" + 
			"	file=#{file},\r\n" + 
			"	c_id=#{c_id},\r\n" + 
			"	name=#{name} \r\n" + 
			"where id=#{id}")
	public void updateTask(Task task);
	
	//教师删除作业
	@Delete("delete from task \r\n" + 
			"	where task.id=#{task_id}")
	public void deleteTask(String task_id);
	
	//教师修改学生分数
	@Update("update st set score=#{score} \r\n" + 
			"	where st.stu_id=#{stu_id} and\r\n" + 
			"		st.tas_id=#{task_id}")
	public void updateScore(@Param("score")int score,@Param("stu_id")String stu_id,@Param("task_id")String task_id);

	//修改教师联系方式
	@Update("update teacher \r\n" + 
			"	set phone=#{phone}\r\n" + 
			"	where id=#{tea_id}")
	public void updatePhone(@Param("phone")String phone,@Param("stu_id")String tea_id);
	
	//查询某课程中所有学生信息
	@Select("select * \r\n" + 
			"from student \r\n" + 
			"where id in(\r\n" + 
			"	select stu_id \r\n" + 
			"	from sc \r\n" + 
			"	where cou_id=#{cou_id}\r\n" + 
			")")
	public ArrayList<Student> getStudentOfCourse(String cou_id);
	
	//给课程号查学生提交记录
	@Select("select student.id stu_id,student.name stu_name,\r\n" + 
			"	task.name task_name,st.score score,st.tas_id task_id\r\n" + 
			"from student,st,task\r\n" + 
			"where \r\n" + 
			"	student.id=st.stu_id and \r\n" + 
			"	st.tas_id=task.id and \r\n" + 
			"	task.c_id=#{cou_id}\r\n" + 
			"")
	public ArrayList<Submission> getSubmissionByCourse(String cou_id);
	
	//根据作业id查询作业的要求文档地址
	@Select("select file from task where id=#{task_id}")
	public String getTaskFile(String task_id);
	
	//根据作业id与学生id查询学生提交的作业地址
	@Select("select filepath from st where stu_id=#{stu_id} and "
			+ "tas_id=#{task_id}")
	public String getSubmissionFilepath(@Param("stu_id")String stu_id,@Param("task_id")String task_id);
	
	//给课程号查教师信息
	@Select("select * from teacher where id=(select tea_id from course where id=#{cou_id})")
	public Teacher getTeacherByCourse(String cou_id);
	
	//给task_id查task内容
	@Select("select * from task where id=#{task_id}")
	public Task getTaskByID(String task_id);
	
	//给tea_id查task_id和task_name
	@Select("select task.id,task.name from task where task.c_id in("
			+ "select course.id from course where course.tea_id=#{tea_id}"
			+ ")")
	public ArrayList<String[]> getTaskInfoByTeaID(String tea_id);
	
	//教师修改密码
	@Update("update teacher set password=#{pswd} where id=#{tea_id}")
	public void updatePswd(@Param("pswd")String pswd,@Param("tea_id")String tea_id);
}
