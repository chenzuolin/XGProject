$(function() {
	$("#zhuce").click(function fun() {
		return dianji();
	})
	$(".datepicker").datepicker({
		defaultDate : null
	});
});

function dianji() {

	// “^”是代表以什么开头，“$”是代表以什么结尾。比如：/^\dA$/ //这个就匹配，以数字开头，并且一大写字母A结尾的字符串。
	// 判断是汉字
	$reg_china = /[\u4E00-\u9FA5\uF900-\uFA2D]/;
	// 用户名由汉字、数字字母下划线组成
	$reg_username = /^[\w\u3E00-\u9FA5]+$/;
	// 密码验证，有数字、字母下划线组成并且6-12位
	$reg_password = /^[a-zA-Z0-9_]{6,12}$/;
	// 邮箱验证
	$reg_mail = /^\w+@\w+(\.[a-zA-Z]{2,3}){1,2}$/;
	// 手机号验证
	$reg_phone = /^1\d{10}$/;
	/* 日期验证 */
	$reg_data = /^(\d{4})(-|\/)(\d{2})\2(\d{2})$/;
	/* 身份证验证 */
	$reg_provincial = /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/;
	/* 负责人只能是汉字或字母 */
	$reg_chinaOrEngish = /^[A-Za-z\u4e00-\u9fa5]+$/;
	/* 判断正浮点数 */
	$reg_zhekou = /^(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$/;
	/* 判断是否是图片格式jpg|.jpeg|.gif|.bmp|.png| */
	$reg_tupian = /\.jpg$|\.jpeg$|\.gif$|\.png$/;

	if (!$reg_username.test($("#username").val())) // 用户名判断
	{
		alert("用户名有误，只能是汉字字母数字下划线");
		return false;
	}
	if (!$reg_password.test($("#password").val())) {
		alert("密码输入有误，只能是字母数字下划线6-12位");
		return false;
	}
	if ($("#confirm").val() != $("#password").val()) {
		alert("两次密码输入的不一致");
		return false;
	}
	if (!$reg_mail.test($("#email").val())) {
		alert("邮箱输入有误");
		return false;
	}
	if (!$reg_phone.test($("#phone").val())) {
		alert("联系电话输入有误，必须是11位");
		return false;
	}
	/*
	 * if(!$reg_tupian.test($("#statusImage").val())) { alert("图片格式有误！"); return
	 * false; } if(!$reg_provincial.test($("#statusNumber").val())) {
	 * alert("身份证号有误！"); return false; }
	 */
	if (!$reg_chinaOrEngish.test($("#human").val())) {
		alert("负责人格式不正确，只能是汉字或字母");
		return false;
	}
	if (!$reg_data.test($("#beginDate").val())) {
		alert("合同起始日期输入不正确，格式为：年-月-日，如：2017-01-01");
		return false;
	}
	if (!$reg_data.test($("#finishDate").val())) {
		alert("合同结束日期输入不正确，格式为：年-月-日，如：2017-01-01");
		return false;
	}
	if (!$reg_chinaOrEngish.test($("#firmName").val())) {
		alert("单位名称格式不正确，只能是汉字或字母组成");
		return false;
	}
	if (!$reg_username.test($("#abbreviation").val())) {
		alert("简称中不能为非法字符");
		return false;
	}
	if (!$reg_username.test($("#sign").val())) {
		alert("助记符不正确，只能是汉字字母数字下划线");
		return false;
	}
	if (!$reg_username.test($("#contract").val())) {
		alert("合同号不正确，只能是汉字字母数字下划线");
		return false;
	}

	if (!$reg_zhekou.test($("#zhekou").val())) {
		alert("折扣不正确，必须是正数整数或正小数");
		return false;
	}

	if (!$reg_china.test($("#address").val())) {
		alert("地址不正确，只能是汉字");
		return false;
	}
	return true;
}
