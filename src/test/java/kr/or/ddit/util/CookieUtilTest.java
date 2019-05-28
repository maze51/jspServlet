package kr.or.ddit.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class CookieUtilTest {
	
	/**
	 * 
	* Method : getCookieTest
	* 작성자 : PC10
	* 변경이력 :
	* Method 설명 : getCookie 테스트
	 */
	@Test
	public void getCookieTest() {
		/***Given***/
		String cookieString = "rememberme=true; userId=brown; test=testValue"; // parsing하려는 문자열
		CookieUtil.setCookieString(cookieString);

		/***When***/
		//문자열 String에서 내가 원하는 쿠키 이름을 줬을 때 해당 쿠키값을 가져오기
		String cookieValue = CookieUtil.getCookie("userId");
		String cookieValue1 = CookieUtil.getCookie("rememberme");
		String cookieValue2 = CookieUtil.getCookie("test");
		String cookieValue3 = CookieUtil.getCookie("test123123");
		String cookieValue4 = CookieUtil.getCookie("userI");

		/***Then***/
		assertEquals("brown", cookieValue);
		assertEquals("true", cookieValue1);
		assertEquals("testValue", cookieValue2);
		assertEquals("", cookieValue3);
		assertEquals("", cookieValue4);
		
	}

}
