package tw.com.eeit.divingworld.controller.page;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.com.eeit.divingworld.model.bean.Lesson;
import tw.com.eeit.divingworld.service.Service;
import tw.com.eeit.divingworld.util.PathConverter;

@WebServlet("/lessons")
public class ToLessons extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Service Service = new Service();
		List<Lesson> lessons = Service.findAllLesson();
		
		request.setAttribute("lessons", lessons);
		
		request.getRequestDispatcher(PathConverter.convertToWebInfPath(request.getServletPath()))
		.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
