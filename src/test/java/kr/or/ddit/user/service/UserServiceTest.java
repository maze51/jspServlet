package kr.or.ddit.user.service;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import kr.or.ddit.paging.model.PageVo;
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
	
	@Test
	public void userPagingListTest(){
		/***Given***/
		PageVo pageVo = new PageVo(1, 10);
		
		/***When***/
		Map<String, Object> resultMap = userService.userPagingList(pageVo);
		List<UserVo> userList = (List<UserVo>) resultMap.get("userList");
		int paginationSize = (Integer) resultMap.get("paginationSize");
		
		/***Then***/
		// pagingList에 대한 assert
		assertNotNull(userList);
		assertEquals(10, userList.size());
		
		// paginationSize에 대한 assert
		assertEquals(11, paginationSize);
	}
	
	@Test
	public void ceilTest(){
		/***Given***/
		int usersCnt = 105;
		int pageSize = 10;

		/***When***/
		double pagenationSize = Math.ceil((double)usersCnt/pageSize);
		logger.debug("paginationSize : {}", pagenationSize);
		/***Then***/
		assertEquals(11, (int)pagenationSize);
		
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
