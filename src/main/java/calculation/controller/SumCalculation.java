package calculation.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.login.controller.LoginController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/SumCalculation")
public class SumCalculation extends HttpServlet {
	private static final Logger logger = LoggerFactory
			.getLogger(SumCalculation.class);
	
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("LoginController doPost()");
		
		int start = Integer.parseInt(request.getParameter("start"));
		int end = Integer.parseInt(request.getParameter("end"));
		int result = 0;
		for(int i=start;i<=end;i++){
			result += i;
		}
		logger.debug("결과 : " + result);
		
		HttpSession session = request.getSession();
		session.setAttribute("sumResult", result);
		
		request.getRequestDispatcher("/context_path/jsp/sumResult.jsp").forward(request, response);
	}

}
