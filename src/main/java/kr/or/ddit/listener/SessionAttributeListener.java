package kr.or.ddit.listener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import kr.or.ddit.user.model.UserVo;

public class SessionAttributeListener implements HttpSessionAttributeListener{
	
	private Map<String, UserVo> sessionUserMap = new HashMap<String, UserVo>();
	
	// 세션에 속성이 추가될 때 발생하는 이벤트
	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		event.getSession().getServletContext().setAttribute("sessionUserMap", sessionUserMap); // application에 넣는다
		
		// event.getName() : 속성명
		if("USER_INFO".equals(event.getName()))
			sessionUserMap.put(event.getSession().getId(), (UserVo)event.getValue());
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		
		if("USER_INFO".equals(event.getName()))
			sessionUserMap.remove(event.getSession().getId()); // 없애준다
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		
	}

}
