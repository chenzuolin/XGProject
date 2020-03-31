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
<script type="text/javascript" src="js/jquery1.9.0.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/bootstrap-datetimepicker.js"></script>
<script src="js/bootstrap-datetimepicker.zh-CN.js"></script>
<style>
.panel-title a {
	font-size: 18px;
	font-weight: bold;
}

.panel-body span {
	font-size: 16px;
	font-weight: bolder;
}

.col-md-3 {
	margin-bottom: 20px;
}
</style>
</head>

<%
	if (request.getSession().getAttribute("loginName") == null
	|| request.getSession().getAttribute("iulist") == null || request.getSession().getAttribute("power")==null) {
%>
<script type="text/javascript">
	$(function() {
		$("html").html("");
	});
</script>
<%
	}
%>
<body>
	<%
		int x = 0;
		int pankuzuofei = 0;
		
		List<Powers> power = (List<Powers>)request.getSession().getAttribute("power");
		for(int i=0; i<power.size(); i++){
			if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("查看短倒订单")){
		x++;
			}
			if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("作废短倒订单")){
		pankuzuofei++;
			}
		}
		if(x==0){
	%>
	<script type="text/javascript">
		$(function() {
			$("html").html("");
		});
	</script>
	<%
		}
	%>
	<!--内容起始处-->
	<!-- 以下为订单内容页面 -->
	<div class="daohang">
		<ul>
			<i>当前位置：</i>
			<a>数据中心</a>
			<span>/</span>&nbsp;
			<a href="javascript:window.history.go(-1)">短倒订单</a>
			<span>/</span>&nbsp;
			<a>查看明细</a>
		</ul>
	</div>
	<div class="container" style="margin-top: 5%;">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<p class="panel-title">
					<a data-toggle=""  style="cursor:pointer;">订单编号:${shift.shiftId }</a>
				</p>
			</div>
			<div id="dd1" class="panel-collapse collapse in">
				<div class="panel-body" style="cursor: pointer;">
					<div class="col-md-3 col-xs-6">
						<d>发起人：</d>
						<span>${shift.interiorUserByShiftFounderMember.iuserName }</span>
					</div>
					<div class="col-md-3 col-xs-6">
						<d>发起时间：</d>
						<span>${shift.shiftTime }</span>
					</div>
					<div class="col-md-3 col-xs-6">
						<d>原库位：</d>
						<span>${shift.tarehouseByShiftPast.tarehouseName }</span>
					</div>
					<div class="col-md-3 col-xs-6">
						<d>新库位：</d>
						<span>${shift.tarehouseByShiftNew.tarehouseName }</span>
					</div>
					<div class="col-md-3 col-xs-6">
						<d>执行人：</d>
						<span>${shift.interiorUserByShiftExecutor.iuserName }</span>
					</div>
					<div class="col-md-3 col-xs-6">
						<d>天车工：</d>
						<span>${shift.shiftCraneman==null?"无":shift.shiftCraneman }</span>
					</div>
					<div class="col-md-3 col-xs-6">
						<d>装卸工：</d>
						<span>${shift.shiftStevedore }</span>
					</div>
					<div class="col-md-3 col-xs-6">
						<d>完成时间：</d>
						<span>${shift.shiftFinishTime==null?"无":shift.shiftFinishTime
							}</span>
					</div>
					<div class="col-md-3 col-xs-6">
						<d>短倒件数：</d>
						<span>${shift.shiftNumber }</span>
					</div>
					<div class="col-md-3 col-xs-6">
						<d>短倒重量：</d>
						<span>${shift.shiftWeight }</span>
					</div>
					<div class="col-md-3 col-xs-6">
						<d>货物品类：</d>
						<span>${shift.goods.goodsCategory.goodsCategoryName }</span>
					</div>
					<div class="col-md-3 col-xs-6">
						<d>货物名称：</d>
						<span>${shift.goods.goodsName }</span>
					</div>
					<div class="col-md-3 col-xs-6">
						<d>规格：</d>
						<span>${shift.goods.goodsStandard.goodsStandardName }</span>
					</div>
					<div class="col-md-3 col-xs-6">
						<d>材质：</d>
						<span>${shift.goods.goodsQuality.goodsQualityName }</span>
					</div>
					<div class="col-md-3 col-xs-6">
						<d>属性：</d>
						<span>${shift.goods.goodsProperty.goodsPropertyName }</span>
					</div>
					<div class="col-md-3 col-xs-6">
						<d>产地：</d>
						<span>${shift.goods.goodsYieldly.goodsYieldlyName }</span>
					</div>
					<div class="col-md-3 col-xs-6">
						<d>订单状态：</d>
						<span>${shift.shiftDefinedOne }</span>
					</div>
					<div class="col-md-3 col-xs-6">
						<d>备注：</d>
						<span id="remark">${shift.shiftRemark==null?"无":shift.shiftRemark
							}</span>
					</div>
				</div>
			</div>
		</div>
		<div class="panel-foot" style="float: right;">
			<%
				if(pankuzuofei!=0){
			%>
			<a type="button" class="btn btn-danger" id="zuofei">作废</a>
			<%
				}
			%>
		</div>
		<form method="post" name="" id="pankumingxiForm">
			<input type="hidden" value="${shift.shiftId }" name="shiftId" />
		</form>
	</div>
</body>
<script>
	$(function() {
		//当点击作废的时候触发
		$("#zuofei")
				.click(
						function() {
							if (confirm("你确定要作废此订单吗？")) {
								$("#pankumingxiForm")
										.attr("action",
												"${pageContext.request.contextPath}/duanDaoAction.do?flag=deleteShift");
								$("#pankumingxiForm").submit();
							}
						});

		$("d").css({
			"display" : "block",
			"width" : "90px",
			"text-align" : "right",
			"float" : "left"
		});
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