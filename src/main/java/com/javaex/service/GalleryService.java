package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.GalleryDao;
import com.javaex.vo.GalleryVo;

@Service
public class GalleryService {
	
	@Autowired
	private GalleryDao galleryDao;
	
	public List<GalleryVo> imgList(){
		List<GalleryVo> imgList = galleryDao.imgList();
		return imgList;
	}
	
	public String imgUpload(GalleryVo galleryVo, MultipartFile file) {
		String saveDir = "/Users/choijungphil/javaStudy/upload";
		
		//파일 정보(DB저장) 추출 저장
		// 오리지널파일명, 저장경로 + 파일(랜덤)명, 파일사이즈
		String orgName = file.getOriginalFilename();
		
		//확장자만 가져오기
		String exName = orgName.substring(orgName.lastIndexOf("."));
		
		//드라이브에 저장할 파일명
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
		System.out.println(saveName);
		
		//파일경로(디렉토리+저장파일명)
		String filePath = saveDir+ "/"+ saveName;
		
		//파일사이즈
		long filesize = file.getSize();
		
		int userNo = galleryVo.getUserNo(); 
		String content = galleryVo.getContent();
		
		GalleryVo gVo = new GalleryVo(userNo,content,filePath,orgName,saveName,filesize);
		
		//--> dao DB저장
		galleryDao.imgInsert(gVo);
		
		//(2)파일저장
		try {
			byte[] fileData = file.getBytes();
		OutputStream os = new FileOutputStream(filePath);
		BufferedOutputStream bos = new BufferedOutputStream(os);
		
		bos.write(fileData);
		bos.close();
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return saveName;
		
	}
	
	public String imgDelete(int no) {
		String success = galleryDao.imgDelete(no);
		
		return success;
	};
	

}
