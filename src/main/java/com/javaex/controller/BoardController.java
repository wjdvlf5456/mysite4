package com.javaex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/board")
public class BoardController {
	
	@RequestMapping(value="/list", method= {RequestMethod.GET,RequestMethod.POST})
	public String list() {
		System.out.println("BoardController > list");
		
		//list.jsp로 포워딩
		return "board/list";
	}
	
	@RequestMapping(value="/writeForm", method= {RequestMethod.GET,RequestMethod.POST})
	public String writeForm() {
		System.out.println("BoardController > writeForm");
		
		//writeForm.jsp로 포워딩
		return "board/writeForm";
	}
	
	@RequestMapping(value="/read", method= {RequestMethod.GET,RequestMethod.POST})
	public String read() {
		System.out.println("BoardController > read");
		
		//read.jsp로 포워딩
		return "board/read";
	}
	
	@RequestMapping(value="/modifyForm", method= {RequestMethod.GET,RequestMethod.POST})
	public String modifyForm() {
		System.out.println("BoardController > modifyForm");
		
		//read.jsp로 포워딩
		return "board/read";
	}
	
	
}
