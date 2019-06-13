package kr.or.ddit.prod.dao;

import java.util.List;

import kr.or.ddit.paging.model.PageVo;
import kr.or.ddit.prod.model.ProdVo;

public interface IProdDao {
	
	List<ProdVo> prodPagingList(PageVo pageVo);
	
	int prodCnt();
	
	/**
	 * 
	* Method : prodList
	* 작성자 : PC10
	* 변경이력 :
	* @param prod_lgu
	* @return
	* Method 설명 : 상품분류코드에 해당하는 상품목록을 반환하는 메서드
	 */
	List<ProdVo> prodList(String prod_lgu);
}
