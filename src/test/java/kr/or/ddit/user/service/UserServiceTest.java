package kr.or.ddit.user.service;

import static org.junit.Assert.*;

import java.util.List;

import kr.or.ddit.user.model.UserVo;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserServiceTest {

	private static final Logger logger = LoggerFactory
	.getLogger(UserServiceTest.class);
	
	// 사용자 전체 리스트를 조회하는 메서드(를 여기서부터 제작하기)
	// 1. 메서드 인자가 필요한가? 별다른 인자가 불필요
	// 2. 리턴 타입은 뭐가 될까? List<UserVo>
	// 3. 메서드 이름은 뭐가 적당하지? userList
	
	private IuserService userService;
	
	@Before
	public void setup(){
		userService = new UserService();
	}
	
	@Test
	public void userListTest(){
		// 내가 바라는 모습을 테스트 코드로 녹여내기
		/***Given***/

		/***When***/
		List<UserVo> userList = userService.userList();
		
		/***Then***/
		assertNotNull(userList);
		assertEquals(105, userList.size());
	}
	
	@Test
	public void getUserTest(){
		/***Given***/
		String id = "brown";
		
		/***When***/
		UserVo userVo = userService.getUser(id);

		/***Then***/
		assertNotNull(userVo); // Null이 아니면 통과
		assertEquals("brown", userVo.getUserId());
		assertEquals("곰", userVo.getAlias());
		logger.debug("userVo : {}", userVo);
		
	}

//------------------------------------------------------------
//	private static final Logger logger = LoggerFactory
//			.getLogger(UserServiceTest.class);
//
//	@Test
//	public void userListTest() {
//		/***Given***/
//		IuserService userService = new UserService();
//
//		/***When***/
//		List<UserVo> userList = userService.userList();
//		
//		/***Then***/
//		assertEquals(5, userList.size());
//		logger.debug("userList : {}", userList);
//		
//	}
//	
//	@Test
//	public void getUserTest(){
//		/***Given***/
//		IuserService userService = new UserService();
//		String id = "brown";
//		
//		/***When***/
//		UserVo userVo = userService.getUser(id);
//
//		/***Then***/
//		assertNotNull(userVo); // Null이 아니면 통과
//		assertEquals("brown", userVo.getUserId());
//		assertEquals("곰", userVo.getAlias());
//		logger.debug("userVo : {}", userVo);
//		
//	}

}
