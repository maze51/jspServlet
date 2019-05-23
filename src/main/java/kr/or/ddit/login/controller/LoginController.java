package kr.or.ddit.login.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.user.model.UserVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class LoginController
 */
/**
* LoginController.java
* 로그인 처리 컨트롤러
*
* @author PC10
* @version 1.0
* @see
*
* <pre>
* << 개정이력(Modification Information) >>
*
* 수정자 수정내용
* ------ ------------------------
* PC10 최초 생성
*
* </pre>
*/
// web.xml에 있던 servlet, servlet-mapping 정보를 java annotation으로 만든 것
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final Logger logger = LoggerFactory
			.getLogger(LoginController.class);
	
	private static final long serialVersionUID = 1L;
       
	// 사용자 로그인 화면 요청 처리
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("LoginController doGet()"); // 정상적으로 이 메서드가 실행되는가? 까지 확인하는 부분
		
		// login화면을 처리해 줄 누군가? 에게 위임
		// 지금은 단순 login화면을 html로 응답을 생성해주는 작업이 필요
		// /login/login.jsp로 위임 --> 서버상 별도의 상태 변경을 가하는 요청이 아니기 때문에
		//							dispatch 방식으로 위임
		
		// session에 사용자 정보가 있을 경우 --> main 화면으로 이동
		if(request.getSession().getAttribute("USER_INFO")!=null){
			request.getRequestDispatcher("/main.jsp").forward(request, response);
		} else {
			// session에 사용자 정보가 없을 경우 --> 기존 로직
			request.getRequestDispatcher("/login/login.jsp").forward(request, response);
		}
		
	}
	
	// 로그인 요청 처리
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("parameter userId : {}", request.getParameter("userId")); // 파라미터가 정상적으로 들어오는지 확인
		logger.debug("parameter password : {}", request.getParameter("password"));
		
		// 사용자 파라미터 userId, password
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		
		// db에서 해당 사용자의 정보조회 (service객체, dao객체)
		
		// 해당 사용자 정보를 이용하여 사용자가 보낸 userId, password가 일치하는지 검사
		// userId : brown이고 password : brown1234라는 값일 때 통과, 이외의 값은 불일치
		
		// 일치하면? 로그인 성공 : main 화면으로 이동
		if(userId.equals("brown") && password.equals("brown1234")){
			
			// session에 사용자 정보를 넣어준다(사용빈도가 높기 때문에)
			HttpSession session = request.getSession();
			session.setAttribute("USER_INFO", new UserVo("브라운", "brown", "곰")); // 세션 이름에는 보통 대문자를 사용한다
			
			RequestDispatcher rd = request.getRequestDispatcher("/main.jsp");
			rd.forward(request, response);
			
		} else { // 불일치하면? 아이디or비밀번호를 잘못 입력 : login 화면으로 이동
			// login 화면으로 이동: localhost/jsp/login
			// 현 상황에서 /jsp/login url로 dispatch 방식으로 위임 불가.
			// request.getMethod();의 리턴값은 get or post
			
			response.sendRedirect(request.getContextPath() + "/login");
		}
	}

}
