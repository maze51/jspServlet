package kr.or.ddit.db.service;

import static org.junit.Assert.*;

import java.util.List;

import kr.or.ddit.db.model.DbVo;

import org.junit.Before;
import org.junit.Test;

public class DbServiceTest {
	/*
	private IDbService dbService;
	
	@Before
	private void setup(){
		dbService = new DbService();
	}
	*/
	
	@Test
	public void mappingListTest() {
		/***Given***/
		IDbService dbService = new DbService();
		
		/***When***/
		List<DbVo> list = dbService.mappingList();

		/***Then***/
		assertEquals(2, list.size());
	}

}
