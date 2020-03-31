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
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<!--让IE浏览器用最高级内核渲染页面 还有用 Chrome 框架的页面用webkit内核-->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!--为移动设备添加 viewport-->
<title>仓储管理系统登陆</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="format-detection" content="telephone=no" />
<!--禁止数字识自动别为电话号码-->
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--禁止百度转码-->
<link rel="shortcut icon" type="image/ico" href="/favicon.ico" />
<!--添加 favicon icon -->
<link rel="stylesheet" href="app-web/css/bootstrap.min.css" />
<link rel="stylesheet" href="app-web/css/public.css" />
<link rel="stylesheet" href="app-web/css/font-awesome.min.css">
<link rel="stylesheet" href="app-web/css/sidebar.css">


<script src="http://libs.baidu.com/jquery/2.1.1/jquery.min.js"></script>
<script src="app-web/js/bootstrap.min.js"></script>


<style type="text/css">
.selectize-control.demo-default.single {
	height: 34px;
}

.panel-primary{
		width: 910px;
		float: right;
}

.input-group>.input-group-addon {
	background: #337AB7;
	color: #fff;
}

.table-hover {
	font-size: 16px;
	font-weight: bold;
}

.modal-dialog
{
	width:300px;
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
                    <li>
                        <a class="1ji" href="${pageContext.request.contextPath}/app-web/home.jsp"><i class="fa fa-dashboard "></i>首页</a>
                    </li>
                    <li>
                        <a class="1ji"><i class="fa fa-desktop "></i>仓储业务</i></a>
                        <ol class="nav ccyw collapse">
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
                        <ol class="nav sjzx collapse">
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
		<div class="wrapper-inner">
			<ol class="breadcrumb">
				<span>当前位置：</span>
				<li>结算中心</li>
				<li><a href="javascript:window.history.go(-1);">返回上一级</a></li>
				<li onclick="javascript:document.location.reload();"
					style="cursor:pointer;">收费明细</li>
			</ol>
			<h3>收费明细</h3>
		</div>

		<div class="panel panel-primary">
			<div class="panel-heading">
				<p class="panel-title" style="text-align: center; font-size: 18px;">收费明细</p>
			</div>
			<div class="panel-body">
				<table class="table table-hover">
					<tr>
						<td>客户名称</td>
						<td style="border-right:1px solid #888888;">${ac.client.clientAbbreviation
							}</td>
						<td style="color: red;">轧账费用合计</td>
						<td style="color: red;">${ac.accountsExpensesSeed }元</td>
												
					</tr>
					<tr>
						<td>起始日期</td>
						<td style="border-right:1px solid #888888;">${ac.accountsCreateTime }</td>
						<td>结算时间</td>
						<td>${ac.jiesuantime
							}</td>					
					</tr>
					<tr>
						<td>结束日期</td>
						<td style="border-right:1px solid #888888;">${ac.accountsFinishTime
							}</td>
						<td>滞纳金起征时间</td>
						<td>${ac.shoufeiqixian}</td>
					</tr>
					<tr>
						<td>汽运入库费</td>
						<td style="border-right:1px solid #888888;">${ac.rukuCost }元</td>
						<td>滞纳金结束时间</td>
						<td>${ac.accountsCollectTime==null?"无":ac.accountsCollectTime
							}</td>	
					</tr>
					<tr>
						<td>火运入库费</td>
						<td style="border-right:1px solid #888888;">${ac.zidingyiFour
							}元</td>			
						<td>滞纳金费率</td>
						<td>${ac.zhinaFeilv
							}元</td>
					</tr>
					<tr>
						<td>汽运出库费</td>
						<td style="border-right:1px solid #888888;">${ac.zidingyiFive }元</td>
						<td style="color: red;">滞纳金合计</td>
						<td style="color: red;">${ac.accountsSeed==null?0.0:ac.accountsSeed
							}元</td>
					</tr>
					<tr>
						<td>火运出库费</td>
						<td style="border-right:1px solid #888888;">${ac.zidingyiSix
							}元</td>
						<td style="color: red;">总费用合计</td>
						<td >${ac.accountsSeed+ac.accountsExpensesSeed
							}元</td>
					</tr>
					<tr>
						<td>过户费</td>
						<td style="border-right:1px solid #888888;">${ac.guohuCost }元</td>
						<td>实收费用</td>
						<td>${ac.shishouCost==null?0:ac.shishouCost }元</td>
						
					</tr>					
					<tr>
						<td>下站费（出）</td>
						<td style="border-right:1px solid #888888;">${ac.chukumaxtime }元</td>
						<td>支付方式</td>
						<td>${ac.accountsDefinedTwo==null?"无":ac.accountsDefinedTwo.pfashionName
							}</td>					
					</tr>					
					<tr>
						<td>下站费（过）</td>
						<td style="border-right:1px solid #888888;">${ac.zhuanchukumaxtime }元</td>
						<td>票据类型</td>
						<td>${ac.accountsDefinedTwo==null?"无":ac.accountsDefinedTwo.pfashionReceipt
							}</td>
					</tr>
					<tr>
						<td>仓储费</td>
						<td style="border-right:1px solid #888888;">${ac.cangchuCost
							}元</td>
						<td>收费时间</td>
						<td>${ac.accountsCollectTime==null?"无":ac.accountsCollectTime
						}</td>															
					</tr>
					<tr>
						<td>备注</td>
						<td style="color: red; border-right:1px solid #888888;">${ac.accountsRemark }</td>
						<td></td>
						<td></td>
					</tr>
				</table>
			</div>
		</div>
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

</html>
