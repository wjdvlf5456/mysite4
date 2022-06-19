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
	public List<BoardVo> getBoardList(){
		return boardDao.getBoardList();
	}
	
	public int boardInsert(BoardVo boardVo) {
		return boardDao.boardInsert(boardVo);
	}
}
