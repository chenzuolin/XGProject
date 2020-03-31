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
						$("#addxitong").html("");// 先清空，再添加;
						$("#addkehuguanli").html("");// 先清空，再添加;
						$("#addshujuzhongxin").html("");// 先清空，再添加;
						$("#addcaiwuguanli").html("");// 先清空，再添加;
						$("#addcangchuziliao").html("");// 先清空，再添加;
						$("#addcangchuyewu").html("");// 先清空，再添加;
						$("#addshouyequanxian").html("");// 先清空，再添加;
						// var $select=$("<select style='width:100px;'
						// name='classT' id='classInfo'></select>");
						var leibie = "";
						var x = 0;
						var y = 0;
						$
								.each(
										$dataObj,
										function(i, item) {
											// 系统管理开始
											if (item.leibei == "系统管理") {
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
													$tr.append("<td>"
															+ item.beizhu
															+ "</td>");
													$tr.appendTo("#addxitong");
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
													$tr.append("<td>"
															+ item.beizhu
															+ "</td>");
													$tr.appendTo("#addxitong");
												}
											}

											// 系统管理结束

											// 客户管理开始
											if (item.leibei == "客户管理") {
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
													$tr.append("<td>"
															+ item.beizhu
															+ "</td>");
													$tr
															.appendTo("#addkehuguanli");
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
													$tr.append("<td>"
															+ item.beizhu
															+ "</td>");
													$tr
															.appendTo("#addkehuguanli");
												}
											}

											// 客户管理结束

											// 数据中心开始
											if (item.leibei == "数据中心") {
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
													$tr.append("<td>"
															+ item.beizhu
															+ "</td>");
													$tr
															.appendTo("#addshujuzhongxin");
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
													$tr.append("<td>"
															+ item.beizhu
															+ "</td>");
													$tr
															.appendTo("#addshujuzhongxin");
												}
											}

											// 数据中心结束

											// 财务管理开始
											if (item.leibei == "财务管理") {
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
													$tr.append("<td>"
															+ item.beizhu
															+ "</td>");
													$tr
															.appendTo("#addcaiwuguanli");
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
													$tr.append("<td>"
															+ item.beizhu
															+ "</td>");
													$tr
															.appendTo("#addcaiwuguanli");
												}
											}

											// 财务管理结束

											// 仓储资料开始
											if (item.leibei == "仓储资料") {
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
													$tr.append("<td>"
															+ item.beizhu
															+ "</td>");
													$tr
															.appendTo("#addcangchuziliao");
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
													$tr.append("<td>"
															+ item.beizhu
															+ "</td>");
													$tr
															.appendTo("#addcangchuziliao");
												}
											}

											// 仓储资料结束

											// 仓储业务开始
											if (item.leibei == "仓储业务") {
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
													$tr.append("<td>"
															+ item.beizhu
															+ "</td>");
													$tr
															.appendTo("#addcangchuyewu");
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
													$tr.append("<td>"
															+ item.beizhu
															+ "</td>");
													$tr
															.appendTo("#addcangchuyewu");
												}
											}

											// 仓储业务结束

											// 首页权限开始
											if (item.leibei == "首页权限") {
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
													$tr.append("<td>"
															+ item.beizhu
															+ "</td>");
													$tr
															.appendTo("#addshouyequanxian");
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
													$tr.append("<td>"
															+ item.beizhu
															+ "</td>");
													$tr
															.appendTo("#addshouyequanxian");
												}
											}

											// 首页权限结束
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
						$("#xitong").html("");// 先清空，再添加;
						$("#kehuguanli").html("");// 先清空，再添加;
						$("#shujuzhongxin").html("");// 先清空，再添加;
						$("#caiwuguanli").html("");// 先清空，再添加;
						$("#cangchuziliao").html("");// 先清空，再添加;
						$("#cangchuyewu").html("");// 先清空，再添加;
						$("#shouyequanxian").html("");// 先清空，再添加;
						// var $select=$("<select style='width:100px;'
						// name='classT' id='classInfo'></select>");
						var leibie = "";
						var x = 0;
						var y = 0;
						$
								.each(
										$dataObj,
										function(i, item) {
											// 系统管理权限
											if (item.leibei == "系统管理") {
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
													$tr.append("<td>"
															+ item.beizhu
															+ "</td>");
													$tr.appendTo("#xitong");
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
													$tr.append("<td>"
															+ item.beizhu
															+ "</td>");
													$tr.appendTo("#xitong");
												}
											}
											// 系统管理结束

											// 客户管理开始
											if (item.leibei == "客户管理") {
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
													$tr.append("<td>"
															+ item.beizhu
															+ "</td>");
													$tr.appendTo("#kehuguanli");
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
													$tr.append("<td>"
															+ item.beizhu
															+ "</td>");
													$tr.appendTo("#kehuguanli");
												}
											}
											// 客户管理结束

											// 数据中心开始
											if (item.leibei == "数据中心") {
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
													$tr.append("<td>"
															+ item.beizhu
															+ "</td>");
													$tr
															.appendTo("#shujuzhongxin");
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
													$tr.append("<td>"
															+ item.beizhu
															+ "</td>");
													$tr
															.appendTo("#shujuzhongxin");
												}
											}
											// 数据中心结束

											// 财务管理开始
											if (item.leibei == "财务管理") {
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
													$tr.append("<td>"
															+ item.beizhu
															+ "</td>");
													$tr
															.appendTo("#caiwuguanli");
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
													$tr.append("<td>"
															+ item.beizhu
															+ "</td>");
													$tr
															.appendTo("#caiwuguanli");
												}
											}
											// 财务管理结束

											// 仓储资料开始
											if (item.leibei == "仓储资料") {
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
													$tr.append("<td>"
															+ item.beizhu
															+ "</td>");
													$tr
															.appendTo("#cangchuziliao");
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
													$tr.append("<td>"
															+ item.beizhu
															+ "</td>");
													$tr
															.appendTo("#cangchuziliao");
												}
											}
											// 仓储资料结束

											// 仓储业务开始
											if (item.leibei == "仓储业务") {
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
													$tr.append("<td>"
															+ item.beizhu
															+ "</td>");
													$tr
															.appendTo("#cangchuyewu");
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
													$tr.append("<td>"
															+ item.beizhu
															+ "</td>");
													$tr
															.appendTo("#cangchuyewu");
												}
											}
											// 仓储业务结束

											// 财务管理开始
											if (item.leibei == "首页权限") {
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
													$tr.append("<td>"
															+ item.beizhu
															+ "</td>");
													$tr
															.appendTo("#shouyequanxian");
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
													$tr.append("<td>"
															+ item.beizhu
															+ "</td>");
													$tr
															.appendTo("#shouyequanxian");
												}
											}
											// 首页权限结束

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

