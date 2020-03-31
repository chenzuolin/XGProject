<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <!-- This file has been downloaded from bootstrap.cn. Enjoy! -->
    <title>Responsive Navigation Menu - bootstrap.cn</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <link rel="stylesheet" href="Css/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet" href="css/sidebar.css">
    <script src="http://libs.baidu.com/jquery/2.1.1/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="Js/bootstrap-datetimepicker.js"></script>
    <script src="Js/bootstrap-datetimepicker.zh-CN.js"></script>
    <style type="text/css">
    .input-group-addon {
        background: #337AB7;
        color: #fff;
    }
    
    .panel-title {
        font-weight: bold;
    }
    
    .modal-dialog
	{
		width:300px;
		margin: 300px auto;
	}
    </style>
</head>

<body>
    <div class="container">
        <div class="header">
            <a class="brand" href="${pageContext.request.contextPath}/app-web/home.jsp">Brand Logo</a>
            <i class="fa fa-bars fa-2x toggle-btn" data-toggle="collapse" data-target="#main-menu"></i>
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
                            </li>
                        </ol>
                    </li>
                </ol>
            </div>
        </nav>
        <!-- 以上是左侧菜单 -->
        <div class="wrapper-inner">
            <ol class="breadcrumb">
                <span>当前位置：</span>
                <li>数据中心</li>
                <li><a href="${pageContext.request.contextPath}/app-web/indent-search.jsp">订单查询</a></li>
                <li>订单明细</li>
            </ol>
            <h3>订单明细</h3>
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <p class="panel-title" style="text-align: center;">
                        订单编号：<span class="glyphicon glyphicon-chevron-down" style="float: right;"></span>
                    </p>
                </div>
                <div class="panel-body">
                    <table class="table table-hover">
                        <tr>
                            <td>订单创建时间</td>
                            <td>2017/07/11/12:00:00</td>
                        </tr>
                        <tr>
                            <td>业务类型</td>
                            <td>入库订单</td>
                        </tr>
                        <tr>
                            <td>客户名称</td>
                            <td>甘肃嘉利晟</td>
                        </tr>
                        <tr>
                            <td>客户单号</td>
                            <td>0166548</td>
                        </tr>
                        <tr>
                            <td>转入单位</td>
                            <td>甘肃中集</td>
                        </tr>
                        
                        <tr>
                            <td>货物品类</td>
                            <td>建材类</td>
                        </tr>
                        <tr>
                            <td>货物名称</td>
                            <td>螺纹钢</td>
                        </tr>
                        <tr>
                            <td>货物规格</td>
                            <td>12</td>
                        </tr>
                        <tr>
                            <td>货物材质</td>
                            <td>HRB400E</td>
                        </tr>
                        <tr>
                            <td>货物属性</td>
                            <td>定尺9米</td>
                        </tr>
                        <tr>
                            <td>货物产地</td>
                            <td>酒钢</td>
                        </tr>
                        <tr>
                            <td>货物重量</td>
                            <td>500.5吨</td>
                        </tr>
                        <tr>
                            <td>订单状态</td>
                            <td>已完成</td>
                        </tr>
                        <tr>
                            <td>完成时间</td>
                            <td>2017/07/11/12:00:00</td>
                        </tr>
                        <tr>
                            <td>备注</td>
                            <td></td>
                        </tr>
                    </table>
                </div>
            </div>
            <!-- 以上是右侧内容页 -->
        </div>
        <!-- 以上是右侧内容页 -->
    </div>
    <!--以下是底部导航 -->
    <div class="container foot">
        <ul class="nav nav-pills nav-justified">
            <li class="active">
                <a href="#"><i class="fa fa-home fa-2x"><span class="wenzi">首页</span></i></a>
            </li>
            <li>
                <a href="#"><i class="fa fa-truck fa-2x"><span class="wenzi">仓储</span></i></a>
            </li>
            <li>
                <a href="#"><i class="fa fa-paypal fa-2x"><span class="wenzi">结算</span></i></a>
            </li>
            <li>
                <a href="#"><i class="fa fa-file-text fa-2x"><span class="wenzi">报表</span></i></a>
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

</html>
