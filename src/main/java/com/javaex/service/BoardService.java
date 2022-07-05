package com.javaex.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {
	
	@Autowired
	private BoardDao boardDao;
	
	// board 리스트
	public Map<String,Object> getBoardList(String keyword,int crtPage){
		
		// ================= 1 페이지 당 게시글 개수 =================
		int listCnt = 10;
		
		// 페이지의 총 게시글 개수
		int totalCnt = boardDao.selectTotalCnt(keyword);
		System.out.println("totalCnt: " + totalCnt);
		
		if (crtPage<1) {
			crtPage=1;
			
		//만약 마지막 페이지보다 큰 수를 입력하면 마지막 페이지로 보내기
		}else if(crtPage>(totalCnt/listCnt)+1){
			crtPage = (totalCnt/listCnt) +1;
		}
		
		System.out.println(crtPage);
		
		// ================= 페이지당 버튼 갯수 =================
		int pageBtnCount = 5;
		
		//마지막 버튼 번호
		//double형을 int형으로 바꿔주면 올림이 되는 것을 이용
		int endPageBtnNo = (int)Math.ceil(crtPage/(double)pageBtnCount)*pageBtnCount;
		
		//시작 버튼 번호
		int startPageBtnNo = endPageBtnNo - pageBtnCount+1;
		
		//마지막 페이지 번호가 게시글 총 개수 * 페이지당 개수를 넘어가지 않게 해준다.
		// ex: 총 개수: 127개,  11~15페이지일 때   13페이지까지만 나오게 
		if (endPageBtnNo > totalCnt/listCnt + 1) {
			endPageBtnNo = totalCnt/listCnt +1;
		}
		
		// 다음 화살표 유무
		boolean next = false;
		
		if (listCnt*endPageBtnNo < totalCnt) {
			next = true;
		}
		
		
		// 이전 화살표 유무
		boolean prev = false;
		if (startPageBtnNo != 1) {
			prev = true;
		}
		
		System.out.println("이전 화살표유무: " + prev + ", 다음 화살표유무: " + next);
		
		System.out.println("시작번호: " + startPageBtnNo);
		System.out.println("현재페이지: " + crtPage);
		System.out.println("끝번호: " + endPageBtnNo);
		
		
		int startRnum = (crtPage-1)*listCnt +1;
		int endRnum = crtPage*listCnt;
		
		Map<String, Object> pMap = new HashMap<String, Object>();
		
		List<BoardVo> boardList = boardDao.getBoardList(keyword, startRnum, endRnum);
		pMap.put("boardList", boardList);
		pMap.put("keyword",keyword);
		pMap.put("prev", prev);
		pMap.put("startPageBtnNo", startPageBtnNo);
		pMap.put("pageBtnCount",pageBtnCount);
		pMap.put("endPageBtnNo", endPageBtnNo);
		pMap.put("next", next);
		
		return pMap;
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
