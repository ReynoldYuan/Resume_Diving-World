package tw.com.eeit.divingworld.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import tw.com.eeit.divingworld.model.bean.Lesson;
import tw.com.eeit.divingworld.service.Service;
@MultipartConfig
@WebServlet("/AddLesson.do")
public class AddLesson extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String lessonName = request.getParameter("lesson_name");
		String lessonLocation = request.getParameter("lesson_location");
		String lessonPhoto = request.getParameter("lesson_photo");
		Part lessonPhotoPart = request.getPart("lesson_photo");
		Integer lessonMemId = Integer.valueOf(request.getParameter("mem_id"));
		
		InputStream in = lessonPhotoPart.getInputStream();
		byte[] lessonPhoto2 = in.readAllBytes();
		String uploadPhoto ="data:image/png;base64,"+Base64.getEncoder().encodeToString(lessonPhoto2);
		in.close();
		
		Lesson l = new Lesson();
		l.setLesson_name(lessonName);
		l.setLesson_location(lessonLocation);
		l.setLesson_photo(uploadPhoto);
//		l.setUploadlessonPhoto(lessonPhoto2);
		l.setMem_id(lessonMemId);
		
		
		Service Service = new Service();
		Service.insertLesson(l);
		
		response.sendRedirect("lessons");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
