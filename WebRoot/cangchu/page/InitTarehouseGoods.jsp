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

<!--引入自写的css样式-->
<link rel="stylesheet" type="text/css" href="cangchu/css/faqichuku.css" />
<link rel="stylesheet" href="cangchu/css/selectize.default.css">
<link type="text/css" href="cangchu/css/public.css" />
<link rel="stylesheet" href="cangchu/css/selectize.css">

<script src="http://libs.baidu.com/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript" src="cangchu/js/all.js"></script>
<script type="text/javascript" src="cangchu/js/faqiruku.js"></script>
<script type="text/javascript" src="cangchu/js/selectize.js"></script>
<script type="text/javascript" src="cangchu/js/bootstrap.min.js"></script>

<style type="text/css">
.form-group {
	margin-top: 20px;
}

.selectize-control.demo-default.single {
	height: 34px;
	width: 300px;
}

.input-group>.input-group-addon {
	background: #337AB7;
	color: #fff;
}
</style>

</head>
<script>
	selectkw();
	// 查询所有的库位
	function selectkw() {
		$.ajax({// ajax提交方式
			type : "post",
			url : "tarehouse.do?flag=selectAjaxKuwei",
			dataType : "json",
			success : function(kuwei) {
				var $dataObj = eval(kuwei);
				if ($dataObj.length > 0) {
					$("#kuwei").html("");// 先清空，再添加;
					var $select = $("#kuwei");
					$.each($dataObj, function(i, item) {
						$select.append("<option value='" + item.id + "'>"
								+ item.name + "</option>");
					});
					$('#kuwei').selectize();
				}
			},
			error : function() {
				alert("请求错误！");
				//document.location.href = "/XGProject/cangchu/page/login.jsp";
			}
		});

	};
</script>
<body onload="QueryKeHu();">

	<!--内容起始处-->
	<div id="mainFrame" class="main">
		<div class="daohang">
			<ul>
				<i>当前位置：</i>
				<a style="cursor:pointer;">系统管理</a>
				<span>></span>&nbsp;
				<a href="javascript:document.location.reload();">初始化库位库存</a>
				<span>/</span>&nbsp;
			</ul>
		</div>

		<div class="chuku_context">
			<!--form开始-->
			<form name="initClientGoodsForm"
				action="${pageContext.request.contextPath}/initClientGoodsAction.do?flag=saveInitTarehouseGoods"
				method="post" id="rukuform">

				<div class="kehu_xuanze">
					<div class="input-group">
						<strong style="float:left">选择库位：</strong>
						<div style="float:left;">
							<select id="kuwei" class="demo-default" name="tarehouse">

							</select>
						</div>
					</div>
				</div>

				<!--货物信息开始-->
				<div id="tab_context" class="tab_context">
					<strong>请 选 择 货 物</strong> <br />
					<table class="tab_bottom" style="width:90%">
						<thead>
							<tr>
								<th>品类</th>
								<th>名称</th>
								<th>规格</th>
								<th>材质</th>
								<th>属性</th>
								<th>产地</th>
								<th>重量</th>
								<th>件数</th>
								<th>操作</th>
							</tr>
						</thead>
						<tr>
							<td align="center">
								<div>
									<select id="pinlei" class="pl" onchange="QueryMingCheng(this)">
									</select>
								</div></td>
							<td align="center">
								<div>
									<select id="mingcheng" class="mc" onchange="QueryGuiGe(this)">
									</select>
								</div></td>
							<td align="center">
								<div>
									<select id="guige" class="gg" onchange="QueryCaiZhi(this)">
									</select>
								</div></td>
							<td align="center">
								<div>
									<select id="caizhi" class="cz" onchange="QueryShuXing(this)">
									</select>
								</div></td>
							<td align="center">
								<div>
									<select id="shuxing" class="sx" onchange="QueryChanDi(this)">
									</select>
								</div></td>
							<td align="center">
								<div class="chandis">
									<select id="chandi" class="cd" name="goodss">
									</select>
								</div></td>
							<td align="center">
								<div>
									<input type="text" id="rukuzhongliang"
										name="iseedShouldWeights" />
								</div>
							</td>
							<td align="center">
								<div>
									<input type="text" id="jianshu" name="number" />
								</div>
							</td>
							<td align="center" width="50px"><a style="cursor:pointer;"
								id="delete"> <img src="cangchu/img/delete_two.png"
									title="删除" width="25" /> </a></td>
						</tr>
					</table>
				</div>
				<!--tab_context结束-->
				<div class="queren">
					<a id="xinzeng" style="cursor:pointer;"><i
						style="background: url(cangchu/img/zengjiahuowu.png);">增加货物</i> </a> <a
						id="inittiijao" style="cursor:pointer;">提 交</a>
				</div>
			</form>
		</div>
		<!--出库内容结束-->
	</div>
	<!--页面起始处结束-->
</body>
<script>
	$(function() {
		$("#inittiijao")
				.click(
						function() {
							var len = $(".tab_bottom").length;//获得添加货物的长度
							var zhongyan = /^\d+(\.\d+)?$|^$|^(\d+|\-){7,}$/;
							//进行验证是否选择了重复的货物，验证时填写的重量、件数是否合法
							for ( var i = 0; i < len; i++) {
								for ( var j = 0; j < len; j++) {
									if ($(".tab_bottom").eq(i).find("#chandi")
											.val() == $(".tab_bottom").eq(j)
											.find("#chandi").val() && i!=j) {
										alert("货物的选择不可以重复，请重新选择！");
										break;
										return false;
									}
								}

								if ($(".tab_bottom").eq(i).find(
										"#rukuzhongliang").val() == ""
										|| zhongyan.test($(".tab_bottom").eq(i)
												.find("#rukuzhongliang").val()) == false) {
									alert("重量输入不合法，请重新输入！");
									return false;
								}
								if ($(".tab_bottom").eq(i).find("#jianshu")
										.val() == ""
										|| zhongyan.test($(".tab_bottom").eq(i)
												.find("#jianshu").val()) == false) {
									alert("件数输入不合法，请重新输入！");
									return false;
								}
							}
							if (confirm("确定提交吗？")) {
								$("#rukuform").submit();
							}
						});
	});
</script>
</html>