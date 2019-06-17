package kr.or.ddit.db.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import kr.or.ddit.db.model.DbVo;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DbDaoTest {
	
	private static final Logger logger = LoggerFactory
			.getLogger(DbDaoTest.class);
	
	private IDbDao dbDao;
	
	@Before
	public void setup(){
		dbDao = new DbDao();
	}
	
	@Test
	public void mappingListTest() {
		/***Given***/
		
		/***When***/
		List<DbVo> mappingList = dbDao.mappingList();
		
		/***Then***/
		assertNotNull(mappingList);
		assertEquals(2, mappingList.size());
		
	}

}
