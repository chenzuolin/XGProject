//定义两个当前页数
var pageNow = 0;
var pageNowTow = 0;

// 第一次加载
function jiazaiLoad() {
	// 第一次加载，或点击该页面时清空
	$(".content_center div").remove();// 先清空，再添加;
	$("#chukupage").val("1");
	pageNow = 0;
	selectPlanInputChange();
	QueryKuQu();
}

function chukuDaichuli() {
	$(function() {
		var pageNow = $("#chukupage").val();
		var huozhu = $(".huozhu").val();
		var kehuhao = $(".kehudanhao").val();
		if (pageNow == "1") {
			$(".content_center").html("");
		}
		$
				.ajax({
					type : "post",
					url : "exportSeed.do?flag=getPeiSong",
					data : {
						"pageNow" : pageNow,
						"kehubianhao" : kehuhao,
						"jiancheng" : huozhu
					},
					dataType : "json",
					success : function(obj) {
						if (obj == null) {
							$(".content_center")
									.append(
											"<div class='content_tab'><table  cellpadding='0' cellspacing='0' style='margin-top: 0px;'><tbody><tr>"
													+ "<td colspan='9' align='center'><strong>无订单</strong></td></tr></tbody></table></div>");
							return false;
						}
						if (obj[0].max == "maxs") {
							alert("已到底部！");
							return false;
						}
						if (obj[0].result == null) {
							$(".content_center")
									.append(
											"<div class='content_tab'><table  cellpadding='0' cellspacing='0' style='margin-top: 0px;'><tbody><tr>"
													+ "<td colspan='9' align='center'><strong>无订单</strong></td></tr></tbody></table></div>");
							return false;
						}
						// $(".content_center div").remove();//先清空，再添加;
						$
								.each(
										obj,
										function(i, item) {
											var $div = $("<div class='content_tab'></div> ");
											$(".content_center").append($div);
											$div
													.append("<table cellpadding='0' cellspacing='0'>"
															+ "<tbody> "
															+ "<tr>"
															+ "<td class='zhankai' align='right' width='100px'><strong>订单编号：</strong></td>"
															+ "<td  class='zhankai' width='130px'>"
															+ "<span>"
															+ item.id
															+ "</span>"
															+ "<input type='hidden' id='ziDingdanId' value='"
															+ item.zid
															+ "' />"
															+ "<input type='hidden' id='huowu' value='"
															+ item.goodsId
															+ "' />"
															+ "</td>"
															+ "<td  class='zhankai' align='right' width='100px'><strong>货主：</strong></td>"
															+ "<td  class='zhankai' width='160px'><span>"
															+ item.huozhu
															+ "</span></td>"
															+ "<td  class='zhankai' align='right' width='100px;'><strong>客户单号：</strong></td>"
															+ "<td  class='zhankai' width='130px'><span>"
															+ item.kehudanhao
															+ "</span></td>"
															+ "<td  class='zhankai' align='right' width='100px'><strong>有效期：</strong></td>"
															+ "<td  class='zhankai' width='100px'><span>"
															+ item.youxiao
															+ "天</span></td>"
															+ "<td rowspan='3' valign='top' width='50px'><img src='cangchu/img/xiangxia.png' width='20' class='zhankai' style='display:block;margin-top:10px;' /></td>"
															+ "</tr>"
															+ "<tr>"
															+ "<td align='right' onclick='tanchuAdd(this)'><strong>运输方式：</strong></td>"
															+ "<td onclick='tanchuAdd(this)'><span>"
															+ item.yunshu
															+ "</span></td>"
															+ "<td align='right' onclick='tanchuAdd(this)'><strong>联系电话：</strong></td>"
															+ "<td onclick='tanchuAdd(this)'><span>"
															+ item.sijitel
															+ "</span></td>"
															+ "<td  class='zhankai' align='right' width='100px'><strong>订单状态：</strong></td>"
															+ "<td  class='zhankai' width='130px'><span style='color:red;'>"
															+ item.zhuangtai
															+ "</span></td>"
															+ "</tr>"
															+ "<tr class='tr_bg'>"
															+ "<td align='right' class='tr_border_left' onclick='tanchuAdd(this)'><strong>货物品类：</strong></td>"
															+ "<td onclick='tanchuAdd(this)'><span>"
															+ item.pinlei
															+ "</span></td>"
															+ "<td align='right' onclick='tanchuAdd(this)'><strong>名称：</strong></td>"
															+ "<td onclick='tanchuAdd(this)'><span>"
															+ item.mingcheng
															+ "</span></td>"
															+ "<td align='right' onclick='tanchuAdd(this)'><strong>规格：</strong></td>"
															+ "<td onclick='tanchuAdd(this)'><span>"
															+ item.guige
															+ "</span></td>"
															+ "<td align='right' onclick='tanchuAdd(this)'><strong>材质：</strong></td>"
															+ "<td class='tr_border_right' onclick='tanchuAdd(this)'><span>"
															+ item.caizhi
															+ "</span></td>"
															+ "</tr>"
															+ "<tr class='tr_bg'>"
															+ "<td align='right' class='tr_border_left_xia' onclick='tanchuAdd(this)'><strong>属性：</strong></td>"
															+ "<td onclick='tanchuAdd(this)'><span>"
															+ item.shuxing
															+ "</span></td>"
															+ "<td align='right' onclick='tanchuAdd(this)'><strong>产地：</strong></td>"
															+ "<td onclick='tanchuAdd(this)'><span>"
															+ item.chandi
															+ "</span></td>"
															+ "<td align='right' onclick='tanchuAdd(this)'><strong>应出重量：</strong></td>"
															+ "<td onclick='tanchuAdd(this)'><span>"
															+ item.zhongliang
															+ "吨<input type='hidden' value="
															+ item.zhongliang
															+ " class='yczld' /><input type='hidden' value='"
															+ item.shichuZL
															+ "' class='sczl' /></span></td>"
															+ "<td align='right' onclick='tanchuAdd(this)'><strong>发起时间：</strong></td>"
															+ "<td class='tr_border_right_xia' width='200px' onclick='tanchuAdd(this)'><span>"
															+ item.faqitime
															+ "</span></td>"
															+ "</tr>"
															+ "</tbody> "
															+ "</table>");
										});
						$("#chukupage").val(obj[0].pageNow);

						// 当点击要展开内容的时候
						diaoyong();

					},
					error : function() {
						document.location.href = "/XGProject/cangchu/page/login.jsp";
					}
				});
	});

}

// 当选择入库和出库操作时调用此方法
function selectPlanInputChange() {

	// 如果选择的是出库订单
	if ($("#type_xuanze").val() == "出库订单") {
		chukuDaichuli();
	} else {// 否则进去
	}
};

// 当选择查看更多时调用此方法
function selectGengduo() {

	// 如果选择的是出库订单
	if ($("#type_xuanze").val() == "出库订单") {
		var page = $("#chukupage").val();
		$("#chukupage").val(parseInt(page) + 1);
		selectPlanInputChange();

	} else {// 否则进去

		var $kehudanhao = $(".kehudanhao").val();
		var $huozhu = $(".huozhu").val();
		// 如果客户单号和货主都为空则调用selectPlanInputChange();
		if ($kehudanhao == "" && $huozhu == "") {
			selectPlanInputChange();
		} else {
			sousu_but();
		}
		// selectPlanInputChange();
		// else完
	}

};

function sousu_but() {
	$("#chukupage").val("1");
	var $kehudanhao = $(".kehudanhao").val();
	var $huozhu = $(".huozhu").val();
	// 如果选择的是出库订单
	if ($("#type_xuanze").val() == "出库订单") {
		selectPlanInputChange();

	} else {// 否则进去

	}

}

// 带条件查询时

$(function() {
	$(".kehudanhao").keydown(function(event) {
		if (event.keyCode == 13) {
			$("#sousu_but").click();
		}
	});
	$(".huozhu").keydown(function(event) {
		if (event.keyCode == 13) {
			$("#sousu_but").click();
		}
	});

	// 当点击查询的时候
	$("#sousu_but").click(function() {
		// 每当点击查询时，清空里面内容
		$(".content_center div").remove();// 先清空，再添加;

		pageNowTow = 0;
		// 调用待条件查询方法
		sousu_but();

	});

	/*----------------------------------------------*/

	$(".daichuli_content .content_top img").hover(function() {
		$(this).attr("src", "cangchu/img/shuaxin2.png");
	}, function() {
		$(this).attr("src", "cangchu/img/shuaxin.png");
	});

	/* 当点击刷新的时候触发 */
	$("#shuxin").click(function() {
		document.location.reload(); // 刷新
	});

	/* 当点击轮播器左右的时候触发 */
	$("#content_right").click(function() {
		var left = $(".content_content_zoudong table").css("left");
		var lefts = left.substr(0, left.length - 2);
		var width = $(".content_content_zoudong table").width();

		if ((parseInt(width) + parseInt(lefts)) > 1000) {
			$(".content_content_zoudong table").stop().animate({
				"left" : lefts - 500
			}, 500);
		}
	});

	$("#content_left").click(function() {
		var left = $(".content_content_zoudong table").css("left");
		var lefts = left.substr(0, left.length - 2);
		if (parseInt(lefts) < 0) {
			$(".content_content_zoudong table").stop().animate({
				"left" : parseInt(lefts) + 500
			}, 500);
		}
	});

	$("#close").click(function() {
		close();
	});

	$("#closes").click(function() {
		close();
	});

	$("#chuku_close").click(function() {
		close();
	});

	$("#chuku_closes").click(function() {
		close();
	});

	// 拖动模态框
	var $div = $(".modeal_top"); /* 绑定鼠标左键按住事件 */
	$div.bind("mousedown", function(event) { /* 获取需要拖动节点的坐标 */
		if ($("#type_xuanze").val() == "入库订单") {
			var offset_x = $("#ruku_modeal")[0].offsetLeft; // x坐标
			var offset_y = $("#ruku_modeal")[0].offsetTop; // y坐标 /* 获取当前鼠标的坐标
			// */
			var mouse_x = event.pageX;
			var mouse_y = event.pageY; /* 绑定拖动事件 *//* 由于拖动时，可能鼠标会移出元素，所以应该使用全局（document）元素 */
			$(document).bind("mousemove", function(ev) {
				/* 计算鼠标移动了的位置 */
				var _x = ev.pageX - mouse_x;
				var _y = ev.pageY - mouse_y; /* 设置移动后的元素坐标 */
				var now_x = (offset_x + _x) + "px";
				var now_y = (offset_y + _y) + "px"; /* 改变目标元素的位置 */
				$("#ruku_modeal").css({
					top : now_y,
					left : now_x
				});
			}); /* 当鼠标左键松开，接触事件绑定 */
			$(document).bind("mouseup", function() {
				$(this).unbind("mousemove");
			});
		} else {
			var offset_x = $("#chuku_modeal")[0].offsetLeft; // x坐标
			var offset_y = $("#chuku_modeal")[0].offsetTop; // y坐标 /* 获取当前鼠标的坐标
			// */
			var mouse_x = event.pageX;
			var mouse_y = event.pageY; /* 绑定拖动事件 *//* 由于拖动时，可能鼠标会移出元素，所以应该使用全局（document）元素 */
			$(document).bind("mousemove", function(ev) {
				/* 计算鼠标移动了的位置 */
				var _x = ev.pageX - mouse_x;
				var _y = ev.pageY - mouse_y; /* 设置移动后的元素坐标 */
				var now_x = (offset_x + _x) + "px";
				var now_y = (offset_y + _y) + "px"; /* 改变目标元素的位置 */
				$("#chuku_modeal").css({
					top : now_y,
					left : now_x
				});
			}); /* 当鼠标左键松开，接触事件绑定 */
			$(document).bind("mouseup", function() {
				$(this).unbind("mousemove");
			});
		}

	});

	/* 当点击提交的时候进行判断，提交的数据是否具有有效性 */
	// 当点击某一个客户发起的订单的时候，将该订单的id保存，添加到隐藏的文本框中，
	// 为了后期提交的时候将该id提交到服务器
	/* 当点击轮播器左右的时候触发 */
	$("#chuku_content_right").click(function() {
		var left = $("#chuku_kuwei table").css("left");
		var lefts = left.substr(0, left.length - 2);
		var width = $("#chuku_kuwei table").width();
		if ((parseInt(width) + parseInt(lefts)) > 1000) {
			$("#chuku_kuwei table").stop().animate({
				"left" : lefts - 500
			}, 500);
		}
	});

	$("#chuku_content_left").click(function() {
		var left = $("#chuku_kuwei table").css("left");
		var lefts = left.substr(0, left.length - 2);
		if (parseInt(lefts) < 0) {
			$("#chuku_kuwei table").stop().animate({
				"left" : parseInt(lefts) + 500
			}, 500);
		}
	});

	/* 当单机选中批次的时候 */
	$(".pici_xuanze ul li input[type=checkbox]").click(function() {
		if ($(this).attr("checked")) {
			$(this).next("label").css({
				"color" : "red",
				"font-weight" : "bold"
			});
		} else {
			$(this).next("label").css({
				"color" : "#000000",
				"font-weight" : "normal"
			});
		}
	});

	// 当分配完出库的订单的时候验证
	$("#daichuli_chuku_tijiao")
			.click(
					function() {
						var caozuoyuan = $("#chuku_modeal #caozuoyuan").val();
						if (caozuoyuan == "" || caozuoyuan == "无在线人员") {
							alert("请选择操作员！");
							return false;
						}

						var checks = $("#chuku_modeal .content_content_zoudong table tr td input[type=checkbox]:checked").length;
						if (checks <= 0) {
							alert("请选择库位！");
							return false;
						}
						var checks = $("#chuku_modeal .pici .pici_xuanze ul li input[type=checkbox]:checked").length;
						if (checks <= 0) {
							alert("请选择批次！");
							return false;
						}
						var fpzl = $("#feipeizhongliang").val();
						var yczl = $("#yingchuzhogliang").val();
						var zhongyan = /^\d+(\.\d+)?$|^$|^(\d+|\-){7,}$/;
						var sczl = $("#shichuzhongliang").val();
						if (fpzl == "" || zhongyan.test(fpzl) == false) {
							alert("请正确填写分配重量！");
							return false;
						}
						if (parseFloat(fpzl) > parseFloat(yczl)) {
							alert("分配重量不能大于出库重量！");
							return false;
						}
						if (parseFloat(yczl) <= parseFloat(sczl)) {
							if (confirm("该订单应出重量已完成，是否再次出库！") == false) {
								return false;
							}
						}
						/*
						 * var checks = $("#chuku_modeal .pici_xuanze ul li
						 * input[type=checkbox]:checked").length; if (checks <=
						 * 0) { alert("请选择批次！"); return; }
						 */
						// 查询选择的执行人员是否作业，如果作业弹出提醒
						$
								.ajax({
									type : "post",
									url : "interiorUser.do?flag=ShiFouZuoYe&id="
											+ $(".chukucaozuo").val()
											+ "&time=" + new Date().getTime(),
									async : true,
									dataType : "json",
									success : function(obj) {
										if (obj[0].result == "zaixian") {
											if (confirm("该操作员已经作业，是否再次分配！")) {
												$("#chukuForm").submit();
											}
										} else {
											if (confirm("确定提交吗？")) {
												$("#chukuForm").submit();
											}
										}
									},
									error : function() {
										document.location.href = "/XGProject/cangchu/page/login.jsp";
									}
								});

					});

});

// 查询对应的库区
function QueryKuQu() {
	$
			.ajax({
				type : "post",
				url : "bursary.do?flag=getKuQuDaiChuLi&time="
						+ new Date().getTime(),
				async : true,
				dataType : "json",
				success : function(obj) {
					$(".kuqu").html("");
					if (obj != null) {

						for ( var i = 0; i < obj.length; i++) {
							if (i == 0) {
								$(".kuqu")
										.append(
												"<span><input type='radio' value='"
														+ obj[i].id
														+ "' name='kuqu' id='kuqu"
														+ obj[i].id
														+ "' checked='checked' ><label for='kuqu"
														+ obj[i].id
														+ "' style='color:red; font-weight:bold'>"
														+ obj[i].name
														+ "</label></span>");
							} else {
								$(".kuqu").append(
										"<span><input type='radio' name='kuqu'value='"
												+ obj[i].id + "' id='kuqu"
												+ obj[i].id
												+ "'><label for='kuqu"
												+ obj[i].id + "'>"
												+ obj[i].name
												+ "</label></span>");
							}
						}
					} else {
						$(".kuqu").append("无库区</option>");
					}

					// 当单机库区进行选择的时候改变对应选中的颜色
					$(".kuqu input[type=radio]").click(function() {
						$(".kuqu label").css({
							"color" : "black",
							"font-weight" : "normal"
						});
						if ($(this).attr("checked") == "checked") {
							$(this).next("label").css({
								"color" : "red",
								"font-weight" : "bold"
							});
							selectKuwei();
						}
						;
					});
				},
				error : function() {
					document.location.href = "/XGProject/cangchu/page/login.jsp";
				}
			});

	selectKuwei();
}

/* 当单机关闭的时候调用 */
function close() {
	$(".daichuli_modeal").css("display", "none").animate({
		"opacity" : "0"
	}, 100);
	$(".modeal_bg").css("display", "none").animate({
		"opacity" : "0"
	}, 100);
}

function diaoyong() {
	// 当点击图片展开掉用该方法
	$(".zhankai").click(function() {
		var h = $(this).parents(".content_tab").css("height");
		if (h == "50px") {
			$(this).parents(".content_tab").stop().animate({
				"height" : "180px"
			}, 200);
		} else {
			$(this).parents(".content_tab").stop().animate({
				"height" : "50px"
			}, 200);
		}
	});

	/* 当单机查看详情的时候触发 */
	/*
	 * $(".content_tab table tr td").not("td:last-child").click(function() {
	 * 
	 * var $mo=$("this").children().find("#td").text(); alert($mo);
	 * 
	 * var type = $("#type_xuanze").val(); if(type == "入库订单") {
	 * 
	 * $("#ruku_modeal").css("display", "block").animate({ "opacity": "1" },
	 * 100); $(".modeal_bg").css("display", "block").animate({ "opacity": "0.5" },
	 * 100); //调用保管方法，然后赋值 selectBaoGuan(); //调用库位 selectKuwei(); } else if(type ==
	 * "出库订单") { $("#chuku_modeal").css("display", "block").animate({ "opacity":
	 * "1" }, 100); $(".modeal_bg").css("display", "block").animate({ "opacity":
	 * "0.5" }, 100); }
	 * 
	 * });
	 */
}

function tanchuAdd(This) {
	// 获取子订单id
	var ziDingdanId = $(This).parents(".content_tab").find("tr").eq(0)
			.children("td").find("#ziDingdanId").val();
	// 把获取的子订单id付给类型为隐藏的input，提交到form中
	$("#ziId").val(ziDingdanId);
	$("#chukuZid").val(ziDingdanId);
	var type = $("#type_xuanze").val();
	if (type == "入库订单") {

		$("#ruku_modeal").css("display", "block").animate({
			"opacity" : "1"
		}, 100);
		$(".modeal_bg").css("display", "block").animate({
			"opacity" : "0.5"
		}, 100);
		// 调用保管方法，然后赋值
		selectBaoGuan();
		// 调用库位
	} else if (type == "出库订单") {
		$("#yingchuzhogliang").val(
				$(This).parents(".content_tab").find(".yczld").val());// ---------------------------------------------------------------------------------------------
		$("#shichuzhongliang").val(
				$(This).parents(".content_tab").find(".sczl").val());
		$(".pici_xuanze ul").html("");
		var huowu = $(This).parents(".content_tab").find("tr").eq(0).children(
				"td").find("#huowu").val();
		$("#chukuhuowu").val(huowu);
		zhixingchuku();// 查询对应的出库执行人
		selectchukukuwei(huowu);// 查询对应的出库时的库位
		$("#chuku_modeal").css("display", "block").animate({
			"opacity" : "1"
		}, 100);
		$(".modeal_bg").css("display", "block").animate({
			"opacity" : "0.5"
		}, 100);
	}

}

// 查询对应的出库的执行人
function zhixingchuku() {
	$(function() {
		$.ajax({
			type : "post",
			url : "checks.do?flag=getChuKuCZY&ff=zhixing&time="
					+ new Date().getTime(),
			async : true,
			dataType : "json",
			success : function(obj) {
				$(".chukucaozuo").html("");
				if (obj != null) {

					if (obj[0].result != null && obj[0].id != null) {
						for ( var i = 0; i < obj.length; i++) {
							$(".chukucaozuo").append(
									"<option value='" + obj[i].id + "'>"
											+ obj[i].name + "</option>");
						}
					} else {
						$(".chukucaozuo").append(
								"<option value='无在线人员'>无在线人员</option>");
					}
				} else {
					$(".chukucaozuo").append(
							"<option value='无在线人员'>无在线人员</option>");
				}
			},
			error : function() {
				document.location.href = "/XGProject/cangchu/page/login.jsp";
			}
		});
	});
}

// 当出库的时候通过货物进行查询对应的库位
function selectchukukuwei(huowu) {
	$(function() {
		$("#chuku_kuwei table").html("");
		$.ajax({
			type : "post",
			url : "tarehouseGoods.do?flag=selectChuKuKuWei&goodsId=" + huowu
					+ "&time=" + new Date().getTime(),
			async : true,
			dataType : "json",
			success : function(obj) {
				if (obj == null) {
					$("#chuku_kuwei table").append("无库位");
					return false;
				}
				if (obj[0].result == null) {
					$("#chuku_kuwei table").append("无库位");
					return false;
				}

				var fhj = 1;
				for ( var i = 0; i < obj.length; i++) {
					if (i == 0) {
						$("#chuku_kuwei table").append("<tr></tr>");
					}
					if (i == (obj.length / 2)) {
						$("#chuku_kuwei table").append("<tr></tr>");
						fhj = 2;
					}
					if (fhj == 1) {
						$("#chuku_kuwei table tr").first("tr").append(
								"<td><input type='checkbox' onclick='kuweixuanze(this)' name='kuwei' value='"
										+ obj[i].id + "' id='ckuwei" + i
										+ "' />" + "<label for='ckuwei" + i
										+ "' title='重量：" + obj[i].weight
										+ "吨，件数：" + obj[i].number + obj[i].unit
										+ "'>" + obj[i].name + "</label>"
										+ "</td>");
					} else {
						$("#chuku_kuwei table tr").last("tr").append(
								"<td><input type='checkbox' onclick='kuweixuanze(this)' name='kuwei' value='"
										+ obj[i].id + "' id='ckuwei" + i
										+ "' />" + "<label for='ckuwei" + i
										+ "' title='重量：" + obj[i].weight
										+ "吨，件数：" + obj[i].number + obj[i].unit
										+ "'>" + obj[i].name + "</label>"
										+ "</td>");
					}
				}
			},
			error : function() {
				document.location.href = "/XGProject/cangchu/page/login.jsp";
			}
		});
	});
}

// 查询保管
function selectBaoGuan() {
	$.ajax({// ajax提交方式
		type : "post",
		url : "input.do?flag=selectBaoguanAjax",
		dataType : "json",
		success : function(baoguan) {
			if (baoguan.result == "null") {
				$(".baoguan option").remove();// 先清空，再添加;
				var $select = $(".baoguan");
				$select.append("<option value='无'>无</option> ");
				return;
			}
			if (baoguan.length > 0) {
				$(".baoguan option").remove();// 先清空，再添加;
				var $select = $(".baoguan");
				$.each(baoguan, function(i, item) {
					$select.append("<option value=" + item.id + ">" + item.name
							+ "</option> ");
				});
			} else {
				$(".baoguan option").remove();// 先清空，再添加;
				var $select = $(".baoguan");
				$select.append("<option value='无'>无</option> ");
			}
		},
		error : function() {
			document.location.href = "/XGProject/cangchu/page/login.jsp";
		}
	});

}

function selectKuwei() {
	$(function() {
		var kuqu = $(".kuqu input[name=kuqu]:checked").val();
		var yan = /^\d+$/;
		if (yan.test(kuqu) == false) {
			return false;
		}
		$("#tableKuwei table").html("");// 先清空，再添加;
		$
				.ajax({// ajax提交方式
					type : "post",
					url : "input.do?flag=selectKuweiAjax",
					async : true,
					data : {
						"kuqu" : kuqu
					},
					dataType : "json",
					success : function(kuwei) {

						var h = 1;
						for ( var i = 0; i < kuwei.length; i++) {
							if (i == 0) {
								$("#tableKuwei table").append("<tr></tr>");
							}
							if (i == (kuwei.length / 2)) {
								$("#tableKuwei table").append("<tr></tr>");
								h = 2;
							}

							if (h == 1) {
								$("#tableKuwei table tr")
										.first("tr")
										.append(
												"<td>"
														+ "<input type='checkbox' onclick='gaibianColor(this)' style='zoom:120%;' id='kuwei1+"
														+ kuwei[i].id
														+ "' name='kuwei' value='"
														+ kuwei[i].id
														+ "'/>"
														+ "<label style='fong-size:20px'  for='kuwei1+"
														+ kuwei[i].id + "'>"
														+ kuwei[i].name
														+ "</label>" + "</td>");
							} else {

								$("#tableKuwei table tr")
										.last("tr")
										.append(
												"<td>"
														+ "<input type='checkbox' onclick='gaibianColor(this)' style='zoom:120%;' id='kuwei1+"
														+ kuwei[i].id
														+ "' name='kuwei' value='"
														+ kuwei[i].id
														+ "'/>"
														+ "<label style='fong-size:20px'  for='kuwei1+"
														+ kuwei[i].id + "'>"
														+ kuwei[i].name
														+ "</label>" + "</td>");
							}

						}
						;
						okSubmit();
						// }
					},
					error : function() {
						document.location.href = "/XGProject/cangchu/page/login.jsp";
					}
				});

	});

}

function okSubmit() {
	// 点击提交
	/* 当点击提交的时候进行判断，提交的数据是否具有有效性 */
	// 当点击某一个客户发起的订单的时候，将该订单的id保存，添加到隐藏的文本框中，
	// 为了后期提交的时候将该id提交到服务器
	$("#daichuli_ruku_tijiao")
			.click(
					function() {
						var caozuoyuan = $("#ruku_modeal #caozuoyuan").val();
						if (caozuoyuan == "无") {
							alert("请选择操作员！");
							return false;
						}
						var checks = $(".content_content_zoudong table tr td input[type=checkbox]:checked").length;
						if (checks <= 0) {
							alert("请选择库位！");
							return false;
						}
						// alert("数据填入成功，可以提交服务器");
						$("#okForm").submit();
					});

}

// 改变入库选中的颜色
function gaibianColor(str) {
	$(function() {
		if ($(str).attr("checked")) {
			$(str).next("label").css({
				"color" : "red",
				"font-weight" : "bold"
			});
		} else {
			$(str).next("label").css({
				"color" : "#000000",
				"font-weight" : "normal"
			});
		}
	});

}

/* 当单机选中库位的时候 */
function kuweixuanze(str) {

	$(function() {
		if ($(str).attr("checked")) {
			$(str).next("label").css({
				"color" : "red",
				"font-weight" : "bold"
			});
		} else {
			$(str).next("label").css({
				"color" : "#000000",
				"font-weight" : "normal"
			});
		}
		$(".pici_xuanze ul").html("");
		for ( var i = 0; i < $("#chuku_kuwei table tr td input[type=checkbox]").length; i++) {
			if ($("#chuku_kuwei table tr td input[type=checkbox]").eq(i).attr(
					"checked")) {
				$
						.ajax({// ajax提交方式
							type : "post",
							url : "tarehouseDetail.do?flag=getChuKuPiCi",
							dataType : "json",
							data : {
								"goods" : $("#chukuhuowu").val(),
								"kuwei" : $(
										"#chuku_kuwei table tr td input[type=checkbox]")
										.eq(i).val()
							},
							success : function(obj) {
								if (obj == null) {
									return false;
								}
								if (obj[0].result == null) {
									return false;
								}
								for ( var i = 0; i < obj.length; i++) {
									$(".pici_xuanze ul")
											.append(
													"<li onmouseover='picishow(this)' onmouseout='picihidden(this)'><input type='checkbox' value='"
															+ obj[i].id
															+ "' id='pici"
															+ obj[i].id
															+ "' name='pici' />"
															+ "<label for='pici"
															+ obj[i].id
															+ "' title='剩余重量："
															+ (parseFloat(obj[i].weight) - parseFloat(obj[i].Eweight))
															+ "吨,剩余件数："
															+ (parseFloat(obj[i].number) - parseFloat(obj[i].Enumber))
															+ obj[i].unit
															+ "'>"
															+ obj[i].id
															+ "</label></li>");
								}
							},
							error : function() {
								document.location.href = "/XGProject/cangchu/page/login.jsp";
							}
						});
			}
		}

	});
}
function picishow(str) {
	$(function() {
		str.onmousemove = function(event) {
			var x = event.pageX - 300;
			var y = event.pageY - 70;
			$(".showpici").css({
				"left" : x,
				"top" : y,
				"display" : "block"
			});
			$("#showpici span").html("");
			var remark = $(str).find("label").attr("title");
			$("#showpici span").text(remark);
		};
	});
}
function picihidden() {
	$(".showpici").css({
		"display" : "none"
	});
}
