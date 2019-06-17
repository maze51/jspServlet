package kr.or.ddit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainController implements Controller{
	
	// Exception오류는 throws추가 + 인터페이스에도 throws추가해서 해결한다
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 다른 부분은 동일하고 경로만 바뀐다. => 개선 가능
		return "/main.jsp"; 
		
		// 기본 => forward
		// redirect:/main.jsp => redirect (redirect 접두어가 있을 경우)
		
		// request.getRequestDispatcher([PATH]).forward(request, response);
		// response.sendRedirect([PATH]);
	}

}
