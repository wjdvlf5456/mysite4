package com.javaex.vo;

public class RboardVo {
	
	//필드
	private int no;
	private int userNo;
	private String title;
	private String content;
	private int hit;
	private String regDate;
	private int groupNo;
	private int orderNo;
	private int depth;
	private String name;

	//생성자
	public RboardVo() {
		
	}
	public RboardVo(int no) {
		this.no = no;
	}
	public RboardVo(int no, int userNo, String title, String content, String regDate, int groupNo, int orderNo,int depth, String name) {
		this.no = no;
		this.userNo = userNo;
		this.title = title;
		this.content = content;
		this.regDate = regDate;
		this.groupNo = groupNo;
		this.orderNo = orderNo;
		this.depth = depth;
		this.name = name;
	}
	public RboardVo(int no, int userNo, String title, String content, int hit, String regDate, int groupNo, int orderNo,int depth, String name) {
		this.no = no;
		this.userNo = userNo;
		this.title = title;
		this.content = content;
		this.hit = hit;
		this.regDate = regDate;
		this.groupNo = groupNo;
		this.orderNo = orderNo;
		this.depth = depth;
		this.name = name;
	}


	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public int getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	//메소드 - 일반
	@Override
	public String toString() {
		return "RboardVo [no=" + no + ", userNo=" + userNo + ", title=" + title + ", content=" + content + ", hit="
				+ hit + ", regDate=" + regDate + ", groupNo=" + groupNo + ", orderNo=" + orderNo + ", depth=" + depth
				+ ", name=" + name + "]";
	}
	
}
