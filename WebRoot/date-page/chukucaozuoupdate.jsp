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
<script
	src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/chukucaozuoupdate.js"></script>
<link rel="stylesheet" href="css/selectize.default.css">
<script src="js/selectize.js"></script>

<!-- 时间插件 -->
<script src="js/bootstrap-datetimepicker.js"></script>
<script src="js/bootstrap-datetimepicker.zh-CN.js"></script>
<link rel="stylesheet" href="css/bootstrap-datetimepicker.min.css">
<script type="text/javascript">
	function closes() {
		window.history.go(-1);
	}
</script>
<style>
#picis {
	
}

#picis li {
	
}
</style>
</head>
<script type="text/javascript">
	$("#zhuangxie").selectize();
</script>
<body>
	<!--内容起始处-->
	<div id="mainFrame" class="main">
		<div class="daohang">
			<ul>
				<i>当前位置：</i>
				<a style="cursor: pointer;">仓储业务</a>
				<span>/</span>&nbsp;
				<a href="javascript:window.history.go(-2);">订单操作</a>
				<span>/</span>&nbsp;
				<a href="javascript:window.history.go(-1);">开始操作</a>
				<span>/</span>&nbsp;
				<a href="javascript:document.location.reload();">订单修改</a>
				<span>/</span>&nbsp;
			</ul>
		</div>
		<div class="container-fluid">
			<ul class="nav nav-tabs nav-pills"
				style="margin: 10px 2.5%; font-weight: bold;">
				<li class="active"><a data-toggle="tab" href="#home"> <span
						class="glyphicon glyphicon-list" aria-hidden="true"
						style="margin-right: 8px;"></span>仓储修改</a></li>
			</ul>
			<div class="tab-content">
				<div id="home" class="tab-pane fade in active">
					<table class="table table-striped table-hover">
						<thead>
							<tr>
								<th>编号</th>
								<th>操作编号</th>
								<th>分配人</th>
								<th>分配时间</th>
								<th>执行人</th>
								<th>过磅/理算</th>
								<th>库位</th>
								<th>批次</th>
								<th>操作重量</th>
								<th>操作件数</th>
								<th>天车工</th>
								<th>装卸工</th>
								<th style="width: 100px;">操作</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>${eo.exportSeed.export.exportId }<input type="hidden"
									value="${eo.exportSeed.goods.goodsId }" id="goodsId" />
								</td>
								<td>${eo.eoperateId }</td>
								<td>${eo.interiorUserByEoperateDispatcher.iuserName }</td>
								<td>${eo.eoperateDispatcherTime }</td>
								<td>${eo.interiorUserByEoperateStoreman.iuserName }</td>
								<td>${eo.eoperatePonderationTrue }</td>
								<td>${eo.tarehouse.tarehouseName }</td>
								<td>${eo.EOperatepici }</td>
								<td>${eo.eoperateRealityWeight }</td>
								<td>${eo.eoperateRealityNumber }</td>
								<td>${eo.eoperateCraneman }</td>
								<td>${eo.eoperateStevedore }<input type="hidden"
									value="${eo.eoperateDefinedOne }" id="zhuangtai" /></td>
								<td><a type="button" class="btn btn-warning btn-xs"
									id="cangchubianji" data-toggle="tab" href="#cangchuupdate"
									onclick="cangchubianji(this)">修改</a></td>
							</tr>
						</tbody>
					</table>
				</div>
				<div id="cangchuupdate" class="tab-pane fade">
					<div class="modal-content" style="margin: auto 30%;">
						<div class="modal-header">
							<h4 class="modal-title" id="myModalLabel">修改出库订单</h4>
						</div>
						<div class="modal-body">
							<form class="form-horizontal" role="form"
								action="${pageContext.request.contextPath }/exportOperate.do?flag=caozuo_update"
								name="exportOperateForm" id="cangchuupdateform" method="post">
								<input type="hidden" value="" id="goodsxuanze" />
								<div class="form-group">
									<label for="bh" class="col-sm-3 control-label">编号</label>
									<div class="col-sm-7">
										<input type="text" class="form-control" id="bh"
											readonly="readonly" name="eoperateId" />
									</div>
								</div>
								<div class="form-group">
									<label for="kuwei" class="col-sm-3 control-label">库位</label>
									<div class="col-sm-7">
										<select class="form-control" id="kuwei" name="tarehouse"
											onchange="kuweixuanze()">
										</select>
									</div>
								</div>
								<div class="form-group">
									<label for="pici" class="col-sm-3 control-label">批次</label>
									<div class="col-sm-9">
										<ul style="list-style: none; margin: 0;" id="picis"
											style="padding:0px;">
										</ul>
									</div>
								</div>
								<div class="form-group">
									<label for="beizhu1" class="col-sm-3 control-label">备注</label>
									<div class="col-sm-7">
										<input type="text" class="form-control" name="eoperateRemark"
											id="beizhu1" />
									</div>
								</div>
							</form>
						</div>
						<div class="modal-footer">
							<a type="button" class="btn btn-default" onclick="closes()"
								value="click" />关闭</a> <a type="button" class="btn btn-primary"
								id="cangchutijiao"> 提交更改 </a>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
			</div>
		</div>
	</div>
</body>

</html>
<!-- selectize.js 单选插件 -->
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

