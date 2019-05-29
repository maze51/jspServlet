package kr.or.ddit.prod.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.paging.model.PageVo;
import kr.or.ddit.prod.model.ProdVo;

public interface IProdService {
	
	/**
	 * 
	* Method : prodPagingList
	* 작성자 : PC10
	* 변경이력 :
	* @param pageVo
	* @return
	* Method 설명 : Paging 처리된 Prod 리스트와 PaginationSize를 반환하는 메서드
	 */
	Map<String, Object> prodPagingList(PageVo pageVo);
	
}
