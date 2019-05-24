package kr.or.ddit.user.dao;

import java.util.List;

import kr.or.ddit.mybatis.MyBatisUtil;
import kr.or.ddit.user.model.UserVo;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserDao implements IuserDao{
	private static final Logger logger = LoggerFactory
			.getLogger(UserDao.class);
	
//	private static UserDao dao;
//	
//	public static UserDao getInstance(){
//		if(dao==null) dao = new UserDao();
//		return dao;
//	}
	
	public static void main(String[] args) {
		
		/***Given***/
		IuserDao userDao = new UserDao();

		/***When***/
		List<UserVo> userList = userDao.userList();
		
		/***Then***/
		logger.debug("userList : {}", userList);
		
		
		/***Given***/
		String userId = "cony";
		
		/***When***/
		UserVo user = userDao.getUser(userId);
		
		/***Then***/
		logger.debug("user : {}", user);
	}
	
	
	/**
	 * 
	* Method : userList
	* 작성자 : PC10
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 전체 조회
	 */
	@Override
	public List<UserVo> userList() {
		
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		List<UserVo> userList = sqlSession.selectList("user.userList");
		return userList;
	}

	/**
	 * 
	* Method : getUser
	* 작성자 : PC10
	* 변경이력 :
	* @param userId
	* @return
	* Method 설명 : 사용자 정보 조회
	 */
	@Override
	public UserVo getUser(String userId) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		UserVo user = sqlSession.selectOne("user.getUser", userId);
		return user;
	}
}
