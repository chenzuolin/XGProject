<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.xinggang.project.entity.InteriorUser"%>
<%@ page import="com.xinggang.project.entity.InteriorUserDuty"%>
<%@ page import="com.xinggang.project.entity.Powers"%>
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
<meta name="description"
	content="甘肃鑫港物流有限公司仓储管理系统。快速、安全、便捷。致力于为广大商户提供快速、安全、便捷的仓储物流服务" />
<meta name="keywords"
	content="甘肃鑫港物流有限公司仓储管理系统。快速、安全、便捷。钢材、木材、百货、物流、汽车货运、铁路货运" />
<meta name="author" content="">
<meta name="format-detection" content="telephone=no" />
<!--禁止数字识自动别为电话号码-->
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--禁止百度转码-->
<link rel="shortcut icon" type="image/ico" href="/favicon.ico" />
<!--添加 favicon icon -->
<link rel="stylesheet" href="css/bootstrap.css" />
<link rel="stylesheet" href="css/public.css" />
<link rel="stylesheet" href="css/selectize.default.css">
<script src="http://libs.baidu.com/jquery/2.1.1/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/selectize.js"></script>

<link rel="stylesheet" type="text/css" href="css/fenye.css" />
<script type="text/javascript" src="js/yuejieshoufei.js"></script>
<style type="text/css">
.selectize-control.demo-default.single {
	height: 34px;
}

.input-group>.input-group-addon {
	background: #337AB7;
	color: #fff;
}
</style>
</head>
<script>
	$('#custom-name').selectize();
</script>
<body onload="load()">
	<div class="container-fluid">
		<div class="">
			<ol class="breadcrumb">
				<span>当前位置：</span>
				<li><a style="cursor:pointer;">结算中心</a>
				</li>
				<li><a href="javascript:document.location.reload();">月结收费</a>
				</li>
			</ol>
		</div>
		<form class="container-fluid" style="width: auto; height: auto;">
			<div class='col-md-3 col-xs-6 form-group'>
				<div class="input-group">
					<select id="custom-name" class="demo-default" onchange="change()">
					</select> <label class="input-group-addon" for="custom-name">客户名称</label>
				</div>
			</div>
			<a type="button" class="btn btn-warning"
				style="float: left; margin-right: 15px;" id="tijiao">提交</a>
		</form>
		<script>
			function tianzhuanmingxi(str) {
				var id = $(str).parents("tr").find(".clientid").val();
				document.location.href = "${pageContext.request.contextPath}/accounts.do?flag=getAccountsId&accountsId="
						+ id;
			}
		</script>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>客户名称</th>
					<th>起始日期</th>
					<th>结束日期</th>
					<th>轧账费用合计</th>
					<th>滞纳金费率</th>
					<th>应收滞纳金</th>
					<th>总费用</th>
					<th>结算人</th>
					<th>结算时间</th>
					<th style="text-align: center;">操作</th>
				</tr>
			</thead>
			<tbody id="neirong">
			</tbody>
		</table>
		<!-- 收费模态框（Modal） -->
		<div class="modal fade" id="shoufei" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 80%;">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" id="myModalLabel">收费页面</h4>
					</div>
					<div class="modal-body">
						<form
							action="${pageContext.request.contextPath}/accounts.do?flag=ShouQuCost"
							method="post" id="shoufeiform" name="accountsForm">
							<input type="hidden" value="" name="accountsId" id="client" />
							<div class="row">
								<div class="col-lg-8 col-md-offset-2 form-group">
									<div class="input-group">
										<span class="input-group-addon">应收总费用</span> <input
											class="form-control" type="text" readonly="readonly"
											id="yingshouzong">
									</div>
								</div>
								<div class="col-lg-8 col-md-offset-2 form-group">
									<div class="input-group">
										<span class="input-group-addon">实收费用</span> <input
											class="form-control" type="text" id="shishou" name="shishouCost">
									</div>
								</div>
								<div class="col-lg-8 col-md-offset-2 form-group">
									<div class="input-group">
										<span class="input-group-addon">缴费人</span> <input
											class="form-control" type="text" name="jiaofeiren" id="jiaofeiren">
									</div>
								</div>
								<div class="col-lg-8 col-md-offset-2 form-group">
									<div class="input-group">
										<span class="input-group-addon">支付方式</span> <select
											class="form-control" name="" id="zhifu" onchange="piaojuleixing()">
										</select>
									</div>
								</div>
								<div class="col-lg-8 col-md-offset-2 form-group">
									<div class="input-group">
										<span class="input-group-addon">票据类型</span> <select
											class="form-control" name="accountsDefinedTwo" id="piaoju">
										</select>
									</div>
								</div>
								<div class="col-lg-8 col-md-offset-2 form-group">
									<div class="input-group">
										<span class="input-group-addon">备注</span> <input
											class="form-control" type="text" name="accountsRemark">
									</div>
								</div>
							</div>
						</form>
						<div class="modal-footer col-md-offset-3">
							<a type="button" class="btn btn-default" data-dismiss="modal">关闭</a>
							<a type="button" class="btn btn-primary" id="shoufeitijiao">提交更改</a>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal -->
			</div>
		</div>
	</div>
	<div class="page_show" style="width: 500px; margin-top: 20px;">
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
		<div>
			<i style="font-style: normal;">显示行数：</i> <select
				style="height: 23px;" id="showrow" onchange="change()">
				<option value="20">20</option>
				<option value="40">40</option>
				<option value="60">60</option>
				<option value="100">100</option>
			</select>
		</div>
	</div>
</body>

</html>
<%
	if (request.getSession().getAttribute("loginName") == null
	|| request.getSession().getAttribute("iulist") == null
	|| request.getSession().getAttribute("power") == null) {
%>
<script type="text/javascript">
	$(function() {
		$("body")
				.append(
						"<div style='width:100%; height:100%; background-color:#000000; position:absolute; left:0px; top:0px; z-index:1000'></div>");
	});
</script>
<%
	}
%>
<%
	int x = 0;
		
		List<Powers> power = (List<Powers>)request.getSession().getAttribute("power");
		for(int i=0; i<power.size(); i++){
	if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("月结收费")){
		x++;
	}
		}
		if(x==0){
%>
<script type="text/javascript">
	$(function() {
		$("body")
				.append(
						"<div style='width:100%; height:100%; background-color:#000000; position:absolute; left:0px; top:0px; z-index:1000'></div>");
	});
</script>
<%
	}
%>