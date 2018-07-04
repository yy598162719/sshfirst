package com.itheima.crm.domain;

import lombok.Data;
import java.util.List;
/**
 * 分页的实体
 * @param <T>
 */
@Data
public class PageBean<T> {
	private Integer currPage; 	// 当前页
	private Integer pageSize; 	// 每页显示的记录数
	private Integer totalCount; // 总记录数
	private Integer totalPage; 	// 总页数
	private List<T> list;      	// 响应的结果
}