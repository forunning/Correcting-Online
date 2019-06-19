package bean;

public class Student extends Manager{
	private String id;
	private String name;
	private String phone;
	private String sex;
	private String password;
	private String enrolltime;
	private String department;
	private String specialty;
	
	

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Student(String id, String password) {
		super(id, password);
		// TODO Auto-generated constructor stub
	}

	public Student(String id, String name, String phone, String sex, String password, String enrolltime,
			String department, String specialty) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.sex = sex;
		this.password = password;
		this.enrolltime = enrolltime;
		this.department = department;
		this.specialty = specialty;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public String getEnrolltime() {
		return enrolltime;
	}

	public void setEnrolltime(String enrolltime) {
		this.enrolltime = enrolltime;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", phone=" + phone + ", sex=" + sex + ", password=" + password
				+ ", enrolltime=" + enrolltime + ", department=" + department + ", specialty=" + specialty + "]";
	}


	


}
