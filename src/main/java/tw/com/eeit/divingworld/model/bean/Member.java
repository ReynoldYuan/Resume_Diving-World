package tw.com.eeit.divingworld.model.bean;

import java.util.List;

public class Member {

	private int mem_id;
	private String mem_account;
	private String mem_password;
	private String mem_level;
	private String mem_name;
	private String mem_photo;
	private String mPhotoBase64;
	private List<Lesson> lessons;
	
	public Member() {
	}
	
	public Member(int mem_id) {
		this.mem_id = mem_id;
	}
		
	
	public int getMem_id() {
		return mem_id;
	}
	public void setMem_id(int mem_id) {
		this.mem_id = mem_id;
	}
	public String getMem_account() {
		return mem_account;
	}
	public void setMem_account(String mem_account) {
		this.mem_account = mem_account;
	}
	public String getMem_password() {
		return mem_password;
	}
	public void setMem_password(String mem_password) {
		this.mem_password = mem_password;
	}
	public String getMem_level() {
		return mem_level;
	}
	public void setMem_level(String mem_level) {
		this.mem_level = mem_level;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public String getMem_photo() {
		return mem_photo;
	}
	public void setMem_photo(String mem_photo) {
		this.mem_photo = mem_photo;
		
//		String base64String = Base64.getEncoder().encodeToString(mem_photo);
//		this.mPhotoBase64 = "data:image/jpeg;base64,"+base64String;
	}

	@Override
	public String toString() {
		return "Member [mem_id=" + mem_id + ", mem_account=" + mem_account + ", mem_password=" + mem_password
				+ ", mem_level=" + mem_level + ", mem_name=" + mem_name + ", mem_photo=" + mem_photo
				+ "]";
	}

	public String getpPhotoBase64() {
		return mPhotoBase64;
	}

	public void setpPhotoBase64(String mPhotoBase64) {
		this.mPhotoBase64 = mPhotoBase64;
	}

	public List<Lesson> getLessons() {
		return lessons;
	}

	public void setLessons(List<Lesson> lessons) {
		this.lessons = lessons;
	}
	
}
