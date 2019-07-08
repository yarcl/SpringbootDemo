package com.yarcl.springquart.bean.util;

import lombok.Data;

import java.util.List;

@Data
public class PageBean<T> {
	private int nowPage = 0;
	private int pageSize = 6;
	private int totalPage = 0;
	private int count = 0;

	private List<T> object;

	public PageBean(int nowPage, int pageSize, int count, List<T> t) {
		this.nowPage = nowPage;
		this.pageSize = pageSize;
		this.setCount(count);
		this.object = t;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
		this.setTotalPage(count%pageSize==0?count/pageSize:(count/pageSize+1));
	}
}
