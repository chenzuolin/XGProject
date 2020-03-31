$(function() {
	$("#cangchubianji").click();
	$("#cangchutijiao").click(function() {
		$(".workzx").val($("#zhuangxie").val());
		if (confirm("确定要修改吗？")) {
			$("#cangchuupdateform").submit();
		}
	});

});
// 单点击仓储的修改时触发
function cangchubianji(str) {
	$(function() {
		var id = $(str).parents("tr").children("td").eq(1).text();// 取出操作订单的编号
		var huowu = $(str).parents("tr").find("#goodsId").val();// 取出对应的货物，去查询对应的库位
		var kuwei = $(str).parents("tr").children("td").eq(6).text();// 取出订单中对应的库位名称
		var pici = $(str).parents("tr").children("td").eq(7).text();// 取出订单中的批次
		var n = (pici.split(',')).length - 1;

		$("#bh").val(id);
		// 用ajax的方式判断该订单，该人是否发起了修改的申请
		// 设置查询的库位
		selectchukukuwei(kuwei, huowu);
		// 设置修改模态框中的货物
		$("#goodsxuanze").val(huowu);
		// 设置显示现有的批次
		$("#picis").html("");
		var picishow = pici.replace(/,/g, "");
		for ( var i = 0; i < n; i++) {
			$("#picis").append(
					"<li><input type='checkbox' checked='checked' id='pici"
							+ picishow.substring(i * 12, (i + 1) * 12)
							+ "' value='"+picishow.substring(i * 12, (i + 1) * 12)+"' name='pici' /><label for='pici"
							+ picishow.substring(i * 12, (i + 1) * 12) + "'>"
							+ picishow.substring(i * 12, (i + 1) * 12)
							+ "</label></li>");
			
		}
	});
}


// 查询对应的库位
// 当出库的时候通过货物进行查询对应的库位
function selectchukukuwei(kuwei, huowu) {
	$(function() {
		$.ajax({
			type : "post",
			url : "tarehouseGoods.do?flag=selectChuKuKuWei&goodsId=" + huowu
					+ "&time=" + new Date().getTime(),
			async : true,
			dataType : "json",
			success : function(obj) {
				$("#kuwei").html("");
				if (obj == null) {
					$("#kuwei").append("<option value='无库位'>无库位</option>");
					return false;
				}
				if (obj[0].result == null) {
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
function kuweixuanze() {
	var goods = $("#goodsxuanze").val();
	var kuwei = $("#kuwei").val();
	$(function() {
		$
				.ajax({// ajax提交方式
					type : "post",
					url : "tarehouseDetail.do?flag=getChuKuPiCi",
					dataType : "json",
					data : {
						"goods" : goods,
						"kuwei" : kuwei
					},
					success : function(obj) {
						$("#picis").html("");
						if (obj == null) {
							return false;
						}
						if (obj[0].result == null) {
							return false;
						}
						for ( var i = 0; i < obj.length; i++) {
							$("#picis")
									.append(
											"<li><input type='checkbox' name='pici' value='"
													+ obj[i].id
													+ "' id='pici"
													+ obj[i].id
													+ "' /><label for='pici"
													+ obj[i].id
													+ "'>"
													+ obj[i].id
													+ ",剩余重量:"
													+ (parseFloat(obj[i].weight) - parseFloat(obj[i].Eweight))
													+ ",剩余件数:"
													+ (parseFloat(obj[i].number) - parseFloat(obj[i].Enumber))
													+ obj[i].unit
													+ "</label></li>");
						}
					},
					error : function() {
						document.location.href = "/XGProject/cangchu/page/login.jsp";
					}
				});

	});
}

