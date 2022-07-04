package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.RboardService;
import com.javaex.vo.RboardVo;

@Controller
@RequestMapping(value = "/api/rboard")
public class ApiBoardController {
	
	@Autowired
	private RboardService rboardService;
	
	// ============================ 게시판 메인(ajax+json) ============================
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public String rList() {
		System.out.println("ApiBoardController > rList");
		
		return "rboard/list";
	}
	
	// ============================== 게시판 데이터만 전송 ==============================
	@ResponseBody
	@RequestMapping(value = "/getList", method = {RequestMethod.GET,RequestMethod.POST})
	public List<RboardVo> getlist(){
		System.out.println("ApiBoardController > getList");
		
		List<RboardVo> rboardList = rboardService.rboardList();
		System.out.println(rboardList);
		
		return rboardList;
	};
	
	// ============================ 게시판 글읽기(ajax+json) ============================
	@RequestMapping(value = "/read", method = {RequestMethod.GET,RequestMethod.POST})
	public String rRead() {
		
		return "rboard/read";
	};
	
	

}
