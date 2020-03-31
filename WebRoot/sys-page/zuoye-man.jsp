<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.xinggang.project.entity.InteriorUser"%>
<%@ page import="com.xinggang.project.entity.InteriorUserDuty"%>
<%@ page import="com.xinggang.project.entity.Powers"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	+ request.getServerName() + ":" + request.getServerPort()
	+ path + "/";
%>

<%
	if (request.getSession().getAttribute("loginName") == null
	|| request.getSession().getAttribute("iulist") == null
	|| request.getSession().getAttribute("power") == null) {
%>
<script type="text/javascript">
	$(function() {
		$("html").html("");
	});
</script>
<%
	}
%>
<%
	int x = 0;
	int bianji = 0;
	int tingyong = 0;
	int zengjia =0;
		
		List<Powers> power = (List<Powers>)request.getSession().getAttribute("power");
		for(int i=0; i<power.size(); i++){
	if(power.get(i).getFunctions()!=null && power.get(i).getFunctions().getFunctionName().equals("非系统操作员管理")){
		x++;
	}
	if(power.get(i).getFunctions()!=null && power.get(i).getFunctions().getFunctionName().equals("编辑非系统操作员")){
		bianji++;
	}
	if(power.get(i).getFunctions()!=null && power.get(i).getFunctions().getFunctionName().equals("停用非系统操作员")){
		tingyong++;
	}
	if(power.get(i).getFunctions()!=null && power.get(i).getFunctions().getFunctionName().equals("添加非系统操作员")){
		zengjia++;
	}
		}
%>
<!DOCTYPE html>
<html lang="en">

<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<!--让IE浏览器用最高级内核渲染页面 还有用 Chrome 框架的页面用webkit内核-->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!--为移动设备添加 viewport-->
<title>仓储管理系统登陆</title>
<meta name="description"
	content="甘肃鑫港物流有限公司仓储管理系统。快速、安全、便捷。致力于为广大商户提供快速、安全、便捷的仓储物流服务" />
<meta name="keywords"
	content="甘肃鑫港物流有限公司仓储管理系统。快速、安全、便捷。钢材、木材、百货、物流、汽车货运、铁路货运" />
<meta name="author" content="">
<meta name="format-detection" content="telephone=no" />
<!--禁止数字识自动别为电话号码-->
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--禁止百度转码-->
<link rel="shortcut icon" type="image/ico" href="/favicon.ico" />
<!--添加 favicon icon -->
<link rel="stylesheet" type="text/css" href="css/fenye.css" />
<link rel="stylesheet" href="css/bootstrap.css" />
<link rel="stylesheet" href="css/public.css" />
<script src="http://libs.baidu.com/jquery/2.1.1/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<style>
.page_show {
	-moz-user-select: none;
	-webkit-user-select: none;
	-ms-user-select: none;
	-khtml-user-select: none;
	user-select: none;
}
</style>
</head>
<%
	if(x==0){
%>
<script type="text/javascript">
	$(function() {
		$("body")
				.append(
						"<div style='width:100%; height:100%; background-color:#000000; position:absolute; left:0px; top:0px; z-index:1000'></div>");
	});
</script>
<%
	}
%>
<body onload="show('f')">
	<!-- 在ajax的权限显示中调用这些隐藏的文本域 -->
	<input type="hidden" value="<%=bianji%>" id="bianji" />
	<input type="hidden" value="<%=tingyong%>" id="tingyong" />
	<c:if test="${workingList==null }">
		<script>
			window.location.href = "${pageContext.request.contextPath}/working.do?flag=getWorkingAll";
		</script>
	</c:if>
	<!--内容起始处-->
	<div id="mainFrame" class="main">
		<div class="daohang">
			<ul>
				<i>当前位置：</i>
				<a style="cursor:pointer;">系统管理</a>
				<span>/</span>&nbsp;
				<a style="cursor:pointer;">非系统操作员管理</a>
				<span>/</span>&nbsp;
			</ul>
		</div>
		<div class="container">
			<ul class="nav nav-tabs nav-pills"
				style="margin: 10px 2.5%; font-weight: bold;">
				<li class="active"><a data-toggle="tab" href="#home"> <span
						class="glyphicon glyphicon-th-list" aria-hidden="true"
						style="margin-right: 8px;"></span>装卸工列表</a>
				</li>
				<li>
					<%
						if(zengjia!=0){
					%> <a data-toggle="tab" href="#menu1"> <span
						class="glyphicon glyphicon-plus" aria-hidden="true"
						style="margin-right: 8px;"></span>新增装卸工</a> <%
 	}
 %>
				</li>
				<!--  <li><a data-toggle="tab" href="#menu2">编辑装卸工</a></li> -->
			</ul>
			<div class="tab-content">
				<div id="home" class="tab-pane fade in active">
					<table class="table table-striped table-hover">
						<thead>
							<tr>
								<th>编号</th>
								<th>姓名</th>
								<th>联系电话</th>
								<th>工种</th>
								<th>备注</th>
								<th style="width: 100px;">编辑</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${workingList }" var="w">
								<tr>
									<td>${w.workingId }</td>
									<td>${w.workingName }</td>
									<td>${w.workingDefinedOne }</td>
									<td>${w.workingKindOfWork }</td>
									<td>${w.workingRemark }</td>
									<td>
										<%
											if(bianji!=0){
										%> <a class="btn btn-warning btn-xs bianji" data-toggle="tab"
										href="#menu2" onclick="contentshow(this)">编辑</a> <%
 	}
 %> <%
 	if(tingyong!=0){
 %> <a class="btn btn-danger btn-xs tingyong " data-toggle="modal"
										data-target="#disable" onclick="tingyong(this)">停用</a> <!-- 模态框（Modal） -->
										<%
											}
										%>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>

					<ul class="pager">
						<script>
							function show(str) {
								var now = $("#pageNow").val();
								now = parseInt(now);
								switch (str) {
								case 'jia':
									now = now + 1;
									break;
								case 'jian':
									now = now - 1;
									break;
								}
								$
										.ajax({
											type : "post",
											url : "working.do?flag=getWorkingAll&ff=ajax&pageNow="
													+ now,
											async : true,
											data : {
												"time" : new Date().getTime()
											},
											dataType : "json",
											success : function(obj) {
												if (obj != null) {
													var tab = $(".table tbody");
													tab.html("");
													for ( var i = 0; i < obj.length; i++) {
														tab
																.append("<tr><td>"
																		+ obj[i].id
																		+ "</td><td>"
																		+ obj[i].name
																		+ "</td><td>"
																		+ obj[i].tel
																		+ "</td><td>"
																		+ obj[i].gongzhong
																		+ "</td><td>"
																		+ obj[i].remark
																		+ "</td><td> </td></tr>");
														if ($("#bianji").val() != "0") {
															tab
																	.find("td")
																	.last()
																	.append(
																			"<a class='bianji btn btn-warning btn-xs' data-toggle='tab' href='#menu2'  onclick='contentshow(this)'>编辑</a>");
														}
														if ($("#tingyong")
																.val() != "0") {
															tab
																	.find("td")
																	.last()
																	.append(
																			"<a class='btn btn-danger btn-xs tingyong' data-toggle='modal' style='margin-left:5px;' data-target='#disable' onclick='tingyong(this)'>停用</a>");
														}
													}
													$("#pageNow").val(
															obj[0].pageNow);
													$("#yeshu").val(
															obj[0].pageNow);
												} else {
													alert("上传失败！");
												}
											},
											error : function() {
												document.location.href = "/XGProject/jsp/loginAll.jsp";
											}
										});
							}
						</script>
						<input type="hidden" id="pageNow" value="1" />
						<!-- <li><a onclick="show('jian')" style="cursor: pointer;">上一页</a>
						</li>
						<li><a onclick="show('jia')" style="cursor: pointer;">下一页</a>
						</li> -->
					</ul>
					<div class="page_show">
						<div class="shang">
							<span id="shouye">首页</span> <span id="shang">上一页</span>
						</div>
						<div class="pageNow">
							<input type="text" size="2" autocomplete="off" id="yeshu" /><span
								id="go">Go</span>
						</div>
						<div class="xia">
							<span id="xia">下一页</span> <span id="weiye">尾页</span>
						</div>
					</div>
				</div>
				<!-- 当点击停用的时候 -->
				<script>
					function tingyong(str) {
						$(function() {
							var td0 = $(str).parents("td").siblings("td").eq(0)
									.text();//编号
							$("#tingyongid").val(td0);

						});
					}
				</script>
				<div class="modal fade bs-example-modal-sm " id="disable"
					tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
					aria-hidden="true" style="margin-top:15%">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h4 class="modal-title" id="myModalLabel">停用装卸工</h4>
							</div>
							<div class="modal-body">
								<h4>确定需要停用此装卸工？</h4>
							</div>
							<div class="modal-footer">
								<form class="form-horizontal"
									action="${pageContext.request.contextPath}/working.do?flag=deleteWorking"
									method="post" name="workingForm">
									<input type="hidden" id="tingyongid" name="workingId" /> <a
										class="btn btn-default" onclick="closes()">关闭</a> <input
										type="submit" class="btn btn-primary" value="提交" />
								</form>
							</div>
						</div>
						<!-- /.modal-content -->
					</div>
					<!-- /.modal -->
				</div>
				<div id="menu2" class="tab-pane fade">
					<form class="form-horizontal"
						action="${pageContext.request.contextPath}/working.do?flag=updateWorking"
						method="post" name="workingForm" id="updateForm">
						<div class="modal-content" style="margin: auto 20%;">
							<div class="modal-header">
								<h4 class="modal-title" id="myModalLabel">编辑装卸工</h4>
							</div>
							<div class="modal-body">
								<div class="form-group">
									<input type="hidden" name="workingId" id="workingid" />
									<!-- 作业人员的编号。通过编号进行修改 -->
									<label for="name1" class="col-sm-3 control-label">姓名</label>
									<div class="col-sm-5">
										<input type="text" class="form-control" name="workingName"
											id="bh" />
									</div>
								</div>
								<div class="form-group">
									<label for="banzu1" class="col-sm-3 control-label">工种</label>
									<div class="col-sm-5">
										<select class="form-control" name="workingKindOfWork"
											id="fylx1">
											<option value="天车工">天车工</option>
											<option value="装卸工">装卸工</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label for="call-num1" class="col-sm-3 control-label">联系电话</label>
									<div class="col-sm-5">
										<input type="text" name="workingDefinedOne"
											class="form-control" id="fy1" />
									</div>
								</div>
								<div class="form-group">
									<label for="beizhu1" class="col-sm-3 control-label">备注</label>
									<div class="col-sm-5">
										<input type="text" name="workingRemark" class="form-control"
											id="beizhu1" />
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<a class="btn btn-default closeok" onclick="closes()">关闭</a> <input
									type="submit" class="btn btn-primary" value="提交"
									id="updatetijiao" />
							</div>
						</div>
						<!-- /.modal-content -->
					</form>
				</div>
				<script>
					$(function() {
						$("#updateForm").submit(function() {
							var name = $("#bh").val();
							var tel = $("#fy1").val();
							if (tel == "" || name == "") {
								alert("请填写有效数据！");
								return false;
							}
						});

						// 当点击上一页的时候，所有的保存当前页的文本框减一
						$("#shang").click(
								function() {
									$("#pageNow").val(
											parseInt($("#pageNow").val()) - 1);
									show('f');
								});

						// 当点击下一页的时候，所有的保存当前页的文本框加一
						$("#xia").click(
								function() {
									$("#pageNow").val(
											parseInt($("#pageNow").val()) + 1);
									show('f');
								});

						// 当点击首页的时候，返回到首页
						$("#shouye").click(function() {
							$("#pageNow").val("1");
							show('f');
						});

						// 跳转文本框中按下回车键的时候，直接跳转
						$("#yeshu").keydown(function(event) {
							if (event.keyCode == 13) {
								$("#go").click();
							}
						});
						// 当点击要跳转到某一页的时候
						$("#go").click(function() {
							var yan = /^[0-9]*$/;
							var page_now = $("#yeshu").val();
							if (yan.test(page_now) && page_now != "") {
								$("#pageNow").val(page_now);
								show('f');
							}
						});

						// 当点击到尾页的时候
						$("#weiye").click(function() {
							$("#pageNow").val("10000");
							show('f');
						});
					});
				</script>
				<div id="menu1" class="tab-pane fade">
					<form class="form-horizontal" role="form" id="addForm"
						action="${pageContext.request.contextPath}/working.do?flag=saveWorking"
						method="post">
						<div class="modal-content" style="margin: auto 20%;">
							<div class="modal-header">
								<h4 class="modal-title" id="myModalLabel">新增装卸工</h4>
							</div>
							<div class="modal-body">
								<div class="form-group">
									<label for="name" class="col-sm-3 control-label">姓名</label>
									<div class="col-sm-5">
										<input type="text" class="form-control" id="name"
											name="workingName" />
									</div>
								</div>
								<div class="form-group">
									<label for="banzu" class="col-sm-3 control-label">工种</label>
									<div class="col-sm-5">
										<select class="form-control" id="banzu"
											name="workingKindOfWork">
											<option value="天车工">天车工</option>
											<option value="装卸工">装卸工</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label for="call-num" class="col-sm-3 control-label">联系电话</label>
									<div class="col-sm-5">
										<input type="text" class="form-control" id="call-num"
											name="workingDefinedOne" />

									</div>
								</div>
								<div class="form-group">
									<label for="beizhu" class="col-sm-3 control-label">备注</label>
									<div class="col-sm-5">
										<input type="text" class="form-control" name="workingRemark" />
									</div>
								</div>

							</div>
							<div class="modal-footer">
								<a class="btn btn-default closeok" onclick="closes()">关闭</a> <input
									type="submit" class="btn btn-primary" value="提交" id="addtijiao" />
							</div>
						</div>
					</form>
					<!-- /.modal-content -->
				</div>

				<!--当增加工作人员的时候，判断填入有效的输入，不可为空-->
				<script>
					$(function() {
						$("#addForm").submit(function() {
							var name = $("#name").val();//取出姓名
							var tel = $("#call-num").val();//取出联系电话
							if (name == "" || tel == "") {
								alert("请填写有效数据！");
								return false;
							}
						});
					});
				</script>
			</div>
		</div>
	</div>
	<script>
		function closes() {
			window.location.href = "/XGProject/sys-page/zuoye-man.jsp";
		}
	</script>
	<script type="text/javascript">
		function contentshow(str) {
			$(function() {
				//document.location.href="${pageContext.request.contextPath}/goodsYieldly.do?flag=goupdategoodsYieldly&id="+$id;
				var td0 = $(str).parents("td").siblings("td").eq(0).text();//编号
				var td1 = $(str).parents("td").siblings("td").eq(1).text();//姓名
				var td2 = $(str).parents("td").siblings("td").eq(2).text();//联系电话
				var td3 = $(str).parents("td").siblings("td").eq(3).text();//工种
				$("#workingid").val(td0);
				$("#bh").val(td1);
				$("#fy1").val(td2);
				$("#fylx1").val(td3);
			});
		}
	</script>
</body>

</html>

