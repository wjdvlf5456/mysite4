package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<UserVo> getUserList(){
		System.out.println("UserDao > getUserList");
		List<UserVo> userList = sqlSession.selectList("user.selectList");
		
		return userList;
	}

}
