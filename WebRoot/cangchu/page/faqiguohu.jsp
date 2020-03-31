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

<!--引入自写的css样式-->
<link rel="stylesheet" type="text/css" href="cangchu/css/faqichuku.css" />

<!--引入过户样式-->
<link rel="stylesheet" type="text/css" href="cangchu/css/faqiguohu.css" />

<!--发起过户js-->
<script type="text/javascript" src="cangchu/js/faqiguohu.js"></script>

<link rel="stylesheet" href="css/selectize.default.css">
<script src="js/selectize.js"></script>
</head>
<!-- selectize.js 单选插件 -->
<script>
	$('#zhuanchu').selectize();
</script>
<script>
	$('#zhuanru').selectize();
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
	int chaofa = 0;
						
		List<Powers> power = (List<Powers>)request.getSession().getAttribute("power");
		for(int i=0; i<power.size(); i++){
	if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("新增过户")){
		x++;
	}
	if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("无库存超发")){
		chaofa++;
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
<body onload="load()" ondragstart="return false">
	<input type="hidden" value="<%=chaofa %>" id="wukucun_chaofa"/>
	<!--内容起始处-->
	<div id="mainFrame" class="main">
		<div class="daohang">
			<ul>
				<i>当前位置：</i>
				<a style="cursor:pointer;">仓储业务</a>
				<span>></span>&nbsp;
				<a>新增过户</a>
				<span>/</span>&nbsp;
			</ul>
		</div>

		<div class="chuku_context">
			<strong class="yunshu_xinxi">过 户 信 息</strong>

			<!--form开始-->
			<!-- 查询转出客户,方法：QueryZhuanChu（） -->

			<form name="shiftStockForm"
				action="${pageContext.request.contextPath }/shiftStock.do?flag=saveShiftStock"
				method="post" id="guohuForm">
				<div class="kehu_xuanze">
					<!--如果是客户发起入库，那么将此div隐藏-->
					<strong style="float:left">转出客户：</strong>
					<div style="float:left;">
						<select id="zhuanchu" onchange="zhuanchudiaoyong()"
							style="width:250px;" class="demo-default"
							name="clientBySstockClientId">
						</select>
					</div>
				</div>
				<!--table开始-->
				<table class="guohu_tab_head">
					<tr>

						<td align="right" width="80px">提货单号：</td>
						<td>
							<div class="tihuo">
								<input type="text" id="tihuodanhao" name="sstockClientNumber" />
								<label>*</label> <img src="cangchu/img/success.png" width="15" />
							</div>
						</td>
						<td align="right">转入单位:</td>
						<td>
							<div class="zhuanru">
								<!--如果是客户发起入库，那么将此div隐藏-->
								<select id="zhuanru" style="width:250px;" class="demo-default"
									name="clientBySstockShiftToFirm">
								</select>
							</div>
						</td>
						<td align="right" width="80px">转库类型：</td>
						<td>
							<select name='sstockDefinedOne'>
								<option value='正常转库'>正常转库</option>
								<option value='直提转库'>直提转库</option>
								<option value='其它转库'>其它转库</option>
							</select>
						</td>
					</tr>
				</table>

				<!--tabhead结束-->

				<!--货物信息开始-->

				<div id="tab_context" class="tab_context">
					<strong>请 选 择 过 户 货 物</strong> <br />
					<table class="tab_bottom">
						<tr>
							<td colspan="9"><span id="goods">货物1</span></td>
						</tr>
						<tr>
							<td align="right">货物品类：</td>
							<td>
								<div>
									<select id="pinlei" class="pl" onchange="QueryMingCheng(this)">
										<option>无</option>
									</select> <label>*</label> <img src="cangchu/img/success.png "
										width="15" />
								</div>
							</td>
							<td align="right">货物名称：</td>
							<td>
								<div>
									<select id="mingcheng" class="mc" onchange="QueryGuiGe(this)">
										<option>无</option>
									</select> <label>*</label> <img src="cangchu/img/success.png" width="15" />
								</div>
							</td>
							<td align="right">货物规格：</td>
							<td>
								<div>
									<select id="guige" class="gg" onchange="QueryCaiZhi(this)">
										<option>无</option>
									</select> <label>*</label> <img src="cangchu/img/success.png" width="15" />
								</div>
							</td>
							<td align="right">货物材质：</td>
							<td>
								<div>
									<select id="caizhi" class="cz" onchange="QueryShuXing(this)">
										<option>无</option>
									</select> <label>*</label> <img src="cangchu/img/success.png" width="15" />
								</div>
							</td>
							<td rowspan="2" align="left" width="50px"><a id="delete"
								style="cursor:pointer;"> <img
									src="cangchu/img/delete_two.png" title="删除" width="25" /> </a>
							</td>
						</tr>
						<tr>
							<td align="right">货物属性：</td>
							<td>
								<div>
									<select id="shuxing" class="sx" onchange="QueryChanDi(this)">
										<option>无</option>
									</select> <label>*</label> <img src="cangchu/img/success.png" width="15" />
								</div>
							</td>
							<td align="right">货物产地：</td>
							<td>
								<div class="chandis">
									<select id="chandi" class="cd" name="goodss">
										<option>无</option>
									</select> <label>*</label> <img src="cangchu/img/success.png" width="15" />
									<div class='cc'></div>
								</div>
							</td>
							<td align="right">重量：</td>
							<td>
								<div>
									<input type="text" id="guohuzhongliang" name="weight" /> <label>*</label>
									<img src="cangchu/img/success.png" width="15" />
								</div>
							</td>
							<td align="right">备注：</td>
							<td><input type="text" name="remark" /></td>
						</tr>
					</table>
				</div>
				<!--tab_context结束-->
				<div class="queren">
					<%
						if(request.getSession().getAttribute("duoxuan")!=null && request.getSession().getAttribute("duoxuan").equals("duoxuanhuowu")){
					%>
					<a style="cursor:pointer;" id="xinzeng"><i>增加货物</i> </a>
					<%
						}
					%>
					<a id="guohutijiao" style="cursor:pointer;">提 交</a>
				</div>
			</form>
		</div>
		<!--出库内容结束-->
	</div>
	<!--页面起始处结束-->
	<!--当点击提交入库订单的时候触发-->
	<!--在向服务器提交数据的时候要讲隐藏域进行设置name，并且重新设置与form相同的name-->
	<div class="modeal_bottom" id="modeal">
		<div>
			<div>
				<div class="modeal_bottom_top">
					<h3>再次确认过户订单</h3>
					<button id="close">X</button>
				</div>
				<div class="modeal_bottom_middle">
					<p>
					<table class="tab_tan" width="100%">
						<tr>
							<td align="right">提货单号：</td>
							<td><strong id="guohudanhao_fu">18215190013</strong></td>
							<td align="right">转入单位：</td>
							<td><strong id="zhuanru_fu">18215190013</strong>
							</td>
						</tr>
					</table>
					<div id="tan_bottom">
						<table border="1" width="100%">
							<tr>
								<th>货物序号</th>
								<th>货物品类</th>
								<th>货物名称</th>
								<th>货物规格</th>
								<th>货物材质</th>
								<th>货物属性</th>
								<th>货物产地</th>
								<th>货物重量</th>
							</tr>
						</table>
					</div>
					</p>
				</div>
				<div class="modeal_bottom_bottom">
					<a id="guohuqueding">确定提交</a>
					<button id="closes">关闭</button>
				</div>
			</div>
		</div>

	</div>
	<div class="modeal_bg"></div>
</body>

</html>

