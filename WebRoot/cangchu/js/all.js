
//被所有页面调用，判断浏览器是否支持ajax
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
