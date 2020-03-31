var $;
layui.use(['element', 'jquery'], function() {
	$ = layui.jquery;
	var element = layui.element;
	$(function() {
		$('#leftnav ul').html(navBar(leftNav)); //使用navBar方式，数据在datas/nav.js 变量名为leftNav
		element.init();
		$('#leftnav ul li').click(function() {
			$('#leftnav ul li').removeClass('layui-nav-itemed');
			$(this).addClass('layui-nav-itemed');
		});
	});
});
//navBar方法是实现步骤，作用是编写出dom结构并且添加数据。
function navBar(strData) {
	var data;
	if(typeof(strData) == "string") {
		data = JSON.parse(strData); //部分用户解析出来的是字符串，转换一下
	} else {
		data = strData;
	}
	var ulHtml = '';

	$.each(data, function(i, obj) {
		if(obj.spread) {
			ulHtml += '<li class="layui-nav-item layui-nav-itemed">';
		} else {
			ulHtml += '<li class="layui-nav-item">';
		}
		if(obj.children != undefined && obj.children.length > 0) {
			ulHtml += '<a href="javascript:;">';
			if(obj.icon != undefined && obj.icon != '') {
				ulHtml += '<i class="fa ' + obj.icon + '"  aria-hidden="true"></i>';
			}
			ulHtml += '<cite>' + obj.title + '</cite>';
			ulHtml += '<span class="layui-nav-more" style="margin-right:10px"></span></a>';
			ulHtml += '<dl class="layui-nav-child">';
			$.each(obj.children, function(j, jobj) {
				if(jobj.show) {
					ulHtml += '<dd><a href="javascript:;" data-url="' + jobj.href + '">';
					ulHtml += jobj.icon;
					ulHtml += '<cite>' + jobj.title + '</cite></a></dd>';
				}
			});
			ulHtml += '</dl>';
		} else {
			ulHtml += '<a href="javascript:;"  data-url="' + obj.href + '">';
			ulHtml += '<i class="fa ' + obj.icon + '" aria-hidden="true"></i>';
			ulHtml += '<cite>' + obj.title + '</cite></a>';

		}
		ulHtml += '</li>';
	});

	return ulHtml;
}