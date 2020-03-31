<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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

<style type="text/css">
.modal-dialog {
	width: 300px;
	margin: 300px auto;
}

.modal-dialog {
	width: 400px;
	margin: 300px auto;
}

.bj {
	margin-left: 700px;
}
</style>

<script type="text/javascript">
    	$(function(){
			$("#bianjiOK").click(
		
				function fun(){
					//邮箱验证
					$reg_mail = /^\w+@\w+(\.[a-zA-Z]{2,3}){1,2}$/;
					//手机号验证
					$reg_phone = /^1\d{10}$/;
					       
					if($("#address").val()==""){
						alert("地址不能为空");
						return false;
					}
					if(!$reg_mail.test($("#email").val()))
					{
						alert("邮箱输入有误");
						return false;
					}
					if(!$reg_phone.test($("#phone").val()))
					{
						alert("联系电话输入有误，必须是11位");
						return false;
					}   
					return true;
				}
			);
		});
    </script>
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
									class="fa fa-flask "></i>注销系统</a> </li>
						</ol></li>
				</ol>
			</div>
		</nav>
		
		<!-- 模态框（Modal） -->
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
				<li><a href="javascript:document.location.reload();">用户资料</a>
				</li>
			</ol>
			<h3>
				企业资料 <span class="bj"><a data-toggle="modal"
					class="btn btn-warning" href="#mol_bainji">编辑</a>
				</span>
			</h3>
			<div class="panel panel-primary">
				<div class="panel-heading">
					<p class="panel-title">
						企业信息：
						<!-- <a style="font-weight: bold;">订单编号:20170606003
                        <span class="glyphicon glyphicon-chevron-down" style="float: right;"></span>
                        </a> -->
						<!--  <span class="glyphicon glyphicon-chevron-down" style="float: right;"></span> -->
					</p>
				</div>
				<div class="panel-body">
					<div class="from-group col-md-6">
						<table class="table table-striped" style="text-align: left;">
							<tr>
								<td>单位全称：</td>
								<td>${client.clientFirmName }</td>
							</tr>
							<tr>
								<td>单位简称：</td>
								<td>${client.clientAbbreviation }</td>
							</tr>
							<tr>
								<td>单位助记符</td>
								<td>${client.clientSign }</td>
							</tr>
							<tr>
								<td>注册时间：</td>
								<td>${client.clientCreateTime }</td>
							</tr>
							<tr>
								<td>合同号：</td>
								<td>${client.clientContract }</td>
							</tr>
							<tr>
								<td>地址：</td>
								<td>${client.clientAddress }</td>
							</tr>
						</table>
					</div>
					<div class="from-group col-md-6">
						<table class="table table-striped" style="text-align: left;">
							<tr>
								<td>合同起始日期：</td>
								<td>${client.clientStartTime }</td>
							</tr>
							<tr>
								<td>合同结束日期：</td>
								<td>${client.clientFinishTime }</td>
							</tr>
							<%-- <tr>
                                <td>结算方式：</td>
                                <td>${client.clientAccounts }</td>
                            </tr>
                            <tr>
                                <td>折扣类型：</td>
                                <td>${client.clientAgio }</td>
                            </tr>
                            <tr>
                                <td>备注：</td>
                                <td>${client.clientRemark }</td>
                            </tr> --%>
						</table>
					</div>
				</div>
			</div>
			<div class="panel panel-primary">
				<div class="panel-heading">
					<p class="panel-title">
						负责人信息：
						<!-- <a style="font-weight: bold;">订单编号:20170606003
                        <span class="glyphicon glyphicon-chevron-down" style="float: right;"></span>
                        </a> -->
						<!--  <span class="glyphicon glyphicon-chevron-down" style="float: right;"></span> -->
					</p>
				</div>
				<div class="panel-body">
					<div class="from-group col-md-6">
						<table class="table table-striped" style="text-align: left;">
							<tr>
								<td>登录账号：</td>
								<td>${client.clientLoginName }</td>
							</tr>
							<tr>
								<td>密码：</td>
								<td>************</td>
								<%-- ${client.clientPassword } --%>
								<td>
									<!-- <a type="button" class="btn btn-primary btn-xs">修改</a></td> -->
							</tr>
							<tr>
								<td>邮箱：</td>
								<td>${client.clientEmail }</td>
							</tr>
							<tr>
								<td>负责人：</td>
								<td>${client.clientHuman }</td>
							</tr>
						</table>
					</div>
					<div class="from-group col-md-6">
						<table class="table table-striped" style="text-align: left;">
							<tr>
								<td>手机电话：</td>
								<td>${client.clientTel }</td>
							</tr>
							<!-- <tr>
                                <td>固定电话：</td>
                                <td>13109315688</td>
                            </tr> -->
							<tr>
								<td>身份证号码：</td>
								<td>${fn:replace(client.clientStatusNumber,fn:substring(client.clientStatusNumber,4,16),'************')}</td>
								<!--  -->
							</tr>

						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--  <a type="button" class="btn btn-primary" style="float: right; margin-right: 15px;">提交</a> -->

	<!-- 以上是右侧内容页 -->

	<!--以下是底部导航 -->
	<div class="container foot">
		<ul class="nav nav-pills nav-justified">
			<li class="active"><a
				href="${pageContext.request.contextPath}/app-web/home.jsp"><i
					class="fa fa-home fa-2x"><span class="wenzi">首页</span>
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

	<!-- 编辑资料模态框（Modal） -->
	<div class="modal fade bs-example-modal-sm " id="mol_bainji"
		tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<form class="form-horizontal" method="post" name="clientForm"
					action="${pageContext.request.contextPath}/client.do?flag=updateClientApp"
					onSubmit="return fun()">
					<input type="hidden" name="clientId" value="${client.clientId }">
					<div class="modal-header">
						<h4 class="modal-title" id="myModalLabel">编辑资料</h4>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label for="name" class="col-sm-3 control-label">地址</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="address"
									name="clientAddress" value="${client.clientAddress }">
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label">邮箱</label>
							<div class="control-group col-sm-6">
								<input type="text" class="form-control" id="email"
									name="clientEmail" value="${client.clientEmail }">
							</div>
						</div>
						<div class="form-group">
							<label for="quality" class="col-sm-3 control-label">联系方式</label>
							<div class="control-group col-sm-6">
								<input type="text" class="form-control" id="phone"
									name="clientTel" value="${client.clientTel }">
							</div>
						</div>

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default closeOk"
							data-dismiss="modal">关闭</button>
						<input type="submit" id="bianjiOK" class="btn btn-primary tijiao"
							value="提交" />
					</div>
				</form>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
	<!-- 模态框结束 -->
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
