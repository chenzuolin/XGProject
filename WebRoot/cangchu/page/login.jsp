<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<meta name="viewport"
	content="minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<link rel="stylesheet" type="text/css" href="cangchu/css/login.css" />
<script type="text/javascript" src="cangchu/js/login.js"></script>
<script type="text/javascript">
	function refreshCc() //刷新图片 
	{
		var ccImg = document.getElementById("img");
		nowTime = new Date();
		ccImg.src = "${pageContext.request.contextPath}/CreateCode?e="
				+ nowTime.getTime();
	}
</script>
</head>
<body ondragstart="return false" style="background: url(cangchu/img/denglubodybg.png) repeat;">
	<form name="loginAllForm"
		action="${pageContext.request.contextPath}/loginAll.do?flag=loginAll"
		method="post" id="submit">
		<div class="content">
			<img src="cangchu/img/logo.png" class="logo" />
			<!-- 进行正常的登录框 -->
			<div class="middle" id="zhengchang">
				<table>
					<tr>
						<td align="right">账号</td>
						<td width="250">
							<div class="zhanghao">
								<img src="cangchu/img/zhanghao.png" /> <input type="text"
									placeholder="请输入账号"  id="loginName"
									autocomplete="off" name="loginName" />
							</div></td>
						<td width="140px">
							<div class="cuowu">
								<img src="cangchu/img/delete_one.png" /> <span> 请输入账号</span>
							</div></td>
					</tr>
					<tr>
						<td align="right">密码</td>
						<td>
							<div class="mima">
								<img src="cangchu/img/mima.png" /> <input type="password"
									placeholder="请输入密码" autocomplete="off"
									id="loginPwd" name="loginPwd" />
							</div></td>
						<td>
							<div class="cuowu">
								<img src="cangchu/img/delete_one.png" /> <span> 请输入密码</span>
							</div></td>
					</tr>
					<tr>
						<td align="right">验证码</td>
						<td>
							<div class="yzm">
								<div class="yanzhengma">
									<input type="text" placeholder="请输入验证码" id="yanzheng"
										name="code" autocomplete="off" />
								</div>
								<img src="<%=basePath%>CreateCode" width="70" height="30"
									id="img" /> <a href="javascript:refreshCc();">换一换</a>
							</div></td>
						<td>
							<div class="cuowu">
								<img src="cangchu/img/delete_one.png" /> <span> 请输入验证码</span>
							</div></td>
					</tr>
					<tr>
						<td></td>
						<td>
							<div class="denglu">
								<div class="anniu_bg">
									<input type="submit" id="login" value="登 录" class="dl" /><input
										type="reset" value="重 填" class="ct" />
								</div>
								<a style="cursor:pointer;" id="wangjipassword">忘记密码？</a>
							</div></td>
						<td></td>
					</tr>
				</table>
				<span class="tishi">${err==null?"":err }</span>
				<div class="bottom_bg">
					<p class="left_bg"></p>
					<p class="right_bg"></p>
				</div>
			</div>
			<!-- 正常登录的框结束 -->
			<!-- 忘记密码框开始 -->
			<div class="middle" id="wangji" style="position: absolute; top: 20%; display: none;">
					<table>
						<tr>
							<td align="right">账号</td>
							<td width="250">
								<div class="zhanghao">
									<img src="cangchu/img/zhanghao.png" />
									<input type="text" placeholder="请输入账号" id="yanloginname" />
								</div>
							</td>
							<td width="140px">
								<div class="cuowu">
									<img src="cangchu/img/delete_one.png" />
									<span> 请输入账号</span>
								</div>
							</td>
						</tr>
						<tr>
							<td align="right">验证码</td>
							<td>
								<div class="yzm">
									<div class="yanzhengma" style="width: 155px;">
										<input type="text" placeholder="请输入验证码" id="huodeyanzheng" />
									</div>
									<a id="huoqu" style="margin-top: -5px; cursor:pointer;">获取验证码</a>
								</div>
							</td>
							<td width="140px">
								<div class="cuowu">
									<img src="cangchu/img/delete_one.png" />
									<span> 请输入验证码</span>
								</div>
							</td>
						</tr>
						<tr>
							<td>
							</td>
							<td>
								<div class="denglu">
									<div class="anniu_bg" style="margin-left: 50px;">
										<input type="button" value="下一步" class="dl" id="downyibu" /><input type="button" value="返 回" class="ct" id="fanhui" />
									</div>
								</div>
							</td>
							<td></td>
						</tr>
					</table>
					<span class="huoqutishi tishi"></span>
					<div class="bottom_bg">
						<p class="left_bg"></p>
						<p class="right_bg"></p>
					</div>
				</div>
				<!-- 忘记密码框结束 -->
				<!-- 修改密码框开始 -->
				<div class="middle" id="update" style="position: absolute; top: 20%; display: none;">
					<table>
						<tr>
							<td align="right">新密码</td>
							<td width="250">
								<div class="zhanghao">
									<img src="cangchu/img/zhanghao.png" />
									<input type="password" placeholder="请输入新密码" id="updatepwd" />
								</div>
							</td>
							<td width="140px">
								<div class="cuowu">
									<img src="cangchu/img/delete_one.png" />
									<span> 请输入新密码</span>
								</div>
							</td>
						</tr>
						<tr>
							<td align="right">确认密码</td>
							<td width="250">
								<div class="zhanghao">
									<img src="cangchu/img/zhanghao.png" />
									<input type="password" placeholder="请输入确认密码" id="updatequerenpwd" />
								</div>
							</td>
							<td width="140px">
								<div class="cuowu">
									<img src="cangchu/img/delete_one.png" />
									<span> 请输入确认密码</span>
								</div>
							</td>
						</tr>
						<tr>
							<td>
							</td>
							<td>
								<div class="denglu">
									<div class="anniu_bg" style="margin-left: 50px;">
										<input type="button" value="提交" class="dl" id="updatetijiao" /><input type="button" value="返 回" class="ct" id="tuichuupdate" />
									</div>
								</div>
							</td>
							<td></td>
						</tr>
					</table>
					<span class="updatetishi tishi"></span>
					<div class="bottom_bg">
						<p class="left_bg"></p>
						<p class="right_bg"></p>
					</div>
				</div>
				<!-- 修改密码框结束 -->
			
		</div>
	</form>
	<div style="position: fixed; right: 0; bottom: 0; min-width: 100%; min-height: 100%; width: auto; 
 				height: auto; z-index: -20;">
		
	</div>
	<video src="<%=basePath %>cangchu/img/150330_Intro_original_handbreak_h264_2000kb.mp4"
		loop="loop" autoplay="autoplay"
		style="position: fixed; right: 0; bottom: 0; min-width: 100%; min-height: 100%; width: auto; 
 				height: auto; z-index: -100; background: url(cangchu/img/denglubodybg.png) repeat; background-size: cover; "></video>
</body>

</html>