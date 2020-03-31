$(function() {
	// 当账号失去焦点
	$("#loginName").blur(function() {
		if ($(this).val() == "") {
			$(this).parents("tr").find(".cuowu").css("display", "block");
		}
	});
	// 当账号得当焦点
	$("#loginName").focus(function() {
		$(this).parents("tr").find(".cuowu").css("display", "none");
	});
	// 当密码得到焦点
	$("#loginPwd").focus(function() {
		$(this).parents("tr").find(".cuowu").css("display", "none");
	});
	// 当密码失去焦点
	$("#loginPwd").blur(function() {
		if ($(this).val() == "") {
			$(this).parents("tr").find(".cuowu").css("display", "block");
		}
	});
	// 当验证码失去交代
	$("#yanzheng").blur(function() {
		if ($(this).val() == "") {
			$(this).parents("tr").find(".cuowu").css("display", "block");
		}
	});
	// 当验证码得到焦点
	$("#yanzheng").focus(function() {
		$(this).parents("tr").find(".cuowu").css("display", "none");
	});

	// 提交的时候
	$("#submit").submit(
			function() {
				var loginName = $("#loginName").val();
				var loginPwd = $("#loginPwd").val();
				var yanzheng = $("#yanzheng").val();
				if (loginName == "") {
					$("#loginName").parents("tr").find(".cuowu").css("display",
							"block");
					return false;
				}
				if (loginPwd == "") {
					$("#loginPwd").parents("tr").find(".cuowu").css("display",
							"block");
					return false;
				}
				if (yanzheng == "") {
					$("#yanzheng").parents("tr").find(".cuowu").css("display",
							"block");
					return false;
				}

			});

	// 当点击忘记密码的时候
	$("#wangjipassword").click(function() {
		$("#wangji").css("display", "block");
		$("#zhengchang").css("display", "none");
	});

	// 当点击返回的时候
	$("#fanhui").click(function() {
		$("#wangji").css("display", "none");
		$("#zhengchang").css("display", "block");
	});

	// 当点击下一步的时候，进行修改密码
	$("#downyibu").click(
			function() {
				if ($("#yanloginname").val() == "") {
					$("#yanloginname").parents("td").next("td").find(".cuowu")
							.css("display", "block");
					return false;
				}
				if ($("#huodeyanzheng").val() == "") {
					$("#huodeyanzheng").parents("td").next("td").find(".cuowu")
							.css("display", "block");
					return false;
				}

				$.ajax({
					type : "post",
					url : "loginAll.do?flag=AuthCode",
					async : true,
					dataType : "text",
					data : {
						"code" : $("#huodeyanzheng").val()
					},
					success : function(text) {
						if (text == "success") {
							$("#wangji").css("display", "none");
							$("#update").css("display", "block");
						} else if (text == "error") {
							$(".huoqutishi").text("验证码错误！");
						} else if (text == "shixiao") {
							$(".huoqutishi").text("验证码失效！");
						}
					},
					error : function() {
						$(".huoqutishi").text("数据上传错误，请重新获取！");
					}
				});
			});

	// 当修改完成要进行提交的时候
	$("#updatetijiao").click(
			function() {
				if ($("#updatepwd").val() == "") {
					$("#updatepwd").parents("td").next("td").find(".cuowu")
							.css("display", "block");
					return false;
				}
				if ($("#updatequerenpwd").val() == "") {
					$("#updatequerenpwd").parents("td").next("td").find(
							".cuowu").css("display", "block");
					return false;
				}
				var yan = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$/;
				if (yan.test($("#updatepwd").val()) == false) {
					$(".updatetishi").text("密码强度不够，必须字母和数字的组合，8~16位！");
					return false;
				}
				if ($("#updatequerenpwd").val() != $("#updatepwd").val()) {
					$(".updatetishi").text("两次密码输入不一致！");
					return false;
				}

				$.ajax({
					type : "post",
					url : "loginAll.do?flag=updatePwd",
					async : true,
					dataType : "text",
					data : {
						"loginName" : $("#yanloginname").val(),
						"loginPwd" : $("#updatequerenpwd").val()
					},
					success : function(text) {
						if (text == "chenggong") {
							$("#update").css("display", "none");
							$("#zhengchang").css("display", "block");
							$(".tishi").text("修改成功！");
						} else if (text == "shibai") {
							$("#update").css("display", "none");
							$("#zhengchang").css("display", "block");
							$(".updatetishi").text("修改失败！");
						}
					},
					error : function() {
						$(".updatetishi").text("数据上传错误，请重新获取！");
					}
				});
			});

	// 当修改密码的时候返回的时候
	$("#tuichuupdate").click(function() {
		$("#update").css("display", "none");
		$("#zhengchang").css("display", "block");
	});

	// 当获得验证码的账号失去焦点是
	$("#yanloginname").blur(
			function() {
				if ($(this).val() == "") {
					$(this).parents("td").next("td").find(".cuowu").css(
							"display", "block");
				}
			});
	// 当获得验证码的账号得到焦点是
	$("#yanloginname").focus(function() {
		$(this).parents("td").next("td").find(".cuowu").css("display", "none");
	});

	// 当点击获取验证码的时候
	$("#huoqu").click(
			function() {
				if ($("#yanloginname").val() == "") {
					$("#yanloginname").parents("td").next("td").find(".cuowu")
							.css("display", "block");
					return false;
				}
				$.ajax({
					type : "post",
					url : "loginAll.do?flag=ForgetPassword",
					async : true,
					dataType : "text",
					data : {
						"loginName" : $("#yanloginname").val()
					},
					success : function(text) {
						if(text==null){
							$(".huoqutishi").text("验证码发送失败！");	
						}else if (text == "error") {
							$(".huoqutishi").text("账号输入错误，请重新输入！");
						} else {
							$("#yanloginname").attr("readonly", "readonly");
							$(".huoqutishi").text(text);
						}
					},
					error : function() {
						$(".huoqutishi").text("数据上传错误，请重新获取！");
					}
				});
			});

	// 当获得验证码失去焦点的时候
	$("#huodeyanzheng").blur(
			function() {
				if ($(this).val() == "") {
					$(this).parents("td").next("td").find(".cuowu").css(
							"display", "block");
				}
			});
	// 当获得验证码得到焦点的时候
	$("#huodeyanzheng").focus(function() {
		$(this).parents("td").next("td").find(".cuowu").css("display", "none");
	});

	// 当输入新密码的框失去焦点的时候
	$("#updatepwd").blur(
			function() {
				if ($(this).val() == "") {
					$(this).parents("td").next("td").find(".cuowu").css(
							"display", "block");
				}
			});
	// 当输入新密码的框得到焦点的时候
	$("#updatepwd").focus(function() {
		$(this).parents("td").next("td").find(".cuowu").css("display", "none");
	});

	// 当确认密码框失去焦点的时候
	$("#updatequerenpwd").blur(
			function() {
				if ($(this).val() == "") {
					$(this).parents("td").next("td").find(".cuowu").css(
							"display", "block");
				}
			});
	// 当确认密码框得到焦点的时候
	$("#updatequerenpwd").focus(function() {
		$(this).parents("td").next("td").find(".cuowu").css("display", "none");
	});
});