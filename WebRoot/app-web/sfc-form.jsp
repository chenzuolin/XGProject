<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.xinggang.project.entity.Client" %>

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
    <link rel="stylesheet" href="app-web/css/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet" href="app-web/css/sidebar.css">
    <link rel="stylesheet" type="text/css" href="app-web/css/jquery.shCircleLoader.css" />
    <script src="http://libs.baidu.com/jquery/2.1.1/jquery.min.js"></script>
    <script src="app-web/js/jquery.shCircleLoader-min.js"></script>
	<script src="app-web/js/jquery.shCircleLoader.js"></script>	
    <script src="app-web/js/bootstrap.min.js"></script>
    <script src="app-web/js/bootstrap-datetimepicker.js"></script>
    <script src="app-web/js/bootstrap-datetimepicker.zh-CN.js"></script>
    
    
    
    <!-- 导出excel插件 -->	
	<script src="cangchu/d/tableExport.js"></script>
	<script src="cangchu/d/jquery.base64.js"></script>
	<script src="cangchu/d/jszip-utils.js"></script>
	<script src="cangchu/d/xlsx.core.min.js"></script>
	<script src="cangchu/jsPDF/jspdf.min.js"></script>
	<script src="cangchu/jsPDF-AutoTable/jspdf.plugin.autotable.js"></script>
	
    <style type="text/css">
    .input-group-addon {
        background: #337AB7;
        color: #fff;
    }
    
    
    .updownPage{

		position: relative;
		width:190px;
		height:60px;
		margin:0 auto;
		top:0px;
	}
	
	.updownPage a{
		float:left;
		margin:0 10px 0 10px;
	}
	.updownPage form{
		width:350px;
		float: left;
	}
	
	.updownPage form input{
		width: 60px;
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
<body onload="showContent();">
	<div id="loader"
		style="position: absolute; left: 48%; top: 40%; color: #337AB7; width: 70px;"></div>
	<script>
		function jiazai() {
			$('#loader').css("display", "block");
			$('#loader').shCircleLoader();
		}
		function jiazaijieshu() {
			$('#loader').css("display", "none");
		}
	</script>
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
                        <ol class="nav nav-second-level collapse">
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
                        <ol class="nav sjzx collapse">
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
                        <ol class="nav nav-second-level collapse">
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
                <li><a>数据中心</a></li>
                <li><a href="javascript:document.location.reload();">收发存报表</a></li>
            </ol>
            <h3>收发存报表</h3>
            <form class="container" style="width: auto; height: auto;">
                <div class='col-md-4 col-xs-6 form-group'>
                    <div class="input-group">
                        <input class="form-control" type="text" id="startTime" placeholder="开始日期" />
                        <label class="glyphicon glyphicon-calendar input-group-addon" for="startTime"></label>
                    </div>
                </div>
                <div class='col-md-4 col-xs-6 form-group'>
                    <div class='input-group date'>
                        <input class="form-control" type="text" id="endTime" placeholder="结束日期" />
                        <label class="glyphicon glyphicon-calendar input-group-addon" for="endTime"></label>
                    </div>
                </div>
                
                <div class='col-md-4 col-xs-6 form-group'>
                    <div class="input-group">
                        <input type="text" class="form-control" id="huowu" placeholder="货物资料">
                        <label class="input-group-addon" for="name">货物资料</label>
                    </div>
                </div>   
                 <a type="button" class="btn btn-warning" style="float: right; margin-right: 15px;" onClick="$('#table-name').tableExport({type:'excel', separator:';', escape:'false'});">导出</a>            
                <a type="button" class="btn btn-warning" id="tijiao" style="float: right; margin-right: 15px;">提交</a>
            </form>
            <div class="panel-body">
                <table class="table table-hover" id="table-name">
                <caption>收发存报表</caption>
                    <thead>
                        <tr style="border-top: 2px solid #000; border-bottom:2px solid #000;">
                            <th>货物品类</th>
                            <th>货物品名</th>
                            <th>期初库存(吨)</th>
                            <th>入库合计(吨)</th>
                            <th>出库合计(吨)</th>
                            <th>期末库存(吨)</th>
                        </tr>
                    </thead>
                    <tbody style="font-size: 14px;" id="contentbody">
			
					</tbody>
                </table>
                           
				<div class="updownPage">
	          		<input type="hidden" id="pageNow" value="1">
					<a class="btn btn-primary" style="cursor:pointer;" onclick="JJpageNow('jian')">上一页</a> 				
					<a class="btn btn-primary" style="cursor:pointer;" onclick="JJpageNow('jia')">下一页</a>					 			
				</div>
            </div>
        </div>
        <!-- 以上是右侧内容页 -->
    </div>
    <!--以下是底部导航 -->
    <div class="container foot">
        <ul class="nav nav-pills nav-justified">
            <li class="active">
                <a href="home.html"><i class="fa fa-home fa-2x"><span class="wenzi">首页</span></i></a>
            </li>
            <li>
                <a href="#"><i class="fa fa-truck fa-2x"><span class="wenzi">仓储</span></i></a>
            </li>
            <li>
                <a href="#"><i class="fa fa-paypal fa-2x"><span class="wenzi">结算</span></i></a>
            </li>
            <li>
                <a href="user-info.html"><i class="fa fa-file-text fa-2x"><span class="wenzi">报表</span></i></a>
            </li>
            <li>
                <a href="mui/examples/setting.html"><i class="fa fa-user fa-2x"><span class="wenzi">我的</span></i></a>
            </li>
        </ul>
    </div>
</body>
<script>
$(document).ready(function() {
    $("a.1ji").click(function() {
        $(this).next("ol").slideToggle()
            // alert("你进入了 p1!");
            // $(this).css("color", "red")

    });
    $("ol.sjzx").slideDown();

});
</script>
<!-- 时间选择插件 -->
<script>
$("#startTime").datetimepicker({
    minView: "month",
    language: 'zh-CN',
    format: 'yyyy-mm-dd',
    autoclose: true,
    todayBtn: "linked",
    todayHighlight: true,
    pickerPosition: "bottom-left"
}).on('changeDate', function(ev) {
    var starttime = $("#startTime").val();
    $("#endTime").datetimepicker('setStartDate', starttime);
    $("#startTime").datetimepicker('hide');
});


$("#endTime").datetimepicker({
    minView: "month",
    language: 'zh-CN',
    format: 'yyyy-mm-dd',
    autoclose: true,
    todayBtn: true,
    pickerPosition: "bottom-left"
}).on('changeDate', function(ev) {
    var starttime = $("#startTime").val();
    var endtime = $("#endTime").val();
    $("#startTime").datetimepicker('setEndDate', endtime);
    $("#endTime").datetimepicker('hide');
});
</script>

</html>

<script>
	$(function() {
	
		$("#startTime").keydown(function(event) {
			if (event.keyCode == 13) {
				$("#tijiao").click();
			}
		});
		$("#endTime").keydown(function(event) {
			if (event.keyCode == 13) {
				$("#tijiao").click();
			}
		});
		$("#huowu").keydown(function(event) {
			if (event.keyCode == 13) {
				$("#tijiao").click();
			}
		});
		$("#huozhu").keydown(function(event) {
			if (event.keyCode == 13) {
				$("#tijiao").click();
			}
		});
		$("#tijiao").click(function() {
			$("#pageNow").val("1");
			showContent();
		});
	});

	function JJpageNow(str) {
		$(function() {
			var pageNow = $("#pageNow").val();
			switch (str) {
			case "jia":
				$("#pageNow").val(parseInt(pageNow) + 1);
				break;
			case "jian":
				$("#pageNow").val(parseInt(pageNow) - 1);
				break;
			}
		});
		showContent();
	}

	function showContent() {
		
		jiazai();
		$(function() {
			$
					.ajax({
						type : "post",
						url : "tidingsAction.do?flag=TongJiXGKHSFCClient&pageNow="
								+ $("#pageNow").val(),
						async : true,
						data : {
							"time" : new Date().getTime(),
							"begin" : $("#startTime").val(),
							"finish" : $("#endTime").val(),
							"goodsName" : $("#huowu").val()
							//"jiancheng" : $("#huozhu").val()
						},
						dataType : "json",
						success : function(obj) {
							jiazaijieshu();
							$("#contentbody").html("");
							if (obj == null) {
								$("#contentbody")
										.append(
												"<tr colspan='13' style='text-align:center;'>无</tr>");
								return false;
							}
							if (obj[0].result == null) {
								$("#contentbody")
										.append(
												"<tr colspan='13' style='text-align:center;'>无</tr>");
								return false;
							}
							for ( var i = 0; i < obj.length; i++) {
								$("#contentbody").append(
										"<tr style='height:40px;'><td>"
												+ obj[i].pinlei + "</td><td>"
												+ obj[i].mingcheng + "</td>"
												+ "<td>" + obj[i].qichu
												+ "</td>" 
												+ "<td>" + obj[i].rukuheji
												+ "</td>" + "<td>"
												+ obj[i].chukuheji
												+ "</td><td>" + obj[i].qimo
												+ "</td></tr>");
							}
							$("#pageNow").val(obj[0].pageNow);
						},
						error : function() {
							alert("无请求数据！");
							jiazaijieshu();
						}
					});
		});

	}
</script>





