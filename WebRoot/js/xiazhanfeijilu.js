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
		$("#pageNow").val("1");
		showContent();
	});

	// 当点击上一页的时候，所有的保存当前页的文本框减一
	$("#shang").click(function() {
		$("#pageNow").val(parseInt($("#pageNow").val()) - 1);
		showContent();
	});

	// 当点击下一页的时候，所有的保存当前页的文本框加一
	$("#xia").click(function() {
		$("#pageNow").val(parseInt($("#pageNow").val()) + 1);
		showContent();
	});

	// 当点击首页的时候，返回到首页
	$("#shouye").click(function() {
		$("#pageNow").val("1");
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
			$("#pageNow").val(page_now);
			showContent();
		}
	});

	// 当点击到尾页的时候
	$("#weiye").click(function() {
		$("#pageNow").val("10000");
		showContent();
	});
});

// 查询结算的内容
function showContent() {
	$(function() {
		$
				.ajax({
					type : "post",
					url : "xiazhanfeiAction.do?flag=getXiaZhanAll&pageNow="
							+ $("#pageNow").val(),
					async : true,
					data : {
						"begin" : $("#startTime").val(),
						"finish" : $("#endTime").val(),
						"clientName" : $("#custom-name").val(),
						"jiesuan" : $("#jiesuanfangshi").val()
					},
					dataType : "json",
					success : function(obj) {
						$("#neirong").html("");
						if (obj == null) {
							$("#neirong")
									.append(
											"<tr><td  style='text-align:center;' colspan='17'>无记录</td></tr>");
							return false;
						}
						if (obj[0].result == null) {
							$("#neirong")
									.append(
											"<tr><td style='text-align:center;' colspan='17'>无记录</td></tr>");
							return false;
						}
						for ( var i = 0; i < obj.length; i++) {

							var str = "<tr style='cursor: pointer;'>" + "<td>"
									+ obj[i].zongbianhao
									+ "<input type='hidden' value='"
									+ obj[i].xzbianhao + "' id='xzid' /></td>"
									+ "<td>" + obj[i].caozuobianhao + "</td>"
									+ "<td>" + obj[i].zhuanchu + "</td>"
									+ "<td>" + obj[i].zhuanru + "</td>"
									+ "<td>" + obj[i].type + "</td>" + "<td>"
									+ obj[i].weight + "</td>" + "<td>"
									+ obj[i].danjia + "</td>" + "<td>"
									+ obj[i].yingshou + "</td>" + "<td>"
									+ obj[i].shoufeiren + "</td>" + "<td>"
									+ obj[i].shoufeitime + "</td>" + "<td>"
									+ obj[i].shishou + "</td>" + "<td>"
									+ obj[i].jiesuan + "</td>" + "<td>"
									+ obj[i].zhifu + "</td><td>"
									+ obj[i].piaoju + "</td><td>"
									+ obj[i].zhuangtai + "</td><td>"
									+ obj[i].remark + "</td>";
							if ($("#update").val() != "0") {
								str += "<td style='text-align: center;'><a class='btn btn-warning btn-xs' type='button' data-toggle='modal'"
										+ " href='#jiesuan' onclick='xiugai(this)'>修改</a></td></tr>";
							} else {
								str += "</tr>";
								$(".table thead tr .caozuo").remove();
							}
							$("#neirong").append(str);
						}
						$("#pageNow").val(obj[0].pageNow);
						$("#yeshu").val(obj[0].pageNow);
					},
					error : function() {
						document.location.href = "/XGProject/cangchu/page/login.jsp";
					}
				});
	});
}
// 单点击修改的时候调用
function xiugai(str) {
	$(function() {
		// 取出对应的下站费的编号
		var id = $(str).parents("tr").find("#xzid").val();
		// 取出下站费单价
		var danjia = $(str).parents("tr").children("td").eq(6).text();
		// 取出下站中的重量
		var weight = $(str).parents("tr").children("td").eq(5).text();
		// 取出对应下站的应收费用
		var yingshou = $(str).parents("tr").children("td").eq(7).text();
		// 取出下站费中的实收
		var shishou = $(str).parents("tr").children("td").eq(10).text();
		// 取出下站费中的结算方式
		var jiesuan = $(str).parents("tr").children("td").eq(11).text();

		// 设置模态框中的编号
		$("#bianhao").val(id);
		// 设置模态框中的单价值
		$("#danjia").val(danjia);
		// 设置模态框中重量的值
		$("#weight").val(weight);
		// 设置模态框中的应收费用
		$("#yingshou").val(yingshou);
		// 设置模态框中的实收费用
		$("#shishou").val(shishou);
		// 设置模态框中的结算方式
		$("#jsfs").val(jiesuan);
		// 设置模态框中的支付方式
		$("#zhifu").val(zhifu);
		// 设置模态框中的票据类型
		$("#piaoju").val(piaoju);

		// 查询对应的支付方式和票据类型
		zhifufangshi(str);
	});
}

$(function() {
	// 当输入单价之后鼠标离开的时候，重新计算出对应的应收费用
	$("#danjia").blur(function() {
		var danjia = $(this).val();// 取出单价
		var weight = $("#weight").val();// 取出重量
		$("#yingshou").val(parseFloat(danjia) * parseFloat(weight));
	});

	// 修改选项填完之后的提交
	$("#tijiaogenggai").click(function() {
		if (confirm("确定修改吗？")) {
			$("#xiazhanform").submit();
		}
	});

});
// 月结的判断
function yuejiepanduan(str) {
	$(function() {
		var type = $(str).val();
		if (type == "月结") {
			$("#zhifu").html("");
			$("#piaoju").html("");
			$("#zhifu").append("<option value='无'>无</option>");
			$("#piaoju").append("<option value='无'>无</option>");

		} else {
			zhifufangshi(str);
		}
	});
}
// 查询支付范式
function zhifufangshi(str) {

	$(function() {
		$("#zhifu").html("");
		// 取出下站费中的支付方式
		var zhifu = $(str).parents("tr").children("td").eq(12).text();
		// 取出下站费中的票据类型
		var piaoju = $(str).parents("tr").children("td").eq(13).text();
		$.ajax({
			type : "post",
			url : "paymentFashion.do?flag=getGuoHuQuery&ff=zhifu",
			dataType : "json",
			success : function(obj) {
				if (obj == null || obj.length <= 0) {
					$("#zhifu").append("<option value='无'>无</option>");
					return false;
				}
				if (obj[0].result == null) {
					$("#zhifu").append("<option value='无'>无</option>");
					return false;
				}
				for ( var i = 0; i < obj.length; i++) {
					$("#zhifu").append(
							"<option value='" + obj[i].zhifu + "'>"
									+ obj[i].zhifu + "</option>");
					if (zhifu == obj[i].zhifu) {
						$("#zhifu").val(obj[i].zhifu);
					}
				}
				piaojuleixing(piaoju);
			},
			error : function() {
				document.location.href = "/XGProject/cangchu/page/login.jsp";
			}
		});

	});
}

// 查询对应的票据类型
function piaojuleixing(str) {
	$(function() {
		$("#piaoju").html("");
		$.ajax({
			type : "post",
			url : "paymentFashion.do?flag=getGuoHuQuery&ff=piaoju",
			data : {
				"pfashionName" : $("#zhifu").val()
			},
			dataType : "json",
			success : function(obj) {
				if (obj == null || obj.length <= 0) {
					$("#piaoju").append("<option value='无'>无</option>");
					return false;
				}
				if (obj[0].result == null) {
					$("#piaoju").append("<option value='无'>无</option>");
					return false;
				}
				for ( var i = 0; i < obj.length; i++) {
					$("#piaoju").append(
							"<option value='" + obj[i].piaoju + "'>"
									+ obj[i].piaoju + "</option>");
					if (str == obj[i].piaoju) {
						$("#piaoju").val(obj[i].piaoju);
					}
				}
			},
			error : function() {
				document.location.href = "/XGProject/cangchu/page/login.jsp";
			}
		});

	});
}
