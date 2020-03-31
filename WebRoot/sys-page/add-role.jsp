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
<link rel="stylesheet" href="css/layui.css" />
<script src="http://libs.baidu.com/jquery/2.1.1/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/bootstrap-datetimepicker.js"></script>
<script src="js/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="js/all.js"></script>
<script src="js/zhiwu.js"></script>
<script src="js/layui.all.js"></script>
<script type="text/javascript">
    function fun(){
		if($(".zhiwu").val()=="" || $(".zhiwu").val()==null){
			alert("职务名称不能为空！");
			return false;
		}		
		return true;			
	}
	
	function fun2(){
		if($("#zhiwuname").val()=="" || $("#zhiwuname").val()==null){
			alert("职务名称不能为空！");
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
				<a style="cursor:pointer;">系统管理</a>
				<span>/</span>&nbsp;
				<a style="cursor:pointer;">角色权限</a>
				<span>/</span>&nbsp;
			</ul>
		</div>
		<div class="container">
			<ul class="nav nav-tabs nav-pills"
				style="margin: 10px 2.5%; font-weight: bold;">
				<li class="active"><a data-toggle="tab" href="#home"> <span
						class="glyphicon glyphicon-th-list" aria-hidden="true"
						style="margin-right: 8px;"></span>职务列表</a></li>
				<li><a data-toggle="tab" href="#menu1" id="addZhiwu"> <span
						class="glyphicon glyphicon-plus" aria-hidden="true"
						style="margin-right: 8px;"></span>添加职务</a></li>
				<!-- <li><a data-toggle="tab" href="#menu2">编辑职务</a></li> -->
			</ul>
			<div class="tab-content">
				<!-- 职务列表 -->
				<div id="home" class="tab-pane fade in active">
					<table class="table table-striped table-hover">
						<thead>
							<tr>
								<th>编号</th>
								<th>职务名称</th>
								<th>职务描述</th>
								<th style="width: 100px;">编辑</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${listInteriorUserDuty==null }">
								<c:redirect
									url="/interiorUserDuty.do?flag=selectInteriorUserDuty" />
							</c:if>
							<c:forEach items="${listInteriorUserDuty }" var="lp"
								varStatus="v">
								<tr>
									<td>${v.index+1 }<input type="hidden" class="id"
										value="${lp.interiorUserDutyId }">
									</td>
									<td>${lp.interiorUserDutyName }</td>
									<td>${lp.interiorUserDutyRemark }</td>
									<td><a class="btn btn-warning btn-xs aId"
										data-toggle="tab" href="#menu2">编辑</a> <a
										class="btn btn-danger btn-xs " data-toggle="modal"
										data-target="#disable${v.index }">停用</a> <!-- 模态框（Modal） -->
										<div class="modal fade bs-example-modal-sm "
											id="disable${v.index }" tabindex="-1" role="dialog"
											aria-labelledby="myModalLabel" aria-hidden="true">
											<div class="modal-dialog">
												<div class="modal-content">
													<div class="modal-header">
														<h4 class="modal-title" id="myModalLabel">停用操作员</h4>
													</div>
													<div class="modal-body">
														<h3>确定需要停用此职务吗？</h3>
													</div>
													<div class="modal-footer">
														<a class="btn btn-default closeOk" data-dismiss="modal">关闭</a>
														<a class="btn btn-primary"
															href="${pageContext.request.contextPath}/interiorUserDuty.do?flag=stopInteriorUserDuty&id=${lp.interiorUserDutyId }">确定</a>
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
				<!-- 添加职务 -->

				<div id="menu1" class="tab-pane fade">
					<form class="form-horizontal" method="post"
						name="interiorUserDutyForm"
						action="${pageContext.request.contextPath}/interiorUserDuty.do?flag=addinteriorUserDuty"
						onSubmit="return fun()">
						<div class="container" style="margin-top: 5px; ">
							<div class="form-group">
								<label for="role-name" class="col-sm-2 control-label">职务名称:</label>
								<div class="col-sm-5">
									<input type="text" class="form-control zhiwu"
										name="interiorUserDutyName" id="role-name">
								</div>
							</div>
							<div class="form-group">
								<label for="role-description" class="col-sm-2 control-label">职务描述:</label>
								<div class="col-sm-5">
									<textarea class="form-control beizhu" rows="3"
										name="interiorUserDutyRemark"></textarea>
								</div>
							</div>
							<!-- 权限 -->
							<div class="layui-tab layui-tab-card">
								<ul class="layui-tab-title">
									<li class="layui-this">系统管理</li>
									<li>客户管理</li>
									<li>数据中心</li>
									<li>财务管理</li>
									<li>仓储资料</li>
									<li>仓储业务</li>
									<li>首页权限</li>
								</ul>
								<div class="layui-tab-content" style="height: auto;"id="bianjiTbody">
									<div class="layui-tab-item layui-show">
										<table class="table" >
											<thead>
												<tr class="success">
													<th>权限类别</th>
													<th>权限名称</th>
													<th>权限描述</th>
												</tr>
											</thead>
											<tbody id="addxitong">
												<!-- 入库权限 -->

											</tbody>
										</table>
									</div>
									<div class="layui-tab-item">
										<table class="table" >
											<thead>
												<tr class="success">
													<th>权限类别</th>
													<th>权限名称</th>
													<th>权限描述</th>
												</tr>
											</thead>
											<tbody id="addkehuguanli">
												<!-- 入库权限 -->

											</tbody>
										</table>
									</div>
									<div class="layui-tab-item">
										<table class="table" >
											<thead>
												<tr class="success">
													<th>权限类别</th>
													<th>权限名称</th>
													<th>权限描述</th>
												</tr>
											</thead>
											<tbody id="addshujuzhongxin">
												<!-- 入库权限 -->

											</tbody>
										</table>
									</div>
									<div class="layui-tab-item">
										<table class="table" >
											<thead>
												<tr class="success">
													<th>权限类别</th>
													<th>权限名称</th>
													<th>权限描述</th>
												</tr>
											</thead>
											<tbody id="addcaiwuguanli">
												<!-- 入库权限 -->

											</tbody>
										</table>
									</div>
									<div class="layui-tab-item">
										<table class="table" >
											<thead>
												<tr class="success">
													<th>权限类别</th>
													<th>权限名称</th>
													<th>权限描述</th>
												</tr>
											</thead>
											<tbody id="addcangchuziliao">
												<!-- 入库权限 -->

											</tbody>
										</table>
									</div>
									<div class="layui-tab-item">
										<table class="table" >
											<thead>
												<tr class="success">
													<th>权限类别</th>
													<th>权限名称</th>
													<th>权限描述</th>
												</tr>
											</thead>
											<tbody id="addcangchuyewu">
												<!-- 入库权限 -->

											</tbody>
										</table>
									</div>
									<div class="layui-tab-item">
										<table class="table" >
											<thead>
												<tr class="success">
													<th>权限类别</th>
													<th>权限名称</th>
													<th>权限描述</th>
												</tr>
											</thead>
											<tbody id="addshouyequanxian">
												<!-- 入库权限 -->

											</tbody>
										</table>
									</div>
								</div>
							</div>
							<input type="submit" class="btn btn-primary"
								style="float: right;" value="提交">

						</div>
					</form>
				</div>

				<!-- 编辑职务 -->
				<div id="menu2" class="tab-pane fade">
					<form class="form-horizontal" method="post"
						name="interiorUserDutyForm"
						action="${pageContext.request.contextPath}/interiorUserDuty.do?flag=updateInteriorUserDuty"
						onSubmit="return fun2()">
						<div class="container" style="margin-top: 5px; ">
							<input type="hidden" id="cdId" name="interiorUserDutyId">
							<div class="form-group">
								<label for="role-name" class="col-sm-2 control-label">职务名称:</label>
								<div class="col-sm-5">
									<input type="text" class="form-control" id="zhiwuname"
										name="interiorUserDutyName">
								</div>
							</div>
							<div class="form-group">
								<label for="role-description" class="col-sm-2 control-label">职务描述:</label>
								<div class="col-sm-5">
									<textarea class="form-control" rows="3" id="textarea"
										name="interiorUserDutyRemark"></textarea>
								</div>
							</div>
							<div class="layui-tab layui-tab-card">
								<ul class="layui-tab-title">
									<li class="layui-this">系统管理</li>
									<li>客户管理</li>
									<li>数据中心</li>
									<li>财务管理</li>
									<li>仓储资料</li>
									<li>仓储业务</li>
									<li>首页权限</li>
								</ul>
								<div class="layui-tab-content" style="height: auto;"id="bianjiTbody">
									<div class="layui-tab-item layui-show">
										<table class="table" >
											<thead>
												<tr class="success">
													<th>权限类别</th>
													<th>权限名称</th>
													<th>权限描述</th>
												</tr>
											</thead>
											<tbody id="xitong">
												<!-- 入库权限 -->

											</tbody>
										</table>
									</div>
									<div class="layui-tab-item">
										<table class="table" >
											<thead>
												<tr class="success">
													<th>权限类别</th>
													<th>权限名称</th>
													<th>权限描述</th>
												</tr>
											</thead>
											<tbody id="kehuguanli">
												<!-- 入库权限 -->

											</tbody>
										</table>
									</div>
									<div class="layui-tab-item">
										<table class="table" >
											<thead>
												<tr class="success">
													<th>权限类别</th>
													<th>权限名称</th>
													<th>权限描述</th>
												</tr>
											</thead>
											<tbody id="shujuzhongxin">
												<!-- 入库权限 -->

											</tbody>
										</table>
									</div>
									<div class="layui-tab-item">
										<table class="table" >
											<thead>
												<tr class="success">
													<th>权限类别</th>
													<th>权限名称</th>
													<th>权限描述</th>
												</tr>
											</thead>
											<tbody id="caiwuguanli">
												<!-- 入库权限 -->

											</tbody>
										</table>
									</div>
									<div class="layui-tab-item">
										<table class="table" >
											<thead>
												<tr class="success">
													<th>权限类别</th>
													<th>权限名称</th>
													<th>权限描述</th>
												</tr>
											</thead>
											<tbody id="cangchuziliao">
												<!-- 入库权限 -->

											</tbody>
										</table>
									</div>
									<div class="layui-tab-item">
										<table class="table" >
											<thead>
												<tr class="success">
													<th>权限类别</th>
													<th>权限名称</th>
													<th>权限描述</th>
												</tr>
											</thead>
											<tbody id="cangchuyewu">
												<!-- 入库权限 -->

											</tbody>
										</table>
									</div>
									<div class="layui-tab-item">
										<table class="table" >
											<thead>
												<tr class="success">
													<th>权限类别</th>
													<th>权限名称</th>
													<th>权限描述</th>
												</tr>
											</thead>
											<tbody id="shouyequanxian">
												<!-- 入库权限 -->

											</tbody>
										</table>
									</div>
								</div>
							</div>
							<input type="submit" class="btn btn-primary"
								style="float: right;" value="提交  ">
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>

</html>
