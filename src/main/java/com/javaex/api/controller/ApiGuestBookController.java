package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
	@RequestMapping(value = "/add", method = { RequestMethod.GET, RequestMethod.POST })
	public String add(@ModelAttribute GuestBookVo guestBookVo) {
		System.out.println("ApiGuestBookController > add");
		guestBookService.guestInsert(guestBookVo);
		
		return "redirect:addList";
	}
	
	// =================================== 방명록 삭제페이지 ===================================
	@RequestMapping(value="/deleteForm/{no}", method= {RequestMethod.GET,RequestMethod.POST})
	public String deleteForm(@PathVariable("no") int no, Model model) {
		System.out.println("GuestBookController > deleteForm");
		
		GuestBookVo guestBookVo = guestBookService.getGuest(no);
		model.addAttribute("guestBookVo",guestBookVo);
		
		//포워딩
		return "guestbook/deleteForm";
	}
	
	// =================================== 방명록 삭제 ===================================
	@RequestMapping(value="/delete", method= {RequestMethod.GET,RequestMethod.POST})
	public String delete(@RequestParam("no") int no, @RequestParam("password") String password) {
		System.out.println("GuestBookController > delete");
		if (guestBookService.getGuest(no).getPassword().equals(password)) {
			guestBookService.guestDelete(no);
		} else {
			System.out.println("비밀번호가 틀립니다.");
		}
		
		//리다이렉트
		return "redirect:addList";
	}

}
