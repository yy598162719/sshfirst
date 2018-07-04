﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<TITLE>添加客户</TITLE> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="${pageContext.request.contextPath }/css/Style.css" type=text/css rel=stylesheet>
<LINK href="${pageContext.request.contextPath }/css/Manage.css" type=text/css
	rel=stylesheet>

<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
	$(function(){
		// 初始化客户级别的下拉框
		$.post("${pageContext.request.contextPath}/baseDict_findByDictTypeCode.action",{'dict_type_code':'006'},function(data){
			/* alert(data);
			// 如果传递过来的是一个文本字符串，可以使用eval函数将字符串转换成json
			data = eval("("+data+")");
			alert(data); */
			$(data).each(function(index,domEle){
				// alert(domEle.dict_id+"     "+domEle.dict_item_name);
				$("#cust_level").append("<option value='"+domEle.dict_id+"'>"+domEle.dict_item_name+"</option>");
			})
			// 使用EL表达式（可以放置到js写 ）
			$("#cust_level option[value=${baseDictLevel.dict_id}]").prop("selected",true);
		},"json");
		// 初始化客户来源的下拉框
		$.post("${pageContext.request.contextPath}/baseDict_findByDictTypeCode.action",{'dict_type_code':'002'},function(data){
			$(data).each(function(index,domEle){
				$("#cust_source").append("<option value='"+domEle.dict_id+"'>"+domEle.dict_item_name+"</option>");
			})
			$("#cust_source option[value=${baseDictSource.dict_id}]").prop("selected",true);
		},"json");
		// 初始化客户所属行业的下拉框
		$.post("${pageContext.request.contextPath}/baseDict_findByDictTypeCode.action",{'dict_type_code':'001'},function(data){
			$(data).each(function(index,domEle){
				$("#cust_industry").append("<option value='"+domEle.dict_id+"'>"+domEle.dict_item_name+"</option>");
			})
			$("#cust_industry option[value=${baseDictIndustry.dict_id}]").prop("selected",true);
		},"json");
	})
</script>
<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
</HEAD>
<BODY>
	<FORM id=form1 name=form1
		action="${pageContext.request.contextPath }/customer_update.action"
		method="post" enctype="multipart/form-data">
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_019.jpg"
						border=0></TD>
					<TD width="100%" background=${pageContext.request.contextPath }/images/new_020.jpg
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
								<TD class=manageHead>当前位置：客户管理 &gt; 修改客户</TD>
							</TR>
							<TR>
								<TD height=2></TD>
							</TR>
						</TABLE>
						<TABLE cellSpacing=0 cellPadding=5  border=0>
							<!-- 存放id -->
							<s:hidden name="cust_id"></s:hidden>
							<!-- 存放图片的路径 -->
							<s:hidden name="cust_image"></s:hidden>
							<TR>
								<td>客户名称：</td>
								<td>
								<s:textfield name="cust_name" cssClass="textbox" id="cust_name"
									cssStyle="WIDTH: 180px" maxlength="50"></s:textfield>
								</td>
								<td>客户级别 ：</td>
								<td>
									<select id="cust_level" name="baseDictLevel.dict_id">
										<option value="">--请选择--</option>
									</select>
								</td>
							</TR>
							
							<TR>
								<td>信息来源：</td>
								<td>
									<select id="cust_source" name="baseDictSource.dict_id">
										<option value="">--请选择--</option>
									</select>
								</td>
								<td>所属行业：</td>
								<td>
									<select id="cust_industry" name="baseDictIndustry.dict_id">
										<option value="">--请选择--</option>
									</select>
								</td>
							</TR>
							<TR>
								
								
								<td>固定电话 ：</td>
								<td>
									<s:textfield name="cust_phone" cssClass="textbox" id="cust_phone"
										cssStyle="WIDTH: 180px" maxlength="50">
									</s:textfield>
								</td>
								<td>移动电话 ：</td>
								<td>
									<s:textfield name="cust_mobile" cssClass="textbox" id="cust_mobile"
										cssStyle="WIDTH: 180px" maxlength="50">
									</s:textfield>
								</td>
							</TR>
							<TR>
								<td>上传图片资料 ：</td>
								<td colspan="3">
									<INPUT type="file" style="WIDTH: 180px" maxLength=50 name="upload">
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
