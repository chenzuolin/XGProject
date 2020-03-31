$(function(){
	$("#addGoods").click(function(){
		//在页面中引入all.js方法然后调用里面方法show()
		var $ok=show();
		if($ok){
			selectPinlei();	
			selectGuige();
			selectCaizhi();
			selectShuxin();
			selectChadi();
			selectJijian();
		}else{
			alert("你的浏览器不支持ajax！");
		}
	});
	
	
	$(".bianjiOk").click(function(){
		var td1 = $(this).parents("td").siblings("td").eq(0).find(".huoId").val();//id
        var td2 = $(this).parents("td").siblings("td").eq(1).text();//货物名称
        var td3 = $(this).parents("td").siblings("td").eq(2).text();//助记符
        var td4 = $(this).parents("td").siblings("td").eq(3).text();//货物品类
        var td5 = $(this).parents("td").siblings("td").eq(4).text();//规格
        var td6 = $(this).parents("td").siblings("td").eq(5).text();//材质
        var td7 = $(this).parents("td").siblings("td").eq(6).text();//属性
        var td8 = $(this).parents("td").siblings("td").eq(7).text();//产地
        var td9 = $(this).parents("td").siblings("td").eq(8).text();//计件单位

        $("#bianhao1").val(td1);
        $("#name1").val(td2);
        $("#zhujifu1").val(td3);
        /*$("#pinlei1").val(td4);
        $("#standard1").val(td5);
        $("#quality1").val(td6);
        $("#property1").val(td7);
        $("#chandi1").val(td8);
        $("#jjdw1").val(td9);*/
		
		var $ok=show();
		if($ok){
			selectPinlei2(td4);	
			selectGuige2(td5);
			selectCaizhi2(td6);
			selectShuxin2(td7);
			selectChadi2(td8);
			selectJijian2(td9);
		}else{
			alert("你的浏览器不支持ajax！");
		}
		
	});
	
});


function selectPinlei(){
	$.ajax({//ajax提交方式
		 type:"post",
		 url:"goods.do?flag=selectGoodsCategory",
		 dataType: "json",
		 success:function(category){
			 var $dataObj=eval(category);	
			 if($dataObj.length>0){
				 $("#pinlei option").remove();//先清空，再添加;					
				 var $select=$("#pinlei");
				 $.each($dataObj, function (i, item) { 
						$select.append("<option value='"+item.id+"'>"+item.name+"</option>");
						$select.appendTo("#pinleiDiv");
			     });  	
			  
			 }		 
		  },
		  error:function(){
			  alert("数据错误！");
		  }
	});

};


function selectJijian(){
	$.ajax({//ajax提交方式
		 type:"post",
		 url:"goods.do?flag=selectGoodsUnit",
		 dataType: "json",
		 success:function(unit){
			 var $dataObj=eval(unit);	
			 if($dataObj.length>0){
				 $("#jjdw option").remove();//先清空，再添加;					
				 var $select=$("#jjdw");
				 $.each($dataObj, function (i, item) { 
						$select.append("<option value='"+item.id+"'>"+item.name+"</option>");
						$select.appendTo("#jijianDiv");
			     });  	
			  
			 }		 
		  },
		  error:function(){
			  alert("数据错误！");
		  }
	});

};


function selectGuige(){
	$.ajax({//ajax提交方式
		 type:"post",
		 url:"goods.do?flag=selectGoodsStandard",
		 dataType: "json",
		 success:function(standard){
			 var $dataObj=eval(standard);	
			 if($dataObj.length>0){
				 
				 $("#standard option").remove();//先清空，再添加;					
				 var $select=$("#standard");
				 $.each($dataObj, function (i, item) { 
						$select.append("<option value='"+item.id+"'>"+item.name+"</option>");
						$select.appendTo("#guigeDiv");
			     });  	
				 $('#standard').selectize({
					    maxItems: 5
				});
			 }		 
		  },
		  error:function(){
			  alert("数据错误！");
		  }
	});

};

//产地
function selectCaizhi(){
	$.ajax({//ajax提交方式
		 type:"post",
		 url:"goods.do?flag=selectGoodsQuality",
		 dataType: "json",
		 success:function(quality){
			 var $dataObj=eval(quality);	
			 if($dataObj.length>0){
				 $("#quality option").remove();//先清空，再添加;					
				 var $select=$("#quality");
				 $.each($dataObj, function (i, item) { 
						$select.append("<option value='"+item.id+"'>"+item.name+"</option>");
						$select.appendTo("#caizhiDiv");
			     });  
				 $('#quality').selectize({
					 maxItems: 5
				 });
			  
			 }		 
		  },
		  error:function(){
			  alert("数据错误！");
		  }
	});

};

function selectShuxin(){
	$.ajax({//ajax提交方式
		 type:"post",
		 url:"goods.do?flag=selectGoodsProperty",
		 dataType: "json",
		 success:function(property){
			 var $dataObj=eval(property);	
			 if($dataObj.length>0){
				 $("#property option").remove();//先清空，再添加;					
				 var $select=$("#property");
				 $.each($dataObj, function (i, item) { 
						$select.append("<option value='"+item.id+"'>"+item.name+"</option>");
						$select.appendTo("#shuxinDiv");
			     });  
				 
				 $('#property').selectize({
					    maxItems: 5
				});
			  
			 }		 
		  },
		  error:function(){
			  alert("数据错误！");
		  }
	});

};

function selectChadi(){
	$.ajax({//ajax提交方式
		 type:"post",
		 url:"goods.do?flag=selectGoodsYieldly",
		 dataType: "json",
		 success:function(yieldly){
			 var $dataObj=eval(yieldly);	
			 if($dataObj.length>0){
				 $("#chandi option").remove();//先清空，再添加;					
				 var $select=$("#chandi");
				 $.each($dataObj, function (i, item) { 
						$select.append("<option value='"+item.id+"'>"+item.name+"</option>");
						$select.appendTo("#chandiDiv");
			     });  
				 $('#chandi').selectize({
					    maxItems: 5
				});

			 }		 
		  },
		  error:function(){
			  alert("数据错误！");
		  }
	});

};

//判断是否为空（添加货物）
function fun(){
	if($("#name").val()=="" || $("#name").val()==null){
		alert("货物名称不能为空！");
		return false;
	}
	if($("#zhujifu").val()=="" || $("#zhujifu").val()==null){
		alert("助记符不能为空！");
		return false;
	}
	if($("#pinlei").val()=="" || $("#pinlei").val()==null){
		alert("品类不能为空！");
		return false;
	}
	if($("#standard").val()=="" || $("#standard").val()==null){
		alert("规格不能为空！");
		return false;
	}
	if($("#quality").val()=="" || $("#quality").val()==null){
		alert("材质不能为空！");
		return false;
	}
	if($("#property").val()=="" || $("#property").val()==null){
		alert("属性不能为空！");
		return false;
	}
	if($("#chandi").val()=="" || $("#chandi").val()==null){
		alert("材质不能为空！");
		return false;
	}
	return true;
}


/*编辑货物*/

//品类
function selectPinlei2(pinglei){
	$.ajax({//ajax提交方式
		 type:"post",
		 url:"goods.do?flag=selectGoodsCategory",
		 dataType: "json",
		 success:function(category){
			 var $dataObj=eval(category);	
			 if($dataObj.length>0){
				 $("#pinlei1 option").remove();//先清空，再添加;					
				 var $select=$("#pinlei1");
				 $.each($dataObj, function (i, item) { 
						$select.append("<option value='"+item.id+"'>"+item.name+"</option>");
						$select.appendTo("#pinleiDiv1");
						//默认选中当前的库房
						if(item.name==pinglei){
					 		//alert(item.id);
					 		$("#pinlei1 option[value='"+item.id+"']").attr("selected",true);
					 		
					 	}
			     });  	
				
			 }		 
		  },
		  error:function(){
			  alert("数据错误！");
		  }
	});

};

//计件
function selectJijian2(jijian){
	$.ajax({//ajax提交方式
		 type:"post",
		 url:"goods.do?flag=selectGoodsUnit",
		 dataType: "json",
		 success:function(unit){
			 var $dataObj=eval(unit);	
			 if($dataObj.length>0){
				 $("#jjdw1 option").remove();//先清空，再添加;					
				 var $select=$("#jjdw1");
				 $.each($dataObj, function (i, item) { 
						$select.append("<option value='"+item.id+"'>"+item.name+"</option>");
						$select.appendTo("#jijianDiv1");
						
						//默认选中当前的库房
						if(item.name==jijian){
					 		//alert(item.id);
					 		$("#jjdw1 option[value='"+item.id+"']").attr("selected",true);
					 		
					 	}
			     });  	
			
			 }		 
		  },
		  error:function(){
			  alert("数据错误！");
		  }
	});

};

//规格
function selectGuige2(guige){
	$.ajax({//ajax提交方式
		 type:"post",
		 url:"goods.do?flag=selectGoodsStandard",
		 dataType: "json",
		 success:function(standard){
			 var $dataObj=eval(standard);	
			 if($dataObj.length>0){
				 
				 $("#standard1 option").remove();//先清空，再添加;					
				 var $select=$("#standard1");
				 $.each($dataObj, function (i, item) { 
						$select.append("<option value='"+item.id+"'>"+item.name+"</option>");
						$select.appendTo("#guigeDiv1");
						//默认选中当前的库房
						if(item.name==guige){
					 		//alert(item.id);
					 		$("#standard1 option[value='"+item.id+"']").attr("selected",true);
					 		
					 	}
			     });  	
				 $('#standard1').selectize();
			 }		 
		  },
		  error:function(){
			  alert("数据错误！");
		  }
	});

};

//材质
function selectCaizhi2(caizhi){
	$.ajax({//ajax提交方式
		 type:"post",
		 url:"goods.do?flag=selectGoodsQuality",
		 dataType: "json",
		 success:function(quality){
			 var $dataObj=eval(quality);	
			 if($dataObj.length>0){
				 $("#quality1 option").remove();//先清空，再添加;					
				 var $select=$("#quality1");
				 $.each($dataObj, function (i, item) { 
						$select.append("<option value='"+item.id+"'>"+item.name+"</option>");
						$select.appendTo("#caizhiDiv1");
						//默认选中当前的库房
						if(item.name==caizhi){
					 		//alert(item.id);
					 		$("#quality1 option[value='"+item.id+"']").attr("selected",true);
					 		
					 	}
			     });  
				 $('#quality1').selectize();
			  
			 }		 
		  },
		  error:function(){
			  alert("数据错误！");
		  }
	});

};

//属性
function selectShuxin2(shuxin){
	$.ajax({//ajax提交方式
		 type:"post",
		 url:"goods.do?flag=selectGoodsProperty",
		 dataType: "json",
		 success:function(property){
			 var $dataObj=eval(property);	
			 if($dataObj.length>0){
				 $("#property1 option").remove();//先清空，再添加;					
				 var $select=$("#property1");
				 $.each($dataObj, function (i, item) { 
						$select.append("<option value='"+item.id+"'>"+item.name+"</option>");
						$select.appendTo("#shuxinDiv1");
						//默认选中当前的库房
						if(item.name==shuxin){
					 		//alert(item.id);
					 		$("#property1 option[value='"+item.id+"']").attr("selected",true);
					 		
					 	}
			     });  
				 
				 $('#property1').selectize();
			  
			 }		 
		  },
		  error:function(){
			  alert("数据错误！");
		  }
	});

};

//产地
function selectChadi2(chandi){
	$.ajax({//ajax提交方式
		 type:"post",
		 url:"goods.do?flag=selectGoodsYieldly",
		 dataType: "json",
		 success:function(yieldly){
			 var $dataObj=eval(yieldly);	
			 if($dataObj.length>0){
				 $("#chandi1 option").remove();//先清空，再添加;					
				 var $select=$("#chandi1");
				 $.each($dataObj, function (i, item) { 
						$select.append("<option value='"+item.id+"'>"+item.name+"</option>");
						$select.appendTo("#chandiDiv1");
						//默认选中当前的库房
						if(item.name==chandi){
					 		//alert(item.id);
					 		$("#chandi1 option[value='"+item.id+"']").attr("selected",true);
					 		
					 	}
			     });  
				 $('#chandi1').selectize();

			 }		 
		  },
		  error:function(){
			  alert("数据错误！");
		  }
	});

};

//判断是否为空（添加货物）
function fun2(){
	if($("#name1").val()=="" || $("#name1").val()==null){
		alert("货物名称不能为空！");
		return false;
	}
	if($("#zhujifu1").val()=="" || $("#zhujifu1").val()==null){
		alert("助记符不能为空！");
		return false;
	}
	if($("#pinlei1").val()=="" || $("#pinlei1").val()==null){
		alert("品类不能为空！");
		return false;
	}
	if($("#standard1").val()=="" || $("#standard1").val()==null){
		alert("规格不能为空！");
		return false;
	}
	if($("#quality1").val()=="" || $("#quality1").val()==null){
		alert("材质不能为空！");
		return false;
	}
	if($("#property1").val()=="" || $("#property1").val()==null){
		alert("属性不能为空！");
		return false;
	}
	if($("#chandi1").val()=="" || $("#chandi1").val()==null){
		alert("材质不能为空！");
		return false;
	}
	return true;
}


