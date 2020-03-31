$(function() {

	// 当点击申请的提交的时候触发
	$("#shenqingtijiao").click(function() {

		if ($("#miaoshu").val() == "") {
			alert("请填写申请描述！");
			return false;
		}
		$("#updateshengqingform").submit();
	});

	$("#cangchutijiao").click(function() {
		// $(".workzx").val($("#zhuangxie").val());
		if (confirm("确定修改吗？")) {
			$("#cangchuupdateform").submit();
		}
	});

	// 当财务修改提交的时候
	/*
	 * $("#caiwutijiao").click(function() { $("#caiwuform").submit(); });
	 */

});

// 单点击仓储的修改时触发
function cangchubianji(str) {
	$(function() {
		var id = $(str).parents("tr").children("td").eq(1).text();// 取出操作订单的编号
		var zhixingren = $(str).parents("tr").children("td").eq(4).text();// 取出对应的执行出库的人
		var lisuan = $(str).parents("tr").children("td").eq(5).text();// 对应取出订单的是否过磅
		var huowu = $(str).parents("tr").find("#goodsId").val();// 取出对应的货物，去查询对应的库位
		var zt = $(str).parents("tr").find("#zt").val();// 取出状态进行判断，如果审核通过不许修改
		var kuwei = $(str).parents("tr").children("td").eq(6).text();// 取出订单中对应的库位名称
		var weight = $(str).parents("tr").children("td").eq(7).text();// 取出对应订单中的操作重量
		var number = $(str).parents("tr").children("td").eq(8).text();// 取出对应订单中的操作件数
		var tianche = $(str).parents("tr").children("td").eq(9).text();// 取出对应订单中天车工
		var zhuangxie = $(str).parents("tr").children("td").eq(10).text();// 取出对应订单中的装卸工

		// 如果操作状态为“准备入库”，“正在审核”，修改信息，否则不许修改，让作废
		if (zt == "准备入库" || zt == "正在审核") {

			// var n = (pici.split(',')).length - 1;
			$("#bh").val(id);

			var bgupdate = $("#bgupdate").val();
			var ddupdate = $("#ddupdate").val();
			var updateshenpi = $("#updateshenpi").val();
			// 用ajax的方式判断该订单，该人是否发起了修改的申请
			$.ajax({// ajax提交方式
				type : "post",
				url : "updateRecordAction.do?flag=goShenQingPanDuan",
				data : {
					"urcaozuoid" : id
				},
				dataType : "json",
				success : function(obj) {
					// 如果具有审批权限的修改
					// 如果没有申请，或者没有权限，那么就打开提交修改申请的模态框
					if (updateshenpi == "0" && obj[0].result == "no") {

						$("#cangchuupdate").css("display", "none");
						$("#updateshenqing").removeClass("fade");
						$("#shenqingbh").val(id);

					} else {

						// 否则就之间进行修改
						// 查询出对应的执行人
						zhixingchuku(zhixingren);
						// 设置过磅为订单的值
						$("#lisuan").val(lisuan);
						// 设置查询的库位
						selectchukukuwei(kuwei, huowu);
						// 设置修改模态框中的货物
						$("#goodsxuanze").val(huowu);
						// 设置显示现有的批次
						$("#picis").html("");
						/*
						 * var picishow = pici.replace(/,/g, ""); for ( var i =
						 * 0; i < n; i++) { $("#picis") .append( "<li><input
						 * type='checkbox' checked='checked' id='pici" +
						 * picishow.substring(i * 12, (i + 1) * 12) + "' /><label
						 * for='pici" + picishow.substring(i * 12, (i + 1) * 12) +
						 * "'>" + picishow.substring(i * 12, (i + 1) * 12) + "</label></li>"); }
						 */
						// 设置对应的操作重量和操作件数
						$("#weight").val(weight);
						$("#number").val(number);

						// 查询对应的天车工
						tianchegong(tianche);
						// 查询对应的装卸工
						zhuangxiegong();
						// 判断不可修改的东西
						if (ddupdate == "0" && updateshenpi == "0") {
							$("#zhixing").attr("disabled", "disabled");
							$("#lisuan").attr("disabled", "disabled");
							$("#kuwei").attr("disabled", "disabled");
							$("#picis input[type=checkbox]").attr("disabled",
									"disabled");
						}
						if (bgupdate == "0" && updateshenpi == "0") {
							$("#weight").attr("disabled", "disabled");
							$("#number").attr("disabled", "disabled");
							$("#tianche").attr("disabled", "disabled");
							$("#zhuangxie").attr("disabled", "disabled");
						}

					}
				},
				error : function() {
					$("#cangchuupdate").css("display", "none");
					$("#updateshenqing").removeClass("fade");
				}
			});
		} else {
			alert("订单已经审核过，只能作废！");
			document.location.reload();
			return false;
		}
	});
}
// 当点击财务的修改时触发
function caiwubianji(str) {
	if ($("#zhuangtai").val() == "计划出库" || $("#zhuangtai").val() == "准备出库"
			|| $("#zhuangtai").val() == "正在出库"
			|| $("#zhuangtai").val() == "审核未通过"
			|| $("#zhuangtai").val() == "审核通过"
			|| $("#zhuangtai").val() == "正在收费") {
		alert("订单不处于收费过程！");
		document.location.reload();
		return false;
	}
	var id = $(str).parents("tr").children("td").eq(1).text();// 取出操作订单的编号
	var jiesuan = $(str).parents("tr").children("td").eq(9).text();// 取出该订单中的结算范式
	var yscost = $(str).parents("tr").children("td").eq(8).text();// 取出该订单中的应收费用

	var cwupdate = $("#cwupdate").val();// 财务修改权限
	var updateshenpi = $("#updateshenpi").val();// 修改审批权限

	// 用ajax的方式判断该订单，该人是否发起了修改的申请
	$.ajax({// ajax提交方式
		type : "post",
		url : "updateRecordAction.do?flag=goShenQingPanDuan",
		data : {
			"urcaozuoid" : id
		},
		dataType : "json",
		success : function(obj) {
			// 如果没有申请，或者没有权限，那么就打开提交修改申请的模态框
			if (updateshenpi == "0" && obj[0].result == "no") {

				$("#caiwuupdate").css("display", "none");
				$("#updateshenqing").removeClass("fade");
				$("#shenqingbh").val(id);
			} else {
				// 设置模态框中的操作编号
				$("#cwbh").val(id);
				// 设置订单中的结算方式
				$("#jiesuan").val(jiesuan);
				// 设置模态框中的应收费用
				$("#yscost").val(yscost);
				// 调用支付范式
				zhifufangshi(str);
			}
		},
		error : function() {
			$("#caiwuupdate").css("display", "none");
			$("#updateshenqing").removeClass("fade");
		}
	});
}

// 条件满足，进行修改
function update() {

}

// 打开进行申请修改的模态框
function showShenqing() {

}

// 查询在线的出库执行人
function zhixingchuku(zuoyeren) {
	$(function() {
		$.ajax({
			type : "post",
			url : "checks.do?flag=getChuKuCZY&ff=zhixing&time="
					+ new Date().getTime(),
			async : true,
			dataType : "json",
			success : function(obj) {
				$("#zhixing").html("");
				if (obj != null) {

					if (obj[0].result != null && obj[0].id != null) {
						for ( var i = 0; i < obj.length; i++) {
							$("#zhixing").append(
									"<option value='" + obj[i].id + "'>"
											+ obj[i].name + "</option>");
							if (obj[i].name == zuoyeren) {
								$("#zhixing").val(obj[i].id);
							}
						}
					} else {
						$("#zhixing").append(
								"<option value='无在线人员'>无在线人员</option>");
					}
				} else {
					$("#zhixing")
							.append("<option value='无在线人员'>无在线人员</option>");
				}
			},
			error : function() {
				document.location.href = "/XGProject/cangchu/page/login.jsp";
			}
		});
	});
}

// 查询对应的库位
// 当出库的时候通过货物进行查询对应的库位
function selectchukukuwei(kuwei, huowu) {
	$(function() {
		$.ajax({
			type : "post",
			url : "tarehouse.do?flag=selectAjaxKuwei",
			async : true,
			dataType : "json",
			success : function(obj) {
				$("#kuwei").html("");
				if (obj == null) {
					$("#kuwei").append("<option value='无库位'>无库位</option>");
					return false;
				}
				for ( var i = 0; i < obj.length; i++) {
					$("#kuwei").append(
							"<option value='" + obj[i].id + "'>" + obj[i].name
									+ "</option>");
					if (kuwei == obj[i].name) {
						$("#kuwei").val(obj[i].id);
					}
				}
			},
			error : function() {
				document.location.href = "/XGProject/cangchu/page/login.jsp";
			}
		});
	});
}

// 通过库位和货物进行选择对应的批次

/*
 * function kuweixuanze() { var goods = $("#goodsxuanze").val(); var kuwei =
 * $("#kuwei").val(); $(function() { $ .ajax({// ajax提交方式 type : "post", url :
 * "tarehouseDetail.do?flag=getChuKuPiCi", dataType : "json", data : { "goods" :
 * goods, "kuwei" : kuwei }, success : function(obj) { $("#picis").html(""); if
 * (obj == null) { return false; } if (obj[0].result == null) { return false; }
 * for ( var i = 0; i < obj.length; i++) { $("#picis") .append( "<li><input
 * type='checkbox' name='pici' value='" + obj[i].id + "' id='pici" + obj[i].id + "' /><label
 * for='pici" + obj[i].id + "'>" + obj[i].id + ",剩余重量:" +
 * (parseFloat(obj[i].weight) - parseFloat(obj[i].Eweight)) + ",剩余件数:" +
 * (parseFloat(obj[i].number) - parseFloat(obj[i].Enumber)) + obj[i].unit + "</label></li>"); } },
 * error : function() { document.location.href =
 * "/XGProject/cangchu/page/login.jsp"; } });
 * 
 * }); }
 */

// 选择对应的天车工
function tianchegong(str) {
	$(function() {
		$.ajax({
			type : "post",
			url : "working.do?flag=getZuoYeRenYuan&ff=tianche&time="
					+ new Date().getTime(),
			async : true,
			dataType : "json",
			success : function(obj) {
				$("#tianche").html("");
				if (obj[0].result != null) {
					for ( var i = 0; i < obj.length; i++) {
						$("#tianche").append(
								"<option value='" + obj[i].name + "'>"
										+ obj[i].name + "</option>");
						if (str == obj[i].name) {
							$("#tianche").val(obj[i].name);
						}
					}
				} else {
					$("#tianche").append("<option value='无人员'>无人员</option>");
				}
			},
			error : function() {
				document.location.href = "/XGProject/cangchu/page/login.jsp";
			}
		});
	});
}
// 选择对应额装卸工
function zhuangxiegong() {
	$(function() {
		$.ajax({
			type : "post",
			url : "working.do?flag=getZuoYeRenYuan&ff=zhuangxie&time="
					+ new Date().getTime(),
			async : true,
			dataType : "json",
			success : function(obj) {
				var $dataObj = eval(obj);
				if ($dataObj.length > 0) {

					$("#zhuangxie option").remove();// 先清空，再添加;
					var $select = $("#zhuangxie");
					$.each($dataObj, function(i, item) {
						$select.append("<option value='" + item.zname + "'>"
								+ item.zname + "</option>");

						$select.appendTo(".zhuangxie");
					});
					$('#zhuangxie').selectize({
						maxItems : 3
					});
				}
			},
			error : function() {
				document.location.href = "/XGProject/cangchu/page/login.jsp";
			}
		});
	});

}

// 查询对应的支付方式
function zhifufangshi(str) {

	$(function() {
		$("#zhifu").html("");
		var zhifu = $(str).parents("tr").children("td").eq(10).text();// 取出该订单中支付方式
		var piaoju = $(str).parents("tr").children("td").eq(11).text();// 取出该订单中的票据类型
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
							"<option value='" + obj[i].zhifuid + "'>"
									+ obj[i].piaoju + "</option>");
					if (str == obj[i].piaoju) {
						$("#piaoju").val(obj[i].zhifuid);
					}
				}
			},
			error : function() {
				document.location.href = "/XGProject/cangchu/page/login.jsp";
			}
		});

	});
}
