package bean;

public class Teacher extends Manager{
	private String id;
	private String phone;
	private String name;
	private String sex;
	private String password;
	private String worktime;
	private String department;

	
	
	
	public Teacher() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
	public Teacher(String id, String phone, String name, String sex, String password, String worktime,
			String department) {
		super();
		this.id = id;
		this.phone = phone;
		this.name = name;
		this.sex = sex;
		this.password = password;
		this.worktime = worktime;
		this.department = department;
	}




	public Teacher(String id, String password) {
		super(id, password);
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getWorktime() {
		return worktime;
	}

	public void setWorktime(String worktime) {
		this.worktime = worktime;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Teacher [id=" + id + ", phone=" + phone + ", name=" + name + ", sex=" + sex + ", password=" + password
				+ ", worktime=" + worktime + ", department=" + department + "]";
	}

}
