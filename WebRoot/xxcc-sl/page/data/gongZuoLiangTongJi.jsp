<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.xinggang.project.entity.InteriorUser"%>
<%@ page import="com.xinggang.project.entity.InteriorUserDuty"%>
<%@ page import="com.xinggang.project.entity.Powers"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>库存管理系统-工作量统计</title>
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<!--layui css-->
		<link rel="stylesheet" type="text/css" href="../../plugin/layui/css/layui.css" />
		<link rel="stylesheet" href="../../css/public.css" media="all">
		<!--layui js-->
		<script src="../../plugin/layui/layui.js"></script>

		<!--自写js-->
		<script src="js/gongZuoLiangTongJi.js"></script>
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

	<body>
		<div class="layui-fluid">
			<!--头部填写表单，搜索表单-->
			<fieldset>
				<legend>工作量统计</legend>
				<form class="layui-form layui-form-pane">
					<div class="layui-row layui-col-space30">

						<div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
							<label class="layui-form-label">开始时间</label>
							<div class="layui-input-block">
								<input type="text" name="begin" placeholder="请输入开始时间" id="begin" autocomplete="off" class="layui-input">
							</div>
						</div>

						<div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
							<label class="layui-form-label">结束时间</label>
							<div class="layui-input-block">
								<input type="text" name="finish" placeholder="请输入结束时间" id="finish" autocomplete="off" class="layui-input">
							</div>
						</div>
						<%
							if(x==0){
						%>
						<div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
							<label class="layui-form-label">操作人</label>
							<div class="layui-input-block">
								<input type="text" name="name" value="<%=name%>" placeholder="请输入操作人" id="name" autocomplete="off" class="layui-input">
							</div>
						</div>
						<%
							}else{
						%>
						<input type="hidden" name="name" value="<%=name%>" id="name">
						<%
							}
						%>
						<div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
							<button class="layui-btn" lay-submit lay-filter="formDemo" id="query">立即查询</button>
							<button type="button" class="layui-btn layui-btn-primary" onClick="$('#showContent').tableExport({type:'excel', separator:';', escape:'false'});">导出</button>
						</div>
					</div>
				</form>
			</fieldset>

			<!--主要内容显示容器-->
			<table id="showContent" lay-filter="demo" style="display: none;">
				<thead>
					<tr>
						<td>职务</td>
						<td>起始日期</td>
						<td>结束日期</td>
						<td>操作人</td>
						<td>入库量(吨)</td>
						<td>出库量(吨)</td>
						<td>过户量(吨)</td>
						<td>挪库量(吨)</td>
						<td>短倒量(吨)</td>
						<td>总合计(吨)</td>
					</tr>
				</thead>
				<tbody>

				</tbody>
			</table>

			<!--table操作工具条的demo-->
			<script type="text/html" id="barDemo">
				<a class="layui-btn layui-btn-sm" lay-event="detail"><span class="layui-icon">&#xe61d;</span>查看详情</a>
			</script>

			<!--分页显示容器-->
			<div id="paging" style="text-align: center;"></div>

			<hr class="layui-bg-red" />

			<!--查看详情弹出层-->
			<div id="jiaoseOpen" style="display: none;">
				<table id="jiaose" lay-filter="jiaose"></table>
			</div>
		</div>
		<script src="../../js/call-client.js"></script>
		<!-- 导出excel插件 -->
		<script src="../../js/DaoChu/tableExport.js"></script>
		<script src="../../js/DaoChu/jquery.base64.js"></script>
		<script src="../../js/DaoChu/jszip-utils.js"></script>
		<script src="../../js/DaoChu/xlsx.core.min.js"></script>
		<script>
			inputBg();
		</script>

	</body>

</html>