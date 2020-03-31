<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.xinggang.project.entity.Client" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html lang="en">

<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<!-- This file has been downloaded from bootstrap.cn. Enjoy! -->
<title>鑫港库存管理系统</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="app-web/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="app-web/css/font-awesome.min.css">
<link rel="stylesheet" href="app-web/css/sidebar.css">
<script src="http://libs.baidu.com/jquery/2.1.1/jquery.min.js"></script>
<script src="app-web/js/bootstrap.min.js"></script>
<script type="text/javascript">
    
    	//实现进度条
    	$(function(){    
    		$(".btn").bind("mouseover", function(){ 
			 	diaoyong();
			 });
        
			 $("#oldPwd").bind("change mouseleave mouseover", function(){ 
			 	diaoyong();
			 });
			 
			 $("#newPwd").bind("change mouseleave mouseover", function(){ 
			 	diaoyong();
			 });
			 
			 $("#okPwd").bind("change mouseleave mouseover", function(){ 
			 	diaoyong();
			 });
                        
    	});
    	
    	function diaoyong(){
    		if($("#oldPwd").val()!="" && $("#newPwd").val()=="" && $("#okPwd").val()==""){
    		 		$("#jingdu").animate({
           				 width:"33%"
        			});
    		 	}
    		 	if($("#oldPwd").val()=="" && $("#newPwd").val()!="" && $("#okPwd").val()==""){
    		 		$("#jingdu").animate({
           				 width:"33%"
        			});
    		 	}
    		 	if($("#oldPwd").val()=="" && $("#newPwd").val()=="" && $("#okPwd").val()!=""){
    		 		$("#jingdu").animate({
           				 width:"33%"
        			});
    		 	}
    		 	
    		 	if($("#oldPwd").val()!="" && $("#newPwd").val()=="" && $("#okPwd").val()!=""){
    		 		$("#jingdu").animate({
           				 width:"66%"
        			});
    		 	}
    		 	if($("#oldPwd").val()=="" && $("#newPwd").val()!="" && $("#okPwd").val()!=""){
    		 		$("#jingdu").animate({
           				 width:"66%"
        			});
    		 	}
    		 	if($("#oldPwd").val()!="" && $("#newPwd").val()!="" && $("#okPwd").val()==""){
    		 		$("#jingdu").animate({
           				 width:"66%"
        			});
    		 	}
    		 	if($("#oldPwd").val()!="" && $("#newPwd").val()!="" && $("#okPwd").val()!=""){
    		 		$("#jingdu").animate({
           				 width:"100%"
        			});
    		 	}
    		 	if($("#oldPwd").val()=="" && $("#newPwd").val()=="" && $("#okPwd").val()==""){
    		 		$("#jingdu").animate({
           				 width:"0%"
        			});
    		 	}
    	}
    	
		function yanzhen(){
			//密码验证，有数字、字母下划线组成并且6-12位
			$reg_password = /^[a-zA-Z0-9_]{6,12}$/;
			
			if($("#oldPwd").val()=="")
			{
				alert("当前密码不能为空！");
				return false;
			}
			if(!$reg_password.test($("#newPwd").val()))
			{
				alert("新密码输入有误，只能是字母数字下划线6-12位");
				return false;
			}
			if($("#okPwd").val()!=$("#newPwd").val())
			{
				alert("新密码与确认密码输入不一致！");
				return false;
			}
			
			return true;
		}
    </script>

<style type="text/css">
.modal-dialog {
	width: 300px;
	margin: 300px auto;
}
</style>
</head>
<script type="text/javascript">
	$(function(){
		$("#logoout").click(function(){
		$("#disable").modal('show');
	});
	});
</script>
<body>
	<div class="container">
		<div class="header">
            <a class="brand" href="${pageContext.request.contextPath}/app-web/home.jsp"><img src="app-web/img/logo.png" width="150px" style="margin:-10px 0 10px 0;" /></a>
            <i class="fa fa-bars fa-2x toggle-btn" data-toggle="collapse" data-target="#main-menu"></i>
           <i class="fa fa-power-off fa-2x" style="float:right;font-size:20px; margin:30px 50px 0 0; color:white; cursor:pointer;" id="logoout">
           <span style="font-size:16px; color:white; margin-left:5px; display:block; float:right; cursor:pointer;">注销</span></i>
            <% Client c = (Client)request.getSession().getAttribute("client"); %>
            <a href="${pageContext.request.contextPath}/client.do?flag=goClientZiliao">
            <i class="fa fa-user fa-2x" style="float:right; font-size:20px; margin:30px 50px 0 0; color:white;"><span style="font-size:16px; color:white; margin-left:5px; 
            display:block; float:right; cursor:pointer;">
            <%=c.getClientFirmName() %></span></i></a>
        </div>
		<!-- 以上是头部 -->
		<nav class="navbar-default navbar-side" role="navigation">
			<div class="sidebar-collapse">
				<ol class="nav collapse " id="main-menu">
					<!-- <li>
                        <div class="user-img-div">
                            <img src="assets/img/user.png" class="img-thumbnail" />
                            <div class="inner-text">
                                Jhon Deo Alex
                                <br />
                                <small>Last Login : 2 Weeks Ago </small>
                            </div>
                        </div>
                    </li> -->
					<li><a class="1ji"
						href="${pageContext.request.contextPath}/app-web/home.jsp"><i
							class="fa fa-dashboard "></i>首页</a></li>
					<li><a class="1ji"><i class="fa fa-desktop "></i>仓储业务</i>
					</a>
						<ol class="nav ccyw collapse">
							<li><a
								href="${pageContext.request.contextPath}/app-web/indent-in.jsp"><i
									class="fa fa-toggle-on"></i>入库订单</a></li>
							<li><a
								href="${pageContext.request.contextPath}/app-web/indent-out.jsp"><i
									class="fa fa-bell "></i>出库订单</a></li>
							<li><a
								href="${pageContext.request.contextPath}/app-web/indent-guo.jsp"><i
									class="fa fa-circle-o "></i>过户订单</a></li>
						</ol></li>
					<li><a class="1ji"><i class="fa fa-paypal "></i>结算中心</a>
						<ol class="nav jszx collapse">
							<li><a
								href="${pageContext.request.contextPath}/app-web/indent-jiesuan.jsp"><i
									class="fa fa-coffee"></i>订单结算</a></li>
							<li><a
								href="${pageContext.request.contextPath}/app-web/jiesuanhuizong.jsp"><i
									class="fa fa-file-text "></i>结算汇总</a></li>
							<li><a
								href="${pageContext.request.contextPath}/app-web/payment.jsp"><i
									class="fa fa-key "></i>缴费记录</a></li>
							<!-- 
	                            <li>
	                                <a href="${pageContext.request.contextPath}/app-web/late-fee.jsp"><i class="fa fa-send "></i>滞纳金</a>
	                            </li>
                             -->
						</ol></li>
					<li><a class="1ji"><i class="fa fa-database "></i>数据中心</a>
						<ol class="nav sjzx collapse">
							<li><a
								href="${pageContext.request.contextPath}/app-web/indent-search.jsp"><i
									class="fa fa-desktop "></i>订单查询</a></li>
							<li><a
								href="${pageContext.request.contextPath}/app-web/indent-zuofei.jsp"><i
									class="fa fa-desktop "></i>作废订单查询</a></li>
							<li><a
								href="${pageContext.request.contextPath}/app-web/sfc-form.jsp"><i
									class="fa fa-file-text "></i>收发存报表</a></li>
							<li><a
								href="${pageContext.request.contextPath}/clientGoods.do?flag=getClientAllInfo"><i
									class="fa fa-file-text "></i>库存查询</a></li>
						</ol></li>
					<li><a class="1ji"><i class="fa fa-sitemap "></i>系统设置</a>
						<ol class="nav xtsz collapse">
							<li><a
								href="${pageContext.request.contextPath}/client.do?flag=goClientZiliao"><i
									class="fa fa-user "></i>用户资料</a></li>
							<li><a
								href="${pageContext.request.contextPath}/app-web/password.jsp"><i
									class="fa fa-flask "></i>修改密码</a></li>
							<li><a data-toggle="modal" data-target="#disable"><i
									class="fa fa-flask "></i>注销系统</a> <!-- 模态框（Modal） -->
								</li>
						</ol></li>
				</ol>
			</div>
		</nav>
		<div class="modal fade bs-example-modal-sm " id="disable"
			tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
			aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">注销系统</h4>
					</div>
					<div class="modal-body">
						<h3>确定要退出吗？</h3>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default"
							data-dismiss="modal">关闭</button>
						<a class="btn btn-primary"
							href="${pageContext.request.contextPath}/loginAll.do?flag=kehuZhuxiao" />确定</a>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div> <!-- 模态框结束 -->
		<!-- 以上是左侧菜单 -->
		<div class="wrapper-inner">
			<ol class="breadcrumb">
				<span>当前位置：</span>
				<li><a>系统设置</a>
				</li>
				<li><a href="javascript:document.location.reload();">修改密码</a>
				</li>
			</ol>
			<h3>修改密码</h3>

			<div class="progress progress-striped active">
				<div class="progress-bar progress-bar-success" id="jingdu"
					role="progressbar" aria-valuenow="60" aria-valuemin="0"
					aria-valuemax="100" style="width: 0%;">
					<span class="sr-only">40% 完成</span>
				</div>
			</div>
			<form name="clientForm"
				action="${pageContext.request.contextPath}/client.do?flag=updatePwd"
				method="post" onsubmit="return yanzhen();">
				<div class="input-group form-group col-md-6">
					<span class="input-group-addon">当前密码</span> <input type="password"
						name="oldPwd" id="oldPwd" class="form-control bar"
						placeholder="当前密码">
				</div>
				<br>
				<div class="input-group form-group col-md-6">
					<span class="input-group-addon">新密码</span> <input type="password"
						class="form-control bar" id="newPwd" name="clientPassword"
						placeholder="新密码">
				</div>
				<br>
				<div class="input-group form-group col-md-6">
					<span class="input-group-addon">确认密码</span> <input type="password"
						class="form-control bar" id="okPwd" name="okPwd"
						placeholder="确认密码">
				</div>
				<input type="submit" class="btn btn-primary"
					style="float: right; margin-right: 15px;" value="提交">
			</form>

		</div>
		<!-- 以上是右侧内容页 -->
	</div>
	<!--以下是底部导航 -->
	<div class="container foot">
		<ul class="nav nav-pills nav-justified">
			<li class="active"><a href="#"><i class="fa fa-home fa-2x"><span
						class="wenzi">首页</span>
				</i>
			</a></li>
			<li><a href="#"><i class="fa fa-truck fa-2x"><span
						class="wenzi">仓储</span>
				</i>
			</a></li>
			<li><a href="#"><i class="fa fa-paypal fa-2x"><span
						class="wenzi">结算</span>
				</i>
			</a></li>
			<li><a href="#"><i class="fa fa-file-text fa-2x"><span
						class="wenzi">报表</span>
				</i>
			</a></li>
			<li><a href="mui/examples/setting.html"><i
					class="fa fa-user fa-2x"><span class="wenzi">我的</span>
				</i>
			</a></li>
		</ul>
	</div>
</body>
<script>
$(document).ready(function() {
    $("a.1ji").click(function() {
        $(this).next("ol").slideToggle()
            // alert("你进入了 p1!");
            // $(this).css("color", "red")

    });
    $("ol.xtsz").slideDown();

});
</script>

</html>
