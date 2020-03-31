function load() {
	QueryZhuanChu();
	jiazaijieshu();
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
	$("#hidden_table").html("");
	$("#shaixuan").attr("checked",false);
	jiazai();
	$(function() {
		$
				.ajax({
					type : "post",
					url : "accounts.do?flag=QueryJieSuanFirst&pageNow="
							+ $("#pageNow").val(),
					async : true,
					data : {
						"time" : new Date().getTime(),
						"begin" : $("#startTime").val(),
						"finish" : $("#endTime").val(),
						"jiancheng" : $("#custom-name").val(),
						"rowSize" : $("#showrow").val()
					},
					dataType : "json",
					success : function(obj) {
						jiazaijieshu();
						$("#neirong").html("");
						if (obj == null) {
							$("#neirong")
									.append(
											"<tr colspan='14' style='text-align:center;'>无</tr>");
							return false;
						}
						for ( var i = 0; i < obj.length; i++) {

							$("#neirong")
									.append(
											"<tr onclick='xuanzhong(this)' style='cursor: pointer;'>"
													+ "<td><input type='checkbox' value='"
													+ obj[i].clientId
													+ "' class='check' name='client' /></td>"
													+ "<td>"
													+ obj[i].clientName
													+ "</td>"
													+ "<td>"
													+ obj[i].begin
													+ "<input style='display:none;' type='checkbox' value='"
													+ obj[i].begin
													+ "' name='accountsCreateTime' /></td>"
													+ "<td>"
													+ obj[i].finish
													+ "<input style='display:none;' type='checkbox' value='"
													+ obj[i].finish
													+ "' name='accountsFinishTime' /></td>"
													+ "<td>"
													+ obj[i].qiru
													+ "<input style='display:none;' type='checkbox' value='"
													+ obj[i].qiru
													+ "' name='rukuCost' /></td>"
													+ "<td>"
													+ obj[i].huoru
													+ "<input style='display:none;' type='checkbox' value='"
													+ obj[i].huoru
													+ "' name='zidingyiFour' /></td>"
													+ "<td>"
													+ obj[i].qichu
													+ "<input style='display:none;' type='checkbox' value='"
													+ obj[i].qichu
													+ "' name='zidingyiFive' /></td>"
													+ "<td>"
													+ obj[i].huochu
													+ "<input style='display:none;' type='checkbox' value='"
													+ obj[i].huochu
													+ "' name='zidingyiSix' /></td>"
													+ "<td>"
													+ obj[i].chuxz
													+ "<input style='display:none;' type='checkbox' value='"
													+ obj[i].chuxz
													+ "' name='chukumaxtime' /></td>"
													+ "<td>"
													+ obj[i].guohu
													+ "<input style='display:none;' type='checkbox' value='"
													+ obj[i].guohu
													+ "' name='guohuCost' /></td>"
													+ "<td>"
													+ obj[i].guoxz
													+ "<input style='display:none;' type='checkbox' value='"
													+ obj[i].guoxz
													+ "' name='zhuanchukumaxtime' /></td>"
													+ "<td>"
													+ obj[i].cangchu
													+ "<input style='display:none;' type='checkbox' value='"
													+ obj[i].cangchu
													+ "' name='cangchuCost' /></td>"
													+ "<td>"
													+ obj[i].heji
													+ "<input style='display:none;' type='checkbox' value='"
													+ obj[i].qimoweight
													+ "' name='qimoWeight' /><input style='display:none;' type='checkbox' value='"
													+ obj[i].heji
													+ "' name='accountsExpensesSeed' /></td>"
													+ "<td style='text-align: center;'><a class='btn btn-warning btn-xs' type='button' data-toggle='modal'"
													+ " href='#jiesuan' onclick='jiesuan(this)'>结算</a></td></tr>");
							if (obj[i].heji == 0) {
								$("#neirong tr:last").css({
									"color" : "#B03060"
								});
							}
						}
						$("#pageNow").val(obj[0].pageNow);
						$("#showrow").val(obj[0].rowSize);
						$("#yeshu").val(obj[0].pageNow);
					},
					error : function() {
						document.location.href = "/XGProject/cangchu/page/login.jsp";
					}
				});
	});
}

function piliangtijiao(str) {
	if (str == "piliang") {
		var check = $("#neirong").find("input[type=checkbox]:checked").length;
		if (check <= 0) {
			alert("请选择结算客户！");
			return false;
		}
		if ($("#piliangjiesuan #qixian").val() == "") {
			alert("请选择缴费期限！");
			return false;
		}
		var zhongyan = /^\d+(\.\d+)?$|^$|^(\d+|\-){7,}$/;
		if ($("#piliangjiesuan #feilv").val() == ""
				|| zhongyan.test($("#piliangjiesuan #feilv").val()) == false) {
			alert("请填写费率！");
			return false;
		}
		if (confirm("确定结算吗？")) {
			$("#accform").submit();
		}
	} else {
		if ($("#jiesuan #qixian").val() == "") {
			alert("请选择缴费期限！");
			return false;
		}
		var zhongyan = /^\d+(\.\d+)?$|^$|^(\d+|\-){7,}$/;
		if ($("#jiesuan #feilv").val() == ""
				|| zhongyan.test($("#jiesuan #feilv").val()) == false) {
			alert("请填写费率！");
			return false;
		}
		if (confirm("确定结算吗？")) {
			$("#dangeform").submit();
		}
	}
}