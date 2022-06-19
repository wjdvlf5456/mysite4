package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlSession;

	// ================================= Board 목록 =================================
	public List<BoardVo> getBoardList() {
		List<BoardVo> boardList = sqlSession.selectList("board.selectList");

		return boardList;
	}

	// ============================= 선택한 board 정보 가져오기 =============================
	public BoardVo getBoard(int no) {
		System.out.println("BoardDao > getBoard");
		return sqlSession.selectOne("board.getBoard", no);
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

}
