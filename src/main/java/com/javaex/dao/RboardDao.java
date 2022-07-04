package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.RboardVo;

@Repository
public class RboardDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<RboardVo> rboardList(){
		List<RboardVo> rBoardList = sqlSession.selectList("rboard.selectList");
		System.out.println("다오: "+rBoardList.toString());
		
		return rBoardList;
	};
	
	public RboardVo getRboard(int no){
		RboardVo rVo = sqlSession.selectOne("rboard.selectRboard",no);
		
		return rVo;
	};
	
	public int getHit(int no) {
		int count = sqlSession.update("rboard.getHit",no);
		
		
		return count;
	};
	
	public int getLastIndex(){
		int no = sqlSession.selectOne("rboard.getLastIndex");
		return no;
	};
	
	public int insertNewBoard(RboardVo rboardVo) {
		int count = sqlSession.insert("rboard.insertNewBoard",rboardVo);
		return count;
	}
	
	
	

}
