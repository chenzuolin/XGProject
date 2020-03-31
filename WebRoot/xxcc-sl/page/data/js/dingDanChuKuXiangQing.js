layui.use(['form', 'jquery', 'layer', 'table', 'laydate', 'element'], function() {
	var $ = layui.jquery;
	var form = layui.form;
	var layer = layui.layer;
	var table = layui.table;
	var laydate = layui.laydate;
	var element = layui.element;

	//给input添加只读属性
	$('#showContent input').attr('readonly', 'readonly');
	//给关键字添加背景,突出显示
	$('label').each(function() {
		if($(this).text() == '时间' || $(this).text() == '订单有效期' ||
			$(this).text().indexOf("重量") != -1 || $(this).text() == '调度员' ||
			$(this).text() == '操作员' || $(this).text() == '作业件数' ||
			$(this).text() == '审核人' || $(this).text() == '收费人' ||
			$(this).text().indexOf("费") != -1) {
			$(this).css({
				'color': '#009688',
			});
			$(this).next('div').find("input").css({
				'color': '#009688',
			});
		}
	});
	//当单机打印的时候转换到对应的打印的页面
	$("#dayin").click(function() {
		document.location.href = "/XGProject/exportSeed.do?flag=getXiangQing&p=list&ff=dayin&eseedId=" + encodeURI(encodeURI($("#kzid").val()));
	});

	//当单机出库完成的时候触发
	$("#chukuwancheng").click(function() {
		$.ajax({
			type: "post",
			url: "/XGProject/exportSeed.do?flag=ChuKuWanChengAjax",
			data: {
				"eseedId": $("#kzid").val()
			},
			dataType: "json",
			success: function(obj) {
				if(obj[0].result == "no") {
					layer.confirm("该订单正在操作，是否完成出库？", {
						icon: 3,
						closeBtn: 2,
						anim: 4,
						title: ['系统提示', 'font-size:16px;'],
						skin: 'layui-layer-blue',
					}, function(index) {
						chuKuWanChengAjax();
						layer.close(index);
					});
				} else {
					layer.confirm("确认出库完成吗？", {
						icon: 3,
						closeBtn: 2,
						anim: 4,
						title: ['系统提示', 'font-size:16px;'],
						skin: 'layui-layer-blue',
					}, function(index) {
						chuKuWanChengAjax();
						layer.close(index);
					});
				}
			},
			error: function() {
				document.location.href = "/XGProject/cangchu/page/login.jsp";
			}
		});
	});

	//当单机作废的时候触发
	$("#zuofei").click(function() {
		//渲染点击作废弹出的表格
		table.init('zuofeiModeal', {
			height: 380
		});
		layer.open({
			type: 1,
			title: ['操作订单作废', 'font-size:16px;'],
			skin: 'layui-layer-blue',
			closeBtn: 2,
			anim: 4,
			content: $('#zuofeiModeal'),
			area: ['1200px', '500px'],
			btn: ['关闭'],
			btn2: function(index, obj) {
				//点击关闭的时候触发
				layer.close(index);
			}
		});
	});

	//对作废表格中工具条的监听
	table.on('tool(zuofeiModeal)', function(obj) {
		var data = obj.data; //获得当前行数据
		var layEvent = obj.event; //获得 lay-event 对应的值
		var tr = obj.tr; //获得当前行 tr 的DOM对象

		if(layEvent == 'del') {
			layer.confirm("确定作废此操作订单吗？", {
				icon: 3,
				closeBtn: 2,
				anim: 4,
				title: ['系统提示', 'font-size:16px;'],
				skin: 'layui-layer-blue',
			}, function(index) {
				//用ajax的方式作废所选中的操作订单
				$.ajax({
					type: "post",
					url: "/XGProject/exportOperate.do?flag=DanGeZuoFei",
					async: false,
					data: {
						"eoperateId": data.bianhao
					},
					dataType: "text",
					success: function(objD) {
						var ok = objD.indexOf("作废成功");
						if(ok != -1) {
							layer.alert("作废成功！", {
								icon: 1,
								closeBtn: 2,
								anim: 4,
								title: ['系统提示', 'font-size:16px;'],
								skin: 'layui-layer-blue',
							}, function() {
								document.location.reload();
							});
						} else {
							layer.alert("作废失败！", {
								icon: 5,
								closeBtn: 2,
								anim: 4,
								title: ['系统提示', 'font-size:16px;'],
								skin: 'layui-layer-blue',
							});
						}
					},
					error: function() {
						layer.alert("上传数据错误，作废失败！", {
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
		}
	});

	//当单机订单时间修改的时候触发
	$("#timeupdate").click(function() {
		laydate.render({
			elem: '#indentTime',
			type: 'datetime'
		});

		layer.open({
			type: 1,
			title: ['订单时间修改', 'font-size:16px;'],
			skin: 'layui-layer-blue',
			closeBtn: 2,
			anim: 4,
			content: $('#timeUpdateOpen'),
			area: ['500px', '300px'],
			btn: ['确定提交', '关闭'],
			yes: function(index, obj) {

				if($("#indentTime").val() == null || $("#indentTime").val() == undefined || $("#indentTime").val() == "") {
					layer.alert("订单时间不能为空！", {
						icon: 2,
						closeBtn: 2,
						anim: 4,
						title: ['系统提示', 'font-size:16px;'],
						skin: 'layui-layer-blue',
					});
					return false;
				}
				//当单机确定提交的时候，用ajax的方式提交修改
				layer.confirm("确定修改吗？", {
					icon: 3,
					closeBtn: 2,
					anim: 4,
					title: ['系统提示', 'font-size:16px;'],
					skin: 'layui-layer-blue',
				}, function(index) {
					$.ajax({
						type: "post",
						url: "/XGProject/export.do?flag=UpdateTime",
						async: false,
						data: {
							"exportId": $("#timeUId").val(),
							"exportReateTime": $("#indentTime").val()
						},
						dataType: "text",
						success: function(data) {
							var ok = data.indexOf("修改成功");
							if(ok != -1) {
								layer.alert("修改成功！", {
									icon: 1,
									closeBtn: 2,
									anim: 4,
									title: ['系统提示', 'font-size:16px;'],
									skin: 'layui-layer-blue',
								}, function(index) {
									document.location.reload();
								});
							} else {
								layer.alert("修改失败！", {
									icon: 5,
									closeBtn: 2,
									anim: 4,
									title: ['系统提示', 'font-size:16px;'],
									skin: 'layui-layer-blue',
								});
							}
						},
						error: function() {
							layer.alert("数据上传错误,修改失败！", {
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
	});

	//当点击订单修改的时候触发
	$("#update").click(function() {
		//渲染点击订单修改弹出的表格
		table.init('updatecaiwuModeal', {});
		table.init('updatecangchuModeal', {});

		//判断登录人是否具有仓储修改和财务修改的权限，不同的显示
		if($("#bgupdate").val() != "0" || $("#ddupdate").val() != "0") {
			$(".layui-tab-content .layui-tab-item:first").addClass("layui-show");
		} else if($("#cwupdate").val() != "0") {
			$(".layui-tab-content .layui-tab-item:last").addClass("layui-show");
		}

		var indexu = layer.open({
			type: 1,
			title: ['出库订单修改', 'font-size:16px;'],
			skin: 'layui-layer-blue',
			closeBtn: 1,
			anim: 1,
			content: $('#updateOpen'),
			area: ["1300px", '600px'],
			resize: true,
			btn: ['关闭打开层'],
			btn2: function(index, obj) {
				layer.close(index);
			}
		});
		layer.full(indexu);
	});

	var shenpi = $("#updateshenpi").val(); //获得修改审批的权限是否具有
	var ddupdate = $("#ddupdate").val(); //获得调用修改的权限
	var bgupdate = $("#bgupdate").val(); //获得保管修改的权限

	//对仓储修改表格进行监听
	table.on('tool(updatecangchuModeal)', function(obj) {
		var data = obj.data; //获得当前行数据
		var layEvent = obj.event; //获得 lay-event 对应的值
		var tr = obj.tr; //获得当前行 tr 的DOM对象
		if(layEvent == "edit") {
			//进行编辑，打开对应的编辑弹出层
			//判断是否具有修改审批权限，如果有审批权限则不用打开审批填写弹框

			//调用查询执行出库人员的函数
			zhixingchuku();
			//调用查询库位的函数
			selectchukukuwei($("#goodsId").val());
			//调用查询天车工的函数
			tianchegong();

			$("#ccbianhao").val(data.bianhao);
			$("#zhixingren option").each(function() {
				if($(this).text() == data.zhixingren) {
					$(this).parent("select").val($(this).val());
					form.render('select');
					return;
				}
			});
			$("#guoli").val(data.guoli);
			$("#kuwei option").each(function() {
				if($(this).text() == data.kuwei) {
					$(this).parent("select").val($(this).val());
					form.render('select');
					return;
				}
			});
			$("#weight").val(data.zhongliang);
			$("#number").val(data.jianshu);
			$("#tianche option").each(function() {
				if($(this).text() == data.tianche) {
					$(this).parent("select").val($(this).val());
					form.render('select');
					return;
				}
			});
			$("#zhuangxie").val(data.zhuangxie);

			if(shenpi != "0") {
				layer.open({
					type: 1,
					title: ['出库订单修改', 'font-size:16px;'],
					skin: 'layui-layer-blue',
					closeBtn: 2,
					anim: 4,
					content: $('#cangchuUpdate'),
					area: ["600px", '700px'],
					btn: ['确定提交', '关闭'],
					yes: function(index, obj) {
						$("#cangchuTijiao").click();
					},
					btn2: function(index, obj) {
						layer.close(index);
					}
				});
			} else {
				//查询是否有订单修改提交的申请，并且是通过的
				$.ajax({
					type: "post",
					url: "/XGProject/updateRecordAction.do?flag=goShenQingPanDuan",
					data: {
						"urcaozuoid": data.bianhao
					},
					dataType: "json",
					success: function(obj) {
						//如果没有订单审批，则进行提交订单修改审批的申请
						if(obj[0].result == "no") {
							$("#urcaozuoid").val(data.bianhao);
							layer.open({
								type: 1,
								title: ['出库订单修改申请', 'font-size:16px;'],
								skin: 'layui-layer-blue',
								closeBtn: 2,
								anim: 4,
								content: $('#updateShenpi'),
								area: ["600px", '300px'],
								btn: ['确定提交', '关闭'],
								yes: function(index, obj) {
									$("#shengpiTijiao").click();
								},
								btn2: function(index, obj) {
									layer.close(index);
								}
							});
						} else {
							if(ddupdate == "0") {
								$("#zhixingren").attr("disabled", "disabled");
								$("#zhixingren").attr("lay-verify", "");
								$("#guoli").attr("disabled", "disabled");
								$("#guoli").attr("lay-verify", "");
								$("#kuwei").attr("disabled", "disabled");
								$("#kuwei").attr("lay-verify", "");
								$("#cangchuUpdate .layui-form-checkbox").prev("input[type=checkbox]").each(function() {
									$(this).attr("disabled", "disabled");
								});
								form.render('select');
								form.render('checkbox');
							}
							if(bgupdate == "0") {
								$("#weight").attr("disabled", "disabled");
								$("#weight").attr("lay-verify", "");
								$("#number").attr("disabled", "disabled");
								$("#number").attr("lay-verify", "");
								$("#tianche").attr("disabled", "disabled");
								$("#tianche").attr("lay-verify", "");
								$("#zhuangxie").attr("disabled", "disabled");
								$("#zhuangxie").attr("lay-verify", "");
								form.render('select');
							}

							layer.open({
								type: 1,
								title: ['出库订单修改', 'font-size:16px;'],
								skin: 'layui-layer-blue',
								closeBtn: 2,
								anim: 4,
								content: $('#cangchuUpdate'),
								area: ["600px", '700px'],
								btn: ['确定提交', '关闭'],
								yes: function(index, obj) {
									$("#cangchuTijiao").click();
								},
								btn2: function(index, obj) {
									layer.close(index);
								}
							});
						}
					},
					error: function() {
						layer.alert("获取修改审批数据错误！", {
							icon: 2,
							anim: 4,
							closeBtn: 2,
							title: ['系统提示', 'font-size:16px;'],
							skin: 'layui-layer-blue',
						});
					}
				});
			}
		}
	});

	//当点击修改审批申请提交的时候触发
	form.on('submit(shengpiTijiao)', function(obj) {
		var data = obj.field;
		layer.confirm("确定提交吗？", {
			icon: 3,
			anim: 4,
			closeBtn: 2,
			title: ['系统提示', 'font-size:16px;'],
			skin: 'layui-layer-blue',
		}, function() {
			$.ajax({
				type: "post",
				url: "/XGProject/updateRecordAction.do?flag=updateFaQi",
				async: false,
				data: {
					"urziid": data.urziid,
					"urzongid": data.urzongid,
					"urupdatetype": data.urupdatetype,
					"urcaozuoid": data.urcaozuoid,
					"urfaqimiaoshu": data.miaoshu,
					"urupdateremark": data.urupdateremark,
					"urdefinedtwo": data.huozhu,
					"urdefinedthree":data.shenpiren
				},
				dataType: "text",
				success: function(data) {
					var ok = data.indexOf("发起成功");
					if(ok != -1) {
						layer.alert("发起成功！", {
							icon: 1,
							anim: 4,
							closeBtn: 2,
							title: ['系统提示', 'font-size:16px;'],
							skin: 'layui-layer-blue',
						}, function() {
							layer.closeAll();
						});
					} else {
						layer.alert("发起失败！", {
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
		});
		return false;
	});

	//当仓储的修改提交的时候触发
	form.on('submit(cangchuTijiao)', function(obj) {
		var data = obj.field;

		var check = $("#cangchuUpdate .layui-form-checkbox");
		var len = check.length;
		var picis = [];
		var x = 0;
		for(var i = 0; i < len; i++) {
			if(check.eq(i).hasClass("layui-form-checked")) {
				picis[x] = check.eq(i).prev("input[type=checkbox]").val();
				x++;
			}
		}
		var dataUpload;
		if(shenpi != '0') {
			dataUpload = {
				"eoperateId": data.bianhao,
				"interiorUserByEoperateStoreman": data.zhixingren,
				"eoperatePonderationTrue": data.guoli,
				"tarehouse": data.kuwei,
				"pici": picis,
				"eoperateRealityWeight": data.weight,
				"eoperateRealityNumber": data.number,
				"eoperateCraneman": data.tianche,
				"eoperateStevedore": data.zhuangxie,
				"eoperateRemark": data.ccbeizhu
			};
		}
		if(ddupdate != '0' && shenpi == "0" && bgupdate != '0') {
			dataUpload = {
				"eoperateId": data.bianhao,
				"interiorUserByEoperateStoreman": data.zhixingren,
				"eoperatePonderationTrue": data.guoli,
				"tarehouse": data.kuwei,
				"pici": picis,
				"eoperateRealityWeight": data.weight,
				"eoperateRealityNumber": data.number,
				"eoperateCraneman": data.tianche,
				"eoperateStevedore": data.zhuangxie,
				"eoperateRemark": data.ccbeizhu
			};
		}
		if(ddupdate != '0' && shenpi == "0" && bgupdate == '0') {
			dataUpload = {
				"eoperateId": data.bianhao,
				"interiorUserByEoperateStoreman": data.zhixingren,
				"eoperatePonderationTrue": data.guoli,
				"tarehouse": data.kuwei,
				"pici": picis,
				"eoperateRemark": data.ccbeizhu
			};
		}
		if(ddupdate == '0' && shenpi == "0" && bgupdate != '0') {
			dataUpload = {
				"eoperateId": data.bianhao,
				"eoperateRealityWeight": data.weight,
				"eoperateRealityNumber": data.number,
				"eoperateCraneman": data.tianche,
				"eoperateStevedore": data.zhuangxie,
				"eoperateRemark": data.ccbeizhu
			};
		}
		if(shenpi != '0' || ddupdate != '0') {
			if(x == 0) {
				layer.msg("请选择批次！", {
					icon: 2,
					time: 3000,
					type: 0
				});
				return false;
			}
		}
		//如果具有修改审批的权限，则全部可以修改
		layer.confirm("确定修改吗？", {
			icon: 3,
			anim: 4,
			closeBtn: 2,
			title: ['系统提示', 'font-size:16px;'],
			skin: 'layui-layer-blue',
		}, function() {
			$.ajax({
				type: "post",
				url: "/XGProject/exportOperate.do?flag=updateExportOperate",
				async: false,
				data: dataUpload,
				dataType: 'text',
				success: function(data) {
					var ok = data.indexOf("修改成功");
					if(ok != -1) {
						layer.alert("修改成功！", {
							icon: 1,
							anim: 4,
							closeBtn: 2,
							title: ['系统提示', 'font-size:16px;'],
							skin: 'layui-layer-blue',
						}, function() {
							document.location.reload();
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
		});
		return false;
	});

	//对财务修改表格进行监听
	table.on('tool(updatecaiwuModeal)', function(obj) {
		var data = obj.data; //获得当前行数据
		var layEvent = obj.event; //获得 lay-event 对应的值
		var tr = obj.tr; //获得当前行 tr 的DOM对象
		if(layEvent == "edit") {
			//进行编辑，打开对应的编辑弹出层
			//调用支付方式查询函数
			zhifufangshi();

			$("#cweoperateId").val(data.bianhao);
			$("#eoperateRealityCost").val(data.shishou);
			$("#jiesuan").val(data.jiesuan);
			$("#zhifu option").each(function() {
				if($(this).text() == data.zhifu) {
					$(this).parent("select").val($(this).val());
					form.render('select');
					return;
				}
			});
			$("#piaoju option").each(function() {
				if($(this).text() == data.piaoju) {
					$(this).parent("select").val($(this).val());
					form.render('select');
					return;
				}
			});

			if(shenpi != "0") {
				layer.open({
					type: 1,
					title: ['出库订单修改', 'font-size:16px;'],
					skin: 'layui-layer-blue',
					closeBtn: 2,
					anim: 4,
					content: $('#caiwuUpdate'),
					area: ["600px", '420px'],
					btn: ['确定提交', '关闭'],
					yes: function(index, obj) {
						$("#caiwuTijiao").click();
					},
					btn2: function(index, obj) {
						layer.close(index);
					}
				});
			} else {
				//查询是否有订单修改提交的申请，并且是通过的
				$.ajax({
					type: "post",
					url: "/XGProject/updateRecordAction.do?flag=goShenQingPanDuan",
					data: {
						"urcaozuoid": data.bianhao
					},
					dataType: "json",
					success: function(obj) {
						//如果没有订单审批，则进行提交订单修改审批的申请
						if(obj[0].result == "no") {
							$("#urcaozuoid").val(data.bianhao);
							layer.open({
								type: 1,
								title: ['出库订单修改申请', 'font-size:16px;'],
								skin: 'layui-layer-blue',
								closeBtn: 2,
								anim: 4,
								content: $('#updateShenpi'),
								area: ["600px", '300px'],
								btn: ['确定提交', '关闭'],
								yes: function(index, obj) {
									$("#shengpiTijiao").click();
								},
								btn2: function(index, obj) {
									layer.close(index);
								}
							});
						} else {
							layer.open({
								type: 1,
								title: ['出库订单修改', 'font-size:16px;'],
								skin: 'layui-layer-blue',
								closeBtn: 2,
								anim: 4,
								content: $('#caiwuUpdate'),
								area: ["600px", '420px'],
								btn: ['确定提交', '关闭'],
								yes: function(index, obj) {
									$("#caiwuTijiao").click();
								},
								btn2: function(index, obj) {
									layer.close(index);
								}
							});
						}
					},
					error: function() {
						layer.alert("获取修改审批数据错误！", {
							icon: 2,
							anim: 4,
							closeBtn: 2,
							title: ['系统提示', 'font-size:16px;'],
							skin: 'layui-layer-blue',
						});
					}
				});
			}
		}
	});

	//财务修改提交
	form.on('submit(caiwuTijiao)', function(obj) {
		var data = obj.field;
		layer.confirm("确定提交吗？", {
			icon: 3,
			anim: 4,
			closeBtn: 2,
			title: ['系统提示', 'font-size:16px;'],
			skin: 'layui-layer-blue',
		}, function() {
			$.ajax({
				type: "post",
				url: "/XGProject/exportOperate.do?flag=updateExportOperate",
				async: false,
				data: {
					"eoperateId": data.eoperateId,
					"eoperateRealityCost": data.eoperateRealityCost,
					"eoperateClientAccounts": data.jiesuan,
					"eoperatePaymentFashion": data.piaoju,
					"eoperateRemark": data.eoperateRemark
				},
				dataType: "text",
				success: function(data) {
					var ok = data.indexOf("修改成功");
					if(ok != -1) {
						layer.alert("修改成功！", {
							icon: 1,
							anim: 4,
							closeBtn: 2,
							title: ['系统提示', 'font-size:16px;'],
							skin: 'layui-layer-blue',
						}, function() {
							document.location.reload();
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
		});
		return false;
	});

	//当库位选择的下拉框切花的时候触发
	form.on('select(kuwei)', function(obj) {
		selectPici($("#goodsId").val(), obj.val());
	});

	//当支付方式切换的时候查询对应的票据类型
	form.on('select(zhifu)', function(obj) {
		piaojuleixing(obj.value);
	});

	//当结算方式下拉框切的时候
	form.on('select(jiesuan)', function(obj) {
		if(obj.value == "日结") {
			//查询对应的支付方式
			zhifufangshi();
			//当为日结的时候实收费用可用					
		} else if(obj.value == "月结") {

			//为月结的时候支付方式和票据类型无
			$("#zhifu").html("<option value='无'>无</option>");
			$("#piaoju").html("<option value='无'>无</option>");
			form.render('select');
		}
	});
});

// 查询对应的支付方式
function zhifufangshi() {
	layui.use(['jquery', 'form', 'layer'], function() {
		var $ = layui.jquery;
		var form = layui.form;
		var layer = layui.layer;

		$.ajax({
			type: "post",
			url: "/XGProject/paymentFashion.do?flag=getGuoHuQuery&ff=zhifu",
			dataType: "json",
			success: function(obj) {
				$("#zhifu").html("");
				if(obj == null || obj.length <= 0) {
					$("#zhifu").append("<option value=''>无</option>");
					return false;
				}
				if(obj[0].result == null) {
					$("#zhifu").append("<option value=''>无</option>");
					return false;
				}
				for(var i = 0; i < obj.length; i++) {
					$("#zhifu").append("<option value='" + obj[i].zhifu + "'>" + obj[i].zhifu + "</option>");
				}
				form.render('select');
				piaojuleixing($("#zhifu").val());
			},
			error: function() {
				layer.alert("获取支付方式数据错误！", {
					icon: 2,
					anim: 4,
					closeBtn: 2,
					title: ['系统提示', 'font-size:16px;'],
					skin: 'layui-layer-blue',
				});
			}
		});
	});
}

// 查询对应的票据类型
function piaojuleixing(zhifu) {
	layui.use(['jquery', 'form', 'layer'], function() {
		var $ = layui.jquery;
		var form = layui.form;
		var layer = layui.layer;

		$.ajax({
			type: "post",
			url: "/XGProject/paymentFashion.do?flag=getGuoHuQuery&ff=piaoju",
			data: {
				"pfashionName": zhifu
			},
			dataType: "json",
			success: function(obj) {
				$("#piaoju").html("");
				if(obj == null || obj.length <= 0) {
					$("#piaoju").append("<option value=''>无</option>");
					return false;
				}
				if(obj[0].result == null) {
					$("#piaoju").append("<option value=''>无</option>");
					return false;
				}
				for(var i = 0; i < obj.length; i++) {
					$("#piaoju").append("<option value='" + obj[i].zhifuid + "'>" + obj[i].piaoju + "</option>");
				}
				form.render('select');
			},
			error: function() {
				layer.alert("获取票据类型数据错误！", {
					icon: 2,
					anim: 4,
					closeBtn: 2,
					title: ['系统提示', 'font-size:16px;'],
					skin: 'layui-layer-blue',
				});
			}
		});
	});
}

// 查询天车工
function tianchegong() {
	layui.use(['jquery', 'form', 'layer'], function() {
		var $ = layui.jquery;
		var form = layui.form;
		var layer = layui.layer;

		$.ajax({
			type: "post",
			url: "/XGProject/working.do?flag=getZuoYeRenYuan&ff=tianche",
			async: false,
			data: {
				"time": new Date().getTime()
			},
			dataType: "json",
			success: function(obj) {
				$("#tianche").html("");
				if(obj[0].result != null) {
					for(var i = 0; i < obj.length; i++) {
						$("#tianche").append("<option value='" + obj[i].name + "'>" + obj[i].name + "</option>");
					}
				} else {
					$("#tianche").append("<option value=''>无人员</option>");
				}
				form.render('select');
			},
			error: function() {
				layer.alert("获取天车工数据错误！", {
					icon: 2,
					anim: 4,
					closeBtn: 2,
					title: ['系统提示', 'font-size:16px;'],
					skin: 'layui-layer-blue',
				});
			}
		});
	});
}

//通过库位和货物进行查询批次
function selectPici(goods, kuwei) {
	layui.use(['jquery', 'form', 'layer'], function() {
		var $ = layui.jquery;
		var form = layui.form;
		var layer = layui.layer;

		$.ajax({ // ajax提交方式
			type: "post",
			url: "/XGProject/tarehouseDetail.do?flag=getChuKuPiCi",
			dataType: "json",
			data: {
				"goods": goods,
				"kuwei": kuwei
			},
			success: function(obj) {
				if(obj == null || obj[0].result == null) {
					return false;
				}
				for(var i = 0; i < obj.length; i++) {
					$("#pici").html("");
					$("#pici").append("<input type='checkbox' name='pici' data-content='剩余重量：" + (parseFloat(obj[i].weight) - parseFloat(obj[i].Eweight)) + "吨,剩余件数:" + (parseFloat(obj[i].number) - parseFloat(obj[i].Enumber)) + obj[i].unit + "' value='" + obj[i].id + "' title='" + obj[i].id + "'>");
				}
				form.render('checkbox');
				$("#pici .layui-form-checkbox").on('click', function() {
					if($(this).hasClass("layui-form-checked")) {
						layer.tips($(this).prev("input[type=checkbox]").attr("data-content"), this, {
							tips: 1,
							time: 4000
						});
					}
				});
			},
			error: function() {
				layer.alert("获取出库批次数据错误！", {
					icon: 2,
					anim: 4,
					closeBtn: 2,
					title: ['系统提示', 'font-size:16px;'],
					skin: 'layui-layer-blue',
				});
			}
		});
	});
}

// 查询对应的库位
function selectchukukuwei(huowu) {
	layui.use(['jquery', 'form', 'layer'], function() {
		var $ = layui.jquery;
		var form = layui.form;
		var layer = layui.layer;

		$.ajax({
			type: "post",
			url: "/XGProject/tarehouseGoods.do?flag=selectChuKuKuWei",
			async: false,
			data: {
				"goodsId": huowu,
				"time": new Date().getTime()
			},
			dataType: "json",
			success: function(obj) {
				$("#kuwei").html("");
				if(obj == null) {
					$("#kuwei").append("<option value=''>无库位</option>");
					form.render('select');
					return false;
				}
				if(obj[0].result == null) {
					$("#kuwei").append("<option value=''>无库位</option>");
					form.render('select');
					return false;
				}

				for(var i = 0; i < obj.length; i++) {
					$("#kuwei").append("<option value='" + obj[i].id + "'>" + obj[i].name + "</option>");
				}
				form.render('select');
				selectPici(huowu, $("#kuwei").val());
			},
			error: function() {
				layer.alert("获取库位数据错误！", {
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

//查询在线的出库执行人,在仓储修改时调用
function zhixingchuku() {
	layui.use(['jquery', 'form', 'layer'], function() {
		var $ = layui.jquery;
		var form = layui.form;
		var layer = layui.layer;

		$.ajax({
			type: "post",
			url: "/XGProject/checks.do?flag=getChuKuCZY&ff=zhixing",
			data: {
				"time": new Date().getTime()
			},
			async: false,
			dataType: "json",
			success: function(obj) {
				$("#zhixingren").html("");
				if(obj != null) {
					if(obj[0].result != null && obj[0].id != null) {
						for(var i = 0; i < obj.length; i++) {
							$("#zhixingren").append("<option value='" + obj[i].id + "'>" + obj[i].name + "</option>");
						}
					} else {
						$("#zhixingren").append("<option value=''>无人员</option>");
					}
				} else {
					$("#zhixingren").append("<option value=''>无人员</option>");
				}
				form.render('select');
			},
			error: function() {
				layer.alert("获取执行人员数据错误！", {
					icon: 2,
					anim: 4,
					closeBtn: 2,
					title: ['系统提示', 'font-size:16px;'],
					skin: 'layui-layer-blue',
				});
			}
		});
	});
}

//出库完成提交函数
function chuKuWanChengAjax() {
	layui.use(['jquery', 'layer'], function() {
		var $ = layui.jquery;
		var layer = layui.layer;

		$.ajax({
			type: "post",
			url: "/XGProject/exportSeed.do?flag=ChuKuWanCheng&ff=daichuli",
			async: false,
			data: {
				"eseedId": $("#kzid").val()
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
					}, function(index) {
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
	});
}
shenpi();
//查询对应的一级审批人
function shenpi() {
	layui.use(['jquery', 'layer', 'form'], function() {
		var $ = layui.jquery;
		var layer = layui.layer;
		var form = layui.form;
		$("#shenpiren").html("");
		$.ajax({
			type: "post",
			url: "/XGProject/interiorUser.do?flag=getPowerMan&iuserDefinedOne=一级审批",
			async: true,
			dataType: "json",
			success: function(data) {
				if(data[0].result == null) {
					$("#shenpiren").html("<option value=''>无一级审批人</option>");
				} else {
					$.each(data, function(i, obj) {
						$("#shenpiren").append("<option value='" + obj.name + "'>" + obj.name + "</option>");
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