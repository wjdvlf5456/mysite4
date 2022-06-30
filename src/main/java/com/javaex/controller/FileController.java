package com.javaex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FileController {
	
	
	// =================================== 갤러리(메인) ===================================
	@RequestMapping(value = "/fileupload/form",method = {RequestMethod.GET,RequestMethod.POST})
	public String form() {
		System.out.println("FileController > form()");
		
		return "fileupload/form";
	}
	
	// =================================== 갤러리 파일 업로드 ===================================
	@RequestMapping(value = "/fileupload/upload", method = {RequestMethod.GET,RequestMethod.POST})
	public String upload(@RequestParam("file") String file) {
		System.out.println("FileController > upload");
		System.out.println(file);
		
		return null;
	}

}
