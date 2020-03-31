$(function() {

	$("#addOperator").click(function() {
		// 在页面中引入all.js方法然后调用里面方法show()
		var $ok = show();
		if ($ok) {
			chooseBumen();
			chooseClassT();
			chooseDuty();
		} else {
			alert("你的浏览器不支持ajax！");
		}
	});

	$(".aId").click(function() {
		// document.location.href="${pageContext.request.contextPath}/goodsYieldly.do?flag=goupdategoodsYieldly&id="+$id;
		var td1 = $(this).parents("td").siblings("td").eq(0).text();// 编号
		var td2 = $(this).parents("td").siblings("td").eq(1).text();// 用户姓名
		var td3 = $(this).parents("td").siblings("td").eq(2).text();// 性别
		var td4 = $(this).parents("td").siblings("td").eq(3).text();// 部门
		var td5 = $(this).parents("td").siblings("td").eq(4).text();// 班组
		var td6 = $(this).parents("td").siblings("td").eq(5).text();// 职务
		var td7 = $(this).parents("td").siblings("td").eq(6).text();// 用户帐号
		var td8 = $(this).parents("td").siblings("td").eq(7).text();// 密码
		var td9 = $(this).parents("td").siblings("td").eq(8).text();// 注册时间
		var td10 = $(this).parents("td").siblings("td").eq(9).text();// 联系电话

		$("#cdId").val(td1);
		$("#loginName").val(td7);
		$("#password").val(td8);
		$("#iusername").val(td2);
		$("#iusersex").val(td3);
		$("#iuserTel").val(td10);
		// $("#bumenBianji").val(td4);
		// $("#banzuBianji").val(td5);
		// $("#zhiwuBianji").val(td6);

		var $ok = show();
		if ($ok) {
			chooseBumen2(td4);
			chooseClassT2(td5);
			chooseDuty2(td6);
		} else {
			alert("你的浏览器不支持ajax！");
		}

	});

});

function chooseClassT() {
	$
			.ajax({// ajax提交方式
				type : "post",
				url : "interiorUser.do?flag=getClassT",
				dataType : "json",
				success : function(classT) {
					var $dataObj = eval(classT);
					// $("#tdInfo select option").remove();//先清空，再添加;
					if ($dataObj.length > 0) {
						$("#banzuAdd option").remove();// 先清空，再添加;
						// var $select=$("<select style='width:100px;'
						// name='classT' id='classInfo'></select>");
						var $select = $("#banzuAdd");
						$.each($dataObj, function(i, item) {
							// alert(item.id + "," + item.classTName);
							$select.append("<option value=" + item.id + ">"
									+ item.classTName + "</option> ");
							$select.appendTo("#banzuDiv");

						});
						$select
								.append("<option value='' selected='true'>请选择--</option>");
					}
				},
				error : function() {
					alert("数据错误！");
				}
			});

}

function chooseDuty() {
	$
			.ajax({// ajax提交方式
				type : "post",
				url : "interiorUser.do?flag=getDuty",
				dataType : "json",
				success : function(interiorUserDuty) {
					var $dataObj = eval(interiorUserDuty);
					// $("#tdInfo select option").remove();//先清空，再添加;
					if ($dataObj.length > 0) {
						$("#zhiwuAdd option").remove();// 先清空，再添加;
						var $select = $("#zhiwuAdd");
						$.each($dataObj, function(i, item) {
							if ($("#zhuceguanliyuan").val() == "0"
									&& item.name == "管理员") {

							} else {
								$select.append("<option value=" + item.id + ">"
										+ item.name + "</option> ");
								$select.appendTo("#zhiwuDiv");
							}
						});
						$select
								.append("<option value='' selected='true'>请选择--</option>");

					}
				},
				error : function() {
					alert("失败！");
				}
			});

}

function chooseBumen() {
	$
			.ajax({// ajax提交方式
				type : "post",
				url : "interiorUser.do?flag=getBumen",
				dataType : "json",
				success : function(bumen) {
					var $dataObj = eval(bumen);
					// $("#tdInfo select option").remove();//先清空，再添加;
					if ($dataObj.length > 0) {
						$("#bumenAdd option").remove();// 先清空，再添加;
						var $select = $("#bumenAdd");
						$.each($dataObj, function(i, item) {
							$select.append("<option value=" + item.id + ">"
									+ item.name + "</option> ");
							$select.appendTo("#bumenDiv");
						});
						$select
								.append("<option value='' selected='true'>请选择--</option>");
					}
				},
				error : function() {
					alert("失败！");
				}
			});

}

// 编辑

function chooseClassT2(banzu) {
	$.ajax({// ajax提交方式
		type : "post",
		url : "interiorUser.do?flag=getClassT",
		dataType : "json",
		success : function(classT) {
			var $dataObj = eval(classT);
			// $("#tdInfo select option").remove();//先清空，再添加;
			if ($dataObj.length > 0) {
				$("#banzuBianji option").remove();// 先清空，再添加;
				// var $select=$("<select style='width:100px;' name='classT'
				// id='classInfo'></select>");
				var $select = $("#banzuBianji");
				$select.append("<option value=''>--无--</option>");
				$.each($dataObj, function(i, item) {
					// alert(item.id + "," + item.classTName);
					$select.append("<option value=" + item.id + ">"
							+ item.classTName + "</option> ");
					$select.appendTo("#banzuDiv2");

					if (item.classTName == banzu) {
						$("#banzuBianji option[value='" + item.id + "']").attr(
								"selected", true);
					}
				});

			}
		},
		error : function() {
			alert("数据错误！");
		}
	});

}

function chooseDuty2(Duty) {
	$.ajax({// ajax提交方式
		type : "post",
		url : "interiorUser.do?flag=getDuty",
		dataType : "json",
		success : function(interiorUserDuty) {
			var $dataObj = eval(interiorUserDuty);
			// $("#tdInfo select option").remove();//先清空，再添加;
			if ($dataObj.length > 0) {
				$("#zhiwuBianji option").remove();// 先清空，再添加;
				var $select = $("#zhiwuBianji");
				$select.append("<option value=''>--无--</option>");
				$.each($dataObj, function(i, item) {
					$select.append("<option value=" + item.id + ">" + item.name
							+ "</option> ");
					$select.appendTo("#zhiwuDiv2");
					// 默认选中当前的库房
					if (item.name == Duty) {
						$("#zhiwuBianji option[value='" + item.id + "']").attr(
								"selected", true);
					}
				});

			}
		},
		error : function() {
			alert("失败！");
		}
	});

}

function chooseBumen2(Bumen) {
	$.ajax({// ajax提交方式
		type : "post",
		url : "interiorUser.do?flag=getBumen",
		dataType : "json",
		success : function(bumen) {
			var $dataObj = eval(bumen);
			// $("#tdInfo select option").remove();//先清空，再添加;
			if ($dataObj.length > 0) {
				$("#bumenBianji option").remove();// 先清空，再添加;
				var $select = $("#bumenBianji");
				$select.append("<option value=''>--无--</option>");
				$.each($dataObj, function(i, item) {
					$select.append("<option value=" + item.id + ">" + item.name
							+ "</option> ");
					$select.appendTo("#bumenDiv2");
					// 默认选中当前的库房
					if (item.name == Bumen) {
						$("#bumenBianji option[value='" + item.id + "']").attr(
								"selected", true);
					}
				});

			}
		},
		error : function() {
			alert("失败！");
		}
	});

}

// 添加

function fun() {
	$reg_password = /^[a-zA-Z0-9_]{6,12}$/;
	$reg_username = /^[\w\u3E00-\u9FA5]+$/;
	// 手机号验证
	$reg_phone = /^1\d{10}$/;
	// 性别
	$reg_sex = /^['男'|'女']$/;

	if (!$reg_username.test($("#zhanghao").val())) // 用户名判断
	{
		alert("账号有误，只能是汉字字母数字下划线");
		return false;
	}
	if (!$reg_password.test($("#pwd").val())) // 用户名判断
	{
		alert("密码格式不正确！必须6-12位");
		return false;
	}
	if (!$reg_username.test($("#name").val())) // 用户姓名
	{
		alert("用户名格式不正确！");
		return false;
	}
	if (!$reg_sex.test($("#sex").val())) // 用户性别
	{
		alert("性别有误！");
		return false;
	}
	if (!$reg_phone.test($("#callnum").val())) // 用户性别
	{
		alert("电话号码有误！");
		return false;
	}
	if ($("#bumenAdd").val() == "" || $("#bumenAdd").val() == null) {
		alert("部门名称不能为空！");
		return false;
	}
	if ($("#zhiwuAdd").val() == "" || $("#zhiwuAdd").val() == null) {
		alert("职务名称不能为空！");
		return false;
	}
	return true;
}

// 编辑

function fun2() {
	$reg_password = /^[a-zA-Z0-9_]{6,12}$/;
	$reg_username = /^[\w\u3E00-\u9FA5]+$/;
	// 手机号验证
	$reg_phone = /^1\d{10}$/;
	// 性别
	$reg_sex = /^['男'|'女']$/;

	if (!$reg_username.test($("#loginName").val())) // 用户名判断
	{
		alert("账号有误，只能是汉字字母数字下划线");
		return false;
	}
	if (!$reg_password.test($("#password").val())) // 用户名判断
	{
		alert("密码格式不正确！");
		return false;
	}
	if (!$reg_username.test($("#iusername").val())) // 用户姓名
	{
		alert("用户名格式不正确！");
		return false;
	}
	if (!$reg_sex.test($("#iusersex").val())) // 用户性别
	{
		alert("性别有误！");
		return false;
	}
	if (!$reg_phone.test($("#iuserTel").val())) // 电话号码
	{
		alert("电话号码有误！");
		return false;
	}
	if ($("#bumenBianji").val() == "" || $("#bumenBianji").val() == null) {
		alert("部门名称不能为空！");
		return false;
	}
	if ($("#zhiwuBianji").val() == "" || $("#zhiwuBianji").val() == null) {
		alert("职务名称不能为空！");
		return false;
	}
	return true;
}
