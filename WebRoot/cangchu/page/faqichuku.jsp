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
<meta charset="UTF-8">
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
<link rel="stylesheet" href="css/selectize.default.css">
<script type="text/javascript" src="cangchu/js/jquery1.9.0.min.js"></script>
<script type="text/javascript" src="cangchu/js/faqichuku.js"></script>
<!--引入自写的css样式-->
<link rel="stylesheet" type="text/css" href="cangchu/css/faqichuku.css" />
<script src="js/selectize.js"></script>
<style type="text/css">
#xinzeng {
	background: url(cangchu/img/zengjiahuowu.png);
}

#xinzeng:hover {
	background: url(cangchu/img/zengjiahuowu2.png);
}
</style>
</head>
<!-- selectize.js 单选插件 -->

<body onload="load()" ondragstart="return false">

	<!--内容起始处-->
	<div id="mainFrame" class="main">
		<div class="daohang">
			<ul>
				<i>当前位置：</i>
				<a style="cursor:pointer;">仓储业务</a>
				<span>></span>&nbsp;
				<a style="cursor:pointer;">新增出库</a>
				<span>/</span>&nbsp;
			</ul>
		</div>
		<script>
			$('#zhuanchu').selectize();
		</script>
		<div class="chuku_context">
			<strong class="yunshu_xinxi">运 输 信 息</strong>

			<!--form开始-->
			<form
				action="${pageContext.request.contextPath }/export.do?flag=FaQiChuKu"
				method="post" name="exportForm" id="chukuform">
				<div class="kehu_xuanze">
					<!--如果是客户发起入库，那么将此div隐藏-->
					<strong style="float:left">客户名称：</strong>
					<div style="float:left;">
						<select id="zhuanchu" onchange="zhuanchudiaoyong()"
							style="width:250px;" class="demo-default" name="client">
						</select>
					</div>
				</div>
				<!--table开始-->
				<table class="tab_head">
					<tr>
						<td align="right" width="100px">运输方式：</td>
						<td align="left" width="130px">
							<div class="yunshu_fangshi">
								<select id="yunshu" name="exportCarryType">
									<option value="汽运">汽运</option>
									<option value="火运">火运</option>
								</select> <label>*</label> <img src="cangchu/img/success.png" width="15" />
							</div></td>
						<td align="right" width="80px">车号：</td>

						<td class="chehao">
							<!--
                                        	作者：2586190195@qq.com
                                        	时间：2017-06-06
                                        	描述：判断选择的运输方式，显示不同的样式
                                        --> <!--当选择的运输方式是火运的时候显示-->
							<div id="huoyun" class="huoyun">
								<span id="huo">火</span>
								<p>
									<input id="chehaos" type="text"
										onKeyDown="this.value=this.value.replace(/[^0-9]/,'');"
										maxlength="10" /> <label>*</label> <img
										src="cangchu/img/success.png" width="15" />
								</p>
								<!--当车号文本框失去焦点-->
								<!--隐藏的车号文本框+++++++++++++++++++++++++++++++++++++++++++++++++++++++-->
							</div> <!--当选择的运输方式是汽运的时候显示-->
							<div id="qiyun" class="qiyun">
								<span id="gan">甘</span>
								<ul class="chehao_ul1">
									<div>
										<li>甘</li>
										<li>新</li>
										<li>宁</li>
										<li>青</li>
										<li>陕</li>
										<li>冀</li>
										<li>豫</li>
										<li>鲁</li>
										<li>辽</li>
										<li>黑</li>
										<li>京</li>
										<li>津</li>
										<li>吉</li>
										<li>晋</li>
										<li>蒙</li>
										<li>云</li>
										<li>贵</li>
										<li>渝</li>
										<li>川</li>
										<li>赣</li>
										<li>湘</li>
										<li>闵</li>
										<li>浙</li>
										<li>沪</li>
										<li>苏</li>
										<li>皖</li>
										<li>鄂</li>
									</div>
								</ul>
								<span id="zimu">&nbsp;A&nbsp;</span>
								<ul class="chehao_ul2">
									<div>
										<li>&nbsp;A&nbsp;</li>
										<li>&nbsp;B&nbsp;</li>
										<li>&nbsp;C&nbsp;</li>
										<li>&nbsp;D&nbsp;</li>
										<li>&nbsp;E&nbsp;</li>
										<li>&nbsp;F&nbsp;</li>
										<li>&nbsp;G&nbsp;</li>
										<li>&nbsp;H&nbsp;</li>
										<li>&nbsp;I&nbsp;</li>
										<li>&nbsp;J&nbsp;</li>
										<li>&nbsp;K&nbsp;</li>
										<li>&nbsp;L&nbsp;</li>
										<li>&nbsp;M&nbsp;</li>
										<li>&nbsp;N&nbsp;</li>
										<li>&nbsp;P&nbsp;</li>
										<li>&nbsp;Q&nbsp;</li>
										<li>&nbsp;R&nbsp;</li>
										<li>&nbsp;S&nbsp;</li>
										<li>&nbsp;T&nbsp;</li>
										<li>&nbsp;U&nbsp;</li>
										<li>&nbsp;V&nbsp;</li>
										<li>&nbsp;W&nbsp;</li>
										<li>&nbsp;X&nbsp;</li>
										<li>&nbsp;Y&nbsp;</li>
										<li>&nbsp;Z&nbsp;</li>
									</div>
								</ul>
								<p>
									<input id="chehao" type="text"
										onKeyDown="this.value=this.value.replace(/[^0-9A-Z]/,'');"
										maxlength="5" /> <label>*</label> <img
										src="cangchu/img/success.png" width="15" />
								</p>
								<!--当车号文本框失去焦点-->
								<!--隐藏的车号文本框+++++++++++++++++++++++++++++++++++++++++++++++++++++++-->
							</div> <input type="hidden" id="chehao_hide" name="exportWagonNumber" />
						</td>
						<td align="right" width="80px">司机姓名：</td>
						<td>
							<div class="sijiname">
								<input type="text" id="siji" name="exportDriverName" /> <label>*</label>
								<img src="cangchu/img/success.png" width="15" />
							</div></td>
						<!--++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++未做完-->
						<td>
							<div class="chaofa">
								<input type="checkbox" checked="checked" id="chaofa"
									name="exportDefinedOne" value="接受超发" /> <label for="chaofa">接受超发</label>
								<label>超发重量在0.5吨以内</label>
							</div></td>

					</tr>
					<tr>
						<td align="right" width="100px">司机电话：</td>
						<td>
							<div class="sijitel">
								<input type="text" id="sijitel" name="exportDriverTel" /> <label>*</label>
								<img src="cangchu/img/success.png" width="15" />
							</div></td>

						<td align="right" width="80px">客户单号：</td>
						<td>
							<div class="tihuo">
								<input type="text" id="rukudanhao" name="exportClientNumber" />
								<label>*</label> <img src="cangchu/img/success.png" width="15" />
							</div></td>

						<td colspan="2"><div class="youxiao">
								订单有效期：<input type="text" value="2" name="exportDefinedTwo" /> 天
							</div></td>
					</tr>
				</table>

				<!--tabhead结束-->

				<!--货物信息开始-->

				<div id="tab_context" class="tab_context">
					<strong>请 选 择 出 库 货 物</strong> <br />
					<table class="tab_bottom">
						<tr>
							<td colspan="9"><span id="goods">货物1</span>
							</td>
						</tr>
						<tr>
							<td align="right">货物品类：</td>
							<td>
								<div>
									<select id="pinlei" class="pl" onchange="QueryMingCheng(this)">
										<option>无</option>
									</select> <label>*</label> <img src="cangchu/img/success.png "
										width="15" />
								</div></td>
							<td align="right">货物名称：</td>
							<td>
								<div>
									<select id="mingcheng" class="mc" onchange="QueryGuiGe(this)">
										<option>无</option>
									</select> <label>*</label> <img src="cangchu/img/success.png" width="15" />
								</div></td>
							<td align="right">货物规格：</td>
							<td>
								<div>
									<select id="guige" class="gg" onchange="QueryCaiZhi(this)">
										<option>无</option>
									</select> <label>*</label> <img src="cangchu/img/success.png" width="15" />
								</div></td>
							<td align="right">货物材质：</td>
							<td>
								<div>
									<select id="caizhi" class="cz" onchange="QueryShuXing(this)">
										<option>无</option>
									</select> <label>*</label> <img src="cangchu/img/success.png" width="15" />
								</div></td>
							<td rowspan="2" align="left" width="50px"><a
								style="cursor:pointer;" id="delete"> <img
									src="cangchu/img/delete_two.png" title="删除" width="25" /> </a></td>
						</tr>
						<tr>
							<td align="right">货物属性：</td>
							<td>
								<div>
									<select id="shuxing" class="sx" onchange="QueryChanDi(this)">
										<option>无</option>
									</select> <label>*</label> <img src="cangchu/img/success.png" width="15" />
								</div></td>
							<td align="right">货物产地：</td>
							<td>
								<div class="chandis">
									<select id="chandi" class="cd" name="goodsId">
										<option>无</option>
									</select> <label>*</label> <img src="cangchu/img/success.png" width="15" />
									<div class='cc'></div>
								</div></td>
							<td align="right">重量：</td>
							<td>
								<div>
									<input type="text" id="rukuzhongliang" name="goodsWeight" /> <label>*</label>
									<img src="cangchu/img/success.png" width="15" />
								</div></td>
							<td align="right">备注：</td>
							<td><input type="text" name="remark" />
							</td>
						</tr>
					</table>
				</div>
				<!--tab_context结束-->
				<div class="queren">
					<%
						if(request.getSession().getAttribute("duoxuan")!=null && request.getSession().getAttribute("duoxuan").equals("duoxuanhuowu")){
					%>
					<a style="cursor:pointer;" id="xinzeng"><i>增加货物</i> </a>
					<%
						}
					%>
					<a style="cursor:pointer;" id="tijiao">提 交</a>
				</div>
			</form>
		</div>
		<!--出库内容结束-->
	</div>
	<!--页面起始处结束-->
	<!--当点击提交入库订单的时候触发-->
	<!--在向服务器提交数据的时候要讲隐藏域进行设置name，并且重新设置与form相同的name-->
	<div class="modeal_bottom" id="modeal">
		<div>
			<div>
				<div class="modeal_bottom_top">
					<h3>再次确认出库订单</h3>
					<button id="close">X</button>
				</div>
				<div class="modeal_bottom_middle">
					<p>
					<table class="tab_tan" width="100%">
						<tr>
							<td align="right">运输方式：</td>
							<td><strong id="yunshu_fu">火运</strong></td>
							<td align="right">车号：</td>
							<td><strong id="chehao_fu">甘A12345</strong></td>
							<td align="right">司机姓名：</td>
							<td><strong id="siji_fu">王麻子</strong></td>
							<td align="right">司机电话：</td>
							<td><strong id="sijitel_fu">18215190013</strong></td>
							<td align="right">提货单号：</td>
							<td><strong id="rukudanhao_fu">18215190013</strong></td>
						</tr>
					</table>
					<div id="tan_bottom">
						<table border="1" width="100%">
							<tr>
								<th>货物序号</th>
								<th>货物品类</th>
								<th>货物名称</th>
								<th>货物规格</th>
								<th>货物材质</th>
								<th>货物属性</th>
								<th>货物产地</th>
								<th>货物重量</th>
							</tr>
						</table>
					</div>
					</p>
				</div>
				<div class="modeal_bottom_bottom
							">
					<a id="queding">确定提交</a>
					<button id="closes">关闭</button>
				</div>
			</div>
		</div>

	</div>
	<div class="modeal_bg"></div>
	<%
		if (request.getSession().getAttribute("loginName") == null
		|| request.getSession().getAttribute("iulist") == null
		|| request.getSession().getAttribute("power") == null) {
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
	<%
		int x = 0;
		int chaofa = 0;
			
			List<Powers> power = (List<Powers>)request.getSession().getAttribute("power");
			for(int i=0; i<power.size(); i++){
		if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("新增出库")){
			x++;
		}
		if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("无库存超发")){
			chaofa++;
		}
			}
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
	<input type="hidden" value="<%=chaofa%>" id="wukucun_chaofa" />
</body>

</html>

