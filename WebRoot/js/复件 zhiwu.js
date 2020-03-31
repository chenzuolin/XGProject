$(function() {
	$("#addZhiwu").click(function() {
		// 在页面中引入all.js方法然后调用里面方法show()
		var $ok = show();
		if ($ok) {
			selectFunction();
		} else {
			alert("你的浏览器不支持ajax！");
		}
	});

	$(".aId").click(function() {

		var td1 = $(this).parents("td").siblings("td").eq(0).find(".id").val();
		var td2 = $(this).parents("td").siblings("td").eq(1).text();
		var td3 = $(this).parents("td").siblings("td").eq(2).text();

		$("#cdId").val(td1);
		$("#zhiwuname").val(td2);
		$("#textarea").val(td3);

		var $ok = show();
		if ($ok) {
			selectFunction2(td1);
		} else {
			alert("你的浏览器不支持ajax！");
		}

	});

});

function selectFunction() {
	$
			.ajax({// ajax提交方式
				type : "post",
				url : "functions.do?flag=goSelectFunctions",
				dataType : "json",
				success : function(functions) {
					var $dataObj = eval(functions);
					// $("#tdInfo select option").remove();//先清空，再添加;
					if ($dataObj.length > 0) {
						$("#ruBody").html("");// 先清空，再添加;
						// var $select=$("<select style='width:100px;'
						// name='classT' id='classInfo'></select>");
						var leibie = "";
						var x = 0;
						var y = 0;
						$
								.each(
										$dataObj,
										function(i, item) {
											var $tr = $("<tr class='ruTr'></tr>");
											if (i == 0) {
												leibie = item.leibei;
											}
											if (item.leibei == leibie) {
												if (x == 0) {
													$tr
															.append("<td rowspan='' style='background-color:red; color:yellow; font-weigth:bold'>"
																	+ item.leibei
																	+ "</td>");
													y++;
												} else {
													$tr
															.append("<td rowspan=''></td>");
												}
												$tr
														.append("<td><input type='checkbox' name='functionId' value='"
																+ item.id
																+ "' id='tdr"
																+ item.id
																+ "'/><input type='hidden'/>"
																+ "<label for='tdr"
																+ item.id
																+ "'>"
																+ item.name
																+ "</label>"
																+ "</td>");
												$tr.append("<td>" + item.beizhu
														+ "</td>");
												$tr.appendTo("#ruBody");
												x++;
											} else {
												leibie = item.leibei;
												$tr
														.append("<td rowspan='' style='background-color:red; color:yellow; font-weigth:bold'>"
																+ item.leibei
																+ "</td>");
												y++;
												$tr
														.append("<td><input type='checkbox' name='functionId' value='"
																+ item.id
																+ "'id='tdr"
																+ item.id
																+ "'/><input type='hidden'/>"
																+ "<label for='tdr"
																+ item.id
																+ "'>"
																+ item.name
																+ "</label>"
																+ "</td>");
												$tr.append("<td>" + item.beizhu
														+ "</td>");
												$tr.appendTo("#ruBody");
											}

										});

					}
				},
				error : function() {
					alert("数据错误！");
				}
			});

};

function selectFunction2(td1) {
	$
			.ajax({// ajax提交方式
				type : "post",
				url : "functions.do?flag=goSelectFunctions",
				dataType : "json",
				success : function(functions) {
					var $dataObj = eval(functions);
					// $("#tdInfo select option").remove();//先清空，再添加;
					if ($dataObj.length > 0) {
						$("#bianjiTbody").html("");// 先清空，再添加;
						// var $select=$("<select style='width:100px;'
						// name='classT' id='classInfo'></select>");
						var leibie = "";
						var x = 0;
						var y = 0;
						$
								.each(
										$dataObj,
										function(i, item) {
											var $tr = $("<tr class='bianjiTr'></tr>");
											if (i == 0) {
												leibie = item.leibei;
											}
											if (item.leibei == leibie) {
												if (x == 0) {
													$tr
															.append("<td rowspan='' style='background-color:red; color:yellow; font-weigth:bold'>"
																	+ item.leibei
																	+ "</td>");
													y++;
												} else {
													$tr
															.append("<td rowspan=''></td>");
												}
												$tr
														.append("<td><input type='checkbox' name='functionId' value='"
																+ item.id
																+ "' id='td"
																+ item.id
																+ "'/><input type='hidden'/>"
																+ "<label for='td"
																+ item.id
																+ "'>"
																+ item.name
																+ "</label>"
																+ "</td>");
												$tr.append("<td>" + item.beizhu
														+ "</td>");
												$tr.appendTo("#bianjiTbody");
												x++;
											} else {
												leibie = item.leibei;
												$tr
														.append("<td rowspan='' style='background-color:red; color:yellow; font-weigth:bold'>"
																+ item.leibei
																+ "</td>");
												y++;
												$tr
														.append("<td><input type='checkbox' name='functionId' value='"
																+ item.id
																+ "'id='td"
																+ item.id
																+ "'/><input type='hidden'/>"
																+ "<label for='td"
																+ item.id
																+ "'>"
																+ item.name
																+ "</label>"
																+ "</td>");
												$tr.append("<td>" + item.beizhu
														+ "</td>");
												$tr.appendTo("#bianjiTbody");
											}

										});

					}
					selectFunction3(td1);
				},
				error : function() {
					alert("数据错误！");
				}
			});

};

function selectFunction3(td1) {
	$.ajax({// ajax提交方式
		type : "post",
		url : "powers.do?flag=selectPowersAll",
		data : {
			"id" : td1
		},
		dataType : "json",
		success : function(data) {
			var $dataObj = eval(data);
			// $("#tdInfo select option").remove();//先清空，再添加;
			if ($dataObj.length > 0) {
				$.each($dataObj, function(i, item) {
					$("#bianjiTbody input[type='checkbox']").each(function() {
						// 比较，如果与数据库中数据相同就赋值为true
						if ($(this).val() == item.id) {
							$(this).attr("checked", true);
						}

					});

				});
			}
		},
		error : function() {
			alert("数据错误！");
		}
	});

};

