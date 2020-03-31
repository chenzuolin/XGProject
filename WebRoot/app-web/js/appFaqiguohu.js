
function yanzhen(){
	var chehaoyan = /^[0-9]{5,10}$/;
	var reg=/^(汽运|火运)$/;
	var telyanzheng = /^1(3|4|5|7|8)\d{9}$/;
	/*判断正浮点数*/
	var fudian=/^(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$/;
	
	
	if($("#kehuDanhao").val()=="") {
		alert("客户单号不能为空！");
		return false;
	}
	if($("#zhuanDanwei").val() == "") {
		alert("单位不能为空！");
		return false;
	}
		
	if(!fudian.test($("#weights").val())) {
		alert("重量有误！");
		return false;
	}
	
	return true;
	
}




//查询货物品类
function selectGoodsPinlei(){
	$.ajax({//ajax提交方式
		 type:"post",
		 url:"goods.do?flag=selectGoodsAll",
		 dataType: "json",
		 success:function(Pinlei){			 				 
				 $(".PLpinlei").html("");//先清空，再添加;					
				 var $select=$(".PLpinlei");				 
				 var goodsPinlei=Pinlei;
				 
				 for(var i=0; i<Pinlei.length; i++){
					 for(var j=0; j<goodsPinlei.length; j++){
						 if(Pinlei[i].goodPinleiName==goodsPinlei[j].goodPinleiName && i!=j){
							 Pinlei[i].goodPinleiName="";
						 }
					 }
				 }				 
				 
				 $.each(Pinlei, function (i, item) { 
					 if(item.goodPinleiName!=""){
						 $select.append("<option value='"+item.goodPinleiId+"'>"+item.goodPinleiName+"</option>");
					 }
												
			     });  				 
				$("#pinlei").selectize();
			 //}		 
		  },
		  error:function(){
			  alert("品类数据错误！");
		  }
	});
	selectGoodsName();
	selectGoodsGuige();
	selectGoodsCaizhi();
	selectGoodsShuxin();
	selectGoodsChandi();
	
};



//查询货物
function selectGoodsName(){
	//selectGoodsGuige($id);
	$.ajax({//ajax提交方式
		 type:"post",
		 url:"goods.do?flag=selectGoodsAll",
		 dataType: "json",		 
		 success:function(goods){
			 var $dataObj=eval(goods);	
			 //if($dataObj.length>0){				 
				 $(".MCmingcheng").html("");//先清空，再添加;					
				 var $select=$(".MCmingcheng");				 
				 var mingcheng = $dataObj;
				 
				 for(var i=0; i<mingcheng.length; i++){
					 for(var j=0; j<goods.length; j++){
						 if(mingcheng[i].goodName==goods[j].goodName && i!=j){
							 mingcheng[i].goodName="";
						 }
					 }
				 }
				 
				 $.each(mingcheng, function (i, item) { 
					 if(item.goodName!=""){
							$select.append("<option value='"+item.goodName+"'>"+item.goodName+"</option>");					
					 }						
											
			     });				
				$('#goods-name').selectize();			
						
			 //}		 
		  },
		  error:function(){
			  alert("货物数据错误！");
		  }
			
	});
	

};

function selectGoodsGuige(){
	
	$.ajax({//ajax提交方式
		 type:"post",
		 url:"goods.do?flag=selectGoodsAll",
		 dataType: "json",
		 success:function(guige){			 			 
			 $(".GGguige").html("");//先清空，再添加;					
			 var $select=$(".GGguige");	
			 
			 var ggName = guige;				 
			 for(var i=0; i<ggName.length; i++){
				 for(var j=0; j<guige.length; j++){
					 if(ggName[i].goodGuigeName==guige[j].goodGuigeName && i!=j){
						ggName[i].goodGuigeName="";
					 }
				 }
			 }			 
			 $.each(ggName, function (i, item) { 
				 if(item.goodGuigeName!=""){
					 $select.append("<option value='"+item.goodGuigeId+"'>"+item.goodGuigeName+"</option>");
				 }																	
		     });  	
			 $('#Standard').selectize();					 
			
			 //}		 
		  },
		  error:function(){
			  alert("规格数据错误！");
		  }
	});
	
		
};


function selectGoodsCaizhi(){		
	$.ajax({//ajax提交方式
		 type:"post",
		 url:"goods.do?flag=selectGoodsAll",
		 dataType: "json",
		 success:function(caizhi){			 			 
			 $(".CZcaizhi").html("");//先清空，再添加;					
			 var $select=$(".CZcaizhi");
			 
			 var ggName = caizhi;				 
			 for(var i=0; i<ggName.length; i++){
				 for(var j=0; j<caizhi.length; j++){
					 if(ggName[i].goodCaizhiName==caizhi[j].goodCaizhiName && i!=j){
						ggName[i].goodCaizhiName="";
					 }
				 }
			 }
							 			
			 $.each(ggName, function (i,item) { 	
				 if(item.goodCaizhiName!=""){
					 $select.append("<option value='"+item.goodCaizhiId+"'>"+item.goodCaizhiName+"</option>");	
				 }					 									
		     }); 
			 $('#caizhi').selectize();
				
			// }		 
		  },
		  error:function(){
			  alert("材质数据错误！");
		  }
		
	});
			
};



function selectGoodsShuxin(){	

	$.ajax({//ajax提交方式
		 type:"post",
		 url:"goods.do?flag=selectGoodsAll",
		 dataType: "json",
		 success:function(shuxin){			 
			 
			 $(".SXshuxing").html("");//先清空，再添加;					
			 var $select=$(".SXshuxing");	
			 
			 var ggName = shuxin;				 
			 for(var i=0; i<ggName.length; i++){
				 for(var j=0; j<shuxin.length; j++){
					 if(ggName[i].goodShuxinName==shuxin[j].goodShuxinName && i!=j){
						 ggName[i].goodShuxinName="";
					 }
				 }
			 }
			 			 
			 $.each(ggName, function (i,item) { 
				 if(item.goodShuxinName!=""){
					 $select.append("<option value='"+item.goodShuxinId+"'>"+item.goodShuxinName+"</option>");
				 }
																			
		     });  
			 $("#shuxing").selectize();
		
				 
			 //}		 
		  },
		  error:function(){
			  alert("属性数据错误！");
		  }
	});
	
};

function selectGoodsChandi(){	
	
	$.ajax({//ajax提交方式
		 type:"post",
		 url:"goods.do?flag=selectGoodsAll",
		 dataType: "json",
		 success:function(chandi){			 
		 
			 	$(".CDchandi").html("");//先清空，再添加;					
				var $select=$(".CDchandi");	
				
				var ggName = chandi;				 
				 for(var i=0; i<ggName.length; i++){
					 for(var j=0; j<chandi.length; j++){
						 if(ggName[i].goodChandiName==chandi[j].goodChandiName && i!=j){
							ggName[i].goodChandiName="";
						 }
					 }
				 }
				
				 $.each(ggName, function (i,item) {
					 if(item.goodChandiName!=""){
						 $select.append("<option value='"+item.goodChandiId+"'>"+item.goodChandiName+"</option>");	
					 }
																
			     }); 
				 $('#chandi').selectize();
		 
		  },
		  error:function(){
			  alert("数据错误！");
		  }
	});
	

};






