package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GalleryVo;

@Repository
public class GalleryDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<GalleryVo> imgList(){
		List<GalleryVo> imgList = sqlSession.selectList("gallery.selectImgList"); 
		System.out.println(imgList);
		return imgList;
	};
	
	public GalleryVo getImageInfo(GalleryVo galleryVo) {
		System.out.println("다오: "+galleryVo);
		
		return sqlSession.selectOne("gallery.selectImg",galleryVo);
	};
	
	
	public String imgInsert(GalleryVo galleryVo) {
		int count = sqlSession.insert("gallery.imgInsert",galleryVo);
		
		return count + "건을 업로딩하였습니다.";
	};
	
	public String imgDelete(int no) {
		int count = sqlSession.delete("gallery.imgDelete",no);
		return "true";
	};

}
