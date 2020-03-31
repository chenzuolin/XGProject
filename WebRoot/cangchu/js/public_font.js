$(function() {
	$("#font_block").hover(function() {
		$(".set_font").css("display", "block");
	}, function() {
		$(".set_font").css("display", "none");
	});

	document.cookie = "size=15px";

	$(".set_font span").click(function() {
		var texts = $(this).text();
		if(texts == "小") {
			document.cookie = "size=13px";
		} else if(texts == "中") {
			document.cookie = "size=15px";
		} else {
			document.cookie = "size=17px";
		}
	});
});

function setfonts(size) {
	$("*").css("font-size", get_cookie("size"));
}

setInterval("setfonts()", 1000);

function get_cookie(Name) {
	if(document.cookie.length > 0) {
		var search = Name + "=" //查询检索的值
		var returnvalue = ""; //返回值
		sd = document.cookie.indexOf(search);
		if(sd != -1) {
			sd += search.length;
			end = document.cookie.indexOf(";", sd);
			if(end == -1)
				end = document.cookie.length;
			//unescape() 函数可对通过 escape() 编码的字符串进行解码。
			returnvalue = unescape(document.cookie.substring(sd, end))
		}
		return returnvalue;
	}
}