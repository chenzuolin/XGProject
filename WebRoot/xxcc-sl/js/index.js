var $, layer;
var sums = 0;
layui.use(['element', 'layer', 'jquery'], function() {
	var element = layui.element;
	$ = layui.jquery;
	layer = layui.layer;
	var x = 0;
	//隐藏左侧导航
	$(".hideMenu").click(function() {
		if(x == 0) {
			$(".layui-side.layui-bg-black").stop().animate({
				"left": "-200px"
			}, 5);
			$(".layui-body").stop().animate({
				"left": "0px"
			}, 5);
			$(this).html("&#xe65b;");
			x = 1;
		} else {
			$(".layui-side.layui-bg-black").stop().animate({
				"left": "0px"
			}, 5);
			$(".layui-body").stop().animate({
				"left": "200px"
			}, 5);
			$(this).html("&#xe65a;");
			x = 0;
		}
	});
	$(function() {
		//当单机退出的时候触发
		$(".lockcms").click(function() {
			layer.alert("确定退出系统吗？", {
				icon: 3,
				anim: 4,
				closeBtn: 2,
				btn: ["确定", "关闭"],
				title: ['系统提示', 'font-size:16px;'],
                skin: 'layui-layer-blue',
			}, function() {
				$.ajax({
					type: "post",
					url: "/XGProject/loginAll.do?flag=goLoginAll&ff=zhuxiao",
					async: true,
					dataType: "json",
					success: function(obj) {
						if(obj != null) {
							if(obj[0].result == "n") {
								document.location.href = "/XGProject/cangchu/page/login.jsp";
							}
						} else {
							document.location.href = "/XGProject/cangchu/page/login.jsp";
						}
					},
					error: function() {
						document.location.href = "/XGProject/cangchu/page/login.jsp";
					}
				});
			});
		});
	});
	$(function() {
		if($("#daichuli").text() != null && $("#daichuli").text() != "") {
			sums += parseInt($("#daichuli").text());
		}
		if($("#dingdancaozuo").text() != null && $("#dingdancaozuo").text() != "") {
			sums += parseInt($("#dingdancaozuo").text());
		}
		if($("#dingdanguobang").text() != null && $("#dingdanguobang").text() != "") {
			sums += parseInt($("#dingdanguobang").text());
		}
		if($("#dingdanshenhe").text() != null && $("#dingdanshenhe").text() != "") {
			sums += parseInt($("#dingdanshenhe").text());
		}
		if($("#pankushenhe").text() != null && $("#pankushenhe").text() != "") {
			sums += parseInt($("#pankushenhe").text());
		}
		if($("#dingdanshoufei").text() != null && $("#dingdanshoufei").text() != "") {
			sums += parseInt($("#dingdanshoufei").text());
		}
		if($("#updateshenpi").text() != null && $("#updateshenpi").text() != "") {
			sums += parseInt($("#updateshenpi").text());
		}
	});
});

function show() {
	$(function() {
		$.ajax({
			type: "post",
			url: "/XGProject/loginAll.do?flag=goLoginAll&ff=ajax",
			async: true,
			dataType: "json",
			success: function(obj) {

				if(obj != null) {
					if(obj[0].result == "n") {
						document.location.href = "/XGProject/cangchu/page/login.jsp";
					}
				} else {
					document.location.href = "/XGProject/cangchu/page/login.jsp";
				}
			},
			error: function() {
				document.location.href = "/XGProject/cangchu/page/login.jsp";
			}
		});
		/*$.ajax({
			type: "post",
			url: "/XGProject/tidingsAction.do?flag=CountOrderForm",
			data: {
				"id": $("#dengluren").val()
			},
			async: true,
			dataType: "json",
			success: function(obj) {
				if(obj != null) {
					$("#daichuli").text(obj[0].daichuli);
					$("#dingdancaozuo").text(obj[0].dingdancaozuo);
					$("#dingdanguobang").text(obj[0].guobang);
					$("#dingdanshenhe").text(obj[0].shenhe);
					$("#pankushenhe").text(obj[0].pankushenhe);
					$("#dingdanshoufei").text(obj[0].shoufei);
					$("#updateshenpi").text(obj[0].updateshenpi);

				}
				var sum = 0;
				if($("#daichuli").text() != null && $("#daichuli").text() != "") {
					sum += parseInt($("#daichuli").text());
				}
				if($("#dingdancaozuo").text() != null && $("#dingdancaozuo").text() != "") {
					sum += parseInt($("#dingdancaozuo").text());
				}
				if($("#dingdanguobang").text() != null && $("#dingdanguobang").text() != "") {
					sum += parseInt($("#dingdanguobang").text());
				}
				if($("#dingdanshenhe").text() != null && $("#dingdanshenhe").text() != "") {
					sum += parseInt($("#dingdanshenhe").text());
				}
				if($("#pankushenhe").text() != null && $("#pankushenhe").text() != "") {
					sum += parseInt($("#pankushenhe").text());
				}
				if($("#dingdanshoufei").text() != null && $("#dingdanshoufei").text() != "") {
					sum += parseInt($("#dingdanshoufei").text());
				}
				if($("#updateshenpi").text() != null && $("#updateshenpi").text() != "") {
					sum += parseInt($("#updateshenpi").text());
				}
				if(sums < sum) {
					if($("#shandong").css("visibility") == "hidden") {
						$("#shandong").css("visibility", "visible");
						$("title").html("【新消息】");
					} else {
						$("#shandong").css("visibility", "hidden");
						$("title").html("【&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;】");
					}
				}
				$("#shandong").click(function() {
					sums = sum;
					$("#shandong").css("visibility", "visible");
					$("title").html("仓储管理系统");
				});
				$("#shandong").mouseover(function() {
					sums = sum;
					$("#shandong").css("visibility", "visible");
					$("title").html("仓储管理系统");
				});
				if(sum >= 100) {
					$("#message").html('99+');
				} else {
					$("#message").html(sum);
				}
			},
			error: function() {
				document.location.href = "/XGProject/cangchu/page/login.jsp";
			}
		});*/
	});

}
setInterval("show()", 500);