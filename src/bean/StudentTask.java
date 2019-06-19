package bean;

import java.sql.Timestamp;

public class StudentTask extends Manager{
	private int task_id;
	private String task_name;
	private int score;
	private Timestamp stime;
	private Timestamp etime;
	private String stu_filepath;
	private String task_filepath;
	public int getTask_id() {
		return task_id;
	}
	public void setTask_id(int task_id) {
		this.task_id = task_id;
	}
	public String getTask_name() {
		return task_name;
	}
	public void setTask_name(String task_name) {
		this.task_name = task_name;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public Timestamp getStime() {
		return stime;
	}
	public void setStime(Timestamp stime) {
		this.stime = stime;
	}
	public Timestamp getEtime() {
		return etime;
	}
	public void setEtime(Timestamp etime) {
		this.etime = etime;
	}
	public String getStu_filepath() {
		return stu_filepath;
	}
	public void setStu_filepath(String stu_filepath) {
		this.stu_filepath = stu_filepath;
	}
	public String getTask_filepath() {
		return task_filepath;
	}
	public void setTask_filepath(String task_filepath) {
		this.task_filepath = task_filepath;
	}
	@Override
	public String toString() {
		return "StudentTask [task_id=" + task_id + ", task_name=" + task_name + ", score=" + score + ", stime=" + stime
				+ ", etime=" + etime + ", stu_filepath=" + stu_filepath + ", task_filepath=" + task_filepath + "]";
	}
	
}
