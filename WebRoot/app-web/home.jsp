<%@page import="com.sun.xml.internal.bind.v2.schemagen.xmlschema.Import"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.xinggang.project.entity.Client"%>
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
<!-- This file has been downloaded from bootstrap.cn. Enjoy! -->
<title>鑫港库存管理系统</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="app-web/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="app-web/css/font-awesome.min.css">
<link rel="stylesheet" href="app-web/css/sidebar.css">
<script src="http://libs.baidu.com/jquery/2.1.1/jquery.min.js"></script>
<script src="app-web/js/bootstrap.min.js"></script>

<style type="text/css">
.modal-dialog {
	width: 300px;
	margin: 300px auto;
}
</style>
</head>
<script type="text/javascript">
	$(function() {
		$("#logoout").click(function() {
			$("#disable").modal('show');
		});
	});
</script>
<body>
	<div class="container">
		<div class="header">
			<a class="brand"
				href="${pageContext.request.contextPath}/app-web/home.jsp"><img
				src="app-web/img/logo.png" width="150px"
				style="margin:-10px 0 10px 0;" /> </a> <i
				class="fa fa-bars fa-2x toggle-btn" data-toggle="collapse"
				data-target="#main-menu"></i> <i class="fa fa-power-off fa-2x"
				style="float:right;font-size:20px; margin:30px 50px 0 0; color:white; cursor:pointer;"
				id="logoout"> <span
				style="font-size:16px; color:white; margin-left:5px; display:block; float:right; cursor:pointer;">注销</span>
			</i>
			<%
				Client c = (Client) request.getSession().getAttribute("client");
			%>
			<a
				href="${pageContext.request.contextPath}/client.do?flag=goClientZiliao">
				<i class="fa fa-user fa-2x"
				style="float:right; font-size:20px; margin:30px 50px 0 0; color:white;"><span
					style="font-size:16px; color:white; margin-left:5px; 
            display:block; float:right; cursor:pointer;">
						<%=c.getClientFirmName()%></span> </i> </a>
		</div>
		<!-- 以上是头部 -->
		<nav class="navbar-default navbar-side" role="navigation">
			<div class="sidebar-collapse">
				<ol class="nav collapse " id="main-menu">
					<!-- <li>
                        <div class="user-img-div">
                            <img src="assets/img/user.png" class="img-thumbnail" />
                            <div class="inner-text">
                                Jhon Deo Alex
                                <br />
                                <small>Last Login : 2 Weeks Ago </small>
                            </div>
                        </div>
                    </li> -->
					<li><a class="1ji"
						href="${pageContext.request.contextPath}/app-web/home.jsp"><i
							class="fa fa-dashboard "></i>首页</a></li>
					<li><a class="1ji"><i class="fa fa-desktop "></i>仓储业务</i> </a>
						<ol class="nav nav-second-level collapse">
							<li><a
								href="${pageContext.request.contextPath}/app-web/indent-in.jsp"><i
									class="fa fa-toggle-on"></i>入库订单</a></li>
							<li><a
								href="${pageContext.request.contextPath}/app-web/indent-out.jsp"><i
									class="fa fa-bell "></i>出库订单</a></li>
							<li><a
								href="${pageContext.request.contextPath}/app-web/indent-guo.jsp"><i
									class="fa fa-circle-o "></i>过户订单</a></li>
						</ol></li>
					<li><a class="1ji"><i class="fa fa-paypal "></i>结算中心</a>
						<ol class="nav nav-second-level collapse">
							<li><a
								href="${pageContext.request.contextPath}/app-web/indent-jiesuan.jsp"><i
									class="fa fa-coffee"></i>订单结算</a></li>
							<li><a
								href="${pageContext.request.contextPath}/app-web/jiesuanhuizong.jsp"><i
									class="fa fa-file-text "></i>结算汇总</a></li>
							<li><a
								href="${pageContext.request.contextPath}/app-web/payment.jsp"><i
									class="fa fa-key "></i>缴费记录</a></li>
							<!-- 
	                            <li>
	                                <a href="${pageContext.request.contextPath}/app-web/late-fee.jsp"><i class="fa fa-send "></i>滞纳金</a>
	                            </li>
                             -->
						</ol></li>
					<li><a class="1ji"><i class="fa fa-database "></i>数据中心</a>
						<ol class="nav nav-second-level collapse">
							<li><a
								href="${pageContext.request.contextPath}/app-web/indent-search.jsp"><i
									class="fa fa-desktop "></i>订单查询</a></li>
							<li><a
								href="${pageContext.request.contextPath}/app-web/indent-zuofei.jsp"><i
									class="fa fa-desktop "></i>作废订单查询</a></li>
							<li><a
								href="${pageContext.request.contextPath}/app-web/sfc-form.jsp"><i
									class="fa fa-file-text "></i>收发存报表</a></li>
							<li><a
								href="${pageContext.request.contextPath}/clientGoods.do?flag=getClientAllInfo"><i
									class="fa fa-file-text "></i>库存查询</a></li>
						</ol></li>
					<li><a class="1ji"><i class="fa fa-sitemap "></i>系统设置</a>
						<ol class="nav nav-second-level collapse">
							<li><a
								href="${pageContext.request.contextPath}/client.do?flag=goClientZiliao"><i
									class="fa fa-user "></i>用户资料</a></li>
							<li><a
								href="${pageContext.request.contextPath}/app-web/password.jsp"><i
									class="fa fa-flask "></i>修改密码</a></li>
							<li><a id="zhuxiao" data-toggle="modal"
								data-target="#disable"><i class="fa fa-flask "></i>注销系统</a></li>
						</ol></li>
				</ol>
			</div>
		</nav>
		<!-- 模态框（Modal） -->
		<div class="modal fade bs-example-modal-sm " id="disable"
			tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
			aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">注销系统</h4>
					</div>
					<div class="modal-body">
						<h3>确定要退出吗？</h3>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭
						</button>
						<a class="btn btn-primary"
							href="${pageContext.request.contextPath}/loginAll.do?flag=kehuZhuxiao" />确定</a>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>
		<!-- 模态框结束 -->
		<!-- 以上是左侧菜单 -->
		<div class="content-m">
			<p>甘肃鑫港物流仓储管理系统</p>
			<div class="lunbotu" style="height: 200px;">
				<div id="myCarousel" class="carousel slide" data-ride="carousel">
					<!-- 轮播（Carousel）指标 -->
					<ol class="carousel-indicators">
						<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
						<li data-target="#myCarousel" data-slide-to="1"></li>
						<li data-target="#myCarousel" data-slide-to="2"></li>
					</ol>
					<!-- 轮播（Carousel）项目 -->
					<div class="carousel-inner">
						<div class="item active">
							<img src="app-web/img/22.png" alt="First slide">
						</div>
						<div class="item">
							<img src="app-web/img/33.png" alt="Second slide">
						</div>
						<div class="item">
							<img src="app-web/img/22.png" alt="Third slide">
						</div>
					</div>
					<!-- 轮播（Carousel）导航 -->
					<a class="carousel-control left" href="#myCarousel"
						data-slide="prev">&lsaquo;</a> <a class="carousel-control right"
						href="#myCarousel" data-slide="next">&rsaquo;</a>
				</div>
			</div>
			<!-- 页面快捷方式 -->
			<ul class="nav nav-pills nav-justified" style="margin: 50px 0;">
				<li><a
					href="${pageContext.request.contextPath}/app-web/indent-in.jsp">
						<i class="fa fa-train fa-5x"></i>
						<h4>入库订单</h4> </a></li>
				<li><a
					href="${pageContext.request.contextPath}/app-web/indent-out.jsp">
						<i class="fa fa-truck fa-5x"></i>
						<h4>出库订单</h4> </a></li>
				<li><a
					href="${pageContext.request.contextPath}/app-web/indent-guo.jsp">
						<i class="fa fa-balance-scale fa-5x"></i>
						<h4>过户订单</h4> </a></li>
			</ul>
			<ul class="nav nav-pills nav-justified" style="margin: 50px 0;">
				<li><a
					href="${pageContext.request.contextPath}/app-web/indent-search.jsp">
						<i class="fa fa-binoculars fa-5x"></i>
						<h4>订单查询</h4> </a></li>
				<li><a
					href="${pageContext.request.contextPath}/app-web/indent-jiesuan.jsp">
						<i class="fa fa-dollar fa-5x"></i>
						<h4>订单结算</h4> </a></li>
				<li><a
					href="${pageContext.request.contextPath}/clientGoods.do?flag=getClientAllInfo">
						<i class="fa fa-search-plus fa-5x"></i>
						<h4>库存查询</h4> </a></li>
			</ul>
		</div>
		<!-- 以上是桌面右侧内容页 -->
		<!-- 以下是手机版右侧内容 -->
		<div class="content">
			<div id="myCarousel" class="carousel slide" data-ride="carousel">
				<!-- 轮播（Carousel）指标 -->
				<ol class="carousel-indicators">
					<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
					<li data-target="#myCarousel" data-slide-to="1"></li>
					<li data-target="#myCarousel" data-slide-to="2"></li>
				</ol>
				<!-- 轮播（Carousel）项目 -->
				<div class="carousel-inner">
					<div class="item active">
						<img src="app-web/img/1.jpg" alt="First slide">
					</div>
					<div class="item">
						<img src="app-web/img/2.jpg" alt="Second slide">
					</div>
					<div class="item">
						<img src="app-web/img/3.jpg" alt="Third slide">
					</div>
				</div>
				<!-- 轮播（Carousel）导航 -->
				<a class="carousel-control left" href="#myCarousel"
					data-slide="prev">&lsaquo;</a> <a class="carousel-control right"
					href="#myCarousel" data-slide="next">&rsaquo;</a>
			</div>
			<ul class="icon-list">
				<li><a
					href="${pageContext.request.contextPath}/app-web/indent-in.jsp"><img
						src="app-web/img/11.png"> </a></li>
				<li><a
					href="${pageContext.request.contextPath}/app-web/indent-out.jsp"><img
						src="app-web/img/12.png"> </a></li>
				<li><a
					href="${pageContext.request.contextPath}/app-web/indent-guo.jsp"><img
						src="app-web/img/13.png"> </a></li>
			</ul>
			<ul class="icon-list">
				<li><a
					href="${pageContext.request.contextPath}/app-web/indent-search.jsp"><img
						src="app-web/img/14.png"> </a></li>
				<li><a
					href="${pageContext.request.contextPath}/app-web/indent-jiesuan.jsp"><img
						src="app-web/img/15.png"> </a></li>
				<li><a
					href="${pageContext.request.contextPath}/app-web/inventory.jsp"><img
						src="app-web/img/14.png"> </a></li>
			</ul>
		</div>
	</div>
	<!--以下是底部导航 -->
	<div class="container foot">
		<ul class="nav nav-pills nav-justified">
			<li class="active"><a href="#"><i class="fa fa-home fa-2x"><span
						class="wenzi">首页</span> </i> </a></li>
			<li><a href="#"><i class="fa fa-truck fa-2x"><span
						class="wenzi">仓储</span> </i> </a></li>
			<li><a href="#"><i class="fa fa-paypal fa-2x"><span
						class="wenzi">结算</span> </i> </a></li>
			<li><a href="#"><i class="fa fa-file-text fa-2x"><span
						class="wenzi">报表</span> </i> </a></li>
			<li><a href="mui/examples/setting.html"><i
					class="fa fa-user fa-2x"><span class="wenzi">我的</span> </i> </a></li>
		</ul>
	</div>

	<!-- 模态框（Modal） -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog"
			style="width:1050px;height: 650px;margin: 100px auto auto auto;">
			<div class="modal-content ">
				<div class="modal-header ">
					<h3 class="modal-title " id="myModalLabel "
						style="text-align: center; ">鑫港物流仓储管理系统服务协议及隐私协议</h3>
				</div>
				<div class="modal-body" style=" height:600px; overflow-y:scroll; ">
					<h4 style="text-align: center;">鑫港物流用户服务协议</h4>
					<p>本协议是您与鑫港物流网站（简称;本站;，网址：www.xg56.cn）所有者（以下简称为;鑫港物流;）之间就鑫港物流网站服务等相关事宜所订立的契约，请您仔细阅读本注册协议，您点击;同意并继续;按钮后，本协议即构成对双方有约束力的法律文件。</p>
					<h4>第1条 本站服务条款的确认和接纳</h4>
					<p>1.1本站的各项电子服务的所有权和运作权归鑫港物流所有。用户同意所有注册协议条款并完成注册程序，才能成为本站的正式用户。用户确认：本协议条款是处理双方权利义务的契约，始终有效，法律另有强制性规定或双方另有特别约定的，依其规定。</p>
					<p>1.2用户点击同意本协议的，即视为用户确认自己具有享受本站服务、下单购物等相应的权利能力和行为能力，能够独立承担法律责任。</p>
					<p>1.3如果您在18周岁以下，您只能在父母或监护人的监护参与下才能使用本站。</p>
					<p>1.4鑫港物流保留在中华人民共和国大陆地区法施行之法律允许的范围内独自决定拒绝服务、关闭用户账户、清除或编辑内容或取消订单的权利。</p>
					<h4>第2条 本站服务</h4>
					<p>2.1鑫港物流通过互联网依法为用户提供互联网信息等服务，用户在完全同意本协议及本站规定的情况下，方有权使用本站的相关服务。</p>
					<p>2.2用户必须自行准备如下设备和承担如下开支：（1）上网设备，包括并不限于电脑或者其他上网终端、调制解调器及其他必备的上网装置；（2）上网开支，包括并不限于网络接入费、上网设备租用费、手机流量费等。</p>

					<h4>第3条 用户信息</h4>
					<p>3.1用户应自行诚信向本公司提供注册资料，用户同意其提供的注册资料真实、准确、完整、合法有效，用户注册资料如有变动的，应及时更新其注册资料。如果用户提供的注册资料不合法、不真实、不准确、不详尽的，用户需承担因此引起的相应责任及后果，并且鑫港物流保留终止用户使用鑫港物流各项服务的权利。</p>
					<p>3.2用户在本站进行浏览、下订单等活动时，涉及用户真实姓名/名称、通信地址、联系电话、电子邮箱等隐私信息的，本站将予以严格保密，除非得到用户的授权或法律另有规定，本站不会向外界披露用户隐私信息。</p>
					<p>3.3用户注册成功后，将产生用户名和密码等账户信息，您可以根据本站规定改变您的密码。用户应谨慎合理的保存、使用其用户名和密码。用户若发现任何非法使用用户账号或存在安全漏洞的情况，请立即通知本站并向公安机关报案。</p>
					<p>3.4用户同意，鑫港物流拥有通过邮件、短信电话等形式，向在本站注册用户、收货人发送订单信息、促销活动等告知信息的权利。</p>
					<p>3.5用户不得将在本站注册获得的账户借给他人使用，否则用户应承担由此产生的全部责任，并与实际使用人承担连带责任。</p>
					<p>3.6用户同意，鑫港物流有权使用用户的注册信息、用户名、密码等信息，登录进入用户的注册账户，进行证据保全，包括但不限于公证、见证等。</p>
					<h4>第4条 用户依法言行义务</h4>
					<p>本协议依据国家相关法律法规规章制定，用户同意严格遵守以下义务：</p>
					<p>（1）不得传输或发表：煽动抗拒、破坏宪法和法律、行政法规实施的言论，煽动颠覆国家政权，推翻社会主义制度的言论，煽动分裂国家、破坏国家统一的的言论，煽动民族仇恨、民族歧视、破坏民族团结的言论；</p>
					<p>（2）从中国大陆向境外传输资料信息时必须符合中国有关法规；</p>
					<p>（3）不得利用本站从事洗钱、窃取商业秘密、窃取个人信息等违法犯罪活动；</p>
					<p>（4）不得干扰本站的正常运转，不得侵入本站及国家计算机信息系统；</p>
					<p>（5）不得传输或发表任何违法犯罪的、骚扰性的、中伤他人的、辱骂性的、恐吓性的、伤害性的、庸俗的，淫秽的、不文明的等信息资料；</p>
					<p>（6）不得传输或发表损害国家社会公共利益和涉及国家安全的信息资料或言论；</p>
					<p>（7）不得教唆他人从事本条所禁止的行为；</p>
					<p>（8）不得利用在本站注册的账户进行牟利性经营活动；</p>
					<p>（9）不得发布任何侵犯他人著作权、商标权等知识产权或合法权利的内容；</p>
					<p>用户应不时关注并遵守本站不时公布或修改的各类合法规则规定。</p>
					<p>本站保有删除站内各类不符合法律政策或不真实的信息内容而无须通知用户的权利。</p>
					<p>若用户未遵守以上规定的，本站有权作出独立判断并采取暂停或关闭用户帐号等措施。用户须对自己在网上的言论和行为承担法律责任。</p>
					<h4>第5条 货物信息</h4>
					<p>本站上的货物等信息随时都有可能发生变动，本站不作特别通知。但由于众所周知的互联网技术因素等客观原因存在，本站网页显示的信息可能会有一定的滞后性或差错，对此情形您知悉并理解；鑫港物流欢迎纠错，并会视情况给予纠错者一定的奖励。</p>
					<h4>第6条 订单</h4>
					<p>6.1在您下订单时，请您仔细确认商品的品类、名称、规格、材质、产地、属性、联系地址、电话等信息。拉货人与用户本人不一致的，拉货人的行为和意思表示视为用户的行为和意思表示，用户应对收货人的行为及意思表示的法律后果承担连带责任。</p>
					<p>6.2除法律另有强制性规定外，双方约定如下：本站上展示的货物等信息仅仅是订单信息的发布，您下单时须填写您希望购买商品的品类、名称、规格、材质、产地、属性、电话、价款及支付方式、收货人、联系方式、收货地址等内容；系统生成的订单信息是计算机信息系统根据您填写的内容自动生成的数据，是您向鑫港物流发出的交易诉求；鑫港物流收到您的订单信息后，会及时联系订单信息上的司机为您提供相关服务，您可以随时登录您在本站注册的账户，查询您的订单状态。</p>
					<h4>第7条 配送</h4>
					<p>7.1如果您的订单上声明了配送需求，我们会及时与您联系，办理配送的相关业务，</p>
					<p>7.2因如下情况造成订单延迟或无法配送等，鑫港物流不承担延迟配送的责任：</p>
					<p>（1）用户提供的信息错误、地址不详细等原因导致的；</p>
					<p>（2）货物送达后无人签收，导致无法配送或延迟配送的；</p>
					<p>（3）情势变更因素导致的；</p>
					<p>（4）不可抗力因素导致的，例如：自然灾害、交通戒严、突发战争等。</p>
					<h4>第8条 所有权及知识产权条款</h4>
					<p>8.1用户一旦接受本协议，即表明该用户主动将其在任何时间段在本站发表的任何形式的信息内容（包括但不限于客户评价、客户咨询、各类话题文章等信息内容）的财产性权利等任何可转让的权利，如著作权财产权（包括并不限于：复制权、发行权、出租权、展览权、表演权、放映权、广播权、信息网络传播权、摄制权、改编权、翻译权、汇编权以及应当由著作权人享有的其他可转让权利），全部独家且不可撤销地转让给鑫港物流所有，用户同意鑫港物流有权就任何主体侵权而单独提起诉讼。</p>
					<p>8.2本协议已经构成《中华人民共和国著作权法》第二十五条（条文序号依照2011年版著作权法确定）及相关法律规定的著作财产权等权利转让书面协议，其效力及于用户在鑫港物流网站上发布的任何受著作权法保护的作品内容，无论该等内容形成于本协议订立前还是本协议订立后。</p>
					<p>8.3用户同意并已充分了解本协议的条款，承诺不将已发表于本站的信息，以任何形式发布或授权其它主体以任何方式使用（包括但不限于在各类网站、媒体上使用）。</p>
					<p>8.4鑫港物流是本站的制作者,拥有此网站内容及资源的著作权等合法权利,受国家法律保护,有权不时地对本协议及本站的内容进行修改，并在本站张贴，无须另行通知用户。在法律允许的最大限度范围内，鑫港物流对本协议及本站内容拥有解释权。</p>
					<p>8.5除法律另有强制性规定外，未经鑫港物流明确的特别书面许可,任何单位或个人不得以任何方式非法地全部或部分复制、转载、引用、链接、抓取或以其他方式使用本站的信息内容，否则，鑫港物流有权追究其法律责任。</p>
					<p>8.6本站所刊登的资料信息（诸如文字、图表、标识、按钮图标、图像、声音文件片段、数字下载、数据编辑和软件），均是鑫港物流或其内容提供者的财产，受中国和国际版权法的保护。本站上所有内容的汇编是鑫港物流的排他财产，受中国和国际版权法的保护。本站上所有软件都是鑫港物流或其关联公司或其软件供应商的财产，受中国和国际版权法的保护。</p>
					<h4>第9条 责任限制及不承诺担保</h4>
					<p>除非另有明确的书面说明,本站及其所包含的或以其它方式通过本站提供给您的全部信息、内容、材料、产品（包括软件）和服务，均是在;按现状;和;按现有;的基础上提供的。</p>
					<p>除非另有明确的书面说明,鑫港物流不对本站的运营及其包含在本网站上的信息、内容、材料、产品（包括软件）或服务作任何形式的、明示或默示的声明或担保（根据中华人民共和国法律另有规定的以外）。</p>
					<p>鑫港物流不担保本站所包含的或以其它方式通过本站提供给您的全部信息、内容、材料、产品（包括软件）和服务、其服务器或从本站发出的电子信件、信息没有病毒或其他有害成分。</p>
					<p>如因不可抗力或其它本站无法控制的原因使本站销售系统崩溃或无法正常使用导致网上交易无法完成或丢失有关的信息、记录等，鑫港物流会合理地尽力协助处理善后事宜。</p>
					<h4>第10条 协议更新及用户关注义务</h4>
					<p>根据国家法律法规变化及网站运营需要，鑫港物流有权对本协议条款不时地进行修改，修改后的协议一旦被张贴在本站上即生效，并代替原来的协议。用户可随时登录查阅最新协议； 用户有义务不时关注并阅读最新版的协议及网站公告。如用户不同意更新后的协议，可以且应立即停止接受鑫港物流网站依据本协议提供的服务；如用户继续使用本网站提供的服务的，即视为同意更新后的协议。鑫港物流建议您在使用本站之前阅读本协议及本站的公告。 如果本协议中任何一条被视为废止、无效或因任何理由不可执行，该条应视为可分的且并不影响任何其余条款的有效性和可执行性。</p>
					<h4>第11条 法律管辖和适用</h4>
					<p>本协议的订立、执行和解释及争议的解决均应适用在中华人民共和国大陆地区适用之有效法律（但不包括其冲突法规则）。
						如发生本协议与适用之法律相抵触时，则这些条款将完全按法律规定重新解释，而其它有效条款继续有效。
						如缔约方就本协议内容或其执行发生任何争议，双方应尽力友好协商解决；协商不成时，任何一方均可向有管辖权的中华人民共和国大陆地区法院提起诉讼。</p>
					<h4>第12条 其他</h4>
					<p>12.1鑫港物流网站所有者是指在政府部门依法许可或备案的鑫港物流网站经营主体。</p>
					<p>12.2鑫港物流尊重用户和消费者的合法权利，本协议及本网站上发布的各类规则、声明等其他内容，均是为了更好的、更加便利的为用户和消费者提供服务。本站欢迎用户和社会各界提出意见和建议，鑫港物流将虚心接受并适时修改本协议及本站上的各类规则。</p>
					<p>12.3本协议内容中以黑体、加粗、下划线、斜体等方式显著标识的条款，请用户着重阅读。</p>
					<p>12.4您点击本协议下方的;同意并继续;按钮即视为您完全接受本协议，在点击之前请您再次确认已知悉并完全理解本协议的全部内容。</p>
					<br /> <br /> <br /> <br />
					<h4 style="text-align: center;">隐私协议</h4>
					<p>鑫港物流（以下或称“我们”）注重保护用户个人信息及个人隐私。本隐私政策解释了用户（“您”）个人信息收集（以下或称“信息”）和使用的有关情况，本隐私政策适用于鑫港物流向您提供的所有相关服务（包括但不限于电子商务、网络资讯、网络社交、互联网金融服务等，以下称“鑫港物流服务”或“服务”）。如果您不同意本隐私政策的任何内容，您应立即停止使用鑫港物流服务。当您使用鑫港物流提供的任一服务时，即表示您已同意我们按照本隐私政策来合法使用和保护您的个人信息。</p>
					<h4>一、您个人信息的收集</h4>
					<p>我们收集信息是为了向您提供更好以及更个性化的服务，并努力提高您的用户体验。我们收集信息的种类如下：</p>
					<p>1、您向我们提供的信息</p>
					<p>当您注册鑫港物流账户及您在使用相关鑫港物流服务时填写、提交及/或其他任何方式提供的信息，包括您的姓名、性别、出生年月日、身份证号码、护照姓、护照名、护照号码、电话号码、电子邮箱、收货地址、鑫港物流钱包或网银在线账号、银行卡信息及相关附加信息（如您地址中的所在的省份和城市、邮政编码等）。您可以选择不提供某一或某些信息，但是这样可能使您无法使用鑫港物流的许多特色服务。请您理解，我们使用您提供的信息是为了回应您的要求，为您在鑫港物流购物或享受服务提供便利，完善鑫港物流网站以及与您进行信息沟通。另外，我们可能会将您所提供的信息与您的鑫港物流账户关联，用以识别您的身份。</p>
					<p>2、我们在您使用服务过程中获得的信息</p>
					<p>为了提高服务质量和用户体验，我们会留存您使用服务以及使用方式的相关信息，这类信息包括：</p>
					<p>（1）您的浏览器和计算机上的信息。在您访问鑫港物流网站或使用鑫港物流服务时，鑫港物流系统自动接收并记录的您的浏览器和计算机上的信息（包括但不限于您的IP地址、浏览器的类型、使用的语言、访问日期和时间、软硬件特征信息及您需求的网页记录等数据）。</p>
					<p>（2）您的位置信息。当您下载或使用鑫港物流、其关联方及合作伙伴开发的应用程序（例如鑫港物流APP），或访问移动网页使用鑫港物流服务时，鑫港物流可能会读取您的位置（大多数移动设备将允许您关闭定位服务，具体建议您联系您的移动设备的服务商或生产商）。</p>
					<p>（3）您的设备信息。</p>
					<p>（4）您的行为或交易信息。鑫港物流可能会记录您访问鑫港物流或使用鑫港物流服务时所进行的操作以及您在鑫港物流网站上进行交易的相关信息。</p>
					<p>除上述信息外，我们还可能为了提供服务及改进服务质量的合理需要而获得的您的其他信息，包括您与我们的客服团队联系时您提供的相关信息，您参与问卷调查时向我们发送的问卷答复信息，以及您与鑫港物流的关联方、鑫港物流合作伙伴之间互动时我们获得的相关信息。同时，为提高您使用鑫港物流提供的服务的安全性，更准确地预防钓鱼网站欺诈和木马病毒，我们可能会通过了解一些您的网络使用习惯、您常用的软件信息等手段来判断您账户的风险，并可能会记录一些我们认为有风险的链接（“URL”）。</p>
					<p>3、来自第三方的信息</p>
					<p>指在您注册鑫港物流账户和使用服务过程中，您授权的鑫港物流可向鑫港物流的关联方、合作伙伴所收集的相关信息，以及您授权的鑫港物流的关联方、合作伙伴可向鑫港物流分享相关的信息。这些信息包括但不限于您的身份信息、行为信息、交易信息、设备信息等，鑫港物流会将此类信息汇总，用于帮助鑫港物流向您提供更好以及更加个性化的服务或更好的预防互联网欺诈。</p>
					<p>您了解并同意，以下信息不适用本隐私政策：</p>
					<p>（1）您在使用鑫港物流提供的搜索服务时输入的关键字信息；</p>
					<p>（2）信用评价、违反法律法规规定或违反鑫港物流平台规则行为及鑫港物流已对您采取的措施；</p>
					<p>（3）应法律法规要求需公示的企业名称等相关工商注册信息以及自然人经营者的信息。</p>
					<p>（4）其他您与鑫港物流或鑫港物流的关联方所签署的协议（包括在线签署的电子协议，例如《鑫港物流用户注册协议》）以及鑫港物流平台规则中明确约定或提示您不适用本隐私政策的与您有关的信息。</p>
					<h4>二、我们对您个人信息的管理和使用</h4>
					<p>为向您提供服务、提升我们的服务质量以及优化您的服务体验，我们会在符合法律规定或根据您授权的情况下使用您的个人信息，并主要用于下列用途：</p>
					<p>1、向您提供您使用的各项服务，并维护、改进这些服务。</p>
					<p>2、向您推荐您可能感兴趣的内容，包括但不限于向您发出产品和服务信息，或通过系统向您展示个性化的第三方推广信息，或在征得您同意的情况下与鑫港物流的合作伙伴共享信息以便他们向您发送有关其产品和服务的信息。如您不希望接收上述信息，可通过相应的退订功能进行退订。</p>
					<p>3、我们可能使用您的个人信息以验证身份、预防、发现、调查欺诈、危害安全、非法或违反与我们或其关联方协议、政策或规则的行为，以保护您、其他鑫港物流用户，或我们或其关联方的合法权益。</p>
					<p>4、我们可能会将来自某项服务的个人信息与来自其他服务所获得的信息结合起来，用于为了给您提供更加个性化的服务使用，例如为让您通过购物拥有更广泛的社交圈而使用、共享或披露您的信息。</p>
					<p>5、我们会对我们的服务使用情况进行统计，并可能会与公众或第三方分享这些统计信息，以展示我们的产品或服务的整体使用趋势。但这些统计信息不包含您的任何身份识别信息。</p>
					<p>6、让您参与有关我们产品及服务的调查。</p>
					<p>7、经您同意或授权的其他用途。</p>
					<h4>三、您个人信息的分享</h4>
					<p>您的个人信息是我们为您提供服务的重要部分，我们会遵循法律规定对您的信息承担保密义务。除以下情形外，我们不会将您的个人信息披露给第三方：</p>
					<p>1、征得您的同意或授权。</p>
					<p>2、根据法律法规的规定或行政或司法机构的要求。</p>
					<p>3、出于实现“我们对您个人信息的管理和使用”部分所述目的，或为履行我们在《鑫港物流用户注册协议》或本隐私政策中的义务和行使我们的权利，向鑫港物流的关联方、合作伙伴或代表鑫港物流履行某项职能的第三方（例如代表我们发出推送通知的通讯服务商、处理银行卡的支付机构等）分享您的个人信息。</p>
					<p>4、如您是适格的知识产权投诉人并已提起投诉，应被投诉人要求，向被投诉人披露，以便双方处理可能产生的权利纠纷。</p>
					<p>5、只有共享您的信息，才能提供您需要的服务，或处理您与他人的纠纷或争议。</p>
					<p>6、您出现违反中国有关法律、法规规定或者违反您与鑫港物流签署的相关协议（包括在线签署的电子协议）或违反相关鑫港物流平台规则时需要向第三方披露的情形。</p>
					<p>7、为维护鑫港物流及其关联方或其他鑫港物流用户的合法权益。
						随着我们业务的发展，我们及我们的关联方有可能进行合并、收购、资产转让或类似的交易，您的个人信息有可能作为此类交易的一部分而被转移。我们将在转移前通知您。
					</p>
					<h4>四、您个人信息的安全</h4>
					<p>鑫港物流严格保护您的个人信息安全。我们使用各种制度、安全技术和程序等措施来保护您的个人信息不被未经授权的访问、篡改、披露或破坏。如果您对我们的个人信息保护有任何疑问，请联系我们的客服。</p>
					<p>在通过鑫港物流网站与第三方进行网上商品或服务的交易时，您不可避免的要向交易对方或潜在的交易对方披露自己的个人信息，如联络方式或者邮政地址等。请您妥善保护自己的个人信息，仅在必要的情形下向他人提供。如您发现自己的个人信息泄密，尤其是你的账户及密码发生泄露，请您立即联络我们的客服，以便我们采取相应措施。</p>
					<h4>五、访问和更新您的个人信息</h4>
					<p>您可以在“用户资料”页面中中查阅您提交给鑫港物流的所有个人信息，你也可通过上述途径更新除实名认证信息之外的其他个人信息（您的实名认证信息是您通过实名认证时使用的姓名和身份证信息），如您需要变更您的实名认证信息，您可拨打xxxx
						服务热线申请变更。</p>
					<h4>六、Cookie 及网络 Beacon的使用</h4>
					<p>1、Cookie的使用</p>
					<p>（1）Cookie是由网页服务器存放在您的访问设备上的文本文件。指定给您的Cookie 是唯一的，它只能被将Cookie发布给您的域中的Web服务器读取。</p>
					<p>（2）鑫港物流使用 Cookie 来帮助您实现您的联机体验的个性化，使您在鑫港物流及其关联方获得更轻松的访问体验。例如，Cookie 会帮助您在后续访问鑫港物流网站时调用您的信息，简化记录您填写个人信息（例如一键登录等）的流程；为您提供安全购物的偏好设置；帮助您优化对广告的选择与互动；保护您的数据安全等。
						您有权接受或拒绝 Cookie。大多数浏览器会自动接受Cookie，但您通常可根据自己的需要来修改浏览器的设置以拒绝 Cookie。如果选择拒绝 Cookie，那么您可能无法完全体验所访问的鑫港物流网站或某些服务的全部功能。
					</p>
					<p>2、网络Beacon的使用</p>
					<p>鑫港物流网页上常会包含一些电子图象（称为"单像素"
						GIF 文件或 "网络 beacon"），它们可以帮助网站计算浏览网页的用户或访问某些cookie。鑫港物流使用网络beacon的方式有：</p>
					<p>（1）鑫港物流通过在鑫港物流网站上使用网络beacon，计算用户访问数量，并通过访问 cookie 辨认注册用户。</p>
					<p>（2）鑫港物流通过得到的cookie信息，可以在鑫港物流网站提供个性化服务。</p>
					<h4>七、未成年人的个人信息保护</h4>
					<p>鑫港物流非常重视对未成年人个人信息的保护。若您是18周岁以下的未成年人，在使用鑫港物流服务前，应事先取得您家长或法定监护人的书面同意。鑫港物流根据国家相关法律法规的规定保护未成年人的个人信息。</p>
					<h4>八、通知和修订</h4>
					<p>为给你提供更好的服务，鑫港物流的业务将不时变化，本隐私政策也将随之调整。鑫港物流会通过在鑫港物流网站、移动端上发出更新版本并提醒您相关内容的更新，也请您访问鑫港物流以便及时了解最新的隐私政策。如果您对于本隐私政策或在使用鑫港物流服务时对于您的个人信息或隐私情况有任何问题，请联系鑫港物流客服并作充分描述，鑫港物流将尽力解决。</p>

				</div>
				<div class="modal-footer ">
					<button type="button " class="btn btn-primary" id="tongyi">同意并且不再提醒</button>
					<button type="button " class="btn btn-primary" id="butongyi"
						style="float">不同意</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<%
		Client client = (Client) request.getSession()
				.getAttribute("client");
	%>
	<%
		if (client.getClientRemark() == null) {
	%>
	<input type="hidden" id="clientId" value="<%=client.getClientId()%>" />
	<script>
		//			$('#myModal').modal('toggle');
		$('#myModal').modal({
			show : true,
			keyboard : false,
			backdrop : 'static',
		});
		$('#tongyi').click(function() {
			$('#myModal').modal('hide')
		});
		$('#butongyi').click(function() {
			$('#myModal').modal('hide')
		});
	</script>
	<!-- /.modal -->
	<%
		}
	%>
</body>
<script>
	$(document)
			.ready(
					function() {
						$("a.1ji").click(function() {
							$(this).next("ol").slideToggle();
						});
						//当点击不同意的时候直接注销
						$("#butongyi")
								.click(
										function() {
											document.location.href = "${pageContext.request.contextPath}/loginAll.do?flag=kehuZhuxiao";
										});
						//当点击同意的时候发送的服务器，不再提醒
						$("#tongyi")
								.click(
										function() {
											document.location.href = "${pageContext.request.contextPath}/client.do?flag=setXieYiNot&clientId="
													+ $("#clientId").val();
										});
					});
</script>




</html>
