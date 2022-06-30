package com.javaex.service;

import java.util.UUID;

import org.apache.coyote.http11.upgrade.UpgradeServletOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.FileDao;
import com.javaex.vo.FileVo;

@Service
public class FileService {
	
	@Autowired
	private FileDao fileDao;
	
	public void save(MultipartFile file) {
		System.out.println("FileService > save()");
		System.out.println(file.getOriginalFilename());
		
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
		
		FileVo fileVo = new FileVo(orgName,saveName,filePath,filesize);
		System.out.println(fileVo);
		
		//--> dao DB저장
		
		
	}
	

}
