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

/**
 * Servlet implementation class MulCalculation
 */
@WebServlet("/MulCalculation")
public class MulCalculation extends HttpServlet {
	private static final Logger logger = LoggerFactory
			.getLogger(MulCalculation.class);
	
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("LoginController doPost()");
		
		int p1 = Integer.parseInt(request.getParameter("param1"));
		int p2 = Integer.parseInt(request.getParameter("param2"));
		
		int result = p1 * p2;
		
		logger.debug("결과 : " + result);
		
		HttpSession session = request.getSession();
		session.setAttribute("mulResult", result);
		
		request.getRequestDispatcher("jsp/mulResult.jsp").forward(request, response);
		
	}

}
