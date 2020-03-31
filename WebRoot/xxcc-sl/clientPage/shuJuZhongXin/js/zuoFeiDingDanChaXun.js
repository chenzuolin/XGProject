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
		showContent(1);
		return false;
	});

	//表格工具条的监听
	table.on('tool(demo)', function(obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
		var data = obj.data; //获得当前行数据
		var layEvent = obj.event; //获得 lay-event 对应的值
		var tr = obj.tr; //获得当前行 tr 的DOM对象
		if(layEvent === 'detail') { //查看
			//id的字符串进行截取然后判断
			var type = (data.id).substring(0, 1);
			switch(type) {
				case "入":
					document.location.href = "/XGProject/inputSeed.do?flag=getXiangQing&ff=client&iseedId=" +
						encodeURI(encodeURI(data.zid));
					break;
				case "出":
					document.location.href = "/XGProject/exportSeed.do?flag=getXiangQing&ff=client&eseedId=" +
						encodeURI(encodeURI(data.zid));
					break;
				case "转":
					document.location.href = "/XGProject/shiftStockSeed.do?flag=getXiangQing&ff=client&ssseedId=" +
						encodeURI(encodeURI(data.zid));
					break;
			}
		}
	});
});

//当页面加载的时候调用显示内容的方法
showContent(1);

//判断加载不容方法的函数，curr为当前页
function showContent(curr) {
	layui.use(['jquery', 'layer'], function() {
		var $ = layui.jquery,
			layer = layui.layer;
		var type = $("#indent-type").val();
		switch(type) {
			case '出库订单':
				queryChuKu(curr, 30);
				break;
			case '入库订单':
				queryRuKu(curr, 30);
				break;
			case '过户订单':
				queryGuoHu(curr, 30);
				break;
		}
		return false;
	});
	return false;
}

//查询对应的入库订单的函数
function queryRuKu(curr, pageRow) {
	layui.use(['element', 'table', 'laypage', 'jquery', 'layer'], function() {
		var table = layui.table,
			laypage = layui.laypage,
			layer = layui.layer,
			element = layui.element,
			$ = layui.jquery;
		//用ajax访问数据
		var begin = $("#begin").val(); //获得起始日期中的值
		var finish = $("#finish").val(); //获得结束日期中的值
		var client_number = $("#client-number").val(); //获得客户单号文本框中的值
		var goods = $("#goods").val(); //获得货物资料中的值

		var loadIndex = layer.load(1); //风格1的加载
		var url = "/XGProject/inputSeed.do?flag=getDingdanAppTjPash";
		var d = {
			"startTime": begin,
			"endTime": finish,
			"kehuDanhao": client_number,
			"goodsName": goods,
			"pageNow": curr,
			"pageRow": pageRow
		};
		var data = ajaxQuery(url, d);
		layer.close(loadIndex);
		if(data != "error") {
			var jdata = JSON.parse(data);
			if(jdata == null || jdata[0].result == null) {
				layer.alert("无入库订单记录！", {
					icon: 5,
					closeBtn: 2,
					anim: 4,
					title: ['系统提示', 'font-size:16px;'],
					skin: 'layui-layer-blue',
				});
			}
			$.each(jdata, function(i, obj) {
				obj.xuhao = (i + 1);
			});
			var w = $(parent.window).width() - 250; //获取浏览器的宽，减去侧边栏的宽度
			table.render({
				data: jdata, //返回的json数据
				elem: '#showContent', //显示数据的容器
				height: 'full-210',
				width: w,
				limit: 1000000, //默认显示的行数
				cellMinWidth: 100,
				even: true,
				cols: [
					[{
						field: 'xuhao', //列显示的值
						title: '序号', //显示的标题
						width: 60, //列的宽度
						sort: false, //是否产生排序
						fixed: true //是否是固定列宽
					}, {
						field: 'id',
						title: '订单编号',
						sort: false,
					}, {
						field: 'zhuangtai',
						title: '订单状态',
						sort: false,
						templet: '#titleTpl'
					}, {
						field: 'huozhu',
						title: '货主',
						sort: false,
						edit: false //是否可编辑
					}, {
						field: 'yunshu',
						title: '运输方式',
						sort: false
					}, {
						field: 'huowu',
						title: '货物',
						width: 140,
						sort: false
					}, {
						field: 'kehudanhao',
						title: '客户单号',
						width: 140,
						sort: false
					}, {
						field: 'zhongliang',
						title: '应收重量',
						sort: false
					}, {
						field: 'shichuweight',
						title: '实收重量',
						sort: false
					}, {
						field: 'faqishijian',
						title: '发起时间',
						sort: false
					}, {
						fixed: 'right',
						width: 130,
						align: 'center',
						toolbar: '#barDemo'
					}]
				]
			});
			//如果是第一次调用此方法或者是当点击查询的时候进行执行下面分页的方法
			if(parseInt(curr) == 1) {
				//分页的渲染
				laypage.render({
					elem: 'paging', //显示分页的容器
					count: parseInt(jdata[0].pageCount) * 30, //显示的总条数
					limit: 30,
					layout: ['prev', 'page', 'next', 'skip', 'limit'],
					limits: [30, 50, 80, 100],
					jump: function(obj, first) {
						//obj包含了当前分页的所有参数，比如：
						console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
						console.log(obj.limit); //得到每页显示的条数
						//如果不是第一次执行就调用显示数据的方法，例如当点击下一页或者上一页的时候调用
						if(!first) {
							queryRuKu(obj.curr, obj.limit);
						}
					}
				});
			}
		}
	});

}

//查询对应的出库订单的函数
function queryChuKu(curr, pageRow) {
	layui.use(['table', 'laypage', 'jquery', 'layer'], function() {
		var table = layui.table,
			laypage = layui.laypage,
			layer = layui.layer,
			$ = layui.jquery;
		//用ajax访问数据
		var begin = $("#begin").val(); //获得起始日期中的值
		var finish = $("#finish").val(); //获得结束日期中的值
		var client_number = $("#client-number").val(); //获得客户单号文本框中的值
		var goods = $("#goods").val(); //获得货物资料中的值

		var loadIndex = layer.load(1); //风格1的加载

		var url = "/XGProject/exportSeed.do?flag=getDingdanAppTjPash";
		var d = {
			"startTime": begin,
			"endTime": finish,
			"kehuDanhao": client_number,
			"goodsName": goods,
			"pageNow": curr,
			"pageRow": pageRow
		};
		var data = ajaxQuery(url, d);
		layer.close(loadIndex);
		if(data != "error") {
			var jdata = JSON.parse(data);
			if(jdata == null || jdata[0].result == null) {
				layer.alert("无出库订单记录！", {
					icon: 5,
					closeBtn: 2,
					anim: 4,
					title: ['系统提示', 'font-size:16px;'],
					skin: 'layui-layer-blue',
				});
			}
			$.each(jdata, function(i, obj) {
				obj.xuhao = (i + 1);
			});
			var w = $(parent.window).width() - 250; //获取浏览器的宽，减去侧边栏的宽度
			table.render({
				data: jdata, //返回的json数据
				elem: '#showContent', //显示数据的容器
				height: 'full-210',
				limit: 1000000, //默认显示的行数
				cellMinWidth: 100,
				width: w,
				even: true,
				cols: [
					[{
						field: 'xuhao', //列显示的值
						title: '序号', //显示的标题
						width: 60, //列的宽度
						sort: false, //是否产生排序
						fixed: true //是否是固定列宽
					}, {
						field: 'id',
						title: '订单编号',
						sort: false,
					}, {
						field: 'zhuangtai',
						title: '订单状态',
						sort: false,
						templet: '#titleTp2'
					}, {
						field: 'huozhu',
						title: '货主',
						sort: false,
						edit: false //是否可编辑
					}, {
						field: 'yunshu',
						title: '运输方式',
						sort: false
					}, {
						field: 'huowu',
						title: '货物',
						sort: false
					}, {
						field: 'kehudanhao',
						title: '客户单号',
						sort: false
					}, {
						field: 'zhongliang',
						title: '应出重量',
						width: 140,
						sort: false
					}, {
						field: 'shichuweight',
						title: '实出重量',
						sort: false
					}, {
						field: 'faqishijian',
						title: '发起时间',
						sort: false
					}, {
						fixed: 'right',
						width: 130,
						align: 'center',
						toolbar: '#barDemo'
					}]
				]
			});

			//如果是第一次调用此方法或者是当点击查询的时候进行执行下面分页的方法
			if(parseInt(curr) == 1) {
				//分页的渲染
				laypage.render({
					elem: 'paging', //显示分页的容器
					count: parseInt(jdata[0].pageCount) * 30, //显示的总条数
					limit: 30,
					layout: ['prev', 'page', 'next', 'skip', 'limit'],
					limits: [30, 50, 80, 100],
					jump: function(obj, first) {
						//obj包含了当前分页的所有参数，比如：
						console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
						console.log(obj.limit); //得到每页显示的条数
						//如果不是第一次执行就调用显示数据的方法，例如当点击下一页或者上一页的时候调用
						if(!first) {
							queryChuKu(obj.curr, obj.limit);
						}

					}
				});
			}
		}
	});
}

//查询对应的过户订单的函数
function queryGuoHu(curr, pageRow) {
	layui.use(['element', 'table', 'laypage', 'jquery', 'layer'], function() {
		var table = layui.table,
			laypage = layui.laypage,
			layer = layui.layer,
			element = layui.element,
			$ = layui.jquery;
		//用ajax访问数据
		var begin = $("#begin").val(); //获得起始日期中的值
		var finish = $("#finish").val(); //获得结束日期中的值
		var client_number = $("#client-number").val(); //获得客户单号文本框中的值
		var goods = $("#goods").val(); //获得货物资料中的值

		var loadIndex = layer.load(1); //风格1的加载

		var url = "/XGProject/shiftStockSeed.do?flag=getDingdanAppTjPash";
		var d = {
			"startTime": begin,
			"endTime": finish,
			"kehuDanhao": client_number,
			"goodsName": goods,
			"pageNow": curr,
			"pageRow": pageRow
		};
		var data = ajaxQuery(url, d);
		layer.close(loadIndex);
		if(data != "error") {
			var jdata = JSON.parse(data);
			if(jdata == null || jdata[0].result == null) {
				layer.alert("无过户订单记录！", {
					icon: 5,
					closeBtn: 2,
					anim: 4,
					title: ['系统提示', 'font-size:16px;'],
					skin: 'layui-layer-blue',
				});
			}
			$.each(jdata, function(i, obj) {
				obj.xuhao = (i + 1);
			});
			var w = $(parent.window).width() - 250; //获取浏览器的宽，减去侧边栏的宽度
			table.render({
				data: jdata, //返回的json数据
				elem: '#showContent', //显示数据的容器
				height: 'full-210',
				width: w,
				limit: 1000000, //默认显示的行数
				cellMinWidth: 100,
				even: true,
				cols: [
					[{
						field: 'xuhao', //列显示的值
						title: '序号', //显示的标题
						width: 60, //列的宽度
						sort: false, //是否产生排序
						fixed: true //是否是固定列宽
					}, {
						field: 'id',
						title: '订单编号',
						sort: false,
					}, {
						field: 'zhuangtai',
						title: '订单状态',
						sort: false,
						templet: '#titleTp3'
					}, {
						field: 'zhuanchu',
						title: '转出单位',
					}, {
						field: 'zhuanru',
						title: '转入单位',
						sort: false,
						edit: false //是否可编辑
					}, {
						field: 'huowu',
						title: '货物',
						sort: false
					}, {
						field: 'kehudanhao',
						title: '客户单号',
						sort: false
					}, {
						field: 'faqishijian',
						title: '发起时间',
						sort: false
					}, {
						field: 'zhongliang',
						title: '转出重量',
						sort: false
					}, {
						field: 'jieguo',
						title: '审核结果',
						sort: false
					}, {
						fixed: 'right',
						width: 130,
						align: 'center',
						toolbar: '#barDemo'
					}]
				]
			});

			//如果是第一次调用此方法或者是当点击查询的时候进行执行下面分页的方法
			if(parseInt(curr) == 1) {
				//分页的渲染
				laypage.render({
					elem: 'paging', //显示分页的容器
					count: parseInt(jdata[0].pageCount) * 30, //显示的总条数
					limit: 30,
					layout: ['prev', 'page', 'next', 'skip', 'limit'],
					limits: [30, 50, 80, 100],
					jump: function(obj, first) {
						//obj包含了当前分页的所有参数，比如：
						console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
						console.log(obj.limit); //得到每页显示的条数
						//如果不是第一次执行就调用显示数据的方法，例如当点击下一页或者上一页的时候调用
						if(!first) {
							queryGuoHu(obj.curr, obj.limit);
						}

					}
				});
			}
		}
	});
}

//对应的ajax访问的封装方法,u：路径,b:起始日期，f，结束日期，c：客户简称,i:订单编号,cn:客户单号,g:货物资料,p;当前页
function ajaxQuery(u, d) {
	layui.use('jquery', function() {
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
	});
	return data;
}