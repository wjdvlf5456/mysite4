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

import com.javaex.dao.FileDao;
import com.javaex.vo.FileVo;

@Service
public class FileService {
	
	@Autowired
	private FileDao fileDao;
	
	public List<FileVo> fileList(){
		
		List<FileVo> fList = fileDao.fileList(); 
		
		return fList;
	}
	
	
	public String save(MultipartFile file) {
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
		fileDao.fileInsert(fileVo);
		
		
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
	

}
