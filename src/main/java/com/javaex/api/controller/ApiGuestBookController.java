package com.javaex.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ApiGuestBookController {
	
	@RequestMapping(value ="/api/guestbook/addList", method = {RequestMethod.GET,RequestMethod.POST})
	public String addList() {
		System.out.println("ApiGuestBookController > addList");
		
		return "apiGuestbook/addList";
	}

}
