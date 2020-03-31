$(function() {

	// 当点击模态框中的确定提交的时候触发
	$("#queding").click(function() {
		$("#pankuForm").submit();
	});

	// 当点击行的时候，前面的复选框选中，并且样式改变
	$(".content_middle table tbody tr td").not("td:last-child").click(
			function() {
				var check = $(this).parent("tr").find("input[type=checkbox]")
						.attr("checked");
				if (check) {
					$(this).parent("tr").find("input[type=checkbox]").attr(
							"checked", false);
					$(this).parent("tr").css("background-color", "");
				} else {
					$(this).parent("tr").find("input[type=checkbox]").attr(
							"checked", true);
					$(this).parent("tr").css("background-color", "#E9C5AF");
				}
			});

	// 当点击全选的时候
	$("#quanxuan").click(
			function() {
				var check = $(this).attr("checked");
				if (check) {
					$(".content_middle table tbody tr td input[type=checkbox]")
							.attr("checked", true);
					$(".content_middle table tbody tr").css("background-color",
							"#E9C5AF");
				} else {
					$(".content_middle table tbody tr td input[type=checkbox]")
							.attr("checked", false);
					$(".content_middle table tbody tr").css("background-color",
							"");
				}
			});

	// 当提交的时候
	$("#panku_tijiao")
			.click(
					function() {
						var check = $(".content_middle table tbody tr td input[type=checkbox]:checked").length;
						if (check <= 0) {
							alert("请对库位和货物进行选择！");
							return;
						}
						var len = $(".content_middle table tbody tr td input[type=checkbox]").length;

						$(".tab_tan tbody").html("");
						for ( var i = 0; i < len; i++) {
							$(".content_middle table tbody tr").eq(i).children(
									"td").eq(11).children("input").attr("name",
									"r");// 将对应的备注的文本框进行清空
						}
						for ( var i = 0; i < len; i++) {

							if ($(
									".content_middle table tbody tr td input[type=checkbox][name=tarehouseGoodss]")
									.eq(i).attr("checked")) {
								var pandianren = $(
										"#pandianren option:selected").text();
								if (pandianren == "无在线人员") {
									alert("请选择盘库人！");
									return false;
								}
								var kuwei = $(".content_middle table tbody tr")
										.eq(i).children("td").eq(1).text();
								var pinlei = $(".content_middle table tbody tr")
										.eq(i).children("td").eq(2).text();
								var mingcheng = $(
										".content_middle table tbody tr").eq(i)
										.children("td").eq(3).text();
								var guige = $(".content_middle table tbody tr")
										.eq(i).children("td").eq(5).text();
								var caizhi = $(".content_middle table tbody tr")
										.eq(i).children("td").eq(6).text();
								var shuxing = $(
										".content_middle table tbody tr").eq(i)
										.children("td").eq(7).text();
								var chandi = $(".content_middle table tbody tr")
										.eq(i).children("td").eq(8).text();
								var zhongliang = $(
										".content_middle table tbody tr").eq(i)
										.children("td").eq(9).text();
								var jianshu = $(
										".content_middle table tbody tr").eq(i)
										.children("td").eq(10).text();
								$(".content_middle table tbody tr").eq(i)
										.children("td").eq(11)
										.children("input").attr("name",
												"checkRemarks");// 给对应的备注的文本框进行重新的设置name属性
								$(".tab_tan tbody")
										.append(
												"<tr style='background-color:#38A3D5; height:40px; line-height:40px; text-align:center; font-weight:bold;'><td >"
														+ pandianren
														+ "</td ><td>"
														+ kuwei
														+ "</td><td>"
														+ pinlei
														+ "</td><td>"
														+ mingcheng
														+ "</td><td>"
														+ guige
														+ "</td><td>"
														+ caizhi
														+ "</td><td>"
														+ shuxing
														+ "</td><td>"
														+ chandi
														+ "<td>"
														+ zhongliang
														+ "</td><td>"
														+ jianshu
														+ "</td></tr>");
							}
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

// 当页面加载的时候，利用ajax的方式查询对应的盘库执行人
function zhixingpanku() {
	$(function() {
		$.ajax({
			type : "post",
			url : "checks.do?flag=getQueryData&ff=zhixing&time="
					+ new Date().getTime(),
			async : true,
			dataType : "json",
			success : function(obj) {
				$("#pandianren").html("");
				if (obj != null) {

					if (obj[0].result != null && obj[0].id != null) {
						for ( var i = 0; i < obj.length; i++) {
							$("#pandianren").append(
									"<option value='" + obj[i].id + "'>"
											+ obj[i].name + "</option>");
						}
					} else {
						$("#pandianren").append(
								"<option value='无在线人员'>无在线人员</option>");
					}
				} else {
					$("#pandianren").append(
							"<option value='无在线人员'>无在线人员</option>");
				}
			},
			error : function() {
				document.location.href = "/XGProject/cangchu/page/login.jsp";
			}
		});
	});
}

// 当页面加载的时候，查询对应的库存货物
function selectGoods() {
	$(function() {
		$
				.ajax({
					type : "post",
					url : "checks.do?flag=getChecksFirst&pageNow="
							+ $("#nowPage").val() + "&ff=goods&time="
							+ new Date().getTime() + "&search="
							+ encodeURI(encodeURI($("#sousu").val())),
					async : true,
					dataType : "json",
					success : function(obj) {
						if (obj[0].max == "maxs") {
							alert("已到底部！");
							return false;
						}
						// $(".content_middle table tbody").html(""); //
						// 清空货物中的dom
						if (obj[0].result != null) {
							// 将当前页保存到隐藏的文本域中
							$("#nowPage").val(parseInt(obj[0].pageNow) + 1);
							for ( var i = 0; i < obj.length; i++) {
								$(".content_middle table tbody")
										.append(
												"<tr><td onclick='tdclick(this)'><input type='checkbox' value='"
														+ obj[i].id
														+ "' name='tarehouseGoodss'/></td>"
														+ "<td onclick='tdclick(this)'>"
														+ obj[i].kuwei
														+ "<input type='checkbox' style='display: none;' value='"
														+ obj[i].kuweiId
														+ "' name='tarehouses' /></td><td onclick='tdclick(this)'>"
														+ obj[i].pinlei
														+ "</td><td onclick='tdclick(this)'>"
														+ obj[i].mingcheng
														+ "</td><td onclick='tdclick(this)'>"
														+ obj[i].zhujifu
														+ "</td><td onclick='tdclick(this)'>"
														+ obj[i].guige
														+ "</td><td onclick='tdclick(this)'>"
														+ obj[i].caizhi
														+ "</td><td onclick='tdclick(this)'>"
														+ obj[i].shuxing
														+ "</td><td onclick='tdclick(this)'>"
														+ obj[i].chandi
														+ "</td><td onclick='tdclick(this)'>"
														+ obj[i].zhongliang
														+ "吨<input type='checkbox' value='"
														+ obj[i].zhongliang
														+ "' name='checkWeight' style='display: none;' /></td>"
														+ "<td onclick='tdclick(this)'>"
														+ obj[i].jianshu
														+ "<input type='checkbox' value='"
														+ obj[i].jianshu
														+ "' name='checkNumber'style='display: none;'/></td><td><input type='text' class='remark' name='r'/></td></tr>");
							}
						} else {
							$(".content_middle table tbody").html("");
							$(".content_middle table tbody").append(
									"<tr><td colspan='12'>无货物</td></tr>");
						}
						if (obj == null) {
							$(".content_middle table tbody").html("");
							$(".content_middle table tbody").append(
									"<tr><td colspan='12'>无货物</td></tr>");
						}
					},
					error : function() {
						document.location.href = "/XGProject/cangchu/page/login.jsp";
					}
				});
	}); // jquery function结束
}

function load() {
	zhixingpanku();
	selectGoods();
}

function tdclick(str) {
	$(function() {
		// 当点击行的时候，前面的复选框选中，并且样式改变
		var check = $(str).parent("tr").find("input[type=checkbox]").attr(
				"checked");
		if (check) {
			$(str).parent("tr").find("input[type=checkbox]").attr("checked",
					false);
			$(str).parent("tr").css("background-color", "");
		} else {
			$(str).parent("tr").find("input[type=checkbox]").attr("checked",
					true);
			$(str).parent("tr").css("background-color", "#E9C5AF");
		}
	});
}