package com.javaex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	// =================================== 로그인폼 ===================================
	@RequestMapping(value = "/loginForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String loginForm() {
		System.out.println("UserController > loginForm");

		// loginForm.jsp로 포워딩
		return "user/loginForm";
	}

	// =================================== 로그인 ===================================
	@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
	public String login() {
		System.out.println("UserController > login");

		// 메인페이지로 리다이렉트
		return "redirect:/main";
	}
	
	// =================================== 로그아웃 ===================================
	@RequestMapping(value = "/logout", method = { RequestMethod.GET, RequestMethod.POST })
	public String logout() {
		System.out.println("UserController > logout");
		
		// 메인페이지로 리다이렉트
		return "redirect:/main";
	}

	// =================================== 등록폼 ===================================
	@RequestMapping(value = "/joinForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String joinForm() {
		System.out.println("UserController > joinForm");

		// joinForm.jsp 포워딩
		return "user/joinForm";
	}

	// =================================== 등록 ===================================
	@RequestMapping(value = "/join", method = { RequestMethod.GET, RequestMethod.POST })
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println("UserController > join");

		// joinOk로 리다이렉
		return "redirect:/joinOk";
	}

	// =================================== 등록성공 ===================================
	@RequestMapping(value = "/joinOk", method = { RequestMethod.GET, RequestMethod.POST })
	public String joinOk() {
		System.out.println("UserController > joinOk");

		// joinOk.jsp 포워딩
		return "user/joinOk";
	}

	// =================================== 정보수정폼 ===================================
	@RequestMapping(value = "/modifyForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String modifyForm() {
		System.out.println("UserController > modifyForm");

		// modifyForm.jsp 포워딩
		return "user/modifyForm";
	}

	// =================================== 정보수정 ===================================
	@RequestMapping(value = "/modify", method = { RequestMethod.GET, RequestMethod.POST })
	public String modify() {
		System.out.println("UserController > modify");

		// 메인으로 리다이렉트
		return "redirect:/main";
	}

}
