layui.use(['laydate', 'form', 'jquery', 'layer', 'table'], function() {
	var laydate = layui.laydate;
	var form = layui.form;
	var $ = layui.jquery;
	var table = layui.table;
	var layer = layui.layer;

	//执行一个laydate实例
	laydate.render({
		elem: '#begin', //指定元素，开始时间
		type: "date"
	});
	laydate.render({
		elem: '#finish', //指定元素，结束时间
		type: "date"
	});

	//form监听提交,当单机【立即提交的时候调用】
	form.on('submit(formDemo)', function(data) {
		show(1);
		return false;
	});

});

//当页面加载的时候调用显示内容的方法
show(1);

function show(curr) {
	layui.use(['element', 'table', 'laypage', 'jquery', 'layer'], function() {
		var table = layui.table,
			laypage = layui.laypage,
			layer = layui.layer,
			element = layui.element,
			$ = layui.jquery;
		var loadIndex = layer.load(1); //风格1的加载
		$.ajax({
			type: "post",
			url: "/XGProject/accounts.do?flag=getClientJieSuan",
			async: true,
			data: {
				"time": new Date().getTime(),
				"begin": $("#begin").val(),
				"finish": $("#finish").val(),
			},
			dataType: "json",
			success: function(obj) {
				layer.close(loadIndex);
				data = JSON.stringify(obj);
				if(data == null || data == undefined || data == "" || data == "[]" || obj[0].result==null) {
					layer.alert("无结算数据！", {
						icon: 5,
						closeBtn: 2,
						anim: 4,
						title: ['系统提示', 'font-size:16px;'],
						skin: 'layui-layer-blue',
					});
				}
				var w = $(parent.window).width() - 235; //获取浏览器的宽，减去侧边栏的宽度  
				table.render({
					data: obj, //返回的json数据
					elem: '#showContent', //显示数据的容器
					width: w,
					height: 'full-150',
					limit: 1000000, //默认显示的行数
					even: true,
					cols: [
						[{
							field: 'kehu', //列显示的值
							title: '客户名称', //显示的标题
							width: 120, //列的宽度
							sort: false, //是否产生排序
							fixed: true //是否是固定列宽
						},{
							field: 'begin', //列显示的值
							title: '起始日期', //显示的标题
							width: 200, //列的宽度
							sort: false, //是否产生排序
						}, {
							field: 'finish',
							title: '结束日期',
							width: 200,
							sort: false,
						}, {
							field: 'qiyunfei',
							title: '汽运入库费(元)',
							width: 130,
							sort: false,
						}, {
							field: 'huoyunfei',
							title: '火运入库费(元)',
							width: 130,
							sort: false,
						}, {
							field: 'qiyunchu',
							title: '汽运出库费(元)',
							width: 130,
							sort: false,
						}, {
							field: 'huoyunchu',
							title: '火运出库费(元)',
							width: 160,
							sort: false,
						},{
							field: 'guohu',
							title: '过户费(元)',
							width: 160,
						}, {
							field: 'cangchu',
							title: '仓储费(元)',
							width: 160,
							sort: false,
							edit: false //是否可编辑
						}, {
							field: 'xiachu',
							title: '出库下站费(元)',
							width: 160,
							sort: false
						}, {
							field: 'xiaguo',
							title: '过户下站费(元)',
							width: 160,
							sort: false
						},  {
							field: 'heji',
							title: '费用合计(元)',
							width: 200,
							sort: false
						}, {
							field: 'jiesuanren',
							title: '结算人',
							width: 160,
							sort: false
						},  {
							field: 'jiesuantime',
							title: '结算时间',
							width: 200,
							sort: false
						},  {
							field: 'feilv',
							title: '滞纳金费率',
							width: 200,
							sort: false
						}, {
							field: 'qixian',
							title: '滞纳金起征日期',
							width: 160,
							sort: false
						},  {
							field: 'zhinajin',
							title: '滞纳金合计',
							width: 200,
							sort: false
						}]
					]
				});
			},
			error: function() {
				layer.close(loadIndex);
				layer.alert("获取数据错误！", {
					icon: 2,
					closeBtn: 2,
					anim: 4,
					title: ['系统提示', 'font-size:16px;'],
					skin: 'layui-layer-blue',
				});
			}
		});
	});
	return false;
}


