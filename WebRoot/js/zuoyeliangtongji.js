$(function() {
	// 当起始时间中按下回车键的时候触发提交
	$("#startTime").keydown(function(event) {
		if (event.keyCode == 13) {
			$("#tijiao").click();
		}
	});
	// 当结束日期中按下回车键的时候触发提交
	$("#endTime").keydown(function(event) {
		if (event.keyCode == 13) {
			$("#tijiao").click();
		}
	});
	// 当操作员选择中按下回车键的时候触发提交
	$("#caozuo").keydown(function(event) {
		if (event.keyCode == 13) {
			$("#tijiao").click();
		}
	});
	// 提交按钮按下，对每个的分页进行清空
	$("#tijiao").click(function() {
		$("#rukupage").val("1");
		$("#chukupage").val("1");
		$("#guohupage").val("1");
		$("#nuokupage").val("1");
		showContent();
	});

	// 当点击上一页的时候，所有的保存当前页的文本框减一
	$("#shang").click(function() {
		$("#rukupage").val(parseInt($("#rukupage").val()) - 1);
		$("#chukupage").val(parseInt($("#chukupage").val()) - 1);
		$("#guohupage").val(parseInt($("#guohupage").val()) - 1);
		$("#nuokupage").val(parseInt($("#nuokupage").val()) - 1);
		showContent();
	});

	// 当点击下一页的时候，所有的保存当前页的文本框加一
	$("#xia").click(function() {
		$("#rukupage").val(parseInt($("#rukupage").val()) + 1);
		$("#chukupage").val(parseInt($("#chukupage").val()) + 1);
		$("#guohupage").val(parseInt($("#guohupage").val()) + 1);
		$("#nuokupage").val(parseInt($("#nuokupage").val()) + 1);
		showContent();
	});

	// 当点击首页的时候，返回到首页
	$("#shouye").click(function() {
		$("#rukupage").val("1");
		$("#chukupage").val("1");
		$("#guohupage").val("1");
		$("#nuokupage").val("1");
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
			$("#rukupage").val(page_now);
			$("#chukupage").val(page_now);
			$("#guohupage").val(page_now);
			$("#nuokupage").val(page_now);
			showContent();
		}
	});

	// 当点击到尾页的时候
	$("#weiye").click(function() {
		$("#rukupage").val("10000");
		$("#chukupage").val("10000");
		$("#guohupage").val("10000");
		$("#nuokupage").val("10000");
		showContent();
	});
});

function zhiwuxuanze() {
	$(function() {
		var type = $("#ddType").val();
		switch (type) {
		case "入库订单":
			// 改变职务的选择
			$("#zhiwu").html("");
			$("#zhiwu")
					.append(
							"<option value='1'>调度员</option><option value='2'>保管员</option><option value='3'>司磅员</option>"
									+ "<option value='4'>审核员</option><option value='5'>收费员</option><option value='6'>天车工</option>"
									+ "<option value='7'>装卸工</option>");
			// 调用查询入库订单的方法
			QueryRuKu();
			// 当选择入库的查询订单的时候，将过户和出库用来保存当前页的文本框重新赋值
			$("#guohupage").val("1");
			$("#chukupage").val("1");
			$("#nuokupage").val("1");
			break;
		case "过户订单":
			// 改变职务的选择
			$("#zhiwu").html("");
			$("#zhiwu")
					.append(
							"<option value='4'>审核员</option><option value='5'>收费员</option>");
			// 调用查询过户订单的方法
			QueryGuoHu();
			// 当选择过户的查询订单的时候，将入库和出库用来保存当前页的文本框重新赋值
			$("#rukupage").val("1");
			$("#chukupage").val("1");
			$("#nuokupage").val("1");
			break;
		case "出库订单":
			// 改变职务的选择
			$("#zhiwu").html("");
			$("#zhiwu")
					.append(
							"<option value='1'>调度员</option><option value='2'>保管员</option><option value='3'>司磅员</option>"
									+ "<option value='4'>审核员</option><option value='5'>收费员</option><option value='6'>天车工</option>"
									+ "<option value='7'>装卸工</option>");
			// 调用查询出库订单的方法
			QueryChuKu();
			// 当选择出库的查询订单的时候，将入库和过户用来保存当前页的文本框重新赋值
			$("#guohupage").val("1");
			$("#rukupage").val("1");
			$("#nuokupage").val("1");
			break;
		case "挪库订单":
			// 改变职务的选择
			$("#zhiwu").html("");
			$("#zhiwu")
					.append(
							"<option value='1'>调度员</option><option value='2'>保管员</option><option value='6'>天车工</option>"
									+ "<option value='7'>装卸工</option>");
			// 调用查询出库订单的方法
			QueryNuoKu();
			// 当选择出库的查询订单的时候，将入库和过户用来保存当前页的文本框重新赋值
			$("#guohupage").val("1");
			$("#rukupage").val("1");
			$("#chukupage").val("1");
			break;

		case "短倒订单":
			// 改变职务的选择
			$("#zhiwu").html("");
			$("#zhiwu")
					.append(
							"<option value='1'>调度员</option><option value='2'>保管员</option><option value='6'>天车工</option>"
									+ "<option value='7'>装卸工</option><option value='8'>短倒司机</option>");
			// 调用查询出库订单的方法
			QueryDuanDao();
			// 当选择出库的查询订单的时候，将入库和过户用来保存当前页的文本框重新赋值
			$("#guohupage").val("1");
			$("#rukupage").val("1");
			$("#chukupage").val("1");
			break;
		}
	});
}

// 进行对订单类型的判断，分别执行不同的方法
function showContent() {

	$(function() {
		$("#ruku").text("");
		$("#chuku").text("");
		$("#guohu").text("");
		$("#nuoku").text("");
		var type = $("#ddType").val();
		switch (type) {
		case "入库订单":
			// 调用查询入库订单的方法
			QueryRuKu();
			// 当选择入库的查询订单的时候，将过户和出库用来保存当前页的文本框重新赋值
			$("#guohupage").val("1");
			$("#chukupage").val("1");
			$("#nuokupage").val("1");
			break;
		case "过户订单":
			// 调用查询过户订单的方法
			QueryGuoHu();
			// 当选择过户的查询订单的时候，将入库和出库用来保存当前页的文本框重新赋值
			$("#rukupage").val("1");
			$("#chukupage").val("1");
			$("#nuokupage").val("1");
			break;
		case "出库订单":
			// 调用查询出库订单的方法
			QueryChuKu();
			// 当选择出库的查询订单的时候，将入库和过户用来保存当前页的文本框重新赋值
			$("#guohupage").val("1");
			$("#rukupage").val("1");
			$("#nuokupage").val("1");
			break;
		case "挪库订单":
			// 调用查询出库订单的方法
			QueryNuoKu();
			// 当选择出库的查询订单的时候，将入库和过户用来保存当前页的文本框重新赋值
			$("#guohupage").val("1");
			$("#rukupage").val("1");
			$("#chukupage").val("1");
			break;

		case "短倒订单":
			// 调用查询出库订单的方法
			QueryDuanDao();
			// 当选择出库的查询订单的时候，将入库和过户用来保存当前页的文本框重新赋值
			$("#guohupage").val("1");
			$("#rukupage").val("1");
			$("#chukupage").val("1");
			break;
		}
	});
}

// 过户统计的方法
function QueryNuoKu() {
	$(function() {
		$
				.ajax({
					type : "post",
					url : "workCountAction.do?flag=QueryShift",
					async : true,
					data : {
						"time" : new Date().getTime(),
						"begin" : $("#startTime").val(),
						"finish" : $("#endTime").val(),
						"name" : $("#caozuo").val(),
						"zhiwu" : $("#zhiwu").val(),
						"pageNow" : $("#nuokupage").val()
					},
					dataType : "json",
					success : function(obj) {
						if (obj == null) {
							$("#neirong").html("");
							$("#neirong")
									.append(
											"<tr><td colspan='6' style='text-align:center;'>无查询结果</td></tr>");
							return false;
						}

						if (obj[0].result == null) {
							$("#neirong").html("");
							$("#neirong")
									.append(
											"<tr><td colspan='6'style='text-align:center;'>无查询结果</td></tr>");
							return false;
						}
						$("#neirong").html("");
						for ( var i = 0; i < obj.length; i++) {
							$("#neirong").append(
									"<tr><td>" + obj[i].name + "</td><td>"
											+ obj[i].time + "</td>" + "<td>"
											+ obj[i].dingdanhao + "</td><td>"
											+ obj[i].type + "</td>" + "<td>"
											+ obj[i].workweight + "</td><td>"
											+ obj[i].worknumber + "</td></tr>");
						}

						$("#nuokupage").val(obj[0].pageNow);// 当前页更改
						$("#yeshu").val(obj[0].pageNow);
						$("#zuoyeren").text($("#caozuo").val());
						$("#ruku").text(obj[0].rukuheji);
						$("#chuku").text(obj[0].chukuheji);
						$("#guohu").text(obj[0].guohuheji);
						$("#nuoku").text(obj[0].nuokuheji);
					},
					error : function() {
						document.location.href = "/XGProject/cangchu/page/login.jsp";
					}
				});
	});
}

function QueryDuanDao() {
	$(function() {
		$
				.ajax({
					type : "post",
					url : "workCountAction.do?flag=QueryDuanDao",
					async : true,
					data : {
						"time" : new Date().getTime(),
						"begin" : $("#startTime").val(),
						"finish" : $("#endTime").val(),
						"name" : $("#caozuo").val(),
						"zhiwu" : $("#zhiwu").val(),
						"pageNow" : $("#nuokupage").val()
					},
					dataType : "json",
					success : function(obj) {
						if (obj == null) {
							$("#neirong").html("");
							$("#neirong")
									.append(
											"<tr><td colspan='6' style='text-align:center;'>无查询结果</td></tr>");
							return false;
						}

						if (obj[0].result == null) {
							$("#neirong").html("");
							$("#neirong")
									.append(
											"<tr><td colspan='6'style='text-align:center;'>无查询结果</td></tr>");
							return false;
						}
						$("#neirong").html("");
						for ( var i = 0; i < obj.length; i++) {
							$("#neirong").append(
									"<tr><td>" + obj[i].name + "</td><td>"
											+ obj[i].time + "</td>" + "<td>"
											+ obj[i].dingdanhao + "</td><td>"
											+ obj[i].type + "</td>" + "<td>"
											+ obj[i].workweight + "</td><td>"
											+ obj[i].worknumber + "</td></tr>");
						}

						$("#nuokupage").val(obj[0].pageNow);// 当前页更改
						$("#yeshu").val(obj[0].pageNow);
						$("#zuoyeren").text($("#caozuo").val());
						$("#ruku").text(obj[0].rukuheji);
						$("#chuku").text(obj[0].chukuheji);
						$("#guohu").text(obj[0].guohuheji);
						$("#nuoku").text(obj[0].nuokuheji);
					},
					error : function() {
						document.location.href = "/XGProject/cangchu/page/login.jsp";
					}
				});
	});
}

// 过户统计的方法
function QueryGuoHu() {
	$(function() {
		$
				.ajax({
					type : "post",
					url : "workCountAction.do?flag=QueryShiftStock",
					async : true,
					data : {
						"time" : new Date().getTime(),
						"begin" : $("#startTime").val(),
						"finish" : $("#endTime").val(),
						"name" : $("#caozuo").val(),
						"zhiwu" : $("#zhiwu").val(),
						"pageNow" : $("#guohupage").val()
					},
					dataType : "json",
					success : function(obj) {
						if (obj == null) {
							$("#neirong").html("");
							$("#neirong")
									.append(
											"<tr><td colspan='6' style='text-align:center;'>无查询结果</td></tr>");
							return false;
						}

						if (obj[0].result == null) {
							$("#neirong").html("");
							$("#neirong")
									.append(
											"<tr><td colspan='6'style='text-align:center;'>无查询结果</td></tr>");
							return false;
						}
						$("#neirong").html("");
						for ( var i = 0; i < obj.length; i++) {
							$("#neirong").append(
									"<tr><td>" + obj[i].name + "</td><td>"
											+ obj[i].time + "</td>" + "<td>"
											+ obj[i].dingdanhao + "</td><td>"
											+ obj[i].type + "</td>" + "<td>"
											+ obj[i].workweight + "</td><td>"
											+ obj[i].worknumber + "</td></tr>");
						}

						$("#guohupage").val(obj[0].pageNow);// 当前页更改
						$("#yeshu").val(obj[0].pageNow);
						$("#zuoyeren").text($("#caozuo").val());
						$("#ruku").text(obj[0].rukuheji);
						$("#chuku").text(obj[0].chukuheji);
						$("#guohu").text(obj[0].guohuheji);
						$("#nuoku").text(obj[0].nuokuheji);
					},
					error : function() {
						document.location.href = "/XGProject/cangchu/page/login.jsp";
					}
				});
	});
}

// 出库统计的方法
function QueryChuKu() {
	$(function() {
		$
				.ajax({
					type : "post",
					url : "workCountAction.do?flag=QueryExport",
					async : true,
					data : {
						"time" : new Date().getTime(),
						"begin" : $("#startTime").val(),
						"finish" : $("#endTime").val(),
						"name" : $("#caozuo").val(),
						"zhiwu" : $("#zhiwu").val(),
						"pageNow" : $("#chukupage").val()
					},
					dataType : "json",
					success : function(obj) {
						if (obj == null) {
							$("#neirong").html("");
							$("#neirong")
									.append(
											"<tr><td colspan='6' style='text-align:center;'>无查询结果</td></tr>");
							return false;
						}

						if (obj[0].result == null) {
							$("#neirong").html("");
							$("#neirong")
									.append(
											"<tr><td colspan='6'style='text-align:center;'>无查询结果</td></tr>");
							return false;
						}
						$("#neirong").html("");
						for ( var i = 0; i < obj.length; i++) {
							$("#neirong").append(
									"<tr><td>" + obj[i].name + "</td><td>"
											+ obj[i].time + "</td>" + "<td>"
											+ obj[i].dingdanhao + "</td><td>"
											+ obj[i].type + "</td>" + "<td>"
											+ obj[i].workweight + "</td><td>"
											+ obj[i].worknumber + "</td></tr>");
						}

						$("#chukupage").val(obj[0].pageNow);// 当前页更改
						$("#yeshu").val(obj[0].pageNow);
						$("#zuoyeren").text($("#caozuo").val());
						$("#ruku").text(obj[0].rukuheji);
						$("#chuku").text(obj[0].chukuheji);
						$("#guohu").text(obj[0].guohuheji);
						$("#nuoku").text(obj[0].nuokuheji);
					},
					error : function() {
						document.location.href = "/XGProject/cangchu/page/login.jsp";
					}
				});
	});

}

function QueryRuKu() {
	$(function() {
		$
				.ajax({
					type : "post",
					url : "workCountAction.do?flag=QueryInput",
					async : true,
					data : {
						"time" : new Date().getTime(),
						"begin" : $("#startTime").val(),
						"finish" : $("#endTime").val(),
						"name" : $("#caozuo").val(),
						"zhiwu" : $("#zhiwu").val(),
						"pageNow" : $("#rukupage").val()
					},
					dataType : "json",
					success : function(obj) {
						if (obj == null) {
							$("#neirong").html("");
							$("#neirong")
									.append(
											"<tr><td colspan='6' style='text-align:center;'>无查询结果</td></tr>");
							return false;
						}

						if (obj[0].result == null) {
							$("#neirong").html("");
							$("#neirong")
									.append(
											"<tr><td colspan='6'style='text-align:center;'>无查询结果</td></tr>");
							return false;
						}
						$("#neirong").html("");
						for ( var i = 0; i < obj.length; i++) {
							$("#neirong").append(
									"<tr><td>" + obj[i].name + "</td><td>"
											+ obj[i].time + "</td>" + "<td>"
											+ obj[i].dingdanhao + "</td><td>"
											+ obj[i].type + "</td>" + "<td>"
											+ obj[i].workweight + "</td><td>"
											+ obj[i].worknumber + "</td></tr>");
						}

						$("#rukupage").val(obj[0].pageNow);// 当前页更改
						$("#yeshu").val(obj[0].pageNow);
						$("#zuoyeren").text($("#caozuo").val());
						$("#ruku").text(obj[0].rukuheji);
						$("#chuku").text(obj[0].chukuheji);
						$("#guohu").text(obj[0].guohuheji);
						$("#nuoku").text(obj[0].nuokuheji);
					},
					error : function() {
						document.location.href = "/XGProject/cangchu/page/login.jsp";
					}
				});
	});
}
