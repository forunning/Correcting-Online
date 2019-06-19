package bean;

import java.sql.Timestamp;

public class TeacherTask extends Manager{
	private int task_id;
	private String task_name;
	private Timestamp stime;
	private Timestamp etime;
	private String filepath;
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
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	@Override
	public String toString() {
		return "TeacherTask [task_id=" + task_id + ", task_name=" + task_name + ", stime=" + stime + ", etime=" + etime
				+ ", filepath=" + filepath + "]";
	}
	
}
