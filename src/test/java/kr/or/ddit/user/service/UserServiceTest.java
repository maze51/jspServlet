package kr.or.ddit.user.service;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	
	/**
	 * 
	* Method : insertUserTest
	* 작성자 : PC10
	* 변경이력 :
	* Method 설명 : 사용자 등록 테스트
	 */
	@Test
	public void insertUserTest() throws ParseException{
		/***Given***/
		// 사용자 정보를 담고 있는 vo객체 준비
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		UserVo userVo = null;
		
			userVo = new UserVo("홍길동", "userTest", "길동", "userTest1234", 
					"대전광역시 중구 대흥등", "영민빌딩", "34940", sdf.parse("2019-05-31"));
		
		/***When***/
		int insertCnt = userService.insertUser(userVo);
		
		/***Then***/
		assertEquals(1, insertCnt);
		
		// data 삭제
		userService.deleteUser(userVo.getUserId());
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
