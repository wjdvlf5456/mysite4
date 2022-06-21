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
	
	public UserVo getUser(UserVo userVo) {
		UserVo authUser = sqlSession.selectOne("user.login",userVo);
		return authUser;
	}
	
	public UserVo maintain(int no) {
		UserVo authUser = sqlSession.selectOne("user.session",no);
		return authUser;
	}
	
	public UserVo getUser(int no) {
		UserVo userVo = sqlSession.selectOne("user.getUser",no);
		return userVo;
	}
	
	
	
	public int userInsert(UserVo userVo) {
		int count = sqlSession.insert("user.userInsert",userVo);
		System.out.println(count + "건이 등록되었습니다.");
		return count;
	}
	
	public int userDelete(int no) {
		int count = sqlSession.delete("user.userDelete",no);
		System.out.println(count + "건이 삭제되었습니다.");
		return count;
	}
	
	public int userUpdate(UserVo userVo) {
		int count = sqlSession.update("user.userUpdate",userVo);
		System.out.println(count + "건이 수정되었습니다.");
		return count;
		
	}

}
