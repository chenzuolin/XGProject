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
<meta charset="UTF-8">
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
<link rel="stylesheet" type="text/css" href="css/fenye.css" />
<link rel="stylesheet" href="css/selectize.default.css">
<link rel="stylesheet" href="css/public.css" />
<script src="http://libs.baidu.com/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery1.9.0.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/bootstrap-datetimepicker.js"></script>
<script src="js/bootstrap-datetimepicker.zh-CN.js"></script>
<!--   <script src="js/jquery.js"></script> -->
<script src="js/selectize.js"></script>
<style type="text/css">
.selectize-control.demo-default {
	height: 34px;
}

.panel-title a {
	font-size: 18px;
	font-weight: bold;
}

.panel-body  span {
	font-size: 16px;
	font-weight: bolder;
}

.page_show {
	-moz-user-select: none;
	-webkit-user-select: none;
	-ms-user-select: none;
	-khtml-user-select: none;
	user-select: none;
}
</style>
</head>
<script>
	function show(str) {
		$(function() {
			var now = $("#pageNow").val();
			now = parseInt(now);
			var kuwei = $("#kuwei").val();
			switch (str) {
			case 'jia':
				now = now + 1;
				break;
			case 'jian':
				now = now - 1;
				break;
			case 'tijiao':
				now = 1;
				break;
			case 'load':
				kuwei = "";
				break;
			}
			$
					.ajax({
						type : "post",
						url : "duanDaoAction.do?flag=ShiftgetAll&ff=ajax&pageNow="
								+ now,
						async : true,
						data : {
							"time" : new Date().getTime(),
							"begin" : $("#startTime").val(),
							"finish" : $("#endTime").val(),
							"kuName" : kuwei
						},
						dataType : "json",
						success : function(obj) {
							if (obj != null) {
								if (obj[0].result == null) {
									$("#neirong table tbody").html("");
									$("#neirong table tbody")
											.append(
													"<tr><td colspan='5'>无订单</td></tr>");
									return false;
								}
								$("#neirong table tbody").html("");
								for ( var i = 0; i < obj.length; i++) {
									$("#neirong table tbody")
											.append(
													"<tr onclick='tiaozhuanmingxi(this)' title='查看详情' style='cursor:pointer;'><input type='hidden' value='"
															+ obj[i].id
															+ "' /><td>"
															+ obj[i].id
															+ "</td><td>"
															+ obj[i].faqiren
															+ "</td><td>"
															+ obj[i].time
															+ "</td><td>"
															+ obj[i].kuwei
															+ "</td><td>"
															+ obj[i].xinkuwei
															+ "</td></tr>");
								}
								$("#pageNow").val(obj[0].pageNow);
								$("#yeshu").val(obj[0].pageNow);
							} else {
								$("#neirong table tbody").html("");
								$("#neirong table tbody").append(
										"<tr><td colspan='5'>无订单</td></tr>");
							}
						},
						error : function() {
							document.location.href = "/XGProject/cangchu/page/login.jsp";
						}
					});
		});
	}

	function xianshi(str) {

		$(function() {
			var cishu = $(str).children("input").val();
			if (cishu == 2) {
				$(str).parents(".panel-group").find(".mamamiya").css("height",
						"53px").slideDown(300);
				$(str).children("input").val("1");
			} else {
				$(str).parents(".panel-group").find(".mamamiya").slideUp(300);
				$(str).children("input").val("2");
			}
		});
	}

	function tiaozhuanmingxi(str) {
		$(function() {
			var id = $(str).children("input").val();
			document.location.href = "${pageContext.request.contextPath}/duanDaoAction.do?flag=getNuoKuMingXi&shiftId="
					+ encodeURI(encodeURI(id));
		});
	}

	$(function() {
		//当键盘中的回车键按下的时候触发
		$("#startTime").keydown(function(event) {
			if (event.keyCode == 13) {
				show('tijiao');
			}
		});
		$("#endTime").keydown(function(event) {
			if (event.keyCode == 13) {
				show('tijiao');
			}
		});
		$("#kuwei").keydown(function(event) {
			if (event.keyCode == 13) {
				show('tijiao');
			}
		});

		// 当点击上一页的时候，所有的保存当前页的文本框减一
		$("#shang").click(function() {
			$("#pageNow").val(parseInt($("#pageNow").val()) - 1);
			show('f');
		});

		// 当点击下一页的时候，所有的保存当前页的文本框加一
		$("#xia").click(function() {
			$("#pageNow").val(parseInt($("#pageNow").val()) + 1);
			show('f');
		});

		// 当点击首页的时候，返回到首页
		$("#shouye").click(function() {
			$("#pageNow").val("1");
			show('f');
		});

		// 跳转文本框中按下回车键的时候，直接跳转
		$("#yeshu").keydown(function(event) {
			if (event.keyCode == 13) {
				$("#go").click();
			}
		});
		// 当点击要跳转到某一页的时候
		$("#go").click(function() {
			var yan = /^[0-9]*$/;
			var page_now = $("#yeshu").val();
			if (yan.test(page_now) && page_now != "") {
				$("#pageNow").val(page_now);
				show('f');
			}
		});

		// 当点击到尾页的时候
		$("#weiye").click(function() {
			$("#pageNow").val("10000");
			show('f');
		});
	});
</script>

<body onload="show('load')">
	<c:if test="${tlist==null }">
		<script>
			document.location.href = "${pageContext.request.contextPath}/tarehouse.do?flag=getTarehouseAll&ff=duandao";
		</script>
	</c:if>

	<!--内容起始处-->
	<div id="mainFrame" class="main">
		<div class="daohang">
			<ul>
				<i>当前位置：</i>
				<a style="cursor:pointer">数据中心</a>
				<span>/</span>&nbsp;
				<a style="cursor:pointer">短倒查询</a>
				<span>/</span>&nbsp;
			</ul>
		</div>
		<div class="container" style="margin-top: 15px;">
			<div class='col-md-3 col-sm-6 form-group'>
				<div class="input-group">
					<input class="form-control" type="text" id="startTime"
						placeholder="开始日期" /> <span class="input-group-addon"><label
						class="glyphicon glyphicon-calendar" for="startTime"></label> </span>
				</div>
			</div>
			<div class="col-md-3 col-sm-6 form-group">
				<div class='input-group date'>
					<input class="form-control" type="text" id="endTime"
						placeholder="结束日期" /> <span class="input-group-addon"><label
						class="glyphicon glyphicon-calendar" for="endTime"></label> </span>
				</div>
			</div>
			<div class="col-lg-3 col-sm-6 form-group">
				<div class="input-group">
					<select id="kuwei" class="demo-default" onchange="show('tijiao')">
						<option value=""></option>
						<c:forEach items="${tlist }" var="t">
							<option value="${t.tarehouseName }">${t.tarehouseName }</option>
						</c:forEach>
					</select> <span class="input-group-addon">库位</span>
				</div>
			</div>
			<a type="button" class="btn btn-warning"
				style="float: right; margin-right: 15px;" onclick="show('tijiao')">提交</a>
		</div>
		<!-- 以下为订单内容页面 -->
		<div class="container" id="neirong">
			<table class="table table-bordered table-hover">
				<thead>
					<tr>
						<th>订单编号</th>
						<th>发起人</th>
						<th>发起时间</th>
						<th>原库位</th>
						<th>新库位</th>
					</tr>
				</thead>
				<tbody>
					<tr></tr>
				</tbody>
			</table>
		</div>
		<ul class="pager">
			<input type="hidden" id="pageNow" value="1" />
			<!-- <li><a onclick="show('jian')" style="cursor: pointer;">上一页</a></li>
			<li><a onclick="show('jia')" style="cursor: pointer;">下一页</a></li> -->
		</ul>
	</div>
	<div class="page_show">
		<div class="shang">
			<span id="shouye">首页</span> <span id="shang">上一页</span>
		</div>
		<div class="pageNow">
			<input type="text" size="2" autocomplete="off" id="yeshu" /><span
				id="go">Go</span>
		</div>
		<div class="xia">
			<span id="xia">下一页</span> <span id="weiye">尾页</span>
		</div>
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
<script>
	$(function() {
		$("#kuwei").selectize();
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
	if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("查看挪库订单")){
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
