$(function(){
	$("#addHouse").click(function(){
		//在页面中引入all.js方法然后调用里面方法show()
		var $ok=show();
		if($ok){
			selectKuwei();			
		}else{
			alert("你的浏览器不支持ajax！");
		}
	});
	
	$(".aId").click(function(){
		alert();
		var td1 = $(this).parents("td").siblings("td").eq(0).find(".id").val();
		var td2 = $(this).parents("td").siblings("td").eq(1).text();
		var td3 = $(this).parents("td").siblings("td").eq(2).text();
		var td4 = $(this).parents("td").siblings("td").eq(3).text();
		var $td5 = $(this).parents("td").siblings("td").eq(4).text();
		var td6 = $(this).parents("td").siblings("td").eq(5).text();
		
		$("#cdId").val(td1);
		$("#kuweimingcheng1").val(td2);
		$("#rongliang1").val(td3);
		$("#jianshu1").val(td4);
		$("#beizhu1").val(td6);
		
		var $ok=show();
		if($ok){
			selectKuwei2($td5);			
		}else{
			alert("你的浏览器不支持ajax！");
		}
		
	});
	
});


function selectKuwei(){
	$.ajax({//ajax提交方式
		 type:"post",
		 url:"tarehouse.do?flag=goSaveTarehouse",
		 dataType: "json",
		 success:function(house){
			 var $dataObj=eval(house);	
			 //$("#tdInfo select option").remove();//先清空，再添加;
			 if($dataObj.length>0){
				 $("#kuweiweizhi option").remove();//先清空，再添加;					
				 //var $select=$("<select style='width:100px;' name='classT' id='classInfo'></select>");
				 var $select=$("#kuweiweizhi");
				 $.each($dataObj, function (i, item) { 
			            //alert(item.id + ","  + item.classTName); 		            
						$select.append("<option value='"+item.id+"'>"+item.bursaryName+"</option>");
						$select.appendTo("#list");
			     });  	
			  
			 }		 
		  },
		  error:function(){
			  alert("数据错误！");
		  }
	});

};


function selectKuwei2($h5){

	$.ajax({//ajax提交方式
		 type:"post",
		 url:"tarehouse.do?flag=goSaveTarehouse",
		 dataType: "json",
		 success:function(house){
			 var $dataObj=eval(house);	
			 //$("#tdInfo select option").remove();//先清空，再添加;
			 if($dataObj.length>0){
				 $("#kuweiweizhi1 option").remove();//先清空，再添加;					
				 //var $select=$("<select style='width:100px;' name='classT' id='classInfo'></select>");
				 var $select=$("#kuweiweizhi1");
				 
				 $.each($dataObj, function (i, item) { 
			            //alert(item.id + ","  + item.classTName); 
					 	//alert(item.bursaryName+","+$h5+","+item.id);					 	
				 		$select.append("<option value='"+item.id+"'>"+item.bursaryName+"</option>");
						$select.appendTo("#listBianji");
						//默认选中当前的库房
						if(item.bursaryName==$h5){
					 	
					 		$("#kuweiweizhi1 option[value='"+item.id+"']").attr("selected",true);
					 		
					 	}
			     });  	
			  
			 }		 
		  },
		  error:function(){
			  alert("数据错误！");
		  }
	});

};


