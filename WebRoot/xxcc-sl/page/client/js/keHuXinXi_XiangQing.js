layui.use(['jquery', 'form', 'layer', 'laydate'], function() {
	var $ = layui.jquery;
	var form = layui.form;
	var layer = layui.layer;
	var laydate = layui.laydate;

	laydate.render({
		elem: '#begin' //指定元素，开始时间
	});
	laydate.render({
		elem: '#finish' //指定元素，结束时间
	});

	//当打开层中的input获得焦点的时候
	$("#bianji input").focus(function() {
		$(this).css("background-color", "#5FB878");
	});

	//当打开层中的input失去焦点的时候
	$("#bianji input").blur(function() {
		$(this).css("background-color", "#FFFFFF");
	});

	//将显示内容中的文本框设置为只读
	$("#showC input").attr("readonly", 'readonly');

	form.on('submit(formDemo)', function(data) {
		var obj = data.field;
		$("#clientId").val(obj.clientId);
		$("#fuzeren").val(obj.fuzeren);
		$("#begin").val(obj.hetongBegin);
		$("#finish").val(obj.hetongFinish);
		$("#jiancheng").val(obj.jiancheng);
		$("#zhujifu").val(obj.zhujifu);
		$("#zhekou").val(obj.zhekou);
		$("#xinyong").val(obj.xinyong);

		layer.open({
			type: 1,
			title: ['编辑客户信息', 'font-size:16px;'],
			skin: 'layui-layer-blue',
			closeBtn: 2,
			anim: 4,
			content: $('#bianji'),
			area: ['500px', '500px'],
			btn: ['立即提交', '关闭'],
			yes: function(index, obj) {
				//点击立交提交的时候触发
				var fuzeren = $("#fuzeren").val();
				var begin = $("#begin").val();
				var finish = $("#finish").val();
				var jiancheng = $("#jiancheng").val();
				var zhujifu = $("#zhujifu").val();
				var zhekou = $("#zhekou").val();
				var clientId = $("#clientId").val();
				var xinyong = $("#xinyong").val();

				//判断负责人是否为空
				if(fuzeren == null || fuzeren == "" || fuzeren == undefined) {
					layer.alert('负责人不能为空！', {
						icon: 2,
						closeBtn: 2,
						anim: 4,
						title: ['系统提示', 'font-size:16px;'],
						skin: 'layui-layer-blue',
					});
					return false;
				}

				//判断合同起始日期是否为空
				if(begin == null || begin == "" || begin == undefined) {
					layer.alert('合同起始日期不能为空！', {
						icon: 2,
						closeBtn: 2,
						anim: 4,
						title: ['系统提示', 'font-size:16px;'],
						skin: 'layui-layer-blue',
					});
					return false;
				}

				//判断合同结束日期是否为空
				if(finish == null || finish == "" || finish == undefined) {
					layer.alert('合同结束日期不能为空！', {
						icon: 2,
						closeBtn: 2,
						anim: 4,
						title: ['系统提示', 'font-size:16px;'],
						skin: 'layui-layer-blue',
					});
					return false;
				}

				//判断单位简称是否为空
				if(jiancheng == null || jiancheng == "" || jiancheng == undefined) {
					layer.alert('单位简称不能为空！', {
						icon: 2,
						closeBtn: 2,
						anim: 4,
						title: ['系统提示', 'font-size:16px;'],
						skin: 'layui-layer-blue',
					});
					return false;
				}

				//判断助记符是否为空
				if(zhujifu == null || zhujifu == "" || zhujifu == undefined) {
					layer.alert('助记符不能为空！', {
						icon: 2,
						closeBtn: 2,
						anim: 4,
						title: ['系统提示', 'font-size:16px;'],
						skin: 'layui-layer-blue',
					});
					return false;
				}

				//判断折扣不能为空
				if(zhekou == null || zhekou == "" || zhekou == undefined) {
					layer.alert('折扣不能为空！', {
						icon: 2,
						closeBtn: 2,
						anim: 4,
						title: ['系统提示', 'font-size:16px;'],
						skin: 'layui-layer-blue',
					});
					return false;
				}
				/* 判断正浮点数 */
				var reg_zhekou = /^(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$/;
				//判断折扣不能为字符
				if(reg_zhekou.test(zhekou) == false) {
					layer.alert('折扣填写有误！', {
						icon: 2,
						closeBtn: 2,
						anim: 4,
						title: ['系统提示', 'font-size:16px;'],
						skin: 'layui-layer-blue',
					});
					return false;
				}
				if(xinyong == null || xinyong == "" || xinyong == undefined) {
					layer.alert("信用度不能为空！", {
						icon: 2,
						closeBtn: 2,
						anim: 4,
						title: ['系统提示', 'font-size:16px;'],
						skin: 'layui-layer-blue',
					});
					return false;
				}
				if(reg_zhekou.test(xinyong) == false) {
					layer.alert('信用度填写有误！', {
						icon: 2,
						closeBtn: 2,
						anim: 4,
						title: ['系统提示', 'font-size:16px;'],
						skin: 'layui-layer-blue',
					});
					return false;
				}
				layer.confirm("确定提交吗？", {
					icon: 3,
					anim: 4,
					closeBtn: 2,
					title: ['系统提示', 'font-size:16px;'],
					skin: 'layui-layer-blue',
				}, function(index) {
					$.ajax({
						type: "post",
						url: "/XGProject/client.do?flag=updateClient",
						data: {
							"clientHuman": fuzeren,
							"clientStartTime": begin,
							"clientFinishTime": finish,
							"clientAbbreviation": jiancheng,
							"clientSign": zhujifu,
							"clientAgio": zhekou,
							"clientId": clientId,
							"clientCredit": xinyong
						},
						async: false,
						dataType: "text",
						success: function(data) {
							var ok = data.indexOf("修改成功！");
							if(ok != -1) {
								layer.alert("修改成功！", {
									icon: 1,
									closeBtn: 2,
									anim: 4,
									title: ['系统提示', 'font-size:16px;'],
									skin: 'layui-layer-blue',
								}, function(index) {
									document.location.href = "/XGProject/xxcc-sl/page/client/keHuXinXi.jsp"
									layer.close(index);
								});
							} else {
								layer.alert("修改失败！", {
									icon: 1,
									closeBtn: 2,
									anim: 4,
									title: ['系统提示', 'font-size:16px;'],
									skin: 'layui-layer-blue',
								});
							}
						},
						error: function() {
							layer.alert("数据上传错误，修改失败！", {
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
			},
			btn2: function(index, obj) {
				//点击关闭的时候触发
				layer.close(index);
			}
		});
		return false;
	});
});