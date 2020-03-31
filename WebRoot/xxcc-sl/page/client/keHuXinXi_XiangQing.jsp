<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>仓储管理系统-客户信息详情</title>
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<!--layui css-->
		<link rel="stylesheet" type="text/css" href="/XGProject/xxcc-sl/plugin/layui/css/layui.css" />
		<link rel="stylesheet" href="/XGProject/xxcc-sl/css/public.css" />
		<style>
			#bianji {
				width: 350px;
				height: 380px;
				text-align: right;
				padding: 10px;
				position: relative;
				display: none;
				margin: 0 auto;
			}
		</style>
	</head>

	<body>
		<div class="layui-fluid">
			<form class="layui-form layui-form-pane" id="showC">
				<input type="hidden" value="${client.clientId }" name="clientId" class="layui-input" />
				<fieldset>
					<legend>客户信息</legend>
					<div class="layui-row layui-col-space30">
						<div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
							<label class="layui-form-label">登录名</label>
							<div class="layui-input-block">
								<input type="text" value="${client.clientLoginName }" name="loginName" id="loginName" class="layui-input">
							</div>
						</div>
						<div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
							<label class="layui-form-label">负责人</label>
							<div class="layui-input-block">
								<input type="text" name="fuzeren" value="${client.clientHuman }" class="layui-input">
							</div>
						</div>
						<div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
							<label class="layui-form-label">邮箱</label>
							<div class="layui-input-block">
								<input type="text" name="email" value="${client.clientEmail }" id="email" class="layui-input">
							</div>
						</div>
						<div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
							<label class="layui-form-label">联系电话</label>
							<div class="layui-input-block">
								<input type="text" name="tel" value="${client.clientTel }" id="tel" class="layui-input">
							</div>
						</div>
					</div>
				</fieldset>
				<br />
				<hr />
				<fieldset>
					<legend>合同信息</legend>
					<div class="layui-row layui-col-space30">
						<div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
							<label class="layui-form-label">合同号</label>
							<div class="layui-input-block">
								<input type="text" name="hetonghao" value="${client.clientContract }" id="hetonghao" class="layui-input">
							</div>
						</div>
						<div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
							<label class="layui-form-label">合同起始日期</label>
							<div class="layui-input-block">
								<input type="text" name="hetongBegin" value="${client.clientStartTime }" id="hetongBegin" class="layui-input">
							</div>
						</div>
						<div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
							<label class="layui-form-label">合同结束日期</label>
							<div class="layui-input-block">
								<input type="text" name="hetongFinish" value="${client.clientFinishTime }" id="hetongFinish" class="layui-input">
							</div>
						</div>
						<div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
							<label class="layui-form-label">折扣</label>
							<div class="layui-input-block">
								<input type="text" name="zhekou" value="${client.clientAgio }" class="layui-input">
							</div>
						</div>
					</div>
					<div class="layui-row layui-col-space30">
						<div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
							<label class="layui-form-label">信用度</label>
							<div class="layui-input-block">
								<input type="text" name="zhekou" value="${client.clientCredit }" class="layui-input">
							</div>
						</div>
					</div>
				</fieldset>
				<br />
				<hr />
				<fieldset>
					<legend>企业信息</legend>
					<div class="layui-row layui-col-space30">
						<div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
							<label class="layui-form-label">单位名称</label>
							<div class="layui-input-block">
								<input type="text" name="mingcheng" value="${client.clientFirmName }" id="mingcheng" class="layui-input">
							</div>
						</div>
						<div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
							<label class="layui-form-label">单位简称</label>
							<div class="layui-input-block">
								<input type="text" name="jiancheng" value="${client.clientAbbreviation }" class="layui-input">
							</div>
						</div>

						<div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
							<label class="layui-form-label">单位助记符</label>
							<div class="layui-input-block">
								<input type="text" name="zhujifu" value="${client.clientSign }" class="layui-input">
							</div>
						</div>
						<div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
							<label class="layui-form-label">单位地址</label>
							<div class="layui-input-block">
								<input type="text" name="addres" value="${client.clientAddress }" id="addres" class="layui-input">
							</div>
						</div>
					</div>
				</fieldset>
				<br />
				<div>
					<div class="layui-inline" style="width: 100%; text-align: left;">
						<button class="layui-btn" lay-submit="" lay-filter="formDemo" id="query">编辑</button>
						<a class="layui-btn layui-btn-warm" onclick="javascript:window.history.go(-1);" style="color:black;">返回</a>
					</div>
				</div>
			</form>
		</div>

		<!--编辑弹出窗口-->
		<div id="bianji">
			<form class="layui-form layui-form-pane" id="bianjiForm">
				<input type="hidden" name="clientId" value="" id="clientId" />
				<div class="layui-form-item">
					<label class="layui-form-label">负责人</label>
					<div class="layui-input-block">
						<input type="text" name="clientHuman" id="fuzeren" class="layui-input" />
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">合同起始日期</label>
					<div class="layui-input-block">
						<input type="text" name="clientStartTime" id="begin" class="layui-input" />
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">合同结束日期</label>
					<div class="layui-input-block">
						<input type="text" name="clientFinishTime" id="finish" class="layui-input" />
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">简称</label>
					<div class="layui-input-block">
						<input type="text" name="clientAbbreviation" id="jiancheng" class="layui-input" />
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">助记符</label>
					<div class="layui-input-block">
						<input type="text" name="clientSign" id="zhujifu" class="layui-input" />
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">折扣</label>
					<div class="layui-input-block">
						<input type="text" name="clientAgio" id="zhekou" class="layui-input" />
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">信用度</label>
					<div class="layui-input-block">
						<input type="text" name="xinyong" id="xinyong" class="layui-input" />
					</div>
				</div>
			</form>
			<hr class="layui-bg-orange" />
		</div>

		<!--layui js-->
		<script src="/XGProject/xxcc-sl/plugin/layui/layui.js"></script>
		<script src="/XGProject/xxcc-sl/js/call-client.js"></script>
		<script type="text/javascript">
			inputBg();
		</script>
		<!--引入自写js-->
		<script src="/XGProject/xxcc-sl/page/client/js/keHuXinXi_XiangQing.js"></script>
	</body>

</html>