package kr.or.ddit.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter("/*")
public class RequestCountFilter implements Filter {
	// /jsp/login : 10회 요청
	// /jsp/userList : 20회 요청
	// key는 uri, value는 요청값 => Map을 사용
	
	private Map<String, Integer> requestMap; 
	
	public void init(FilterConfig fConfig) throws ServletException {
		requestMap = new HashMap<String, Integer>();
		//requestMap.put("userId", 10);
		fConfig.getServletContext().setAttribute("requestMap", requestMap); // application에 저장한다
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		// 요청이 올 때마다 uri를 체크해서 requestMap에 데이터를 한 건씩 증가시켜준다<요구사항>
		HttpServletRequest req = (HttpServletRequest)request;
		// 예) /jsp/login
		String uri = req.getRequestURI();
		int reqCount = requestMap.getOrDefault(uri, 0); // 한 번도 요청이 들어오지 않은 uri는 0으로 지정
		reqCount++;
		requestMap.put(uri, reqCount);
		
		chain.doFilter(request, response);
	}

	public void destroy() {
		// 서버 내려갈 때 처리할 부분이라면 여기에 추가한다
		// (올라간 count를 몰아서 한 번에 db에 저장하려 할 경우)
	}

}
