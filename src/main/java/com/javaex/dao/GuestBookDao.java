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

	// 방명록 목록 불러오기
	public List<GuestBookVo> guestList() {
		List<GuestBookVo> guestList = sqlSession.selectList("guestbook.selectList");
		System.out.println(guestList.toString());
		return guestList;
	}

	// 방명록 글 1개 등록
	public int guestInsert(GuestBookVo guestBookVo) {
		int count = sqlSession.insert("guestbook.guestInsert", guestBookVo);
		
		System.out.println("방명록 " + count + "건을 등록하였습니다.");
		return count;
	}

	// 방명록 글 1개 삭제
	public int guestDelete(int no) {
		int count = sqlSession.delete("guestbook.guestDelete", no);
		System.out.println(no + "번 방명록을 삭제하였습니다.");
		return count;
	}

	// 비밀번호 조회위해 글 하나 조회
	public GuestBookVo getGuest(int no) {
		GuestBookVo guestBookVo = sqlSession.selectOne("guestbook.getGuest", no);
		System.out.println(guestBookVo.toString());
		return guestBookVo;
	}

}
