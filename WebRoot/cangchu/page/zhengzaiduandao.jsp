<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<script type="text/javascript" src="cangchu/js/jquery1.9.0.min.js"></script>
<!--引入自写的css样式-->
<link rel="stylesheet" type="text/css" href="cangchu/css/faqichuku.css" />
<link rel="stylesheet" href="cangchu/cangchu/css/publick.css">

<link rel="stylesheet" href="css/selectize.default.css">
<script src="js/selectize.js"></script>

<script type="text/javascript">
	$(".standard1").selectize();
</script>

<style>
.tab_context .tab_bottom {
	width: 1100px;
	padding-top: 10px;
}

.tab_context .tab_bottom tr td select[class=zhuangxie] {
	width: 110px;
}

.qingkong {
	background-color: blue;
	color: #FFFFFF;
	text-decoration: none;
	padding: 1px 5px;
}

.chuku_context {
	width: 1100px;
	margin: 0 auto;
	padding-top: 20px;
}

.modeal_bg {
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background-color: #000000;
	opacity: 0.5;
}

.modeal_bottom_middle p {
	text-align: center;
	margin-top: 10%;
}

#caozuotijiao {
	background: url(cangchu/img/zengjiahuowu.png);
}

#caozuotijiao:hover {
	background: url(cangchu/img/zengjiahuowu2.png);
}

table {
	-moz-box-shadow: 5px 5px 5px #000;
	-webkit-box-shadow: 5px 5px 5px #000;
	box-shadow: 2px 2px 10px #000;
	border-top: 1px solid #888888;
	border-left: 1px solid #888888;
}
</style>
</head>

<body onload="load()" ondragstart="return false">
	<!--内容起始处-->
	<div id="mainFrame" class="main">
		<div class="daohang">
			<ul>
				<i>当前位置：</i>
				<a style="cursor:pointer;">仓储业务</a>
				<span>/</span>&nbsp;
				<a href="javascript:window.history.go(-1);">操作订单</a>
				<span>/</span>&nbsp;
				<a href="javascript:document.location.reload();">开始操作</a>
			</ul>
		</div>
		<div class="chuku_context">
			<strong class="yunshu_xinxi">订 单 信 息</strong>
			<!--form开始-->
			<form
				action="${pageContext.request.contextPath }/duanDaoAction.do?flag=wanchengShift"
				method="post" name="duanDaoForm" id="nuokuform">
				<input type="hidden"
					value="<%=request.getSession().getAttribute("iulistId")%>"
					name="interiorUserByShiftExecutor" />
				<!--table开始-->
				<table class="tab_head">
					<tr>
						<td align="right" width="80px">订单编号：</td>
						<td width="150px">${shift.shiftId }<input type="hidden"
							value="${shift.shiftId }" name="shiftId" /></td>
						<td align="right" width="80px">发起人：</td>
						<td align="left" width="150px">${shift.interiorUserByShiftFounderMember.iuserName
							}</td>
						<td align="right" width="80px">发起时间：</td>
						<td class="chehao" width="150px">
							<!--
                                        	作者：2586190195@qq.com
                                        	时间：2017-06-06
                                        	描述：判断选择的运输方式，显示不同的样式
                                        --> <!--当选择的运输方式是火运的时候显示-->
							${shift.shiftTime }</td>
						<td align="right" width="80px">原库位：</td>
						<td>${shift.tarehouseByShiftPast.tarehouseName }</td>
						<!--++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++未做完-->
					</tr>
					<tr>
						<td align="right">新库位：</td>
						<td>${shift.tarehouseByShiftNew.tarehouseName }</td>
						<td align="right" width="80px">执行人：</td>
						<td>${shift.interiorUserByShiftExecutor.iuserName }</td>
						<td>状态：</td>
						<td>${shift.shiftDefinedOne }</td>
						<td align="right" width="80px">备注：</td>
						<td>${shift.shiftRemark }</td>
					</tr>
				</table>
				<!--tabhead结束-->
				<!--货物信息开始-->
				<div id="tab_context" class="tab_context">
					<strong>货 物 信 息</strong> <br />
					<table class="tab_bottom">
						<tr>
							<td align="right" width="80px">货物品类：</td>
							<td width="150px">${shift.goods.goodsCategory.goodsCategoryName
								}</td>
							<td align="right" width="80px">货物名称：</td>
							<td width="150px">${shift.goods.goodsName }</td>
							<td align="right" width="80px">货物规格：</td>
							<td width="150px">${shift.goods.goodsStandard.goodsStandardName
								}</td>
							<td align="right" width="80px">货物材质：</td>
							<td width="150px">${shift.goods.goodsQuality.goodsQualityName
								}</td>
						</tr>
						<tr>
							<td align="right">货物属性：</td>
							<td>${shift.goods.goodsProperty.goodsPropertyName }</td>
							<td align="right">货物产地：</td>
							<td>${shift.goods.goodsYieldly.goodsYieldlyName }</td>
							<td align="right">重量：</td>
							<td>${shift.shiftWeight }吨</td>
							<td align="right">件数：</td>
							<td>${shift.shiftNumber
								}${shift.goods.goodsUnit.goodsUnitName }</td>
						</tr>
					</table>
				</div>
				<div id="tab_context" class="tab_context">
					<strong>填 写 信 息</strong> <br />
					<table class="tab_bottom" style="padding-bottom: 10px;">
						<tr>
							<td align="right" width="80px">天车工：</td>
							<td width="200px"><select id="tianche" name="shiftCraneman">
							</select>
							</td>
							<td align="right" width="80px">装卸工：</td>
							<td colspan="2" width="200px">
								<!-- <input type="text"
								readonly="readonly" id="zhuangxie" name="shiftStevedore" /> <a
								style="cursor:pointer;" class="qingkong">清空</a> <select
								class="zhuangxie" id="zhuangxies">
							</select> -->

								<div class="form-group">
									<div id="guigeDiv1" class="zhuangxie2">
										<select id="standard1" class="demo-default standard1"
											onchange="dd()">
										</select> <input type="hidden" name="shiftStevedore" class="workzx" />
									</div>
								</div>
							</td>
							<td align="right">短倒司机：</td>
							<td><input type="text" name="driverName" />
							</td>
						</tr>
						<tr>
						<td align="right">备注：</td>
							<td><input type="text" name="shiftRemark" />
							</td>
						</tr>
					</table>
				</div>
				<div class="queren">
					<a style="cursor:pointer;" id="caozuotijiao">提 交</a>
				</div>
			</form>
		</div>
		<!--出库内容结束-->
	</div>
	<!--页面起始处结束-->
</body>
<script>
	$(function() {

		//当点击提交的时候
		$("#caozuotijiao").click(function() {
			var tianche = $("#tianche").val(); //天车工
			var zhaungxie = $("#standard1").val(); //装卸工

			if (tianche == "" || tianche == "无人员") {
				alert("请填入天车工!");
				return false;
			}

			if (zhaungxie == "" || zhaungxie == null) {
				alert("请填入装卸工！");
				return false;
			}

			$(".workzx").val($("#standard1").val());
			if (confirm("确定提交吗？")) {
				$("#nuokuform").submit();
			}
		});

	});

	function tianchegong() {
		$(function() {
			$
					.ajax({
						type : "post",
						url : "working.do?flag=getZuoYeRenYuan&ff=tianche&time="
								+ new Date().getTime(),
						async : true,
						dataType : "json",
						success : function(obj) {
							$("#tianche").html("");
							if (obj[0].result != null) {
								for ( var i = 0; i < obj.length; i++) {
									$("#tianche")
											.append(
													"<option value='"+obj[i].name+"'>"
															+ obj[i].name
															+ "</option>");
								}
							} else {
								$("#tianche").append(
										"<option value='无人员'>无人员</option>");
							}
						},
						error : function() {
							document.location.href = "/XGProject/cangchu/page/login.jsp";
						}
					});
		});
	}

	function zhuangxiegong() {
		$(function() {
			$
					.ajax({
						type : "post",
						url : "working.do?flag=getZuoYeRenYuan&ff=zhuangxie&time="
								+ new Date().getTime(),
						async : true,
						dataType : "json",
						success : function(obj) {
							var $dataObj = eval(obj);
							if ($dataObj.length > 0) {

								$(".standard1 option").remove();//先清空，再添加;					
								var $select = $(".standard1");
								$
										.each(
												$dataObj,
												function(i, item) {
													$select
															.append("<option value='"+item.zname+"'>"
																	+ item.zname
																	+ "</option>");
													$select
															.appendTo(".zhuangxie");
												});
								$('.standard1').selectize({
									maxItems : 3
								});
							}
						},
						error : function() {
							document.location.href = "/XGProject/cangchu/page/login.jsp";
						}
					});
		});

	}

	function load() {
		tianchegong();
		zhuangxiegong();
	}
</script>

</html>