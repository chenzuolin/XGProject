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
<html>
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<!--让IE浏览器用最高级内核渲染页面 还有用 Chrome 框架的页面用webkit内核-->
<!-- <meta name="viewport" content="width=device-width, initial-scale=1.0"> -->
<title>仓储管理系统</title>
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
<script src="cangchu/js/jquery1.9.0.min.js"></script>
<script src="cangchu/js/admin.js">
	
</script>
<link rel="stylesheet" href="cangchu/css/admin.css" />
<!-- <meta name="viewport"
	content="minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" /> -->
</head>
<%
	if (request.getSession().getAttribute("loginName") == null
	|| request.getSession().getAttribute("iulist") == null
	|| request.getSession().getAttribute("power") == null) {
%>
<script type="text/javascript">
	document.location.href = "/XGProject/cangchu/page/login.jsp";
	return null;
</script>
<%
	}
%>
<%
	int daichuli = 0;
	int caozuodingdan = 0;
	int guobang = 0;
	int shenhe = 0;
	int pankushenhe = 0;
	int shoufei= 0;
	int xinzengchuku = 0;
	int xinzengruku = 0;
	int xinzengguohu = 0;
	int xinzengpanku = 0;
	int xinzengnuoku = 0;
	int kufangguanli = 0;
	int kuweiguanli = 0;
	int huowuleixing = 0;
	int huowumingcheng = 0;
	int huowuguige = 0;
	int huowucaizhi = 0;
	int huowuchandi = 0;
	int huowushuxing = 0;
	int jijiandanwei = 0;
	int feiyongleixing = 0;
	int zhifufangshi = 0;
	int piaojuleixing = 0;
	int zhinajin = 0;
	int dingdanchaxun = 0;
	int kucunchaxun = 0;
	int kehukucunchaxun = 0;
	int nuokuchaxun = 0;
	int pankuchaxun = 0;
	int shoufacun = 0;
	int kehuxinxi = 0;
	int zhucekehu = 0;
	int xitongrizhi = 0;
	int bumenguanli = 0;
	int banzuguanli = 0;
	int feixitongcaozuoyuan = 0;
	int xitongcaozuoyuan = 0;
	int juesequanxian = 0;
	int huowupicibiao = 0;
	int kuweikucunchaxun = 0;
	int zuoyeliangtongji = 0;
	int feiyongjiesuan = 0;
	int yuejieshoufei = 0;
	int jiedong = 0;
	int updateshenpi = 0;
	int xiazhanfeijilu = 0;
	int rijiecost = 0;
	int peisong = 0;
	int jinrigangjia = 0;
	int pandiankehukucun = 0;
	int duandao = 0;
	int duandaochaxun = 0;
	String zhiwu ="";
		
		List<Powers> power = (List<Powers>)request.getSession().getAttribute("power");
		InteriorUser iu = (InteriorUser)request.getSession().getAttribute("iulist");
		if(iu==null){
			zhiwu ="无";
		}else{
			zhiwu = iu.getInteriorUserDuty()==null?"无":iu.getInteriorUserDuty().getInteriorUserDutyName();
		}
		if(power==null){
	response.sendRedirect("/XGProject/cangchu/page/login.jsp");			
		}
		if(power!=null){
		
		for(int i=0; i<power.size(); i++){
		//查询待处理订单的权限
		if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("待处理订单")){
	daichuli++;
		}
		//查询订单操作的权限
		if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("订单操作")){
	caozuodingdan++;
		}
		//查询订单过磅的权限
		if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("订单过磅")){
	guobang++;
		}
		//查询订单审核的权限
		if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("订单审核")){
	shenhe++;
		}
		//查询盘库订单审核的权限
		if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("盘库审核")){
	pankushenhe++;
		}
		//查询订单收费的权限
		if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("订单收费")){
	shoufei++;
		}
		//查询新增出库的权限
		if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("新增出库")){
	xinzengchuku++;
		}
		//查询新增入库的权限
		if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("新增入库")){
	xinzengruku++;
		}
		//查询新增过户的权限
		if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("新增过户")){
	xinzengguohu++;
		}
		//查询新增盘库的权限
		if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("新增盘库")){
	xinzengpanku++;
		}
		//查询新增挪库的权限
		if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("新增挪库")){
	xinzengnuoku++;
		}
		//查询库区管理的权限
		if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("库区管理")){
	kufangguanli++;
		}
		//查询库位管理的权限
		if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("库位管理")){
	kuweiguanli++;
		}
		//查询二级菜单中货物类型的访问权限
		if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("货物类型")){
	huowuleixing++;
		}
		//查询对货物名称的访问权限
		if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("货物名称")){
	huowumingcheng++;
		}
		//查询对货物规格的访问权限
		if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("货物规格")){
	huowuguige++;
		}
		//查询对货物规格的访问权限
		if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("货物材质")){
	huowucaizhi++;
		}
		//查询对货物茶地的访问权限
		if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("货物产地")){
	huowuchandi++;
		}
		//查询对货物属性的访问权限
		if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("货物属性")){
	huowushuxing++;
		}
		//查询对货物计件单位的访问权限
		if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("计件单位")){
	jijiandanwei++;
		}
		
		//查询对费用类型的访问权限
		if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("费用类型")){
	feiyongleixing++;
		}
		
		//查询对支付方式的访问权限
		if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("支付方式")){
	zhifufangshi++;
		}
		
		//查询票据类型的访问权限
		if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("票据类型")){
	piaojuleixing++;
		}
		
		//查询滞纳金的访问权限
		if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("收费记录")){
	zhinajin++;
		}
		//查询订单查询的访问权限
		if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("订单查询")){
	dingdanchaxun++;
		}
		//查询库存查询的访问权限
		if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("库存查询")){
	kucunchaxun++;
		}
		//查询客户库存查询的访问权限
		if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("客户库存查询")){
	kehukucunchaxun++;
		}
		
		//查询挪库查询的访问权限
		if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("查看挪库订单")){
	nuokuchaxun++;
		}
		//查询盘库查询的访问权限
		if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("查看盘库订单")){
	pankuchaxun++;
		}
		//查询收发存报表的访问权限
		if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("收发存报表")){
	shoufacun++;
		}
		
		//查询客户信息的访问权限
		if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("客户信息")){
	kehuxinxi++;
		}
		
		//查询注册客户的访问权限
		if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("注册客户")){
	zhucekehu++;
		}
		
		//查询系统日志的访问权限
		if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("查看系统日志")){
	xitongrizhi++;
		}
		
		//查询部门管理的访问权限
		if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("部门管理")){
	bumenguanli++;
		}
		
		//查询班组管理的访问权限
		if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("班组管理")){
	banzuguanli++;
		}
		
		//查询非系统操作员管理的访问权限
		if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("非系统操作员管理")){
	feixitongcaozuoyuan++;
		}
		
		//查询系统操作员的访问权限
		if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("系统操作员管理")){
	xitongcaozuoyuan++;
		}
		
		//查询角色权限的访问权限
		if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("角色权限")){
	juesequanxian++;
		}
		//查看货物的批次的权限
		if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("货物批次表")){
	huowupicibiao++;
		}
		//库位库存查询权限
		if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("库位库存查询")){
	kuweikucunchaxun++;
		}
		
		//工作量统计权限
		if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("工作量统计")){
	zuoyeliangtongji++;
		}
		
		//费用结算权限
		if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("费用结算")){
	feiyongjiesuan++;
		}
		
		//费用结算权限
		if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("月结收费")){
	yuejieshoufei++;
		}
		//冻结和解冻客户库存的权限
		if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("冻结和解冻")){
	jiedong++;
		}
		//审批修改订单的权限
		if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("订单修改审批")){
	updateshenpi++;
		}
		
		//下站费记录权限
		if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("下站费记录")){
	xiazhanfeijilu++;
		}
		//日结收费明细权限
		if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("日结收费明细")){
	rijiecost++;
		}
		//查看配送订单的权限
		if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("配送订单")){
	peisong++;
		}
		//查看今日钢价的权限
		if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("今日钢价")){
	jinrigangjia++;
		}
		//盘点客户库存的权限
		if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("新增盘点")){
	pandiankehukucun++;
		}
		//查询新增短倒的权限
		if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("新增短倒")){
	duandao++;
		}
		//查询短倒历史订单的权限
		if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("查看短倒订单")){
	duandaochaxun++;
		}
		}
		}
%>
<body ondragstart="return false">
	<!-- <embed src="cangchu/img/5103.wav" id="maiti" autoplay="false" loop="true" hidden="true"></embed> -->
	<div class="tixing">
		<%
			if(daichuli!=0){
		%>
		<p>
			<a
				href="${pageContext.request.contextPath}/cangchu/page/daichuli.jsp"
				target="context">待处理订单<span id="daichuli">0</span>条</a>
		</p>
		<%
			}
		%>
		<%
			if(caozuodingdan!=0){
		%>
		<p>
			<a
				href="${pageContext.request.contextPath}/cangchu/page/caozuodingdan_main.jsp"
				target="context">待操作订单 <span id="dingdancaozuo">0</span>条</a>
		</p>
		<%
			}
		%>
		<%
			if(guobang!=0){
		%>
		<p>
			<a
				href="${pageContext.request.contextPath}/cangchu/page/rukuguobang_main.jsp"
				target="context">待过磅订单 <span id="dingdanguobang">0</span>条</a>
		</p>
		<%
			}
		%>
		<%
			if(shenhe!=0){
		%>
		<p>
			<a
				href="${pageContext.request.contextPath}/cangchu/page/dingdanshenhe_main.jsp"
				target="context">待审核订单 <span id="dingdanshenhe">0</span>条</a>
		</p>
		<%
			}
		%>
		<%
			if(pankushenhe!=0){
		%>
		<p>
			<a
				href="${pageContext.request.contextPath}/cangchu/page/pankushenhe.jsp"
				target="context">盘库审核订单 <span id="pankushenhe">0</span>条</a>
		</p>
		<%
			}
		%>
		<%
			if(shoufei!=0){
		%>
		<p>
			<a
				href="${pageContext.request.contextPath}/cangchu/page/dingdanshoufei_main.jsp"
				target="context">收费订单 <span id="dingdanshoufei">0</span>条</a>
		</p>
		<%
			}
		%>
		<%
			if(updateshenpi!=0){
		%>
		<p>
			<a
				href="${pageContext.request.contextPath}/sys-page/updateshenpi.jsp"
				target="context">订单修改审批<span id="updateshenpi">0</span>条</a>
		</p>
		<%
			}
		%>
	</div>
	<script>
		var sums = 0;
		$(function(){
			if($("#daichuli").text()!=null && $("#daichuli").text()!=""){
				sums+=parseInt($("#daichuli").text());
			}
			if($("#dingdancaozuo").text()!=null && $("#dingdancaozuo").text()!=""){
				sums+=parseInt($("#dingdancaozuo").text());
			}
			if($("#dingdanguobang").text()!=null && $("#dingdanguobang").text()!=""){
				sums+=parseInt($("#dingdanguobang").text());
			}
			if($("#dingdanshenhe").text()!=null && $("#dingdanshenhe").text()!=""){
				sums+=parseInt($("#dingdanshenhe").text());
			}
			if($("#pankushenhe").text()!=null && $("#pankushenhe").text()!=""){
				sums+=parseInt($("#pankushenhe").text());
			}
			if($("#dingdanshoufei").text()!=null && $("#dingdanshoufei").text()!=""){
				sums+=parseInt($("#dingdanshoufei").text());
			}
			if($("#updateshenpi").text()!=null && $("#updateshenpi").text()!=""){
				sums+=parseInt($("#updateshenpi").text());
			}
		});
		function show() {
			$(function() {
				$
						.ajax({
							type : "post",
							url : "${pageContext.request.contextPath}/loginAll.do?flag=goLoginAll&ff=ajax",
							async : true,
							dataType : "json",
							success : function(obj) {

								if (obj != null) {
									if (obj[0].result == "n") {
										document.location.href = "/XGProject/cangchu/page/login.jsp";
									}
								} else {
									document.location.href = "/XGProject/cangchu/page/login.jsp";
								}
							},
							error : function() {
								document.location.href = "/XGProject/cangchu/page/login.jsp";
							}
						});
				$
						.ajax({
							type : "post",
							url : "${pageContext.request.contextPath}/tidingsAction.do?flag=CountOrderForm",
							data : {
								"id" : $("#dengluren").val()
							},
							async : true,
							dataType : "json",
							success : function(obj) {

								if (obj != null) {
									$("#daichuli").text(obj[0].daichuli);
									$("#dingdancaozuo").text(
											obj[0].dingdancaozuo);
									$("#dingdanguobang").text(obj[0].guobang);
									$("#dingdanshenhe").text(obj[0].shenhe);
									$("#pankushenhe").text(obj[0].pankushenhe);
									$("#dingdanshoufei").text(obj[0].shoufei);
									$("#updateshenpi").text(obj[0].updateshenpi);
									
								}
								var sum = 0;
								if($("#daichuli").text()!=null && $("#daichuli").text()!=""){
									sum+=parseInt($("#daichuli").text());
								}
								if($("#dingdancaozuo").text()!=null && $("#dingdancaozuo").text()!=""){
									sum+=parseInt($("#dingdancaozuo").text());
								}
								if($("#dingdanguobang").text()!=null && $("#dingdanguobang").text()!=""){
									sum+=parseInt($("#dingdanguobang").text());
								}
								if($("#dingdanshenhe").text()!=null && $("#dingdanshenhe").text()!=""){
									sum+=parseInt($("#dingdanshenhe").text());
								}
								if($("#pankushenhe").text()!=null && $("#pankushenhe").text()!=""){
									sum+=parseInt($("#pankushenhe").text());
								}
								if($("#dingdanshoufei").text()!=null && $("#dingdanshoufei").text()!=""){
									sum+=parseInt($("#dingdanshoufei").text());
								}
								if($("#updateshenpi").text()!=null && $("#updateshenpi").text()!=""){
									sum+=parseInt($("#updateshenpi").text());
								}
								if(sums<sum){
									if($(".shandong").css("visibility")=="hidden"){
										$(".shandong").css("visibility","visible");
										$("title").html("【新消息】");
									}else{
										$(".shandong").css("visibility","hidden");
										$("title").html("【&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;】");
									}
								}
								$(".shandong").click(function(){
									sums=sum;
									$(".shandong").css("visibility","visible");
									$("title").html("仓储管理系统");
								});
								$(".tixing a").click(function(){
									sums=sum;
									$(".shandong").css("visibility","visible");
									$("title").html("仓储管理系统");
								});
								if(sum==0){
									$(".tixingxianshi").css("display","none");
								}else{
									$(".tixingxianshi").css("display","block");
								}
								$(".tixingxianshi").text(sum);
							},
							error : function() {
								document.location.href = "/XGProject/cangchu/page/login.jsp";
							}
						});
			});

		}
		setInterval("show()", 500);
	</script>
	<!--头部开始-->
	<input type="hidden"
		value="<%=request.getSession().getAttribute("iulistId")%>"
		id="dengluren" />
	<div class="head">
		<a href="cangchu/page/admin.jsp"><img src="cangchu/img/logo.png"
			class="logo" /> </a>
		<div class="head_right">
			<ul>
				<li><img src="cangchu/img/bangzhu.png" width="15" /></li>
				<li>帮助</li>
				<li class="head_margin"><img src="cangchu/img/zhuxiao.png"
					width="15" /></li>
				<li id="alert">注销</li>
				<a href="${pageContext.request.contextPath}/home-page/user-info.jsp"
					target="context">
					<li class="head_margin"><img src="cangchu/img/kaipiao.png"
						width="15" /></li>
					<li><%=request.getSession().getAttribute("loginName")%></li> </a>
				<li class="head_margin"><img src="cangchu/img/duanxin.png"
					width="15" /></li>
				<li>发送短信</li>
				<li class="head_margin shandong"><img
					src="cangchu/img/xiaoxi.png" width="15" /></li>
				<li class="shandong">消息<span class="tixingxianshi"></span>
				</li>
			</ul>
		</div>
		<div class="gundong">
			<marquee direction="right" behavior="alternate" scrollamount="2">
				欢迎【
				<%=request.getSession().getAttribute("loginName")%>】登录，当前时间：<span
					id="now_date">2017年6月24日 11:06:20 星期三</span>
			</marquee>
		</div>
	</div>
	<!--头部结束-->
	<!--中间左侧开始-->
	<div class="middle_left">
		<div class="middle_left_span">
			<span><s>首页</s> </span>
			<div class="middle_left_a">
				<a href="cangchu/page/shouye.jsp" target="context">
					<p>
						<img src="cangchu/img/171.png" /><i>返回首页</i>
					</p> </a> <a
					href="${pageContext.request.contextPath}/home-page/user-info.jsp"
					target="context">
					<p>
						<img src="cangchu/img/171.png" /><i>个人资料</i>
					</p> </a><a
					href="${pageContext.request.contextPath}/home-page/online-user.jsp"
					target="context">
					<p>
						<img src="cangchu/img/171.png" /><i>在线用户</i>
					</p> </a>
				<!--<a
					 href="${pageContext.request.contextPath}/input.do?flag=selectPlanInput" -->
				<%
					if(daichuli!=0){
				%>
				<a
					href="${pageContext.request.contextPath}/cangchu/page/daichuli.jsp"
					target="context">
					<p>
						<img src="cangchu/img/171.png" /><i>待处理订单</i>
					</p> </a>
				<%
					}
				%>
				<%
					if(peisong!=0){
				%>
				<a
					href="${pageContext.request.contextPath}/cangchu/page/peisong.jsp"
					target="context">
					<p>
						<img src="cangchu/img/171.png" /><i>配送订单</i>
					</p> </a>
				<%
					}
				%>
			</div>
			<span><s>仓储业务</s> </span>
			<div class="middle_left_a">
				<%
					if(xinzengchuku!=0){
				%>
				<a
					href="${pageContext.request.contextPath}/cangchu/page/faqichuku.jsp"
					target="context">
					<p>
						<img src="cangchu/img/171.png" /><i>新增出库</i>
					</p> </a>
				<%
					}
				%>
				<%
					if(xinzengruku!=0){
				%>
				<a
					href="${pageContext.request.contextPath}/cangchu/page/faqiruku.jsp"
					target="context">
					<p>
						<img src="cangchu/img/171.png" /><i>新增入库</i>
					</p> </a>
				<%
					}
				%>
				<%
					if(xinzengguohu!=0){
				%>
				<a
					href="${pageContext.request.contextPath}/cangchu/page/faqiguohu.jsp"
					target="context">
					<p>
						<img src="cangchu/img/171.png" /><i>新增过户</i>
					</p> </a>
				<%
					}
				%>
				<%
					if(xinzengpanku!=0){
				%>
				<a
					href="${pageContext.request.contextPath}/cangchu/page/faqipanku.jsp"
					target="context">
					<p>
						<img src="cangchu/img/171.png" /><i>新增盘库</i>
					</p> </a>
				<%
					}
				%>
				<%
					if(xinzengnuoku!=0){
				%>
				<a
					href="${pageContext.request.contextPath}/cangchu/page/faqinuoku.jsp"
					target="context">
					<p>
						<img src="cangchu/img/171.png" /><i>新增挪库</i>
					</p> </a>
				<%
					}
				%>
				<%
					if(duandao!=0){
				%>
				<a
					href="${pageContext.request.contextPath}/cangchu/page/faqiduandao.jsp"
					target="context">
					<p>
						<img src="cangchu/img/171.png" /><i>新增短倒</i>
					</p> </a>
				<%
					}
				%>
				<%
					if(caozuodingdan!=0){
				%>
				<a
					href="${pageContext.request.contextPath}/cangchu/page/caozuodingdan_main.jsp"
					target="context">
					<p>
						<img src="cangchu/img/171.png" /><i>订单操作</i>
					</p> </a>
				<%
					}
				%>
				<%
					if(guobang!=0){
				%>
				<a
					href="${pageContext.request.contextPath}/cangchu/page/rukuguobang_main.jsp"
					target="context">
					<p>
						<img src="cangchu/img/171.png" /><i>订单过磅</i>
					</p> </a>
				<%
					}
				%>
				<%
					if(shenhe!=0){
				%>
				<a
					href="${pageContext.request.contextPath}/cangchu/page/dingdanshenhe_main.jsp"
					target="context">
					<p>
						<img src="cangchu/img/171.png" /><i>订单审核</i>
					</p> </a>
				<%
					}
				%>
				<%
					if(pankushenhe!=0){
				%>
				<a
					href="${pageContext.request.contextPath}/cangchu/page/pankushenhe.jsp"
					target="context">
					<p>
						<img src="cangchu/img/171.png" /><i>盘库审核</i>
					</p> </a>
				<%
					}
				%>
				<%
					if(shoufei!=0){
				%>
				<a
					href="${pageContext.request.contextPath}/cangchu/page/dingdanshoufei_main.jsp"
					target="context">
					<p>
						<img src="cangchu/img/171.png" /><i>订单收费</i>
					</p> </a>
				<%
					}
				%>
				<%
					if(jiedong!=0){
				%>
				<a
					href="${pageContext.request.contextPath}/date-page/kehukucundongjie.jsp"
					target="context">
					<p>
						<img src="cangchu/img/171.png" /><i>冻结客户库存</i>
					</p> </a>
				<%
					}
				%>
				<%
					if(pandiankehukucun!=0){
				%>
				<a
					href="${pageContext.request.contextPath}/date-page/pandiankehukucun.jsp"
					target="context">
					<p>
						<img src="cangchu/img/171.png" /><i>盘点客户库存</i>
					</p> </a>
				<%
					}
				%>
			</div>
			<span><s>仓储资料</s> </span>
			<div class="middle_left_a">
				<%
					if(kufangguanli!=0){
				%>
				<a
					href="${pageContext.request.contextPath}/bursary.do?flag=getBursaryAll"
					target="context">
					<p>
						<img src="cangchu/img/171.png" /><i>库区管理</i>
					</p> </a>
				<%
					}
				%>
				<%
					if(kuweiguanli!=0){
				%>
				<a
					href="${pageContext.request.contextPath}/tarehouse.do?flag=getTarehouseAll"
					target="context">
					<p>
						<img src="cangchu/img/171.png" /><i>库位管理</i>
					</p> </a>
				<%
					}
				%>
				<%
					if(huowuleixing!=0){
				%>
				<a
					href="${pageContext.request.contextPath}/goodsCategory.do?flag=selectGoodsCategory"
					target="context">
					<p>
						<img src="cangchu/img/171.png" /><i>货物类型</i>
					</p> </a>
				<%
					}
				%>
				<%
					if(huowumingcheng!=0){
				%>
				<a
					href="${pageContext.request.contextPath}/goods.do?flag=goSelectGoods"
					target="context">
					<p>
						<img src="cangchu/img/171.png" /><i>货物名称</i>
					</p> </a>
				<%
					}
				%>
				<%
					if(huowuguige!=0){
				%>
				<a
					href="${pageContext.request.contextPath}/goodsStandard.do?flag=selectGoodsStandard"
					target="context">
					<p>
						<img src="cangchu/img/171.png" /><i>货物规格</i>
					</p> </a>
				<%
					}
				%>
				<%
					if(huowucaizhi!=0){
				%>
				<a
					href="${pageContext.request.contextPath}/goodsQuality.do?flag=selectGoodsQuality"
					target="context">
					<p>
						<img src="cangchu/img/171.png" /><i>货物材质</i>
					</p> </a>

				<%
					}
				%>
				<%
					if(huowuchandi!=0){
				%>
				<a
					href="${pageContext.request.contextPath}/goodsYieldly.do?flag=goSelectgoodsYieldly"
					target="context">
					<p>
						<img src="cangchu/img/171.png" /><i>货物产地</i>
					</p> </a>
				<%
					}
				%>
				<%
					if(huowushuxing!=0){
				%>
				<a
					href="${pageContext.request.contextPath}/goodsProperty.do?flag=selectGoodsProperty"
					target="context">
					<p>
						<img src="cangchu/img/171.png" /><i>货物属性</i>
					</p> </a>
				<%
					}
				%>
				<%
					if(jijiandanwei!=0){
				%>
				<a
					href="${pageContext.request.contextPath}/goodsUnit.do?flag=selectGoodsUnit"
					target="context">
					<p>
						<img src="cangchu/img/171.png" /><i>计件单位</i>
					</p> </a>
				<%
					}
				%>
				<%
					if(jinrigangjia!=0){
				%>
				<a
					href="${pageContext.request.contextPath}/sys-page/jinrigangjia.jsp"
					target="context">
					<p>
						<img src="cangchu/img/171.png" /><i>今日钢价</i>
					</p> </a>
				<%
					}
				%>
			</div>
			<span><s>财务管理</s> </span>
			<div class="middle_left_a">
				<%
					if(feiyongjiesuan!=0){
				%>
				<a
					href="${pageContext.request.contextPath}/caiwu-page/jiesuanhuizong.jsp"
					target="context">
					<p>
						<img src="cangchu/img/171.png" /><i>费用结算</i>
					</p> </a>
				<%
					}
				%>
				<%
					if(yuejieshoufei!=0){
				%>
				<a
					href="${pageContext.request.contextPath}/caiwu-page/yuejieshoufei.jsp"
					target="context">
					<p>
						<img src="cangchu/img/171.png" /><i>月结收费</i>
					</p> </a>
				<%
					}
				%>
				<%
					if(feiyongleixing!=0){
				%>
				<a
					href="${pageContext.request.contextPath}/costType.do?flag=getCostTypeAll"
					target="context">
					<p>
						<img src="cangchu/img/171.png" /><i>费用类型</i>
					</p> </a>
				<%
					}
				%>
				<%
					if(zhifufangshi!=0){
				%>
				<a
					href="${pageContext.request.contextPath}/paymentFashion.do?flag=getAll"
					target="context">
					<p>
						<img src="cangchu/img/171.png" /><i>支付方式</i>
					</p> </a>
				<%
					}
				%>
				<%
					if(piaojuleixing!=0){
				%>
				<a
					href="${pageContext.request.contextPath}/paymentFashion.do?flag=getAllTwo"
					target="context">
					<p>
						<img src="cangchu/img/171.png" /><i>票据类型</i>
					</p> </a>
				<%
					}
				%>
				<%
					if(zhinajin!=0){
				%>
				<a
					href="${pageContext.request.contextPath}/caiwu-page/shoufeijilu.jsp"
					target="context">
					<p>
						<img src="cangchu/img/171.png" /><i>收费记录</i>
					</p> </a>
				<%
					}
				%>
				<%
					if(xiazhanfeijilu!=0){
				%>
				<a
					href="${pageContext.request.contextPath}/caiwu-page/xiazhanfeijilu.jsp"
					target="context">
					<p>
						<img src="cangchu/img/171.png" /><i>下站费记录</i>
					</p> </a>
				<%
					}
				%>
				<%
					if(rijiecost!=0){
				%>
				<a
					href="${pageContext.request.contextPath}/caiwu-page/rijieshoufeimingxi.jsp"
					target="context">
					<p>
						<img src="cangchu/img/171.png" /><i>日结收费明细</i>
					</p> </a>
				<%
					}
				%>
			</div>
			<span><s>数据中心</s> </span>
			<div class="middle_left_a">
				<%
					if(dingdanchaxun!=0){
				%>
				<a href="${pageContext.request.contextPath}/date-page/indent.jsp"
					target="context">
					<p>
						<img src="cangchu/img/171.png" /><i>订单查询</i>
					</p> </a>
				<%
					}
				%>
				<%
					if(kucunchaxun!=0){
				%>
				<a
					href="${pageContext.request.contextPath}/date-page/XGkucunchaxun.jsp"
					target="context">
					<p>
						<img src="cangchu/img/171.png" /><i>历史库存查询</i>
					</p> </a>
				<%
					}
				%>
				<%
					if(kehukucunchaxun!=0){
				%>
				<a
					href="${pageContext.request.contextPath}/date-page/kehukucunbaobiao.jsp"
					target="context">
					<p>
						<img src="cangchu/img/171.png" /><i>客户库存查询</i>
					</p> </a>
				<%
					}
				%>
				<%
					if(nuokuchaxun!=0){
				%>
				<a
					href="${pageContext.request.contextPath}/date-page/indent-nuoku.jsp"
					target="context">
					<p>
						<img src="cangchu/img/171.png" /><i>挪库查询</i>
					</p> </a>
				<%
					}
				%>
				<%
					if(duandaochaxun!=0){
				%>
				<a
					href="${pageContext.request.contextPath}/date-page/indent-duandao.jsp"
					target="context">
					<p>
						<img src="cangchu/img/171.png" /><i>短倒查询</i>
					</p> </a>
				<%
					}
				%>
				<%
					if(pankuchaxun!=0){
				%>
				<a
					href="${pageContext.request.contextPath}/date-page/indent-panku.jsp"
					target="context">
					<p>
						<img src="cangchu/img/171.png" /><i>盘库查询</i>
					</p> </a>
				<%
					}
				%>
				<%
					if(shoufacun!=0){
				%>
				<a href="${pageContext.request.contextPath}/date-page/shoufacun.jsp"
					target="context">
					<p>
						<img src="cangchu/img/171.png" /><i>收发存报表</i>
					</p> </a>
				<%
					}
				%>
				<%
					if(kuweikucunchaxun!=0){
				%>

				<!-- 库位库存查询 -->
				<a
					href="${pageContext.request.contextPath}/date-page/kuweiKuCun.jsp"
					target="context">
					<p>
						<img src="cangchu/img/171.png" /><i>库位库存查询</i>

					</p> </a>

				<%
					}
				%>
				<%
					if(huowupicibiao!=0){
				%>
				<a
					href="${pageContext.request.contextPath}/date-page/huowupicibiao.jsp"
					target="context">
					<p>
						<img src="cangchu/img/171.png" /><i>货物批次表</i>
					</p> </a>
				<%
					}
				%>
				<%
					if(zuoyeliangtongji!=0){
				%>
				<a
					href="${pageContext.request.contextPath}/date-page/zuoyeliangtongji.jsp"
					target="context">
					<p>
						<img src="cangchu/img/171.png" /><i>工作量统计</i>
					</p> </a>
				<%
					}
				%>
				<%
					if(updateshenpi!=0){
				%>
				<a
					href="${pageContext.request.contextPath}/sys-page/updateshenpi.jsp"
					target="context">
					<p>
						<img src="cangchu/img/171.png" /><i>订单修改审批</i>
					</p> </a>
				<%
					}
				%>
			</div>
			<span><s>客户管理</s> </span>
			<div class="middle_left_a">
				<%
					if(kehuxinxi!=0){
				%>
				<a
					href="${pageContext.request.contextPath}/client.do?flag=selectClient"
					target="context">
					<p>
						<img src="cangchu/img/171.png" /><i>客户信息</i>
					</p> </a>
				<%
					}
				%>
				<%
					if(zhucekehu!=0){
				%>
				<a href="${pageContext.request.contextPath}/custom-page/zhuce.jsp"
					target="context">
					<p>
						<img src="cangchu/img/171.png" /><i>注册客户</i>
					</p> </a>
				<%
					}
				%>
				<!-- <a href="#"> <p>
						<img src="cangchu/img/171.png" /><i>实名认证</i>
					</p> </a> <a href="#">
					<p>
						<img src="cangchu/img/171.png" /><i>支付信息</i>
					</p> </a>-->
			</div>
			<span><s>系统管理</s> </span>
			<div class="middle_left_a">
				<%
					if(xitongrizhi!=0){
				%>
				<a href="${pageContext.request.contextPath }/sys-page/sys-log.jsp"
					target="context">
					<p>
						<img src="cangchu/img/171.png" /><i>系统日志</i>
					</p> </a>
				<%
					}
				%>
				<%
					if(bumenguanli!=0){
				%>
				<a
					href="${pageContext.request.contextPath}/section.do?flag=selectSection"
					target="context">
					<p>
						<img src="cangchu/img/171.png" /><i>部门管理</i>
					</p> </a>
				<%
					}
				%>
				<%
					if(banzuguanli!=0){
				%>
				<a href="${pageContext.request.contextPath}/sys-page/banzu.jsp"
					target="context">
					<p>
						<img src="cangchu/img/171.png" /><i>班组管理</i>
					</p> </a>
				<%
					}
				%>
				<%
					if(feixitongcaozuoyuan!=0){
				%>
				<a href="${pageContext.request.contextPath }/sys-page/zuoye-man.jsp"
					target="context">
					<p>
						<img src="cangchu/img/171.png" /><i>非系统操作员</i>
					</p> </a>
				<%
					}
				%>
				<%
					if(xitongcaozuoyuan!=0){
				%>
				<a href="${pageContext.request.contextPath}/sys-page/operator.jsp"
					target="context">
					<p>
						<img src="cangchu/img/171.png" /><i>系统操作员</i>
					</p> </a>
				<%
					}
				%>
				<%
					if(juesequanxian!=0){
				%>
				<a
					href="${pageContext.request.contextPath}/interiorUserDuty.do?flag=selectInteriorUserDuty"
					target="context">
					<p>
						<img src="cangchu/img/171.png" /><i>角色权限</i>
					</p> </a>
				<%
					}
				%>

				<%
					if(zhiwu.equals("管理员")){
				%>

				<a
					href="${pageContext.request.contextPath}/cangchu/page/InitCliengGoods.jsp"
					target="context">
					<p>
						<img src="cangchu/img/171.png" /><i>初始化客户库存</i>
					</p> </a> <a
					href="${pageContext.request.contextPath}/cangchu/page/InitTarehouseGoods.jsp"
					target="context">
					<p>
						<img src="cangchu/img/171.png" /><i>初始化库位库存</i>
					</p> </a>

				<%
					}
				%>
			</div>
		</div>
		<div class="right_bg"></div>
	</div>
	<!--中间左侧结束-->
	<!--关闭菜单栏容器-->
	<div class="left_switch" id="switch"></div>
	<div class="hide_switch"></div>
	<!--显示的内容页-->
	<iframe id="context" src="cangchu/page/shouye.jsp" border="0"
		class="iframe" scrolling="auto" name="context" target="main"></iframe>

	<!--注销窗口-->
	<div class="modal_content" id="modeal">
		<p class="modeal_p">
			<span class="zhuxiao">注销系统<span><span class="guan">&times;</span>
		</p>
		<div class="modealp_div_content">
			<h1>您确定退出系统吗？</h1>
			<div class="modeal_a">
				<a href="#" id="queding">确定</a> <a id="close"
					style="cursor: pointer;">关闭</a>
			</div>
		</div>
	</div>
	<!--注销背景-->
	<div class="modeal_back" id="back"></div>

	<!-- 当单机注销的时候，将登录名注销 -->
	<script>
		$(function() {
			$("#queding")
					.click(
							function() {
								$
										.ajax({
											type : "post",
											url : "loginAll.do?flag=goLoginAll&ff=zhuxiao",
											async : true,
											dataType : "json",
											success : function(obj) {
												if (obj != null) {
													if (obj[0].result == "n") {
														document.location.href = "/XGProject/cangchu/page/login.jsp";
													}
												} else {
													document.location.href = "/XGProject/cangchu/page/login.jsp";
												}
											},
											error : function() {
												document.location.href = "/XGProject/cangchu/page/login.jsp";
											}
										});
							});
		});
	</script>
</body>

</html>