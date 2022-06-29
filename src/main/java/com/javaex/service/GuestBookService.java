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
	public GuestBookVo guestInsert(GuestBookVo guestBookVo) {
		System.out.println("GuestBookSerVice > guestInsert");
		
		int count = guestBookDao.guestInsert(guestBookVo);
		
		int no = guestBookVo.getNo();
		GuestBookVo gVo = guestBookDao.getGuest(no);
		System.out.println(gVo);
		
		return gVo;
	}

	public String guestDelete(GuestBookVo guestBookVo) {
		GuestBookVo gVo = guestBookDao.getGuest(guestBookVo.getNo());
		
		String state = "";
		
		if (guestBookVo.getPassword().equals(gVo.getPassword())) {
			guestBookDao.guestDelete(gVo.getNo());
			state += "true";
		} else {
			state += "false";
		}
		
		return state;
	}

	public GuestBookVo getGuest(int no) {
		return guestBookDao.getGuest(no);
	}

}
