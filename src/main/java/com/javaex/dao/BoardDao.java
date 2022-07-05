package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlSession;

	// ================================= Board 목록 =================================
	public List<BoardVo> getBoardList(String keyword,int startRnum,int endRnum) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyword", keyword);
		map.put("startRnum", startRnum);
		map.put("endRnum", endRnum);
		System.out.println(map.toString());
		
		List<BoardVo> boardList = sqlSession.selectList("board.selectList",map);
		return boardList;
	}
	
	public int getLastIndex() {
		int lastNo = sqlSession.selectOne("board.getLastIndex");
		
		return lastNo;
		
	}
	
	// ============================= 선택한 board 정보 가져오기 =============================
	public BoardVo getBoard(int no) {
		System.out.println("BoardDao > getBoard");
		return sqlSession.selectOne("board.getBoard", no);
	}

	// =================================== Board 게시글 조회수 증가 ===================================
	public int getHit(int no) {
		int count = sqlSession.update("board.getHit", no);
		System.out.println(no + " 번 게시판을 조회하였습니다. (BoardDao)");

		return count;
	}

	// =================================== Board 게시글 등록 ===================================
	public int boardInsert(BoardVo boardVo) {
		int count = sqlSession.insert("board.boardInsert", boardVo);
		System.out.println(count + " 건이 등록되었습니다. (BoardDao)");
		return count;
	}

	// =================================== Board 게시글 삭제 ===================================
	public int boardDelete(int no) {
		int count = sqlSession.delete("board.boardDelete", no);
		System.out.println(count + " 건이 삭제되었습니다. (BoardDao)");
		
		return count;
	}

	// =================================== Board 게시글 수정 ===================================
	public int boardUpdate(BoardVo boardVo) {
		int count = sqlSession.update("board.boardUpdate", boardVo);
		System.out.println(count + " 건이 수정되었습니다. (BoardDao)");
		
		return count;
	}
	
}
