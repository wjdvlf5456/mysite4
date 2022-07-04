package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	// ============================ 게시판 글읽기 (jstl) ============================
	@RequestMapping(value = "/read/{no}", method = {RequestMethod.GET,RequestMethod.POST})
	public String rRead(@PathVariable("no")int no, Model model) {
		
		RboardVo rboardVo = rboardService.getRboard(no);
		model.addAttribute("rboardVo",rboardVo);
		System.out.println(rboardVo);
		
		return "rboard/read";
	};
	
	// ============================ 게시판 글쓰기폼으로 (jstl) ============================
	@RequestMapping(value = "/writeForm", method = {RequestMethod.GET,RequestMethod.POST})
	public String rWriteForm(@ModelAttribute RboardVo rboardVo,Model model) {
		System.out.println("ApiBoardController > rWriteFomr");
		
		model.addAttribute("rboardVo",rboardVo);
		return "rboard/writeForm";
	};
	// ============================ 게시판 새글등록 (jstl) ============================
	@RequestMapping(value = "/write", method = {RequestMethod.GET,RequestMethod.POST})
	public String rWrite(@ModelAttribute RboardVo rboardVo) {
		
		int count = rboardService.insertNewBorad(rboardVo);
		System.out.println(count+"건을 등록하였습니다.");
		
		return "redirect:/api/rboard/list";
	};
	

}
