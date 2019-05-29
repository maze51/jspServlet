package kr.or.ddit.lprod.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.paging.model.PageVo;

public interface IlprodService {
	
	List<LprodVo> lprodList();
	
	Map<String, Object> lprodPagingList(PageVo pageVo);
}
