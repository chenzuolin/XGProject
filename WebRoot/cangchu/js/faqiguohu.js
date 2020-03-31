$(function() {

	// 当点击确定提交的时候触发
	$("#guohuqueding").click(function() {
		$("#guohuForm").submit();
	});

	// 验证提货单号是否为空
	$("#tihuodanhao").blur(function() {
		if ($(this).val() == "") {
			$(this).siblings("img").attr("src", "cangchu/img/err.png");
		}
	});
	$("#tihuodanhao").focus(function() {
		$(this).siblings("img").attr("src", "cangchu/img/success.png");
	});

	// 验证输入的重量是否合法
	$("#guohuzhongliang").blur(function() {
		if ($(this).val() == "") {
			$(this).siblings("img").attr("src", "cangchu/img/err.png");
		}
	});
	$("#guohuzhongliang").focus(function() {
		$(this).siblings("img").attr("src", "cangchu/img/success.png");
	});

	// 当点击提交的时候验证
	$("#guohutijiao")
			.click(
					function() {
						var tihuodanhao = $("#tihuodanhao").val();
						if (tihuodanhao == "") {
							alert("请输入提货单号");
							$("#tihuodanhao").siblings("img").attr("src",
									"cangchu/img/err.png");
							return;
						}
						$("#tan_bottom table tr").not(":first").html("");
						var xx = 0;
						for ( var i = 0; i < $("#tab_context .tab_bottom").length; i++) {

							// 货物品类
							var pinlei = $("#tab_context .tab_bottom").eq(i)
									.find("#pinlei option:selected").text();
							// 名称
							var mingcheng = $("#tab_context .tab_bottom").eq(i)
									.find("#mingcheng option:selected").text();
							// 规格
							var guige = $("#tab_context .tab_bottom").eq(i)
									.find("#guige option:selected").text();
							// 材质
							var caizhi = $("#tab_context .tab_bottom").eq(i)
									.find("#caizhi option:selected").text();
							// 属性
							var shuxing = $("#tab_context .tab_bottom").eq(i)
									.find("#shuxing option:selected").text();
							// 产地
							var chandi = $("#tab_context .tab_bottom").eq(i)
									.find("#chandi option:selected").text();
							// 重量
							var zhongliang = $("#tab_context .tab_bottom")
									.eq(i).find("#guohuzhongliang").val();
							// 重量验证正则表达式
							var zhongyan = /^\d+(\.\d+)?$|^$|^(\d+|\-){7,}$/;

							if (chandi == "无") {
								alert("请选择货物！");
								return false;
							}
							for ( var j = 0; j < $("#tab_context .tab_bottom").length; j++) {
								var goodsid = $("#tab_context .tab_bottom").eq(
										i).find("#chandi option:selected")
										.val();
								var g = $("#tab_context .tab_bottom").eq(j)
										.find("#chandi option:selected").val();
								if (goodsid == g && i != j) {
									alert("选择的货物不可以重复，请将重复的货物删除或重新选择！");
									return false;
									break;
								}
							}
							// 验证重量
							if (zhongliang == "" || !zhongyan.test(zhongliang)) {
								$("#tab_context .tab_bottom").eq(i).find(
										"#guohuzhongliang").siblings("img")
										.attr("src", "cangchu/img/err.png");
								alert("请填写货物重量");
								return;
								break;
							}
							var chaofa = $("#wukucun_chaofa").val();
							if (chaofa == "0") {
								if (parseFloat($("#tab_context .tab_bottom")
										.eq(i)
										.find(".chandis .cc input")
										.eq(
												$("#tab_context .tab_bottom")
														.eq(i)
														.find(
																"#chandi option:selected")
														.index()).val()) < parseFloat(zhongliang)) {
									alert("过户重量不能大于库存重量！");
									return false;
								}
							} else {
								if (parseFloat($("#tab_context .tab_bottom")
										.eq(i)
										.find(".chandis .cc input")
										.eq(
												$("#tab_context .tab_bottom")
														.eq(i)
														.find(
																"#chandi option:selected")
														.index()).val()) < parseFloat(zhongliang)) {
									if (confirm("客户现有库存量小于出库重量，是否超发！") == false) {
										return false;
									}
								}
							}
							if ($("#zhuanchu").val() == ""
									|| $("#zhuanchu").val() == "无客户") {
								alert("请选择转出客户！");
								return false;
							}
							if ($("#zhuanru").val() == ""
									|| $("#zhuanru").val() == "无客户") {
								alert("请选择转入客户！");
								return false;
							}
							$("#zhuanru_fu").text(
									$("#zhuanru option:selected").text());
							$("#guohudanhao_fu").text($("#tihuodanhao").val());
							$("#tan_bottom table").append(
									"<tr style='background-color:#00FF7F; text-align:center;'><td ><strong>货物 "
											+ (i + 1)
											+ "</strong></td ><td><strong>"
											+ pinlei
											+ "</strong></td><td><strong>"
											+ mingcheng
											+ "</strong></td><td><strong>"
											+ guige
											+ "</strong></td><td><strong>"
											+ caizhi
											+ " </strong></td><td><strong>"
											+ shuxing
											+ "</strong></td><td><strong>"
											+ chandi + " </strong><td><strong>"
											+ zhongliang
											+ "吨 </strong></td></tr>");
						}
						// 确认提交
						$(".modeal_bg").animate({
							"opacity" : 0.5
						}).css("display", "block");
						$(".modeal_bottom").animate({
							"opacity" : 1,
							"top" : "25%"
						}).css("display", "block");
					});

	// 当点击添加货物的时候，复制窗口-->
	var i = 1;
	$("#xinzeng").click(
			function() {
				i++;
				$("#tab_context").append(
						$("#tab_context .tab_bottom").first().clone(true));
				$("#tab_context .tab_bottom").last().find("#goods").text(
						"货物" + i);

			});
	$("#delete").click(function() {
		if ($("#tab_context .tab_bottom").length > 1) {
			if (confirm("确定要删除吗？")) {
				$(this).parents(".tab_bottom").remove();
			}
		}
	});

	$("#delete").hover(function() {
		$(this).children("img").attr("src", "cangchu/img/delete_one.png");
	}, function() {
		$(this).children("img").attr("src", "cangchu/img/delete_two.png");
	});

	// 拖动模态框
	var $div = $(".modeal_bottom_top"); /* 绑定鼠标左键按住事件 */
	$div.bind("mousedown", function(event) { /* 获取需要拖动节点的坐标 */
		var offset_x = $("#modeal")[0].offsetLeft; // x坐标
		var offset_y = $("#modeal")[0].offsetTop; // y坐标 /* 获取当前鼠标的坐标 */
		var mouse_x = event.pageX;
		var mouse_y = event.pageY; /* 绑定拖动事件 *//* 由于拖动时，可能鼠标会移出元素，所以应该使用全局（document）元素 */
		$(document).bind("mousemove", function(ev) {
			/* 计算鼠标移动了的位置 */
			var _x = ev.pageX - mouse_x;
			var _y = ev.pageY - mouse_y; /* 设置移动后的元素坐标 */
			var now_x = (offset_x + _x) + "px";
			var now_y = (offset_y + _y) + "px"; /* 改变目标元素的位置 */
			$("#modeal").css({
				top : now_y,
				left : now_x
			});
		}); /* 当鼠标左键松开，接触事件绑定 */
		$(document).bind("mouseup", function() {
			$(this).unbind("mousemove");
		});

	});

	// 当点击关闭按钮的时候，将模态框关闭

	$("#close").click(function() {
		$(".modeal_bottom").css("display", "none");
		$(".modeal_bg").css("display", "none");
	});
	$("#closes").click(function() {
		$(".modeal_bottom").css("display", "none");
		$(".modeal_bg").css("display", "none");

	});
});

var eventHandler = function(name) {
	return function() {
		console.log(name);
		QueryZhuanChu();
	};
};
var eventHandlers = function(name) {
	return function() {
		console.log(name);
		QueryZhuanRu();
	};
};
// 查询转出客户
function QueryZhuanChu() {
	var $area = $("#zhuanchu").selectize({
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
							id : obj[i].id,
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
		QueryZhuanRu();

	});
}

// 查询转入客户
function QueryZhuanRu() {
	var $area = $("#zhuanru").selectize({
		valueField : 'id',
		labelField : 'title',
		searchField : 'title',
		sortField : 'title',
		options : [],
		create : false,
		dropdownParent : 'body',
		onFocus : eventHandlers('onFocus'),
	});
	var control = $area[0].selectize;
	$(function() {
		$.ajax({
			type : "post",
			url : "shiftStock.do?flag=QueryZhuanChu&ff=zhuanru",
			async : true,
			dataType : "json",
			data : {
				"clientBySstockShiftToFirm" : $("#zhuanchu").val()
			},
			success : function(obj) {
				control.clearOptions();
				if (obj != null) {
					for ( var i = 0; i < obj.length; i++) {
						control.addOption({
							id : obj[i].id,
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

function QueryPinlei() {
	$(function() {
		var length = $(".tab_bottom").length;
		if (parseInt(length) > 1) {
			$(".tab_bottom").not(".tab_bottom:first").remove();
		}

		$
				.ajax({
					type : "post",
					url : "shiftStockSeed.do?flag=getKeHuKuCun&ff=pinlei",
					async : true,
					dataType : "json",
					data : {
						"clientBySstockClientId" : $("#zhuanchu").val()
					},
					success : function(obj) {

						$(".pl").html("");
						if (obj != null) {
							var result = obj;
							for ( var i = 0; i < obj.length; i++) {
								for ( var j = 0; j < result.length; j++) {
									if (obj[i].pinleiId == result[j].pinleiId
											&& i != j) {
										obj[i].pinleiId = "";
									}
								}
							}
							for ( var i = 0; i < obj.length; i++) {
								if (obj[i].pinleiId != "") {
									$(".pl").append(
											"<option value='" + obj[i].pinleiId
													+ "'>" + obj[i].pinlei
													+ "</option>");
								}
							}
							QueryMingCheng($(".pl"));
						} else {
							$(".pl").append("<option value='无'>无</option>");
							QueryMingCheng($(".pl"));
						}
					},
					error : function() {
						document.location.href = "/XGProject/cangchu/page/login.jsp";
					}
				});

	});
}

function QueryMingCheng(str) {
	$(str).parents(".tab_bottom").find(".mc").html("");
	if ($(str).val() == '无') {
		$(str).parents(".tab_bottom").find(".mc").append(
				"<option value='无'>无</option>");
		QueryGuiGe($(str).parents(".tab_bottom").find(".mc"));
		return false;
	}
	$(function() {

		$.ajax({
			type : "post",
			url : "shiftStockSeed.do?flag=getKeHuKuCun&ff=mingcheng",
			async : true,
			dataType : "json",
			data : {
				"clientBySstockClientId" : $("#zhuanchu").val(),
				"pinlei" : $(str).val()
			},
			success : function(obj) {

				if (obj != null) {
					var result = obj;
					for ( var i = 0; i < obj.length; i++) {
						for ( var j = 0; j < result.length; j++) {
							if (obj[i].mingcheng == result[j].mingcheng
									&& i != j) {
								obj[i].mingcheng = "";
							}
						}
					}
					for ( var i = 0; i < obj.length; i++) {
						if (obj[i].mingcheng != "") {
							$(str).parents(".tab_bottom").find(".mc").append(
									"<option value='" + obj[i].mingcheng + "'>"
											+ obj[i].mingcheng + "</option>");
						}
					}
					QueryGuiGe($(str).parents(".tab_bottom").find(".mc"));
				} else {
					$(str).parents(".tab_bottom").find(".mc").append(
							"<option value='无'>无</option>");
					QueryGuiGe($(str).parents(".tab_bottom").find(".mc"));
				}
			},
			error : function() {
				document.location.href = "/XGProject/cangchu/page/login.jsp";
			}
		});

	});
}

function QueryGuiGe(str) {
	$(function() {
		$(str).parents(".tab_bottom").find(".gg").html("");
		if ($(str).val() == "无") {
			$(str).parents(".tab_bottom").find(".gg").append(
					"<option value='无'>无</option>");
			QueryCaiZhi($(str).parents(".tab_bottom").find(".gg"));
			return false;
		}
		$.ajax({
			type : "post",
			url : "shiftStockSeed.do?flag=getKeHuKuCun&ff=guige",
			async : true,
			dataType : "json",
			data : {
				"clientBySstockClientId" : $("#zhuanchu").val(),
				"pinlei" : $(str).parents(".tab_bottom").find(".pl").val(),
				"goodsname" : $(str).val()
			},
			success : function(obj) {

				if (obj != null) {
					var result = obj;
					for ( var i = 0; i < obj.length; i++) {
						for ( var j = 0; j < result.length; j++) {
							if (obj[i].guigeId == result[j].guigeId && i != j) {
								obj[i].guigeId = "";
							}
						}
					}
					for ( var i = 0; i < obj.length; i++) {
						if (obj[i].guigeId != "") {
							$(str).parents(".tab_bottom").find(".gg").append(
									"<option value='" + obj[i].guigeId + "'>"
											+ obj[i].gui + "</option>");
						}
					}
					QueryCaiZhi($(str).parents(".tab_bottom").find(".gg"));
				} else {
					$(str).parents(".tab_bottom").find(".gg").append(
							"<option value='无'>无</option>");
					QueryCaiZhi($(str).parents(".tab_bottom").find(".gg"));
				}
			},
			error : function() {
				document.location.href = "/XGProject/cangchu/page/login.jsp";
			}
		});
	});
}

function QueryCaiZhi(str) {
	$(str).parents(".tab_bottom").find(".cz").html("");
	if ($(str).val() == "无") {
		$(str).parents(".tab_bottom").find(".cz").append(
				"<option value='无'>无</option>");
		QueryShuXing($(str).parents(".tab_bottom").find(".cz"));
		return false;
	}
	$(function() {
		$
				.ajax({
					type : "post",
					url : "shiftStockSeed.do?flag=getKeHuKuCun&ff=caizhi",
					async : true,
					dataType : "json",
					data : {
						"clientBySstockClientId" : $("#zhuanchu").val(),
						"pinlei" : $(str).parents(".tab_bottom").find(".pl")
								.val(),
						"goodsname" : $(str).parents(".tab_bottom").find(".mc")
								.val(),
						"guige" : $(str).val()
					},
					success : function(obj) {

						if (obj != null) {
							var result = obj;
							for ( var i = 0; i < obj.length; i++) {
								for ( var j = 0; j < result.length; j++) {
									if (obj[i].caizhiId == result[j].caizhiId
											&& i != j) {
										obj[i].caizhiId = "";
									}
								}
							}
							for ( var i = 0; i < obj.length; i++) {
								if (obj[i].caizhiId != "") {
									$(str).parents(".tab_bottom").find(".cz")
											.append(
													"<option value='"
															+ obj[i].caizhiId
															+ "'>"
															+ obj[i].caizhi
															+ "</option>");
								}
							}
							QueryShuXing($(str).parents(".tab_bottom").find(
									".cz"));
						} else {
							$(str).parents(".tab_bottom").find(".cz").append(
									"<option value='无'>无</option>");
							QueryShuXing($(str).parents(".tab_bottom").find(
									".cz"));
						}
					},
					error : function() {
						document.location.href = "/XGProject/cangchu/page/login.jsp";
					}
				});
	});
}

function QueryShuXing(str) {
	$(str).parents(".tab_bottom").find(".sx").html("");
	if ($(str).val() == "无") {
		$(str).parents(".tab_bottom").find(".sx").append(
				"<option value='无'>无</option>");
		QueryChanDi($(str).parents(".tab_bottom").find(".sx"));
		return false;
	}
	$(function() {
		$.ajax({
			type : "post",
			url : "shiftStockSeed.do?flag=getKeHuKuCun&ff=shuxing",
			async : true,
			dataType : "json",
			data : {
				"clientBySstockClientId" : $("#zhuanchu").val(),
				"pinlei" : $(str).parents(".tab_bottom").find(".pl").val(),
				"goodsname" : $(str).parents(".tab_bottom").find(".mc").val(),
				"guige" : $(str).parents(".tab_bottom").find(".gg").val(),
				"caizhi" : $(str).val()
			},
			success : function(obj) {

				if (obj != null) {
					var result = obj;
					for ( var i = 0; i < obj.length; i++) {
						for ( var j = 0; j < result.length; j++) {
							if (obj[i].shuxingId == result[j].shuxingId
									&& i != j) {
								obj[i].shuxingId = "";
							}
						}
					}
					for ( var i = 0; i < obj.length; i++) {
						if (obj[i].shuxingId != "") {
							$(str).parents(".tab_bottom").find(".sx").append(
									"<option value='" + obj[i].shuxingId + "'>"
											+ obj[i].shuxing + "</option>");
						}
					}
					QueryChanDi($(str).parents(".tab_bottom").find(".sx"));
				} else {
					$(str).parents(".tab_bottom").find(".sx").append(
							"<option value='无'>无</option>");
					QueryChanDi($(str).parents(".tab_bottom").find(".sx"));
				}
			},
			error : function() {
				document.location.href = "/XGProject/cangchu/page/login.jsp";
			}
		});
	});
}

function QueryChanDi(str) {
	$(str).parents(".tab_bottom").find(".cd").html("");
	$(str).parents(".tab_bottom").find(".chandis .cc").html("");
	if ($(str).val() == "无") {
		$(str).parents(".tab_bottom").find(".cd").append(
				"<option value='无'>无</option>");
		return false;
	}
	$(function() {
		$.ajax({
			type : "post",
			url : "shiftStockSeed.do?flag=getKeHuKuCun&ff=chandi",
			async : true,
			dataType : "json",
			data : {
				"clientBySstockClientId" : $("#zhuanchu").val(),
				"pinlei" : $(str).parents(".tab_bottom").find(".pl").val(),
				"goodsname" : $(str).parents(".tab_bottom").find(".mc").val(),
				"guige" : $(str).parents(".tab_bottom").find(".gg").val(),
				"caizhi" : $(str).parents(".tab_bottom").find(".cz").val(),
				"shuxing" : $(str).val()
			},
			success : function(obj) {

				if (obj != null) {
					for ( var i = 0; i < obj.length; i++) {
						$(str).parents(".tab_bottom").find(".cd").append(
								"<option value='" + obj[i].goodsId + "'>"
										+ obj[i].chandi + "</option>");
						$(str).parents(".tab_bottom").find(".chandis .cc")
								.append(
										"<input type='hidden' value='"
												+ obj[i].zhongliang
												+ "' class='weight' />");
					}
				} else {
					$(str).parents(".tab_bottom").find(".cd").append(
							"<option value='无'>无</option>");
				}
			},
			error : function() {
				document.location.href = "/XGProject/cangchu/page/login.jsp";
			}
		});
	});
}

// 当页面加载完成的时候调用这些方法
function load() {
	QueryZhuanChu();
}

// 当转出客户的内容发生改变的时候去调用这些方法
function zhuanchudiaoyong() {
	QueryZhuanRu();

	QueryPinlei();
}