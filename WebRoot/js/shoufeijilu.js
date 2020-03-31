function load() {
	QueryZhuanChu();
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

// 查询的内容
function showContent() {
	$(function() {
		$
				.ajax({
					type : "post",
					url : "accounts.do?flag=getAccountsAll&pageNow="
							+ $("#pageNow").val(),
					async : true,
					data : {
						"time" : new Date().getTime(),
						"jiancheng" : $("#custom-name").val(),
						"begin" : $("#startTime").val(),
						"finish" : $("#endTime").val()
					},
					dataType : "json",
					success : function(obj) {
						$("#neirong").html("");
						if (obj == null) {
							$("#neirong")
									.append(
											"<tr colspan='9' style='text-align:center;'>无</tr>");
							return false;
						}
						for ( var i = 0; i < obj.length; i++) {
							$("#neirong")
									.append(
											"<tr style='cursor: pointer;' title='查看详情'>"
													+ "<td onclick='tianzhuanmingxi(this)'>"
													+ obj[i].clientName
													+ ""
													+ "<input type='hidden' value='"
													+ obj[i].id
													+ "' class='clientid' /></td>"
													+ "<td onclick='tianzhuanmingxi(this)'>"
													+ obj[i].begin
													+ "</td>"
													+ "<td onclick='tianzhuanmingxi(this)'>"
													+ obj[i].finish
													+ "</td>"
													+ "<td onclick='tianzhuanmingxi(this)'>"
													+ obj[i].count
													+ "</td>"
													+ "<td onclick='tianzhuanmingxi(this)'>"
													+ obj[i].feilv
													+ "</td>"
													+ "<td onclick='tianzhuanmingxi(this)'>"
													+ obj[i].zhinajin
													+ "</td>"
													+ "<td onclick='tianzhuanmingxi(this)'>"
													+ obj[i].zong
													+ "</td>"
													+ "<td onclick='tianzhuanmingxi(this)'>"
													+ obj[i].shoufeiren
													+ "</td>"
													+ "<td onclick='tianzhuanmingxi(this)'>"
													+ obj[i].shoufeitime
													+ "</td></tr>");
						}
						$("#pageNow").val(obj[0].pageNow);
						$("#yeshu").val(obj[0].pageNow);
					},
					error : function() {
						document.location.href = "/XGProject/cangchu/page/login.jsp";
					}
				});
	});
}
