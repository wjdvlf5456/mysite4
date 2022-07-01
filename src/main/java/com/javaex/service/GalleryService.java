package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GalleryDao;
import com.javaex.vo.GalleryVo;

@Service
public class GalleryService {
	
	@Autowired
	private GalleryDao galleryDao;
	
	public List<GalleryVo> imgList(){
		List<GalleryVo> imgList = galleryDao.imgList();
		System.out.println(imgList);
		return imgList;
	}
	
	public String imgInsert() {
		
		
		
		
		
		return null;
	}
	

}
