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
<link rel="stylesheet" href="css/selectize.default.css">
<link rel="stylesheet" href="css/bootstrap-datetimepicker.min.css">
<link rel="stylesheet" type="text/css" href="css/fenye.css" />
<link rel="stylesheet" href="css/public.css" />
<script src="http://libs.baidu.com/jquery/2.1.1/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/bootstrap-datetimepicker.js"></script>
<script src="js/bootstrap-datetimepicker.zh-CN.js"></script>
<!--   <script src="js/jquery.js"></script> -->
<script src="js/selectize.js"></script>
<!--  <script src="js/index.js"></script> -->
<style type="text/css">
.selectize-control.demo-default {
	height: 34px;
}

caption {
	font-size: 20px;
	font-weight: 600;
	text-align: center;
	color: #000;
}

#count_div {
	position: fixed;
	bottom: 5px;
	right: 10px;
}

#count_div table {
	width: 900px;
}

#count_div table tr td {
	border: 1px solid #888888;
	height: 35px;
	line-height: 35px;
	text-align: center;
}
</style>
</head>
<script>
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
		$("#kuwei").keydown(function(event) {
			if (event.keyCode == 13) {
				$("#tijiao").click();
			}
		});
		$("#huowu").keydown(function(event) {
			if (event.keyCode == 13) {
				$("#tijiao").click();
			}
		});
		$("#tijiao").click(function() {
			showContent();
		});
	});
	function showContent() {
		$(function() {
			$
					.ajax({
						type : "post",
						url : "tarehouseDetail.do?flag=getTDetailAll",
						async : true,
						data : {
							"time" : new Date().getTime(),
							"begin" : $("#startTime").val(),
							"finish" : $("#endTime").val(),
							"kuname" : $("#kuwei").val(),
							"pageNow" : $("#pageNow").val(),
							"goodsName" : $("#huowu").val()
						},
						dataType : "json",
						success : function(obj) {
							$("#contents tbody").html("");
							if (obj == null) {
								$("#contents tbody")
										.append(
												"<tr style='height: 40px;'><td colspan='13' style='text-align:center;'>无批次</td></tr>");
								return false;
							}
							if (obj[0].result == null) {
								$("#contents tbody")
										.append(
												"<tr style='height: 40px;'><td colspan='13' style='text-align:center;'>无批次</td></tr>");
								return false;
							}
							
							var weight_shengyu = 0;//剩余重量相加，统计合计
							var number_shengyu = 0;//剩余件数相加，统计合计
							
							for ( var i = 0; i < obj.length; i++) {
								$("#contents tbody")
										.append(
												"<tr style='height: 40px;'><td>"
														+ obj[i].kuwei
														+ "</td>"
														+ "<td>"
														+ obj[i].picihao
														+ "</td><td>"
														+ obj[i].goodsName
														+ "</td><td>"
														+ obj[i].guige
														+ "</td><td>"
														+ obj[i].caizhi
														+ "</td><td>"
														+ obj[i].shuxing
														+ "</td>"
														+ "<td>"
														+ obj[i].chandi
														+ "</td><td>"
														+ obj[i].rukuweight.toFixed(3)
														+ "</td><td>"
														+ obj[i].rukunumber.toFixed(3)
														+ "</td><td>"
														+ obj[i].chukuweight.toFixed(3)
														+ "</td><td>"
														+ obj[i].chukunumber.toFixed(3)
														+ "</td>"
														+ "<td>"
														+ obj[i].shenyuweight.toFixed(3)
														+ "</td><td>"
														+ obj[i].shenyunumber.toFixed(3)
														+ "</td><td><a type='button' data-toggle='tab' onclick='clickMenu(this)'  href='#menu2' class='btn btn-danger btn-xs'>修改</a></td></tr>");
								var piciupdate = $("#piciupdate").val();
								if (piciupdate == "0") {
									$("#contents tbody tr td").last().remove();
								}
								
								weight_shengyu+=parseFloat(obj[i].shenyuweight);
								number_shengyu+=parseFloat(obj[i].shenyunumber);
							}
							$("#pageNow").val(obj[0].pageNow);
							$("#yeshu").val(obj[0].pageNow);
							
							$("#count_div table tr td").eq(0).children("b").text(weight_shengyu.toFixed(3)+"吨");
							$("#count_div table tr td").eq(1).children("b").text(number_shengyu.toFixed(3));
						},
						error : function() {
							document.location.href = "/XGProject/cangchu/page/login.jsp";
						}
					});
		});
	}

	//当点击修改的时候将对应的模态框显示出来
	function clickMenu(str) {
		var id = $(str).parents("tr").children("td").eq(1).text();
		$("#menu2").css("display", "block");
		$("#TGoodsId").val(id);//设置对应的点击的编号
		//设置对应的入库重量、入库件数、已出重量、已出件数
		var rukuweight = $(str).parents("tr").children("td").eq(7).text();
		var rukunumber = $(str).parents("tr").children("td").eq(8).text();
		var yichuweight = $(str).parents("tr").children("td").eq(9).text();
		var yichunumber = $(str).parents("tr").children("td").eq(10).text();
		$("#rukuweight").val(rukuweight);
		$("#rukunumber").val(rukunumber);
		$("#yichuweight").val(yichuweight);
		$("#yichunumber").val(yichunumber);

	}
	//当点击关闭的时候将模态框隐藏
	function closeModeal() {
		$("#menu2").css("display", "none");
	}

	//当修改完毕进行提交的时候触发
	$(function() {
		$("#TDetailUpdate").click(
				function() {
					var rukuweight = $("#rukuweight").val();
					var rukunumber = $("#rukunumber").val();
					var yichuweight = $("#yichuweight").val();
					var yichunumber = $("#yichunumber").val();

					if (rukuweight == "" || rukuweight == null
							|| rukunumber == "" || rukunumber == null
							|| yichuweight == "" || yichuweight == null
							|| yichunumber == "" || yichunumber == null) {
						alert("请填写有效值，不可以为空！");
						return false;
					}
					// 重量验证正则表达式
					var zhongyan = /^\d+(\.\d+)?$|^$|^(\d+|\-){7,}$/;
					if (zhongyan.test(rukuweight) == false
							|| zhongyan.test(rukunumber) == false
							|| zhongyan.test(yichunumber) == false
							|| zhongyan.test(yichuweight) == false) {
						alert("请填写有效值，不能包含其他字符！");
						return false;
					}

					//验证通过则提交
					if (confirm("确定提交吗？")) {
						$("#TGoodsForm").submit();
					}
				});
	});
</script>

<%
	int x = 0;//批次修改的权限
	
	List<Powers> power = (List<Powers>)request.getSession().getAttribute("power");
	for(int i=0; i<power.size(); i++){
		if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("批次修改")){
	x++;
		}
	}
%>
<body onload="showContent()">
	<c:if test="${tlist==null }">
		<script>
			document.location.href = "${pageContext.request.contextPath}/tarehouse.do?flag=getTarehouseAll&ff=pici";
		</script>
	</c:if>
	<input type="hidden" value="<%=x%>" id="piciupdate" />
	<script>
		//如果没有修改批次的权限的时候隐藏对应的编辑按钮
		$(function() {
			var piciupdate = $("#piciupdate").val();
			if (piciupdate == "0") {
				$("#contents thead tr th").last().remove();
			}
		});
	</script>
	<!--内容起始处-->
	<div id="mainFrame" class="main">
		<div class="daohang">
			<ul>
				<i>当前位置：</i>
				<a style="cursor:pointer;">数据中心</a>
				<span>/</span>&nbsp;
				<a href="javascript:document.location.reload();">货物批次查询</a>
				<span>/</span>&nbsp;
			</ul>
		</div>
		<div class="container-fluid" style="margin-top: 15px;">
			<form action="">
				<div class='col-md-2 col-md-offset-1 col-sm-6'>
					<div class="form-group">
						<div class="input-group">
							<input class="form-control" type="text" id="startTime"
								placeholder="开始日期" /> <span class="input-group-addon"><label
								class="glyphicon glyphicon-calendar" for="startTime"></label> </span>
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
				<div class="col-md-2 col-xs-6 form-group">
					<div class="input-group">
						<select id="kuwei" class="demo-default" onchange="showContent()">
							<option value=""></option>
							<c:forEach items="${tlist }" var="t">
								<option value="${t.tarehouseName }">${t.tarehouseName }</option>
							</c:forEach>
						</select> <span class="input-group-addon">库位</span>
					</div>
				</div>
				<div class="col-md-2  col-xs-6 form-group">
					<div class="input-group">
						<input type="text" class="form-control" id="huowu"
							placeholder="货物资料"> <span class="input-group-addon">货物资料</span>
					</div>
				</div>
				<a type="button" class="btn btn-warning"
					style="float: left; margin-left: 15px;" id="tijiao">提交</a>
			</form>
		</div>
		<!-- 以下为订单内容页面 -->
		<div class="container-fluid">
			<table class="table table-striped table-hover" id="contents">
				<!-- <caption>库位明细表</caption> -->
				<thead style="font-size: 15px;">
					<tr>
						<th>库位名称</th>
						<th>货物批次</th>
						<th>货物名称</th>
						<th>货物规格</th>
						<th>货物材质</th>
						<th>货物属性</th>
						<th>货物产地</th>
						<th>入库重量</th>
						<th>入库件数</th>
						<th>已出重量</th>
						<th>已出件数</th>
						<th>剩余重量</th>
						<th>剩余件数</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
		<!-- 修改模态框 -->
		<div id="menu2" class="tab-pane fade"
			style="position: fixed; top:15%; left:20%; width:600px; height: 400px; display: none; ">
			<div class="modal-content" style="width:100%">
				<form class="form-horizontal" name="tarehouseDetailForm"
					method="post"
					action="${pageContext.request.contextPath}/tarehouseDetail.do?flag=updatePiCi"
					id="TGoodsForm">
					<div class="modal-header">
						<h4 class="modal-title" id="myModalLabel">货物批次修改</h4>
					</div>
					<input type="hidden" id="TGoodsId" name="tdetailId" />
					<div class="modal-body">
						<div class="form-group">
							<label for="name1" class="col-sm-3 control-label">入库重量</label>
							<div class="col-sm-6" style="margin-top:-5px;">
								<input type="text" class="form-control" id="rukuweight"
									name="tdetailWeight">
							</div>
						</div>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label for="name1" class="col-sm-3 control-label">入库件数</label>
							<div class="col-sm-6" style="margin-top:-5px;">
								<input type="text" class="form-control" id="rukunumber"
									name="tdetailNumber">
							</div>
						</div>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label for="name1" class="col-sm-3 control-label">已出重量</label>
							<div class="col-sm-6" style="margin-top:-5px;">
								<input type="text" class="form-control" id="yichuweight"
									name="tdetailEweight">
							</div>
						</div>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label for="name1" class="col-sm-3 control-label">已出件数</label>
							<div class="col-sm-6" style="margin-top:-5px;">
								<input type="text" class="form-control" id="yichunumber"
									name="tdetailEnumber">
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<a class="btn btn-primary closeOk" onclick="closeModeal()">关闭</a>
						<input class="btn btn-primary" type="button" id="TDetailUpdate"
							value="提交" />
					</div>
				</form>
			</div>
		</div>
	</div>
	<input type="hidden" id="pageNow" value="1" />
	<div class="page_show" style="margin-top:10px;">
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
	
	<div id="count_div">
		<table>
			<tr>
				<td>剩余重量：<b></b></td>
				<td>剩余件数：<b></b></td>
			</tr>
		</table>
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
	$('#kuwei').selectize();
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
