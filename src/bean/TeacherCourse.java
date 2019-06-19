package bean;

public class TeacherCourse extends Manager{
	private String cou_name;
	private int cou_id;
	private int stu_num;
	private int task_num;
	public String getCou_name() {
		return cou_name;
	}
	public void setCou_name(String cou_name) {
		this.cou_name = cou_name;
	}
	public int getCou_id() {
		return cou_id;
	}
	public void setCou_id(int cou_id) {
		this.cou_id = cou_id;
	}
	public int getStu_num() {
		return stu_num;
	}
	public void setStu_num(int stu_num) {
		this.stu_num = stu_num;
	}
	public int getTask_num() {
		return task_num;
	}
	public void setTask_num(int task_num) {
		this.task_num = task_num;
	}
	@Override
	public String toString() {
		return "TeacherCourse [cou_name=" + cou_name + ", cou_id=" + cou_id + ", stu_num=" + stu_num + ", task_num="
				+ task_num + "]";
	}
	
	
}
