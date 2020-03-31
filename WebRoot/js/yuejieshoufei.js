function load() {
	QueryZhuanChu();
	showContent();
	zhifufangshi();
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
	
	//当点击确定提交的时候触发
	$("#shoufeitijiao").click(function(){
		if($("#shishou").val()==""){
			alert("请填写实收费用！");
			return false;
		}
		var zhongyan = /^\d+(\.\d+)?$|^$|^(\d+|\-){7,}$/;
		if(zhongyan.test($("#shishou").val())==false){
			alert("请正确填写实收费用！");
			return false;
		}
		if($("#jiaofeiren").val()==""){
			alert("请填写缴费人！");
			return false;
		}
		if(confirm("确定提交吗？")){
			$("#shoufeiform").submit();
		}
	});
});

// 查询结算的内容
function showContent() {
	$(function() {
		$
				.ajax({
					type : "post",
					url : "accounts.do?flag=getShouQuQuery&pageNow="
							+ $("#pageNow").val(),
					async : true,
					data : {
						"time" : new Date().getTime(),
						"jiancheng" : $("#custom-name").val(),
						"rowSize" : $("#showrow").val()
					},
					dataType : "json",
					success : function(obj) {
						$("#neirong").html("");
						if (obj == null) {
							$("#neirong")
									.append(
											"<tr colspan='10' style='text-align:center;'>无</tr>");
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
													+ obj[i].jiesuanren
													+ "</td>"
													+ "<td onclick='tianzhuanmingxi(this)'>"
													+ obj[i].jiesuantime
													+ "</td>"
													+ "<td style='text-align: center;'><a class='btn btn-danger btn-xs' type='button' data-toggle='modal'"
													+ " href='#shoufei' onclick='fuzhi(this)'>收费</a></td></tr>");
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

function fuzhi(str) {
	var id = $(str).parents("tr").find(".clientid").val();
	$("#client").val(id);
	var zong = $(str).parents("tr").children("td").eq(6).text();
	$("#yingshouzong").val(zong);
}

function zhifufangshi() {
	$(function() {
		$("#zhifu").html("");
		$.ajax({
			type : "post",
			url : "paymentFashion.do?flag=getGuoHuQuery&ff=zhifu",
			dataType : "json",
			success : function(obj) {
				if (obj == null || obj.length <= 0) {
					$("#zhifu").append("<option value='无'>无</option>");
					return false;
				}
				for ( var i = 0; i < obj.length; i++) {
					$("#zhifu").append(
							"<option value='" + obj[i].zhifu + "'>"
									+ obj[i].zhifu + "</option>");
				}
				piaojuleixing();
			},
			error : function() {
				document.location.href = "/XGProject/cangchu/page/login.jsp";
			}
		});

	});
}

function piaojuleixing() {
	$(function() {
		$.ajax({
			type : "post",
			url : "paymentFashion.do?flag=getGuoHuQuery&ff=piaoju",
			data : {
				"pfashionName" : $("#zhifu").val()
			},
			dataType : "json",
			success : function(obj) {
				$("#piaoju").html("");
				if (obj == null) {
					$("#piaoju").append("<option value='无'>无</option>");
					return false;
				}
				for ( var i = 0; i < obj.length; i++) {
					$("#piaoju").append(
							"<option value='" + obj[i].zhifuid + "'>"
									+ obj[i].piaoju + "</option>");
				}
			},
			error : function() {
				document.location.href = "/XGProject/cangchu/page/login.jsp";
			}
		});

	});
}

