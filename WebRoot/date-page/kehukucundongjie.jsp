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
				<a href="javascript:document.location.reload();">冻结客户库存</a>
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
				style="float: left; margin-left: 10px;" id="tijiao">提交</a>
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
					<th>冻结重量(吨)</th>
					<th>剩余重量(吨)</th>
					<td>操作</td>
				</tr>
			</thead>
			<tbody style="font-size: 14px;" id="contentbody">
				<tr>
					<td>嘉利晟<input type="hidden" value="2" id="clientId" /></td>
					<td>建材类</td>
					<td>螺纹钢</td>
					<td>12</td>
					<td>HRB400E</td>
					<td>定尺9米</td>
					<td>酒钢<input type="hidden" value="5" id="goodsId" /></td>
					<td>100</td>
					<td>100</td>
					<td><a class='btn btn-warning btn-xs' type='button'
						data-toggle='modal' href="#jiesuan" onclick="jiesuan(this)">解冻</a>
						<a class='btn btn-warning btn-xs' type='button'
						data-toggle='modal' href="#jiedong" onclick="jiedong(this)">冻结</a>
					</td>
				</tr>
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
			$("#jiesuan #weight").val(
					$(str).parents("tr").children("td").eq(8).text());
			$("#jiesuan #dongzhong").val(
					$(str).parents("tr").children("td").eq(7).text());
		}
		function jiedong(str) {
			$("#jiedong #kehu").val(
					$(str).parents("tr").find("#clientId").val());
			$("#jiedong #goods").val(
					$(str).parents("tr").find("#goodsId").val());
			$("#jiedong #weight").val(
					$(str).parents("tr").children("td").eq(8).text());
		}
	</script>
	<!-- 解冻模态框（Modal） -->
	<div class="modal fade" id="jiesuan" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width: 80%;">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="myModalLabel">解冻</h4>
				</div>
				<div class="modal-body">
					<form
						action="${pageContext.request.contextPath }/clientGoods.do?flag=JieDongClientGoods"
						method="post" name="clientGoodsForm" id="jiedongform">
						<input type="hidden" id="kehu" name='cgoodsId' /> <input
							type="hidden" id="goods" /> <input type="hidden" id="weight" /><input
							type="hidden" value="" id="dongzhong" />

						<div class="row">
							<div class="col-lg-8 col-md-offset-2 form-group">
								<div class="input-group">
									<span class="input-group-addon">解冻重量</span> <input
										class="form-control" type="text" name="cgoodsFreezeWeight"
										id="jiedongweight">
								</div>
							</div>
							<div class="col-lg-8 col-md-offset-2 form-group">
								<div class="input-group">
									<span class="input-group-addon">备注</span> <input
										class="form-control" type="text" name='cgoodsRemark'>
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
	<!-- 冻结摸态框（Modal） -->
	<div class="modal fade" id="jiedong" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width: 80%;">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="myModalLabel">冻结</h4>
				</div>
				<div class="modal-body">
					<form
						action="${pageContext.request.contextPath }/clientGoods.do?flag=DongJieClientGoods"
						method="post" name="clientGoodsForm" id="dongjieform">
						<input type="hidden" id="kehu" name='cgoodsId' /> <input
							type="hidden" id="goods" /> <input type="hidden" id="weight" />

						<div class="row">
							<div class="col-lg-8 col-md-offset-2 form-group">
								<div class="input-group">
									<span class="input-group-addon">冻结重量</span> <input
										class="form-control" type="text" name="cgoodsFreezeWeight"
										id="dongjieweight">
								</div>
							</div>
							<div class="col-lg-8 col-md-offset-2 form-group">
								<div class="input-group">
									<span class="input-group-addon">备注</span> <input
										class="form-control" type="text" name='cgoodsRemark'>
								</div>
							</div>
						</div>
					</form>
					<div class="modal-footer col-md-offset-3">
						<a type="button" class="btn btn-default" data-dismiss="modal">关闭</a>
						<a type="button" class="btn btn-primary"
							onclick="caozuotijiao('dong')">提交更改</a>
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
				<td>冻结重量（吨）：<b></b></td>
				<td>剩余重量（吨）：<b></b></td>
			</tr>
		</table>
	</div>
</body>


</html>
<script>
	//当点击要提交的时候触发
	function caozuotijiao(str) {
		//判断如果是解冻
		if (str == 'jie') {
			//对解冻进行判断，解冻的重量不能大于，现在已经冻结的重量
			if (parseFloat($("#dongzhong").val()) < parseFloat($(
					"#jiedongweight").val())) {
				alert("解冻重量不能大于冻结重量，请重写填写！");
				return false;
			}
			if (confirm("确定解冻吗？")) {
				$("#jiedongform").submit();
			}
		} else if (str == 'dong') {
			//判断冻结的重量不能大于现有的重量
			if (parseFloat($("#dongjieweight").val()) > parseFloat($(
					"#jiedong #weight").val())) {
				alert("冻结重量不能大于现有重量，请重写填写！");
				return false;
			}
			if (confirm("确定冻结吗？")) {
				$("#dongjieform").submit();
			}
		}
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
							var weight_dongjie = 0;//冻结重量的相加
							var weight_shengyu = 0;//剩余重量的相加
							
							for ( var i = 0; i < obj.length; i++) {
								$("#contentbody")
										.append(
												"<tr style='height:40px;'><td>"
														+ obj[i].clientName
														+ "<input type='hidden' value='"+obj[i].cgid+"' id='clientId'/></td><td>"
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
														+ obj[i].dongjieweight
														+ "</td><td>"
														+ obj[i].shengyuweight
														+ "</td>"
														+ "<td><a class='btn btn-warning btn-xs' type='button' data-toggle='modal'"
														+ "href='#jiesuan' onclick='jiesuan(this)'>解冻</a> <a class='btn btn-warning btn-xs' type='button' data-toggle='modal'"
														+ "href='#jiedong' onclick='jiedong(this)'>冻结</a>"
														+ "</td></tr>");
														
											weight_dongjie+=parseFloat(obj[i].dongjieweight);
											weight_shengyu+=parseFloat(obj[i].shengyuweight);
							}
							$("#pageNow").val(obj[0].pageNow);
							$("#yeshu").val(obj[0].pageNow);
							$("#count_div table tr td").eq(0).children("b").text(weight_dongjie.toFixed(3));
							$("#count_div table tr td").eq(1).children("b").text(weight_shengyu.toFixed(3));
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
	if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("冻结和解冻")){
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