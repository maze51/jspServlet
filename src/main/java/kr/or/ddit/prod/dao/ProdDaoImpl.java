package kr.or.ddit.prod.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.mybatis.MyBatisUtil;
import kr.or.ddit.paging.model.PageVo;
import kr.or.ddit.prod.model.ProdVo;

public class ProdDaoImpl implements IProdDao {

	@Override
	public List<ProdVo> prodPagingList(PageVo pageVo) {
		SqlSession sqlSesson = MyBatisUtil.getSqlSession();
		List<ProdVo> prodList = sqlSesson.selectList("prod.prodPagingList", pageVo);
		sqlSesson.close();
		return prodList;
	}

	@Override
	public int prodCnt() {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int prodCnt = sqlSession.selectOne("prod.prodCnt");
		sqlSession.close();
		return prodCnt;
	}

	@Override
	public List<ProdVo> prodList(String prod_lgu) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		List<ProdVo> list = sqlSession.selectList("prod.prodList", prod_lgu);
		sqlSession.close();
		return list;
	}
	
	

}
