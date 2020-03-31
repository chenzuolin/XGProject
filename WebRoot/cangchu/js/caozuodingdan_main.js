$(function() {
	$("#.daichuli_content .content_top img").hover(function() {
		$(this).attr("src", "cangchu/img/shuaxin2.png");
	}, function() {
		$(this).attr("src", "cangchu/img/shuaxin.png");
	});

	/* 当点击刷新的时候触发 */
	$("#shuxin").click(function() {
		document.location.reload(); // 刷新
	});

});
/* 通过上边的类型选择不同的订单，通过选择类型的名称进行查询 */

function selectDingDan() {
	$(function() {
		var type = $("#type_xuanze").val();
		switch (type) {
		case "入库订单":
			$
					.ajax({
						type : "post",
						url : "inputOperate.do?flag=selectBaoGuanInputOperatAjax",
						data : {
							"dengluName" : $("#dengluren").val()
						},
						dataType : "json",
						success : function(obj) {
							$(".content_center").html("");
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
																			+ "<td onclick='tiaozhuanruku(this)' align='right'><strong>订单编号：</strong></td>"
																			+ "<td onclick='tiaozhuanruku(this)'>"
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
																			+ "<td onclick='tiaozhuanruku(this)' align='right'><strong>货主：</strong></td>"
																			+ "<td onclick='tiaozhuanruku(this)'><span>"
																			+ item.clientLoginName
																			+ "</span></td>"
																			+ "<td onclick='tiaozhuanruku(this)' align='right'><strong>入库单号：</strong></td>"
																			+ "<td onclick='tiaozhuanruku(this)'><span>"
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
																			+ "<td onclick='tiaozhuanruku(this)' align='right'><strong>备注：</strong></td>"
																			+ "<td onclick='tiaozhuanruku(this)'><span>"
																			+ item.beizhu
																			+ "</span></td>"
																			+ "<td colspan='2' class='tr_border_right_xia'></td>"
																			+ "</tr>"
																			+

																			"</tbody></table></div>");
												});

							} else {
								$(".content_center")
										.append(
												"<div class='content_tab'><table  cellpadding='0' cellspacing='0' style='margin-top: 0px;'><tbody><tr>"
														+ "<td colspan='9' align='center'><strong>无订单</strong></td></tr></tbody></table></div>");
							}
						},
						error : function() {
							document.location.href = "/XGProject/cangchu/page/login.jsp";
						}
					});

			break;
		case "出库订单":
			$
					.ajax({
						type : "post",
						url : "exportOperate.do?flag=ZhunBiChuKu",
						data : {
							"interiorUserByEoperateStoreman" : $("#dengluren")
									.val()
						},
						dataType : "json",
						success : function(obj) {
							$(".content_center").html("");
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
																			+ "<td onclick='tiaozhuanchuku(this)' align='right'><strong>订单编号：</strong></td>"
																			+ "<td onclick='tiaozhuanchuku(this)'>"
																			+ "<span>"
																			+ item.id
																			+ "</span>"
																			+ "<input type='hidden' id='ziId' value='"
																			+ item.zid
																			+ "' />"
																			+ "<input type='hidden' id='caozuoId'  value='"
																			+ item.cid
																			+ "' />"
																			+ "</td>"
																			+ "<td onclick='tiaozhuanchuku(this)' align='right'><strong>货主：</strong></td>"
																			+ "<td onclick='tiaozhuanchuku(this)'><span>"
																			+ item.huozhu
																			+ "</span></td>"
																			+ "<td onclick='tiaozhuanchuku(this)' align='right'><strong>客户单号：</strong></td>"
																			+ "<td onclick='tiaozhuanchuku(this)'><span>"
																			+ item.kehuhao
																			+ "</span></td>"
																			+ "<td onclick='tiaozhuanchuku(this)' align='right'><strong>有效期：</strong></td>"
																			+ "<td onclick='tiaozhuanchuku(this)'><span>"
																			+ item.youxiao
																			+ "</span></td>"
																			+ "<td rowspan='4' valign='top'><img src='cangchu/img/xiangxia.png' width='20' class='caozuo_zhankai' onclick='showHidden(this)' style='display:block;margin-top:10px;' /></td>"
																			+ "</tr>"
																			+ "<tr>"
																			+ "<td onclick='tiaozhuanchuku(this)' align='right'><strong>运输方式：</strong></td>"
																			+ "<td onclick='tiaozhuanchuku(this)'><span>"
																			+ item.yunshu
																			+ "</span></td>"
																			+ "<td onclick='tiaozhuanchuku(this)' align='right'><strong>车号：</strong></td>"
																			+ "<td onclick='tiaozhuanchuku(this)'><span>"
																			+ item.chehao
																			+ "</span></td>"
																			+ "<td onclick='tiaozhuanchuku(this)' align='right'><strong>司机姓名：</strong></td>"
																			+ "<td onclick='tiaozhuanchuku(this)'><span>"
																			+ item.siji
																			+ "</span></td>"
																			+ "<td onclick='tiaozhuanchuku(this)' align='right'><strong>司机电话：</strong></td>"
																			+ "<td onclick='tiaozhuanchuku(this)'><span>"
																			+ item.sijitel
																			+ "</span></td>"
																			+ "</tr>"
																			+ "<tr class='tr_bg'>"
																			+ "<td onclick='tiaozhuanchuku(this)' align='right' class='tr_border_left'><strong>货物品类：</strong></td>"
																			+ "<td onclick='tiaozhuanchuku(this)'><span>"
																			+ item.pinlei
																			+ "</span></td>"
																			+ "<td onclick='tiaozhuanchuku(this)' align='right'><strong>名称：</strong></td>"
																			+ "<td onclick='tiaozhuanchuku(this)'><span>"
																			+ item.mingcheng
																			+ "</span></td>"
																			+ "<td onclick='tiaozhuanchuku(this)' align='right'><strong>规格：</strong></td>"
																			+ "<td onclick='tiaozhuanchuku(this)'><span>"
																			+ item.guige
																			+ "</span></td>"
																			+ "<td onclick='tiaozhuanchuku(this)' align='right'><strong>材质：</strong></td>"
																			+ "<td onclick='tiaozhuanchuku(this)' class='tr_border_right'><span>"
																			+ item.caizhi
																			+ "</span></td>"
																			+ "</tr>"
																			+ "<tr class='tr_bg'>"
																			+ "<td onclick='tiaozhuanchuku(this)' align='right' class='tr_border_left_xia'><strong>属性：</strong></td>"
																			+ "<td onclick='tiaozhuanchuku(this)'><span>"
																			+ item.shuxing
																			+ "</span></td>"
																			+ "<td onclick='tiaozhuanchuku(this)' align='right'><strong>产地：</strong></td>"
																			+ "<td onclick='tiaozhuanchuku(this)'><span>"
																			+ item.chandi
																			+ "</span></td>"
																			+ "<td onclick='tiaozhuanchuku(this)' align='right'><strong>重量：</strong></td>"
																			+ "<td onclick='tiaozhuanchuku(this)'><span>"
																			+ item.yingchuweight
																			+ "吨</span></td>"
																			+ "<td onclick='tiaozhuanchuku(this)' align='right'><strong>发起时间：</strong></td>"
																			+ "<td onclick='tiaozhuanchuku(this)' class='tr_border_right_xia'><span>"
																			+ item.faqitime
																			+ "</span></td>"
																			+ "</tr>"
																			+ "<tr class='tr_diaodu_bg'>"
																			+ "<td onclick='tiaozhuanchuku(this)' align='right' class='tr_border_left'><strong>调度员：</strong></td>"
																			+ "<td onclick='tiaozhuanchuku(this)'><span>"
																			+ item.diaoduyuan
																			+ "</span></td>"
																			+ "<td onclick='tiaozhuanchuku(this)' align='right'><strong>分配时间：</strong></td>"
																			+ "<td onclick='tiaozhuanchuku(this)'><span>"
																			+ item.fenpeitime
																			+ "</span></td>"
																			+ "<td onclick='tiaozhuanchuku(this)' align='right'><strong>过磅/理算：</strong></td>"
																			+ "<td onclick='tiaozhuanchuku(this)'><span>"
																			+ item.lisuan
																			+ "</span></td>"
																			+ "<td onclick='tiaozhuanchuku(this)' align='right'><strong>库位：</strong></td>"
																			+ "<td onclick='tiaozhuanchuku(this)' class='tr_border_right'><span>"
																			+ item.kuwei
																			+ "</span></td>"
																			+ "</tr>"
																			+ "<tr class='tr_diaodu_bg'>"
																			+ "<td onclick='tiaozhuanchuku(this)' align='right' class='tr_border_left_xia'><strong>备注：</strong></td>"
																			+ "<td onclick='tiaozhuanchuku(this)'  colspan='7' class='tr_border_right_xia'><span>"
																			+ item.remark
																			+ "</span></td>"
																			+ "</tr>"
																			+

																			"</tbody></table></div>");
												});

							} else {
								alert("eles");
								$(".content_center")
										.append(
												"<div class='content_tab'><table  cellpadding='0' cellspacing='0' style='margin-top: 0px;'><tbody><tr>"
														+ "<td colspan='9' align='center'><strong>无订单</strong></td></tr></tbody></table></div>");
							}
						},
						error : function() {
							document.location.href = "/XGProject/cangchu/page/login.jsp";
						}
					});
			break;
		case "挪库订单":
			$
					.ajax({
						type : "post",
						url : "shift.do?flag=getZhengZaiFirst&dates="
								+ new Date().getTime(),
						async : true,
						data : {
							"interiorUserByShiftExecutor" : $("#dengluren")
									.val()
						},
						dataType : "json",
						success : function(obj) {
							$(".content_center").html("");
							if (obj == null) {
								$(".content_center")
										.append(
												"<div class='content_tab'><table  cellpadding='0' cellspacing='0' style='margin-top: 0px;'><tbody><tr>"
														+ "<td colspan='9' align='center'><strong>无订单</strong></td></tr></tbody></table></div>");
							}
							if (obj[0].result != null) {
								for ( var i = 0; i < obj.length; i++) {
									$(".content_center")
											.append(
													"<div class='content_tab' title='开始作业'><table cellpadding='0' cellspacing='0' style='margin-top: 0px;'><tbody><tr>"
															+ "<td align='right' onclick='tiaozhuannuoku(this)'><strong>订单编号：</strong></td>"
															+ "<td onclick='tiaozhuannuoku(this)'><span>"
															+ obj[i].id
															+ "</span></td>"
															+ "<td align='right' onclick='tiaozhuannuoku(this)'><strong>发起人：</strong></td>"
															+ "<td onclick='tiaozhuannuoku(this)'><span>"
															+ obj[i].faqiren
															+ "</span></td>"
															+ "<td align='right' onclick='tiaozhuannuoku(this)'><strong>发起时间：</strong></td>"
															+ "<td onclick='tiaozhuannuoku(this)'><span>"
															+ obj[i].time
															+ "</span></td>"
															+ "<td align='right' onclick='tiaozhuannuoku(this)'><strong>原库位：</strong></td>"
															+ "<td onclick='tiaozhuannuoku(this)'><span>"
															+ obj[i].yuankuwei
															+ "</span></td>"
															+ "<td rowspan='4' valign='top'><img"
															+ " src='cangchu/img/xiangxia.png' width='20'"
															+ " class='caozuo_zhankai' style='margin-top:10px' onclick='showHiddens(this)' /></td>"
															+ "</tr>"
															+ "<tr class='tr_bg'>"
															+ "<td align='right' class='tr_border_left' onclick='tiaozhuannuoku(this)'><strong>货物品类：</strong>"
															+ "</td>"
															+ "<td onclick='tiaozhuannuoku(this)'><span>"
															+ obj[i].pinlei
															+ "</span></td>"
															+ "<td align='right' onclick='tiaozhuannuoku(this)'><strong>名称：</strong></td>"
															+ "<td onclick='tiaozhuannuoku()'><span>"
															+ obj[i].mingcheng
															+ "</span></td>"
															+ "<td align='right' onclick='tiaozhuannuoku(this)'><strong>规格：</strong></td>"
															+ "<td onclick='tiaozhuannuoku(this)'><span>"
															+ obj[i].guige
															+ "</span></td>"
															+ "<td align='right' onclick='tiaozhuannuoku(this)'><strong>材质：</strong></td>"
															+ "<td class='tr_border_right' onclick='tiaozhuannuoku(this)'><span>"
															+ obj[i].caizhi
															+ "</span></td>"
															+ "</tr>"
															+ "<tr class='tr_bg'>"
															+ "<td align='right' class='tr_border_left_xia' onclick='tiaozhuannuoku(this)'><strong>属性：</strong>"
															+ "</td>"
															+ "<td onclick='tiaozhuannuoku(this)'><span>"
															+ obj[i].shuxing
															+ "</span></td>"
															+ "<td align='right' onclick='tiaozhuannuoku(this)'><strong>产地：</strong></td>"
															+ "<td onclick='tiaozhuannuoku(this)'><span>"
															+ obj[i].chandi
															+ "</span></td><td align='right' onclick='tiaozhuannuoku(this)'><strong>重量：</strong></td>"
															+ "<td onclick='tiaozhuannuoku(this)'><span>"
															+ obj[i].zhongliang
															+ "</span></td>"
															+ "<td align='right' onclick='tiaozhuannuoku(this)'><strong>状态：</strong></td>"
															+ "<td onclick='tiaozhuannuoku(this)' class='tr_border_right_xia'><span>"
															+ obj[i].zhuangtai
															+ "</span></td></tr></tbody></table></div>");
								}
							} else {
								$(".content_center")
										.append(
												"<div class='content_tab'><table  cellpadding='0' cellspacing='0' style='margin-top: 0px;'><tbody><tr>"
														+ "<td colspan='9' align='center'><strong>无订单</strong></td></tr></tbody></table></div>");
							}
						},
						error : function() {
							document.location.href = "/XGProject/cangchu/page/login.jsp";
						}
					});
			break;
		case "短倒订单":
			$
					.ajax({
						type : "post",
						url : "duanDaoAction.do?flag=getZhengZaiFirst&dates="
								+ new Date().getTime(),
						async : true,
						data : {
							"interiorUserByShiftExecutor" : $("#dengluren")
									.val()
						},
						dataType : "json",
						success : function(obj) {
							$(".content_center").html("");
							if (obj == null) {
								$(".content_center")
										.append(
												"<div class='content_tab'><table  cellpadding='0' cellspacing='0' style='margin-top: 0px;'><tbody><tr>"
														+ "<td colspan='9' align='center'><strong>无订单</strong></td></tr></tbody></table></div>");
							}
							if (obj[0].result != null) {
								for ( var i = 0; i < obj.length; i++) {
									$(".content_center")
											.append(
													"<div class='content_tab' title='开始作业'><table cellpadding='0' cellspacing='0' style='margin-top: 0px;'><tbody><tr>"
															+ "<td align='right' onclick='tiaozhuanduandao(this)'><strong>订单编号：</strong></td>"
															+ "<td onclick='tiaozhuanduandao(this)'><span>"
															+ obj[i].id
															+ "</span></td>"
															+ "<td align='right' onclick='tiaozhuanduandao(this)'><strong>发起人：</strong></td>"
															+ "<td onclick='tiaozhuanduandao(this)'><span>"
															+ obj[i].faqiren
															+ "</span></td>"
															+ "<td align='right' onclick='tiaozhuanduandao(this)'><strong>发起时间：</strong></td>"
															+ "<td onclick='tiaozhuanduandao(this)'><span>"
															+ obj[i].time
															+ "</span></td>"
															+ "<td align='right' onclick='tiaozhuanduandao(this)'><strong>原库位：</strong></td>"
															+ "<td onclick='tiaozhuanduandao(this)'><span>"
															+ obj[i].yuankuwei
															+ "</span></td>"
															+ "<td rowspan='4' valign='top'><img"
															+ " src='cangchu/img/xiangxia.png' width='20'"
															+ " class='caozuo_zhankai' style='margin-top:10px' onclick='showHiddens(this)' /></td>"
															+ "</tr>"
															+ "<tr class='tr_bg'>"
															+ "<td align='right' class='tr_border_left' onclick='tiaozhuanduandao(this)'><strong>货物品类：</strong>"
															+ "</td>"
															+ "<td onclick='tiaozhuanduandao(this)'><span>"
															+ obj[i].pinlei
															+ "</span></td>"
															+ "<td align='right' onclick='tiaozhuanduandao(this)'><strong>名称：</strong></td>"
															+ "<td onclick='tiaozhuanduandao()'><span>"
															+ obj[i].mingcheng
															+ "</span></td>"
															+ "<td align='right' onclick='tiaozhuanduandao(this)'><strong>规格：</strong></td>"
															+ "<td onclick='tiaozhuanduandao(this)'><span>"
															+ obj[i].guige
															+ "</span></td>"
															+ "<td align='right' onclick='tiaozhuanduandao(this)'><strong>材质：</strong></td>"
															+ "<td class='tr_border_right' onclick='tiaozhuanduandao(this)'><span>"
															+ obj[i].caizhi
															+ "</span></td>"
															+ "</tr>"
															+ "<tr class='tr_bg'>"
															+ "<td align='right' class='tr_border_left_xia' onclick='tiaozhuanduandao(this)'><strong>属性：</strong>"
															+ "</td>"
															+ "<td onclick='tiaozhuanduandao(this)'><span>"
															+ obj[i].shuxing
															+ "</span></td>"
															+ "<td align='right' onclick='tiaozhuanduandao(this)'><strong>产地：</strong></td>"
															+ "<td onclick='tiaozhuanduandao(this)'><span>"
															+ obj[i].chandi
															+ "</span></td><td align='right' onclick='tiaozhuanduandao(this)'><strong>重量：</strong></td>"
															+ "<td onclick='tiaozhuanduandao(this)'><span>"
															+ obj[i].zhongliang
															+ "</span></td>"
															+ "<td align='right' onclick='tiaozhuanduandao(this)'><strong>状态：</strong></td>"
															+ "<td onclick='tiaozhuanduandao(this)' class='tr_border_right_xia'><span>"
															+ obj[i].zhuangtai
															+ "</span></td></tr></tbody></table></div>");
								}
							} else {
								$(".content_center")
										.append(
												"<div class='content_tab'><table  cellpadding='0' cellspacing='0' style='margin-top: 0px;'><tbody><tr>"
														+ "<td colspan='9' align='center'><strong>无订单</strong></td></tr></tbody></table></div>");
							}
						},
						error : function() {
							document.location.href = "/XGProject/cangchu/page/login.jsp";
						}
					});
			break;
		case "盘库订单":
			$
					.ajax({
						type : "post",
						url : "checks.do?flag=getChecksJiHua&dates="
								+ new Date().getTime(),
						async : true,
						data : {
							"interiorUserByCheckHuman" : $("#dengluren").val()
						},
						dataType : "json",
						success : function(obj) {
							$(".content_center").html("");
							if (obj == null) {
								$(".content_center")
										.append(
												"<div class='content_tab'><table  cellpadding='0' cellspacing='0' style='margin-top: 0px;'><tbody><tr>"
														+ "<td colspan='9' align='center'><strong>无订单</strong></td></tr></tbody></table></div>");
							}
							if (obj[0].result != null) {
								for ( var i = 0; i < obj.length; i++) {
									$(".content_center")
											.append(
													"<div class='content_tab' title='开始作业'><table cellpadding='0' cellspacing='0' style='margin-top: 0px;'><tbody><tr>"
															+ "<td align='right' onclick='tiaozhuanpanku(this)'><strong>订单编号：</strong></td>"
															+ "<td onclick='tiaozhuanpanku(this)'><span>"
															+ obj[i].id
															+ "</span></td>"
															+ "<td align='right' onclick='tiaozhuanpanku(this)'><strong>发起人：</strong></td>"
															+ "<td onclick='tiaozhuanpanku(this)'><span>"
															+ obj[i].faqiren
															+ "</span></td>"
															+ "<td align='right' onclick='tiaozhuanpanku(this)'><strong>发起时间：</strong></td>"
															+ "<td onclick='tiaozhuanpanku(this)'><span>"
															+ obj[i].time
															+ "</span></td>"
															+ "<td align='right' onclick='tiaozhuanpanku(this)'><strong>库位：</strong></td>"
															+ "<td onclick='tiaozhuanpanku(this)'><span>"
															+ obj[i].kuwei
															+ "</span></td>"
															+ "<td rowspan='4' valign='top'><img"
															+ " src='cangchu/img/xiangxia.png' width='20'"
															+ " class='caozuo_zhankai' style='margin-top:10px' onclick='showHiddens(this)' /></td>"
															+ "</tr>"
															+ "<tr class='tr_bg'>"
															+ "<td align='right' class='tr_border_left' onclick='tiaozhuanpanku(this)'><strong>货物品类：</strong>"
															+ "</td>"
															+ "<td onclick='tiaozhuanpanku(this)'><span>"
															+ obj[i].pinlei
															+ "</span></td>"
															+ "<td align='right' onclick='tiaozhuanpanku(this)'><strong>名称：</strong></td>"
															+ "<td onclick='tiaozhuanpanku()'><span>"
															+ obj[i].mingcheng
															+ "</span></td>"
															+ "<td align='right' onclick='tiaozhuanpanku(this)'><strong>规格：</strong></td>"
															+ "<td onclick='tiaozhuanpanku(this)'><span>"
															+ obj[i].guige
															+ "</span></td>"
															+ "<td align='right' onclick='tiaozhuanpanku(this)'><strong>材质：</strong></td>"
															+ "<td class='tr_border_right' onclick='tiaozhuanpanku(this)'><span>"
															+ obj[i].caizhi
															+ "</span></td>"
															+ "</tr>"
															+ "<tr class='tr_bg'>"
															+ "<td align='right' class='tr_border_left_xia' onclick='tiaozhuanpanku(this)'><strong>属性：</strong>"
															+ "</td>"
															+ "<td onclick='tiaozhuanpanku(this)'><span>"
															+ obj[i].shuxing
															+ "</span></td>"
															+ "<td align='right' onclick='tiaozhuanpanku(this)'><strong>产地：</strong></td>"
															+ "<td onclick='tiaozhuanpanku(this)'><span>"
															+ obj[i].chandi
															+ "</span></td>"
															+ "<td align='right' onclick='tiaozhuanpanku(this)'><strong>状态：</strong></td>"
															+ "<td onclick='tiaozhuanpanku(this)'><span>"
															+ obj[i].zhuangtai
															+ "</span></td><td align='right' onclick='tiaozhuanpanku(this)'><strong></strong></td>"
															+ "<td onclick='tiaozhuanpanku(this)' class='tr_border_right_xia'><span>"
															+ "</span></td></tr></tbody></table></div>");
								}
							} else {
								$(".content_center")
										.append(
												"<div class='content_tab'><table  cellpadding='0' cellspacing='0' style='margin-top: 0px;'><tbody><tr>"
														+ "<td colspan='9' align='center'><strong>无订单</strong></td></tr></tbody></table></div>");
							}
						},
						error : function() {
							document.location.href = "/XGProject/cangchu/page/login.jsp";
						}
					});
			break;
		}
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

function showHiddens(str) {
	$(function() {
		var h = $(str).parents(".content_tab").height();
		if (h == 50) {
			$(str).parents(".content_tab").animate({
				"height" : "150px"
			}, 200);
		} else {
			$(str).parents(".content_tab").animate({
				"height" : "50px"
			}, 200);
		}
	});
}