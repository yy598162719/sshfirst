﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<TITLE>添加拜访记录</TITLE> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="${pageContext.request.contextPath }/css/Style.css" type=text/css rel=stylesheet>
<LINK href="${pageContext.request.contextPath }/css/Manage.css" type=text/css
	rel=stylesheet>
<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.11.3.min.js"></script>

<script type="text/javascript">
	$(function(){
		// 异步加载客户的数据
		$.post("${pageContext.request.contextPath }/customer_findAll.action",{},function(data){
			// 遍历json数据：
			$(data).each(function(i,n){
				$("#cust_id").append("<option value='"+n.cust_id+"'>"+n.cust_name+"</option>");
			});
		},"json");
		// 异步加载用户的数据
		$.post("${pageContext.request.contextPath }/user_findAll.action",{},function(data){
			// 遍历json数据：
			$(data).each(function(i,n){
				$("#user_id").append("<option value='"+n.user_id+"'>"+n.user_name+"</option>");
			});
		},"json");
		
	});
</script>
</HEAD>
<BODY>
	<FORM id=form1 name=form1
		action="${pageContext.request.contextPath }/saleVisit_save.action"
		method=post>
		

		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_019.jpg"
						border=0></TD>
					<TD width="100%" background="${pageContext.request.contextPath }/images/new_020.jpg"
						height=20></TD>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_021.jpg"
						border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15 background=${pageContext.request.contextPath }/images/new_022.jpg><IMG
						src="${pageContext.request.contextPath }/images/new_022.jpg" border=0></TD>
					<TD vAlign=top width="100%" bgColor=#ffffff>
						<TABLE cellSpacing=0 cellPadding=5 width="100%" border=0>
							<TR>
								<TD class=manageHead>当前位置：客户拜访管理&gt; 添加拜访记录</TD>
							</TR>
							<TR>
								<TD height=2></TD>
							</TR>
						</TABLE>
						
						<TABLE cellSpacing=0 cellPadding=5  border=0>
						  
						    
							<TR>
								<td>拜访客户名称：</td>
								<td>
									<select id="cust_id" name="customer.cust_id">
										<option value="">-请选择-</option>
									</select>
								</td>
								<td>业务员姓名 ：</td>
								<td>
									<select id="user_id" name="user.user_id">
										<option value="">-请选择-</option>
									</select>
								</td>
							</TR>
							
							<TR>
								
								<td>拜访时间</td>
								<td>
									<INPUT class=textbox id="visit_time"
														style="WIDTH: 180px" maxLength=50 name="visit_time" readonly="readonly">
								</td>
								<td>拜访地点：</td>
								<td>
									<INPUT class=textbox id=sChannel2
														style="WIDTH: 180px" maxLength=50 name="visit_addr">
								</td>
							</TR>
							
							<TR>
								<td>拜访详情 ：</td>
								<td>
								<INPUT class=textbox id=sChannel2
														style="WIDTH: 180px" maxLength=50 name="visit_detail">
								</td>
								<td>下次拜访时间 ：</td>
								<td>
								<INPUT class=textbox id="visit_nexttime"
														style="WIDTH: 180px" maxLength=50 name="visit_nexttime" readonly="readonly">
								</td>
							</TR>
							
							<tr>
								<td rowspan=2>
								<INPUT class=button id=sButton2 type=submit
														value=" 保存 " name=sButton2>
								</td>
							</tr>
						</TABLE>
						
						
					</TD>
					<TD width=15 background="${pageContext.request.contextPath }/images/new_023.jpg">
					<IMG src="${pageContext.request.contextPath }/images/new_023.jpg" border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_024.jpg"
						border=0></TD>
					<TD align=middle width="100%"
						background="${pageContext.request.contextPath }/images/new_025.jpg" height=15></TD>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_026.jpg"
						border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
	</FORM>
</BODY>
</HTML>
