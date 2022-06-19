package com.javaex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/gbc")
public class GuestBookController {
	
	// =================================== 방명록 메인 ===================================
	@RequestMapping(value="/addList", method= {RequestMethod.GET,RequestMethod.POST})
	public String addList() {
		System.out.println("GuestBookController > addList");
		
		//포워딩
		return "guestbook/addList";
	}
	
	// =================================== 방명록에 글쓰기 ===================================
	@RequestMapping(value="/add", method= {RequestMethod.GET,RequestMethod.POST})
	public String add() {
		System.out.println("GuestBookController > add");
		
		//리다이렉트
		return "redirect:addList";
	}
	
	// =================================== 방명록 삭제페이지 ===================================
	@RequestMapping(value="/deleteForm", method= {RequestMethod.GET,RequestMethod.POST})
	public String deleteForm() {
		System.out.println("GuestBookController > addList");
		
		//포워딩
		return "guestbook/deleteForm";
	}
	
	// =================================== 방명록 삭제 ===================================
	@RequestMapping(value="/delete", method= {RequestMethod.GET,RequestMethod.POST})
	public String delete() {
		System.out.println("GuestBookController > delete");
		
		//리다이렉트
		return "redirect:addList";
	}
	

}
