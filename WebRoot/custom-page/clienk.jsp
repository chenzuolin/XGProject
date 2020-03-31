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
<title>仓储管理系统</title>
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
<script type="text/javascript">
    	$(".closeOk").click(function(){
				document.location.href="${pageContext.request.contextPath}/client.do?flag=selectGoodsProperty";
		});
		
		function yesNoKong(){
	   		if($("#textNull").val()=="" || $("#textNull").val()==null){
	   			alert("请输入数字");
	   			return false;   			
	   		}
	   		return true;
   		 }
    </script>
<style type="text/css">
.updownPage {
	position: relative;
	width: 600px;
	height: 300px;
	margin: 0 auto;
	top: 0px;
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
				<a style="cursor:pointer;">客户管理</a>
				<span>/</span>&nbsp;
				<a href="javascript:document.location.reload();">客户信息</a>
				<span>/</span>&nbsp;
			</ul>
		</div>
		<div class="container-fluid">
			<ul class="nav nav-tabs nav-pills"
				style="margin: 10px 2.5%; font-weight: bold;">
				<li class="active"><a data-toggle="tab" href="#home"> <span
						class="glyphicon glyphicon-th-list" aria-hidden="true"
						style="margin-right: 8px;"></span>客户列表</a>
				</li>
				<!-- <li>
                    <a  href="${pageContext.request.contextPath}/client.do?flag=goAddClient">
                        <span class="glyphicon glyphicon-plus" aria-hidden="true" style="margin-right: 8px;"></span>添加客户</a>
                </li> -->
				<!-- <li><a data-toggle="tab" href="#menu2">编辑职务</a></li> -->
			</ul>
			<div class="tab-content">
				<!-- 职务列表 -->
				<div id="home" class="tab-pane fade in active">
					<table class="table table-striped table-hover">
						<thead>
							<tr>
								<th>客户编号</th>
								<th>登录名</th>
								<th>注册时间</th>
								<th>联系电话</th>
								<th>单位名称</th>
								<th>合同号</th>
								<th>负责人</th>
								<th>地址</th>
								<th style="width: 100px;">操作</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${clientlist==null }">
								<c:redirect url="/client.do?flag=selectClient" />
							</c:if>
							<c:forEach items="${clientlist }" var="lp" varStatus="v">
								<tr>
									<td>${lp.clientId }</td>
									<td>${lp.clientLoginName }</td>
									<td>${lp.clientCreateTime }</td>
									<td>${lp.clientTel }</td>
									<td>${lp.clientFirmName }</td>
									<td>${lp.clientContract }</td>
									<td>${lp.clientHuman }</td>
									<td>${lp.clientAddress }</td>
									<td><a class="btn btn-warning btn-xs aId"
										href="${pageContext.request.contextPath}/client.do?flag=goChakanClient&id=${lp.clientId }">查看</a>
										<a class="btn btn-danger btn-xs " data-toggle="modal"
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
														<h3>确定需要停用此客户吗？</h3>
													</div>
													<div class="modal-footer">
														<a class="btn btn-default closeOk" data-dismiss="modal">关闭</a>
														<a class="btn btn-primary"
															href="${pageContext.request.contextPath}/client.do?flag=stopClient&id=${lp.clientId }">确定</a>
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
				</div>
				<!-- 分页，上一页和下一页 -->
				<div class="updownPage">
					<a class="btn btn-primary"
						href="${pageContext.request.contextPath}/client.do?flag=selectClient&pageNow=${pageNow2-1}">上一页</a>
					<a class="btn btn-primary"
						href="${pageContext.request.contextPath}/client.do?flag=selectClient&pageNow=${pageNow2+1}">下一页</a>
					<form name="goodsForm"
						action="${pageContext.request.contextPath}/client.do?flag=selectClient"
						method="post" onsubmit="return yesNoKong()">
						跳转到<input style="text-align:center;" value="${pageNow2}"
							type="text" id="textNull" placeholder="数字" name="yeshu"
							onkeyup="this.value=this.value.replace(/[^0-9-]+/,'');" />&nbsp;页&nbsp;&nbsp;&nbsp;<input
							class="btn btn-primary" type="submit" value="确定" /> <a
							href="${pageContext.request.contextPath }/cangchu/page/clientdaochu.jsp"
							style="float:right;" class="btn btn-primary">导出</a>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>

</html>
