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
<link rel="stylesheet" href="css/bootstrap-datetimepicker.min.css">
<link rel="stylesheet" href="css/public.css" />
<script
	src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/bootstrap-datetimepicker.js"></script>
<script src="js/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="js/all.js"></script>
<script src="js/kuwei.js"></script>
<style type="text/css">
#kuweiweizhi {
	width: 205px;
	height: 35px;
	border-radius: 5px;
	border: 1px solid #CCCCCC;
}

#kuweiweizhi1 {
	width: 205px;
	height: 35px;
	border-radius: 5px;
	border: 1px solid #CCCCCC;
}
</style>
<script type="text/javascript">
	$(function() {

		$(".closeOk")
				.click(
						function() {
							document.location.href = "${pageContext.request.contextPath}/tarehouse.do?flag=getTarehouseAll";
						});

	});
	function fun() {
		$reg = /^(\d*\.)?\d+$/;
		if ($("#kuweimingcheng").val() == ""
				|| $("#kuweimingcheng").val() == null) {
			alert("库位名称不能为空！");
			return false;
		}
		if (!$reg.test($("#rongliang").val())
				|| !$reg.test($("#rongliang").val())) {
			alert("库位重量必须是浮点数！");
			return false;
		}
		if (!$reg.test($("#jianshu").val()) || !$reg.test($("#jianshu").val())) {
			alert("库位件数必须是浮点数！");
			return false;
		}

		return true;
	}

	function fun2() {
		$reg = /^(\d*\.)?\d+$/;
		if ($("#kuweimingcheng1").val() == ""
				|| $("#kuweimingcheng1").val() == null) {
			alert("库位名称不能为空！");
			return false;
		}
		if (!$reg.test($("#rongliang1").val())
				|| !$reg.test($("#rongliang1").val())) {
			alert("库位重量必须是浮点数！");
			return false;
		}
		if (!$reg.test($("#jianshu1").val())
				|| !$reg.test($("#jianshu1").val())) {
			alert("库位件数必须是浮点数！");
			return false;
		}

		return true;
	}
</script>
</head>

<body>
	<!--内容起始处-->
	<div id="mainFrame" class="main">
		<div class="daohang">
			<ul>
				<i>当前位置：</i>
				<a style="cursor:pointer;">仓储资料</a>
				<span>/</span>&nbsp;
				<a style="cursor:pointer;">库位管理</a>
				<span>/</span>&nbsp;
			</ul>
		</div>
		<div class="container">
			<ul class="nav nav-tabs nav-pills"
				style="margin: 10px 2.5%; font-weight: bold;">
				<li class="active"><a data-toggle="tab" href="#home"> <span
						class="glyphicon glyphicon-th-list" aria-hidden="true"
						style="margin-right: 8px;"></span>库位列表</a></li>
				<li><a data-toggle="tab" href="#menu1" id="addHouse"> <span
						class="glyphicon glyphicon-plus" aria-hidden="true"
						style="margin-right: 8px;"></span>新增库位</a></li>
				<li></li>
			</ul>

			<div class="tab-content">
				<div id="home" class="tab-pane fade in active">
					<!--  <div class="input-group col-md-3" style="float:right;border:1px solid #f2f2f2; margin-right: 30px;">
                 	<span class="glyphicon glyphicon-search" aria-hidden="true" style="margin-left: 8px; margin-top:8px; position: absolute;"></span>
					<input type="text" placeholder="" class="form-control" id="huowu" style="float:right; width:100%; border:0px; background: transparent;
						padding-left: 28px;" />
				</div> -->
					<div class="input-group input-group-sm col-md-4 "
						style="float:right; margin-right: 30px;">

						<input type="text" class="form-control" placeholder="库位搜索"
							id="kuwei" value="${kuwei }"> <span
							class="input-group-addon glyphicon glyphicon-search"
							style="cursor:pointer;" id="search"></span>
					</div>
					<script>
						$(function() {
							$("#kuwei").keydown(function(event) {
								if (event.keyCode == 13) {
									$("#search").click();
								}
							});
							$("#search")
									.click(
											function() {
												var kuwei = $("#kuwei").val();
												document.location.href = "${pageContext.request.contextPath}/tarehouse.do?flag=getTarehouseAll&kuwei="
														+ encodeURI(encodeURI(kuwei));
											});
						});
					</script>
					<table class="table table-striped table-hover">
						<thead>
							<tr>
								<th>编号</th>
								<th>库位名称</th>
								<th>库位容量（吨）</th>
								<th>库位容量（件数）</th>
								<th>库位位置</th>
								<th>备注</th>
								<th style="width: 100px;">编辑</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${tlist==null }">
								<c:redirect url="/tarehouse.do?flag=getTarehouseAll" />
							</c:if>
							<c:forEach items="${tlist }" var="lp" varStatus="v">
								<tr>
									<td>${v.index+1 }<input type="hidden" class="id" value="${lp.tarehouseId }"></td>
									<td>${lp.tarehouseName }</td>
									<td>${lp.tarehouseMaxWeight }</td>
									<td>${lp.tarehouseMaxNumber }</td>
									<td>${lp.bursary.bursaryName }</td>
									<td>${lp.tarehouseRemark }</td>
									<td><a class="btn btn-warning btn-xs aId"
										data-toggle="tab" href="#menu2">编辑</a> <!-- <button class="btn btn-danger btn-xs " data-toggle="modal" data-target="#disable${v.index }">停用</button>-->
										<!-- 模态框（Modal） -->
										<div class="modal fade bs-example-modal-sm " id="disable"
											tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
											aria-hidden="true">
											<div class="modal-dialog">
												<div class="modal-content">
													<div class="modal-header">
														<button type="button" class="close" data-dismiss="modal"
															aria-hidden="true">&times;</button>
													</div>
													<div class="modal-body">
														<h3>确定需要停用此库位？</h3>
													</div>
													<div class="modal-footer">
														<a class="btn btn-default closeOk" data-dismiss="modal">关闭</a>
														<a class="btn btn-primary"
															href="${pageContext.request.contextPath}/tarehouse.do?flag=getTarehouseAll&id=${lp.tarehouseId }">确定</a>
													</div>
												</div>
												<!-- /.modal-content -->
											</div>
											<!-- /.modal -->
										</div></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<!-- 新增库位 -->
				<div id="menu1" class="tab-pane fade">
					<div class="modal-content" style="margin: auto 25%;">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">新增库位</h4>
						</div>
						<form class="form-horizontal" method="post" name="tarehouseForm"
							action="${pageContext.request.contextPath}/tarehouse.do?flag=saveTarehouse"
							onSubmit="return fun()">
							<div class="modal-body">

								<div class="form-group">
									<label for="kuweimingcheng" class="col-sm-3 control-label">库位名称</label>
									<div class="col-sm-5">
										<input type="text" class="form-control" id="kuweimingcheng"
											name="tarehouseName">
									</div>
								</div>
								<div class="form-group">
									<label for="kuweiweizhi" class="col-sm-3 control-label">库位位置</label>
									<div class="col-sm-5" id="list">
										<select id="kuweiweizhi" name="bursary">

										</select>
									</div>
								</div>
								<div class="form-group">
									<label for="rongliang" class="col-sm-3 control-label">库位容量（吨）</label>
									<div class="col-sm-5">
										<input type="text" class="form-control" id="rongliang"
											name="tarehouseMaxWeight">
									</div>
								</div>
								<div class="form-group">
									<label for="jianshu" class="col-sm-3 control-label">库位容量（件）</label>
									<div class="col-sm-5">
										<input type="text" class="form-control" id="jianshu"
											name="tarehouseMaxNumber">
									</div>
								</div>
								<div class="form-group">
									<label for="beizhu" class="col-sm-3 control-label">备注</label>
									<div class="col-sm-5">
										<input type="text" class="form-control" id="beizhu"
											name="tarehouseRemark">
									</div>
								</div>

							</div>
							<div class="modal-footer">
								<a class="btn btn-primary closeOk">关闭</a> <input
									class="btn btn-primary" type="submit" value="提交" />
							</div>
						</form>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- 编辑库位 -->
				<div id="menu2" class="tab-pane fade">
					<div class="modal-content" style="margin: auto 25%;">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">编辑库位</h4>
						</div>
						<form class="form-horizontal" name="tarehouseForm" method="post"
							action="${pageContext.request.contextPath}/tarehouse.do?flag=updateTarehouse"
							onSubmit="return fun2()">
							<div class="modal-body">
								<input type="hidden" id="cdId" name="tarehouseId" />
								<div class="form-group">
									<label for="kuweimingcheng1" class="col-sm-3 control-label">库位名称</label>
									<div class="col-sm-5">
										<input type="text" class="form-control" id="kuweimingcheng1"
											name="tarehouseName">
									</div>
								</div>
								<div class="form-group">
									<label for="kuweiweizhi1" class="col-sm-3 control-label">库位位置</label>
									<div class="col-sm-5" id="listBianji">
										<select id="kuweiweizhi1" name="bursary">
											<option>xxxx</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label for="rongliang1" class="col-sm-3 control-label">库位容量（吨）</label>
									<div class="col-sm-5">
										<input type="text" class="form-control" id="rongliang1"
											name="tarehouseMaxWeight">
									</div>
								</div>
								<div class="form-group">
									<label for="jianshu1" class="col-sm-3 control-label">库位容量（件）</label>
									<div class="col-sm-5">
										<input type="text" class="form-control" id="jianshu1"
											name="tarehouseMaxNumber">
									</div>
								</div>
								<div class="form-group">
									<label for="beizhu1" class="col-sm-3 control-label">备注</label>
									<div class="col-sm-5">
										<input type="text" class="form-control" id="beizhu1"
											name="tarehouseRemark">
									</div>
								</div>

							</div>
							<div class="modal-footer">
								<a class="btn btn-primary closeOk">关闭</a> <input
									class="btn btn-primary" type="submit" value="提交" />
							</div>
						</form>
					</div>
					<!-- /.modal-content -->
				</div>
			</div>
		</div>
	</div>
</body>

</html>
