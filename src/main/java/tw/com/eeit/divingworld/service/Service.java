package tw.com.eeit.divingworld.service;

import java.sql.Connection;
import java.util.List;

import tw.com.eeit.divingworld.model.bean.Member;
import tw.com.eeit.divingworld.model.bean.Lesson;

import tw.com.eeit.divingworld.model.dao.MemberDAO;
import tw.com.eeit.divingworld.model.dao.LessonDAO;
import tw.com.eeit.divingworld.util.ConnectionFactory;

public class Service {

	public List<Lesson> findAllLesson() {

		try (Connection conn = ConnectionFactory.getConnection()) {

			LessonDAO lessonDAO = new LessonDAO(conn);

			List<Lesson> lessons = lessonDAO.findAllLessonWithMember();

			return lessons;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	public Member login(String email, String password) {

		try (Connection conn = ConnectionFactory.getConnection()) {

			MemberDAO memberDAO = new MemberDAO(conn);
			Member member = memberDAO.findMemberByAccountAndPassword(email, password);

			return member;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public Lesson insertLesson(Lesson l) {
		try (Connection conn = ConnectionFactory.getConnection()) {

			LessonDAO LessonDAO = new LessonDAO(conn);
			LessonDAO.insertLesson(l);

			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return l;

	}
	
	public Lesson deleteLessonByID(int lessonId) {
		try (Connection conn = ConnectionFactory.getConnection()) {

			LessonDAO LessonDAO = new LessonDAO(conn);
			LessonDAO.deleteLessonByID(lessonId);

			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public Lesson updateLesson(Lesson l) {
		try (Connection conn = ConnectionFactory.getConnection()) {

			LessonDAO LessonDAO = new LessonDAO(conn);
			LessonDAO.updateLesson(l);

			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public Lesson findLessonByID(int lessonId) {
		try (Connection conn = ConnectionFactory.getConnection()) {

			LessonDAO LessonDAO = new LessonDAO(conn);
			Lesson lesson = LessonDAO.findLessonByID(lessonId);

			conn.close();
			
			return lesson;

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}

}
