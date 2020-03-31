layui.use(['jquery', 'form', 'layer'], function() {
	var $ = layui.jquery;
	var form = layui.form;
	var layer = layui.layer;

	//将显示内容的文本框改变为只读
	$('#showContent input').attr('readonly', 'readonly');

	//给关键字添加背景,突出显示
	$('#showContent label').each(function() {
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

	//form提交监听
	form.on('submit(formDemo)', function(obj) {
		var data = obj.field;

		//用ajax的方式进行提交
		$.ajax({
			type: "post",
			url: "/XGProject/exportOperate.do?flag=ChuKuWanCheng",
			async: false,
			data: {
				"eoperateId": data.eoperateId,
				"eoperateRealityWeight": data.weight,
				"eoperateRealityNumber": data.number,
				"eoperateCraneman": data.tianche,
				"eoperateTruckNum": data.chehao,
				"eoperateStevedore": data.zhuangxie,
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
						document.location.href = "/XGProject/xxcc-sl/page/cc-caozuo/dingDanCaoZuo.jsp";
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

		return false;
	});

	//当点击二次作业的时候，打开对应的弹出层
	$("#erci").click(function() {
		layer.open({
			type: 1,
			title: ['二次作业填写', 'font-size:16px;'],
            skin: 'layui-layer-blue',
			closeBtn: 2,
			anim: 4,
			content: $('#ercizuoye'),
			area: ['700px', '400px'],
			btn: ['确定提交', '关闭'],
			yes: function() {
				//用ajax的方式提交二次作业信息
				var id = $("#exportoperateid").val();
				var weight = $("#erciWeight").val();
				var tianche = $("#tianche2").val();
				var zhuangxie = $("#zhuangxie2").val();
				var beizhu = $("#beizhu2").val();

				if(weight == null || weight == undefined || weight == '') {
					layer.alert("二次作业重量不能为空！", {
						icon: 2,
						closeBtn: 2,
						anim: 4,
						title: ['系统提示', 'font-size:16px;'],
		                skin: 'layui-layer-blue',
					});
					return false;
				}

				var zhongyan = /^\d+(\.\d+)?$|^$|^(\d+|\-){7,}$/;

				if(zhongyan.test(weight) == false) {
					layer.alert("二次作业重量输入不合法！", {
						icon: 2,
						closeBtn: 2,
						anim: 4,
						title: ['系统提示', 'font-size:16px;'],
		                skin: 'layui-layer-blue',
					});
					return false;
				}
				if(tianche == null || tianche == undefined || tianche == '') {
					layer.alert("天车工不能为空！", {
						icon: 2,
						closeBtn: 2,
						anim: 4,
						title: ['系统提示', 'font-size:16px;'],
		                skin: 'layui-layer-blue',
					});
					return false;
				}
				if(zhuangxie == null || zhuangxie == undefined || zhuangxie == '') {
					layer.alert("装卸工不能为空！", {
						icon: 2,
						closeBtn: 2,
						anim: 4,
						title: ['系统提示', 'font-size:16px;'],
		                skin: 'layui-layer-blue',
					});
					return false;
				}

				$.ajax({
					type: "post",
					url: "/XGProject/exportOperate.do?flag=ErCiZuoYe",
					async: true,
					data: {
						"eoperateId": id,
						"eoperateDefinedThree": weight,
						"eoperateCraneman": tianche,
						"eoperateStevedore": zhuangxie,
						"eoperateRemark": beizhu
					},
					dataType: 'text',
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
								document.location.href = "/XGProject/xxcc-sl/page/cc-caozuo/dingDanCaoZuo.jsp";
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
			},
			btn2: function(index, obj) {
				//点击关闭的时候触发
				layer.close(index);
			}
		});
	});

	//当单机订单修改的时候，打开对应的弹出层
	$("#update").click(function() {
		//查询对应的库位
		selectchukukuwei();
		//查询对应的批次
		QueryPiCi();

		layer.open({
			type: 1,
			title: ['订单修改', 'font-size:16px;'],
            skin: 'layui-layer-blue',
			closeBtn: 2,
			anim: 4,
			content: $('#updateOpen'),
			area: ['700px', '400px'],
			btn: ['确定提交', '关闭'],
			yes: function() {
				//用ajax的方式提交二次作业信息
				var id = $("#exportoperateid").val();
				var kuwei = $("#kuwei").val();
				var pici = $("#pici .layui-form-checkbox");
				var beizhu = $("#beizhu3").val();

				if(kuwei == null || kuwei == undefined || kuwei == '') {
					layer.alert("库位不能为空！", {
						icon: 2,
						closeBtn: 2,
						anim: 4,
						title: ['系统提示', 'font-size:16px;'],
		                skin: 'layui-layer-blue',
					});
					return false;
				}
				var picis = [];
				var x = 0;
				for(var i = 0; i < pici.length; i++) {
					if(pici.eq(i).hasClass("layui-form-checked")) {
						picis[x] = pici.eq(i).prev("input[type=checkbox]").val();
						x++;
					}
				}
				if(picis.length <= 0 || picis == undefined || picis == "" || picis == null) {
					layer.alert("请选择对应的批次！", {
						icon: 2,
						closeBtn: 2,
						anim: 4,
						title: ['系统提示', 'font-size:16px;'],
		                skin: 'layui-layer-blue',
					});
					return false;
				}
				$.ajax({
					type: "post",
					url: "/XGProject/exportOperate.do?flag=caozuo_update",
					async: true,
					data: {
						"eoperateId": id,
						"tarehouse": kuwei,
						"pici": picis,
						"eoperateRemark": beizhu
					},
					dataType: 'text',
					success: function(data) {
						var ok = data.indexOf("修改成功");
						if(ok != -1) {
							layer.alert("修改成功！", {
								icon: 1,
								closeBtn: 2,
								anim: 4,
								title: ['系统提示', 'font-size:16px;'],
				                skin: 'layui-layer-blue',
							}, function() {
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
						layer.alert("数据上传错误！", {
							icon: 2,
							closeBtn: 2,
							anim: 4,
							title: ['系统提示', 'font-size:16px;'],
			                skin: 'layui-layer-blue',
						});
					}
				});
			},
			btn2: function(index, obj) {
				//点击关闭的时候触发
				layer.close(index);
			}
		});
	});
});

//当页面加载的时候调用天车工方法
queryTianChe('#tianche');
queryTianChe('#tianche2');

//查询对应的天车工
function queryTianChe(_this) {
	layui.use(['jquery', 'layer', 'form'], function() {
		var $ = layui.jquery;
		var layer = layui.layer;
		var form = layui.form;

		$.ajax({
			type: "post",
			url: "/XGProject/working.do?flag=getZuoYeRenYuan&ff=tianche",
			data: {
				"time": new Date().getTime()
			},
			async: false,
			dataType: "json",
			success: function(obj) {
				$(_this).html("");
				if(obj[0].result != null) {
					for(var i = 0; i < obj.length; i++) {
						if(i == 0) {
							$(_this).append("<option value=''>请选择天车工</option>");
						}
						$(_this).append("<option value='" + obj[i].name + "'>" + obj[i].name + "</option>");
					}
				} else {
					$(_this).append("<option value=''>无人员</option>");
				}
				form.render('select');
			},
			error: function() {
				layer.alert("获得天车工数据错误！", {
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

//在订单修改层中通过对应的货物查询对应的库位
function selectchukukuwei() {
	layui.use(['jquery', 'layer', 'form'], function() {
		var $ = layui.jquery;
		var form = layui.form;
		var layer = layui.layer;

		$.ajax({
			type: "post",
			url: "/XGProject/tarehouseGoods.do?flag=selectChuKuKuWei",
			async: false,
			data: {
				"time": new Date().getTime(),
				"goodsId": $("#goodsId").val()
			},
			dataType: "json",
			success: function(obj) {
				$("#kuwei").html("");
				if(obj == null || obj[0].result == null) {
					$("#kuwei").append("<option value=''>无库位</option>");
					return false;
				}
				for(var i = 0; i < obj.length; i++) {
					$("#kuwei").append(
						"<option value='" + obj[i].id + "'>" + obj[i].name +
						"</option>");
					if($("#tarehouseName").val() == obj[i].name) {
						$("#kuwei").val(obj[i].id);
					}
				}
				form.render('select');
			},
			error: function() {
				layer.alert("查询对应库位出错！", {
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

//通过库位和货物进行查询批次
function QueryPiCi() {
	layui.use(['jquery', 'form', 'layer'], function() {
		var $ = layui.jquery;
		var form = layui.form;
		var layer = layui.layer;

		var goods = $("#goodsId").val();
		var kuwei = $("#kuwei").val();
		$.ajax({ // ajax提交方式
			type: "post",
			url: "/XGProject/tarehouseDetail.do?flag=getChuKuPiCi",
			dataType: "json",
			async: false,
			data: {
				"goods": goods,
				"kuwei": kuwei
			},
			success: function(obj) {
				$("#pici").html("");
				if(obj == null || obj[0].result == null) {

					return false;
				}
				for(var i = 0; i < obj.length; i++) {
					$("#pici").append("<input type='checkbox' name='like[write]' value='" + obj[i].id + "' title='" + obj[i].id + "'>");
					//剩余重量Eweight，剩余件数：Enumber
				}
				form.render('checkbox');
			},
			error: function() {
				layer.alert("获得批次数据错误！", {
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