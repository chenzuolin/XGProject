//input背景改变 函数
inputBg();
//加载选择客户函数
clientName('#client');
//显示内容函数调用
showContent();

layui.use(['table', 'form', 'jquery', 'laydate'], function() {
	var table = layui.table;
	var form = layui.form;
	var $ = layui.jquery;
	var laydate = layui.laydate;

	laydate.render({
		elem: '#begin',
		type: 'datetime'
	});
	laydate.render({
		elem: '#finish',
		type: 'datetime'
	});

	form.on('submit(formDemo)', function(obj) {
		showContent();
		return false;
	});

	//表格工具条的监听
	table.on('tool(demo)', function(obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
		var data = obj.data; //获得当前行数据
		var layEvent = obj.event; //获得 lay-event 对应的值
		var tr = obj.tr; //获得当前行 tr 的DOM对象
		if(layEvent === 'detail') { //查看
			//id的字符串进行截取然后判断
			var type = (data.zongid).substring(0, 1);
			switch(type) {
				case "入":
					//当点击查看详情的时候触发
					var iframeopen = layer.open({
						type: 2,
						anim: 1,
						closeBtn: 1,
						title: ['日收费明细', 'font-size:16px;'],
						skin: 'layui-layer-blue',
						content: "/XGProject/inputSeed.do?flag=getXiangQing&ff=shoufei&iseedId=" +
							encodeURI(encodeURI(data.zid)),
						btn: ["关闭打开层"],
						btnAlign: "l"
					});
					layer.full(iframeopen);
					break;
				case "出":
					//当点击查看详情的时候触发
					var iframeopen = layer.open({
						type: 2,
						anim: 1,
						closeBtn: 1,
						title: ['日收费明细', 'font-size:16px;'],
						skin: 'layui-layer-blue',
						content: "/XGProject/exportSeed.do?flag=getXiangQing&ff=shoufei&eseedId=" +
							encodeURI(encodeURI(data.zid)),
						btn: ["关闭打开层"],
						btnAlign: "l"
					});
					layer.full(iframeopen);
					break;
				case "转":
					//当点击查看详情的时候触发
					var iframeopen = layer.open({
						type: 2,
						anim: 1,
						closeBtn: 1,
						title: ['日收费明细', 'font-size:16px;'],
						skin: 'layui-layer-blue',
						content: "/XGProject/shiftStockSeed.do?flag=getXiangQing&ff=shoufei&ssseedId=" +
							encodeURI(encodeURI(data.zid)),
						btn: ["关闭打开层"],
						btnAlign: "l"
					});
					layer.full(iframeopen);
					break;
			}
		}
	});

});

function showContent() {
	layui.use('jquery', function() {
		var $ = layui.jquery;
		var type = $("#type").val();
		switch(type) {
			case "出库订单":
				queryChuku(1);
				break;
			case "入库订单":
				queryRuku(1);
				break;
			case "过户订单":
				queryGuohu(1);
				break;
		}
	});
}
//查询出库订单的函数
function queryChuku(curr) {
	layui.use(['jquery', 'form', 'table', 'laypage', 'layer'], function() {
		var $ = layui.jquery;
		var form = layui.form;
		var table = layui.table;
		var laypage = layui.laypage;
		var layer = layui.layer;

		var d = {
			"begin": $("#begin").val(),
			"finish": $("#finish").val(),
			"jiancheng": $("#client").val(),
			"eseedClientAccounts": $("#jiesuan").val(),
			"pageNow": curr,
			"shoufeiren": $('#shoufeiren').val()
		};
		var data = queryAjax('/XGProject/exportSeed.do?flag=QueryJieSuan', d, 'json');
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
				layer.alert("无出库收费订单数据！", {
					icon: 5,
					anim: 4,
					closeBtn: 2,
					title: ['系统提示', 'font-size:16px;'],
					skin: 'layui-layer-blue',
				});
			}
			var chukufei = 0.0; // 应收的出库费
			var shishou = 0.0; // 实收的出库费
			var erci = 0.0; // 应收的二次作业费
			var shishouerci = 0.0; // 实收的二次作业费
			var xiazhan = 0.0; // 应收的下站费
			var shishouxia = 0.0; // 实收的下站费

			var obj = JSON.parse(data);

			for(var i = 0; i < obj.length; i++) {
				if(obj[0].result!=null){
					chukufei += parseFloat(obj[i].chukucost);
					shishou += parseFloat(obj[i].sscost);
					erci += parseFloat(obj[i].ercizuoye);
					shishouerci += parseFloat(obj[i].ercishishou);
					xiazhan += parseFloat(obj[i].xzcost);
					shishouxia += parseFloat(obj[i].ssxzcost);
				}
			}
			
			var sumJson = {
				"zongid":"",
				"kehudanhao":"当页合计：",
				"kehu":"",
				"time":"",
				"goods":"",
				"ycweight":"",
				"scweight":"",
				"yunshu":"",
				"jiesuan":"",
				"chukucost":chukufei,
				"sscost":shishou,
				"ercizuoye":erci,
				"ercishishou":shishouerci,
				"xzcost":xiazhan,
				"ssxzcost":shishouxia,
			};
			obj.push(sumJson);
			var w=$(parent.window).width()-245;//获取浏览器的宽，减去侧边栏的宽度  
			table.render({
				data: obj, //返回的json数据
				elem: '#showContent', //显示数据的容器
				height: 'full-210',
				width:w,
				cellMInWidth: 90,
				limit: 1000000, //默认显示的行数
				even: true,
				cols: [
					[{
						field: 'zongid', //列显示的值
						title: '订单编号', //显示的标题
						width: 130, //列的宽度
						sort: false, //是否产生排序
						fixed: true //是否是固定列宽
					}, {
						field: 'kehudanhao',
						title: '客户单号',
						width: 120,
						sort: false,
					}, {
						field: 'kehu',
						title: '客户名称',
						width: 120,
					}, {
						field: 'time',
						title: '订单发起时间',
						width: 170,
						sort: false,
						edit: false //是否可编辑
					}, {
						field: 'goods',
						title: '货物名称',
						width: 120,
						sort: false
					}, {
						field: 'ycweight',
						title: '应出重量',
						width: 100,
						sort: false
					}, {
						field: 'scweight',
						title: '实出重量',
						width: 100,
						sort: false
					}, {
						field: 'yunshu',
						title: '运输方式',
						width: 80,
						sort: false
					}, {
						field: 'jiesuan',
						title: '结算方式',
						width: 100,
						sort: false,
					}, {
						field: 'chukucost',
						title: '应收费用',
						width: 100,
						sort: false
					}, {
						field: 'sscost',
						title: '实收费用',
						width: 100,
						sort: false
					}, {
						field: 'ercizuoye',
						title: '二次作业费用',
						width: 100,
						sort: false,
					}, {
						field: 'ercishishou',
						title: '二次作业实收',
						width: 100,
						sort: false
					}, {
						field: 'xzcost',
						title: '下站费',
						width: 100,
						sort: false,
					}, {
						field: 'ssxzcost',
						title: '实收下站费',
						width: 100,
						sort: false
					}, {
						fixed: 'right',
						width: 120,
						align: 'center',
						toolbar: '#barDemo'
					}]
				],
				done:function(){
					$(".layui-table-view div.layui-table-body table tbody tr:last-child").css({
						"font-weight": "bold",
						"color": "red"
					});
				}
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
							queryChuku(obj.curr);
						}

					}
				});
			}
		}
	});
}
//查询入库订单方法
function queryRuku(curr) {
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
			"iseedClientAccounts": $("#jiesuan").val(),
			"pageNow": curr,
			"shoufeiren": $('#shoufeiren').val()
		};
		var data = queryAjax('/XGProject/inputSeed.do?flag=QueryJieSuan', d, 'json');
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
				layer.alert("无入库收费订单数据！", {
					icon: 5,
					anim: 4,
					closeBtn: 2,
					title: ['系统提示', 'font-size:16px;'],
					skin: 'layui-layer-blue',
				});
			}
			var rukufei = 0.0; // 入库的应收费用
			var rukushishou = 0.0; // 入库的实收费用

			var obj = JSON.parse(data);

			for(var i = 0; i < obj.length; i++) {
				if(obj[0].result!=null){
					rukufei += parseFloat(obj[i].chukucost);
					rukushishou += parseFloat(obj[i].sscost);
				}
			}
			var sumJson = {
				"zongid":"",
				"kehudanhao":"当页合计：",
				"kehu":"",
				"time":"",
				"goods":"",
				"ycweight":"",
				"scweight":"",
				"yunshu":"",
				"jiesuan":"",
				"chukucost":rukufei,
				"sscost":rukushishou,
			};
			obj.push(sumJson);
			 var w=$(parent.window).width()-245;//获取浏览器的宽，减去侧边栏的宽度  
			table.render({
				data: obj, //返回的json数据
				elem: '#showContent', //显示数据的容器
				width:w,
				height: 'full-210',
				cellMInWidth: 90,
				limit: 1000000, //默认显示的行数
				even: true,
				cols: [
					[{
						field: 'zongid', //列显示的值
						title: '订单编号', //显示的标题
						width: 180, //列的宽度
						sort: false, //是否产生排序
						fixed: true //是否是固定列宽
					}, {
						field: 'kehudanhao',
						title: '客户单号',
						width: 150,
						sort: false,
					}, {
						field: 'kehu',
						title: '客户名称',
						width: 130,
					}, {
						field: 'time',
						title: '订单发起时间',
						width: 190,
						sort: false,
						edit: false //是否可编辑
					}, {
						field: 'goods',
						title: '货物名称',
						width: 120,
						sort: false
					}, {
						field: 'ycweight',
						title: '应收重量',
						width: 120,
						sort: false
					}, {
						field: 'scweight',
						title: '实收重量',
						width: 120,
						sort: false
					}, {
						field: 'yunshu',
						title: '运输方式',
						width: 80,
						sort: false
					}, {
						field: 'jiesuan',
						title: '结算方式',
						width: 100,
						sort: false,
					}, {
						field: 'chukucost',
						title: '应收费用',
						width: 100,
						sort: false
					}, {
						field: 'sscost',
						title: '实收费用',
						width: 100,
						sort: false
					}, {
						fixed: 'right',
						width: 120,
						align: 'center',
						toolbar: '#barDemo'
					}]
				], //设置表头
				done:function(){
					$(".layui-table-view div.layui-table-body table tbody tr:last-child").css({
						"font-weight": "bold",
						"color": "red"
					});
				}
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
							queryRuku(obj.curr);
						}

					}
				});
			}
		}
	});
}

//查询过户的方法
function queryGuohu(curr) {
	layui.use(['jquery', 'form', 'table', 'laypage', 'layer'], function() {
		var $ = layui.jquery;
		var form = layui.form;
		var table = layui.table;
		var laypage = layui.laypage;
		var layer = layui.layer;

		var d = {
			"begin": $("#begin").val(),
			"finish": $("#finish").val(),
			"jiancheng": $("#client").val(),
			"ssseedClientAccounts": $("#jiesuan").val(),
			"pageNow": curr,
			"shoufeiren": $('#shoufeiren').val()
		};
		var data = queryAjax('/XGProject/shiftStockSeed.do?flag=QueryJieSuan', d, 'json');
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
				layer.alert("无过户收费订单数据！", {
					icon: 5,
					anim: 4,
					closeBtn: 2,
					title: ['系统提示', 'font-size:16px;'],
					skin: 'layui-layer-blue',
				});
			}

			var guohufei = 0.0; // 应收过户费
			var shishouguohu = 0.0; // 实收过户费
			var xiazhan = 0.0; // 应收下站费
			var shishouxiazhan = 0.0; // 实收下站费

			var obj = JSON.parse(data);

			for(var i = 0; i < obj.length; i++) {
				if(obj[0].result!=null){
					guohufei += parseFloat(obj[i].cost);
					shishouguohu += parseFloat(obj[i].sscost);
					xiazhan += parseFloat(obj[i].xzcost);
					shishouxiazhan += parseFloat(obj[i].ssxzcost);
				}
			}
			
			var sumJson = {
				"zongid":"",
				"kehudanhao":"当页合计：",
				"zhuanchu":"",
				"zhuanru":"",
				"time":"",
				"goods":"",
				"weight":"",
				"jiesuan":"",
				"cost":guohufei,
				"sscost":shishouguohu,
				"xzcost":xiazhan,
				"ssxzcost":shishouxiazhan,
			};
			obj.push(sumJson);
			 var w=$(parent.window).width()-245;//获取浏览器的宽，减去侧边栏的宽度  
			table.render({
				data: obj, //返回的json数据
				width:w,
				elem: '#showContent', //显示数据的容器
				height: 'full-210',
				cellMInWidth: 90,
				limit: 1000000, //默认显示的行数
				even: true,
				cols: [
					[{
						field: 'zongid', //列显示的值
						title: '订单编号', //显示的标题
						width: 180, //列的宽度
						sort: false, //是否产生排序
						fixed: true //是否是固定列宽
					}, {
						field: 'kehudanhao',
						title: '客户单号',
						width: 150,
						sort: false,
					}, {
						field: 'zhuanchu',
						title: '转出单位',
						width: 140,
					}, {
						field: 'zhuanru',
						title: '转入单位',
						width: 140,
						sort: false,
						edit: false //是否可编辑
					}, {
						field: 'time',
						title: '过户时间',
						width: 160,
						sort: false
					}, {
						field: 'goods',
						title: '货物名称',
						width: 120,
						sort: false
					}, {
						field: 'weight',
						title: '过户重量',
						width: 120,
						sort: false
					}, {
						field: 'jiesuan',
						title: '结算方式',
						width: 100,
						sort: false
					}, {
						field: 'cost',
						title: '应收费用',
						width: 80,
						sort: false
					}, {
						field: 'sscost',
						title: '实收费用',
						width: 100,
						sort: false,
					}, {
						field: 'xzcost',
						title: '下站费',
						width: 100,
						sort: false
					}, {
						field: 'ssxzcost',
						title: '实收下站费',
						width: 100,
						sort: false
					}, {
						fixed: 'right',
						width: 120,
						align: 'center',
						toolbar: '#barDemo'
					}]
				], //设置表头
				done:function(){
					$(".layui-table-view div.layui-table-body table tbody tr:last-child").css({
						"font-weight": "bold",
						"color": "red"
					});
				}
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
							queryGuohu(obj.curr);
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