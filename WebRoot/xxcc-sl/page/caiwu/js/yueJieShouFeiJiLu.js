inputBg();

clientName('#client');

showContent(1, 20);

layui.use(['table', 'form', 'jquery', 'laydate', 'layer'], function() {
	var table = layui.table;
	var form = layui.form;
	var $ = layui.jquery;
	var laydate = layui.laydate;
	var layer = layui.layer;

	laydate.render({
		elem: '#begin',
		type: "datetime"
	});
	laydate.render({
		elem: '#finish',
		type: "datetime"
	});

	$("#daochu").click(function() {
		document.location.href = "/XGProject/xxcc-sl/page/caiwu/shoufeijiludaochu.jsp";
	});

	form.on('submit(formDemo)', function(obj) {
		showContent(1, 20);
		return false;
	});
	var xiazhanId;
	table.on('tool(showcontent)', function(obj) {
		var data = obj.data; //获得当前行数据
		var layEvent = obj.event; //获得 lay-event 对应的值
		var tr = obj.tr; //获得当前行 tr 的DOM对象
		if(layEvent == "edit") {
			//当点击查看详情的时候触发
			//当点击查看详情的时候触发
			if(data.id!="" && data.id!=null && data.id!=undefined){
				var iframeopen = layer.open({
					type: 2,
					anim: 1,
					closeBtn: 1,
					title: ['月结收费明细', 'font-size:16px;'],
	                skin: 'layui-layer-blue',
					content: "/XGProject/accounts.do?flag=getAccountsId&accountsId=" + data.id,
					btn: ["关闭打开层"],
					btnAlign: "l"
				});
				layer.full(iframeopen);
			}
		}
	});
});

function showContent(curr, pageRow) {
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
			"pageNow": curr,
			"pageRow": pageRow,
			"shoufeiren":$("#shoufeiren").val()
		};
		var data = queryAjax('/XGProject/accounts.do?flag=getAccountsAll', d, 'json');
		if(data == "error") {
			layer.alert("获取数据错误！", {
				icon: 2,
				anim: 4,
				closeBtn: 2,
				title: ['系统提示', 'font-size:16px;'],
                skin: 'layui-layer-blue',
			});
		} else {
			var obj = JSON.parse(data);
			if(obj == null || obj == "" || obj == undefined || obj == []) {
				layer.alert("无月结收费记录！", {
					icon: 5,
					anim: 4,
					closeBtn: 2,
					title: ['系统提示', 'font-size:16px;'],
	                skin: 'layui-layer-blue',
				});
				var sumJson={
					"clientName":"",
					"begin":""
				};
				obj.push(sumJson);
			}
			var w=$(parent.window).width()-245;//获取浏览器的宽，减去侧边栏的宽度  
			table.render({
				data: obj, //返回的json数据
				elem: '#showContent', //显示数据的容器
				width:w,
				height: 'full-210',
				limit: 1000000, //默认显示的行数
				even: true,
				cols: [
					[{
						field: 'clientName', //列显示的值
						title: '客户名称', //显示的标题
						width: 150, //列的宽度
						sort: false, //是否产生排序
					}, {
						field: 'begin',
						title: '起始日期',
						width: 200,
						sort: false,
					}, {
						field: 'finish',
						title: '结束日期',
						width: 200,
					}, {
						field: 'count',
						title: '轧账费用合计',
						width: 160,
						sort: false,
						edit: false //是否可编辑
					}, {
						field: 'feilv',
						title: '滞纳金费率',
						width: 100,
						sort: false
					}, {
						field: 'zhinajin',
						title: '应收滞纳金',
						width: 100,
						sort: false
					}, {
						field: 'zong',
						title: '总费用',
						width: 180,
						sort: false
					}, {
						field: 'shoufeiren',
						title: '收费人',
						width: 120,
						sort: false
					}, {
						field: 'shoufeitime',
						title: '收费时间',
						width: 200,
						sort: false,
					}, {
						fixed: 'right',
						width: 150,
						align: 'center',
						toolbar: '#barDemo'
					}]
				] //设置表头
			});
			if(parseInt(curr) == 1 && parseInt(pageRow) == 20) {
				//分页的渲染
				laypage.render({
					elem: 'paging', //显示分页的容器
					count: parseInt(obj[0].pageCount) * pageRow, //显示的总条数
					limit: pageRow,
					layout: ['prev', 'page', 'next', 'skip', 'limit'],
					limits: [20, 40, 60, 80, 100],
					jump: function(obj, first) {
						//obj包含了当前分页的所有参数，比如：
						//如果不是第一次执行就调用显示数据的方法，例如当点击下一页或者上一页的时候调用
						if(!first) {
							showContent(obj.curr, obj.limit);
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