package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;

@Controller
@RequestMapping(value = "/gallery")
public class GalleryController {
	
	@Autowired
	private GalleryService galleryService;
	
	// ================================= 갤러리 메인 =================================
	@RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		System.out.println("GalleryController > list");
		
		List<GalleryVo> imgList = galleryService.imgList();
		model.addAttribute("imgList",imgList);
		
		return "gallery/list";
	}
	
	// ================================= 갤러리 메인(모달) =================================
	@ResponseBody
	@RequestMapping(value = "/getImageInfo", method = {RequestMethod.GET, RequestMethod.POST})
	public GalleryVo getImageInfo(@RequestBody GalleryVo galleryVo) {
		System.out.println("GalleryController > list");
		System.out.println(galleryVo);
		
		GalleryVo gVo = galleryService.getImageInfo(galleryVo);
		System.out.println(gVo);
		
		return gVo;
	}
	
	// ================================= 사진 업로드  =================================
	@RequestMapping(value = "/upload", method = {RequestMethod.GET, RequestMethod.POST})
	public String imgUpload(@RequestParam("file") MultipartFile file,@RequestParam("userNo") int userNo, @RequestParam("content") String content) {
		System.out.println("GalleryController > upload");
		
		
		GalleryVo galleryVo = new GalleryVo(userNo,content);
		
		galleryService.imgUpload(galleryVo,file);
		
		return "redirect:list";
	}
	
	// ================================= 사진 삭제  =================================
		@ResponseBody
		@RequestMapping(value = "/delete", method = {RequestMethod.GET, RequestMethod.POST})
		public String imgDelete(@RequestBody GalleryVo galleryVo) {
			System.out.println("GalleryController > delete");
			System.out.println(galleryVo.toString());
			String success = galleryService.imgDelete(galleryVo);
			
			return success;
		}
	
	
	
	

}
