package kr.or.ddit.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

// contextPath => js, css 경로 설정시
// ${cp}/js/jquery.js 처럼 매번 작성함

// application cp속성에 contextPath값을 넣어주면
// ${cp}/js/jquery.js 처럼 사용할 수 있다

public class ApplicationListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext application = sce.getServletContext();
		application.setAttribute("cp", application.getContextPath());
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}

}
