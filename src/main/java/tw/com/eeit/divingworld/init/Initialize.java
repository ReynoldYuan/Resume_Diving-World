package tw.com.eeit.divingworld.init;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import tw.com.eeit.divingworld.model.bean.Lesson;
import tw.com.eeit.divingworld.model.bean.Member;
import tw.com.eeit.divingworld.util.ConnectionFactory;

@WebListener
public class Initialize implements ServletContextListener {

	private String INITIALIZATION_DATA_PATH = "META-INF/initialization_data/";

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext context = sce.getServletContext();

		// 初始化
		context.setAttribute("root", context.getContextPath());
		context.setAttribute("webName", "DivingWorld");
		context.setAttribute("component", "/WEB-INF/view/component");

		// 以下為「在資料庫塞入預設值」的程式
		INITIALIZATION_DATA_PATH = context.getRealPath(INITIALIZATION_DATA_PATH);

		try (Connection conn = ConnectionFactory.getConnection()) {

			createDB(conn); // 建立資料庫
			createTableAndInsertData(conn); // 建立Member資料表並加入預設值

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void createDB(Connection conn) throws SQLException {
		// 建立DivingWorld資料庫
		String SQL = "IF DB_ID('DivingWorld') IS NULL CREATE DATABASE DivingWorld";

		Statement state = conn.createStatement();
		state.execute(SQL);
		state.close();
	}

	private void createTableAndInsertData(Connection conn) throws Exception {
		// 建立Member資料表
		String SQL = "IF OBJECT_ID('[DivingWorld].[dbo].[member]') IS NULL " + "CREATE TABLE [DivingWorld].[dbo].[member]("
				+ " [mem_id] [int] IDENTITY(1,1) PRIMARY KEY NOT NULL," + " [mem_account] [nvarchar](50) NOT NULL,"
				+ " [mem_password] [nvarchar](50) NOT NULL," + " [mem_level] [nchar](10) NOT NULL,"
				+ " [mem_name] [nvarchar](10)," + " [mem_photo] [nvarchar](max)" + ")";
		Statement state = conn.createStatement();
		state.execute(SQL);
		state.close();

		// 建立Lesson資料表
		SQL = "IF OBJECT_ID('[DivingWorld].[dbo].[lesson]') IS NULL " + "CREATE TABLE [DivingWorld].[dbo].[lesson]("
				+ " [lesson_id] [int] IDENTITY(1,1) PRIMARY KEY NOT NULL," + " [lesson_name] [varchar](50) NOT NULL,"
				+ " [lesson_location] [varchar](50)," + " [lesson_photo] [nvarchar](max),"
				+ " [mem_id] [int] FOREIGN KEY REFERENCES [DivingWorld].[dbo].[member]([mem_id])" + ")";
		state = conn.createStatement();
		state.execute(SQL);
		state.close();

		// 新增Member資料
		if (!conn.createStatement().executeQuery("SELECT [mem_id] FROM [DivingWorld].[dbo].[member]").next()) {
			
			BufferedReader br = new BufferedReader(new FileReader(INITIALIZATION_DATA_PATH + "Member.json"));
			List<Member> mList = new Gson().fromJson(br, new TypeToken<List<Member>>() {
			}.getType());
			br.close();

			SQL = "INSERT INTO [DivingWorld].[dbo].[member] ([mem_account],[mem_password],[mem_level],[mem_name],[mem_photo]) VALUES (?, ?, ?, ?, ?)";

			// 新增資料到資料表
			PreparedStatement preState = conn.prepareStatement(SQL);
			for (Member m : mList) {
				preState.setString(1, m.getMem_account());
				preState.setString(2, m.getMem_password());
				preState.setString(3, m.getMem_level());
				preState.setString(4, m.getMem_name());

				BufferedInputStream bis = new BufferedInputStream(
						new FileInputStream(INITIALIZATION_DATA_PATH + "/image/member/" + m.getMem_photo() + ".jpg"));
				String base64Photo = "data:image/png;base64," + Base64.getEncoder().encodeToString(bis.readAllBytes());
				bis.close();

				preState.setString(5, base64Photo);
				preState.addBatch();
			}
			preState.executeBatch();
			preState.close();
		}

		// 新增lesson資料
		if (!conn.createStatement().executeQuery("SELECT [lesson_id] FROM [DivingWorld].[dbo].[lesson]").next()) {
			
			BufferedReader br = new BufferedReader(new FileReader(INITIALIZATION_DATA_PATH + "Member.json"));
			List<Member> mList = new Gson().fromJson(br, new TypeToken<List<Member>>() {
			}.getType());
			br.close();

			SQL = "INSERT INTO [DivingWorld].[dbo].[lesson] ([lesson_name], [lesson_location], [lesson_photo], [mem_id]) VALUES (?, ?, ?, ?)";

			PreparedStatement preState = conn.prepareStatement(SQL);

			for (Member m : mList) {
				for (Lesson l : m.getLessons()) {
					preState.setString(1, l.getLesson_name());
					preState.setString(2, l.getLesson_location());

					BufferedInputStream bis = new BufferedInputStream(
							new FileInputStream(INITIALIZATION_DATA_PATH + "/image/lesson/" + l.getLesson_photo() + ".jpg"));
					String base64Photo = "data:image/png;base64," + Base64.getEncoder().encodeToString(bis.readAllBytes());
					bis.close();

					preState.setString(3, base64Photo);
					preState.setInt(4, m.getMem_id());
					preState.addBatch();
				}

			}
			preState.executeBatch();
			preState.close();
		}
	}

}
