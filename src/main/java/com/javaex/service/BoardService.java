package com.javaex.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.ls.LSException;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {
	
	@Autowired
	private BoardDao boardDao;
	
	// board 리스트
	public List<BoardVo> getBoardList(String keyword,int crtPage){
		
		//페이지 당 게시글 개수
		int listCnt = 10;
		
		int totalCnt = boardDao.selectTotalCnt();
		if (crtPage<1) {
			crtPage=1;
		}else if(crtPage>(totalCnt/listCnt)+1){
			crtPage = (totalCnt/listCnt) +1;
		}
		
		System.out.println(crtPage);
		
		//페이지당 버튼 갯수
		int pageBtnCount = 5;
		
		//마지막 버튼 번호
		int endPageBtnNo = (int)Math.ceil(crtPage/(double)pageBtnCount)*pageBtnCount;
		
		//시작 버튼 번호
		int startPageBtnNo = endPageBtnNo - pageBtnCount+1;
		
		
		if (endPageBtnNo > totalCnt/listCnt + 1) {
			endPageBtnNo = totalCnt/listCnt +1;
		}
		
		System.out.println("시작번호: " + startPageBtnNo);
		System.out.println("현재페이지: " + crtPage);
		System.out.println("끝번호: " + endPageBtnNo);
		
		
		
		int startRnum = (crtPage-1)*listCnt +1;
		int endRnum = crtPage*listCnt;
		
		return boardDao.getBoardList(keyword,startRnum,endRnum);
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
