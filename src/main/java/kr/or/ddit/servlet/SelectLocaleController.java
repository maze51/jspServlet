package kr.or.ddit.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/selectLocale")
public class SelectLocaleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static final Logger logger = LoggerFactory
			.getLogger(SelectLocaleController.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String locale = request.getParameter("locale");
		//if(locale==null)
		//	locale = "ko";
		
		locale = locale == null ? "ko" : locale; // 한줄로
		
		request.setAttribute("locale", locale);
		request.getRequestDispatcher("/jstl/selectLocale.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String locale = request.getParameter("locale");
		logger.debug("locale : {}", locale);
		
		response.sendRedirect(request.getContextPath()+"/selectLocale?locale=" + locale);
	}

}
