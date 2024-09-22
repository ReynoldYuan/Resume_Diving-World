package tw.com.eeit.divingworld.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.com.eeit.divingworld.model.bean.Member;
import tw.com.eeit.divingworld.service.Service;

@WebServlet("/login.do")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String account = request.getParameter("mem_account");
		String password = request.getParameter("mem_password");
		
		
		Service Service = new Service();
		
		Member member = Service.login(account, password);
		
		if(member == null) {
			// 登入失敗-->導回登入頁面
			request.getSession().setAttribute("message", "登入失敗!");
			response.sendRedirect("login");
			return;
		}
		
		if(member != null) {
			// 登入成功-->存取資料 並導向首頁
			request.getSession().setAttribute("loggedInMember", member);
			response.sendRedirect("index");
		
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
