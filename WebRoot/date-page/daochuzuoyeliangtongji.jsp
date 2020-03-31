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
<link rel="stylesheet" href="css/public.css" />
<script src="http://libs.baidu.com/jquery/2.1.1/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/bootstrap-datetimepicker.js"></script>
<script src="js/bootstrap-datetimepicker.zh-CN.js"></script>

<!-- 引用加载文件 -->
<script src="js/jquery.shCircleLoader-min.js"></script>
<script src="js/jquery.shCircleLoader.js"></script>
<link rel="stylesheet" type="text/css"
	href="css/jquery.shCircleLoader.css" />
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
</style>
</head>
<body onload="showContent()">
	<div id="loader"
		style="position: absolute; left: 48%; top: 40%; color: #337AB7; width: 70px;"></div>
	<script>
		function jiazai() {
			$('#loader').css("display", "block");
			$('#loader').shCircleLoader();
		}
		function jiazaijieshu() {
			$('#loader').css("display", "none");
		}
	</script>
	<!--内容起始处-->
	<div id="mainFrame" class="main">
		<div class="daohang">
			<ul>
				<i>当前位置：</i>
				<a style="cursor:pointer;">数据中心</a>
				<span>/</span>&nbsp;
				<a href="javascript:window.history.go(-1);">工作量统计</a>
				<span>/</span>&nbsp;
				<a href="javascript:document.location.reload();">导出</a>
			</ul>
		</div>
		<div class="container" style="margin-top: 15px;">
			<div class='col-md-2 col-xs-6'>
				<form>
					<div class="form-group">
						<div class="input-group">
							<input class="form-control" type="text" id="startTime"
								placeholder="开始日期" /> <span class="input-group-addon"><label
								class="glyphicon glyphicon-calendar" for="startTime"></label> </span>
						</div>
					</div>
			</div>
			<div class="col-md-2 col-xs-6">
				<div class="form-group">
					<div class='input-group date'>
						<input class="form-control" type="text" id="endTime"
							placeholder="结束日期" /> <span class="input-group-addon"><label
							class="glyphicon glyphicon-calendar" for="endTime"></label> </span>
					</div>
				</div>
			</div>
			<a type="button" class="btn btn-warning"
				style="float: left; margin-right: 15px;" id="tijiao">提交</a> <a
				type="button" class="btn btn-danger"
				style="float: left; margin-right: 15px;"
				onClick="$('#table-name').tableExport({type:'excel', separator:';', escape:'false'});">导出</a>

		</div>
		<!-- 以下为订单内容页面 -->
		<div class="container">
			<table class="table table-bordered table-hover"
				style="margin-bottom:20px;" id="table-name">
				<thead>
					<tr>
						<th>职务</th>
						<th>人员姓名</th>
						<th>入库量（吨）</th>
						<th>出库量（吨）</th>
						<th>过户量（吨）</th>
						<th>挪库量（吨）</th>
						<th>短倒量（吨）</th>
						<th>合计（吨）</th>
					</tr>
				</thead>
				<tbody style="font-size: 14px;" id="neirong">

				</tbody>
			</table>
		</div>
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
<script type="text/javascript">
	//当点击提交的时候触发此方法
	$(function() {
		$("#tijiao").click(function() {
			showContent();
		});
	});
	function showContent() {
		jiazai();
		$("#neirong").html("");
		$(function() {
			$
					.ajax({
						type : "post",
						url : "workCountAction.do?flag=getExcelCount",
						async : true,
						data : {
							"time" : new Date().getTime(),
							"begin" : $("#startTime").val(),
							"finish" : $("#endTime").val(),
						},
						dataType : "json",
						success : function(obj) {
							jiazaijieshu();
							if (obj == null) {
								$("#neirong")
										.append(
												"<tr><td colspan='7' style='text-align:center;'>无查询结果</td></tr>");
								return false;
							}
							
							for ( var i = 0; i < obj.length; i++) {
								$("#neirong").append(
										"<tr><td>" + obj[i].zhiwu + "</td><td>"
												+ obj[i].name + "</td>"
												+ "<td>"
												+ parseFloat(obj[i].ruku).toFixed(3)
												+ "</td><td>"
												+ parseFloat(obj[i].chuku).toFixed(3)
												+ "</td>" + "<td>"
												+ parseFloat(obj[i].guohu).toFixed(3)
												+ "</td><td>"
												+ parseFloat(obj[i].nuoku).toFixed(3)
												+ "</td><td>"
												+ parseFloat(obj[i].duandao).toFixed(3)
												+ "</td><td>"
												+ parseFloat(obj[i].sum).toFixed(3)
												+ "</td></tr>");
							}
						},
						error : function() {
							jiazaijieshu();
							document.location.href = "/XGProject/cangchu/page/login.jsp";
						}
					});
		});
	}
</script>
</html>
