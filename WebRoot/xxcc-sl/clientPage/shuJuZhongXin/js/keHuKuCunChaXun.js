layui.use(['laydate', 'form', 'jquery', 'layer', 'table'], function() {
	var laydate = layui.laydate;
	var form = layui.form;
	var $ = layui.jquery;
	var table = layui.table;
	var layer = layui.layer;

	//form监听提交,当单机【立即提交的时候调用】
	form.on('submit(formDemo)', function(data) {
		showContent(1, 20);
		return false;
	});

});

//当页面加载的时候调用显示内容的方法
showContent(1, 20);

//显示内容的方法
function showContent(curr, pageRow) {
	layui.use(['element', 'table', 'laypage', 'jquery', 'layer'], function() {
		var table = layui.table,
			laypage = layui.laypage,
			layer = layui.layer,
			element = layui.element,
			$ = layui.jquery;
		//用ajax访问数据
		var pinlei = $("#pinlei").val(); //获得品类中的值
		var mingcheng = $("#mingcheng").val(); //获得名称中的值
		var guige = $("#guige").val(); //获得规格中的值
		var caizhi = $("#caizhi").val(); //获得材质中的值
		var shuxing = $("#shuxing").val(); //获得货物属性文本框中的值
		var chandi = $("#chandi").val(); //获得货物产地文本框中的值

		var loadIndex = layer.load(1); //风格1的加载
		$.ajax({
			type: "post",
			url: "/XGProject/clientGoods.do?flag=getClientAllTj&pinlei=" + pinlei + "&huowu=" + mingcheng + "&guige=" + guige + "&caizhi=" + caizhi + "&shuxin=" + shuxing + "&chandi=" + chandi,
			async: true,
			data: {
				"pageNow": curr,
				"pageRow": pageRow,
				"ff": "ajax"
			},
			dataType: "json",
			success: function(data) {
				layer.close(loadIndex);
				if(data == null || data[0].result == null) {
					layer.alert("无库存记录！", {
						icon: 5,
						closeBtn: 2,
						anim: 4,
						title: ['系统提示', 'font-size:16px;'],
						skin: 'layui-layer-blue',
					});
				}
				var sum = 0.0;
				$("#showContent tbody").html("");
				$.each(data, function(i, obj) {
					obj.xuhao = (i + 1);
					$("#showContent tbody").append("<tr><td>" + obj.xuhao + "</td><td>" + obj.kehu + "</td>" +
						"<td>" + obj.pinlei + "</td><td>" + obj.mingcheng + "</td><td>" + obj.guige + "</td>" +
						"<td>" + obj.caizhi + "</td><td>" + obj.shuxing + "</td><td>" + obj.chandi + "</td>" +
						"<td>" + obj.zhongliang + "</td><td>" + obj.DJzhongliang + "</td></tr>");
					sum += parseFloat(obj.zhongliang);
				});

				var sumJson = {
					"chandi": "当页合计：",
					"zhongliang": sum,
					"DJzhongliang": "",
				};
				data.push(sumJson);
				var w = $(parent.window).width() - 235; //获取浏览器的宽，减去侧边栏的宽度
				table.render({
					data: data, //返回的json数据
					elem: '#showContent', //显示数据的容器
					width: w,
					height: 'full-215',
					limit: 1000000, //默认显示的行数
					cellMinWidth: 60,
					even: true,
					cols: [
						[{
							field: 'xuhao', //列显示的值
							title: '序号', //显示的标题
							width: 60, //列的宽度
							sort: false, //是否产生排序
							fixed: true //是否是固定列宽
						}, {
							field: 'kehu',
							title: '客户名称',
							sort: true
						}, {
							field: 'pinlei',
							title: '货物品类',
							sort: true
						}, {
							field: 'mingcheng',
							title: '货物名称',
							sort: true
						}, {
							field: 'guige',
							title: '货物规格',
							sort: true,
							edit: false //是否可编辑
						}, {
							field: 'caizhi',
							title: '货物材质',
							sort: true
						}, {
							field: 'shuxing',
							title: '货物属性',
							sort: true
						}, {
							field: 'chandi',
							title: '货物产地',
							sort: true
						}, {
							field: 'zhongliang',
							title: '库存重量(吨)',
							sort: false
						}, {
							field: 'DJzhongliang',
							title: '冻结重量(吨)',
							sort: false
						}]
					], //设置表头
					done: function() {
						$(".layui-table-view div.layui-table-body table tbody tr:last-child").css({
							"font-weight": "bold",
							"color": "red"
						});
					}
				});

				//如果是第一次调用此方法或者是当点击查询的时候进行执行下面分页的方法
				if(parseInt(curr) == 1 && pageRow == 20) {
					//分页的渲染
					laypage.render({
						elem: 'paging', //显示分页的容器
						count: parseInt(data[0].pageCount) * 20, //显示的总条数
						limit: 20,
						layout: ['prev', 'page', 'next', 'skip', 'limit'],
						limits: [20, 40, 60, 100, 300, 500], //选择显示的行数
						jump: function(obj, first) {
							//obj包含了当前分页的所有参数，比如：
							console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
							console.log(obj.limit); //得到每页显示的条数
							//如果不是第一次执行就调用显示数据的方法，例如当点击下一页或者上一页的时候调用
							if(!first) {
								showContent(obj.curr, obj.limit);
							}
						}
					});
				}
			},
			error: function() {
				layer.close(loadIndex);
				layer.alert("加载失败！", {
					icon: 5,
					closeBtn: 2,
					anim: 4,
					title: ['系统提示', 'font-size:16px;'],
					skin: 'layui-layer-blue',
				});
			}
		});
	});
}