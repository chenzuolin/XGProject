$(function() {

	//当页面加载的时候将左侧的显示内容的a标签全部隐藏
	$(".middle_left .middle_left_span .middle_left_a").css("display", "none");
	//默认将第一个显示
	$(".middle_left .middle_left_span .middle_left_a:first").css("display", "block");

	/*判断浏览器的窗口，如果小于隐藏，大于现实*/
	var clientWidth = document.documentElement.clientWidth; //页面对象宽度（即BODY对象宽度加上Margin宽）
	//如果小于800隐藏，否则显示
	if(clientWidth <= 800) {
		$(".head_right").removeClass("head_visable").addClass("head_hide");
	} else if(clientWidth > 800) {
		$(".head_right").removeClass("head_hide").addClass("head_visable");
	}
	var clientHeight = document.documentElement.clientHeight; //屏幕高度

	//当屏幕的大小发生变化的时候触发
	window.onresize = function() {
		clientWidth = document.documentElement.clientWidth; //页面对象宽度（即BODY对象宽度加上Margin宽） 
		clientHeight = document.documentElement.clientHeight; //屏幕高度
		if(clientWidth <= 800) {
			$(".head_right").removeClass("head_visable").addClass("head_hide");
		} else if(clientWidth > 800) {
			$(".head_right").removeClass("head_hide").addClass("head_visable");
		}

		//判断左侧菜单是否隐藏
		if($(".middle_left").hasClass("head_hide")) {
			$("#context").width(clientWidth - 10); //设置frame宽度
		} else {
			$("#context").width(clientWidth - 256); //设置frame宽度
		}
		$("#context").height(clientHeight - 72); //设置frame高度
		$(".middle_left").height(clientHeight - 72); //设置左侧菜单高度
	}
	/*判断结束*/
	//设置frame的宽度
	$("#context").width(clientWidth - 256); //设置frame宽度
	$("#context").height(clientHeight - 72); //设置frame高度
	$(".middle_left").height(clientHeight - 72); //设置左侧菜单高度

	//单电机左侧的关闭菜单时
	$("#switch").toggle(function() {
		$(".middle_left").animate({
			"opacity": "0",
			"width": "0px"
		}, 300, function() {
			$(".middle_left").removeClass("head_visable").addClass("head_hide");
		});
		$("#context").width($("#context").width() + 250);
	}, function() {
		$(".middle_left").removeClass("head_hide").addClass("head_visable").animate({
			"opacity": "1",
			"width": "250px",
		}, 300, function() {
			$("#context").width($("#context").width() - 250);
		});

	});

	//当点击左侧标题的时候
	$(".middle_left .middle_left_span span").click(function() {
		$(".middle_left .middle_left_span span").css({
			"background": "url(../img/left.png) no-repeat left",
			"color": "#000000"
		});
		$(this).css({
			"background": "url(../img/left2.png) no-repeat left",
			"color": "#38A3D5"
		});
		$(".middle_left .middle_left_span .middle_left_a").stop().slideUp();
		$(this).next("div").stop().slideDown();
	});

	//当点击a标签的时候
	$(".middle_left .middle_left_span .middle_left_a a").click(function() {
		$(".middle_left .middle_left_span .middle_left_a a").css("border-left", "5px solid #FFFFFF");
		$(".middle_left .middle_left_span .middle_left_a a").removeClass("middle_left_aclick");
		$(".middle_left .middle_left_span .middle_left_a a p").removeClass("middle_left_p2 middle_left_p");
		$(this).addClass("middle_left_aclick");
		$(this).css("border-left", "5px solid red");
		$(this).children("p").addClass("middle_left_p");
	});

	//当进入的时候
	$(".middle_left .middle_left_span div a  p").mouseover(function() {
		if($(this).parent("a").hasClass("middle_left_aclick") == false) {
			$(".middle_left .middle_left_span div a p").removeClass("middle_left_p2");
			$(this).addClass("middle_left_p2");
			$(this).parent("a").css("border-left", "5px solid #E2E2E2");
		}
	});

	$(".middle_left .middle_left_span div a  p").mouseout(function() {
		if($(this).parent("a").hasClass("middle_left_aclick") == false) {
			$(".middle_left .middle_left_span div a p").removeClass("middle_left_p2");
			$(this).parent("a").css("border-left", "5px solid #ffffff");
		}
	});

	//点击注销
	$("#alert").click(function() {

		$("#modeal").css("display", "block").animate({
			"opacity": "1",
			"top": "42%"
		}, 500).animate({
			"top": "38%"
		}, 500);
		$("#back").css("display", "block").animate({
			"opacity": "0.8"
		}, 100);
	});
	$("#close").click(function() {
		$("#modeal").animate({
			"opacity": "0",
			"top": "30%"
		}, 100, function() {
			$("#modeal").css("display", "none");
		})
		$("#back").animate({
			"opacity": "0"
		}, 500, function() {
			$("#back").css("display", "none");
		})
	});
	$("#queding").click(function() {
		window.open('about:blank', '_self').close(); //关闭浏览器窗口
	});

	$(".guan").click(function() {
		$("#modeal").animate({
			"opacity": "0",
			"top": "30%"
		}, 100, function() {
			$("#modeal").css("display", "none");
		})
		$("#back").animate({
			"opacity": "0"
		}, 500, function() {
			$("#back").css("display", "none");
		})
	});

	//拖动模态框
	var $div = $(".modeal_p"); /* 绑定鼠标左键按住事件 */
	$div.bind("mousedown", function(event) { /* 获取需要拖动节点的坐标 */
		var offset_x = $("#modeal")[0].offsetLeft; //x坐标
		var offset_y = $("#modeal")[0].offsetTop; //y坐标         /* 获取当前鼠标的坐标 */        
		var mouse_x = event.pageX;
		var mouse_y = event.pageY; /* 绑定拖动事件 */ /* 由于拖动时，可能鼠标会移出元素，所以应该使用全局（document）元素 */
		$(document).bind("mousemove", function(ev) {
			/* 计算鼠标移动了的位置 */
			var _x = ev.pageX - mouse_x;
			var _y = ev.pageY - mouse_y; /* 设置移动后的元素坐标 */
			var now_x = (offset_x + _x) + "px";
			var now_y = (offset_y + _y) + "px"; /* 改变目标元素的位置 */
			$("#modeal").css({
				top: now_y,
				left: now_x
			});
		}); /* 当鼠标左键松开，接触事件绑定 */
		$(document).bind("mouseup", function() {
			$(this).unbind("mousemove");
		});

	});
});