inputBg();
layui.use(['jquery', 'form', 'layer'], function() {
	var $ = layui.jquery;
	var form = layui.form;
	var layer = layui.layer;

	//当单机打印的时候转换到对应的打印的页面
	$("#dayin").click(function() {
		document.location.href = "/XGProject/exportSeed.do?flag=getXiangQing&ff=dayin&eseedId=" + encodeURI(encodeURI($("#ziId").val()))+"&caozuoId="+encodeURI(encodeURI($("#czId").val()));
	});
	layer.tips('若产生下站费，点击此处收取相应的下站费，然后收取出库费用！', '#xiazhan', {
		tips: 1,
		time:6000
	});
	//当单机下站费按钮的时候打开对应的弹出层
	$("#xiazhan").click(function() {
		layer.open({
			type: 1,
			title: ['收取下站费', 'font-size:16px;'],
            skin: 'layui-layer-blue',
			closeBtn: 2,
			anim: 4,
			content: $('#xiazhanOpen'),
			area: ['500px', '600px'],
			btn: ['立即提交', '关闭'],
			yes: function(index, obj) {
				$("#formDemo").click();
			},
			btn2: function(index, obj) {
				//点击关闭的时候触发
				layer.close(index);
			}
		});
	});

	//查询下站费中选择的客户
	client("#clientByXzzhuanruclient");

	//当光标移除下站费单价的时候自动计算对应的下站费合计
	$("#danjia").blur(function() {
		var reg_zhekou = /^(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$/;
		if($(this).val() != null && $(this).val() != undefined && $(this) != "" && reg_zhekou.test($(this).val())) {
			var sum = parseFloat($(this).val()) * parseFloat($("#weight").val());
			$("#heji").val(sum);
		}
	});

	//加载下站费中的支付方式和票据类型
	zhifu('#xiazhifu');
	piaoju("#xiapiaoju", $("#xiazhifu").val(), 'text');

	//当下站费中的结算方式切换的时候触发
	form.on('select(xiajiesuan)', function(obj) {
		if(obj.value == "日结") {
			//查询对应的支付方式
			zhifu('#xiazhifu');
			//查询对应的票据类型
			piaoju('#xiapiaoju', $("#xiazhifu").val(), 'text');
			//当为日结的时候实收费用可用					
			$("#xiashishou").attr("disabled", false);
			//表单提交验证的属性开放
			$("#xiashishou").attr("lay-verify", "required|number");

		} else if(obj.value == "月结") {

			//为月结的时候支付方式和票据类型无
			$("#xiazhifu").html("<option value='无'>无</option>");
			$("#xiapiaoju").html("<option value='无'>无</option>");
			form.render('select');
			//实收费用文本框不可用
			$("#xiashishou").attr("disabled", 'disabled');
			//表单验证取消
			$("#xiashishou").attr("lay-verify", "");
		}
	});

	form.on('submit(xiaformDemo)', function(obj) {
		//用ajax的方式进行提交
		var data = obj.field;
		layer.confirm("确定提交吗？", {
			icon: 3,
			closeBtn: 2,
			anim: 4,
			title: ['系统提示', 'font-size:16px;'],
            skin: 'layui-layer-blue',
		}, function() {
			$.ajax({
				type: "post",
				url: "/XGProject/xiazhanfeiAction.do?flag=SaveXiaZhan",
				async: false,
				data: {
					"xadefinedtwo": data.xadefinedtwo,
					"xzdefinedthree": data.xzdefinedthree,
					"xadefinedfive": data.xadefinedfive,
					"clientByXzzhuanruclient": data.clientByXzzhuanruclient,
					"xzdanjia": data.danjia,
					"xzweight": data.weight,
					"xzcount": data.heji,
					"xzdefinedone": data.xiajiesuan,
					"xzzhifu": data.xiazhifu,
					"xzpiaoju": data.xiapiaoju,
					"xadefinedfour": data.xiashishou,
					"xzremark": data.xiabeizhu
				},
				dataType: "text",
				success: function(data) {
					var ok = data.indexOf("提交成功");
					if(ok != -1) {
						layer.alert("提交成功！", {
							icon: 1,
							anim: 4,
							closeBtn: 2,
							title: ['系统提示', 'font-size:16px;'],
			                skin: 'layui-layer-blue',
						}, function() {
							document.location.reload();
						});
					} else {
						layer.alert("提交失败！", {
							icon: 1,
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
						closeBtn: 2,
						anim: 4,
						title: ['系统提示', 'font-size:16px;'],
		                skin: 'layui-layer-blue',
					});
				}
			});
		});
		return false;
	});

	//查询对应的支付方式
	zhifu('#zhifu');
	//查询对应的票据类型
	piaoju('#piaoju', $("#zhifu").val(), 'value');
	//当支付方式切换的时候触发
	form.on('select(zhifu)', function(obj) {
		//查询对应的票据类型
		piaoju('#piaoju', obj.value);
	});

	//当结算方式下拉框切的时候
	form.on('select(jiesuan)', function(obj) {
		if(obj.value == "日结") {
			//查询对应的支付方式
			zhifu('#zhifu');
			//查询对应的票据类型
			piaoju('#piaoju', $("#zhifu").val(), 'value');
			//当为日结的时候实收费用可用					
			$("#shishou").attr("disabled", false);
			//表单提交验证的属性开放
			$("#shishou").attr("lay-verify", "required|number");

		} else if(obj.value == "月结") {

			//为月结的时候支付方式和票据类型无
			$("#zhifu").html("<option value='无'>无</option>");
			$("#piaoju").html("<option value='无'>无</option>");
			form.render('select');
			//实收费用文本框不可用
			$("#shishou").attr("disabled", 'disabled');
			//表单验证取消
			$("#shishou").attr("lay-verify", "");
		}
	});

	//表单提交
	form.on('submit(formDemo)', function(obj) {
		var data = obj.field;
		layer.confirm('如果产生下站费，请先收取下站费！确定提交吗？', {
			icon: 3,
			closeBtn: 2,
			anim: 4,
			title: ['系统提示', 'font-size:16px;'],
            skin: 'layui-layer-blue',
		}, function() {
			//用ajax的方式进行提交
			$.ajax({
				type: "post",
				url: "/XGProject/exportOperate.do?flag=ShouFei",
				async: false,
				data: {
					"eoperateId": data.eoperateId,
					"eoperateRealityCost": data.shishou,
					"eoperateClientAccounts": data.jiesuan,
					"eoperatePaymentFashion": data.piaoju,
					"ercishishou": data.ercishishou,
					"eoperateRemark": data.beizhu
				},
				dataType: "text",
				success: function(data) {
					var ok = data.indexOf("提交成功");
					if(ok != -1) {
						layer.alert("提交成功！", {
							icon: 1,
							closeBtn: 2,
							anim: 4,
							title: ['系统提示', 'font-size:16px;'],
			                skin: 'layui-layer-blue',
						}, function() {
							document.location.reload();
						});
					} else {
						layer.alert("提交失败！", {
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
		})
		return false;
	});

	//将显示内容的文本框改变为只读
	$('#showContent input').attr('readonly', 'readonly');

	//给关键字添加背景,突出显示
	$('#showContent label').each(function() {
		if($(this).text() == '时间' || $(this).text() == '订单有效期' ||
			$(this).text().indexOf("重量") != -1 ||
			$(this).text() == '调度员' ||
			$(this).text() == '操作员' ||
			$(this).text() == '作业件数' ||
			$(this).text() == '审核人' ||
			$(this).text() == '收费人' ||
			$(this).text().indexOf("费") != -1) {
			$(this).css({
				'color': '#009688',
			});
			$(this).next('div').find("input").css({
				'color': '#009688',
			});
		}
	});
});