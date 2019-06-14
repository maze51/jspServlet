package kr.or.ddit.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 접속여부를 확인해서 (세션에 USER_INFO 속성이 존재하는지)
// 접속X --> login화면으로 이동
// 접속O --> 정상적으로 최초 요청한 리소스로 위임

// 예) /user/userList --> /login(세션 정보 X)
// 예) /user/userPagingList --> /login(세션 정보 X)
// 예) /login --> (세션이 없어도 띄워줘야 하지만 안티패턴이 없다. 어떻게? 일단 다 받고, 피하고 싶은 uri정보를 받아 처리한다)

@WebFilter("/*")
public class LoginCheckFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		String uri = req.getRequestURI();
		
		// /jsp/userPagingList --> /userPagingList로 바꿔주기
		// 이렇게 하지 않고 아래 if문 내용 앞에 /jsp/를 붙여도 무방
		uri = uri.substring(req.getContextPath().length()); // ContextPath부분 제거
		
		// 세션이 없어도 처리돼야 하는 것들 : /login, /js, /css, /img(.js, .css, img확장자들)
		if(uri.startsWith("/login") || uri.startsWith("/js") || 
		   uri.startsWith("/css") || uri.startsWith("/img") || 
		   uri.startsWith("/requestCount") || uri.startsWith("/bootstrap"))
			chain.doFilter(request, response);
		// 세션을 체크해야 하는 대상들
		else if(req.getSession().getAttribute("USER_INFO") != null){
			chain.doFilter(request, response);
		}
		else{
			HttpServletResponse res = (HttpServletResponse)response;
			res.sendRedirect(req.getContextPath()+"/login");
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
