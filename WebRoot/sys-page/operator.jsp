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
<script src="js/all.js"></script>
<script src="js/addinteriorUser.js"></script>

<script type="text/javascript">
    
    $(function() {
        $(".closeOk").click(function(){
				document.location.href="${pageContext.request.contextPath}/interiorUser.do?flag=selectInteriorUser";
		});
    });

    
    </script>
<style type="text/css">
.allSelect {
	width: 205px;
	height: 35px;
	border-radius: 5px;
	border: 1px solid #CCCCCC;
}
</style>
</head>

<body>
<%
	int x = 0;
		
		List<Powers> power = (List<Powers>)request.getSession().getAttribute("power");
		for(int i=0; i<power.size(); i++){
	if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("注册管理员")){
		x++;
	}
		}
%>
<input type="hidden" value="<%=x %>" id="zhuceguanliyuan" />

	<!--内容起始处-->
	<div id="mainFrame" class="main">
		<div class="daohang">
			<ul>
				<i>当前位置：</i>
				<a style="cursor:pointer;">系统管理</a>
				<span>/</span>&nbsp;
				<a href="javascript:document.location.reload();">系统操作员管理</a>
				<span>/</span>&nbsp;
			</ul>
		</div>
		<div class="container-fluid">
			<ul class="nav nav-tabs nav-pills"
				style="margin: 10px 2.5%; font-weight: bold;">
				<li class="active"><a data-toggle="tab" href="#home"> <span
						class="glyphicon glyphicon-th-list" aria-hidden="true"
						style="margin-right: 8px;"></span>操作员列表</a></li>
				<li><a data-toggle="tab" href="#menu1" id="addOperator"> <span
						class="glyphicon glyphicon-plus" aria-hidden="true"
						style="margin-right: 8px;"></span>新增操作员</a></li>
			</ul>
			<div class="tab-content">
				<div id="home" class="tab-pane fade in active">
					<table class="table table-striped table-hover">
						<thead>
							<tr>
								<th>编号</th>
								<th>用户姓名</th>
								<th>性别</th>
								<th>部门</th>
								<th>班组</th>
								<th>职务</th>
								<th>用户帐号</th>
								<th>密码</th>
								<th>注册时间</th>
								<th>联系电话</th>
								<th>在线状态</th>
								<th style="width: 100px;">编辑</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${listInteriorUser==null }">
								<c:redirect url="/interiorUser.do?flag=selectInteriorUser" />
							</c:if>
							<c:forEach items="${listInteriorUser }" var="lp" varStatus="v">
								<tr>
									<td>${lp.iuserId }</td>
									<td>${lp.iuserName}</td>
									<td>${lp.iuserSex }</td>
									<td>${lp.section.sectionName }</td>
									<td>${lp.classT.className }</td>
									<td>${lp.interiorUserDuty.interiorUserDutyName }</td>
									<td>${lp.iuserLoginName }</td>
									<td>${lp.iuserPassword }</td>
									<td>${lp.iuserCreateTime }</td>
									<td>${lp.iuserTel }</td>
									<c:choose>
										<c:when test="${lp.iuserOnline==0 }">
											<td>是</td>
										</c:when>
										<c:otherwise>
											<td>否</td>
										</c:otherwise>
									</c:choose>
									<td>
										<button class="btn btn-warning btn-xs aId">
											<a data-toggle="tab" href="#menu2">编辑</a>
										</button>
										<button class="btn btn-danger btn-xs " data-toggle="modal"
											data-target="#disable" onclick="tingyong(this)">停用</button> <!-- 模态框（Modal） -->
										<div class="modal fade bs-example-modal-sm " id="disable"
											tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
											aria-hidden="true">
											<div class="modal-dialog">
												<div class="modal-content">
													<div class="modal-header">
														<h4 class="modal-title" id="myModalLabel">停用操作员</h4>
													</div>
													<div class="modal-body">
														<h3>确定需要停用此操作员？</h3>
													</div>
													<div class="modal-footer">
														<a class="btn btn-default closeOk" data-dismiss="modal">关闭</a>
														<a class="btn btn-primary"
															href="${pageContext.request.contextPath}/interiorUser.do?flag=stopInteriorUser&id=${lp.iuserId }"
															id="queding">确定</a>
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
				<script>
					function tingyong(str){
						$(function(){
							var id = $(str).parents("tr").children("td:first").text();
							$("#queding").attr("href","${pageContext.request.contextPath}/interiorUser.do?flag=stopInteriorUser&id="+id);
						});
					}
				</script>
				<div id="menu1" class="tab-pane fade">
					<div class="modal-content" style="margin: auto 30%;">
						<div class="modal-header">
							<h4 class="modal-title" id="myModalLabel">新增操作员</h4>
						</div>
						<form class="form-horizontal" method="post"
							name="interiorUserForm"
							action="${pageContext.request.contextPath}/interiorUser.do?flag=addInteriorUserOk"
							onSubmit="return fun()">
							<div class="modal-body">
								<div class="form-group">
									<label for="zhanghao" class="col-sm-3 control-label">用户帐号</label>
									<div class="col-sm-5">
										<input type="text" class="form-control" id="zhanghao"
											name="iuserLoginName">
									</div>
								</div>
								<div class="form-group">
									<label for="inpassword" class="col-sm-3 control-label">用户密码</label>
									<div class="col-sm-5">
										<input type="text" class="form-control" id="pwd"
											name="iuserPassword">
									</div>
								</div>
								<div class="form-group">
									<label for="name" class="col-sm-3 control-label">用户姓名</label>
									<div class="col-sm-5">
										<input type="text" class="form-control" id="name"
											name="iuserName">
									</div>
								</div>
								<div class="form-group">
									<label for="sex" class="col-sm-3 control-label">用户性别</label>
									<div class="col-sm-5">
										<input type="text" class="form-control" id="sex"
											name="iuserSex">
									</div>
								</div>
								<div class="form-group">
									<label for="callnum" class="col-sm-3 control-label">联系电话</label>
									<div class="col-sm-5">
										<input type="text" class="form-control" id="callnum"
											name="iuserTel">
									</div>
								</div>
								<div class="form-group">
									<label for="bumen" class="col-sm-3 control-label">部门</label>
									<div class="col-sm-5" id="bumenDiv">
										<select id="bumenAdd" class="allSelect" name="section">
											<option>xxxx</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label for="banzu" class="col-sm-3 control-label">班组</label>
									<div class="col-sm-5" id="banzuDiv">
										<select id="banzuAdd" class="allSelect" name="classT">
											<option>xxxxx</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label for="zhiwu" class="col-sm-3 control-label">职务</label>
									<div class="col-sm-5" id="zhiwuDiv">
										<select id="zhiwuAdd" class="allSelect"
											name="interiorUserDuty">
											<option>xxxx</option>

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
					<!-- /.modal-content -->
				</div>
				<div id="menu2" class="tab-pane fade">
					<div class="modal-content" style="margin: auto 30% ;">
						<form class="form-horizontal" method="post"
							name="interiorUserForm"
							action="${pageContext.request.contextPath}/interiorUser.do?flag=updateinteriorUser"
							onSubmit="return fun2()">
							<div class="modal-header">
								<h4 class="modal-title" id="myModalLabel">编辑操作员</h4>
							</div>

							<div class="modal-body">
								<input type="hidden" id="cdId" name="iuserId" />
								<div class="form-group">
									<label for="zhanghao" class="col-sm-3 control-label">用户帐号</label>
									<div class="col-sm-5">
										<input type="text" id="loginName" class="form-control"
											name="iuserLoginName">
									</div>
								</div>
								<div class="form-group">
									<label for="inpassword" class="col-sm-3 control-label">用户密码</label>
									<div class="col-sm-5">
										<input type="text" class="form-control" id="password"
											name="iuserPassword">
									</div>
								</div>
								<div class="form-group">
									<label for="name" class="col-sm-3 control-label">用户姓名</label>
									<div class="col-sm-5">
										<input type="text" class="form-control" id="iusername"
											name="iuserName">
									</div>
								</div>
								<div class="form-group">
									<label for="sex" class="col-sm-3 control-label">用户性别</label>
									<div class="col-sm-5">
										<input type="text" class="form-control" id="iusersex"
											name="iuserSex">
									</div>
								</div>
								<div class="form-group">
									<label for="callnum" class="col-sm-3 control-label">联系电话</label>
									<div class="col-sm-5">
										<input type="text" class="form-control" id="iuserTel"
											name="iuserTel">
									</div>
								</div>
								<div class="form-group">
									<label for="bumen" class="col-sm-3 control-label">部门</label>
									<div class="col-sm-5" id="bumenDiv2">
										<select id="bumenBianji" class="allSelect" name="section">
											<option>xxxx</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label for="banzu" class="col-sm-3 control-label">班组</label>
									<div class="col-sm-5" id="banzuDiv2">
										<select id="banzuBianji" class="allSelect" name="classT">
											<option>xxxx</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label for="zhiwu" class="col-sm-3 control-label">职务</label>
									<div class="col-sm-5" id="zhiwuDiv2">
										<select id="zhiwuBianji" class="allSelect"
											name="interiorUserDuty">
											<option>xxxx</option>
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
					<!-- /.modal-content -->
				</div>
			</div>
		</div>
	</div>
</body>

</html>
