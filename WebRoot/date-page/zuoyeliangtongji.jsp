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
<link rel="stylesheet" href="css/bootstrap-datetimepicker.min.css">
<link rel="stylesheet" href="css/public.css" />
<script src="http://libs.baidu.com/jquery/2.1.1/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/bootstrap-datetimepicker.js"></script>
<script src="js/bootstrap-datetimepicker.zh-CN.js"></script>
<link rel="stylesheet" type="text/css" href="css/fenye.css" />
<script type="text/javascript" src="js/zuoyeliangtongji.js"></script>
<style>
.panel-title a {
	font-size: 18px;
	font-weight: bold;
}

.panel-body span {
	font-size: 16px;
	font-weight: bolder;
}
</style>
</head>
<%
	int x = 0;
		
		List<Powers> power = (List<Powers>)request.getSession().getAttribute("power");
		for(int i=0; i<power.size(); i++){
	if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("个人工作量统计")){
		x++;
	}
		}
		
		String name = "";
		InteriorUser iu = (InteriorUser)request.getSession().getAttribute("iulist");
		name=iu.getIuserName();//保存登录人的名称
%>
<body onload="showContent()">
	<!--内容起始处-->
	<div id="mainFrame" class="main">
		<div class="daohang">
			<ul>
				<i>当前位置：</i>
				<a style="cursor:pointer;">数据中心</a>
				<span>/</span>&nbsp;
				<a href="javascript:document.location.reload();">工作量统计</a>
				<span>/</span>&nbsp;
			</ul>
		</div>
		<div class="container" style="margin-top: 15px;">
			<div class='col-md-2 col-xs-6'>
				<form>
					<div class="form-group">
						<div class="input-group">
							<input class="form-control" type="text" id="startTime"
								placeholder="开始日期" /> <span class="input-group-addon"><label
								class="glyphicon glyphicon-calendar" for="startTime"></label> </span>
						</div>
					</div>
			</div>
			<div class="col-md-2 col-xs-6">
				<div class="form-group">
					<div class='input-group date'>
						<input class="form-control" type="text" id="endTime"
							placeholder="结束日期" /> <span class="input-group-addon"><label
							class="glyphicon glyphicon-calendar" for="endTime"></label> </span>
					</div>
				</div>
			</div>
			<div class="col-md-2 col-xs-6" id="caozuoyuan">
				<div class="input-group form-group">
					<select class="form-control" id="zhiwu" onchange="showContent()">
						<option value="1">调度员</option>
						<option value="2">保管员</option>
						<option value="3">司磅员</option>
						<option value="4">审核员</option>
						<option value="5">收费员</option>
						<option value="6">天车工</option>
						<option value="7">装卸工</option>
					</select> <span class="input-group-addon">职务</span>
				</div>
			</div>
			<%
				if(x!=0){
			%>
			<script type="text/javascript">
					$(function(){
						$("#caozuoyuans").css("display","none");
						$("#caozuo").val("<%=name%>");
				});
			</script>
			<%
				}
			%>
			<div class="col-md-2 col-xs-6" id="caozuoyuans">
				<div class="input-group form-group">
					<input type="text" class="form-control" placeholder="操作员"
						id="caozuo"> <span class="input-group-addon">操作员</span>
				</div>
			</div>
			<div class="col-md-3 col-xs-6">
				<div class="input-group form-group">
					<select class="form-control" id="ddType" onchange="zhiwuxuanze()">
						<option value="出库订单">出库订单</option>
						<option value="入库订单">入库订单</option>
						<option value="过户订单">过户订单</option>
						<option value="挪库订单">挪库订单</option>
						<option value="短倒订单">短倒订单</option>
					</select> <span class="input-group-addon">订单类型</span>
				</div>
			</div>
			<a type="button" class="btn btn-warning"
				style="float: left; margin-right: 15px;" id="tijiao">提交</a>

			<%
				if(x==0){
			%>
			<a type="button" class="btn btn-danger"
				style="float: left; margin-right: 15px; margin-top:10px;" id="daochu">导出</a>
			<%
				}
			%>
			<script type="text/javascript">
				//当点击导出的时候触发
				$(function(){
					$("#daochu").click(function(){
						document.location.href="/XGProject/date-page/daochuzuoyeliangtongji.jsp";
					});
				});
			</script>
			</form>
		</div>
		<!-- 以下为订单内容页面 -->
		<div class="container">
			<table class="table table-bordered table-hover">
				<thead>
					<tr>
						<th>作业人员</th>
						<th>订单日期</th>
						<th>订单号</th>
						<th>订单类型</th>
						<th>作业重量</th>
						<th>作业件数</th>
					</tr>
				</thead>
				<tbody style="font-size: 14px;" id="neirong">

				</tbody>
			</table>
			<input type="hidden" value="1" id="rukupage" /> <input type="hidden"
				value="1" id="chukupage" /> <input type="hidden" value="1"
				id="guohupage" /> <input type="hidden" value="1" id="nuokupage" />
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
			<div class="navbar-fixed-bottom"
				style="min-width: 600px; max-width: 60%; position: fixed; bottom: 5px; left: 40%; font-weight: bold; z-index:-1">
				<table class="table table-bordered">
					<tr>
						<td align="right">作业人：</td>
						<td><span id="zuoyeren"></span></td>
						<td align="right">入库合计：</td>
						<td><span id="ruku">100</span>吨</td>
						<td align="right">出库合计：</td>
						<td><span id="chuku">100</span>吨</td>
						<td align="right">过户合计：</td>
						<td><span id="guohu">100</span>吨</td>
						<td align="right">挪库合计：</td>
						<td><span id="nuoku">100</span>吨</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</body>
<script>
	$("#startTime").datetimepicker({
		minView : "month",
		language : 'zh-CN',
		format : 'yyyy-mm-dd',
		autoclose : true,
		todayBtn : "linked",
		todayHighlight : true,
		pickerPosition : "bottom-left"
	}).on('changeDate', function(ev) {
		var starttime = $("#startTime").val();
		$("#endTime").datetimepicker('setStartDate', starttime);
		$("#startTime").datetimepicker('hide');
	});

	$("#endTime").datetimepicker({
		minView : "month",
		language : 'zh-CN',
		format : 'yyyy-mm-dd',
		autoclose : true,
		todayBtn : true,
		pickerPosition : "bottom-left"
	}).on('changeDate', function(ev) {
		var starttime = $("#startTime").val();
		var endtime = $("#endTime").val();
		$("#startTime").datetimepicker('setEndDate', endtime);
		$("#endTime").datetimepicker('hide');
	});
</script>

</html>
