package kr.or.ddit.lprod.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.paging.model.PageVo;

public interface IlprodDao {
	
	List<LprodVo> lprodList();
	
	List<LprodVo> lprodPagingList(PageVo pageVo);
	
	int lprodCnt();
}
