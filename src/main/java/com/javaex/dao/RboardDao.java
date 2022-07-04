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
	
	// ================================= Rboard 목록 =================================
	public List<RboardVo> rboardList(String keyword){
		List<RboardVo> rBoardList = sqlSession.selectList("rboard.selectList",keyword);
		System.out.println("다오: "+rBoardList.toString());
		
		return rBoardList;
	};
	
	// ============================= 선택한 Rboard 정보 가져오기 =============================
	public RboardVo getRboard(int no){
		RboardVo rVo = sqlSession.selectOne("rboard.selectRboard",no);
		return rVo;
	};
	
	// =================================== Rboard 게시글 조회수 증가 ===================================
	public int getHit(int no) {
		int count = sqlSession.update("rboard.getHit", no);
		System.out.println(no + " 번 게시판을 조회하였습니다.");
		return count;
	};
	
	// =================================== Rboard 새게시글 등록 ===================================
	public int insertNewBoard(RboardVo rboardVo) {
		int count = sqlSession.insert("rboard.insertNewBoard",rboardVo);
		return count;
	};
	
	// =================================== Rboard 답글 등록 ===================================
	public int insertReqBoard(RboardVo rboardVo) {
		int count = sqlSession.insert("rboard.insertReqBoard",rboardVo);
		return count;
	};
	// =================================== Rboard 답글 등록시 orderNo 미루기 ===================================
	public int getOrderNo(RboardVo rboardVo) {
		int count = sqlSession.update("rboard.getOrderNo",rboardVo);
		return count;
	};
	
	//게시글 마지막 숫자 가져오기
	public int getLastIndex(){
		int no = sqlSession.selectOne("rboard.getLastIndex");
		return no;
	};
	
	// =================================== Rboard 게시글 삭제 ===================================
	public int rboardDelete(int no) {
		int count = sqlSession.delete("rboard.rboardDelete", no);
		System.out.println(count + " 건이 삭제되었습니다.");
		
		return count;
	}
	
	// =================================== Rboard 게시글 수정 ===================================
	public int rboardUpdate(RboardVo rboardVo) {
		int count = sqlSession.update("rboard.rboardUpdate", rboardVo);
		System.out.println(count + " 건이 수정되었습니다.");
		
		return count;
	};

}
