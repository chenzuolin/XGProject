layui.use(['form', 'jquery', 'layer', 'table', 'laydate'], function() {
	var $ = layui.jquery;
	var form = layui.form;
	var layer = layui.layer;
	var table = layui.table;
	var laydate = layui.laydate;

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
	
	//当单机打印的时候转换到打印页面
	$("#dayin").click(function() {
		var id = $("#exportoperateid").val();
		document.location.href = "/XGProject/inputOperate.do?flag=getDaYin&p=list&ioperateId=" + id+
		"&zid="+encodeURI(encodeURI($('#kzid').val()));
	});

	//当单机入库完成的时候触发
	$("#rukuwancheng").click(function() {
		$.ajax({
			type: "post",
			url: "/XGProject/inputSeed.do?flag=RuKuWanChengAjax",
			data: {
				"iseedId": $("#kzid").val()
			},
			dataType: "json",
			success: function(obj) {
				if(obj[0].result == "no") {
					layer.confirm("该订单正在操作，是否完成入库？", {
						icon: 3,
						title: '系统提示',
						closeBtn: 2,
						anim: 4,
						title: ['系统提示', 'font-size:16px;'],
		                skin: 'layui-layer-blue',
					}, function(index) {
						rukuwangchengAjax();
						layer.close(index);
					});
				} else {
					layer.confirm("确认入库完成吗？", {
						icon: 3,
						closeBtn: 2,
						anim: 4,
						title: ['系统提示', 'font-size:16px;'],
		                skin: 'layui-layer-blue',
					}, function(index) {
						rukuwangchengAjax();
						layer.close(index);
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

	//当单机作废的时候触发
	$("#zuofei").click(function() {
		//渲染点击作废弹出的表格
		table.init('zuofeiModeal', {
			height: 380,
			width: "full"
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
					url: "/XGProject/inputOperate.do?flag=DanGeZuoFei",
					async: false,
					data: {
						"ioperateId": data.bianhao
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
						url: "/XGProject/input.do?flag=UpdateTime",
						async: false,
						data: {
							"inputId": $("#timeUId").val(),
							"inputCreateTime": $("#indentTime").val()
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

	//渲染对应的table表格
	table.init('updatecangchuModeal', {
		width: "full",
	});
	//当单机订单修改的时候触发
	$("#update").click(function() {
		if($("#bgupdate").val() != "0" || $("#ddupdate").val() != "0") {
			$(".layui-tab-content .layui-tab-item:first").addClass("layui-show");
		}
		var indexu = layer.open({
			type: 1,
			title: ['入库订单修改', 'font-size:16px;'],
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

			//调用查询执行入库人员的函数
			zhixingruku();
			//调用查询库位的函数
			selectchukukuwei();
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
					title: ['入库订单修改', 'font-size:16px;'],
	                skin: 'layui-layer-blue',
					closeBtn: 2,
					anim: 4,
					content: $('#cangchuUpdate'),
					area: ["600px", '550px'],
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
								title: ['入库订单修改申请', 'font-size:16px;'],
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
								form.render('select');
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
								title: ['入库订单修改', 'font-size:16px;'],
				                skin: 'layui-layer-blue',
								closeBtn: 2,
								anim: 4,
								content: $('#cangchuUpdate'),
								area: ["600px", '550px'],
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
		var dataUpload;
		var zhuangxie = [];
		zhuangxie[0] = data.zhuangxie;
		if(shenpi != '0') {
			dataUpload = {
				"ioperateId": data.bianhao,
				"interiorUserByIoperateStoremanId": data.zhixingren,
				"ioperatePonderationTrue": data.guoli,
				"tarehouse": data.kuwei,
				"ioperateRealityWeight": data.weight,
				"ioperateRealityNumber": data.number,
				"ioperateCraneman": data.tianche,
				"zhuangxieGong": zhuangxie,
				"ioperateRemark": data.ccbeizhu
			};
		}
		if(ddupdate != '0' && shenpi == "0" && bgupdate != '0') {
			dataUpload = {
				"ioperateId": data.bianhao,
				"interiorUserByIoperateStoremanId": data.zhixingren,
				"ioperatePonderationTrue": data.guoli,
				"tarehouse": data.kuwei,
				"ioperateRealityWeight": data.weight,
				"ioperateRealityNumber": data.number,
				"ioperateCraneman": data.tianche,
				"zhuangxieGong": zhuangxie,
				"ioperateRemark": data.ccbeizhu
			};
		}
		if(ddupdate != '0' && shenpi == "0" && bgupdate == '0') {
			dataUpload = {
				"ioperateId": data.bianhao,
				"interiorUserByIoperateStoremanId": data.zhixingren,
				"ioperatePonderationTrue": data.guoli,
				"tarehouse": data.kuwei,
				"ioperateRemark": data.ccbeizhu
			};
		}
		if(ddupdate == '0' && shenpi == "0" && bgupdate != '0') {
			dataUpload = {
				"ioperateId": data.bianhao,
				"ioperateRealityWeight": data.weight,
				"ioperateRealityNumber": data.number,
				"ioperateCraneman": data.tianche,
				"zhuangxieGong": zhuangxie,
				"ioperateRemark": data.ccbeizhu
			};
		}
		//如果具有修改审批的权限，则全部可以修改
		layer.confirm("确定修改吗？", {
			icon: 3,
			anim: 4,
			closeBtn: 2
		}, function() {
			$.ajax({
				type: "post",
				url: "/XGProject/inputOperate.do?flag=updateInputOperate",
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
});

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

// 查询对应的库位
function selectchukukuwei() {
	layui.use(['jquery', 'form', 'layer'], function() {
		var $ = layui.jquery;
		var form = layui.form;
		var layer = layui.layer;

		$.ajax({
			type: "post",
			url: "/XGProject/tarehouse.do?flag=selectAjaxKuwei",
			async: true,
			dataType: "json",
			success: function(obj) {
				$("#kuwei").html("");
				if(obj == null) {
					$("#kuwei").append("<option value=''>无库位</option>");
					return false;
				}
				for(var i = 0; i < obj.length; i++) {
					$("#kuwei").append("<option value='" + obj[i].id + "'>" + obj[i].name + "</option>");
				}
				form.render('select');
			},
			error: function() {
				alert("获得库位数据错误！", {
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

//查询在线的入库执行人,在仓储修改时调用
function zhixingruku() {
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

//入库完成提交函数
function rukuwangchengAjax() {
	layui.use(['jquery', 'layer'], function() {
		var $ = layui.jquery;
		var layer = layui.layer;

		$.ajax({
			type: "post",
			url: "/XGProject/inputSeed.do?flag=RuKuWanCheng&ff=daichuli",
			async: false,
			data: {
				"iseedId": $("#kzid").val()
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