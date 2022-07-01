package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.FileService;
import com.javaex.vo.FileVo;

@Controller
@RequestMapping(value = "/fileupload")
public class FileController {
	
	@Autowired
	private FileService fileService;
	
	// =================================== 갤러리(메인) ===================================
	@RequestMapping(value = "/result",method = {RequestMethod.GET,RequestMethod.POST})
	public String result(Model model) {
		System.out.println("FileController > result()");
		
		List<FileVo> fileList = fileService.fileList();
		System.out.println(fileList.toString());
		model.addAttribute("fileList",fileList);
		return "fileupload/result";
	}
	
	// =================================== 갤러리(업로드 폼) ===================================
	@RequestMapping(value = "/form",method = {RequestMethod.GET,RequestMethod.POST})
	public String form() {
		System.out.println("FileController > form()");
		
		return "fileupload/form";
	}
	
	// =================================== 갤러리 파일 업로드 ===================================
	@RequestMapping(value = "/upload", method = {RequestMethod.GET,RequestMethod.POST})
	public String upload(@RequestParam("file") MultipartFile file, Model model) {
		System.out.println("FileController > upload");
		System.out.println(file.getOriginalFilename());
		
		String saveName = fileService.save(file);
		model.addAttribute("saveName",saveName);
		
		return "fileupload/result";
	}

}
