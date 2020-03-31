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
<script type="text/javascript" src="cangchu/js/jquery1.9.0.min.js"></script>
<script type="text/javascript" src="cangchu/js/caozuoruku.js"></script>
<script type="text/javascript" src="cangchu/js/selectize.js"></script>
<!--引入自写的css样式-->
<link rel="stylesheet" type="text/css" href="cangchu/css/faqichuku.css" />
<link rel="stylesheet" href="cangchu/css/publick.css">
<link rel="stylesheet" href="cangchu/css/selectize.css">
<link rel="stylesheet" href="cangchu/css/selectize.default.css">
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

.modeal_bg {
	opacity: 0.8;
}

.item_jisuan {
	height: 50px;
	line-height: 50px;
	text-align: center;
	background-color: #f2f2f2;
	cursor: pointer;
	border-bottom: 1px solid #ffffff;
}

#jisuan_gangban table tr td {
	height: 40px;
	text-align: center;
}

#jisuan_luowengang table tr td {
	height: 40px;
	text-align: center;
}

#jisuan_xinggang table tr td {
	height: 40px;
	text-align: center;
}
</style>
</head>
<%
	int update = 0;
		
	List<Powers> power = (List<Powers>)request.getSession().getAttribute("power");
		for(int i=0; i<power.size(); i++){
	if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("操作修改")){
		update++;
	}
}
%>
<body>
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
			<strong class="yunshu_xinxi">运 输 信 息</strong>
			<!--form开始-->
			<form name="inputOperateForm" method="post"
				action="${pageContext.request.contextPath}/inputOperate.do?flag=updateInputOperat"
				id="inputOperateOk">
				<!--table开始-->
				<input type="hidden" value="${caozuoid}" name="ioperateId"
					id="exportoperateid" /> <input type="hidden" id="seedId"
					value="${inputOperate.inputSeed.iseedId}" />
				<table class="tab_head">
					<tr>
						<td align="right" width="80px">客户：</td>
						<td width="150px">${inputOperate.inputSeed.input.client.clientAbbreviation
							}</td>
						<td align="right" width="80px">运输方式：</td>
						<td align="left" width="150px">
							${inputOperate.inputSeed.input.inputCarryType}</td>
						<td align="right" width="80px">车号：</td>
						<td class="chehao" width="150px">
							<!--
                                        	作者：2586190195@qq.com
                                        	时间：2017-06-06
                                        	描述：判断选择的运输方式，显示不同的样式
                                        --> <!--当选择的运输方式是火运的时候显示-->
							${inputOperate.inputSeed.input.inputPlateNumber}</td>
						<td align="right" width="80px">司机姓名：</td>
						<td>${inputOperate.inputSeed.input.inputDriverName}</td>
						<!--++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++未做完-->
					</tr>
					<tr>
						<td align="right">司机电话：</td>
						<td>${inputOperate.inputSeed.input.inputDriverTel}</td>
						<td align="right" width="80px">提货单号：</td>
						<td>${inputOperate.inputSeed.input.inputClientNumber}</td>
						<td align="right" width="80px">备注：</td>
						<td>${inputOperate.inputSeed.input.inputRemark}</td>
					</tr>
				</table>
				<!--tabhead结束-->
				<!--货物信息开始-->
				<div id="tab_context" class="tab_context">
					<strong>货 物 信 息</strong> <br />
					<table class="tab_bottom">
						<tr>
							<td align="right" width="80px">货物品类：</td>
							<td width="150px">
								${inputOperate.inputSeed.goods.goodsCategory.goodsCategoryName }
							</td>
							<td align="right" width="80px">货物名称：</td>
							<td width="150px">${inputOperate.inputSeed.goods.goodsName }
							</td>
							<td align="right" width="80px">货物规格：</td>
							<td width="150px">
								${inputOperate.inputSeed.goods.goodsStandard.goodsStandardName }
							</td>
							<td align="right" width="80px">货物材质：</td>
							<td width="150px">
								${inputOperate.inputSeed.goods.goodsQuality.goodsQualityName }</td>
						</tr>
						<tr>
							<td align="right">货物属性：</td>
							<td>
								${inputOperate.inputSeed.goods.goodsProperty.goodsPropertyName }
							</td>
							<td align="right">货物产地：</td>
							<td>
								${inputOperate.inputSeed.goods.goodsYieldly.goodsYieldlyName }</td>
							<td align="right">重量：</td>
							<td>${inputOperate.inputSeed.iseedShouldWeight }吨</td>
							<td align="right">备注：</td>
							<td>${inputOperate.inputSeed.iseedRemark }</td>
						</tr>
						<tr>
							<td align="right">车皮号：</td>
							<td>${inputOperate.inputSeed.iseedDefinedOne }</td>
						</tr>
					</table>
				</div>
				<!--tab_context结束-->
				<div id="tab_context" class="tab_context">
					<strong>分 配 信 息</strong> <br />
					<table class="tab_bottom">
						<tr>
							<td align="right" width="80px">调度员：</td>
							<td width="150px">
								${inputOperate.interiorUserByIoperateDispatcherId.iuserLoginName
								}</td>
							<td align="right" width="80px">分配时间：</td>
							<td width="150px">${inputOperate.ioperateDispatcherTime }</td>
							<td align="right" width="80px">过磅/理算：</td>
							<td width="150px" id="guobang_lisuan">
								<!-- 将是否理算传入服务器判断，如果是理算就由保管员添加重量，如果是非理算，司磅员添加货物 --> <input
								type="hidden" name="ioperatePonderationTrue"
								value="${inputOperate.ioperatePonderationTrue }" />
								${inputOperate.ioperatePonderationTrue }</td>
							<td align="right" width="80px">分配重量：</td>
							<td width="150px">
								${inputOperate.inputSeed.iseedShouldWeight}吨</td>
						</tr>
						<tr>
							<td align="right">库位：</td>
							<td>${inputOperate.tarehouse.tarehouseName}</td>
							<td align="right">订单状态：</td>
							<td>${inputOperate.ioperateDefinedTwo}</td>
							<td colspan="4"></td>
						</tr>
					</table>
				</div>
				<div id="tab_context" class="tab_context">
					<strong>填 写 信 息</strong> <br />
					<table class="tab_bottom">
						<tr>
							<!-- 如果是理算货物显示，如果不是不显示 -->
							<c:if test="${inputOperate.ioperatePonderationTrue=='理算' }">
								<td align="right" width="150px">入库重量：</td>
								<td width="150px"><input type="text" id="weight"
									name="ioperateRealityWeight" style="width:100px;" /><a
									style="cursor:pointer; background-color:#0430AE; padding:1px 3px; border-radius:5px; 
										color:#ffffff; margin-left:15px;"
									id="jisuan">计算</a>
								</td>
							</c:if>
							<td align="right" width="150px">入库件数：</td>
							<td colspan="2" width="500px"><input type="text" id="number"
								name="ioperateRealityNumber" />
							</td>
							<td align="right" width="150px">天车工：</td>
							<td width="200px"><select id="tianche"
								name="ioperateCraneman">
									<c:forEach items="${workinglist}" var="work">
										<c:if test="${work.workingKindOfWork=='天车工'}">
											<option value="${work.workingName}">${work.workingName}</option>
										</c:if>
									</c:forEach>
							</select>
							</td>
						</tr>
						<tr>

							<td align="right">备注：</td>
							<td><input type="text" name="ioperateRemark" />
							</td>
							<td align="right" width="80px">装卸工：</td>
							<td colspan="2" width="300px">
								<!-- <input type="text" readonly="readonly" id="zhuangxie" name="ioperateStevedore"/>
                                <a class="qingkong">清空</a> --> <select
								class="zhuangxie" class="demo-default" name="zhuangxieGong">
									<c:forEach items="${workinglist}" var="work">
										<c:if test="${work.workingKindOfWork=='装卸工'}">
											<option value="${work.workingName}">${work.workingName}</option>
										</c:if>
									</c:forEach>
							</select>
							</td>
							<td align="right" width="150px">车牌号</td>
							<td><input type="text" id="chehao" name="ioperateTruckNum" />
							</td>
						</tr>
					</table>
				</div>
				<div class="queren">
					<%
						if(update!=0){
					%>
					<a id="update" style="cursor:pointer;"><i
						style="background: url(cangchu/img/zengjiahuowu.png);">订单修改</i> </a>
					<%
						}
					%>
					<a id="tijiaoOk" style="cursor:pointer;">提交</a>
				</div>
			</form>
		</div>
		<!--出库内容结束-->
		<!-- 当点击订单修改的时候触发 -->
		<script>
			$(function() {
				$("#update")
						.click(
								function() {
									var id = $("#seedId").val();
									var eoid = $("#exportoperateid").val();
									document.location.href = "${pageContext.request.contextPath}/inputSeed.do?flag=getXiangQing&ff=caozuoupdate&iseedId="
											+ encodeURI(encodeURI(id))
											+ "&eoid="
											+ encodeURI(encodeURI(eoid));
								});
			});
		</script>
	</div>
	<!--页面起始处结束-->
	<!-- 计算对应的货物的重量 -->
	<div class="modeal_bottom" id="modeal_weight"
		style="width:400px; margin:0px auto;">
		<div>
			<div>
				<div class="modeal_bottom_top" id="jisuan_modeal_top">
					<h3>重量计算</h3>
					<button id="close1">X</button>
				</div>
				<div class="modeal_bottom_middle">
					<p>
					<div id="jisuan_xuanze">
						<h3 style="text-align:center;">请选择计算类型</h3>
						<h4 class="item_jisuan">钢板计算</h4>
						<h4 class="item_jisuan">螺纹钢计算</h4>
						<h4 class="item_jisuan">H型钢计算</h4>
					</div>

					<!-- 计算钢板的选项 -->
					<div id="jisuan_gangban" style="display:none;">
						<table width="100%">
							<tr>
								<td>钢板高度：</td>
								<td><input type="text" id="gangban_gao" style="float:left;" />
								</td>
							</tr>
							<tr>
								<td>钢板宽度：</td>
								<td><input type="text" id="gangban_kuan"
									style="float:left;" />
								</td>
							</tr>
							<tr>
								<td>钢板长度：</td>
								<td><input type="text" id="gangban_chang"
									style="float:left;" />
								</td>
							</tr>
							<tr>
								<td>钢板密度：</td>
								<td><input type="text" id="gangban_midu"
									style="float:left;" />
								</td>
							</tr>
							<tr>
								<td><div class="modeal_bottom_bottom">
										<button class="jisuan_fanhui">返回</button>
									</div></td>
								<td><div class="modeal_bottom_bottom">
										<button id="jisuan_queding" style="float:left;">确定</button>
									</div></td>
							</tr>
						</table>
					</div>
					<!-- 计算螺纹钢的选项 -->
					<div id="jisuan_luowengang" style="display:none;">
						<table width="100%">
							<tr>
								<td>件数：</td>
								<td><input type="text" id="luowen_jian" style="float:left;" />
								</td>
							</tr>
							<tr>
								<td>理论重量：</td>
								<td><input type="text" id="luowen_zhong"
									style="float:left;" />
								</td>
							</tr>
							<tr>
								<td><div class="modeal_bottom_bottom">
										<button class="jisuan_luowen_fanhui">返回</button>
									</div></td>
								<td><div class="modeal_bottom_bottom">
										<button id="jisuan_luowen_queding" style="float:left;">确定</button>
									</div></td>
							</tr>
						</table>
					</div>
					<!-- H型钢的计算 -->
					<div id="jisuan_xinggang" style="display:none;">
						<table width="100%">
							<tr>
								<td>理论重量：</td>
								<td><input type="text" id="xinggang_zhong"
									style="float:left;" /></td>
							</tr>
							<tr>
								<td>支数：</td>
								<td><input type="text" id="xinggang_zhi"
									style="float:left;" />
								</td>
							</tr>
							<tr>
								<td>米数：</td>
								<td><input type="text" id="xinggang_mi" style="float:left;" />
								</td>
							</tr>
							<tr>
								<td><div class="modeal_bottom_bottom">
										<button class="jisuan_xinggang_fanhui">返回</button>
									</div></td>
								<td><div class="modeal_bottom_bottom">
										<button id="jisuan_xinggang_queding" style="float:left;">确定</button>
									</div></td>
							</tr>
						</table>
					</div>
					</p>
				</div>
				<div class="modeal_bottom_bottom">
					<button id="closes2">关闭</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modeal_bg"></div>
	<input type="hidden"
		value="${inputOperate.inputSeed.goods.goodsStandard.goodsStandardName }"
		id="gangban_guige_zhi" />
</body>
<!-- 如果此时的货物是钢板的时候，进行截取规格赋值给钢板中计算的文本框中 -->
<c:if test="${inputOperate.inputSeed.goods.goodsName=='钢板' }">
	<script>
		$(function() {
			//如果此时的货物的是钢板的时候，将钢板的规格，进行添加到计算钢的中间去
			var guige = $("#gangban_guige_zhi").val();
			var gangban_gao = guige.substr(0, guige.indexOf("*"));
			var gangban_kuan = guige.substr(guige.indexOf("*") + 1,
					guige.length).substr(
					0,
					guige.substr(guige.indexOf("*") + 1, guige.length).indexOf(
							"*"));
			$("#gangban_gao").val(gangban_gao);
			$("#gangban_kuan").val(gangban_kuan);
			//进行对规格字符串进行截取
		});
	</script>
</c:if>
<script>
	$(function() {

		//计算重量时候的调用
		//当单机计算的时候触发
		$("#jisuan").click(function() {
			$("#modeal_weight").css("display", "block")
			$(".modeal_bg").css("display", "block");
		});
		//当单机计算中的关闭的时候
		$("#closes2").click(function() {
			$("#modeal_weight").css("display", "none");
			$(".modeal_bg").css("display", "none");
		});
		$("#close1").click(function() {
			$("#modeal_weight").css("display", "none");
			$(".modeal_bg").css("display", "none");
		});
		//当点击计算中方的返回的时候触发
		$(".jisuan_fanhui").click(function() {
			$("#jisuan_xuanze").css("display", "block");
			$("#jisuan_gangban").css("display", "none");
		});
		//当单机螺纹钢计算中的返回的时候触发
		$(".jisuan_luowen_fanhui").click(function() {
			$("#jisuan_xuanze").css("display", "block");
			$("#jisuan_luowengang").css("display", "none");
		});
		//当单机型钢的计算中的返回的时候触发
		$(".jisuan_xinggang_fanhui").click(function() {
			$("#jisuan_xuanze").css("display", "block");
			$("#jisuan_xinggang").css("display", "none");
		});

		//当点击钢板中计算的确定的时候触发
		$("#jisuan_queding").click(
				function() {
					//钢板的计算
					var gao = $("#gangban_gao").val();//取出钢板的高度
					var kuan = $("#gangban_kuan").val();//取出钢板的宽度
					var chang = $("#gangban_chang").val();//取出钢板的长度
					var midu = $("#gangban_midu").val();//取出钢板的密度
					//计算相应的钢板的重量，赋值到重量的文本框中
					var result = parseFloat(gao) * parseFloat(kuan)
							* parseFloat(chang) * parseFloat(midu);
					$("#weight").val(result);
					$("#closes2").click();
				});

		//当单机螺纹钢中计算的确定的时候触发
		$("#jisuan_luowen_queding").click(function() {
			var jianshu = $("#luowen_jian").val();//取出螺纹钢中对应的件数
			var zhongliang = $("#luowen_zhong").val();//取出螺纹钢中对应的理论重量
			//计算相应的结果赋值给重量文本框中
			var result = parseFloat(jianshu) * parseFloat(zhongliang);
			$("#weight").val(result);
			$("#closes2").click();
		});
		//当单机H型钢中计算的确定的时候触发
		$("#jisuan_xinggang_queding").click(
				function() {
					var zhongliang = $("#xinggang_zhong").val();//取出型钢中的理论重量
					var zhi = $("#xinggang_zhi").val();//取出型钢中的支数
					var mi = $("#xinggang_mi").val();//取出型钢中的米数
					//进行计算，将计算的结果赋值给重量的文本框中
					var result = parseFloat(zhongliang) * parseFloat(zhi)
							* parseFloat(mi) / 1000;
					$("#weight").val(result);
					$("#closes2").click();
				});
		//当点击计算类型的时候
		$(".item_jisuan").click(function() {
			switch ($(this).index()) {
			case 1:
				$("#jisuan_xuanze").css("display", "none");
				$("#jisuan_gangban").css("display", "block");
				break;
			case 2:
				$("#jisuan_xuanze").css("display", "none");
				$("#jisuan_luowengang").css("display", "block");
				break;
			case 3:
				$("#jisuan_xuanze").css("display", "none");
				$("#jisuan_xinggang").css("display", "block");
				break;
			}
		});

		//当点击清空的时候
		$(".qingkong").click(function() {
			$("#zhuangxie").val("");
		});

		$(".zhuangxie").change(function() {
			var sumvalue = $("#zhuangxie").val();
			var zvalue = $(this).val();
			sumvalue += zvalue;
			$("#zhuangxie").val(sumvalue + ",");
		});

		//当点击二次作业的时候触发
		$("#erci").click(function() {
			$("#modeal").css("display", "block")
			$(".modeal_bg").css("display", "block");
		});

		//二次作业重量点击确定提交
		$("#erci_queding").click(function() {
			var weight = $("#erciweight").val(); //二次作业重量
			var zhongyan = /^\d+(\.\d+)?$|^$|^(\d+|\-){7,}$/;
			if (weight == "") {
				alert("二次作业重量不可以为空，请重新填写！");
				return;
			}
			if (zhongyan.test(weight) == false) {
				alert("请核对二次作业重量的填写！");
				return;
			}

			alert("可以提交了");

			$("#modeal").css("display", "none");
			$(".modeal_bg").css("display", "none");
		});

		$("#tijiaoOk").click(function() {
			//当点击提交的时候
			var weight = $("#weight").val(); //出库重量
			var numbers = $("#number").val(); //出库件数
			var tianche = $("#tianche").val(); //天车工
			var chehao = $("#chehao").val(); //车号
			var zhaungxie = $("#zhuangxie").val(); //装卸工

			var lisuan = $("#guobang_lisuan").children("input").val(); //判断是否理算

			var zhongyan = /^\d+(\.\d+)?$|^$|^(\d+|\-){7,}$/;
			if (lisuan == "理算") {
				if (weight == "") {
					alert("重量不可以为空，请重新填写！");
					return false;
				}
				if (zhongyan.test(weight) == false) {
					alert("请核对重量的填写！");
					return false;
				}
			}

			if (numbers == "") {
				alert("件数不能为空，请重新填写！");
				return false;
			}
			if (zhongyan.test(numbers) == false) {
				alert("请核对件数的填写");
				return false;
			}
			if (tianche == "") {
				alert("请填入天车工!");
				return false;
			}

			if (chehao == "") {
				alert("请填入车号！");
				return false;
			}
			if (zhaungxie == "") {
				alert("请填入装卸工！");
				return false;
			} else {

				if (confirm("你确定要提交吗？")) {
					$("#inputOperateOk").submit();
				}
			}

		});
	});

	$(".zhuangxie").selectize({
		maxItems : 3
	});

	//重量计算中拖动模态框
	var $div = $("#jisuan_modeal_top"); /* 绑定鼠标左键按住事件 */
	$div.bind("mousedown", function(event) { /* 获取需要拖动节点的坐标 */
		var offset_x = $("#modeal_weight")[0].offsetLeft; //x坐标
		var offset_y = $("#modeal_weight")[0].offsetTop; //y坐标         /* 获取当前鼠标的坐标 */        
		var mouse_x = event.pageX;
		var mouse_y = event.pageY; /* 绑定拖动事件 *//* 由于拖动时，可能鼠标会移出元素，所以应该使用全局（document）元素 */
		$(document).bind("mousemove", function(ev) {
			/* 计算鼠标移动了的位置 */
			var _x = ev.pageX - mouse_x;
			var _y = ev.pageY - mouse_y; /* 设置移动后的元素坐标 */
			var now_x = (offset_x + _x) + "px";
			var now_y = (offset_y + _y) + "px"; /* 改变目标元素的位置 */
			$("#modeal_weight").css({
				top : now_y,
				left : now_x
			});
		}); /* 当鼠标左键松开，接触事件绑定 */
		$(document).bind("mouseup", function() {
			$(this).unbind("mousemove");
		});

	});
</script>

</html>
