package kr.or.ddit.core.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.controller.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// localhost/userList.do ==> init() ==> service() ==> doXXX() 단계를 톰캣이 자동으로 호출한다
@WebServlet("*.do") // .do로 끝나는 요청에 대해 프론트 컨트롤러가 응답을 처리한다
public class FrontController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory
			.getLogger(FrontController.class);
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		logger.debug("service : {}", req.getRequestURI());
		
		String uri = req.getRequestURI();
		
		// 다형성을 활용해 아래 문제 해결 : 어떤 uri를 어떤 컨트롤러에서 처리한다.
		// db에 그 상관관계를 담아 불러와서 처리하면 간편.
		Controller controller = RequestMapping.getController(uri);
		String viewName = controller.execute(req, resp);
		
		ViewResolver.viewResolve(viewName, req, resp); // 별도 메서드로 대체
		/*
		if(uri.equals("/userList.do"))
			new UserListController().execute(req, resp);
		else if(uri.equals("/userCreate.do"))
			new UserCreateController().execute(req, resp);
		//... 개별 uri에 대한 처리를 넣으려면 else if문이 계속 늘어난다
		//새 uri가 등록되면 소스를 계속 수정해야 한다.
		*/
		
	}

	
}
