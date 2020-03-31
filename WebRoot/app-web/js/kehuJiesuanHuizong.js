

function load() {

	showContent();
}


$(function() {

	$("#tijiao").click(function() {
		$("#pageNow").val("1");
		showContent();
	});

	// 当点击上一页的时候，所有的保存当前页的文本框减一
	$("#shang").click(function() {
		$("#pageNow").val(parseInt($("#pageNow").val()) - 1);
		showContent();
	});

	// 当点击下一页的时候，所有的保存当前页的文本框加一
	$("#xia").click(function() {
		$("#pageNow").val(parseInt($("#pageNow").val()) + 1);
		showContent();
	});

	// 当点击首页的时候，返回到首页
	$("#shouye").click(function() {
		$("#pageNow").val("1");
		showContent();
	});

	// 跳转文本框中按下回车键的时候，直接跳转
	$("#yeshu").keydown(function(event) {
		if (event.keyCode == 13) {
			$("#go").click();
		}
	});
	// 当点击要跳转到某一页的时候
	$("#go").click(function() {
		var yan = /^[0-9]*$/;
		var page_now = $("#yeshu").val();
		if (yan.test(page_now) && page_now != "") {
			$("#pageNow").val(page_now);
			showContent();
		}
	});

	// 当点击到尾页的时候
	$("#weiye").click(function() {
		$("#pageNow").val("10000");
		showContent();
	});
});


// 查询结算的内容
function showContent() {
	$(function() {
		$
				.ajax({
					type : "post",
					url : "accounts.do?flag=QueryJieSuanKehu&pageNow="
							+ $("#pageNow").val(),
					async : true,
					data : {
						"time" : new Date().getTime(),
						"begin" : $("#startTime").val(),
						"finish" : $("#endTime").val(),
						"rowSize" : $("#showrow").val()
					},
					dataType : "json",
					success : function(obj) {
						$("#neirong").html("");
						if (obj == null) {
							$("#neirong")
									.append(
											"<tr colspan='14' style='text-align:center;'>无</tr>");
							return false;
						}
						for ( var i = 0; i < obj.length; i++) {
							$("#neirong")
									.append(
										"<tr style='cursor: pointer;'>"													
												+ "<td>"+ obj[i].begin+ "</td>"//开始时间
												+ "<td>"+ obj[i].finish+ "</td>"//结束时间
												+ "<td>"+ obj[i].huoQichu+ "</td>"//出库费用合计
												+ "<td>"+ obj[i].huoQiru+ "</td>"//入库费用合计											
												+ "<td>"+ obj[i].guohu+ "</td>"//过户费
												+ "<td>"+ obj[i].cangchu+ "</td>"//仓储费
												+ "<td>"+ obj[i].erci+ "</td>"//二次作业费
												+ "<td>"+ obj[i].chuGuoxz+ "</td>"//出库，过户下站费合计									
												+ "<td>"+ obj[i].heji+ "</td>"//总费用合计
										+"</tr>");
						}
						$("#pageNow").val(obj[0].pageNow);
						$("#showrow").val(obj[0].rowSize);
						$("#yeshu").val(obj[0].pageNow);
					},
					error : function() {
						alert("请求错误！");
					}
				});
	});
}