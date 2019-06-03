package kr.or.ddit.login.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.IuserService;
import kr.or.ddit.user.service.UserService;

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
	
	private IuserService userService;
	
	@Override
	public void init() throws ServletException {
		userService = new UserService();
	}
       
	// 사용자 로그인 화면 요청 처리
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//logger.debug("LoginController doGet()"); // 정상적으로 이 메서드가 실행되는가? 까지 확인하는 부분
		
		if(request.getCookies() != null){
			for(Cookie cookie : request.getCookies()){
				logger.debug("cookie : {}, {}", cookie.getName(), cookie.getValue());
			}
		}
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
		//logger.debug("parameter rememberme: {}", request.getParameter("rememberme"));
		//logger.debug("parameter userId : {}", request.getParameter("userId")); // 파라미터가 정상적으로 들어오는지 확인
		//logger.debug("parameter password : {}", request.getParameter("password"));
		
		// 사용자 파라미터 userId, password
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		String encryptPassword = KISA_SHA256.encrypt(password);
		
		// db에서 해당 사용자의 정보조회 (service객체, dao객체)
		UserVo userVo = userService.getUser(userId);
		
		// id로 db에서 검색한 해당 사용자 정보를 이용하여 password가 일치하는지 검사
		if(userVo!=null && encryptPassword.equals(userVo.getPass())){
		// 일치하면? 로그인 성공 : main 화면으로 이동
			
			// 로그인 성공시 rememberme 파라미터가 존재할 경우 userId, rememberme cookie를 설정해 준다
			// rememberme 파라미터가 존재하지 않을 경우 userId, rememberme cookie를 삭제한다
			int cookieMaxAge = 0;
			if(request.getParameter("rememberme") != null)
				cookieMaxAge = 60*60*24*30;
				
			Cookie userIdCookie = new Cookie("userId", userId);
			userIdCookie.setMaxAge(cookieMaxAge); // expires에 30일을 지정
			
			Cookie rememberMeCookie = new Cookie("rememberme", "true");
			rememberMeCookie.setMaxAge(cookieMaxAge);
			
			response.addCookie(userIdCookie);
			response.addCookie(rememberMeCookie); // response에 cookie를 담아준다
			
			for(Cookie cookie : request.getCookies()){ // 조회 안된다. 왜? 요청할 때 클라이언트(브라우저)가 쿠키를 보낸다. 그것을 바로 불러와도 조회되지 않는다
				logger.debug("cookie : {}, {}", cookie.getName(), cookie.getValue());
			}
				
			// session에 사용자 정보를 넣어준다(사용빈도가 높기 때문에)
			HttpSession session = request.getSession();
			session.setAttribute("USER_INFO", userVo); // 세션 이름에는 보통 대문자를 사용한다
			
			RequestDispatcher rd = request.getRequestDispatcher("/main.jsp");
			rd.forward(request, response);
			
		} else { // 불일치하면? 아이디or비밀번호를 잘못 입력 : login 화면으로 이동
			// login 화면으로 이동: localhost/jsp/login
			// 현 상황에서 /jsp/login url로 dispatch 방식으로 위임 불가.
			// request.getMethod();의 리턴값은 get or post
			
			request.getRequestDispatcher("/login/login.jsp").forward(request, response); // el 테스트를 위해 방식 변경 
			//response.sendRedirect(request.getContextPath() + "/login");
		}
	}

}
