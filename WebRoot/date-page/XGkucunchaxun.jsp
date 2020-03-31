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
<style>
.page_show {
	-moz-user-select: none;
	-webkit-user-select: none;
	-ms-user-select: none;
	-khtml-user-select: none;
	user-select: none;
}
</style>
</head>

<body onload="showContent()">
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
				<a style="cursor:pointer;">数据中心</a>
				<span>></span>&nbsp;
				<a href="javascript:document.location.reload();">库存查询</a>
				<span>/</span>&nbsp;
			</ul>
		</div>
		<div class="options"
			style=" max-width: 90%; margin: 0 auto;margin-top: 20px;">
			<div class="col-md-2 col-xs-6 form-group">
				<div class="input-group">
					<input class="form-control" type="text" id="startTime"
						placeholder="开始日期" /> <span class="input-group-addon"><label
						class="glyphicon glyphicon-calendar" for="startTime"></label> </span>
				</div>
			</div>
			<div class="col-md-2 col-xs-6 form-group">
				<div class='input-group date'>
					<input class="form-control" type="text" id="endTime"
						placeholder="结束日期" /> <span class="input-group-addon"><label
						class="glyphicon glyphicon-calendar" for="endTime"></lable>
					</span>
				</div>
			</div>
			<div class="col-md-2 col-xs-6 form-group">
				<div class="input-group">
					<input type="text" placeholder="货物" class="form-control" id="huowu" />
					<span class="input-group-addon">货物</span>
				</div>
			</div>
			<div class="col-md-2 col-xs-6 form-group">
				<div class="input-group">
					<input type="text" placeholder="货物品类" class="form-control"
						id="pinlei" /> <span class="input-group-addon">品类</span>
				</div>
			</div>
			<a type="button" class="btn btn-warning"
				style="float: left; margin-left: 10px;" id="tijiao">提交</a>
		</div>
		<!-- 数据内容页面 -->
		<table class="table table-bordered table-hover"
			style="min-width: 800px;">
			<thead>
				<tr>
					<th>序号</th>
					<th>货物品类</th>
					<th>货物名称</th>
					<th>货物规格</th>
					<th>货物材质</th>
					<th>货物属性</th>
					<th>货物产地</th>
					<th>期初库存(吨)</th>
					<th>入库合计(吨)</th>
					<th>出库合计(吨)</th>
					<th>期末库存(吨)</th>
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
		<div class="navbar-fixed-bottom"
			style="min-width: 600px; max-width: 60%; position: fixed; bottom: 5px; left: 40%; font-weight: bold; z-index:-1">
			<table class="table table-bordered">
				<tr>
					<td align="right">期初合计：</td>
					<td><span id="qichu">100</span>吨</td>
					<td align="right">入库合计：</td>
					<td><span id="ruku_heji">100</span>吨</td>
					<td align="right">出库合计：</td>
					<td><span id="chuku_heji">100</span>吨</td>
					<td align="right">期末合计</td>
					<td><span id="qimo">100</span>吨</td>
				</tr>
			</table>
		</div>
	</div>
</body>
<!-- selectize.js 单选插件 -->
<script>
	$('#custom-name').selectize();
</script>
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
		$("#pinlei").keydown(function(event) {
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
						url : "tidingsAction.do?flag=TongJiXGLSKC&pageNow="
								+ $("#pageNow").val(),
						async : true,
						data : {
							"time" : new Date().getTime(),
							"begin" : $("#startTime").val(),
							"finish" : $("#endTime").val(),
							"goodsName" : $("#huowu").val(),
							"pinlei" : $("#pinlei").val()
						},
						dataType : "json",
						success : function(obj) {
							jiazaijieshu();
							$("#contentbody").html("");
							if (obj == null) {
								$("#contentbody")
										.append(
												"<tr colspan='10' style='text-align:center;'>无</tr>");
								$("#qichu").text("0");
								$("#ruku_heji").text("0");
								$("#chuku_heji").text("0");
								$("#qimo").text("0");
								return false;
							}
							var xuhao = 0;//对应的序号
							for ( var i = 0; i < obj.length; i++) {
								xuhao++;
								$("#contentbody").append(
										"<tr style='height:40px;'><td>"+xuhao+"</td><td>"
												+ obj[i].pinlei + "</td><td>"
												+ obj[i].mingcheng + "</td>"
												+ "<td>" + obj[i].guige
												+ "</td><td>" + obj[i].caizhi
												+ "</td>" + "<td>"
												+ obj[i].shuxing + "</td><td>"
												+ obj[i].chandi + "</td>"
												+ "<td>" + obj[i].qichu
												+ "</td><td>" + obj[i].rukuheji
												+ "</td>" + "<td>"
												+ obj[i].chukuheji
												+ "</td><td>" + obj[i].qimo
												+ "</td></tr>");
								$("#qichu").text(obj[i].QCZH);
								$("#ruku_heji").text(obj[i].RKZH);
								$("#chuku_heji").text(obj[i].CKZH);
								$("#qimo").text("" + obj[i].QMZH + "");
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
