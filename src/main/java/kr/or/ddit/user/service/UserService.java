package kr.or.ddit.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
import kr.or.ddit.mybatis.MyBatisUtil;
import kr.or.ddit.paging.model.PageVo;
import kr.or.ddit.user.dao.IuserDao;
import kr.or.ddit.user.dao.UserDao;
import kr.or.ddit.user.model.UserVo;

public class UserService implements IuserService {
	
	private static final Logger logger = LoggerFactory
			.getLogger(UserService.class);
	
	private IuserDao userDao;
	
	public UserService(){
		userDao = new UserDao();
	}
	
	@Override
	public List<UserVo> userList() {
		return userDao.userList();
	}

	@Override
	public UserVo getUser(String id) {
		return userDao.getUser(id);
	}

	@Override
	public Map<String, Object> userPagingList(PageVo pageVo) {
		// 두 변수를 return할 수는 없다. 그럼 다른 방법은?
		// 1. List<UserVo>, userCnt를 필드로 하는 vo
		// 2. List<Object> resultList = new ArrayList<Object>();
		//		resultList.add(usersList);
		//		resultList.add(usersCnt);
		// 3. Map<String, Object> resultMap = new HashMap<String, Object>();
		//		resultMap.put("userList", userList);
		//		resultMap.put("usersCnt", userCnt);
		
		// 단, 두번째 방법은 첫번째 인덱스 값이 뭔지? 두번째는 뭔지? 정확히 알아야 한다. 그래서 권장하지 않음
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("userList", userDao.userPagingList(pageVo));
//		resultMap.put("usersCnt", userDao.usersCnt());
		
		// usersCnt 대신 paginationSize를 여기서 계산해서 변경해 주기(개선작업)
		int usersCnt = userDao.usersCnt();
		// pageSize는 pageVo.getPageSize()활용
		int paginationSize = (int)Math.ceil((double)usersCnt/pageVo.getPageSize());
		resultMap.put("paginationSize", paginationSize);
		
		return resultMap;
	}

	@Override
	public int insertUser(UserVo userVo) {
		return userDao.insertUser(userVo);
	}

	@Override
	public int deleteUser(String userId) {
		return userDao.deleteUser(userId);
	}

	@Override
	public int updateUser(UserVo userVo) {
		return userDao.updateUser(userVo);
	}
	
	/**
	 * 
	* Method : encryptPassAllUser
	* 작성자 : PC10
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 비밀번호 암호화 일괄 적용 배치
	* 			 : 재적용 금지!
	 */
	@Override
	public int encryptPassAllUser() {
		
		/* 재사용 금지! */
		if(1==1) return 0; 
		
		// 0. sql실행에 필요한 sqlSession 객체 생성
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		
		// 1. 모든 사용자 정보 조회(단, 기존 암호화 적용 사용자 제외)
		List<UserVo> userList = userDao.userListForPassEncrypt(sqlSession);
		
		// 2. 조회된 사용자의 비밀번호를 암호화 적용 후 사용자 업데이트
		int updateCntSum = 0;
		for(UserVo userVo : userList){
			String encryptPass = KISA_SHA256.encrypt(userVo.getPass());
			userVo.setPass(encryptPass);
			
			int updateCnt = userDao.updateUserEncryptPass(sqlSession, userVo);
			updateCntSum += updateCnt;
			
			// 비정상 update
			if(updateCnt != 1){
				sqlSession.rollback();
				break;
			}
		}
		// 3. sqlSession객체를 commit, close
		sqlSession.commit();
		sqlSession.close();
		
		return updateCntSum;
	}
	
	public static void main(String[] args){
		IuserService userService = new UserService();
		int updateCnt = userService.encryptPassAllUser();
		logger.debug("updateCnt : {}", updateCnt);
	}

}
