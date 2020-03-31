
$(function(){
	$("#selChange").change(function(){
		var $ok=show();
		if($ok){
			chooseClassT();			
		}
	});
	$("#updatOK").click(function(){
		var $ok=show();
		if($ok){
			updatBut();
		}
	});
});

//判断浏览器是否支持ajax
function show(){
	xmlhttp=null;
	//ie7,Firefox,google
	if (window.XMLHttpRequest)
	  {// code for IE7, Firefox,google, Opera, etc.
	  xmlhttp=new XMLHttpRequest();
	  }
	//IE6, IE5
	else if (window.ActiveXObject)
	  {// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	//如果浏览器支持
	if (xmlhttp!=null)
	 {		
		//调用函数
		return true;
	  }
	else
	  {
	  alert("你的浏览器不支持ajax!");
	  	return false;
	  }
	 
}

function chooseClassT(){
	chooseDuty();
	var $a=$("#selChange").val();	
	$.ajax({//ajax提交方式
		 type:"post",
		 url:"interiorUser.do?flag=addInteriorUser",		 
		 data:{id:$a},
		 success:function(classT){
			 var $dataObj=eval(classT);	
			 //$("#tdInfo select option").remove();//先清空，再添加;
			 if($dataObj.length>0){
				 $("#tdInfoClass select").remove();//先清空，再添加;					
				 var $select=$("<select style='width:100px;' name='classT' id='classInfo'></select>");
				 $.each($dataObj, function (i, item) { 
			            //alert(item.id + ","  + item.classTName); 		            
						$select.append("<option value="+item.id+">"+item.classTName+"</option> ");
						$select.appendTo("#tdInfoClass");
			     });  	
			  
			 }else{
			    $("#tdInfoClass select").remove();//先清空，再添加;
				var $select=$("<select style='width:100px;' name='classT' id='classInfo'></select>");	            
				$select.append("<option>无班组</option> ");
				$select.appendTo("#tdInfoClass");			     	
			 } 			 
		  },
		  error:function(){
		   alert("数据错误！");
		  }
	});

}


function chooseDuty(){
	var $a=$("#selChange").val();
	$.ajax({//ajax提交方式
		 type:"post",
		 url:"interiorUser.do?flag=addInteriorUser2",		 
		 data:{id:$a},
		 success:function(interiorUserDuty){
			 var $dataObj=eval(interiorUserDuty);	
			 //$("#tdInfo select option").remove();//先清空，再添加;
			 if($dataObj.length>0){
				 $("#tdInfoDuty select").remove();//先清空，再添加;					
				 var $select=$("<select style='width:100px;' name='interiorUserDuty'></select>");
				 $.each($dataObj, function (i, item) { 
			        		            
						$select.append("<option value="+item.InteriorUserDutyId+">"+item.InteriorUserDutyName+"</option> ");
						$select.appendTo("#tdInfoDuty");
			     });  	
			  
			 }else{
			    $("#tdInfoDuty select").remove();//先清空，再添加;
				var $select=$("<select style='width:100px;' name='interiorUserDuty'></select>");	            
				$select.append("<option>无职责</option> ");
				$select.appendTo("#tdInfoDuty");			     	
			 } 			 
		  },
		  error:function(){
		   alert("失败！");
		  }
	});

}


function updatBut(){
	$.ajax({//ajax提交方式
		 type:"post",
		 url:"interiorUser.do?flag=updateinteriorUser",
		 data:$("#updateForm").serialize(),
		 dataType:"html",
		 success:function(data){
			 alert("修改成功！");		 		 
		  },
		  error:function(){
		   alert("失败！");
		  }
	});

}
