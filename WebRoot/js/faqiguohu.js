$(function() {
	//验证提货单号是否为空
	$("#tihuodanhao").blur(function() {
		if($(this).val() == "") {
			$(this).siblings("img").attr("src", "../img/err.png");
		}
	});
	$("#tihuodanhao").focus(function() {
		$(this).siblings("img").attr("src", "../img/success.png");
	});

	//验证输入的重量是否合法
	$("#guohuzhongliang").blur(function() {
		if($(this).val() == "") {
			$(this).siblings("img").attr("src", "../img/err.png");
		}
	});
	$("#guohuzhongliang").focus(function() {
		$(this).siblings("img").attr("src", "../img/success.png");
	});

	//当点击提交的时候验证
	$("#guohutijiao").click(function() {
		var tihuodanhao = $("#tihuodanhao").val();
		if(tihuodanhao == "") {
			alert("请输入提货单号");
			$("#tihuodanhao").siblings("img").attr("src", "../img/err.png");
			return;
		}
		$("#tan_bottom table tr").not(":first").html("");
		for(var i = 0; i < $("#tab_context .tab_bottom").length; i++) {

			//货物品类
			var pinlei = $("#tab_context .tab_bottom").eq(i).find("#pinlei").val();
			//名称
			var mingcheng = $("#tab_context .tab_bottom").eq(i).find("#mingcheng").val();
			//规格
			var guige = $("#tab_context .tab_bottom").eq(i).find("#guige").val();
			//材质
			var caizhi = $("#tab_context .tab_bottom").eq(i).find("#caizhi").val();
			//属性
			var shuxing = $("#tab_context .tab_bottom").eq(i).find("#shuxing").val();
			//产地
			var chandi = $("#tab_context .tab_bottom").eq(i).find("#chandi").val();
			//重量
			var zhongliang = $("#tab_context .tab_bottom").eq(i).find("#guohuzhongliang").val();
			//重量验证正则表达式
			var zhongyan = /^\d+(\.\d+)?$|^$|^(\d+|\-){7,}$/;

			//验证重量
			if(zhongliang == "" || !zhongyan.test(zhongliang)) {
				$("#tab_context .tab_bottom").eq(i).find("#guohuzhongliang").siblings("img").attr("src", "../img/err.png")
				alert("请填写货物重量");
				return;
				break;
			}

			$("#zhuanru_fu").text($(".zhuanru").children("select").val());
			$("#guohudanhao_fu").text($("#tihuodanhao").val());
			$("#tan_bottom table").append("<tr style='background-color:#00FF7F;'><td ><strong>货物 " + (i + 1) + "</strong></td ><td><strong>" + pinlei + "</strong></td><td><strong>" + mingcheng + "</strong></td><td><strong>" + guige + "</strong></td><td><strong>" + caizhi + " </strong></td><td><strong>" + shuxing + "</strong></td><td><strong>" + chandi + " </strong><td><strong>" + zhongliang + "吨 </strong></td></tr>");
		}
		//确认提交
		$(".modeal_bg").animate({
			"opacity": 0.5
		}).css("display", "block");
		$(".modeal_bottom").animate({
			"opacity": 1,
			"top": "25%"
		}).css("display", "block");
	});
});