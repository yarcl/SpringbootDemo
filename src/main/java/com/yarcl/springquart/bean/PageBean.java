package com.yarcl.springquart.bean;

import lombok.Data;

@Data
public class PageBean {
	private int nowPage;
	private int pageSize;
	private int totalPage;
	private int count;
	public PageBean() {
		super();
		this.nowPage = 1;
		this.pageSize = 6;
		this.totalPage = 0;
		this.count = 0;
	}
	public PageBean(int nowPage, int pageSize, int totalPage, int count) {
		super();
		this.nowPage = nowPage;
		this.pageSize = pageSize;
		this.totalPage = totalPage;
		this.count = count;
	}

	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
		this.setTotalPage(count%pageSize==0?count/pageSize:(count/pageSize+1));
	}
}
