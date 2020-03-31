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
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/public.css" />
<link rel="stylesheet" href="css/bootstrap-datetimepicker.min.css">
<link rel="stylesheet" href="css/selectize.default.css">
<script src="http://libs.baidu.com/jquery/2.1.1/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/bootstrap-datetimepicker.js"></script>
<script src="js/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="js/selectize.js"></script>
<link rel="stylesheet" type="text/css" href="css/fenye.css" />
<script type="text/javascript" src="js/shoufeijilu.js"></script>
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
<!-- selectize.js 单选插件 -->
<script>
	$('#custom-name').selectize();
</script>
<body onload="load()">
	<script>
		function tianzhuanmingxi(str) {
			var id = $(str).parents("tr").find(".clientid").val();
			document.location.href = "${pageContext.request.contextPath}/accounts.do?flag=getAccountsId&accountsId="
					+ id;
		}
	</script>
	<div class="container-fluid">
		<div class="">
			<ol class="breadcrumb">
				<span>当前位置：</span>
				<li><a style="cursor:pointer;">结算中心</a></li>
				<li><a href="javascript:document.location.reload();">收费记录</a></li>
			</ol>
		</div>
		<form class="container" style="width: auto; height: auto;">
			<div class="col-lg-3 col-xs-6 form-group">
				<div class="input-group">
					<select id="custom-name" class="demo-default" onchange="change()">
					</select> <span class="input-group-addon">客户名称</span>
				</div>
			</div>
			<div class='col-md-3 col-xs-6 form-group'>
				<div class="input-group">
					<input class="form-control" type="text" id="startTime"
						placeholder="开始日期" /> <label
						class="glyphicon glyphicon-calendar input-group-addon"
						for="startTime"></label>
				</div>
			</div>
			<div class='col-md-3 col-xs-6 form-group'>
				<div class='input-group date'>
					<input class="form-control" type="text" id="endTime"
						placeholder="结束日期" /> <label
						class="glyphicon glyphicon-calendar input-group-addon"
						for="endTime"></label>
				</div>
			</div>
			<a type="button" class="btn btn-warning"
				style="float: left; margin-right: 15px;" id="tijiao">提交</a> <a href="${pageContext.request.contextPath }/cangchu/page/shoufeijiludaochu.jsp"
				type="button" class="btn btn-warning"
				style="float: left; margin-right: 15px;">导出</a>
		</form>
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
					<th>收费人</th>
					<th>收费时间</th>
				</tr>
			</thead>
			<tbody id="neirong">
				<tr onclick="tianzhuanmingxi(this)" style="cursor: pointer;"
					title="查看详情">
					<td>20170701320</td>
					<td>170205</td>
					<td>出库业务</td>
					<td>张三</td>
					<td>螺纹钢</td>
					<td>20.3</td>
					<td>18</td>
					<td></td>
					<td></td>
				</tr>
			</tbody>
		</table>
	</div>
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
</body>
<!-- selectize.js 时间插件 -->
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