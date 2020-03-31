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

<body onload="load()">
	<div class="right" id="mainFrame">
		<div class="daohang">
			<ul>
				<i>当前位置：</i>
				<a style="cursor: pointer;">仓储业务</a>
				<span>></span>&nbsp;
				<a href="javascript:document.location.reload();">盘点客户库存</a>
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
			<div class="col-lg-3 col-xs-6">
				<div class="input-group form-group">
					<input type="text" class="form-control" placeholder="货物资料"
						id="huowu"> <span class="input-group-addon">货物资料</span>
				</div>
			</div>
			<a type="button" class="btn btn-warning"
				style="float: left; margin-left: 10px;" id="tijiao">提交</a> <a
				type="button" class="btn"
				style="float: left; margin-left: 10px;" id="chakan">查看盘点记录</a>
			<!-- 当点击查看盘点记录的时候触发 -->
			<script type="text/javascript">
				$(function(){
					$("#chakan").click(function(){
						document.location.href="/XGProject/date-page/pandianjilu.jsp";
					});
				});
			</script>
		</div>
		<!-- 以下为数据内容 -->
		<table class="table table-bordered table-hover"
			style="min-width: 1000px;">
			<thead>
				<tr>
					<th>客户名称</th>
					<th>货物品类</th>
					<th>货物名称</th>
					<th>货物规格</th>
					<th>货物材质</th>
					<th>货物属性</th>
					<th>货物产地</th>
					<th>原重量(吨)</th>
					<td>操作</td>
				</tr>
			</thead>
			<tbody style="font-size: 14px;" id="contentbody">
			</tbody>
		</table>
		</form>
		<ul class="pager">
			<input type="hidden" id="pageNow" value="1" />
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
	<script>
		function jiesuan(str) {
			$("#jiesuan #kehu").val(
					$(str).parents("tr").find("#clientId").val());
			$("#jiesuan #goods").val(
					$(str).parents("tr").find("#goodsId").val());
			$("#jiesuan #yuanzhong").val(
					$(str).parents("tr").children("td").eq(7).text());
		}
	</script>
	<!-- 解冻模态框（Modal） -->
	<div class="modal fade" id="jiesuan" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width: 80%;">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="myModalLabel">盘点客户库存</h4>
				</div>
				<div class="modal-body">
					<form
						action="${pageContext.request.contextPath }/checksClientGoods.do?flag=FaQiChecksClientGoods"
						method="post" name="checksClientGoodsForm" id="jiedongform">
						<!-- 客户编号 -->
						<input type="hidden" id="kehu" name='client' />
						<!-- 货物编号 -->
						<input type="hidden" id="goods" name="goods" />
						<!-- 原重量 -->
						<input type="hidden" value="" id="yuanzhong"
							name="ccgoodsBeforeWeight" />
						<!-- 设置盘点人 -->
						<input type="hidden"
							value="<%=request.getSession().getAttribute("iulistId")%>"
							name="interiorUser" />

						<div class="row">
							<div class="col-lg-8 col-md-offset-2 form-group">
								<div class="input-group">
									<span class="input-group-addon">盈库重量（吨）</span> <input
										class="form-control" type="text" name="ccgoodsSurplus"
										id="yingweight">
								</div>
							</div>
							<div class="col-lg-8 col-md-offset-2 form-group">
								<div class="input-group">
									<span class="input-group-addon">亏库重量（吨）</span> <input
										class="form-control" type="text" name="ccgoodsLoss"
										id="kuiweight">
								</div>
							</div>
							<div class="col-lg-8 col-md-offset-2 form-group">
								<div class="input-group">
									<span class="input-group-addon">备注</span> <input
										class="form-control" type="text" name='ccgoodsRemark'>
								</div>
							</div>
						</div>
					</form>
					<div class="modal-footer col-md-offset-3">
						<a type="button" class="btn btn-default" data-dismiss="modal">关闭</a>
						<a type="button" class="btn btn-primary"
							onclick="caozuotijiao('jie')">提交更改</a>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>
	</div>
	
	<div id="count_div">
		<table>
			<tr>
				<td>重量合计（吨）：<b></b></td>
			</tr>
		</table>
	</div>
</body>


</html>
<script>
	//当点击要提交的时候触发
	function caozuotijiao(str) {
		//进行盘点提交
		$(function() {
			if ($("#yingweight").val() == "" && $("#kuiweight").val() == "") {
				alert("请填写盈库重量或者亏库重量！");
				return false;
			}
			// 重量验证正则表达式
			var zhongyan = /^\d+(\.\d+)?$|^$|^(\d+|\-){7,}$/;
			if ($("#yingweight").val() != "") {
				if (zhongyan.test($("#yingweight").val()) == false) {
					alert("请正确填写盈库重量！");
					return false;
				}
				//当是盈库的时候填写的盈库的重量不能大于现在目前的原有重量
				if (parseFloat($("#yuanzhong").val()) < parseFloat($(
						"#yingweight").val())) {
					alert("盈库的重量不能大于原有重量！");
					return false;
				}
			}
			if ($("#kuiweight").val() != "") {
				if (zhongyan.test($("#kuiweight").val()) == false) {
					alert("请正确填写亏库重量！");
					return false;
				}
			}
			if (confirm("确定提交吗？")) {
				$("#jiedongform").submit();//进行提交
			}
		});
	}
	$(function() {
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

		$(function() {
			$
					.ajax({
						type : "post",
						url : "clientGoods.do?flag=getClientGoodsAll&pageNow="
								+ $("#pageNow").val(),
						async : true,
						data : {
							"time" : new Date().getTime(),
							"goodsName" : $("#huowu").val(),
							"jiancheng" : $("#huozhu").val()
						},
						dataType : "json",
						success : function(obj) {
							$("#contentbody").html("");
							if (obj == null) {
								$("#contentbody")
										.append(
												"<tr colspan='10' style='text-align:center;'>无</tr>");
								return false;
							}
							if (obj[0].result == null) {
								$("#contentbody")
										.append(
												"<tr colspan='10' style='text-align:center;'>无</tr>");
								return false;
							}
							
							var sum_weight = 0 ;//剩余重量进行相加，统计出来合计
							for ( var i = 0; i < obj.length; i++) {
								$("#contentbody")
										.append(
												"<tr style='height:40px;'><td>"
														+ obj[i].clientName
														+ "<input type='hidden' value='"+obj[i].clientId+"' id='clientId'/></td><td>"
														+ obj[i].pinlei
														+ "</td><td>"
														+ obj[i].goodsName
														+ "</td>"
														+ "<td>"
														+ obj[i].guige
														+ "</td><td>"
														+ obj[i].caizhi
														+ "</td>"
														+ "<td>"
														+ obj[i].shuxing
														+ "</td><td>"
														+ obj[i].chandi
														+ "<input type='hidden' value='"+obj[i].goodsId+"' id='goodsId'/></td>"
														+ "<td>"
														+ obj[i].shengyuweight
														+ "</td>"
														+ "<td><a class='btn btn-danger btn-xs' type='button' data-toggle='modal'"
														+ "href='#jiesuan' onclick='jiesuan(this)'>盘点</a> "
														+ "</td></tr>");
														
														sum_weight+=parseFloat(obj[i].shengyuweight);
							}
							$("#pageNow").val(obj[0].pageNow);
							$("#yeshu").val(obj[0].pageNow);
							$("#count_div table tr td").eq(0).children("b").text(sum_weight.toFixed(3));;
						},
						error : function() {
							document.location.href = "/XGProject/cangchu/page/login.jsp";
						}
					});
		});

	}
</script>

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
	if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("新增盘点")){
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