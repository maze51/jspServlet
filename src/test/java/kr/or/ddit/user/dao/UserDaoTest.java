package kr.or.ddit.user.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import kr.or.ddit.user.model.UserVo;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserDaoTest {
	
	private static final Logger logger = LoggerFactory
			.getLogger(UserDaoTest.class);
	
	// JUnit 실행순서
	// @BeforeClass가 적용된 메서드가 1회 실행
	
	// 다음 구간은 @Test가 적용된 모든 메서드에 대해 반복 적용
	// @Before가 적용된 메서드 실행
	// @Test가 적용된 메서드 실행
	// @After가 적용된 메서드 실행
	
	// @AfterClass가 적용된 메서드가 1회 실행
	
	@BeforeClass
	public static void beforeClass(){ // 잘 안쓴다
		logger.debug("beforeClass");
	}
	
	private IuserDao userDao;
	
	@Before
	public void setup(){ // 많이 쓰인다
		userDao = new UserDao();
		logger.debug("setup");
	}
	
	@After
	public void teardown(){ // 많이 쓰인다
		logger.debug("teardown");
	}
	
	@AfterClass
	public static void afterClass(){
		logger.debug("afterClass");
	}
	
	/**
	 * 
	* Method : userListTest
	* 작성자 : PC10
	* 변경이력 :
	* Method 설명 : 사용자 전체 조회 테스트
	 */
	@Test
	public void userListTest() {
		/***Given***/

		/***When***/
		List<UserVo> userList = userDao.userList();
		
		/***Then***/
		assertEquals("brown", userList.get(0).getUserId());
		assertEquals(105, userList.size());
		logger.debug("userList : {}", userList);
	}
	
	/**
	 * 
	* Method : getUserTest
	* 작성자 : PC10
	* 변경이력 :
	* Method 설명 : 사용자 조회 테스트
	 */
	@Test
	public void getUserTest(){
		/***Given***/
		String userId = "brown";

		/***When***/
		UserVo userVo = userDao.getUser(userId);

		/***Then***/
		assertEquals("브라운", userVo.getName());
		logger.debug("userVo : {}", userVo );

	}
	
	// 사용자 페이징 리스트 조회
	
	// 페이징 처리시 고려사항
	// 몇번째 페이지 조회인지? 페이지당 몇건씩 데이터를 보여줄건지? : 쿼리 실행 파라미터와 관련된 것
	// 정렬순서? (기본적으로 정렬에 대한 순서가 필요하다. 정렬된 것을 끊어서 보여주기 때문에) : 로직 --> 파라미터화 시킬 수 있다
	// --> 우리는 사용자 아이디 순으로 정렬한다.
}
