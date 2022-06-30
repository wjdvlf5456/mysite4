package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.FileVo;

@Repository
public class FileDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public String fileInsert(FileVo fileVo) {
		
		int count = sqlSession.insert("fileInsert",fileVo);
		
		return count + "개의 파일을 저장하였습니다.";
	}
	

}
