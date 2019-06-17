package kr.or.ddit.core.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.controller.Controller;
import kr.or.ddit.db.model.DbVo;
import kr.or.ddit.db.service.DbService;
import kr.or.ddit.db.service.IDbService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestMapping {
	
	private static final Logger logger = LoggerFactory
			.getLogger(RequestMapping.class);
	
	// /main.do => MainController
	// /userList.do => UserListController
	// 이런 식의 Mapping정보는 db에서 조회해 왔다고 가정
	
	// 하지만 db에 클래스 자체(new MainController())를 넣을 수는 없다(대신 클래스명 전체를 넣어준다)
	
	//private static Map<String, String> requestMappingClass;
	
	// Mapping정보를 담을 Map객체
	private static Map<String, Controller> requestMapping;
	
	private static IDbService dbService = new DbService(); // 일단 이렇게 처리한다
	
	static{
		List<DbVo> urlClassMappingList = dbService.mappingList(); // Map으로 된 하드코딩을 List로 바꾼다
		
		//Map<String, String> requestMappingClass = new HashMap<String, String>();
		//requestMappingClass.put("/main.do", "kr.or.ddit.controller.MainController"); // 실제 db에는 이렇게 들어가 있다. 이를 바탕으로 인스턴스 만들기
		requestMapping = new HashMap<String, Controller>();
		// 위 Map에 채우기
		
		for(DbVo dbVo : urlClassMappingList){
			String classInfo = dbVo.getClassname();
			// classInfo = kr.or.ddit.controller.MainController // 클래스 전체 이름이 담겨있다
			try {
				Class clazz = Class.forName(classInfo); // 인스턴스 생성
				Object obj = clazz.newInstance();
				requestMapping.put(dbVo.getUri(), (Controller)obj);
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
		// main.do로 요청했을 때 MainController로 main.jsp를 띄운다
		// 아래처럼 넣었던 정보를 for문으로 보다 간편하게 넣는다
		/*
		requestMapping.put("/main.do", new MainController());
		requestMapping.put("/userList.do", new UserListController());
		*/
	}
	
	public static Controller getController(String uri) {
		logger.debug("getController : {}", uri);
		
		// db에 있는 uri와 컨트롤러 매핑정보를 읽어 반환해 준다
		return requestMapping.get(uri);
	}

}
