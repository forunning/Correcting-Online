package bean;

import java.sql.Timestamp;

public class Task {
	private int id;
	private Timestamp s_time;
	private Timestamp e_time;
	private String file;
	private int c_id;
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Timestamp getS_time() {
		return s_time;
	}
	public void setS_time(Timestamp s_time) {
		this.s_time = s_time;
	}
	public Timestamp getE_time() {
		return e_time;
	}
	public void setE_time(Timestamp e_time) {
		this.e_time = e_time;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public int getC_id() {
		return c_id;
	}
	public void setC_id(int c_id) {
		this.c_id = c_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Task [id=" + id + ", s_time=" + s_time + ", e_time=" + e_time + ", file=" + file + ", c_id=" + c_id
				+ ", name=" + name + "]";
	}
	
	
}
