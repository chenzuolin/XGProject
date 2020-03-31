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
		$("body")
				.append(
						"<div style='width:100%; height:100%; background-color:#000000; position:absolute; left:0px; top:0px; z-index:1000'></div>");
	});
</script>
<%
	}
%>
<body>
	<%
		int x = 0;
		int pankuupdate = 0;
		int pankuzuofei = 0;
		
		List<Powers> power = (List<Powers>)request.getSession().getAttribute("power");
		for(int i=0; i<power.size(); i++){
			if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("查看盘库订单")){
		x++;
			}
			if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("修改盘库订单")){
		pankuupdate++;
			}
			if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("作废盘库订单")){
		pankuzuofei++;
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
	<!--内容起始处-->
	<!-- 以下为订单内容页面 -->
	<div class="daohang">
		<ul>
			<i>当前位置：</i>
			<a>数据中心</a>
			<span>/</span>&nbsp;
			<a href="javascript:window.history.go(-1)">盘库订单</a>
			<span>/</span>&nbsp;
			<a>查看明细</a>
		</ul>
	</div>
	<div class="container" style="margin-top: 5%;">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<p class="panel-title">
					<a data-toggle="" style="cursor:pointer;">订单编号:${clist.checkId }</a>
				</p>
			</div>
			<div id="dd1" class="panel-collapse collapse in">
				<div class="panel-body" style="cursor: pointer;">
					<div class="col-md-3 col-xs-6">
						<d>发起人：</d>
						<span>${clist.interiorUserByCheckFounderMember.iuserName }</span>
					</div>
					<div class="col-md-3 col-xs-6">
						<d>发起时间：</d>
						<span>${clist.checkTime }</span>
					</div>
					<div class="col-md-3 col-xs-6">
						<d>库位：</d>
						<span>${clist.tarehouse.tarehouseName }</span>
					</div>
					<div class="col-md-3 col-xs-6">
						<d>盘库人：</d>
						<span>${clist.interiorUserByCheckHuman.iuserName }</span>
					</div>
					<div class="col-md-3 col-xs-6">
						<d>订单状态：</d>
						<span>${clist.checkAuditingTrue }</span>
					</div>
					<div class="col-md-3 col-xs-6">
						<d>完成时间：</d>
						<span>${clist.checkDefinedOne==null?"无":clist.checkDefinedOne
							}</span>
					</div>
					<div class="col-md-3 col-xs-6">
						<d>审核人：</d>
						<span>${clist.interiorUserByCheckAuditing.iuserName==null?"无":clist.interiorUserByCheckAuditing.iuserName
							}</span>
					</div>
					<div class="col-md-3 col-xs-6">
						<d>审核时间：</d>
						<span>${clist.checkAuditingTime==null?"无":clist.checkAuditingTime
							}</span>
					</div>
					<div class="col-md-3 col-xs-6">
						<d>货物品类：</d>
						<span>${clist.tarehouseGoods.goods.goodsCategory.goodsCategoryName
							}</span>
					</div>
					<div class="col-md-3 col-xs-6">
						<d>货物名称：</d>
						<span>${clist.tarehouseGoods.goods.goodsName }</span>
					</div>
					<div class="col-md-3 col-xs-6">
						<d>规格：</d>
						<span>${clist.tarehouseGoods.goods.goodsStandard.goodsStandardName
							}</span>
					</div>
					<div class="col-md-3 col-xs-6">
						<d>材质：</d>
						<span>${clist.tarehouseGoods.goods.goodsQuality.goodsQualityName
							}</span>
					</div>
					<div class="col-md-3 col-xs-6">
						<d>属性：</d>
						<span>${clist.tarehouseGoods.goods.goodsProperty.goodsPropertyName
							}</span>
					</div>
					<div class="col-md-3 col-xs-6">
						<d>产地：</d>
						<span>${clist.tarehouseGoods.goods.goodsYieldly.goodsYieldlyName
							}</span>
					</div>
					<div class="col-md-3 col-xs-6">
						<d>库存件数：</d>
						<span>${clist.checkTarehouseNumber }</span>
					</div>
					<form method="post" name="checksForm" id="pankumingxiForm">
						<input type="hidden" value="${clist.checkId }" name="checkId" />
						<div class="col-md-3 col-xs-6" style="color: red;">
							<d>盘库结果：</d>
							<span id="result">${clist.checkResultNumber==null?"无":clist.checkResultNumber
								}</span>
						</div>
						<div class="col-md-12 col-xs-12">
							<d>备注：</d>
							<span id="remark">${clist.checkRemark==null?"无":clist.checkRemark
								}</span>
						</div>
					</form>
				</div>
			</div>
		</div>
		<div class="panel-foot" style="float: right;">
			<c:if test="${clist.checkAuditingTrue=='审核通过' }">
				<%
					if(pankuupdate!=0){
				%>
				<a type="button" class="btn btn-primary" id="update">修改</a>
				<%
					}
				%>
			</c:if>
			<%
				if(pankuzuofei!=0){
			%>
			<a type="button" class="btn btn-danger" id="zuofei">作废</a>
			<%
				}
			%>
		</div>
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
												"${pageContext.request.contextPath}/checks.do?flag=deleteChecks");
								$("#pankumingxiForm").submit();
							}
						});

		$("d").css({
			"display" : "block",
			"width" : "90px",
			"text-align" : "right",
			"float" : "left"
		});
		$("#update")
				.click(
						function() {
							if ($(this).text() == "修改") {
								var result = $("#result").text();
								$("#result")
										.html(
												"<input name='checkResultNumber' type='text' style='width:100px; height:20px;' value='" + result + "' />");
								$("#remark")
										.html(
												"<input name='checkRemark' type='text' style='width:200px; height:20px;' />");
								$(this).text("提交");
							} else {
								$("#pankumingxiForm")
										.attr("action",
												"${pageContext.request.contextPath}/checks.do?flag=updateChecks");
								$("#pankumingxiForm").submit();
								var result = $("#result input").val();
								var remark = $("#remark input").val();
								$("#result").html(result);
								$("#remark").html(remark);
								$(this).text("修改");
							}
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