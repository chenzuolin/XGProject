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
<script src="js/rukudingdanupdate.js"></script>
<link rel="stylesheet" href="css/selectize.default.css">
<script src="js/selectize.js"></script>
<script type="text/javascript">
	function closes() {
		document.location.reload();
	}
</script>

<!-- 时间插件 -->
<script src="js/bootstrap-datetimepicker.js"></script>
<script src="js/bootstrap-datetimepicker.zh-CN.js"></script>
<link rel="stylesheet" href="css/bootstrap-datetimepicker.min.css">
<style>
#picis {
	
}

#picis li {
	
}
</style>
</head>
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
	int update = 0;
	int bgupdate = 0;
	int ddupdate = 0;
	int cwupdate = 0;
	int updateshenpi = 0;
	int timeupdate = 0;
		
	List<Powers> power = (List<Powers>)request.getSession().getAttribute("power");
		for(int i=0; i<power.size(); i++){
	if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("订单修改")){
		update++;
	}
	if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("保管修改")){
		bgupdate++;
	}
	if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("调度修改")){
		ddupdate++;
	}
	if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("财务修改")){
		cwupdate++;
	}
	if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("订单修改审批")){
		updateshenpi++;
	}
	//修改订单时间的权限
	if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("订单时间修改")){
		timeupdate++;
	}
}
%>
<%
	if(update==0){
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
	$("#zhuangxie").selectize();
</script>
<body>
	<input type="hidden" value="<%=bgupdate%>" id="bgupdate" />
	<input type="hidden" value="<%=ddupdate%>" id="ddupdate" />
	<input type="hidden" value="<%=cwupdate%>" id="cwupdate" />
	<input type="hidden" value="<%=updateshenpi%>" id="updateshenpi" />
	<!--内容起始处-->
	<div id="mainFrame" class="main">
		<div class="daohang">
			<ul>
				<i>当前位置：</i>
				<a style="cursor: pointer;">数据中心</a>
				<span>/</span>&nbsp;
				<a href="javascript:window.history.go(-2);">订单查询</a>
				<span>/</span>&nbsp;
				<a href="javascript:window.history.go(-1);">入库详情</a>
				<span>/</span>&nbsp;
				<a href="javascript:document.location.reload();">入库订单修改</a>
				<span>/</span>&nbsp;
			</ul>
		</div>
		<%
			if (timeupdate != 0) {
		%>
		<a type="button" class="btn btn-warning"
			style="position:absolute; left:200px; top:50px;" data-toggle="tab"
			href="#timeupdate">订单时间修改</a>

		<%
			}
		%>
		
		<%
			if (bgupdate != 0 || ddupdate != 0) {
		%>
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
								<th>操作重量</th>
								<th>操作件数</th>
								<th>天车工</th>
								<th>装卸工</th>
								<th style="width: 100px;">操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${iolist }" var="io" varStatus="eos">
								<c:if test="${io.ioperateDefinedTwo!='订单作废' }">
									<tr>
										<td>${io.inputSeed.input.inputId }<input type="hidden"
											value="${io.inputSeed.goods.goodsId }" id="goodsId" />
											<input type="hidden" value="${io.ioperateDefinedTwo }" id="zt" />
										</td>
										<td>${io.ioperateId }</td>
										<td>${io.interiorUserByIoperateDispatcherId.iuserName }</td>
										<td>${io.ioperateDispatcherTime }</td>
										<td>${io.interiorUserByIoperateStoremanId.iuserName }</td>
										<td>${io.ioperatePonderationTrue }</td>
										<td>${io.tarehouse.tarehouseName }</td>
										<td>${io.ioperateRealityWeight }</td>
										<td>${io.ioperateRealityNumber }</td>
										<td>${io.ioperateCraneman }</td>
										<td>${io.ioperateStevedore }</td>
										<td><a type="button" class="btn btn-warning btn-xs"
											data-toggle="tab" href="#cangchuupdate"
											onclick="cangchubianji(this)">修改</a></td>
									</tr>
								</c:if>
								
							</c:forEach>
						</tbody>
					</table>
				</div>
				<!-- 订单发起时间的修改 -->
				<div id="timeupdate" class="tab-pane fade">
					<div class="modal-content" style="margin: auto 30%;">
						<div class="modal-header">
							<h4 class="modal-title" id="myModalLabel">修改入库订单发起时间</h4>
						</div>
						<div class="modal-body">
							<form class="form-horizontal" role="form"
								action="${pageContext.request.contextPath }/input.do?flag=UpdateTime"
								name="inputForm" id="timeupdateform" method="post">
								<div class="form-group">
									<label for="bh" class="col-sm-3 control-label">编号</label>
									<div class="col-sm-7">
										<input type="text" id="timeupdateId" class="form-control" readonly="readonly"
											value="${is.input.inputId }" name="inputId" />
									</div>
								</div>

								<div class="form-group" >
									<div class='input-group date col-sm-6 col-sm-7' style="margin:0px auto;">
										<input class="form-control" type="text" id="endTime"
											placeholder="日期修改" name="inputCreateTime" /> <span class="input-group-addon"><label
											class="glyphicon glyphicon-calendar" for="endTime"></lable>
										</span>
									</div>
								</div>
								<br /><br />
							</form>
						</div>
						<div class="modal-footer">
							<a type="button" class="btn btn-default" onclick="closes()"
								value="click" />关闭</a> <a type="button" class="btn btn-primary"
								id="timeupdatetijiao"> 提交更改 </a>
						</div>
					</div>
					<!-- /.modal-content -->
					<script>
						$(function(){
							//当点击事件的修改提交的时候触发
							$("#timeupdatetijiao").click(function(){
								if($("#timeupdateId").val()=="" || $("#timeupdateId").val()==null){
									alert("主键不可以为空！");
									return false;
								}
								if($("#endTime").val()=="" || $("#endTime")==null){
									alert("请选择要修改的时间值！");
									return false;
								}
								if(confirm("确定修改吗？")){
									$("#timeupdateform").submit();
								}
							});
						});
					</script>
				</div>
				<div id="cangchuupdate" class="tab-pane fade">
					<div class="modal-content" style="margin: auto 30%;">
						<div class="modal-header">
							<h4 class="modal-title" id="myModalLabel">修改入库订单</h4>
						</div>
						<div class="modal-body">
							<form class="form-horizontal" role="form"
								action="${pageContext.request.contextPath }/inputOperate.do?flag=updateInputOperate"
								name="exportOperateForm" id="cangchuupdateform" method="post">
								<input type="hidden" value="" id="goodsxuanze" />
								<div class="form-group">
									<label for="bh" class="col-sm-3 control-label">编号</label>
									<div class="col-sm-7">
										<input type="text" class="form-control" id="bh"
											readonly="readonly" name="ioperateId" />
									</div>
								</div>
								<div class="form-group">
									<label for="zhixing" class="col-sm-3 control-label">执行人</label>
									<div class="col-sm-7">

										<select class="form-control" id="zhixing"
											name="interiorUserByIoperateStoremanId">
										</select>
									</div>
								</div>
								<div class="form-group">
									<label for="lisuan" class="col-sm-3 control-label">过磅/理算</label>
									<div class="col-sm-7">
										<select class="form-control" id="lisuan"
											name="ioperatePonderationTrue">
											<option value="过磅">过磅</option>
											<option value="理算">理算</option>
										</select>
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
									<label for="weight" class="col-sm-3 control-label">操作重量</label>
									<div class="col-sm-7">
										<input type="text" class="form-control" id="weight"
											name="ioperateRealityWeight" />
									</div>
								</div>
								<div class="form-group">
									<label for="number" class="col-sm-3 control-label">操作件数</label>
									<div class="col-sm-7">
										<input type="text" class="form-control" id="number"
											name="ioperateRealityNumber" />
									</div>
								</div>
								<div class="form-group">
									<label for="tianche" class="col-sm-3 control-label">天车工</label>
									<div class="col-sm-7">
										<select class="form-control" id="tianche"
											name="ioperateCraneman">
										</select>
									</div>
								</div>
								<div class="form-group">
									<label for="zhuangxie" class="col-sm-3 control-label">装卸工</label>
									<div class="col-sm-7">
										<div id="guigeDiv1" class="zhuangxie">
											<select id="zhuangxie" class="demo-default" name="zhuangxieGong">
											</select>
										</div>
									</div>
								</div>
								<div class="form-group">
									<label for="beizhu1" class="col-sm-3 control-label">备注</label>
									<div class="col-sm-7">
										<input type="text" class="form-control" name="ioperateRemark"
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
		<%
			}
		%>
		<%
			if (cwupdate != 0) {
		%>
		<!--财务修改的开始           -->
		<!-- 
		<div class="container-fluid">
			<ul class="nav nav-tabs nav-pills"
				style="margin: 10px 2.5%; font-weight: bold;">
				<li class="active"><a data-toggle="tab" href="#home"> <span
						class="glyphicon glyphicon-list" aria-hidden="true"
						style="margin-right: 8px;"></span>财务修改</a></li>
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
								<th>操作重量</th>
								<th>操作件数</th>
								<th>应收费用</th>
								<th>实收费用</th>
								<th>结算方式</th>
								<th>支付方式</th>
								<th>票据类型</th>
								<th style="width: 100px;">操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${iolist }" var="io" varStatus="eos">
								<tr>
									<td>${io.inputSeed.input.inputId }<input type="hidden" value="${io.ioperateDefinedTwo }" id="zhuangtai" /></td>
									<td>${io.ioperateId }</td>
									<td>${io.interiorUserByIoperateDispatcherId.iuserName }</td>
									<td>${io.ioperateDispatcherTime }</td>
									<td>${io.interiorUserByIoperateStoremanId.iuserName }</td>
									<td>${io.ioperateRealityWeight }</td>
									<td>${io.ioperateRealityNumber }</td>
									<td>${io.ioperateShouldCost }</td>
									<td>${io.ioperateRealityCost }</td>
									<td>${io.ioperateClientAccounts }</td>
									<td>${io.ioperatePaymentFashion.pfashionName }</td>
									<td>${io.ioperatePaymentFashion.pfashionReceipt }<input
										type="hidden" value="${io.ioperatePaymentFashion.pfashionId }"
										id="pj" />
									</td>
									<td><a type="button" class="btn btn-warning btn-xs"
										data-toggle="tab" href="#caiwuupdate"
										onclick="caiwubianji(this)">修改</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div> -->
				<!-- 
				<div id="caiwuupdate" class="tab-pane fade">
					<div class="modal-content" style="margin: auto 30%;">
						<div class="modal-header">
							<h4 class="modal-title" id="myModalLabel">修改入库订单</h4>
						</div>
						<div class="modal-body">
							<form class="form-horizontal" role="form"
								action="${pageContext.request.contextPath }/exportOperate.do?flag=updateExportOperate"
								name="exportOperateForm" id="caiwuform" method="post">
								<div class="form-group">
									<label for="bh" class="col-sm-3 control-label">编号</label>
									<div class="col-sm-7">
										<input type="text" class="form-control" id="cwbh"
											readonly="readonly" name="ioperateId" />
									</div>
								</div>
								<div class="form-group">
									<label for="yscost" class="col-sm-3 control-label">实收费用</label>
									<div class="col-sm-7">
										<input type="text" class="form-control" id="yscost" name="ioperateRealityCost" />
									</div>
								</div>
								<div class="form-group">
									<label for="jiesuan" class="col-sm-3 control-label">结算方式</label>
									<div class="col-sm-7">
										<select class="form-control" id="jiesuan" name="ioperateClientAccounts">
											<option value="现结">现结</option>
											<option value="月结">月结</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label for="zhifu" class="col-sm-3 control-label">支付方式</label>
									<div class="col-sm-7">
										<select class="form-control" id="zhifu"
											onchange="piaojuleixing('0')">
										</select>
									</div>
								</div>

								<div class="form-group">
									<label for="tianche" class="col-sm-3 control-label" >票据类型</label>
									<div class="col-sm-7">
										<select class="form-control" id="piaoju" name="eoperatePaymentFashion">
										
										</select>
									</div>
								</div>
								<div class="form-group">
									<label for="beizhu1" class="col-sm-3 control-label">备注</label>
									<div class="col-sm-7">
										<input type="text" class="form-control" id="beizhu1" />
									</div>
								</div>
							</form>
						</div>
						<div class="modal-footer">
							<a type="button" class="btn btn-default" onclick="closes()"
								value="click" />关闭</a> <a type="button" class="btn btn-primary"
								id="caiwutijiao"> 提交更改 </a>
						</div>
					</div> -->
					<!-- /.modal-content -->
				</div>
			</div>
		</div>
		<!--财务修改的结束-->
		<%
			}
		%>
	</div>
	<!--提交修改申请-->
	<div id="updateshenqing" class="tab-pane fade">
		<div class="modal-content" style="margin: auto 30%;">
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel">修改入库订单申请</h4>
			</div>
			<div class="modal-body">
				<form
					action="${pageContext.request.contextPath }/updateRecordAction.do?flag=updateFaQi"
					name="updateRecordForm" method="post" id="updateshengqingform"
					class="form-horizontal" role="form">
					<input type="hidden" value="${is.iseedId }" name="urziid" /> <input
						type="hidden" value="${is.input.inputId }" name="urzongid" /> <input
						type="hidden" value="入库订单" name="urupdatetype" />
					<div class="form-group">
						<label for="shenqingbh" class="col-sm-3 control-label">编号</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" id="shenqingbh"
								readonly="readonly" name="urcaozuoid" />
						</div>
					</div>
					<div class="form-group">
						<label for="cost" class="col-sm-3 control-label">申请描述</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" id="miaoshu"
								name="urfaqimiaoshu" />
						</div>
					</div>
					<div class="form-group">
						<label for="beizhu1" class="col-sm-3 control-label">备注</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" id="beizhu1"
								name="urupdateremark" />
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<a type="button" class="btn btn-default" onclick="closes()"
					value="click" />关闭</a> <a type="button" class="btn btn-primary"
					id="shenqingtijiao"> 提交 </a>
			</div>
		</div>
		<!-- /.modal-content -->
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

