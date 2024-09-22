package tw.com.eeit.divingworld.controller.page;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.com.eeit.divingworld.model.bean.Lesson;
import tw.com.eeit.divingworld.service.Service;
import tw.com.eeit.divingworld.util.PathConverter;
@MultipartConfig
@WebServlet("/update_lesson")
public class ToUpdateLesson extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Integer lessonId = Integer.valueOf(request.getParameter("id"));
		Service service = new Service();
		Lesson lesson = service.findLessonByID(lessonId);

		request.setAttribute("lesson", lesson);
		
		request.getRequestDispatcher(PathConverter.convertToWebInfPath(request.getServletPath()))
		.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
