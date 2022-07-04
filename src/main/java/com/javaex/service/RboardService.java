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

	// rboard 리스트
	public List<RboardVo> rboardList(String keyword) {
		return rboardDao.rboardList(keyword);
	}

	public RboardVo getRboard(int no) {

		// read
		if (no > 0) {
			rboardDao.getHit(no);
			// modify
		} else {
			no *= -1;
		}
		return rboardDao.getRboard(no);
	}

	// 새 글 작성
	public int insertNewBorad(RboardVo rboardVo) {
		int lastNo = rboardDao.getLastIndex();

		// rownum으로 마지막 숫자 조회 후 그룹넘버추가
		rboardVo.setGroupNo(lastNo + 1);
		int count = rboardDao.insertNewBoard(rboardVo);

		return count;
	};

	// 답글 작성
	public int insertReqBoard(RboardVo rboardVo) {
		
		System.out.println(rboardVo);

		int no = rboardVo.getNo();

		// 부모 데이터
		RboardVo rVo = rboardDao.getRboard(no);
		
		int cnt = rVo.getDepth()+1;
		System.out.println("cnt:"+cnt);

		// 부모로부터 1더함
		rboardVo.setDepth(cnt);
		

		String title = rboardVo.getTitle();
		
		String str = "";
		for (int i = 0; i < cnt; i++) {
			str += "--";
		}
		str += title;
		
		System.out.println(str);
		
		rboardVo.setTitle(str);

		rboardVo.setGroupNo(rVo.getGroupNo());

		int orderNo = rVo.getOrderNo() + 1;
		System.out.println(orderNo);
		rboardVo.setOrderNo(orderNo);
		
		System.out.println(rboardVo);
		rboardDao.getOrderNo(rboardVo);

		int count = rboardDao.insertReqBoard(rboardVo);

		return count;
	};

	public int rboardDelete(int no) {
		return rboardDao.rboardDelete(no);
	};

	public int rboardUpdate(RboardVo rboardVo) {
		int count = rboardDao.rboardUpdate(rboardVo);
		return count;
	}

}
