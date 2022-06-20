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
	
	//회원정보 목록 불러오기
	public List<UserVo> getUserList(){
		System.out.println("UserDao > getUserList");
		List<UserVo> userList = sqlSession.selectList("user.selectList");
		System.out.println(userList.toString());
		
		return userList;
	}
	
	public UserVo getUser(int no) {
		UserVo userVo = sqlSession.selectOne("user.getUser",no);
		System.out.println(userVo.toString());
		return userVo;
	}
	
	public int userInsert(UserVo userVo) {
		int count = sqlSession.insert("user.userInsert",userVo);
		System.out.println(count + "건이 등록되었습니다.");
		return count;
	}

}
