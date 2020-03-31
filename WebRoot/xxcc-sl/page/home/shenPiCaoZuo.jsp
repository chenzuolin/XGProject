<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.xinggang.project.entity.InteriorUser"%>
<%@ page import="com.xinggang.project.entity.InteriorUserDuty"%>
<%@ page import="com.xinggang.project.entity.Powers"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<title>仓储管理系统-审批操作</title>
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<link rel="stylesheet" href="/XGProject/xxcc-sl/plugin/layui/css/layui.css" media="all">
		<link rel="stylesheet" href="/XGProject/xxcc-sl/css/public.css" media="all">
		<style type="text/css">
			.layui-row {
				margin: 2rem 8rem;
			}
			
			.kuanDu {
				box-sizing: border-box;
				width: 5.5%;
				padding-left: 14px;
				display: inline-block;
			}
			
			.kuanDu1 {
				position: relative;
				top: -0.5rem;
				width: 25%;
				display: inline-block;
			}
			
			.kuanDu1 .layui-progress-bar {
				background: #E7E7E7;
			}
			
			.number {
				box-sizing: border-box;
				width: 3rem;
				height: 3rem;
				line-height: 38px;
				text-align: center;
				margin: 0 auto;
				font-size: 1rem;
				font-weight: 600;
				color: #fff;
				border-radius: 100%;
				border: 5px #fff solid;
				background: #CFCFCF;
			}
			
			.shenqingren {
				font-size: 15px;
				font-weight: 500;
			}
			
			.bg {
				background: #2F86F6 !important;
			}
			
			.bg1 {
				text-align: center;
				background: -webkit-linear-gradient(left, #2F86F6, #E7E7E7 85%) !important;
				background: -o-linear-gradient(left, #2F86F6, #E7E7E7 85%) !important;
				background: -moz-linear-gradient(left, #2F86F6, #E7E7E7 85%) !important;
				background: -mos-linear-gradient(left, #2F86F6, #E7E7E7 85%) !important;
				background: linear-gradient(left, #2F86F6, #E7E7E7 85%) !important;
			}
			
			#shenpi .layui-form {
				margin-top: 5rem;
			}
			
			#shenpi div.layui-form-item {
				width: 30rem;
				margin: 1rem auto;
			}
		</style>
		<script src="/XGProject/xxcc-sl/plugin/layui/layui.js" charset="utf-8"></script>
		<script src="/XGProject/xxcc-sl/js/call-client.js" charset="utf-8"></script>
	</head>

	<body>
		<input type='hidden' value="${ur.urid}" id="id" />
		<!--审批模态框-->
		<div id="shenpi" class="layui-fluid">
			<!--进度条-->
			<div class="layui-row">
				<div class="kuanDu">
					<div class="layui-inline">
						<div class="number bg">1</div>
						<span class="shenqingren">申请人</span>
					</div>
				</div>
				<div class="kuanDu1">
					<div class="layui-progress ">
						<div class="layui-progress-bar bg" lay-percent="100%"></div>
					</div>
				</div>
				<div class="kuanDu">
					<div class="layui-inline">
						<div class="number bg">2</div>
						<div class="shenqingren">一级审批</div>
					</div>
				</div>
				<div class="kuanDu1">
					<div class="layui-progress">
						<div class="layui-progress-bar bg1" lay-percent="100%"></div>
					</div>
				</div>
				<div class="kuanDu">
					<div class="layui-inline">
						<div class="number">3</div>
						<div class="shenqingren">二级审批</div>
					</div>
				</div>
				<div class="kuanDu1">
					<div class="layui-progress">
						<div class="layui-progress-bar" lay-percent="100%"></div>
					</div>
				</div>
				<div class="kuanDu">
					<div class="layui-inline">
						<div class="number">4</div>
						<div class="shenqingren">三级审批</div>
					</div>
				</div>
			</div>
			<c:if test="${(ur.urdefinedfour=='' || ur.urdefinedfour==null) && ur.urdefinedthree!='' && ur.urdefinedthree !=null }">
				<!--一级审批页面-->
				<form class="layui-form" action="" id="one">
					<input type="hidden" value="1" name="type" />
					<div class="layui-form-item">
						<label class="layui-form-label">申请内容：</label>
						<div class="layui-input-block">
							<textarea name="" class="layui-textarea" readonly="readonly">${ur.urfaqimiaoshu }</textarea>
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">审批：</label>
						<div class="layui-input-block" id="yes">
							<input type="radio" name="yes" checked="checked" value="同意" title="同意">
							<input type="radio" name="yes" value="不同意" title="不同意">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">审批人：</label>
						<div class="layui-input-block">
							<select name="man" id="man" lay-filter="man">
								<option value="">请选择审批人</option>
							</select>
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">审批意见：</label>
						<div class="layui-input-block">
							<textarea name="yijian" class="layui-textarea"></textarea>
						</div>
					</div>
					<div class="layui-form-item">
						<div class="layui-input-block" style="float:right">
							<button class="layui-btn" lay-submit lay-filter="btn" id="btn">立即提交</button>
							<button type="reset" class="layui-btn layui-btn-primary">重置</button>
						</div>
					</div>
				</form>
			</c:if>
			<c:if test="${(ur.urdefinedeight==null || ur.urdefinedeight=='') && ur.urdefinedseven!=null && ur.urdefinedseven!='' }">
				<!--给相应的导航加背景-->
				<script>
					layui.use("jquery", function() {
						var $ = layui.jquery;
						$(".number").each(function(i) {
							if(i < 3) {
								$(this).addClass("bg");
							}
						});
						$(".layui-progress-bar").each(function(i) {
							if(i < 2) {
								$(this).removeClass("bg1").addClass("bg");
							} else {
								$(this).addClass("bg1");
							}
						});
					});
				</script>

				<!--二级审批页面-->
				<form class="layui-form" action="" id="two">
					<input type="hidden" value="2" name="type" />
					<div class="layui-form-item">
						<label class="layui-form-label">申请内容：</label>
						<div class="layui-input-block">
							<textarea name="" class="layui-textarea" readonly="readonly">${ur.urfaqimiaoshu }</textarea>
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">审批：</label>
						<div class="layui-input-block" id="yes">
							<input type="radio" name="yes" checked="checked" value="同意" title="同意">
							<input type="radio" name="yes" value="不同意" title="不同意">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">审批人：</label>
						<div class="layui-input-block">
							<select name="man" id="erman" lay-filter="man">
								<option value="">请选择审批人</option>
							</select>
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">审批意见：</label>
						<div class="layui-input-block">
							<textarea name="yijian" class="layui-textarea"></textarea>
						</div>
					</div>
					<div class="layui-form-item">
						<div class="layui-input-block" style="float:right">
							<button class="layui-btn" lay-submit lay-filter="btn" id="btn">立即提交</button>
							<button type="reset" class="layui-btn layui-btn-primary">重置</button>
						</div>
					</div>
				</form>
			</c:if>
			<c:if test="${(ur.urdefinedtwelve=='' || ur.urdefinedtwelve==null) && ur.urdefinedeleven!=null && ur.urdefinedeleven!=''}">
				<script>
					layui.use("jquery", function() {
						var $ = layui.jquery;
						$(".number").each(function(i) {
							$(this).addClass("bg");
						});
						$(".layui-progress-bar").each(function(i) {
							$(this).removeClass("bg1").addClass("bg");
						});
					});
				</script>

				<!--三级审批页面-->
				<form class="layui-form" action="" id="three">
					<input type="hidden" value="3" name="type" />
					<div class="layui-form-item">
						<label class="layui-form-label">申请内容：</label>
						<div class="layui-input-block">
							<textarea name="" class="layui-textarea" readonly="readonly">${ur.urfaqimiaoshu }</textarea>
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">审批：</label>
						<div class="layui-input-block" id="yes">
							<input type="radio" name="yes" checked="checked" value="同意" title="同意">
							<input type="radio" name="yes" value="不同意" title="不同意">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">审批意见：</label>
						<div class="layui-input-block">
							<textarea name="yijian" class="layui-textarea"></textarea>
						</div>
					</div>
					<div class="layui-form-item">
						<div class="layui-input-block" style="float:right">
							<button class="layui-btn" lay-submit lay-filter="btn" id="btn">立即提交</button>
							<button type="reset" class="layui-btn layui-btn-primary">重置</button>
						</div>
					</div>
				</form>
			</c:if>

		</div>
	</body>
	<script>
		layui.use(['element', 'form', 'table', 'layer', 'jquery'], function() {
			var element = layui.element,
				form = layui.form,
				table = layui.table,
				layer = layui.layer,
				$ = layui.jquery;
			shenpiren("man");
			shenpiren("erman");

			//当点击提交的时候触发
			form.on('submit(btn)', function(obj) {
				var data = obj.field;
				layer.confirm("确定提交吗？", {
					icon: 3,
					closeBtn: 2,
					anim: 4,
					title: ['系统提示', 'font-size:16px;'],
					skin: 'layui-layer-blue',
				}, function(index) {
					//用ajax的方式进行提交
					var d;
					switch(data.type) {
						case "1":
							d = {
								"urid": $("#id").val(),
								"urdefinedfifteen": data.type,
								"urdefinedfour": data.yes,
								"urdefinedfive": data.yijian,
								"urdefinedseven": data.man
							};
							break;
						case "2":
							d = {
								"urid": $("#id").val(),
								"urdefinedfifteen": data.type,
								"urdefinedeight": data.yes,
								"urdefinednine": data.yijian,
								"urdefinedeleven": data.man
							};
							break;
						case "3":
							d = {
								"urid": $("#id").val(),
								"urdefinedfifteen": data.type,
								"urdefinedtwelve": data.yes,
								"urdefinedthirteen": data.yijian,
							};
							break;
					}
					$.ajax({
						type: "post",
						url: "/XGProject/updateRecordAction.do?flag=shenPiSubmit",
						async: false,
						data: d,
						dataType: "text",
						success: function(data) {
							if(data.indexOf("提交成功") != -1) {
								layer.alert("审批成功！", {
									icon: 1,
									closeBtn: 2,
									anim: 4,
									title: ['系统提示', 'font-size:16px;'],
									skin: 'layui-layer-blue',
								}, function() {
									var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
									parent.layer.close(index); //再执行关闭   
								});
							} else {
								layer.alert("审批失败！", {
									icon: 5,
									closeBtn: 2,
									anim: 4,
									title: ['系统提示', 'font-size:16px;'],
									skin: 'layui-layer-blue',
								});
							}
						},
						error: function() {
							layer.alert("数据上传错误！", {
								icon: 2,
								closeBtn: 2,
								anim: 4,
								title: ['系统提示', 'font-size:16px;'],
								skin: 'layui-layer-blue',
							});
						}
					});
					layer.close(index);
				});
				return false;
			});
		});

		//查询审批人
		function shenpiren(_this) {
			layui.use(['jquery', 'layer', 'form'], function() {
				var $ = layui.jquery;
				var layer = layui.layer;
				var form = layui.form;
				var man = "二级审批";
				if(_this == "erman") {
					man = "三级审批"
				}
				$("#" + _this).html("");
				//查询对应的审批人
				$.ajax({
					type: "post",
					url: "/XGProject/interiorUser.do?flag=getPowerMan&iuserDefinedOne=" + man,
					async: true,
					dataType: "json",
					success: function(data) {
						if(data[0].result == null) {
							$("#" + _this).html("<option value=''>无审批人</option>");
						} else {
							$.each(data, function(i, obj) {
								$("#" + _this).append("<option value='" + obj.name + "'>" + obj.name + "</option>");
							});
						}
						form.render('select');
					},
					error: function() {
						layer.alert("获取审批人数据错误！", {
							icon: 2,
							closeBtn: 2,
							anim: 4,
							title: ['系统提示', 'font-size:16px;'],
							skin: 'layui-layer-blue',
						});
					}
				});
			});
		}
	</script>

</html>