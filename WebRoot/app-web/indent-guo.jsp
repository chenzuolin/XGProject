<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.xinggang.project.entity.Client" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
<script type="text/javascript">
	$(function(){
		$("#logoout").click(function(){
		$("#disable").modal('show');
	});
	});
</script>
<head>
	<base href="<%=basePath%>">
    <meta charset="utf-8">
    <!-- This file has been downloaded from bootstrap.cn. Enjoy! -->
    <title>鑫港库存管理系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="app-web/css/bootstrap.min.css">
    <link rel="stylesheet" href="app-web/css/font-awesome.min.css">
    <link rel="stylesheet" href="app-web/css/selectize.default.css">
    <link rel="stylesheet" href="app-web/css/sidebar.css">
    <script src="http://libs.baidu.com/jquery/2.1.1/jquery.min.js"></script>
    <script src="app-web/js/bootstrap.min.js"></script>
    <script src="app-web/js/selectize.js"></script>
    <script src="app-web/js/appFaqiguohu.js"></script>
    <style type="text/css">
    .selectize-control.demo-default.single {
        height: 34px;
    }
    
    .input-group>.input-group-addon {
        background: #337AB7;
        color: #fff;
    }
    
    .modal-dialog
	{
		width:300px;
		margin: 300px auto;
	}
    </style>
</head>
<script type="text/javascript">
	$(function(){
		$("#logoout").click(function(){
		$("#disable").modal('show');
	});
	});
</script>
<body onload="selectGoodsPinlei();">
    <div class="container">
         <div class="header">
            <a class="brand" href="${pageContext.request.contextPath}/app-web/home.jsp"><img src="app-web/img/logo.png" width="150px" style="margin:-10px 0 10px 0;" /></a>
            <i class="fa fa-bars fa-2x toggle-btn" data-toggle="collapse" data-target="#main-menu"></i>
           <i class="fa fa-power-off fa-2x" style="float:right;font-size:20px; margin:30px 50px 0 0; color:white; cursor:pointer;" id="logoout">
           <span style="font-size:16px; color:white; margin-left:5px; display:block; float:right; cursor:pointer;">注销</span></i>
            <% Client c = (Client)request.getSession().getAttribute("client"); %>
            <a href="${pageContext.request.contextPath}/client.do?flag=goClientZiliao">
            <i class="fa fa-user fa-2x" style="float:right; font-size:20px; margin:30px 50px 0 0; color:white;"><span style="font-size:16px; color:white; margin-left:5px; 
            display:block; float:right; cursor:pointer;">
            <%=c.getClientFirmName() %></span></i></a>
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
                    <li>
                        <a class="1ji" href="${pageContext.request.contextPath}/app-web/home.jsp"><i class="fa fa-dashboard "></i>首页</a>
                    </li>
                    <li>
                        <a class="1ji"><i class="fa fa-desktop "></i>仓储业务</i></a>
                        <ol class="nav ccyw collapse">
                            <li>
                                <a href="${pageContext.request.contextPath}/app-web/indent-in.jsp"><i class="fa fa-toggle-on"></i>入库订单</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/app-web/indent-out.jsp"><i class="fa fa-bell "></i>出库订单</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/app-web/indent-guo.jsp"><i class="fa fa-circle-o "></i>过户订单</a>
                            </li>
                        </ol>
                    </li>
                    <li>
                        <a class="1ji"><i class="fa fa-paypal "></i>结算中心</a>
                        <ol class="nav nav-second-level collapse">
                            <li>
                                <a href="${pageContext.request.contextPath}/app-web/indent-jiesuan.jsp"><i class="fa fa-coffee"></i>订单结算</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/app-web/jiesuanhuizong.jsp"><i class="fa fa-file-text "></i>结算汇总</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/app-web/payment.jsp"><i class="fa fa-key "></i>缴费记录</a>
                            </li>
                            <!-- 
	                            <li>
	                                <a href="${pageContext.request.contextPath}/app-web/late-fee.jsp"><i class="fa fa-send "></i>滞纳金</a>
	                            </li>
                             -->
                        </ol>
                    </li>
                    <li>
                        <a class="1ji"><i class="fa fa-database "></i>数据中心</a>
                        <ol class="nav nav-second-level collapse">
                            <li>
                                <a href="${pageContext.request.contextPath}/app-web/indent-search.jsp"><i class="fa fa-desktop "></i>订单查询</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/app-web/indent-zuofei.jsp"><i class="fa fa-desktop "></i>作废订单查询</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/app-web/sfc-form.jsp"><i class="fa fa-file-text "></i>收发存报表</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/app-web/inventory.jsp"><i class="fa fa-file-text "></i>库存查询</a>
                            </li>
                        </ol>
                    </li>
                    <li>
                        <a class="1ji"><i class="fa fa-sitemap "></i>系统设置</a>
                        <ol class="nav xtsz collapse">
                            <li>
                                <a href="${pageContext.request.contextPath}/client.do?flag=goClientZiliao"><i class="fa fa-user "></i>用户资料</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/app-web/password.jsp"><i class="fa fa-flask "></i>修改密码</a>
                            </li>
                            <li>
                                <a data-toggle="modal" data-target="#disable"><i class="fa fa-flask "></i>注销系统</a>
                            </li>
                        </ol>
                    </li>
                </ol>
            </div>
        </nav>
        <!-- 模态框（Modal） -->
       <div class="modal fade bs-example-modal-sm " id="disable" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
           <div class="modal-dialog">
               <div class="modal-content">
                   <div class="modal-header">
                       <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                           &times;
                       </button>
                       <h4 class="modal-title" id="myModalLabel">注销系统</h4>
                   </div>
                   <div class="modal-body">
                       <h3>确定要退出吗？</h3>
                   </div>
                   <div class="modal-footer">
                       <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                       </button>
                       <a class="btn btn-primary" href="${pageContext.request.contextPath}/loginAll.do?flag=kehuZhuxiao"/>确定</a>
                   </div>
               </div>
               <!-- /.modal-content -->
           </div>
           <!-- /.modal -->
       </div>
     <!-- 模态框结束 --> 
        <!-- 以上是左侧菜单 -->
        <div class="wrapper-inner">
            <ol class="breadcrumb">
                <span>当前位置：</span>
                <li><a style="cursor:pointer;">仓储业务</a></li>
                <li><a href="javascript:document.location.reload();">过户订单</a></li>
            </ol>
            <h3>过户订单</h3>
            <form name="shiftStockForm"
				action="${pageContext.request.contextPath }/shiftStock.do?flag=saveShiftStockApp"
				method="post" onsubmit="return yanzhen();">
            <div class="dingdanye">
                <div style="margin-top: 20px; height: 50px;">
                        <div class="col-md-4 col-xs-6 form-group">
                            <div class="input-group">
                                <span class="input-group-addon">客户单号</span>
                                <input id="kehuDanhao" type="text" class="form-control" name="sstockClientNumber">

                            </div>
                        </div>
                       <div class="col-md-4 col-xs-6 form-group">
                            <div class="input-group">
                               <span class="input-group-addon">货主</span>
                               <input type="hidden"  value="${clientId}" name="clientBySstockClientId">
                               <input type="text"  readonly="readonly"  class="form-control" value="${danwei}">
                        </div>
                        </div>
                        <div class="col-md-4 col-xs-6 form-group">
                            <div class="input-group">
                                <span class="input-group-addon">转入单位</span>
                                <input id="zhuanDanwei" type="text" class="form-control" name="firm">
                            </div>
                        </div>
                </div>
                 <div class="clearfix"></div>
                <h4 style="margin-left: 20px;">货物信息</h4>
                <div style="margin-top: 20px; height: 200px;">
                         <div class="col-md-4 col-xs-6 form-group">
                            <div class="input-group">
                               <span class="input-group-addon">货物品类</span>
                               <select id="pinlei" class="demo-default PLpinlei" name="goodsCategoryId">
                                    <option>建材类</option>
                                    <option>板材类</option>
                                    <option>型材类</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-4 col-xs-6 form-group">
                            <div class="input-group">
                                <span class="input-group-addon">货物名称</span>
                                <select id="goods-name" class="demo-default MCmingcheng" name="goodsName">
                                    <option>xxxx</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-4 col-xs-6 form-group">
                            <div class="input-group">
                                <span class="input-group-addon">规格</span>
                                <select id="Standard" class="demo-default GGguige" name="goodsStandardId">
                                    <option>xxxxx</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-4 col-xs-6 form-group">
                            <div class="input-group">
                                <span class="input-group-addon">材质</span>
                                <select id="caizhi" class="demo-default CZcaizhi" name="goodsQualityId">
                                   <option>xxxxx</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-4 col-xs-6 form-group">
                            <div class="input-group">
                                <span class="input-group-addon">属性</span>
                                <select id="shuxing" class="demo-default SXshuxing" name="goodsPropertyId">
                                   <option>xxxxx</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-4 col-xs-6 form-group">
                            <div class="input-group">
                                <span class="input-group-addon">产地</span>
                                <select id="chandi" class="demo-default CDchandi" name="goodsYieldlyId">
                                   <option>xxxxx</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-4 col-xs-6 form-group">
                            <div class="input-group">
                                <span class="input-group-addon">过户重量</span>
                                <input type="text" id="weights" class="form-control" name="weight">
                            </div>
                        </div>
                        <div class="col-md-4 col-xs-6 form-group">
                            <div class="input-group">
                                <span class="input-group-addon">备注</span>
                                <input type="text" class="form-control" name="sstockRemark">
                            </div>
                        </div>                         
                </div>
            </div>
            <input type="submit" value="提交" class="btn btn-warning" style="float: right; margin-right: 15px;">
            </form>
        </div>
    </div>
    <!-- 底部导航 -->
    <div class="container-fluid foot">
        <ul class="nav nav-pills nav-justified">
            <li class="active">
                <a href="#"><i class="fa fa-home fa-2x"><span class="wenzi">首页</span></i></a>
            </li>
            <li><a href="#"><i class="fa fa-truck fa-2x"><span class="wenzi">仓储</span></i></a>
            </li>
            <li><a href="#"><i class="fa fa-paypal fa-2x"><span class="wenzi">结算</span></i></a>
            </li>
            <li><a href="#"><i class="fa fa-file-text fa-2x"><span class="wenzi">报表</span></i></a>
            </li>
            <li><a href="mui/examples/setting.html"><i class="fa fa-user fa-2x"><span class="wenzi">我的</span></i></a>
            </li>
        </ul>
    </div>
</body>
<script>
$(document).ready(function() {
    $("a.1ji").click(function() {
        $(this).next("ol").slideToggle();

    });
    $("ol.ccyw").slideDown();

});
</script>
<!-- selectize.js 单选插件 -->


</html>
