package bean;

import java.sql.Timestamp;

public class Submission {
	private String stu_id;
	private String stu_name;
	private String stu_sex;
	private String stu_spe;
	private String filepath;
	private String task_name;
	public String getTask_id() {
		return task_id;
	}
	public void setTask_id(String task_id) {
		this.task_id = task_id;
	}
	private String task_id;
	private Timestamp time;
	private int score;
	public String getStu_id() {
		return stu_id;
	}
	public void setStu_id(String stu_id) {
		this.stu_id = stu_id;
	}
	public String getStu_name() {
		return stu_name;
	}
	public void setStu_name(String stu_name) {
		this.stu_name = stu_name;
	}
	public String getStu_sex() {
		return stu_sex;
	}
	public void setStu_sex(String stu_sex) {
		this.stu_sex = stu_sex;
	}
	public String getStu_spe() {
		return stu_spe;
	}
	public void setStu_spe(String stu_spe) {
		this.stu_spe = stu_spe;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public int getScore() {
		return score;
	}
	public String getTask_name() {
		return task_name;
	}
	public void setTask_name(String task_name) {
		this.task_name = task_name;
	}
	public void setScore(int score) {
		this.score = score;
	}
	@Override
	public String toString() {
		return "Submission [stu_id=" + stu_id + ", stu_name=" + stu_name + ", stu_sex=" + stu_sex + ", stu_spe="
				+ stu_spe + ", filepath=" + filepath + ", task_name=" + task_name + ", task_id=" + task_id + ", time="
				+ time + ", score=" + score + "]";
	}
	
}
