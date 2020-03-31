// 定义两个当前页数
var pageNow = 1;

$(function() {

	// 当点击上一页的时候，所有的保存当前页的文本框减一
	$("#shang").click(function() {
		// 清空里面内容
		$(".divAdd").html("");// 先清空，再添加;

		pageNow--;
		// 如果pageNow小于0将1赋值给pageNow
		if (pageNow <= 0) {
			pageNow = 1;
		}
		selectKuweiInfo();
	});

	// 当点击下一页的时候，所有的保存当前页的文本框加一
	$("#xia").click(function() {
		// 得到最大页数
		var pageCount = $("#inputVal").val();
		// 每当点击查询时，清空里面内容
		$(".divAdd").html("");// 先清空，再添加;
		pageNow++;
		// 如果pageNow大于最大页数，将最大页数付给pageNow
		if (pageNow > pageCount) {
			pageNow = pageCount;
		}
		selectKuweiInfo();
	});

	// 当点击首页的时候，返回到首页
	$("#shouye").click(function() {
		$(".divAdd").html("");// 先清空，再添加;
		pageNow = 1;
		selectKuweiInfo();
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
			pageNow = page_now;
			// 得到最大页数
			var pageCount = $("#inputVal").val();
			// 每当点击查询时，清空里面内容
			$(".divAdd").html("");// 先清空，再添加;
			// 如果pageNow大于最大页数，将最大页数付给pageNow
			if (pageNow > pageCount) {
				pageNow = pageCount;
			}
			selectKuweiInfo();
		}
	});

	// 当点击到尾页的时候
	$("#weiye").click(function() {
		$(".divAdd").html("");// 先清空，再添加;
		pageNow = $("#inputVal").val();
		selectKuweiInfo();
	});
});
// 第一次加载
function jiazaiLoad() {

	$(".divAdd").html("");// 先清空，再添加;
	selectkw();
}

// 上一页
function shangPage() {
	// 清空里面内容
	$(".divAdd").html("");// 先清空，再添加;

	pageNow--;
	// 如果pageNow小于0将1赋值给pageNow
	if (pageNow <= 0) {
		pageNow = 1;
	}
	selectKuweiInfo();

}

// 下一页
function xiaPage() {
	// 得到最大页数
	var pageCount = $("#inputVal").val();
	// 每当点击查询时，清空里面内容
	$(".divAdd").html("");// 先清空，再添加;
	pageNow++;
	// 如果pageNow大于最大页数，将最大页数付给pageNow
	if (pageNow > pageCount) {
		pageNow = pageCount;
	}
	selectKuweiInfo();

}

// 查询所有的库位
function selectkw() {
	$.ajax({// ajax提交方式
		type : "post",
		url : "tarehouse.do?flag=selectAjaxKuwei",
		dataType : "json",
		success : function(kuwei) {
			var $dataObj = eval(kuwei);
			if ($dataObj.length > 0) {
				$("#kuwei").html("");// 先清空，再添加;
				var $select = $("#kuwei");
				$.each($dataObj, function(i, item) {
					$select.append("<option value='" + item.name + "'>"
							+ item.name + "</option>");
				});
				$('#kuwei').selectize();
			}
			selectKuweiInfo();
		},
		error : function() {
			document.location.href = "/XGProject/cangchu/page/login.jsp";
		}
	});
	$("#huowu").keydown(function(event){
		if(event.keyCode==13){
			$("#tijiao").click();
		}
	});

};

// 查询该库位下的信息

function selectKuweiInfo() {
	// 获取库位和货物的值
	var kuwei = $("#kuwei").val();
	var huowu = $("#huowu").val();
	$("#yeshu").val(pageNow);
	$.ajax({
		type : "post",
		data : {
			"kuwei" : kuwei,
			"huowu" : huowu,
			"pageNow" : pageNow
		// 传入当前页
		},
		url : "tarehouseGoods.do?flag=selectHouseGoodsAjax",
		dataType : "json",
		success : function(obj) {
			var sum_weight = 0;
			var sum_number = 0;
			if (obj.qingkong == "clean") {
				$("#count_div table tr td").eq(0).children("b").text(0);
				$("#count_div table tr td").eq(1).children("b").text(0);
				alert("没有数据！");
			}
			if (obj.length > 0) {
				$(".divAdd").html("");
				$.each(obj, function(i, item) {
					// 将当页数保存起来
					$("#inputVal").val(item.pageCount);
					sum_weight+=parseFloat(item.shenYUWeight.toFixed(3));//剩余重量相加
					sum_number+=parseFloat(item.shenYUNumber.toFixed(3));//剩余件数相加
					$(".divAdd").append(
							"<tr style='height: 40px;'>" + "<td>"
									+ item.kuweiName + "</td>" + "<td>"
									+ item.goodsName + "</td>" + "<td>"
									+ item.guige + "</td>" + "<td>"
									+ item.caizhi + "</td>" + "<td>"
									+ item.shuxin + "</td>" + "<td>"
									+ item.chandi + "</td>" + "<td>"
									+ item.shenYUWeight.toFixed(3) + "</td>" + "<td>"
									+ item.shenYUNumber.toFixed(3) + "</td>" + "<td><a type='button' data-toggle='tab' onclick='clickMenu("+item.id+")'  href='#menu2' class='btn btn-danger btn-xs'>修改</a></td></tr>");
					var piciupdate = $("#piciupdate").val();
					if (piciupdate == "0") {
						$(".table tbody tr td").last().remove();
					}

				});
			}
			
			$("#count_div table tr td").eq(0).children("b").text(sum_weight.toFixed(3));
			$("#count_div table tr td").eq(1).children("b").text(sum_number.toFixed(3));
		},
		error : function() {
			document.location.href = "/XGProject/cangchu/page/login.jsp";
		}
	});
	// ---ajax完毕

};
//当点击修改的时候将对应的模态框显示出来
function clickMenu(str){
	$("#menu2").css("display","block");
	$("#TGoodsId").val(str);
}
//当点击关闭的时候将模态框隐藏
function closeModeal(){
	$("#menu2").css("display","none");
}

$(function() {

	$("#tijiao").click(function() {
		$(".divAdd").html("");// 先清空，再添加;
		pageNow = 1;
		// 调用方法
		selectKuweiInfo();
	});
	
	//当点击库位库存修改提交的时候触发
	$("#TgoodsUpdate").click(function(){
		var weight = $("#weight").val();
		var number = $("#number").val();
		if(weight=="" || weight==null){
			alert("请填写重量！");
			return false;
		}
		if(number=="" || number==null){
			alert("请填写件数！");
			return false;
		}
		// 重量验证正则表达式
		var zhongyan = /^\d+(\.\d+)?$|^$|^(\d+|\-){7,}$/;
		if(zhongyan.test(weight)==false|| zhongyan.test(number)==false){
			alert("请正确填写有效值！");
			return false;
		}
		
		//验证通过则提交
		if(confirm("确定提交吗？")){
			$("#TGoodsForm").submit();
		}
	});

});
