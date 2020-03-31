layui.use(['laydate', 'form', 'jquery', 'table'], function() {
	var laydate = layui.laydate;
	var form = layui.form;
	var $ = layui.jquery;
	var table = layui.table;

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
		showContent(1, 20);
		content_sum();
		return false;
	});

	//在客户名称下拉框中当键盘按下弹起的时候，将该文本框的值改变为小写
	$(".layui-form-select .layui-select-title input").keyup(function() {
		$(this).val($(this).val().toLowerCase());
	});
	clientName("#client");
});


//当页面加载的时候调用此方法显示对应的数据
showContent(1, 20);

/*显示内容*/
//此方法传入两个参数，curr为当前页，pageRow为每页显示的行数
function showContent(curr, pageRow) {
	layui.use(['element', 'table', 'laypage', 'jquery', 'layer'], function() {
		var table = layui.table,
			laypage = layui.laypage,
			layer = layui.layer,
			element = layui.element,
			$ = layui.jquery;

		var loadIndex = layer.load(1); //风格1的加载

		var client = $("#client").val(); //获得客户名称文本框中的值
		var begin = $("#begin").val(); //获得起始日期中的值
		var finish = $("#finish").val(); //获得结束日期中的值
		var goodsName = $("#goodsName").val(); //获得货物资料中的值

		//ajax获取数据
		$.ajax({
			type: "post",
			url: "/XGProject/tidingsAction.do?flag=TongJiXGKHSFC",
			async: true,
			data: {
				"time": new Date().getTime(),
				"pageNow": curr, //当前页
				"begin": begin,
				"finish": finish,
				"goodsName": goodsName,
				"jiancheng": client,
				"pageRow": pageRow
			},
			dataType: 'json',
			success: function(jdata) {
				$("#showContent tbody").html("");
				var qichu = 0.0,
					qiru = 0.0,
					huoru = 0.0,
					guohuru = 0.0,
					rukuheji = 0.0,
					qichechu = 0.0,
					huochu = 0.0,
					guohuchu = 0.0,
					chukuheji = 0.0,
					qimo = 0.0;
				$.each(jdata, function(i, obj) {
					$("#showContent tbody").append("<tr><td>" + obj.kehu + "</td><td>" + obj.pinlei + "</td>" +
						"<td>" + obj.mingcheng + "</td><td>" + obj.qichu + "</td><td>" + obj.qiru + "</td>" +
						"<td>" + obj.huoru + "</td><td>" + obj.guohuru + "</td><td>" + obj.rukuheji + "</td>" +
						"<td>" + obj.qichechu + "</td><td>" + obj.huochu + "</td><td>" + obj.guohuchu + "</td>" +
						"<td>" + obj.chukuheji + "</td><td>" + obj.qimo + "</td></tr>");
					if(obj.result != null) {
						qichu += parseFloat(obj.qichu);
						qiru += parseFloat(obj.qiru);
						huoru += parseFloat(obj.huoru);
						guohuru += parseFloat(obj.guohuru);
						rukuheji += parseFloat(obj.rukuheji);
						qichechu += parseFloat(obj.qichechu);
						huochu += parseFloat(obj.huochu);
						guohuchu += parseFloat(obj.guohuchu);
						chukuheji += parseFloat(obj.chukuheji);
						qimo += parseFloat(obj.qimo);
					}
				});
				var sumJson = {
					"kehu": "",
					"pinlei": "",
					"mingcheng": "当页合计：",
					"qichu": qichu.toFixed(3),
					"qiru": qiru.toFixed(3),
					"huoru": huoru.toFixed(3),
					"guohuru": guohuru.toFixed(3),
					"rukuheji": rukuheji.toFixed(3),
					"qichechu": qichechu.toFixed(3),
					"huochu": huochu.toFixed(3),
					"guohuchu": guohuchu.toFixed(3),
					"chukuheji": chukuheji.toFixed(3),
					"qimo": qimo.toFixed(3)
				}
				jdata.push(sumJson);
				layer.close(loadIndex);
				//把返回的数据渲染到对应的表格中
				var w=$(parent.window).width()-235;//获取浏览器的宽，减去侧边栏的宽度 
				var tableObj = table.render({
					data: jdata, //返回的json数据
					elem: '#showContent', //显示数据的容器
					height: 'full-235',
					limit: 1000000, //默认显示的行数
					even: true,
					cols: [
						[{
							field: 'kehu', //列显示的值
							title: '客户名称', //显示的标题
							width: 140, //列的宽度
							sort: false, //是否产生排序
							fixed: false //是否是固定列宽
						}, {
							field: 'pinlei',
							title: '货物品类',
							width: 140,
							sort: false,
						}, {
							field: 'mingcheng',
							title: '货物名称',
							width: 140,
							sort: false,
							edit: false //是否可编辑
						}, {
							field: 'qichu',
							title: '期初库存（吨）',
							width: 140,
							sort: true
						}, {
							field: 'qiru',
							title: '汽车入库（吨）',
							width: 140,
							sort: true
						}, {
							field: 'huoru',
							title: '火车入库（吨）',
							width: 140,
							sort: true
						}, {
							field: 'guohuru',
							title: '过户入库（吨）',
							width: 140,
							sort: true
						}, {
							field: 'rukuheji',
							title: '入库合计（吨）',
							width: 140,
							sort: true
						}, {
							field: 'qichechu',
							title: '汽车出库（吨）',
							width: 140,
							sort: true
						}, {
							field: 'huochu',
							title: '火车出库（吨）',
							width: 140,
							sort: true
						}, {
							field: 'guohuchu',
							title: '过户出库（吨）',
							width: 140,
							sort: true
						}, {
							field: 'chukuheji',
							title: '出库合计（吨）',
							width: 140,
							sort: true
						}, {
							field: 'qimo',
							title: '期末库存（吨）',
							width: 140,
							sort: true
						}]
					] //设置表头
				});

				$(".layui-table-view div.layui-table-body table tbody tr:last-child").css({
					"font-weight": "bold",
					"color": "red"
				});

				//如果是第一次调用此方法或者是当点击查询的时候进行执行下面分页的方法
				if(parseInt(curr) == 1 && parseInt(pageRow) == 20) {
					//分页的渲染
					laypage.render({
						elem: 'test1', //显示分页的容器
						count: parseInt(jdata[0].pageCount) * parseInt(pageRow), //显示的总条数
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
			error: function() { //当ajax访问出错的时候调用的方法
				layer.close(loadIndex);
				//layui中layer.alert的标准写法
				layer.alert("请求数据错误！", {
					icon: 2,
					anim:4,
					closeBtn:2,
					title: ['系统提示', 'font-size:16px;'],
	                skin: 'layui-layer-blue',
				});
			}
		});
	});
}

//当页面加载的时候调用数据总合计的方法
content_sum();

//查询对应的数据总的合计
function content_sum() {
	layui.use('jquery', function() {
		var $ = layui.jquery;
		var client = $("#client").val(); //获得客户名称文本框中的值
		var begin = $("#begin").val(); //获得起始日期中的值
		var finish = $("#finish").val(); //获得结束日期中的值
		var goodsName = $("#goodsName").val(); //获得货物资料中的值
		if(!client) {
			client = "";
		}
		$.ajax({
			type: "post",
			url: "/XGProject/tidingsAction.do?flag=getSum",
			async: true,
			data: {
				"time": new Date().getTime(),
				"begin": begin,
				"finish": finish,
				"goodsName": goodsName,
				"jiancheng": client
			},
			dataType: "json",
			success: function(data) {
				var qichu = data[0].qichu; //期初库存合计
				var ruku = data[0].ruku; //入库合计
				var chuku = data[0].chuku; //出库合计
				var qimo = data[0].qimo; //期末合计
				$("#content-sum tr td").eq(1).html("期初库存：" + qichu + "吨");
				$("#content-sum tr td").eq(2).html("入库合计：" + ruku + "吨");
				$("#content-sum tr td").eq(3).html("出库合计：" + chuku + "吨");
				$("#content-sum tr td").eq(4).html("期末库存：" + qimo + "吨");
			},
			error: function() {
				alert("出错了");
			}
		});
	});
}