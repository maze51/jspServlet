package kr.or.ddit.prod.service;

import java.util.HashMap;
import java.util.Map;

import kr.or.ddit.paging.model.PageVo;
import kr.or.ddit.prod.dao.IProdDao;
import kr.or.ddit.prod.dao.ProdDaoImpl;
import kr.or.ddit.prod.model.ProdVo;

public class ProdServiceImpl implements IProdService{
	
	private IProdDao prodDao;
	
	public ProdServiceImpl() {
		prodDao = new ProdDaoImpl();
	}
	
	@Override
	public Map<String, Object> prodPagingList(PageVo pageVo) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("prodList", prodDao.prodPagingList(pageVo));
		
		int prodCnt = prodDao.prodCnt();
		
		int paginationSize = (int) Math.ceil((double)prodCnt/pageVo.getPageSize());
		resultMap.put("paginationSize", paginationSize);
		
		return resultMap;
	}

}
