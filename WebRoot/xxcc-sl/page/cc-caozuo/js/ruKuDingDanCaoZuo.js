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
		var zhuang = [];
		zhuang[0] = data.zhuangxie;
		//用ajax的方式进行提交
		$.ajax({
			type: "post",
			url: "/XGProject/inputOperate.do?flag=updateInputOperat",
			async: false,
			data: {
				"ioperateId": data.ioperateId,
				"ioperateRealityWeight": data.weight,
				"ioperateRealityNumber": data.number,
				"ioperateCraneman": data.tianche,
				"ioperateTruckNum": data.chehao,
				"zhuangxieGong": zhuang,
				"ioperateRemark": data.beizhu,
				"ioperatePonderationTrue": data.guoli
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

	//当单机订单修改的时候，打开对应的弹出层
	$("#update").click(function() {
		//查询对应的库位
		selectchukukuwei();

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
				$.ajax({
					type: "post",
					url: "/XGProject/inputOperate.do?flag=updateTarehouse",
					async: true,
					data: {
						"ioperateId": id,
						"tarehouse": kuwei,
						"ioperateRemark": beizhu
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
			url: "/XGProject/tarehouse.do?flag=selectAjaxKuwei",
			async: false,
			data: {
				"time": new Date().getTime()
			},
			dataType: "json",
			success: function(obj) {
				$("#kuwei").html("");
				if(obj == null) {
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