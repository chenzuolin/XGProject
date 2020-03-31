
//定义两个当前页数
var pageNow = 0;
// 有条件的查询当前页数
var pageNowTow = 0;


// 第一次加载
function jiazaiLoad() {
	// 第一次加载，或点击该页面时清空
	//----别忘了，获取session中的值
	
	$(".content_center").html("");	
	$("#guohupage").val("1");
	pageNow=0;//当前页赋值为0	
	//如果选择的是出库订单，调用出库方法
	if($("#type_xuanze").val()=="出库订单"){
		
		$("#guohupage").val("1");// 当点击其他的时候将过户的当前页设为1
		//调用出库方法
		selectShenHePlanExport();
		
	}else if ($("#type_xuanze").val() == "过户订单") {
		// 查询正在审核的过户订单
		guohushenhe();
	}else{//进入库方法
		selectShenHePlanInput();
	}

}

// 当选择入库和出库操作时调用此方法

function selectShenHePlanInput() {

	 // 否则到入库订单进去
		// 当前页自加一
		pageNow++;

		$
		.ajax({
			type : "post",
			url : "inputOperate.do?flag=selectShenheInputAjax",
			data : {
				"pageNow" : pageNow// 传入当前页
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
																+ "<td onclick='tiaozhuanruku(this)' align='right'><strong>客户单号：</strong></td>"
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
																+ "<td onclick='tiaozhuanruku(this)' colspan='2' class='tr_border_right_xia'></td>"
																+ "</tr>"
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
	

};



//当选择出库审核时调用此方法

function selectShenHePlanExport(){		
	var $kehudanhao=$(".kehudanhao").val();
	var $huozhu=$(".huozhu").val();	
	//当前页自加一
	pageNow++;	
	$.ajax({
		type:"post",
		url:"exportOperate.do?flag=getShenheChaxun",
		data:{"danhao":$kehudanhao,"huozhu":$huozhu,"pageNow":pageNow},//传入当前页
		dataType : "json",
		success : function(obj) {					
			//$(".content_center").html("");				
			if(obj.qingkong=="clean"){
				 alert("请求数据已完！");
			 }
			if (obj == null) {
				$(".content_center")
						.append(
								"<div class='content_tab'><table  cellpadding='0' cellspacing='0' style='margin-top: 0px;'><tbody><tr>"
										+ "<td colspan='9' align='center'><strong>无订单</strong></td></tr></tbody></table></div>");
			}
			if (obj.length>0) {
				 $.each(obj, function (i,item) {
					$(".content_center")
							.append(
									"<div class='content_tab' title='开始作业'>" +																						
									"<table cellpadding='0' cellspacing='0' style='margin-top: 0px;'><tbody>" +
									 "<tr>"+
									 	"<td onclick='tiaozhuanchuku(this)' align='right'><strong>订单编号：</strong></td>"+
									 	"<td onclick='tiaozhuanchuku(this)'>" +
									 		"<span>"+item.zongId+"</span>" +
									 		"<input type='hidden' id='ziId' value='"+item.exportSeedId+"' />"+
									 		"<input type='hidden' id='caozuoId'  value='"+item.exportOperateId+"' />"+
									 	"</td>"+
									 	"<td onclick='tiaozhuanchuku(this)' align='right'><strong>货主：</strong></td>"+
									 	"<td onclick='tiaozhuanchuku(this)'><span>"+item.clientFirmName+"</span></td>"+
									 	"<td onclick='tiaozhuanchuku(this)' align='right'><strong>客户单号：</strong></td>"+
									 	"<td onclick='tiaozhuanchuku(this)'><span>"+item.exportClientNumber+"</span></td>"+
									 	"<td onclick='tiaozhuanchuku(this)' align='right'><strong>是否接受超发：</strong></td>"+
									 	"<td onclick='tiaozhuanchuku(this)'><span>"+item.exportDefinedOne+"</span></td>"+
									 	"<td rowspan='4' valign='top'><img src='cangchu/img/xiangxia.png' width='20' class='caozuo_zhankai' onclick='showHidden(this)' style='display:block;margin-top:10px;' /></td>"+								 	
									 "</tr>"+
									 "<tr>"+
									 	"<td onclick='tiaozhuanchuku(this)' align='right'><strong>运输方式：</strong></td>"+
									 	"<td onclick='tiaozhuanchuku(this)'><span>"+item.exportCarryType+"</span></td>"+
									 	"<td onclick='tiaozhuanchuku(this)' align='right'><strong>车号：</strong></td>"+
									 	"<td onclick='tiaozhuanchuku(this)'><span>"+item.exportPlateNumber+"</span></td>"+
									 	"<td onclick='tiaozhuanchuku(this)' align='right'><strong>司机姓名：</strong></td>"+
									 	"<td onclick='tiaozhuanchuku(this)'><span>"+item.exportDriverName+"</span></td>"+
									 	"<td onclick='tiaozhuanchuku(this)' align='right'><strong>司机电话：</strong></td>"+
									 	"<td onclick='tiaozhuanchuku(this)'><span>"+item.exportDriverTel+"</span></td>"+
									 "</tr>"+
									 "<tr class='tr_bg'>"+
									 	"<td onclick='tiaozhuanchuku(this)' align='right' class='tr_border_left'><strong>货物品类：</strong></td>"+
									 	"<td onclick='tiaozhuanchuku(this)'><span>"+item.goodsCategoryName+"</span></td>"+
									 	"<td onclick='tiaozhuanchuku(this)' align='right'><strong>名称：</strong></td>"+
									 	"<td onclick='tiaozhuanchuku(this)'><span>"+item.goodsName+"</span></td>"+
									 	"<td onclick='tiaozhuanchuku(this)' align='right'><strong>规格：</strong></td>"+
									 	"<td onclick='tiaozhuanchuku(this)'><span>"+item.goodsStandardName+"</span></td>"+
									 	"<td onclick='tiaozhuanchuku(this)' align='right'><strong>材质：</strong></td>"+
									 	"<td onclick='tiaozhuanchuku(this)' class='tr_border_right'><span>"+item.goodsQualityName+"</span></td>"+
									 "</tr>"+
									 "<tr class='tr_bg'>"+
									 	"<td onclick='tiaozhuanchuku(this)' align='right' class='tr_border_left_xia'><strong>属性：</strong></td>"+
									 	"<td onclick='tiaozhuanchuku(this)'><span>"+item.goodsPropertyName+"</span></td>"+
									 	"<td onclick='tiaozhuanchuku(this)' align='right'><strong>产地：</strong></td>"+
									 	"<td onclick='tiaozhuanchuku(this)'><span>"+item.goodsYieldlyName+"</span></td>"+
									 	"<td onclick='tiaozhuanchuku(this)' align='right'><strong>重量：</strong></td>"+
									 	"<td onclick='tiaozhuanchuku(this)'><span>"+item.iseedShouldWeight+"</span></td>"+
									 	"<td onclick='tiaozhuanchuku(this)' align='right'><strong>发起时间：</strong></td>"+
									 	"<td onclick='tiaozhuanchuku(this)' class='tr_border_right_xia'><span>"+item.exportCreateTime+"</span></td>"+
									 "</tr>"+
									 "<tr class='tr_diaodu_bg'>"+
									 	"<td onclick='tiaozhuanchuku(this)' align='right' class='tr_border_left'><strong>调度员：</strong></td>"+
									 	"<td onclick='tiaozhuanchuku(this)'><span>"+item.diaodu+"</span></td>"+
									 	"<td onclick='tiaozhuanchuku(this)' align='right'><strong>分配时间：</strong></td>"+
									 	"<td onclick='tiaozhuanchuku(this)'><span>"+item.fenpeiTime+"</span></td>"+
									 	"<td onclick='tiaozhuanchuku(this)' align='right'><strong>过磅/理算：</strong></td>"+
									 	"<td onclick='tiaozhuanchuku(this)'><span>"+item.guoLi+"</span></td>"+
									 	"<td onclick='tiaozhuanchuku(this)' align='right'><strong>分配重量：</strong></td>"+
									 	"<td onclick='tiaozhuanchuku(this)' class='tr_border_right'><span>"+item.feipeiWeight+"</span></td>"+
									 "</tr>"+
									 "<tr class='tr_diaodu_bg'>"+
									 	"<td onclick='tiaozhuanchuku(this)' align='right' class='tr_border_left_xia'><strong>库位：</strong></td>"+
									 	"<td onclick='tiaozhuanchuku(this)'><span>"+item.kuwei+"</span></td>"+
									 	"<td onclick='tiaozhuanchuku(this)' align='right'><strong>保管员：</strong></td>"+
									 	"<td onclick='tiaozhuanchuku(this)'><span>"+item.baoguan+"</span></td>"+
									 	"<td onclick='tiaozhuanchuku(this)' align='right'><strong>备注：</strong></td>"+
									 	"<td colspan='3' class='tr_border_right_xia' title="+item.beizhu+"><span class='span' style='display:block;width: 200px;'>"+item.beizhu+"</span></td>"+
									 "</tr>"+
															 
							"</tbody></table></div>");
				});
			} 
		},
		error : function() {
			document.location.href = "/XGProject/cangchu/page/login.jsp";
		}
	});
	//---ajax完毕
	

};






// 当选择查看更多时调用此方法
function selectGengduo() {

	// 如果选择的是出库订单
	if ($("#type_xuanze").val() == "出库订单") {
		//调用审核出库方法
		selectShenHePlanExport();
	
	} else if ($("#type_xuanze").val() == "过户订单") {
		// 查询正在审核的过户订单
		guohushenhe();
	} else {// 否则进去

		var $kehudanhao = $(".kehudanhao").val();
		var $huozhu = $(".huozhu").val();
		// 如果客户单号和货主都为空则调用selectPlanInputChange();
		if ($kehudanhao == "" && $huozhu == "") {
			selectShenHePlanInput();
		} else {// 否则调用sousu_but方法
			sousu_but();
		}
		// selectPlanInputChange();
		// else完
	}

};

// -----------------当点击查询按钮时被调用的方法开始

function sousu_but() {

	$("#mimi").val("chaxun");
	$("#guohupage").val("1");// 当点击其他的时候将过户的当前页设为1

	var $kehudanhao = $(".kehudanhao").val();
	var $huozhu = $(".huozhu").val();
	// 如果选择的是出库订单
	if ($("#type_xuanze").val() == "出库订单") {
		alert("还没做奥！");
	} else if ($("#type_xuanze").val() == "过户订单") {
		// 查询正在审核的过户订单
		guohushenhe();
	} else {// 否则进去

		// 有条件页数自增一
		pageNowTow++;

		$
				.ajax({
					type : "post",
					url : "inputOperate.do?flag=selectShenheInputAjaxTj",
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
																		+ "<td onclick='tiaozhuanruku(this)' align='right'><strong>客户单号：</strong></td>"
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
																		+ "<td onclick='tiaozhuanchuku(this)' align='right'><strong>运输方式：</strong></td>"
																		+ "<td onclick='tiaozhuanchuku(this)'><span>"
												 						+ item.inputCarryType
																		+ "</span></td>"
																		+ "<td onclick='tiaozhuanchuku(this)' align='right'><strong>车号：</strong></td>"
																		+ "<td onclick='tiaozhuanchuku(this)'><span>"
																		+ item.inputPlateNumber
																		+ "</span></td>"
																		+ "<td onclick='tiaozhuanchuku(this)' align='right'><strong>司机姓名：</strong></td>"
																		+ "<td onclick='tiaozhuanchuku(this)'><span>"
																		+ item.inputDriverName
																		+ "</span></td>"
																		+ "<td onclick='tiaozhuanchuku(this)' align='right'><strong>司机电话：</strong></td>"
																		+ "<td onclick='tiaozhuanchuku(this)'><span>"
																		+ item.inputDriverTel
																		+ "</span></td>"
																		+ "</tr>"
																		+ "<tr class='tr_bg'>"
																		+ "<td onclick='tiaozhuanchuku(this)' align='right' class='tr_border_left'><strong>货物品类：</strong></td>"
																		+ "<td onclick='tiaozhuanchuku(this)'><span>"
																		+ item.goodsCategoryName
																		+ "</span></td>"
																		+ "<td onclick='tiaozhuanchuku(this)' align='right'><strong>名称：</strong></td>"
																		+ "<td onclick='tiaozhuanchuku(this)'><span>"
																		+ item.goodsName
																		+ "</span></td>"
																		+ "<td onclick='tiaozhuanchuku(this)' align='right'><strong>规格：</strong></td>"
																		+ "<td onclick='tiaozhuanchuku(this)'><span>"
																		+ item.goodsStandardName
																		+ "</span></td>"
																		+ "<td onclick='tiaozhuanchuku(this)' align='right'><strong>材质：</strong></td>"
																		+ "<td onclick='tiaozhuanchuku(this)' class='tr_border_right'><span>"
																		+ item.goodsQualityName
																		+ "</span></td>"
																		+ "</tr>"
																		+ "<tr class='tr_bg'>"
																		+ "<td onclick='tiaozhuanchuku(this)' align='right' class='tr_border_left_xia'><strong>属性：</strong></td>"
																		+ "<td onclick='tiaozhuanchuku(this)'><span>"
																		+ item.goodsPropertyName
																		+ "</span></td>"
																		+ "<td onclick='tiaozhuanchuku(this)' align='right'><strong>产地：</strong></td>"
																		+ "<td onclick='tiaozhuanchuku(this)'><span>"
																		+ item.goodsYieldlyName
																		+ "</span></td>"
																		+ "<td onclick='tiaozhuanchuku(this)' align='right'><strong>重量：</strong></td>"
																		+ "<td onclick='tiaozhuanchuku(this)'><span>"
																		+ item.iseedShouldWeight
																		+ "</span></td>"
																		+ "<td onclick='tiaozhuanchuku(this)' align='right'><strong>发起时间：</strong></td>"
																		+ "<td onclick='tiaozhuanchuku(this)' class='tr_border_right_xia'><span>"
																		+ item.inputCreateTime
																		+ "</span></td>"
																		+ "</tr>"
																		+ "<tr class='tr_diaodu_bg'>"
																		+ "<td onclick='tiaozhuanchuku(this)' align='right' class='tr_border_left'><strong>调度员：</strong></td>"
																		+ "<td onclick='tiaozhuanchuku(this)'><span>"
																		+ item.diaodu
																		+ "</span></td>"
																		+ "<td onclick='tiaozhuanchuku(this)' align='right'><strong>分配时间：</strong></td>"
																		+ "<td onclick='tiaozhuanchuku(this)'><span>"
																		+ item.fenpeiTime
																		+ "</span></td>"
																		+ "<td onclick='tiaozhuanchuku(this)' align='right'><strong>过磅/理算：</strong></td>"
																		+ "<td onclick='tiaozhuanchuku(this)'><span>"
																		+ item.guoLi
																		+ "</span></td>"
																		+ "<td onclick='tiaozhuanchuku(this)' align='right'><strong>分配重量：</strong></td>"
																		+ "<td onclick='tiaozhuanchuku(this)' class='tr_border_right'><span>"
																		+ item.feipeiWeight
																		+ "</span></td>"
																		+ "</tr>"
																		+ "<tr class='tr_diaodu_bg'>"
																		+ "<td onclick='tiaozhuanchuku(this)' align='right' class='tr_border_left_xia'><strong>库位：</strong></td>"
																		+ "<td onclick='tiaozhuanchuku(this)'><span>"
																		+ item.kuwei
																		+ "</span></td>"
																		+ "<td onclick='tiaozhuanchuku(this)' align='right'><strong>保管员：</strong></td>"
																		+ "<td onclick='tiaozhuanchuku(this)'><span>"
																		+ item.baoguan
																		+ "</span></td>"
																		+ "<td onclick='tiaozhuanchuku(this)' align='right'><strong>备注：</strong></td>"
																		+ "<td onclick='tiaozhuanchuku(this)'><span>"
																		+ item.beizhu
																		+ "</span></td>"
																		+ "<td onclick='tiaozhuanchuku(this)' colspan='2' class='tr_border_right_xia'></td>"
																		+ "</tr>"
																		+

																		"</tbody></table></div>");
											});
						}

					},
					error : function() {
						document.location.href = "/XGProject/cangchu/page/login.jsp";
					}
				});
	}

};

// ---------------查询按钮方法结束



// ---------------过户审核方法开始

function guohushenhe() {
	if ($("#guohupage").val() == "1") {
		$(".content_center").html("");
	}

	var page = $("#guohupage").val();// 当前页
	var kehudanhao = $(".kehudanhao").val();
	var huozhu = $(".huozhu").val();
	$
			.ajax({
				type : "post",
				url : "shiftStockSeed.do?flag=getJiHuaShiftStock",
				data : {
					"pageNow" : page,
					"tihuohao" : kehudanhao,
					"huozhu" : huozhu
				},
				dataType : "json",
				success : function(obj) {
					if (obj == null) {
						$(".content_center")
								.append(
										"<div class='content_tab'><table  cellpadding='0' cellspacing='0' style='margin-top: 0px;'><tbody><tr>"
												+ "<td colspan='9' align='center'><strong>无订单</strong></td></tr></tbody></table></div>");
					}
					if (obj.length > 0) {
						if (obj[0].max == "maxs") {
							alert("已到底部！");
							return false;
						}
						$
								.each(
										obj,
										function(i, item) {
											$(".content_center")
													.append(
															"<div class='content_tab' title='开始作业'>"
																	+ "<table cellpadding='0' cellspacing='0' style='margin-top: 0px;'><tbody>"
																	+ "<tr>"
																	+ "<td  onclick='guohushowHidden(this)'' align='right'><strong>订单编号：</strong></td>"
																	+ "<td  onclick='guohushowHidden(this)'>"
																	+ "<span>"
																	+ item.id
																	+ "</span>"
																	+ "<input type='hidden' id='ziId' value='"
																	+ item.zid
																	+ "' />"
																	+ "</td>"
																	+ "<td  onclick='guohushowHidden(this)' align='right'><strong>转出单位：</strong></td>"
																	+ "<td  onclick='guohushowHidden(this)'><span>"
																	+ item.zhuanchu
																	+ "</span></td>"
																	+ "<td  onclick='guohushowHidden(this)' align='right'><strong>转入单位：</strong></td>"
																	+ "<td  onclick='guohushowHidden(this)'><span>"
																	+ item.zhuanru
																	+ "</span></td>"
																	+ "<td  onclick='guohushowHidden(this)' align='right'><strong>客户单号：</strong></td>"
																	+ "<td  onclick='guohushowHidden(this)'><span>"
																	+ item.kehudanhao
																	+ "</span></td>"
																	+ "<td rowspan='4' valign='top'><img src='cangchu/img/xiangxia.png' width='20' class='caozuo_zhankai' onclick='guohushowHidden(this)' style='display:block;margin-top:10px;' /></td>"
																	+ "</tr>"
																	+ "<tr>"
																	+ "<td align='right' onclick='tiaozhuanguohu(this)'><strong>过户时间：</strong></td>"
																	+ "<td onclick='tiaozhuanguohu(this)'><span>"
																	+ item.time
																	+ "</span></td>"
																	+ "<td align='right' onclick='tiaozhuanguohu(this)'><strong>订单状态：</strong></td>"
																	+ "<td onclick='tiaozhuanguohu(this)'><span>"
																	+ item.zhuangtai
																	+ "</span></td>"
																	+ "<td align='right' onclick='tiaozhuanguohu(this)'><strong>备注：</strong></td>"
																	+ "<td onclick='tiaozhuanguohu(this)'><span>"
																	+ item.remark
																	+ "</span></td>"
																	+ "</tr>"
																	+ "<tr class='tr_bg'>"
																	+ "<td align='right' class='tr_border_left' onclick='tiaozhuanguohu(this)'><strong>货物品类：</strong></td>"
																	+ "<td onclick='tiaozhuanguohu(this)'><span>"
																	+ item.pinlei
																	+ "</span></td>"
																	+ "<td align='right' onclick='tiaozhuanguohu(this)'><strong>名称：</strong></td>"
																	+ "<td onclick='tiaozhuanguohu(this)'><span>"
																	+ item.mingcheng
																	+ "</span></td>"
																	+ "<td align='right' onclick='tiaozhuanguohu(this)'><strong>规格：</strong></td>"
																	+ "<td onclick='tiaozhuanguohu(this)'><span>"
																	+ item.guige
																	+ "</span></td>"
																	+ "<td align='right' onclick='tiaozhuanguohu(this)'><strong>材质：</strong></td>"
																	+ "<td class='tr_border_right' onclick='tiaozhuanguohu(this)'><span>"
																	+ item.caizhi
																	+ "</span></td>"
																	+ "</tr>"
																	+ "<tr class='tr_bg'>"
																	+ "<td align='right' class='tr_border_left_xia' onclick='tiaozhuanguohu(this)'><strong>属性：</strong></td>"
																	+ "<td onclick='tiaozhuanguohu(this)'><span>"
																	+ item.shuxing
																	+ "</span></td>"
																	+ "<td align='right' onclick='tiaozhuanguohu(this)'><strong>产地：</strong></td>"
																	+ "<td onclick='tiaozhuanguohu(this)'><span>"
																	+ item.chandi
																	+ "</span></td>"
																	+ "<td align='right' onclick='tiaozhuanguohu(this)'><strong>过户重量：</strong></td>"
																	+ "<td onclick='tiaozhuanguohu(this)'><span>"
																	+ item.zhongliang
																	+ "吨</span></td>"
																	+ "<td align='right' onclick='tiaozhuanguohu(this)'><strong></strong></td>"
																	+ "<td class='tr_border_right_xia' onclick='tiaozhuanguohu(this)'><span>"
																	+ "</span></td>"
																	+ "</tr>"
																	+ "</tbody></table></div>");
										});
						$("#guohupage").val(parseInt(obj[0].pageNow) + 1);
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

}

// ---------------------过户审核方法结束



/*--------------jQuery中方法----------------*/

$(function() {

	$("#shuaxin").click(function(){
		document.location.reload();
	});
	// 当点击查询按钮时时候
	$("#sousu_but").click(function() {
		$("#guohupage").val("1");
		//如果选择的是出库订单
		if($("#type_xuanze").val()=="出库订单"){
			//每当点击查询时，清空里面内容
			$(".content_center").html("");//先清空，再添加;
			//当前页赋值为0
			pageNow=0;
			selectShenHePlanExport();
			
		}else if ($("#type_xuanze").val() == "过户订单") {
			// 查询正在审核的过户订单
			guohushenhe();
			
		} else {

			// 每当点击查询时，清空里面内容
			$(".content_center").html("");// 先清空，再添加;
			// 当前页赋值为0
			pageNowTow = 0;
			// 调用待条件查询方法
			sousu_but();

		}
	});

	$("#kehudanhao").keydown(function(event) {
		if (event.keyCode == 13) {
			$("#sousu_but").click();
		}
	});
	$("#huozhu").keydown(function(event) {
		if (event.keyCode == 13) {
			$("#sousu_but").click();
		}
	});

	/* 发起出库的运输方式判断，显示不同的样式 */
	$("#yunshu").change(function() {
		var x = $(this).val();
		if (x == "汽运") {
			$("#huoyun").css("display", "none");
			$("#qiyun").css("display", "block");
		} else {
			$("#huoyun").css("display", "block");
			$("#qiyun").css("display", "none");
		}
	});

	/* 当车号的文本框失去焦点的时候进行判断 */
	$("#chehaos").blur(function() {
		// 验证车牌号
		var chehaoyan = /^[0-9]{5,10}$/;
		if ($(this).val() == "" || !chehaoyan.test($(this).val())) {
			$(this).siblings("img").attr("src", "cangchu/img/err.png");
		}
	});
	/* 当车号的文本框得到的焦点的时候 */
	$("#chehaos").focus(function() {
		$(this).siblings("img").attr("src", "cangchu/img/success.png");
	});

	// 车号的样式显示
	$("#gan").hover(function() {
		$(".chehao_ul1").css("display", "block");
	}, function() {
		$(".chehao_ul1").hover(function() {
			$(".chehao_ul1").css("display", "block");

			$(".chehao_ul1 li").mouseover(function() {
				$(".chehao_ul1 li").css({
					"background-color" : "#0066FF",
					"color" : "#ffffff"
				});
				$(this).css({
					"background-color" : "#FFF",
					"color" : "#000000"
				});
				var text = $(this).text();
				$(this).click(function() {
					$("#gan").text(text);
				});
			});
		}, function() {
			$(".chehao_ul1").css("display", "none");
		});

	});
	$("#gan").mouseout(function() {
		$(".chehao_ul1").css("display", "none");
	});

	$("#zimu").hover(function() {
		$(".chehao_ul2").css("display", "block");
	}, function() {
		$(".chehao_ul2").hover(function() {
			$(".chehao_ul2").css("display", "block");

			$(".chehao_ul2 li").mouseover(function() {
				$(".chehao_ul2 li").css({
					"background-color" : "#0066FF",
					"color" : "#ffffff"
				});
				$(this).css({
					"background-color" : "#FFF",
					"color" : "#000000"
				});
				var text = $(this).text();
				$(this).click(function() {
					$("#zimu").text(text);
				});
			});
		}, function() {
			$(".chehao_ul2").css("display", "none");
		});

	});
	$("#zimu").mouseout(function() {
		$(".chehao_ul2").css("display", "none");
	});

	/* 车号失去焦点，汽运 */
	$("#chehao").blur(function() {
		// 验证车牌号
		var chehaoyan = /^[0-9a-zA-Z]{5}$/;
		if ($(this).val() == "" || !chehaoyan.test($(this).val())) {
			$(this).siblings("img").attr("src", "cangchu/img/err.png");
		}
	});
	$("#chehao").focus(function() {
		$(this).siblings("img").attr("src", "cangchu/img/success.png");
	});

	// 判断司机姓名是否为空
	$("#siji").blur(function() {
		if ($(this).val() == "") {
			$(this).siblings("img").attr("src", "cangchu/img/err.png");
		}
	});
	$("#siji").focus(function() {
		$(this).siblings("img").attr("src", "cangchu/img/success.png");
	});

	// 当电话失去焦点-->
	$("#sijitel").blur(function() {
		// 验证电话
		var telyanzheng = /^1(3|4|5|7|8)\d{9}$/;
		if ($(this).val() == "" || !telyanzheng.test($(this).val())) {
			$(this).siblings("img").attr("src", "cangchu/img/err.png");
		}
	});
	$("#sijitel").focus(function() {
		$(this).siblings("img").attr("src", "cangchu/img/success.png");
	});

	// 入库单号的焦点判断-->
	$("#rukudanhao").blur(function() {
		if ($(this).val() == "") {
			$(this).siblings("img").attr("src", "cangchu/img/err.png");
		}
	});
	$("#rukudanhao").focus(function() {
		$(this).siblings("img").attr("src", "cangchu/img/success.png");
	});

	// 判断入库的填写的数字-->
	$("#rukuzhongliang").blur(function() {
		var zhongyan = /^\d+(\.\d+)?$|^$|^(\d+|\-){7,}$/;
		if ($(this).val() == "" || !zhongyan.test($(this).val())) {
			$(this).siblings("img").attr("src", "cangchu/img/err.png");
		}
	});
	$("#rukuzhongliang").focus(function() {
		$(this).siblings("img").attr("src", "cangchu/img/success.png");
	});

	// 最后一次验证
	$("#tijiao")
			.click(
					function() {
						// 删除已经具有的打开样式

						if ($("#yunshu").val() == "汽运") {
							// 将车号的选择付给隐藏的文本框中
							$("#chehao_hide").val(
									$("#gan").text() + $("#zimu").text()
											+ $("#chehao").val());
							var chehao = $("#chehao").val();
						} else {
							$("#chehao_hide").val(
									$("#huo").text() + $("#chehaos").val());
							var chehao = $("#chehaos").val();
						}

						$("#chehao_fu").text($("#chehao_hide").val());
						// 运输方式
						var yunshu = $("#yunshu").val();
						$("#yunshu_fu").text(yunshu);
						// 司机
						var siji = $("#siji").val();
						$("#siji_fu").text(siji);
						// 司机电话
						var sijitel = $("#sijitel").val();
						$("#sijitel_fu").text(sijitel);
						// 入库单号
						var rukudanhao = $("#rukudanhao").val();
						$("#rukudanhao_fu").text(rukudanhao);

						var telyanzheng = /^1(3|4|5|7|8)\d{9}$/;

						if ($("#yunshu").val() == "汽运") {
							// 验证车牌号
							var chehaoyan = /^[0-9A-Z]{5}$/;
						} else {
							// 验证车牌号
							var chehaoyan = /^[0-9]{5,10}$/;
						}

						// 验证车号
						if (chehao == "" || !chehaoyan.test(chehao)) {
							$("#chehao").siblings("img").attr("src",
									"cangchu/img/err.png");
							alert("车号不能为空且请填写有效的车号");
							return;
						}
						// 验证司机姓名
						if (siji == "") {
							$("#siji").siblings("img").attr("src",
									"cangchu/img/err.png");
							alert("司机姓名不能为空");
							return;
						}

						// 验证司机电话
						if (sijitel == "" || !telyanzheng.test(sijitel)) {
							$("#sijitel").siblings("img").attr("src",
									"cangchu/img/err.png");
							alert("司机电话不能为空且请填写有效电话");
							return;
						}

						// 验证出库单号
						if (rukudanhao == "") {
							$("#rukudanhao").siblings("img").attr("src",
									"cangchu/img/err.png");
							alert("提货单号不能为空");
							return;
						}

						// 验证选择的单位
						var danwei = $("#danwei").val();
						if (danwei == "") {
							alert("请选择出货单位！");
							return false;
						}
						// 货物
						$("#tan_bottom table tr").not(":first").html("");
						for ( var i = 0; i < $("#tab_context .tab_bottom").length; i++) {

							// 货物品类
							var pinlei = $("#tab_context .tab_bottom").eq(i)
									.find("#pinlei").val();
							// 名称
							var mingcheng = $("#tab_context .tab_bottom").eq(i)
									.find("#mingcheng").val();
							// 规格
							var guige = $("#tab_context .tab_bottom").eq(i)
									.find("#guige").val();
							// 材质
							var caizhi = $("#tab_context .tab_bottom").eq(i)
									.find("#caizhi").val();
							// 属性
							var shuxing = $("#tab_context .tab_bottom").eq(i)
									.find("#shuxing").val();
							// 产地
							var chandi = $("#tab_context .tab_bottom").eq(i)
									.find("#chandi").val();
							// 重量
							var zhongliang = $("#tab_context .tab_bottom")
									.eq(i).find("#rukuzhongliang").val();
							// 重量验证正则表达式
							var zhongyan = /^\d+(\.\d+)?$|^$|^(\d+|\-){7,}$/;

							// 验证重量
							if (zhongliang == "" || !zhongyan.test(zhongliang)) {
								$("#tab_context .tab_bottom").eq(i).find(
										"#rukuzhongliang").siblings("img")
										.attr("src", "cangchu/img/err.png");
								alert("请填写货物重量");
								return;
								break;
							}
							if (pinlei == "" || mingcheng == "" || guige == ""
									|| caizhi == "" || shuxing == ""
									|| chandi == "" || zhongliang == ""
									|| !zhongyan.test(zhongliang)) {
								alert("请将信息填写完整，带红星的为必填项，手机号为13,14,15,17,19开头");
								return;
								break;
							}

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

	// 验证完成点击提交
	$("#queding").click(function() {
		alert("确定提交");
		$(".modeal_bottom").css("display", "none");
		$(".modeal_bg").css("display", "none");
		$("#rukuform").submit();
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

	$(".chaofa label:first").css({
		"color" : "#000000"
	});
	$(".qiyun span:last").css({
		"margin-left" : "5px"
	});

	$(".queren a:last").css({
		"float" : "right",
		"width" : "110px",
		"height" : "30px",
		"line-height" : "30px",
		"background" : "url(cangchu/img/zengjiahuowu.png)",
		"color" : "#FFFFFF",
		"text-align" : "center",
		"font-weight" : "bold"
	});
	$(".queren a:last").hover(function() {
		$(this).css({
			"background" : "url(cangchu/img/zengjiahuowu2.png)"
		});
	}, function() {
		$(this).css({
			"background" : "url(cangchu/img/zengjiahuowu.png)"
		});
	});
	$(".queren a i").hover(function() {
		$(this).css({
			"background" : "url(cangchu/img/zengjiahuowu2.png)"
		});
	}, function() {
		$(this).css({
			"background" : "url(cangchu/img/zengjiahuowu.png)"
		});
	});
});

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

function guohushowHidden(str) {
	$(function() {
		var h = $(str).parents(".content_tab").height();
		if (h == 50) {
			$(str).parents(".content_tab").animate({
				"height" : "180px"
			}, 200);
		} else {
			$(str).parents(".content_tab").animate({
				"height" : "50px"
			}, 200);
		}
	});
}
