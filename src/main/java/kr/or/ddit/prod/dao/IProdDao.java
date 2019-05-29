package kr.or.ddit.prod.dao;

import java.util.List;

import kr.or.ddit.paging.model.PageVo;
import kr.or.ddit.prod.model.ProdVo;

public interface IProdDao {
	
	List<ProdVo> prodPagingList(PageVo pageVo);
	
	int prodCnt();
}
