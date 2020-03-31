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
<script src="js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/base.css"/>
<!--主要样式-->
<link rel="stylesheet" type="text/css" href="css/pageGroup.css"/>
<script type="text/javascript" src="js/jquery1.9.0.min.js"></script>
<script type="text/javascript" src="js/pageGroup.js"></script>
<script src="js/bootstrap-datetimepicker.js"></script>
<script src="js/bootstrap-datetimepicker.zh-CN.js"></script>
<style type="text/css">
table tr td,table tr th {
	text-align: center;
	height: 30px;
}

table {
	table-layout: fixed;
}

td {
	overflow: hidden;
	white-space: nowrap;
	text-overflow: ellipsis;
}

#xuanfu {
	position: absolute;
	left: 0;
	top: 0;
	background-color: #ffffff;
	border-top: 1px solid #888888;
	border-left: 1px solid #888888;
	-moz-box-shadow: 5px 5px 5px #888888;
	-webkit-box-shadow: 5px 5px 5px #888888;
	box-shadow: 5px 5px 5px #888888;
	-moz-box-shadow: 5px 5px 5px #888888;
}
</style>
</head>

<body>
	<%-- <c:if test="${listLLog==null }">
		<c:redirect url="/loginLog.do?flag=getAllLLog"></c:redirect>
	</c:if> --%>
	<c:if test="${listLLog==null }">
		<script>
			document.location.href = "${pageContext.request.contextPath}/loginLog.do?flag=getAllLLog";
		</script>
	</c:if>
	<input type="hidden" id="pageCount" value="${pageCount }"/>
	<div class="right" id="mainFrame">
		<div class="daohang">
			<ul>
				<i>当前位置：</i>
				<a style="cursor:pointer;">系统管理</a>
				<span>></span>&nbsp;
				<a style="cursor:pointer;">系统日志</a>
				<span>/</span>&nbsp;
			</ul>
		</div>
		<script>
			$(function() {
				$("#tijiao")
						.click(
								function() {
									$
											.ajax({
												type : "post",
												url : "loginLog.do?flag=getAllLLog&ff=ajax",
												async : true,
												data : {
													"begin" : $(
															"input[name=begin]")
															.val(),
													"finish" : $(
															"input[name=finish]")
															.val(),
													"llogName" : $(
															"input[name=llogName]")
															.val(),
													"llogRemark" : $(
															"input[name=llogRemark]")
															.val(),
													"time" : new Date()
															.getTime()
												},
												dataType : "json",
												success : function(obj) {
													if (obj != null) {
														var tab = $(".table tbody");
														tab.html("");
														for ( var i = 0; i < obj.length; i++) {
															tab
																	.append("<tr style='height:30px;'><td>"
																			+ obj[i].id
																			+ "</td><td>"
																			+ obj[i].time
																			+ "</td><td>"
																			+ obj[i].loginName
																			+ "</td><td>"
																			+ obj[i].ip
																			+ "</td><td>"
																			+ obj[i].type
																			+ "</td><td onmouseover='remarkshow(this)' style='cursor:pointer;'>"
																			+ obj[i].remark
																			+ "<input type='hidden' value='" + obj[i].remark + "'/></td></tr>");
														}
														$("#pageCount").val(obj[0].pageCount);
													} else {
														alert("上传失败！");
													}
												},
												error : function() {
													document.location.href = "/XGProject/cangchu/page/login.jsp";
												}
											});
								});
			});

			//当鼠标移动到备注上边的时候
			function remarkshow(str) {
				$(function() {
					str.onmousemove = function(event) {
						var x = event.pageX;
						var y = event.pageY;
						$("#xuanfu").css({
							"left" : x,
							"top" : y
						});
						$("#xuanfu").html("");
						var remark = $(str).children("input").val();
						$("#xuanfu").text(remark);
					};
				});
			}

			$(function() {
				//当键盘中的回车键按下的时候触发
				$("#startTime").keydown(function(event) {
					if (event.keyCode == 13) {
						$("#tijiao").click();
					}
				});
				$("#endTime").keydown(function(event) {
					if (event.keyCode == 13) {
						$("#tijiao").click();
					}
				});
				$("#llogName").keydown(function(event) {
					if (event.keyCode == 13) {
						$("#tijiao").click();
					}
				});
				$("#llogRemark").keydown(function(event) {
					if (event.keyCode == 13) {
						$("#tijiao").click();
					}
				});
			});
		</script>
		<div style="max-width:500px; padding:5px 0px;" id="xuanfu"></div>
		<div class="options">
			<div class='col-md-2'>
				<div class="form-group">
					<div class="input-group">
						<input class="form-control" type="text" id="startTime"
							placeholder="开始日期" name="begin" /> <span
							class="input-group-addon" style="background-color:#337AB7"><label
							class="glyphicon glyphicon-calendar" for="startTime"></label> </span>
					</div>
				</div>
			</div>
			<div class="col-md-2">
				<div class="form-group">
					<div class='input-group date'>
						<input class="form-control" type="text" id="endTime"
							placeholder="结束日期" name="finish" /> <span
							class="input-group-addon" style="background-color:#337AB7 "><label
							class="glyphicon glyphicon-calendar" for="endTime"></label> </span>
					</div>
				</div>
			</div>
			<div class="col-md-2">
				<div class="input-group form-group">
					<input type="text" class="form-control" placeholder="登录名"
						name="llogName" id="llogName" /> <span class="input-group-addon"
						style="background-color:#337AB7 ">登录名</span>
				</div>
			</div>
			<div class="col-md-2">
				<div class="input-group form-group">
					<input type="text" class="form-control" placeholder="操作内容"
						name="llogRemark" id="llogRemark"> <span
						class="input-group-addon" style="background-color:#337AB7 ">操作内容</span>
				</div>
			</div>
			<a class="btn btn-warning" id="tijiao">提交</a>
		</div>
		<table class="table table-striped table-hover">
			<thead>
				<tr>
					<th>编号</th>
					<th>时间</th>
					<th>登录名</th>
					<th>IP地址</th>
					<th>操作类型</th>
					<th width="700px">操作内容</th>
				</tr>
			</thead>
			<tbody style="font-size: 14px;">
				<c:forEach items="${listLLog }" var="ll">
					<tr style="height:30px;">
						<td>${ll.llogId }</td>
						<td>${ll.llogTime }</td>
						<td>${ll.llogName }</td>
						<td>${ll.llogIp }</td>
						<td>${ll.llogDefinedOne}</td>
						<td onmouseover='remarkshow(this)' style='cursor:pointer;'>${ll.llogRemark
							} <input type='hidden' value="${ll.llogRemark
							}" />
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<ul class="pager">
			<script>
				function show(str) {
					var now = $("#pageNow").val();
					now = parseInt(now);
					$
							.ajax({
								type : "post",
								url : "loginLog.do?flag=getAllLLog&ff=ajax&pageNow="
										+ now,
								async : true,
								data : {
									"time" : new Date().getTime()
								},
								dataType : "json",
								success : function(obj) {
									if (obj != null) {
										var tab = $(".table tbody");
										tab.html("");
										for ( var i = 0; i < obj.length; i++) {
											tab
													.append("<tr style='height:30px;'><td>"
															+ obj[i].id
															+ "</td><td>"
															+ obj[i].time
															+ "</td><td>"
															+ obj[i].loginName
															+ "</td><td>"
															+ obj[i].ip
															+ "</td><td>"
															+ obj[i].type
															+ "</td><td onmouseover='remarkshow(this)' style='cursor:pointer;'>"
															+ obj[i].remark
															+ "<input type='hidden' value='" + obj[i].remark + "'/></td></tr>");
										}
										$("#pageNow").val(obj[0].pageNow);
									} else {
										alert("上传失败！");
									}
								},
								error : function() {
									document.location.href = "/XGProject/cangchu/page/login.jsp";
								}
							});
				}
			</script>
			<input type="hidden" id="pageNow" value="1" />
		</ul>
	</div>
	
	<div id="pageGro" class="cb" style="margin-top:-40px;">
	<div class="pageUp">上一页</div>
    <div class="pageList">
        <ul>
            <li>1</li>
            <li>2</li>
            <li>3</li>
            <li>4</li>
            <li>5</li>
        </ul>
    </div>
    <div class="pageDown">下一页</div>
</div>
</body>
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
	int x = 0;
		
		List<Powers> power = (List<Powers>)request.getSession().getAttribute("power");
		for(int i=0; i<power.size(); i++){
	if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("查看系统日志")){
		x++;
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