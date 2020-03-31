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
<script src="http://libs.baidu.com/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<style>
.table tr td {
	height: 50px;
	font-size: 16px;
}
</style>
</head>
<c:if test="${iu==null }">
	<script>
		document.location.href = "${pageContext.request.contextPath}/interiorUser.do?flag=getGeRen";
	</script>
</c:if>

<body>
	<div class="right" id="mainFrame">
		<div class="daohang">
			<ul>
				<i>当前位置：</i>
				<a style="cursor:pointer;">首页</a>
				<span>></span>&nbsp;
				<a href="javascript:document.location.reload();">个人资料</a>
				<span>/</span>&nbsp;
			</ul>
		</div>
		<div class="jbxx" role="form"
			style="width:60%; margin-left:20%; margin-right:30%">
			<h3 style="margin-left:10px; margin-right: 130px;">
				基本信息 <a type="submit" class="btn btn-primary" data-toggle="modal"
					href="#bianji" style="float: right;">编辑</a>
			</h3>
			<!-- 用户编辑模态框起始  id是编写方式：拼音首字母或者全拼或者英文-->
			<div class="modal fade" id="bianji" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content" style="margin: auto ;">
						<div class="modal-header">
							<h4 class="modal-title" id="myModalLabel">编辑用户资料</h4>
						</div>
						<form class="form-horizontal" role="form"
							action="/XGProject/interiorUser.do?flag=updatePassword"
							method="post" id="gerenForm" name="interiorUserForm">
							<div class="modal-body">
								<div class="form-group">
									<label for="zhanghao" class="col-sm-3 control-label">用户帐号</label>
									<div class="col-sm-5">
										<input type="text" class="form-control" id="帐号"
											readonly="readonly" value="${iu.iuserLoginName }">
									</div>
								</div>
								<div class="form-group">
									<label for="inpassword" class="col-sm-3 control-label">原密码</label>
									<div class="col-sm-5">
										<input type="password" class="form-control" id="yuanmima">
									</div>
								</div>
								<div class="form-group">
									<label for="inpassword" class="col-sm-3 control-label">新密码</label>
									<div class="col-sm-5">
										<input type="password" class="form-control" id="xinmima">
									</div>
								</div>
								<div class="form-group">
									<label for="inpassword" class="col-sm-3 control-label">再次输入新密码</label>
									<div class="col-sm-5">
										<input type="password" class="form-control" id="querenmima"
											name="iuserPassword">
									</div>
								</div>
								<div class="form-group">
									<label for="name" class="col-sm-3 control-label">用户姓名</label>
									<div class="col-sm-5">
										<input type="text" class="form-control" id="name"
											readonly="readonly" value="${iu.iuserName }">
									</div>
								</div>
								<div class="form-group">
									<label for="sex" class="col-sm-3 control-label">用户性别</label>
									<div class="col-sm-5">
										<input type="text" class="form-control" id="sex"
											readonly="readonly" value="${iu.iuserSex }">
									</div>
								</div>
								<div class="form-group">
									<label for="callnum" class="col-sm-3 control-label">联系电话</label>
									<div class="col-sm-5">
										<input type="text" class="form-control" id="callnum"
											value="${iu.iuserTel }" name="iuserTel">
									</div>
								</div>
								<div class="form-group">
									<label for="bumen" class="col-sm-3 control-label">部门</label>
									<div class="col-sm-5">
										<input type="text" class="form-control" list="bumen"
											readonly="readonly" value="${iu.classT.section.sectionName }">
										<datalist id="bumen">
											<option>仓储部</option>
											<option>财务部</option>
											<option>市场部</option>
											<option>总经理</option>
										</datalist>
									</div>
								</div>
								<div class="form-group">
									<label for="banzu" class="col-sm-3 control-label">班组</label>
									<div class="col-sm-5">
										<input type="text" class="form-control" list="banzu"
											readonly="readonly" value="${iu.classT.className }">
										<datalist id="banzu">
											<option>一班</option>
											<option>二班</option>
											<option>三班</option>
										</datalist>
									</div>
								</div>
								<div class="form-group">
									<label for="zhiwu" class="col-sm-3 control-label">职务</label>
									<div class="col-sm-5">
										<input type="text" class="form-control" list="zhiwu"
											readonly="readonly"
											value="${iu.interiorUserDuty.interiorUserDutyName }">
										<datalist id="zhiwu">
											<option>总经理</option>
											<option>仓储经理</option>
											<option>财务经理</option>
											<option>仓储主管</option>
											<option>调度员</option>
											<option>保管员</option>
											<option>审核员</option>
											<option>收费员</option>
											<option>司磅员</option>
										</datalist>
									</div>
								</div>
								<div class="form-group">
									<label for="time" class="col-sm-3 control-label">注册时间</label>
									<div class="col-sm-5">
										<input type="text" class="form-control" id="time"
											readonly="readonly" value="${iu.iuserCreateTime }">
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<a class="btn btn-default closeOk" onclick="closes()"
									value="click" />关闭</a> <input type="submit"
									class="btn btn-primary" value="提交" />
							</div>
						</form>
						<
						<!-- /.modal-content -->
					</div>
				</div>
			</div>
			<div class="from-group col-md-5">
				<table class="table table-striped">
					<tr>
						<td>姓名:</td>
						<td>${iu.iuserName }</td>
					</tr>
					<tr>
						<td>性别：</td>
						<td>${iu.iuserSex }</td>
					</tr>
					<tr>
						<td>联系电话：</td>
						<td>${iu.iuserTel }</td>
					</tr>
					<tr>
						<td>用户帐号：</td>
						<td>${iu.iuserLoginName }</td>
					</tr>
					<tr>
						<td>密码：</td>
						<td>**********<input type="hidden" value="${iu.iuserPassword }"
							readonly="readonly" style="border:0px; background: transparent;"
							id="chushimima" /></td>
					</tr>
				</table>
			</div>
			<div class="from-group col-md-5">
				<table class="table table-striped" style="margin-left:50px;">
					<tr>
						<td>部门:</td>
						<td>${iu.classT.section.sectionName }</td>
					</tr>
					<tr>
						<td>班组：</td>
						<td>${iu.classT.className }</td>
					</tr>
					<tr>
						<td>职务：</td>
						<td>${iu.interiorUserDuty.interiorUserDutyName }</td>
					</tr>
					<tr>
						<td>入职时间：</td>
						<td>${iu.iuserCreateTime }</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	<!-- 底部 -->
</body>
<script type="text/javascript">
	function closes() {
		alert("确定要关闭吗？");
		window.location.href = "${pageContext.request.contextPath}/home-page/user-info.jsp";
	}
	$(function() {
		$("#gerenForm").submit(function() {
			if ($("#yuanmima").val() == "") {
				alert("请输入初始密码！");
				return false;
			}
			if ($("#yuanmima").val() != $("#chushimima").val()) {
				alert("原密码错误，请重新输入！");
				return false;
			}
			if ($("#xinmima").val() == "") {
				alert("请输入新密码！");
				return false;
			}
			if ($("#querenmima").val() == "") {
				alert("请输入确认密码！");
				return false;
			}
			if ($("#xinmima").val() != $("#querenmima").val()) {
				alert("新密码和确认密码不一致，请重新输入！");
				return false;
			}
			var yan = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$/;
			if (yan.test($("#xinmima").val()) == false) {
				alert("新密码必须是数字和字母的组合，并且长度为8位，请重新填写！");
				return false;
			}
			
			if(confirm("确定修改吗？")==false){
				return false;
			}
		});
	});
</script>

</html>
