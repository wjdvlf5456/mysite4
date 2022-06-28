package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestBookDao;
import com.javaex.vo.GuestBookVo;

@Service
public class GuestBookService {

	@Autowired
	private GuestBookDao guestBookDao;

	// ================ 방명록 리스트 가져오기 ===============
	public List<GuestBookVo> guestList() {
		List<GuestBookVo> guestList = guestBookDao.guestList();
		return guestList;
	}

	// ================ 방명록 저장(ajax) ===============
	public int guestInsert(GuestBookVo guestBookVo) {
		System.out.println("GuestBookSerVice > guestInsert");
		return guestBookDao.guestInsert(guestBookVo);
	}

	public int guestDelete(int no) {
		return guestBookDao.guestDelete(no);
	}

	public GuestBookVo getGuest(int no) {
		return guestBookDao.getGuest(no);
	}

}
