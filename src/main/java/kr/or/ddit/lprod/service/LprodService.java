package kr.or.ddit.lprod.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.lprod.dao.IlprodDao;
import kr.or.ddit.lprod.dao.LprodDao;
import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.paging.model.PageVo;

public class LprodService implements IlprodService{
	
	private IlprodDao lprodDao;
	
	public LprodService() {
		lprodDao = new LprodDao();
	}
	
	@Override
	public List<LprodVo> lprodList() {
		return lprodDao.lprodList();
	}

	@Override
	public Map<String, Object> lprodPagingList(PageVo pageVo) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("lprodList", lprodDao.lprodPagingList(pageVo));
		
		int lprodCnt = lprodDao.lprodCnt();
		
		int pagenationSize = (int) Math.ceil((double)lprodCnt/pageVo.getPageSize());
		resultMap.put("paginationSize", pagenationSize);
		
		return resultMap;
	}

}
