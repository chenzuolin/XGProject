
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
		var jiesuanType=$("#jiesuanType").val();
			
		pageNow--;
		//如果pageNow小于0将1赋值给pageNow
		if(pageNow<=0){
			pageNow=1;
		}
		
		//如果模糊查询条件都为空时，调用selectRuke();
		if(startTime=="" && endTime=="" 
			&& kehuDanhao=="" && goodsName=="" &&  jiesuanType==""){			
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
		var jiesuanType=$("#jiesuanType").val();
			
		pageNow--;
		//如果pageNow小于0将1赋值给pageNow
		if(pageNow<=0){
			pageNow=1;
		}
		
		//如果模糊查询条件都为空时，调用selectRuke();
		if(startTime=="" && endTime=="" 
			&& kehuDanhao=="" && goodsName=="" &&  jiesuanType==""){			
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
		var jiesuanType=$("#jiesuanType").val();
			
		pageNow--;
		//如果pageNow小于0将1赋值给pageNow
		if(pageNow<=0){
			pageNow=1;
		}
		
		//如果模糊查询条件都为空时，调用selectRuke();
		if(startTime=="" && endTime=="" 
			&& kehuDanhao=="" && goodsName=="" &&  jiesuanType==""){			
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
		var jiesuanType=$("#jiesuanType").val();
			
		pageNow++;
		//如果pageNow大于最大页数，将最大页数付给pageNow
		if(pageNow>pageCount){
			pageNow=pageCount;
		}
		
		//如果模糊查询条件都为空时，调用selectRuke();
		if(startTime=="" && endTime=="" 
			&& kehuDanhao=="" && goodsName=="" && jiesuanType==""){			
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
		var jiesuanType=$("#jiesuanType").val();
			
		pageNow++;
		//如果pageNow大于最大页数，将最大页数付给pageNow
		if(pageNow>pageCount){
			pageNow=pageCount;
		}
		
		//如果模糊查询条件都为空时，调用selectRuke();
		if(startTime=="" && endTime=="" 
			&& kehuDanhao=="" && goodsName=="" && jiesuanType==""){			
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
		var jiesuanType=$("#jiesuanType").val();
			
		pageNow++;
		//如果pageNow大于最大页数，将最大页数付给pageNow
		if(pageNow>pageCount){
			pageNow=pageCount;
		}
		
		//如果模糊查询条件都为空时，调用selectRuke();
		if(startTime=="" && endTime=="" 
			&& kehuDanhao=="" && goodsName=="" && jiesuanType==""){			
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
			url :"inputSeed.do?flag=getJieSuanAll",
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
								"<tr style='cursor: pointer;'>" +
									"<td onclick='tiaozhuanruku(this)'>"+item.clientNumber+"<input type='hidden' class='inputVal' value='"+item.seedId+"'></td>" +
									"<td onclick='tiaozhuanruku(this)'>"+item.jiesuanType+"</td>" +
									"<td onclick='tiaozhuanruku(this)'>入库业务</td>" +
									"<td onclick='tiaozhuanruku(this)'>"+item.carryType+"</td>" +
									"<td onclick='tiaozhuanruku(this)'>"+item.sijiName+"</td>" +
									"<td onclick='tiaozhuanruku(this)'>"+item.goodsName+"</td>" +
									"<td onclick='tiaozhuanruku(this)'>"+item.realityWeight+"</td>" +
									"<td onclick='tiaozhuanruku(this)'>"+item.shouldCost+"</td>" +
									"<td onclick='tiaozhuanruku(this)'>"+item.realityCost+"</td>" +										
								"</tr>"
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
	var jiesuanType=$("#jiesuanType").val();
	// 如果选择的是出库订单
	$.ajax({
		type : "post",			
		data : {
			"startTime":startTime,
			"endTime":endTime,
			"kehuDanhao":kehuDanhao,
			"goodsName":goodsName,
			"pageNow" : pageNow,// 传入当前页
			"jiesuanType":jiesuanType
		},
		url :"inputSeed.do?flag=getJieSuanAllTj",
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
							"<tr style='cursor: pointer;'>" +
								"<td onclick='tiaozhuanruku(this)'>"+item.clientNumber+"<input type='hidden' class='inputVal' value='"+item.seedId+"'></td>" +
								"<td onclick='tiaozhuanruku(this)'>"+item.jiesuanType+"</td>" +
								"<td onclick='tiaozhuanruku(this)'>入库业务</td>" +
								"<td onclick='tiaozhuanruku(this)'>"+item.carryType+"</td>" +
								"<td onclick='tiaozhuanruku(this)'>"+item.sijiName+"</td>" +
								"<td onclick='tiaozhuanruku(this)'>"+item.goodsName+"</td>" +
								"<td onclick='tiaozhuanruku(this)'>"+item.realityWeight+"</td>" +
								"<td onclick='tiaozhuanruku(this)'>"+item.shouldCost+"</td>" +
								"<td onclick='tiaozhuanruku(this)'>"+item.realityCost+"</td>" +										
							"</tr>"
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
			url :"exportSeed.do?flag=getChuJiesuanApp",
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
								"<tr style='cursor: pointer;'>" +
									"<td onclick='tiaozhuanChuku(this)'>"+item.clientNumber+"<input type='hidden' class='inputVal' value='"+item.seedId+"'></td>" +
									"<td onclick='tiaozhuanChuku(this)'>"+item.jiesuanType+"</td>" +
									"<td onclick='tiaozhuanChuku(this)'>出库业务</td>" +
									"<td onclick='tiaozhuanChuku(this)'>"+item.carryType+"</td>" +
									"<td onclick='tiaozhuanChuku(this)'>"+item.sijiName+"</td>" +
									"<td onclick='tiaozhuanChuku(this)'>"+item.goodsName+"</td>" +
									"<td onclick='tiaozhuanChuku(this)'>"+item.realityWeight+"</td>" +
									"<td onclick='tiaozhuanChuku(this)'>"+item.shouldCost+"</td>" +
									"<td onclick='tiaozhuanChuku(this)'>"+item.realityCost+"</td>" +										
								"</tr>"
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
	var jiesuanType=$("#jiesuanType").val();
	// 如果选择的是出库订单
	$.ajax({
		type : "post",			
		data : {
			"startTime":startTime,
			"endTime":endTime,
			"kehuDanhao":kehuDanhao,
			"goodsName":goodsName,
			"pageNow" : pageNow,// 传入当前页
			"jiesuanType":jiesuanType
		},
		url :"exportSeed.do?flag=getChuJiesuanAppTj",
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
							"<tr style='cursor: pointer;'>" +
								"<td onclick='tiaozhuanChuku(this)'>"+item.clientNumber+"<input type='hidden' class='inputVal' value='"+item.seedId+"'></td>" +
								"<td onclick='tiaozhuanChuku(this)'>"+item.jiesuanType+"</td>" +
								"<td onclick='tiaozhuanChuku(this)'>出库业务</td>" +
								"<td onclick='tiaozhuanChuku(this)'>"+item.carryType+"</td>" +
								"<td onclick='tiaozhuanChuku(this)'>"+item.sijiName+"</td>" +
								"<td onclick='tiaozhuanChuku(this)'>"+item.goodsName+"</td>" +
								"<td onclick='tiaozhuanChuku(this)'>"+item.realityWeight+"</td>" +
								"<td onclick='tiaozhuanChuku(this)'>"+item.shouldCost+"</td>" +
								"<td onclick='tiaozhuanChuku(this)'>"+item.realityCost+"</td>" +										
							"</tr>"
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



//--------------------------当选择过户时调用此方法

function selectGuohu() {
		// 如果选择的是出库订单
		$.ajax({
			type : "post",			
			data : {			
				"pageNow" : pageNow// 传入当前页
			},
			url :"shiftStockSeed.do?flag=getGuojiesuanApp",
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
								"<tr style='cursor: pointer;'>" +
									"<td onclick='tiaozhuanGuoku(this)'>"+item.clientNumber+"<input type='hidden' class='inputVal' value='"+item.seedId+"'></td>" +
									"<td onclick='tiaozhuanGuoku(this)'>"+item.jiesuanType+"</td>" +
									"<td onclick='tiaozhuanGuoku(this)'>过户业务</td>" +
									"<td onclick='tiaozhuanGuoku(this)'></td>" +
									"<td onclick='tiaozhuanGuoku(this)'></td>" +
									"<td onclick='tiaozhuanGuoku(this)'>"+item.goodsName+"</td>" +									
									"<td onclick='tiaozhuanGuoku(this)'>"+item.realityWeight+"</td>" +
									"<td onclick='tiaozhuanGuoku(this)'>"+item.shouldCost+"</td>" +
									"<td onclick='tiaozhuanGuoku(this)'>"+item.realityCost+"</td>" +										
								"</tr>"
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
	var jiesuanType=$("#jiesuanType").val();
	// 如果选择的是出库订单
	$.ajax({
		type : "post",			
		data : {
			"startTime":startTime,
			"endTime":endTime,
			"kehuDanhao":kehuDanhao,
			"goodsName":goodsName,
			"pageNow" : pageNow,// 传入当前页
			"jiesuanType":jiesuanType,
		},
		url :"shiftStockSeed.do?flag=getGuoJiesuanTj",
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
							"<tr style='cursor: pointer;'>" +
								"<td onclick='tiaozhuanGuoku(this)'>"+item.clientNumber+"<input type='hidden' class='inputVal' value='"+item.seedId+"'></td>" +
								"<td onclick='tiaozhuanGuoku(this)'>"+item.jiesuanType+"</td>" +
								"<td onclick='tiaozhuanGuoku(this)'>过户业务</td>" +
								"<td onclick='tiaozhuanGuoku(this)'></td>" +
								"<td onclick='tiaozhuanGuoku(this)'></td>" +
								"<td onclick='tiaozhuanGuoku(this)'>"+item.goodsName+"</td>" +									
								"<td onclick='tiaozhuanGuoku(this)'>"+item.realityWeight+"</td>" +
								"<td onclick='tiaozhuanGuoku(this)'>"+item.shouldCost+"</td>" +
								"<td onclick='tiaozhuanGuoku(this)'>"+item.realityCost+"</td>" +										
							"</tr>"
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
			var jiesuanType=$("#jiesuanType").val();
			
			if(startTime=="" && endTime=="" 
				&& kehuDanhao=="" && goodsName=="" && jiesuanType==""){
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
			var jiesuanType=$("#jiesuanType").val();
			
			if(startTime=="" && endTime=="" 
				&& kehuDanhao=="" && goodsName=="" && jiesuanType==""){
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
			var jiesuanType=$("#jiesuanType").val();
			
			if(startTime=="" && endTime=="" 
				&& kehuDanhao=="" && goodsName=="" && jiesuanType==""){
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


