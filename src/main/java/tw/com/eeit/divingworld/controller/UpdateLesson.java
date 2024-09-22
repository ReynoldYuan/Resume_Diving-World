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
@WebServlet("/UpdateLesson.do")
public class UpdateLesson extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		Integer lessonId = Integer.valueOf(request.getParameter("id"));
		Service service = new Service();
		Lesson lesson = service.findLessonByID(lessonId);
		
		
		//response.getWriter().write(lessons.toString());
		
		request.setAttribute("lesson", lesson);
		
		request.getRequestDispatcher("/update_lesson.jsp").forward(request, response);
		
	}
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");

		Integer lessonId = Integer.valueOf(request.getParameter("id"));
		String lessonName = request.getParameter("lesson_name");
        String lessonLocation = request.getParameter("lesson_location");
		Part lessonPhotoPart = request.getPart("lesson_photo");
        Integer lessonMemId = Integer.parseInt(request.getParameter("mem_id"));
        
        String uploadPhoto = null;
        if (lessonPhotoPart != null && lessonPhotoPart.getSize() > 0) {
            InputStream in = lessonPhotoPart.getInputStream();
            byte[] lessonPhoto2 = in.readAllBytes();
            uploadPhoto = "data:image/png;base64," + Base64.getEncoder().encodeToString(lessonPhoto2);
            in.close();
        }

        Lesson l = new Lesson();
        l.setLesson_id(lessonId);
        l.setLesson_name(lessonName);
        l.setLesson_location(lessonLocation);
        if (uploadPhoto != null) {
            l.setLesson_photo(uploadPhoto);
        }
        l.setMem_id(lessonMemId);
        

		Service service = new Service();
		service.updateLesson(l);
		
		response.sendRedirect("/DivingWorld/lessons");
        
		}


}
