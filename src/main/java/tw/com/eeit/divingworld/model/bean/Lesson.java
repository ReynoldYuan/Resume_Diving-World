package tw.com.eeit.divingworld.model.bean;

import java.util.Base64;

public class Lesson {
	private int lesson_id;
	private String lesson_name;
	private String lesson_location;
	private String lesson_photo;
	private Member member;
	private int mem_id;
	private String lPhotoBase64;
	private byte[] uploadLessonPhoto;
	
	public Lesson() {
	}
	
	public Lesson(int lesson_id,String lesson_name, String lesson_location, String lesson_photo) {
		this.lesson_id = lesson_id;
		this.lesson_name = lesson_name;
		this.lesson_location = lesson_location;
		this.lesson_photo = lesson_photo;
	}
	
	public Lesson(int lesson_id,String lesson_name, String lesson_location, String lesson_photo, Member member) {
		this.lesson_id = lesson_id;
		this.lesson_name = lesson_name;
		this.lesson_location = lesson_location;
		this.lesson_photo = lesson_photo;
		this.member = member;
	}
	
	public Lesson(int lesson_id) {
		super();
		this.lesson_id = lesson_id;
	}
	
	public int getLesson_id() {
		return lesson_id;
	}
	public void setLesson_id(int lesson_id) {
		this.lesson_id = lesson_id;
	}
	public String getLesson_name() {
		return lesson_name;
	}
	public void setLesson_name(String lesson_name) {
		this.lesson_name = lesson_name;
	}
	public String getLesson_location() {
		return lesson_location;
	}
	public void setLesson_location(String lesson_location) {
		this.lesson_location = lesson_location;
	}
	public String getLesson_photo() {
		return lesson_photo;
	}
	public void setLesson_photo(String lesson_photo) {
		this.lesson_photo = lesson_photo;
		
	}
	
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public String getlPhotoBase64() {
		return lPhotoBase64;
	}

	public void setlPhotoBase64(String lPhotoBase64) {
		this.lPhotoBase64 = lPhotoBase64;
	}
	
	@Override
	public String toString() {
		return "Lesson [lesson_id=" + lesson_id + ", lesson_name=" + lesson_name + ", lesson_location="
				+ lesson_location + ", lesson_photo=" + lesson_photo + ", member=" + member
				+ ", pPhotoBase64=" + lPhotoBase64 + "]";
	}

	public int getMem_id() {
		return mem_id;
	}

	public void setMem_id(int mem_id) {
		this.mem_id = mem_id;
	}

	public byte[] getUploadlessonPhoto() {
		return uploadLessonPhoto;
	}

	public void setUploadlessonPhoto(byte[] uploadlessonPhoto) {
		this.uploadLessonPhoto = uploadlessonPhoto;
		
		String base64String = Base64.getEncoder().encodeToString(uploadlessonPhoto);
		this.lPhotoBase64 = "data:image/png;base64,"+base64String;
		
	}
	
}
