package bean;

public class StudentCourse extends Manager{
	private int cou_id;
	private String cou_name;
	private String tea_name;
	private int stu_num;//学生数量
	private int task_num;//课程的数量
	
	public StudentCourse() {
		super();
	}
	
	public StudentCourse(int cou_id,String cou_name,String tea_name,int stu_num,
			int task_num) {
		super();
		this.cou_id = cou_id;
		this.cou_name = cou_name;
		this.tea_name = tea_name;
		this.stu_num = stu_num;
		this.task_num = task_num;
	}
	
	public int getCou_id() {
		return cou_id;
	}
	public void setCou_id(int cou_id) {
		this.cou_id = cou_id;
	}
	public String getCou_name() {
		return cou_name;
	}
	public void setCou_name(String cou_name) {
		this.cou_name = cou_name;
	}
	public String getTea_name() {
		return tea_name;
	}
	public void setTea_name(String tea_name) {
		this.tea_name = tea_name;
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
		return "StudentCourse [cou_id=" + cou_id + ", cou_name=" + cou_name + ", tea_name=" + tea_name + ", stu_num="
				+ stu_num + ", task_num=" + task_num + "]";
	}


	
}
