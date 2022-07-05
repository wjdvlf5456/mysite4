package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.RboardService;
import com.javaex.vo.RboardVo;

@Controller
@RequestMapping(value = "/rboard")
public class RboardController {

	@Autowired
	private RboardService rboardService;

	// =================================== 게시판(메인) ===================================
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(Model model, @RequestParam(required = false) String keyword) {
		System.out.println("BoardController > list");

		List<RboardVo> rboardList = rboardService.rboardList(keyword);
		System.out.println(rboardList.toString());
		System.out.println("");

		model.addAttribute("rboardList", rboardList);

		// list.jsp로 포워딩
		return "rboard/list";
	}

	// =================================== 글쓰기 폼 ===================================
	@RequestMapping(value = "/writeForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String writeForm() {
		System.out.println("RboardController > writeForm");
		System.out.println("");

		// writeForm.jsp로 포워딩
		return "rboard/writeForm";
	}
	// =================================== 답글쓰기 폼 ===================================
	@RequestMapping(value = "/reqWriteForm/{no}", method = { RequestMethod.GET, RequestMethod.POST })
	public String  reqWriteForm(@PathVariable("no")int no, Model model) {
		System.out.println("RboardController > reqWriteForm");
		
		RboardVo rboardVo = rboardService.getRboard(no);
		
		model.addAttribute("rboardVo",rboardVo);
		// writeForm.jsp로 포워딩
		return "rboard/writeForm";
	}

	// =================================== 글쓰기 ===================================
	@RequestMapping(value = "/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(@ModelAttribute RboardVo rboardVo) {
		System.out.println("BoardController > write");
		
		System.out.println(rboardVo);
		if (rboardVo.getNo()==0) {
			int count = rboardService.insertNewBorad(rboardVo);
			System.out.println("새글");
		} else {
			
			int count = rboardService.insertReqBoard(rboardVo);
			System.out.println("답글");
		}
		System.out.println("");

		// list.jsp로 리다이렉트
		return "redirect:/rboard/list";
	}

	// =================================== 삭제 ===================================
	@RequestMapping(value = "/delete/{no}", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@PathVariable("no") int no) {
		System.out.println("RboardController > delete");
		rboardService.rboardDelete(no);

		// list.jsp로 리다이렉트
		return "redirect:/rboard/list";
	}

	// =================================== 읽기 페이지 ===================================
	@RequestMapping(value = "/read/{no}", method = { RequestMethod.GET, RequestMethod.POST })
	public String read(@PathVariable("no") int no, Model model) {
		System.out.println("BoardController > read");
		RboardVo rboardVo = rboardService.getRboard(no);

		model.addAttribute("rboardVo", rboardVo);

		// read.jsp로 포워딩
		return "rboard/read";
	}

	// =================================== 수정페이지 ===================================
	@RequestMapping(value = "/modifyForm/{no}", method = { RequestMethod.GET, RequestMethod.POST })
	public String modifyForm(@PathVariable("no") int no, Model model) {
		System.out.println("RboardController > modifyForm");

		// 수정시 조회수 증가X
		no *= -1;
		RboardVo rboardVo = rboardService.getRboard(no);
		model.addAttribute("rboardVo", rboardVo);

		// modifyForm.jsp로 포워딩
		return "rboard/modifyForm";
	}

	// =================================== 수정 ===================================
	@RequestMapping(value = "/modify", method = { RequestMethod.GET, RequestMethod.POST })
	public String modify(@ModelAttribute RboardVo rboardVo) {
		System.out.println("RboardController > modifyForm");

		rboardService.rboardUpdate(rboardVo);

		// list.jsp로 리다이렉트
		return "redirect:./list";

	}

}
