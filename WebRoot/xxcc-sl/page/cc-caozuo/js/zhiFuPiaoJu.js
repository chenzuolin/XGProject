//查询对应的支付方式
function zhifu(_this) {
	layui.use(['jquery', 'layer', 'form'], function() {
		var $ = layui.jquery;
		var layer = layui.layer;
		var form = layui.form;

		$(_this).html("");
		$.ajax({
			type: "post",
			url: "/XGProject/paymentFashion.do?flag=getGuoHuQuery&ff=zhifu",
			dataType: "json",
			async: false,
			success: function(obj) {
				if(obj == null || obj.length <= 0 || obj[0].result == null) {
					$(_this).append("<option value=''>无</option>");
					return false;
				}
				for(var i = 0; i < obj.length; i++) {
					$(_this).append("<option value='" + obj[i].zhifu + "'>" + obj[i].zhifu + "</option>");
				}
			},
			error: function() {
				layer.alert("获得支付方式数据错误！", {
					icon: 2,
					closeBtn: 2,
					anim: 4
				});
			}
		});
		form.render('select');
	});
}
//查询对应的票据类型
function piaoju(_this, zhifu, type) {
	layui.use(['jquery', 'layer', 'form'], function() {
		var $ = layui.jquery;
		var layer = layui.layer;
		var form = layui.form;

		$(_this).html("");
		$.ajax({
			type: "post",
			url: "/XGProject/paymentFashion.do?flag=getGuoHuQuery&ff=piaoju",
			async: false,
			data: {
				"pfashionName": zhifu
			},
			dataType: "json",
			success: function(obj) {
				if(obj == null || obj.length <= 0 || obj[0].result == null) {
					$(_this).append("<option value=''>无</option>");
					return false;
				}
				if(type == "text") {
					for(var i = 0; i < obj.length; i++) {
						$(_this).append("<option value='" + obj[i].piaoju + "'>" + obj[i].piaoju + "</option>");
					}
				} else {
					for(var i = 0; i < obj.length; i++) {
						$(_this).append("<option value='" + obj[i].zhifuid + "'>" + obj[i].piaoju + "</option>");
					}
				}
			},
			error: function() {
				layer.alert("获得票据类型数据错误！", {
					icon: 2,
					closeBtn: 2,
					anim: 4
				});
			}
		});
		form.render('select');
	});
}