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

#savegangJia input {
	width: 100%;
}
</style>
</head>

<body onload="load()">

	<!--内容起始处-->
	<div id="mainFrame" class="main">
		<div class="daohang">
			<ul>
				<i>当前位置：</i>
				<a style="cursor:pointer;">仓储资料</a>
				<span>/</span> &nbsp;
				<a href="javascript:document.location.reload();"> 今日钢价 </a>
				<span>/</span> &nbsp;
			</ul>
		</div>
		<div class="container-fluid">
			<ul class="nav nav-tabs nav-pills"
				style="margin: 10px 2.5%; font-weight: bold;">
				<li class="active"><a data-toggle="tab" href="#home"> <span
						class="glyphicon glyphicon-th-list" aria-hidden="true"
						style="margin-right: 8px;"></span>添加今日钢价</a>
				</li>
				<li><a data-toggle="tab" href="#menu2" id="addOperator"> <span
						class="glyphicon glyphicon-th-list" aria-hidden="true"
						style="margin-right: 8px;"></span>历史钢价</a>
				</li>
			</ul>
			<div class="tab-content">
				<div id="home" class="tab-pane fade in active">
					<div class="container-fluid" style="margin-top: 15px;">
						<div class="col-md-2 col-sm-6">
							<div class="input-group form-group">
								<input type="text" class="form-control" id="hangshuvalue"
									placeholder="显示行数"> <span class="input-group-addon">显示行数</span>
							</div>
						</div>
						<script type="text/javascript">
							$(function(){
								$("#hangshuvalue").keydown(function(e){
									if(e.keyCode==13){
										$("#hangshu").click();
									}
								});
							});
						</script>
						<a type="button" class="btn btn-warning" id="hangshu"
							style="margin-left: 5%;">提交</a>
					</div>
					<table class="table table-striped table-hover">
						<thead>
							<tr>
								<th>货物名称</th>
								<th>货物规格</th>
								<th>货物材质</th>
								<th>货物属性</th>
								<th>货物产地</th>
								<th>货物单价(吨)</th>
								<th>备注</th>
								<th style="width: 100px;"><a
									class='btn btn-danger btn-sm bianjiOk' onclick='savefun()'>添加</a>
								</th>
							</tr>
						</thead>
						<tbody id="savegangJia">

						</tbody>
					</table>
					<a type="button" class="btn btn-warning btn-sm bianjiOk"
						id="zengjia"
						style=" float: left; margin-left: 5%; color:black; font-weight:600; margin-top:10px;">增加</a>
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
									placeholder="货物资料"> <span class="input-group-addon">货物资料</span>
							</div>
						</div>
						<a type="button" class="btn btn-warning" id="tijiao"
							style="margin-left: 5%;">提交</a>
					</div>
					<div id="home" class="tab-pane fade in active">
						<table class="table table-striped table-hover">
							<thead>
								<tr>
									<th>货物名称</th>
									<th>货物规格</th>
									<th>货物材质</th>
									<th>货物属性</th>
									<th>货物产地</th>
									<th>货物单价(吨)</th>
									<th>操作员</th>
									<th>操作时间</th>
									<th>备注</th>
									<th style="width: 100px;">操作</th>
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
	<input type="hidden" value="" id="message" />
</body>
<script>
	function load() {
		saveshowContent();
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

		//当点击下方的增加的时候，进行增加添加的数据
		$("#zengjia")
				.click(
						function() {
							$("#savegangJia")
									.append(
											"<tr><td><input /></td><td><input /></td><td><input /></td>"
													+ "<td><input /></td><td><input /></td><td><input /></td><td><input /></td><td></td></tr>");
						});

		//当吧行数添加上显示的时候提交
		$("#hangshu").click(function() {
			saveshowContent();
		});
	});
	//当点击添加的时候进行触发
	function savefun() {
		$(function() {
			var message = "";
			var len = $("#savegangJia").find("tr").length;
			for ( var i = 0; i < len; i++) {
				var goodsName = $("#savegangJia").find("tr").eq(i)
						.find("input").eq(0).val();
				var guige = $("#savegangJia").find("tr").eq(i).find("input")
						.eq(1).val();
				var caizhi = $("#savegangJia").find("tr").eq(i).find("input")
						.eq(2).val();
				var shuxing = $("#savegangJia").find("tr").eq(i).find("input")
						.eq(3).val();
				var chandi = $("#savegangJia").find("tr").eq(i).find("input")
						.eq(4).val();
				var danjia = $("#savegangJia").find("tr").eq(i).find("input")
						.eq(5).val();
				var beizhu = $("#savegangJia").find("tr").eq(i).find("input")
						.eq(6).val();

				if (goodsName == "" || guige == "" || caizhi == ""
						|| shuxing == "" || chandi == "" || danjia == "") {
					alert("请将数据信息填写完整！");
					return false;
				}

				$.ajax({
					type : "post",
					url : "steelPriceAction.do?flag=saveSteelPrice",
					data : {
						"time" : new Date().getTime(),
						"spgoodsName" : goodsName,
						"spgoodsStandard" : guige,
						"spgoodsQuality" : caizhi,
						"spgoodsProperty" : shuxing,
						"spgoodsYieldly" : chandi,
						"spgoodsPrice" : danjia,
						"spremark" : beizhu
					},
					dataType : "text",
					async : false,
					success : function(obj) {
						if (obj == null) {
							message = "添加失败！";
						} else {
							message = obj;
						}
					},
					error : function() {
						message === "上传失败！";
					}
				});
			}
			alert(message);
		});
	}
	//添加之前的查询
	function saveshowContent() {
		var rowSize = $("#hangshuvalue").val();
		var yan = /^[0-9]*$/;
		if (rowSize == "" || rowSize == null || yan.test(rowSize) == false) {
			rowSize = 10;
		}
		$
				.ajax({
					type : "post",
					url : "steelPriceAction.do?flag=saveQuerySteelPrice",
					data : {
						"time" : new Date().getTime(),
						"rowSize" : rowSize
					},
					dataType : "json",
					success : function(obj) {
						$("#savegangJia").html("");
						if (obj != null) {
							for ( var i = 0; i < obj.length; i++) {
								$("#savegangJia")
										.append(
												"<tr><td><input value='"+obj[i].goodsName+"' /></td><td><input value='"+obj[i].guige+"' />"
														+ "</td><td><input value='"+obj[i].caizhi+"' /></td>"
														+ "<td><input value='"+obj[i].shuxing+"' /></td><td><input value='"+obj[i].chandi+"' /></td><td><input value='"+obj[i].danjia+"' />"
														+ "</td><td><input  /></td><td></td></tr>");
							}
						}
					},
					error : function() {
						alert("上传失败！");
					}
				});
	}
	//查询历史钢价的记录
	function showContent() {

		$(function() {
			$
					.ajax({
						type : "post",
						url : "steelPriceAction.do?flag=querySteelPrice",
						async : true,
						data : {
							"time" : new Date().getTime(),
							"begin" : $("#startTime").val(),
							"finish" : $("#endTime").val(),
							"spgoodsName" : $("#caozuoren").val(),
							"pageNow" : $("#pageNow").val(),
						},
						dataType : "json",
						success : function(obj) {
							$("#jilubody").html("");
							if (obj == null || obj.length <= 0) {
								$("#jilubody")
										.append(
												"<tr><td colspan='10' style='text-align:center;' >无历史记录</td></tr>");
								return false;
							}
							if (obj[0].result == null) {
								$("#jilubody")
										.append(
												"<tr><td colspan='10' style='text-align:center;'>无历史记录</td></tr>");
								return false;
							}
							for ( var i = 0; i < obj.length; i++) {
								$("#jilubody")
										.append(
												"<tr><td data-toggle='popover' data-trigger='hover'>"
														+ obj[i].goodsName
														+ "</td><td data-toggle='popover' data-trigger='hover'>"
														+ obj[i].guige
														+ "</td><td data-toggle='popover' data-trigger='hover'>"
														+ obj[i].caizhi
														+ "</td><td data-toggle='popover' data-trigger='hover'>"
														+ obj[i].shuxing
														+ "</td><td data-toggle='popover' data-trigger='hover'>"
														+ obj[i].chandi
														+ "</td><td style='width:500px;' data-toggle='popover' data-trigger='hover'>"
														+ obj[i].danjia
														+ "</td><td data-toggle='popover' data-trigger='hover'>"
														+ obj[i].caozuoren
														+ "</td><td data-toggle='popover' data-trigger='hover'>"
														+ obj[i].time
														+ "</td><td data-toggle='popover' data-trigger='hover'>"
														+ obj[i].remark
														+ "</td><td data-toggle='popover' data-placement='left' data-trigger='hover'>"
														+ "<a class='btn btn-danger btn-xs bianjiOk'"
														+ " onclick='updatefun(this)'>修改</a><input type='hidden' value='"+obj[i].id+"' id='spid' /></td></tr>");
							}
							$("#pageNow").val(obj[0].pageNow);
							$("#yeshu").val(obj[0].pageNow);
						},
						error : function() {
							alert("数据错误！");
						}
					});
		});
	}
	function updatefun(str) {
		$(function() {
			if ($(str).text() == "修改") {
				var goodsName = $(str).parents("tr").find("td").eq(0).text();
				var guige = $(str).parents("tr").find("td").eq(1).text();
				var caizhi = $(str).parents("tr").find("td").eq(2).text();
				var shuxing = $(str).parents("tr").find("td").eq(3).text();
				var chandi = $(str).parents("tr").find("td").eq(4).text();
				var danjia = $(str).parents("tr").find("td").eq(5).text();
				var beizhu = $(str).parents("tr").find("td").eq(8).text();
				$(str).parents("tr").find("td").eq(0).html(
						"<input value='"+goodsName+"'/>");
				$(str).parents("tr").find("td").eq(1).html(
						"<input value='"+guige+"'/>");
				$(str).parents("tr").find("td").eq(2).html(
						"<input value='"+caizhi+"'/>");
				$(str).parents("tr").find("td").eq(3).html(
						"<input value='"+shuxing+"'/>");
				$(str).parents("tr").find("td").eq(4).html(
						"<input value='"+chandi+"'/>");
				$(str).parents("tr").find("td").eq(5).html(
						"<input value='"+danjia+"'/>");
				$(str).parents("tr").find("td").eq(8).html(
						"<input value='"+beizhu+"'/>");
				$(str).removeClass("btn-danger").addClass("btn-warning");
				$(str).text("提交");
			} else {
				var id = $(str).parents("tr").find("#spid").val();
				var goodsName = $(str).parents("tr").find("input").eq(0).val();
				var guige = $(str).parents("tr").find("input").eq(1).val();
				var caizhi = $(str).parents("tr").find("input").eq(2).val();
				var shuxing = $(str).parents("tr").find("input").eq(3).val();
				var chandi = $(str).parents("tr").find("input").eq(4).val();
				var danjia = $(str).parents("tr").find("input").eq(5).val();
				var beizhu = $(str).parents("tr").find("input").eq(6).val();
				if (goodsName == "" || guige == "" || caizhi == ""
						|| shuxing == "" || chandi == "" || danjia == "") {
					alert("请将数据信息填写完整！");
					return false;
				}
				$.ajax({
					type : "post",
					url : "steelPriceAction.do?flag=updateSteelPrice",
					data : {
						"time" : new Date().getTime(),
						"spgoodsName" : goodsName,
						"spgoodsStandard" : guige,
						"spgoodsQuality" : caizhi,
						"spgoodsProperty" : shuxing,
						"spgoodsYieldly" : chandi,
						"spgoodsPrice" : danjia,
						"spremark" : beizhu,
						"spid" : id
					},
					dataType : "text",
					success : function(obj) {
						$(str).parents("tr").find("td").eq(0).html(goodsName);
						$(str).parents("tr").find("td").eq(1).html(guige);
						$(str).parents("tr").find("td").eq(2).html(caizhi);
						$(str).parents("tr").find("td").eq(3).html(shuxing);
						$(str).parents("tr").find("td").eq(4).html(chandi);
						$(str).parents("tr").find("td").eq(5).html(danjia);
						$(str).parents("tr").find("td").eq(8).html(beizhu);
						$(str).text("修改");
						if (obj == null) {
							alert("修改失败！");
						} else {
							alert(obj);
						}
					},
					error : function() {
						alert("上传失败！");
					}
				});

				$(str).removeClass("btn-warning").addClass("btn-danger");
			}
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