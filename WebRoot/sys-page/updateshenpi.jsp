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
<script src="js/all.js"></script>
<script src="js/addinteriorUser.js"></script>
<link rel="stylesheet" type="text/css" href="css/fenye.css" />
<link rel="stylesheet" href="css/bootstrap-datetimepicker.min.css">
<script src="js/bootstrap-datetimepicker.js"></script>
<script src="js/bootstrap-datetimepicker.zh-CN.js"></script>

<script type="text/javascript">
	$(function() {
		$(".closeOk").click(function() {
			document.location.reload();
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

table {
	table-layout: fixed;
}

td {
	width: 500px;
	overflow: hidden;
	white-space: nowrap;
	text-overflow: ellipsis;
	overflow: hidden;
}
</style>
</head>

<body onload="load()">

	<!--内容起始处-->
	<div id="mainFrame" class="main">
		<div class="daohang">
			<ul>
				<i>当前位置：</i>
				<a style="cursor:pointer;">数据中心</a>
				<span>/</span>&nbsp;
				<a href="javascript:document.location.reload();">修改审批</a>
				<span>/</span>&nbsp;
			</ul>
		</div>
		<c:if test="${urlist==null }">
			<script>
				document.location.href = "${pageContext.request.contextPath}/updateRecordAction.do?flag=getJinXingShenPi";
			</script>
		</c:if>
		<div class="container-fluid">
			<ul class="nav nav-tabs nav-pills"
				style="margin: 10px 2.5%; font-weight: bold;">
				<li class="active"><a data-toggle="tab" href="#home"> <span
						class="glyphicon glyphicon-th-list" aria-hidden="true"
						style="margin-right: 8px;"></span>修改审批</a>
				</li>
				<li><a data-toggle="tab" href="#menu2" id="addOperator"> <span
						class="glyphicon glyphicon-th-list" aria-hidden="true"
						style="margin-right: 8px;"></span>审批记录</a>
				</li>
			</ul>
			<div class="tab-content">
				<div id="home" class="tab-pane fade in active">
					<table class="table table-striped table-hover">
						<thead>
							<tr>
								<th>编号</th>
								<th>操作编号</th>
								<th>修改发起人</th>
								<th>发起时间</th>
								<th>描述</th>
								<th>订单类型</th>
								<th>备注</th>
								<th style="width: 100px;">操作</th>
							</tr>
						</thead>
						<tbody id="shenpibody">
							<c:forEach items="${urlist }" var="ur">
								<tr>
									<td>${ur.urzongid }</td>
									<td>${ur.urcaozuoid }</td>
									<td>${ur.urfaqiren }</td>
									<td>${ur.urfaqitime }</td>
									<td>${ur.urfaqimiaoshu }</td>
									<td>${ur.urupdatetype }</td>
									<td>${ur.urupdateremark }</td>
									<td style="text-align:center;"><a
										href="${pageContext.request.contextPath }/updateRecordAction.do?flag=ShenPiTongGuo&urid=${ur.urid}"
										type="button" class="btn btn-warning btn-xs">同意</a> <a
										type="button" class="btn btn-danger btn-xs"
										href="${pageContext.request.contextPath }/updateRecordAction.do?flag=ShenPiNotTongGuo&urid=${ur.urid}">不同意</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div id="menu2" class="tab-pane fade">
					<div class="container-fluid" style="margin-top: 15px;">
						<div class='col-md-2 col-sm-6'>
							<div class="form-group">
								<div class="input-group">
									<input class="form-control" type="text" id="startTime"
										placeholder="开始日期" /> <span class="input-group-addon"><label
										class="glyphicon glyphicon-calendar" for="startTime"></label>
									</span>
								</div>
							</div>
						</div>
						<div class="col-md-2 col-sm-6">
							<div class="form-group">
								<div class='input-group date'>
									<input class="form-control" type="text" id="endTime"
										placeholder="结束日期" /> <span class="input-group-addon"><label
										class="glyphicon glyphicon-calendar" for="endTime"></label> </span>
								</div>
							</div>
						</div>
						<div class="col-md-2 col-sm-6">
							<div class="input-group form-group">
								<input type="text" class="form-control" id="caozuoren"
									placeholder="发起人或审批人"> <span class="input-group-addon">操作人</span>
							</div>
						</div>
						<div class="col-md-2 col-sm-6">
							<div class="input-group form-group">
								<input type="text" class="form-control" id="bianhao"
									placeholder="编号"> <span class="input-group-addon">编号</span>
							</div>
						</div>
						<a type="button" class="btn btn-warning" id="tijiao"
							style="margin-left: 5%;">提交</a>
					</div>
					<div id="home" class="tab-pane fade in active">
						<table class="table table-striped table-hover">
							<thead>
								<tr>
									<th width="120px">编号</th>
									<th width="150px">操作编号</th>
									<th>修改发起人</th>
									<th>发起时间</th>
									<th>描述</th>
									<th style="width:500px;">修改内容</th>
									<th>审批人</th>
									<th>审批时间</th>
									<th>订单类型</th>
									<th>状态</th>
									<th>备注</th>
								</tr>
							</thead>
							<tbody id="jilubody">
							</tbody>
						</table>
						<input type="hidden" id="pageNow" value="1" />
						<div class="page_show" style="margin-top:20px;">
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
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script>
	function load() {
		showContent();
	}
	$(function() {

		// 当点击上一页的时候，所有的保存当前页的文本框减一
		$("#shang").click(function() {
			$("#pageNow").val(parseInt($("#pageNow").val()) - 1);
			showContent();
		});

		// 当点击下一页的时候，所有的保存当前页的文本框加一
		$("#xia").click(function() {
			$("#pageNow").val(parseInt($("#pageNow").val()) + 1);
			showContent();
		});

		// 当点击首页的时候，返回到首页
		$("#shouye").click(function() {
			$("#pageNow").val("1");
			showContent();
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
				showContent();
			}
		});

		// 当点击到尾页的时候
		$("#weiye").click(function() {
			$("#pageNow").val("10000");
			showContent();
		});

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
		$("#caozuoren").keydown(function(event) {
			if (event.keyCode == 13) {
				$("#tijiao").click();
			}
		});
		$("#bianhao").keydown(function(event) {
			if (event.keyCode == 13) {
				$("#tijiao").click();
			}
		});

		$("#tijiao").click(function() {
			$("#pageNow").val("1");
			showContent();
		});
	});
	function showContent() {

		$(function() {
			$.ajax({
				type : "post",
				url : "updateRecordAction.do?flag=getUpdateAll",
				async : true,
				data : {
					"time" : new Date().getTime(),
					"begin" : $("#startTime").val(),
					"finish" : $("#endTime").val(),
					"urfaqiren" : $("#caozuoren").val(),
					"pageNow" : $("#pageNow").val(),
					"urzongid" : $("#bianhao").val()
				},
				dataType : "json",
				success : function(obj) {
					$("#jilubody").html("");
					if (obj == null || obj.length <= 0) {
						$("#jilubody").append(
								"<tr><td colspan='11'>无审批记录</td></tr>");
						return false;
					}
					if (obj[0].result == null) {
						$("#jilubody").append(
								"<tr><td colspan='11'>无审批记录</td></tr>");
						return false;
					}
					for ( var i = 0; i < obj.length; i++) {
						$("#jilubody").append(
								"<tr><td data-toggle='popover' data-trigger='hover'>" + obj[i].bianhao + "</td><td data-toggle='popover' data-trigger='hover'>"
										+ obj[i].caozuobh + "</td><td data-toggle='popover' data-trigger='hover'>"
										+ obj[i].faqiren + "</td><td data-toggle='popover' data-trigger='hover'>"
										+ obj[i].faqitime + "</td><td data-toggle='popover' data-trigger='hover'>"
										+ obj[i].miaoshu
										+ "</td><td style='width:500px;' data-toggle='popover' data-trigger='hover'>"
										+ obj[i].updateneirong + "</td><td data-toggle='popover' data-trigger='hover'>"
										+ obj[i].shenpiren + "</td><td data-toggle='popover' data-trigger='hover'>"
										+ obj[i].shenpitime + "</td><td data-toggle='popover' data-trigger='hover'>"
										+ obj[i].type + "</td><td data-toggle='popover' data-placement='left' data-trigger='hover'>"
										+ obj[i].zhuangtai + "</td><td data-toggle='popover' data-placement='left' data-trigger='hover'>"
										+ obj[i].beizhu + "</td></tr>");
					}
					$("#pageNow").val(obj[0].pageNow);
					$("#yeshu").val(obj[0].pageNow);

					$("#menu2 table tbody tr td").mouseover(function() {
						$("[data-toggle='popover']").popover({
							html : true,
							title : "订单修改审批记录",
							delay : {
								show : 0,
								hide : 0
							},
							container:"body",
							content : function() {
								return $(this).text();
							}
						});
					});
				},
				error : function() {
					alert("数据错误！");
				}
			});
		});
	}
</script>

</html>
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