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
<html>

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
<script type="text/javascript" src="cangchu/js/faqichuku.js"></script>
<!--引入自写的css样式-->
<link rel="stylesheet" type="text/css" href="cangchu/css/faqichuku.css" />

<link rel="stylesheet" type="text/css" href="cangchu/css/daichuli.css" />

<script src="cangchu/js/daichuli.js"></script>

<style>
.content_tab {
	height: 50px;
	padding-bottom: 10px;
}

.tr_diaodu_bg {
	background-color: #f0f8ff;
}
</style>
</head>
<script>
	$(function() {
		$(".content_tab table .tr_diaodu_bg:first td").css({
			"border-top" : "5px solid #FFFFFF"
		});

		$(".caozuo_zhankai").click(function() {
			var h = $(this).parents(".content_tab").height();
			if (h == 50) {
				$(this).parents(".content_tab").animate({
					"height" : "150px"
				}, 200);
			} else {
				$(this).parents(".content_tab").animate({
					"height" : "50px"
				}, 200);
			}
		});

		/*当单机查看详情的时候触发*/
		$(".content_tab table tr td").not("td:last-child").click(function() {

			if (confirm("确定要开始作业吗？")) {
				document.location.href = "dingdanshenhe.html";
			}
		});
	});
</script>

<body onload="shenghepanku('load')">
	<!--内容起始处-->
	<div id="mainFrame" class="main">
		<!--头部当前位置-->
		<div class="daohang">
			<ul>
				<i>当前位置：</i>
				<a style="cursor:pointer;">仓储业务</a>
				<span>></span>&nbsp;
				<a href="javascript:document.location.reload();">盘库审核</a>
				<span>/</span>&nbsp;
			</ul>
		</div>
		<div class="daichuli_content">
			<div class="content_top">
				<div class="sousu">
					<input type="text" placeholder="请输入库位名称"
						style="margin-left: -60px;" id="kuwei" /> <input type="button"
						id="sousu_but" value="查询" onclick="shenghepanku('query')" />
				</div>
				<img src="cangchu/img/shuaxin.png" id="shuxin" title="刷新" />
			</div>

			<div class="content_center"></div>
			<!--content_center结束-->

			<div class="content_bottom">
				<a style="cursor:pointer;" onclick="shenghepanku('gengduo')">查看更多</a><input
					type="hidden"
					value="<%=request.getSession().getAttribute("iulistId")%>"
					id="dengluren" /><input type="hidden" value="1" id="page" />
			</div>
		</div>
		<!--daichuli_content结束-->
	</div>
	<!--main结束-->

</body>
<script type="text/javascript">
	var kuwei = "";
	function shenghepanku(str) {
		$(function() {
			if (str == 'query') {
				kuwei = $("#kuwei").val();
				$("#page").val("1");
				$(".content_center").html("");
			}
			if (str == 'load') {
				kuwei = $("#kuwei").val();
				$("#page").val("1");
				$(".content_center").html("");
			}
			$
					.ajax({
						type : "post",
						url : "checks.do?flag=getChecksWanCheng&dates="
								+ new Date().getTime(),
						async : true,
						dataType : "json",
						data : {
							"interiorUserByCheckHuman" : $("#dengluren").val(),
							"kuname" : kuwei,
							"pageNow" : $("#page").val()
						},
						success : function(obj) {
							if (str == 'gengduo' && obj[0].max == "maxs") {
								alert("已到底部！");
								return false;
							}
							if (obj == null) {
								$(".content_center")
										.append(
												"<div class='content_tab'><table  cellpadding='0' cellspacing='0' style='margin-top: 0px;'><tbody><tr>"
														+ "<td colspan='9' align='center'><strong>无订单</strong></td></tr></tbody></table></div>");
								return false;
							}
							if (obj[0].result != null) {
								$("#page").val(parseInt(obj[0].pageNow) + 1);
								for ( var i = 0; i < obj.length; i++) {
									$(".content_center")
											.append(
													"<div class='content_tab' title='开始作业'><table cellpadding='0' cellspacing='0' style='margin-top: 0px;'><tbody><tr>"
															+ "<td align='right'  onclick='showHidden(this)'><strong>订单编号：</strong></td>"
															+ "<td  onclick='showHidden(this)'><span>"
															+ obj[i].id
															+ "</span></td>"
															+ "<td align='right'  onclick='showHidden(this)'><strong>发起人：</strong></td>"
															+ "<td  onclick='showHidden(this)'><span>"
															+ obj[i].faqiren
															+ "</span></td>"
															+ "<td align='right'  onclick='showHidden(this)'><strong>发起时间：</strong></td>"
															+ "<td  onclick='showHidden(this)'><span>"
															+ obj[i].time
															+ "</span></td>"
															+ "<td align='right'  onclick='showHidden(this)'><strong>库位：</strong></td>"
															+ "<td  onclick='showHidden(this)'><span>"
															+ obj[i].kuwei
															+ "</span></td>"
															+ "<td rowspan='4' valign='top'><img"
															+ " src='cangchu/img/xiangxia.png' width='20'"
															+ " class='caozuo_zhankai' style='margin-top:10px' onclick='showHidden(this)' /></td>"
															+ "</tr>"
															+ "<tr class='tr_bg'>"
															+ "<td align='right' class='tr_border_left' onclick='tiaozhuanpanku(this)'><strong>货物品类：</strong>"
															+ "</td>"
															+ "<td onclick='tiaozhuanpanku(this)'><span>"
															+ obj[i].pinlei
															+ "</span></td>"
															+ "<td align='right' onclick='tiaozhuanpanku(this)'><strong>名称：</strong></td>"
															+ "<td onclick='tiaozhuanpanku()'><span>"
															+ obj[i].mingcheng
															+ "</span></td>"
															+ "<td align='right' onclick='tiaozhuanpanku(this)'><strong>规格：</strong></td>"
															+ "<td onclick='tiaozhuanpanku(this)'><span>"
															+ obj[i].guige
															+ "</span></td>"
															+ "<td align='right' onclick='tiaozhuanpanku(this)'><strong>材质：</strong></td>"
															+ "<td class='tr_border_right' onclick='tiaozhuanpanku(this)'><span>"
															+ obj[i].caizhi
															+ "</span></td>"
															+ "</tr>"
															+ "<tr class='tr_bg'>"
															+ "<td align='right' class='tr_border_left_xia' onclick='tiaozhuanpanku(this)'><strong>属性：</strong>"
															+ "</td>"
															+ "<td onclick='tiaozhuanpanku(this)'><span>"
															+ obj[i].shuxing
															+ "</span></td>"
															+ "<td align='right' onclick='tiaozhuanpanku(this)'><strong>产地：</strong></td>"
															+ "<td onclick='tiaozhuanpanku(this)'><span>"
															+ obj[i].chandi
															+ "</span></td><td align='right' onclick='tiaozhuanpanku(this)'><strong>盘库结果：</strong>"
															+ "</td><td onclick='tiaozhuanpanku(this)'><span>"
															+ obj[i].jianshu
															+ ""
															+ obj[i].danwei
															+ "</span></td>"
															+ "<td align='right' onclick='tiaozhuanpanku(this)'><strong>状态：</strong></td>"
															+ "<td onclick='tiaozhuanpanku(this)' class='tr_border_right_xia'><span>"
															+ obj[i].zhuangtai
															+ "</span></td></tr></tbody></table></div>");
								}
							} else {
								$(".content_center")
										.append(
												"<div class='content_tab'><table  cellpadding='0' cellspacing='0' style='margin-top: 0px;'><tbody><tr>"
														+ "<td colspan='9' align='center'><strong>无订单</strong></td></tr></tbody></table></div>");
							}
						},
						error : function() {
							document.location.href = "/XGProject/cangchu/page/login.jsp";
						}
					});
		});
	};

	function showHidden(str) {
		$(function() {
			var h = $(str).parents(".content_tab").height();
			if (h == 50) {
				$(str).parents(".content_tab").animate({
					"height" : "150px"
				}, 200);
			} else {
				$(str).parents(".content_tab").animate({
					"height" : "50px"
				}, 200);
			}
		});
	}

	function tiaozhuanpanku(str) {
		$(function() {
			var id = $(str).parents(".content_tab").find("tr").eq(0).children(
					"td").eq(1).text();
			if (confirm("确定要开始作业吗？")) {
				// "${pageContext.request.contextPath}/checks.do?flag=ZhengZaiChecks&checkId="+
				// id
				document.location.href = "checks.do?flag=ZhengZaiShenHe&checkId="
						+ encodeURI(encodeURI(id));
			}
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
		for(int i=0; i<power.size(); i++){
	if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("盘库审核")){
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