layui.use(['form', 'jquery', 'layer', 'table'], function() {
	var form = layui.form;
	var $ = layui.jquery;
	var table = layui.table;
	var layer = layui.layer;

	//form监听提交,当单机【立即提交的时候调用】
	form.on('submit(formDemo)', function(data) {
		showContent(1);
		return false;
	});

	//在客户名称下拉框中当键盘按下弹起的时候，将该文本框的值改变为小写
	$(".layui-form-select .layui-select-title input").keyup(function() {
		$(this).val($(this).val().toLowerCase());
	});

	var cgoodsId;
	var dongjieWeight;
	var shengyuWeight;

	//表格工具条的监听
	table.on('tool(demo)', function(obj) {
		var data = obj.data;
		var layEvent = obj.event;
		cgoodsId = data.cgid;
		dongjieWeight = data.dongjieweight;
		shengyuWeight = data.shengyuweight;

		if(layEvent == "detail") {
			//如果单机冻结的时候触发
			//打开冻结对应的显示层
			if($("#dongjie").val()=="0"){
		 		  layer.alert("无冻结权限！", {
		               icon: 5,
		               anim: 4,
		               closeBtn: 2,
		               title: ['系统提示', 'font-size:16px;'],
		               skin: 'layui-layer-blue',
		            });
		 		  return false;
		 	  }
			$("#DJW").val(shengyuWeight);
			layer.open({
				type: 1,
				anim: 1,
				title: ['客户库存冻结', 'font-size:16px;'],
                skin: 'layui-layer-blue',
				closeBtn: 2,
				content: $("#dongjieOpen"),
				btn: ["立即提交", '关闭'],
				area: ["450px", '300px'],
				yes: function() {
					$("#dongjie").click();
				},
				btn2: function(index) {
					layer.close(index);
				}
			});
		} else if(layEvent == "edit") {
			//单机解冻的时候触发
			//打开解冻对应的显示层
			if($("#dongjie").val()=="0"){
		 		  layer.alert("无解冻权限！", {
		               icon: 5,
		               anim: 4,
		               closeBtn: 2,
		               title: ['系统提示', 'font-size:16px;'],
		               skin: 'layui-layer-blue',
		            });
		 		  return false;
		 	  }
			$("#JDW").val(dongjieWeight);
			layer.open({
				type: 1,
				anim: 1,
				closeBtn: 2,
				content: $("#jiedongOpen"),
				btn: ["立即提交", '关闭'],
				title: ['客户库存解冻', 'font-size:16px;'],
                skin: 'layui-layer-blue',
				area: ["450px", '300px'],
				yes: function() {
					$("#jiedong").click();
				},
				btn2: function(index) {
					layer.close(index);
				}
			});
		}
	});

	//冻结form表单提交
	form.on('submit(dongjie)', function(obj) {
		var data = obj.field;
		if(parseFloat(data.DJW) > parseFloat(shengyuWeight)) {
			layer.msg("冻结重量不能大于剩余重量！", {
				icon: 2,
				time: 3000
			});
			return false;
		}
		layer.confirm("确定冻结吗？", {
			icon: 3,
			anim: 4,
			closeBtn: 2,
			title: ['系统提示', 'font-size:16px;'],
            skin: 'layui-layer-blue',
		}, function() {
			$.ajax({
				type: "post",
				url: "/XGProject/clientGoods.do?flag=DongJieClientGoods",
				async: false,
				data: {
					"cgoodsId": cgoodsId,
					"cgoodsFreezeWeight": data.DJW,
					"cgoodsRemark": data.beizhu
				},
				dataType: "text",
				success: function(data) {
					if(data.indexOf("冻结成功") != -1) {
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

	//解冻form表单提交
	form.on('submit(jiedong)', function(obj) {
		var data = obj.field;
		if(parseFloat(data.JDW) > parseFloat(dongjieWeight)) {
			layer.msg("解冻重量不能大于冻结重量！");
			return false;
		}
		layer.confirm("确定解冻吗？", {
			icon: 3,
			anim: 4,
			closeBtn: 2,
			title: ['系统提示', 'font-size:16px;'],
            skin: 'layui-layer-blue',
		}, function() {
			$.ajax({
				type: "post",
				url: "/XGProject/clientGoods.do?flag=JieDongClientGoods",
				async: false,
				data: {
					"cgoodsId": cgoodsId,
					"cgoodsFreezeWeight": data.JDW,
					"cgoodsRemark": data.beizhu
				},
				dataType: "text",
				success: function(data) {
					if(data.indexOf("解冻成功") != -1) {
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

//当页面加载的时候调用查询对应客户的方法
clientName("#client");

//当页面加载的时候调用显示内容的方法
showContent(1);

//显示内容的方法
function showContent(curr) {
	layui.use(['element', 'table', 'laypage', 'jquery', 'layer'], function() {
		var table = layui.table,
			laypage = layui.laypage,
			layer = layui.layer,
			element = layui.element,
			$ = layui.jquery;
		//用ajax访问数据
		var client = $("#client").val(); //获得客户名称文本框中的值
		var goodsName = $("#goodsName").val(); //获得货物名称中的值

		$.ajax({
			type: "post",
			url: "/XGProject/clientGoods.do?flag=getClientGoodsAll",
			async: false,
			data: {
				"time": new Date().getTime(),
				"goodsName": goodsName,
				"jiancheng": client,
				"pageNow": curr,
			},
			dataType: "json",
			success: function(data) {
				if(data == null || data[0].result == null) {
					layer.alert("无客户库存记录！", {
						icon: 5,
						closeBtn: 2,
						anim: 4,
						title: ['系统提示', 'font-size:16px;'],
		                skin: 'layui-layer-blue',
					});
					$("#content-sum tr td").eq(1).text("冻结重量：0吨");
					$("#content-sum tr td").eq(2).text("剩余重量：0吨");
					return true;
				}
				var weight_dongjie = 0; //冻结重量的相加
				var weight_shengyu = 0; //剩余重量的相加

				$.each(data, function(i, obj) {
					obj.xuhao = (i + 1);
					weight_dongjie += parseFloat(obj.dongjieweight);
					weight_shengyu += parseFloat(obj.shengyuweight);
				});
				var w=$(parent.window).width()-235;//获取浏览器的宽，减去侧边栏的宽度  
				table.render({
					data: data, //返回的json数据
					elem: '#showContent', //显示数据的容器
					height: 'full-125',
					limit: 1000000, //默认显示的行数
					width:w,
					even: true,
					cols: [
						[{
							field: 'xuhao', //列显示的值
							title: '序号', //显示的标题
							width: 80, //列的宽度
							sort: false, //是否产生排序
							fixed: true //是否是固定列宽
						}, {
							field: 'clientName',
							title: '客户名称',
							width: 160,
							sort: false,
						}, {
							field: 'pinlei',
							title: '货物品类',
							width: 160,
							sort: false,
						}, {
							field: 'goodsName',
							title: '货物名称',
							width: 200,
						}, {
							field: 'guige',
							title: '货物规格',
							width: 180,
							sort: false,
							edit: false //是否可编辑
						}, {
							field: 'caizhi',
							title: '货物材质',
							width: 140,
							sort: false
						}, {
							field: 'shuxing',
							title: '货物属性',
							width: 140,
							sort: false
						}, {
							field: 'chandi',
							title: '货物产地',
							width: 140,
							sort: false
						}, {
							field: 'dongjieweight',
							title: '冻结重量（吨）',
							width: 130,
							sort: false
						}, {
							field: 'shengyuweight',
							title: '剩余重量（吨）',
							width: 130,
							sort: false
						}, {
							fixed: 'right',
							width: 200,
							align: 'center',
							toolbar: '#barDemo'
						}]
					] //设置表头
				});

				$("#content-sum tr td").eq(1).text("冻结重量：" + weight_dongjie.toFixed(3) + "吨");
				$("#content-sum tr td").eq(2).text("剩余重量：" + weight_shengyu.toFixed(3) + "吨");

				//如果是第一次调用此方法或者是当点击查询的时候进行执行下面分页的方法
				if(parseInt(curr) == 1) {
					//分页的渲染
					laypage.render({
						elem: 'paging', //显示分页的容器
						count: parseInt(data[0].pageCount) * 30, //显示的总条数
						limit: 30,
						layout: ['prev', 'page', 'next', 'skip'],
						jump: function(obj, first) {
							//obj包含了当前分页的所有参数，比如：
							//如果不是第一次执行就调用显示数据的方法，例如当点击下一页或者上一页的时候调用
							if(!first) {
								showContent(obj.curr);
							}
						}
					});
				}
			},
			error: function() {
				layer.alert("数据加载错误！", {
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