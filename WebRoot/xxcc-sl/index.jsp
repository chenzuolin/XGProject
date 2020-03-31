<%@page import="net.sf.json.JSONObject"%>
<%@page import="net.sf.json.JSONArray"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.xinggang.project.entity.InteriorUser"%>
<%@ page import="com.xinggang.project.entity.InteriorUserDuty"%>
<%@ page import="com.xinggang.project.entity.Powers"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<title>仓储管理系统</title>
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta http-equiv="Access-Control-Allow-Origin" content="*">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="format-detection" content="telephone=no">
		<link rel="icon" href="favicon.ico">
		<link rel="stylesheet" href="plugin/layui/css/layui.css">
		<link rel="stylesheet" href="plugin/font-awesome-4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" href="css/index.css" />
		<link rel="stylesheet" href="css/public.css" />
		<style>
			#leftnav li a i {
				color: #1E9FFF;
				margin-right: 15px;
			}
			
			#leftnav cite {
				font-weight: 540;
			}
			
			#leftnav dd {
				padding-left: 15px;
			}
			
			#shouye i:last-child {
				display: none;
			}
			
			.marg0 {
				margin: 0;
			}
			
			.layui-body {
				bottom: 0 !important;
			}
			
			.full {
				position: fixed;
				align-content: center;
				width: 100%;
				height: 100%;
				overflow: auto;
			}
			
			.logo {
				color: #fff;
				float: left;
				line-height: 60px;
				font-size: 17px;
				padding: 0 25px;
				text-align: center;
				width: 150px;
			}
			
			.hideMenu {
				float: left;
				margin: 15px 15px 0 0;
				font-size: 17px;
				text-align: center;
				padding: 3px 5px;
				color: #fff;
				background-color: #1AA094;
			}
			
			.hideMenu:hover {
				color: #fff;
			}
			
			.weather {
				color: #fff;
				float: left;
				margin: 15px 0 0 50px;
			}
			
			.time {
				float: left;
				margin: 15px 15px 0 0;
				font-size: 14px;
				text-align: center;
				padding: 4px 5px;
				color: #ffffff;
			}
		</style>
	</head>

	<%
	if (request.getSession().getAttribute("loginName") == null
		|| request.getSession().getAttribute("iulist") == null
		|| request.getSession().getAttribute("power") == null) {
	%>
	<script type="text/javascript">
		document.location.href = "/XGProject/cangchu/page/login.jsp";
		return null;
	</script>
	<%
		}
	%>
	<%
		String zhiwu ="";
		String sex = "";
		int daichuli = 0;
		int caozuodingdan = 0;
		int guobang = 0;
		int shenhe = 0;
		int pankushenhe = 0;
		int shoufei = 0;
		int updateshenpi = 0;
		
		List<Powers> power = (List<Powers>)request.getSession().getAttribute("power");
		InteriorUser iu = (InteriorUser)request.getSession().getAttribute("iulist");
		if(iu==null){
			zhiwu ="无";
		}else{
			zhiwu = iu.getInteriorUserDuty()==null?"无":iu.getInteriorUserDuty().getInteriorUserDutyName();
		}
		sex = iu.getIuserSex();
		if(power==null){
			response.sendRedirect("/XGProject/cangchu/page/login.jsp");			
		}
		JSONArray array = new JSONArray();
		if(power!=null){
			for(int i=0; i<power.size(); i++){
				JSONObject obj = new JSONObject();
				obj.put("functionName", power.get(i).getFunctions().getFunctionName());
				array.add(obj);
				if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("待处理订单")){
					daichuli++;
				}
				if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("订单操作")){
					caozuodingdan++;
				}
						//查询订单过磅的权限
				if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("订单过磅")){
					guobang++;
				}
						//查询订单审核的权限
				if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("订单审核")){
					shenhe++;
				}
						//查询盘库订单审核的权限
				if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("盘库审核")){
					pankushenhe++;
				}
						//查询订单收费的权限
				if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("订单收费")){
					shoufei++;
				}
				if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("订单修改审批")){
					updateshenpi++;
				}
			}
		}
	%>

	<body class="layui-layout-body" id="cont">
		<input type="hidden" value='<%=request.getSession().getAttribute("iulistId")%>' id="dengluren" />
		<input type="hidden" value="<%=sex%>" id="sex" />
		<input type="hidden" value="<%=zhiwu %>" id="zhiwu" />
		<p style="display:none;" id="power">
			<%=array.toString() %>
		</p>
		<div class="layui-layout layui-layout-admin">
			<!--头部内容-->
			<div class="layui-header header">
				<div class='layui-layou-left'>
					<a href="javascript:;" class="logo">鑫港库存管理系统</a>
					<i class="layui-icon layui-btn  layui-btn-small hideMenu">&#xe65a;</i>
					<!-- 天气信息 -->
					<div class="weather">
						<div id="tp-weather-widget"></div>
						<script>
							(function(T, h, i, n, k, P, a, g, e) {
								g = function() {
									P = h.createElement(i);
									a = h.getElementsByTagName(i)[0];
									P.src = k;
									P.charset = "utf-8";
									P.async = 1;
									a.parentNode.insertBefore(P, a)
								};
								T["ThinkPageWeatherWidgetObject"] = n;
								T[n] || (T[n] = function() {
									(T[n].q = T[n].q || []).push(arguments)
								});
								T[n].l = +new Date();
								if(T.attachEvent) {
									T.attachEvent("onload", g)
								} else {
									T.addEventListener("load", g, false)
								}
							}(window, document, "script", "tpwidget", "//widget.seniverse.com/widget/chameleon.js"))
						</script>
						<script>
							tpwidget("init", {
								"flavor": "slim",
								"location": "WX4FBXXFKE4F",
								"geolocation": "enabled",
								"language": "zh-chs",
								"unit": "c",
								"theme": "chameleon",
								"container": "tp-weather-widget",
								"bubble": "disabled",
								"alarmType": "badge",
								"color": "#FFFFFF",
								"uid": "U9EC08A15F",
								"hash": "039da28f5581f4bcb5c799fb4cdfb673"
							});
							tpwidget("show");
						</script>

					</div>
					<div class="time" id="now_date"></div>
				</div>
				<ul class="layui-nav  layui-layout-right top_menu">
					<li class="layui-nav-item lockcms" pc>
						<a href="javascript:;">
							<i class="fa fa-times-circle" aria-hidden="true" style="font-size: 1.2rem;"></i>&nbsp;
							<cite>退出</cite>
						</a>
					</li>
					<li class="layui-nav-item" pc>
						<ul class="layui-nav" lay-filter="demo">
							<li class="layui-nav-item">
								<a href="javascript:;" id="shandong"><i class="fa fa-commenting" aria-hidden="true" style="font-size: 1.2rem;"></i>&nbsp;
									<cite>消息<div class="layui-badge" id="message">0</div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</cite>
								</a>
								<dl class="layui-nav-child">
									<%
										if(daichuli!=0){
									%>
									<dd>
										<a href="javascript:;" data-url="/XGProject/xxcc-sl/page/home/dingDanDaiChuLi.jsp"><cite>待处理订单</cite><span style="color: red;" id="daichuli"> 0 </span>条</a>
									</dd>
									<%
										}
									%>
									<%
										if(caozuodingdan!=0){
									%>
									<dd>
										<a href="javascript:;" data-url="/XGProject/xxcc-sl/page/cc-caozuo/dingDanCaoZuo.jsp"><cite>订单操作</cite><span style="color: red;" id="dingdancaozuo"> 0 </span>条</a>
									</dd>
									<%
										}
									%>
									<%
										if(guobang!=0){
									%>
									<dd>
										<a href="javascript:;" data-url="/XGProject/xxcc-sl/page/cc-caozuo/dingDanGuoBang.jsp" class="changeSkin"><cite>订单过磅</cite><span id="dingdanguobang" style="color: red;"> 0 </span>条</a>
									</dd>
									<%
										}
									%>
									<%
										if(shenhe!=0){
									%>
									<dd>
										<a href="javascript:;" data-url="/XGProject/xxcc-sl/page/cc-caozuo/dingDanShenHe.jsp" class="signOut"><cite>订单审核</cite><span id="dingdanshenhe" style="color: red;"> 0 </span>条</a>
									</dd>
									<%
										}
									%>
									<%
										if(pankushenhe!=0){
									%>
									<dd>
										<a href="javascript:;" data-url="/XGProject/xxcc-sl/page/cc-caozuo/dingDanShouFei.jsp" class="signOut"><cite>盘库审核</cite><span id="pankushenhe" style="color: red;"> 0 </span>条</a>
									</dd>
									<%
										}
									%>
									<%
										if(shoufei!=0){
									%>
									<dd>
										<a href="javascript:;" data-url="/XGProject/xxcc-sl/page/cc-caozuo/dingDanShouFei.jsp" class="signOut"><cite>订单收费</cite><span id="dingdanshoufei" style="color: red;"> 0 </span>条</a>
									</dd>
									<%
										}
									%>
									<%
										if(updateshenpi!=0){
									%>
									<dd>
										<a href="javascript:;" data-url="/XGProject/xxcc-sl/page/home/shenPi.jsp"><cite>订单修改审批</cite><span style="color: red;" id="updateshenpi"> 0 </span>条</a>
									</dd>
									<%
										}
									%>
								</dl>
							</li>
						</ul>
					</li>
				</ul>
			</div>
			<!--左侧菜单-->
			<div class="layui-side layui-bg-black">
				<div class="layui-side-scroll" id="leftnav">
					<div style=" width:195px; height:130px;text-align: center; margin-top: 10px; cursor: pointer;" title="点击查看个人资料" id="geren">
						<img id="touxiang" src="img/woman.jpg" style="border: 5px solid #009688;" class="layui-circle" width="80" height="80">
						<p style="margin-top: 10px;">你好！<span style="color: #01AAED;"><%=request.getSession().getAttribute("loginName")%></span>, 欢迎登录</p>
					</div>
					<ul class="layui-nav layui-nav-tree" lay-filter="demo">
					</ul>
					<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
				</div>
			</div>
			<!--内容区-->
			<div class="layui-body layui-form">
				<div class="layui-tab  marg0" lay-filter="bodyTab" lay-allowClose="true">
					<ul class="layui-tab-title  topCard">
						<li class="layui-this" lay-id='' id="shouye">
							<i class="fa fa-align-justify"></i>
							<cite>后台首页</cite></li>
					</ul>
					<ul class="layui-nav closeBox layui-layout-right">
						<li class="layui-nav-item">
							<a href="javascript:;"><i class="fa fa-hand-pointer-o"></i><cite>页面操作</cite></a>
							<dl class="layui-nav-child">
								<dd>
									<a href="javascript:;" class="otherpage"><i class="fa fa-times"></i><cite>关闭其他</cite></a>
								</dd>
								<dd>
									<a href="javascript:;" class="allpage"><i class="fa fa-times-circle"></i><cite>关闭全部</cite></a>
								</dd>
							</dl>
						</li>
					</ul>
					<div class="layui-tab-content frameWindow">
						<div class="layui-tab-item layui-show">
							<iframe src="page/main.jsp"></iframe>
						</div>
					</div>
				</div>
			</div>
		</div>
		<script src="plugin/layui/layui.js"></script>
		<script src="js/DaoChu/jquery1.9.0.min.js"></script>
		<script src="datas/navs.js"></script>
		<script>
			layui.use(['jquery'], function() {
				var $ = layui.jquery;
				if($("#sex").val() == "男") {
					$("#touxiang").attr("src", "img/boy.jpg");
				} else {
					$("#touxiang").attr("src", "img/girl.jpg");
				}
				var data = JSON.parse($("#power").text());
				$.each(data, function(i, obj) {
					if(obj.functionName == "新增盘点") {
						obj.functionName = "盘点客户库存";
					}
					if(obj.functionName == "冻结和解冻") {
						obj.functionName = "冻结客户库存";
					}
					if(obj.functionName == "费用结算") {
						obj.functionName = "月结费用结算";
					}
					if(obj.functionName == "收费记录") {
						obj.functionName = "月结收费记录";
					}
					if(obj.functionName == "库存查询") {
						obj.functionName = "历史库存查询";
					}
					if(obj.functionName == "查看挪库订单") {
						obj.functionName = "挪库查询";
					}
					if(obj.functionName == "查看短倒订单") {
						obj.functionName = "短倒查询";
					}
					if(obj.functionName == "查看盘库订单") {
						obj.functionName = "盘库查询";
					}
					if(obj.functionName == "系统操作员管理") {
						obj.functionName = "操作人员";
					}
					if(obj.functionName == "非系统操作员管理") {
						obj.functionName = "装卸人员";
					}
					if(obj.functionName == "查看系统日志") {
						obj.functionName = "系统日志";
					}
					if(obj.functionName == "日结收费明细") {
						obj.functionName = "日收费明细";
					}
				});
				if($("#zhiwu").val() == "管理员") {
					var chushihuakehu = {
						"functionName": "初始化客户库存"
					};
					var chushihuakuwei = {
						"functionName": "初始化库位库存"
					};
					data.push(chushihuakehu);
					data.push(chushihuakuwei);
				}
				for(var i = 0; i < leftNav.length; i++) {
					if(leftNav[i].children.length > 0 && leftNav[i].children != null && leftNav[i].children != undefined) {
						for(var j = 0; j < leftNav[i].children.length; j++) {
							for(var x = 0; x < data.length; x++) {
								if(leftNav[i].children[j].title == data[x].functionName) {
									leftNav[i].children[j].show = true;
								}
							}
						}
					}
				}
			});

			layui.use(['jquery', 'element'], function() {
				var $ = layui.jquery;
				var element = layui.element;
				//当单机个人资料的时候触发
				$("#geren").click(function() {
					var tabIndex = false;
					$(".layui-tab-title.topCard li").each(function() {
						if($(this).find('cite').text() == '个人资料') {
							$(this).click();
							//如果页面已经打开，切换到相应的页面，并且跳出遍历循环。
							tabIndex = true;
							return;
						}
					});
					if(tabIndex) {
						return false;
					}
					var url = "/XGProject/xxcc-sl/page/home/geRenZiLiao.jsp";
					var ids = new Date().getTime();
					element.tabAdd('bodyTab', {
						title: '<i class="layui-icon">&#xe612;</i><cite>个人资料</cite>',
						content: "<iframe src='" + url + "' data-id=''></frame>",
						id: ids
					});
					$(this).click();
				});
			});

			function getNowFormatDate() {
				var date = new Date();
				var seperator1 = "-";
				var seperator2 = ":";
				var month = date.getMonth() + 1;
				var strDate = date.getDate();
				var day = date.getDay();
				if(month >= 1 && month <= 9) {
					month = "0" + month;
				}
				if(strDate >= 0 && strDate <= 9) {
					strDate = "0" + strDate;
				}

				var shi = date.getHours();
				if(parseInt(date.getHours()) < 10) {
					shi = "0" + date.getHours();
				}
				var fen = date.getMinutes();
				if(parseInt(date.getMinutes()) < 10) {
					fen = "0" + date.getMinutes();
				}
				var miao = date.getSeconds();
				if(parseInt(date.getSeconds()) < 10) {
					miao = "0" + date.getSeconds();
				}

				var currentdate = date.getFullYear() + "年" + month + "月" + strDate + "日 " +
					shi + "时" + fen + "分" + miao + "秒";

				var days = "";
				switch(day) {
					case 1:
						days = "星期一";
						break;
					case 2:
						days = "星期二";
						break;
					case 3:
						days = "星期三";
						break;
					case 4:
						days = "星期四";
						break;
					case 5:
						days = "星期五";
						break;
					case 6:
						days = "星期六";
						break;
					case 0:
						days = "星期日";
						break;
				}
				document.getElementById("now_date").innerHTML = currentdate + " " + days;
			}

			setInterval("getNowFormatDate()", 1000);
		</script>

		<script src="js/left-nav.js"></script>
		<script src="js/tab.js"></script>
		<script src='js/index.js'></script>
	</body>

</html>