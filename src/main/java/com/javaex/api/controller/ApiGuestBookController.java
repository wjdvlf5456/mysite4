package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestBookService;
import com.javaex.vo.GuestBookVo;

@Controller
@RequestMapping(value ="/api/guestbook")
public class ApiGuestBookController {
	
	@Autowired
	private GuestBookService guestBookService;
	
	
	@RequestMapping(value ="/addList", method = {RequestMethod.GET,RequestMethod.POST})
	public String addList() {
		System.out.println("ApiGuestBookController > addList");
		
		return "apiGuestbook/addList";
	}
	
	//방명록 리스트 데이터만 보내줘
	@ResponseBody
	@RequestMapping(value ="/list", method = {RequestMethod.GET,RequestMethod.POST})
	public List<GuestBookVo> list() {
		System.out.println("ApiGuestBookController > list");
		
		List<GuestBookVo> guestList = guestBookService.guestList();
		System.out.println(guestList);
		
		return guestList;
	}
	
}
