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
<!-- 引用加载文件 -->
<link rel="stylesheet" type="text/css" href="css/fenye.css" />
<style type="text/css">
.selectize-control.demo-default.single {
	height: 34px;
}

.input-group>.input-group-addon {
	background: #337AB7;
	color: #fff;
}

label {
	margin: 0px;
	font-weight: 600;
	color: #2D42D2;
}

.check {
	width: 18px;
	height: 18px;
	cursor: pointer;
	text-align: center;
	vertical-align: sub;
}
#count_div{
	position: fixed;
	bottom:5px;
	right:10px;
}
#count_div table{
	width:900px;
}
#count_div table tr td{
	border:1px solid #888888;
	height: 35px;
	line-height: 35px;
	text-align: center;
}
</style>
<script type="text/javascript" src="js/rijieshoufeimingxi.js"></script>
</head>
<!-- selectize.js 单选插件 -->
<script>
	$('#custom-name').selectize();
</script>

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
	if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("日结收费明细")){
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
<body onload="load()">
	<div class="container-fluid">
		<div class="">
			<ol class="breadcrumb">
				<span>当前位置：</span>
				<li><a style="cursor: pointer;">财务管理</a>
				</li>
				<li><a href="javascript:document.location.reload();">日结收费明细</a>
				</li>
			</ol>
		</div>
		<div class="col-lg-3 col-xs-6 form-group">
			<div class="input-group">
				<select id="custom-name" class="demo-default" onchange="change()">
				</select> <span class="input-group-addon">客户名称</span>
			</div>
		</div>
		<div class='col-md-2 col-xs-6 form-group'>
			<div class="input-group">
				<input class="form-control" type="text" id="startTime"
					placeholder="开始日期" /> <label
					class="glyphicon glyphicon-calendar input-group-addon"
					for="startTime"></label>
			</div>
		</div>
		<div class='col-md-2 col-xs-6 form-group'>
			<div class='input-group date'>
				<input class="form-control" type="text" id="endTime"
					placeholder="结束日期" /> <label
					class="glyphicon glyphicon-calendar input-group-addon"
					for="endTime"></label>
			</div>
		</div>
		<div class='col-md-2 col-xs-6 form-group'>
			<div class='input-group date'>
				<select class="form-control" id="jiesuanfangshi" onchange="change()">
					<option value="日结">日结</option>
					<option value="月结">月结</option>
				</select><label class="glyphicon input-group-addon" for="jiesuanfangshi">结算方式</label>
			</div>
		</div>
		<div class='col-md-2 col-xs-6 form-group'>
			<div class='input-group date'>
				<select class="form-control" id="dingdantype" onchange="change()">
					<option value="出库订单">出库订单</option>
					<option value="入库订单">入库订单</option>
					<option value="过户订单">过户订单</option>
				</select><label class="glyphicon input-group-addon" for="dingdantype">订单类型</label>
			</div>
		</div>
		<a type="button" class="btn btn-warning"
			style="margin-right: 15px; float: left;" id="tijiao">提交</a>

		<table class="table table-hover">
			<!--  <caption>订单结算明细</caption> -->
			<thead id="content_head">
			
			</thead>
			<tbody id="neirong">

			</tbody>
		</table>


		<div class="page_show" style="margin-top: 20px;">
			<!-- 定义分页显示的当前页 -->
			<input type="hidden" value="1" id="chukupage" /> <input
				type="hidden" value="1" id="rukupage" /> <input type="hidden"
				value="1" id="guohupage" />
			<!-- 定义分页显示的当前页结束 -->
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
		<div id="count_div">
			<table>
				
			</table>
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
