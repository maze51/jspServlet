package kr.or.ddit.db.dao;

import java.util.List;

import kr.or.ddit.db.model.DbVo;

public interface IDbDao {
	
	/**
	 * 
	* Method : mappingList
	* 작성자 : PC10
	* 변경이력 :
	* @return
	* Method 설명 : 전체 URI Mapping 리스트 조회
	 */
	List<DbVo> mappingList();
	
}
