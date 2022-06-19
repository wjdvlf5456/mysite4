package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;

@Controller
@RequestMapping(value="/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	// =================================== 게시판(메인) ===================================
	@RequestMapping(value="/list", method= {RequestMethod.GET,RequestMethod.POST})
	public String list(Model model) {
		System.out.println("BoardController > list");
		
		List<BoardVo> boardList = boardService.getBoardList();
		
		model.addAttribute("boradList" , boardList);
		
		//list.jsp로 포워딩
		return "board/list";
	}
	
	// =================================== 글쓰기 폼 ===================================
	@RequestMapping(value="/writeForm", method= {RequestMethod.GET,RequestMethod.POST})
	public String writeForm() {
		System.out.println("BoardController > writeForm");
		
		//writeForm.jsp로 포워딩
		return "board/writeForm";
	}
	
	// =================================== 글쓰기 ===================================
	@RequestMapping(value="/write", method= {RequestMethod.GET,RequestMethod.POST})
	public String write() {
		System.out.println("BoardController > write");
		
		//list.jsp로 리다이렉트
		return "redirect:/list";
	}
	
	// =================================== 삭제 ===================================
	@RequestMapping(value="/delete", method= {RequestMethod.GET,RequestMethod.POST})
	public String delete() {
		System.out.println("BoardController > delete");
		
		//list.jsp로 리다이렉트
		return "redirect:/list";
	}
	
	// =================================== 읽기 페이지 ===================================
	@RequestMapping(value="/read", method= {RequestMethod.GET,RequestMethod.POST})
	public String read() {
		System.out.println("BoardController > read");
		
		//read.jsp로 포워딩
		return "board/read";
	}
	
	// =================================== 수정페이지 ===================================
	@RequestMapping(value="/modifyForm", method= {RequestMethod.GET,RequestMethod.POST})
	public String modifyForm() {
		System.out.println("BoardController > modifyForm");
		
		//read.jsp로 포워딩
		return "board/read";
	}
	
	// =================================== 수정 ===================================
	@RequestMapping(value="/modify", method= {RequestMethod.GET,RequestMethod.POST})
	public String modify() {
		System.out.println("BoardController > modifyForm");
		
		//read.jsp로 포워딩
		return "board/read";
	}
	
	
}
