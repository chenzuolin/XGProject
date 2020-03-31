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
		elem: "#begin",
		type: "datetime"
	});
	laydate.render({
		elem: "#finish",
		type: "datetime"
	});

	form.on('submit(formDemo)', function(obj) {
		showContent(1, 20);
		return false;
	});

	var piliangData;
	//当单机批量结算的时候触发
	$("#piliang").click(function() {
		var checkStatus = table.checkStatus('idTest');
		if(checkStatus.data.length <= 0) {
			layer.alert("请选择对应的结算客户！", {
				icon: 5,
				anim: 4,
				closeBtn: 2,
				title: ['系统提示', 'font-size:16px;'],
                skin: 'layui-layer-blue',
			});
			return false;
		}
		laydate.render({
			elem: "#qixian"
		});
		piliangData = checkStatus.data;
		//打开对应的弹出层
		layer.open({
			type: 1,
			anim: 4,
			title: ['批量结算', 'font-size:16px;'],
            skin: 'layui-layer-blue',
			closeBtn: 2,
			content: $("#piliangOpen"),
			area: ['600px', '300px'],
			btn: ['确定提交', '关闭'],
			yes: function() {
				$("#pilaingDemo").click();
			},
			btn2: function(index, obj) {
				//点击关闭的时候触发
				layer.close(index);
			}
		});
	});
	form.on('submit(pilaingDemo)', function(obj) {
		var data = obj.field;
		//将数据改变为数组
		var clientId = [];
		var begin = [];
		var finish = [];
		var qiru = [];
		var huoru = [];
		var qichu = [];
		var huochu = [];
		var chuxz = [];
		var guoxz = [];
		var guohu = [];
		var cangchu = [];
		var heji = [];
		var qimoweight = [];
		if(piliangData.length == undefined || piliangData.length == null || piliangData.length == "") {
			clientId[0] = piliangData.clientId;
			begin[0] = piliangData.begin;
			finish[0] = piliangData.finish;
			qiru[0] = piliangData.qiru;
			huoru[0] = piliangData.huoru;
			qichu[0] = piliangData.qichu;
			huochu[0] = piliangData.huochu;
			chuxz[0] = piliangData.chuxz;
			guoxz[0] = piliangData.guoxz;
			guohu[0] = piliangData.guohu;
			cangchu[0] = piliangData.cangchu;
			heji[0] = piliangData.heji;
			qimoweight[0] = piliangData.qimoweight;
		} else {
			for(var i = 0; i < piliangData.length; i++) {
				clientId[i] = piliangData[i].clientId;
				begin[i] = piliangData[i].begin;
				finish[i] = piliangData[i].finish;
				qiru[i] = piliangData[i].qiru;
				huoru[i] = piliangData[i].huoru;
				qichu[i] = piliangData[i].qichu;
				huochu[i] = piliangData[i].huochu;
				chuxz[i] = piliangData[i].chuxz;
				guoxz[i] = piliangData[i].guoxz;
				guohu[i] = piliangData[i].guohu;
				cangchu[i] = piliangData[i].cangchu;
				heji[i] = piliangData[i].heji;
				qimoweight[i] = piliangData[i].qimoweight;
			}
		}

		layer.confirm("确定结算吗？", {
			icon: 3,
			anim: 4,
			closeBtn: 2,
			title: ['系统提示', 'font-size:16px;'],
            skin: 'layui-layer-blue',
		}, function() {
			var u = "/XGProject/accounts.do?flag=shouquAccounts";
			var d = {
				"client": clientId,
				"accountsCreateTime": begin,
				"accountsFinishTime": finish,
				"rukuCost": qiru,
				"zidingyiFour": huoru,
				"zidingyiFive": qichu,
				"zidingyiSix": huochu,
				"chukumaxtime": chuxz,
				"zhuanchukumaxtime": guoxz,
				"guohuCost": guohu,
				"cangchuCost": cangchu,
				"accountsExpensesSeed": heji,
				"qimoWeight": qimoweight,
				"shoufeiqixian": data.qixian,
				"zhinaFeilv": data.feilv,
				"accountsRemark": data.beizhu
			};
			var jiesuan = queryAjax(u, d, 'text');
			if(jiesuan == 'error') {
				layer.alert("数据上传错误！", {
					icon: 2,
					anim: 4,
					closeBtn: 2,
					title: ['系统提示', 'font-size:16px;'],
	                skin: 'layui-layer-blue',
				});
			} else {
				if(jiesuan.indexOf("结算成功") != -1) {
					layer.alert("结算成功！", {
						icon: 1,
						anim: 4,
						closeBtn: 2,
						title: ['系统提示', 'font-size:16px;'],
		                skin: 'layui-layer-blue',
					}, function() {
						document.location.reload();
					});
				} else {
					layer.alert("结算失败！", {
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

	table.on('tool(showcontent)', function(obj) {
		var data = obj.data; //获得当前行数据
		var layEvent = obj.event; //获得 lay-event 对应的值
		var tr = obj.tr; //获得当前行 tr 的DOM对象
		laydate.render({
			elem: "#qixian"
		});
		piliangData = data;
		if(layEvent == "edit") {
			//打开对应的弹出层
			layer.open({
				type: 1,
				anim: 4,
				title: ['月结费用结算', 'font-size:16px;'],
                skin: 'layui-layer-blue',
				closeBtn: 2,
				content: $("#piliangOpen"),
				area: ['600px', '300px'],
				btn: ['确定提交', '关闭'],
				yes: function() {
					$("#pilaingDemo").click();
				},
				btn2: function(index, obj) {
					//点击关闭的时候触发
					layer.close(index);
				}
			});
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
			"rowSize": pageRow
		};
		var loadIndex = layer.load(1); //风格1的加载
		$.ajax({
			type: "post",
			url: '/XGProject/accounts.do?flag=QueryJieSuanFirst',
			async: true,
			data: d,
			dataType: 'json',
			success: function(obj) {
				layer.close(loadIndex);
				if(obj == null || obj == "" || obj == undefined || obj == []) {
					layer.alert("无数据！", {
						icon: 5,
						anim: 4,
						closeBtn: 2,
						title: ['系统提示', 'font-size:16px;'],
		                skin: 'layui-layer-blue',
					});
					return false;
				}
				var w=$(parent.window).width()-245;//获取浏览器的宽，减去侧边栏的宽度  
				table.render({
					data: obj, //返回的json数据
					elem: '#showContent', //显示数据的容器
					height: 'full-160',
					width:w,
					limit: 1000000, //默认显示的行数
					even: true,
					id: 'idTest',
					cols: [
						[{
							fixed: "left",
							checkbox: true,
						}, {
							field: 'clientName', //列显示的值
							title: '客户名称', //显示的标题
							width: 150, //列的宽度
							sort: false, //是否产生排序
						}, {
							field: 'begin',
							title: '起始日期',
							width: 180,
							sort: false,
						}, {
							field: 'finish',
							title: '结束日期',
							width: 180,
						}, {
							field: 'qiru',
							title: '汽运入库费',
							width: 100,
							sort: false,
							edit: false //是否可编辑
						}, {
							field: 'huoru',
							title: '火运入库费',
							width: 120,
							sort: false
						}, {
							field: 'qichu',
							title: '汽运出库费',
							width: 120,
							sort: false
						}, {
							field: 'huochu',
							title: '火运出库费',
							width: 100,
							sort: false
						}, {
							field: 'chuxz',
							title: '下站费（出）',
							width: 120,
							sort: false
						}, {
							field: 'guohu',
							title: '过户费',
							width: 120,
							sort: false,
						}, {
							field: 'guoxz',
							title: '下站费（过）',
							width: 120,
							sort: false
						}, {
							field: 'cangchu',
							title: '仓储费',
							width: 120,
							sort: false
						}, {
							field: 'heji',
							title: '费用合计',
							width: 130,
							sort: false
						}, {
							fixed: 'right',
							width: 100,
							align: 'center',
							toolbar: '#barDemo'
						}]
					], //设置表头
					done:function(){
						layer.tips('选中缴费期限和费率相同的客户，可在此进行批量结算！', '#piliang', {
							tips: 1,
							time: 10000
						});
					}
				});
				if(parseInt(curr) == 1 && parseInt(pageRow) == 20) {
					//分页的渲染
					laypage.render({
						elem: 'paging', //显示分页的容器
						count: parseInt(obj[0].pageCount) * pageRow, //显示的总条数
						limit: pageRow,
						layout: ['prev', 'page', 'next', 'skip', 'limit'],
						limits: [20, 40, 60, 80, 100,200,300,400],
						jump: function(obj, first) {
							//obj包含了当前分页的所有参数，比如：
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
				layer.alert("获取数据错误！", {
					icon: 2,
					anim: 4,
					closeBtn: 2,
					title: ['系统提示', 'font-size:16px;'],
	                skin: 'layui-layer-blue',
				});
			}
		});

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