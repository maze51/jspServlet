package kr.or.ddit.user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.user.service.IuserService;
import kr.or.ddit.user.service.UserService;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/userList")
public class UserListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private IuserService userService;
	
	@Override
	public void init() throws ServletException {
		userService = new UserService(); // userService에 데이터를 넘김
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// userList객체를 userList.jsp에서 내용 출력 시 참고할 수 있도록 request객체에 속성으로 넣어준다
		request.setAttribute("userList", userService.userList()); // 속성명은 자유지정 가능. 보통은 알기 쉽게 객체명으로
		
		// userList객체를 이용하여 사용자 화면을 생성하는 jsp
		request.getRequestDispatcher("/user/userList.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
