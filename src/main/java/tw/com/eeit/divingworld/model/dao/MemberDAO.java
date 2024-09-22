package tw.com.eeit.divingworld.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tw.com.eeit.divingworld.model.bean.Member;
import tw.com.eeit.divingworld.model.bean.Lesson;

public class MemberDAO {

	private Connection conn;

	public MemberDAO(Connection conn) {
		this.conn = conn;
	}

	/**
	 * 根據會員的ID取得指定會員的所有資料。
	 * <p>
	 * 
	 * @param MemberID 會員的ID。
	 * @return Member 會員的資料載體，裡面有會員的所有資料； 若ID不存在則回傳null。
	 */
	public Member findMemberByID(int memberID) throws SQLException {
		final String SQL = "SELECT * FROM [DivingWorld].[dbo].[member] WHERE [mem_id] = ?";
		PreparedStatement preState = conn.prepareStatement(SQL);
		preState.setInt(1, memberID);
		ResultSet rs = preState.executeQuery();

		if (rs.next()) {
			Member m = new Member();
			m.setMem_id(rs.getInt("mem_id"));
			m.setMem_account(rs.getString("mem_account"));
			m.setMem_password(rs.getString("mem_password"));
			m.setMem_level(rs.getString("mem_level"));
			m.setMem_name(rs.getString("mem_name"));
			m.setMem_photo(rs.getString("mem_photo"));
			return m;
		}

		rs.close();
		preState.close();

		return null;
	}

	

	/**
	 * 在一次查詢中，取得所有member，與其的所有pet資訊。 <br>
	 * 此方法邏輯稍微複雜，不一定要看懂。
	 * <p>
	 * 
	 * @return List<Member> 所有會員的集合，包含寵物資訊。
	 */
	public List<Member> findAllMemberWithLesson() throws SQLException {
		final String SQL = "SELECT * FROM [DivingWorld].[dbo].[member] AS [m]" + " LEFT JOIN [DivingWorld].[dbo].[lesson] AS [l]"
				+ " ON [m].[mem_id] = [l].[mem_id]";
		PreparedStatement preState = conn.prepareStatement(SQL);

		ResultSet rs = preState.executeQuery();

		List<Member> mList = new ArrayList<>();
		List<Lesson> lList = null;

		Member m = null;
		int prevMemberID = -1;

		// 所有資料抓出來做迴圈
		while (rs.next()) {

			// 取得當下那筆資料的mID
			int currentMemberID = rs.getInt("mem_id");

			// 若與上筆mID不相等，則表示是新的member，以下會建立一個新的member
			if (prevMemberID != currentMemberID) {

				// 迴圈跑到這，m還是舊狀態(上一筆member)，只要舊狀態不為null，就將上一筆member加入mList
				if (m != null) {
					m.setLessons(lList);
					mList.add(m);
				}

				// 建立當下member
				m = new Member();
				m.setMem_id(rs.getInt("mem_id"));
				m.setMem_account(rs.getString("mem_account"));
				m.setMem_password(rs.getString("mem_password"));
				m.setMem_level(rs.getString("mem_level"));
				m.setMem_name(rs.getString("mem_name"));
				m.setMem_photo(rs.getString("mem_photo"));
				lList = new ArrayList<>();
			}

			// 不論如何都要抓到pet資訊
			Lesson l = new Lesson();
			l.setLesson_id(rs.getInt("lesson_id"));
			l.setLesson_name(rs.getString("lesson_name"));
			l.setLesson_location(rs.getString("lesson_location"));
			l.setLesson_photo(rs.getString("lesson_photo"));

			lList.add(l);

			// 更新mID
			prevMemberID = currentMemberID;
		}

		rs.close();

		preState.close();
		return mList;
	}
	
	public Member findMemberByAccountAndPassword(String email, String password) throws SQLException {
		final String SQL = "SELECT * FROM [DivingWorld].[dbo].[member] WHERE [mem_account] COLLATE Latin1_General_CS_AS = ? AND [mem_password] COLLATE Latin1_General_CS_AS = ?";
		PreparedStatement preState = conn.prepareStatement(SQL);
		preState.setString(1, email);
		preState.setString(2, password);

		ResultSet rs = preState.executeQuery();

		if (rs.next()) {
			Member m = new Member();
			m.setMem_id(rs.getInt("mem_id"));
			m.setMem_account(rs.getString("mem_account"));
			m.setMem_password(rs.getString("mem_password"));
			m.setMem_level(rs.getString("mem_level"));
			m.setMem_name(rs.getString("mem_name"));
			m.setMem_photo(rs.getString("mem_photo"));

			return m;
		}

		rs.close();
		preState.close();
		return null;

	}

}
