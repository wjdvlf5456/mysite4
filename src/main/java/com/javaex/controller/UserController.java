package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	UserService userService;

	// =================================== 로그인폼 ===================================
	@RequestMapping(value = "/loginForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String loginForm() {
		System.out.println("UserController > loginForm");

		// loginForm.jsp로 포워딩
		return "user/loginForm";
	}

	// =================================== 로그인 ===================================
	@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("UserController > login");

		UserVo authUser = userService.login(userVo);
		System.out.println(authUser);
		if (authUser == null) {	//로그인 실패
			return "redirect:/user/loginForm?result=fail";

		} else {	//로그인 성공
			session.setAttribute("authUser", authUser);
			
			// 메인페이지로 리다이렉트
			return "redirect:/main";

		}

	}

	// =================================== 로그아웃 ===================================
	@RequestMapping(value = "/logout", method = { RequestMethod.GET, RequestMethod.POST })
	public String logout(HttpSession session) {
		System.out.println("UserController > logout");
		
		session.removeAttribute("/logout");
		session.invalidate();
		
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
		
		System.out.println(userVo.toString());
		userService.userInsert(userVo);
		
		// joinOk로 리다이렉트
		return "redirect:/user/joinOk";

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
	public String modifyForm(HttpSession session, Model model) {
		System.out.println("UserController > modifyForm");
		
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		UserVo userVo = userService.getUser(authUser.getNo());
		
		model.addAttribute("userVo",userVo);
		
		// modifyForm.jsp 포워딩
		return "user/modifyForm";
	}

	// =================================== 정보수정 ===================================
	@RequestMapping(value = "/modify", method = { RequestMethod.GET, RequestMethod.POST })
	public String modify(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("UserController > modify");
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		//회원정보 수정
		userService.userUpdate(userVo);
		
		//authUser name 값만 바꿔주기
		authUser.setName(userService.getUser(authUser.getNo()).getName());
		System.out.println(authUser.toString());
		
		
		session.setAttribute("authUser", authUser);

		
		// 메인으로 리다이렉트
		return "redirect:/main";
	}

}
