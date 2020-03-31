var xuhao = 0;
$(function() {
	$("#startTime").keydown(function(event) {
		if (event.keyCode == 13) {
			$("#lishitijiao").click();
		}
	});
	$("#kehuName").keydown(function(event) {
		if (event.keyCode == 13) {
			$("#lishitijiao").click();
		}
	});

	$("#endTime").keydown(function(event) {
		if (event.keyCode == 13) {
			$("#lishitijiao").click();
		}
	});
	$("#digndanhao").keydown(function(event) {
		if (event.keyCode == 13) {
			$("#lishitijiao").click();
		}
	});

	$("#kehudanhao").keydown(function(event) {
		if (event.keyCode == 13) {
			$("#lishitijiao").click();
		}
	});
	$("#huowu").keydown(function(event) {
		if (event.keyCode == 13) {
			$("#lishitijiao").click();
		}
	});

	$("#lishitijiao").click(function() {

		// 当点击查询的时候清空所欲的保存当前页的文本框
		$("#guohuPage").val("1");
		$("#chukuPage").val("1");
		$("#rukuPage").val("1");
		$("#chukuzuofeiPage").val("1");
		$("#rukuzuofeiPage").val("1");
		QueryDingDan();
	});

	// 当点击上一页的时候，所有的保存当前页的文本框减一
	$("#shang").click(function() {
		$("#guohuPage").val(parseInt($("#guohuPage").val()) - 1);
		$("#chukuPage").val(parseInt($("#chukuPage").val()) - 1);
		$("#rukuPage").val(parseInt($("#rukuPage").val()) - 1);
		$("#chukuzuofeiPage").val(parseInt($("#chukuzuofeiPage").val()) - 1);
		$("#rukuzuofeiPage").val(parseInt($("#rukuzuofeiPage").val()) - 1);
		QueryDingDan();
	});

	// 当点击下一页的时候，所有的保存当前页的文本框加一
	$("#xia").click(function() {
		$("#guohuPage").val(parseInt($("#guohuPage").val()) + 1);
		$("#chukuPage").val(parseInt($("#chukuPage").val()) + 1);
		$("#rukuPage").val(parseInt($("#rukuPage").val()) + 1);
		$("#chukuzuofeiPage").val(parseInt($("#chukuzuofeiPage").val()) + 1);
		$("#rukuzuofeiPage").val(parseInt($("#rukuzuofeiPage").val()) + 1);
		QueryDingDan();
	});

	// 当点击首页的时候，返回到首页
	$("#shouye").click(function() {
		$("#guohuPage").val("1");
		$("#chukuPage").val("1");
		$("#rukuPage").val("1");
		$("#chukuzuofeiPage").val("1");
		$("#rukuzuofeiPage").val("1");
		QueryDingDan();
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
			$("#guohuPage").val(page_now);
			$("#chukuPage").val(page_now);
			$("#rukuPage").val(page_now);
			$("#chukuzuofeiPage").val(page_now);
			$("#rukuzuofeiPage").val(page_now);
			QueryDingDan();
		}
	});

	// 当点击到尾页的时候
	$("#weiye").click(function() {
		$("#guohuPage").val("10000");
		$("#chukuPage").val("10000");
		$("#rukuPage").val("10000");
		$("#chukuzuofeiPage").val("10000");
		$("#rukuzuofeiPage").val("10000");
		QueryDingDan();
	});

});

// 下拉隐藏显示方法
/*
 * function xianshi(str) {
 * 
 * $(function() { var cishu = $(str).children("input").val(); if (cishu == 2) {
 * $(str).parents(".panel-group").find(".mamamiya").css("height",
 * "auto").slideDown(300); $(str).children("input").val("1"); } else {
 * $(str).parents(".panel-group").find(".mamamiya").slideUp(300);
 * $(str).children("input").val("2"); } }); }
 */

// 查询不同的订单入库订单、过户订单、出库订单的查询
function QueryDingDan() {
	$(function() {
		var type = $("#ddType").val();
		switch (type) {
		case "入库订单":
			// 入库订单的时候改变表头
			$("#content_table thead").html("");
			$("#content_table thead")
					.html(
							"<tr style='background-color:#337AB7; color:#ffffff;'>"
									+ "<th>序号</th><th>订单编号</th><th>货主</th><th>运输方式</th><th>货物</th><th>客户单号</th>"
									+ "<th>发起时间</th><th>应收重量</th><th>实收重量</th><th>订单状态</th></tr>");
			//改变下方合计的名称
			$("#indent_count table tr td").eq(0).text("应收合计");
			$("#indent_count table tr td").eq(2).text("实收合计");
			
			xuhao = 0;
			// 调用查询入库订单的方法
			QueryRuKu();
			// 当选择入库的查询订单的时候，将过户和出库用来保存当前页的文本框重新赋值
			$("#guohuPage").val("1");
			$("#chukuPage").val("1");
			$("#chukuzuofeiPage").val("1");
			$("#rukuzuofeiPage").val("1");
			break;
		case "过户订单":
			// 过户的时候改变表头
			$("#content_table thead").html("");
			$("#content_table thead")
					.html(
							"<tr style='background-color:#337AB7; color:#ffffff;'>"
									+ "<th>序号</th><th>订单编号</th><th>转出单位</th><th>转入单位</th><th>货物</th><th>客户单号</th>"
									+ "<th>发起时间</th><th>转出重量</th><th>审核结果</th><th>订单状态</th></tr>");
			
			//改变下方合计的名称
			$("#indent_count table tr td").eq(0).text("合计");
			$("#indent_count table tr td").eq(2).text("");
			$("#indent_count table tr td").eq(3).text("");
			
			xuhao = 0;
			// 调用查询过户订单的方法
			QueryGuoHu();
			// 当选择过户的查询订单的时候，将入库和出库用来保存当前页的文本框重新赋值
			$("#chukuPage").val("1");
			$("#rukuPage").val("1");
			$("#chukuzuofeiPage").val("1");
			$("#rukuzuofeiPage").val("1");
			break;
		case "出库订单":
			// 出库表头
			$("#content_table thead").html("");
			$("#content_table thead")
					.html(
							"<tr style='background-color:#337AB7; color:#ffffff;'>"
									+ "<th>序号</th><th>订单编号</th><th>货主</th><th>运输方式</th><th>货物</th><th>客户单号</th>"
									+ "<th>发起时间</th><th>应出重量</th><th>实出重量</th><th>订单状态</th></tr>");
			
			//改变下方合计的名称
			$("#indent_count table tr td").eq(0).text("应出合计");
			$("#indent_count table tr td").eq(2).text("实出合计");
			
			xuhao = 0;
			// 调用查询出库订单的方法
			QueryChuKu();
			// 当选择出库的查询订单的时候，将入库和过户用来保存当前页的文本框重新赋值
			$("#guohuPage").val("1");
			$("#rukuPage").val("1");
			$("#chukuzuofeiPage").val("1");
			$("#rukuzuofeiPage").val("1");
			break;
		case "出库作废订单":
			// 出库作废表头
			$("#content_table thead").html("");
			$("#content_table thead")
					.html(
							"<tr style='background-color:#337AB7; color:#ffffff;'>"
									+ "<th>订单编号</th><th>货主</th><th>运输方式</th><th>货物</th><th>客户单号</th>"
									+ "<th>发起时间</th><th>应出重量</th><th>实出重量</th><th>订单状态</th></tr>");
			//改变下方合计的名称
			$("#indent_count table tr td").eq(0).text("");
			$("#indent_count table tr td").eq(2).text("");
			$("#indent_count table tr td").eq(1).text("");
			$("#indent_count table tr td").eq(3).text("");
			
			xuhao = 0;
			// 调用查询出库订单的方法
			QueryChuKuZuoFei();
			// 当选择出库的查询订单的时候，将入库和过户用来保存当前页的文本框重新赋值
			$("#guohuPage").val("1");
			$("#rukuPage").val("1");
			$("#chukuPage").val("1");
			$("#rukuzuofeiPage").val("1");
			break;
		case "入库作废订单":
			// 入库作废表头
			$("#content_table thead").html("");
			$("#content_table thead")
					.html(
							"<tr style='background-color:#337AB7; color:#ffffff;'>"
									+ "<th>订单编号</th><th>货主</th><th>运输方式</th><th>货物</th><th>客户单号</th>"
									+ "<th>发起时间</th><th>应收重量</th><th>实收重量</th><th>订单状态</th></tr>");
			
			//改变下方合计的名称
			$("#indent_count table tr td").eq(0).text("");
			$("#indent_count table tr td").eq(2).text("");
			$("#indent_count table tr td").eq(1).text("");
			$("#indent_count table tr td").eq(3).text("");
			
			xuhao = 0;
			// 调用查询出库订单的方法
			QueryRuKuZuoFei();
			// 当选择出库的查询订单的时候，将入库和过户用来保存当前页的文本框重新赋值
			$("#guohuPage").val("1");
			$("#chukuPage").val("1");
			$("#rukuPage").val("1");
			$("#chukuzuofeiPage").val("1");
			break;
		}
	});
}
// 入库作废订单查询
function QueryRuKuZuoFei() {
	$(function() {
		$
				.ajax({
					type : "post",
					url : "inputSeed.do?flag=getRuKuZuoFei",
					async : true,
					data : {
						"time" : new Date().getTime(),
						"begin" : $("#startTime").val(),
						"finish" : $("#endTime").val(),
						"clientName" : $("#kehuName").val(),
						"input" : $("#digndanhao").val(),
						"clientNumber" : $("#kehudanhao").val(),
						"goodsName" : $("#huowu").val(),
						"pageNow" : $("#rukuzuofeiPage").val()
					},
					dataType : "json",
					success : function(obj) {
						if (obj == null) {
							$("#content_table tbody").html("");
							$("#content_table tbody").html(
									"<tr><td colspan='9'>无订单</td></tr>");
							return false;
						}

						if (obj[0].result == null) {
							$("#content_table tbody").html("");
							$("#content_table tbody").html(
									"<tr><td colspan='9'>无订单</td></tr>");
							return false;
						}
						$("#content_table tbody").html("");
						for ( var i = 0; i < obj.length; i++) {
							$("#content_table tbody")
									.append(
											"<tr style='cursor:pointer;'title='查看详情'onclick='tiaozhuanruku(this)'><input type='hidden' value='"
													+ obj[i].zid
													+ "' class='guohuzi' />"
													+ "<td>"
													+ obj[i].id
													+ "</td><td>"
													+ obj[i].huozhu
													+ "</td><td>"
													+ obj[i].yunshu
													+ "</td><td>"
													+ obj[i].huowu
													+ "</td><td>"
													+ obj[i].kehudanhao
													+ "</td>"
													+ "<td>"
													+ obj[i].faqishijian
													+ "</td><td>"
													+ obj[i].zhongliang.toFixed(3)
													+ "吨</td><td>"
													+ obj[i].shichuweight.toFixed(3)
													+ "吨</td><td>"
													+ obj[i].zhuangtai
													+ "</td></tr>");
						}

						$("#rukuzuofeiPage").val(obj[0].pageNow);// 当前页更改
						$("#yeshu").val(obj[0].pageNow);
					},
					error : function() {
						document.location.href = "/XGProject/cangchu/page/login.jsp";
					}
				});
	});
}

// 入库订单查询
function QueryRuKu() {
	$(function() {
		$
				.ajax({
					type : "post",
					url : "inputSeed.do?flag=getRuKuLiShi",
					async : true,
					data : {
						"time" : new Date().getTime(),
						"begin" : $("#startTime").val(),
						"finish" : $("#endTime").val(),
						"clientName" : $("#kehuName").val(),
						"input" : $("#digndanhao").val(),
						"clientNumber" : $("#kehudanhao").val(),
						"goodsName" : $("#huowu").val(),
						"pageNow" : $("#rukuPage").val()
					},
					dataType : "json",
					success : function(obj) {
						if (obj == null) {
							$("#content_table tbody").html("");
							$("#content_table tbody").html(
									"<tr><td colspan='9'>无订单</td></tr>");
							$("#indent_count table tr td").eq(1).text("0吨");
							$("#indent_count table tr td").eq(3).text("0吨");
							return false;
						}

						if (obj[0].result == null) {
							$("#content_table tbody").html("");
							$("#content_table tbody").html(
									"<tr><td colspan='9'>无订单</td></tr>");
							$("#indent_count table tr td").eq(1).text("0吨");
							$("#indent_count table tr td").eq(3).text("0吨");
							return false;
						}
						$("#content_table tbody").html("");
						for ( var i = 0; i < obj.length; i++) {
							xuhao++;
							var style = "";
							switch (obj[i].zhuangtai) {
							case "准备入库":
								style="blue";
								break;
							case "计划入库":
								style="red";
								break;
							case "入库完成":
							case "未收费":
								style="green";
								break;
							}
							$("#content_table tbody")
									.append(
											"<tr style='cursor:pointer;'title='查看详情'onclick='tiaozhuanruku(this)'><input type='hidden' value='"
													+ obj[i].zid
													+ "' class='guohuzi' />"
													+ "<td>"+xuhao+"</td><td>"
													+ obj[i].id
													+ "</td><td>"
													+ obj[i].huozhu
													+ "</td><td>"
													+ obj[i].yunshu
													+ "</td><td>"
													+ obj[i].huowu
													+ "</td><td>"
													+ obj[i].kehudanhao
													+ "</td>"
													+ "<td>"
													+ obj[i].faqishijian
													+ "</td><td>"
													+ obj[i].zhongliang.toFixed(3)
													+ "吨</td><td>"
													+ obj[i].shichuweight.toFixed(3)
													+ "吨</td><td style='color:"+style+"'>"
													+ obj[i].zhuangtai
													+ "</td></tr>");
						}

						$("#rukuPage").val(obj[0].pageNow);// 当前页更改
						$("#yeshu").val(obj[0].pageNow);
						$("#indent_count table tr td").eq(1).text(obj[obj.length-1].ycsum+"吨");
						$("#indent_count table tr td").eq(3).text(obj[obj.length-1].scsum+"吨");
					},
					error : function() {
						document.location.href = "/XGProject/cangchu/page/login.jsp";
					}
				});
	});
}
// 出库作废订单查询
function QueryChuKuZuoFei() {
	$(function() {
		$
				.ajax({
					type : "post",
					url : "exportSeed.do?flag=getChuKuLiShiZuoFei",
					async : true,
					data : {
						"time" : new Date().getTime(),
						"begin" : $("#startTime").val(),
						"finish" : $("#endTime").val(),
						"jiancheng" : $("#kehuName").val(),
						"export" : $("#digndanhao").val(),
						"kehubianhao" : $("#kehudanhao").val(),
						"goodsName" : $("#huowu").val(),
						"pageNow" : $("#chukuzuofeiPage").val()
					},
					dataType : "json",
					success : function(obj) {
						if (obj == null) {
							$("#content_table tbody").html("");
							$("#content_table tbody").html(
									"<tr><td colspan='9'>无订单</td></tr>");
							return false;
						}

						if (obj[0].result == null) {
							$("#content_table tbody").html("");
							$("#content_table tbody").html(
									"<tr><td colspan='9'>无订单</td></tr>");
							return false;
						}
						$("#content_table tbody").html("");
						for ( var i = 0; i < obj.length; i++) {
							$("#content_table tbody")
									.append(
											"<tr style='cursor:pointer;'title='查看详情'onclick='tiaozhuanchuku(this)'><input type='hidden' value='"
													+ obj[i].zid
													+ "' class='guohuzi' />"
													+ "<td>"
													+ obj[i].id
													+ "</td><td>"
													+ obj[i].huozhu
													+ "</td><td>"
													+ obj[i].yunshu
													+ "</td><td>"
													+ obj[i].huowu
													+ "</td><td>"
													+ obj[i].kehudanhao
													+ "</td>"
													+ "<td>"
													+ obj[i].faqishijian
													+ "</td><td>"
													+ obj[i].zhongliang.toFixed(3)
													+ "吨</td><td>"
													+ obj[i].shichuweight.toFixed(3)
													+ "吨</td><td>"
													+ obj[i].zhuangtai
													+ "</td></tr>");
						}

						$("#chukuzuofeiPage").val(obj[0].pageNow);// 当前页更改
						$("#yeshu").val(obj[0].pageNow);
					},
					error : function() {
						document.location.href = "/XGProject/cangchu/page/login.jsp";
					}
				});
	});
}
// 出库订单查询
function QueryChuKu() {
	$(function() {
		$
				.ajax({
					type : "post",
					url : "exportSeed.do?flag=getChuKuLiShi",
					async : true,
					data : {
						"time" : new Date().getTime(),
						"begin" : $("#startTime").val(),
						"finish" : $("#endTime").val(),
						"jiancheng" : $("#kehuName").val(),
						"export" : $("#digndanhao").val(),
						"kehubianhao" : $("#kehudanhao").val(),
						"goodsName" : $("#huowu").val(),
						"pageNow" : $("#chukuPage").val()
					},
					dataType : "json",
					success : function(obj) {
						if (obj == null) {
							$("#content_table tbody").html("");
							$("#content_table tbody").html(
									"<tr><td colspan='9'>无订单</td></tr>");
							$("#indent_count table tr td").eq(1).text("0吨");
							$("#indent_count table tr td").eq(3).text("0吨");
							return false;
						}

						if (obj[0].result == null) {
							$("#content_table tbody").html("");
							$("#content_table tbody").html(
									"<tr><td colspan='9'>无订单</td></tr>");
							$("#indent_count table tr td").eq(1).text("0吨");
							$("#indent_count table tr td").eq(3).text("0吨");
							return false;
						}
						$("#content_table tbody").html("");
						for ( var i = 0; i < obj.length; i++) {
							xuhao++;
							var style = "";
							switch (obj[i].zhuangtai) {
							case "准备出库":
								style="blue";
								break;
							case "计划出库":
								style="red";
								break;
							case "出库完成":
							case "未收费":
								style="green";
								break;
							}
							$("#content_table tbody")
									.append(
											"<tr style='cursor:pointer;'title='查看详情'onclick='tiaozhuanchuku(this)'><input type='hidden' value='"
													+ obj[i].zid
													+ "' class='guohuzi' />"
													+ "<td>"+xuhao+"</td><td>"
													+ obj[i].id
													+ "</td><td>"
													+ obj[i].huozhu
													+ "</td><td>"
													+ obj[i].yunshu
													+ "</td><td>"
													+ obj[i].huowu
													+ "</td><td>"
													+ obj[i].kehudanhao
													+ "</td>"
													+ "<td>"
													+ obj[i].faqishijian
													+ "</td><td>"
													+ obj[i].zhongliang.toFixed(3)
													+ "吨</td><td>"
													+ obj[i].shichuweight.toFixed(3)
													+ "吨</td><td style='color:"+style+"'>"
													+ obj[i].zhuangtai
													+ "</td></tr>");
						}

						$("#chukuPage").val(obj[0].pageNow);// 当前页更改
						$("#yeshu").val(obj[0].pageNow);
						$("#indent_count table tr td").eq(1).text(obj[obj.length-1].ycsum+"吨");
						$("#indent_count table tr td").eq(3).text(obj[obj.length-1].scsum+"吨");
					},
					error : function() {
						document.location.href = "/XGProject/cangchu/page/login.jsp";
					}
				});
	});
}

// 过户订单的查询
function QueryGuoHu() {
	$(function() {
		$
				.ajax({
					type : "post",
					url : "shiftStockSeed.do?flag=getShiftStockAll",
					async : true,
					data : {
						"time" : new Date().getTime(),
						"begin" : $("#startTime").val(),
						"finish" : $("#endTime").val(),
						"jiancheng" : $("#kehuName").val(),
						"shiftStock" : $("#digndanhao").val(),
						"kehudanhao" : $("#kehudanhao").val(),
						"goodsname" : $("#huowu").val(),
						"pageNow" : $("#guohuPage").val()
					},
					dataType : "json",
					success : function(obj) {
						
						if (obj == null) {
							$("#content_table tbody").html("");
							$("#content_table tbody").html(
									"<tr><td colspan='9'>无订单</td></tr>");
							$("#indent_count table tr td").eq(1).text("0吨");
							return false;
						}

						if (obj[0].result == null) {
							$("#content_table tbody").html("");
							$("#content_table tbody").html(
									"<tr><td colspan='9'>无订单</td></tr>");
							$("#indent_count table tr td").eq(1).text("0吨");
							return false;
						}
						$("#content_table tbody").html("");
						for ( var i = 0; i < obj.length; i++) {
							xuhao++;
							var style = "";
							switch (obj[i].zhuangtai) {
							case "正在审核":
								style="red";
								break;
							case "正在收费":
								style="blue";
								break;
							default:
								style="green";
								break;
							}
							$("#content_table tbody")
									.append(
											"<tr style='cursor:pointer;'title='查看详情'onclick='tiaozhuanguohu(this)'><input type='hidden' value='"
													+ obj[i].zid
													+ "' class='guohuzi' />"
													+ "<td>"+xuhao+"</td><td>"
													+ obj[i].id
													+ "</td><td>"
													+ obj[i].zhuanchu
													+ "</td><td>"
													+ obj[i].zhuanru
													+ "</td><td>"
													+ obj[i].huowu
													+ "</td><td>"
													+ obj[i].kehudanhao
													+ "</td>"
													+ "<td>"
													+ obj[i].faqishijian
													+ "</td><td>"
													+ obj[i].zhongliang.toFixed(3)
													+ "吨</td><td>"
													+ obj[i].jieguo
													+ "</td><td style='color:"+style+"'>"
													+ obj[i].zhuangtai
													+ "</td></tr>");
						}

						$("#guohuPage").val(obj[0].pageNow);// 当前页更改
						$("#yeshu").val(obj[0].pageNow);
						$("#indent_count table tr td").eq(1).text(obj[obj.length-1].sum+"吨");
					},
					error : function() {
						document.location.href = "/XGProject/cangchu/page/login.jsp";
					}
				});
	});
}
