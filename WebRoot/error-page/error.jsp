<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<!-- saved from url=(0025)http://www.funmtv.com/bbs -->
<HTML>
<HEAD>
<base href="<%=basePath%>">
<TITLE>出错了</TITLE>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">

</HEAD>
<BODY leftMargin=0 topMargin=0>
	<TABLE cellSpacing=0 cellPadding=0 width=658 align=center border=0>
		<TBODY>
			<TR>
				<TD><IMG height=430 src="<%=basePath %>error-page/img/fourone.jpg"
					width=381 useMap=#Map border=0 />
				</TD>
				<TD><IMG height=384 src="<%=basePath %>error-page/img/fourtwo.jpg" width=243 border=0 />
				</TD>
			</TR>
		</TBODY>
	</TABLE>
</BODY>
</HTML>
