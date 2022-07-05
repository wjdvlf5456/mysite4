package com.javaex.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;

@Controller
@RequestMapping(value = "/board")
public class BoardController {

	@Autowired
	private BoardService boardService;

	// =================================== 게시판(메인) ===================================
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(Model model, @RequestParam(required = false) String keyword, @RequestParam(required = false)String crtPage) {
		System.out.println("BoardController > list");

		int currentPage = 1;
		//required = false는 null값을 가져오는 건데 int형을 null로 비교할 수 없어 따로 선언해주었다.
		if (crtPage==null) {
			currentPage=1;
		} else {
			currentPage = Integer.parseInt(crtPage);
		}
		
		//검색시 활용
		String key = "";
		if (keyword!=null) {
			key += keyword;
		} else {
			key = "";
		}
		
		Map<String,Object> pMap = boardService.getBoardList(key,currentPage);
		System.out.println(pMap.toString());
		
		model.addAttribute("pMap", pMap);

		// list.jsp로 포워딩
		return "board/list";
	}

	// =================================== 글쓰기 폼 ===================================
	@RequestMapping(value = "/writeForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String writeForm() {
		System.out.println("BoardController > writeForm");

		// writeForm.jsp로 포워딩
		return "board/writeForm";
	}

	// =================================== 글쓰기 ===================================
	@RequestMapping(value = "/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(@ModelAttribute BoardVo boardVo) {
		System.out.println("BoardController > write");
		int count = boardService.boardInsert(boardVo);
		System.out.println(count + " 컨트롤러");

		// list.jsp로 리다이렉트
		return "redirect:/board/list";
	}

	// =================================== 삭제 ===================================
	@RequestMapping(value = "/delete/{no}", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@PathVariable("no") int no) {
		System.out.println("BoardController > delete");
		boardService.boardDelete(no);

		// list.jsp로 리다이렉트
		return "redirect:/board/list";
	}

	// =================================== 읽기 페이지 ===================================
	@RequestMapping(value = "/read/{no}", method = { RequestMethod.GET, RequestMethod.POST })
	public String read(@PathVariable("no") int no, Model model) {
		System.out.println("BoardController > read");
		BoardVo boardVo = boardService.getBoard(no);

		model.addAttribute("boardVo", boardVo);

		// read.jsp로 포워딩
		return "board/read";
	}

	// =================================== 수정페이지 ===================================
	@RequestMapping(value = "/modifyForm/{no}", method = { RequestMethod.GET, RequestMethod.POST })
	public String modifyForm(@PathVariable("no") int no, Model model) {
		System.out.println("BoardController > modifyForm");

		// 수정시 조회수 증가X
		no *= -1;
		BoardVo boardVo = boardService.getBoard(no);
		model.addAttribute("boardVo", boardVo);

		// modifyForm.jsp로 포워딩
		return "board/modifyForm";
	}

	// =================================== 수정 ===================================
	@RequestMapping(value = "/modify", method = { RequestMethod.GET, RequestMethod.POST })
	public String modify(@ModelAttribute BoardVo boardVo) {
		System.out.println("BoardController > modifyForm");

		boardService.boardUpdate(boardVo);

		// list.jsp로 리다이렉트
		return "redirect:./list";

	}

}
