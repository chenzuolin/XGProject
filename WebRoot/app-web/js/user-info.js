
$(function(){
	$("#bianjiOK").click(
		
		function fun(){
			//邮箱验证
			$reg_mail = /^\w+@\w+(\.[a-zA-Z]{2,3}){1,2}$/;
			//手机号验证
			$reg_phone = /^1\d{10}$/;

		
			       
			if($("#address").val()==""){
				alert("地址不能为空");
				return false;
			}
			if($("#abbreviation").val()==""){
				alert("简称中不能为空！");
				return false;
			}
			if($("#sign").val()==""){
				alert("助记符不能为空！");
				return false;
			}
			if(!$reg_mail.test($("#email").val()))
			{
				alert("邮箱输入有误");
				return false;
			}
			if(!$reg_phone.test($("#phone").val()))
			{
				alert("联系电话输入有误，必须是11位");
				return false;
			}   
			return true;
		}
	);
});

 
