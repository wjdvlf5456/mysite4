package com.javaex.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.RboardService;

@Controller
@RequestMapping(value = "/api/rboard")
public class ApiBoardController {
	
	@Autowired
	private RboardService rboardService;
	
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public String rList() {
		System.out.println("ApiBoardController > rList");
		
		
		
		
		return "rboard/list";
	}
	

}
