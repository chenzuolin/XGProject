<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<!--引入自写的css样式-->
<link rel="stylesheet" type="text/css" href="cangchu/css/faqichuku.css" />
<link rel="stylesheet" type="text/css" href="cangchu/css/daichuli.css" />
<link rel="stylesheet" href="cangchu/cangchu/css/public.css">
<script type="text/javascript" src="cangchu/js/jquery1.9.0.min.js"></script>
<script type="text/javascript" src="cangchu/js/caozuodingdan_main.js"></script>
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

		/*$(".caozuo_zhankai").click(function() {
			var h = $(this).parents(".content_tab").height();
			if (h == 50) {
				$(this).parents(".content_tab").animate({
					"height" : "250px"
				}, 200);
			} else {
				$(this).parents(".content_tab").animate({
					"height" : "50px"
				}, 200);
			}
		});*/

		/*当单机查看详情的时候触发*/
		$(".content_tab table tr td").not("td:last-child").click(function() {

			if (confirm("确定要开始作业吗？")) {
				document.location.href = "caozuodingdan.html";
			}
		});
	});

	//跳转到入库操作页面
	function tiaozhuanruku(str) {
		$(function() {
			//获取总订单id
			var zongid = $(str).parents(".content_tab").find("tr").eq(0)
					.children("td").eq(1).text();
			//获取子订单id
			var ziid = $(str).parents(".content_tab").find("tr").eq(0)
					.children("td").find("#ziId").val();

			//获取操作订单id
			var caozuoid = $(str).parents(".content_tab").find("tr").eq(0)
					.children("td").find("#caozuoId").val();

			if (confirm("确定要开始作业吗？")) {
				// "${pageContext.request.contextPath}/checks.do?flag=ZhengZaiChecks&checkId="+
				// id
				document.location.href = "inputOperate.do?flag=selectBaoGuanChuli&caozuoid="
						+ caozuoid;
			}
		});
	}

	//跳转到出库操作页面
	function tiaozhuanchuku(str) {
		$(function() {
			//获取总订单id

			//获取操作订单id
			var caozuoid = $(str).parents(".content_tab").find("tr").eq(0)
					.children("td").find("#caozuoId").val();

			if (confirm("确定要开始作业吗？")) {
				// "${pageContext.request.contextPath}/checks.do?flag=ZhengZaiChecks&checkId="+
				// id
				document.location.href = "exportOperate.do?flag=ZhengZaiChuKu&eoperateId="
						+ encodeURI(encodeURI(caozuoid));
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
				document.location.href = "checks.do?flag=ZhengZaiChecks&checkId="
						+ encodeURI(encodeURI(id));
			}
		});
	}

	function tiaozhuannuoku(str) {
		$(function() {
			var id = $(str).parents(".content_tab").find("tr").eq(0).children(
					"td").eq(1).text();
			if (confirm("确定要开始作业吗？")) {
				// "${pageContext.request.contextPath}/checks.do?flag=ZhengZaiChecks&checkId="+
				// id
				document.location.href = "shift.do?flag=zhengzaiShift&shiftId="
						+ encodeURI(encodeURI(id))
						+ "&interiorUserByShiftExecutor="
						+ $("#dengluren").val();
			}
		});
	}
	
	function tiaozhuanduandao(str) {
		$(function() {
			var id = $(str).parents(".content_tab").find("tr").eq(0).children(
					"td").eq(1).text();
			if (confirm("确定要开始作业吗？")) {
				// "${pageContext.request.contextPath}/checks.do?flag=ZhengZaiChecks&checkId="+
				// id
				document.location.href = "duanDaoAction.do?flag=zhengzaiShift&shiftId="
						+ encodeURI(encodeURI(id))
						+ "&interiorUserByShiftExecutor="
						+ $("#dengluren").val();
			}
		});
	}
</script>

<body ondragstart="return false" onload="selectDingDan()">
	<!--内容起始处-->
	<div id="mainFrame" class="main">
		<!--头部当前位置-->
		<div class="daohang">
			<ul>
				<i>当前位置：</i>
				<a style="cursor:pointer;">仓储业务</a>
				<span>></span>&nbsp;
				<a style="cursor:pointer;">订单操作</a>
				<span>/</span>&nbsp;
			</ul>
		</div>
		<div class="daichuli_content">
			<div class="content_top">
				<select id="type_xuanze" onchange="selectDingDan()">
					<option value="出库订单">出库订单</option>
					<option value="入库订单">入库订单</option>
					<option value="盘库订单">盘库订单</option>
					<option value="挪库订单">挪库订单</option>
					<option value="短倒订单">短倒订单</option>
				</select> <img src="cangchu/img/shuaxin.png" id="shuxin" title="刷新" />
			</div>
			<div class="content_center">
				<div class="content_tab" title="开始作业">
					<table cellpadding="0" cellspacing="0" style="margin-top: 0px;">
						<tbody>
						</tbody>
					</table>
				</div>
			</div>
			<!--content_center结束-->
			<div class="content_bottom">
				<a style="cursor:pointer;">查看更多</a> <input type="hidden"
					id="rukuPage" value="1" /> <input type="hidden" id="chukuPage"
					value="1" /> <input type="hidden" id="pankuPage" value="1" /> <input
					type="hidden" id="nuokuPage" value="1" /> <input type="hidden"
					value="<%=request.getSession().getAttribute("iulistId")%>"
					id="dengluren" />
			</div>
		</div>
		<!--daichuli_content结束-->
	</div>
	<!--main结束-->
</body>

</html>
