//定义两个当前页数
var pageNow = 0;
var pageNowTow = 0;

// 第一次加载
function jiazaiLoad() {
	// 第一次加载，或点击该页面时清空
	$(".content_center").html("");
	pageNow = 0;
	selectSibangPlanInput();
}

// 当选择入库和出库操作时调用此方法

function chukuguobang() {
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
					url : "exportOperate.do?flag=getGuoBang",
					data : {
						"pageNow" : pageNow,
						"kehudaohao" : kehuhao,
						"jiacheng" : huozhu
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
													.append("<table cellpadding='0' cellspacing='0' title='开始作业'>"
															+ "<tbody> "
															+ "<tr>"
															+ "<td onclick='tanchuAdd(this)' align='right'><strong>订单编号：</strong></td>"
															+ "<td onclick='tanchuAdd(this)'>"
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
															+ "<td onclick='tanchuAdd(this)' align='right'><strong>货主：</strong></td>"
															+ "<td onclick='tanchuAdd(this)'><span>"
															+ item.huozhu
															+ "</span></td>"
															+ "<td onclick='tanchuAdd(this)' align='right'><strong>客户单号：</strong></td>"
															+ "<td onclick='tanchuAdd(this)'><span>"
															+ item.kehudanhao
															+ "</span></td>"
															+ "<td onclick='tanchuAdd(this)' align='right'><strong>有效期：</strong></td>"
															+ "<td onclick='tanchuAdd(this)'><span>"
															+ item.youxiao
															+ "</span></td>"
															+ ""
															+ "<td rowspan='3' valign='top'><img src='cangchu/img/xiangxia.png' width='20' class='zhankai' style='display:block;margin-top:10px;' /></td>"
															+ "</tr>"
															+ "<tr>"
															+ "<td align='right' onclick='tanchuAdd(this)'><strong>运输方式：</strong></td>"
															+ "<td onclick='tanchuAdd(this)'><span>"
															+ item.yunshu
															+ "</span></td>"
															+ "<td align='right' onclick='tanchuAdd(this)'><strong>车号：</strong></td>"
															+ "<td onclick='tanchuAdd(this)'><span>"
															+ item.chehao
															+ "</span></td>"
															+ "<td align='right' onclick='tanchuAdd(this)'><strong>司机姓名：</strong></td>"
															+ "<td onclick='tanchuAdd(this)'><span>"
															+ item.siji
															+ "</span></td>"
															+ "<td align='right' onclick='tanchuAdd(this)'><strong>司机电话：</strong></td>"
															+ "<td onclick='tanchuAdd(this)'><span>"
															+ item.sijitel
															+ "</span></td>"
															+ "<td colspan='2' rowspan='4'>"
															+ "<input type='hidden' value='"
															+ item.cid
															+ "' class='ckcid'/></td>"
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
															+ "吨</span></td>"
															+ "<td align='right' onclick='tanchuAdd(this)'><strong>发起时间：</strong></td>"
															+ "<td class='tr_border_right_xia' onclick='tanchuAdd(this)'><span>"
															+ item.faqitime
															+ "</span></td>"
															+ "</tr><tr class='tr_diaodu_bg'><td align='right' class='tr_border_left' onclick='tanchuAdd(this)'><strong>调度员：</strong></td>"
															+ "<td onclick='tanchuAdd(this)'><span>"
															+ item.diaodu
															+ "</span></td>"
															+ "<td align='right' onclick='tanchuAdd(this)'><strong>分配时间：</strong></td>"
															+ "<td onclick='tanchuAdd(this)'><span>"
															+ item.fenpeitime
															+ "</span></td>"
															+ "<td align='right' onclick='tanchuAdd(this)'><strong>过磅/理算：</strong></td>"
															+ "<td onclick='tanchuAdd(this)'><span>"
															+ item.lisuan
															+ "</span></td>"
															+ "<td align='right' onclick='tanchuAdd(this)'><strong>分配重量：</strong></td>"
															+ "<td class='tr_border_right' onclick='tanchuAdd(this)'><span>"
															+ item.fenpeiweight
															+ "吨</span></td></tr>"
															+ "<tr class='tr_diaodu_bg'>"
															+ "<td align='right' class='tr_border_left_xia' onclick='tanchuAdd(this)'><strong>库位：</strong></td>"
															+ "<td onclick='tanchuAdd(this)'><span>"
															+ item.kuwei
															+ "</span></td>"
															+ "<td align='right'><strong onclick='tanchuAdd(this)'>备注：</strong></td>"
															+ "<td colspan='5' class='tr_border_right_xia' onclick='tanchuAdd(this)'><span>"
															+ item.beizhu
															+ "</span></td></tr>"
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

function selectSibangPlanInput() {
	// 如果选择的是出库订单
	if ($("#type_xuanze").val() == "出库订单") {
		chukuguobang();

	} else {// 否则进去
		// 当前页自加一
		$("#chukupage").val("1");
		pageNow++;

		$
				.ajax({
					type : "post",
					url : "inputOperate.do?flag=selectSibangInputAjax",
					data : {
						"pageNow" : pageNow
					},// 传入当前页
					dataType : "json",
					success : function(obj) {
						// $(".content_center").html("");
						if (obj.qingkong == "clean") {
							alert("请求数据已完！");
						}
						if (obj == null) {
							$(".content_center")
									.append(
											"<div class='content_tab'><table  cellpadding='0' cellspacing='0' style='margin-top: 0px;'><tbody><tr>"
													+ "<td colspan='9' align='center'><strong>无订单</strong></td></tr></tbody></table></div>");
						}
						if (obj.length > 0) {
							$
									.each(
											obj,
											function(i, item) {
												$(".content_center")
														.append(
																"<div class='content_tab' title='开始作业'>"
																		+ "<table cellpadding='0' cellspacing='0' style='margin-top: 0px;'><tbody>"
																		+ "<tr>"
																		+ "<td onclick='tiaozhuanruku(this)' style='width:8%' align='right'><strong>订单编号：</strong></td>"
																		+ "<td onclick='tiaozhuanruku(this)' style='width:13%'>"
																		+ "<span>"
																		+ item.zongId
																		+ "</span>"
																		+ "<input type='hidden' id='ziId' value='"
																		+ item.inputSeedId
																		+ "' />"
																		+ "<input type='hidden' id='caozuoId'  value='"
																		+ item.inputOperateId
																		+ "' />"
																		+ "</td>"
																		+ "<td onclick='tiaozhuanruku(this)' style='width:8%' align='right'><strong>货主：</strong></td>"
																		+ "<td onclick='tiaozhuanruku(this)' style='width:14%'><span>"
																		+ item.clientLoginName
																		+ "</span></td>"
																		+ "<td onclick='tiaozhuanruku(this)' style='width:8%' align='right'><strong>入库单号：</strong></td>"
																		+ "<td onclick='tiaozhuanruku(this)' style='width:18%'><span>"
																		+ item.inputClientNumber
																		+ "</span></td>"
																		+ "<td onclick='tiaozhuanruku(this)' align='right'><strong>有效期：</strong></td>"
																		+ "<td onclick='tiaozhuanruku(this)'><span>"
																		+ item.inputDefinedOne
																		+ "</span></td>"
																		+ "<td rowspan='4' valign='top'><img src='cangchu/img/xiangxia.png' width='20' class='caozuo_zhankai' onclick='showHidden(this)' style='display:block;margin-top:10px;' /></td>"
																		+ "</tr>"
																		+ "<tr>"
																		+ "<td onclick='tiaozhuanruku(this)' align='right'><strong>运输方式：</strong></td>"
																		+ "<td onclick='tiaozhuanruku(this)'><span>"
																		+ item.inputCarryType
																		+ "</span></td>"
																		+ "<td onclick='tiaozhuanruku(this)' align='right'><strong>车号：</strong></td>"
																		+ "<td onclick='tiaozhuanruku(this)'><span>"
																		+ item.inputPlateNumber
																		+ "</span></td>"
																		+ "<td onclick='tiaozhuanruku(this)' align='right'><strong>司机姓名：</strong></td>"
																		+ "<td onclick='tiaozhuanruku(this)'><span>"
																		+ item.inputDriverName
																		+ "</span></td>"
																		+ "<td onclick='tiaozhuanruku(this)' align='right'><strong>司机电话：</strong></td>"
																		+ "<td onclick='tiaozhuanruku(this)'><span>"
																		+ item.inputDriverTel
																		+ "</span></td>"
																		+ "</tr>"
																		+ "<tr class='tr_bg'>"
																		+ "<td onclick='tiaozhuanruku(this)' align='right' class='tr_border_left'><strong>货物品类：</strong></td>"
																		+ "<td onclick='tiaozhuanruku(this)'><span>"
																		+ item.goodsCategoryName
																		+ "</span></td>"
																		+ "<td onclick='tiaozhuanruku(this)' align='right'><strong>名称：</strong></td>"
																		+ "<td onclick='tiaozhuanruku(this)'><span>"
																		+ item.goodsName
																		+ "</span></td>"
																		+ "<td onclick='tiaozhuanruku(this)' align='right'><strong>规格：</strong></td>"
																		+ "<td onclick='tiaozhuanruku(this)'><span>"
																		+ item.goodsStandardName
																		+ "</span></td>"
																		+ "<td onclick='tiaozhuanruku(this)' align='right'><strong>材质：</strong></td>"
																		+ "<td onclick='tiaozhuanruku(this)' class='tr_border_right'><span>"
																		+ item.goodsQualityName
																		+ "</span></td>"
																		+ "</tr>"
																		+ "<tr class='tr_bg'>"
																		+ "<td onclick='tiaozhuanruku(this)' align='right' class='tr_border_left_xia'><strong>属性：</strong></td>"
																		+ "<td onclick='tiaozhuanruku(this)'><span>"
																		+ item.goodsPropertyName
																		+ "</span></td>"
																		+ "<td onclick='tiaozhuanruku(this)' align='right'><strong>产地：</strong></td>"
																		+ "<td onclick='tiaozhuanruku(this)'><span>"
																		+ item.goodsYieldlyName
																		+ "</span></td>"
																		+ "<td onclick='tiaozhuanruku(this)' align='right'><strong>重量：</strong></td>"
																		+ "<td onclick='tiaozhuanruku(this)'><span>"
																		+ item.iseedShouldWeight
																		+ "</span></td>"
																		+ "<td onclick='tiaozhuanruku(this)' align='right'><strong>发起时间：</strong></td>"
																		+ "<td onclick='tiaozhuanruku(this)' class='tr_border_right_xia'><span>"
																		+ item.inputCreateTime
																		+ "</span></td>"
																		+ "</tr>"
																		+ "<tr class='tr_diaodu_bg'>"
																		+ "<td onclick='tiaozhuanruku(this)' align='right' class='tr_border_left'><strong>调度员：</strong></td>"
																		+ "<td onclick='tiaozhuanruku(this)'><span>"
																		+ item.diaodu
																		+ "</span></td>"
																		+ "<td onclick='tiaozhuanruku(this)' align='right'><strong>分配时间：</strong></td>"
																		+ "<td onclick='tiaozhuanruku(this)'><span>"
																		+ item.fenpeiTime
																		+ "</span></td>"
																		+ "<td onclick='tiaozhuanruku(this)' align='right'><strong>过磅/理算：</strong></td>"
																		+ "<td onclick='tiaozhuanruku(this)'><span>"
																		+ item.guoLi
																		+ "</span></td>"
																		+ "<td onclick='tiaozhuanruku(this)' align='right'><strong>分配重量：</strong></td>"
																		+ "<td onclick='tiaozhuanruku(this)' class='tr_border_right'><span>"
																		+ item.feipeiWeight
																		+ "</span></td>"
																		+ "</tr>"
																		+ "<tr class='tr_diaodu_bg'>"
																		+ "<td onclick='tiaozhuanruku(this)' align='right' class='tr_border_left_xia'><strong>库位：</strong></td>"
																		+ "<td onclick='tiaozhuanruku(this)'><span>"
																		+ item.kuwei
																		+ "</span></td>"
																		+ "<td onclick='tiaozhuanruku(this)' align='right'><strong>保管员：</strong></td>"
																		+ "<td onclick='tiaozhuanruku(this)'><span>"
																		+ item.baoguan
																		+ "</span></td>"
																		+ "<td onclick='tiaozhuanruku(this)' align='right' ><strong>备注：</strong></td>"
																		+ "<td onclick='tiaozhuanruku(this)' title="+ item.beizhu+ "><span class='span' style='display:block;width: 200px;'>"
																		+ item.beizhu
																		+ "</span></td>"
																		+ "<td onclick='tiaozhuanruku(this)' align='right'><strong>件数：</strong></td>"
																		+ "<td onclick='tiaozhuanruku(this)' class='tr_border_right_xia'><span>"
																		+ item.shijiJianshu
																		+ "</span></td>"
																		+
																		// "<td
																		// colspan='2'
																		// class='tr_border_right_xia'></td>"+
																		"</tr>"
																		+

																		"</tbody></table></div>");
											});
						}
					},
					error : function() {
						document.location.href = "/XGProject/cangchu/page/login.jsp";
					}
				});
		// ---ajax完毕
	}

};

// 当选择查看更多时调用此方法
function selectGengduo() {

	// 如果选择的是出库订单
	if ($("#type_xuanze").val() == "出库订单") {
		var page = $("#chukupage").val();
		$("#chukupage").val(parseInt(page) + 1);
		chukuguobang();

	} else {// 否则进去

		var $kehudanhao = $(".kehudanhao").val();
		var $huozhu = $(".huozhu").val();
		// 如果客户单号和货主都为空则调用selectPlanInputChange();
		if ($kehudanhao == "" && $huozhu == "") {
			selectSibangPlanInput();
		} else {// 否则调用sousu_but方法
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
		chukuguobang();

	} else {// 否则进去
		// 有条件页数自增一
		pageNowTow++;

		$
				.ajax({
					type : "post",
					url : "inputOperate.do?flag=selectSibangInputAjaxTj",
					data : {
						"danhao" : $kehudanhao,
						"huozhu" : $huozhu,
						"pageNow" : pageNowTow
					},
					dataType : "json",
					success : function(obj) {
						// $(".content_center").html("");
						if (obj.qingkong == "clean") {
							alert("请求数据已完！");
						}

						if (obj == null) {
							$(".content_center")
									.append(
											"<div class='content_tab'><table  cellpadding='0' cellspacing='0' style='margin-top: 0px;'><tbody><tr>"
													+ "<td colspan='9' align='center'><strong>无订单</strong></td></tr></tbody></table></div>");
						}

						if (obj.length > 0) {
							$
									.each(
											obj,
											function(i, item) {
												$(".content_center")
														.append(
																"<div class='content_tab' title='开始作业'>"
																+ "<table cellpadding='0' cellspacing='0' style='margin-top: 0px;'><tbody>"
																+ "<tr>"
																+ "<td onclick='tiaozhuanruku(this)' style='width:8%' align='right'><strong>订单编号：</strong></td>"
																+ "<td onclick='tiaozhuanruku(this)' style='width:13%'>"
																+ "<span>"
																+ item.zongId
																+ "</span>"
																+ "<input type='hidden' id='ziId' value='"
																+ item.inputSeedId
																+ "' />"
																+ "<input type='hidden' id='caozuoId'  value='"
																+ item.inputOperateId
																+ "' />"
																+ "</td>"
																+ "<td onclick='tiaozhuanruku(this)' style='width:8%' align='right'><strong>货主：</strong></td>"
																+ "<td onclick='tiaozhuanruku(this)' style='width:14%'><span>"
																+ item.clientLoginName
																+ "</span></td>"
																+ "<td onclick='tiaozhuanruku(this)' style='width:8%' align='right'><strong>入库单号：</strong></td>"
																+ "<td onclick='tiaozhuanruku(this)' style='width:18%'><span>"
																+ item.inputClientNumber
																+ "</span></td>"
																+ "<td onclick='tiaozhuanruku(this)' align='right'><strong>有效期：</strong></td>"
																+ "<td onclick='tiaozhuanruku(this)'><span>"
																+ item.inputDefinedOne
																+ "</span></td>"
																+ "<td rowspan='4' valign='top'><img src='cangchu/img/xiangxia.png' width='20' class='caozuo_zhankai' onclick='showHidden(this)' style='display:block;margin-top:10px;' /></td>"
																+ "</tr>"
																+ "<tr>"
																+ "<td onclick='tiaozhuanruku(this)' align='right'><strong>运输方式：</strong></td>"
																+ "<td onclick='tiaozhuanruku(this)'><span>"
																+ item.inputCarryType
																+ "</span></td>"
																+ "<td onclick='tiaozhuanruku(this)' align='right'><strong>车号：</strong></td>"
																+ "<td onclick='tiaozhuanruku(this)'><span>"
																+ item.inputPlateNumber
																+ "</span></td>"
																+ "<td onclick='tiaozhuanruku(this)' align='right'><strong>司机姓名：</strong></td>"
																+ "<td onclick='tiaozhuanruku(this)'><span>"
																+ item.inputDriverName
																+ "</span></td>"
																+ "<td onclick='tiaozhuanruku(this)' align='right'><strong>司机电话：</strong></td>"
																+ "<td onclick='tiaozhuanruku(this)'><span>"
																+ item.inputDriverTel
																+ "</span></td>"
																+ "</tr>"
																+ "<tr class='tr_bg'>"
																+ "<td onclick='tiaozhuanruku(this)' align='right' class='tr_border_left'><strong>货物品类：</strong></td>"
																+ "<td onclick='tiaozhuanruku(this)'><span>"
																+ item.goodsCategoryName
																+ "</span></td>"
																+ "<td onclick='tiaozhuanruku(this)' align='right'><strong>名称：</strong></td>"
																+ "<td onclick='tiaozhuanruku(this)'><span>"
																+ item.goodsName
																+ "</span></td>"
																+ "<td onclick='tiaozhuanruku(this)' align='right'><strong>规格：</strong></td>"
																+ "<td onclick='tiaozhuanruku(this)'><span>"
																+ item.goodsStandardName
																+ "</span></td>"
																+ "<td onclick='tiaozhuanruku(this)' align='right'><strong>材质：</strong></td>"
																+ "<td onclick='tiaozhuanruku(this)' class='tr_border_right'><span>"
																+ item.goodsQualityName
																+ "</span></td>"
																+ "</tr>"
																+ "<tr class='tr_bg'>"
																+ "<td onclick='tiaozhuanruku(this)' align='right' class='tr_border_left_xia'><strong>属性：</strong></td>"
																+ "<td onclick='tiaozhuanruku(this)'><span>"
																+ item.goodsPropertyName
																+ "</span></td>"
																+ "<td onclick='tiaozhuanruku(this)' align='right'><strong>产地：</strong></td>"
																+ "<td onclick='tiaozhuanruku(this)'><span>"
																+ item.goodsYieldlyName
																+ "</span></td>"
																+ "<td onclick='tiaozhuanruku(this)' align='right'><strong>重量：</strong></td>"
																+ "<td onclick='tiaozhuanruku(this)'><span>"
																+ item.iseedShouldWeight
																+ "</span></td>"
																+ "<td onclick='tiaozhuanruku(this)' align='right'><strong>发起时间：</strong></td>"
																+ "<td onclick='tiaozhuanruku(this)' class='tr_border_right_xia'><span>"
																+ item.inputCreateTime
																+ "</span></td>"
																+ "</tr>"
																+ "<tr class='tr_diaodu_bg'>"
																+ "<td onclick='tiaozhuanruku(this)' align='right' class='tr_border_left'><strong>调度员：</strong></td>"
																+ "<td onclick='tiaozhuanruku(this)'><span>"
																+ item.diaodu
																+ "</span></td>"
																+ "<td onclick='tiaozhuanruku(this)' align='right'><strong>分配时间：</strong></td>"
																+ "<td onclick='tiaozhuanruku(this)'><span>"
																+ item.fenpeiTime
																+ "</span></td>"
																+ "<td onclick='tiaozhuanruku(this)' align='right'><strong>过磅/理算：</strong></td>"
																+ "<td onclick='tiaozhuanruku(this)'><span>"
																+ item.guoLi
																+ "</span></td>"
																+ "<td onclick='tiaozhuanruku(this)' align='right'><strong>分配重量：</strong></td>"
																+ "<td onclick='tiaozhuanruku(this)' class='tr_border_right'><span>"
																+ item.feipeiWeight
																+ "</span></td>"
																+ "</tr>"
																+ "<tr class='tr_diaodu_bg'>"
																+ "<td onclick='tiaozhuanruku(this)' align='right' class='tr_border_left_xia'><strong>库位：</strong></td>"
																+ "<td onclick='tiaozhuanruku(this)'><span>"
																+ item.kuwei
																+ "</span></td>"
																+ "<td onclick='tiaozhuanruku(this)' align='right'><strong>保管员：</strong></td>"
																+ "<td onclick='tiaozhuanruku(this)'><span>"
																+ item.baoguan
																+ "</span></td>"
																+ "<td onclick='tiaozhuanruku(this)' align='right' ><strong>备注：</strong></td>"
																+ "<td onclick='tiaozhuanruku(this)' title="+ item.beizhu+ "><span class='span' style='display:block;width: 200px;'>"
																+ item.beizhu
																+ "</span></td>"
																+ "<td onclick='tiaozhuanruku(this)' align='right'><strong>件数：</strong></td>"
																+ "<td onclick='tiaozhuanruku(this)' class='tr_border_right_xia'><span>"
																+ item.shijiJianshu
																+ "</span></td>"
																+
																// "<td
																// colspan='2'
																// class='tr_border_right_xia'></td>"+
																"</tr>"
																+

																"</tbody></table></div>");
											});
						}
					},
					error : function() {
						document.location.href = "/XGProject/cangchu/page/login.jsp";
					}

				});

		// ajax------結束
	}
	showHidden(str);

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
		// 如果选择的是出库订单
		if ($("#type_xuanze").val() == "出库订单") {
			sousu_but();

		} else {

			// 每当点击查询时，清空里面内容
			$(".content_center").html("");// 先清空，再添加;
			// 当前页赋值为0
			pageNowTow = 0;
			// 调用待条件查询方法
			sousu_but();
		}

	});

	$("#.daichuli_content .content_top img").hover(function() {
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

	/* 当单机选中库位的时候 */
	$(".content_content_zoudong table tr td input[type=checkbox]").click(
			function() {
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
	okSubmit();

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
						if (caozuoyuan == "") {
							alert("请选择操作员！");
							return;
						}

						var checks = $("#chuku_modeal .content_content_zoudong table tr td input[type=checkbox]:checked").length;
						if (checks <= 0) {
							alert("请选择库位！");
							return;
						}

						var checks = $("#chuku_modeal .pici_xuanze ul li input[type=checkbox]:checked").length;
						if (checks <= 0) {
							alert("请选择批次！");
							return;
						}

						alert("数据填入成功，可以提交服务器");
					});
	diaoyong();

});

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
				"height" : "250px"
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
	// 点击后获取当前元素中tr下有id为td的文本值
	var $inputSeedId = $(This).parents("tr").find("#td").text();
	// 把获取的子订单id付给类型为隐藏的input，提交到form中
	$("#ziId").val($inputSeedId);
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
		selectKuwei();
	} else if (type == "出库订单") {
		var id = $(This).parents(".content_tab").find(".ckcid").val();
		if (confirm("确定开始作业吗？")) {
			document.location.href = "exportOperate.do?flag=getGuoBangCaoZuo&eoperateId="
					+ encodeURI(encodeURI(id));
		}
	}

}

// 查询保管
function selectBaoGuan() {
	$.ajax({// ajax提交方式
		type : "post",
		url : "input.do?flag=selectBaoguanAjax",
		dataType : "json",
		success : function(baoguan) {
			// if(baoguan.length>0){
			$(".baoguan option").remove();// 先清空，再添加;
			var $select = $(".baoguan");
			$.each(baoguan, function(i, item) {
				$select.append("<option value=" + item.id + ">" + item.name
						+ "</option> ");
			});

			// }
		},
		error : function() {
			document.location.href = "/XGProject/cangchu/page/login.jsp";
		}
	});

}

function selectKuwei() {
	$
			.ajax({// ajax提交方式
				type : "post",
				url : "input.do?flag=selectKuweiAjax",
				dataType : "json",
				success : function(kuwei) {
					// if(baoguan.length>0){
					$("#tdKuwei").remove();// 先清空，再添加;
					$
							.each(
									kuwei,
									function(i, item) {
										var $select = $("<td id='tdKuwei'></td>");
										$select
												.append("<input type='checkbox' id='kuwei1' name='kuwei' value='"
														+ item.id + "'/>");
										$select.append("<label for='kuwei1'>"
												+ item.name + "</label>");
										$select.appendTo("#trKuwei");
									});
					okSubmit();
					// }
				},
				error : function() {
					document.location.href = "/XGProject/cangchu/page/login.jsp";
				}
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
						if (caozuoyuan == "") {
							alert("请选择操作员！");
							return;
						}
						var checks = $(".content_content_zoudong table tr td input[type=checkbox]:checked").length;
						if (checks <= 0) {
							alert("请选择库位！");
							return;
						}
						// alert("数据填入成功，可以提交服务器");
						$("#okForm").submit();
					});

}

function showHidden(str) {
	$(function() {
		var h = $(str).parents(".content_tab").height();
		if (h == 50) {
			$(str).parents(".content_tab").animate({
				"height" : "250px"
			}, 200);
		} else {
			$(str).parents(".content_tab").animate({
				"height" : "50px"
			}, 200);
		}
	});
}
