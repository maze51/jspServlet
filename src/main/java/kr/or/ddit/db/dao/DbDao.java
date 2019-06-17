package kr.or.ddit.db.dao;

import java.util.List;

import mybatis.MyBatisUtil;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.db.model.DbVo;

public class DbDao implements IDbDao{

	@Override
	public List<DbVo> mappingList() {
		SqlSession session = MyBatisUtil.getSqlSession();
		List<DbVo> mappingList = session.selectList("db.mappingList");
		session.close();
		return mappingList;
	}

}
