package com.itheima.crm.domain;

import java.util.List;

/**
 * 分页的实体
 * @param <T>
 */
public class PageBean<T> {
	private Integer currPage; 	// 当前页
	private Integer pageSize; 	// 每页显示的记录数
	private Integer totalCount; // 总记录数
	private Integer totalPage; 	// 总页数
	private List<T> list;      	// 响应的结果
	public Integer getCurrPage() {
		return currPage;
	}
	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	
}