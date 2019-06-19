package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import bean.Student;
import bean.StudentCourse;
import bean.StudentTask;
import bean.Submission;
import bean.Task;

public interface StudentToolMapper {
	//--��ѯѧ�������Ŀγ���Ϣ//����������ѯ��ɢ������ѯ��������ʹ��
	@Select("select course.id cou_id,course.name cou_name,teacher.name tea_name,b.stu_num,a.task_num\r\n" + 
			"from course,teacher,(\r\n" + 
			"	select c_id cou_id,count(*) task_num \r\n" + 
			"	from task \r\n" + 
			"	group by c_id \r\n" + 
			") as a,(\r\n" + 
			"	select cou_id,count(*) stu_num \r\n" + 
			"	from sc \r\n" + 
			"	group by cou_id\r\n" + 
			") as b,sc \r\n" + 
			"where course.tea_id=teacher.id and \r\n" + 
			"	course.id=a.cou_id and \r\n" + 
			"	course.id=b.cou_id and \r\n" + 
			" 	course.id=sc.cou_id and\r\n" + 
			"	sc.stu_id=#{stu_id}")
	public ArrayList<StudentCourse> getStudentCourse(String stu_id);
	
	//��ѯѧ�������γ���Ϣ
	//��ѯ�����пγ�id
	@Select("select course.id cou_id,course.name cou_name \r\n" + 
			"from sc,course where course.id = sc.cou_id\r\n" + 
			"and sc.stu_id = #{stu_id}")
	public ArrayList<HashMap> getCourseIDName(String stu_id);
	
	//��ѯ�γ̵�ѧ������
	@Select("SELECT COUNT(*) stu_num \r\n" + 
			"	from sc where sc.cou_id = #{cou_id}")
	public int getStuNum(String cou_id);
	
	//��ѯ�γ̵���ҵ����
	@Select("SELECT count(*) task_num \r\n" + 
			"	from task where task.c_id = #{cou_id}")
	public int getTaskNum(String cou_id);
	
	
	//--��ѯѧ���Ŀγ̵���ҵ�б�
	@Select("select task.id task_id,task.name task_name,st.score score, \r\n" + 
			"	task.s_time stime,task.e_time etime,st.filepath stu_filepath, \r\n" + 
			"	task.file task_filepath \r\n" + 
			"from task \r\n" + 
			"LEFT JOIN sc on task.c_id = sc.cou_id \r\n" + 
			"LEFT JOIN st on task.id = st.tas_id\r\n" + 
			"where task.c_id = #{cou_id}\r\n" + 
			"	and st.stu_id = #{stu_id}")
	public ArrayList<StudentTask> getStudentTask(@Param("cou_id")String cou_id,@Param("stu_id")String stu_id);
	
	//���ݿγ̺Ų�γ�name
	@Select("select name from course where id=#{cou_id}")
	public String getCourseName(String cou_id);
	
	//���ݿγ̺Ų�ѧ��
	@Select("select * from student where id in (\r\n" + 
			"	select stu_id from sc where cou_id=#{cou_id}\r\n" + 
			")")
	public ArrayList<Student> getStudentByCourse(String cou_id);
	
	//�ύ��ҵ����st���в����µ���ҵ�ύ��¼��
	@Insert("insert into st \r\n" + 
			"	values(#{stu_id},#{task_id},\r\n" + 
			"	#{filepath},#{time},#{score})")
	public void addSubmission(Submission sub);
	
	//���ݿγ̺ź�ѧ���Ų�ѯ��ҵ�ύ��¼
	@Select("select stu_id,filepath,time,score \r\n" + 
			"from st\r\n" + 
			"where stu_id=#{stu_id}\r\n" + 
			"and tas_id in (\r\n" + 
			"	select id from task \r\n" + 
			"	where c_id=#{cou_id}\r\n" + 
			")")
	public ArrayList<Submission> getSubmissionList(@Param("cou_id")String cou_id,@Param("stu_id")String stu_id);
	
	//�޸�ѧ����ϵ��ʽ
	@Update("update student \r\n" + 
			"	set phone=#{phone}\r\n" + 
			"	where id=#{stu_id}")
	public void updatePhone(@Param("phone")String phone,@Param("stu_id")String stu_id);
	
	//����task_id��ѯsubmission��¼
	@Select("select * from st where st.stu_id=#{stu_id} and tas_id=#{task_id}")
	public ArrayList<Submission> getSubmissionListByID(@Param("stu_id")String stu_id,@Param("task_id")String task_id);;
	
	//ɾ��submission��¼
	@Delete("delete from st where stu_id=#{stu_id} and tas_id=#{task_id}")
	public void delSubmissionByID(@Param("stu_id")String stu_id,@Param("task_id")String task_id);
	
	//����cou_id��task��
	@Select("select * from task where c_id=#{cou_id}")
	public ArrayList<Task> getTaskIDByCourse(String cou_id);
	
	//����Task_id ��ѯѧ���б�
	@Select("select * from student where id in (select stu_id from st where tas_id=#{task_id})")
	public ArrayList<Student> getStudentByTaskID(String task_id);
	
	//����Task_id ��ѯ�ɼ�ƽ����
	@Select("SELECT AVG(st.score) average from st where tas_id=#{task_id}")
	public String getAvgScore(String task_id);
	
	//��ѯ�ļ������Ϣ
	@Select("select st.time time,task.id task_id,task.name task_name,course.name cou_name,student.id stu_id,\r\n" + 
			"	student.`name` stu_name,st.filepath filepath from student,st,task,course \r\n" + 
			"	where student.id=st.stu_id AND task.id=st.tas_id and task.c_id=course.id ")
	public ArrayList<HashMap> getFileInfo();
}
