
//定义两个当前页数
var pageNow = 1;
// 有条件的查询当前页数
var pageNowTow = 0;

//第一次加载
function jiazaiLoad() {
	// 第一次加载，或点击该页面时清空
	//----别忘了，获取session中的值	
	$(".divAdd").html("");		
	pageNow=1;//当前页赋值为1	
	
	//如果选择的是出库订单，调用出库方法
	if($("#selOnchange").val()=="出库订单"){		
		//调用出库方法
		selectChuku();
		
	}else if ($("#selOnchange").val() == "过户订单") {
		// 查询正在审核的过户订单
		selectGuohu();
	}else{//进入库方法
		
		selectRuku();
	}

}


//上一页
function shangPage(){

	// 每当点击查询时，清空里面内容
	$(".divAdd").html("");// 先清空，再添加;
	// 如果选择的是出库订单
	if ($("#selOnchange").val() == "出库订单") {
		var startTime=$("#startTime").val();
		var endTime=$("#endTime").val();
		var kehuDanhao=$("#kehuDanhao").val();
		var goodsName=$("#goodsName").val();
			
		pageNow--;
		//如果pageNow小于0将1赋值给pageNow
		if(pageNow<=0){
			pageNow=1;
		}
		
		//如果模糊查询条件都为空时，调用selectRuke();
		if(startTime=="" && endTime=="" 
			&& kehuDanhao=="" && goodsName==""){			
			selectChuku();
		}else{//else调用	selectRukuBut();		
			// 当前页赋值为0
			pageNow--;
			// 调用待条件查询方法
			selectChukuBut();
		}
	
	} else if ($("#selOnchange").val() == "过户订单") {
		var startTime=$("#startTime").val();
		var endTime=$("#endTime").val();
		var kehuDanhao=$("#kehuDanhao").val();
		var goodsName=$("#goodsName").val();
			
		pageNow--;
		//如果pageNow小于0将1赋值给pageNow
		if(pageNow<=0){
			pageNow=1;
		}
		
		//如果模糊查询条件都为空时，调用selectRuke();
		if(startTime=="" && endTime=="" 
			&& kehuDanhao=="" && goodsName==""){			
			selectGuohu();
		}else{//else调用	selectRukuBut();		
			// 当前页赋值为0
			pageNow--;
			// 调用待条件查询方法
			selectGuohuBut();
		}
	} else {// 否则进去
		var startTime=$("#startTime").val();
		var endTime=$("#endTime").val();
		var kehuDanhao=$("#kehuDanhao").val();
		var goodsName=$("#goodsName").val();
			
		pageNow--;
		//如果pageNow小于0将1赋值给pageNow
		if(pageNow<=0){
			pageNow=1;
		}
		
		//如果模糊查询条件都为空时，调用selectRuke();
		if(startTime=="" && endTime=="" 
			&& kehuDanhao=="" && goodsName==""){			
			selectRuku();
		}else{//else调用	selectRukuBut();		
			// 当前页赋值为0
			pageNow--;
			// 调用待条件查询方法
			selectRukuBut();
		}
		
		
	}
	
}

//下一页
function xiaPage(){
	//得到最大页数
	var pageCount=$("#inputVal").val();
	// 每当点击查询时，清空里面内容
	$(".divAdd").html("");// 先清空，再添加;
	
	// 如果选择的是出库订单
	if ($("#selOnchange").val() == "出库订单") {
		
		var startTime=$("#startTime").val();
		var endTime=$("#endTime").val();
		var kehuDanhao=$("#kehuDanhao").val();
		var goodsName=$("#goodsName").val();
			
		pageNow++;
		//如果pageNow大于最大页数，将最大页数付给pageNow
		if(pageNow>pageCount){
			pageNow=pageCount;
		}
		
		//如果模糊查询条件都为空时，调用selectRuke();
		if(startTime=="" && endTime=="" 
			&& kehuDanhao=="" && goodsName==""){			
			selectChuku();
		}else{//else调用	selectRukuBut();	
			// 调用待条件查询方法
			selectChukuBut();
		}
		
	
	} else if ($("#selOnchange").val() == "过户订单") {
		
		var startTime=$("#startTime").val();
		var endTime=$("#endTime").val();
		var kehuDanhao=$("#kehuDanhao").val();
		var goodsName=$("#goodsName").val();
			
		pageNow++;
		//如果pageNow大于最大页数，将最大页数付给pageNow
		if(pageNow>pageCount){
			pageNow=pageCount;
		}
		
		//如果模糊查询条件都为空时，调用selectRuke();
		if(startTime=="" && endTime=="" 
			&& kehuDanhao=="" && goodsName==""){			
			selectGuohu();
		}else{//else调用	selectRukuBut();	
			// 调用待条件查询方法
			selectGuohuBut();
		}
	
	} else {// 否则进去
		var startTime=$("#startTime").val();
		var endTime=$("#endTime").val();
		var kehuDanhao=$("#kehuDanhao").val();
		var goodsName=$("#goodsName").val();
			
		pageNow++;
		//如果pageNow大于最大页数，将最大页数付给pageNow
		if(pageNow>pageCount){
			pageNow=pageCount;
		}
		
		//如果模糊查询条件都为空时，调用selectRuke();
		if(startTime=="" && endTime=="" 
			&& kehuDanhao=="" && goodsName==""){			
			selectRuku();
		}else{//else调用	selectRukuBut();	
			// 调用待条件查询方法
			selectRukuBut();
		}
		
		
	}
	
}


// 当选择入库时调用此方法

function selectRuku() {
		// 如果选择的是出库订单
		$.ajax({
			type : "post",			
			data : {			
				"pageNow" : pageNow// 传入当前页
			},
			url :"inputSeed.do?flag=getDingdanAppPash",
			dataType : "json",
			success : function(obj) {
				if (obj.qingkong == "clean") {
					alert("没有数据！");
				}				
				if (obj.length > 0) {

					$
						.each(obj,function(i, item) {
							//将当页数保存起来
							$("#inputVal").val(item.pageCount);
							$(".divAdd")
								.append(
									"<div class='panel panel-primary divStyle'>" +
											"<input type='hidden' class='inputSeedId' value='"+item.inputSeedId+"'>" +
											"<div class='panel-heading'>" +
												"<p class='panel-title'>订单编号" +
													"<span class='glyphicon glyphicon-chevron-down' onclick='showHidden(this)' style='float: right;'></span>" +
												"</p>" +
											"</div>" +
											"<div class='panel-body' onclick='tiaozhuanruku(this)'; style='cursor: pointer;'>" +
												"<div class='col-md-3 col-xs-6'>" +
													"客户单号：<span>"+item.inputClientNumber+"</span>" +
												"</div>" +
												"<div class='col-md-3 col-xs-6'>" +
													"货物名称：<span>"+item.goodsName+"</span>" +
												"</div>" +
												"<div class='col-md-3 col-xs-6'>" +
													"货物规格：<span>"+item.goodsStandardName+"</span>" +
												"</div>" +
												"<div class='col-md-3 col-xs-6'>" +
													"货物产地 ：<span>"+item.goodsYieldlyName+"</span>" +
												"</div>" +
											"</div>"+
											"<div class='panel-body' onclick='tiaozhuanruku(this)'; style='cursor: pointer;'>" +
												"<div class='col-md-3 col-xs-6'>" +
													"联系方式：<span>"+item.inputDriverTel+"</span>" +
												"</div>" +
												"<div class='col-md-3 col-xs-6'>" +
													"货物品类：<span>"+item.goodsCategoryName+"</span>" +
												"</div>" +
												"<div class='col-md-3 col-xs-6'>" +
													"货物材质：<span>"+item.goodsQualityName+"</span>" +
												"</div>" +
												"<div class='col-md-3 col-xs-6'>" +
													"运输方式：<span>"+item.inputCarryType+"</span>" +
												"</div>" +
											"</div>"+
										"</div>"
										);
						});
				}
			},
			error : function() {
				alert("数据请求错误！");
			}
		});
		// ---ajax完毕
	

};


// -----------------当点击查询入库时被调用的方法开始


function selectRukuBut() {	
	
	var startTime=$("#startTime").val();
	var endTime=$("#endTime").val();
	var kehuDanhao=$("#kehuDanhao").val();
	var goodsName=$("#goodsName").val();
	// 如果选择的是出库订单
	$.ajax({
		type : "post",			
		data : {
			"startTime":startTime,
			"endTime":endTime,
			"kehuDanhao":kehuDanhao,
			"goodsName":goodsName,
			"pageNow" : pageNow// 传入当前页
		},
		url :"inputSeed.do?flag=getDingdanAppTjPash",
		dataType : "json",
		success : function(obj) {
			if (obj.qingkong == "clean") {
				alert("没有数据！");
			}		
			if (obj.length > 0) {

				$.each(obj,function(i, item) {	
					//将当页数保存起来
					$("#inputVal").val(item.pageCount);
						$(".divAdd")
							.append(
								"<div class='panel panel-primary divStyle'>" +
									"<input type='hidden' class='inputSeedId' value='"+item.inputSeedId+"'>" +
										"<div class='panel-heading'>" +
											"<p class='panel-title'>订单编号" +
												"<span class='glyphicon glyphicon-chevron-down' onclick='showHidden(this)' style='float: right;'></span>" +
											"</p>" +
										"</div>" +
										"<div class='panel-body' onclick='tiaozhuanruku(this)'; style='cursor: pointer;'>" +
											"<div class='col-md-3 col-xs-6'>" +
												"客户单号：<span>"+item.inputClientNumber+"</span>" +
											"</div>" +
											"<div class='col-md-3 col-xs-6'>" +
												"货物名称：<span>"+item.goodsName+"</span>" +
											"</div>" +
											"<div class='col-md-3 col-xs-6'>" +
												"货物规格：<span>"+item.goodsStandardName+"</span>" +
											"</div>" +
											"<div class='col-md-3 col-xs-6'>" +
												"货物产地 ：<span>"+item.goodsYieldlyName+"</span>" +
											"</div>" +
										"</div>"+
										"<div class='panel-body' onclick='tiaozhuanruku(this)'; style='cursor: pointer;'>" +
											"<div class='col-md-3 col-xs-6'>" +
												"联系方式：<span>"+item.inputDriverTel+"</span>" +
											"</div>" +
											"<div class='col-md-3 col-xs-6'>" +
												"货物品类：<span>"+item.goodsCategoryName+"</span>" +
											"</div>" +
											"<div class='col-md-3 col-xs-6'>" +
												"货物材质：<span>"+item.goodsQualityName+"</span>" +
											"</div>" +
											"<div class='col-md-3 col-xs-6'>" +
												"运输方式：<span>"+item.inputCarryType+"</span>" +
											"</div>" +
										"</div>"+
									"</div>"
									);
					});
			}
		},
		error : function() {
			alert("数据请求错误！");
		}
	});
	// ---ajax完毕


};




//--------------------------当选择出库时调用此方法

function selectChuku() {
		// 如果选择的是出库订单
		$.ajax({
			type : "post",			
			data : {			
				"pageNow" : pageNow// 传入当前页
			},
			url :"exportSeed.do?flag=getDingdanAppPash",
			dataType : "json",
			success : function(obj) {
				if (obj.qingkong == "clean") {
					alert("没有数据！");
				}				
				if (obj.length > 0) {

					$
						.each(obj,function(i, item) {
							//将当页数保存起来
							$("#inputVal").val(item.pageCount);
							$(".divAdd")
								.append(
									"<div class='panel panel-primary divStyle'>" +
											"<input type='hidden' class='seedId' value='"+item.seedId+"'>" +
											"<div class='panel-heading'>" +
												"<p class='panel-title'>订单编号" +
													"<span class='glyphicon glyphicon-chevron-down' onclick='showHidden(this)' style='float: right;'></span>" +
												"</p>" +
											"</div>" +
											"<div class='panel-body' onclick='tiaozhuanChuku(this)'; style='cursor: pointer;'>" +
												"<div class='col-md-3 col-xs-6'>" +
													"客户单号：<span>"+item.exportClientNumber+"</span>" +
												"</div>" +
												"<div class='col-md-3 col-xs-6'>" +
													"货物名称：<span>"+item.goodsName+"</span>" +
												"</div>" +
												"<div class='col-md-3 col-xs-6'>" +
													"货物规格：<span>"+item.goodsStandardName+"</span>" +
												"</div>" +
												"<div class='col-md-3 col-xs-6'>" +
													"货物产地 ：<span>"+item.goodsYieldlyName+"</span>" +
												"</div>" +
											"</div>"+
											"<div class='panel-body' onclick='tiaozhuanChuku(this)'; style='cursor: pointer;'>" +
												"<div class='col-md-3 col-xs-6'>" +
													"联系方式：<span>"+item.exportDriverTel+"</span>" +
												"</div>" +
												"<div class='col-md-3 col-xs-6'>" +
													"货物品类：<span>"+item.goodsCategoryName+"</span>" +
												"</div>" +
												"<div class='col-md-3 col-xs-6'>" +
													"货物材质：<span>"+item.goodsQualityName+"</span>" +
												"</div>" +
												"<div class='col-md-3 col-xs-6'>" +
													"运输方式：<span>"+item.exportCarryType+"</span>" +
												"</div>" +
											"</div>"+
										"</div>"
										);
						});
				}
			},
			error : function() {
				alert("数据请求错误！");
			}
		});
		// ---ajax完毕
	

};





//-----------------当点击查询入库时被调用的方法开始


function selectChukuBut() {	
	
	var startTime=$("#startTime").val();
	var endTime=$("#endTime").val();
	var kehuDanhao=$("#kehuDanhao").val();
	var goodsName=$("#goodsName").val();
	// 如果选择的是出库订单
	$.ajax({
		type : "post",			
		data : {
			"startTime":startTime,
			"endTime":endTime,
			"kehuDanhao":kehuDanhao,
			"goodsName":goodsName,
			"pageNow" : pageNow// 传入当前页
		},
		url :"exportSeed.do?flag=getDingdanAppTjPash",
		dataType : "json",
		success : function(obj) {
			if (obj.qingkong == "clean") {
				alert("没有数据！");
			}		
			if (obj.length > 0) {

				$.each(obj,function(i, item) {
					//将当页数保存起来
					$("#inputVal").val(item.pageCount);
						$(".divAdd")
							.append(
									"<div class='panel panel-primary divStyle'>" +
										"<input type='hidden' class='seedId' value='"+item.seedId+"'>" +
										"<div class='panel-heading'>" +
											"<p class='panel-title'>订单编号" +
												"<span class='glyphicon glyphicon-chevron-down' onclick='showHidden(this)' style='float: right;'></span>" +
											"</p>" +
										"</div>" +
										"<div class='panel-body' onclick='tiaozhuanChuku(this)'; style='cursor: pointer;'>" +
											"<div class='col-md-3 col-xs-6'>" +
												"客户单号：<span>"+item.exportClientNumber+"</span>" +
											"</div>" +
											"<div class='col-md-3 col-xs-6'>" +
												"货物名称：<span>"+item.goodsName+"</span>" +
											"</div>" +
											"<div class='col-md-3 col-xs-6'>" +
												"货物规格：<span>"+item.goodsStandardName+"</span>" +
											"</div>" +
											"<div class='col-md-3 col-xs-6'>" +
												"货物产地 ：<span>"+item.goodsYieldlyName+"</span>" +
											"</div>" +
										"</div>"+
										"<div class='panel-body' onclick='tiaozhuanChuku(this)'; style='cursor: pointer;'>" +
											"<div class='col-md-3 col-xs-6'>" +
												"联系方式：<span>"+item.exportDriverTel+"</span>" +
											"</div>" +
											"<div class='col-md-3 col-xs-6'>" +
												"货物品类：<span>"+item.goodsCategoryName+"</span>" +
											"</div>" +
											"<div class='col-md-3 col-xs-6'>" +
												"货物材质：<span>"+item.goodsQualityName+"</span>" +
											"</div>" +
											"<div class='col-md-3 col-xs-6'>" +
												"运输方式：<span>"+item.exportCarryType+"</span>" +
											"</div>" +
										"</div>"+
									"</div>"
									);
					});
			}
		},
		error : function() {
			alert("数据请求错误！");
		}
	});
	// ---ajax完毕


};



//--------------------------当选择出库时调用此方法

function selectGuohu() {
		// 如果选择的是出库订单
		$.ajax({
			type : "post",			
			data : {			
				"pageNow" : pageNow// 传入当前页
			},
			url :"shiftStockSeed.do?flag=getDingdanAppPash",
			dataType : "json",
			success : function(obj) {
				if (obj.qingkong == "clean") {
					alert("没有数据！");
				}				
				if (obj.length > 0) {

					$
						.each(obj,function(i, item) {
							//将当页数保存起来
							$("#inputVal").val(item.pageCount);
							$(".divAdd")
								.append(
									"<div class='panel panel-primary divStyle'>" +
											"<input type='hidden' class='seedId' value='"+item.seedId+"'>" +
											"<div class='panel-heading'>" +
												"<p class='panel-title'>订单编号" +
													"<span class='glyphicon glyphicon-chevron-down' onclick='showHidden(this)' style='float: right;'></span>" +
												"</p>" +
											"</div>" +
											"<div class='panel-body' onclick='tiaozhuanGuoku(this)'; style='cursor: pointer;'>" +
												"<div class='col-md-3 col-xs-6'>" +
													"客户单号：<span>"+item.shiftClientNumber+"</span>" +
												"</div>" +
												"<div class='col-md-3 col-xs-6'>" +
													"货物名称：<span>"+item.goodsName+"</span>" +
												"</div>" +
												"<div class='col-md-3 col-xs-6'>" +
													"货物规格：<span>"+item.goodsStandardName+"</span>" +
												"</div>" +
												"<div class='col-md-3 col-xs-6'>" +
													"货物产地 ：<span>"+item.goodsYieldlyName+"</span>" +
												"</div>" +
											"</div>"+
											"<div class='panel-body' onclick='tiaozhuanGuoku(this)'; style='cursor: pointer;'>" +
												"<div class='col-md-3 col-xs-6'>" +
													"联系方式：<span>"+item.shiftDriverTel+"</span>" +
												"</div>" +
												"<div class='col-md-3 col-xs-6'>" +
													"货物品类：<span>"+item.goodsCategoryName+"</span>" +
												"</div>" +
												"<div class='col-md-3 col-xs-6'>" +
													"货物材质：<span>"+item.goodsQualityName+"</span>" +
												"</div>" +												
											"</div>"+
										"</div>"
										);
						});
				}
			},
			error : function() {
				alert("数据请求错误！");
			}
		});
		// ---ajax完毕
	

};





//-----------------当点击查询入库时被调用的方法开始


function selectGuohuBut() {	
	
	var startTime=$("#startTime").val();
	var endTime=$("#endTime").val();
	var kehuDanhao=$("#kehuDanhao").val();
	var goodsName=$("#goodsName").val();
	// 如果选择的是出库订单
	$.ajax({
		type : "post",			
		data : {
			"startTime":startTime,
			"endTime":endTime,
			"kehuDanhao":kehuDanhao,
			"goodsName":goodsName,
			"pageNow" : pageNow// 传入当前页
		},
		url :"shiftStockSeed.do?flag=getDingdanAppTjPash",
		dataType : "json",
		success : function(obj) {
			if (obj.qingkong == "clean") {
				alert("没有数据！");
			}		
			if (obj.length > 0) {

				$.each(obj,function(i, item) {
					//将当页数保存起来
					$("#inputVal").val(item.pageCount);
						$(".divAdd")
							.append(
									"<div class='panel panel-primary divStyle'>" +
									"<input type='hidden' class='seedId' value='"+item.seedId+"'>" +
									"<div class='panel-heading'>" +
										"<p class='panel-title'>订单编号" +
											"<span class='glyphicon glyphicon-chevron-down' onclick='showHidden(this)' style='float: right;'></span>" +
										"</p>" +
									"</div>" +
									"<div class='panel-body' onclick='tiaozhuanGuoku(this)'; style='cursor: pointer;'>" +
										"<div class='col-md-3 col-xs-6'>" +
											"客户单号：<span>"+item.shiftClientNumber+"</span>" +
										"</div>" +
										"<div class='col-md-3 col-xs-6'>" +
											"货物名称：<span>"+item.goodsName+"</span>" +
										"</div>" +
										"<div class='col-md-3 col-xs-6'>" +
											"货物规格：<span>"+item.goodsStandardName+"</span>" +
										"</div>" +
										"<div class='col-md-3 col-xs-6'>" +
											"货物产地 ：<span>"+item.goodsYieldlyName+"</span>" +
										"</div>" +
									"</div>"+
									"<div class='panel-body' onclick='tiaozhuanGuoku(this)'; style='cursor: pointer;'>" +
										"<div class='col-md-3 col-xs-6'>" +
											"联系方式：<span>"+item.shiftDriverTel+"</span>" +
										"</div>" +
										"<div class='col-md-3 col-xs-6'>" +
											"货物品类：<span>"+item.goodsCategoryName+"</span>" +
										"</div>" +
										"<div class='col-md-3 col-xs-6'>" +
											"货物材质：<span>"+item.goodsQualityName+"</span>" +
										"</div>" +												
									"</div>"+
								"</div>"
							);
					});
			}
		},
		error : function() {
			alert("数据请求错误！");
		}
	});
	// ---ajax完毕


};








/*--------------jQuery中方法----------------*/

$(function() {
		
	// 当点击查询按钮时时候
	$("#sousu_but").click(function() {
		
		// 每当点击查询时，清空里面内容
		$(".divAdd").html("");// 先清空，再添加;
		
		//如果选择的是出库订单
		if($("#selOnchange").val()=="出库订单"){
			
			var startTime=$("#startTime").val();
			var endTime=$("#endTime").val();
			var kehuDanhao=$("#kehuDanhao").val();
			var goodsName=$("#goodsName").val();
			
			if(startTime=="" && endTime=="" 
				&& kehuDanhao=="" && goodsName==""){
				pageNow= 1;
				selectChuku();
			}else{		
				// 当前页赋值为0
				pageNow= 1;
				// 调用待条件查询方法
				selectChukuBut();
			}	
			
		}else if ($("#selOnchange").val() == "过户订单") {//---------过户
			
			var startTime=$("#startTime").val();
			var endTime=$("#endTime").val();
			var kehuDanhao=$("#kehuDanhao").val();
			var goodsName=$("#goodsName").val();
			
			if(startTime=="" && endTime=="" 
				&& kehuDanhao=="" && goodsName==""){
				pageNow= 1;
				selectGuohu();
			}else{		
				// 当前页赋值为0
				pageNow= 1;
				// 调用待条件查询方法
				selectGuohuBut();
			}	
			
		} else {
			
			var startTime=$("#startTime").val();
			var endTime=$("#endTime").val();
			var kehuDanhao=$("#kehuDanhao").val();
			var goodsName=$("#goodsName").val();
			
			if(startTime=="" && endTime=="" 
				&& kehuDanhao=="" && goodsName==""){
				pageNow= 1;
				selectRuku();
			}else{		
				// 当前页赋值为0
				pageNow= 1;
				// 调用待条件查询方法
				selectRukuBut();
			}
		}
	});
	
});


function showHidden(str) {
	$(function() {
		var h = $(str).parents(".divStyle").height();
		if (h == 98) {
			$(str).parents(".divStyle").animate({
				"height" : "144px"
			}, 200);
		} else {
			$(str).parents(".divStyle").animate({
				"height" : "100px"
			}, 200);
		}
	});
}

/*function guohushowHidden(str) {
	$(function() {
		var h = $(str).parents(".content_tab").height();
		if (h == 50) {
			$(str).parents(".content_tab").animate({
				"height" : "180px"
			}, 200);
		} else {
			$(str).parents(".content_tab").animate({
				"height" : "50px"
			}, 200);
		}
	});
}*/


