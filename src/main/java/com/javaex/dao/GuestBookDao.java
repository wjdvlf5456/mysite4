package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestBookVo;

@Repository
public class GuestBookDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<GuestBookVo> guestList(){
		List<GuestBookVo> guestList = sqlSession.selectList("guestbook.selectList");
		return guestList;
	}
	
	public int guestInsert(GuestBookVo guestBookVo) {
		int count = sqlSession.insert("guestbook.guestInsert",guestBookVo);
		return count;
		
	}
	public int guestDelete(int no, String password) {
		
		int count = sqlSession.delete("guestbook.guestDelete",no);
		
		return count;
		
	}

}
