<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<script src="js/jquery.js"></script>
<script src="js/selectize.js"></script>
<script src="js/index.js"></script>
<script src="js/all.js"></script>
<script src="js/goods.js"></script>
<script type="text/javascript">
	$(function() {
		$(".closeOk")
				.click(
						function() {
							document.location.href = "${pageContext.request.contextPath}/goods.do?flag=goSelectGoods";
						});

	});
	function yesNoKong() {
		var goods = $("#huowu").val() == "" ? "" : $("#huowu").val();
		var g = encodeURI(encodeURI(goods));
		if ($("#textNull").val() == "" || $("#textNull").val() == null) {
			alert("请输入数字");
			return false;
		} else {
			$("#tiaoform").attr(
					"action",
					"${pageContext.request.contextPath}/goods.do?flag=goSelectGoods&goodsNme="
							+ g);
		}
		return true;
	}
</script>

<style type="text/css">
.updownPage {
	position: fixed;
	width: 600px;
	height: 300px;
	top: 90%;
	left: 35%;
}

.updownPage a {
	float: left;
	margin: 0 10px 0 10px;
}

.updownPage form {
	width: 350px;
	float: left;
}

.updownPage form input {
	width: 60px;
}
</style>
</head>

<body>
	<!--内容起始处-->
	<div id="mainFrame" class="main">
		<div class="daohang">
			<ul>
				<i>当前位置：</i>
				<a style="cursor:pointer;">仓储资料</a>
				<span>></span>&nbsp;
				<a style="cursor:pointer;">货物名称</a>
				<span>/</span>&nbsp;
			</ul>
		</div>
		<div class="container-fluid">
			<ul class="nav nav-tabs nav-pills"
				style="margin: 10px 2.5%; font-weight: bold;">
				<li class="active"><a data-toggle="tab" href="#home"> <span
						class="glyphicon glyphicon-th-list" aria-hidden="true"
						style="margin-right: 8px;"></span>货物列表</a></li>
				<li><a data-toggle="tab" href="#menu1" id="addGoods"> <span
						class="glyphicon glyphicon-plus" aria-hidden="true"
						style="margin-right: 8px;"></span>新增货物</a></li>
			</ul>
			<div class="tab-content">
				<div id="home" class="tab-pane fade in active">
					<!-- 搜索框 -->
					<div class="input-group input-group-sm col-md-3"
						style="float:right; margin-right: 30px;">

						<input type="text" class="form-control" id="huowu"
							placeholder="货物资料搜索" value="${goodsName==null?'':goodsName }">
						<span class="input-group-addon glyphicon glyphicon-search"
							style="cursor:pointer;" id="search"></span>
					</div>
					<script>
						$(function() {
							$("#huowu").keydown(function(event) {
								if (event.keyCode == 13) {
									$("#search").click();
								}
							});
							$("#search")
									.click(
											function() {
												var goods = $("#huowu").val() == "" ? ""
														: $("#huowu").val();
												document.location.href = "${pageContext.request.contextPath}/goods.do?flag=goSelectGoods&pageNow=1&goodsNme="
														+ encodeURI(encodeURI(goods));
											});
						});
					</script>
					<!-- 搜索狂结束 -->
					<table class="table table-striped table-hover">
						<thead>
							<tr>
								<th>编号</th>
								<th>货物名称</th>
								<th>助记符</th>
								<th>货物品类</th>
								<th>规格</th>
								<th>材质</th>
								<th>属性</th>
								<th>产地</th>
								<th>计件单位</th>
								<th>备注</th>
								<th style="width: 100px;">编辑</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${goodslist==null }">
								<c:redirect url="/goods.do?flag=goSelectGoods" />
							</c:if>
							<c:forEach items="${goodslist }" var="lp" varStatus="v">
								<tr>
									<td>${v.index+1 }<input type="hidden" class="huoId"
										value="${lp.goodsId }">
									</td>
									<td>${lp.goodsName }</td>
									<td>${lp.goodsSign }</td>
									<td>${lp.goodsCategory.goodsCategoryName }</td>
									<td>${lp.goodsStandard.goodsStandardName }</td>
									<td>${lp.goodsQuality.goodsQualityName }</td>
									<td>${lp.goodsProperty.goodsPropertyName }</td>
									<td>${lp.goodsYieldly.goodsYieldlyName }</td>
									<td>${lp.goodsUnit.goodsUnitName }</td>
									<td>${lp.goodsRemark}</td>
									<td><a class="btn btn-warning btn-xs bianjiOk"
										data-toggle="tab" href="#menu2">编辑</a> <a
										class="btn btn-danger btn-xs " data-toggle="modal"
										data-target="#disable${v.index }">停用</a> <!-- 模态框（Modal） -->
										<div class="modal fade bs-example-modal-sm "
											id="disable${v.index }" tabindex="-1"
											aria-labelledby="myModalLabel" aria-hidden="true">
											<div class="modal-dialog">
												<div class="modal-content">
													<div class="modal-body">
														<h3>确定需要停用此货物名称？</h3>
													</div>
													<div class="modal-footer">
														<a class="btn btn-default closeOk" data-dismiss="modal">关闭</a>
														<a class="btn btn-primary"
															href="${pageContext.request.contextPath}/goods.do?flag=stopGoods&id=${lp.goodsId }">确定</a>
													</div>
												</div>
												<!-- /.modal-content -->
											</div>
											<!-- /.modal -->
										</div>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<!-- 分页，上一页和下一页 -->
					<script>
						function shang() {
							var goods = $("#huowu").val() == "" ? "" : $(
									"#huowu").val();
							var g = encodeURI(encodeURI(goods));
							document.location.href = "${pageContext.request.contextPath}/goods.do?flag=goSelectGoods&pageNow=${pageNow2-1}&goodsNme="
									+ g;
						}
						function xia() {
							var goods = $("#huowu").val() == "" ? "" : $(
									"#huowu").val();
							var g = encodeURI(encodeURI(goods));
							document.location.href = "${pageContext.request.contextPath}/goods.do?flag=goSelectGoods&pageNow=${pageNow2+1}&goodsNme="
									+ g;
						}
						function tiao() {
							var goods = $("#huowu").val() == "" ? "" : $(
									"#huowu").val();
							var g = encodeURI(encodeURI(goods));
							document.location.href = "${pageContext.request.contextPath}/goods.do?flag=goSelectGoods&pageNow=${pageNow2+1}&goodsNme="
									+ g;
						}
					</script>
					<div class="updownPage">
						<a class="btn btn-primary" onclick="shang()">上一页</a> <a
							class="btn btn-primary" onclick="xia()">下一页</a>
						<form name="goodsForm" method="post" onsubmit="return yesNoKong()"
							id="tiaoform">
							跳转到<input type="text" id="textNull" placeholder="数字" name="yeshu"
								onkeyup="this.value=this.value.replace(/[^0-9-]+/,'');"
								style="text-align:center;" value="${pageNow2}" />&nbsp;页&nbsp;&nbsp;&nbsp;<input
								class="btn btn-primary" type="submit" value="确定" />
						</form>
					</div>
					<a href="${pageContext.request.contextPath }/cangchu/page/goodsdaochu.jsp" class="btn btn-primary" style="margin-left:50px;">导出</a>
				</div>
				<!-- 新增货物名称 -->
				<div id="menu1" class="tab-pane fade">
					<div class="modal-content">
						<form class="form-horizontal" method="post" name="goodsForm"
							action="${pageContext.request.contextPath}/goods.do?flag=addGoods"
							onSubmit="return fun()">
							<div class="modal-header">
								<h4 class="modal-title" id="myModalLabel">新增货物</h4>
							</div>
							<div class="modal-body">
								<div class="form-group">
									<label for="name" class="col-sm-3 control-label">货物名称</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" id="name"
											name="goodsName">
									</div>
								</div>
								<div class="form-group">
									<label for="zhujifu" class="col-sm-3 control-label">助记符</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" id="zhujifu"
											name="goodsSign">
									</div>
								</div>
								<div class="form-group">
									<label for="pinlei" class="col-sm-3 control-label">货物品类</label>
									<div class="col-sm-6" id="pinleiDiv">
										<select id="pinlei" class="form-control"
											name="goodsCategoryId">

										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">规格</label>
									<div class="control-group col-sm-6" id="guigeDiv">
										<select id="standard" class="demo-default standard"
											name="standardId">

										</select>
									</div>
								</div>
								<div class="form-group">
									<label for="quality" class="col-sm-3 control-label">材质</label>
									<div class="control-group col-sm-6" id="caizhiDiv">
										<select id="quality" class="demo-default" name="qualityId">

										</select>
									</div>
								</div>
								<div class="form-group">
									<label for="property" class="col-sm-3 control-label">属性</label>
									<div class="control-group col-sm-6" id="shuxinDiv">
										<select id="property" class="demo-default" name="propertyId">

										</select>
									</div>
								</div>
								<div class="form-group">
									<label for="chandi" class="col-sm-3 control-label">产地</label>
									<div class="control-group col-sm-6" id="chadiDiv">
										<select id="chandi" class="demo-default" name="yieldlyId">

										</select>
									</div>
								</div>
								<div class="form-group">
									<label for="jjdw" class="col-sm-3 control-label">计件单位</label>
									<div class="col-sm-6" id="jijianDiv">
										<select id="jjdw" class="form-control" name="goodsUnitId">

										</select>
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<a class="btn btn-default closeOk">关闭</a> <input type="submit"
									class="btn btn-primary tijiao" value="提交" />
							</div>
						</form>
					</div>
				</div>
				<!-- 编辑货物名称 -->
				<div id="menu2" class="tab-pane fade">
					<div class="modal-content">
						<form class="form-horizontal" name="goodsForm" method="post"
							action="${pageContext.request.contextPath}/goods.do?flag=updateGoods"
							onSubmit="return fun2()">
							<div class="modal-header">
								<h4 class="modal-title" id="myModalLabel">编辑货物</h4>
							</div>
							<input type="hidden" id="bianhao1" name="goodsId" />
							<div class="modal-body">
								<div class="form-group">
									<label for="name1" class="col-sm-3 control-label">货物名称</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" id="name1"
											name="goodsName">
									</div>
								</div>
								<div class="form-group">
									<label for="zhujifu1" class="col-sm-3 control-label">助记符</label>
									<div class="col-sm-6">
										<input type="text" class="form-control" id="zhujifu1"
											value="lwg" name="goodsSign">
									</div>
								</div>
								<div class="form-group">
									<label for="pinlei1" class="col-sm-3 control-label">货物品类</label>
									<div class="col-sm-6" id="#pinleiDiv1">
										<select class="form-control" id="pinlei1"
											name="goodsCategoryId">
											<option>xxxx</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label for="standard1" class="col-sm-3 control-label">规格</label>
									<div class="control-group col-sm-6" id="guigeDiv1">
										<select id="standard1" class="demo-default"
											name="goodsStandardId">
											<option>xxxx</option>

										</select>
									</div>
								</div>
								<div class="form-group">
									<label for="quality1" class="col-sm-3 control-label">材质</label>
									<div class="control-group col-sm-6" id="caizhiDiv1">
										<select id="quality1" class="demo-default"
											name="goodsQualityId">
											<option>xxxx</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label for="property1" class="col-sm-3 control-label">属性</label>
									<div class="control-group col-sm-6" id="shuxinDiv1">
										<select id="property1" class="demo-default"
											name="goodsPropertyId">
											<option>xxxx</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label for="chandi1" class="col-sm-3 control-label">产地</label>
									<div class="control-group col-sm-6" id="chandiDiv1">
										<select id="chandi1" class="demo-default"
											name="goodsYieldlyId">
											<option>xxxx</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label for="jjdw1" class="col-sm-3 control-label">计件单位</label>
									<div class="col-sm-6" id="jijianDiv1">
										<select class="form-control" id="jjdw1" name="goodsCategoryId">
											<option>件</option>
											<option>张</option>
										</select>
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<a class="btn btn-primary closeOk">关闭</a> <input
									class="btn btn-primary" type="submit" value="提交" />
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>

</html>
