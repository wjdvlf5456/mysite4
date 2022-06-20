package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	public List<UserVo> getUserList(){
		return userDao.getUserList();
	}
	
	public UserVo getUser(int no) {
		return userDao.getUser(no);
	}
	
	public int userInsert(UserVo userVo) {
		int count = userDao.userInsert(userVo);
		return count;
	}
	
}
