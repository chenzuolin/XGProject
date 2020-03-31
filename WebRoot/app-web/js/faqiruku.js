$(function() {
	
	/*发起出库的运输方式判断，显示不同的样式*/
	$("#yunshu").change(function() {
		var x = $(this).val();
		if(x == "汽运") {
			$("#huoyun").css("display", "none");
			$("#qiyun").css("display", "block");
		} else {
			$("#huoyun").css("display", "block");
			$("#qiyun").css("display", "none");
		}
	});

	/*当车号的文本框失去焦点的时候进行判断*/
	$("#chehaos").blur(function() {
		//验证车牌号
		var chehaoyan = /^[0-9]{5,10}$/;
		if($(this).val() == "" || !chehaoyan.test($(this).val())) {
			$(this).siblings("img").attr("src", "cangchu/img/err.png");
		}
	});
	/*当车号的文本框得到的焦点的时候*/
	$("#chehaos").focus(function() {
		$(this).siblings("img").attr("src", "cangchu/img/success.png");
	});	

	//车号的样式显示
	$("#gan").hover(function() {
		$(".chehao_ul1").css("display", "block");
	}, function() {
		$(".chehao_ul1").hover(function() {
			$(".chehao_ul1").css("display", "block");

			$(".chehao_ul1 li").mouseover(function() {
				$(".chehao_ul1 li").
				css({
					"background-color": "#0066FF",
					"color": "#ffffff"
				});
				$(this).css({
					"background-color": "#FFF",
					"color": "#000000"
				});
				var text = $(this).text();
				$(this).click(function() {
					$("#gan").text(text);
				});
			});
		}, function() {
			$(".chehao_ul1").css("display", "none");
		});

	});
	$("#gan").mouseout(function() {
		$(".chehao_ul1").css("display", "none");
	});

	$("#zimu").hover(function() {
		$(".chehao_ul2").css("display", "block");
	}, function() {
		$(".chehao_ul2").hover(function() {
			$(".chehao_ul2").css("display", "block");

			$(".chehao_ul2 li").mouseover(function() {
				$(".chehao_ul2 li").
				css({
					"background-color": "#0066FF",
					"color": "#ffffff"
				});
				$(this).css({
					"background-color": "#FFF",
					"color": "#000000"
				});
				var text = $(this).text();
				$(this).click(function() {
					$("#zimu").text(text);
				});
			});
		}, function() {
			$(".chehao_ul2").css("display", "none");
		});

	});
	$("#zimu").mouseout(function() {
		$(".chehao_ul2").css("display", "none");
	});

	/*车号失去焦点，汽运*/
	$("#chehao").blur(function() {
		//验证车牌号
		var chehaoyan = /^[0-9a-zA-Z]{5}$/;
		if($(this).val() == "" || !chehaoyan.test($(this).val())) {
			$(this).siblings("img").attr("src", "cangchu/img/err.png");
		}
	});
	$("#chehao").focus(function() {
		$(this).siblings("img").attr("src", "cangchu/img/success.png");
	});

	//判断司机姓名是否为空
	$("#siji").blur(function() {
		if($(this).val() == "") {
			$(this).siblings("img").attr("src", "cangchu/img/err.png");
		}
	});
	$("#siji").focus(function() {
		$(this).siblings("img").attr("src", "cangchu/img/success.png");
	});

	//当电话失去焦点-->
	$("#sijitel").blur(function() {
		//验证电话
		var telyanzheng = /^1(3|4|5|7|8)\d{9}$/;
		if($(this).val() == "" || !telyanzheng.test($(this).val())) {
			$(this).siblings("img").attr("src", "cangchu/img/err.png");
		}
	});
	$("#sijitel").focus(function() {
		$(this).siblings("img").attr("src", "cangchu/img/success.png");
	});

	//入库单号的焦点判断-->
	$("#rukudanhao").blur(function() {
		if($(this).val() == "") {
			$(this).siblings("img").attr("src", "cangchu/img/err.png");
		}
	});
	$("#rukudanhao").focus(function() {
		$(this).siblings("img").attr("src", "cangchu/img/success.png");
	});

	//判断入库的填写的数字-->
	$("#rukuzhongliang").blur(function() {
		var zhongyan = /^\d+(\.\d+)?$|^$|^(\d+|\-){7,}$/;
		if($(this).val() == "" || !zhongyan.test($(this).val())) {
			$(this).siblings("img").attr("src", "cangchu/img/err.png");
		}
	});
	$("#rukuzhongliang").focus(function() {
		$(this).siblings("img").attr("src", "cangchu/img/success.png");
	});

	//最后一次验证
	$("#tijiao").click(function() {
		//删除已经具有的打开样式
		if($("#yunshu").val() == "汽运") {
			//将车号的选择付给隐藏的文本框中
			$("#chehao_hide").val($("#gan").text() +
				$("#zimu").text() + $("#chehao").val());
			var chehao = $("#chehao").val();
		} else {
			$("#chehao_hide").val($("#huo").text() +
				$("#chehaos").val());
			var chehao = $("#chehaos").val();
		}

		$("#chehao_fu").text($("#chehao_hide").val());
		//运输方式
		var yunshu = $("#yunshu").val();
		$("#yunshu_fu").text(yunshu);
		//司机
		var siji = $("#siji").val();
		$("#siji_fu").text(siji);
		//司机电话
		var sijitel = $("#sijitel").val();
		$("#sijitel_fu").text(sijitel);
		//入库单号
		var rukudanhao = $("#rukudanhao").val();
		$("#rukudanhao_fu").text(rukudanhao);

		var telyanzheng = /^1(3|4|5|7|8)\d{9}$/;

		if($("#yunshu").val() == "汽运") {
			//验证车牌号
			var chehaoyan = /^[0-9A-Z]{5}$/;
		} else {
			//验证车牌号
			var chehaoyan = /^[0-9]{5,10}$/;
		}

		//验证车号
		if(chehao == "" || !chehaoyan.test(chehao)) {
			$("#chehao").siblings("img").attr("src", "cangchu/img/err.png");
			alert("车号不能为空且请填写有效的车号");
			return;
		}
		//验证司机姓名
		if(siji == "") {
			$("#siji").siblings("img").attr("src", "cangchu/img/err.png");
			alert("司机姓名不能为空");
			return;
		}

		//验证司机电话
		if(sijitel == "" || !telyanzheng.test(sijitel)) {
			$("#sijitel").siblings("img").attr("src", "cangchu/img/err.png");
			alert("司机电话不能为空且请填写有效电话");
			return;
		}

		//验证出库单号
		if(rukudanhao == "") {
			$("#rukudanhao").siblings("img").attr("src", "cangchu/img/err.png");
			alert("提货单号不能为空");
			return;
		}

		//验证选择的单位
		var danwei = $("#danwei").val();
		if(danwei == "") {
			alert("请选择出货单位！");
			return false;
		}
		//货物
		$("#tan_bottom table tr").not(":first").html("");
		for(var i = 0; i < $("#tab_context .tab_bottom").length; i++) {
			
			//货物品类
			var pindex = $("#tab_context .tab_bottom").eq(i).find("#pinlei option:selected").index();
			var pinlei = $("#tab_context .tab_bottom").eq(i).find("#pinlei option").eq(pindex).text();
			//名称
			var mindex = $("#tab_context .tab_bottom").eq(i).find("#mingcheng option:selected").index();
			var mingcheng = $("#tab_context .tab_bottom").eq(i).find("#mingcheng option").eq(mindex).text();
			//规格
			var gindex = $("#tab_context .tab_bottom").eq(i).find("#guige option:selected").index();
			var guige = $("#tab_context .tab_bottom").eq(i).find("#guige option").eq(gindex).text();
			//材质
			var cindex = $("#tab_context .tab_bottom").eq(i).find("#caizhi option:selected").index();
			var caizhi = $("#tab_context .tab_bottom").eq(i).find("#caizhi option").eq(cindex).text();
			//属性
			var sindex = $("#tab_context .tab_bottom").eq(i).find("#shuxing option:selected").index();
			var shuxing = $("#tab_context .tab_bottom").eq(i).find("#shuxing option").eq(sindex).text();
			//产地
			var chindex = $("#tab_context .tab_bottom").eq(i).find("#chandi option:selected").index();
			var chandi = $("#tab_context .tab_bottom").eq(i).find("#chandi option").eq(chindex).text();
			//重量
			var zhongliang = $("#tab_context .tab_bottom").eq(i).find("#rukuzhongliang").val();
			//重量验证正则表达式
			var zhongyan = /^\d+(\.\d+)?$|^$|^(\d+|\-){7,}$/;

			//验证重量
			if(zhongliang == "" || !zhongyan.test(zhongliang)) {
				$("#tab_context .tab_bottom").eq(i).find("#rukuzhongliang").siblings("img").attr("src", "cangchu/img/err.png");
				alert("请填写货物重量");
				return;
				break;
			}
			if(pinlei == "" || mingcheng == "" || guige == "" || caizhi == "" || shuxing == "" || chandi == "" || zhongliang == "" || !zhongyan.test(zhongliang)) {
				alert("请将信息填写完整，带红星的为必填项，手机号为13,14,15,17,19开头");
				return;
				break;
			}

			$("#tan_bottom table").append("<tr style='background-color:#00FF7F; text-align:center;'><td ><strong>货物 " + (i + 1) + "</strong></td ><td><strong>" + pinlei + "</strong></td><td><strong>" + mingcheng + "</strong></td><td><strong>" + guige + "</strong></td><td><strong>" + caizhi + " </strong></td><td><strong>" + shuxing + "</strong></td><td><strong>" + chandi + " </strong><td><strong>" + zhongliang + "吨 </strong></td></tr>");
		}

		//确认提交
		$(".modeal_bg").animate({
			"opacity": 0.5
		}).css("display", "block");
		$(".modeal_bottom").animate({
			"opacity": 1,
			"top": "25%"
		}).css("display", "block");
	});

	//当点击添加货物的时候，复制窗口-->
	var i = 1;
	$("#xinzeng").click(function() {
		i++;
		$("#tab_context").append($("#tab_context .tab_bottom").first().clone(true));
		$("#tab_context .tab_bottom").last().find("#goods").text("货物" + i);
		
	});
	$("#delete").click(function() {
		if($("#tab_context .tab_bottom").length > 1) {
			if(confirm("确定要删除吗？")) {
				$(this).parents(".tab_bottom").remove();
			}
		}
	});

	//验证完成点击提交
	$("#queding").click(function() {
		alert("确定提交");
		$(".modeal_bottom").css("display", "none");
		$(".modeal_bg").css("display", "none");
		$("#rukuform").submit();
	});

	$("#delete").hover(function() {
		$(this).children("img").attr("src", "cangchu/img/delete_one.png");
	}, function() {
		$(this).children("img").attr("src", "cangchu/img/delete_two.png");
	});

	//拖动模态框
	var $div = $(".modeal_bottom_top"); /* 绑定鼠标左键按住事件 */
	$div.bind("mousedown", function(event) { /* 获取需要拖动节点的坐标 */
		var offset_x = $("#modeal")[0].offsetLeft; //x坐标
		var offset_y = $("#modeal")[0].offsetTop; //y坐标         /* 获取当前鼠标的坐标 */        
		var mouse_x = event.pageX;
		var mouse_y = event.pageY; /* 绑定拖动事件 */ /* 由于拖动时，可能鼠标会移出元素，所以应该使用全局（document）元素 */
		$(document).bind("mousemove", function(ev) {
			/* 计算鼠标移动了的位置 */
			var _x = ev.pageX - mouse_x;
			var _y = ev.pageY - mouse_y; /* 设置移动后的元素坐标 */
			var now_x = (offset_x + _x) + "px";
			var now_y = (offset_y + _y) + "px"; /* 改变目标元素的位置 */
			$("#modeal").css({
				top: now_y,
				left: now_x
			});
		}); /* 当鼠标左键松开，接触事件绑定 */
		$(document).bind("mouseup", function() {
			$(this).unbind("mousemove");
		});

	});

	//当点击关闭按钮的时候，将模态框关闭

	$("#close").click(function() {
		$(".modeal_bottom").css("display", "none");
		$(".modeal_bg").css("display", "none");
	});
	$("#closes").click(function() {
		$(".modeal_bottom").css("display", "none");
		$(".modeal_bg").css("display", "none");

	});

	$(".chaofa label:first").css({
		"color": "#000000"
	});
	$(".qiyun span:last").css({
		"margin-left": "5px"
	});

	$(".queren a:last").css({
		"float": "right",
		"width": "110px",
		"height": "30px",
		"line-height": "30px",
		"background": "url(cangchu/img/zengjiahuowu.png)",
		"color": "#FFFFFF",
		"text-align": "center",
		"font-weight": "bold"
	});
	$(".queren a:last").hover(function() {
		$(this).css({
			"background": "url(cangchu/img/zengjiahuowu2.png)"
		});
	}, function() {
		$(this).css({
			"background": "url(cangchu/img/zengjiahuowu.png)"
		});
	});
	$(".queren a i").hover(function() {
		$(this).css({
			"background": "url(cangchu/img/zengjiahuowu2.png)"
		});
	}, function() {
		$(this).css({
			"background": "url(cangchu/img/zengjiahuowu.png)"
		});
	});
});


//查询货物品类
function selectGoodsPinlei(){
	$.ajax({//ajax提交方式
		 type:"post",
		 url:"input.do?flag=selectGoodsPinlei",
		 dataType: "json",
		 success:function(Pinlei){			 
			 var $dataObj=eval(Pinlei);	
			 //if($dataObj.length>0){				 
				 $(".PLpinlei").html("");//先清空，再添加;					
				 var $select=$(".PLpinlei");
				 $.each($dataObj, function (i, item) { 
						$select.append("<option value='"+item.id+"'>"+item.categoryName+"</option>");						
			     });  				 
				 selectGoodsName($(".PLpinlei"));
			 //}		 
		  },
		  error:function(){
			  alert("品类数据错误！");
		  }
	});
	
};



//查询货物

function selectGoodsName(geThis){
	//selectGoodsGuige($id);	
	var $id=$(geThis).val();
	$.ajax({//ajax提交方式
		 type:"post",
		 url:"input.do?flag=xuanGoodsPinlei",
		 data:{"id":$id},
		 dataType: "json",
		 success:function(goods){
			 var $dataObj=eval(goods);	
			 //if($dataObj.length>0){				 
				 $(geThis).parents(".tab_bottom").find(".MCmingcheng option").remove();//先清空，再添加;					
				 var $select= $(geThis).parents(".tab_bottom").find(".MCmingcheng");				 
				 var mingcheng = $dataObj;
				 
				 for(var i=0; i<mingcheng.length; i++){
					 for(var j=0; j<goods.length; j++){
						 if(mingcheng[i].goodsName==goods[j].goodsName && i!=j){
							 mingcheng[i].goodsName="";
						 }
					 }
				 }
				 
				 $.each(mingcheng, function (i, item) { 
					if(item.goodsName!=""){
						$select.append("<option value='"+item.goodsName+"'>"+item.goodsName+"</option>");
					
					}
			     });  
				 selectGoodsGuige($(geThis).parents(".tab_bottom").find(".MCmingcheng"));
		
			 //}		 
		  },
		  error:function(){
			  alert("货物数据错误！");
		  }
	});
	
	
};

function selectGoodsGuige(hwThis){
	if($(hwThis).val()==null || $(hwThis).val()==""){
		 $(hwThis).parents(".tab_bottom").find(".GGguige option").remove();//先清空，再添加;					
	     var $select=$(hwThis).parents(".tab_bottom").find(".GGguige");	
		 $select.append("<option value=''></option>");
		 selectGoodsCaizhi($(hwThis).parents(".tab_bottom").find(".GGguige"));
	}else{		
		var $name=$(hwThis).val();
		$.ajax({//ajax提交方式
			 type:"post",
			 url:"input.do?flag=xuanGoodsName",
			 data:{"name":$name},
			 dataType: "json",
			 success:function(guige){			 
				 var $dataObj=eval(guige);	
				 //if($dataObj.length>0){				 
				 $(hwThis).parents(".tab_bottom").find(".GGguige option").remove();//先清空，再添加;					
					 var $select=$(hwThis).parents(".tab_bottom").find(".GGguige");				 
					 var ggName = $dataObj;				 
					 for(var i=0; i<ggName.length; i++){
						 for(var j=0; j<guige.length; j++){
							 if(ggName[i].guigeName==guige[j].guigeName && i!=j){
								ggName[i].guigeName="";
							 }
						 }
					 }
					 
					 $.each(ggName, function (i, item) { 
						 if(item.guigeName!=""){
							 $select.append("<option value='"+item.id+"'>"+item.guigeName+"</option>");
						 }											
				     });  	
					 selectGoodsCaizhi($(hwThis).parents(".tab_bottom").find(".GGguige"));
				 //}		 
			  },
			  error:function(){
				  alert("规格数据错误！");
			  }
		});
	}
		
};


function selectGoodsCaizhi(ggThis){	
	if($(ggThis).val()==""){
		$(ggThis).parents(".tab_bottom").find(".CZcaizhi option").remove();//先清空，再添加;					
		var $select=$(ggThis).parents(".tab_bottom").find(".CZcaizhi");
		$select.append("<option value=''></option>");
		selectGoodsShuxin($(ggThis).parents(".tab_bottom").find(".CZcaizhi"));
	}else{
		var $id=$(ggThis).val();
		$.ajax({//ajax提交方式
			 type:"post",
			 url:"input.do?flag=xuanGuige",
			 data:{"id":$id},
			 dataType: "json",
			 success:function(caizhi){			 
				 //var $dataObj=eval(guige);		 
				 //if(caizhi.length>0){				 
				 $(ggThis).parents(".tab_bottom").find(".CZcaizhi option").remove();//先清空，再添加;					
				 var $select=$(ggThis).parents(".tab_bottom").find(".CZcaizhi");					 
					 var ggName = caizhi;				 
					 for(var i=0; i<ggName.length; i++){
						 for(var j=0; j<caizhi.length; j++){
							 if(ggName[i].caizhiName==caizhi[j].caizhiName && i!=j){
								ggName[i].caizhiName="";
							 }
						 }
					 }
					 
					 $.each(ggName, function (i,item) { 
						 if(item.caizhiName!=""){
							 $select.append("<option value='"+item.id+"'>"+item.caizhiName+"</option>");	
						 }
											
				     }); 
					 selectGoodsShuxin($(ggThis).parents(".tab_bottom").find(".CZcaizhi"));
			
				// }		 
			  },
			  error:function(){
				  alert("材质数据错误！");
			  }
			
		});
	}		
};

function selectGoodsShuxin(czThis){	
	if($(czThis).val()==""){
		$(czThis).parents(".tab_bottom").find(".SXshuxing option").remove();//先清空，再添加;					
		var $select=$(czThis).parents(".tab_bottom").find(".SXshuxing");
		$select.append("<option value=''></option>");
		selectGoodsChandi($(czThis).parents(".tab_bottom").find(".SXshuxing"));
	}else{
		var $id=$(czThis).val();	
		$.ajax({//ajax提交方式
			 type:"post",
			 url:"input.do?flag=xuanCaizhi",
			 data:{"id":$id},
			 dataType: "json",
			 success:function(shuxin){			 
				 //var $dataObj=eval(guige);		 
				 //if(shuxin.length>0){				 
				 $(czThis).parents(".tab_bottom").find(".SXshuxing option").remove();//先清空，再添加;					
				 var $select=$(czThis).parents(".tab_bottom").find(".SXshuxing");				 
				 var ggName = shuxin;				 
				 for(var i=0; i<ggName.length; i++){
					 for(var j=0; j<shuxin.length; j++){
						 if(ggName[i].shuxinName==shuxin[j].shuxinName && i!=j){
							ggName[i].shuxinName="";
						 }
					 }
				 }				 
				 $.each(ggName, function (i,item) { 
					 if(item.shuxinName!=""){
						 $select.append("<option value='"+item.id+"'>"+item.shuxinName+"</option>");	
					 }
										
			     });  
				 selectGoodsChandi($(czThis).parents(".tab_bottom").find(".SXshuxing"));
					 
				 //}		 
			  },
			  error:function(){
				  alert("属性数据错误！");
			  }
		});
	}
};

function selectGoodsChandi(sxThis){	
	if($(sxThis).val()==""){
		$(sxThis).parents(".tab_bottom").find(".CDchandi option").remove();//先清空，再添加;					
		var $select=$(sxThis).parents(".tab_bottom").find(".CDchandi");	
		$select.append("<option value=''></option>");
	}else{
		var $id=$(sxThis).val();	
		$.ajax({//ajax提交方式
			 type:"post",
			 url:"input.do?flag=xuanShuxin",
			 data:{"id":$id},
			 dataType: "json",
			 success:function(chandi){			 
				 //var $dataObj=eval(guige);		 
				 //if(chandi.length>0){				 
				 	$(sxThis).parents(".tab_bottom").find(".CDchandi option").remove();//先清空，再添加;					
					var $select=$(sxThis).parents(".tab_bottom").find(".CDchandi");				 
					var ggName = chandi;				 
					 for(var i=0; i<ggName.length; i++){
						 for(var j=0; j<chandi.length; j++){
							 if(ggName[i].chandiName==chandi[j].chandiName && i!=j){
								ggName[i].chandiName="";
							 }
						 }
					 }
					 
					 $.each(chandi, function (i,item) { 
						 if(item.chandiName!=""){
							 $select.append("<option value='"+item.id+"'>"+item.chandiName+"</option>");	
						 }											
				     });  	
			
				 //}		 
			  },
			  error:function(){
				  alert("数据错误！");
			  }
		});
	}

};







