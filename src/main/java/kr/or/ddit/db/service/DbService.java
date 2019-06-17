package kr.or.ddit.db.service;

import java.util.List;

import kr.or.ddit.db.dao.DbDao;
import kr.or.ddit.db.dao.IDbDao;
import kr.or.ddit.db.model.DbVo;

public class DbService implements IDbService{
	
	private IDbDao dbDao;
	
	public DbService() {
		dbDao = new DbDao();
	}
	
	@Override
	public List<DbVo> mappingList() {
		return dbDao.mappingList();
	}

}
