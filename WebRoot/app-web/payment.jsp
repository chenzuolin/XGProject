﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.xinggang.project.entity.Client" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
    <link rel="stylesheet" type="text/css" href="app-web/css/fenye.css" />
    <link rel="stylesheet" href="app-web/css/bootstrap-datetimepicker.min.css">
    <script src="http://libs.baidu.com/jquery/2.1.1/jquery.min.js"></script>
    <script src="app-web/js/bootstrap.min.js"></script>
    <script src="app-web/js/shoufeijilu.js"></script>
    <script src="app-web/js/bootstrap-datetimepicker.js"></script>
    <script src="app-web/js/bootstrap-datetimepicker.zh-CN.js"></script>
    <style type="text/css">
    .input-group>.input-group-addon {
        background: #337AB7;
        color: #fff;
    }
    
    .modal-dialog
	{
		width:300px;
		margin: 300px auto;
	}
    </style>
    
    <script>
		function tianzhuanmingxi(str) {
			var id = $(str).parents("tr").find(".clientid").val();
			
			document.location.href = "${pageContext.request.contextPath}/accounts.do?flag=getAccountsKHId&accountsId="
					+ id;
		}
	</script>
    
</head>
<script type="text/javascript">
	$(function(){
		$("#logoout").click(function(){
		$("#disable").modal('show');
	});
	});
</script>
<body onload="load();">
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
                   
                    <li>
                        <a class="1ji" href="${pageContext.request.contextPath}/app-web/home.jsp"><i class="fa fa-dashboard "></i>首页</a>
                    </li>
                    <li>
                        <a class="1ji"><i class="fa fa-desktop "></i>仓储业务</i></a>
                        <ol class="nav nav-second-level collapse">
                            <li>
                                <a href="${pageContext.request.contextPath}/app-web/indent-in.jsp"><i class="fa fa-toggle-on"></i>入库订单</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/app-web/indent-out.jsp"><i class="fa fa-bell "></i>出库订单</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/app-web/indent-guo.jsp"><i class="fa fa-circle-o "></i>过户订单</a>
                            </li>
                        </ol>
                    </li>
                    <li>
                        <a class="1ji"><i class="fa fa-paypal "></i>结算中心</a>
                        <ol class="nav jszx collapse">
                            <li>
                                <a href="${pageContext.request.contextPath}/app-web/indent-jiesuan.jsp"><i class="fa fa-coffee"></i>订单结算</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/app-web/jiesuanhuizong.jsp"><i class="fa fa-file-text "></i>结算汇总</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/app-web/payment.jsp"><i class="fa fa-key "></i>缴费记录</a>
                            </li>
                            <!-- 
	                            <li>
	                                <a href="${pageContext.request.contextPath}/app-web/late-fee.jsp"><i class="fa fa-send "></i>滞纳金</a>
	                            </li>
                             -->
                        </ol>
                    </li>
                    <li>
                        <a class="1ji"><i class="fa fa-database "></i>数据中心</a>
                        <ol class="nav nav-second-level collapse">
                            <li>
                                <a href="${pageContext.request.contextPath}/app-web/indent-search.jsp"><i class="fa fa-desktop "></i>订单查询</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/app-web/indent-zuofei.jsp"><i class="fa fa-desktop "></i>作废订单查询</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/app-web/sfc-form.jsp"><i class="fa fa-file-text "></i>收发存报表</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/app-web/inventory.jsp"><i class="fa fa-file-text "></i>库存查询</a>
                            </li>
                        </ol>
                    </li>
                    <li>
                        <a class="1ji"><i class="fa fa-sitemap "></i>系统设置</a>
                        <ol class="nav nav-second-level collapse">
                            <li>
                                <a href="${pageContext.request.contextPath}/client.do?flag=goClientZiliao"><i class="fa fa-user "></i>用户资料</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/app-web/password.jsp"><i class="fa fa-flask "></i>修改密码</a>
                            </li>
                            <li>
                                <a data-toggle="modal" data-target="#disable"><i class="fa fa-flask "></i>注销系统</a>
                            </li>
                        </ol>
                    </li>
                </ol>
            </div>
        </nav>
        <!-- 模态框（Modal） -->
        <div class="modal fade bs-example-modal-sm " id="disable" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                            &times;
                        </button>
                        <h4 class="modal-title" id="myModalLabel">注销系统</h4>
                    </div>
                    <div class="modal-body">
                        <h3>确定要退出吗？</h3>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                        </button>
                        <a class="btn btn-primary" href="${pageContext.request.contextPath}/loginAll.do?flag=kehuZhuxiao"/>确定</a>
                    </div>
                </div>
                <!-- /.modal-content -->
            </div>
            <!-- /.modal -->
        </div>
      <!-- 模态框结束 --> 
        <!-- 以上是左侧菜单 -->
        <div class="wrapper-inner">
            <ol class="breadcrumb">
                <span>当前位置：</span>
                <li><a>结算中心</a></li>
                <li><a href="${pageContext.request.contextPath}/app-web/indent-jiesuan.jsp">缴费记录</a></li>
            </ol>
            <h3>缴费记录</h3>
            <form class="container" style="width: auto; height: auto;">
                <div class='col-md-4 col-xs-6 form-group'>
                    <div class="input-group">
                        <input class="form-control" type="text" id="startTime" placeholder="开始日期" />
                        <label class="glyphicon glyphicon-calendar input-group-addon" for="startTime"></label>
                    </div>
                </div>
                <div class='col-md-4 col-xs-6 form-group'>
                    <div class='input-group date'>
                        <input class="form-control" type="text" id="endTime" placeholder="结束日期" />
                        <label class="glyphicon glyphicon-calendar input-group-addon" for="endTime"></label>
                    </div>
                </div>
                <a type="button" class="btn btn-warning" style="float: right; margin-right: 15px;">提交</a>
            </form>
            <div class="chearfix"></div>
            <table class="table table-hover">
                <caption>201707/01-2017/07/31缴费记录（元）</caption>
                <thead>
                    <tr>
						<th>客户名称</th>
						<th>起始日期</th>
						<th>结束日期</th>
						<th>轧账费用合计</th>
						<th>滞纳金费率</th>
						<th>应收滞纳金</th>
						<th>总费用</th>
						<th>收费人</th>
						<th>收费时间</th>
					</tr>
                </thead>
                <tbody id="neirong">
                                       
                </tbody>
            </table>
            <div class="page_show" style=" margin-top: 20px;">
				<input type="hidden" value="1" id="pageNow" />
				<div class="shang">
				<span id="shouye">首页</span> <span id="shang">上一页</span>
			</div>
			<div class="pageNow">
			<input type="text" size="2" autocomplete="off" id="yeshu" /><span
				id="go">Go</span>
			</div>
			<div class="xia">
				<span id="xia">下一页</span> <span id="weiye">尾页</span>
			</div>
		</div>
        </div>
        <!-- 以上是右侧内容页 -->
    </div>
    <!--以下是底部导航 -->
    <div class="container foot">
        <ul class="nav nav-pills nav-justified">
            <li class="active">
                <a href="#"><i class="fa fa-home fa-2x"><span class="wenzi">首页</span></i></a>
            </li>
            <li>
                <a href="#"><i class="fa fa-truck fa-2x"><span class="wenzi">仓储</span></i></a>
            </li>
            <li>
                <a href="#"><i class="fa fa-paypal fa-2x"><span class="wenzi">结算</span></i></a>
            </li>
            <li>
                <a href="#"><i class="fa fa-file-text fa-2x"><span class="wenzi">报表</span></i></a>
            </li>
            <li>
                <a href="mui/examples/setting.html"><i class="fa fa-user fa-2x"><span class="wenzi">我的</span></i></a>
            </li>
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
    $("ol.jszx").slideDown();

});
</script>

<script>
	$("#startTime").datetimepicker({
		minView : "month",
		language : 'zh-CN',
		format : 'yyyy-mm-dd',
		autoclose : true,
		todayBtn : "linked",
		todayHighlight : true,
		pickerPosition : "bottom-left"
	}).on('changeDate', function(ev) {
		var starttime = $("#startTime").val();
		$("#endTime").datetimepicker('setStartDate', starttime);
		$("#startTime").datetimepicker('hide');
	});

	$("#endTime").datetimepicker({
		minView : "month",
		language : 'zh-CN',
		format : 'yyyy-mm-dd',
		autoclose : true,
		todayBtn : true,
		pickerPosition : "bottom-left"
	}).on('changeDate', function(ev) {
		var starttime = $("#startTime").val();
		var endtime = $("#endTime").val();
		$("#startTime").datetimepicker('setEndDate', endtime);
		$("#endTime").datetimepicker('hide');
	});
</script>
</html>
