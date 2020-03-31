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
<link rel="stylesheet" href="css/bootstrap-datetimepicker.min.css">
<link rel="stylesheet" type="text/css" href="css/public.css" />
<link rel="stylesheet" type="text/css" href="css/fenye.css" />
<script src="http://libs.baidu.com/jquery/2.1.1/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/bootstrap-datetimepicker.js"></script>
<script src="js/bootstrap-datetimepicker.zh-CN.js"></script>

<script src="js/indent_daochu.js"></script>

<!-- 导出excel插件 -->	
<script src="cangchu/d/tableExport.js"></script>
<script src="cangchu/d/jquery.base64.js"></script>
<script src="cangchu/d/jszip-utils.js"></script>
<script src="cangchu/d/xlsx.core.min.js"></script>

<style>
.panel-title a {
	font-size: 18px;
	font-weight: bold;
}

.panel-body span {
	font-size: 16px;
	font-weight: bolder;
}

.bb {
	position: fixed;
	left: 45%;
	top: 40%;
	width: 200px;
	height: 50px;
	line-height: 50px;
	border: 1px solid #888888;
	background-color: #f2f2f2;
	-moz-box-shadow: 5px 5px 5px #ccc;
	-webkit-box-shadow: 5px 5px 5px #ccc;
	box-shadow: 2px 2px 2px #ccc;
	text-align: center;
	z-index: 1000;
	opacity: 0;
	display: none;
}

body {
	/* -moz-user-select: none;
	-webkit-user-select: none;
	-ms-user-select: none;
	-khtml-user-select: none;
	user-select: none; */
}
table thead tr th{
	font-size:16px;
}
table thead tr th,table tbody tr td{
	text-align:center;
}
#indent_count{
	position: fixed;
	bottom: 5px;
	right: 20px;
}
#indent_count table{
	width:400px;
}
#indent_count table tr td{
	border:1px solid #888888;
	height: 35px;
	line-height: 35px;
}

.gengduo{
color:#000000;}
.gengduo:hover{
color:blue;}
</style>
</head>
<%
	if (request.getSession().getAttribute("loginName") == null
	|| request.getSession().getAttribute("iulist") == null || request.getSession().getAttribute("power")==null) {
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
		int ruku = 0;
		int chuku = 0;
		int guohu = 0;
		int chukuzuofei = 0;
		int rukuzuofei = 0;
		
		List<Powers> power = (List<Powers>)request.getSession().getAttribute("power");
		for(int i=0; i<power.size(); i++){
	if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("订单查询")){
		x++;
	}
	if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("入库订单查询")){
		ruku++;
	}
	if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("出库订单查询")){
		chuku++;
	}
	if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("过户订单查询")){
		guohu++;
	}
	if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("出库作废订单查询")){
		chukuzuofei++;
	}
	if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("入库作废订单查询")){
		rukuzuofei++;
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

<script type="text/javascript">
	// 当点击查看详情的时候跳转到对应的详情界面
	function tiaozhuanguohu(str) {
		$(function() {
			var id = $(str).children("input").val();
			document.location.href = "${pageContext.request.contextPath}/shiftStockSeed.do?flag=getXiangQing&ssseedId="
					+ encodeURI(encodeURI(id));
		});
	}
	function tiaozhuanchuku(str) {
		$(function() {
			var id = $(str).children("input").val();
			document.location.href = "${pageContext.request.contextPath}/exportSeed.do?flag=getXiangQing&eseedId="
					+ encodeURI(encodeURI(id));
		});
	}
	function tiaozhuanruku(str) {
		$(function() {
			var id = $(str).children("input").val();
			document.location.href = "${pageContext.request.contextPath}/inputSeed.do?flag=getXiangQing&iseedId="
					+ encodeURI(encodeURI(id));
		});
	}
</script>

<body onload="QueryDingDan()">
	<!--内容起始处-->
	<div id="mainFrame" class="main">
		<div class="daohang">
			<ul>
				<i>当前位置：</i>
				<a style="cursor:pointer;">数据中心</a>
				<span>/</span>&nbsp;
				<a href="javascript:document.location.reload();">订单查询</a>
				<span>/</span>&nbsp;
			</ul>
		</div>
		<div class="container" style="margin-top: 15px;">
			<div class='col-md-2 col-sm-6'>
				<div class="form-group">
					<div class="input-group">
						<input class="form-control" type="text" id="startTime"
							placeholder="开始日期" /> <span class="input-group-addon"><label
							class="glyphicon glyphicon-calendar" for="startTime"></label> </span>
					</div>
				</div>
			</div>
			<div class="col-md-2 col-sm-6">
				<div class="form-group">
					<div class='input-group date'>
						<input class="form-control" type="text" id="endTime"
							placeholder="结束日期" /> <span class="input-group-addon"><label
							class="glyphicon glyphicon-calendar" for="endTime"></label> </span>
					</div>
				</div>
			</div>
			<div class="col-md-3 col-sm-6">
				<div class="input-group form-group">
					<input type="text" class="form-control" id="kehuName"
						placeholder="客户名称"> <span class="input-group-addon">客户名称</span>
				</div>
			</div>
			<div class="col-md-3 col-sm-6">
				<div class="input-group form-group">
					<select class="form-control" id="ddType" onchange="QueryDingDan()">
						<%
							if(chuku!=0){
						%>
						<option value="出库订单">出库订单</option>
						<%
							}
						%>
						<%
							if(ruku!=0){
						%>
						<option value="入库订单">入库订单</option>
						<%
							}
						%>
						<%
							if(guohu!=0){
						%>
						<option value="过户订单">过户订单</option>
						<%
							}
						%>
					</select> <span class="input-group-addon">订单类型</span>
				</div>
			</div>
		</div>
		<div class="container" style="margin-top: 15px;">
			<div class="col-md-1 col-sm-6">
				<div class="input-group form-group"></div>
			</div>
			<div class="col-md-3 col-sm-6">
				<div class="input-group form-group">
					<input type="text" class="form-control" placeholder="订单编号"
						id="digndanhao"> <span class="input-group-addon">订单编号</span>
				</div>
			</div>
			<div class="col-md-3 col-sm-6">
				<div class="input-group form-group">
					<input type="text" class="form-control" placeholder="客户单号"
						id="kehudanhao"> <span class="input-group-addon">客户单号</span>
				</div>
			</div>
			<div class="col-md-3 col-sm-6">
				<div class="input-group form-group">
					<input type="text" class="form-control" placeholder="货物" id="huowu">
					<span class="input-group-addon"> &nbsp;&nbsp;货 &nbsp;
						物&nbsp;&nbsp; </span>
				</div>
			</div>
			<a type="button" class="btn btn-warning" id="lishitijiao"
				style="margin-left: 5%;">提交</a>
				
			<a
				type="button" class="btn btn-danger"
				style="" onClick="$('#content_table').tableExport({type:'excel', separator:';', escape:'false'});">导出</a>
		</div>
		<table class="table table-bordered table-hover" id="content_table" style='width:4000px;'>
			<thead>
				<tr style="background-color:#337AB7; color:#ffffff;">
					<th>客户名称</th>
					<th>货物名称</th>
					<th>期初库存</th>
					<th>汽车入库</th>
					<th>火车入库</th>
					<th>过户入库</th>
					<th>入库合计</th>
					<th>汽车出库</th>
					<th>火车出库</th>
					<th>过户出库</th>
					<th>出库合计</th>
					<th>期末库存</th>
				</tr>
			</thead>
			<tbody style="font-size: 14px;">
				<tr style="cursor:pointer;" title='查看详情'>
					<td>兰州宏智</td>
					<td>螺纹钢</td>
					<td>300</td>
					<td>150</td>
					<td>150</td>
					<td>0</td>
					<td>300</td>
					<td>100</td>
					<td>100</td>
					<td>100</td>
					<td>300</td>
					<td>300</td>
				</tr>
			</tbody>
		</table>
		<div style="width:250px; text-align:center; margin:0 auto; margin-top:10px; font-size:16px;">
			<span style='cursor:pointer;' class='gengduo'>加载更多</span>
		</div>
		<ul class="pager">
			<input type="hidden" id="guohuPage" value="1" />
			<input type="hidden" id="rukuPage" value="1" />
			<input type="hidden" id="chukuPage" value="1" />
			<input type="hidden" id="chukuzuofeiPage" value="1" />
			<input type="hidden" id="rukuzuofeiPage" value="1" />
		</ul>
	</div>
	<!-- 用来提醒分页已到底部的模态框 -->
	<div class="bb">已到底部！</div>
	<div id="indent_count">
		<table>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
		</table>
	</div>
</body>
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