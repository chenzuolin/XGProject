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
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/public.css" />
<link rel="stylesheet" href="css/bootstrap-datetimepicker.min.css">
<link rel="stylesheet" href="css/selectize.default.css">
<script src="http://libs.baidu.com/jquery/2.1.1/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/bootstrap-datetimepicker.js"></script>
<script src="js/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="js/selectize.js"></script>
<script type="text/javascript" src="js/feiyongjiesuan.js"></script>
<!-- 引用加载文件 -->
<script src="js/jquery.shCircleLoader.js"></script>
<script src="js/jquery.shCircleLoader-min.js"></script>
<link rel="stylesheet" type="text/css"
	href="css/jquery.shCircleLoader.css" />
<link rel="stylesheet" type="text/css" href="css/fenye.css" />
<style type="text/css">
.selectize-control.demo-default.single {
	height: 34px;
}

.input-group>.input-group-addon {
	background: #337AB7;
	color: #fff;
}

label {
	margin: 0px;
	font-weight: 600;
	color: #2D42D2;
}

.check {
	width: 18px;
	height: 18px;
	cursor: pointer;
	text-align: center;
	vertical-align: sub;
}
</style>
</head>
<!-- selectize.js 单选插件 -->
<script>
	$('#custom-name').selectize();
</script>
<body onload="load()">

	<div id="loader"
		style="position: fixed; left: 48%; top: 40%; color: #337AB7; width: 70px;"></div>
	<script>
		$('#loader').shCircleLoader();

		function jiazai() {
			$('#loader').css("display", "block");
			$('#loader').shCircleLoader();
		}

		function jiazaijieshu() {
			$('#loader').css("display", "none");
		}
	</script>
	<div class="container-fluid">
		<div class="">
			<ol class="breadcrumb">
				<span>当前位置：</span>
				<li><a style="cursor: pointer;">财务管理</a>
				</li>
				<li><a href="javascript:document.location.reload();">费用结算</a>
				</li>
			</ol>
		</div>
		<form class="container" style="width: auto; height: auto;">
			<div class="col-lg-3 col-xs-6 form-group">
				<div class="input-group">
					<select id="custom-name" class="demo-default" onchange="change()">
					</select> <span class="input-group-addon">客户名称</span>
				</div>
			</div>
			<div class='col-md-3 col-xs-6 form-group'>
				<div class="input-group">
					<input class="form-control" type="text" id="startTime"
						placeholder="开始日期" /> <label
						class="glyphicon glyphicon-calendar input-group-addon"
						for="startTime"></label>
				</div>
			</div>
			<div class='col-md-3 col-xs-6 form-group'>
				<div class='input-group date'>
					<input class="form-control" type="text" id="endTime"
						placeholder="结束日期" /> <label
						class="glyphicon glyphicon-calendar input-group-addon"
						for="endTime"></label>
				</div>
			</div>
			<a type="button" class="btn btn-warning"
				style="margin-right: 15px; float: left;" id="tijiao">提交</a> <a
				type="button" class="btn btn-primary" style=" float: left;"
				data-toggle="modal" href="#piliangjiesuan">批量结算</a>

		</form>

		<table id="hidden_table" style="display:none">

		</table>
		<script type="text/javascript">
			$(function() {
				//当点击“筛选当页无费用客户"的时候触发
				$("#shaixuan").click(
						function() {
							var shaixuan = $(this).prop("checked");//获得点击的容器
							//如果选中的时候
							if (shaixuan) {
								var len = $("#neirong tr").length;//显示内容表格中的行数

								for ( var i = 0; i < len; i++) {
									var text = $("#neirong tr").eq(i).children(
											"td").eq(12).text();
									if (parseFloat(text) == 0) {
										$("#hidden_table").append($("#neirong tr").eq(i).clone(true));
									}
								}
								for ( var i = 0; i < len; i++) {
									var text = $("#neirong tr").eq(i).children(
											"td").eq(12).text();
									if (parseFloat(text) == 0) {
										$("#neirong tr").eq(i).remove();
										i--;
										len--;
									}

								}
							} else {
								var $htr = $("#hidden_table tr");
								var len = $("#hidden_table tr").length;
								for ( var i = 0; i < len; i++) {
									$("#neirong").append($htr.eq(i));
								}
								$("#hidden_table").html("");
							}
						});
			});
		</script>
		<form
			action="${pageContext.request.contextPath }/accounts.do?flag=shouquAccounts"
			method="post" name="accountsForm" id="accform">
			<table class="table table-hover">
				<!--  <caption>订单结算明细</caption> -->
				<thead>
					<tr>
						<td colspan="14" align="left"><input type="checkbox"
							id="shaixuan" /><label for="shaixuan">筛选当页无费用客户</label></td>
					</tr>
					<tr>
						<th><input type="checkbox" class="check" id="quanxuan" /><label
							for="quanxuan">全选</label></th>
						<th>客户名称</th>
						<th>起始日期</th>
						<th>结束日期</th>
						<th>汽运入库费</th>
						<th>火运入库费</th>
						<th>汽运出库费</th>
						<th>火运出库费</th>
						<th>下站费（出）</th>
						<th>过户费</th>
						<th>下站费（过）</th>
						<th>仓储费</th>
						<th>费用合计</th>
						<th style="text-align: center;">操作</th>
					</tr>
				</thead>
				<tbody id="neirong">

				</tbody>
			</table>

			<!-- 批量结算模态框（Modal） -->
			<div class="modal fade" id="piliangjiesuan" tabindex="-1"
				role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog" style="width: 80%;">
					<div class="modal-content">
						<div class="modal-header">
							<h4 class="modal-title" id="myModalLabel">费用结算</h4>
						</div>
						<div class="modal-body">
							<form action="">
								<div class="row">
									<div class="col-lg-8 col-md-offset-2 form-group">
										<div class="input-group">
											<span class="input-group-addon">缴费期限</span> <input
												class="form-control" type="date" name="shoufeiqixian"
												id="qixian">
										</div>
									</div>
									<div class="col-lg-8 col-md-offset-2 form-group">
										<div class="input-group">
											<span class="input-group-addon">滞纳金费率</span> <input
												class="form-control" type="text" name="zhinaFeilv"
												id="feilv">
										</div>
									</div>
									<div class="col-lg-8 col-md-offset-2 form-group">
										<div class="input-group">
											<span class="input-group-addon">备注</span> <input
												class="form-control" type="text" name='accountsRemark'>
										</div>
									</div>
								</div>
							</form>
							<div class="modal-footer col-md-offset-3">
								<a type="button" class="btn btn-default" data-dismiss="modal">关闭</a>
								<a type="button" class="btn btn-primary"
									onclick="piliangtijiao('piliang')">提交更改</a>
							</div>
						</div>
						<!-- /.modal-content -->
					</div>
					<!-- /.modal -->
				</div>
			</div>
		</form>
		<script>
			function xuanzhong(str) {
				var check = $(str).find("input[type=checkbox]").prop("checked");
				if (check) {
					$(str).find("input[type=checkbox]").prop("checked", false);
				} else {
					$(str).find("input[type=checkbox]").prop("checked", true);
				}
			}
			$("#quanxuan").click(
					function() {
						$("#neirong").find("input[type=checkbox]").prop(
								"checked", $(this).prop("checked"))
					});
			function jiesuan(str) {
				$("#jiesuan #kehu").val(
						$(str).parents("tr").children("td").eq(0).children(
								"input[name=client]").val());
				$("#jiesuan #begin").val(
						$(str).parents("tr").children("td").eq(2).text());
				$("#jiesuan #finish").val(
						$(str).parents("tr").children("td").eq(3).text());
				$("#jiesuan #qirucost").val(
						$(str).parents("tr").children("td").eq(4).text());
				$("#jiesuan #huorucost").val(
						$(str).parents("tr").children("td").eq(5).text());
				$("#jiesuan #qichucost").val(
						$(str).parents("tr").children("td").eq(6).text());
				$("#jiesuan #huochucost").val(
						$(str).parents("tr").children("td").eq(7).text());
				$("#jiesuan #chuxzcost").val(
						$(str).parents("tr").children("td").eq(8).text());
				$("#jiesuan #guohucost").val(
						$(str).parents("tr").children("td").eq(9).text());
				$("#jiesuan #guoxzcost").val(
						$(str).parents("tr").children("td").eq(10).text());
				$("#jiesuan #cangchucost").val(
						$(str).parents("tr").children("td").eq(11).text());
				$("#jiesuan #hejicost").val(
						$(str).parents("tr").children("td").eq(12).text());
				$("#jiesuan #qimoweight").val(
						$(str).parents("tr").children("td").eq(12).children(
								"input[name=qimoweight]").val());
			}
		</script>
		<!-- 结算模态框（Modal） -->
		<div class="modal fade" id="jiesuan" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 80%;">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" id="myModalLabel">费用结算</h4>
					</div>
					<div class="modal-body">
						<form
							action="${pageContext.request.contextPath }/accounts.do?flag=shouquAccounts"
							method="post" name="accountsForm" id="dangeform">
							<input type="hidden" id="kehu" name='client' /> 
							<input type="hidden" id="begin" name='accountsCreateTime' /> <input
								type="hidden" id="finish" name='accountsFinishTime' /> <input
								type="hidden" value="" id="qirucost" name='rukuCost' /> <input
								type="hidden" value="" id="huorucost" name='zidingyiFour' /> <input
								type="hidden" value="" id="qichucost" name='zidingyiFive' /> <input
								type="hidden" value="" id="huochucost" name='zidingyiSix' /> <input
								type="hidden" value="" id="chuxzcost" name='chukumaxtime' /> <input
								type="hidden" value="" id="guoxzcost" name='zhuanchukumaxtime' />
							<input type="hidden" value="" id="guohucost" name='guohuCost' />
							<input type="hidden" value="" id="cangchucost" name='cangchuCost' />
							<input type="hidden" value="" id="hejicost"
								name='accountsExpensesSeed' /> <input type="hidden" value=""
								id="qimoweight" name='qimoWeight' />

							<div class="row">
								<div class="col-lg-8 col-md-offset-2 form-group">
									<div class="input-group">
										<span class="input-group-addon">缴费期限</span> <input
											class="form-control" type="date" name="shoufeiqixian"
											id="qixian">
									</div>
								</div>
								<div class="col-lg-8 col-md-offset-2 form-group">
									<div class="input-group">
										<span class="input-group-addon">滞纳金费率</span> <input
											class="form-control" type="text" name="zhinaFeilv" id="feilv">
									</div>
								</div>
								<div class="col-lg-8 col-md-offset-2 form-group">
									<div class="input-group">
										<span class="input-group-addon">备注</span> <input
											class="form-control" type="text" name='accountsRemark'>
									</div>
								</div>
							</div>
						</form>
						<div class="modal-footer col-md-offset-3">
							<a type="button" class="btn btn-default" data-dismiss="modal">关闭</a>
							<a type="button" class="btn btn-primary"
								onclick="piliangtijiao('dange')">提交更改</a>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal -->
			</div>
		</div>

		<div class="page_show" style="width: 500px; margin-top: 20px;">
			<input type="hidden" value="1" id="pageNow" />
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
			<div>
				<i style="font-style: normal;">显示行数：</i> <select
					style="height: 23px;" id="showrow" onchange="change()">
					<option value="20">20</option>
					<option value="40">40</option>
					<option value="60">60</option>
					<option value="100">100</option>
				</select>
			</div>
		</div>
</body>

<!-- selectize.js 时间插件 -->
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
	if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("费用结算")){
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