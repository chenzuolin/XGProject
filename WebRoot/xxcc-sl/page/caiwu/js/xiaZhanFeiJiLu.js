inputBg();
clientName('#client');
showContent(1);

layui.use(['table', 'form', 'jquery', 'laydate', 'layer'], function() {
	var table = layui.table;
	var form = layui.form;
	var $ = layui.jquery;
	var laydate = layui.laydate;
	var layer = layui.layer;

	laydate.render({
		elem: '#begin',
		type: 'datetime'
	});
	laydate.render({
		elem: '#finish',
		type: 'datetime'
	});

	form.on('submit(formDemo)', function(obj) {
		showContent(1);
		return false;
	});
	var xiazhanId;
	table.on('tool(showcontent)', function(obj) {
		var data = obj.data; //获得当前行数据
		var layEvent = obj.event; //获得 lay-event 对应的值
		var tr = obj.tr; //获得当前行 tr 的DOM对象
		if(layEvent == "edit") {
			//加载下站费中的支付方式和票据类型
			xiazhanId = data.xzbianhao;
			zhifu('#zhifu');
			piaoju("#piaoju", $("#zhifu").val(), 'text');
			$("#danjia").val(data.danjia);
			$("#weight").val(data.weight);
			$("#yingshou").val(data.yingshou);
			$("#shishou").val(data.shishou);
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
			$("#beizhu").val(data.remark);
			//打开对应的修改弹出层
			layer.open({
				type: 1,
				title: ['下站费修改', 'font-size:16px;'],
                skin: 'layui-layer-blue',
				closeBtn: 2,
				anim: 1,
				content: $('#updateOpen'),
				area: ['600px', '600px'],
				btn: ['确定提交', '关闭'],
				yes: function(index, obj) {
					$("#tijiao").click();
				},
				btn2: function(index) {
					layer.close(index);
				}
			});
		}
	});

	form.on('select(zhifu)', function(obj) {
		piaoju('#piaoju', obj.value, 'text');
	});

	form.on('select(jiesuan)', function(obj) {
		if(obj.value == "日结") {
			//查询对应的支付方式
			zhifu('#zhifu');
			//查询对应的票据类型
			piaoju('#piaoju', $("#zhifu").val(), 'text');
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

	//当光标移除单价的时候自动计算对应的下站费合计
	$("#danjia").blur(function() {
		var reg_zhekou = /^(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$/;
		if($(this).val() != null && $(this).val() != undefined && $(this) != "" && reg_zhekou.test($(this).val())) {
			var sum = parseFloat($(this).val()) * parseFloat($("#weight").val());
			$("#yingshou").val(sum);
		}
	});

	form.on('submit(updatetijiao)', function(obj) {
		var data = obj.field;
		//当单机确定提交的时候，用ajax的方式提交修改
		layer.confirm("确定修改吗？", {
			icon: 3,
			title: ['系统提示', 'font-size:16px;'],
            skin: 'layui-layer-blue',
			closeBtn: 2,
			anim: 4
		}, function(index) {
			var d = {
				"xzid": xiazhanId,
				"xzdanjia": data.danjia,
				"xzweight": data.weight,
				"xzcount": data.yingshou,
				"xadefinedfour": data.shishou,
				"xzdefinedone": data.jiesuan,
				"xzzhifu": data.zhifu,
				"xzpiaoju": data.piaoju,
				"xzremark": data.beizhu
			};
			var text = queryAjax('/XGProject/xiazhanfeiAction.do?flag=UpdateXiaZhan', d, 'text');
			if(text == "error") {
				layer.alert("数据上传错误！", {
					icon: 2,
					anim: 4,
					closeBtn: 2,
					title: ['系统提示', 'font-size:16px;'],
	                skin: 'layui-layer-blue',
				});
			} else {
				if(text.indexOf("修改成功") != -1) {
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
			}
		});

		return false;
	});

});

function showContent(curr) {
	layui.use(['jquery', 'form', 'table', 'laypage', 'layer'], function() {
		var $ = layui.jquery;
		var form = layui.form;
		var table = layui.table;
		var laypage = layui.laypage;
		var layer = layui.layer;

		var d = {
			"begin": $("#begin").val(),
			"finish": $("#finish").val(),
			"clientName": $("#client").val(),
			"jiesuan": $("#jiesuan").val(),
			"pageNow": curr,
			"shoufeiren": $("#shoufeiren").val()
		};
		var data = queryAjax('/XGProject/xiazhanfeiAction.do?flag=getXiaZhanAll', d, 'json');
		if(data == "error") {
			layer.alert("获取数据错误！", {
				icon: 2,
				anim: 4,
				closeBtn: 2,
				title: ['系统提示', 'font-size:16px;'],
                skin: 'layui-layer-blue',
			});
		} else {
			if(data == null || data == "" || JSON.parse(data)[0].result == null) {
				layer.alert("无下站费记录！", {
					icon: 5,
					anim: 4,
					closeBtn: 2,
					title: ['系统提示', 'font-size:16px;'],
	                skin: 'layui-layer-blue',
				});
			}
			var obj = JSON.parse(data);
			var w=$(parent.window).width()-245;//获取浏览器的宽，减去侧边栏的宽度  
			table.render({
				data: obj, //返回的json数据
				elem: '#showContent', //显示数据的容器
				height: 'full-210',
				width:w,
				limit: 1000000, //默认显示的行数
				even: true,
				cols: [
					[{
						field: 'zongbianhao', //列显示的值
						title: '订单编号', //显示的标题
						width: 160, //列的宽度
						sort: false, //是否产生排序
					}, {
						field: 'caozuobianhao',
						title: '操作编号',
						width: 160,
						sort: false,
					}, {
						field: 'zhuanru',
						title: '客户名称',
						width: 130,
						sort: false,
						edit: false //是否可编辑
					}, {
						field: 'type',
						title: '下站类型',
						width: 100,
						sort: false
					}, {
						field: 'weight',
						title: '下站重量',
						width: 100,
						sort: false
					}, {
						field: 'danjia',
						title: '下站费单价',
						width: 100,
						sort: false
					}, {
						field: 'yingshou',
						title: '应收费用',
						width: 80,
						sort: false
					}, {
						field: 'shoufeiren',
						title: '收费人',
						width: 100,
						sort: false,
					}, {
						field: 'shoufeitime',
						title: '收费时间',
						width: 180,
						sort: false
					}, {
						field: 'shishou',
						title: '实收费用',
						width: 100,
						sort: false
					}, {
						field: 'jiesuan',
						title: '结算方式',
						width: 100,
						sort: false,
					}, {
						field: 'zhifu',
						title: '支付方式',
						width: 100,
						sort: false
					}, {
						field: 'piaoju',
						title: '票据类型',
						width: 100,
						sort: false,
					}, {
						field: 'zhuangtai',
						title: '状态',
						width: 100,
						sort: false
					}, {
						field: 'remark',
						title: '备注',
						width: 130,
						sort: false
					}, {
						fixed: 'right',
						width: 100,
						align: 'center',
						toolbar: '#barDemo'
					}]
				] //设置表头
			});
			if(parseInt(curr) == 1) {
				//分页的渲染
				laypage.render({
					elem: 'paging', //显示分页的容器
					count: parseInt(obj[0].pageCount) * 30, //显示的总条数
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
		}
	});
}

//封装ajax访问方法
function queryAjax(u, d, dt) {
	var data;
	layui.use("jquery", function() {
		var $ = layui.jquery;
		$.ajax({
			type: "post",
			url: u,
			async: false,
			data: d,
			dataType: dt,
			success: function(obj) {
				data = JSON.stringify(obj);
			},
			error: function() {
				data = "error";
			}
		});
	});
	return data;
}