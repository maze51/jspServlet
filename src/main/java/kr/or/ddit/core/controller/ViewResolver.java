package kr.or.ddit.core.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ViewResolver {
	
	private static final Logger logger = LoggerFactory
			.getLogger(ViewResolver.class);
	
	public static void viewResolve(String viewName, HttpServletRequest request, 
								HttpServletResponse response) throws IOException, ServletException{
		// viewName이 'redirect:'으로 시작할 경우 redirect
		// 그 외에는 forward
		
		// 예) redirect:/main.jsp이 viewName이라면 /와 그 뒷부분만 남긴다
		logger.debug("ViewResolver.viewResolve : {}", viewName);
		if(viewName.startsWith("redirect:"))
			response.sendRedirect(viewName.substring("redirect:".length()));
		else
			request.getRequestDispatcher(viewName).forward(request, response);
		
		
		
	}
	
}
