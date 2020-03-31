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
	});

}
setInterval("show()", 500);