package tw.com.eeit.divingworld.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tw.com.eeit.divingworld.model.bean.Member;
import tw.com.eeit.divingworld.model.bean.Lesson;

public class LessonDAO {

	private Connection conn;

	
	public LessonDAO(Connection conn) {
		this.conn = conn;
	}

	
	public Lesson findLessonByID(int lessonId) throws SQLException {
		final String SQL = "SELECT * FROM [DivingWorld].[dbo].[lesson] WHERE [lesson_id] = ?";
		PreparedStatement preState = conn.prepareStatement(SQL);
		preState.setInt(1, lessonId);
		ResultSet rs = preState.executeQuery();

		if (rs.next()) {
			Lesson l = new Lesson();
			l.setLesson_id(rs.getInt("lesson_id"));
			l.setLesson_name(rs.getString("lesson_name"));
			l.setLesson_location(rs.getString("lesson_location"));
			l.setLesson_photo(rs.getString("lesson_photo"));
			l.setMem_id(rs.getInt("mem_id"));
			return l;
		}

		preState.close();

		return null;
	}
	public void updateLesson(Lesson l) throws SQLException {
		final String SQL;
	
		if (l.getLesson_photo() != null) {
			SQL = "UPDATE [DivingWorld].[dbo].[lesson] SET [lesson_name] = ?, [lesson_location] = ?, [lesson_photo] = ?, [mem_id] = ? WHERE [lesson_id] = ?";
		}
		else
		{
			SQL = "UPDATE [DivingWorld].[dbo].[lesson] SET [lesson_name] = ?, [lesson_location] = ?,[mem_id] = ? WHERE [lesson_id] = ?";
		}
		
		PreparedStatement preState = conn.prepareStatement(SQL);
		preState.setString(1, l.getLesson_name());
		preState.setString(2, l.getLesson_location());
		
		if (l.getLesson_photo() != null) {
		preState.setString(3, l.getLesson_photo());
		preState.setInt(4, l.getMem_id());
		preState.setInt(5, l.getLesson_id());
		}
		else
		{
		preState.setInt(3, l.getMem_id());
		preState.setInt(4, l.getLesson_id());
		}

		preState.executeUpdate();
		preState.close();
	}

	
	
	public List<Lesson> findAllLessonWithMember() throws SQLException {
		final String SQL = "SELECT * FROM [DivingWorld].[dbo].[lesson] AS [l]"
				+ " LEFT JOIN [DivingWorld].[dbo].[member] AS [m]" + " ON [l].[mem_id] = [m].[mem_id]";

		PreparedStatement preState = conn.prepareStatement(SQL);
		ResultSet rs = preState.executeQuery();

		List<Lesson> lList = new ArrayList<Lesson>();

		while (rs.next()) {
			Lesson l = new Lesson();
			l.setLesson_id(rs.getInt("lesson_id"));
			l.setLesson_name(rs.getString("lesson_name"));
			l.setLesson_location(rs.getString("lesson_location"));
			l.setLesson_photo(rs.getString("lesson_photo"));

			Member m = new Member();
			m.setMem_id(rs.getInt("mem_id"));
			m.setMem_account(rs.getString("mem_account"));
			m.setMem_password(rs.getString("mem_password"));
			m.setMem_level(rs.getString("mem_level"));
			m.setMem_name(rs.getString("mem_name"));
			m.setMem_photo(rs.getString("mem_photo"));

			l.setMember(m);

			lList.add(l);
		}

		rs.close();
		preState.close();

		return lList;

	}

	
	public void insertLesson(Lesson l) throws SQLException {
		final String SQL = "INSERT INTO [DivingWorld].[dbo].[lesson]([lesson_name], [lesson_location], [lesson_photo], [mem_id]) VALUES (?, ?, ?, ?)";

		PreparedStatement preState = conn.prepareStatement(SQL);
		preState.setString(1, l.getLesson_name());
		preState.setString(2, l.getLesson_location());
		preState.setString(3, l.getLesson_photo());
		preState.setInt(4, l.getMem_id());

		preState.execute();
		preState.close();
	}

	
	public void deleteLessonByID(int lessonId) throws SQLException {
		final String SQL = "DELETE FROM [DivingWorld].[dbo].[lesson] WHERE [lesson_id] = ?";

		PreparedStatement preState = conn.prepareStatement(SQL);
		preState.setInt(1, lessonId);
		preState.execute();
		preState.close();
	}

}
