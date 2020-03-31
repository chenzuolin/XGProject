<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>仓储管理系统-客户注册</title>
		<!--layui css-->
		<link rel="stylesheet" type="text/css" href="/XGProject/xxcc-sl/plugin/layui/css/layui.css" />
		<link rel="stylesheet" type="text/css" href="/XGProject/xxcc-sl/css/public.css" />

	</head>
	<c:if test="${iu==null }">
		<script>
			document.location.href = "/XGProject/interiorUser.do?flag=getGeRen";
		</script>
	</c:if>

	<body>
		<div class="layui-fluid">
			<form class="layui-form layui-form-pane" id="content">
				<fieldset>
					<legend>个人资料</legend>
					<div class="layui-row layui-col-space30">
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md4 ">
							<div class="layui-block">
								<button class="layui-btn" lay-submit lay-filter="bianji" id="bianji"><i class="layui-icon">&#xe642;</i>编辑资料</button>
							</div>
						</div>
					</div>
					<div class="layui-row layui-col-space30">
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md4 ">
							<label class="layui-form-label">姓名</label>
							<div class="layui-input-block">
								<input type="text" name="name" value='${iu.iuserName }' id="name" class="layui-input">
							</div>
						</div>
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md4 ">
							<label class="layui-form-label">性别</label>
							<div class="layui-input-block">
								<input type="text" name="sex" value="${iu.iuserSex }" id="sex" class="layui-input">
							</div>
						</div>
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md4 ">
							<label class="layui-form-label">联系电话</label>
							<div class="layui-input-block">
								<input type="text" name="tel" value="${iu.iuserTel }" id="tel" class="layui-input">
							</div>
						</div>
					</div>
					<div class="layui-row layui-col-space30">
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md4 ">
							<label class="layui-form-label">账号</label>
							<div class="layui-input-block">
								<input type="text" name="loginName" value='${iu.iuserLoginName }' id="loginName" class="layui-input">
							</div>
						</div>
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md4 ">
							<label class="layui-form-label">密码</label>
							<div class="layui-input-block">
								<input type="text" name="pwd" value="**********" id="pwd" class="layui-input">
								<input type="hidden" name="passwords" id="passwords" readonly="readonly" value='${iu.iuserPassword }' />
							</div>
						</div>
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md4 ">
							<label class="layui-form-label">部门</label>
							<div class="layui-input-block">
								<input type="text" name="bumen" value='${iu.classT.section==null?"无":iu.classT.section.sectionName }' id="bumen" class="layui-input">
							</div>
						</div>
					</div>

					<div class="layui-row layui-col-space30">
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md4 ">
							<label class="layui-form-label">班组</label>
							<div class="layui-input-block">
								<input type="text" name="banzu" value='${iu.classT==null?"无":iu.classT.className }' id="banzu" class="layui-input">
							</div>
						</div>
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md4 ">
							<label class="layui-form-label">职务</label>
							<div class="layui-input-block">
								<input type="text" name="zhiwu" value='${iu.interiorUserDuty.interiorUserDutyName }' id="zhiwu" class="layui-input">
							</div>
						</div>
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md4 ">
							<label class="layui-form-label">注册时间</label>
							<div class="layui-input-block">
								<input type="text" name="time" value='${iu.iuserCreateTime }' id="time" class="layui-input">
							</div>
						</div>
					</div>
				</fieldset>
			</form>
		</div>
		<div id="bianjiOpen" style="display: none; margin: 10px;">
			<form class="layui-form layui-form-pane">
				<div class="layui-form-item">
					<label class="layui-form-label">联系电话</label>
					<div class="layui-input-block">
						<input type="text" name="dianhua" value="${iu.iuserTel }" lay-verify="required|phone" id="dianhua" placeholder="请输入联系电话" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">原密码</label>
					<div class="layui-input-block">
						<input type="text" name="yuanpwd" lay-verify="required" id="yuanpwd" placeholder="请输入原密码" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">新密码</label>
					<div class="layui-input-block">
						<input type="text" name="newpwd" lay-verify="required" placeholder="请输入新密码" autocomplete="off" id="newpwd" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">确认新密码</label>
					<div class="layui-input-block">
						<input type="text" name="quenewpwd" lay-verify="required" placeholder="请输入确认新密码" autocomplete="off" id="quenewpwd" class="layui-input">
					</div>
				</div>
				<button class="layui-btn" lay-submit lay-filter="tijiao" id="tijiao" style="display: none;">立即提交</button>
			</form>
		</div>
	</body>
	<!--layui js-->
	<script src="/XGProject/xxcc-sl/plugin/layui/layui.js"></script>
	<script src="/XGProject/xxcc-sl/js/call-client.js"></script>
	<script>
		inputBg();
		layui.use(["jquery", 'form', 'layer'], function() {
			var $ = layui.jquery;
			var form = layui.form;
			var layer = layui.layer;
			$("#content input").attr("readonly", 'readonly');
			var pwd;
			form.on("submit(bianji)", function(obj) {
				var data = obj.field;
				pwd = data.passwords;
				//打开编辑弹出层
				layer.open({
					type: 1,
					anim: 4,
					closeBtn: 2,
					title: ['个人资料编辑', 'font-size:16px;'],
                	skin: 'layui-layer-blue',
					content: $("#bianjiOpen"),
					area: ["500px", "360px"],
					btn: ["确定提交", "关闭"],
					yes: function() {
						$("#tijiao").click();
					},
					btn2: function(index) {
						layer.close(index);
					}
				});
				return false;
			});
			form.on("submit(tijiao)", function(obj) {
				var data = obj.field;
				if(data.yuanpwd != pwd) {
					layer.msg("原密码输入错误！", {
						icon: 2,
						time: 3000,
						shade: 0.4
					});
					return false;
				}
				var yan = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$/;
				if(yan.test(data.newpwd) == false) {
					layer.msg("新密码必须是字母和数字的组合，并且长度为8-16位！", {
						icon: 2,
						time: 3000,
						shade: 0.4
					});
					return false;
				}
				if(data.newpwd != data.quenewpwd) {
					layer.msg("新密码和确认密码不一致，请重新输入！", {
						icon: 2,
						time: 3000,
						shade: 0.4
					});
					return false;
				}
				layer.confirm("确定提交吗？", {
					icon: 3,
					anim: 4,
					closeBtn: 2,
					title: ['系统提示', 'font-size:16px;'],
                	skin: 'layui-layer-blue',
				}, function() {
					//用ajax的方式进行提交
					$.ajax({
						type: "post",
						url: "/XGProject/interiorUser.do?flag=updatePassword",
						async: true,
						data: {
							"iuserPassword": data.newpwd,
							"iuserTel": data.dianhua
						},
						dataType: "text",
						success: function(data) {
							if(data.indexOf("修改成功") != -1) {
								layer.alert("修改成功！", {
									icon: 1,
									anim: 4,
									closeBtn: 2,
									title: ['系统提示', 'font-size:16px;'],
                					skin: 'layui-layer-blue',
								}, function() {
									layer.closeAll();
								});
							} else {
								layer.alert("修改失败！", {
									icon: 5,
									anim: 4,
									closeBtn: 2,
									title: ['系统提示', 'font-size:16px;'],
                					skin: 'layui-layer-blue',
								});
							}
						},
						error: function() {
							layer.alert("数据上传错误！", {
								icon: 2,
								anim: 4,
								closeBtn: 2,
								title: ['系统提示', 'font-size:16px;'],
                				skin: 'layui-layer-blue',
							});
						}
					});
				})
				return false;
			});
		});
	</script>

</html>