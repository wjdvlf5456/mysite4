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
	
	public List<FileVo> fileList(){
		
		return sqlSession.selectList("selectFileList"); 
	}

}
