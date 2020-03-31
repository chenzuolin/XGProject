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
<!--添加 favicon icon -->

<script type="text/javascript" src="cangchu/js/jquery1.9.0.min.js"></script>
<script src="cangchu/js/faqipanku.js"></script>

<!--引入自写的css样式-->
<link rel="stylesheet" type="text/css" href="cangchu/css/faqichuku.css" />

<link rel="stylesheet" type="text/css" href="cangchu/css/faqipanku.css" />

<link rel="stylesheet" href="css/font-awesome.css" />
</head>

<body onload="load()" ondragstart="return false">
	<!--内容起始处-->
	<div id="mainFrame" class="main">
		<div class="daohang">
			<ul>
				<i>当前位置：</i>
				<a style="cursor:pointer;">仓储业务</a>
				<span>></span>&nbsp;
				<a style="cursor:pointer;">新增盘库</a>
				<span>/</span>&nbsp;
			</ul>
		</div>
		<div class="input-group "
			style="position: absolute; right: 20%; top:11%; border:1px solid #888888; width:250px; height:26px;">
			<span class="input-group-addon fa fa-search" style="cursor:pointer; position:absolute; color:#888888; margin-left:5px; 
				margin-top: 3px; font-size: 19px;"
				id="search"></span> <input type="text" class="form-control"
				placeholder="搜索" id="sousu" style="width: 224px; height: 26px; padding-left:25px; border: 0px; background: transparent;
				outline:none;border:none;">

		</div>
		<script>
			$(function() {
				$("#sousu").keydown(function(event) {
					if (event.keyCode == 13) {
						$("#search").click();
					}
				});
				$("#search").click(function() {
					$(".content_middle table tbody").html("");
					$("#nowPage").val("1");
					selectGoods();
				});
			});
		</script>
		<!--中间起始-->
		<div class="middle_content">
			<form
				action="${pageContext.request.contextPath }/checks.do?flag=FaQiChecks"
				method="post" name="checksForm" id="pankuForm">
				<!-- 设置发起盘库的人 -->
				<input type="hidden"
					value="<%=request.getSession().getAttribute("iulistId")%>"
					name="interiorUserByCheckFounderMember" />
				<div class="kehu_xuanze">
					<!--选择盘点人-->
					<strong>盘库人：</strong> <select id="pandianren"
						name="interiorUserByCheckHuman">
					</select>
					<div class="querens">
						<a style="cursor:pointer;" id="panku_tijiao">提 交</a>
					</div>
				</div>
				<!--中间内容起始 -->
				<div class="content_middle">
					<table cellpadding="0" cellspacing="0">
						<thead>
							<tr>
								<th class="tr_border_left"><label for="quanxuan">全选</label>
									<input type="checkbox" id="quanxuan" />
								</th>
								<th>库位</th>
								<th>货物品类</th>
								<th>名称</th>
								<th>助记符</th>
								<th>规格</th>
								<th>材质</th>
								<th>属性</th>
								<th>产地</th>
								<th>重量</th>
								<th>件数</th>
								<th class="beizhu_width tr_border_right">备注</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
						<thead style="border-top:3px solid #3A70D7;border-bottom:0px;">
							<tr>
								<td colspan="12" class="last_tr" style="text-align:center;">
									<a id="loadgengduo" style="cursor:pointer;"
									onclick="selectGoods()">加载更多<input type="hidden"
										id="nowPage" value="1" /> </a></td>
							</tr>
						</thead>
					</table>
				</div>
				<!--content_middle结束-->
			</form>
		</div>
		<!--middle_content结束-->
	</div>
	<!--mainFrame结束-->

	<div class="modeal_bottom" id="modeal">
		<div>
			<div>
				<div class="modeal_bottom_top">
					<h3>盘库确认</h3>
					<button id="close">X</button>
				</div>
				<div class="modeal_bottom_middle">
					<table class="tab_tan" width="100%">
						<thead>
							<tr>
								<th>盘库人</th>
								<th>库位</th>
								<th>货物品类</th>
								<th>名称</th>
								<th>规格</th>
								<th>材质</th>
								<th>属性</th>
								<th>产地</th>
								<th>重量</th>
								<th>件数</th>
							</tr>
						</thead>

						<tbody>

						</tbody>
					</table>
				</div>
				<div class="modeal_bottom_bottom">
					<a id="queding">确定提交</a>
					<button id="closes">关闭</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modeal_bg"></div>
</body>

</html>
<%
	if (request.getSession().getAttribute("loginName") == null
	|| request.getSession().getAttribute("iulist") == null
	|| request.getSession().getAttribute("power") == null) {
%>
<script type="text/javascript">
	$(function() {
		$("html").html("");
	});
</script>
<%
	}
%>
<%
	int x = 0;
		
		List<Powers> power = (List<Powers>)request.getSession().getAttribute("power");
		for(int i=0; i<power.size(); i++){
	if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("新增盘库")){
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