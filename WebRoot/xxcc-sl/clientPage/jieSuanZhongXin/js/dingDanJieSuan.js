layui.use(['laydate', 'form', 'jquery', 'layer', 'table'], function() {
	var laydate = layui.laydate;
	var form = layui.form;
	var $ = layui.jquery;
	var table = layui.table;
	var layer = layui.layer;

	//执行一个laydate实例
	laydate.render({
		elem: '#begin', //指定元素，开始时间
		type: "datetime"
	});
	laydate.render({
		elem: '#finish', //指定元素，结束时间
		type: "datetime"
	});

	//form监听提交,当单机【立即提交的时候调用】
	form.on('submit(formDemo)', function(data) {
		show(data.field.yewu);
		return false;
	});

	//对应工具条进行监听
	table.on("tool(demo)", function(obj) {
		var data = obj.data; //获得当前行数据
		var layEvent = obj.event; //获得 lay-event 对应的值
		if(layEvent == "detail") {
			var type = (data.zongId).substring(0, 1);
			switch(type) {
				case "入":
					document.location.href = "/XGProject/inputSeed.do?flag=getXiangQing&ff=client&iseedId=" +
					encodeURI(encodeURI(data.seedId));
					break;
				case "出":
					document.location.href = "/XGProject/exportSeed.do?flag=getXiangQing&ff=client&eseedId=" +
					encodeURI(encodeURI(data.seedId));
					break;
				case "转":
					document.location.href = "/XGProject/shiftStockSeed.do?flag=getXiangQing&ff=client&ssseedId=" +
					encodeURI(encodeURI(data.seedId));
					break;
			}
		}
	});
});

//当页面加载的时候调用显示内容的方法
show(document.getElementById("yewu").value);

function show(type) {
	switch(type) {
		case "入库订单":
			queryRuku(1, 20);
			break;
		case "出库订单":
			queryChuku(1, 20);
			break;
		case "过户订单":
			queryGuohu(1, 20);
			break;
	}
	return false;
}

//查询入库订单的方法
function queryRuku(curr, pageRow) {
	layui.use(['element', 'table', 'laypage', 'jquery', 'layer'], function() {
		var table = layui.table,
			laypage = layui.laypage,
			layer = layui.layer,
			element = layui.element,
			$ = layui.jquery;
		var loadIndex = layer.load(1); //风格1的加载
		var u = "/XGProject/inputSeed.do?flag=getJieSuanAllTj";
		var d = {
			"startTime": $("#begin").val(),
			"endTime": $("#finish").val(),
			"kehuDanhao": $("#kehudanaho").val(),
			"goodsName": $("#goodsName").val(),
			"pageNow": curr,
			"jiesuanType": $("#jiesuan").val(),
			"pageRow": pageRow
		};
		var data = queryAjax(u, d);
		layer.close(loadIndex);
		if(data == 'error') {
			layer.alert("获取入库订单数据错误！", {
				icon: 2,
				closeBtn: 2,
				anim: 4,
				title: ['系统提示', 'font-size:16px;'],
				skin: 'layui-layer-blue',
			});
		} else {
			var jobj = JSON.parse(data);
			if(data == null || data == undefined || data == "" || data == "[]") {
				layer.alert("无入库订单记录！", {
					icon: 5,
					closeBtn: 2,
					anim: 4,
					title: ['系统提示', 'font-size:16px;'],
					skin: 'layui-layer-blue',
				});
				return false;
			}
			var w=$(parent.window).width()-250;//获取浏览器的宽，减去侧边栏的宽度  
			table.render({
				data: jobj, //返回的json数据
				elem: '#showContent', //显示数据的容器
				width:w,
				height: 'full-210',
				limit: 1000000, //默认显示的行数
				cellMinWidth:150,
				even: true,
				cols: [
					[{
						field: 'zongId', //列显示的值
						title: '订单编号', //显示的标题
						width: 160, //列的宽度
						sort: false, //是否产生排序
						fixed: true //是否是固定列宽
					}, {
						field: 'clientNumber',
						title: '客户单号',
						width: 160,
						sort: false,
					}, {
						field: 'jiesuanType',
						title: '结算方式',
						width: 130,
						sort: false,
					}, {
						field: 'yewu',
						title: '业务类型',
						width: 130,
						sort: false,
					}, {
						field: 'carryType',
						title: '运输方式',
						width: 130,
					}, {
						field: 'sijiName',
						title: '司机姓名',
						width: 130,
						sort: false,
						edit: false //是否可编辑
					}, {
						field: 'goodsName',
						title: '货物名称',
						width: 160,
						sort: false
					}, {
						field: 'realityWeight',
						title: '实入重量',
						width: 140,
						sort: false
					}, {
						field: 'shouldCost',
						title: '应收费用',
						width: 140,
						sort: false
					}, {
						field: 'realityCost',
						title: '实收费用',
						width: 180,
						sort: false
					}, {
						fixed: 'right',
						width: 180,
						align: 'center',
						toolbar: '#barDemo'
					}]
				]
			});

			//如果是第一次调用此方法或者是当点击查询的时候进行执行下面分页的方法
			if(parseInt(curr) == 1 && parseInt(pageRow) == 20) {
				//分页的渲染
				laypage.render({
					elem: 'paging', //显示分页的容器
					count: parseInt(JSON.parse(data)[0].pageCount) * 20, //显示的总条数
					limit: 20,
					layout: ['prev', 'page', 'next', 'skip', 'limit'],
					limits: [20, 40, 60, 100, 150], //选择显示的行数
					jump: function(obj, first) {
						//obj包含了当前分页的所有参数，比如：
						console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
						console.log(obj.limit); //得到每页显示的条数
						//如果不是第一次执行就调用显示数据的方法，例如当点击下一页或者上一页的时候调用
						if(!first) {
							queryRuku(obj.curr, obj.limit);
						}
					}
				});
			}
		}
	});
}

//查询出库订单的方法
function queryChuku(curr, pageRow) {
	layui.use(['element', 'table', 'laypage', 'jquery', 'layer'], function() {
		var table = layui.table,
			laypage = layui.laypage,
			layer = layui.layer,
			element = layui.element,
			$ = layui.jquery;
		var loadIndex = layer.load(1); //风格1的加载
		var u = "/XGProject/exportSeed.do?flag=getChuJiesuanAppTj";
		var d = {
			"startTime": $("#begin").val(),
			"endTime": $("#finish").val(),
			"kehuDanhao": $("#kehudanaho").val(),
			"goodsName": $("#goodsName").val(),
			"pageNow": curr,
			"jiesuanType": $("#jiesuan").val(),
			"pageRow": pageRow
		};
		var data = queryAjax(u, d);
		layer.close(loadIndex);
		if(data == 'error') {
			layer.alert("获取出库订单数据错误！", {
				icon: 2,
				closeBtn: 2,
				anim: 4,
				title: ['系统提示', 'font-size:16px;'],
				skin: 'layui-layer-blue',
			});
		} else {
			var jobj = JSON.parse(data);
			if(data == null || data == undefined || data == "" || data == "[]") {
				layer.alert("无出库订单记录！", {
					icon: 5,
					closeBtn: 2,
					anim: 4,
					title: ['系统提示', 'font-size:16px;'],
					skin: 'layui-layer-blue',
				});
				return false;
			}
			var w=$(parent.window).width()-250;//获取浏览器的宽，减去侧边栏的宽度  
			table.render({
				data: jobj, //返回的json数据
				elem: '#showContent', //显示数据的容器
				height: 'full-210',
				width:w,
				limit: 1000000, //默认显示的行数
				even: true,
				cols: [
					[{
						field: 'zongId', //列显示的值
						title: '订单编号', //显示的标题
						width: 160, //列的宽度
						sort: false, //是否产生排序
						fixed: true //是否是固定列宽
					}, {
						field: 'clientNumber',
						title: '客户单号',
						width: 160,
						sort: false,
					}, {
						field: 'jiesuanType',
						title: '结算方式',
						width: 130,
						sort: false,
					}, {
						field: 'yewu',
						title: '业务类型',
						width: 130,
						sort: false,
					}, {
						field: 'carryType',
						title: '运输方式',
						width: 130,
					}, {
						field: 'sijiName',
						title: '司机姓名',
						width: 130,
						sort: false,
						edit: false //是否可编辑
					}, {
						field: 'goodsName',
						title: '货物名称',
						width: 160,
						sort: false
					}, {
						field: 'realityWeight',
						title: '实出重量',
						width: 130,
						sort: false
					}, {
						field: 'shouldCost',
						title: '应收费用',
						width: 140,
						sort: false
					}, {
						field: 'realityCost',
						title: '实收费用',
						width: 180,
						sort: false
					}, {
						fixed: 'right',
						width: 180,
						align: 'center',
						toolbar: '#barDemo'
					}]
				]
			});

			//如果是第一次调用此方法或者是当点击查询的时候进行执行下面分页的方法
			if(parseInt(curr) == 1 && parseInt(pageRow) == 20) {
				//分页的渲染
				laypage.render({
					elem: 'paging', //显示分页的容器
					count: parseInt(JSON.parse(data)[0].pageCount) * 20, //显示的总条数
					limit: 20,
					layout: ['prev', 'page', 'next', 'skip', 'limit'],
					limits: [20, 40, 60, 100, 150], //选择显示的行数
					jump: function(obj, first) {
						//obj包含了当前分页的所有参数，比如：
						console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
						console.log(obj.limit); //得到每页显示的条数
						//如果不是第一次执行就调用显示数据的方法，例如当点击下一页或者上一页的时候调用
						if(!first) {
							queryChuku(obj.curr, obj.limit);
						}
					}
				});
			}
		}
	});
}

//查询过户订单的方法
function queryGuohu(curr, pageRow) {
	layui.use(['element', 'table', 'laypage', 'jquery', 'layer'], function() {
		var table = layui.table,
			laypage = layui.laypage,
			layer = layui.layer,
			element = layui.element,
			$ = layui.jquery;
		var loadIndex = layer.load(1); //风格1的加载
		var u = "/XGProject/shiftStockSeed.do?flag=getGuoJiesuanTj";
		var d = {
			"startTime": $("#begin").val(),
			"endTime": $("#finish").val(),
			"kehuDanhao": $("#kehudanaho").val(),
			"goodsName": $("#goodsName").val(),
			"pageNow": curr,
			"jiesuanType": $("#jiesuan").val(),
			"pageRow": pageRow
		};
		var data = queryAjax(u, d);
		layer.close(loadIndex);
		if(data == 'error') {
			layer.alert("获取过户订单数据错误！", {
				icon: 2,
				closeBtn: 2,
				anim: 4,
				title: ['系统提示', 'font-size:16px;'],
				skin: 'layui-layer-blue',
			});
		} else {
			var jobj = JSON.parse(data);
			if(data == null || data == undefined || data == "" || data == "[]") {
				layer.alert("无过户订单记录！", {
					icon: 5,
					closeBtn: 2,
					anim: 4,
					title: ['系统提示', 'font-size:16px;'],
					skin: 'layui-layer-blue',
				});
				return false;
			}
			var w=$(parent.window).width()-250;//获取浏览器的宽，减去侧边栏的宽度  
			table.render({
				data: jobj, //返回的json数据
				elem: '#showContent', //显示数据的容器
				height: 'full-210',
				limit: 1000000, //默认显示的行数
				width:w,
				even: true,
				cols: [
					[{
						field: 'zongId', //列显示的值
						title: '订单编号', //显示的标题
						width: 160, //列的宽度
						sort: false, //是否产生排序
						fixed: true //是否是固定列宽
					}, {
						field: 'clientNumber',
						title: '客户单号',
						width: 160,
						sort: false,
					}, {
						field: 'jiesuanType',
						title: '结算方式',
						width: 130,
						sort: false,
					}, {
						field: 'yewu',
						title: '业务类型',
						width: 130,
						sort: false,
					}, {
						field: 'goodsName',
						title: '货物名称',
						width: 160,
						sort: false
					}, {
						field: 'realityWeight',
						title: '实过重量',
						width: 140,
						sort: false
					}, {
						field: 'shouldCost',
						title: '应收费用',
						width: 160,
						sort: false
					}, {
						field: 'realityCost',
						title: '实收费用',
						width: 180,
						sort: false
					}, {
						fixed: 'right',
						width: 180,
						align: 'center',
						toolbar: '#barDemo'
					}]
				]
			});

			//如果是第一次调用此方法或者是当点击查询的时候进行执行下面分页的方法
			if(parseInt(curr) == 1 && parseInt(pageRow) == 20) {
				//分页的渲染
				laypage.render({
					elem: 'paging', //显示分页的容器
					count: parseInt(JSON.parse(data)[0].pageCount) * 20, //显示的总条数
					limit: 20,
					layout: ['prev', 'page', 'next', 'skip', 'limit'],
					limits: [20, 40, 60, 100, 150], //选择显示的行数
					jump: function(obj, first) {
						//obj包含了当前分页的所有参数，比如：
						console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
						console.log(obj.limit); //得到每页显示的条数
						//如果不是第一次执行就调用显示数据的方法，例如当点击下一页或者上一页的时候调用
						if(!first) {
							queryGuohu(obj.curr, obj.limit);
						}
					}
				});
			}
		}
	});
}

//ajax请求方法
function queryAjax(u, d) {
	var data = "";
	layui.use(['jquery'], function() {
		var $ = layui.jquery;
		$.ajax({
			type: "post",
			url: u,
			async: false,
			data: d,
			dataType: "json",
			success: function(obj) {
				data = JSON.stringify(obj);
			},
			error: function() {
				data = "error";
			}
		});
		return data;
	});
	return data;
}