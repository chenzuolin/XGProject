<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.xinggang.project.entity.InteriorUser"%>
<%@ page import="com.xinggang.project.entity.InteriorUserDuty"%>
<%@ page import="com.xinggang.project.entity.Powers"%>
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>

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
<link rel="stylesheet" type="text/css" href="cangchu/css/faqichuku.css" />
<link rel="stylesheet" type="text/css" href="cangchu/css/daichuli.css" />
<!--引入自写的css样式-->
<script type="text/javascript" src="cangchu/js/jquery1.9.0.min.js"></script>
<script type="text/javascript" src="cangchu/js/daichuli.js"></script>
<!-- <script type="text/javascript" src="cangchu/js/faqichuku.js"></script> -->
<style>
.showpici {
	position: absolute;
	border: 1px solid #F2F2F2;
	-moz-box-shadow: 5px 5px 5px #000;
	-webkit-box-shadow: 5px 5px 5px #000;
	box-shadow: 2px 2px 10px #000;
	border-top: 1px solid #888888;
	border-left: 1px solid #888888;
	display: none;
	top: 0;
	left: 0;
	z-index: 1000;
}

.showpici span {
	font-size: 14px;
	color: red;
}
</style>
</head>

<body onload="jiazaiLoad();">
	<!--内容起始处-->
	<div id="mainFrame" class="main">
		<!--头部当前位置-->
		<div class="daohang">
			<ul>
				<i>当前位置：</i>
				<a style="cursor:pointer">首页</a>
				<span>></span>&nbsp;
				<a href="javascript:document.location.reload();">待处理订单</a>
				<span>/</span>&nbsp;
			</ul>
		</div>
		<div class="daichuli_content">
			<div class="content_top">
				<select id="type_xuanze" onchange="jiazaiLoad();">
					<option value="出库订单">出库订单</option>
					<option value="入库订单">入库订单</option>
				</select>
				<div class="sousu">
					<input type="text" placeholder="车号" class="kehudanhao" /> <input
						type="text" placeholder="货主" class="huozhu" /> <input
						type="button" id="sousu_but" value="查询" />
				</div>
				<img src="cangchu/img/shuaxin.png" id="shuxin" title="刷新" />
			</div>
			<div class="content_center">
				<c:forEach items="${inputSeedlist }" var="seedlist" varStatus="v">
					<div class="content_tab">
						<table cellpadding="0" cellspacing="0">
							<tbody>
								<tr>
									<td onclick="tanchuAdd(this)" align="right"><strong>订单编号：</strong>
									</td>
									<td onclick="tanchuAdd(this)"><span>${seedlist.input.inputId
											}</span> <input type="hidden" id="ziDingdanId"
										value="${seedlist.iseedId }" />
									</td>
									<td onclick="tanchuAdd(this)" align="right"><strong>货主：</strong>
									</td>
									<td onclick="tanchuAdd(this)"><span>${seedlist.input.client.clientLoginName
											}</span></td>
									<td onclick="tanchuAdd(this)" align="right"><strong>客户单号：</strong>
									</td>
									<td onclick="tanchuAdd(this)"><span>${seedlist.input.inputClientNumber
											}</span></td>
									<td onclick="tanchuAdd(this)" align="right"><strong>有效期（天）：</strong>
									</td>
									<td onclick="tanchuAdd(this)"><span>${seedlist.input.inputDefinedOne
											}</span></td>
									<td onclick="tanchuAdd(this)" align="right"><strong>订单状态：</strong>
									</td>
									<td onclick="tanchuAdd(this)"><span style="color:red;">${seedlist.iseedOrderStatus
											}</span></td>
									<td rowspan="4" valign="top"><img
										src="cangchu/img/xiangxia.png" width="20" class="zhankai"
										style="display:block;margin-top:10px;" /></td>
								</tr>
								<tr>
									<td align="right"><strong>运输方式：</strong></td>
									<td><span>${seedlist.input.inputCarryType }</span></td>
									<td align="right"><strong>车号：</strong></td>
									<td><span>${seedlist.input.inputPlateNumber }</span></td>
									<td align="right"><strong>司机姓名：</strong></td>
									<td><span>${seedlist.input.inputDriverName }</span></td>
									<td align="right"><strong>司机电话：</strong></td>
									<td><span>${seedlist.input.inputDriverTel }</span></td>
									<td colspan="2" rowspan="4"><a class="aClass"
										href="${pageContext.request.contextPath}/inputOperate.do?flag=getAllFinally&ziId=${seedlist.iseedId }">详情</a>
									</td>
								</tr>
								<tr class="tr_bg">
									<td align="right" class="tr_border_left"><strong>货物品类：</strong>
									</td>
									<td><span>${seedlist.goods.goodsCategory.goodsCategoryName
											}</span></td>
									<td align="right"><strong>名称：</strong></td>
									<td><span>${seedlist.goods.goodsName }</span></td>
									<td align="right"><strong>规格：</strong></td>
									<td><span>${seedlist.goods.goodsStandard.goodsStandardName
											}</span></td>
									<td align="right"><strong>材质：</strong></td>
									<td class="tr_border_right"><span>${seedlist.goods.goodsQuality.goodsQualityName
											}</span></td>
								</tr>
								<tr class="tr_bg">
									<td align="right" class="tr_border_left_xia"><strong>属性：</strong>
									</td>
									<td><span>${seedlist.goods.goodsProperty.goodsPropertyName
											}</span></td>
									<td align="right"><strong>产地：</strong></td>
									<td><span>${seedlist.goods.goodsYieldly.goodsYieldlyName
											}</span></td>
									<td align="right"><strong>重量：</strong></td>
									<td><span>${seedlist.iseedShouldWeight }</span></td>
									<td align="right"><strong>发起时间：</strong></td>
									<td class="tr_border_right_xia"><span>${seedlist.input.inputCreateTime
											}</span></td>
								</tr>
							</tbody>
						</table>
					</div>
				</c:forEach>
			</div>
			<!--content_center结束-->
			<div class="content_bottom">
				<a id="chakanGenduo" style="cursor: pointer;">查看更多</a> <input
					type="hidden" value="1" id="chukupage" />
			</div>
		</div>
		<!--daichuli_content结束-->
	</div>
	<!--main结束-->
	<!--分配入库订单模态框-->
	<div class="daichuli_modeal" id="ruku_modeal">
		<form method="post" name="inputOperateForm" id="okForm"
			action="${pageContext.request.contextPath}/inputOperate.do?flag=addInputOperat">
			<!---模态框头部-->
			<input type="hidden" id="ziId" name="inputSeed" />
			<div class="modeal_top">
				<p>
					<strong> 入库订单分配</strong><strong class="close" id="close">X</strong>
				</p>
			</div>
			<!---模态框中间-->
			<div class="modeal_content">
				<div class="modeal_content_top">
					<strong>请选择操作员：</strong> <select id="caozuoyuan" class="baoguan"
						name="interiorUserByIoperateStoremanId">
						<option></option>
					</select>
				</div>
				<strong class="qingxuanzekuwei">请选择库区：</strong>
				<div class="kuqu" style="margin-top: 10px; padding: 10px; border: 1px solid #888888;">
				</div>
				<strong class="qingxuanzekuwei">请选择库位：</strong>
				<div class="modeal_content_content">
					<div class="content_left" id=content_left></div>
					<div class="content_content">
						<div class="content_content_zoudong" id="tableKuwei">
							<table>
								<tr>

								</tr>
							</table>
						</div>
					</div>
					<div class="content_right" id="content_right"></div>
				</div>
				<div class="guobang">
					<strong>请选择类型:</strong>
					<div>
						<input type="radio" value="过磅" name="ioperatePonderationTrue"
							id="guobang" /> <label for="guobang">过磅</label> <input
							type="radio" value="理算" name="ioperatePonderationTrue"
							id="lisuan" checked="checked" /> <label for="lisuan">理算</label>
					</div>
				</div>
				<div class="beizhud">
					<strong>备注：</strong>
					<textarea name="ioperateRemark"></textarea>
				</div>
			</div>
			<!--模态框底部-->
			<div class="modeal_bottom_bottom">
				<a id="daichuli_ruku_tijiao">确定提交</a> <a id="closes">关闭</a>
			</div>
		</form>
	</div>
	<!--分配入库订单结束-->


	<!---分配出库订单模态框-->
	<div class="daichuli_modeal" id="chuku_modeal">
		<form
			action="${pageContext.request.contextPath }/exportSeed.do?flag=DiaoDuFenPei"
			method="post" name="exportSeedForm" id="chukuForm">
			<!---模态框头部-->
			<input type="hidden" id="chukuZid" name="eseedId" /> <input
				type="hidden" id="chukuhuowu" value="" />
			<div class="modeal_top">
				<p>
					<strong> 出库订单分配</strong><strong class="close" id="chuku_close">X</strong>
				</p>
			</div>
			<!---模态框中间-->
			<div class="modeal_content" style="height:600px">
				<div class="modeal_content_top">
					<strong>请选择操作员：</strong> <select id="caozuoyuan"
						class="chukucaozuo" name="baoguan">
					</select>
				</div>
				<strong class="qingxuanzekuwei">请选择库位：</strong>
				<div class="modeal_content_content">
					<div class="content_left" id="chuku_content_left"></div>
					<div class="content_content">
						<div class="content_content_zoudong" id="chuku_kuwei">
							<table>
								<tr>
								</tr>
							</table>
						</div>
					</div>
					<div class="content_right" id="chuku_content_right"></div>
				</div>
				<div class="pici">
					<strong>请选择批次：</strong>
					<!-- 悬浮显示对应批次的剩余重量 -->
					<div class="showpici" id="showpici">
						<span></span>
					</div>
					<div class="pici_xuanze">
						<ul>

						</ul>
					</div>
				</div>
				<div class="guobang">
					<strong>请选择类型:</strong>
					<div>
						<input type="radio" value="过磅" name="lisuan" id="chuku_guobang" />
						<label for="chuku_guobang">过磅</label> <input type="radio"
							value="理算" name="lisuan" id="chuku_lisuan" checked="checked" />
						<label for="chuku_lisuan">理算</label>
					</div>
				</div>
				<div style="width:300px; margin-top:10px;">
					<strong>分配重量:</strong> <input type="text" name="fenpeizhongliang"
						id="feipeizhongliang" /> <input type="hidden" value=""
						id="yingchuzhogliang"> <input type="hidden" value=""
						id="shichuzhongliang" />
				</div>
				<div class="cbeizhud" style="margin-top:0px;">
					<strong>备注：</strong>
					<textarea name="beizhu"></textarea>
				</div>
			</div>
			<input type="hidden"
				value="<%=request.getSession().getAttribute("iulistId")%>"
				name="diaodu" />
			<!--模态框底部-->
			<div class="modeal_bottom_bottom">
				<a id="daichuli_chuku_tijiao">确定提交</a> <a id="chuku_closes">关闭</a>
			</div>
		</form>
	</div>
	<!--模态框背景-->
	<div class="modeal_bg"></div>
</body>
<script type="text/javascript">
	$(function() {
		//当点击查看更多时调用方法
		$("#chakanGenduo").click(function() {
			selectGengduo();
		});
	});

	function tianzhuanchuku(str) {
		$(function() {
			document.location.href = "exportOperate.do?flag=getDaiChuLiXQ&exportSeed="
					+ encodeURI(encodeURI($(str).children("input").val()));
		});
	}
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
		if(power!=null){
	for(int i=0; i<power.size(); i++){
	if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("待处理订单")){
		x++;
	}
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

