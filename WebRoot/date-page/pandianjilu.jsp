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
<link rel="stylesheet" type="text/css" href="css/fenye.css" />
<!--添加 favicon icon -->
<link rel="stylesheet" href="css/bootstrap.css" />
<link rel="stylesheet" href="css/public.css" />
<link rel="stylesheet" href="css/selectize.default.css">
<link rel="stylesheet" href="css/bootstrap-datetimepicker.min.css">
<script src="http://libs.baidu.com/jquery/2.1.1/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/bootstrap-datetimepicker.js"></script>
<script src="js/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="js/selectize.js"></script>
<!-- 引用加载文件 -->
<script src="js/jquery.shCircleLoader-min.js"></script>
<script src="js/jquery.shCircleLoader.js"></script>
<link rel="stylesheet" type="text/css"
	href="css/jquery.shCircleLoader.css" />

<!-- 导出excel插件 -->
<script src="cangchu/d/tableExport.js"></script>
<script src="cangchu/d/jquery.base64.js"></script>
<script src="cangchu/d/jszip-utils.js"></script>
<script src="cangchu/d/xlsx.core.min.js"></script>
<script src="cangchu/jsPDF/jspdf.min.js"></script>
<script src="cangchu/jsPDF-AutoTable/jspdf.plugin.autotable.js"></script>
<style type="text/css">
.selectize-control.demo-default {
	height: 34px;
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

<body onload="load()">
	<div id="loader"
		style="position: absolute; left: 48%; top: 40%; color: #337AB7; width: 70px;"></div>
	<script>
		function jiazai() {
			$('#loader').css("display", "block");
			$('#loader').shCircleLoader();
		}
		function jiazaijieshu() {
			$('#loader').css("display", "none");
		}
	</script>
	<div class="right" id="mainFrame">
		<div class="daohang">
			<ul>
				<i>当前位置：</i>
				<a style="cursor: pointer;">仓储业务</a>
				<span>></span>&nbsp;
				<a href="javascript:window.history.go(-1);">盘点客户库存</a>
				<span>/</span>&nbsp;
				<a href="javascript:document.location.reload();">查看盘点记录</a>
				<span>/</span>&nbsp;
			</ul>
		</div>
		<!-- selectize.js 单选插件 -->
		<script>
			$('.huozhu').selectize();
		</script>
		<script type="text/javascript">
			function load() {
				QueryZhuanChu();
				showContent();
			}
			var eventHandler = function(name) {
				return function() {
					console.log(name);
					QueryZhuanChu();
				};
			};
			// 查询转出客户
			function QueryZhuanChu() {
				var $area = $("#huozhu").selectize({
					valueField : 'id',
					labelField : 'title',
					searchField : 'title',
					sortField : 'title',
					options : [],
					create : false,
					dropdownParent : 'body',
					onFocus : eventHandler('onFocus'),
				});
				var control = $area[0].selectize;
				$(function() {
					$
							.ajax({
								type : "post",
								url : "shiftStock.do?flag=QueryZhuanChu&ff=zhuanchu",
								async : true,
								dataType : "json",
								success : function(obj) {
									control.clearOptions();
									if (obj != null) {
										for ( var i = 0; i < obj.length; i++) {
											control.addOption({
												id : obj[i].jiancheng,
												title : obj[i].jiancheng,
											});
										}
									} else {

										control.addOption({
											id : "无",
											title : "无",
										});
									}

								},
								error : function() {
									document.location.href = "/XGProject/cangchu/page/login.jsp";
								}
							});
				});
			}
		</script>
		<div class="options"
			style=" max-width: 90%; margin: 0 auto;margin-top: 20px;">
			<div class="col-lg-3 col-xs-6">
				<div class="input-group form-group">
					<select id="huozhu" class="demo-default huozhu"
						onchange="showContent()">
					</select> <span class="input-group-addon">客户名称</span>
				</div>
			</div>
			<div class="col-lg-2 col-xs-6">
				<div class="form-group">
					<div class="input-group">
						<input class="form-control" type="text" id="startTime"
							placeholder="开始日期" /> <span class="input-group-addon"><label
							class="glyphicon glyphicon-calendar" for="startTime"></label> </span>
					</div>
				</div>
			</div>
			<div class="col-lg-2 col-xs-6">
				<div class="form-group">
					<div class='input-group date'>
						<input class="form-control" type="text" id="endTime"
							placeholder="结束日期" /> <span class="input-group-addon"><label
							class="glyphicon glyphicon-calendar" for="endTime"></lable>
						</span>
					</div>
				</div>
			</div>
			<div class="col-lg-2 col-xs-6">
				<div class="input-group form-group">
					<input type="text" class="form-control" placeholder="货物资料"
						id="huowu"> <span class="input-group-addon">货物资料</span>
				</div>
			</div>
			<a type="button" class="btn btn-warning"
				style="float: left; margin-left: 10px;" id="tijiao">提交</a>
		</div>
		<!-- 以下为数据内容 -->
		<table class="table table-bordered table-hover"
			style="min-width: 1000px;" id="table-name">
			<thead>
				<tr>
					<th>编号</th>
					<th>客户名称</th>
					<th>货物品类</th>
					<th>货物名称</th>
					<th>货物规格</th>
					<th>货物材质</th>
					<th>货物属性</th>
					<th>货物产地</th>
					<th>原有重量(吨)</th>
					<th>盈库(吨)</th>
					<th>亏库(吨)</th>
					<th>盘点时间</th>
					<th>盘点人</th>
					<th>备注</th>
				</tr>
			</thead>
			<tbody style="font-size: 14px;" id="contentbody">
			</tbody>
		</table>
		</form>
		<ul class="pager">
			<input type="hidden" id="pageNow" value="1" />
			<!-- <li><a style="cursor:pointer;" onclick="JJpageNow('jian')">上一页</a>
			</li>
			<li><a style="cursor:pointer;" onclick="JJpageNow('jia')">下一页</a>
			</li> -->
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

<!-- selectize.js 单选插件 -->
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
<script>
	$(function() {
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
		$("#huowu").keydown(function(event) {
			if (event.keyCode == 13) {
				$("#tijiao").click();
			}
		});
		$("#huozhu").keydown(function(event) {
			if (event.keyCode == 13) {
				$("#tijiao").click();
			}
		});
		$("#tijiao").click(function() {
			$("#pageNow").val("1");
			showContent();
		});

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
	});

	function showContent() {

		jiazai();
		$(function() {
			$
					.ajax({
						type : "post",
						url : "checksClientGoods.do?flag=getChecksClientGoodsAll&pageNow="
								+ $("#pageNow").val(),
						async : true,
						data : {
							"time" : new Date().getTime(),
							"begin" : $("#startTime").val(),
							"finish" : $("#endTime").val(),
							"goodsName" : $("#huowu").val(),
							"jiancheng" : $("#huozhu").val()
						},
						dataType : "json",
						success : function(obj) {
							jiazaijieshu();
							$("#contentbody").html("");
							if (obj == null) {
								$("#contentbody")
										.append(
												"<tr colspan='14' style='text-align:center;'>无</tr>");
								return false;
							}
							if (obj[0].result == null || obj[0].result=="null") {
								$("#contentbody")
										.append(
												"<tr colspan='14' style='text-align:center;'>无</tr>");
								return false;
							}
							for ( var i = 0; i < obj.length; i++) {
								$("#contentbody").append(
										"<tr style='height:40px;'><td>"+obj[i].id+"</td><td>"
												+ obj[i].client + "</td><td>"
												+ obj[i].pinlei + "</td><td>"
												+ obj[i].mingcheng + "</td>"
												+ "<td>" + obj[i].guige
												+ "</td><td>" + obj[i].caizhi
												+ "</td>" + "<td>"
												+ obj[i].shuxing + "</td><td>"
												+ obj[i].chandi + "</td>"
												+ "<td>" + obj[i].yuanweight
												+ "</td><td>" + obj[i].yingweight
												+ "</td>" + "<td>"
												+ obj[i].kuiweight
												+ "</td><td>" + obj[i].time
												+ "</td><td>"+obj[i].pandianren+"</td><td>"+obj[i].beizhu+"</td></tr>");
							}
							$("#pageNow").val(obj[0].pageNow);
							$("#yeshu").val(obj[0].pageNow);
						},
						error : function() {
							document.location.href = "/XGProject/cangchu/page/login.jsp";
						}
					});
		});

	}
</script>