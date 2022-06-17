package com.javaex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestApp {
	
	@RequestMapping("test")
	public String test() {
		System.out.println("test");
			
		return "/WEB-INF/views/test.jsp";
	}
	
	
}
