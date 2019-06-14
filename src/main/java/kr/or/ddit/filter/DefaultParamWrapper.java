package kr.or.ddit.filter;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class DefaultParamWrapper extends HttpServletRequestWrapper {

	private Map<String, String[]> paramMap;
	
	public DefaultParamWrapper(HttpServletRequest request) {
		super(request);
		paramMap = new HashMap<String, String[]>(request.getParameterMap()); // Map객체를 바탕으로 새로운 Map객체 생성(복사)
		
	}
	
	// 메서드 4개 재정의(새로 만든 Map을 이용하도록)
	
	@Override
	public String getParameter(String name) {
		String[] values = paramMap.get(name);
		return values == null ? null : values[0];
	}

	@Override
	public Map<String, String[]> getParameterMap() {
		return paramMap;
	}

	@Override
	public Enumeration<String> getParameterNames() { // 표준 스펙이 enumeration이라 맞춰준다
		return Collections.enumeration(paramMap.keySet());
	}

	@Override
	public String[] getParameterValues(String name) {
		return paramMap.get(name);
	}
	
	public void setParameter(String name, String[] value){
		paramMap.put(name, value);
	}
	
}
