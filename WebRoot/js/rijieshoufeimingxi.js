function load() {
	QueryZhuanChu();
	showContent();
}

// 当选择客户的下拉框的内容改变的时候调用
function change() {
	$("#tijiao").click();
}
var eventHandler = function(name) {
	return function() {
		console.log(name);
		QueryZhuanChu();
	};
};

// 查询对应的客户
function QueryZhuanChu() {

	var $area = $("#custom-name").selectize({
		valueField : 'id',
		labelField : 'title',
		searchField : 'title',
		sortField : 'title',
		options : [],
		create : false,
		dropdownParent : 'body',
		onFocus : eventHandler('onFocus'),
	});
	var control = $area[0].selectize;
	$(function() {
		$.ajax({
			type : "post",
			url : "shiftStock.do?flag=QueryZhuanChu&ff=zhuanchu",
			async : true,
			dataType : "json",
			success : function(obj) {
				control.clearOptions();
				if (obj != null) {
					for ( var i = 0; i < obj.length; i++) {
						control.addOption({
							id : obj[i].jiancheng,
							title : obj[i].jiancheng,
						});
					}
				} else {

					control.addOption({
						id : "无客户",
						title : "无客户",
					});
				}
			},
			error : function() {
				document.location.href = "/XGProject/cangchu/page/login.jsp";
			}
		});
	});
}

$(function() {

	$("#tijiao").click(function() {
		$("#chukupage").val("1");
		$("#rukupage").val("1");
		$("#guohupage").val("1");
		showContent();
	});

	// 当点击上一页的时候，所有的保存当前页的文本框减一
	$("#shang").click(function() {
		$("#chukupage").val(parseInt($("#chukupage").val()) - 1);
		$("#rukupage").val(parseInt($("#rukupage").val()) - 1);
		$("#guohupage").val(parseInt($("#guohupage").val()) - 1);
		showContent();
	});

	// 当点击下一页的时候，所有的保存当前页的文本框加一
	$("#xia").click(function() {
		$("#chukupage").val(parseInt($("#chukupage").val()) + 1);
		$("#rukupage").val(parseInt($("#rukupage").val()) + 1);
		$("#guohupage").val(parseInt($("#guohupage").val()) + 1);
		showContent();
	});

	// 当点击首页的时候，返回到首页
	$("#shouye").click(function() {
		$("#chukupage").val("1");
		$("#rukupage").val("1");
		$("#guohupage").val("1");
		showContent();
	});

	// 跳转文本框中按下回车键的时候，直接跳转
	$("#yeshu").keydown(function(event) {
		if (event.keyCode == 13) {
			$("#go").click();
		}
	});
	// 当点击要跳转到某一页的时候
	$("#go").click(function() {
		var yan = /^[0-9]*$/;
		var page_now = $("#yeshu").val();
		if (yan.test(page_now) && page_now != "") {
			$("#chukupage").val(page_now);
			$("#rukupage").val(page_now);
			$("#guohupage").val(page_now);
			showContent();
		}
	});

	// 当点击到尾页的时候
	$("#weiye").click(function() {
		$("#chukupage").val("10000");
		$("#rukupage").val("10000");
		$("#guohupage").val("10000");
		showContent();
	});
});

/** 出库、入库、过户的查询明天继续 */
// 查询结算的内容
function showContent() {
	// 判断订单的类型
	var type = $("#dingdantype").val();
	switch (type) {
	case "出库订单":
		// 将其他得当前页设置为1
		$("#rukupage").val("1");
		$("#guohupage").val("1");
		// 改变订单显示的头部内容
		$("#content_head").html("");
		$("#content_head")
				.append(
						"<tr><th>订单编号</th><th>客户单号</th><th>客户名称</th>"
								+ "<th>订单生成日期</th><th>货物名称</th><th>应出重量</th><th>实出重量</th>"
								+ "<th>运输方式</th><th>出库费用</th><th>实收出库费</th><th>二次作业费用</th><th>实收二次费</th>"
								+ "<th>下站费</th><th>实收下站费</th><th>结算方式</th></tr>");
		// 如果是出库订单，那么就调用查询出库订单的方法
		QueryChuKu();
		break;
	case "入库订单":
		// 将其他的当前页设置为1
		$("#chukupage").val("1");
		$("#guohupage").val("1");
		// 改变订单显示的头部内容
		$("#content_head").html("");
		$("#content_head")
				.append(
						"<tr><th>订单编号</th><th>客户单号</th><th>客户名称</th>"
								+ "<th>订单生成日期</th><th>货物名称</th><th>应入重量</th><th>实入重量</th>"
								+ "<th>运输方式</th><th>入库费用</th><th>实收入库费用</th><th>结算方式</th></tr>");
		// 调用查询入库订单方法
		QueryRuKu();
		break;
	case "过户订单":
		// 将其他的当前页设置为1
		$("#chukupage").val("1");
		$("#rukupage").val("1");
		// 改变订单显示的头部内容
		$("#content_head").html("");
		$("#content_head")
				.append(
						"<tr><th>订单编号</th><th>客户单号</th><th>转出客户</th><th>转入客户</th>"
								+ "<th>订单生成日期</th><th>货物名称</th><th>过户重量</th>"
								+ "<th>过户费用</th><th>实收过户费用</th><th>下站费</th><th>实收下站费</th><th>结算方式</th></tr>");
		// 调用过户订单查询方法
		QueryGuoHu();
		break;
	}
}
// 查询出库的收费订单
function QueryChuKu() {
	$(function() {
		var pageNow = $("#chukupage").val();
		$
				.ajax({
					type : "post",
					url : "exportSeed.do?flag=QueryJieSuan",
					async : true,
					data : {
						"begin" : $("#startTime").val(),
						"finish" : $("#endTime").val(),
						"jiancheng" : $("#custom-name").val(),
						"eseedClientAccounts" : $("#jiesuanfangshi").val(),
						"pageNow" : pageNow
					},
					dataType : "json",
					success : function(obj) {
						$("#neirong").html("");
						if (obj == null) {
							$("#neirong")
									.append(
											"<tr><td  style='text-align:center;' colspan='15'>无记录</td></tr>");
							$("#count_div table").html("");
							return false;
						}
						if (obj[0].result == null) {
							$("#neirong")
									.append(
											"<tr><td style='text-align:center;' colspan='15'>无记录</td></tr>");
							$("#count_div table").html("");
							return false;
						}
						var chukufei = 0.0;// 应收的出库费
						var shishou = 0.0;// 实收的出库费
						var erci = 0.0;// 应收的二次作业费
						var shishouerci = 0.0;// 实收的二次作业费
						var xiazhan = 0.0;// 应收的下站费
						var shishouxia = 0.0;// 实收的下站费
						
						for ( var i = 0; i < obj.length; i++) {
							chukufei += parseFloat(obj[i].chukucost);
							shishou += parseFloat(obj[i].sscost);
							erci += parseFloat(obj[i].ercizuoye);
							shishouerci += parseFloat(obj[i].ercishishou);
							xiazhan += parseFloat(obj[i].xzcost);
							shishouxia += parseFloat(obj[i].ssxzcost);

							var str = "<tr style='cursor: pointer;'>" + "<td>"
									+ obj[i].zongid + "</td>" + "<td>"
									+ obj[i].kehudanhao + "</td>" + "<td>"
									+ obj[i].kehu + "</td>" + "<td>"
									+ obj[i].time + "</td>" + "<td>"
									+ obj[i].goods + "</td>" + "<td>"
									+ obj[i].ycweight + "</td>" + "<td>"
									+ obj[i].scweight + "</td>" + "<td>"
									+ obj[i].yunshu + "</td>" + "<td>"
									+ obj[i].chukucost + "</td>" + "<td>"
									+ obj[i].sscost + "</td>" + "<td>"
									+ obj[i].ercizuoye + "</td>" + "<td>"
									+ obj[i].ercishishou + "</td>" + "<td>"
									+ obj[i].xzcost + "</td><td>"
									+ obj[i].ssxzcost + "</td><td>"
									+ obj[i].jiesuan + "</td>";
							$("#neirong").append(str);
						}
						$("#chukupage").val(obj[0].pageNow);
						$("#yeshu").val(obj[0].pageNow);
						$("#count_div table").html(
								"<tr><td>出库费用：<b>" + chukufei.toFixed(2) + "元</b></td>"
										+ "<td>实收出库费：<b>" + shishou.toFixed(2)
										+ "元</b></td>" + "<td>二次作业费：<b>" + erci.toFixed(2)
										+ "元</b></td>" + "<td>实收二次作业费：<b>"
										+ shishouerci.toFixed(2) + "元</b></td>"
										+ "<td>下站费：<b>" + xiazhan.toFixed(2)
										+ "元</b></td>" + "<td>实收下站费：<b>"
										+ shishouxia.toFixed(2) + "元</b></td></tr>");

						/**/
					},
					error : function() {
						document.location.href = "/XGProject/cangchu/page/login.jsp";
					}
				});
	});
}

// 查询入库的收费订单
function QueryRuKu() {
	var pageNow = $("#rukupage").val();
	$(function() {
		var pageNow = $("#chukupage").val();
		$
				.ajax({
					type : "post",
					url : "inputSeed.do?flag=QueryJieSuan",
					async : true,
					data : {
						"begin" : $("#startTime").val(),
						"finish" : $("#endTime").val(),
						"clientName" : $("#custom-name").val(),
						"iseedClientAccounts" : $("#jiesuanfangshi").val(),
						"pageNow" : pageNow
					},
					dataType : "json",
					success : function(obj) {
						$("#neirong").html("");
						if (obj == null) {
							$("#neirong")
									.append(
											"<tr><td  style='text-align:center;' colspan='11'>无记录</td></tr>");
							$("#count_div table").html("");
							return false;
						}
						if (obj[0].result == null) {
							$("#neirong")
									.append(
											"<tr><td style='text-align:center;' colspan='11'>无记录</td></tr>");
							$("#count_div table").html("");
							return false;
						}
						var rukufei = 0.0;// 入库的应收费用
						var rukushishou = 0.0;// 入库的实收费用

						for ( var i = 0; i < obj.length; i++) {
							rukufei += parseFloat(obj[i].chukucost);
							rukushishou += parseFloat(obj[i].sscost);
							var str = "<tr style='cursor: pointer;'>" + "<td>"
									+ obj[i].zongid + "</td>" + "<td>"
									+ obj[i].kehudanhao + "</td>" + "<td>"
									+ obj[i].kehu + "</td>" + "<td>"
									+ obj[i].time + "</td>" + "<td>"
									+ obj[i].goods + "</td>" + "<td>"
									+ obj[i].ycweight + "</td>" + "<td>"
									+ obj[i].scweight + "</td>" + "<td>"
									+ obj[i].yunshu + "</td>" + "<td>"
									+ obj[i].chukucost + "</td>" + "<td>"
									+ obj[i].sscost + "</td>" + "<td>"
									+ obj[i].jiesuan + "</td>";
							$("#neirong").append(str);
						}
						$("#rukupage").val(obj[0].pageNow);
						$("#yeshu").val(obj[0].pageNow);
						$("#count_div table").html(
								"<tr><td>入库费用：<b>" + rukufei.toFixed(2) + "元</b></td><td>实收入库费用：<b>"
										+ rukushishou.toFixed(2) + "元</b></td></tr>");
					},
					error : function() {
						document.location.href = "/XGProject/cangchu/page/login.jsp";
					}
				});
	});
}

// 查询过户的收费订单
function QueryGuoHu() {

	$(function() {
		var pageNow = $("#guohupage").val();
		$
				.ajax({
					type : "post",
					url : "shiftStockSeed.do?flag=QueryJieSuan",
					async : true,
					data : {
						"begin" : $("#startTime").val(),
						"finish" : $("#endTime").val(),
						"jiancheng" : $("#custom-name").val(),
						"ssseedClientAccounts" : $("#jiesuanfangshi").val(),
						"pageNow" : pageNow
					},
					dataType : "json",
					success : function(obj) {
						$("#neirong").html("");
						if (obj == null) {
							$("#neirong")
									.append(
											"<tr><td  style='text-align:center;' colspan='12'>无记录</td></tr>");
							$("#count_div table").html("");
							return false;
						}
						if (obj[0].result == null) {
							$("#neirong")
									.append(
											"<tr><td style='text-align:center;' colspan='12'>无记录</td></tr>");
							$("#count_div table").html("");
							return false;
						}
						var guohufei = 0.0;// 应收过户费
						var shishouguohu = 0.0;// 实收过户费
						var xiazhan = 0.0;// 应收下站费
						var shishouxiazhan = 0.0;// 实收下站费

						for ( var i = 0; i < obj.length; i++) {
							guohufei += parseFloat(obj[i].cost);
							shishouguohu += parseFloat(obj[i].sscost);
							xiazhan += parseFloat(obj[i].xzcost);
							shishouxiazhan += parseFloat(obj[i].ssxzcost);

							var str = "<tr style='cursor: pointer;'>" + "<td>"
									+ obj[i].zongid + "</td>" + "<td>"
									+ obj[i].kehudanhao + "</td>" + "<td>"
									+ obj[i].zhuanchu + "</td><td>"
									+ obj[i].zhuanru + "</td>" + "<td>"
									+ obj[i].time + "</td>" + "<td>"
									+ obj[i].goods + "</td>" + "<td>"
									+ obj[i].weight + "</td>" + "<td>"
									+ obj[i].cost + "</td>" + "<td>"
									+ obj[i].sscost + "</td>" + "<td>"
									+ obj[i].xzcost + "</td><td>"
									+ obj[i].ssxzcost + "</td><td>"
									+ obj[i].jiesuan + "</td>";
							$("#neirong").append(str);
						}
						$("#guohupage").val(obj[0].pageNow);
						$("#yeshu").val(obj[0].pageNow);
						$("#count_div table").html(
								"<tr><td>过户费用：<b>" + guohufei.toFixed(2) + "元</b></td><td>实收过户费用：<b>"
										+ shishouguohu.toFixed(2) + "元</b></td><td>下站费：<b>" + xiazhan.toFixed(2)
										+ "元</b></td><td>实收下站费：<b>" + shishouxiazhan.toFixed(2)
										+ "元</b></td></tr>");
					},
					error : function() {
						document.location.href = "/XGProject/cangchu/page/login.jsp";
					}
				});

	});

}