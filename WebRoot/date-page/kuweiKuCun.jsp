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
<link rel="stylesheet" href="css/bootstrap.css" />
<link rel="stylesheet" href="css/selectize.default.css">
<link rel="stylesheet" href="css/public.css" />
<link rel="stylesheet" type="text/css" href="css/fenye.css" />
<script src="http://libs.baidu.com/jquery/2.1.1/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/kuweiKuCun.js"></script>
<!--   <script src="../js/jquery.js"></script> -->
<script src="js/selectize.js"></script>
<!--  <script src="../js/index.js"></script> -->
<style type="text/css">
.selectize-control.demo-default {
	height: 34px;
}

caption {
	font-size: 20px;
	font-weight: 600;
	text-align: center;
	color: #000;
}

.updownPage {
	position: relative;
	width: 190px;
	height: 60px;
	margin: 0 auto;
	top: 0px;
}

.updownPage a {
	float: left;
	margin: 0 10px 0 10px;
}

.updownPage form {
	width: 350px;
	float: left;
}

.updownPage form input {
	width: 60px;
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
<%
	int x = 0;//批次修改的权限
	
	List<Powers> power = (List<Powers>)request.getSession().getAttribute("power");
	for(int i=0; i<power.size(); i++){
		if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("库位库存修改")){
	x++;
		}
	}
%>
<body onload="jiazaiLoad();">
	<input type="hidden" value="<%=x%>" id="piciupdate" />
	<script>
		//如果没有修改批次的权限的时候隐藏对应的编辑按钮
		$(function() {
			var piciupdate = $("#piciupdate").val();
			if (piciupdate == "0") {
				$(".table thead tr th").last().remove();
			}
		});
	</script>
	<!--内容起始处-->
	<div id="mainFrame" class="main">
		<div class="daohang">
			<ul>
				<i>当前位置：</i>
				<a style="cursor:pointer;">数据中心</a>
				<span>/</span>&nbsp;
				<a href="javascript:document.location.reload();">库位查询</a>
				<span>/</span>&nbsp;
			</ul>
		</div>
		<div class="container" style="margin-top: 15px;">
			<form action="">
				<div class="col-md-3 col-xs-6 form-group">
					<div class="input-group">
						<select id="kuwei" class="demo-default"
							onchange="selectKuweiInfo()">

						</select> <span class="input-group-addon">库位</span>
					</div>
				</div>
				<div class="col-md-4 col-md-offset-1 col-xs-6 form-group">
					<div class="input-group">
						<input type="text" id="huowu" class="form-control"
							placeholder="货物资料"> <span class="input-group-addon">货物资料</span>
					</div>
				</div>
				<a type="button" id="tijiao" class="btn btn-warning"
					style="float: left; margin-right: 15px;">提交</a>
			</form>
		</div>
		<!-- 以下为订单内容页面 -->
		<div class="container-fulide">
			<table class="table table-striped table-hover">
				<!-- <caption>库位明细表</caption> -->
				<thead style="font-size: 15px;">
					<tr>
						<th>库位名称</th>
						<th>货物名称</th>
						<th>货物规格</th>
						<th>货物材质</th>
						<th>货物属性</th>
						<th>货物产地</th>
						<th>剩余重量</th>
						<th>剩余件数</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody class="divAdd">

				</tbody>
				<!-- 修改模态框 -->
				<div id="menu2" class="tab-pane fade" style="position: fixed; top:15%; left:20%; width:600px; height: 400px; display: none; ">
					<div class="modal-content"  style="width:100%">
						<form class="form-horizontal" name="tarehouseGoodsForm" method="post"
							action="${pageContext.request.contextPath}/tarehouseGoods.do?flag=updateTarehouseGoods" id="TGoodsForm">
							<div class="modal-header">
								<h4 class="modal-title" id="myModalLabel">库位库存修改</h4>
							</div>
							<input type="hidden" id="TGoodsId" name="tgoodsId" />
							<div class="modal-body">
								<div class="form-group">
									<label for="name1" class="col-sm-3 control-label">剩余重量</label>
									<div class="col-sm-6" style="margin-top:-5px;">
										<input type="text" class="form-control" id="weight"
											name="tgoodsWeight">
									</div>
								</div>
							</div>
							<div class="modal-body">
								<div class="form-group">
									<label for="name1" class="col-sm-3 control-label">剩余件数</label>
									<div class="col-sm-6" style="margin-top:-5px;">
										<input type="text" class="form-control" id="number"
											name="tgoodsNumber">
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<a class="btn btn-primary closeOk" onclick="closeModeal()">关闭</a> <input
									class="btn btn-primary" type="button" id="TgoodsUpdate" value="提交" />
							</div>
						</form>
					</div>
				</div>
			</table>
			<!-- 保存页数 -->
			<input type="hidden" id="inputVal">
			<div class="page_show" style="margin-top:10px;">
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
		</div>
	</div>
	
	<div id="count_div">
		<table>
			<tr>
				<td>剩余重量：<b></b></td>
				<td>剩余件数：<b></b></td>
			</tr>
		</table>
	</div>
</body>

</html>
