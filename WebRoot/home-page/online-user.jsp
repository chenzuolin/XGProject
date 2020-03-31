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
<link rel="stylesheet" href="css/font-awesome.min.css">
<link rel="stylesheet" href="css/public.css" />
<script
	src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
</head>
<script type="text/javascript">
	$(function() {
		$.ajax({
			type : "post",
			url : "section.do?flag=selectSectionAjax",
			async : true,
			dataType : "json",
			success : function(obj) {
				$("#bumen").html("");
				if (obj != null) {
					$("#bumen").append(
							"<option value='0'></option>");
					for ( var i = 0; i < obj.length; i++) {
						$("#bumen").append(
								"<option value='"+obj[i].id+"'>" + obj[i].name
										+ "</option>");
					}
				} else {
					$("#bumen").append("<option value='无'>无</option>");
				}
				QueryBanZu();
			},
			error : function() {
				document.location.href = "/XGProject/cangchu/page/login.jsp";
			}
		});
	});
	//通过部门查询班组
	function QueryBanZu() {
		$(function() {
			//获得部门的值
			var id = $("#bumen").val();
			if (id == "无") {
				$("#banzu").html("");
				$("#banzu").append("<option value='无'>无</option>");
				QueryOnLine();
				return false;
			}

			$
					.ajax({
						type : "post",
						url : "classT.do?flag=selectclassTAjax",
						data : {
							"section" : id
						},
						async : true,
						dataType : "json",
						success : function(obj) {
							$("#banzu").html("");
							if (obj != null) {
								for ( var i = 0; i < obj.length; i++) {
									$("#banzu")
											.append(
													"<option value='"+obj[i].name+"'>"
															+ obj[i].name
															+ "</option>");
								}
								QueryOnLine();
							} else {
								$("#banzu").append(
										"<option value='无'>无</option>");
							}
						},
						error : function() {
							document.location.href = "/XGProject/cangchu/page/login.jsp";
						}
					});
		});
	}
	//查询在线的人员
	function QueryOnLine() {
		var bumenid = $("#bumen").find("option:selected").text();//部门的id
		var banzuid = $("#banzu").val();//班组的id

		if (bumenid == "无" || banzuid == "无") {
			$(".table tbody").html("");
			$(".table tbody").append("<tr><td clospan='8'>无</td></tr>");
			return false;
		}

		$
				.ajax({
					type : "post",
					url : "interiorUser.do?flag=getOnLine",
					data : {
						"bumen" : bumenid,
						"banzu" : banzuid
					},
					async : true,
					dataType : "json",
					success : function(obj) {
						$(".table tbody").html("");
						if (obj != null) {
							if (obj[0].result == null) {
								$(".table tbody")
										.append(
												"<tr><td colspan='8' style='text-align:center; font-size:18px; font-weight:bold;'>无</td></tr>");
								return false;
							}
							for ( var i = 0; i < obj.length; i++) {
								$(".table tbody").append(
										"<tr><td>" + obj[i].id + "</td><td>"
												+ obj[i].name + "</td><td>"
												+ obj[i].bumen + "</td>"
												+ "<td>" + obj[i].juese
												+ "</td><td>" + obj[i].zaixian
												+ "</td><td>" + obj[i].zuoye
												+ "</td>" + "<td>" + obj[i].tel
												+ "</td><td>" + obj[i].remark
												+ "</td></tr>");
							}
						} else {
							$(".table tbody").append(
									"<tr><td colspan='8'>无</td></tr>");
						}
					},
					error : function() {
						document.location.href = "/XGProject/cangchu/page/login.jsp";
					}
				});
	}
</script>
<body>
	<div class="right" id="mainFrame">
		<div class="daohang">
			<ul>
				<i>当前位置：</i>
				<a style="cursor:pointer;">首页</a>
				<span>></span>&nbsp;
				<a href="javascript:document.location.reload();">在线用户</a>
				<span>/</span>&nbsp;
			</ul>
		</div>
		<div class="option" style="margin-left:30px; margin-top:20px;">
			<div class="col-md-2 col-xs-6 form-group">
				<div class="input-group ">
					<span class="input-group-addon">部门</span> <select
						style="height: 30px; width: 200px;" id="bumen"
						onchange="QueryBanZu()">
						<option>仓储部</option>
						<option>财务部</option>
					</select>
				</div>
			</div>
			<div class="col-md-2 col-xs-6 form-group">
				<div class="input-group ">
					<span class="input-group-addon">班组</span> <select
						style="height: 30px; width: 200px;" id="banzu"
						onchange="QueryOnLine()">
						<option>一班组</option>
						<option>二班组</option>
					</select>
				</div>
			</div>
		</div>
		<table class="table table-striped table-hover">
			<thead>
				<tr>
					<th>编号</th>
					<th>姓名</th>
					<th>部门</th>
					<th>角色</th>
					<th>是否在线</th>
					<th>作业状态</th>
					<th>联系电话</th>
					<th>备注</th>
				</tr>
			</thead>
			<tbody style="font-size: 14px;">
				<tr>
					<td>1</td>
					<td>张三</td>
					<td>仓储部</td>
					<td>保管员</td>
					<td>是</td>
					<td>作业中</td>
					<td>123456789101</td>
					<td></td>
				</tr>
				<tr>
					<td>dsf</td>
					<td>adsf</td>
					<td>ads</td>
					<td>adf</td>
					<td>ads</td>
					<td>adsf</td>
					<td>asdf</td>
					<td>asdf</td>
				</tr>
				<tr>
					<td>dsf</td>
					<td>asdf</td>
					<td>adsf</td>
					<td>ads</td>
					<td>adf</td>
					<td>ads</td>
					<td>adsf</td>
					<td>asdf</td>
				</tr>
				<tr>
					<td>dsf</td>
					<td>adsf</td>
					<td>ads</td>
					<td>adf</td>
					<td>ads</td>
					<td>adsf</td>
					<td>asdf</td>
					<td>asdf</td>
				</tr>
			</tbody>
		</table>
	</div>
	<!-- 底部 -->
</body>

</html>
