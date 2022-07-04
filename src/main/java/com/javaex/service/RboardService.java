package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.RboardDao;
import com.javaex.vo.RboardVo;

@Service
public class RboardService {
	
	@Autowired
	private RboardDao rboardDao;
	
	public List<RboardVo> rboardList(){
		
		return rboardDao.rboardList();
	}
	
	public RboardVo getRboard(int no) {
		RboardVo rVo = rboardDao.getRboard(no);
		
		int count = rboardDao.getHit(no);
		System.out.println("조회수 "+count+"증가");
		return rVo;
	};
	
	//새 글 작성
	public int insertNewBorad(RboardVo rboardVo){
		int lastNo = rboardDao.getLastIndex();
		
		//rownum으로 마지막 숫자 조회 후 그룹넘버추가
		rboardVo.setGroupNo(lastNo);
		int count = rboardDao.insertNewBoard(rboardVo);
		
		return count;
	};

}
