package kr.or.ddit.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CookieUtil {
	
	public static String cookieString; // 분석할 쿠키 문자열
	private static final Logger logger = LoggerFactory
			.getLogger(CookieUtil.class);
	/**
	 * 
	* Method : setCookieString
	* 작성자 : PC10
	* 변경이력 :
	* @param cookieString
	* Method 설명 : 분석할 쿠키 문자열을 받는다
	 */
	public static void setCookieString(String cookieString) {
		CookieUtil.cookieString = cookieString;
	}
	
	/**
	 * 
	* Method : getCookie
	* 작성자 : PC10
	* 변경이력 :
	* @param cookie
	* @return
	* Method 설명 : 쿠키 문자열에서 특정 쿠키 값을 가져온다
	 */
	public static String getCookie(String cookie) {
		String[] cookieArray = cookieString.split("; ");
		// cookieName=cookieValue 형태이다
		//cookieArray[0] = "rememberme=true"
		//cookieArray[1] = "userId=brown"
		//cookieArray[2] = "test=testValue"
		
		String cookieValue = ""; // 반환할 값을 저장할 변수
		
		for(String str : cookieArray){
			logger.debug("str : {}", str);
//			if(str.startsWith(cookie)){ // 찾고자 하는 쿠키 이름이 맞다면
//			if(str.startsWith(cookie+"=")){ //다른 개선방법(=까지 같이 비교한다)
			if(str.substring(0, str.indexOf("=")).equals(cookie)){ // 개선
				String[] cookieStr = str.split("=");
				//cookieStr[0] = "userId"
				//cookieStr[1] = "brown"
				cookieValue = cookieStr[1];
				break;
			}
		}
		return cookieValue;
	}
	
	
//	public static String getCookie(String cookie) {
//		String cookieValue = "";
//		//매개변수에 해당하는 value값을 어떻게 뽑아낼 것인가?
//		String[] spl = cookieString.split(";");
//		
//		for (int i = 0; i < spl.length; i++) {
//			
//			String tmp = spl[i].substring(0, spl[i].indexOf("=")).trim();
//			
//			if(tmp.equals(cookie)){
//				cookieValue = spl[i].substring(spl[i].indexOf("=")+1);
//			}
//		}
//		return cookieValue;
//	}

}
