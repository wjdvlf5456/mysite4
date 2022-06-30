package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.FileService;

@Controller
public class FileController {
	
	@Autowired
	private FileService fileService;
	
	// =================================== 갤러리(메인) ===================================
	@RequestMapping(value = "/fileupload/form",method = {RequestMethod.GET,RequestMethod.POST})
	public String form() {
		System.out.println("FileController > form()");
		
		return "fileupload/form";
	}
	
	// =================================== 갤러리 파일 업로드 ===================================
	@RequestMapping(value = "/fileupload/upload", method = {RequestMethod.GET,RequestMethod.POST})
	public String upload(@RequestParam("file") MultipartFile file, Model model) {
		System.out.println("FileController > upload");
		System.out.println(file.getOriginalFilename());
		
		String saveName = fileService.save(file);
		model.addAttribute("saveName",saveName);
		
		return "fileupload/result";
	}

}
