package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestBookService;
import com.javaex.vo.GuestBookVo;

@Controller
@RequestMapping(value = "/api/guestbook")
public class ApiGuestBookController {

	@Autowired
	private GuestBookService guestBookService;

	// ============================ 방명록 메인(ajax+json) ============================
	@RequestMapping(value = "/addList", method = { RequestMethod.GET, RequestMethod.POST })
	public String addList() {
		System.out.println("ApiGuestBookController > addList");

		return "apiGuestbook/addList";
	}

	// ============================== 방명록 데이터만 전송 ==============================
	@ResponseBody
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public List<GuestBookVo> list() {
		System.out.println("ApiGuestBookController > list");
		List<GuestBookVo> guestList = guestBookService.guestList();

		return guestList;
	}

	// ============================== 방명록에 글쓰기 ==============================
	@ResponseBody
	@RequestMapping(value = "/add", method = { RequestMethod.GET, RequestMethod.POST })
	public GuestBookVo add(@ModelAttribute GuestBookVo guestBookVo) {
		System.out.println("ApiGuestBookController > add");
		GuestBookVo gVo = guestBookService.guestInsert(guestBookVo);
		
		return gVo;
	}
	
	// =================================== 방명록 삭제 ===================================
	@ResponseBody
	@RequestMapping(value="/remove", method= {RequestMethod.GET,RequestMethod.POST})
	public String remove(@ModelAttribute GuestBookVo guestBookVo) {
		System.out.println("GuestBookController > delete");
		String state = guestBookService.guestDelete(guestBookVo);
		
		return state;
	}

}
