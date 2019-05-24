package kr.or.ddit.user.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.user.model.UserVo;

public interface IuserDao {
	
	/**
	 * 
	* Method : userList
	* 작성자 : PC10
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 전체 조회
	 */
	public List<UserVo> userList();
	
	/**
	 * 
	* Method : getUser
	* 작성자 : PC10
	* 변경이력 :
	* @param userId
	* @return
	* Method 설명 : 사용자 정보 조회
	 */
	public UserVo getUser(String userId);
}
