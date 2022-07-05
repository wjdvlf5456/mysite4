package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {
	
	@Autowired
	private BoardDao boardDao;
	
	// board 리스트
	public List<BoardVo> getBoardList(String keyword){
		return boardDao.getBoardList(keyword);
	}
	
	public BoardVo getBoard(int no) {
		
		//read
		if (no>0) {
			boardDao.getHit(no);
		//modify	
		} else {
				no *= -1;
		}
		return boardDao.getBoard(no);
	}
	
	public int boardInsert(BoardVo boardVo) {
		
		
		return boardDao.boardInsert(boardVo);
	}
	
	public int boardDelete(int no) {
		return boardDao.boardDelete(no);
	}
	
	public int boardUpdate(BoardVo boardVo) {
		int count = boardDao.boardUpdate(boardVo);
		return count;
	}
}
