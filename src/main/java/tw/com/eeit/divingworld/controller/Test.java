package tw.com.eeit.divingworld.controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.com.eeit.divingworld.model.dao.MemberDAO;
import tw.com.eeit.divingworld.model.dao.LessonDAO;
import tw.com.eeit.divingworld.service.Service;
import tw.com.eeit.divingworld.util.ConnectionFactory;

@WebServlet("/Test")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try (Connection conn = ConnectionFactory.getConnection()) {
			MemberDAO mDAO = new MemberDAO(conn);
			LessonDAO lDAO = new LessonDAO(conn);

			Service mService = new Service();			

			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
