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

	public List<UserVo> getUserList() {
		return userDao.getUserList();
	}

	public UserVo login(UserVo userVo) {
		UserVo authUser = userDao.getUser(userVo);
		return authUser;
	}

	public UserVo getUser(int no) {
		UserVo userVo = userDao.getUser(no);
		return userVo;
	}

	public int userInsert(UserVo userVo) {
		int count = userDao.userInsert(userVo);
		return count;
	}
	
	public int userCheck(String id) {
		
		
		
		return 1;
	}
	
	

	public int userDelete(int no) {
		int count = userDao.userDelete(no);
		return count;
	}

	public int userUpdate(UserVo userVo) {
		int count = userDao.userUpdate(userVo);
		return count;
	}

}
